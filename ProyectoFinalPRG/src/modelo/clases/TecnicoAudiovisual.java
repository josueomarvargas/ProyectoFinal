package modelo.clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TecnicoAudiovisual extends Trabajador {

	private List<String> areaTrabajos;

	public TecnicoAudiovisual() {
		super();
		areaTrabajos = new ArrayList<>();
	}

	public TecnicoAudiovisual(int idTrabajador, String dni, String nombre, String apellido, int numTel, int numPremios,
			String direccion, LocalDate fechaNac, String tipo, List<String> areaTrabajos) {
		super(idTrabajador, dni, nombre, apellido, numTel, numPremios, direccion, tipo, fechaNac);
		this.areaTrabajos = areaTrabajos;
	}

	public List<String> getAreaTrabajos() {
		return areaTrabajos;
	}

	public void setAreaTrabajos(List<String> areaTrabajos) {
		this.areaTrabajos = areaTrabajos;
	}

	public void sortList() {
		Collections.sort(areaTrabajos);
	}

}
