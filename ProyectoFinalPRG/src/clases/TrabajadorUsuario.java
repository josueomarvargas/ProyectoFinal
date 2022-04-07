package clases;

public class TrabajadorUsuario {

	private int idTrabajador;
	private int idUsuario;
	private String tipoUsuario;

	public TrabajadorUsuario(int idTrabajador, int idUsuario, String tipoUsuario) {
		super();
		this.idTrabajador = idTrabajador;
		this.idUsuario = idUsuario;
		this.tipoUsuario = tipoUsuario;
	}

	public TrabajadorUsuario() {
		super();
	}

	public int getIdTrabajador() {
		return idTrabajador;
	}

	public void setIdTrabajador(int idTrabajador) {
		this.idTrabajador = idTrabajador;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}
