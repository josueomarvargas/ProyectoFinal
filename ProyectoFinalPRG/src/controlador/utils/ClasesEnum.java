package controlador.utils;

public enum ClasesEnum {
	PELICULA("peli"), SERIE("serie"), OBRA("obra"), EQUIPAMIENTO("equipamiento"), PATROCINADOR("patrocinador"),
	TRABAJADOR("trabajador");
	
	private String name;
	
	private ClasesEnum(String value){
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
}
