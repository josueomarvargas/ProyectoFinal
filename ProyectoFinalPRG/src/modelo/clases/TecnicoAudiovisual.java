package modelo.clases;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para guardar la información de los técnicos
 * 
 * 
 * @author Henrique Yeguo
 * @author Iker Aspiazu
 */
public class TecnicoAudiovisual extends Trabajador {

	/** Lista de las areas en las que trabaja **/
	private List<String> areaTrabajos;

	/**
	 * Constructor vacío
	 */
	public TecnicoAudiovisual() {
		super();
		areaTrabajos = new ArrayList<>();
	}

	/**
	 * 
	 * @return areaTrabajos
	 */
	public List<String> getAreaTrabajos() {
		return areaTrabajos;
	}

	/**
	 * 
	 * @param areaTrabajos
	 */
	public void setAreaTrabajos(List<String> areaTrabajos) {
		this.areaTrabajos = areaTrabajos;
	}

	@Override
	public List<String> getList() {
		return areaTrabajos;
	}

}
