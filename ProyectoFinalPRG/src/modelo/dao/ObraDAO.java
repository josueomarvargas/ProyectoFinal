package modelo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
<<<<<<< HEAD
=======
import java.util.ArrayList;
>>>>>>> be8214910679c26ea801d855a873b706a6d01963
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controlador.interfaz.BDgeneric;
<<<<<<< HEAD
import controlador.utils.SQLCon;
=======
import controlador.utils.dao.SQLCon;
>>>>>>> be8214910679c26ea801d855a873b706a6d01963
import modelo.clases.ObraAudiovisual;
import modelo.clases.Pelicula;
import modelo.clases.Serie;

<<<<<<< HEAD
=======
/**
 * Esta clase implementa la interfaz gen�rica
 * {@link controlador.interfaz.BDgeneric BDgeneric}, el CRUD de este DAO
 * gestiona las tablas {@code ObraAudiovisual, Serie y Pelicula}.
 * <p>
 * Varios m�todos de este DAO s�n transacci�nes SQL, ya que es necesario
 * escribir/actualizar informaci�n en dos tablas para la integridad de los
 * datos.
 * <p>
 * En los {@code try-catch} se usan recursos, estos nos permite declarar
 * objectos que al finalizar el bloque se cierren autom�ticamente sin uso de un
 * finally, se tienen que declarar antes o en el try. Esto nos ser� �til para
 * declarar los {@code PrepareStatements} como recursos del try y no tener que
 * preocuparnos por no haberlo cerrado. Tambi�n hay try sin catch esto se puede
 * hacer, pero es necesario poner un finally o tener un recurso en el try.
 * 
 * @author Henrique Yeguo
 **/
>>>>>>> be8214910679c26ea801d855a873b706a6d01963
public class ObraDAO implements BDgeneric<ObraAudiovisual> {

	// MySQL Consultas
	// Insert Obra
	private final String CREATE = "INSERT INTO obraaudiovisual(nombre, duracion, FechaEstreno, presupuesto, tipo, imgPath) VALUES(?, ?, ?, ?, ?, ?)";

	// Insert Peli
	private final String INSERTPELI = "INSERT INTO pelicula VALUE(?, ?)";

	// Insert Serie
	private final String INSERTSERIE = "INSERT INTO serie VALUE(?, ?, ?, ?)";

	// Buscar una �nica obra
	private final String SEARCH = "CALL searchObra(?)";

	// Leer todas las obras
	private final String READALL = "CALL showObras()";
<<<<<<< HEAD
	private final String UPDATE = "UPDATE obraaudiovisual SET nombre = ?, duracion = ?, FechaEstreno = ?, presupuesto = ?, tipo = ? WHERE idObra = ?";
	private final String DELETE = "DELETE FROM obraaudiovisual WHERE idObra = ?";

=======

	// Actualizar datos
	private final String UPDATE = "UPDATE obraaudiovisual SET nombre = ?, duracion = ?, FechaEstreno = ?, presupuesto = ? WHERE idObra = ?";
	private final String UPDATEPELI = "UPDATE pelicula SET esTaquillera = ? WHERE idObra = ?";

	// Eliminar obra
	private final String DELETE = "DELETE FROM obraaudiovisual WHERE idObra = ?";

