package controlador.utils;

/**
 * Esta enumeraci�n guarda los nombres de las clases que necesitamos, esto nos
 * sirve nos ayuda a que si necesitamos el nombre solo no nos equivoquemos al
 * escribirlo.
 * 
 * @author Henrique Yeguo
 *
 */
public enum ClasesEnum {
	PELICULA("peli"), SERIE("serie"), OBRA("obra"), EQUIPAMIENTO("equipamiento"), PATROCINADOR("patrocinador"),
	TRABAJADOR("trabajador"), EQUIPOBRA("equipObra"), PARTICIPA("participa"), PROMOCIONA("promociona");

	/**
	 * La variable donde se guarda momentariamente el valor de la enumeraci�n
	 **/
	private String name;

	/**
	 * 
	 * @param value el valor que contiene la enumeraci�n
	 */
	private ClasesEnum(String value) {
		this.name = value;
	}

	/**
	 * Al llamar este m�todo el constructor guardar� el valor de la enumeraci�n al
	 * atributo, y lo podremos recoger ese valor devolverlo por el return.
	 * 
	 * @return el valor que contiene la enumeraci�n
	 */
	public String getName() {
		return this.name;
	}
}
