package controlador.interfaz;

import java.sql.SQLException;
import java.util.Map;

/**
 * <h1>Interfaz generica</h1>
 * <p>
 * Esta interfaz declara los m�todos CRUD (CREATE, READ, UPDATE, DELETE) las
 * clases DAO implementar�n esta inferfaz especificando la clase que usar�.
 * Adem�s todos los m�todos lanzan SQLException. <br>
 * Los m�todos que no devuelven informaci�n {@code (create, update, remove)} nos
 * devolver�n un boolean para determinar si se ha ejecutado correctamente. Para
 * ello habr� que comprobar qu� es lo que devuelve el comando
 * {@link java.sql.PreparedStatement#executeUpdate()} solo hay dos
 * posibilidades:
 * 
 * <li>0 si la consulta no devuelve nada, esto puede llegar ha indicar que hubo
 * un error al ejecutar la sentencia SQL</li>
 * <li>El n�mero de filas de la sentencia SQL DML, esto nos dice que se ha
 * ejecutado correctamente la consulta</li>
 * 
 * 
 * <p>
 * Al implementar esta interfaz hay que especificar con el <b>Diamond
 * Operator</b> que clase se usar� <br>
 * Ej:
 * <b>{@code public class EjemploClaseDAO implements BDgeneric<EjemploClase>}</b>
 * 
 * <p>
 * Si se requiere hacer multiples inserts, updates o deletes, es necesario el
 * uso de transacciones, para ello antes de ejecutar cualquier update habr� que
 * establecer el AutoCommit de la conexi�n a la base de datos a false, una vez
 * ejecutados las queries se debe de hacer un commit. Si surgue alg�n problema
 * en las consultas se podr� llamar al m�todo
 * {@link java.sql.Connection#rollback()} para rehacer los cambios en la
 * transacci�n actual. Tambi�n si hay que hacer un insert o update de varios
 * datos se recomienda usar el commando
 * {@link java.sql.PreparedStatement#addBatch()} para agruparlos, una vez
 * acabado de a�adirlos se debe de ejecutar el comando
 * {@link java.sql.PreparedStatement#executeBatch()}, este devuelve
 * {@code int[]}
 * 
 * 
 * @param <T> tipo de objecto gen�rico, al implementar la interfaz se debe de
 *            especificar en el diamond operator la clase que se utilizar�
 * 
 * @see <a href=
 *      "https://docs.oracle.com/javase/tutorial/jdbc/basics/transactions.html">
 *      JDBC Transactions</a>
 * @see <a href=
 *      "https://docs.oracle.com/javase/7/docs/api/java/sql/Connection.html#rollback()">
 *      Java Docs SQL API</a>
 * 
 * @author Henrique Yeguo
 **/

public interface BDgeneric<T> {

	/**
	 * Para a�adir datos a la BD se pasar� por par�metros al m�todo la clase con
	 * toda la informaci�n necesaria para a�adirlo a la tabla de la base de datos
	 * 
	 * @param clase la clase con la informaci�n que se necesita para el m�todo
	 * @return un boolean que ser� true o false dependiendo de si se ha ejecutado
	 *         con �xito la consulta
	 * @throws SQLException
	 * 
	 **/
	public boolean create(T clase) throws SQLException;

	/**
	 * Para buscar una fila en concreto de la tabla especificada le pasaremos por
	 * par�metros al m�todo un String que contenga el valor que se buscar�, para
	 * buscar se recomienda usar la columna de la clave primaria.
	 * 
	 * @param id String con el ID para buscar en la base de datos
	 * @return <T> el m�todo devuelve un objecto, este debe de ser el mismo que se
	 *         declar� al implementar la interfaz
	 * @throws SQLException
	 **/
	public T search(String id) throws SQLException;

	/**
	 * Para leer todo de una tabla no ser� necesario ning�n par�metro, lo que nos
	 * devuelva la consulta SQL se guardar� en un MAP
	 * 
	 * @return un Map la clave ser� un string y la clase que se declar� al
	 *         implementar la interfaz
	 * @throws SQLException
	 **/
	public Map<String, T> readAll() throws SQLException;

	/**
	 * Para actualizar le pasaremos la clase con los datos ya cambiados
	 * 
	 * @param clase la clase con la informaci�n que se necesita para el m�todo
	 * @return un boolean que ser� true o false dependiendo de si se ha ejecutado
	 *         con �xito la consulta
	 * @trows SQLException
	 **/
	public boolean update(T clase) throws SQLException;

	/**
	 * Para eliminar la informaci�n, pasaremos por par�metro el ID de la tabla
	 * 
	 * @param id String con el ID para buscar en la base de datos
	 * @return un boolean que ser� true o false dependiendo de si se ha ejecutado
	 *         con �xito la consulta
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