	// Eliminar en la tabla serie
	private final String DELETESERIE = "DELETE FROM serie WHERE idObra = ?";

>>>>>>> be8214910679c26ea801d855a873b706a6d01963
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
<<<<<<< HEAD
=======
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
>>>>>>> be8214910679c26ea801d855a873b706a6d01963
	 * M�todo para insertar {@code ObrasAudiovisuales}, e informaci�n adicional
	 * dependiendo de si s�n {@code Peliculas} o {@code Series}. <br>
	 * <b>C�mo funciona el m�todo:</b> <blockquote> Primero de todo insertaremos en
	 * el {@code PreparedStatement} los datos necesarios para insertar una obra,
	 * ejecutaremos la consulta y recogemos el ID que se ha generado. <br>
	 * Comprobamos que el RS nos ha devuelto la clave, y comprobamos si la obra es
	 * una pel�cula o serie. En el caso de que sea una pel�cula solo tenemos que
<<<<<<< HEAD
	 * a�adir si es taquillera o no, en cambio si es una serie debemos de insertar,
	 * la temporada, el n�mero del cap�tulo junto con su nombre, esto se hace
	 * iterando por una lista de 2-dimensiones, la lista de fuera es la que guarda
	 * las temporadas, y la de dentro el capitulo con el nombre.<br>
	 * En cada iteraci�n se a�ade la inserci�n a un batch, al acabar se ejecuta este
	 * batch y llamamos al {@link java.sql.Connection#commit()} para aplicar las
	 * inserciones. </blockquote>
=======
	 * a�adir si es taquillera o no, en cambio si es una serie llamamos al m�todo
	 * {@link #insertSerie} y luego llamamos al {@link java.sql.Connection#commit()}
	 * para aplicar las inserciones. </blockquote>
>>>>>>> be8214910679c26ea801d855a873b706a6d01963
	 * 
	 * 
	 * @param clase el objecto con la informaci�n para insertar en su tabla
	 * @return true si se ha ejecutado correctamente la inserci�n
	 **/
	@Override
	public boolean create(ObraAudiovisual clase) {

		this.openConnection();
		ResultSet rs;

		// Prepare Statement - Create
		try (PreparedStatement stat = con.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
<<<<<<< HEAD
				PreparedStatement peli = con.prepareStatement(INSERTPELI);
				PreparedStatement serie = con.prepareStatement(INSERTSERIE)) {

=======
				PreparedStatement peli = con.prepareStatement(INSERTPELI)) {

			// Inicio de la transacci�n
>>>>>>> be8214910679c26ea801d855a873b706a6d01963
			con.setAutoCommit(false);

			// A�adir datos al Prepare Statement
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
<<<<<<< HEAD
					Serie aux = ((Serie) clase);

					// Iterar por el array exterior
					for (int i = 0; i < aux.getNombreCap().size(); i++) {
						// Iterar por el array interior
						for (int j = 0; j < aux.getNombreCap().get(i).size(); j++) {

							// A�adir los datos de la serie al STAT
							serie.setInt(1, rs.getInt(1));
							serie.setInt(2, i);
							serie.setInt(3, j);
							// Recoger el nombre del capitulo
							serie.setString(4, aux.getNombreCap().get(i).get(j));
							serie.addBatch();
						}
					}
					serie.executeBatch();

=======
					insertSerie(rs.getInt(1), clase);
>>>>>>> be8214910679c26ea801d855a873b706a6d01963
				}
			}

			// Guardar los cambios de los inserts
			con.commit();

			// Establecer autocommit al valor por defecto
			con.setAutoCommit(true);

			return true;

		} catch (SQLException e) {
<<<<<<< HEAD
			System.err.println(e);

=======
			rollback(e);
>>>>>>> be8214910679c26ea801d855a873b706a6d01963
			return false; // Si hay alguna excepcion devolver� false
		} finally {
			this.closeConnection();
		}
	}

<<<<<<< HEAD
	@Override
	public ObraAudiovisual search(String id) {
=======
	/**
	 * M�todo para insertar la informaci�n de una serie, se ha creado este m�todo
	 * para reducir el c�digo repetitivo.
	 * 
	 * Para inserter una serie debemos de a�adir, la temporada, el n�mero del
	 * cap�tulo junto con su nombre, esto se hace iterando por una lista de
	 * 2-dimensiones, por el �ndice de las listas sabemos el n�mero de la
	 * temporadas, y el n�mero del capitulo con el nombre, java empieza por cero las
	 * listas por lo que hay que sumar uno para mostrar los n�meros correctos. En
	 * cada iteraci�n se a�ade la inserci�n a un batch, al acabar se ejecuta este
	 * batch. <br>
	 * Ej: En el �ndice 0 de la lista de fuera ser� la temporada 1, y en la lista
	 * interior indice 0 ser� el cap�tulo 1 con su nombre.
	 * 
	 * 
	 * @param id    Id de la obra
	 * @param clase el objecto clase con la informaci�n que se a�adir�
	 **/
	private void insertSerie(Integer id, ObraAudiovisual clase) throws SQLException {
		List<List<String>> aux = ((Serie) clase).getNombreCap();

		try (PreparedStatement serieStat = con.prepareStatement(INSERTSERIE)) {

			// Iterar por el array exterior
			for (int i = 0; i < aux.size(); i++) {
				// Iterar por el array interior
				for (int j = 0; j < aux.get(i).size(); j++) {

					// A�adir los datos de la serie al STAT
					serieStat.setInt(1, id);
					serieStat.setInt(2, i + 1);
					serieStat.setInt(3, j + 1);
					// Recoger el nombre del capitulo
					serieStat.setString(4, aux.get(i).get(j));
					serieStat.addBatch();
				}
			}
			serieStat.executeBatch();

		}
	}

	/**
	 * M�todo para buscar una obra audiovisual, tambi�n se recoger� la informaci�n
	 * dependiendo de si es una pel�cula o serie. <br>
	 * <b>C�mo funciona el m�todo:</b><blockquote> Buscamos la obra por su ID
	 * llamando al procedimiento guardado en el servidor, si la obra es una pel�cula
	 * guardamos si es taquillera, en la base de datos est� guardado como un 1 y 0
	 * (True/False), en el caso de que sea una serie debemos de guardar las
	 * temporadas, cap�tulos y los nombres de estos cap�tulos, esto se hace en el
	 * m�todo {@link #addToList}, y al finalizar devolvemos la obra.</blockquote>
	 * 
	 * @param id identificador para buscar la obra
	 * @return objecto con la informaci�n de una obra audiovisual, ya sea
	 **/
	@Override
	public ObraAudiovisual search(String[] id) {
>>>>>>> be8214910679c26ea801d855a873b706a6d01963

		this.openConnection();

		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		ObraAudiovisual oa = null;

		// Callable Statement - Search
		try (CallableStatement stat = con.prepareCall(SEARCH, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY)) {

			// A�adir datos al Prepare Statement
<<<<<<< HEAD
			stat.setString(1, id);
=======
			stat.setString(1, id[0]);
>>>>>>> be8214910679c26ea801d855a873b706a6d01963

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
<<<<<<< HEAD
				}

=======

				}

				rs.first();
>>>>>>> be8214910679c26ea801d855a873b706a6d01963
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

		// Devolvemos el objecto, si RS NO ha devuelto nada, devolver� NULL
		return oa;
	}

<<<<<<< HEAD
	private ObraAudiovisual addToList(ResultSet rs, ObraAudiovisual obra) {

		// Lista 2D
		List<List<String>> nombreCap;

		return obra;
	}

