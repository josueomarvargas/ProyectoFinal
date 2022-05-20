package modelo.clases;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para guardar la relaci�n entre obra y equipamiento
 * 
 * @author Henrique Yeguo
 * @author Iker Aspiazu
 */
public class EquipObra {

	/** Lista que guardar� el id de las obras **/
	private List<Integer> idObra;
	/** Lista que guardar� el id de los equipamientos **/
	private List<Integer> idEquip;

	/**
	 * Constructor vac�o
	 **/
	public EquipObra() {
		super();
		this.idObra = new ArrayList<>();
		this.idEquip = new ArrayList<>();

	}

	/**
	 * 
	 * @return idObra
	 */
	public List<Integer> getIdObra() {
		return idObra;
	}

	/**
	 * 
	 * @param idObra
	 */
	public void setIdObra(List<Integer> idObra) {
		this.idObra = idObra;
	}

	/**
	 * 
	 * @return idEquip
	 */
	public List<Integer> getIdEquip() {
		return idEquip;
	}

	/**
	 * 
	 * @param idEquip
	 */
	public void setIdEquip(List<Integer> idEquip) {
		this.idEquip = idEquip;
	}

}
