package vistas.dao;

import java.util.Iterator;
import java.util.Map;

import modelo.clases.Equipamiento;
import modelo.clases.ObraAudiovisual;
import modelo.clases.Patrocinador;
import modelo.clases.Pelicula;
import modelo.clases.Serie;
import modelo.clases.Trabajador;

public abstract class ToObject2Darray {

	public static <K, V> Object[][] toObjectArray(Map<K, V> map, String tipo) {

		Object[][] array2D = null;
		Iterator<?> iter = map.values().iterator();
		ObraAudiovisual obra = null;
		int i = 0;
		switch (tipo) {
		case "peli":
			array2D = new Object[getMapObraLength(map)[0]][6];
			while (iter.hasNext()) {
				obra = (ObraAudiovisual) iter.next();
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
			while (iter.hasNext()) {
				obra = (ObraAudiovisual) iter.next();
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
			for (; iter.hasNext(); i++) {
				Equipamiento aux = (Equipamiento) iter.next();
				array2D[i][0] = aux.getIdEquip();
				array2D[i][1] = aux.getNombre();
				array2D[i][2] = aux.getTipo();
			}
			break;
		case "patrocinador":
			array2D = new Object[map.size()][4];
			for (; iter.hasNext(); i++) {
				Patrocinador aux = (Patrocinador) iter.next();
				array2D[i][0] = aux.getIdPatro();
				array2D[i][1] = aux.getNombre();
				array2D[i][2] = aux.getCantDinero();
				array2D[i][3] = aux.getCondicion();
			}
			break;
		case "trabajador":
			array2D = new Object[map.size()][9];
			for (; iter.hasNext(); i++) {
				Trabajador aux = (Trabajador) iter.next();
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
			break;
		case "obra":
			array2D = new Object[map.size()][6];
			for (; iter.hasNext(); i++) {
				ObraAudiovisual aux = (ObraAudiovisual) iter.next();
				array2D[i][0] = aux.getIdObra();
				array2D[i][1] = aux.getNombre();
				array2D[i][2] = aux.getDuracion();
				array2D[i][3] = aux.getFechaEstreno();
				array2D[i][4] = aux.getPresupuesto();
				array2D[i][5] = aux.getTipo();
			}
			break;
		}
		return array2D;
	}

	public static <K, V> int[] getMapObraLength(Map<K, V> map) {
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
