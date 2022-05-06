package modelo.clases;

import java.time.LocalDate;

public class ViewSerie {

	private int id;
	private String nombre;
	private String director;
	private String guionista;
	private int numTrabajadores;
	private int presupuesto;
	private LocalDate fechaEstreno;
	private int temporadas;
	private int capitulos;

	public ViewSerie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ViewSerie(int id, String nombre, String director, String guionista, int numTrabajadores, int presupuesto,
			LocalDate fechaEstreno, int temporadas, int capitulos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.director = director;
		this.guionista = guionista;
		this.numTrabajadores = numTrabajadores;
		this.presupuesto = presupuesto;
		this.fechaEstreno = fechaEstreno;
		this.temporadas = temporadas;
		this.capitulos = capitulos;
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

	public int getTemporadas() {
		return temporadas;
	}

	public void setTemporadas(int temporadas) {
		this.temporadas = temporadas;
	}

	public int getCapitulos() {
		return capitulos;
	}

	public void setCapitulos(int capitulos) {
		this.capitulos = capitulos;
	}

	@Override
	public String toString() {
		return "ViewSerie [id=" + id + ", nombre=" + nombre + ", director=" + director + ", guionista=" + guionista
				+ ", numTrabajadores=" + numTrabajadores + ", presupuesto=" + presupuesto + ", fechaEstreno="
				+ fechaEstreno + ", temporadas=" + temporadas + ", capitulos=" + capitulos + "]";
	}

}
