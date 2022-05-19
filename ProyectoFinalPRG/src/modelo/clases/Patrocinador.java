package modelo.clases;

public class Patrocinador {

	private int idPatro;
	private String nombre;
	private int cantDinero;
	private String condicion;
	private String imgPath;

	public Patrocinador() {
		super();
	}

	public Patrocinador(int idPatro, String nombre, int cantDinero, String condicion, String imgPath) {
		super();
		this.idPatro = idPatro;
		this.nombre = nombre;
		this.cantDinero = cantDinero;
		this.condicion = condicion;
		this.imgPath = imgPath;
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

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@Override
	public String toString() {
		return "Patrocinador [idPatro=" + idPatro + ", nombre=" + nombre + ", cantDinero=" + cantDinero + ", condicion="
				+ condicion + ", imgPath=" + imgPath + "]";
	}

	
}
