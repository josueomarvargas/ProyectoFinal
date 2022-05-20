package controlador.utils;

public enum ClasesEnum {
	PELICULA("peli"), SERIE("serie"), OBRA("obra"), EQUIPAMIENTO("equipamiento"), PATROCINADOR("patrocinador"),
	TRABAJADOR("trabajador"), EQUIPOBRA("equipObra"), PARTICIPA("participa"), PROMOCIONA("promociona");

	private String name;

	private ClasesEnum(String value) {
		this.name = value;
	}

	public String getName() {
		return this.name;
	}
}
