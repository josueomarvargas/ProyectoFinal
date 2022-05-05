package controlador.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@SuppressWarnings("unused")

/**
 * <h1>SQLCon Singleton</h1>
 * 
 * <p>
 * Conexión a la base de datos con protección entre procesos (thread safe). Esto
 * se consigue usando la palabra clave "synchronized", esto lo que hace es que
 * todos los objectos del programa que necesiten comunicación a la BD compartan
 * el mismo proceso, ya que si por cada objecto se abre una conexión esto podría
 * causar problemas al servidor de BD.
 * </p>
 * 
 * <p>
 * Al crear los métodos para comunicarse con la base de datos no hará falta
 * abrir una conexión cada vez que hacemos una consulta DML/DLL, se usará una
 * única conexión que será este singleton, así no harémos una conexión por cada
 * método. <br>
 * Normalmente se abre la conexión a la base de datos se ejecuta la consulta y
 * se cierra, pero con un singleton se abre la conexión y esa conexión es la que
 * se usará para todo el programa.
 * </p>
 * 
 * @see <a href="https://www.geeksforgeeks.org/singleton-design-pattern/">
 *      Singleton design pattern</a>
 *      <a href="https://www.geeksforgeeks.org/synchronization-in-java/">
 *      Synchronization</a>
 * @author Henrique Yeguo6
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

			// Establecemos conexión a la base de datos
			con = DriverManager.getConnection(bdProp.getProperty("url"), bdProp.getProperty("user"),
					bdProp.getProperty("pass"));
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Este método comprobará que no exista ninguna conexión previa a la base de
	 * datos, una vez comprobado se sincroniza con la clase, esto hará que solo haya
	 * un proceso de esa clase, luego se vuelve a comprobar y si no hay conexión la
	 * abrimos llamando al constructor privado
	 * 
	 * @return devolverá un objeto connection con la conexión a la BD
	 **/
	public static Connection getConnection() {
		if (con == null) {

			// Hacer que sea seguro entre hilos/procesos (thread safe)
			synchronized (SQLCon.class) {
				// Se comprueba otra vez porque puede entrar
				// en la condición ya que lo sincronizamos después
				if (con == null) {
					// Llamamos al constructor privado
					sql = new SQLCon();
				}
			}
		}
		return con;
	}

}
