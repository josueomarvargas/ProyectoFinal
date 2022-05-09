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
import modelo.clases.EquipObra;

/**
 * La clase {@code EquipObraDAO} es una clase que implementa la interfaz
 * gen�rica {@link controlador.interfaz.BDgeneric BDgeneric}, esta interfaz crea
 * m�todos CRUD necesarios para gestionar tabla {@link modelo.clases.EquipObra
 * EquipOBra}
 * 
 * @author Henrique Yeguo
 * 
 **/
public class EquipObraDAO implements BDgeneric<EquipObra> {

	// MySQL Consultas
	// Insertar equip - obra
	private final String CREATE = "INSERT INTO usa(idObra, idEquip) VALUES(?, ?)";

	// Buscar las obras que usan un equipamiento espec�fico
	private final String SEARCHOBRA = "SELECT idObra FROM usa WHERE idEquip = ?";

	// BUscar los equipamientos que se usan en esa obra espec�fica
	private final String SEARCHEQUIP = "SELECT idEquip FROM usa WHERE idObra = ?";

	// Eliminar un equip - obra
	private final String DELETEUSA = "DELETE FROM usa WHERE idObra = ? AND idEquip = ?";
	private final String DELETEOBRA = "DELETE FROM usa WHERE idObra = ? AND idEquip = ?";
	private final String DELETEEQUIP = "DELETE FROM usa WHERE idObra = ? AND idEquip = ?";

	// Establecer conexi�n a la base de datos
	private static Connection con;

	/**
	 * M�todo para abrir la conexi�n, este m�todo llama al
	 * {@link SQLCon#openConnection}.
	 **/
	private void openConnection() {
		con = SQLCon.openConnection();
	}

