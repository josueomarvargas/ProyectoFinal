package modelo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controlador.interfaz.BDgeneric;
import controlador.utils.SQLCon;
import modelo.clases.ObraAudiovisual;
import modelo.clases.Pelicula;
import modelo.clases.Serie;

/**
 * Esta clase implementa la interfaz genérica
 * {@link controlador.interfaz.BDgeneric BDgeneric}, el CRUD de este DAO
 * gestiona las tablas {@code ObraAudiovisual, Serie y Pelicula}.
 * <p>
 * Varios métodos de este DAO són transacciónes SQL, ya que es necesario
 * escribir/actualizar información en dos tablas para la integridad de los
 * datos.
 * <p>
 * En los {@code try-catch} se usan recursos, estos nos permite declarar
 * objectos que al finalizar el bloque se cierren automáticamente sin uso de un
 * finally, se tienen que declarar antes o en el try. Esto nos será útil para
 * declarar los {@code PrepareStatements} como recursos del try y no tener que
 * preocuparnos por no haberlo cerrado. También hay try sin catch esto se puede
 * hacer, pero es necesario poner un finally o tener un recurso en el try.
 * 
 * @author Henrique Yeguo
 **/
public class ObraDAO implements BDgeneric<ObraAudiovisual> {

	// MySQL Consultas
	// Insert Obra
	private final String CREATE = "INSERT INTO obraaudiovisual(nombre, duracion, FechaEstreno, presupuesto, tipo, imgPath) VALUES(?, ?, ?, ?, ?, ?)";

	// Insert Peli
	private final String INSERTPELI = "INSERT INTO pelicula VALUE(?, ?)";

	// Insert Serie
	private final String INSERTSERIE = "INSERT INTO serie VALUE(?, ?, ?, ?)";

	// Buscar una única obra
	private final String SEARCH = "CALL searchObra(?)";

	// Leer todas las obras
	private final String READALL = "CALL showObras()";

	// Actualizar datos
	private final String UPDATE = "UPDATE obraaudiovisual SET nombre = ?, duracion = ?, FechaEstreno = ?, presupuesto = ? WHERE idObra = ?";
	private final String UPDATEPELI = "UPDATE pelicula SET esTaquillera = ? WHERE idObra = ?";

	// Eliminar obra
	private final String DELETE = "DELETE FROM obraaudiovisual WHERE idObra = ?";

