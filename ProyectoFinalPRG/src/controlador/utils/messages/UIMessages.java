package controlador.utils.messages;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Esta clase sirve para establecer excepciones que devuelven un mensaje y abre
 * una interfaz gráfica con el error especificado
 * 
 * 
 **/
public class UIMessages extends Throwable {

	private static final long serialVersionUID = 1L;

	/**
	 * Este es el constructor de la excepción que recibirá unos datos, es necesario
	 * pasar que ventana es el padre de esta excepción.
	 * 
	 * @param parent  ventana padre, que debe ser un hijo de la clase JDialog
	 * @param message mensaje de error que saldrá en la ventana
	 * @param title   titulo de la ventana
	 * 
	 **/
	public <E extends JDialog> UIMessages(E parent, String message, String title) {
		super(message);
		JOptionPane.showMessageDialog(parent, message, title, JOptionPane.ERROR_MESSAGE);

	}

	/**
	 * Este es el constructor de la excepción que recibirá unos datos, es necesario
	 * pasar que ventana es el padre de esta excepción.
	 * 
	 * @param parent  ventana padre, que debe ser un hijo de la clase JFrame
	 * @param message mensaje de error que saldrá en la ventana
	 * @param title   titulo de la ventana
	 **/
	public <E extends JFrame> UIMessages(E parent, String message, String title) {
		super(message);
		JOptionPane.showMessageDialog(parent, message, title, JOptionPane.ERROR_MESSAGE);

	}

}
