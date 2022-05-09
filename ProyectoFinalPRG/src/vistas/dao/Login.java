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
 * Esta clase gestiona el inicio de sesión y cierre de sesión.
 * 
 * 
 * @author Henrique Yeguo
 *
 */
public class Login implements UIcontrol<Trabajador> {

	private static Trabajador tData;

	/**
	 * Este método será el encarcado de llamar al método
	 * {@link UserDAO#recogerInfo}, para recoger la información del
	 * {@link Trabajador} que ha encontrado al pasarle el usuario y contraseña.
	 * 
	 * 
	 * 
	 * @param list lista con el usuario y contraseña
	 * @return objecto trabjador con los datos recogido, si devuelve NULL significa
	 *         que no ha encontrado al {@link Trabajador}, esto significará que el
	 *         usuario y/o contraseña són inválidas.
	 **/
	@Override
	public Trabajador check(List<String> list) {
		if (tData == null) {
			// Controlador UI
			@SuppressWarnings("unchecked")
			BDretrieveData<Usuario, Trabajador> aux = (BDretrieveData<Usuario, Trabajador>) GenericFactory.USER
					.getUserLogin();
			tData = aux.recogerInfo(new Usuario(list.get(0), list.get(1)));
		}
		return tData;
	}

	/**
	 * Método para cerrar sesión, básicamente establecemos el atrtributo
	 * {@code tData}, que es el encargado a guardar la información del trabajador
	 * iniciado, lo pondremos a null.
	 * 
	 * @return true si se ha cerrado sesión
	 **/
	public boolean logOut() {
		if (tData != null) {
			tData = null;
			return true;
		}
		return false;
	}

}
