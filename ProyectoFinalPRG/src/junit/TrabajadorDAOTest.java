package junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import controlador.utils.dao.FactoryDAO;
import modelo.clases.Actor;
import modelo.clases.Trabajador;
import modelo.dao.TrabajadorDAO;

public class TrabajadorDAOTest {

	@Test
	public void testCreate() {
		/** Crea un usuario con la id 17 **/

		TrabajadorDAO aDao = (TrabajadorDAO) FactoryDAO.getTrabajador();
		Trabajador tr = new Actor();
		tr.setIdTrabajador(17);
		tr.setDni("17090623Y");
		tr.setNombre("manuel");
		tr.setApellido("Garcia");
		tr.setNumTel(688612456);
		tr.setNumPremios(6);
		tr.setDireccion("Loiu");
		tr.setTipo("actor");
		tr.setFechaNac(LocalDate.of(1997, 10, 20));
		List<String> especialidades = new ArrayList<>();
		especialidades.add("comedia");
		especialidades.add("doblaje");
		especialidades.add("terror");
		((Actor) tr).setEspecialidades(especialidades);
		boolean estado = aDao.create(tr);
		assertTrue(estado);
	}

	@Test
	public void testSearch() {
		/** Buscar el trabajador con la id 14 **/
		TrabajadorDAO aDao = (TrabajadorDAO) FactoryDAO.getTrabajador();
		Trabajador tr = new Actor();
		tr.setIdTrabajador(14);
		tr.setDni("17090623Y");
		tr.setNombre("manuel");
		tr.setApellido("Garcia");
		tr.setNumTel(688612456);
		tr.setNumPremios(6);
		tr.setDireccion("Loiu");
		tr.setTipo("actor");
		tr.setFechaNac(LocalDate.of(1997, 10, 20));
		List<String> especialidades = new ArrayList<>();
		especialidades.add("comedia");
		especialidades.add("doblaje");
		especialidades.add("terror");
		((Actor) tr).setEspecialidades(especialidades);
		String[] id = { "14" };
		Trabajador tr1 = aDao.search(id);
		assertEquals("Correcto", tr.getIdTrabajador(), tr1.getIdTrabajador());
	}

	@Test
	public void testReadAll() {
		/** Busca todos los trabajadores que hay **/

		boolean estado = false;
		TrabajadorDAO aDao = (TrabajadorDAO) FactoryDAO.getTrabajador();
		Map<Integer, Trabajador> map = aDao.readAll();
		Trabajador aux;
		Iterator<Trabajador> iter = map.values().iterator();
		while (iter.hasNext()) {
			estado = false;
			aux = iter.next();
			estado = true;

		}
		assertTrue(estado);
	}

	@Test
	public void testUpdate() {
		/** Modifica los datos de un trabajador **/

		TrabajadorDAO aDao = (TrabajadorDAO) FactoryDAO.getTrabajador();
		Trabajador tr = new Actor();
		tr.setIdTrabajador(14);
		tr.setDni("17090623Y");
		tr.setNombre("manuel");
		tr.setApellido("Garcia");
		tr.setNumTel(688612456);
		tr.setNumPremios(6);
		tr.setDireccion("Loiu");
		tr.setTipo("actor");
		tr.setFechaNac(LocalDate.of(1997, 10, 20));
		List<String> especialidades = new ArrayList<>();
		especialidades.add("comedia");
		especialidades.add("doblaje");
		especialidades.add("terror");
		((Actor) tr).setEspecialidades(especialidades);
		String[] id = { "14" };
		Trabajador tr1 = aDao.search(id);
		assertEquals("Correcto", tr.getIdTrabajador(), tr1.getIdTrabajador());
		boolean estado = aDao.update(tr);
		assertTrue(estado);
	}

	@Test
	public void testRemove() {
		/** Elimina el trabjador que tenga la id 14 **/

		String[] idTrabajador = { "14" };
		TrabajadorDAO aDao = (TrabajadorDAO) FactoryDAO.getTrabajador();

		boolean estado = aDao.remove(idTrabajador);

		assertTrue(estado);
	}

}
