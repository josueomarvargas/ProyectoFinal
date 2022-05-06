package controlador.utils;

import java.util.function.Supplier;

import controlador.interfaz.*;
import modelo.dao.*;

/**
 * <h1>Factoria de las clases</h1>
 * <p>
 * Esta enumeraci�n es una factoria para generar una instancia de las clases
 * DAO, usando expresiones lambda (ej: {@code Class::new}) para hacer referencia
 * a un m�todo que se encuentra en esa clase, en este caso se llama al
 * constructor. Usamos un Enum como factoria para facilitar la legibilidad del
 * c�digo ya que con el m�todo de un switch se crear�a bloque de c�digo largo y
 * facil de olvidar/equivocarse al usarlo.
 * <p>
 * Al llamar una enumeraci�n lo que har� es llamar al constructor privado que le
 * pasaremos la expresi�n lambda con el constructor para crear la instancia, el
 * Supplier es una interfaz que tiene el m�todo {@code T get()}, este m�todo nos
 * sirve para recoger esta expresi�n lambda, en este caso ser� el constructor, y
 * llamar al get para que nos devuelva el resultado, que es la instancia del
 * objecto que queremos.
 * <p>
 * Ej:
 * {@code Trabajador tDao = (Trabajador) DAOFactory.Trabajador.getInstance();}
 * esto lo que har� es devolvernos una instancia del
 * {@link modelo.dao.TrabajadorDAO TrabajadorDAO}
 * 
 * @see <a href=
 *      "https://stackoverflow.com/questions/17581310/using-enum-for-factory-in-java-a-best-practice">
 *      Usar Enum como factoria</a>
 * @see <a href="https://dzone.com/articles/java-lambda-method-reference">Java
 *      Lambda referencia a m�todo</a>
 * @see <a href=
 *      "https://www.geeksforgeeks.org/supplier-interface-in-java-with-examples/">Supplier
 *      Interface</a>
 *
 * @author Henrique Yeguo
 **/

public enum DAOFactory {
	EQUIP(EquipDAO::new), EQUIPOBRA(EquipObraDAO::new), OBRA(ObraDAO::new), PARTICIPA(ParticipaDAO::new),
	PATROCINADOR(PatrocinadorDAO::new), PROMOCIONA(PromocionaDAO::new), TRABAJADOR(TrabajadorDAO::new),
	USER(UserDAO::new), TRABAJADORUSER(TrabajadorUserDAO::new), VIEWPELIS(ViewPeliDAO::new),
	VIEWSERIE(ViewSerieDAO::new);

	private Supplier<?> instantiator;

	private DAOFactory(Supplier<?> instantiator) {
		this.instantiator = instantiator;
	}

	/**
	 * @return devuelve una instancia de una de las clases DAO que implementa la
	 *         interfaz {@code BDgeneric}
	 **/
	public BDgeneric<?> getInstance() {
		return (BDgeneric<?>) instantiator.get();
	}

	/**
	 * @return devuelve una instancia que implementa la interfaz {@code BDview}
	 **/
	public BDview<?> getInstanceView() {
		return (BDview<?>) instantiator.get();
	}

}
