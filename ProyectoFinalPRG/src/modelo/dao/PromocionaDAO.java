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
import modelo.clases.Promociona;

public class PromocionaDAO implements BDgeneric<Promociona> {

	// MySQL Consultas
	private final String CREATE = "INSERT INTO promociona(idObra, idPatro) VALUES(?, ?)";
	private final String SEARCH = "SELECT * FROM promociona WHERE idObra = ?";
	private final String READALL = "SELECT * FROM promociona";
	private final String UPDATE = "UPDATE promociona SET idPatro = ? WHERE idObra = ? AND idPatro = ?";
	private final String DELETE = "DELETE FROM promociona WHERE idObra = ? AND idPatro = ?";

	// Establecer conexi�n a la base de datos
	private static Connection con = SQLCon.getConnection();
	private PreparedStatement stat;

	@Override
	public boolean create(Promociona clase) throws SQLException {

		try {
			// Prepare Statement - Create
			stat = con.prepareStatement(CREATE);

			// Si va a insertar m�s de 1 patro
			if (clase.getIdPatro().size() > 1) {
				int i = 0;
				// A�adir al grupo cada patro
				for (Integer patro : clase.getIdPatro()) {
					// A�adir datos al Prepare Statement
					stat.setInt(1, clase.getIdObra());
					stat.setInt(2, patro);
					// A�adir comando al grupo
					stat.addBatch();
					i++;

					if (i % 1000 == 0 || i == clase.getIdPatro().size()) {
						// Ejecutar� los STAT en una sola llamada al servidor
						return stat.executeBatch().length > 0 ? true : false;
						// Seg�n la API si recibe un n�mero mayor que 0 se a ejecutado correctamente

					}
				}
			} else {
				stat.setInt(1, clase.getIdObra());
				stat.setInt(2, clase.getIdPatro().get(0));
			}

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false; // Si hay alguna excepcion devolver� false
		}
	}

	@Override
	public Promociona search(String id) throws SQLException {

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		List<Integer> patroList = new ArrayList<>();
		Promociona pro = null;

		try {
			// Prepare Statement - Search
			stat = con.prepareStatement(SEARCH);

			// A�adir datos al Prepare Statement
			stat.setInt(1, Integer.parseInt(id));

			// Ejecutar consulta y guardarlo en el Result Set
			rs = stat.executeQuery();

			// Comprobar que RS a recuperado informacion del executeQuery
			while (rs.next()) { // Como este objecto tiene una lista por ende la consulta devuelve varios datos
				// Creamos una instancia del objecto y a�adimos los valores del RS al objecto
				if (pro == null) {
					pro = new Promociona();
					pro.setIdObra(rs.getInt(1));
				}
				patroList.add(rs.getInt(2));
			}
			if (pro != null && !patroList.isEmpty()) {
				pro.setIdPatro(patroList);
			}

		} catch (SQLException e) {
			System.err.println(e);
		}
		return pro;
	}

	@Override
	public Map<String, Promociona> readAll() throws SQLException {

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		Map<String, Promociona> proMap = new TreeMap<>();
		List<Integer> patro = null;
		Promociona pro = null;

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

				if (pro == null || pro.getIdObra() != rs.getInt(1)) {
					if (pro != null) {
						pro.setIdPatro(patro);
						proMap.put(Integer.toString(pro.getIdObra()), pro);
					}
					// Creamos una instancia del objecto y guardamos solo el ID
					pro = new Promociona();
					patro = new ArrayList<>();
					pro.setIdObra(rs.getInt(1));
				}

				// Comprobamos que el ID que sea igual que el ID de la consulta
				if (pro.getIdObra() == rs.getInt(1)) {
					// A�adimos la patro a la lista
					patro.add(rs.getInt(2));
				}

				if (rs.isLast() == true) {
					pro.setIdPatro(patro);
					proMap.put(Integer.toString(pro.getIdObra()), pro);
				}
			}

		} catch (SQLException e) {
			System.err.println(e);
		}
		return proMap;
	}

	@Override
	public boolean update(Promociona clase) throws SQLException {

		// Clase proor para guardar los antiguos datos
		Promociona pro = this.search(Integer.toString(clase.getIdObra()));

		// Ordenamos las dos listas para que luego comparar con el
		// que hemos recuperado de la base de datos
		clase.sortList();
		pro.sortList();

		try {
			// Prepare Statement - Update
			stat = con.prepareStatement(UPDATE);

			// Si va a proualizar m�s de 1 patro
			if (clase.getIdPatro().size() > 1) {
				int i = 0;
				// Iterar por las 2 listas para comprobar cual es la que no coincide
				for (int j = 0; j < clase.getIdPatro().size(); j++) {
					if (!pro.getIdPatro().get(j).equals(clase.getIdPatro().get(j))) {

						// A�adir datos al Prepare Statement
						stat.setInt(1, clase.getIdPatro().get(j));
						stat.setInt(2, clase.getIdObra());
						stat.setInt(3, pro.getIdPatro().get(j));
						// A�adimos los comandos al grupo
						stat.addBatch();
						i++;
					}
				}

				if (i % 1000 == 0 || i == clase.getIdPatro().size()) {
					// Ejecutar� los batch en una sola llamada al servidor
					return stat.executeBatch().length > 0 ? true : false;
					// Seg�n la API si recibe un n�mero mayor que 0 se a ejecutado correctamente

				}

				// Cuando solo se proualiza 1 patro
			} else {
				stat.setInt(1, clase.getIdPatro().get(0));
				stat.setInt(2, clase.getIdObra());
				stat.setInt(3, pro.getIdPatro().get(0));
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
			stat.setInt(1, Integer.parseInt(id[0]));
			stat.setInt(2, Integer.parseInt(id[1]));

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false;
		}
	}

}
