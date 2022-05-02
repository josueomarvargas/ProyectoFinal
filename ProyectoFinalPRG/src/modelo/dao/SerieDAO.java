package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import controlador.interfaz.BDgeneric;
import controlador.utils.SQLCon;
import modelo.clases.Serie;

public class SerieDAO implements BDgeneric<Serie> {

	// MySQL Consultas
	private final String CREATE = "INSERT INTO serie(idObra, numTemporada, numCapitulo, nombreCap) VALUES(?, ?, ?, ?)";
	private final String SEARCH = "SELECT * FROM serie WHERE idObra = ?";
	private final String READALL = "SELECT * FROM serie";
	private final String UPDATE = "UPDATE serie SET nombreCap = ? WHERE idObra = ? AND numTemporada = ? AND numCapitulo";
	private final String DELETE = "DELETE FROM usuario WHERE idObra = ? AND numTemporada = ? AND nombreCap = ?";

	// Establecer conexión a la base de datos
	private static Connection con = SQLCon.getConnection();
	private PreparedStatement stat;

	@Override
	public boolean create(Serie clase) throws SQLException {

		try {
			// Prepare Statement - Create
			stat = con.prepareStatement(CREATE);

			// Añadimos los datos al Prepare Statement
			stat.setInt(1, clase.getIdObra());
			stat.setInt(2, clase.getNumTemporada());
			stat.setInt(3, clase.getNumCapitulo());
			stat.setString(4, clase.getNombreCap());

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

	@Override
	public Serie search(String id) throws SQLException {

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		Serie serie = null;

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
				serie = new Serie();
				serie.setIdObra(rs.getInt(1));
				serie.setNumTemporada(rs.getInt(2));
				serie.setNumCapitulo(rs.getInt(3));
				serie.setNombreCap(rs.getString(4));
			}

		} catch (SQLException e) {
			System.err.println(e);
		}

		// Devolvemos el objecto, si RS NO ha devuelto nada, devolverá NULL
		return serie;
	}

	@Override
	public Map<String, Serie> readAll() throws SQLException {

		// RS y la clase para recoger los datos, además un map para guardar
		Map<String, Serie> allSerie = new HashMap<>();
		ResultSet rs = null;
		Serie serie = null;

		try {
			// Prepare Statement - ReadAll
			stat = con.prepareStatement(READALL);

			// Ejecutar consulta y guardarlo en el result set
			rs = stat.executeQuery();

			// Mientras que RS sigua teniendo filas con información
			while (rs.next()) {
				// Crearmos una instancia del objecto
				serie = new Serie();
				serie.setIdObra(rs.getInt(1));
				serie.setNumTemporada(rs.getInt(2));
				serie.setNumCapitulo(rs.getInt(3));
				serie.setNombreCap(rs.getString(4));

				// Añadimos la clave y el objecto al map
				allSerie.put(Integer.toString(serie.getIdObra()), serie);
			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		// Devolverá un map con los datos, o un map vacío
		return allSerie;
	}

	@Override
	public boolean update(Serie clase) throws SQLException {

		try {
			// Prepare Statement - Update
			stat = con.prepareStatement(UPDATE);

			// Añadir datos al Prepare Statement
			stat.setString(1, clase.getNombreCap());
			stat.setInt(2, clase.getIdObra());
			stat.setInt(3, clase.getNumTemporada());
			stat.setInt(4, clase.getNumCapitulo());

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
			stat.setInt(1, Integer.parseInt(id[0]));
			stat.setInt(2, Integer.parseInt(id[1]));
			stat.setInt(3, Integer.parseInt(id[2]));

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false;
		}
	}
}
