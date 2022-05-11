package modelo.clases;

import java.util.ArrayList;
import java.util.List;

public class Participa {

	private List<Integer> idTrabajador;
	private List<Integer> idObra;

	public Participa() {
		super();
		this.idTrabajador = new ArrayList<>();
		this.idObra = new ArrayList<>();
	}



	public List<Integer> getIdTrabajador() {
		return idTrabajador;
	}

	public void setIdTrabajador(List<Integer> idTrabajador) {
		this.idTrabajador = idTrabajador;
	}

	public List<Integer> getIdObra() {
		return idObra;
	}

	public void setIdObra(List<Integer> idObra) {
		this.idObra = idObra;
	}



	@Override
	public String toString() {
		return "Participa [idTrabajador=" + idTrabajador + ", idObra=" + idObra + "]";
	}
	
	

}
