package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import controlador.interfaz.BDgeneric;
<<<<<<< HEAD
import controlador.utils.SQLCon;
import modelo.clases.Patrocinador;

public class PatrocinadorDAO implements BDgeneric<Patrocinador> {

	// MySQL Consultas
	private final String CREATE = "INSERT INTO patrocinador(idPatro, nombre, cantDinero, condicion) VALUES(?, ?, ?, ?)";
	private final String SEARCH = "SELECT * FROM patrocinador WHERE idPatro = ?";
	private final String READALL = "SELECT * FROM patrocinador";
	private final String UPDATE = "UPDATE patrocinador SET especialidad = ? WHERE idTrabajador = ? AND especialidad = ?";
	private final String DELETE = "DELETE FROM patrocinador WHERE idTrabajador = ?";

	// Establecer conexi�n a la base de datos
	private static Connection con = SQLCon.getConnection();
	private PreparedStatement stat;

	@Override
	public boolean create(Patrocinador clase) throws SQLException {

		try {
			// Prepare Statement - Create
			stat = con.prepareStatement(CREATE);
=======
import controlador.utils.dao.SQLCon;
import modelo.clases.Patrocinador;

/**
 * La clase {@code PatrocinadorDAO} es una clase que implementa la interfaz
 * gen�rica {@link controlador.interfaz.BDgeneric BDgeneric}, esta interfaz crea
 * m�todos CRUD necesarios para gestionar la clase
 * {@link modelo.clases.Patrocinador Patrocinador}
 * 
 * @author Henrique Yeguo
 * 
 **/
public class PatrocinadorDAO implements BDgeneric<Patrocinador> {

	// MySQL Consultas
	// Insertar
	private final String CREATE = "INSERT INTO patrocinador(nombre, cantDinero, condicion, imgPath) VALUES(?, ?, ?, ?)";

	// Buscar un patrocinador
	private final String SEARCH = "SELECT * FROM patrocinador WHERE idPatro = ?";

	// Leer todos los patrocinadores
	private final String READALL = "SELECT * FROM patrocinador";

	// Actualizar un patrocinador
	private final String UPDATE = "UPDATE patrocinador SET nombre = ?, cantDinero = ?, condici�n = ?, imgPath = ? WHERE idPatro = ?";

	// Eliminar un patrocinador
	private final String DELETE = "DELETE FROM patrocinador WHERE idPatro = ?";

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
	 * M�todo para insertar los datos de un patrocinador
	 * 
	 * @param clase objecto con los datos del patrocinador
	 * @return true si se ha ejecutado correctamente la consulta
	 **/
	@Override
	public boolean create(Patrocinador clase) {

		this.openConnection();

		// Prepare Statement - Create
		try (PreparedStatement stat = con.prepareStatement(CREATE);) {
>>>>>>> be8214910679c26ea801d855a873b706a6d01963

			// A�adimos los datos al Prepare Statement
			stat.setInt(1, clase.getIdPatro());
			stat.setString(2, clase.getNombre());
			stat.setInt(3, clase.getCantDinero());
			stat.setString(4, clase.getCondicion());

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
<<<<<<< HEAD
			System.err.println(e);
			return false;
		}
	}

	@Override
	public Patrocinador search(String id) throws SQLException {
=======
			return false;
		} finally {
			this.closeConnection();
		}
	}

	/**
	 * M�todo para buscar un patrocinador mediante su ID.
	 *
	 * @param id identificador que se usar� para buscar un patrocinador
	 * @return patrocinador con los datos recogidos por la consulta
	 **/
	@Override
	public Patrocinador search(String[] id) {

		this.openConnection();
>>>>>>> be8214910679c26ea801d855a873b706a6d01963

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		Patrocinador patro = null;

<<<<<<< HEAD
		try {
			// Prepare Statement - Search
			stat = con.prepareStatement(SEARCH);

			// A�adir datos al Prepare Statement
			stat.setString(1, id);
=======
		try (PreparedStatement stat = con.prepareStatement(SEARCH);) {

			// A�adir datos al Prepare Statement
			stat.setString(1, id[0]);
>>>>>>> be8214910679c26ea801d855a873b706a6d01963

			// Ejecutar consulta y guardarlo en el Result Set
			rs = stat.executeQuery();

			// Comprobar que RS a recuperado informacion del executeQuery
			if (rs.next()) {
				// Creamos una instancia del objecto y a�adimos los valores del RS al objecto
				patro = new Patrocinador();
				patro.setIdPatro(rs.getInt(1));
				patro.setNombre(rs.getString(2));
				patro.setCantDinero(rs.getInt(3));
				patro.setCondicion(rs.getString(4));
<<<<<<< HEAD
=======
				patro.setImgPath(rs.getString(5));
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

		return patro;

	}

<<<<<<< HEAD
	@Override
	public Map<String, Patrocinador> readAll() throws SQLException {

		// RS y la clase para recoger los datos, adem�s un map para guardar
		Map<String, Patrocinador> allDir = new HashMap<>();
		ResultSet rs = null;
		Patrocinador patro = null;

		try {
			// Prepare Statement - ReadAll
			stat = con.prepareStatement(READALL);
=======
	/**
	 * M�todo para recoger la informaci�n de todos los patrocinadores
	 * 
	 * @return Map<Integer, Patrocinador>, map ID del patrocinador como clave y el
	 *         objecto con todos los datos
	 **/
	@Override
	public Map<Integer, Patrocinador> readAll() {

		this.openConnection();

		// RS y la clase para recoger los datos, adem�s un map para guardar
		Map<Integer, Patrocinador> allDir = new HashMap<>();
		ResultSet rs = null;
		Patrocinador patro = null;

		// Prepare Statement - ReadAll
		try (PreparedStatement stat = con.prepareStatement(READALL);) {
>>>>>>> be8214910679c26ea801d855a873b706a6d01963

			// Ejecutar consulta y guardarlo en el result set
			rs = stat.executeQuery();

			// Mientras que RS sigua teniendo filas con informaci�n
			while (rs.next()) {
				// Crearmos una instancia del objecto
				patro = new Patrocinador();
				patro.setIdPatro(rs.getInt(1));
				patro.setNombre(rs.getString(2));
				patro.setCantDinero(rs.getInt(3));
				patro.setCondicion(rs.getString(4));
<<<<<<< HEAD

				// A�adimos la clave y el objecto al map
				allDir.put(Integer.toString(patro.getIdPatro()), patro);
=======
				patro.setImgPath(rs.getString(5));

				// A�adimos la clave y el objecto al map
				allDir.put(patro.getIdPatro(), patro);
>>>>>>> be8214910679c26ea801d855a873b706a6d01963
			}

		} catch (SQLException e) {
			System.out.println(e);
<<<<<<< HEAD
=======
		} finally {
			this.closeConnection();
>>>>>>> be8214910679c26ea801d855a873b706a6d01963
		}

		// Devolver� un map con los datos, o un map vac�o
		return allDir;
	}

<<<<<<< HEAD
	@Override
	public boolean update(Patrocinador clase) throws SQLException {

		try {
			// Prepare Statement - Update
			stat = con.prepareStatement(UPDATE);

			// A�adir datos al Prepare Statement
			stat.setInt(1, clase.getIdPatro());
			stat.setString(2, clase.getNombre());
			stat.setInt(3, clase.getCantDinero());
			stat.setString(4, clase.getCondicion());
=======
	/**
	 * M�todo para guardar los datos de un patrocinador
	 * 
	 * 
	 * @param clase objecto patrocinador con los nuevos datos
	 * @return true si se se ha actualizado correctamente
	 **/
	@Override
	public boolean update(Patrocinador clase) {

		this.openConnection();

		// Prepare Statement - Update
		try (PreparedStatement stat = con.prepareStatement(UPDATE);) {

			// A�adir datos al Prepare Statement
			stat.setString(1, clase.getNombre());
			stat.setInt(2, clase.getCantDinero());
			stat.setString(3, clase.getCondicion());
			stat.setString(4, clase.getImgPath());
			stat.setInt(5, clase.getIdPatro());
>>>>>>> be8214910679c26ea801d855a873b706a6d01963

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
<<<<<<< HEAD
			System.err.println(e);

			return false; // Si hay alguna excepcion devolver� false
=======
			return false; // Si hay alguna excepcion devolver� false

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
	 * M�todo para eliminar un patrocinador
	 * 
	 * 
	 * @param id identificador del patrocinador
	 **/
	@Override
	public boolean remove(String[] id) {

		this.openConnection();

		// Prepare Statement - Delete
		try (PreparedStatement stat = con.prepareStatement(DELETE);) {
>>>>>>> be8214910679c26ea801d855a873b706a6d01963

			// A�adir datos al Prepare Statement
			stat.setString(1, id[0]);

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
<<<<<<< HEAD
			System.err.println(e);

			return false;
=======
			return false;

		} finally {
			this.closeConnection();
>>>>>>> be8214910679c26ea801d855a873b706a6d01963
		}
	}

}
