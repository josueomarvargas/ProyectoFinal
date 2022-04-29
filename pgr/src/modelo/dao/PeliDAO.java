package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import controlador.interfaz.BDgeneric;
import controlador.utils.SQLCon;
import modelo.clases.Pelicula;

public class PeliDAO implements BDgeneric<Pelicula> {

	// MySQL Consultas
	private final String CREATE = "INSERT INTO pelicula(idObra, esTaquillera) VALUES(?, ?)";
	private final String SEARCH = "SELECT * FROM pelicula WHERE idObra = ?";
	private final String READALL = "SELECT * FROM pelicula";
	private final String UPDATE = "UPDATE pelicula SET esTaquillera = ? WHERE idObra = ?";
	private final String DELETE = "DELETE FROM usuario WHERE idObra = ?";

	// Establecer conexión a la base de datos
	private static Connection con = SQLCon.getConnection();
	private PreparedStatement stat;

	@Override
	public boolean create(Pelicula clase) throws SQLException {

		try {
			// Prepare Statement - Create
			stat = con.prepareStatement(CREATE);

			// Añadimos los datos al Prepare Statement
			stat.setInt(1, clase.getIdObra());
			stat.setInt(2, clase.isEsTaquillera() == true ? 1 : 0);

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}

	}

	@Override
	public Pelicula search(String id) throws SQLException {

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		Pelicula patro = null;

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
				patro = new Pelicula();
				patro.setIdObra(rs.getInt(1));
				patro.setEsTaquillera(rs.getInt(2) == 1 ? true : false);
			}

		} catch (SQLException e) {
			System.err.println(e);
		}
		return patro;
	}

	@Override
	public Map<String, Pelicula> readAll() throws SQLException {

		// RS y la clase para recoger los datos, además un map para guardar
		Map<String, Pelicula> allDir = new HashMap<>();
		ResultSet rs = null;
		Pelicula patro = null;

		try {
			// Prepare Statement - ReadAll
			stat = con.prepareStatement(READALL);

			// Ejecutar consulta y guardarlo en el result set
			rs = stat.executeQuery();

			// Mientras que RS sigua teniendo filas con información
			while (rs.next()) {
				// Crearmos una instancia del objecto
				patro = new Pelicula();
				patro.setIdObra(rs.getInt(1));
				patro.setEsTaquillera(rs.getInt(2) == 1 ? true : false);

				// Añadimos la clave y el objecto al map
				allDir.put(Integer.toString(patro.getIdObra()), patro);
			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		// Devolverá un map con los datos, o un map vacío
		return allDir;
	}

	@Override
	public boolean update(Pelicula clase) throws SQLException {

		try {
			// Prepare Statement - Update
			stat = con.prepareStatement(UPDATE);

			// Añadir datos al Prepare Statement
			stat.setInt(1, clase.isEsTaquillera() == true ? 1 : 0);
			stat.setInt(2, clase.getIdObra());

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

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false;
		}
	}

}
