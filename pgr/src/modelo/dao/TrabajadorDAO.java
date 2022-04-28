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
import modelo.clases.Trabajador;

/**
 * La clase {@code TrabajadorDAO} es una clase que implementa la interfaz
 * genérica {@link controlador.interfaz.BDgeneric BDgeneric}, esta interfaz crea
 * métodos CRUD necesarios para gestionar la clase
 * {@link modelo.clases.Trabajador Trabajador}
 * 
 * @author Henrique Yeguo
 * 
 **/
public class TrabajadorDAO implements BDgeneric<Trabajador> {

	// MySQL Consultas
	private final String CREATE = "INSERT INTO trabajador"
			+ "(dni, nombre, apellido, numtel, numPremios, direccion, tipo, FechaNac) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private final String SEARCH = "SELECT * FROM trabajador WHERE idTrabajador = ?";
	private final String READALL = "SELECT * FROM trabajador";
	private final String UPDATE = "UPDATE usuario SET nombre = ?, apellido = ?, numTel= ?, numPremios = ?, direccion = ?, tipo = ?, fechaNac = ?"
			+ "WHERE idTrabajador = ?";
	private final String DELETE = "DELETE FROM usuario WHERE idTrabajador = ?";

	// Establecer conexión a la base de datos
	private static Connection con = SQLCon.getConnection();
	private PreparedStatement stat;

	@Override
	public boolean create(Trabajador clase) throws SQLException {

		try {
			// Prepare Statement - Create
			stat = con.prepareStatement(CREATE);

			// Añadir datos al Prepare Statement
			stat.setString(1, clase.getDni());
			stat.setString(2, clase.getNombre());
			stat.setString(3, clase.getApellido());
			stat.setInt(4, clase.getNumTel());
			stat.setInt(5, clase.getNumPremios());
			stat.setString(6, clase.getDireccion());
			stat.setString(7, clase.getTipo());
			stat.setDate(8, Date.valueOf(clase.getFechaNac()));

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false; // Si hay alguna excepcion devolverá false
		}
	}

	@Override
	public Trabajador search(String id) throws SQLException {

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		Trabajador trabajador = null;

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
				trabajador = new Trabajador(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getDate(9).toLocalDate());
			}

		} catch (SQLException e) {
			System.err.println(e);
		}

		// Devolvemos el objecto, si RS NO ha devuelto nada, devolverá NULL
		return trabajador;
	}

	@Override
	public Map<String, Trabajador> readAll() throws SQLException {
		// RS y la clase para recoger los datos, además un map para guardar
		Map<String, Trabajador> allTrabajador = new HashMap<>();
		ResultSet rs = null;
		Trabajador trabajador = null;

		try {
			// Prepare Statement - ReadAll
			stat = con.prepareStatement(READALL);

			// Ejecutar consulta y guardarlo en el result set
			rs = stat.executeQuery();

			// Mientras que RS sigua teniendo filas con información
			while (rs.next()) {
				// Crearmos una instancia del objecto y añadimos los datos
				trabajador = new Trabajador(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getDate(9).toLocalDate());
				// Como en la interfaz el map es un String hay que hacer un toString al Int
				allTrabajador.put(Integer.toString(rs.getInt(1)), trabajador);
			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		// Devolverá un map con los datos, o un map vacío
		return allTrabajador;
	}

	@Override
	public boolean update(Trabajador clase) throws SQLException {

		try {
			// Prepare Statement - Update
			stat = con.prepareStatement(UPDATE);

			// Añadir datos al Prepare Statement
			stat.setString(1, clase.getDni());
			stat.setString(2, clase.getNombre());
			stat.setString(3, clase.getApellido());
			stat.setInt(4, clase.getNumTel());
			stat.setInt(5, clase.getNumPremios());
			stat.setString(6, clase.getDireccion());
			stat.setString(7, clase.getTipo());
			stat.setDate(8, Date.valueOf(clase.getFechaNac()));
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
