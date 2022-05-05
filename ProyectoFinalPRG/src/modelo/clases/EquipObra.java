package modelo.clases;

import java.util.ArrayList;
import java.util.List;

public class EquipObra {

	private int idObra;
	private List<Integer> idEquip;

	public EquipObra() {
		super();
		idEquip = new ArrayList<>();
	}

	public EquipObra(int idObra, List<Integer> idEquip) {
		super();
		this.idObra = idObra;
		this.idEquip = idEquip;
	}

	public int getIdObra() {
		return idObra;
	}

	public void setIdObra(int idObra) {
		this.idObra = idObra;
	}

	public List<Integer> getIdEquip() {
		return idEquip;
	}

	public void setIdEquip(List<Integer> idEquip) {
		this.idEquip = idEquip;
	}

}
