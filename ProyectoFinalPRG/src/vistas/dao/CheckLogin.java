package vistas.dao;

import controlador.interfaz.DataManager;
import controlador.utils.dao.FactoryDAO;
import modelo.clases.Trabajador;
import modelo.clases.Usuario;
import modelo.dao.UserDAO;

/**
 * <h1>Login</h1>
 * <p>
 * Esta clase gestiona el inicio de sesi�n y cierre de sesi�n.
 * 
 * 
 * @author Henrique Yeguo
 *
 */
public class CheckLogin implements DataManager<Usuario, Trabajador> {

	private static Trabajador tData;

	/**
	 * Este m�todo ser� el encarcado de llamar al m�todo
	 * {@link UserDAO#recogerInfo}, para recoger la informaci�n del
	 * {@link Trabajador} que ha encontrado con el usuario y contrase�a que le hemos
	 * pasado por par�metros.
	 * 
	 * 
	 * 
	 * @param list lista con el usuario y contrase�a
	 * @return objecto trabjador con los datos recogido, si devuelve NULL significa
	 *         que no ha encontrado al {@link Trabajador}, esto significar� que el
	 *         usuario y/o contrase�a s�n inv�lidas.
	 **/
	@Override
	public Trabajador dataManage(Usuario user) {
		if (tData == null) {
			// Recoger informaci�n del trabajador
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
	 * M�todo para cerrar sesi�n, b�sicamente establecemos el atrtributo
	 * {@code tData}, que es el encargado a guardar la informaci�n del trabajador
	 * iniciado, lo pondremos a null.
	 * 
	 **/
	public static void logOut() {
		if (tData != null) {
			tData = null;
		}

	}

}
