package clases;

import java.time.LocalDate;

public class Pelicula extends ObraAudiovisual {

	private boolean esTaquillera;

	public Pelicula() {
		super();
	}

	public Pelicula(int idObra, String nombre, int duracion, LocalDate fechaEstreno, int presupuesto, String tipo) {
		super(idObra, nombre, duracion, fechaEstreno, presupuesto, tipo);
	}

	public Pelicula(boolean esTaquillera) {
		super();
		this.esTaquillera = esTaquillera;
	}

	public boolean isEsTaquillera() {
		return esTaquillera;
	}

	public void setEsTaquillera(boolean esTaquillera) {
		this.esTaquillera = esTaquillera;
	}

}
