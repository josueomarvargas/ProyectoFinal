package controlador.utils.exceptions;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Esta clase sirve para establecer excepciones que devuelven un mensaje y abre
 * una interfaz gr�fica con el error especificado
 * 
 * 
 **/
public class UIException extends Throwable {

	private static final long serialVersionUID = 1L;

	/**
	 * Este es el constructor de la excepci�n que recibir� unos datos, es necesario
	 * pasar que ventana es la padre de esta excepci�n, solo hay dos tipos de
	 * ventanas que se van a usar {@code JDialog} y {@code JFrame}. <br>
	 * En el caso de que se envie la excepci�n y la ventana padre es
	 * {@code JDialog}, en el otro par�metro le pasaremos {@code NULL}.
	 * 
	 * 
	 * @param jdialog ventana padre
	 * @param jframe  ventana padre
	 * @param message mensaje de error que saldr� en la ventana
	 * @param title   titulo de la ventana
	 * 
	 **/
	public UIException(JDialog jdialog, JFrame jframe, String message, String title) {
		super(message);
		if (jdialog != null)
			JOptionPane.showMessageDialog(jdialog, message, title, JOptionPane.ERROR_MESSAGE);
		else
			JOptionPane.showMessageDialog(jframe, message, title, JOptionPane.ERROR_MESSAGE);

	}

}
