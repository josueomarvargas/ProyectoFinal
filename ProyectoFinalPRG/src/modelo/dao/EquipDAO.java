package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controlador.interfaz.BDgeneric;
import controlador.utils.dao.SQLCon;
import modelo.clases.Equipamiento;

public class EquipDAO implements BDgeneric<Equipamiento> {

	// MySQL Consultas
	// Insert Equip
	private final String CREATE = "INSERT INTO equipamiento(nombre, tipo) VALUES( ?, ?)";

	// Insert atributos
	private final String INSERTATRIBUTO = "INSERT INTO caracteristica(idEquip, caracteristica) VALUES(?, ?)";

	// Buscar información de un equipamiento junto con sus caracteristicas
	private final String SEARCH = "SELECT e.*, c.caracteristica FROM equipamiento e INNER JOIN caracteristica c ON c.idEquip = e.idEquip WHERE e.idequip = ?";

	// Leer todo de la tabla equipamiento junto con sus caracteristicas
	private final String READALL = "select e.*, c.caracteristica from equipamiento e INNER JOIN caracteristica c ON c.idEquip = e.idEquip";

	// Actualización de datos
	private final String UPDATE = "UPDATE equipamiento SET nombre = ?, tipo = ? WHERE idEquip = ?";

	// Eliminar datos
	private final String DELETE = "DELETE FROM equipamiento WHERE idEquip = ?";
	private final String DELETEATRIBUTO = "DELETE FROM caracteristica WHERE idEquip = ?";

	// Establecer conexión a la base de datos
	private static Connection con;

	/**
	 * Método para abrir la conexión, este método llama al
	 * {@link SQLCon#openConnection}.
	 **/
	private void openConnection() {
		con = SQLCon.openConnection();
	}

