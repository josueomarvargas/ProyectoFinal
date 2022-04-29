package modelo.clases;

import java.time.LocalDate;

public class Trabajador {

	private int idTrabajador;
	private String dni;
	private String nombre;
	private String apellido;
	private int numTel;
	private int numPremios;
	private String direccion;
	private String tipo;
	private LocalDate fechaNac;

	public Trabajador() {
		super();
	}

	public Trabajador(int idTrabajador, String dni, String nombre, String apellido, int numTel, int numPremios,
			String direccion, String tipo, LocalDate fechaNac) {
		super();
		this.idTrabajador = idTrabajador;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.numTel = numTel;
		this.numPremios = numPremios;
		this.direccion = direccion;
		this.tipo = tipo;
		this.fechaNac = fechaNac;
	}

	public int getIdTrabajador() {
		return idTrabajador;
	}

	public void setIdTrabajador(int idTrabajador) {
		this.idTrabajador = idTrabajador;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getNumTel() {
		return numTel;
	}

	public void setNumTel(int numTel) {
		this.numTel = numTel;
	}

	public int getNumPremios() {
		return numPremios;
	}

	public void setNumPremios(int numPremios) {
		this.numPremios = numPremios;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Trabajador [idTrabajador=" + idTrabajador + ", dni=" + dni + ", nombre=" + nombre + ", apellido="
				+ apellido + ", numTel=" + numTel + ", numPremios=" + numPremios + ", direccion=" + direccion
				+ ", fechaNac=" + fechaNac + ", tipo=" + tipo + "]";
	}

}
