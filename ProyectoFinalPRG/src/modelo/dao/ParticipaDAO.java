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
import modelo.clases.Participa;

/**
 * La clase {@code ParticipaDAO} es una clase que implementa la interfaz
 * genérica {@link controlador.interfaz.BDgeneric BDgeneric}, esta interfaz crea
 * métodos CRUD necesarios para gestionar tabla {@link modelo.clases.Participa
 * Participa}
 * 
 * @author Henrique Yeguo
 * 
 **/
public class ParticipaDAO implements BDgeneric<Participa> {

	// MySQL Consultas
	// Insertar datos
	private final String CREATE = "INSERT INTO participa(idTrabajador, idObra) VALUES(?, ?)";

	// Buscar los trabajadores que han participado en una obra
	private final String SEARCHTRABAJADOR = "SELECT idTrabajador FROM participa WHERE idObra = ?";

	// Buscar las obras que han participado un trabajador
	private final String SEARCHOBRA = "SELECT idObra FROM participa WHERE idTrabajador = ?";

	// Eliimar
	private final String DELETEPARTICIPA = "DELETE FROM participa WHERE idTrabajador = ? AND idObra = ?";
	private final String DELETETRABAJADOR = "DELETE FROM participa WHERE idTrabajador = ?";
	private final String DELETEOBRA = "DELETE FROM participa WHERE idObra = ?";

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
	 * Método para insertar participantes a obras y viceversa, en la relación
	 * "Trabajador-obra".
	 * 
	 * @param clase la clase participa que se pasa por parámetros una de las listas
	 *              solo debe tener 1 valor, ese ID será usado para diferenciar si
	 *              debemmos de añadir multiples obras o trabajadores.
	 * @return true si se ha ejecutado correctamente la consulta
	 * 
	 **/
	@Override
	public boolean create(Participa clase) {

		this.openConnection();

		// Prepare Statement - Create
		try (PreparedStatement stat = con.prepareStatement(CREATE)) {

			// Inicio de la transacción
			con.setAutoCommit(false);

			if (clase.getIdTrabajador().size() == 1) {
				for (Integer idObra : clase.getIdObra()) {
					stat.setInt(1, clase.getIdTrabajador().get(0));
					stat.setInt(2, idObra);
					stat.addBatch();
				}

			} else {
				for (Integer idTrabajador : clase.getIdTrabajador()) {
					stat.setInt(1, idTrabajador);
					stat.setInt(2, clase.getIdObra().get(0));
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
			rollback(e);
			return false; // Si hay alguna excepcion devolverá false
		} finally {
			this.closeConnection();
		}
	}

	/**
	 * Método para leer los datos de la relación dependiendo de que ID se use para
	 * buscar, en el caso de que se busque por obras se recogerán los IDs de los
	 * {@code Trabajadores} que se participan en esa obra, y viceversa si se busca
	 * por trabajadores se guardará los IDs de las obras en las que ha participado
	 * ese trabajador.
	 * 
	 * @param id el ID es un array para saber que ID se quiere buscar, el ID en el
	 *           índice 0: obra, 1: trabajador.
	 * @return objecto equip-obra con los datos los IDs de equipamientos u obras
	 **/
	@Override
	public Participa search(String[] id) {

		this.openConnection();

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		List<Integer> aux = null, idAux = new ArrayList<>();
		Participa part = null;
		boolean searchObra;

		try (PreparedStatement obraStat = con.prepareStatement(SEARCHOBRA);
				PreparedStatement trabajadorStat = con.prepareStatement(SEARCHTRABAJADOR)) {

			// Si hay ID en el índice 0, buscar por obra
			if (id[0].isBlank()) {
				obraStat.setString(1, id[0]);
				rs = obraStat.executeQuery();
				searchObra = true;
				// Si no, esta en el índice 1, buscar por equipamiento
			} else {
				trabajadorStat.setString(1, id[1]);
				rs = trabajadorStat.executeQuery();
				searchObra = false;
			}

			// Comprobar que RS a recuperado informacion del executeQuery
			while (rs.next()) {
				// Creamos una instancia del objecto
				if (part == null) {
					part = new Participa();
					aux = new ArrayList<>();
				}
				// Añadir el ID a la lista auxiliar
				aux.add(rs.getInt(1));

				// Si el RS está en la última fila
				if (rs.isLast()) {
					// En el caso de que se ha buscado por obras
					if (searchObra) {
						// Añadir ID a la lista
						idAux.add(Integer.parseInt(id[0]));
						// Añadir las listas al objecto
						part.setIdObra(idAux);
						part.setIdTrabajador(aux);
					} else {
						idAux.add(Integer.parseInt(id[1]));
						part.setIdTrabajador(idAux);
						part.setIdObra(aux);
					}
				}
			}

		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			this.closeConnection();
		}

		return part;
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
	public Map<String, Participa> readAll() {
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
	public boolean update(Participa clase) {
		this.openConnection();

		try (PreparedStatement delObra = con.prepareStatement(DELETEOBRA);
				PreparedStatement delPatro = con.prepareStatement(DELETETRABAJADOR);
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
				delPatro.setInt(1, clase.getIdTrabajador().get(0));
				delPatro.executeUpdate();
			}

			// En el caso de que queramos insertar varias obras en un patrocinador
			if (clase.getIdTrabajador().size() == 1) {
				for (Integer idObra : clase.getIdObra()) {
					// Añadir datos al Prepare Statement
					stat.setInt(1, idObra);
					stat.setInt(2, clase.getIdTrabajador().get(0));
					stat.addBatch();
				}

				// Insertar varios patrocinadores en una obra
			} else {
				for (Integer idPatro : clase.getIdTrabajador()) {

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
	 * Método para quitar un trabajador de una obra
	 * 
	 * @param id Los identificadores para eliminar un trabajador de una obra, el
	 *           índice 0 del array: idTrabajador, y el 1: idObra
	 * @return true si se ha ejecutado correctamente la consulta
	 **/
	@Override
	public boolean remove(String[] id) {

		this.openConnection();

		// Prepare Statement - Delete
		try (PreparedStatement stat = con.prepareStatement(DELETEPARTICIPA)) {

			// Añadir datos al Prepare Statement
			stat.setString(1, id[0]);
			stat.setString(2, id[1]);

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
