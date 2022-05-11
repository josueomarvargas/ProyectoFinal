package main;

import vistas.ventanas.gui.LogIn;
import vistas.ventanas.table.TablaPatrocinadores;
import vistas.ventanas.table.TablaPeliculasSeries;

public class Principal {

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {

//		LogIn vprincipal = new LogIn();
//		vprincipal.setVisible(true);

//		TablaPatrocinadores test = new TablaPatrocinadores();

		TablaPeliculasSeries test = new TablaPeliculasSeries();

		test.setVisible(true);
	}

}