	/**
	 * Método para cerrar la conexión, este método llama al
	 * {@link SQLCon#closeConnection()} y {@code con.Close} para cerra la conexión
	 * aquí y en el objecto {@code SQLCon}
	 **/
	private void closeConnection() {
		try {
			// Cerrar la conexión aquí y en el SQLCon
			con.close();
			SQLCon.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para revertir los cambios
	 * 
	 * @param e Se pasa por parámetros la exceptión que recoge el catch,
	 **/
	private void rollback(Exception e) {
		try {
			// Revertir los cambios en el objecto Connection
			con.rollback();
			con.setAutoCommit(true);
			System.err.println(e + "\nProcediendo a revertir los cambios, iniciando rollback...");
		} catch (SQLException e1) {
			e.printStackTrace();

		}

	}

	/**
	 * Método para insertar los datos de un {@code Equipamiento} a su tabla que se
	 * encuentra en el servidor MySQL. <br>
	 * <b>Cómo funciona el método:</b><blockquote>Establecemos el {@code AutoCommit}
	 * como false porque debemos de insertar en las dos tablas, añadimos los datos a
	 * la consulta y lo ejecutamos, también recogemos en un {@code ResultSet} el ID
	 * que se ha generado, seguido hay un loop que irá por cada característica que
	 * tenga el equipamiento, estas consultas las añadiremos en un batch, al
	 * finalizar el loop ejecutamos el batch y guardamos los cambios llamando al
	 * {@link java.sql.Connection#commit Commit} </blockquote>
	 * 
	 * 
	 * @param clase el objecto equipamiento con los datos que se introducirán
	 * @return true si la inserción se ha ejecutado correctamente
	 **/
	@Override
	public boolean create(Equipamiento clase) {

		this.openConnection();
		ResultSet rs;

		// Prepare Statement - Create
		try (PreparedStatement stat = con.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS);) {

			// Inicio de la transacción
			con.setAutoCommit(false);

			// Añadir datos al Prepare Statement
			stat.setString(1, clase.getNombre());
			stat.setString(2, clase.getTipo());

			// Ejecutar consulta y devolver true o false
			stat.executeUpdate();

			// Recuperar ID generado
			rs = stat.getGeneratedKeys();

			if (rs.next()) {
				insertAtributos(rs.getInt(1), clase.getCaracteristicas());
			}

			// Guardar los cambios
			con.commit();

			// Establecer el autocommit por defecto
			con.setAutoCommit(true);

			return true;

		} catch (SQLException e) {
			rollback(e);
			return false; // Si hay alguna excepcion devolverá false
		} finally {
			this.closeConnection();
		}
	}

	/**
	 * Método para insertar los datos de la lista que tienen las clase heredadas de
	 * {@code Trabajador} a las respectivas tablas en el servidor MySQL.
	 * <p>
	 * <b>Como funciona este método:</b> <blockquote> Hay un loop de la lista de
	 * atributos que se añadirán al grupo/batch la llamada al procedimiento y al
	 * finalizar se ejecutará el grupo/batch con todas las las llamadas.
	 * 
	 * </blockquote>
	 * 
	 * @param clase objeto con los datos que se insertarán
	 * @throws SQLException
	 * 
	 **/
	private void insertAtributos(Integer id, List<String> atributoList) throws SQLException {

		// Prepare Statement - Insertar Atributo
		try (PreparedStatement stat = con.prepareStatement(INSERTATRIBUTO)) {

			// Iteramos por cada atributo
			for (String atributo : atributoList) {

				// Añadir parametros al procedimiento
				stat.setInt(1, id);
				stat.setString(2, atributo);
				// Añadir comando al grupo
				stat.addBatch();
			}
			// Ejecutará el grupo de consultas
			stat.executeBatch();

		}
	}

	/**
	 * Método para buscar la información del equipamiento a buscar junto con sus
	 * caracteristicas. <br>
	 * <b>Cómo funciona el método:</b><blockquote> Recogemos la consulta en un RS y
	 * comprobamos que nos ha devuelto algo, si hay datos instanciamos un
	 * equipamiento y añadimos sus datos, ya que un equipamiento puede tener varias
	 * características habrá un do while que las añadirña a una lista, luego
	 * insertaremos la lista al equipamiento. </blockquote>
	 * 
	 * @param id identificador que se usará para buscar
	 * @return objecto equipamiento con los datos recogidos de la base de datos
	 **/
	@Override
	public Equipamiento search(String[] id) {

		this.openConnection();

		// ResultSet, una lista y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		Equipamiento equip = null;
		List<String> caracteristicas = null;

		try (PreparedStatement stat = con.prepareStatement(SEARCH);) {

			// Añadir datos al Prepare Statement
			stat.setString(1, id[0]);

			// Ejecutar consulta y guardarlo en el Result Set
			rs = stat.executeQuery();

			// Comprobar que RS a recuperado informacion del executeQuery
			if (rs.next()) {
				// Creamos una instancia del objecto
				equip = new Equipamiento();

				// Añadimos los valores del RS al objecto
				equip.setIdEquip(rs.getInt(1));
				equip.setNombre(rs.getString(2));
				equip.setTipo(rs.getString(3));

				caracteristicas = new ArrayList<>();
				// Loop para recorrer todas las características que puede tener
				do {
					caracteristicas.add(rs.getString(4));
				} while (rs.next());

				equip.setCaracteristicas(caracteristicas);
			}

		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			this.closeConnection();
		}

		// Devolvemos el objecto, si RS NO ha devuelto nada, devolverá NULL
		return equip;

	}

	/**
	 * Método para leer toda la información de los equipamiento incluyendo sus
	 * características. <br>
	 * <b>Cómo funciona el método:</b><blockquote> Ejecutamos la consulta que nos
	 * devolverá los datos del equipamiento y sus características, por lo que usamos
	 * un while para iterar todas las filas que tiene el {@code ResultSet}, en cada
	 * loop comprobamos si el map ya contiene la clave, si tiene recogemos la lista
	 * y añadimos más datos a la lista, si no existe en el map instanciamos un nuevo
	 * equipamiento y una lista, añadimos los datos al objecto, y la caracteristica
	 * a la lista, después insertamos la lista al equipamiento, y también añadimos
	 * el ID y el objecto al map. </blockquote>
	 * 
	 * 
	 * @return {@code Map<Integer, Equipamiento>} la clave será el id del
	 *         equipamiento y el valor el objecto con sus datos
	 **/
	@Override
	public Map<Integer, Equipamiento> readAll() {

		this.openConnection();

		// RS y la clase para recoger los datos, además un map para guardar
		Map<Integer, Equipamiento> allEquip = new HashMap<>();
		ResultSet rs = null;
		Equipamiento equip = null;

		// Lista auxiliar
		List<String> auxCaracteristicas = null;

		// Prepare Statement - ReadAll
		try (PreparedStatement stat = con.prepareStatement(READALL)) {

			// Ejecutar consulta y guardarlo en el result set
			rs = stat.executeQuery();

			// Mientras que RS sigua teniendo filas con información
			while (rs.next()) {

				// Si el map ya contiene el equipamiento
				if (allEquip.containsKey(rs.getInt(1))) {

					// Recogemos el equipamiento del map
					equip = allEquip.get(rs.getInt(1));

					// Recogemos la lista que tiene el equipamiento
					auxCaracteristicas = equip.getCaracteristicas();

					// Añadir la caracteristica a la lista
					auxCaracteristicas.add(rs.getString(4));

				} else {
					// Crearmos una instancia del objecto y añadimos los datos
					equip = new Equipamiento();
					equip.setIdEquip(rs.getInt(1));
					equip.setNombre(rs.getString(2));
					equip.setTipo(rs.getString(3));

					// Instanciamos un nuevo arrayList
					auxCaracteristicas = new ArrayList<>();

					// Añadimos la caracteristica a la lista
					auxCaracteristicas.add(rs.getString(4));

				}

				// Insertamos la lista al equipamiento
				equip.setCaracteristicas(auxCaracteristicas);
				// Añadir la clave y el equip al map
				allEquip.put(rs.getInt(1), equip);
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			this.closeConnection();
		}

		// Devolverá un map con los datos, o un map vacío
		return allEquip;
	}

	/**
	 * Este método sirve para actualizar la información de un {@code Equipamiento}-
	 * <br>
	 * <b>Cómo funciona el método:</b><blockquote> En el
	 * {@code PreparedStatement stat} introcudimos todos los datos modificables del
	 * equipamiento, ejecutamos la consulta, despues llamamos al método
	 * {@link #DELETEATRIBUTO} para eliminar los atributos de la tabla, luego
	 * llamamos al {@link #INSERTATRIBUTO} para insertar todos los atributos de la
	 * lista, y una vez finalizado aplicamos los cambios llamando al
	 * {@link java.sql.Connection#commit Commit} </blockquote>
	 *
	 * @param clase la clase trabajador con los datos nuevos que quieren actualizar
	 * @return true si se ha ejecutado correctamente
	 **/
	@Override
	public boolean update(Equipamiento clase) {

		this.openConnection();

		// Prepare Statement - Update
		try (PreparedStatement stat = con.prepareStatement(UPDATE);
				PreparedStatement deleteStat = con.prepareStatement(DELETEATRIBUTO)) {

			// Inicio de una transacción
			con.setAutoCommit(false);

			// Añadir datos al Prepare Statement
			stat.setString(1, clase.getNombre());
			stat.setString(2, clase.getTipo());
			stat.setInt(3, clase.getIdEquip());

			// Ejecutar consulta
			stat.executeUpdate();

			// Eliminar los atributos de los equipamientos
			deleteStat.setInt(1, clase.getIdEquip());
			deleteStat.executeUpdate();

			// Volver a insertar los atributos
			insertAtributos(clase.getIdEquip(), clase.getCaracteristicas());

			// Guardar los cambios de la consulta
			con.commit();

			// Cambiar a como estaba por defecto
			con.setAutoCommit(true);

			return true;

		} catch (SQLException e) {
			rollback(e);
			return false; // Si hay alguna excepcion devolverá false
		} finally {
			this.closeConnection();
		}
	}

	/**
	 * Método para eliminar toda la información relacionada sobre un equipamiento.
	 * 
	 * @param id id del equipamiento que se quiere eliminar
	 * @return true si se ha completado la consulta correctamente
	 **/
	@Override
	public boolean remove(String[] id) {

		this.openConnection();

		// Prepare Statement - Delete
		try (PreparedStatement stat = con.prepareStatement(DELETE)) {

			// Añadir datos al Prepare Statement
			stat.setString(1, id[0]);

			// Ejecutar consulta
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			return false; // Si hay alguna excepcion devolverá false
		} finally {
			this.closeConnection();
		}
	}

}
