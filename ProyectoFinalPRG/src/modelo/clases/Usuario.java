package modelo.clases;

/**
 * Clase para guardar la información de los usuarios
 * 
 * @author Henrique Yeguo
 * @author Iker Aspiazu
 */
public class Usuario {

	/** El nombre de usuario **/
	private String idUsuario;
	/** La contrasña **/
	private String passwd;
	/** El id del trabajador **/
	private int idTrabajador;

	/**
	 * Constructor vacío
	 */
	public Usuario() {
		super();
	}

	/**
	 * Constructor con parámetros
	 * 
	 * @param idUsuario
	 * @param passwd
	 */
	public Usuario(String idUsuario, String passwd) {
		super();
		this.idUsuario = idUsuario;
		this.passwd = passwd;
	}

	/**
	 * 
	 * @return idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * 
	 * @param idUsuario
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * 
	 * @return passwd
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * 
	 * @param passwd
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	/**
	 * 
	 * 
	 * @return idTrabajador
	 */
	public int getIdTrabajador() {
		return idTrabajador;
	}

	/**
	 * 
	 * @param idTrabajador
	 */
	public void setIdTrabajador(int idTrabajador) {
		this.idTrabajador = idTrabajador;
	}

	/**
	 * 
	 * @param idUser
	 * @param passwd
	 */
	public void setUserPass(String idUser, String passwd) {
		this.idUsuario = idUser;
		this.passwd = passwd;
	}

}
