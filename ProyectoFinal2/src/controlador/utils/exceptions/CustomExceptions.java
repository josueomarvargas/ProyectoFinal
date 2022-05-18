package controlador.utils.exceptions;

import java.awt.Window;

import vistas.ventanas.custom.containers.OptionPanel;


/**
 * Esta clase sirve para establecer excepciones que devuelven un mensaje y abre
 * una interfaz gráfica con el error especificado
 * 
 * 
 **/
public class CustomExceptions extends Throwable {

	private static final long serialVersionUID = 1L;

	/**
	 * Este es el constructor de la excepción que recibirá unos datos, es necesario
	 * pasar que ventana es el padre de esta excepción.
	 * 
	 * @param parent  ventana padre, que debe ser un hijo de la clase JFrame
	 * @param message mensaje de error que saldrá en la ventana
	 * @param title   titulo de la ventana
	 **/
	public CustomExceptions(Window parent, String message, String title) {
		super(message);
		OptionPanel.showMessage(parent, message, title, OptionPanel.ERROR);

	}

}
