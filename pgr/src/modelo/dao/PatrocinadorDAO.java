package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import controlador.interfaz.BDgeneric;
import controlador.utils.SQLCon;
import modelo.clases.Patrocinador;

public class PatrocinadorDAO implements BDgeneric<Patrocinador> {

	// MySQL Consultas
	private final String CREATE = "INSERT INTO patrocinador(idPatro, nombre, cantDinero, condicion) VALUES(?, ?, ?, ?)";
	private final String SEARCH = "SELECT * FROM patrocinador WHERE idPatro = ?";
	private final String READALL = "SELECT * FROM patrocinador";
	private final String UPDATE = "UPDATE patrocinador SET especialidad = ? WHERE idTrabajador = ? AND especialidad = ?";
	private final String DELETE = "DELETE FROM patrocinador WHERE idTrabajador = ?";

	// Establecer conexión a la base de datos
	private static Connection con = SQLCon.getConnection();
	private PreparedStatement stat;

	@Override
	public boolean create(Patrocinador clase) throws SQLException {

		try {
			// Prepare Statement - Create
			stat = con.prepareStatement(CREATE);

			// Añadimos los datos al Prepare Statement
			stat.setInt(1, clase.getIdPatro());
			stat.setString(2, clase.getNombre());
			stat.setInt(3, clase.getCantDinero());
			stat.setString(4, clase.getCondicion());

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

	@Override
	public Patrocinador search(String id) throws SQLException {

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		Patrocinador patro = null;

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
				patro = new Patrocinador();
				patro.setIdPatro(rs.getInt(1));
				patro.setNombre(rs.getString(2));
				patro.setCantDinero(rs.getInt(3));
				patro.setCondicion(rs.getString(4));
			}

		} catch (SQLException e) {
			System.err.println(e);
		}

		return patro;

	}

	@Override
	public Map<String, Patrocinador> readAll() throws SQLException {

		// RS y la clase para recoger los datos, además un map para guardar
		Map<String, Patrocinador> allDir = new HashMap<>();
		ResultSet rs = null;
		Patrocinador patro = null;

		try {
			// Prepare Statement - ReadAll
			stat = con.prepareStatement(READALL);

			// Ejecutar consulta y guardarlo en el result set
			rs = stat.executeQuery();

			// Mientras que RS sigua teniendo filas con información
			while (rs.next()) {
				// Crearmos una instancia del objecto
				patro = new Patrocinador();
				patro.setIdPatro(rs.getInt(1));
				patro.setNombre(rs.getString(2));
				patro.setCantDinero(rs.getInt(3));
				patro.setCondicion(rs.getString(4));

				// Añadimos la clave y el objecto al map
				allDir.put(Integer.toString(patro.getIdPatro()), patro);
			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		// Devolverá un map con los datos, o un map vacío
		return allDir;
	}

	@Override
	public boolean update(Patrocinador clase) throws SQLException {

		try {
			// Prepare Statement - Update
			stat = con.prepareStatement(UPDATE);

			// Añadir datos al Prepare Statement
			stat.setInt(1, clase.getIdPatro());
			stat.setString(2, clase.getNombre());
			stat.setInt(3, clase.getCantDinero());
			stat.setString(4, clase.getCondicion());

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
