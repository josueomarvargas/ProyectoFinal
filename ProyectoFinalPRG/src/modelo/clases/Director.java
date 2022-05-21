package modelo.clases;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para guardar los datos del director
 * 
 * 
 * @author Henrique Yeguo
 * @author Iker Aspiazu
 */
public class Director extends Trabajador {

	/** Categoria del director **/
	private String categoria;

	/** Constructor vacío **/
	public Director() {
		super();
	}

	/**
	 * 
	 * @return categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * 
	 * @param categoria
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public List<String> getList() {
		List<String> categ = new ArrayList<>();
		categ.add(this.categoria);
		return categ;
	}

}
