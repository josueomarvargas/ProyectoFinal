package modelo.clases;

import java.time.LocalDate;
import java.util.List;

/**
 * Clase para guardar la información de los trabajadores
 * 
 * @author Henrique Yeguo
 * @author Iker Aspiazu
 **/

public abstract class Trabajador {

	/** id del trabajador **/
	private int idTrabajador;
	/** DNI del trabajador **/
	private String dni;
	/** Nombre del trabajador **/
	private String nombre;
	/** Apellido del trabajador **/
	private String apellido;
	/** Número de teléfono **/
	private int numTel;
	/** Número de premios **/
	private int numPremios;
	/** Dirección del trabajador **/
	private String direccion;
	/** Tipo de trabajaodre **/
	private String tipo;
	/** Fecha de naciemiento **/
	private LocalDate fechaNac;

	/**
	 * 
	 * @return idTrabajador
	 */
	public int getIdTrabajador() {
		return idTrabajador;
	}

	/**
	 * 
	 * @param idTrabajador
	 */
	public void setIdTrabajador(int idTrabajador) {
		this.idTrabajador = idTrabajador;
	}

	/**
	 * 
	 * @return dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * 
	 * @param dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * 
	 * @return apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * 
	 * @param apellido
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * 
	 * @return número de telefono
	 */
	public int getNumTel() {
		return numTel;
	}

	/**
	 * 
	 * @param numTel
	 */
	public void setNumTel(int numTel) {
		this.numTel = numTel;
	}

	/**
	 * 
	 * @return numero de premios
	 */
	public int getNumPremios() {
		return numPremios;
	}

	/**
	 * 
	 * @param numPremios
	 */
	public void setNumPremios(int numPremios) {
		this.numPremios = numPremios;
	}

	/**
	 * 
	 * @return dirección
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * 
	 * @param direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * 
	 * @return fecha de nacimiento
	 */
	public LocalDate getFechaNac() {
		return fechaNac;
	}

	/**
	 * 3
	 * 
	 * @param fechaNac
	 */
	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	/**
	 * 
	 * @return tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * 
	 * @param tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "idTrabajador=" + idTrabajador + ", dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", numTel=" + numTel + ", numPremios=" + numPremios + ", direccion=" + direccion + ", tipo=" + tipo
				+ ", fechaNac=" + fechaNac;
	}

	public void setDatos(int idTrabajador, String dni, String nombre, String apellido, int numTel, int numPremios,
			String direccion, String tipo, LocalDate fechaNac) {
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

	public List<String> getList() {
		return null;
	}

}
