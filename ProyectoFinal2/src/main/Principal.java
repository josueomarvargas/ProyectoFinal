package main;

import vistas.ventanas.LogIn;
import vistas.ventanas.data.DatosEquipamiento;
import vistas.ventanas.data.DatosObra;
import vistas.ventanas.table.TablaPatrocinadores;
import vistas.ventanas.table.TablaPeliculasSeries;

public class Principal {

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {

		//LogIn vprincipal = new LogIn();
		//vprincipal.setVisible(true);

		TablaPeliculasSeries tps = new TablaPeliculasSeries(null, true);
		tps.setVisible(true);
		//TablaPatrocinadores tp= new TablaPatrocinadores();
		//tp.setVisible(true);

<<<<<<< HEAD:ProyectoFinal2/src/main/Principal.java
		//		DatosObra datosObra = new DatosObra();
		//		datosObra.setVisible(true);
=======
//		DatosObra datosObra = new DatosObra(null, true, "pelicula");
//		datosObra.setVisible(true);
>>>>>>> 409a10d0888d0cf78705544f5ac0413365a0f6bd:ProyectoFinalPRG/src/main/Principal.java

	}

}
