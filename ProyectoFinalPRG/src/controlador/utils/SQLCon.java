package controlador.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * <h1>SQLCon Singleton</h1>
 * <p>
 * Esta clase facilita la conexi�n a la base de datos, para cambiar alg�n
 * par�metro como el usuario, contrase�a o el nombre de la base de datos, hay
 * que modificar el archivo que est� en {@code src/modelo/bd.proeprties}.
 * 
 * @see <a href="https://www.geeksforgeeks.org/singleton-design-pattern/">
 *      Singleton design pattern</a>
 * @author Henrique Yeguo
 **/

public class SQLCon {

	// Nombre del archivo properties
	private static final String properties = "./src/modelo/bd.properties";
	private static Connection con = null;
	private static SQLCon sql = null;

	// Constructor privado para que no se pueda instanciar fuera de la clase
	private SQLCon() {

		try {
			// Cargamos el archivo properties
			Properties bdProp = new Properties();
			bdProp.load(new FileInputStream(properties));

			// Establecemos conexi�n a la base de datos
			con = DriverManager.getConnection(bdProp.getProperty("url"), bdProp.getProperty("user"),
					bdProp.getProperty("pass"));
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Este m�todo comprobar� que no exista ninguna conexi�n previa a la base de
	 * datos, y si no hay conexi�n la abrimos llamando al constructor privado
	 * 
	 * @return devolver� un objeto connection con la conexi�n a la BD
	 **/
	public static Connection openConnection() {
		if (con == null && sql == null) {
			// Llamamos al constructor privado
			sql = new SQLCon();

		}
		return con;
	}

	/**
	 * Este m�todo es para cerrar la conexi�n de la base de datos, tambi�n
	 * inicializar� el objecto {@code CON, SQL} como null, para poder llamar otra vez al
	 * {@link #openConnection()} cuando se necesite.
	 * 
	 **/
	public static void closeConnection() {
		if (con != null) {
			try {
				con.close();
				con = null;
				sql = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}