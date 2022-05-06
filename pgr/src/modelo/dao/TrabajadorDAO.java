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
import modelo.clases.Actor;
import modelo.clases.Director;
import modelo.clases.Guionista;
import modelo.clases.TecnicoAudiovisual;
import modelo.clases.Trabajador;

/**
 * Esta clase implementa la interfaz genérica
 * {@link controlador.interfaz.BDgeneric BDgeneric}, el CRUD de este DAO
 * gestiona las tablas
 * {@code Trabajador, Actor, Director, Guionista y TécnicoAudiovisual}, que se
 * encuentran en el servidor MySQL.
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
 * 
 * @see <a href=
 *      "https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html">
 *      Recursos en el Try-Catch</a>
 * 
 **/
public class TrabajadorDAO implements BDgeneric<Trabajador> {

	// MySQL Consultas
	// Inserción de datos en las tablas
	private final String CREATE = "INSERT INTO trabajador"
			+ "(dni, nombre, apellido, numtel, numPremios, direccion, tipo, FechaNac) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

	// Insertar en las tablas de la herencia
	private final String INSERTATRIBUTO = "CALL insertAtributo(?, ?)";

	// Buscar un trabajador en especifico
	private final String SEARCH = "CALL searchTrabajador(?)";

	// Leer todos los trabajadores
	private final String READALL = "CALL showTrabajadores()";

	// Actualizar datos
	private final String UPDATE = "UPDATE trabajador SET nombre = ?, apellido = ?, numtel = ?, numPremios = ?, direccion = ?, fechaNac = ? WHERE idTrabajador = ?";

	// Eliminar datos
	private final String DELETE = "DELETE trabajador WHERE idtrabajador = ? ";

