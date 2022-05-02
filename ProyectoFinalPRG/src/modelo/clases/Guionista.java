package modelo.clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Guionista extends Trabajador {

	private List<String> tipoGuiones;

	public Guionista() {
		super();
		tipoGuiones = new ArrayList<>();
	}

	public Guionista(int idTrabajador, String dni, String nombre, String apellido, int numTel, int numPremios,
			String direccion, LocalDate fechaNac, String tipo, List<String> tipoGuiones) {
		super(idTrabajador, dni, nombre, apellido, numTel, numPremios, direccion, tipo, fechaNac);
		this.tipoGuiones = tipoGuiones;
	}

	public List<String> getTipoGuiones() {
		return tipoGuiones;
	}

	public void setTipoGuiones(List<String> tipoGuiones) {
		this.tipoGuiones = tipoGuiones;
	}

	public void sortList() {
		Collections.sort(tipoGuiones);
	}
}
