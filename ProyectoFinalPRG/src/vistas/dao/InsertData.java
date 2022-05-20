package vistas.dao;

import controlador.interfaz.DataManager;
import controlador.utils.dao.FactoryDAO;
import modelo.clases.Equipamiento;
import modelo.clases.ObraAudiovisual;
import modelo.clases.Patrocinador;
import modelo.clases.Trabajador;
import modelo.clases.Usuario;

/**
 * Clase que implementa la interfaz {@link DataManager}, que se utilizará para
 * insertar datos.
 *
 * @author yeguo
 */
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
		} else if (clase instanceof Usuario) {
			return FactoryDAO.getUsuario().create((Usuario) clase);
		}
		return false;
	}

}
