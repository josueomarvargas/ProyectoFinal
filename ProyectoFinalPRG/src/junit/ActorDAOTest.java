package junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controlador.utils.dao.FactoryDAO;
import modelo.clases.Actor;
import modelo.clases.Trabajador;
import modelo.dao.TrabajadorDAO;

class ActorDAOTest {

	static Trabajador act;

	boolean estado=false;
	TrabajadorDAO aDao = (TrabajadorDAO) FactoryDAO.getTrabajador();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Trabajador act = new Actor();
		act.setIdTrabajador(14);
		act.setDni("17090623Y");
		act.setNombre("manuel");
		act.setApellido("Garcia");
		act.setNumTel(688612456);
		act.setNumPremios(6);
		act.setDireccion("Loiu");
		act.setTipo("actor");
		act.setFechaNac(LocalDate.of(1997, 10, 20));
		List<String> especialidades = new ArrayList<>();
		especialidades.add("comedia");
		especialidades.add("doblaje");
		especialidades.add("terror");
		((Actor) act).setEspecialidades(especialidades);


	}

	@Test
	void testCreate() {
	 /** Introducimos un nuevo trabajador de tipo actor**/
		act = new Actor();
		act.setIdTrabajador(14);
		act.setDni("17090623Y");
		act.setNombre("manuel");
		act.setApellido("Garcia");
		act.setNumTel(688612456);
		act.setNumPremios(6);
		act.setDireccion("Loiu");
		act.setTipo("actor");
		act.setFechaNac(LocalDate.of(1997, 10, 20));
		List<String> especialidades = new ArrayList<>();
		especialidades.add("comedia");
		especialidades.add("doblaje");
		especialidades.add("terror");
		((Actor) act).setEspecialidades(especialidades);


		estado = aDao.create(act);
		assertTrue(estado);
	}

	@Test
	void testSearch() {
		String [] id = {"14"};
			Trabajador aux = aDao.search(id);
			
			assertEquals("es correcto",act,aux);
		

	
		

	
	}

	@Test
	void testReadAll() {
			Trabajador aux;
			Map<Integer, Trabajador> map = aDao.readAll();
			Iterator<Trabajador> iter = map.values().iterator();
			while (iter.hasNext()) {
				estado=false;
				aux = iter.next();
				if(aux instanceof Actor) {
					assertTrue(estado);
				}


			}
		
	}

	@Test
	void testUpdate() {
		
			estado = aDao.update(act);
			assertTrue(estado);

	}

	@Test
	void testRemove() {
		String[] idTrabajador = { "14" };
		
			estado = aDao.remove(idTrabajador);

			assertTrue(estado);
	
	}

}
