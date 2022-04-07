package clases;

import java.time.LocalDate;
import java.util.List;

public class TecnicoAudiovisual extends Trabajador {

	private List<AreaTrabajo> areaTrabajos;

	public TecnicoAudiovisual() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TecnicoAudiovisual(int idTrabajador, String dni, String nombre, String apellido, int numTel, int numPremios,
			String direccion, LocalDate fechaNac, String tipo) {
		super(idTrabajador, dni, nombre, apellido, numTel, numPremios, direccion, fechaNac, tipo);
		// TODO Auto-generated constructor stub
	}

	public TecnicoAudiovisual(List<AreaTrabajo> areaTrabajos) {
		super();
		this.areaTrabajos = areaTrabajos;
	}

	public List<AreaTrabajo> getAreaTrabajos() {
		return areaTrabajos;
	}

	public void setAreaTrabajos(List<AreaTrabajo> areaTrabajos) {
		this.areaTrabajos = areaTrabajos;
	}

}
