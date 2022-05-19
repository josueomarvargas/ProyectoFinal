package main;

import vistas.ventanas.LogIn;
import vistas.ventanas.data.DatosEquipamiento;
import vistas.ventanas.data.DatosObra;
import vistas.ventanas.table.TablaEquipamiento;
import vistas.ventanas.table.TablaPeliculasSeries;

public class Principal {

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {

//		LogIn vprincipal = new LogIn();
//		vprincipal.setVisible(true);

		TablaPeliculasSeries tps = new TablaPeliculasSeries(null, true);
		tps.setVisible(true);

//		TablaEquipamiento tequip = new TablaEquipamiento(null, true);
//		tequip.setVisible(true);

//		DatosObra datosObra = new DatosObra(null, true, "pelicula");
//		datosObra.setVisible(true);

	}

}
