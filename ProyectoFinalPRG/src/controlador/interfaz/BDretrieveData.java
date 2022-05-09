package controlador.interfaz;

/**
 * <h1>Interfaz para recoger datos específicos</h1>
 * <p>
 * En esta interfaz declara un solo método, que servirá para devolver datos de
 * una clase diferente a la que se le pasa por parámetros. <br>
 * Ej: {public Trabajador recogerInfo(Usuario clase)}, en este ejemplo le
 * pasamos el usuario por parámetros y recojemos los datos del trabajador
 * 
 * 
 * @author Henrique Yeguo
 *
 * @param <T> objeto que se pasará por parametros en el método
 * @param <U> objeto que devolverá el método
 */
public interface BDretrieveData<T, U> {

	/**
	 * 
	 * @param clase objecto que se ha especificado al implementar y que pasará por
	 *              parametros
	 * @return devolverá el tipo que se ha especificado al implementarlko
	 */
	public U recogerInfo(T clase);

}
