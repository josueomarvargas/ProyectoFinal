package main;

import vistas.ventanas.LogIn;

/**
 * Clase principal para ejecutar el programa
 * 
 * <note>Nota: es importante que en el classpath del proyecto esten las
 * siguientes librerias
 * <li>Atxy2k
 * <li>jcalendar
 * <li>miglayout15 swing
 * <li>mysql connector java
 * <li>TimingFramework </note>
 * 
 */
public class Principal {

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {

		LogIn vprincipal = new LogIn();
		vprincipal.setVisible(true);

	}

}
