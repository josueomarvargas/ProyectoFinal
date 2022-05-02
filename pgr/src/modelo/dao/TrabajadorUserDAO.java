package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import controlador.interfaz.BDgeneric;
import controlador.utils.SQLCon;
import modelo.clases.TrabajadorUsuario;

public class TrabajadorUserDAO implements BDgeneric<TrabajadorUsuario> {

	// MySQL Consultas
	private final String CREATE = "INSERT INTO trabajador_usuario(idTrabajador, idUsuario, tipoUsuario) VALUES(?, ?, ?)";
	private final String SEARCH = "SELECT * FROM trabajador_usuario WHERE idTrabajador = ?";
	private final String READALL = "SELECT * FROM trabajador_usuario";
	private final String UPDATE = "UPDATE trabajador_usuario SET idUsuario = ?, tipoUsuario = ? WHERE idTrabajador = ?";
	private final String DELETE = "DELETE FROM trabajador_usuario WHERE idTrabajador = ?";

	// Establecer conexi�n a la base de datos
	private static Connection con = SQLCon.getConnection();
	private PreparedStatement stat;

	@Override
	public boolean create(TrabajadorUsuario clase) throws SQLException {

		try {
			// Prepare Statement - Create
			stat = con.prepareStatement(CREATE);

			// A�adimos los datos al Prepare Statement
			stat.setInt(1, clase.getIdTrabajador());
			stat.setString(2, clase.getIdUsuario());
			stat.setString(3, clase.getTipoUsuario());

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}

	}

	@Override
	public TrabajadorUsuario search(String id) throws SQLException {

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		TrabajadorUsuario tu = null;

		try {
			// Prepare Statement - Search
			stat = con.prepareStatement(SEARCH);

			// A�atu datos al Prepare Statement
			stat.setString(1, id);

			// Ejecutar consulta y guardarlo en el Result Set
			rs = stat.executeQuery();

			// Comprobar que RS a recuperado informacion del executeQuery
			if (rs.next()) {
				// Creamos una instancia del objecto y a�adimos los valores del RS al objecto
				tu = new TrabajadorUsuario();
				tu.setIdTrabajador(rs.getInt(1));
				tu.setIdUsuario(rs.getString(2));
				tu.setTipoUsuario(rs.getString(3));
			}
		} catch (SQLException e) {
			System.err.println(e);
		}

		// Devolvemos el objecto, si RS NO ha devuelto nada, devolver� NULL
		return tu;
	}

	@Override
	public Map<String, TrabajadorUsuario> readAll() throws SQLException {

		// RS y la clase para recoger los datos, adem�s un map para guardar
		Map<String, TrabajadorUsuario> tuMap = new HashMap<>();
		ResultSet rs = null;
		TrabajadorUsuario tu = null;

		try {
			// Prepare Statement - ReadAll
			stat = con.prepareStatement(READALL);

			// Ejecutar consulta y guardarlo en el result set
			rs = stat.executeQuery();

			// Mientras que RS sigua teniendo filas con informaci�n
			while (rs.next()) {
				// Crearmos una instancia del objecto
				tu = new TrabajadorUsuario();
				tu.setIdTrabajador(rs.getInt(1));
				tu.setIdUsuario(rs.getString(2));
				tu.setTipoUsuario(rs.getString(3));

				// A�adimos la clave y el objecto al map
				tuMap.put(Integer.toString(tu.getIdTrabajador()), tu);
			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		// Devolver� un map con los datos, o un map vac�o
		return tuMap;
	}

	@Override
	public boolean update(TrabajadorUsuario clase) throws SQLException {

		try {
			// Prepare Statement - Update
			stat = con.prepareStatement(UPDATE);

			// A�atu datos al Prepare Statement
			stat.setString(1, clase.getIdUsuario());
			stat.setString(2, clase.getTipoUsuario());
			stat.setInt(3, clase.getIdTrabajador());

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false; // Si hay alguna excepcion devolver� false
		}
	}

	@Override
	public boolean remove(String[] id) throws SQLException {

		try {
			// Prepare Statement - Delete
			stat = con.prepareStatement(DELETE);

			// A�atu datos al Prepare Statement
			stat.setString(1, id[0]);

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false;
		}
	}

}
