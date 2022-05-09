package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
<<<<<<< HEAD
import java.util.HashMap;
import java.util.Map;

import controlador.interfaz.BDgeneric;
import controlador.utils.SQLCon;
=======
import java.util.Map;

import controlador.interfaz.BDgeneric;
import controlador.utils.dao.SQLCon;
>>>>>>> be8214910679c26ea801d855a873b706a6d01963
import modelo.clases.Usuario;

/**
 * La clase {@code UsuarioDAO} es una clase que implementa la interfaz gen�rica
 * {@link controlador.interfaz.BDgeneric BDgeneric}, esta interfaz crea m�todos
 * CRUD necesarios para gestionar la clase {@link modelo.clases.Usuario Usuario}
 * 
 * @author Henrique Yeguo
 * 
 **/
public class UserDAO implements BDgeneric<Usuario> {

	// MySQL Consultas
<<<<<<< HEAD
	private final String CREATE = "INSERT INTO usuario(idUsuario, passwd) VALUES(?, ?)";
	private final String SEARCH = "SELECT * FROM usuario WHERE idUsuario = ?";
	private final String READALL = "SELECT * FROM usuario";
	private final String UPDATE = "UPDATE usuario SET passwd = ? WHERE idUsuario = ?";
	private final String DELETE = "DELETE FROM usuario WHERE idUsuario = ?";

	// Establecer conexi�n a la base de datos
	private static Connection con = SQLCon.getConnection();
	private PreparedStatement stat;