	/**
	 * M�todo para cerrar la conexi�n, este m�todo llama al
	 * {@link SQLCon#closeConnection()} y {@code con.Close} para cerra la conexi�n
	 * aqu� y en el objecto {@code SQLCon}
	 **/
	private void closeConnection() {
		try {
			// Cerrar la conexi�n aqu� y en el SQLCon
			con.close();
			SQLCon.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo para revertir los cambios
	 * 
	 * @param e Se pasa por par�metros la excepti�n que recoge el catch,
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
	 * M�todo para insertar equipamiento a obras y viceversa, en la relaci�n
	 * "Equip-obra".
	 * 
	 * @param clase la clase equiobra que se pasa por par�metros una de las listas
	 *              solo debe tener 1 valor, ese ID ser� usado para diferenciar si
	 *              debemmos de a�adir multiples obras o equipamientos.
	 * @return true si se ha ejecutado correctamente la consulta
	 * 
	 **/
	@Override
	public boolean create(EquipObra clase) {

		this.openConnection();

		// Prepare Statement - Create
		try (PreparedStatement stat = con.prepareStatement(CREATE)) {

			// Inicio de la transacci�n
			con.setAutoCommit(false);

			// Relacionar varias obras a un equipamiento
			if (clase.getIdEquip().size() == 1) {
				for (Integer idObra : clase.getIdObra()) {
					// A�adir datos al Prepare Statement
					stat.setInt(1, idObra);
					stat.setInt(2, clase.getIdEquip().get(0));
					stat.addBatch();
				}

				// Relacionar varios equipamiento a una obra
			} else {
				for (Integer idEquip : clase.getIdEquip()) {

					stat.setInt(1, clase.getIdObra().get(0));
					stat.setInt(2, idEquip);
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
			return false; // Si hay alguna excepcion devolver� false
		} finally {
			this.closeConnection();
		}
	}

	/**
	 * M�todo para leer los datos de la relaci�n dependiendo de que ID se use para
	 * buscar, en el caso de que se busque por obras se recoger�n los IDs de los
	 * {@code Equipamientos} que se usan en esa obra, y viceversa si se busca por
	 * equip se guardar� los IDs de las obras en las que se usa ese equipamiento.
	 * 
	 * @param id el ID es un array para saber que ID se quiere buscar, el ID en el
	 *           �ndice 0: obra, 1: equipamiento.
	 * @return objecto equip-obra con los datos los IDs de equipamientos u obras
	 **/
	@Override
	public EquipObra search(String[] id) {

		this.openConnection();

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		EquipObra eo = null;
		List<Integer> aux = null, idAux = new ArrayList<>();
		boolean searchObra;

		// Prepare Statement - Search
		try (PreparedStatement obraStat = con.prepareStatement(SEARCHOBRA);
				PreparedStatement equipStat = con.prepareStatement(SEARCHEQUIP)) {

			// Si hay ID en el �ndice 0, buscar por obra
			if (!id[0].isBlank()) {
				obraStat.setString(1, id[0]);
				rs = obraStat.executeQuery();
				searchObra = true;
				// Si no, esta en el �ndice 1, buscar por equipamiento
			} else {
				equipStat.setString(1, id[1]);
				rs = equipStat.executeQuery();
				searchObra = false;
			}

			// Comprobar que RS a recuperado informacion del executeQuery
			while (rs.next()) {
				// Creamos una instancia del objecto
				if (eo == null) {
					eo = new EquipObra();
					aux = new ArrayList<>();
				}
				// A�adir el ID a la lista auxiliar
				aux.add(rs.getInt(1));

				// Si el RS est� en la �ltima fila
				if (rs.isLast()) {
					// En el caso de que se ha buscado por obras
					if (searchObra) {
						// A�adir ID a la lista
						idAux.add(Integer.parseInt(id[0]));
						// A�adir las listas al objecto
						eo.setIdObra(idAux);
						eo.setIdEquip(aux);
					} else {
						idAux.add(Integer.parseInt(id[1]));
						eo.setIdEquip(idAux);
						eo.setIdObra(aux);
					}
				}
			}

		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			this.closeConnection();
		}

		// Devolvemos el objecto, si RS NO ha devuelto nada, devolver� NULL
		return eo;
	}

	/**
	 * Este m�todo no se usar� por lo tanto devolver� null. Al implementar la
	 * interfaz obliga a la clase implementar todos sus m�todos, pero en este caso
	 * no necesitamos este m�todo.
	 * 
	 * @return null
	 * @deprecated
	 **/
	@Override
	public Map<String, EquipObra> readAll() {
		return null;
	}

	/**
	 * M�todo para actualizar los datos ya existentes, lo que se ha hecho es
	 * eliminar los datos previamente, y luego volver a introducirlo con los datos
	 * nuevos. Ya que las claves primarias de esta tabla es compuesta y es
	 * complicado actualizar los datos con un {@code UPDATE}
	 * 
	 * @param clase objecto promociona con los datos nuevos
	 * @return true si se ha ejecutado correctamente la consulta
	 */
	@Override
	public boolean update(EquipObra clase) {
		this.openConnection();

		try (PreparedStatement delObra = con.prepareStatement(DELETEOBRA);
				PreparedStatement delEquip = con.prepareStatement(DELETEEQUIP);
				PreparedStatement stat = con.prepareStatement(CREATE)) {
			// Inicio de la transacci�n
			con.setAutoCommit(false);

			// Eliminar todos los datos relacionados con una obra
			if (clase.getIdObra().size() == 1) {
				delObra.setInt(1, clase.getIdObra().get(0));
				delObra.executeUpdate();
			}
			// Eliminar todos los datos relacionados con un patrocinador
			else {
				delEquip.setInt(1, clase.getIdEquip().get(0));
				delEquip.executeUpdate();
			}

			// En el caso de que queramos insertar varias obras en un patrocinador
			if (clase.getIdEquip().size() == 1) {
				for (Integer idObra : clase.getIdObra()) {
					// A�adir datos al Prepare Statement
					stat.setInt(1, idObra);
					stat.setInt(2, clase.getIdEquip().get(0));
					stat.addBatch();
				}

				// Insertar varios patrocinadores en una obra
			} else {
				for (Integer idPatro : clase.getIdEquip()) {

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
	 * M�todo para quitar el uso del equipamiento de una obra.
	 * 
	 * @param id Los identificadores para eliminar un equip-obra, el primer �ndice
	 *           0: idObra y el 1: idEquip
	 * @return true si se ejecuta correctamente la consulta
	 **/
	@Override
	public boolean remove(String[] id) {

		this.openConnection();
		
		// Prepare Statement - Delete
		try (PreparedStatement stat = con.prepareStatement(DELETEUSA)) {

			// A�adir datos al Prepare Statement
			stat.setString(1, id[0]);
			stat.setString(2, id[1]);

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}finally {
			this.closeConnection();
		}
	}

}
