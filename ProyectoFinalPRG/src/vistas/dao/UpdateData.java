package vistas.dao;

import controlador.interfaz.DataManager;
import controlador.utils.dao.FactoryDAO;
import modelo.clases.Equipamiento;
import modelo.clases.ObraAudiovisual;
import modelo.clases.Patrocinador;
import modelo.clases.Trabajador;

/**
 * Clase para actualizar los datos
 *
 * @author yeguo
 */
public class UpdateData implements DataManager<Object, Boolean> {

	@Override
	public Boolean dataManage(Object clase) {

		if (clase instanceof ObraAudiovisual) {
			return FactoryDAO.getObra().update((ObraAudiovisual) clase);
		} else if (clase instanceof Trabajador) {
			return FactoryDAO.getTrabajador().update((Trabajador) clase);
		} else if (clase instanceof Patrocinador) {
			return FactoryDAO.getPatrocinador().update((Patrocinador) clase);
		} else if (clase instanceof Equipamiento) {
			return FactoryDAO.getEquip().update((Equipamiento) clase);
		}
		return false;
	}

}
