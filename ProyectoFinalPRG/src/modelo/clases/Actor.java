package modelo.clases;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para guardar los datos del trabajador
 * 
 * @author Henrique Yeguo
 * @author Iker Aspiazu
 */
public class Actor extends Trabajador {
	/** Lista de las especialidades **/
	private List<String> especialidades;

	/**
	 * Constructor vacío
	 */
	public Actor() {
		super();
		especialidades = new ArrayList<>();
	}

	/**
	 * 
	 * @return lista de las especialidades
	 */
	public List<String> getEspecialidades() {
		return especialidades;
	}

	/**
	 * 
	 * @param especialidades
	 */
	public void setEspecialidades(List<String> especialidades) {
		this.especialidades = especialidades;
	}

	@Override
	public String toString() {
		return super.toString() + ", especialidades=" + especialidades.toString();
	}

	@Override
	public List<String> getList() {
		return especialidades;
	}

}
