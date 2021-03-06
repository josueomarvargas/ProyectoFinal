package controlador.interfaz;

/**
 * <h1>Interfaz para recoger datos y/o comprobar datos</h1>
 * <p>
 * En esta interfaz declara un solo m?todo, que servir? para devolver datos de
 * una clase diferente a la que se le pasa por par?metros. <br>
 * Ej: {public Trabajador recogerInfo(Usuario clase)}, en este ejemplo le
 * pasamos el usuario por par?metros y recojemos los datos del trabajador
 * 
 * 
 * @author Henrique Yeguo
 *
 * @param <T> objeto que se pasar? por parametros en el m?todo
 * @param <U> objeto que devolver? el m?todo
 */
public interface DataManager<T, U> {

	/**
	 * 
	 * @param data objecto que se ha especificado al implementar y que pasar? por
	 *              parametros
	 * @return devolver? el tipo que se ha especificado al implementarlo
	 */
	public U dataManage(T data);

}
