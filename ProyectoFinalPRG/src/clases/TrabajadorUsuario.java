package modelo.clases;

public class TrabajadorUsuario {

	private int idTrabajador;
	private String idUsuario;
	private String tipoUsuario;

	public TrabajadorUsuario() {
		super();
	}

	public TrabajadorUsuario(int idTrabajador, String idUsuario, String tipoUsuario) {
		super();
		this.idTrabajador = idTrabajador;
		this.idUsuario = idUsuario;
		this.tipoUsuario = tipoUsuario;
	}


	public int getIdTrabajador() {
		return idTrabajador;
	}

	public void setIdTrabajador(int idTrabajador) {
		this.idTrabajador = idTrabajador;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}