	// Eliminar un atributo Ej: una especialidad de un actor
	private final String DELETEATRIBUTO = "CALL deleteAtributo(?)";

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
	 * Inserción de la tabla {@code Trabajador} y de sus hijos:
	 * {@code Actor, Director, Guionista, TecnicoAudiovisual}.
	 * <p>
	 * <b>Cómo funciona el método:</b> <blockquote> Será una transacción por lo que
	 * el autoCommit estará en FALSE, primero crearemos un {@code PrepareStatement}
	 * con la sentencia para insertar en la tabla {@code Trabajador} y añadimos los
	 * datos necesarios, ejecutamos la consulta, recogemos el ID que se ha generado
	 * por el {@code AUTO_INCREMENT} y posteriormente llamaremos al método
	 * {@link #INSERTATRIBUTO} que le pasaremos el ID generado y la lista de los
	 * atributos de la clase, un ejemplo: si es actor se le pasara la lista de las
	 * especialidades que tiene.<br>
	 * Posteriormente se ejecutará {@code con.commit()} para guardar todos los
	 * cambios que hemos hecho en la base de datos.</blockquote>
	 * 
	 * <note>Nota: al crear el {@code PreparedStatement} aparte de pasar la
	 * sentencia SQL se ha puesto
	 * {@link java.sql.PreparedStatement#RETURN_GENERATED_KEYS}, esto sirve para
	 * decir a la consulta que nos devuelva las claves que se generan por el
	 * {@code AUTO_INCREMENT}, con esta opción podemos recogerlo en un
	 * {@code ResultSet}</note>
	 * 
	 * 
	 * @param clase objecto trabajador instanciado como uno de sus hijos
	 * @return true si se ha ejecutado correctamente la inserción
	 **/
	@Override
	public boolean create(Trabajador clase) {

		// Abrir la conexión
		this.openConnection();
		ResultSet rs;

		// PreparedStatement insertar trabajador
		try (PreparedStatement trabajadorStat = con.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS)) {

			// Inicio de la transacción
			con.setAutoCommit(false);

			// Añadir datos al Prepare Statement
			trabajadorStat.setString(1, clase.getDni());
			trabajadorStat.setString(2, clase.getNombre());
			trabajadorStat.setString(3, clase.getApellido());
			trabajadorStat.setInt(4, clase.getNumTel());
			trabajadorStat.setInt(5, clase.getNumPremios());
			trabajadorStat.setString(6, clase.getDireccion());
			trabajadorStat.setString(7, clase.getTipo());
			trabajadorStat.setDate(8, Date.valueOf(clase.getFechaNac()));

			// Ejecutar consulta
			trabajadorStat.executeUpdate();

			// Recoger la clave del trabajador generada
			rs = trabajadorStat.getGeneratedKeys();

			if (rs.next()) {
				insertAtributos(rs.getInt(1), clase.getList());
			}

			// Aplicamos los cambios al servidor
			con.commit();

			// Autocommit por defecto
			con.setAutoCommit(true);
			return true;

		} catch (SQLException e) {
			System.err.println(e);

			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			return false; // Si hay alguna excepcion devolverá false

		} finally {
			// Cerrar la conexión
			this.closeConnection();

		}

	}

	/**
	 * Método para insertar los datos de la lista que tienen las clase heredadas de
	 * {@code Trabajador} a las respectivas tablas en el servidor MySQL.
	 * <p>
	 * <b>Como funciona este método:</b> <blockquote> Hay un loop de la lista de
	 * atributos que se añadirán al grupo/batch la llamada al procedimiento y al
	 * finalizar se ejecutará el grupo/batch con todas las las llamadas.
	 * 
	 * </blockquote>
	 * 
	 * @param clase objeto con los datos que se insertarán
	 * @throws SQLException
	 * 
	 **/
	private void insertAtributos(Integer id, List<String> atributoList) throws SQLException {

		// Callable Statement - Insertar Atributo
		try (CallableStatement stat = con.prepareCall(INSERTATRIBUTO)) {

			// Iteramos por cada atributo
			for (String atributo : atributoList) {
				// Añadir parametros al procedimiento
				stat.setInt(1, id);
				stat.setString(2, atributo);
				// Añadir comando al grupo
				stat.addBatch();
			}
			// Ejecutará el grupo de consultas
			stat.executeBatch();

		}
	}

	/**
	 * Este método llama a un procedimiento guardado en la base de datos, este
	 * procedimiento nos servirá para visualizar los datos de un trabajador.
	 * <p>
	 * <b>Cómo funciona el método:</b><blockquote> Este procedimiento requiere que
	 * le pasemos un ID por parámetros, lo llamamos y recogemos el
	 * {@code ResultSet}. Si hay datos recogidos comprobamos que tipo de trabajador
	 * es, dependiendo de quien sea instanciamos la clase requerida haciendo
	 * polimorfismo, si la clase hija tiene una lista llamamos al método
	 * {@link #addToList} para añadir la información, posteriormente agregamos los
	 * datos de la clase padre.</blockquote>
	 * 
	 * <note>Nota: Por defecto el result set solo se mueve para adelante, con
	 * {@code ResultSet.TYPE_SCROLL_INSENSITIVE} nos permite mover el cursor del RS
	 * hacia atras, y el {@code  ResultSet.CONCUR_READ_ONLY} es para que el RS esté
	 * en modo solo lectura.</note>
	 * 
	 * @param id Identificador del trabajador que se va a buscar en la base de datos
	 * @return un objecto {@code Trabajador}, downcasteado a uno de sus hijos:
	 *         {@code Actor, Director, Guionista, TecnicoAudiovisual}
	 **/
	@Override
	public Trabajador search(String id) {

		this.openConnection();
		// ResultSet y la clase para recoger los datos de la consulta
		ResultSet rs = null;
		Trabajador trabajador = null;

		// Prepare Statement - Search
		try (CallableStatement stat = con.prepareCall(SEARCH, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY)) {

			// Añadir datos al Prepare Statement
			stat.setString(1, id);

			// Ejecutar consulta y guardarlo en el Result Set
			rs = stat.executeQuery();

			// Comprobar que RS a recuperado informacion del executeQuery
			if (rs.next()) {

				// Recoger el tipo de trabajador
				switch (rs.getString("tipo").toLowerCase()) {

				case "actor":
					// Downcasting Trabajador -> Actor
					trabajador = new Actor();
					do {
						trabajador = addToList(rs, trabajador);
					} while (rs.next());
					break;

				case "director":
					// Downcasting Trabajador -> Director
					trabajador = new Director();
					// Añadir al director su categoria
					((Director) trabajador).setCategoria(rs.getString("Categoria"));
					break;

				case "guionista":
					// Downcasting Trabajador -> Guionista
					trabajador = new Guionista();
					do {
						trabajador = addToList(rs, trabajador);
					} while (rs.next());
					break;

				case "tecnicoaudiovisual":
					// Downcasting Trabajador -> Tecnico Audiovisual
					trabajador = new TecnicoAudiovisual();
					do {
						trabajador = addToList(rs, trabajador);
					} while (rs.next());
					break;

				}

				// Volver al primer RS para recoger la información que nos falta
				rs.first();
				// Añadir los valores del RS al objecto
				trabajador.setDatos(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getString(7), rs.getString(8), rs.getDate(9).toLocalDate());

			}

		} catch (SQLException e) {
			System.err.println(e);
			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			this.closeConnection();
		}

		// Devolvemos el objecto, si RS NO ha devuelto nada, devolverá NULL
		return trabajador;
	}

	/**
	 * Este método llamará al procedimiento guardado en la base de datos para
	 * recoger toda la información de un {@code trabajador}, e información adicional
	 * dependiendo de si es
	 * {@code Actor, Director, Guionista, TecnicoAudiovisual}.<br>
	 * <b>Cómo funciona el método:</b><blockquote> Este procedimiento nos devolverá
	 * todos los trabajadores, comprobamos si el map contiene ya el ID del
	 * trabajador, si lo tiene recogemos el objecto trabajador y añadimos más datos,
	 * si el map no lo contiene deberemos de comprobar el tipo de trabajdor que es y
	 * en un switch añadimos los datos específicos de ese tipo de trabajador. Se
	 * añadirá los datos al map, si es una ID igual se sobreescribirá con los nuevos
	 * datos, si no se añadirá un objecto nuevo<br>
	 * </blockquote>
	 * 
	 * <note>Nota: para añadir los datos a un trabajador se ha creado un método para
	 * no crear código repetitivo. {@link #addToList(ResultSet, Trabajador)
	 * addToList}<note>
	 * 
	 * @return Map como clave ID del trabajador, y el objecto trabajador con sus
	 *         datos
	 * 
	 **/
	@Override
	public Map<Integer, Trabajador> readAll() {

		this.openConnection();

		// RS y la clase para recoger los datos, además un map para guardar
		Map<Integer, Trabajador> trabajadores = new HashMap<>();
		ResultSet rs = null;
		Trabajador trabajador = null;

		// Prepare Statement - ReadAll
		try (CallableStatement stat = con.prepareCall(READALL, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY)) {

			// Ejecutar el procedimiento y guardarlo en el result set
			rs = stat.executeQuery();

			// Mientras que RS sigua teniendo filas con información
			while (rs.next()) {

				// Si es igual el ID, osea es la misma persona
				if (trabajadores.containsKey(rs.getInt(1))) {
					// Recogemos el objecto trabajador del MAP
					trabajador = trabajadores.get(rs.getInt(1));
					trabajador = addToList(rs, trabajador);

				} else {
					// Recoger el tipo de trabajador
					switch (rs.getString("tipo").toLowerCase()) {

					case "actor":
						// Downcasting Trabajador -> Actor
						trabajador = new Actor();
						trabajador = addToList(rs, trabajador);
						break;

					case "director":
						// Downcasting Trabajador -> Director
						trabajador = new Director();
						// Añadir al director su categoria
						((Director) trabajador).setCategoria(rs.getString("Categoria"));
						break;

					case "guionista":
						// Downcasting Trabajador -> Guionista
						trabajador = new Guionista();
						trabajador = addToList(rs, trabajador);
						break;

					case "tecnicoaudiovisual":
						// Downcasting Trabajador -> Tecnico Audiovisual
						trabajador = new TecnicoAudiovisual();
						trabajador = addToList(rs, trabajador);
						break;
					}
					// Añadimos los datos del trabajador
					trabajador.setDatos(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
							rs.getInt(6), rs.getString(7), rs.getString(8), rs.getDate(9).toLocalDate());
				}

				// Añadimos al map el key-ID, value-Trabajador
				trabajadores.put(rs.getInt(1), trabajador);
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			this.closeConnection();
		}

		// Devolverá un map con los datos, o un map vacío
		return trabajadores;
	}

	/**
	 * Añadir los datos a la lista del trabajador, este método se usará para las
	 * clases {@code Actor, Guionista, TecnicoAudiovisual} que tienen una lista y
	 * para no crear código repetitivo se ha creado un método.
	 * 
	 * 
	 * @param rs         ResultSet con la información que necesitamos para añadir
	 *                   los datos al trabajador
	 * @param trabajador el objecto al que se le añadirá los datos del RS
	 * @return objecto trabajador con datos añadidos a la lista
	 **/
	private Trabajador addToList(ResultSet rs, Trabajador trabajador) throws SQLException {
		// Lista para añadir varios atributos que tenga un trabajador
		List<String> auxList;

		// Recoger la lista
		auxList = trabajador.getList();

		// Comprobamos como esta instanciado el trabajador
		if (trabajador instanceof Actor) {
			// Añadir datos a la lista
			auxList.add(rs.getString("Especialidad"));
			// Poner la lista al objecto
			((Actor) trabajador).setEspecialidades(auxList);
		} else if (trabajador instanceof Guionista) {
			auxList.add(rs.getString("TipoGuion"));
			((Guionista) trabajador).setTipoGuiones(auxList);
		} else {
			auxList.add(rs.getString("AreaTrabajo"));
			((TecnicoAudiovisual) trabajador).setAreaTrabajos(auxList);
		}

		return trabajador;
	}

	/**
	 * Este método sirve para actualizar la información de un {@code Trabajador}-
	 * <br>
	 * <b>Cómo funciona el método:</b><blockquote> En el
	 * {@code PreparedStatement stat} introcudimos todos los datos modificables del
	 * trabajador, ejecutamos la consulta y luego llamamos al método
	 * {@link #DELETEATRIBUTO} para eliminar los atributos de la tabla, y luego
	 * llamamos al {@link #INSERTATRIBUTO} para insertar todos los atributos de la
	 * lista, una vez finalizado aplicamos los cambios llamando al
	 * {@link java.sql.Connection#commit Commit} </blockquote>
	 *
	 * @param clase la clase trabajador con los datos nuevos que quieren actualizar
	 * @return true si se ha ejecutado correctamente
	 **/
	@Override
	public boolean update(Trabajador clase) {

		this.openConnection();

		try (PreparedStatement stat = con.prepareStatement(UPDATE);
				CallableStatement procedure = con.prepareCall(DELETEATRIBUTO)) {

			// Inicio de la transacción
			con.setAutoCommit(false);

			// Añadir datos al Prepare Statement
			stat.setString(1, clase.getNombre());
			stat.setString(2, clase.getApellido());
			stat.setInt(3, clase.getNumTel());
			stat.setInt(4, clase.getNumPremios());
			stat.setString(5, clase.getDireccion());
			stat.setDate(6, Date.valueOf(clase.getFechaNac()));
			stat.setInt(7, clase.getIdTrabajador());

			// Ejecutar consulta
			stat.executeUpdate();

			// Eliminamos los atributos del trabajador
			procedure.setInt(1, clase.getIdTrabajador());
			procedure.executeUpdate();

			// Insertamos los nuevos
			insertAtributos(clase.getIdTrabajador(), clase.getList());

			// Guardar los cambios de la consulta
			con.commit();

			// Cambiar a como estaba por defecto
			con.setAutoCommit(true);

			return true;

		} catch (SQLException e) {
			System.err.println(e);

			try {
				// Revertir los cambios
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			return false; // Si hay alguna excepcion devolverá false
		} finally {
			this.closeConnection();
		}
	}

	/**
	 * Este método elimina toda la información relacionada con el trabajador
	 * 
	 * @param id id del trabajador que se quiere eliminar 
	 * @return true/false dependiendo de si se ha completado correctamente la
	 *         consulta
	 **/
	@Override
	public boolean remove(String id) {

		this.openConnection();

		// PreparedStatement Delete
		try (PreparedStatement stat = con.prepareStatement(DELETE)) {

			// Añadir datos al Prepare Statement
			stat.setString(1, id);

			// Ejecutar consulta
			return stat.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			System.err.println(e);

			return false;

		} finally {
			this.closeConnection();
		}
	}

}
