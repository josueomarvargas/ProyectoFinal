package controlador.utils.dao;

import modelo.dao.EquipDAO;
import modelo.dao.EquipObraDAO;
import modelo.dao.ObraDAO;
import modelo.dao.ParticipaDAO;
import modelo.dao.PatrocinadorDAO;
import modelo.dao.PromocionaDAO;
import modelo.dao.TrabajadorDAO;
import modelo.dao.UserDAO;
import modelo.dao.ViewPeliDAO;
import modelo.dao.ViewSerieDAO;
import vistas.dao.CheckLogin;
import vistas.dao.GetData;
import vistas.dao.UpdateData;

public class FactoryDAO {

	private static EquipDAO equip;
	private static EquipObraDAO equipObra;
	private static ObraDAO obra;
	private static ParticipaDAO participa;
	private static PatrocinadorDAO patrocinador;
	private static PromocionaDAO promociona;
	private static TrabajadorDAO trabajador;
	private static UserDAO usuario;
	private static ViewPeliDAO viewPeli;
	private static ViewSerieDAO viewSerie;
	private static CheckLogin checkLogin;
	private static GetData getData;
	private static UpdateData updateData;

	public static EquipDAO getEquip() {
		if (equip == null)
			equip = new EquipDAO();
		return equip;
	}

	public static EquipObraDAO getEquipObra() {
		if (equipObra == null)
			equipObra = new EquipObraDAO();
		return equipObra;
	}

	public static ObraDAO getObra() {
		if (obra == null)
			obra = new ObraDAO();
		return obra;
	}

	public static ParticipaDAO getParticipa() {
		if (participa == null)
			participa = new ParticipaDAO();
		return participa;
	}

	public static PatrocinadorDAO getPatrocinador() {
		if (patrocinador == null)
			patrocinador = new PatrocinadorDAO();
		return patrocinador;
	}

	public static PromocionaDAO getPromociona() {
		if (promociona == null)
			promociona = new PromocionaDAO();
		return promociona;
	}

	public static TrabajadorDAO getTrabajador() {
		if (trabajador == null)
			trabajador = new TrabajadorDAO();
		return trabajador;
	}

	public static UserDAO getUsuario() {
		if (usuario == null)
			usuario = new UserDAO();
		return usuario;
	}

	public static ViewPeliDAO getViewPeli() {
		if (viewPeli == null)
			viewPeli = new ViewPeliDAO();
		return viewPeli;
	}

	public static ViewSerieDAO getViewSerie() {
		if (viewSerie == null)
			viewSerie = new ViewSerieDAO();
		return viewSerie;
	}

	public static CheckLogin getCheckLogin() {
		if (checkLogin == null)
			checkLogin = new CheckLogin();
		return checkLogin;
	}

	public static GetData getGetData() {
		if (getData == null)
			getData = new GetData();
		return getData;
	}
	
	public static UpdateData getUpdateData() {
		if (getUpdateData() == null) {
			updateData = new UpdateData();
		}
		return updateData;
	}

}
