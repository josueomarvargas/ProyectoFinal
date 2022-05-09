package controlador.utils.dao;

import controlador.interfaz.BDgeneric;

public class DAOSingleton {

	private static BDgeneric<?> singletondao;

	public static BDgeneric<?> getDao(DAOFactory dao, boolean changeDAO) {
		if (singletondao == null)
			singletondao = dao.getInstance();
		else if (changeDAO)
			singletondao = dao.getInstance();

		return singletondao;
	}

}
