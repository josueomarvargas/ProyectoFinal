package modelo.clases;

import java.util.ArrayList;
import java.util.List;

public class Director extends Trabajador {
	private String categoria;

	public Director() {
		super();
	}

	public String getCategoria() {
		return categoria;
	}

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
