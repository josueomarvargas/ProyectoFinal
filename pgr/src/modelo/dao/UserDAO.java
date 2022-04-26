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

public class UserDAO implements BDgeneric<Usuario> {

	// MySQL Consultas
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

			// A�adir datos al Prepare Statement
			stat.setString(1, clase.getIdUsuario());
			stat.setString(2, clase.getPasswd());

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false; // Si hay alguna excepcion devolver� false
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

			// A�adir datos al Prepare Statement
			stat.setString(1, id);

			// Ejecutar consulta y guardarlo en el Result Set
			rs = stat.executeQuery();

			// Comprobar que RS a recuperado informacion del executeQuery
			if (rs.next()) {
				// Creamos una instancia del objecto y a�adimos los valores del RS al objecto
				user = new Usuario();
				user.setIdUsuario(rs.getString(1));
				user.setPasswd(rs.getString(2));

			}

		} catch (SQLException e) {
			System.err.println(e);
		}

		// Devolvemos el objecto, si RS NO ha devuelto nada, devolver� NULL
		return user;
	}

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

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false; // Si hay alguna excepcion devolver� false
		}

	}

	@Override
	public boolean remove(String id) throws SQLException {

		try {

			// Prepare Statement - Delete
			stat = con.prepareStatement(DELETE);

			// A�adir datos al Prepare Statement
			stat.setString(1, id);

			// Ejecutar consulta
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false; // Si hay alguna excepcion devolver� false
		}
	}
}