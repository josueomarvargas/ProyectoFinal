package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
<<<<<<< HEAD
import java.util.TreeMap;

import controlador.interfaz.BDgeneric;
import controlador.utils.SQLCon;
=======

import controlador.interfaz.BDgeneric;
import controlador.utils.dao.SQLCon;
>>>>>>> be8214910679c26ea801d855a873b706a6d01963
import modelo.clases.EquipObra;

/**
 * La clase {@code EquipObraDAO} es una clase que implementa la interfaz
 * genérica {@link controlador.interfaz.BDgeneric BDgeneric}, esta interfaz crea
 * métodos CRUD necesarios para gestionar tabla {@link modelo.clases.EquipObra
 * EquipOBra}
 * 
 * @author Henrique Yeguo
 * 
 **/
public class EquipObraDAO implements BDgeneric<EquipObra> {

	// MySQL Consultas
<<<<<<< HEAD
	private final String CREATE = "INSERT INTO usa(idObra, idEquip) VALUES(?, ?)";
	private final String SEARCH = "SELECT * FROM usa WHERE idObra = ? AND idEquip";
	private final String READALL = "SELECT * FROM usa";
	private final String UPDATE = "UPDATE usa SET idEquip = ?  WHERE idObra = ? AND idEquip = ?";
	private final String DELETE = "DELETE FROM usa WHERE idObra = ? AND idEquip = ?";

	// Establecer conexión a la base de datos
	private static Connection con = SQLCon.getConnection();
	private PreparedStatement stat;

	@Override
	public boolean create(EquipObra clase) throws SQLException {

		try {
			// Prepare Statement - Create
			stat = con.prepareStatement(CREATE);

			// Añadir datos al Prepare Statement
			stat.setInt(1, clase.getIdObra());
			stat.setInt(2, clase.getIdEquip().get(0));

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false; // Si hay alguna excepcion devolverá false
		}
	}