	// Eliminar en la tabla serie
	private final String DELETESERIE = "DELETE FROM serie WHERE idObra = ?";

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
	 * Método para insertar {@code ObrasAudiovisuales}, e información adicional
	 * dependiendo de si són {@code Peliculas} o {@code Series}. <br>
	 * <b>Cómo funciona el método:</b> <blockquote> Primero de todo insertaremos en
	 * el {@code PreparedStatement} los datos necesarios para insertar una obra,
	 * ejecutaremos la consulta y recogemos el ID que se ha generado. <br>
	 * Comprobamos que el RS nos ha devuelto la clave, y comprobamos si la obra es
	 * una película o serie. En el caso de que sea una película solo tenemos que
	 * añadir si es taquillera o no, en cambio si es una serie llamamos al método
	 * {@link #insertSerie} y luego llamamos al {@link java.sql.Connection#commit()}
	 * para aplicar las inserciones. </blockquote>
	 * 
	 * 
	 * @param clase el objecto con la información para insertar en su tabla
	 * @return true si se ha ejecutado correctamente la inserción
	 **/
	@Override
	public boolean create(ObraAudiovisual clase) {

		this.openConnection();
		ResultSet rs;

		// Prepare Statement - Create
		try (PreparedStatement stat = con.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
				PreparedStatement peli = con.prepareStatement(INSERTPELI)) {

			// Inicio de la transacción
			con.setAutoCommit(false);

			// Añadir datos al Prepare Statement
			stat.setString(1, clase.getNombre());
			stat.setInt(2, clase.getDuracion());
			stat.setDate(3, Date.valueOf(clase.getFechaEstreno()));
			stat.setInt(4, clase.getPresupuesto());
			stat.setString(5, clase.getTipo());
			stat.setString(6, clase.getImgPath());

			// Ejecutar consulta
			stat.executeUpdate();

			// Recoger el ID generado
			rs = stat.getGeneratedKeys();

			if (rs.next()) {
				if (clase instanceof Pelicula) {
					peli.setInt(1, rs.getInt(1));
					peli.setInt(2, ((Pelicula) clase).isEsTaquillera() == true ? 1 : 0);
					peli.executeUpdate();
				} else {
					insertSerie(rs.getInt(1), clase);
				}
			}

			// Guardar los cambios de los inserts
			con.commit();

			// Establecer autocommit al valor por defecto
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
	 * Método para insertar la información de una serie, se ha creado este método
	 * para reducir el código repetitivo.
	 * 
	 * Para inserter una serie debemos de añadir, la temporada, el número del
	 * capítulo junto con su nombre, esto se hace iterando por una lista de
	 * 2-dimensiones, la lista de fuera es la que guarda las temporadas, y la de
	 * dentro el capitulo con el nombre. En cada iteración se añade la inserción a
	 * un batch, al acabar se ejecuta este batch.
	 * 
	 * 
	 * @param id    Id de la obra
	 * @param clase el objecto clase con la información que se añadirá
	 **/
	private void insertSerie(Integer id, ObraAudiovisual clase) throws SQLException {
		Serie aux = ((Serie) clase);

		try (PreparedStatement serie = con.prepareStatement(INSERTSERIE)) {

			// Iterar por el array exterior
			for (int i = 0; i < aux.getNombreCap().size(); i++) {
				// Iterar por el array interior
				for (int j = 0; j < aux.getNombreCap().get(i).size(); j++) {

					// Añadir los datos de la serie al STAT
					serie.setInt(1, id);
					serie.setInt(2, i);
					serie.setInt(3, j);
					// Recoger el nombre del capitulo
					serie.setString(4, aux.getNombreCap().get(i).get(j));
					serie.addBatch();
				}
			}
			serie.executeBatch();

		}
	}

	/**
	 * Método para buscar una obra audiovisual, también se recogerá la información
	 * dependiendo de si es una película o serie. <br>
	 * <b>Cómo funciona el método:</b><blockquote> Buscamos la obra por su ID
	 * llamando al procedimiento guardado en el servidor, si la obra es una película
	 * guardamos si es taquillera, en la base de datos está guardado como un 1 y 0
	 * (True/False), en el caso de que sea una serie debemos de guardar las
	 * temporadas, capítulos y los nombres de estos capítulos, esto se hace en el
	 * método {@link #addToList}, y al finalizar devolvemos la obra.</blockquote>
	 * 
	 * @param id identificador para buscar la obra
	 * @return objecto con la información de una obra audiovisual, ya sea
	 **/
	@Override
	public ObraAudiovisual search(String id) {

		this.openConnection();

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		ObraAudiovisual oa = null;

		// Callable Statement - Search
		try (CallableStatement stat = con.prepareCall(SEARCH, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY)) {

			// Añadir datos al Prepare Statement
			stat.setString(1, id);

			// Ejecutar consulta y guardarlo en el Result Set
			rs = stat.executeQuery();

			// Comprobar que RS a recuperado informacion del executeQuery
			if (rs.next()) {
				// Comprobamos el tipo de obra
				if (rs.getString("tipo").equals("pelicula")) {
					oa = new Pelicula();
					((Pelicula) oa).setEsTaquillera(rs.getInt("esTaquillera") == 1 ? true : false);

				} else {
					oa = new Serie();
					do {
						oa = addToList(rs, oa);
					} while (rs.next());

				}

				oa.setIdObra(rs.getInt(1));
				oa.setNombre(rs.getString(2));
				oa.setDuracion(rs.getInt(3));
				oa.setFechaEstreno(rs.getDate(4).toLocalDate());
				oa.setPresupuesto(rs.getInt(5));
				oa.setTipo(rs.getString(6));
				oa.setImgPath(rs.getString(7));
			}

		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			this.closeConnection();
		}

		// Devolvemos el objecto, si RS NO ha devuelto nada, devolverá NULL
		return oa;
	}

	/**
	 * Este método es para añadir los datos de una serie, estos datos són; la
	 * temporada, el número del capítulo y el nombre. <br>
	 * <b>Cómo funciona el método:</b> <blockquote> Primero de todo recogemos la
	 * lista que se encuentra en la obra, el número de la temporada del
	 * {@code ResultSet}, y comprobamos si el capítulo tiene nombre.<br>
	 * Posteriormente comprobamos el número de temporada que nos devuelve la
	 * consulta con el tamaño de la lista, si no es el mismo significa que es
	 * diferente temporada por lo que añadimos a la lista una nueva sublista.
	 * Después añadimos a la sublista el nombre del capítulo. <br>
	 * </blockquote> <note>Nota: La lista es una lista de 2-dimensiones, la exterior
	 * guarda las temporadas, y la interior guardará los nombres de los
	 * capítulos.<note>
	 * 
	 * @param rs   ResultSet con los datos a insertar
	 * @param obra la obra a donde se van a insertar los datos
	 * 
	 **/
	private ObraAudiovisual addToList(ResultSet rs, ObraAudiovisual obra) throws SQLException {

		// Recoger la lista de 2Dimensiones
		List<List<String>> nombreCap = ((Serie) obra).getNombreCap();
		int numTemporada = rs.getInt("NumTemporada");
		String nomCap = "";

		// Comprobar si el capitulo tiene nombre
		if (rs.getString("nombreCap") == null) {
			nomCap = "No asignado";
		} else {
			nomCap = rs.getString("nombreCap");
		}

		// EJ: el RS nos devuelve la temporada 3 y el tamaño de la lista es de 2 crear
		// una nueva temporada/lista
		if (numTemporada != nombreCap.size()) {
			// Crear una nueva lista
			nombreCap.add(new ArrayList<>());
		}

		// Añadir a la temporada, el capítulo y su nombre
		// Ej: temporada 1 el índice en la lista será la 0
		nombreCap.get(numTemporada - 1).add(nomCap);

		// Guardar la lista a la obra
		((Serie) obra).setNombreCap(nombreCap);
		return obra;
	}

	/**
	 * Método para leer todos los datos de las obras, si es una película se guarda
	 * si es taquillera, y en el caso de las series se guarda en una lista de
	 * 2-dimensiones, la temporada, el capítulo y el nombre de este. <br>
	 * <b>Cómo funciona el método:</b> <blockquote> Al recoger la información del
	 * procedimiento almacenado, comprobamos si el map ya tiene la clave de la obra.
	 * En el caso de que ya la tiene y es una serie, recogemos la obra del map y
	 * añadimos más capítulos a su lista usando el método {@link #addToList}. En el
	 * caso de que no lo contenga instanciamos la clase dependiendo de que tipo de
	 * obra sea, si es una serie añadimos los datos de la serie en su lista llamando
	 * al método mencionado anteriormente, en el caso de que sea una película solo
	 * se añade si es taquillera.<br>
	 * Guardamos los datos de la obra y lo guardamos en el map junto con su ID.
	 * 
	 * </blockquote>
	 * 
	 * 
	 * 
	 * @return Map<Integer, ObraAudiovisual>, la clave será el ID de la obra y el
	 *         valor el objecto completo con su información.
	 **/
	@Override
	public Map<Integer, ObraAudiovisual> readAll() {

		this.openConnection();

		// RS y la clase para recoger los datos, además un map para guardar
		Map<Integer, ObraAudiovisual> obras = new HashMap<>();
		ResultSet rs = null;
		ObraAudiovisual oa = null;

		// Prepare Statement - ReadAll
		try (CallableStatement stat = con.prepareCall(READALL)) {

			// Ejecutar consulta y guardarlo en el result set
			rs = stat.executeQuery();

			// Mientras que RS sigua teniendo filas con información
			while (rs.next()) {

				if (obras.containsKey(rs.getInt(1)) && rs.getString("tipo").equalsIgnoreCase("serie")) {
					oa = obras.get(rs.getInt(1));
					oa = addToList(rs, oa);

				} else {

					// Crearmos una instancia del objecto dependiendo del tipo
					if (rs.getString("tipo").equalsIgnoreCase("serie")) {
						oa = new Serie();
						oa = addToList(rs, oa);

					} else {
						oa = new Pelicula();
						((Pelicula) oa).setEsTaquillera(rs.getInt("esTaquillera") == 1 ? true : false);
					}
					// Añadimos los datos de la obra
					oa.setIdObra(rs.getInt(1));
					oa.setNombre(rs.getString(2));
					oa.setDuracion(rs.getInt(3));
					oa.setFechaEstreno(rs.getDate(4).toLocalDate());
					oa.setPresupuesto(rs.getInt(5));
					oa.setTipo(rs.getString(6));
				}

				// Añadimos la clave y el objecto al map
				obras.put(oa.getIdObra(), oa);
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			this.closeConnection();
		}

		// Devolverá un map con los datos, o un map vacío
		return obras;
	}

	@Override
	public boolean update(ObraAudiovisual clase) {

		this.openConnection();

		// Prepare Statement - Update
		try (PreparedStatement stat = con.prepareStatement(UPDATE);
				PreparedStatement updatePeli = con.prepareStatement(UPDATEPELI);
				PreparedStatement delSerie = con.prepareStatement(DELETESERIE);) {

			// Inicio de la trasnacción
			con.setAutoCommit(false);

			// Añadir datos al Prepare Statement
			stat.setString(1, clase.getNombre());
			stat.setInt(2, clase.getDuracion());
			stat.setDate(3, Date.valueOf(clase.getFechaEstreno()));
			stat.setInt(4, clase.getPresupuesto());
			stat.setInt(5, clase.getIdObra());

			// Ejecutar consulta
			stat.executeUpdate();

			if (clase instanceof Pelicula) {
				// Actualizamos si es taquillera
				updatePeli.setInt(1, ((Pelicula) clase).isEsTaquillera() == true ? 1 : 0);
				updatePeli.executeUpdate();
			} else {
				// Eliminamos las series
				delSerie.setInt(1, clase.getIdObra());
				delSerie.executeUpdate();
			}

			// Insertamos de nuevo las series
			insertSerie(clase.getIdObra(), clase);

			// Aplicar los cambios
			con.commit();

			// Cambiar autocommit por defecto
			con.setAutoCommit(true);

			// Ejecutar consulta y devolver true o false
			return true;

		} catch (SQLException e) {
			System.err.println(e);

			return false; // Si hay alguna excepcion devolverá false
		} finally {
			this.closeConnection();
		}
	}

	/**
	 * Método para eliminar todos los datos relacionados con la obra
	 * 
	 * @param id el identificador de la obra que se quiere eliminar
	 * @return true si se a ejecutado correctamente la consulta
	 **/
	@Override
	public boolean remove(String id) {

		// Prepare Statement - Delete
		try (PreparedStatement stat = con.prepareStatement(DELETE)) {

			// Añadir datos al Prepare Statement
			stat.setString(1, id);

			// Ejecutar consulta
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false; // Si hay alguna excepcion devolverá false
		}
	}

}
