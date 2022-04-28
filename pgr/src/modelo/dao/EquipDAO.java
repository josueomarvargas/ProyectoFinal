package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import controlador.interfaz.BDgeneric;
import controlador.utils.SQLCon;
import modelo.clases.Equipamiento;

public class EquipDAO implements BDgeneric<Equipamiento> {

	// MySQL Consultas
	private final String CREATE = "INSERT INTO equipamiento(idEquip, nombre, tipo) VALUES(?, ?, ?)";
	private final String SEARCH = "SELECT * FROM equipamiento WHERE idEquip = ?";
	private final String READALL = "SELECT * FROM equipamiento";
	private final String UPDATE = "UPDATE equipamiento SET nombre = ?, tipo = ? WHERE idEquip = ?";
	private final String DELETE = "DELETE FROM equipamiento WHERE idEquip = ?";

	// Establecer conexi�n a la base de datos
	private static Connection con = SQLCon.getConnection();
	private PreparedStatement stat;

	@Override
	public boolean create(Equipamiento clase) throws SQLException {

		try {
			// Prepare Statement - Create
			stat = con.prepareStatement(CREATE);

			// A�adir datos al Prepare Statement
			stat.setInt(1, clase.getIdEquip());
			stat.setString(2, clase.getNombre());
			stat.setString(3, clase.getTipo());

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false; // Si hay alguna excepcion devolver� false
		}
	}

	@Override
	public Equipamiento search(String id) throws SQLException {

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		Equipamiento equip = null;

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
				equip = new Equipamiento();
				equip.setIdEquip(rs.getInt(1));
				equip.setNombre(rs.getString(2));
				equip.setTipo(rs.getString(3));
			}

		} catch (SQLException e) {
			System.err.println(e);
		}

		// Devolvemos el objecto, si RS NO ha devuelto nada, devolver� NULL
		return equip;

	}

	@Override
	public Map<String, Equipamiento> readAll() throws SQLException {

		// RS y la clase para recoger los datos, adem�s un map para guardar
		Map<String, Equipamiento> allEquip = new HashMap<>();
		ResultSet rs = null;
		Equipamiento equip = null;

		try {
			// Prepare Statement - ReadAll
			stat = con.prepareStatement(READALL);

			// Ejecutar consulta y guardarlo en el result set
			rs = stat.executeQuery();

			// Mientras que RS sigua teniendo filas con informaci�n
			while (rs.next()) {
				// Crearmos una instancia del objecto y a�adimos los datos
				equip = new Equipamiento();
				equip.setIdEquip(rs.getInt(1));
				equip.setNombre(rs.getString(2));
				equip.setTipo(rs.getString(3));
				// Como en la interfaz el map es un String hay que hacer un toString al Int
				allEquip.put(Integer.toString(rs.getInt(1)), equip);
			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		// Devolver� un map con los datos, o un map vac�o
		return allEquip;
	}

	@Override
	public boolean update(Equipamiento clase) throws SQLException {

		try {
			// Prepare Statement - Update
			stat = con.prepareStatement(UPDATE);

			// A�adir datos al Prepare Statement
			stat.setInt(1, clase.getIdEquip());
			stat.setString(2, clase.getNombre());
			stat.setString(3, clase.getTipo());

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

			// A�adir datos al Prepare Statement
			stat.setString(1, id[0]);

			// Ejecutar consulta
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false; // Si hay alguna excepcion devolver� false
		}
	}

}
