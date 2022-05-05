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
 * preocuparnos por no haberlo cerrado.
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
	private final String UPDATEATRIBUTO = "CALL updateAtributo(?, ?, ?)";

	// Eliminar datos
	private final String DELETE = "CALL deleteTrabajador(?)";

	// Eliminar un atributo Ej: una especialidad de un actor
	private final String DELETEATRIBUTO = "CALL deleteAtributo(?, ?)";

	// Establecer conexión a la base de datos
	private static Connection con = SQLCon.getConnection();

	/**
	 * Inserción de la tabla {@code Trabajador} y de sus hijos:
	 * {@code Actor, Director, Guionista, TecnicoAudiovisual}.
	 * <p>
	 * <b>Cómo funciona el método:</b> <blockquote> Será una transacción por lo que
	 * el autoCommit estará en FALSE, primero crearemos un {@code PrepareStatement}
	 * con la sentencia para insertar en la tabla {@code Trabajador} y añadimos los
	 * datos necesarios, ejecutamos la consulta y posteriormente llamaremos al
	 * método {@link #INSERTATRIBUTO} que le pasaremos el ID y la lista de los
	 * atributos de la clase, un ejemplo: si es actor se le pasara la lista de las
	 * especialidades que tiene.<br>
	 * Posteriormente se ejecutará {@code con.commit()} para guardar todos los
	 * cambios que hemos hecho en la base de datos.</blockquote>
	 * 
	 * @param clase objecto trabajador instanciado como uno de sus hijos
	 * @return true si se ha ejecutado correctamente la inserción
	 **/
	@Override
	public boolean create(Trabajador clase) {

		// PreparedStatement insertar trabajador
		try (PreparedStatement trabajadorStat = con.prepareStatement(CREATE)) {

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

			insertAtributos(clase.getIdTrabajador(), clase.getList());

			con.commit();
			con.setAutoCommit(true);
			return true;

		} catch (

		SQLException e) {
			System.err.println(e);

			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			return false; // Si hay alguna excepcion devolverá false
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

		// Callable Statement - InsertarAtributo
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

		} catch (SQLException e) { // Si hay un problema con el servidor MySQL
			System.err.println(e);
			con.rollback();
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
	 * @param rs ResultSet con la información que necesitamos para añadir los datos
	 *           al trabajador
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
	 * <b>Cómo funciona el método:</b><blockquote> Primero buscamos los datos que ya
	 * están en la base de datos, solo usaremos la lista de sus atributos. En el
	 * {@code PreparedStatement} introcudimos todos los datos modificables del
	 * trabajador, ejecutamos la consulta y luego llamamos al método
	 * {@link #UPDATEATRIBUTO} para actualizar los atributos de esa clase, para más
	 * detalles de como funciona ir al método mencionado, una vez acabada la
	 * actualización de los atributos guardamos los cambios llamando al
	 * {@link java.sql.Connection#commit Commit} </blockquote>
	 *
	 * @param clase la clase trabajador con los datos nuevos que quieren actualizar
	 * @return true si se ha ejecutado correctamente
	 **/
	@Override
	public boolean update(Trabajador clase) {

		try (PreparedStatement stat = con.prepareStatement(UPDATE)) {

			// Datos antiguos del trabjador para comprobar que datos hay que actualizar
			Trabajador oldData = search(Integer.toString(clase.getIdTrabajador()));

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

			updateAtributo(clase.getIdTrabajador(), clase.getList(), oldData.getList());

			// Guardar los cambios de la consulta
			con.commit();

			// Cambiar a como estaba por defecto
			con.setAutoCommit(true);

			return true;

		} catch (SQLException e) {
			System.err.println(e);

			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			return false; // Si hay alguna excepcion devolverá false
		}
	}

	/**
	 * Método para actualizar los atributos que tenga un trabajador, este
	 * procedimiento se le pasa por parámetros el ID del trabajador, el atributo
	 * nuevo, y el atributo antiguo.
	 * <p>
	 * <b>Cómo funciona el método:</b><blockquote> Primero creamos dos listas, en
	 * uno se guardará los datos que no existen en la lista antigua, osea los datos
	 * nuevos, y en otro los datos que no existan en la lista nueva, en otras
	 * palabras los datos que se deben de eliminar. <br>
	 * Luego habrá un {@code ForLoop} para recorrer las listas, en este loop añade
	 * los datos que necesita el procedimiento y añade el comando a un batch/grupo,
	 * al finalizar el loop se ejecutará el batch. Posteriormente comprobamos si las
	 * dos listas són de diferente tamaño, si lo són significa que debemos de hacer
	 * una inserción o eliminar algún dato que falta o sobre, en el caso de que la
	 * lista de datos nuevos sea la mayor habrá que insertar nuevos atributos, de lo
	 * contrario habrá que eliminar los atributos que sobren.</blockquote>
	 * 
	 * 
	 * 
	 * @param id      id del trabajador
	 * @param newList la lista con los datos nuevos
	 * @param oldList la lista con los datos antiguos
	 **/
	private void updateAtributo(Integer id, List<String> newList, List<String> oldList) throws SQLException {

		// Buscar los atributos que no están en la antigua lista
		List<String> newData = newList;
		newData.removeAll(oldList);

		// Buscar los atributos que no están en la nueva lista
		List<String> removedData = oldList;
		removedData.removeAll(newList);

		// CallableStatement Statement - Update
		try (CallableStatement stat = con.prepareCall(UPDATEATRIBUTO)) {
			int i = 0;

			// Iterar por las listas
			for (i = 0; i < newData.size() && i < removedData.size(); i++) {

				// Añadir datos al CallableStatement
				stat.setInt(1, id);
				stat.setString(1, newData.get(i));
				stat.setString(3, removedData.get(i));

				// Añadimos los comandos al grupo
				stat.addBatch();

			}
			// Ejecutar el batch
			stat.executeBatch();

			// Comprobar si hay más especialidades en una de las dos listas
			if (newData.size() != removedData.size()) {
				List<String> aux = null;

				// Datos nuevos que faltan por insertar
				if (newData.size() > removedData.size()) {
					aux = newData.subList(removedData.size(), newData.size());
					insertAtributos(id, aux);

					// Especialidades que se deben eliminar
				} else {
					aux = removedData.subList(newData.size(), removedData.size());
					removeAtributo(id, oldList);
				}

			}
		}

	}

	/**
	 * Este método llama al procedimiento almacenado que sirve para eliminar los
	 * atributos que tenga un trabajador, pasando por parámetros el ID y un
	 * atributo.
	 * 
	 * @param id           el id del trabajador que tiene ese atributo
	 * @param atributoList la lista de los atributos que se quieren eliminar
	 **/
	private void removeAtributo(Integer id, List<String> atributoList) throws SQLException {

		// Callable Statement - DeleteAtributo
		try (CallableStatement stat = con.prepareCall(DELETEATRIBUTO)) {

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

		} catch (SQLException e) { // Si hay un problema con el servidor MySQL
			System.err.println(e);
			con.rollback();
		}

	}

	/**
	 * Este método llama al procedimiento almacenado en la base de datos, este
	 * procedimiento busca el tipo de trabajador y elimina la información en la
	 * tabla relacionada, luego en su propia tabla. <br>
	 * Ej: el procedimiento busca el tipo del trabajador, ei es actor va su tabla
	 * donde estan las especialidads y eliminará todas las especialidades, luego
	 * eliminará en la tabla de trabajador.
	 * 
	 * @param id en este caso para eliminar al trabajador necesitamos que el ID sea
	 *           un número
	 * @return true/false dependiendo de si se ha completado correctamente la
	 *         consulta
	 **/
	@Override
	public boolean remove(String id) {

		// PreparedStatement Delete
		try (PreparedStatement stat = con.prepareStatement(DELETE)) {

			con.setAutoCommit(false);
			// Añadir datos al Prepare Statement
			stat.setInt(1, Integer.parseInt(id));

			// Ejecutar consulta
			stat.executeUpdate();

			// Aplicamos los cambios al servidor
			con.commit();

			return true;

		} catch (SQLException e) {
			System.err.println(e);
			// Si falla volvemos como estabamos antes
			try {
				con.setAutoCommit(true);
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
	}

}
