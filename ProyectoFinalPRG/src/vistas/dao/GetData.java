package vistas.dao;

import java.util.Map;

import controlador.interfaz.DataManager;
import controlador.utils.dao.FactoryDAO;
import modelo.clases.Equipamiento;
import modelo.clases.ObraAudiovisual;
import modelo.clases.Patrocinador;
import modelo.clases.Trabajador;

/**
 * Clase que implementa la interfaz {@link UIcontrol} para recoger los datos de
 * una tabla y transformarlo a un array de dos dimensiones facilitar el
 * rellenado de información en los JTables.
 * 
 * 
 * @author Henrique Yeguo
 */
public class GetData implements DataManager<String, Object[][]> {

	/**
	 * Estos atributos guardarán maps con la información completa de las tablas,
	 * esto será util cuando se necesite enseñar todos los datos que no estan en la
	 * JTable.
	 **/
	private static Map<Integer, ObraAudiovisual> obra = null;
	private static Map<Integer, Equipamiento> equipamiento = null;
	private static Map<Integer, Patrocinador> patrocinador = null;
	private static Map<Integer, Trabajador> trabajador = null;

	/**
	 * Este método devolverá el array de objetos de dos dimensiones, los posibles
	 * datos que podrá devolver serán de las clases: {@link ObraAudiovisual},
	 * {@link Equipamiento}, {@link Patrocinador} y {@link Trabajador}.
	 * 
	 * @param en la lista que solo recogerá un valor, que será el nombre del objecto
	 *           que se quiere recuperar de la base de datos
	 **/
	@Override
	public Object[][] dataManage(String type) {

		// Guardar todos los datos de las obras
		if (type.equals("peli") || type.equals("serie")) {
			obra = FactoryDAO.getObra().readAll();
		}

		switch (type) {
		case "peli":
			return ToObject2Darray.toObjectArray(FactoryDAO.getObra().readAll(), "peli");
		case "serie":
			return ToObject2Darray.toObjectArray(FactoryDAO.getObra().readAll(), "serie");
		case "equipamiento":
			if (equipamiento == null)
				equipamiento = FactoryDAO.getEquip().readAll();
			return ToObject2Darray.toObjectArray(equipamiento, "equipamiento");

		case "patrocinador":
			if (patrocinador == null)
				patrocinador = FactoryDAO.getPatrocinador().readAll();
			return ToObject2Darray.toObjectArray(patrocinador, "patrocinador");

		case "trabajador":
			if (trabajador == null)
				trabajador = FactoryDAO.getTrabajador().readAll();
			return ToObject2Darray.toObjectArray(trabajador, "trabajador");
		}

		return null;
	}

	/**
	 * 
	 * @param clase nombre del objecto que se quiere recoger
	 * @return
	 */
	public static Map<Integer, ?> getDatos(String clase) {

		switch (clase) {
		case "obra":
			return obra;

		case "equipamiento":
			return equipamiento;

		case "patrocinador":
			return patrocinador;

		case "trabajador":
			return trabajador;
		default:
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static void setDatos(Map<Integer, ?> map, String clase) {

		switch (clase) {
		case "obra":
			obra = (Map<Integer, ObraAudiovisual>) map;

		case "equipamiento":
			equipamiento = (Map<Integer, Equipamiento>) map;

		case "patrocinador":
			patrocinador = (Map<Integer, Patrocinador>) map;

		case "trabajador":
			trabajador = (Map<Integer, Trabajador>) map;

		}
	}

}
