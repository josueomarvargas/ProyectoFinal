package clases;

public class Participa {

	private int idTrabajador;
	private int idObra;

	public Participa() {
		super();
	}

	public Participa(int idTrabajador, int idObra) {
		super();
		this.idTrabajador = idTrabajador;
		this.idObra = idObra;

	}

	public int getIdTrabajador() {
		return idTrabajador;
	}

	public void setIdTrabajador(int idTrabajador) {
		this.idTrabajador = idTrabajador;
	}

	public int getIdObra() {
		return idObra;
	}

	public void setIdObra(int idObra) {
		this.idObra = idObra;
	}

}
