package modelo.clases;

import java.util.ArrayList;
import java.util.List;

public class Actor extends Trabajador {

	private List<String> especialidades;

	public Actor() {
		super();
		especialidades = new ArrayList<>();
	}

	public List<String> getEspecialidades() {
		return especialidades;
	}

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
