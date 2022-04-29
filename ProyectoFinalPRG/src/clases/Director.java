package modelo.clases;

import java.time.LocalDate;

public class Director extends Trabajador {
	private String categoria;

	public Director() {
		super();
	}

	public Director(int idTrabajador, String dni, String nombre, String apellido, int numTel, int numPremios,
			String direccion, LocalDate fechaNac, String tipo, String nacionalidad) {
		super(idTrabajador, dni, nombre, apellido, numTel, numPremios, direccion, tipo, fechaNac);
		this.categoria = nacionalidad;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	

}
