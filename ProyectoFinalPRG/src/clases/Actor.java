package modelo.clases;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class Actor extends Trabajador {

	private List<String> especialidades;

	public Actor() {
		super();
	}

	public Actor(int idTrabajador, String dni, String nombre, String apellido, int numTel, int numPremios,
			String direccion, String tipo, LocalDate fechaNac, List<String> especialidades) {
		super(idTrabajador, dni, nombre, apellido, numTel, numPremios, direccion, tipo, fechaNac);
		this.especialidades = especialidades;
	}

	public List<String> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<String> especialidades) {
		this.especialidades = especialidades;
	}

	public void sortList() {
		Collections.sort(especialidades);
	}

}
