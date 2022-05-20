package modelo.clases;

import java.util.ArrayList;
import java.util.List;

public class EquipObra {

	private List<Integer> idObra;
	private List<Integer> idEquip;

	public EquipObra() {
		super();
		this.idObra = new ArrayList<>();
		this.idEquip = new ArrayList<>();

	}

	public List<Integer> getIdObra() {
		return idObra;
	}

	public void setIdObra(List<Integer> idObra) {
		this.idObra = idObra;
	}

	public List<Integer> getIdEquip() {
		return idEquip;
	}

	public void setIdEquip(List<Integer> idEquip) {
		this.idEquip = idEquip;
	}

}
