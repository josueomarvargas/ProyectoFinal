package controlador.interfaz;

import java.util.Map;

/**
 * 
 * <h1>Interfaz para las vistas de la BD</h1>
 * <p>
 * En esta interfaz declara el método {@code Map<Integer,T> callView()} para
 * llamar a las vista en la base de datos.
 * 
 * 
 * @param <T> tipo de objecto genérico, al implementar la interfaz se debe de
 *            especificar en el diamond operator la clase que se utilizará
 * 
 * @author Henrique Yeguo
 * 
 **/

public interface BDview<T> {

	/**
	 * 
	 * @return devolverá un map, la clave será un integer, y el valor la clase
	 *         especificada al implementarlo
	 */
	public Map<Integer, T> callView();

}
