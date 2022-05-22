package main;

import vistas.ventanas.LogIn;
<<<<<<< HEAD
import vistas.ventanas.data.DatosEquipamiento;
import vistas.ventanas.data.DatosObra;
<<<<<<< HEAD
import vistas.ventanas.data.DatosPersonal;
import vistas.ventanas.table.TablaPatrocinadores;
=======
import vistas.ventanas.table.TablaEquipamiento;
>>>>>>> 89bb8fce0cc912af3e1e9b80e4fef56badfa70da
import vistas.ventanas.table.TablaPeliculasSeries;
=======
>>>>>>> 0eb93779c040273dc6c5e24fe7d9afc3a015e84d

/**
 * Clase principal para ejecutar el programa
 * 
 * <note>Nota: es importante que en el classpath del proyecto esten las
 * siguientes librerias
 * <li>Atxy2k
 * <li>jcalendar
 * <li>miglayout15 swing
 * <li>mysql connector java
 * <li>TimingFramework </note>
 * 
 */
public class Principal {

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {

		LogIn vprincipal = new LogIn();
		vprincipal.setVisible(true);

<<<<<<< HEAD

<<<<<<< HEAD
<<<<<<< HEAD
		//DatosObra do = new DatosObra(null, true, null, null);
		//do.setVisible(true);
		TablaPatrocinadores tp= new TablaPatrocinadores(null, true);
		tp.setVisible(true);

		//		DatosObra datosObra = new DatosObra();
		//		datosObra.setVisible(true);
		//		DatosObra datosObra = new DatosObra(null, true, "pelicula");
		//		datosObra.setVisible(true);
=======
//		TablaEquipamiento tequip = new TablaEquipamiento(null, true);
//		tequip.setVisible(true);

//		DatosObra datosObra = new DatosObra(null, true, "pelicula");
//		datosObra.setVisible(true);
>>>>>>> 89bb8fce0cc912af3e1e9b80e4fef56badfa70da
=======
>>>>>>> 0eb93779c040273dc6c5e24fe7d9afc3a015e84d

=======
>>>>>>> 02931d77581c2c5fc75eaf1ab1c10763fc16ee96
	}

}
