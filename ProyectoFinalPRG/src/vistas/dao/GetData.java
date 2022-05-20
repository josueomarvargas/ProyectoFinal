package vistas.dao;

import java.util.Iterator;
import java.util.Map;

import controlador.interfaz.DataManager;
import controlador.utils.dao.FactoryDAO;
import modelo.clases.Equipamiento;
import modelo.clases.ObraAudiovisual;
import modelo.clases.Patrocinador;
import modelo.clases.Pelicula;
import modelo.clases.Serie;
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
			return toObjectArray(FactoryDAO.getObra().readAll(), "peli");
		case "serie":
			return toObjectArray(FactoryDAO.getObra().readAll(), "serie");
		case "equipamiento":
			if (equipamiento == null)
				equipamiento = FactoryDAO.getEquip().readAll();
			return toObjectArray(equipamiento, "equipamiento");

		case "patrocinador":
			if (patrocinador == null)
				patrocinador = FactoryDAO.getPatrocinador().readAll();
			return toObjectArray(patrocinador, "patrocinador");

		case "trabajador":
			if (trabajador == null)
				trabajador = FactoryDAO.getTrabajador().readAll();
			return toObjectArray(trabajador, "trabajador");

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

	private <K, V> Object[][] toObjectArray(Map<K, V> map, String tipo) {

		Object[][] array2D = null;
		Iterator<?> iterObra = map.values().iterator();
		ObraAudiovisual obra = null;
		int i = 0;
		switch (tipo) {
		case "peli":
			array2D = new Object[getMapObraLength(map)[0]][6];
			while (iterObra.hasNext()) {
				obra = (ObraAudiovisual) iterObra.next();
				if (obra instanceof Pelicula) {
					Pelicula aux = (Pelicula) obra;
					array2D[i][0] = aux.getIdObra();
					array2D[i][1] = aux.getNombre();
					array2D[i][2] = aux.getDuracion();
					array2D[i][3] = aux.getFechaEstreno();
					array2D[i][4] = aux.getPresupuesto();
					array2D[i][5] = aux.isEsTaquillera() == true ? "Sí" : "No";
					i++;
				}
			}
			break;
		case "serie":
			array2D = new Object[getMapObraLength(map)[1]][6];
			while (iterObra.hasNext()) {
				obra = (ObraAudiovisual) iterObra.next();
				if (obra instanceof Serie) {
					Serie aux = (Serie) obra;
					array2D[i][0] = aux.getIdObra();
					array2D[i][1] = aux.getNombre();
					array2D[i][2] = aux.getDuracion();
					array2D[i][3] = aux.getFechaEstreno();
					array2D[i][4] = aux.getPresupuesto();
					array2D[i][5] = aux.getNombreCap().size();
					i++;
				}
			}
			break;
		case "equipamiento":
			array2D = new Object[map.size()][3];
			for (; iterObra.hasNext(); i++) {
				Equipamiento aux = (Equipamiento) iterObra.next();
				array2D[i][0] = aux.getIdEquip();
				array2D[i][1] = aux.getNombre();
				array2D[i][2] = aux.getTipo();
			}
			break;
		case "patrocinador":
			array2D = new Object[map.size()][4];
			for (; iterObra.hasNext(); i++) {
				Patrocinador aux = (Patrocinador) iterObra.next();
				array2D[i][0] = aux.getIdPatro();
				array2D[i][1] = aux.getNombre();
				array2D[i][2] = aux.getCantDinero();
				array2D[i][3] = aux.getCondicion();
			}
			break;
		case "trabajador":
			array2D = new Object[map.size()][9];
			for (; iterObra.hasNext(); i++) {
				Trabajador aux = (Trabajador) iterObra.next();
				array2D[i][0] = aux.getIdTrabajador();
				array2D[i][1] = aux.getDni();
				array2D[i][2] = aux.getNombre();
				array2D[i][3] = aux.getApellido();
				array2D[i][4] = aux.getNumTel();
				array2D[i][5] = aux.getNumPremios();
				array2D[i][6] = aux.getDireccion();
				array2D[i][7] = aux.getTipo();
				array2D[i][8] = aux.getFechaNac();
			}
		}
		return array2D;
	}

	private <K, V> int[] getMapObraLength(Map<K, V> map) {
		Iterator<?> iterObra = map.values().iterator();
		int[] size = { 0, 0 };
		while (iterObra.hasNext()) {
			ObraAudiovisual obra = (ObraAudiovisual) iterObra.next();
			if (obra instanceof Pelicula)
				size[0] += 1;
			else
				size[1] += 1;
		}
		return size;
	}

}
