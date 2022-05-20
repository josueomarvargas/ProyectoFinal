package vistas.dao;

import java.util.HashMap;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import controlador.interfaz.DataManager;
import controlador.utils.ClasesEnum;
import controlador.utils.dao.FactoryDAO;
import modelo.clases.EquipObra;
import modelo.clases.Equipamiento;
import modelo.clases.ObraAudiovisual;
import modelo.clases.Participa;
import modelo.clases.Patrocinador;
import modelo.clases.Promociona;
import modelo.clases.Trabajador;

public class RelationData implements DataManager<String[], DefaultTableModel> {

	private static EquipObra equipObra;
	private static Participa participa;
	private static Promociona promociona;

	private static DefaultTableModel equipModel;
	private static DefaultTableModel obraModel;
	private static DefaultTableModel trabajadorModel;
	private static DefaultTableModel patroModel;

	private static final String[] columnObra = { "ID", "Nombre", "Duración", "Fecha Estreno", "Presupuesto", "Tipo" };;
	private static final String[] columnEquip = { "ID", "Nombre", "Tipo" };;
	private static final String[] columnTrabajador = { "ID", "DNI", "Nombre", "Apellido", "Num. Tel", "Num. Premios",
			"Dirección", "Tipo", "Fecha Nacimiento" };;
	private static final String[] columnPatrocinador = { "ID", "Nombre", "CantidadDinero", "Condicion" };;

	public static DefaultTableModel getEquipModel() {
		return equipModel;
	}

	public static DefaultTableModel getObraModel() {
		return obraModel;
	}

	public static DefaultTableModel getTrabajadorModel() {
		return trabajadorModel;
	}

	public static DefaultTableModel getPatroModel() {
		return patroModel;
	}

	/**
	 * 
	 * 
	 * 
	 * @param data index 0: que relacion entre clases se va buscar <br>
	 *             index 1: que clase usaremos para buscar <br>
	 *             index 2: identificador para buscar
	 **/
	@Override
	public DefaultTableModel dataManage(String[] data) {

		Map<Integer, ObraAudiovisual> obras = FactoryDAO.getObra().readAll();

		switch (data[0]) {
		case "equipObra":
			// En el caso de que busquemos las obra que usa un Equipamiento
			if (data[1].equalsIgnoreCase(ClasesEnum.EQUIPAMIENTO.getName())) {
				equipObra = FactoryDAO.getEquipObra().search(new String[] { data[2], "" });
				if (equipObra != null) {

					// Copiar el map
					Map<Integer, ObraAudiovisual> aux = new HashMap<>(obras);

					// Quitar al map los que ya tiene
					aux.keySet().removeAll(equipObra.getIdObra());

					// Crear la tabla con los datos sin los que ya está añadidos
					obraModel = new DefaultTableModel(ToObject2Darray.toObjectArray(aux, ClasesEnum.OBRA.getName()),
							columnObra);

					// Solo guardar con los datos que tiene
					obras.keySet().retainAll(equipObra.getIdObra());
					// Crear tabla
					return new DefaultTableModel(ToObject2Darray.toObjectArray(obras, ClasesEnum.OBRA.getName()),
							columnObra);
				}
			} else { // Buscar los equipamientos que usa una obra
				equipObra = FactoryDAO.getEquipObra().search(new String[] { "", data[2] });
				Map<Integer, Equipamiento> equips = FactoryDAO.getEquip().readAll();
				if (equipObra != null) {

					// Datos que no están en la relación
					Map<Integer, Equipamiento> aux = new HashMap<>(equips);
					aux.keySet().removeAll(equipObra.getIdEquip());
					equipModel = new DefaultTableModel(
							ToObject2Darray.toObjectArray(aux, ClasesEnum.EQUIPAMIENTO.getName()), columnEquip);
					// Datos que están en la relación
					equips.keySet().retainAll(equipObra.getIdEquip());
					return new DefaultTableModel(
							ToObject2Darray.toObjectArray(equips, ClasesEnum.EQUIPAMIENTO.getName()), columnEquip);
				} else {
					equipObra = new EquipObra();
					equipObra.getIdObra().add(Integer.parseInt(data[2]));
					equipModel = new DefaultTableModel(
							ToObject2Darray.toObjectArray(equips, ClasesEnum.EQUIPAMIENTO.getName()), columnEquip);
				}
			}
			break;
		case "participa":
			// Buscar las obras que participa un trabajador
			if (data[1].equalsIgnoreCase(ClasesEnum.TRABAJADOR.getName())) {
				participa = FactoryDAO.getParticipa().search(new String[] { data[2], "" });
				if (participa != null) {

					// Datos que no está en la relación
					Map<Integer, ObraAudiovisual> aux = new HashMap<>(obras);
					aux.keySet().removeAll(participa.getIdObra());
					obraModel = new DefaultTableModel(ToObject2Darray.toObjectArray(aux, ClasesEnum.OBRA.getName()),
							columnObra);

					// Datos que están en la relación
					obras.keySet().retainAll(participa.getIdObra());
					return new DefaultTableModel(ToObject2Darray.toObjectArray(obras, ClasesEnum.OBRA.getName()),
							columnObra);
				}
			} else {// Buscar los trabajadores que participa en una obra
				participa = FactoryDAO.getParticipa().search(new String[] { "", data[2] });
				Map<Integer, Trabajador> trab = FactoryDAO.getTrabajador().readAll();
				if (participa != null) {

					// Datos que no están en la relación
					Map<Integer, Trabajador> aux = new HashMap<>(trab);
					aux.keySet().removeAll(participa.getIdTrabajador());
					trabajadorModel = new DefaultTableModel(
							ToObject2Darray.toObjectArray(aux, ClasesEnum.TRABAJADOR.getName()), columnTrabajador);

					// Datos que están en la relación
					trab.keySet().retainAll(participa.getIdTrabajador());
					return new DefaultTableModel(ToObject2Darray.toObjectArray(trab, ClasesEnum.TRABAJADOR.getName()),
							columnTrabajador);
				} else {
					participa = new Participa();
					participa.getIdObra().add(Integer.parseInt(data[2]));
					trabajadorModel = new DefaultTableModel(
							ToObject2Darray.toObjectArray(trab, ClasesEnum.TRABAJADOR.getName()), columnTrabajador);
				}
			}
			break;
		case "promociona":
			// Buscar las obras que patrocina
			if (data[1].equalsIgnoreCase(ClasesEnum.PATROCINADOR.getName())) {
				promociona = FactoryDAO.getPromociona().search(new String[] { data[2], "" });
				if (promociona != null) {

					// Datos que no están en la relación
					Map<Integer, ObraAudiovisual> aux = new HashMap<>(obras);
					aux.keySet().removeAll(promociona.getIdObra());
					obraModel = new DefaultTableModel(ToObject2Darray.toObjectArray(aux, ClasesEnum.OBRA.getName()),
							columnObra);

					// Datos que está en la relación
					obras.keySet().retainAll(promociona.getIdObra());
					return new DefaultTableModel(ToObject2Darray.toObjectArray(obras, ClasesEnum.OBRA.getName()),
							columnObra);
				}
			} else {// Buscar los patrocinadores de una obra
				promociona = FactoryDAO.getPromociona().search(new String[] { "", data[2] });
				Map<Integer, Patrocinador> patro = FactoryDAO.getPatrocinador().readAll();
				if (promociona != null) {

					// Datos que no están en la relación
					Map<Integer, Patrocinador> aux = new HashMap<>(patro);
					aux.keySet().removeAll(promociona.getIdPatro());
					patroModel = new DefaultTableModel(
							ToObject2Darray.toObjectArray(aux, ClasesEnum.PATROCINADOR.getName()), columnPatrocinador);

					// Datos que está en la relación
					patro.keySet().retainAll(promociona.getIdPatro());
					return new DefaultTableModel(
							ToObject2Darray.toObjectArray(patro, ClasesEnum.PATROCINADOR.getName()),
							columnPatrocinador);
				} else {
					promociona = new Promociona();
					promociona.getIdObra().add(Integer.parseInt(data[2]));
					patroModel = new DefaultTableModel(
							ToObject2Darray.toObjectArray(patro, ClasesEnum.PATROCINADOR.getName()),
							columnPatrocinador);
				}
				break;
			}

		}
		if (obraModel == null) {
			obraModel = new DefaultTableModel(ToObject2Darray.toObjectArray(obras, ClasesEnum.OBRA.getName()),
					columnObra);
		}
		return null;

	}

