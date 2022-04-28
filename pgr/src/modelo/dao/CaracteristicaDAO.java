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
import modelo.clases.Equipamiento;

public class CaracteristicaDAO implements BDgeneric<Equipamiento> {

	// MySQL Consultas
	private final String CREATE = "INSERT INTO caracteristica(idEquip, caracteristica) VALUES(?, ?)";
	private final String SEARCH = "SELECT * FROM caracteristica WHERE idEquip = ?";
	private final String READALL = "SELECT * FROM caracteristica";
	private final String UPDATE = "UPDATE caracteristica SET caracteristica = ? WHERE idEquip = ? AND caracteristica = ?";
	private final String DELETE = "DELETE FROM caracteristica WHERE idEquip = ?";

	// Establecer conexi�n a la base de datos
	private static Connection con = SQLCon.getConnection();
	private PreparedStatement stat;

	@Override
	public boolean create(Equipamiento clase) throws SQLException {

		try {
			// Prepare Statement - Create
			stat = con.prepareStatement(CREATE);

			// Si va a insertar m�s de 1 caracteristica
			if (clase.getCaracteristicas().size() > 1) {
				int i = 0;
				// A�adir al grupo cada atributo
				for (String atributo : clase.getCaracteristicas()) {
					// A�adir datos al Prepare Statement
					stat.setInt(1, clase.getIdEquip());
					stat.setString(2, atributo);
					// A�adimos los comandos al grupo
					stat.addBatch();
					i++;

					if (i % 1000 == 0 || i == clase.getCaracteristicas().size()) {
						// Ejecutar� los STAT en una sola llamada al servidor
						return stat.executeBatch().length > 0 ? true : false;
						// Seg�n la API si recibe un n�mero mayor que 0 se a ejecutado correctamente

					}
				}
			} else {
				stat.setInt(1, clase.getIdEquip());
				stat.setString(2, clase.getCaracteristicas().get(0));
			}

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			return false; // Si hay alguna excepcion devolver� false
		}

	}

	@Override
	public Equipamiento search(String id) throws SQLException {

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		List<String> caracteristicas = new ArrayList<>();
		Equipamiento equip = null;

		try {
			// Prepare Statement - Search
			stat = con.prepareStatement(SEARCH);

			// A�adir datos al Prepare Statement
			stat.setString(1, id);

			// Ejecutar consulta y guardarlo en el Result Set
			rs = stat.executeQuery();

			// Comprobar que RS a recuperado informacion del executeQuery
			while (rs.next()) { // Como en esta tabla uno puede tener varios valores un while en vez de if
				// Creamos una instancia del objecto y a�adimos los valores del RS al objecto
				if (equip == null) {
					equip = new Equipamiento();
					equip.setIdEquip((rs.getInt(1)));
				}
				caracteristicas.add(rs.getString(2));
			}
			if (equip != null && !caracteristicas.isEmpty()) {
				equip.setCaracteristicas(caracteristicas);
			}

		} catch (SQLException e) {
			System.err.println(e);
		}
		return equip;

	}

	@Override
	public Map<String, Equipamiento> readAll() throws SQLException {

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		Map<String, Equipamiento> equips = new TreeMap<>();
		List<String> caracteristicas = null;
		Equipamiento equip = null;

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
				 * siendo null, crear� un objecto equip donde se guarda su ID y la lista donde
				 * se guarda las caracteristicas. En la siguiente condici�n comprueba que el ID
				 * del equip coincida con la ID de la consulta y a�ade el atributo a la lista,
				 * cuando vuelva al inicio solo entrar� si los IDs no coinciden, si coincide
				 * ser� que es el mismo equip y se le volver� a introducir otra atributo, si es
				 * diferente entrar� otra vez en la condici�n como esta vez no es null guarda
				 * los datos del equip y los pone en el map y volver� a crear el equip y la
				 * lista. Cuando sea el �ltimo result set guardar� los datos al map.
				 */

				if (equip == null || equip.getIdEquip() != rs.getInt(1)) {
					if (equip != null) {
						equip.setCaracteristicas(caracteristicas);
						equips.put(Integer.toString(equip.getIdEquip()), equip);
					}
					// Creamos una instancia del objecto y guardamos solo el ID
					equip = new Equipamiento();
					caracteristicas = new ArrayList<>();
					equip.setIdEquip(rs.getInt(1));
				}

				// Comprobamos que el ID que sea igual que el ID de la consulta
				if (equip.getIdEquip() == rs.getInt(1)) {
					// A�adimos el atributo a la lista
					caracteristicas.add(rs.getString(2));
				}

				if (rs.isLast() == true) {
					equip.setCaracteristicas(caracteristicas);
					equips.put(Integer.toString(equip.getIdEquip()), equip);
				}
			}

		} catch (SQLException e) {
			System.err.println(e);
		}
		return equips;
	}

	@Override
	public boolean update(Equipamiento clase) throws SQLException {

		// Clase para guardar los antiguos datos
		Equipamiento equip = this.search(Integer.toString(clase.getIdEquip()));

		// Ordenamos las dos listas alfab�ticamente para que luego comparar con el
		// que hemos recuperado de la base de datos
		clase.sortList();
		equip.sortList();

		try {
			// Prepare Statement - Update
			stat = con.prepareStatement(UPDATE);

			// Si va a actualizar m�s de 1 atributo
			if (clase.getCaracteristicas().size() > 1) {
				int i = 0;
				// Iterar por las 2 listas para comprobar cual es la que no coincide
				for (int j = 0; j < clase.getCaracteristicas().size(); j++) {
					if (!equip.getCaracteristicas().get(j).equals(clase.getCaracteristicas().get(j))) {

						// A�adir datos al Prepare Statement
						stat.setString(1, clase.getCaracteristicas().get(j));
						stat.setInt(2, clase.getIdEquip());
						stat.setString(3, equip.getCaracteristicas().get(j));
						// A�adimos los comandos al grupo
						stat.addBatch();
						i++;
					}
				}

				if (i % 1000 == 0 || i == clase.getCaracteristicas().size()) {
					// Ejecutar� los batch en una sola llamada al servidor
					return stat.executeBatch().length > 0 ? true : false;
					// Seg�n la API si recibe un n�mero mayor que 0 se a ejecutado correctamente

				}

				// Cuando solo se actualiza 1 atributo
			} else {
				stat.setString(1, clase.getCaracteristicas().get(0));
				stat.setInt(2, clase.getIdEquip());
				stat.setString(3, equip.getCaracteristicas().get(0));
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
