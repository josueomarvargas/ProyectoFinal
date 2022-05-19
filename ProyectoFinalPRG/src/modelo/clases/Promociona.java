package modelo.clases;

import java.util.ArrayList;
import java.util.List;

public class Promociona {

	private List<Integer> idPatro;
	private List<Integer> idObra;

	public Promociona() {
		super();
		this.idPatro = new ArrayList<>();
		this.idObra = new ArrayList<>();

	}

	public List<Integer> getIdPatro() {
		return idPatro;
	}

	public void setIdPatro(List<Integer> idPatro) {
		this.idPatro = idPatro;
	}

	public List<Integer> getIdObra() {
		return idObra;
	}

	public void setIdObra(List<Integer> idObra) {
		this.idObra = idObra;
	}

	@Override
	public String toString() {
		return "Promociona [idPatro=" + idPatro + ", idObra=" + idObra + "]";
	}

}
