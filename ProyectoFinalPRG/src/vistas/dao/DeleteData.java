package vistas.dao;

import controlador.interfaz.DataManager;
import controlador.utils.ClasesEnum;
import controlador.utils.dao.FactoryDAO;

/**
 * 
 *
 * @author Henrique Yeguo
 */
public class DeleteData implements DataManager<String[], Boolean> {

	@Override
	public Boolean dataManage(String[] data) {
		if (data[0].equalsIgnoreCase(ClasesEnum.OBRA.getName())) {
			return FactoryDAO.getObra().remove(new String[] { data[1] });
		} else if (data[0].equalsIgnoreCase(ClasesEnum.EQUIPAMIENTO.getName())) {
			return FactoryDAO.getEquip().remove(new String[] { data[1] });
		} else if (data[0].equalsIgnoreCase(ClasesEnum.PATROCINADOR.getName())) {
			return FactoryDAO.getPatrocinador().remove(new String[] { data[1] });
		} else if (data[0].equalsIgnoreCase(ClasesEnum.TRABAJADOR.getName())) {
			return FactoryDAO.getTrabajador().remove(new String[] { data[1] });
		}
		return false;
	}

}
