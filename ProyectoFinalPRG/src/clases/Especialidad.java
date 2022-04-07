package clases;

import java.time.LocalDate;

public class Especialidad extends Trabajador {
	private String especialidad;

	public Especialidad() {
		super();
	}

	public Especialidad(int idTrabajador, String dni, String nombre, String apellido, int numTel, int numPremios,
			String direccion, LocalDate fechaNac, String tipo) {
		super(idTrabajador, dni, nombre, apellido, numTel, numPremios, direccion, fechaNac, tipo);
	}

	public Especialidad(String especialidad) {
		super();
		this.especialidad = especialidad;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

}
