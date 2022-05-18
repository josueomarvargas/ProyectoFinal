package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controlador.interfaz.BDgeneric;
import controlador.utils.dao.SQLCon;
import modelo.clases.Promociona;

public class PromocionaDAO implements BDgeneric<Promociona> {

	// MySQL Consultas
	// Insertar Obra - Patrocinador
	private final String CREATE = "INSERT INTO promociona(idObra, idPatro) VALUES(?, ?)";

	// Buscar las obras que promociona un patrocinador
	private final String SEARCHOBRA = "SELECT idObra FROM promociona WHERE idPatro = ?";

	// Buscar los patrocinadores que són promocionados en una obra
	private final String SEARCHPATRO = "SELECT idPatro FROM promociona WHERE idObra = ?";

	// Eliminar datos
	private final String DELETEPROMOCIONA = "DELETE FROM promociona WHERE idObra = ? AND idPatro = ?";
	private final String DELETEPATRO = "DELETE FROM promociona WHERE idPatro = ?";
	private final String DELETEOBRA = "DELETE FROM promociona WHERE idObra = ?";

	// Establecer conexión a la base de datos
	private static Connection con;

	/**
	 * Método para abrir la conexión, este método llama al
	 * {@link SQLCon#openConnection}.
	 **/
	private void openConnection() {
		con = SQLCon.openConnection();
	}

