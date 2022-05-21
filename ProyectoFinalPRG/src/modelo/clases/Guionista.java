package modelo.clases;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que guardará la información de los guionistas
 * 
 * @author Henrique Yeguo
 * @author Iker Aspiazu
 *
 */
public class Guionista extends Trabajador {

	/** Lista donde se guardará los tipos de guiones **/
	private List<String> tipoGuiones;

	/**
	 * Constructor privado
	 **/
	public Guionista() {
		super();
		tipoGuiones = new ArrayList<>();
	}

	/**
	 * 
	 * @return lista de los tipos de guiones
	 */
	public List<String> getTipoGuiones() {
		return tipoGuiones;
	}

	/**
	 * 
	 * @param tipoGuiones
	 */
	public void setTipoGuiones(List<String> tipoGuiones) {
		this.tipoGuiones = tipoGuiones;
	}

	@Override
	public List<String> getList() {
		return tipoGuiones;
	}

}
