package clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Participa {

	private List<Integer> idTrabajador;
	private int idObra;

	public Participa() {
		super();
		idTrabajador = new ArrayList<>();
	}

	public Participa(List<Integer> idTrabajador, int idObra) {
		super();
		this.idTrabajador = idTrabajador;
		this.idObra = idObra;

	}

	public List<Integer> getIdTrabajador() {
		return idTrabajador;
	}

	public void setIdTrabajador(List<Integer> idTrabajador) {
		this.idTrabajador = idTrabajador;
	}

	public int getIdObra() {
		return idObra;
	}

	public void setIdObra(int idObra) {
		this.idObra = idObra;
	}
	
	public void sortList() {
		Collections.sort(idTrabajador);
	}

}
