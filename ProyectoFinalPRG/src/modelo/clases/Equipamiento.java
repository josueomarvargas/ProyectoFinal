package modelo.clases;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para guardar los datos del equipamiento
 * 
 * 
 * @author Henrique Yeguo
 * @author Iker Aspiazu
 */
public class Equipamiento {

	/** ID del equipamiento **/
	private int idEquip;
	/** Nombre del equipamiento **/
	private String nombre;
	/** Tipo de equipamiento **/
	private String tipo;
	/** Ruta de la imagen **/
	private String imgPath;
	/** Lista de las caraterísticas del equipamiento **/
	private List<String> caracteristicas;

	/**
	 * Contrusctor vacío
	 */
	public Equipamiento() {
		super();
		caracteristicas = new ArrayList<>();
	}

	/**
	 * 
	 * @return idEquip
	 */
	public int getIdEquip() {
		return idEquip;
	}

	/**
	 * 
	 * @param idEquip
	 */
	public void setIdEquip(int idEquip) {
		this.idEquip = idEquip;
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
	 * @return lista de las caracteristicas
	 */
	public List<String> getCaracteristicas() {
		return caracteristicas;
	}

	/**
	 * 
	 * @param caracteristicas
	 */
	public void setCaracteristicas(List<String> caracteristicas) {
		this.caracteristicas = caracteristicas;
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

}