	@Override
	public EquipObra search(String id) throws SQLException {

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		List<Integer> idEquip = new ArrayList<>();
		EquipObra eo = null;

		try {
			// Prepare Statement - Search
			stat = con.prepareStatement(SEARCH);

			// Añadir datos al Prepare Statement
			stat.setString(1, id);

			// Ejecutar consulta y guardarlo en el Result Set
			rs = stat.executeQuery();

			// Comprobar que RS a recuperado informacion del executeQuery
			while (rs.next()) { // Como en esta tabla uno puede tener varios valores un while en vez de if
				// Creamos una instancia del objecto y añadimos los valores del RS al objecto
				if (eo == null) {
					eo = new EquipObra();
					eo.setIdObra((rs.getInt(1)));
				}
				idEquip.add(rs.getInt(2));
			}
			if (eo != null && idEquip.isEmpty()) {
				eo.setIdEquip(idEquip);
=======
	// Insertar equip - obra
	private final String CREATE = "INSERT INTO usa(idObra, idEquip) VALUES(?, ?)";

	// Buscar las obras que usan un equipamiento específico
	private final String SEARCHOBRA = "SELECT idObra FROM usa WHERE idEquip = ?";

	// BUscar los equipamientos que se usan en esa obra específica
	private final String SEARCHEQUIP = "SELECT idEquip FROM usa WHERE idObra = ?";

	// Eliminar un equip - obra
	private final String DELETEUSA = "DELETE FROM usa WHERE idObra = ? AND idEquip = ?";
	private final String DELETEOBRA = "DELETE FROM usa WHERE idObra = ? AND idEquip = ?";
	private final String DELETEEQUIP = "DELETE FROM usa WHERE idObra = ? AND idEquip = ?";

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
	 * Método para insertar equipamiento a obras y viceversa, en la relación
	 * "Equip-obra".
	 * 
	 * @param clase la clase equiobra que se pasa por parámetros una de las listas
	 *              solo debe tener 1 valor, ese ID será usado para diferenciar si
	 *              debemmos de añadir multiples obras o equipamientos.
	 * @return true si se ha ejecutado correctamente la consulta
	 * 
	 **/
	@Override
	public boolean create(EquipObra clase) {

		this.openConnection();

		// Prepare Statement - Create
		try (PreparedStatement stat = con.prepareStatement(CREATE)) {

			// Inicio de la transacción
			con.setAutoCommit(false);

			// Relacionar varias obras a un equipamiento
			if (clase.getIdEquip().size() == 1) {
				for (Integer idObra : clase.getIdObra()) {
					// Añadir datos al Prepare Statement
					stat.setInt(1, idObra);
					stat.setInt(2, clase.getIdEquip().get(0));
					stat.addBatch();
				}

				// Relacionar varios equipamiento a una obra
			} else {
				for (Integer idEquip : clase.getIdEquip()) {

					stat.setInt(1, clase.getIdObra().get(0));
					stat.setInt(2, idEquip);
					stat.addBatch();
				}
			}

			// Ejecutar consulta
			stat.executeBatch();

			// Aplicar los cambios
			con.commit();

			// Establecer autocommit por defecto
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
	 * Método para leer los datos de la relación dependiendo de que ID se use para
	 * buscar, en el caso de que se busque por obras se recogerán los IDs de los
	 * {@code Equipamientos} que se usan en esa obra, y viceversa si se busca por
	 * equip se guardará los IDs de las obras en las que se usa ese equipamiento.
	 * 
	 * @param id el ID es un array para saber que ID se quiere buscar, el ID en el
	 *           índice 0: obra, 1: equipamiento.
	 * @return objecto equip-obra con los datos los IDs de equipamientos u obras
	 **/
	@Override
	public EquipObra search(String[] id) {

		this.openConnection();

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		EquipObra eo = null;
		List<Integer> aux = null, idAux = new ArrayList<>();
		boolean searchObra;

		// Prepare Statement - Search
		try (PreparedStatement obraStat = con.prepareStatement(SEARCHOBRA);
				PreparedStatement equipStat = con.prepareStatement(SEARCHEQUIP)) {

			// Si hay ID en el índice 0, buscar por obra
			if (!id[0].isBlank()) {
				obraStat.setString(1, id[0]);
				rs = obraStat.executeQuery();
				searchObra = true;
				// Si no, esta en el índice 1, buscar por equipamiento
			} else {
				equipStat.setString(1, id[1]);
				rs = equipStat.executeQuery();
				searchObra = false;
			}

			// Comprobar que RS a recuperado informacion del executeQuery
			while (rs.next()) {
				// Creamos una instancia del objecto
				if (eo == null) {
					eo = new EquipObra();
					aux = new ArrayList<>();
				}
				// Añadir el ID a la lista auxiliar
				aux.add(rs.getInt(1));

				// Si el RS está en la última fila
				if (rs.isLast()) {
					// En el caso de que se ha buscado por obras
					if (searchObra) {
						// Añadir ID a la lista
						idAux.add(Integer.parseInt(id[0]));
						// Añadir las listas al objecto
						eo.setIdObra(idAux);
						eo.setIdEquip(aux);
					} else {
						idAux.add(Integer.parseInt(id[1]));
						eo.setIdEquip(idAux);
						eo.setIdObra(aux);
					}
				}
>>>>>>> be8214910679c26ea801d855a873b706a6d01963
			}

		} catch (SQLException e) {
			System.err.println(e);
<<<<<<< HEAD
=======
		} finally {
			this.closeConnection();
>>>>>>> be8214910679c26ea801d855a873b706a6d01963
		}

		// Devolvemos el objecto, si RS NO ha devuelto nada, devolverá NULL
		return eo;
	}

<<<<<<< HEAD
	@Override
	public Map<String, EquipObra> readAll() throws SQLException {
		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		Map<String, EquipObra> equipObra = new TreeMap<>();
		List<Integer> idEquip = null;
		EquipObra eo = null;

		try {
			// Prepare Statement - Read All
			stat = con.prepareStatement(READALL);

			// Ejecutar consulta y guardarlo en el Result Set
			rs = stat.executeQuery();

			// Comprobar que RS a recuperado informacion del executeQuery
			while (rs.next()) {

				/*
				 * Esto es lo que hará este loop: La primera vez que entre el la condición será
				 * null, por lo tanto entra, la concición de dentro no entrará porque aún sigue
				 * siendo null, creará un objecto donde se guardará su ID y la lista donde
				 * guardaremos los datos. En la siguiente condición comprueba que el ID del
				 * equip coincida con la ID de la consulta y añade el dato a la lista, cuando
				 * vuelva al inicio solo entrará si los IDs no coinciden, si coincide será que
				 * es el mismo ID y querrá decir que hay más datos por introducir, si es
				 * diferente entrará otra vez en la condición como esta vez no es null guarda
				 * los datos en el map y volverá a crear el objeto y la lista. Cuando sea el
				 * último result set guardará los datos al map.
				 */
				if (eo == null || eo.getIdObra() != rs.getInt(1)) {
					if (eo != null) {
						eo.setIdEquip(idEquip);
						equipObra.put(Integer.toString(eo.getIdObra()), eo);
					}
					// Creamos una instancia del objecto y guardamos solo el ID
					eo = new EquipObra();
					idEquip = new ArrayList<>();
					eo.setIdObra(rs.getInt(1));
				}

				// Comprobamos que el ID que sea igual que el ID de la consulta
				if (eo.getIdObra() == rs.getInt(1)) {
					// Añadimos el atributo a la lista
					idEquip.add(rs.getInt(2));
				}

				if (rs.isLast() == true) {
					eo.setIdEquip(idEquip);
					equipObra.put(Integer.toString(eo.getIdObra()), eo);
				}
			}

		} catch (SQLException e) {
			System.err.println(e);
		}
		return equipObra;
	}

	@Override
	public boolean update(EquipObra clase) throws SQLException {

		try {
			// Clase para guardar los antiguos datos
			EquipObra eoAux = this.search(Integer.toString(clase.getIdObra()));

			// Ordenamos las dos listas para que luego comparar con el
			// que hemos recuperado de la base de datos
			clase.sortList();
			eoAux.sortList();

			// Prepare Statement - Update
			stat = con.prepareStatement(UPDATE);

			// Si va a actualizar más de 1 atributo
			if (clase.getIdEquip().size() > 1) {
				int i = 0;
				// Iterar por las 2 listas para comprobar cual es la que no coincide
				for (int j = 0; j < clase.getIdEquip().size(); j++) {
					if (!eoAux.getIdEquip().get(j).equals(clase.getIdEquip().get(j))) {

						// Añadir datos al Prepare Statement
						stat.setInt(1, clase.getIdEquip().get(j));
						stat.setInt(2, clase.getIdObra());
						stat.setInt(3, eoAux.getIdEquip().get(j));
						// Añadimos los comandos al grupo
						stat.addBatch();
						i++;
					}
				}

				if (i % 1000 == 0 || i == clase.getIdEquip().size()) {
					// Ejecutará los batch en una sola llamada al servidor
					return stat.executeBatch().length > 0 ? true : false;
					// Según la API si recibe un número mayor que 0 se a ejecutado correctamente

				}

				// Cuando solo se actualiza 1 atributo
			} else {
				stat.setInt(2, clase.getIdObra());
				stat.setInt(1, clase.getIdEquip().get(0));
				stat.setInt(3, eoAux.getIdEquip().get(0));
			}

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false; // Si hay alguna excepcion devolverá false
		}
	}

	@Override
	public boolean remove(String[] id) throws SQLException {

		try {
			// Prepare Statement - Delete
			stat = con.prepareStatement(DELETE);
=======
	/**
	 * Este método no se usará por lo tanto devolverá null. Al implementar la
	 * interfaz obliga a la clase implementar todos sus métodos, pero en este caso
	 * no necesitamos este método.
	 * 
	 * @return null
	 * @deprecated
	 **/
	@Override
	public Map<String, EquipObra> readAll() {
		return null;
	}

	/**
	 * Método para actualizar los datos ya existentes, lo que se ha hecho es
	 * eliminar los datos previamente, y luego volver a introducirlo con los datos
	 * nuevos. Ya que las claves primarias de esta tabla es compuesta y es
	 * complicado actualizar los datos con un {@code UPDATE}
	 * 
	 * @param clase objecto promociona con los datos nuevos
	 * @return true si se ha ejecutado correctamente la consulta
	 */
	@Override
	public boolean update(EquipObra clase) {
		this.openConnection();

		try (PreparedStatement delObra = con.prepareStatement(DELETEOBRA);
				PreparedStatement delEquip = con.prepareStatement(DELETEEQUIP);
				PreparedStatement stat = con.prepareStatement(CREATE)) {
			// Inicio de la transacción
			con.setAutoCommit(false);

			// Eliminar todos los datos relacionados con una obra
			if (clase.getIdObra().size() == 1) {
				delObra.setInt(1, clase.getIdObra().get(0));
				delObra.executeUpdate();
			}
			// Eliminar todos los datos relacionados con un patrocinador
			else {
				delEquip.setInt(1, clase.getIdEquip().get(0));
				delEquip.executeUpdate();
			}

			// En el caso de que queramos insertar varias obras en un patrocinador
			if (clase.getIdEquip().size() == 1) {
				for (Integer idObra : clase.getIdObra()) {
					// Añadir datos al Prepare Statement
					stat.setInt(1, idObra);
					stat.setInt(2, clase.getIdEquip().get(0));
					stat.addBatch();
				}

				// Insertar varios patrocinadores en una obra
			} else {
				for (Integer idPatro : clase.getIdEquip()) {

					stat.setInt(1, clase.getIdObra().get(0));
					stat.setInt(2, idPatro);
					stat.addBatch();
				}
			}

			// Ejecutar consulta
			stat.executeBatch();

			// Aplicar los cambios
			con.commit();

			// Cambiar a como estaba por defecto

			con.setAutoCommit(true);

			return true;
		} catch (SQLException e) {
			rollback(e);
			return false;

		} finally {
			this.closeConnection();
		}
	}

	/**
	 * Método para quitar el uso del equipamiento de una obra.
	 * 
	 * @param id Los identificadores para eliminar un equip-obra, el primer índice
	 *           0: idObra y el 1: idEquip
	 * @return true si se ejecuta correctamente la consulta
	 **/
	@Override
	public boolean remove(String[] id) {

		this.openConnection();
		
		// Prepare Statement - Delete
		try (PreparedStatement stat = con.prepareStatement(DELETEUSA)) {
>>>>>>> be8214910679c26ea801d855a873b706a6d01963

			// Añadir datos al Prepare Statement
			stat.setString(1, id[0]);
			stat.setString(2, id[1]);

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);
<<<<<<< HEAD

			return false;
=======
			return false;
		}finally {
			this.closeConnection();
>>>>>>> be8214910679c26ea801d855a873b706a6d01963
		}
	}

}
