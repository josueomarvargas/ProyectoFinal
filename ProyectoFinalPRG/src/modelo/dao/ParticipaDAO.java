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
import modelo.clases.Participa;

/**
 * La clase {@code ParticipaDAO} es una clase que implementa la interfaz
 * gen�rica {@link controlador.interfaz.BDgeneric BDgeneric}, esta interfaz crea
 * m�todos CRUD necesarios para gestionar tabla {@link modelo.clases.Participa
 * Participa}
 * 
 * @author Henrique Yeguo
 * 
 **/
public class ParticipaDAO implements BDgeneric<Participa> {

	// MySQL Consultas
<<<<<<< HEAD
	private final String CREATE = "INSERT INTO participa(idTrabajador, idObra) VALUES(?, ?)";
	private final String SEARCH = "SELECT * FROM participa WHERE idObra = ?";
	private final String READALL = "SELECT * FROM participa";
	private final String UPDATE = "UPDATE participa SET idTrabajador = ? WHERE idTrabajador = ? AND idObra = ?";
	private final String DELETE = "DELETE FROM participa WHERE idTrabajador = ?";

	// Establecer conexi�n a la base de datos
	private static Connection con = SQLCon.getConnection();
	private PreparedStatement stat;

	@Override
	public boolean create(Participa clase) throws SQLException {

		try {
			// Prepare Statement - Create
			stat = con.prepareStatement(CREATE);

			// Si va a insertar m�s de 1 idTrabajador
			if (clase.getIdTrabajador().size() > 1) {
				int i = 0;
				// A�adir al grupo cada idTrabajador
				for (Integer idTrabajador : clase.getIdTrabajador()) {
					// A�adir datos al Prepare Statement
					stat.setInt(1, idTrabajador);
					stat.setInt(2, clase.getIdObra());
					// A�adimos los comandos al grupo
					stat.addBatch();
					i++;

					if (i % 1000 == 0 || i == clase.getIdTrabajador().size()) {
						// Ejecutar� los STAT en una sola llamada al servidor
						return stat.executeBatch().length > 0 ? true : false;
						// Seg�n la API si recibe un n�mero mayor que 0 se a ejecutado correctamente

					}
				}
			} else {
				stat.setInt(1, clase.getIdTrabajador().get(0));
				stat.setInt(2, clase.getIdObra());
			}

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false; // Si hay alguna excepcion devolver� false
		}
	}

	@Override
	public Participa search(String id) throws SQLException {

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		List<Integer> idTrabajador = new ArrayList<>();
		Participa part = null;

		try {
			// Prepare Statement - Search
			stat = con.prepareStatement(SEARCH);

			// A�adir datos al Prepare Statement
			stat.setString(1, id);

			// Ejecutar consulta y guardarlo en el Result Set
			rs = stat.executeQuery();

			// Comprobar que RS a recuperado informacion del executeQuery
			while (rs.next()) { // Como este objecto su atributo es una lista un while en vez de if
				// Creamos una instancia del objecto y a�adimos los valores del RS al objecto
				if (part == null) {
					part = new Participa();
					part.setIdObra(rs.getInt(2));
				}
				idTrabajador.add(rs.getInt(1));
			}
			if (part != null && !idTrabajador.isEmpty()) {
				part.setIdTrabajador(idTrabajador);
			}

			return part;
		} catch (SQLException e) {
			System.err.println(e);
			return part;
		}

	}

