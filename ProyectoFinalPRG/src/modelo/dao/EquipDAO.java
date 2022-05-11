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

	// Buscar informaci�n de un equipamiento junto con sus caracteristicas
	private final String SEARCH = "SELECT e.*, c.caracteristica FROM equipamiento e INNER JOIN caracteristica c ON c.idEquip = e.idEquip WHERE e.idequip = ?";

	// Leer todo de la tabla equipamiento junto con sus caracteristicas
	private final String READALL = "select e.*, c.caracteristica from equipamiento e INNER JOIN caracteristica c ON c.idEquip = e.idEquip";

	// Actualizaci�n de datos
	private final String UPDATE = "UPDATE equipamiento SET nombre = ?, tipo = ? WHERE idEquip = ?";

	// Eliminar datos
	private final String DELETE = "DELETE FROM equipamiento WHERE idEquip = ?";
	private final String DELETEATRIBUTO = "DELETE FROM caracteristica WHERE idEquip = ?";

	// Establecer conexi�n a la base de datos
	private static Connection con;

	/**
	 * M�todo para abrir la conexi�n, este m�todo llama al
	 * {@link SQLCon#openConnection}.
	 **/
	private void openConnection() {
		con = SQLCon.openConnection();
	}

	/**
	 * M�todo para cerrar la conexi�n, este m�todo llama al
	 * {@link SQLCon#closeConnection()} y {@code con.Close} para cerra la conexi�n
	 * aqu� y en el objecto {@code SQLCon}
	 **/
	private void closeConnection() {
		try {
			// Cerrar la conexi�n aqu� y en el SQLCon
			con.close();
			SQLCon.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo para revertir los cambios
	 * 
	 * @param e Se pasa por par�metros la excepti�n que recoge el catch,
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
	 * M�todo para insertar los datos de un {@code Equipamiento} a su tabla que se
	 * encuentra en el servidor MySQL. <br>
	 * <b>C�mo funciona el m�todo:</b><blockquote>Establecemos el {@code AutoCommit}
	 * como false porque debemos de insertar en las dos tablas, a�adimos los datos a
	 * la consulta y lo ejecutamos, tambi�n recogemos en un {@code ResultSet} el ID
	 * que se ha generado, seguido hay un loop que ir� por cada caracter�stica que
	 * tenga el equipamiento, estas consultas las a�adiremos en un batch, al
	 * finalizar el loop ejecutamos el batch y guardamos los cambios llamando al
	 * {@link java.sql.Connection#commit Commit} </blockquote>
	 * 
	 * 
	 * @param clase el objecto equipamiento con los datos que se introducir�n
	 * @return true si la inserci�n se ha ejecutado correctamente
	 **/
	@Override
	public boolean create(Equipamiento clase) {

		this.openConnection();
		ResultSet rs;

		// Prepare Statement - Create
		try (PreparedStatement stat = con.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS);) {

			// Inicio de la transacci�n
			con.setAutoCommit(false);

			// A�adir datos al Prepare Statement
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
			return false; // Si hay alguna excepcion devolver� false
		} finally {
			this.closeConnection();
		}
	}

	/**
	 * M�todo para insertar los datos de la lista que tienen las clase heredadas de
	 * {@code Trabajador} a las respectivas tablas en el servidor MySQL.
	 * <p>
	 * <b>Como funciona este m�todo:</b> <blockquote> Hay un loop de la lista de
	 * atributos que se a�adir�n al grupo/batch la llamada al procedimiento y al
	 * finalizar se ejecutar� el grupo/batch con todas las las llamadas.
	 * 
	 * </blockquote>
	 * 
	 * @param clase objeto con los datos que se insertar�n
	 * @throws SQLException
	 * 
	 **/
	private void insertAtributos(Integer id, List<String> atributoList) throws SQLException {

		// Prepare Statement - Insertar Atributo
		try (PreparedStatement stat = con.prepareStatement(INSERTATRIBUTO)) {

			// Iteramos por cada atributo
			for (String atributo : atributoList) {

				// A�adir parametros al procedimiento
				stat.setInt(1, id);
				stat.setString(2, atributo);
				// A�adir comando al grupo
				stat.addBatch();
			}
			// Ejecutar� el grupo de consultas
			stat.executeBatch();

		}
	}

	/**
	 * M�todo para buscar la informaci�n del equipamiento a buscar junto con sus
	 * caracteristicas. <br>
	 * <b>C�mo funciona el m�todo:</b><blockquote> Recogemos la consulta en un RS y
	 * comprobamos que nos ha devuelto algo, si hay datos instanciamos un
	 * equipamiento y a�adimos sus datos, ya que un equipamiento puede tener varias
	 * caracter�sticas habr� un do while que las a�adir�a a una lista, luego
	 * insertaremos la lista al equipamiento. </blockquote>
	 * 
	 * @param id identificador que se usar� para buscar
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

			// A�adir datos al Prepare Statement
			stat.setString(1, id[0]);

			// Ejecutar consulta y guardarlo en el Result Set
			rs = stat.executeQuery();

			// Comprobar que RS a recuperado informacion del executeQuery
			if (rs.next()) {
				// Creamos una instancia del objecto
				equip = new Equipamiento();

				// A�adimos los valores del RS al objecto
				equip.setIdEquip(rs.getInt(1));
				equip.setNombre(rs.getString(2));
				equip.setTipo(rs.getString(3));

				caracteristicas = new ArrayList<>();
				// Loop para recorrer todas las caracter�sticas que puede tener
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

		// Devolvemos el objecto, si RS NO ha devuelto nada, devolver� NULL
		return equip;

	}

	/**
	 * M�todo para leer toda la informaci�n de los equipamiento incluyendo sus
	 * caracter�sticas. <br>
	 * <b>C�mo funciona el m�todo:</b><blockquote> Ejecutamos la consulta que nos
	 * devolver� los datos del equipamiento y sus caracter�sticas, por lo que usamos
	 * un while para iterar todas las filas que tiene el {@code ResultSet}, en cada
	 * loop comprobamos si el map ya contiene la clave, si tiene recogemos la lista
	 * y a�adimos m�s datos a la lista, si no existe en el map instanciamos un nuevo
	 * equipamiento y una lista, a�adimos los datos al objecto, y la caracteristica
	 * a la lista, despu�s insertamos la lista al equipamiento, y tambi�n a�adimos
	 * el ID y el objecto al map. </blockquote>
	 * 
	 * 
	 * @return {@code Map<Integer, Equipamiento>} la clave ser� el id del
	 *         equipamiento y el valor el objecto con sus datos
	 **/
	@Override
	public Map<Integer, Equipamiento> readAll() {

		this.openConnection();

		// RS y la clase para recoger los datos, adem�s un map para guardar
		Map<Integer, Equipamiento> allEquip = new HashMap<>();
		ResultSet rs = null;
		Equipamiento equip = null;

		// Lista auxiliar
		List<String> auxCaracteristicas = null;

		// Prepare Statement - ReadAll
		try (PreparedStatement stat = con.prepareStatement(READALL)) {

			// Ejecutar consulta y guardarlo en el result set
			rs = stat.executeQuery();

			// Mientras que RS sigua teniendo filas con informaci�n
			while (rs.next()) {

				// Si el map ya contiene el equipamiento
				if (allEquip.containsKey(rs.getInt(1))) {

					// Recogemos el equipamiento del map
					equip = allEquip.get(rs.getInt(1));

					// Recogemos la lista que tiene el equipamiento
					auxCaracteristicas = equip.getCaracteristicas();

					// A�adir la caracteristica a la lista
					auxCaracteristicas.add(rs.getString(4));

				} else {
					// Crearmos una instancia del objecto y a�adimos los datos
					equip = new Equipamiento();
					equip.setIdEquip(rs.getInt(1));
					equip.setNombre(rs.getString(2));
					equip.setTipo(rs.getString(3));

					// Instanciamos un nuevo arrayList
					auxCaracteristicas = new ArrayList<>();

					// A�adimos la caracteristica a la lista
					auxCaracteristicas.add(rs.getString(4));

				}

				// Insertamos la lista al equipamiento
				equip.setCaracteristicas(auxCaracteristicas);
				// A�adir la clave y el equip al map
				allEquip.put(rs.getInt(1), equip);
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			this.closeConnection();
		}

		// Devolver� un map con los datos, o un map vac�o
		return allEquip;
	}

	/**
	 * Este m�todo sirve para actualizar la informaci�n de un {@code Equipamiento}-
	 * <br>
	 * <b>C�mo funciona el m�todo:</b><blockquote> En el
	 * {@code PreparedStatement stat} introcudimos todos los datos modificables del
	 * equipamiento, ejecutamos la consulta, despues llamamos al m�todo
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

			// Inicio de una transacci�n
			con.setAutoCommit(false);

			// A�adir datos al Prepare Statement
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
			return false; // Si hay alguna excepcion devolver� false
		} finally {
			this.closeConnection();
		}
	}

	/**
	 * M�todo para eliminar toda la informaci�n relacionada sobre un equipamiento.
	 * 
	 * @param id id del equipamiento que se quiere eliminar
	 * @return true si se ha completado la consulta correctamente
	 **/
	@Override
	public boolean remove(String[] id) {

		this.openConnection();

		// Prepare Statement - Delete
		try (PreparedStatement stat = con.prepareStatement(DELETE)) {

			// A�adir datos al Prepare Statement
			stat.setString(1, id[0]);

			// Ejecutar consulta
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			return false; // Si hay alguna excepcion devolver� false
		} finally {
			this.closeConnection();
		}
	}

}
