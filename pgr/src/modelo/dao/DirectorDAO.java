package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import controlador.interfaz.BDgeneric;
import controlador.utils.SQLCon;
import modelo.clases.Director;

/**
 * La clase {@code DirectorDAO} es una clase que implementa la interfaz genérica
 * {@link controlador.interfaz.BDgeneric BDgeneric}, esta interfaz crea métodos
 * CRUD necesarios para gestionar la clase {@link modelo.clases.Director
 * Director}
 * 
 * @author Henrique Yeguo
 * 
 **/
public class DirectorDAO implements BDgeneric<Director> {

	// MySQL Consultas
	private final String CREATE = "INSERT INTO director(idTrabajador, categoria) VALUES(?, ?)";
	private final String SEARCH = "SELECT * FROM director WHERE idTrabajador = ?";
	private final String READALL = "SELECT * FROM director";
	private final String UPDATE = "UPDATE director SET especialidad = ? WHERE idTrabajador = ?";
	private final String DELETE = "DELETE FROM director WHERE idTrabajador = ?";

	// Establecer conexión a la base de datos
	private static Connection con = SQLCon.getConnection();
	private PreparedStatement stat;

	@Override
	public boolean create(Director clase) throws SQLException {

		try {
			// Prepare Statement - Create
			stat = con.prepareStatement(CREATE);

			// Añadimos los datos al Prepare Statement
			stat.setInt(1, clase.getIdTrabajador());
			stat.setString(2, clase.getCategoria());

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}

	}

	@Override
	public Director search(String id) throws SQLException {

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		Director dir = null;

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
				dir = new Director();
				dir.setIdTrabajador(rs.getInt(1));
				dir.setCategoria(rs.getString(2));
			}

		} catch (SQLException e) {
			System.err.println(e);
		}

		// Devolvemos el objecto, si RS NO ha devuelto nada, devolverá NULL
		return dir;
	}

	@Override
	public Map<String, Director> readAll() throws SQLException {

		// RS y la clase para recoger los datos, además un map para guardar
		Map<String, Director> allDir = new HashMap<>();
		ResultSet rs = null;
		Director dir = null;

		try {
			// Prepare Statement - ReadAll
			stat = con.prepareStatement(READALL);

			// Ejecutar consulta y guardarlo en el result set
			rs = stat.executeQuery();

			// Mientras que RS sigua teniendo filas con información
			while (rs.next()) {
				// Crearmos una instancia del objecto
				dir = new Director();
				dir.setIdTrabajador(rs.getInt(1));
				dir.setCategoria(rs.getString(2));

				// Añadimos la clave y el objecto al map
				allDir.put(Integer.toString(dir.getIdTrabajador()), dir);
			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		// Devolverá un map con los datos, o un map vacío
		return allDir;
	}

	@Override
	public boolean update(Director clase) throws SQLException {

		try {
			// Prepare Statement - Update
			stat = con.prepareStatement(UPDATE);

			// Añadir datos al Prepare Statement
			stat.setInt(1, clase.getIdTrabajador());
			stat.setString(2, clase.getCategoria());

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false; // Si hay alguna excepcion devolverá false
		}

	}

	@Override
	public boolean remove(String id) throws SQLException {
		try {
			// Prepare Statement - Delete
			stat = con.prepareStatement(DELETE);

			// Añadir datos al Prepare Statement
			stat.setString(1, id);

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false;
		}
	}

}
