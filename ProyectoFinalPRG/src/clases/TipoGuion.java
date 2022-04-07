package clases;

import java.time.LocalDate;

public class TipoGuion extends Trabajador {

	private String tipoGuion;

	public TipoGuion() {
		super();
	}

	public TipoGuion(int idTrabajador, String dni, String nombre, String apellido, int numTel, int numPremios,
			String direccion, LocalDate fechaNac, String tipo) {
		super(idTrabajador, dni, nombre, apellido, numTel, numPremios, direccion, fechaNac, tipo);
	}

	public TipoGuion(String tipoGuion) {
		super();
		this.tipoGuion = tipoGuion;
	}

	public String getTipoGuion() {
		return tipoGuion;
	}

	public void setTipoGuion(String tipoGuion) {
		this.tipoGuion = tipoGuion;
	}

}