	/**
	 * Método para cerrar la conexión, este método llama al
	 * {@link SQLCon#closeConnection()} y {@code con.Close} para cerra la conexión
	 * aquí y en el objecto {@code SQLCon}
	 **/
	private void closeConnection() {
		try {
			// Cerrar la conexión aquí y en el SQLCon
			con.close();
			SQLCon.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para revertir los cambios
	 * 
	 * @param e Se pasa por parámetros la exceptión que recoge el catch,
	 **/
	private void rollback(Exception e) {
		try {
			// Revertir los cambios en el objecto Connection
			con.rollback();
			con.setAutoCommit(true);
			System.err.println(e + "\nProcediendo a revertir los cambios, iniciando rollback...");
		} catch (SQLException e1) {
			e.printStackTrace();

		}

	}

	/**
	 * Método para insertar patrocinadores a obras y viceversa, en la relación
	 * "Patrocinador-obra".
	 * 
	 * @param clase la clase promociona que se pasa por parámetros una de las listas
	 *              solo debe tener 1 valor, ese ID será usado para diferenciar si
	 *              debemmos de añadir multiples obras o equipamientos.
	 * @return true si se ha ejecutado correctamente la consulta
	 * 
	 **/
	@Override
	public boolean create(Promociona clase) {

		this.openConnection();

		try (PreparedStatement stat = con.prepareStatement(CREATE)) {
			// Inicio de la transacción
			con.setAutoCommit(false);

			// En el caso de que queramos insertar varias obras en un patrocinador
			if (clase.getIdPatro().size() == 1) {
				for (Integer idObra : clase.getIdObra()) {
					// Añadir datos al Prepare Statement
					stat.setInt(1, idObra);
					stat.setInt(2, clase.getIdPatro().get(0));
					stat.addBatch();
				}

				// Insertar varios patrocinadores en una obra
			} else {
				for (Integer idPatro : clase.getIdPatro()) {

					stat.setInt(1, clase.getIdObra().get(0));
					stat.setInt(2, idPatro);
					stat.addBatch();
				}
			}

			// Ejecutar consulta
			stat.executeBatch();

			// Aplicar los cambios
			con.commit();

			// Establecer autocommit por defecto
			con.setAutoCommit(true);

			return true;

		} catch (SQLException e) {
			System.err.println(e);

			return false; // Si hay alguna excepcion devolverá false
		} finally {
			this.closeConnection();
		}
	}

	/**
	 * Método para leer los datos de la relación dependiendo de que ID se use para
	 * buscar, en el caso de que se busque por obras se recogerán los IDs de los
	 * {@code Patrocinadores} que se patrocinan en esa obra, y viceversa si se busca
	 * por patrocinadores se guardará los IDs de las obras en las que promociona.
	 * 
	 * @param id el ID es un array para saber que ID se quiere buscar, el ID en el
	 *           índice 0: obra, 1: patrocinador.
	 * @return objecto equip-obra con los datos los IDs de equipamientos u obras
	 **/
	@Override
	public Promociona search(String[] id) {

		this.openConnection();

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		Promociona pro = null;
		List<Integer> aux = null, idAux = new ArrayList<>();
		boolean searchObra;

		// Prepare Statement - Search
		try (PreparedStatement obraStat = con.prepareStatement(SEARCHOBRA);
				PreparedStatement patroStat = con.prepareStatement(SEARCHPATRO)) {

			// Si hay ID en el índice 0, buscar por obra
			if (!id[0].isBlank()) {
				obraStat.setString(1, id[0]);
				rs = obraStat.executeQuery();
				searchObra = true;
				// Si no, esta en el índice 1, buscar por equipamiento
			} else {
				patroStat.setString(1, id[1]);
				rs = patroStat.executeQuery();
				searchObra = false;
			}

			// Comprobar que RS a recuperado informacion del executeQuery
			while (rs.next()) {
				// Creamos una instancia del objecto
				if (pro == null) {
					pro = new Promociona();
					aux = new ArrayList<>();
				}
				aux.add(rs.getInt(2));
			}
			// Si el RS está en la última fila
			if (rs.isLast()) {
				// En el caso de que se ha buscado por obras
				if (searchObra) {
					// Añadir ID a la lista
					idAux.add(Integer.parseInt(id[0]));
					// Añadir las listas al objecto
					pro.setIdObra(idAux);
					pro.setIdPatro(aux);
				} else {
					idAux.add(Integer.parseInt(id[1]));
					pro.setIdPatro(idAux);
					pro.setIdObra(aux);
				}
			}

		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			this.closeConnection();
		}
		return pro;
	}

	/**
	 * Este método no se usará por lo tanto devolverá null. Al implementar la
	 * interfaz obliga a la clase implementar todos sus métodos, pero en este caso
	 * no necesitamos este método.
	 * 
	 * @return null
	 * @deprecated
	 **/
	@Override
	public Map<String, Promociona> readAll() {
		return null;
	}

	/**
	 * Método para actualizar los datos ya existentes, lo que se ha hecho es
	 * eliminar los datos previamente, y luego volver a introducirlo con los datos
	 * nuevos. Ya que las claves primarias de esta tabla es compuesta y es
	 * complicado actualizar los datos con un {@code UPDATE}
	 * 
	 * @param clase objecto promociona con los datos nuevos
	 * @return true si se ha ejecutado correctamente la consulta
	 */
	@Override
	public boolean update(Promociona clase) {

		this.openConnection();

		try (PreparedStatement delObra = con.prepareStatement(DELETEOBRA);
				PreparedStatement delPatro = con.prepareStatement(DELETEPATRO);
				PreparedStatement stat = con.prepareStatement(CREATE)) {
			// Inicio de la transacción
			con.setAutoCommit(false);

			// Eliminar todos los datos relacionados con una obra
			if (clase.getIdObra().size() == 1) {
				delObra.setInt(1, clase.getIdObra().get(0));
				delObra.executeUpdate();
			}
			// Eliminar todos los datos relacionados con un patrocinador
			else {
				delPatro.setInt(1, clase.getIdPatro().get(0));
				delPatro.executeUpdate();
			}

			// En el caso de que queramos insertar varias obras en un patrocinador
			if (clase.getIdPatro().size() == 1) {
				for (Integer idObra : clase.getIdObra()) {
					// Añadir datos al Prepare Statement
					stat.setInt(1, idObra);
					stat.setInt(2, clase.getIdPatro().get(0));
					stat.addBatch();
				}

				// Insertar varios patrocinadores en una obra
			} else {
				for (Integer idPatro : clase.getIdPatro()) {

					stat.setInt(1, clase.getIdObra().get(0));
					stat.setInt(2, idPatro);
					stat.addBatch();
				}
			}

			// Ejecutar consulta
			stat.executeBatch();

			// Aplicar los cambios
			con.commit();

			// Cambiar a como estaba por defecto

			con.setAutoCommit(true);

			return true;
		} catch (SQLException e) {
			rollback(e);
			return false;

		} finally {
			this.closeConnection();
		}

	}

	/**
	 * Método para quitar una promocion de una obra.
	 * 
	 * @param id Los identificadores para eliminar un equip-obra
	 * @return true si se ejecuta correctamente la consulta
	 **/
	@Override
	public boolean remove(String[] id) {

		this.openConnection();

		// Prepare Statement - Delete
		try (PreparedStatement stat = con.prepareStatement(DELETEPROMOCIONA)) {

			// Añadir datos al Prepare Statement
			stat.setInt(1, Integer.parseInt(id[0]));
			stat.setInt(2, Integer.parseInt(id[1]));

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false;
		} finally {
			this.closeConnection();
		}
	}

}
