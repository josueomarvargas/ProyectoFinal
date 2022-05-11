package vistas.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import controlador.interfaz.BDgeneric;
import controlador.interfaz.BDview;
import controlador.interfaz.UIcontrol;
import controlador.utils.dao.GenericFactory;
import modelo.clases.Equipamiento;
import modelo.clases.ObraAudiovisual;
import modelo.clases.Patrocinador;
import modelo.clases.Trabajador;
import modelo.clases.ViewPeli;
import modelo.clases.ViewSerie;

/**
 * Clase que implementa la interfaz {@link UIcontrol} para recoger los datos de
 * una tabla y transformarlo a un array de dos dimensiones facilitar el
 * rellenado de información en los JTables.
 * 
 * 
 * @author Henrique Yeguo
 */
public class GetData implements UIcontrol<Object[][]> {

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
	@SuppressWarnings("unchecked")
	@Override
	public Object[][] check(List<String> list) {

		BDgeneric<?> dao;
		BDview<?> viewDao;

		// Guardar todos los datos de las obras
		if (list.get(0).equals("peli") || list.get(0).equals("serie")) {
			dao = GenericFactory.OBRA.getInstance();
			obra = (Map<Integer, ObraAudiovisual>) dao.readAll();
		}

		switch (list.get(0)) {
		case "peli":
			viewDao = GenericFactory.VIEWPELIS.getInstanceView();
			Map<Integer, ViewPeli> peli = (Map<Integer, ViewPeli>) viewDao.callView();
			return toObjectArray(peli, "peli");
		case "serie":
			viewDao = (BDview<ViewSerie>) GenericFactory.VIEWSERIE.getInstanceView();
			Map<Integer, ViewSerie> serie = (Map<Integer, ViewSerie>) viewDao.callView();
			return toObjectArray(serie, "serie");

		case "equipamiento":
			dao = GenericFactory.EQUIP.getInstance();
			if (equipamiento == null)
				equipamiento = (Map<Integer, Equipamiento>) dao.readAll();
			return toObjectArray(equipamiento, "equipamiento");

		case "patrocinador":
			dao = GenericFactory.PATROCINADOR.getInstance();
			if (patrocinador == null)
				patrocinador = (Map<Integer, Patrocinador>) dao.readAll();
			return toObjectArray(patrocinador, "patrocinador");

		case "trabajador":
			dao = GenericFactory.TRABAJADOR.getInstance();
			if (trabajador == null)
				trabajador = (Map<Integer, Trabajador>) dao.readAll();
			return toObjectArray(trabajador, "trabajador");

		}

		return null;
	}

	/**
	 * 
	 * @param clase nombre del objecto que se quiere recoger
	 * @return
	 */
	public Map<Integer, ?> getDatos(String clase) {

		switch (clase) {
		case "obra":
			return obra;

		case "equipamiento":
			return equipamiento;

		case "patrocinador":
			return patrocinador;

		case "trabajador":
			return trabajador;
		}
		return null;
	}

	private <K, V> Object[][] toObjectArray(Map<K, V> map, String tipo) {

		Object[][] array2D = null;
		Iterator<?> iterObra;
		iterObra = map.values().iterator();

		switch (tipo) {
		case "peli":
			array2D = new Object[map.size()][8];
			for (int i = 0; iterObra.hasNext(); i++) {
				ViewPeli aux = (ViewPeli) iterObra.next();
				array2D[i][0] = aux.getId();
				array2D[i][1] = aux.getNombre();
				array2D[i][2] = aux.getDirector();
				array2D[i][3] = aux.getGuionista();
				array2D[i][4] = aux.getNumTrabajadores();
				array2D[i][5] = aux.getPresupuesto();
				array2D[i][6] = aux.getFechaEstreno();
				array2D[i][7] = aux.getEsTaquillero().equals("1") ? "Sí" : "No";
			}
			break;
		case "serie":
			array2D = new Object[map.size()][9];
			for (int i = 0; iterObra.hasNext(); i++) {
				ViewSerie aux = (ViewSerie) iterObra.next();
				array2D[i][0] = aux.getId();
				array2D[i][1] = aux.getNombre();
				array2D[i][2] = aux.getDirector();
				array2D[i][3] = aux.getGuionista();
				array2D[i][4] = aux.getNumTrabajadores();
				array2D[i][5] = aux.getPresupuesto();
				array2D[i][6] = aux.getFechaEstreno();
				array2D[i][7] = aux.getTemporadas();
				array2D[i][8] = aux.getCapitulos();
			}
			break;
		case "equipamiento":
			array2D = new Object[map.size()][3];
			for (int i = 0; iterObra.hasNext(); i++) {
				Equipamiento aux = (Equipamiento) iterObra.next();
				array2D[i][0] = aux.getIdEquip();
				array2D[i][1] = aux.getNombre();
				array2D[i][2] = aux.getTipo();
			}
			break;
		case "patrocinador":
			array2D = new Object[map.size()][3];
			for (int i = 0; iterObra.hasNext(); i++) {
				Patrocinador aux = (Patrocinador) iterObra.next();
				array2D[i][0] = aux.getIdPatro();
				array2D[i][1] = aux.getNombre();
				array2D[i][2] = aux.getCantDinero();
			}
			break;
		case "trabajador":
			array2D = new Object[map.size()][9];
			for (int i = 0; iterObra.hasNext(); i++) {
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

}
