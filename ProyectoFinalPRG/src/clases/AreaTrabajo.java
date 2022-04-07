package clases;

import java.time.LocalDate;

public class AreaTrabajo extends Trabajador {

	private String areaTrabajo;

	public AreaTrabajo() {
		super();
	}

	public AreaTrabajo(int idTrabajador, String dni, String nombre, String apellido, int numTel, int numPremios,
			String direccion, LocalDate fechaNac, String tipo) {
		super(idTrabajador, dni, nombre, apellido, numTel, numPremios, direccion, fechaNac, tipo);
	}

	public AreaTrabajo(String areaTrabajo) {
		super();
		this.areaTrabajo = areaTrabajo;
	}

	public String getAreaTrabajo() {
		return areaTrabajo;
	}

	public void setAreaTrabajo(String areaTrabajo) {
		this.areaTrabajo = areaTrabajo;
	}

}