	/**
	 * 
	 * @param data array con los datos necesarios para actualizar la relacion de las
	 *             tablas. Se deberá de pasar los datos de la siguiente manera:
	 *             <li>indice 0: que relación de tablas se quiere actualizar
	 *             <li>indice 1: la tabla que con el ID que se insertará
	 *             <li>indice 2: el ID nuevo que se quiere insertar
	 * 
	 * @return
	 */
	public boolean updateRelation(String[] data) {

		switch (data[0]) {
		case "equipObra":
			if (equipObra != null) {

				// Añadir el ID de la obra a la relación
				if (data[1].equalsIgnoreCase(ClasesEnum.EQUIPAMIENTO.getName())) {
					equipObra.getIdObra().add(Integer.parseInt(data[2]));
				} else { // Añadir el ID del equipamiento a la relación
					equipObra.getIdEquip().add(Integer.parseInt(data[2]));
				}
				return FactoryDAO.getEquipObra().update(equipObra);
			}
		case "participa":
			if (participa != null) {

				// Añadir el ID de la obra a la relación
				if (data[1].equalsIgnoreCase(ClasesEnum.PARTICIPA.getName())) {
					participa.getIdObra().add(Integer.parseInt(data[2]));
				} else {// Añadir el ID del trabajador a la relación
					participa.getIdTrabajador().add(Integer.parseInt(data[2]));
				}
				return FactoryDAO.getParticipa().update(participa);
			}
		case "promociona":
			if (promociona != null) {
				// Añadir el ID de la obra a la relación
				if (data[1].equalsIgnoreCase(ClasesEnum.PATROCINADOR.getName())) {
					promociona.getIdObra().add(Integer.parseInt(data[2]));
				} else {// Añadir patrocinador a una obra
					promociona.getIdPatro().add(Integer.parseInt(data[2]));
				}
				return FactoryDAO.getPromociona().update(promociona);
			}
		}
		return false;
	}

}
