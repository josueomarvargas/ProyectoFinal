package modelo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import controlador.interfaz.BDgeneric;
import controlador.interfaz.BDretrieveData;
import controlador.utils.dao.GenericFactory;
import controlador.utils.dao.SQLCon;
import modelo.clases.Trabajador;
import modelo.clases.Usuario;

/**
 * La clase {@code UsuarioDAO} es una clase que implementa la interfaz gen�rica
 * {@link controlador.interfaz.BDgeneric BDgeneric}, esta interfaz crea m�todos
 * CRUD necesarios para gestionar la clase {@link modelo.clases.Usuario Usuario}
 * 
 * @author Henrique Yeguo
 * 
 **/
public class UserDAO implements BDgeneric<Usuario>, BDretrieveData<Usuario, Trabajador> {

	// MySQL Consultas
	// Insertar usuarios
	private final String CREATE = "INSERT INTO usuario(idUsuario, passwd, idTrabajdor) VALUES(?, ?, ?)";

	// Buscar buscar info del usuario
	private final String SEARCH = "SELECT * FROM usuario WHERE idTrabajador = ?";

	// Actualizar el usuario y/o contrase�a
	private final String UPDATE = "UPDATE usuario SET idUsuario = ?, passwd = ? WHERE idTrabajador = ?";

	// Eliminar usuario
	private final String DELETE = "DELETE FROM usuario WHERE idTrabajador = ?";

	// Logging
	private final String LOGGING = "CALL logging(?, ?)";

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

			// A�adir datos al Prepare Statement
			stat.setString(1, clase.getIdUsuario());
			stat.setString(2, clase.getPasswd());
			stat.setInt(3, clase.getIdTrabajador());

			// Ejecutrar consulta
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			this.closeConnection();
		}

	}

	/**
	 * M�todo para buscar la informaci�n de un usuario mediante su ID trabajador.
	 * 
	 * @param id id del trabajador que se usar� para buscar
	 * @return informaci�n del usuario
	 **/
	@Override
	public Usuario search(String[] id) {

		this.openConnection();

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		Usuario user = null;

		// Prepare Statement - Search
		try (PreparedStatement stat = con.prepareStatement(SEARCH)) {

			// A�adir datos al Prepare Statement
			stat.setString(1, id[0]);

			// Ejecutar consulta y guardarlo en el Result Set
			rs = stat.executeQuery();

			// Comprobar que RS a recuperado informacion del executeQuery
			if (rs.next()) {
				// Creamos una instancia del objecto y a�adimos los valores del RS al objecto
				user = new Usuario();
				user.setIdUsuario(rs.getString(1));
				user.setPasswd(rs.getString(2));
				user.setIdTrabajador(rs.getInt(3));

			}

		} catch (SQLException e) {
			System.err.println(e);

		} finally {
			this.closeConnection();
		}

		// Devolvemos el objecto, si RS NO ha devuelto nada, devolver� NULL
		return user;
	}

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

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);
			return false; // Si hay alguna excepcion devolver� false
		} finally {
			this.closeConnection();
		}

	}

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

			// A�adir datos al Prepare Statement
			stat.setString(1, id[0]);

			// Ejecutar consulta
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);
			return false; // Si hay alguna excepcion devolver� false
		} finally {
			this.closeConnection();
		}
	}

	/**
	 * M�todo para recuperar la informaci�n del trabajador al iniciar sesi�n,
	 * 
	 * 
	 * @param clase informaci�n del usuario para identificarse; user y pass
	 * @return informaci�n del trabajador
	 **/
	@Override
	public Trabajador recogerInfo(Usuario clase) {

		this.openConnection();
		ResultSet rs = null;

		// Procedimiento para logearse
		try (CallableStatement stat = con.prepareCall(LOGGING)) {

			stat.setString(1, clase.getIdUsuario());
			stat.setString(2, clase.getPasswd());

			rs = stat.executeQuery();

			// Si el RS a devuelto informaci�n, significa que los datos s�n correctos
			if (rs.next()) {
				String[] id = { rs.getString(1) };

				Trabajador trabajador = (Trabajador) GenericFactory.TRABAJADOR.getInstance().search(id);
				return trabajador;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection();
		}
		return null;
	}

}