	@Override
	public Map<String, Participa> readAll() throws SQLException {

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		Map<String, Participa> participantes = new TreeMap<>();
		List<Integer> idTrabajador = null;
		Participa part = null;

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

				if (part == null || part.getIdObra() != rs.getInt(2)) {
					if (part != null) {
						part.setIdTrabajador(idTrabajador);
						participantes.put(Integer.toString(part.getIdObra()), part);
					}
					// Creamos una instancia del objecto y guardamos solo el ID
					part = new Participa();
					idTrabajador = new ArrayList<>();
					part.setIdObra(rs.getInt(2));
				}

				// Comprobamos que el ID que sea igual que el ID de la consulta
				if (part.getIdObra() == rs.getInt(2)) {
					// A�adimos la idTrabajador a la lista
					idTrabajador.add(rs.getInt(1));
				}

				if (rs.isLast() == true) {
					part.setIdTrabajador(idTrabajador);
					participantes.put(Integer.toString(part.getIdObra()), part);
				}
			}

		} catch (SQLException e) {
			System.err.println(e);
		}
		return participantes;
	}

	@Override
	public boolean update(Participa clase) throws SQLException {

		// Clase para guardar los antiguos datos
		Participa partAux = this.search(Integer.toString(clase.getIdObra()));

		// Ordenamos las dos listas para que luego comparar con el
		// que hemos recuperado de la base de datos
		clase.sortList();
		partAux.sortList();

		try {
			// Prepare Statement - Update
			stat = con.prepareStatement(UPDATE);

			// Si va a actualizar m�s de 1 atributo
			if (clase.getIdTrabajador().size() > 1) {
				int i = 0;
				// Iterar por las 2 listas para comprobar cual es la que no coincide
				for (int j = 0; j < clase.getIdTrabajador().size(); j++) {
					if (!partAux.getIdTrabajador().get(j).equals(clase.getIdTrabajador().get(j))) {

						// A�adir datos al Prepare Statement
						stat.setInt(1, clase.getIdTrabajador().get(j));
						stat.setInt(2, partAux.getIdTrabajador().get(j));
						stat.setInt(3, clase.getIdObra());
						// A�adimos los comandos al grupo
						stat.addBatch();
						i++;
					}
				}

				if (i % 1000 == 0 || i == clase.getIdTrabajador().size()) {
					// Ejecutar� los batch en una sola llamada al servidor
					return stat.executeBatch().length > 0 ? true : false;
					// Seg�n la API si recibe un n�mero mayor que 0 se a ejecutado correctamente

				}

				// Cuando solo se actualiza 1 atributo
			} else {
				stat.setInt(1, clase.getIdTrabajador().get(0));
				stat.setInt(3, partAux.getIdTrabajador().get(0));
				stat.setInt(2, clase.getIdObra());
			}

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false; // Si hay alguna excepcion devolver� false
		}

	}

	@Override
	public boolean remove(String[] id) throws SQLException {
		try {
			// Prepare Statement - Delete
			stat = con.prepareStatement(DELETE);
=======
	// Insertar datos
	private final String CREATE = "INSERT INTO participa(idTrabajador, idObra) VALUES(?, ?)";

	// Buscar los trabajadores que han participado en una obra
	private final String SEARCHTRABAJADOR = "SELECT idTrabajador FROM participa WHERE idObra = ?";

	// Buscar las obras que han participado un trabajador
	private final String SEARCHOBRA = "SELECT idObra FROM participa WHERE idTrabajador = ?";

	// Eliimar
	private final String DELETEPARTICIPA = "DELETE FROM participa WHERE idTrabajador = ? AND idObra = ?";
	private final String DELETETRABAJADOR = "DELETE FROM participa WHERE idTrabajador = ?";
	private final String DELETEOBRA = "DELETE FROM participa WHERE idObra = ?";

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
	 * M�todo para insertar participantes a obras y viceversa, en la relaci�n
	 * "Trabajador-obra".
	 * 
	 * @param clase la clase participa que se pasa por par�metros una de las listas
	 *              solo debe tener 1 valor, ese ID ser� usado para diferenciar si
	 *              debemmos de a�adir multiples obras o trabajadores.
	 * @return true si se ha ejecutado correctamente la consulta
	 * 
	 **/
	@Override
	public boolean create(Participa clase) {

		this.openConnection();

		// Prepare Statement - Create
		try (PreparedStatement stat = con.prepareStatement(CREATE)) {

			// Inicio de la transacci�n
			con.setAutoCommit(false);

			if (clase.getIdTrabajador().size() == 1) {
				for (Integer idObra : clase.getIdObra()) {
					stat.setInt(1, clase.getIdTrabajador().get(0));
					stat.setInt(2, idObra);
					stat.addBatch();
				}

			} else {
				for (Integer idTrabajador : clase.getIdTrabajador()) {
					stat.setInt(1, idTrabajador);
					stat.setInt(2, clase.getIdObra().get(0));
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
			return false; // Si hay alguna excepcion devolver� false
		} finally {
			this.closeConnection();
		}
	}

	/**
	 * M�todo para leer los datos de la relaci�n dependiendo de que ID se use para
	 * buscar, en el caso de que se busque por obras se recoger�n los IDs de los
	 * {@code Trabajadores} que se participan en esa obra, y viceversa si se busca
	 * por trabajadores se guardar� los IDs de las obras en las que ha participado
	 * ese trabajador.
	 * 
	 * @param id el ID es un array para saber que ID se quiere buscar, el ID en el
	 *           �ndice 0: obra, 1: trabajador.
	 * @return objecto equip-obra con los datos los IDs de equipamientos u obras
	 **/
	@Override
	public Participa search(String[] id) {

		this.openConnection();

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		List<Integer> aux = null, idAux = new ArrayList<>();
		Participa part = null;
		boolean searchObra;

		try (PreparedStatement obraStat = con.prepareStatement(SEARCHOBRA);
				PreparedStatement trabajadorStat = con.prepareStatement(SEARCHTRABAJADOR)) {

			// Si hay ID en el �ndice 0, buscar por obra
			if (id[0].isBlank()) {
				obraStat.setString(1, id[0]);
				rs = obraStat.executeQuery();
				searchObra = true;
				// Si no, esta en el �ndice 1, buscar por equipamiento
			} else {
				trabajadorStat.setString(1, id[1]);
				rs = trabajadorStat.executeQuery();
				searchObra = false;
			}

			// Comprobar que RS a recuperado informacion del executeQuery
			while (rs.next()) {
				// Creamos una instancia del objecto
				if (part == null) {
					part = new Participa();
					aux = new ArrayList<>();
				}
				// A�adir el ID a la lista auxiliar
				aux.add(rs.getInt(1));

				// Si el RS est� en la �ltima fila
				if (rs.isLast()) {
					// En el caso de que se ha buscado por obras
					if (searchObra) {
						// A�adir ID a la lista
						idAux.add(Integer.parseInt(id[0]));
						// A�adir las listas al objecto
						part.setIdObra(idAux);
						part.setIdTrabajador(aux);
					} else {
						idAux.add(Integer.parseInt(id[1]));
						part.setIdTrabajador(idAux);
						part.setIdObra(aux);
					}
				}
			}

		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			this.closeConnection();
		}

		return part;
	}

	/**
	 * Este m�todo no se usar� por lo tanto devolver� null. Al implementar la
	 * interfaz obliga a la clase implementar todos sus m�todos, pero en este caso
	 * no necesitamos este m�todo.
	 * 
	 * @return null
	 * @deprecated
	 **/
	@Override
	public Map<String, Participa> readAll() {
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
	public boolean update(Participa clase) {
		this.openConnection();

		try (PreparedStatement delObra = con.prepareStatement(DELETEOBRA);
				PreparedStatement delPatro = con.prepareStatement(DELETETRABAJADOR);
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
				delPatro.setInt(1, clase.getIdTrabajador().get(0));
				delPatro.executeUpdate();
			}

			// En el caso de que queramos insertar varias obras en un patrocinador
			if (clase.getIdTrabajador().size() == 1) {
				for (Integer idObra : clase.getIdObra()) {
					// A�adir datos al Prepare Statement
					stat.setInt(1, idObra);
					stat.setInt(2, clase.getIdTrabajador().get(0));
					stat.addBatch();
				}

				// Insertar varios patrocinadores en una obra
			} else {
				for (Integer idPatro : clase.getIdTrabajador()) {

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
	 * M�todo para quitar un trabajador de una obra
	 * 
	 * @param id Los identificadores para eliminar un trabajador de una obra, el
	 *           �ndice 0 del array: idTrabajador, y el 1: idObra
	 * @return true si se ha ejecutado correctamente la consulta
	 **/
	@Override
	public boolean remove(String[] id) {

		this.openConnection();

		// Prepare Statement - Delete
		try (PreparedStatement stat = con.prepareStatement(DELETEPARTICIPA)) {
>>>>>>> be8214910679c26ea801d855a873b706a6d01963

			// A�adir datos al Prepare Statement
			stat.setString(1, id[0]);
			stat.setString(2, id[1]);

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
