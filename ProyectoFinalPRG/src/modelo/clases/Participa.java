package modelo.clases;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase donde se guarda la relación entre trabajador y obra
 * 
 * @author Iker Aspiazu
 * @author Henrique yeguo
 *
 */
public class Participa {

	/** Lista donde se guarda las IDs de los trabajadores **/
	private List<Integer> idTrabajador;
	/** Lista donde se guarda las IDs de las obras **/
	private List<Integer> idObra;

	/**
	 * Constructor vacio que inicializa las listas a arraylists
	 */
	public Participa() {
		super();
		this.idTrabajador = new ArrayList<>();
		this.idObra = new ArrayList<>();
	}

	/**
	 * @return devolver la lista de las IDs de los trabajadores
	 */
	public List<Integer> getIdTrabajador() {
		return idTrabajador;
	}

	/**
	 * @param idTrabajador lista con los IDs de los trabajadores
	 */
	public void setIdTrabajador(List<Integer> idTrabajador) {
		this.idTrabajador = idTrabajador;
	}

	/**
	 * @return devolver la lista de IDs de las obras
	 */
	public List<Integer> getIdObra() {
		return idObra;
	}

	/**
	 * @param idObra lista con los IDs de las obras
	 */
	public void setIdObra(List<Integer> idObra) {
		this.idObra = idObra;
	}

	@Override
	public String toString() {
		return "Participa [idTrabajador=" + idTrabajador + ", idObra=" + idObra + "]";
	}

}
