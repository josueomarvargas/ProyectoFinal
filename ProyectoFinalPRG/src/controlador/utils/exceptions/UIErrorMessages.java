package controlador.utils.exceptions;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class UIErrorMessages {

	public static void dbError(JDialog jdialog, JFrame jframe) throws UIException {
		throw new UIException(jdialog, jframe,
				"Se ha producido un error inesperado en la conexión a la base de datos, por favor intentelo de nuevo.",
				"Error inesperado");

	}

	public static void userError(JDialog jdialog, JFrame jframe) throws UIException {
		throw new UIException(jdialog, jframe, "El usuario y/o contraseña són incorrectos, vuelva a intentarlo.  ",
				"Permiso denegado");
	}

}
