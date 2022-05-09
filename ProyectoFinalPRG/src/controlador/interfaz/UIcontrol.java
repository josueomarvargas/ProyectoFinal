package controlador.interfaz;

import java.util.List;

/**
 * <h1>Controlador de Interfaz de Usuario</h1>
 * <p>
 * Esta interfaz crea un m�todo para controlar la UI, servir� para hacer
 * cualquier comprobaci�n en el programa.
 * 
 * 
 * @author Henrique Yeguo
 *
 * @param <T> tipo gen�rico que se especificar� al implementar la interfaz
 */
public interface UIcontrol<T> {

	/**
	 * M�todo para hacer las comprobaciones que sean necesarias
	 * 
	 * 
	 * @param list se le pasa una lista de strings ya que es el objecto con el que
	 *             se puede transformar a cualquier otro de forma sencilla, y se
	 *             decide como gestionar la lista al implementarlo.
	 * @return T el tipo que se ha especificado al implementar
	 */
	public T check(List<String> list);

}
