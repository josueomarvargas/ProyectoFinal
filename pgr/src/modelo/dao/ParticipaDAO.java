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
import modelo.clases.Participa;

/**
 * La clase {@code ParticipaDAO} es una clase que implementa la interfaz
 * gen�rica {@link controlador.interfaz.BDgeneric BDgeneric}, esta interfaz crea
 * m�todos CRUD necesarios para gestionar tabla {@link modelo.clases.Participa
 * Participa}
 * 
 * @author Henrique Yeguo
 * 
 **/
public class ParticipaDAO implements BDgeneric<Participa> {

	// MySQL Consultas
	private final String CREATE = "INSERT INTO participa(idTrabajador, idObra) VALUES(?, ?)";
	private final String SEARCH = "SELECT * FROM participa WHERE idObra = ?";
	private final String READALL = "SELECT * FROM participa";
	private final String UPDATE = "UPDATE participa SET idTrabajador = ? WHERE idTrabajador = ? AND idObra = ?";
	private final String DELETE = "DELETE FROM participa WHERE idTrabajador = ?";

	// Establecer conexi�n a la base de datos
	private static Connection con = SQLCon.getConnection();
	private PreparedStatement stat;

	@Override
	public boolean create(Participa clase) throws SQLException {

		try {
			// Prepare Statement - Create
			stat = con.prepareStatement(CREATE);

			// Si va a insertar m�s de 1 idTrabajador
			if (clase.getIdTrabajador().size() > 1) {
				int i = 0;
				// A�adir al grupo cada idTrabajador
				for (Integer idTrabajador : clase.getIdTrabajador()) {
					// A�adir datos al Prepare Statement
					stat.setInt(1, idTrabajador);
					stat.setInt(2, clase.getIdObra());
					// A�adimos los comandos al grupo
					stat.addBatch();
					i++;

					if (i % 1000 == 0 || i == clase.getIdTrabajador().size()) {
						// Ejecutar� los STAT en una sola llamada al servidor
						return stat.executeBatch().length > 0 ? true : false;
						// Seg�n la API si recibe un n�mero mayor que 0 se a ejecutado correctamente

					}
				}
			} else {
				stat.setInt(1, clase.getIdTrabajador().get(0));
				stat.setInt(2, clase.getIdObra());
			}

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false; // Si hay alguna excepcion devolver� false
		}
	}

	@Override
	public Participa search(String id) throws SQLException {

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		List<Integer> idTrabajador = new ArrayList<>();
		Participa part = null;

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
				if (part == null) {
					part = new Participa();
					part.setIdObra(rs.getInt(2));
				}
				idTrabajador.add(rs.getInt(1));
			}
			if (part != null && !idTrabajador.isEmpty()) {
				part.setIdTrabajador(idTrabajador);
			}

			return part;
		} catch (SQLException e) {
			System.err.println(e);
			return part;
		}

	}

	@Override
	public Map<String, Participa> readAll() throws SQLException {

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		Map<String, Participa> participantes = new TreeMap<>();
		List<Integer> idTrabajador = null;
		Participa part = null;

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

				if (part == null || part.getIdObra() != rs.getInt(2)) {
					if (part != null) {
						part.setIdTrabajador(idTrabajador);
						participantes.put(Integer.toString(part.getIdObra()), part);
					}
					// Creamos una instancia del objecto y guardamos solo el ID
					part = new Participa();
					idTrabajador = new ArrayList<>();
					part.setIdObra(rs.getInt(2));
				}

				// Comprobamos que el ID que sea igual que el ID de la consulta
				if (part.getIdObra() == rs.getInt(2)) {
					// A�adimos la idTrabajador a la lista
					idTrabajador.add(rs.getInt(1));
				}

				if (rs.isLast() == true) {
					part.setIdTrabajador(idTrabajador);
					participantes.put(Integer.toString(part.getIdObra()), part);
				}
			}

		} catch (SQLException e) {
			System.err.println(e);
		}
		return participantes;
	}

	@Override
	public boolean update(Participa clase) throws SQLException {

		// Clase para guardar los antiguos datos
		Participa partAux = this.search(Integer.toString(clase.getIdObra()));

		// Ordenamos las dos listas para que luego comparar con el
		// que hemos recuperado de la base de datos
		clase.sortList();
		partAux.sortList();

		try {
			// Prepare Statement - Update
			stat = con.prepareStatement(UPDATE);

			// Si va a actualizar m�s de 1 atributo
			if (clase.getIdTrabajador().size() > 1) {
				int i = 0;
				// Iterar por las 2 listas para comprobar cual es la que no coincide
				for (int j = 0; j < clase.getIdTrabajador().size(); j++) {
					if (!partAux.getIdTrabajador().get(j).equals(clase.getIdTrabajador().get(j))) {

						// A�adir datos al Prepare Statement
						stat.setInt(1, clase.getIdTrabajador().get(j));
						stat.setInt(2, partAux.getIdTrabajador().get(j));
						stat.setInt(3, clase.getIdObra());
						// A�adimos los comandos al grupo
						stat.addBatch();
						i++;
					}
				}

				if (i % 1000 == 0 || i == clase.getIdTrabajador().size()) {
					// Ejecutar� los batch en una sola llamada al servidor
					return stat.executeBatch().length > 0 ? true : false;
					// Seg�n la API si recibe un n�mero mayor que 0 se a ejecutado correctamente

				}

				// Cuando solo se actualiza 1 atributo
			} else {
				stat.setInt(1, clase.getIdTrabajador().get(0));
				stat.setInt(3, partAux.getIdTrabajador().get(0));
				stat.setInt(2, clase.getIdObra());
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
