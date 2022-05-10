package vistas.dao;

import java.util.List;

import controlador.interfaz.BDretrieveData;
import controlador.interfaz.UIcontrol;
import controlador.utils.dao.GenericFactory;
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
public class Login implements UIcontrol<Trabajador> {

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
	public Trabajador check(List<String> list) {
		if (tData == null) {
			// Controlador UI
			@SuppressWarnings("unchecked")
			BDretrieveData<Usuario, Trabajador> aux = (BDretrieveData<Usuario, Trabajador>) GenericFactory.USER
					.getUserLogin();
			// Recoger informaci�n del trabajador
			tData = aux.recogerInfo(new Usuario(list.get(0), list.get(1)));
			
		}
		return tData;
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
