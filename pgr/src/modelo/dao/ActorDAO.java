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

/**
 * La clase {@code ActorDAO} es una clase que implementa la interfaz gen�rica
 * {@link controlador.interfaz.BDgeneric BDgeneric}, esta interfaz crea m�todos
 * CRUD necesarios para gestionar la clase {@link modelo.clases.Actor Actor}
 * 
 * @author Henrique Yeguo
 * 
 **/
public class ActorDAO implements BDgeneric<Actor> {

	// MySQL Consultas
	private final String CREATE = "INSERT INTO especialidad(idTrabajador, especialidad) VALUES(?, ?)";
	private final String SEARCH = "SELECT * FROM especialidad WHERE idTrabajador = ?";
	private final String READALL = "SELECT * FROM especialidad";
	private final String UPDATE = "UPDATE especialidad SET especialidad = ? WHERE idTrabajador = ? AND especialidad = ?";
	private final String DELETE = "DELETE FROM especialidad WHERE idTrabajador = ? AND especialidad = ?";

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
					// A�adir comando al grupo
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
			while (rs.next()) { // Como este objecto tiene una lista por ende la consulta devuelve varios datos
				// Creamos una instancia del objecto y a�adimos los valores del RS al objecto
				if (act == null) {
					act = new Actor();
					act.setIdTrabajador(rs.getInt(1));
				}
				especialidad.add(rs.getString(2));
			}
			if (act != null && !especialidad.isEmpty()) {
				act.setEspecialidades(especialidad);
			}

		} catch (SQLException e) {
			System.err.println(e);
		}
		return act;
	}

	@Override
	public Map<String, Actor> readAll() throws SQLException {

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		Map<String, Actor> actores = new TreeMap<>();
		List<String> especialidad = null;
		Actor act = null;

		try {
			// Prepare Statement - Read All
			stat = con.prepareStatement(READALL);

			// Ejecutar consulta y guardarlo en el Result Set
			rs = stat.executeQuery();

			// Comprobar que RS a recuperado informacion del executeQuery
			while (rs.next()) {

				/*
				 * Esto es lo que har� este loop: La primera vez que entre el la condici�n ser�
				 * null, por lo tanto entra, la concici�n de dentro no entrar� porque a�n sigue
				 * siendo null, crear� un objecto donde se guardar� su ID y la lista donde
				 * guardaremos los datos. En la siguiente condici�n comprueba que el ID del
				 * equip coincida con la ID de la consulta y a�ade el dato a la lista, cuando
				 * vuelva al inicio solo entrar� si los IDs no coinciden, si coincide ser� que
				 * es el mismo ID y querr� decir que hay m�s datos por introducir, si es
				 * diferente entrar� otra vez en la condici�n como esta vez no es null guarda
				 * los datos en el map y volver� a crear el objeto y la lista. Cuando sea el
				 * �ltimo result set guardar� los datos al map.
				 */

				if (act == null || act.getIdTrabajador() != rs.getInt(1)) {
					if (act != null) {
						act.setEspecialidades(especialidad);
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

				if (rs.isLast() == true) {
					act.setEspecialidades(especialidad);
					actores.put(Integer.toString(act.getIdTrabajador()), act);
				}
			}

		} catch (SQLException e) {
			System.err.println(e);
		}
		return actores;
	}

	@Override
	public boolean update(Actor clase) throws SQLException {

		// Clase actor para guardar los antiguos datos
		Actor act = this.search(Integer.toString(clase.getIdTrabajador()));

		// Ordenamos las dos listas para que luego comparar con el
		// que hemos recuperado de la base de datos
		clase.sortList();
		act.sortList();

		try {
			// Prepare Statement - Update
			stat = con.prepareStatement(UPDATE);

			// Si va a actualizar m�s de 1 especialidad
			if (clase.getEspecialidades().size() > 1) {
				int i = 0;
				// Iterar por las 2 listas para comprobar cual es la que no coincide
				for (int j = 0; j < clase.getEspecialidades().size(); j++) {
					if (!act.getEspecialidades().get(j).equals(clase.getEspecialidades().get(j))) {

						// A�adir datos al Prepare Statement
						stat.setString(1, clase.getEspecialidades().get(j));
						stat.setInt(2, clase.getIdTrabajador());
						stat.setString(3, act.getEspecialidades().get(j));
						// A�adimos los comandos al grupo
						stat.addBatch();
						i++;
					}
				}

				if (i % 1000 == 0 || i == clase.getEspecialidades().size()) {
					// Ejecutar� los batch en una sola llamada al servidor
					return stat.executeBatch().length > 0 ? true : false;
					// Seg�n la API si recibe un n�mero mayor que 0 se a ejecutado correctamente

				}

				// Cuando solo se actualiza 1 especialidad
			} else {
				stat.setString(1, clase.getEspecialidades().get(0));
				stat.setInt(2, clase.getIdTrabajador());
				stat.setString(3, act.getEspecialidades().get(0));
			}

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
			stat.setString(2, id[1]);

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false;
		}

	}

}
