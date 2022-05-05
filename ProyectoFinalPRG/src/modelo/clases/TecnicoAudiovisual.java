package modelo.clases;

import java.util.ArrayList;
import java.util.List;

public class TecnicoAudiovisual extends Trabajador {

	private List<String> areaTrabajos;

	public TecnicoAudiovisual() {
		super();
		areaTrabajos = new ArrayList<>();
	}

	public List<String> getAreaTrabajos() {
		return areaTrabajos;
	}

	public void setAreaTrabajos(List<String> areaTrabajos) {
		this.areaTrabajos = areaTrabajos;
	}

	@Override
	public List<String> getList() {
		return areaTrabajos;
	}

}
