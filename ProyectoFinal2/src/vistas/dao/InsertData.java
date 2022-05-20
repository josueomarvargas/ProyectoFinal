package vistas.dao;

import controlador.interfaz.DataManager;
import controlador.utils.dao.FactoryDAO;
import modelo.clases.Equipamiento;
import modelo.clases.ObraAudiovisual;
import modelo.clases.Patrocinador;
import modelo.clases.Trabajador;

public class InsertData implements DataManager<Object, Boolean> {

	@Override
	public Boolean dataManage(Object clase) {
		if (clase instanceof ObraAudiovisual) {
			return FactoryDAO.getObra().create((ObraAudiovisual) clase);
		} else if (clase instanceof Trabajador) {
			return FactoryDAO.getTrabajador().create((Trabajador) clase);
		} else if (clase instanceof Patrocinador) {
			return FactoryDAO.getPatrocinador().create((Patrocinador) clase);
		} else if (clase instanceof Equipamiento) {
			return FactoryDAO.getEquip().create((Equipamiento) clase);
		}
		return false;
	}

}