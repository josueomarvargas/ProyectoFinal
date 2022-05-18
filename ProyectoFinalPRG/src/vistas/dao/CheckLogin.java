package vistas.dao;

import controlador.interfaz.DataManager;
import controlador.utils.dao.FactoryDAO;
import modelo.clases.Trabajador;
import modelo.clases.Usuario;
import modelo.dao.UserDAO;

/**
 * <h1>Login</h1>
 * <p>
 * Esta clase gestiona el inicio de sesión y cierre de sesión.
 * 
 * 
 * @author Henrique Yeguo
 *
 */
public class CheckLogin implements DataManager<Usuario, Trabajador> {

	private static Trabajador tData;

	/**
	 * Este método será el encarcado de llamar al método
	 * {@link UserDAO#recogerInfo}, para recoger la información del
	 * {@link Trabajador} que ha encontrado con el usuario y contraseña que le hemos
	 * pasado por parámetros.
	 * 
	 * 
	 * 
	 * @param list lista con el usuario y contraseña
	 * @return objecto trabjador con los datos recogido, si devuelve NULL significa
	 *         que no ha encontrado al {@link Trabajador}, esto significará que el
	 *         usuario y/o contraseña són inválidas.
	 **/
	@Override
	public Trabajador dataManage(Usuario user) {
		if (tData == null) {
			// Recoger información del trabajador
			tData = FactoryDAO.getUsuario().dataManage(user);
		}
		return tData;
	}

	public static Trabajador getLogin() {
		if (tData != null) {
			return tData;
		}
		return null;
	}

	/**
	 * Método para cerrar sesión, básicamente establecemos el atrtributo
	 * {@code tData}, que es el encargado a guardar la información del trabajador
	 * iniciado, lo pondremos a null.
	 * 
	 **/
	public static void logOut() {
		if (tData != null) {
			tData = null;
		}

	}

}
