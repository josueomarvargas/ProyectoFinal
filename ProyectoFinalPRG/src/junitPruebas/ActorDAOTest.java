package junitPruebas;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controlador.utils.DAOFactory;
import modelo.clases.Actor;
import modelo.clases.Trabajador;
import modelo.dao.TrabajadorDAO;

class ActorDAOTest {

	static Trabajador act = null;
	boolean estado;
	TrabajadorDAO aDao = (TrabajadorDAO) DAOFactory.TRABAJADOR.getInstance();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		act = new Actor();
		act.setIdTrabajador(10);
		act.setDni("17090623Y");
		act.setNombre("manu");
		act.setApellido("Garcia");
		act.setNumTel(688612456);
		act.setNumPremios(6);
		act.setDireccion("sarriena");
		act.setTipo("actor");
		act.setFechaNac(LocalDate.of(1997, 10, 20));
		List<String> especialidades = new ArrayList<>();
		especialidades.add("comedia");
		especialidades.add("doblaje");
		especialidades.add("terror");
		((Actor) act).setEspecialidades(especialidades);
	}

//	@Test
//	void testCreate() {
//
//		try {
//			estado = aDao.create(act);
//
//			assertTrue(estado);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("Fallo SQL");
//		}
//
//	}

//	@Test
//	void testSearch() {

//		try {
//			Trabajador aux = aDao.search("1");
//			
//			System.out.println(aux.toString());
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		fail("Not yet implemented");
//	}
//
	@Test
	void testReadAll() {
		try {
			Trabajador aux;
			Map<Integer, Trabajador> map = aDao.readAll();
//			System.out.println(map.get(2).toString());
			Iterator<Trabajador> iter = map.values().iterator();
			while (iter.hasNext()) {
				aux = iter.next();
				if(aux instanceof Actor) {
					System.out.println(((Actor) aux).toString());
					
				}
				
		act.toString();		
						
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail("Not yet implemented");
	}
//
//	@Test
//	void testUpdate() {
//		try {
//			estado = aDao.update(act);
//
//			assertTrue(estado);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("Fallo SQL");
//		}
//	}
//
//	@Test
//	void testRemove() {
//		String[] actor = { "1", "terror" };
//		try {
//			estado = aDao.remove(actor);
//
//			assertTrue(estado);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("Fallo SQL");
//		}
//	}

}
