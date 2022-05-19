package modelo.clases;

public class Usuario {

	private String idUsuario;
	private String passwd;
	private int idTrabajador;

	public Usuario() {
		super();
	}

	public Usuario(String idUsuario, String passwd) {
		super();
		this.idUsuario = idUsuario;
		this.passwd = passwd;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public int getIdTrabajador() {
		return idTrabajador;
	}

	public void setIdTrabajador(int idTrabajador) {
		this.idTrabajador = idTrabajador;
	}

	public void setUserPass(String idUser, String passwd) {
		this.idUsuario = idUser;
		this.passwd = passwd;
	}

}
