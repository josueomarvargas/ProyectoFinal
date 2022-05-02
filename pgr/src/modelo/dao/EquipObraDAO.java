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
import modelo.clases.EquipObra;

/**
 * La clase {@code EquipObraDAO} es una clase que implementa la interfaz
 * genérica {@link controlador.interfaz.BDgeneric BDgeneric}, esta interfaz crea
 * métodos CRUD necesarios para gestionar tabla {@link modelo.clases.EquipObra
 * EquipOBra}
 * 
 * @author Henrique Yeguo
 * 
 **/
public class EquipObraDAO implements BDgeneric<EquipObra> {

	// MySQL Consultas
	private final String CREATE = "INSERT INTO usa(idObra, idEquip) VALUES(?, ?)";
	private final String SEARCH = "SELECT * FROM usa WHERE idObra = ? AND idEquip";
	private final String READALL = "SELECT * FROM usa";
	private final String UPDATE = "UPDATE usa SET idEquip = ?  WHERE idObra = ? AND idEquip = ?";
	private final String DELETE = "DELETE FROM usa WHERE idObra = ? AND idEquip = ?";

	// Establecer conexión a la base de datos
	private static Connection con = SQLCon.getConnection();
	private PreparedStatement stat;

	@Override
	public boolean create(EquipObra clase) throws SQLException {

		try {
			// Prepare Statement - Create
			stat = con.prepareStatement(CREATE);

			// Añadir datos al Prepare Statement
			stat.setInt(1, clase.getIdObra());
			stat.setInt(2, clase.getIdEquip().get(0));

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false; // Si hay alguna excepcion devolverá false
		}
	}

	@Override
	public EquipObra search(String id) throws SQLException {

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		List<Integer> idEquip = new ArrayList<>();
		EquipObra eo = null;

		try {
			// Prepare Statement - Search
			stat = con.prepareStatement(SEARCH);

			// Añadir datos al Prepare Statement
			stat.setString(1, id);

			// Ejecutar consulta y guardarlo en el Result Set
			rs = stat.executeQuery();

			// Comprobar que RS a recuperado informacion del executeQuery
			while (rs.next()) { // Como en esta tabla uno puede tener varios valores un while en vez de if
				// Creamos una instancia del objecto y añadimos los valores del RS al objecto
				if (eo == null) {
					eo = new EquipObra();
					eo.setIdObra((rs.getInt(1)));
				}
				idEquip.add(rs.getInt(2));
			}
			if (eo != null && idEquip.isEmpty()) {
				eo.setIdEquip(idEquip);
			}

		} catch (SQLException e) {
			System.err.println(e);
		}

		// Devolvemos el objecto, si RS NO ha devuelto nada, devolverá NULL
		return eo;
	}

	@Override
	public Map<String, EquipObra> readAll() throws SQLException {
		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		Map<String, EquipObra> equipObra = new TreeMap<>();
		List<Integer> idEquip = null;
		EquipObra eo = null;

		try {
			// Prepare Statement - Read All
			stat = con.prepareStatement(READALL);

			// Ejecutar consulta y guardarlo en el Result Set
			rs = stat.executeQuery();

			// Comprobar que RS a recuperado informacion del executeQuery
			while (rs.next()) {

				/*
				 * Esto es lo que hará este loop: La primera vez que entre el la condición será
				 * null, por lo tanto entra, la concición de dentro no entrará porque aún sigue
				 * siendo null, creará un objecto donde se guardará su ID y la lista donde
				 * guardaremos los datos. En la siguiente condición comprueba que el ID del
				 * equip coincida con la ID de la consulta y añade el dato a la lista, cuando
				 * vuelva al inicio solo entrará si los IDs no coinciden, si coincide será que
				 * es el mismo ID y querrá decir que hay más datos por introducir, si es
				 * diferente entrará otra vez en la condición como esta vez no es null guarda
				 * los datos en el map y volverá a crear el objeto y la lista. Cuando sea el
				 * último result set guardará los datos al map.
				 */
				if (eo == null || eo.getIdObra() != rs.getInt(1)) {
					if (eo != null) {
						eo.setIdEquip(idEquip);
						equipObra.put(Integer.toString(eo.getIdObra()), eo);
					}
					// Creamos una instancia del objecto y guardamos solo el ID
					eo = new EquipObra();
					idEquip = new ArrayList<>();
					eo.setIdObra(rs.getInt(1));
				}

				// Comprobamos que el ID que sea igual que el ID de la consulta
				if (eo.getIdObra() == rs.getInt(1)) {
					// Añadimos el atributo a la lista
					idEquip.add(rs.getInt(2));
				}

				if (rs.isLast() == true) {
					eo.setIdEquip(idEquip);
					equipObra.put(Integer.toString(eo.getIdObra()), eo);
				}
			}

		} catch (SQLException e) {
			System.err.println(e);
		}
		return equipObra;
	}

	@Override
	public boolean update(EquipObra clase) throws SQLException {
		// Clase para guardar los antiguos datos
		EquipObra eoAux = this.search(Integer.toString(clase.getIdObra()));

		// Ordenamos las dos listas para que luego comparar con el
		// que hemos recuperado de la base de datos
		clase.sortList();
		eoAux.sortList();

		try {
			// Prepare Statement - Update
			stat = con.prepareStatement(UPDATE);

			// Si va a actualizar más de 1 atributo
			if (clase.getIdEquip().size() > 1) {
				int i = 0;
				// Iterar por las 2 listas para comprobar cual es la que no coincide
				for (int j = 0; j < clase.getIdEquip().size(); j++) {
					if (!eoAux.getIdEquip().get(j).equals(clase.getIdEquip().get(j))) {

						// Añadir datos al Prepare Statement
						stat.setInt(1, clase.getIdEquip().get(j));
						stat.setInt(2, clase.getIdObra());
						stat.setInt(3, eoAux.getIdEquip().get(j));
						// Añadimos los comandos al grupo
						stat.addBatch();
						i++;
					}
				}

				if (i % 1000 == 0 || i == clase.getIdEquip().size()) {
					// Ejecutará los batch en una sola llamada al servidor
					return stat.executeBatch().length > 0 ? true : false;
					// Según la API si recibe un número mayor que 0 se a ejecutado correctamente

				}

				// Cuando solo se actualiza 1 atributo
			} else {
				stat.setInt(2, clase.getIdObra());
				stat.setInt(1, clase.getIdEquip().get(0));
				stat.setInt(3, eoAux.getIdEquip().get(0));
			}

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
			stat.setString(2, id[1]);

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false;
		}
	}

}
