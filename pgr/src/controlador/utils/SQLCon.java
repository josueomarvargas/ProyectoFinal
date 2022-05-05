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
 * Conexi�n a la base de datos con protecci�n entre procesos (thread safe). Esto
 * se consigue usando la palabra clave "synchronized", esto lo que hace es que
 * todos los objectos del programa que necesiten comunicaci�n a la BD compartan
 * el mismo proceso, ya que si por cada objecto se abre una conexi�n esto podr�a
 * causar problemas al servidor de BD.
 * </p>
 * 
 * <p>
 * Al crear los m�todos para comunicarse con la base de datos no har� falta
 * abrir una conexi�n cada vez que hacemos una consulta DML/DLL, se usar� una
 * �nica conexi�n que ser� este singleton, as� no har�mos una conexi�n por cada
 * m�todo. <br>
 * Normalmente se abre la conexi�n a la base de datos se ejecuta la consulta y
 * se cierra, pero con un singleton se abre la conexi�n y esa conexi�n es la que
 * se usar� para todo el programa.
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

			// Establecemos conexi�n a la base de datos
			con = DriverManager.getConnection(bdProp.getProperty("url"), bdProp.getProperty("user"),
					bdProp.getProperty("pass"));
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Este m�todo comprobar� que no exista ninguna conexi�n previa a la base de
	 * datos, una vez comprobado se sincroniza con la clase, esto har� que solo haya
	 * un proceso de esa clase, luego se vuelve a comprobar y si no hay conexi�n la
	 * abrimos llamando al constructor privado
	 * 
	 * @return devolver� un objeto connection con la conexi�n a la BD
	 **/
	public static Connection getConnection() {
		if (con == null) {

			// Hacer que sea seguro entre hilos/procesos (thread safe)
			synchronized (SQLCon.class) {
				// Se comprueba otra vez porque puede entrar
				// en la condici�n ya que lo sincronizamos despu�s
				if (con == null) {
					// Llamamos al constructor privado
					sql = new SQLCon();
				}
			}
		}
		return con;
	}

}
