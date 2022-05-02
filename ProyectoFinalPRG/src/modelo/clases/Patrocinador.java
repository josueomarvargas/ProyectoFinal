package modelo.clases;

public class Patrocinador {

	private int idPatro;
	private String nombre;
	private int cantDinero;
	private String condicion;

	public Patrocinador() {
		super();
	}

	public Patrocinador(int idPatro, String nombre, int cantDinero, String condicion) {
		super();
		this.idPatro = idPatro;
		this.nombre = nombre;
		this.cantDinero = cantDinero;
		this.condicion = condicion;
	}

	public int getIdPatro() {
		return idPatro;
	}

	public void setIdPatro(int idPatro) {
		this.idPatro = idPatro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantDinero() {
		return cantDinero;
	}

	public void setCantDinero(int cantDinero) {
		this.cantDinero = cantDinero;
	}

	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

}
