package modelo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controlador.interfaz.BDgeneric;
import controlador.utils.SQLCon;
import modelo.clases.ObraAudiovisual;
import modelo.clases.Pelicula;
import modelo.clases.Serie;

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
	private final String UPDATE = "UPDATE obraaudiovisual SET nombre = ?, duracion = ?, FechaEstreno = ?, presupuesto = ?, tipo = ? WHERE idObra = ?";
	private final String DELETE = "DELETE FROM obraaudiovisual WHERE idObra = ?";

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
	 * añadir si es taquillera o no, en cambio si es una serie debemos de insertar,
	 * la temporada, el número del capítulo junto con su nombre, esto se hace
	 * iterando por una lista de 2-dimensiones, la lista de fuera es la que guarda
	 * las temporadas, y la de dentro el capitulo con el nombre.<br>
	 * En cada iteración se añade la inserción a un batch, al acabar se ejecuta este
	 * batch y llamamos al {@link java.sql.Connection#commit()} para aplicar las
	 * inserciones. </blockquote>
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
				PreparedStatement peli = con.prepareStatement(INSERTPELI);
				PreparedStatement serie = con.prepareStatement(INSERTSERIE)) {

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
					Serie aux = ((Serie) clase);

					// Iterar por el array exterior
					for (int i = 0; i < aux.getNombreCap().size(); i++) {
						// Iterar por el array interior
						for (int j = 0; j < aux.getNombreCap().get(i).size(); j++) {

							// Añadir los datos de la serie al STAT
							serie.setInt(1, rs.getInt(1));
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

	private ObraAudiovisual addToList(ResultSet rs, ObraAudiovisual obra) {

		// Lista 2D
		List<List<String>> nombreCap;

		return obra;
	}

	@Override
	public Map<String, ObraAudiovisual> readAll() {
		// RS y la clase para recoger los datos, además un map para guardar
		Map<String, ObraAudiovisual> allUsers = new HashMap<>();
		ResultSet rs = null;
		ObraAudiovisual oa = null;

		try {
			// Prepare Statement - ReadAll
			stat = con.prepareStatement(READALL);

			// Ejecutar consulta y guardarlo en el result set
			rs = stat.executeQuery();

			// Mientras que RS sigua teniendo filas con información
			while (rs.next()) {
				// Crearmos una instancia del objecto
				oa = new ObraAudiovisual();
				oa.setIdObra(rs.getInt(1));
				oa.setNombre(rs.getString(2));
				oa.setDuracion(rs.getInt(3));
				oa.setFechaEstreno(rs.getDate(4).toLocalDate());
				oa.setPresupuesto(rs.getInt(5));
				oa.setTipo(rs.getString(6));
				// Añadimos la clave y el objecto al map
				allUsers.put(Integer.toString(oa.getIdObra()), oa);
			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		// Devolverá un map con los datos, o un map vacío
		return allUsers;
	}

	@Override
	public boolean update(ObraAudiovisual clase) {
		try {
			// Prepare Statement - Update
			stat = con.prepareStatement(UPDATE);

			// Añadir datos al Prepare Statement
			stat.setInt(1, clase.getIdObra());
			stat.setString(2, clase.getNombre());
			stat.setInt(3, clase.getDuracion());
			stat.setDate(4, Date.valueOf(clase.getFechaEstreno()));
			stat.setInt(5, clase.getPresupuesto());
			stat.setString(6, clase.getTipo());

			// Ejecutar consulta y devolver true o false
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false; // Si hay alguna excepcion devolverá false
		}
	}

	@Override
	public boolean remove(String id) {

		try {

			// Prepare Statement - Delete
			stat = con.prepareStatement(DELETE);

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
