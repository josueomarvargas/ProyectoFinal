package modelo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import controlador.interfaz.BDgeneric;
import controlador.utils.SQLCon;
import modelo.clases.ObraAudiovisual;

public class ObraDAO implements BDgeneric<ObraAudiovisual> {

	// MySQL Consultas
	private final String CREATE = "INSERT INTO obraaudiovisual(idObra, nombre, duracion, FechaEstreno, presupuesto, tipo) VALUES(?, ?, ?, ?, ?, ?)";
	private final String SEARCH = "SELECT * FROM obraaudiovisual WHERE idObra = ?";
	private final String READALL = "SELECT * FROM obraaudiovisual";
	private final String UPDATE = "UPDATE obraaudiovisual SET nombre = ?, duracion = ?, FechaEstreno = ?, presupuesto = ?, tipo = ? WHERE idObra = ?";
	private final String DELETE = "DELETE FROM obraaudiovisual WHERE idObra = ?";

	// Establecer conexión a la base de datos
	private static Connection con = SQLCon.getConnection();
	private PreparedStatement stat;

	@Override
	public boolean create(ObraAudiovisual clase) throws SQLException {

		try {
			// Prepare Statement - Create
			stat = con.prepareStatement(CREATE);

			// Añadir datos al Prepare Statement
			stat.setInt(1, clase.getIdObra());
			stat.setString(2, clase.getNombre());
			stat.setInt(3, clase.getDuracion());
			stat.setDate(4, Date.valueOf(clase.getFechaEstreno()));
			stat.setInt(5, clase.getPresupuesto());
			stat.setString(6, clase.getTipo());

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false; // Si hay alguna excepcion devolverá false
		}
	}

	@Override
	public ObraAudiovisual search(String id) throws SQLException {

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		ObraAudiovisual oa = null;

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
				oa = new ObraAudiovisual();
				oa.setIdObra(rs.getInt(1));
			}

		} catch (SQLException e) {
			System.err.println(e);
		}

		// Devolvemos el objecto, si RS NO ha devuelto nada, devolverá NULL
		return oa;
	}

	@Override
	public Map<String, ObraAudiovisual> readAll() throws SQLException {
		// RS y la clase para recoger los datos, además un map para guardar
		Map<String, ObraAudiovisual> allUsers = new HashMap<>();
		ResultSet rs = null;
		ObraAudiovisual oa = null;

		try {
			// Prepare Statement - ReadAll
			stat = con.prepareStatement(READALL);

			// Ejecutar consulta y guardarlo en el result set
			rs = stat.executeQuery();

			// Mientras que RS sigua teniendo filas con información
			while (rs.next()) {
				// Crearmos una instancia del objecto
				oa = new ObraAudiovisual();
				oa.setIdObra(rs.getInt(1));
				oa.setNombre(rs.getString(2));
				oa.setDuracion(rs.getInt(3));
				oa.setFechaEstreno(rs.getDate(4).toLocalDate());
				oa.setPresupuesto(rs.getInt(5));
				oa.setTipo(rs.getString(6));
				// Añadimos la clave y el objecto al map
				allUsers.put(Integer.toString(oa.getIdObra()), oa);
			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		// Devolverá un map con los datos, o un map vacío
		return allUsers;
	}

	@Override
	public boolean update(ObraAudiovisual clase) throws SQLException {
		try {
			// Prepare Statement - Update
			stat = con.prepareStatement(UPDATE);

			// Añadir datos al Prepare Statement
			stat.setInt(1, clase.getIdObra());
			stat.setString(2, clase.getNombre());
			stat.setInt(3, clase.getDuracion());
			stat.setDate(4, Date.valueOf(clase.getFechaEstreno()));
			stat.setInt(5, clase.getPresupuesto());
			stat.setString(6, clase.getTipo());

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
