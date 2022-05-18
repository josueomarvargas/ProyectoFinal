package modelo.clases;

import java.util.ArrayList;
import java.util.List;

public class Guionista extends Trabajador {

	private List<String> tipoGuiones;

	public Guionista() {
		super();
		tipoGuiones = new ArrayList<>();
	}

	public List<String> getTipoGuiones() {
		return tipoGuiones;
	}

	public void setTipoGuiones(List<String> tipoGuiones) {
		this.tipoGuiones = tipoGuiones;
	}

	@Override
	public List<String> getList() {
		return tipoGuiones;
	}

}
