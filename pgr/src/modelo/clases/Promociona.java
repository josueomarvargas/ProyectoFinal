package modelo.clases;

import java.util.List;

public class Promociona {

	private List<Integer> idPatro;
	private int idObra;

	public Promociona() {
		super();
	}

	public Promociona(List<Integer> idPatro, int idObra) {
		super();
		this.idPatro = idPatro;
		this.idObra = idObra;
	}

	public List<Integer> getIdPatro() {
		return idPatro;
	}

	public void setIdPatro(List<Integer> idPatro) {
		this.idPatro = idPatro;
	}

	public int getIdObra() {
		return idObra;
	}

	public void setIdObra(int idObra) {
		this.idObra = idObra;
	}

}
