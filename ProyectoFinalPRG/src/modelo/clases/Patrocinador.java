package modelo.clases;

/**
 * Clase para guardar los datos de los patrocinadores
 * 
 * @author yeguo
 * @author Iker Aspiazu
 *
 */
public class Patrocinador {

	/** Id del patrocinador **/
	private int idPatro;
	/** Nombre del patrocinador **/
	private String nombre;
	/** Cantidad de dinero que ha proporcionado el patrocinador **/
	private int cantDinero;
	/** La condición que ha establecido el patrocinador **/
	private String condicion;
	/** La ruta de la iagen **/
	private String imgPath;

	/**
	 * Constructor vacío
	 **/
	public Patrocinador() {
		super();
	}

	/**
	 * 
	 * @return id del patrocinador
	 */
	public int getIdPatro() {
		return idPatro;
	}

	/**
	 * 
	 * @param idPatro
	 */
	public void setIdPatro(int idPatro) {
		this.idPatro = idPatro;
	}

	/**
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * 
	 * @return cantidad de dinero
	 */
	public int getCantDinero() {
		return cantDinero;
	}

	/**
	 * 
	 * @param cantDinero
	 */
	public void setCantDinero(int cantDinero) {
		this.cantDinero = cantDinero;
	}

	/**
	 * 
	 * @return condicion
	 */
	public String getCondicion() {
		return condicion;
	}

	/**
	 * 
	 * @param condicion
	 */
	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	/**
	 * 
	 * @return ruta de la imagen
	 */
	public String getImgPath() {
		return imgPath;
	}

	/**
	 * 
	 * @param imgPath
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@Override
	public String toString() {
		return "Patrocinador [idPatro=" + idPatro + ", nombre=" + nombre + ", cantDinero=" + cantDinero + ", condicion="
				+ condicion + ", imgPath=" + imgPath + "]";
	}

}
