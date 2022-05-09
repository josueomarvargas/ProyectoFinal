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

	// Establecer conexi�n a la base de datos
	private static Connection con = SQLCon.getConnection();
	private PreparedStatement stat;

	@Override
	public boolean create(Promociona clase) throws SQLException {

		try {
			// Prepare Statement - Create
			stat = con.prepareStatement(CREATE);

			// Si va a insertar m�s de 1 patro
			if (clase.getIdPatro().size() > 1) {
				int i = 0;
				// A�adir al grupo cada patro
				for (Integer patro : clase.getIdPatro()) {
					// A�adir datos al Prepare Statement
					stat.setInt(1, clase.getIdObra());
					stat.setInt(2, patro);
					// A�adir comando al grupo
					stat.addBatch();
					i++;

					if (i % 1000 == 0 || i == clase.getIdPatro().size()) {
						// Ejecutar� los STAT en una sola llamada al servidor
						return stat.executeBatch().length > 0 ? true : false;
						// Seg�n la API si recibe un n�mero mayor que 0 se a ejecutado correctamente

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

	// Buscar los patrocinadores que s�n promocionados en una obra
	private final String SEARCHPATRO = "SELECT idPatro FROM promociona WHERE idObra = ?";

	// Eliminar datos
	private final String DELETEPROMOCIONA = "DELETE FROM promociona WHERE idObra = ? AND idPatro = ?";
	private final String DELETEPATRO = "DELETE FROM promociona WHERE idPatro = ?";
	private final String DELETEOBRA = "DELETE FROM promociona WHERE idObra = ?";

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
	 * M�todo para insertar patrocinadores a obras y viceversa, en la relaci�n
	 * "Patrocinador-obra".
	 * 
	 * @param clase la clase promociona que se pasa por par�metros una de las listas
	 *              solo debe tener 1 valor, ese ID ser� usado para diferenciar si
	 *              debemmos de a�adir multiples obras o equipamientos.
	 * @return true si se ha ejecutado correctamente la consulta
	 * 
	 **/
	@Override
	public boolean create(Promociona clase) {

		this.openConnection();

		try (PreparedStatement stat = con.prepareStatement(CREATE)) {
			// Inicio de la transacci�n
			con.setAutoCommit(false);

			// En el caso de que queramos insertar varias obras en un patrocinador
			if (clase.getIdPatro().size() == 1) {
				for (Integer idObra : clase.getIdObra()) {
					// A�adir datos al Prepare Statement
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

			return false; // Si hay alguna excepcion devolver� false
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

			// A�adir datos al Prepare Statement
			stat.setInt(1, Integer.parseInt(id));

			// Ejecutar consulta y guardarlo en el Result Set
			rs = stat.executeQuery();

			// Comprobar que RS a recuperado informacion del executeQuery
			while (rs.next()) { // Como este objecto tiene una lista por ende la consulta devuelve varios datos
				// Creamos una instancia del objecto y a�adimos los valores del RS al objecto
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
	 * M�todo para leer los datos de la relaci�n dependiendo de que ID se use para
	 * buscar, en el caso de que se busque por obras se recoger�n los IDs de los
	 * {@code Patrocinadores} que se patrocinan en esa obra, y viceversa si se busca
	 * por patrocinadores se guardar� los IDs de las obras en las que promociona.
	 * 
	 * @param id el ID es un array para saber que ID se quiere buscar, el ID en el
	 *           �ndice 0: obra, 1: patrocinador.
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

			// Si hay ID en el �ndice 0, buscar por obra
			if (!id[0].isBlank()) {
				obraStat.setString(1, id[0]);
				rs = obraStat.executeQuery();
				searchObra = true;
				// Si no, esta en el �ndice 1, buscar por equipamiento
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
			// Si el RS est� en la �ltima fila
			if (rs.isLast()) {
				// En el caso de que se ha buscado por obras
				if (searchObra) {
					// A�adir ID a la lista
					idAux.add(Integer.parseInt(id[0]));
					// A�adir las listas al objecto
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
				 * Esto es lo que har� este loop: La primera vez que entre el la condici�n ser�
				 * null, por lo tanto entra, la concici�n de dentro no entrar� porque a�n sigue
				 * siendo null, crear� un objecto donde se guardar� su ID y la lista donde
				 * guardaremos los datos. En la siguiente condici�n comprueba que el ID del
				 * equip coincida con la ID de la consulta y a�ade el dato a la lista, cuando
				 * vuelva al inicio solo entrar� si los IDs no coinciden, si coincide ser� que
				 * es el mismo ID y querr� decir que hay m�s datos por introducir, si es
				 * diferente entrar� otra vez en la condici�n como esta vez no es null guarda
				 * los datos en el map y volver� a crear el objeto y la lista. Cuando sea el
				 * �ltimo result set guardar� los datos al map.
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
					// A�adimos la patro a la lista
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

			// Si va a proualizar m�s de 1 patro
			if (clase.getIdPatro().size() > 1) {
				int i = 0;
				// Iterar por las 2 listas para comprobar cual es la que no coincide
				for (int j = 0; j < clase.getIdPatro().size(); j++) {
					if (!pro.getIdPatro().get(j).equals(clase.getIdPatro().get(j))) {

						// A�adir datos al Prepare Statement
						stat.setInt(1, clase.getIdPatro().get(j));
						stat.setInt(2, clase.getIdObra());
						stat.setInt(3, pro.getIdPatro().get(j));
						// A�adimos los comandos al grupo
						stat.addBatch();
						i++;
					}
				}

				if (i % 1000 == 0 || i == clase.getIdPatro().size()) {
					// Ejecutar� los batch en una sola llamada al servidor
					return stat.executeBatch().length > 0 ? true : false;
					// Seg�n la API si recibe un n�mero mayor que 0 se a ejecutado correctamente

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

			return false; // Si hay alguna excepcion devolver� false
=======
	/**
	 * Este m�todo no se usar� por lo tanto devolver� null. Al implementar la
	 * interfaz obliga a la clase implementar todos sus m�todos, pero en este caso
	 * no necesitamos este m�todo.
	 * 
	 * @return null
	 * @deprecated
	 **/
	@Override
	public Map<String, Promociona> readAll() {
		return null;
	}

	/**
	 * M�todo para actualizar los datos ya existentes, lo que se ha hecho es
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
			// Inicio de la transacci�n
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
					// A�adir datos al Prepare Statement
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
	 * M�todo para quitar una promocion de una obra.
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

			// A�adir datos al Prepare Statement
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
