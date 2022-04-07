package clases;

public class Usuario {

	private String idUsuario;
	private int passwd;

	public Usuario() {
		super();
	}

	public Usuario(String idUsuario, int passwd) {
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

	public int getPasswd() {
		return passwd;
	}

	public void setPasswd(int passwd) {
		this.passwd = passwd;
	}

}