	@Override
	public boolean create(Usuario clase) throws SQLException {

		try {
			// Prepare Statement - Create
			stat = con.prepareStatement(CREATE);
=======
	// Insertar usuarios
	private final String CREATE = "INSERT INTO usuario(idUsuario, passwd, idTrabajdor) VALUES(?, ?, ?)";

	// Buscar buscar info del usuario
	private final String SEARCH = "SELECT * FROM usuario WHERE idTrabajador = ?";

	// Actualizar el usuario y/o contrase�a
	private final String UPDATE = "UPDATE usuario SET idUsuario = ?, passwd = ? WHERE idTrabajador = ?";

	// Eliminar usuario
	private final String DELETE = "DELETE FROM usuario WHERE idTrabajador = ?";

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
	 * M�todo para insertar los datos del usuario.
	 * 
	 * @param clase usuario con la informaci�n que se introducir� en la BD
	 * @return true si se ha ejecutado correctamente la consulta
	 **/
	@Override
	public boolean create(Usuario clase) {

		this.openConnection();

		// Prepare Statement - Create
		try (PreparedStatement stat = con.prepareStatement(CREATE)) {
>>>>>>> be8214910679c26ea801d855a873b706a6d01963

			// A�adir datos al Prepare Statement
			stat.setString(1, clase.getIdUsuario());
			stat.setString(2, clase.getPasswd());
<<<<<<< HEAD

			// Ejecutar consulta y devolver true o false
=======
			stat.setInt(3, clase.getIdTrabajador());

			// Ejecutrar consulta
>>>>>>> be8214910679c26ea801d855a873b706a6d01963
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);
<<<<<<< HEAD

			return false; // Si hay alguna excepcion devolver� false
=======
			return false;
		} finally {
			this.closeConnection();
>>>>>>> be8214910679c26ea801d855a873b706a6d01963
		}

	}

<<<<<<< HEAD
	@Override
	public Usuario search(String id) throws SQLException {
=======
	/**
	 * M�todo para buscar la informaci�n de un usuario mediante su ID trabajador.
	 * 
	 * @param id id del trabajador que se usar� para buscar
	 * @return informaci�n del usuario
	 **/
	@Override
	public Usuario search(String[] id) {

		this.openConnection();
>>>>>>> be8214910679c26ea801d855a873b706a6d01963

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		Usuario user = null;

<<<<<<< HEAD
		try {
			// Prepare Statement - Search
			stat = con.prepareStatement(SEARCH);

			// A�adir datos al Prepare Statement
			stat.setString(1, id);
=======
		// Prepare Statement - Search
		try (PreparedStatement stat = con.prepareStatement(SEARCH)) {

			// A�adir datos al Prepare Statement
			stat.setString(1, id[0]);
>>>>>>> be8214910679c26ea801d855a873b706a6d01963

			// Ejecutar consulta y guardarlo en el Result Set
			rs = stat.executeQuery();

			// Comprobar que RS a recuperado informacion del executeQuery
			if (rs.next()) {
				// Creamos una instancia del objecto y a�adimos los valores del RS al objecto
				user = new Usuario();
				user.setIdUsuario(rs.getString(1));
				user.setPasswd(rs.getString(2));
<<<<<<< HEAD
=======
				user.setIdTrabajador(rs.getInt(3));
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

		// Devolvemos el objecto, si RS NO ha devuelto nada, devolver� NULL
		return user;
	}

<<<<<<< HEAD
	@Override
	public Map<String, Usuario> readAll() throws SQLException {

		// RS y la clase para recoger los datos, adem�s un map para guardar
		Map<String, Usuario> allUsers = new HashMap<>();
		ResultSet rs = null;
		Usuario user = null;

		try {
			// Prepare Statement - ReadAll
			stat = con.prepareStatement(READALL);

			// Ejecutar consulta y guardarlo en el result set
			rs = stat.executeQuery();

			// Mientras que RS sigua teniendo filas con informaci�n
			while (rs.next()) {
				// Crearmos una instancia del objecto
				user = new Usuario();
				user.setIdUsuario(rs.getString(1));
				user.setPasswd(rs.getString(2));
				// A�adimos la clave y el objecto al map
				allUsers.put(user.getIdUsuario(), user);
			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		// Devolver� un map con los datos, o un map vac�o
		return allUsers;
	}

	@Override
	public boolean update(Usuario clase) throws SQLException {

		try {
			// Prepare Statement - Update
			stat = con.prepareStatement(UPDATE);

			// A�adir datos al Prepare Statement
			stat.setString(1, clase.getPasswd());
			stat.setString(2, clase.getIdUsuario());
=======
	/**
	 * Este m�todo no se usar� por lo tanto devolver� null
	 * 
	 * @return null
	 * @deprecated
	 **/
	@Override
	public Map<String, Usuario> readAll() {
		return null;
	}

	/**
	 * M�todo para actualizar los datos de un usuario
	 *
	 * @param clase objecto clase que se usara para actualizar los datos
	 * @return true si la consulta se ha ejecutado correctamente
	 *
	 **/
	@Override
	public boolean update(Usuario clase) {

		this.openConnection();

		// Prepare Statement - Update
		try (PreparedStatement stat = con.prepareStatement(UPDATE)) {

			// A�adir datos al Prepare Statement
			stat.setString(1, clase.getIdUsuario());
			stat.setString(2, clase.getPasswd());
			stat.setInt(2, clase.getIdTrabajador());
>>>>>>> be8214910679c26ea801d855a873b706a6d01963

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);
<<<<<<< HEAD

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
	 * M�todo para eliminar un usuario
	 * 
	 * @param id id identificado que se usar� para eliminar el usuario
	 * @return true si se ha ejecutado correctamente la consulta
	 **/
	@Override
	public boolean remove(String[] id) {

		this.openConnection();

		// Prepare Statement - Delete
		try (PreparedStatement stat = con.prepareStatement(DELETE)) {
>>>>>>> be8214910679c26ea801d855a873b706a6d01963

			// A�adir datos al Prepare Statement
			stat.setString(1, id[0]);

			// Ejecutar consulta
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);
<<<<<<< HEAD

			return false; // Si hay alguna excepcion devolver� false
=======
			return false; // Si hay alguna excepcion devolver� false
		} finally {
			this.closeConnection();
>>>>>>> be8214910679c26ea801d855a873b706a6d01963
		}
	}
}
