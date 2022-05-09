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
import modelo.clases.Promociona;

public class PromocionaDAO implements BDgeneric<Promociona> {

	// MySQL Consultas
<<<<<<< HEAD
	private final String CREATE = "INSERT INTO promociona(idObra, idPatro) VALUES(?, ?)";
	private final String SEARCH = "SELECT * FROM promociona WHERE idObra = ?";
	private final String READALL = "SELECT * FROM promociona";
	private final String UPDATE = "UPDATE promociona SET idPatro = ? WHERE idObra = ? AND idPatro = ?";
	private final String DELETE = "DELETE FROM promociona WHERE idObra = ? AND idPatro = ?";

	// Establecer conexión a la base de datos
	private static Connection con = SQLCon.getConnection();
	private PreparedStatement stat;

	@Override
	public boolean create(Promociona clase) throws SQLException {

		try {
			// Prepare Statement - Create
			stat = con.prepareStatement(CREATE);

			// Si va a insertar más de 1 patro
			if (clase.getIdPatro().size() > 1) {
				int i = 0;
				// Añadir al grupo cada patro
				for (Integer patro : clase.getIdPatro()) {
					// Añadir datos al Prepare Statement
					stat.setInt(1, clase.getIdObra());
					stat.setInt(2, patro);
					// Añadir comando al grupo
					stat.addBatch();
					i++;

					if (i % 1000 == 0 || i == clase.getIdPatro().size()) {
						// Ejecutará los STAT en una sola llamada al servidor
						return stat.executeBatch().length > 0 ? true : false;
						// Según la API si recibe un número mayor que 0 se a ejecutado correctamente

					}
				}
			} else {
				stat.setInt(1, clase.getIdObra());
				stat.setInt(2, clase.getIdPatro().get(0));
			}

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;
=======
	// Insertar Obra - Patrocinador
	private final String CREATE = "INSERT INTO promociona(idObra, idPatro) VALUES(?, ?)";

	// Buscar las obras que promociona un patrocinador
	private final String SEARCHOBRA = "SELECT idObra FROM promociona WHERE idPatro = ?";

	// Buscar los patrocinadores que són promocionados en una obra
	private final String SEARCHPATRO = "SELECT idPatro FROM promociona WHERE idObra = ?";

	// Eliminar datos
	private final String DELETEPROMOCIONA = "DELETE FROM promociona WHERE idObra = ? AND idPatro = ?";
	private final String DELETEPATRO = "DELETE FROM promociona WHERE idPatro = ?";
	private final String DELETEOBRA = "DELETE FROM promociona WHERE idObra = ?";

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
	 * Método para insertar patrocinadores a obras y viceversa, en la relación
	 * "Patrocinador-obra".
	 * 
	 * @param clase la clase promociona que se pasa por parámetros una de las listas
	 *              solo debe tener 1 valor, ese ID será usado para diferenciar si
	 *              debemmos de añadir multiples obras o equipamientos.
	 * @return true si se ha ejecutado correctamente la consulta
	 * 
	 **/
	@Override
	public boolean create(Promociona clase) {

		this.openConnection();

		try (PreparedStatement stat = con.prepareStatement(CREATE)) {
			// Inicio de la transacción
			con.setAutoCommit(false);

			// En el caso de que queramos insertar varias obras en un patrocinador
			if (clase.getIdPatro().size() == 1) {
				for (Integer idObra : clase.getIdObra()) {
					// Añadir datos al Prepare Statement
					stat.setInt(1, idObra);
					stat.setInt(2, clase.getIdPatro().get(0));
					stat.addBatch();
				}

				// Insertar varios patrocinadores en una obra
			} else {
				for (Integer idPatro : clase.getIdPatro()) {

					stat.setInt(1, clase.getIdObra().get(0));
					stat.setInt(2, idPatro);
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
>>>>>>> be8214910679c26ea801d855a873b706a6d01963

		} catch (SQLException e) {
			System.err.println(e);

			return false; // Si hay alguna excepcion devolverá false
<<<<<<< HEAD
		}
	}

	@Override
	public Promociona search(String id) throws SQLException {

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		List<Integer> patroList = new ArrayList<>();
		Promociona pro = null;

		try {
			// Prepare Statement - Search
			stat = con.prepareStatement(SEARCH);

			// Añadir datos al Prepare Statement
			stat.setInt(1, Integer.parseInt(id));

			// Ejecutar consulta y guardarlo en el Result Set
			rs = stat.executeQuery();

			// Comprobar que RS a recuperado informacion del executeQuery
			while (rs.next()) { // Como este objecto tiene una lista por ende la consulta devuelve varios datos
				// Creamos una instancia del objecto y añadimos los valores del RS al objecto
				if (pro == null) {
					pro = new Promociona();
					pro.setIdObra(rs.getInt(1));
				}
				patroList.add(rs.getInt(2));
			}
			if (pro != null && !patroList.isEmpty()) {
				pro.setIdPatro(patroList);
=======
		} finally {
			this.closeConnection();
		}
	}

	/**
	 * Método para leer los datos de la relación dependiendo de que ID se use para
	 * buscar, en el caso de que se busque por obras se recogerán los IDs de los
	 * {@code Patrocinadores} que se patrocinan en esa obra, y viceversa si se busca
	 * por patrocinadores se guardará los IDs de las obras en las que promociona.
	 * 
	 * @param id el ID es un array para saber que ID se quiere buscar, el ID en el
	 *           índice 0: obra, 1: patrocinador.
	 * @return objecto equip-obra con los datos los IDs de equipamientos u obras
	 **/
	@Override
	public Promociona search(String[] id) {

		this.openConnection();

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		Promociona pro = null;
		List<Integer> aux = null, idAux = new ArrayList<>();
		boolean searchObra;

		// Prepare Statement - Search
		try (PreparedStatement obraStat = con.prepareStatement(SEARCHOBRA);
				PreparedStatement patroStat = con.prepareStatement(SEARCHPATRO)) {

			// Si hay ID en el índice 0, buscar por obra
			if (!id[0].isBlank()) {
				obraStat.setString(1, id[0]);
				rs = obraStat.executeQuery();
				searchObra = true;
				// Si no, esta en el índice 1, buscar por equipamiento
			} else {
				patroStat.setString(1, id[1]);
				rs = patroStat.executeQuery();
				searchObra = false;
			}

			// Comprobar que RS a recuperado informacion del executeQuery
			while (rs.next()) {
				// Creamos una instancia del objecto
				if (pro == null) {
					pro = new Promociona();
					aux = new ArrayList<>();
				}
				aux.add(rs.getInt(2));
			}
			// Si el RS está en la última fila
			if (rs.isLast()) {
				// En el caso de que se ha buscado por obras
				if (searchObra) {
					// Añadir ID a la lista
					idAux.add(Integer.parseInt(id[0]));
					// Añadir las listas al objecto
					pro.setIdObra(idAux);
					pro.setIdPatro(aux);
				} else {
					idAux.add(Integer.parseInt(id[1]));
					pro.setIdPatro(idAux);
					pro.setIdObra(aux);
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
		return pro;
	}

<<<<<<< HEAD
	@Override
	public Map<String, Promociona> readAll() throws SQLException {

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		Map<String, Promociona> proMap = new TreeMap<>();
		List<Integer> patro = null;
		Promociona pro = null;

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

				if (pro == null || pro.getIdObra() != rs.getInt(1)) {
					if (pro != null) {
						pro.setIdPatro(patro);
						proMap.put(Integer.toString(pro.getIdObra()), pro);
					}
					// Creamos una instancia del objecto y guardamos solo el ID
					pro = new Promociona();
					patro = new ArrayList<>();
					pro.setIdObra(rs.getInt(1));
				}

				// Comprobamos que el ID que sea igual que el ID de la consulta
				if (pro.getIdObra() == rs.getInt(1)) {
					// Añadimos la patro a la lista
					patro.add(rs.getInt(2));
				}

				if (rs.isLast() == true) {
					pro.setIdPatro(patro);
					proMap.put(Integer.toString(pro.getIdObra()), pro);
				}
			}

		} catch (SQLException e) {
			System.err.println(e);
		}
		return proMap;
	}

	@Override
	public boolean update(Promociona clase) throws SQLException {

		// Clase proor para guardar los antiguos datos
		Promociona pro = this.search(Integer.toString(clase.getIdObra()));

		// Ordenamos las dos listas para que luego comparar con el
		// que hemos recuperado de la base de datos
		clase.sortList();
		pro.sortList();

		try {
			// Prepare Statement - Update
			stat = con.prepareStatement(UPDATE);

			// Si va a proualizar más de 1 patro
			if (clase.getIdPatro().size() > 1) {
				int i = 0;
				// Iterar por las 2 listas para comprobar cual es la que no coincide
				for (int j = 0; j < clase.getIdPatro().size(); j++) {
					if (!pro.getIdPatro().get(j).equals(clase.getIdPatro().get(j))) {

						// Añadir datos al Prepare Statement
						stat.setInt(1, clase.getIdPatro().get(j));
						stat.setInt(2, clase.getIdObra());
						stat.setInt(3, pro.getIdPatro().get(j));
						// Añadimos los comandos al grupo
						stat.addBatch();
						i++;
					}
				}

				if (i % 1000 == 0 || i == clase.getIdPatro().size()) {
					// Ejecutará los batch en una sola llamada al servidor
					return stat.executeBatch().length > 0 ? true : false;
					// Según la API si recibe un número mayor que 0 se a ejecutado correctamente

				}

				// Cuando solo se proualiza 1 patro
			} else {
				stat.setInt(1, clase.getIdPatro().get(0));
				stat.setInt(2, clase.getIdObra());
				stat.setInt(3, pro.getIdPatro().get(0));
			}

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false; // Si hay alguna excepcion devolverá false
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
	public Map<String, Promociona> readAll() {
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
	public boolean update(Promociona clase) {

		this.openConnection();

		try (PreparedStatement delObra = con.prepareStatement(DELETEOBRA);
				PreparedStatement delPatro = con.prepareStatement(DELETEPATRO);
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
				delPatro.setInt(1, clase.getIdPatro().get(0));
				delPatro.executeUpdate();
			}

			// En el caso de que queramos insertar varias obras en un patrocinador
			if (clase.getIdPatro().size() == 1) {
				for (Integer idObra : clase.getIdObra()) {
					// Añadir datos al Prepare Statement
					stat.setInt(1, idObra);
					stat.setInt(2, clase.getIdPatro().get(0));
					stat.addBatch();
				}

				// Insertar varios patrocinadores en una obra
			} else {
				for (Integer idPatro : clase.getIdPatro()) {

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
>>>>>>> be8214910679c26ea801d855a873b706a6d01963
		}

	}

<<<<<<< HEAD
	@Override
	public boolean remove(String[] id) throws SQLException {

		try {
			// Prepare Statement - Delete
			stat = con.prepareStatement(DELETE);
=======
	/**
	 * Método para quitar una promocion de una obra.
	 * 
	 * @param id Los identificadores para eliminar un equip-obra
	 * @return true si se ejecuta correctamente la consulta
	 **/
	@Override
	public boolean remove(String[] id) {

		this.openConnection();

		// Prepare Statement - Delete
		try (PreparedStatement stat = con.prepareStatement(DELETEPROMOCIONA)) {
>>>>>>> be8214910679c26ea801d855a873b706a6d01963

			// Añadir datos al Prepare Statement
			stat.setInt(1, Integer.parseInt(id[0]));
			stat.setInt(2, Integer.parseInt(id[1]));

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false;
<<<<<<< HEAD
=======
		} finally {
			this.closeConnection();
>>>>>>> be8214910679c26ea801d855a873b706a6d01963
		}
	}

}
