package controlador;

import java.sql.SQLException;
import java.util.Map;

/**
 * <h1>Interfaz generica</h1>
 * <p>
 * Esta interfaz declara los métodos CRUD (CREATE, READ, UPDATE, DELETE) las
 * clases DAO implementarán esta inferfaz especificando la clase que usará.
 * Además todos los métodos lanzan SQLException. <br>
 * Los métodos que no devuelven información (create, update, remove) nos
 * devolverán un boolean para determinar si se ha ejecutado correctamente
 * </p>
 * 
 * <p>
 * Al implementar esta interfaz hay que especificar con el <b>Diamond
 * Operator</b> que clase se usará <br>
 * Ej:
 * <code><b> public class EjemploClaseDAO implements BDgeneric<"EjemploClase"></b></code>
 * </p>
 * 
 * <p>
 * Si se requiere hacer multiples inserts, updates o deletes, es necesario el
 * uso de transacciones, para ello antes de ejecutar cualquier update habrá que
 * establecer el AutoCommit de la conexión a la base de datos a false, una vez
 * ejecutados las queries se debe de hacer un commit. Si surgue algún problema
 * en las consultas se podrá llamar al método {@code Connection.rollback()}
 * </p>
 * 
 * @param <T> tipo de objecto genérico, al implementar la interfaz se debe de
 *            especificar en el diamond operator la clase que se utilizará
 * 
 * @see <a href=
 *      "https://docs.oracle.com/javase/tutorial/jdbc/basics/transactions.html">
 *      JDBC Transactions</a>
 * @see <a href=
 *      "https://docs.oracle.com/javase/7/docs/api/java/sql/Connection.html#rollback()">
 *      Java Docs SQL API</a>
 * @author Henrique Yeguo
 * 
 **/

public interface BDgeneric<T> {

	/**
	 * Para añadir datos a la BD se pasará por parámetros al método la clase con
	 * toda la información necesaria para añadirlo a la tabla de la base de datos
	 * 
	 * @param clase la clase con la información que se necesita para el método
	 * @return un boolean que será true o false dependiendo de si se ha ejecutado
	 *         con éxito la consulta o no
	 * @throws SQLException
	 * 
	 **/
	public boolean create(T clase) throws SQLException;

	/**
	 * Para buscar una fila en concreto de la tabla especificada le pasaremos por
	 * parámetros al método un String que contenga el valor que se buscará, para
	 * buscar se recomienda usar la columna de la clave primaria.
	 * 
	 * @param string_id String con el ID para buscar en la base de datos
	 * @return <T> el método devuelve un objecto, este debe de ser el mismo que se
	 *         declaró al implementar la interfaz
	 * @throws SQLException
	 **/
	public T search(String id) throws SQLException;

	/**
	 * Para leer todo de una tabla no será necesario ningún parámetro, lo que nos
	 * devuelva la consulta SQL se guardará en un MAP
	 * 
	 * @return un Map la clave será un string y la clase que se declaró al
	 *         implementar la interfaz
	 * @throws SQLException
	 **/
	public Map<String, T> readAll() throws SQLException;

	/**
	 * Para actualizar le pasaremos la clase con los datos ya cambiados
	 * 
	 * @param clase la clase con la información que se necesita para el método
	 * @return un boolean que será true o false dependiendo de si se ha ejecutado
	 *         con éxito la consulta o no
	 * @trows SQLException
	 **/
	public boolean update(T clase) throws SQLException;

	/**
	 * Para eliminar la información, pasaremos por parámetro el ID de la tabla
	 * 
	 * @param string_id String con el ID para buscar en la base de datos
	 * @return un boolean que será true o false dependiendo de si se ha ejecutado
	 *         con éxito la consulta o no
	 * 
	 * @throws SQLException
	 **/
	public boolean remove(String id) throws SQLException;

	// Intentar hacer rollback cuando se trata de una transaccion
//	if (con.getAutoCommit() == false) {
//		try {
//			System.err.println("Fallo en la transaccion, ejecutando un rollback");
//			con.rollback();
//		} catch (SQLException e2) {
//			System.out.println(e2);
//		}
//	}

}
