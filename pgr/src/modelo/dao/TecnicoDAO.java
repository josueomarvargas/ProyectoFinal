package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import controlador.interfaz.BDgeneric;
import controlador.utils.SQLCon;
import modelo.clases.TecnicoAudiovisual;

public class TecnicoDAO implements BDgeneric<TecnicoAudiovisual> {

	// MySQL Consultas
	private final String CREATE = "INSERT INTO areatrabajo(idTrabajador, AreaTrabajo) VALUES(?, ?)";
	private final String SEARCH = "SELECT * FROM areatrabajo WHERE idTrabajador = ?";
	private final String READALL = "SELECT * FROM areatrabajo";
	private final String UPDATE = "UPDATE areatrabajo SET AreaTrabajo = ? WHERE idTrabajador = ? AND areaTrabajo = ?";
	private final String DELETE = "DELETE FROM areatrabajo WHERE idTrabajador = ? AND AreaTrabajo = ?";

	// Establecer conexi�n a la base de datos
	private static Connection con = SQLCon.getConnection();
	private PreparedStatement stat;

	@Override
	public boolean create(TecnicoAudiovisual clase) throws SQLException {

		try {
			// Prepare Statement - Create
			stat = con.prepareStatement(CREATE);

			// Si se va a�adir m�s de 1 area
			if (clase.getAreaTrabajos().size() > 1) {
				int i = 0;
				// Iterar por la lista
				for (String area : clase.getAreaTrabajos()) {
					// A�adimos los datos al Prepare Statement
					stat.setInt(1, clase.getIdTrabajador());
					stat.setString(2, area);
					// A�adir comando al grupo
					stat.addBatch();
					i++;

					if (i == clase.getAreaTrabajos().size()) {
						// Ejecutar� los STAT en una sola llamada al servidor
						return stat.executeBatch().length > 0 ? true : false;
						// Seg�n la API si recibe un n�mero mayor que 0 se a ejecutado correctamente
					}
				}
			} else {
				stat.setInt(1, clase.getIdTrabajador());
				stat.setString(2, clase.getAreaTrabajos().get(0));
			}

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}

	}

	@Override
	public TecnicoAudiovisual search(String id) throws SQLException {

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		List<String> areaTrabajos = new ArrayList<>();
		TecnicoAudiovisual ta = null;

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
				if (ta == null) {
					ta = new TecnicoAudiovisual();
					ta.setIdTrabajador(rs.getInt(1));
				}
				areaTrabajos.add(rs.getString(2));
			}
			if (ta != null && !areaTrabajos.isEmpty()) {
				ta.setAreaTrabajos(areaTrabajos);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}

		// Devolvemos el objecto, si RS NO ha devuelto nada, devolver� NULL
		return ta;
	}

	@Override
	public Map<String, TecnicoAudiovisual> readAll() throws SQLException {

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		Map<String, TecnicoAudiovisual> tas = new TreeMap<>();
		List<String> areaTrabajos = null;
		TecnicoAudiovisual ta = null;

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

				if (ta == null || ta.getIdTrabajador() != rs.getInt(1)) {
					if (ta != null) {
						ta.setAreaTrabajos(areaTrabajos);
						tas.put(Integer.toString(ta.getIdTrabajador()), ta);
					}
					// Creamos una instancia del objecto y guardamos solo el ID
					ta = new TecnicoAudiovisual();
					areaTrabajos = new ArrayList<>();
					ta.setIdTrabajador(rs.getInt(1));
				}

				// Comprobamos que el ID que sea igual que el ID de la consulta
				if (ta.getIdTrabajador() == rs.getInt(1)) {
					// A�adimos la areaTrabajos a la lista
					areaTrabajos.add(rs.getString(2));
				}

				if (rs.isLast() == true) {
					ta.setAreaTrabajos(areaTrabajos);
					tas.put(Integer.toString(ta.getIdTrabajador()), ta);
				}
			}

		} catch (SQLException e) {
			System.err.println(e);
		}
		return tas;
	}

	@Override
	public boolean update(TecnicoAudiovisual clase) throws SQLException {

		// Clase actor para guardar los antiguos datos
		TecnicoAudiovisual ta = this.search(Integer.toString(clase.getIdTrabajador()));

		// Ordenamos las dos listas para que luego comparar con el
		// que hemos recuperado de la base de datos
		clase.sortList();
		ta.sortList();

		try {
			// Prepare Statement - Update
			stat = con.prepareStatement(UPDATE);

			// Si va a actualizar m�s de 1 areaTrabajos
			if (clase.getAreaTrabajos().size() > 1) {
				int i = 0;
				// Iterar por las 2 listas para comprobar cual es la que no coincide
				for (int j = 0; j < clase.getAreaTrabajos().size(); j++) {
					if (!ta.getAreaTrabajos().get(j).equals(clase.getAreaTrabajos().get(j))) {

						// A�adir datos al Prepare Statement
						stat.setString(1, clase.getAreaTrabajos().get(j));
						stat.setInt(2, clase.getIdTrabajador());
						stat.setString(3, ta.getAreaTrabajos().get(j));
						// A�adimos los comandos al grupo
						stat.addBatch();
						i++;
					}
				}

				if (i % 1000 == 0 || i == clase.getAreaTrabajos().size()) {
					// Ejecutar� los batch en una sola llamada al servidor
					return stat.executeBatch().length > 0 ? true : false;
					// Seg�n la API si recibe un n�mero mayor que 0 se a ejecutado correctamente

				}

				// Cuando solo se actualiza 1 areaTrabajos
			} else {
				stat.setString(1, clase.getAreaTrabajos().get(0));
				stat.setInt(2, clase.getIdTrabajador());
				stat.setString(3, ta.getAreaTrabajos().get(0));
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

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false;
		}
	}

}
