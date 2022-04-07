package clases;

public class Caracteristica {

	private int idEquip;
	private String caracteristica;

	public Caracteristica() {
		super();
	}

	public Caracteristica(int idEquip, String caracteristica) {
		super();
		this.idEquip = idEquip;
		this.caracteristica = caracteristica;
	}

	public int getIdEquip() {
		return idEquip;
	}

	public void setIdEquip(int idEquip) {
		this.idEquip = idEquip;
	}

	public String getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}

}
