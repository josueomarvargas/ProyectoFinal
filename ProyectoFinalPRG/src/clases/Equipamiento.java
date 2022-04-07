package clases;

import java.util.List;

public class Equipamiento {

	private int idEquip;
	private String nombre;
	private String tipo;
	private List<Caracteristica> caracteristicas;

	public Equipamiento() {
		super();
	}

	public Equipamiento(int idEquip, String nombre, String tipo, List<Caracteristica> caracteristicas) {
		super();
		this.idEquip = idEquip;
		this.nombre = nombre;
		this.tipo = tipo;
		this.caracteristicas = caracteristicas;
	}

	public int getIdEquip() {
		return idEquip;
	}

	public void setIdEquip(int idEquip) {
		this.idEquip = idEquip;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(List<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

}
