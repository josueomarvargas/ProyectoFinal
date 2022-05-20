package modelo.clases;

import java.time.LocalDate;

/**
 * Clase abstracta para guardar los datos de la obra audiovisual
 * 
 * @author Henrique Yeguo
 * @author Iker Aspiazu
 */
public abstract class ObraAudiovisual {

	/** Id de la obra **/
	private int idObra;
	/** Nombre de la obra **/
	private String nombre;
	/** Duración de la obra **/
	private int duracion;
	/** Fecha de estreno **/
	private LocalDate fechaEstreno;
	/** Presupuesto de la obra **/
	private int presupuesto;
	/** Tipo de obra **/
	private String tipo;
	/** Ruta de la imagen **/
	private String imgPath;

	/**
	 * Set datos
	 * 
	 * @param idObra
	 * @param nombre
	 * @param duracion
	 * @param fechaEstreno
	 * @param presupuesto
	 * @param tipo
	 * @param imgPath
	 */
	public void setDatos(int idObra, String nombre, int duracion, LocalDate fechaEstreno, int presupuesto, String tipo,
			String imgPath) {
		this.idObra = idObra;
		this.nombre = nombre;
		this.duracion = duracion;
		this.fechaEstreno = fechaEstreno;
		this.presupuesto = presupuesto;
		this.tipo = tipo;
		this.imgPath = imgPath;
	}

	/**
	 * 
	 * @return idObra
	 */
	public int getIdObra() {
		return idObra;
	}

	/**
	 * 
	 * @param idObra
	 */
	public void setIdObra(int idObra) {
		this.idObra = idObra;
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
	 * @return duracion
	 */
	public int getDuracion() {
		return duracion;
	}

	/**
	 * 
	 * @param duracion
	 */
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	/**
	 * 
	 * @return fechaEstreno
	 */
	public LocalDate getFechaEstreno() {
		return fechaEstreno;
	}

	/**
	 * 
	 * @param fechaEstreno
	 */
	public void setFechaEstreno(LocalDate fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}

	/**
	 * 
	 * @return presupuesto
	 */
	public int getPresupuesto() {
		return presupuesto;
	}

	/**
	 * 
	 * @param presupuesto
	 */
	public void setPresupuesto(int presupuesto) {
		this.presupuesto = presupuesto;
	}

	/**
	 * 
	 * @return tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * 
	 * @param tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;

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
		return "idObra=" + idObra + ", nombre=" + nombre + ", duracion=" + duracion + ", fechaEstreno=" + fechaEstreno
				+ ", presupuesto=" + presupuesto + ", tipo=" + tipo + ", imgPath=" + imgPath;
	}

}
