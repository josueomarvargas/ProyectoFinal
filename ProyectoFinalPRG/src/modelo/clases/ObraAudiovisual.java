package modelo.clases;

import java.time.LocalDate;

public class ObraAudiovisual {

	private int idObra;
	private String nombre;
	private int duracion;
	private LocalDate fechaEstreno;
	private int presupuesto;
	private String tipo;

	public ObraAudiovisual() {
		super();
	}

	public ObraAudiovisual(int idObra, String nombre, int duracion, LocalDate fechaEstreno, int presupuesto,
			String tipo) {
		super();
		this.idObra = idObra;
		this.nombre = nombre;
		this.duracion = duracion;
		this.fechaEstreno = fechaEstreno;
		this.presupuesto = presupuesto;
		this.tipo = tipo;
	}

	public int getIdObra() {
		return idObra;
	}

	public void setIdObra(int idObra) {
		this.idObra = idObra;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public LocalDate getFechaEstreno() {
		return fechaEstreno;
	}

	public void setFechaEstreno(LocalDate fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}

	public int getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(int presupuesto) {
		this.presupuesto = presupuesto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}