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
 * La clase {@code UsuarioDAO} es una clase que implementa la interfaz genérica
 * {@link controlador.interfaz.BDgeneric BDgeneric}, esta interfaz crea métodos
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

	// Establecer conexión a la base de datos
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

	// Actualizar el usuario y/o contraseña
	private final String UPDATE = "UPDATE usuario SET idUsuario = ?, passwd = ? WHERE idTrabajador = ?";

	// Eliminar usuario
	private final String DELETE = "DELETE FROM usuario WHERE idTrabajador = ?";

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
	 * Método para insertar los datos del usuario.
	 * 
	 * @param clase usuario con la información que se introducirá en la BD
	 * @return true si se ha ejecutado correctamente la consulta
	 **/
	@Override
	public boolean create(Usuario clase) {

		this.openConnection();

		// Prepare Statement - Create
		try (PreparedStatement stat = con.prepareStatement(CREATE)) {
>>>>>>> be8214910679c26ea801d855a873b706a6d01963

			// Añadir datos al Prepare Statement
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

			return false; // Si hay alguna excepcion devolverá false
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
	 * Método para buscar la información de un usuario mediante su ID trabajador.
	 * 
	 * @param id id del trabajador que se usará para buscar
	 * @return información del usuario
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

			// Añadir datos al Prepare Statement
			stat.setString(1, id);
=======
		// Prepare Statement - Search
		try (PreparedStatement stat = con.prepareStatement(SEARCH)) {

			// Añadir datos al Prepare Statement
			stat.setString(1, id[0]);
>>>>>>> be8214910679c26ea801d855a873b706a6d01963

			// Ejecutar consulta y guardarlo en el Result Set
			rs = stat.executeQuery();

			// Comprobar que RS a recuperado informacion del executeQuery
			if (rs.next()) {
				// Creamos una instancia del objecto y añadimos los valores del RS al objecto
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

		// Devolvemos el objecto, si RS NO ha devuelto nada, devolverá NULL
		return user;
	}

<<<<<<< HEAD
	@Override
	public Map<String, Usuario> readAll() throws SQLException {

		// RS y la clase para recoger los datos, además un map para guardar
		Map<String, Usuario> allUsers = new HashMap<>();
		ResultSet rs = null;
		Usuario user = null;

		try {
			// Prepare Statement - ReadAll
			stat = con.prepareStatement(READALL);

			// Ejecutar consulta y guardarlo en el result set
			rs = stat.executeQuery();

			// Mientras que RS sigua teniendo filas con información
			while (rs.next()) {
				// Crearmos una instancia del objecto
				user = new Usuario();
				user.setIdUsuario(rs.getString(1));
				user.setPasswd(rs.getString(2));
				// Añadimos la clave y el objecto al map
				allUsers.put(user.getIdUsuario(), user);
			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		// Devolverá un map con los datos, o un map vacío
		return allUsers;
	}

	@Override
	public boolean update(Usuario clase) throws SQLException {

		try {
			// Prepare Statement - Update
			stat = con.prepareStatement(UPDATE);

			// Añadir datos al Prepare Statement
			stat.setString(1, clase.getPasswd());
			stat.setString(2, clase.getIdUsuario());
=======
	/**
	 * Este método no se usará por lo tanto devolverá null
	 * 
	 * @return null
	 * @deprecated
	 **/
	@Override
	public Map<String, Usuario> readAll() {
		return null;
	}

	/**
	 * Método para actualizar los datos de un usuario
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

			// Añadir datos al Prepare Statement
			stat.setString(1, clase.getIdUsuario());
			stat.setString(2, clase.getPasswd());
			stat.setInt(2, clase.getIdTrabajador());
>>>>>>> be8214910679c26ea801d855a873b706a6d01963

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);
<<<<<<< HEAD

			return false; // Si hay alguna excepcion devolverá false
=======
			return false; // Si hay alguna excepcion devolverá false
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
	 * Método para eliminar un usuario
	 * 
	 * @param id id identificado que se usará para eliminar el usuario
	 * @return true si se ha ejecutado correctamente la consulta
	 **/
	@Override
	public boolean remove(String[] id) {

		this.openConnection();

		// Prepare Statement - Delete
		try (PreparedStatement stat = con.prepareStatement(DELETE)) {
>>>>>>> be8214910679c26ea801d855a873b706a6d01963

			// Añadir datos al Prepare Statement
			stat.setString(1, id[0]);

			// Ejecutar consulta
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);
<<<<<<< HEAD

			return false; // Si hay alguna excepcion devolverá false
=======
			return false; // Si hay alguna excepcion devolverá false
		} finally {
			this.closeConnection();
>>>>>>> be8214910679c26ea801d855a873b706a6d01963
		}
	}
}
