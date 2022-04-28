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
import modelo.clases.Guionista;

public class GuionistaDAO implements BDgeneric<Guionista> {

	// MySQL Consultas
	private final String CREATE = "INSERT INTO tipoguion(idTrabajador, tipoGuion) VALUES(?, ?)";
	private final String SEARCH = "SELECT * FROM tipoguion WHERE idTrabajador = ?";
	private final String READALL = "SELECT * FROM tipoguion";
	private final String UPDATE = "UPDATE tipoguion SET tipoGuion = ? WHERE idTrabajador = ? AND tipoGuion = ?";
	private final String DELETE = "DELETE FROM tipoguion WHERE idTrabajador = ? AND tipoGuion = ?";

	// Establecer conexi�n a la base de datos
	private static Connection con = SQLCon.getConnection();
	private PreparedStatement stat;

	@Override
	public boolean create(Guionista clase) throws SQLException {

		try {
			// Prepare Statement - Create
			stat = con.prepareStatement(CREATE);

			// Si va a insertar m�s de 1 especialidad
			if (clase.getTipoGuiones().size() > 1) {
				int i = 0;
				// A�adir al grupo cada especialidad
				for (String tipoGuion : clase.getTipoGuiones()) {
					// A�adir datos al Prepare Statement
					stat.setInt(1, clase.getIdTrabajador());
					stat.setString(2, tipoGuion);
					// A�adimos los comandos al grupo
					stat.addBatch();
					i++;

					if (i % 1000 == 0 || i == clase.getTipoGuiones().size()) {
						// Ejecutar� los STAT en una sola llamada al servidor
						return stat.executeBatch().length > 0 ? true : false;
						// Seg�n la API si recibe un n�mero mayor que 0 se a ejecutado correctamente

					}
				}
			} else {
				stat.setInt(1, clase.getIdTrabajador());
				stat.setString(2, clase.getTipoGuiones().get(0));
			}

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false; // Si hay alguna excepcion devolver� false
		}
	}

	@Override
	public Guionista search(String id) throws SQLException {
		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		List<String> especialidad = new ArrayList<>();
		Guionista guionista = null;

		try {
			// Prepare Statement - Search
			stat = con.prepareStatement(SEARCH);

			// A�adir datos al Prepare Statement
			stat.setString(1, id);

			// Ejecutar consulta y guardarlo en el Result Set
			rs = stat.executeQuery();

			// Comprobar que RS a recuperado informacion del executeQuery
			while (rs.next()) { // Como este objecto su atributo es una lista un while en vez de if
				// Creamos una instancia del objecto y a�adimos los valores del RS al objecto
				if (guionista == null) {
					guionista = new Guionista();
					guionista.setIdTrabajador(rs.getInt(1));
				}
				especialidad.add(rs.getString(2));
			}
			if (guionista != null && !especialidad.isEmpty()) {
				guionista.setTipoGuiones(especialidad);
			}

			return guionista;
		} catch (SQLException e) {
			System.err.println(e);
			return guionista;
		}
	}

	@Override
	public Map<String, Guionista> readAll() throws SQLException {

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		Map<String, Guionista> guionistas = new TreeMap<>();
		List<String> guion = null;
		Guionista guionista = null;

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
				 * siendo null, crear� un objecto Guionista donde se guarda su ID y la lista
				 * donde se guarda las especialidades. En la siguiente condici�n comprueba que
				 * el ID coincida con la ID de la consulta y a�ade la especialidad a la lista,
				 * cuando vuelva al inicio solo entrar� si los IDs no coinciden, si coincide
				 * ser� que es el mismo Guionista y se le volver� a introducir otra
				 * especialidad, si es diferente entrar� otra vez en la condici�n como esta vez
				 * no es null guarda los datos y los pone en el map y volver� a crear el
				 * guionista y la lista. Cuando sea el �ltimo result set guardar� los datos al
				 * map.
				 */

				if (guionista == null || guionista.getIdTrabajador() != rs.getInt(1)) {
					if (guionista != null) {
						guionista.setTipoGuiones(guion);
						guionistas.put(Integer.toString(guionista.getIdTrabajador()), guionista);
					}
					// Creamos una instancia del objecto y guardamos solo el ID
					guionista = new Guionista();
					guion = new ArrayList<>();
					guionista.setIdTrabajador(rs.getInt(1));
				}

				// Comprobamos que el ID que sea igual que el ID de la consulta
				if (guionista.getIdTrabajador() == rs.getInt(1)) {
					// A�adimos la especialidad a la lista
					guion.add(rs.getString(2));
				}

				if (rs.isLast() == true) {
					guionista.setTipoGuiones(guion);
					guionistas.put(Integer.toString(guionista.getIdTrabajador()), guionista);
				}
			}

		} catch (SQLException e) {
			System.err.println(e);
		}
		return guionistas;
	}

	@Override
	public boolean update(Guionista clase) throws SQLException {
		// Clase para guardar los antiguos datos
		Guionista guionista = this.search(Integer.toString(clase.getIdTrabajador()));

		// Ordenamos las dos listas alfab�ticamente para que luego comparar con el
		// que hemos recuperado de la base de datos
		clase.sortList();
		guionista.sortList();

		try {
			// Prepare Statement - Update
			stat = con.prepareStatement(UPDATE);

			// Si va a actualizar m�s de 1 especialidad
			if (clase.getTipoGuiones().size() > 1) {
				int i = 0;
				// Iterar por las 2 listas para comprobar cual es la que no coincide
				for (int j = 0; j < clase.getTipoGuiones().size(); j++) {
					if (!guionista.getTipoGuiones().get(j).equals(clase.getTipoGuiones().get(j))) {

						// A�adir datos al Prepare Statement
						stat.setString(1, clase.getTipoGuiones().get(j));
						stat.setInt(2, clase.getIdTrabajador());
						stat.setString(3, guionista.getTipoGuiones().get(j));
						// A�adimos los comandos al grupo
						stat.addBatch();
						i++;
					}
				}

				if (i % 1000 == 0 || i == clase.getTipoGuiones().size()) {
					// Ejecutar� los batch en una sola llamada al servidor
					return stat.executeBatch().length > 0 ? true : false;
					// Seg�n la API si recibe un n�mero mayor que 0 se a ejecutado correctamente

				}

				// Cuando solo se actualiza 1 especialidad
			} else {
				stat.setString(1, clase.getTipoGuiones().get(0));
				stat.setInt(2, clase.getIdTrabajador());
				stat.setString(3, guionista.getTipoGuiones().get(0));
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
