package controlador.utils.dao;

import modelo.dao.EquipDAO;
import modelo.dao.EquipObraDAO;
import modelo.dao.ObraDAO;
import modelo.dao.ParticipaDAO;
import modelo.dao.PatrocinadorDAO;
import modelo.dao.PromocionaDAO;
import modelo.dao.TrabajadorDAO;
import modelo.dao.UserDAO;
import vistas.dao.CheckLogin;
import vistas.dao.DeleteData;
import vistas.dao.GetData;
import vistas.dao.RelationData;
import vistas.dao.InsertData;
import vistas.dao.UpdateData;

/**
 * Esta factoria guardará todas las instancias de las implementaciones de las
 * interfaces que se necesitará a lo largo del programa.
 * 
 * 
 * @author Henrique Yeguo
 */
public class FactoryDAO {

	/**
	 * Variable donde se guarda la instancia de la implemntación {@code EquipDAO}
	 */
	private static EquipDAO equip;

	/**
	 * Variable donde se guarda la instancia de la implemntación
	 * {@code EquipObraDAO}
	 */
	private static EquipObraDAO equipObra;

	/** Variable donde se guarda la instancia de la implemntación {@code ObraDAO} */
	private static ObraDAO obra;

	/**
	 * Variable donde se guarda la instancia de la implemntación
	 * {@code ParticipaDAO}
	 */
	private static ParticipaDAO participa;

	/**
	 * Variable donde se guarda la instancia de la implemntación
	 * {@code PatrocinadorDAO}
	 */
	private static PatrocinadorDAO patrocinador;

	/**
	 * Variable donde se guarda la instancia de la implemntación
	 * {@code PromocionaDAO}
	 */
	private static PromocionaDAO promociona;

	/**
	 * Variable donde se guarda la instancia de la implemntación
	 * {@code TrabajadorDAO*
	 */
	private static TrabajadorDAO trabajador;

	/** Variable donde se guarda la instancia de la implemntación {@code UserDAO} */
	private static UserDAO usuario;

	/**
	 * Variable donde se guarda la instancia de la implemntación {@code CheckLogin}
	 */
	private static CheckLogin checkLogin;

	/** Variable donde se guarda la instancia de la implemntación {@code GetData} */
	private static GetData getData;

	/**
	 * Variable donde se guarda la instancia de la implemntación {@code UpdateData}
	 */
	private static UpdateData updateData;

	/**
	 * Variable donde se guarda la instancia de la implemntación {@code DeleteData}
	 */
	private static DeleteData deleteData;

	/**
	 * Variable donde se guarda la instancia de la implemntación {@code InsertData}
	 */
	private static InsertData insertData;

	/**
	 * Variable donde se guarda la instancia de la implemntación
	 * {@code RelationData}
	 */
	private static RelationData relationData;

	/**
	 * 
	 * @return devuelve la instancia del {@code EquipDAO}
	 */
	public static EquipDAO getEquip() {
		if (equip == null)
			equip = new EquipDAO();
		return equip;
	}

	/**
	 * 
	 * @return devuelve la instancia del {@code EquipObraDAO}
	 */
	public static EquipObraDAO getEquipObra() {
		if (equipObra == null)
			equipObra = new EquipObraDAO();
		return equipObra;
	}

	/**
	 * 
	 * @return devuelve la instancia del {@code ObraDAO}
	 */
	public static ObraDAO getObra() {
		if (obra == null)
			obra = new ObraDAO();
		return obra;
	}

	/**
	 * 
	 * @return devuelve la instancia del {@code ParticipaDAO}
	 */
	public static ParticipaDAO getParticipa() {
		if (participa == null)
			participa = new ParticipaDAO();
		return participa;
	}

	/**
	 * 
	 * @return devuelve la instancia del {@code PatrocinadorDAO}
	 */
	public static PatrocinadorDAO getPatrocinador() {
		if (patrocinador == null)
			patrocinador = new PatrocinadorDAO();
		return patrocinador;
	}

	/**
	 * 
	 * @return devuelve la instancia del {@code PromocionaDAO}
	 */
	public static PromocionaDAO getPromociona() {
		if (promociona == null)
			promociona = new PromocionaDAO();
		return promociona;
	}

	/**
	 * 
	 * @return devuelve la instancia del {@code TrabajadorDAO}
	 */
	public static TrabajadorDAO getTrabajador() {
		if (trabajador == null)
			trabajador = new TrabajadorDAO();
		return trabajador;
	}

	/**
	 * 
	 * @return devuelve la instancia del {@code UserDAO}
	 */
	public static UserDAO getUsuario() {
		if (usuario == null)
			usuario = new UserDAO();
		return usuario;
	}

	/**
	 * 
	 * @return devuelve la instancia del {@code CheckLogin}
	 */
	public static CheckLogin getCheckLogin() {
		if (checkLogin == null)
			checkLogin = new CheckLogin();
		return checkLogin;
	}

	/**
	 * 
	 * @return devuelve la instancia del {@code GetData}
	 */
	public static GetData getGetData() {
		if (getData == null)
			getData = new GetData();
		return getData;
	}

	/**
	 * 
	 * @return devuelve la instancia del {@code UpdateData}
	 */
	public static UpdateData getUpdateData() {
		if (updateData == null) {
			updateData = new UpdateData();
		}
		return updateData;
	}

	/**
	 * 
	 * @return devuelve la instancia del {@code DeleteData}
	 */
	public static DeleteData getDeleteData() {
		if (deleteData == null) {
			deleteData = new DeleteData();
		}
		return deleteData;
	}

	/**
	 * 
	 * @return devuelve la instancia del {@code InsertData}
	 */
	public static InsertData getInsertData() {
		if (insertData == null) {
			insertData = new InsertData();
		}
		return insertData;
	}

	/**
	 * 
	 * @return devuelve la instancia del {@code RelationData}
	 */
	public static RelationData getRelationData() {
		if (relationData == null) {
			relationData = new RelationData();
		}
		return relationData;
	}

}