	@Override
	public Map<String, ObraAudiovisual> readAll() {
		// RS y la clase para recoger los datos, adem�s un map para guardar
		Map<String, ObraAudiovisual> allUsers = new HashMap<>();
		ResultSet rs = null;
		ObraAudiovisual oa = null;

		try {
			// Prepare Statement - ReadAll
			stat = con.prepareStatement(READALL);
=======
	/**
	 * Este m�todo es para a�adir los datos de una serie, estos datos s�n; la
	 * temporada, el n�mero del cap�tulo y el nombre. <br>
	 * <b>C�mo funciona el m�todo:</b> <blockquote> Primero de todo recogemos la
	 * lista que se encuentra en la obra, el n�mero de la temporada del
	 * {@code ResultSet}, y comprobamos si el cap�tulo tiene nombre.<br>
	 * Posteriormente comprobamos el n�mero de temporada que nos devuelve la
	 * consulta con el tama�o de la lista, si no es el mismo significa que es
	 * diferente temporada por lo que a�adimos a la lista una nueva sublista.
	 * Despu�s a�adimos a la sublista el nombre del cap�tulo. <br>
	 * </blockquote> <note>Nota: La lista es una lista de 2-dimensiones, la exterior
	 * guarda las temporadas, y la interior guardar� los nombres de los cap�tulos,
	 * por el �ndice de estas listas sabemos el n�mero de temporada y el n�mero de
	 * cap�tulo.<note>
	 * 
	 * @param rs   ResultSet con los datos a insertar
	 * @param obra la obra a donde se van a insertar los datos
	 * @return devuelve la obra con los datos insertados
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

		// EJ: el RS nos devuelve la temporada 3 y el tama�o de la lista es de 2 crear
		// una nueva temporada/lista
		if (numTemporada != nombreCap.size()) {
			// Crear una nueva lista
			nombreCap.add(new ArrayList<>());
		}

		// A�adir a la temporada, el cap�tulo y su nombre
		// Ej: temporada 1 el �ndice en la lista ser� la 0
		nombreCap.get(numTemporada - 1).add(nomCap);

		// Guardar la lista a la obra
		((Serie) obra).setNombreCap(nombreCap);
		return obra;
	}

	/**
	 * M�todo para leer todos los datos de las obras, si es una pel�cula se guarda
	 * si es taquillera, y en el caso de las series se guarda en una lista de
	 * 2-dimensiones, la temporada, el cap�tulo y el nombre de este. <br>
	 * <b>C�mo funciona el m�todo:</b> <blockquote> Al recoger la informaci�n del
	 * procedimiento almacenado, comprobamos si el map ya tiene la clave de la obra.
	 * En el caso de que ya la tiene y es una serie, recogemos la obra del map y
	 * a�adimos m�s cap�tulos a su lista usando el m�todo {@link #addToList}. En el
	 * caso de que no lo contenga instanciamos la clase dependiendo de que tipo de
	 * obra sea, si es una serie a�adimos los datos de la serie en su lista llamando
	 * al m�todo mencionado anteriormente, en el caso de que sea una pel�cula solo
	 * se a�ade si es taquillera.<br>
	 * Y al finalzar guardamos los datos de la obra y lo guardamos en el map junto
	 * con su ID. </blockquote>
	 * 
	 * 
	 * 
	 * @return Map<Integer, ObraAudiovisual>, la clave ser� el ID de la obra y el
	 *         valor el objecto completo con su informaci�n.
	 **/
	@Override
	public Map<Integer, ObraAudiovisual> readAll() {

		this.openConnection();

		// RS y la clase para recoger los datos, adem�s un map para guardar
		Map<Integer, ObraAudiovisual> obras = new HashMap<>();
		ResultSet rs = null;
		ObraAudiovisual oa = null;

		// Prepare Statement - ReadAll
		try (CallableStatement stat = con.prepareCall(READALL)) {
>>>>>>> be8214910679c26ea801d855a873b706a6d01963

			// Ejecutar consulta y guardarlo en el result set
			rs = stat.executeQuery();

			// Mientras que RS sigua teniendo filas con informaci�n
			while (rs.next()) {
<<<<<<< HEAD
				// Crearmos una instancia del objecto
				oa = new ObraAudiovisual();
				oa.setIdObra(rs.getInt(1));
				oa.setNombre(rs.getString(2));
				oa.setDuracion(rs.getInt(3));
				oa.setFechaEstreno(rs.getDate(4).toLocalDate());
				oa.setPresupuesto(rs.getInt(5));
				oa.setTipo(rs.getString(6));
				// A�adimos la clave y el objecto al map
				allUsers.put(Integer.toString(oa.getIdObra()), oa);
=======

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
					// A�adimos los datos de la obra
					oa.setIdObra(rs.getInt(1));
					oa.setNombre(rs.getString(2));
					oa.setDuracion(rs.getInt(3));
					oa.setFechaEstreno(rs.getDate(4).toLocalDate());
					oa.setPresupuesto(rs.getInt(5));
					oa.setTipo(rs.getString(6));
				}

				// A�adimos la clave y el objecto al map
				obras.put(oa.getIdObra(), oa);
>>>>>>> be8214910679c26ea801d855a873b706a6d01963
			}

		} catch (SQLException e) {
			System.out.println(e);
<<<<<<< HEAD
		}

		// Devolver� un map con los datos, o un map vac�o
		return allUsers;
	}

	@Override
	public boolean update(ObraAudiovisual clase) {
		try {
			// Prepare Statement - Update
			stat = con.prepareStatement(UPDATE);

			// A�adir datos al Prepare Statement
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

			return false; // Si hay alguna excepcion devolver� false
		}
	}

	@Override
	public boolean remove(String id) {

		try {

			// Prepare Statement - Delete
			stat = con.prepareStatement(DELETE);

			// A�adir datos al Prepare Statement
			stat.setString(1, id);
=======
		} finally {
			this.closeConnection();
		}

		// Devolver� un map con los datos, o un map vac�o
		return obras;
	}

	/**
	 * M�todo para actualizar los datos de una obra. <br>
	 * <b>C�mo funciona este m�todo:</b><blockquote> Primero de todo actualizamos
	 * los datos de la obra, luego dependiendo de si es una pel�cula tambi�n
	 * actualizaremos su informaci�n, en el caso de que sea una serie, eliminaremos
	 * los datos de esa esa serie y luego insertaremos los datos nuevos, al
	 * finalizar aplicamos los cambios llamando al
	 * {@link java.sql.Connection#commit}.</blockquote>
	 * 
	 * 
	 * 
	 * @param clase la obra con la nueva informaci�n que se quiere actualizar
	 * @return true si se ha ejecutado correctamente la consulta
	 **/
	@Override
	public boolean update(ObraAudiovisual clase) {

		this.openConnection();

		// Prepare Statement - Update
		try (PreparedStatement stat = con.prepareStatement(UPDATE);
				PreparedStatement updatePeli = con.prepareStatement(UPDATEPELI);
				PreparedStatement delSerie = con.prepareStatement(DELETESERIE);) {

			// Inicio de la trasnacci�n
			con.setAutoCommit(false);

			// A�adir datos al Prepare Statement
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
			rollback(e);
			return false; // Si hay alguna excepcion devolver� false
		} finally {
			this.closeConnection();
		}
	}

	/**
	 * M�todo para eliminar todos los datos relacionados con la obra
	 * 
	 * @param id el identificador de la obra que se quiere eliminar
	 * @return true si se a ejecutado correctamente la consulta
	 **/
	@Override
	public boolean remove(String[] id) {

		this.openConnection();

		// Prepare Statement - Delete
		try (PreparedStatement stat = con.prepareStatement(DELETE)) {

			// A�adir datos al Prepare Statement
			stat.setString(1, id[0]);
>>>>>>> be8214910679c26ea801d855a873b706a6d01963

			// Ejecutar consulta
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
<<<<<<< HEAD
			System.err.println(e);

			return false; // Si hay alguna excepcion devolver� false
=======
			return false; // Si hay alguna excepcion devolver� false
		} finally {
			this.closeConnection();
>>>>>>> be8214910679c26ea801d855a873b706a6d01963
		}
	}

}
