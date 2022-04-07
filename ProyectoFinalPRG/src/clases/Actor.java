package clases;

import java.time.LocalDate;
import java.util.List;

public class Actor extends Trabajador {

	private List <Especialidad> especialidades;

	public Actor() {
		super();
	}

	public Actor(int idTrabajador, String dni, String nombre, String apellido, int numTel, int numPremios,
			String direccion, LocalDate fechaNac, String tipo) {
		super(idTrabajador, dni, nombre, apellido, numTel, numPremios, direccion, fechaNac, tipo);
		// TODO Auto-generated constructor stub
	}

	public Actor(List<Especialidad> especialidades) {
		super();
		this.especialidades = especialidades;
	}

	public List<Especialidad> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidad> especialidades) {
		this.especialidades = especialidades;
	}

	
	
	
}
