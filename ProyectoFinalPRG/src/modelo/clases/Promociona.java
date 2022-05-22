package modelo.clases;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para guardar la relación entre el patrocinador y las obras
 * 
 * @author Henrique Yeguo
 * @author Iker Aspiazu
 */
public class Promociona {

	/** Lista para guardar el id de los patrocinadores **/
	private List<Integer> idPatro;
	/** Lista para guardar el id de las obras **/
	private List<Integer> idObra;

	/**
	 * Constructor vacío
	 **/
	public Promociona() {
		super();
		this.idPatro = new ArrayList<>();
		this.idObra = new ArrayList<>();

	}

	/**
	 * 
	 * @return idPatro
	 */
	public List<Integer> getIdPatro() {
		return idPatro;
	}

	/**
	 * 
	 * @param idPatro
	 */
	public void setIdPatro(List<Integer> idPatro) {
		this.idPatro = idPatro;
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

	@Override
	public String toString() {
		return "Promociona [idPatro=" + idPatro + ", idObra=" + idObra + "]";
	}

}
