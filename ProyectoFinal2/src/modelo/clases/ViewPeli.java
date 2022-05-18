package modelo.clases;

import java.time.LocalDate;

public class ViewPeli {

	private int id;
	private String nombre;
	private String director;
	private String guionista;
	private int numTrabajadores;
	private int presupuesto;
	private LocalDate fechaEstreno;
	private String esTaquillero;

	public ViewPeli() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ViewPeli(int id, String nombre, String director, String guionista, int numTrabajadores, int presupuesto,
			LocalDate fechaEstreno, String esTaquillero) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.director = director;
		this.guionista = guionista;
		this.numTrabajadores = numTrabajadores;
		this.presupuesto = presupuesto;
		this.fechaEstreno = fechaEstreno;
		this.esTaquillero = esTaquillero;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getGuionista() {
		return guionista;
	}

	public void setGuionista(String guionista) {
		this.guionista = guionista;
	}

	public int getNumTrabajadores() {
		return numTrabajadores;
	}

	public void setNumTrabajadores(int numTrabajadores) {
		this.numTrabajadores = numTrabajadores;
	}

	public int getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(int presupuesto) {
		this.presupuesto = presupuesto;
	}

	public LocalDate getFechaEstreno() {
		return fechaEstreno;
	}

	public void setFechaEstreno(LocalDate fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}

	public String getEsTaquillero() {
		return esTaquillero;
	}

	public void setEsTaquillero(String esTaquillero) {
		this.esTaquillero = esTaquillero;
	}

	@Override
	public String toString() {
		return "ViewPeli [id=" + id + ", nombre=" + nombre + ", director=" + director + ", guionista=" + guionista
				+ ", numTrabajadores=" + numTrabajadores + ", presupuesto=" + presupuesto + ", fechaEstreno="
				+ fechaEstreno + ", esTaquillero=" + esTaquillero + "]";
	}

}
