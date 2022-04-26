package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import controlador.interfaz.BDgeneric;
import controlador.utils.SQLCon;
import modelo.clases.Actor;

public class ActorDAO implements BDgeneric<Actor> {

	// MySQL Consultas
	private final String CREATE = "INSERT INTO especialidad(idTrabajador, especialidad) VALUES(?, ?)";
	private final String SEARCH = "SELECT * FROM especialidad WHERE idTrabajador = ?";
	private final String READALL = "SELECT * FROM especialidad";
	private final String UPDATE = "UPDATE especialidad SET especialidad = ? WHERE idTrabajador = ? AND especialidad = ?";
	private final String DELETE = "DELETE FROM usuario WHERE idTrabajador = ?";

	// Establecer conexi�n a la base de datos
	private static Connection con = SQLCon.getConnection();
	private PreparedStatement stat;

	@Override
	public boolean create(Actor clase) throws SQLException {

		try {
			// Prepare Statement - Create
			stat = con.prepareStatement(CREATE);

			// Si va a insertar m�s de 1 especialidad
			if (clase.getEspecialidades().size() > 1) {
				int i = 0;
				// A�adir al grupo cada especialidad
				for (String especialidad : clase.getEspecialidades()) {
					// A�adir datos al Prepare Statement
					stat.setInt(1, clase.getIdTrabajador());
					stat.setString(2, especialidad);
					// A�adimos los comandos al grupo
					stat.addBatch();
					i++;

					if (i % 1000 == 0 || i == clase.getEspecialidades().size()) {
						// Ejecutar� los STAT en una sola llamada al servidor
						return stat.executeBatch().length > 0 ? true : false;
						// Seg�n la API si recibe un n�mero mayor que 0 se a ejecutado correctamente

					}
				}
			} else {
				stat.setInt(1, clase.getIdTrabajador());
				stat.setString(2, clase.getEspecialidades().get(0));
			}

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false; // Si hay alguna excepcion devolver� false
		}

	}

	@Override
	public Actor search(String id) throws SQLException {

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		List<String> especialidad = new ArrayList<>();
		Actor act = null;

		try {
			// Prepare Statement - Search
			stat = con.prepareStatement(SEARCH);

			// A�adir datos al Prepare Statement
			stat.setString(1, id);

			// Ejecutar consulta y guardarlo en el Result Set
			rs = stat.executeQuery();

			// Comprobar que RS a recuperado informacion del executeQuery
			while (rs.next()) { // Como un actor puede tener varias especialidades un while en vez de if
				// Creamos una instancia del objecto y a�adimos los valores del RS al objecto
				if (act == null) {
					act = new Actor();
					act.setIdTrabajador(rs.getInt(1));
				}
				especialidad.add(rs.getString(2));
			}
			if (act != null && especialidad != null) {
				act.setEspecialidades(especialidad);
			}

			return act;
		} catch (SQLException e) {
			System.err.println(e);
			return act;
		}
	}

	@Override
	public Map<String, Actor> readAll() throws SQLException {

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		Map<String, Actor> actores = new TreeMap<>();
		List<String> especialidad = null;
		Actor act = null;

		try {
			// Prepare Statement - Search
			stat = con.prepareStatement(SEARCH);

			// Ejecutar consulta y guardarlo en el Result Set
			rs = stat.executeQuery();

			// Comprobar que RS a recuperado informacion del executeQuery
			while (rs.next()) {
				/*
				 * Esto es lo que har� este loop: La primera vez que entre el la condici�n ser�
				 * null, por lo tanto entra, la concici�n de dentro no entrar� porque a�n sigue
				 * siendo null, crear� un objecto Actor donde se guarda su ID y la lista donde
				 * se guarda las especialidades. En la siguiente condici�n comprueba que el ID
				 * del actor coincida con la ID de la consulta y a�ade la especialidad a la
				 * lista, cuando vuelva al inicio solo entrar� si los IDs no coinciden, si
				 * coincide ser� que es el mismo Actor y se le volver� a introducir otra
				 * especialidad, si es diferente entrar� otra vez en la condici�n y volver� a
				 * crear el actor y la lista.
				 */
				if (act == null || act.getIdTrabajador() != rs.getInt(1)) {
					if (act != null) {
						actores.put(Integer.toString(act.getIdTrabajador()), act);
					}
					// Creamos una instancia del objecto y guardamos solo el ID
					act = new Actor();
					especialidad = new ArrayList<>();
					act.setIdTrabajador(rs.getInt(1));
				}

				// Comprobamos que el ID que sea igual que el ID de la consulta
				if (act.getIdTrabajador() == rs.getInt(1)) {
					// A�adimos la especialidad a la lista
					especialidad.add(rs.getString(2));
				}
			}

			return actores;
		} catch (

		SQLException e) {
			System.err.println(e);
			return actores;
		}
	}

	@Override
	public boolean update(Actor clase) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(String id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
