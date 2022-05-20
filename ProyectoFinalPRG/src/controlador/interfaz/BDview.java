package controlador.interfaz;

import java.util.Map;

/**
 * 
 * <h1>Interfaz para las vistas de la BD</h1>
 * <p>
 * En esta interfaz declara el m�todo {@code Map<Integer,T> callView()} para
 * llamar a las vista en la base de datos.
 * 
 * 
 * @param <T> tipo de objecto gen�rico, al implementar la interfaz se debe de
 *            especificar en el diamond operator la clase que se utilizar�
 * 
 * @author Henrique Yeguo
 * 
 **/

public interface BDview<T> {

	/**
	 * 
	 * @return devolver� un map, la clave ser� un integer, y el valor la clase
	 *         especificada al implementarlo
	 */
	public Map<Integer, T> callView();

}
