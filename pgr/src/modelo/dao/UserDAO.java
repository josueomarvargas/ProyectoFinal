package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import controlador.interfaz.BDgeneric;
import controlador.utils.SQLCon;
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

			// Añadir datos al Prepare Statement
			stat.setString(1, clase.getIdUsuario());
			stat.setString(2, clase.getPasswd());

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false; // Si hay alguna excepcion devolverá false
		}

	}

	@Override
	public Usuario search(String id) throws SQLException {

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		Usuario user = null;

		try {
			// Prepare Statement - Search
			stat = con.prepareStatement(SEARCH);

			// Añadir datos al Prepare Statement
			stat.setString(1, id);

			// Ejecutar consulta y guardarlo en el Result Set
			rs = stat.executeQuery();

			// Comprobar que RS a recuperado informacion del executeQuery
			if (rs.next()) {
				// Creamos una instancia del objecto y añadimos los valores del RS al objecto
				user = new Usuario();
				user.setIdUsuario(rs.getString(1));
				user.setPasswd(rs.getString(2));

			}

		} catch (SQLException e) {
			System.err.println(e);
		}

		// Devolvemos el objecto, si RS NO ha devuelto nada, devolverá NULL
		return user;
	}

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

			// Añadir datos al Prepare Statement
			stat.setString(1, id[0]);

			// Ejecutar consulta
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false; // Si hay alguna excepcion devolverá false
		}
	}
}
