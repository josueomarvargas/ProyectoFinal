package controlador.utils;

import java.util.function.Supplier;

import controlador.interfaz.BDgeneric;
import modelo.dao.*;

/**
 * <h1>Factoria de las clases</h1>
 * <p>
 * Esta enumeraci�n es una factoria para generar una instancia de las clases
 * DAO, usando expresiones lambda para hacer referencia a un m�todo que se
 * encuentra en esa clase.
 * 
 * 
 * @see <a href=
 *      "https://stackoverflow.com/questions/17581310/using-enum-for-factory-in-java-a-best-practice">
 *      Usar Enum como factoria</a>
 * @see <a href="https://dzone.com/articles/java-lambda-method-reference">Java
 *      Lambda referencia a m�todo</a>
 **/

public enum DAOFactory {
	ACTOR(ActorDAO::new), CARACTERISTICA(CaracteristicaDAO::new), DIRECTOR(DirectorDAO::new), EQUIP(EquipDAO::new),
	EQUIPOBRA(EquipObraDAO::new), GUIONISTA(GuionistaDAO::new), OBRA(ObraDAO::new), PARTICIPA(ParticipaDAO::new),
	PATROCINADOR(PatrocinadorDAO::new), PELI(PeliDAO::new), PROMOCIONA(PromocionaDAO::new), SERIE(SerieDAO::new),
	TECNICO(TecnicoDAO::new), TRABAJADOR(TrabajadorDAO::new), USER(UserDAO::new),
	TRABAJADORUSER(TrabajadorUserDAO::new);

	private Supplier<?> instantiator;

	private DAOFactory(Supplier<?> instantiator) {
		this.instantiator = instantiator;
	}

	public BDgeneric<?> getInstance() {
		return (BDgeneric<?>) instantiator.get();
	}

	public BDgeneric<?> getDAO(DAOFactory dao) {
		return dao.getInstance();
	}

}
