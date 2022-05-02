package clases;

import java.time.LocalDate;

public class Pelicula extends ObraAudiovisual {

	private boolean esTaquillera;

	public Pelicula() {
		super();
	}

	public Pelicula(int idObra, String nombre, int duracion, LocalDate fechaEstreno, int presupuesto, String tipo,
			boolean esTaquillera) {
		super(idObra, nombre, duracion, fechaEstreno, presupuesto, tipo);
		this.esTaquillera = esTaquillera;
	}

	public boolean isEsTaquillera() {
		return esTaquillera;
	}

	public void setEsTaquillera(boolean esTaquillera) {
		this.esTaquillera = esTaquillera;
	}

}
