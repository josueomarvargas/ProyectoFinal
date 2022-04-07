package clases;

import java.time.LocalDate;
import java.util.List;

public class Guionista extends Trabajador{

	private List <TipoGuion> tipoGuiones;

	public Guionista() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Guionista(int idTrabajador, String dni, String nombre, String apellido, int numTel, int numPremios,
			String direccion, LocalDate fechaNac, String tipo) {
		super(idTrabajador, dni, nombre, apellido, numTel, numPremios, direccion, fechaNac, tipo);
		// TODO Auto-generated constructor stub
	}

	public Guionista(List<TipoGuion> tipoGuiones) {
		super();
		this.tipoGuiones = tipoGuiones;
	}

	public List<TipoGuion> getTipoGuiones() {
		return tipoGuiones;
	}

	public void setTipoGuiones(List<TipoGuion> tipoGuiones) {
		this.tipoGuiones = tipoGuiones;
	}

	
	
	
}
