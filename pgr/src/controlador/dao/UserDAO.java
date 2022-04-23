package controlador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import controlador.BDgeneric;
import controlador.SQLCon;
import modelo.Usuario;

public class UserDAO implements BDgeneric<Usuario> {

	// MySQL Consultas
	private final String CREATE = "INSERT INTO usuario(idUsuario, passwd) VALUES(?, ?)";
	private final String SEARCH = "SELECT * FROM usuario WHERE idUsuario = ?";
	private final String READALL = "SELECT * FROM usuario";
	private final String UPDATE = "UPDATE usuario SET idUsuario = ?, passwd = ? WHERE idUsuario = ?";
	private final String DELETE = "DELETE FROM usuario WHERE idUsuario = ?";

	// Establecer conexi�n a la base de datos
	private static Connection con = SQLCon.openConnection();
	private PreparedStatement stat;

	@Override
	public boolean create(Usuario clase) throws SQLException {

		try {
			stat = con.prepareStatement(CREATE);
			stat.setString(1, clase.getIdUsuario());
			stat.setString(2, clase.getPasswd());
			stat.executeUpdate();

			return true; // Si no da ning�n error nos devolver� true

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
			stat = con.prepareStatement(SEARCH);
			stat.setString(1, id);
			rs = stat.executeQuery();

			// Comprobar que RS a recuperado informacion del executeQuery
			if (rs.next()) {
				// Creamos una instancia del objecto y a�adimos los valores del RS al objecto
				user = new Usuario();
				user.setIdUsuario(rs.getString(1));
				user.setPasswd(rs.getString(2));

			}

		} catch (Exception e) {
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
			stat = con.prepareStatement(READALL);
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

		} catch (Exception e) {
			System.out.println(e);
		}

		// Devolver� un map con los datos, o un map vac�o
		return allUsers;
	}

	@Override
	public boolean update(Usuario clase) throws SQLException {

		try {
			stat = con.prepareStatement(UPDATE);
			stat.setString(1, clase.getIdUsuario());
			stat.setString(2, clase.getPasswd());
			stat.executeUpdate();

			return true; // Si no da ning�n error nos devolver� true

		} catch (SQLException e) {
			System.err.println(e);

			return false; // Si hay alguna excepcion devolver� false
		}

	}

	@Override
	public boolean remove(String id) throws SQLException {

		try {
			stat = con.prepareStatement(DELETE);
			stat.setString(1, id);
			stat.executeUpdate();

			return true; // Si no da ning�n error nos devolver� true

		} catch (SQLException e) {
			System.err.println(e);

			return false; // Si hay alguna excepcion devolver� false
		}
	}
}
