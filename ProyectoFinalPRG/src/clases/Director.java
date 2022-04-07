package clases;

import java.time.LocalDate;

public class Director extends Trabajador{
	private String categoria;

	public Director() {
		super();
	}

	public Director(int idTrabajador, String dni, String nombre, String apellido, int numTel, int numPremios,
			String direccion, LocalDate fechaNac, String tipo) {
		super(idTrabajador, dni, nombre, apellido, numTel, numPremios, direccion, fechaNac, tipo);
	}

	public Director(String nacionalidad) {
		super();
		this.categoria = nacionalidad;
	}

	public String getNacionalidad() {
		return categoria;
	}

	public void setNacionalidad(String nacionalidad) {
		this.categoria = nacionalidad;
	}
	
	
}
