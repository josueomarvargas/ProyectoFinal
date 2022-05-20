package junit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import controlador.utils.dao.FactoryDAO;
import modelo.clases.Director;
import modelo.clases.Trabajador;
import modelo.dao.TrabajadorDAO;

class DirectorDAOTest {

	static Trabajador dir = null;
	boolean estado;
	TrabajadorDAO dDao = (TrabajadorDAO) FactoryDAO.getTrabajador();

	@Before
	static void setUpBeforeClass() throws Exception {
		dir = new Director();
		dir.setIdTrabajador(19);
		dir.setNombre("fjksef78y4r2487234747fuherf34758uy34qfjer89'");
		dir.setApellido("Perez");
		dir.setNumTel(1111111111);
		dir.setNumPremios(6);
		dir.setDireccion("Bergara");
		dir.setTipo("director");
		dir.setFechaNac(LocalDate.of(0000, 10, 20));

		((Director) dir).setCategoria("comedia");
	}

//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//	}
//
//	@Test
//	void testCreate() {
//		try {
//		estado = dDao.create(dir);
//
//			assertTrue(estado);
//		} catch (Exception e) {
//		// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("Fallo SQL");
//		}
//	}

//	@Test
//	void testSearch() {
//		String[] id = {"14"};
//		try {
//			Director aux = (Director) dDao.search(id);
//			System.out.println(aux.toString());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		fail("Not yet implemented");
//	}

//	@Test
//	void testReadAll() {
//		try {
//			Trabajador aux;
//			Map<Integer, Trabajador> map = dDao.readAll();
//			System.out.println(map.get(1).toString());
//			Iterator<Trabajador> iter = map.values().iterator();
//			while (iter.hasNext()) {
//				aux = iter.next();
//				if(aux instanceof Director) {
//					System.out.println(((Director) aux).toString());
//					
//				}
//				
//		dir.toString();		
//						
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		fail("Not yet implemented");
//	}

//	@Test
//	void testUpdate() {
//		try {
//			estado = dDao.update(dir);
//
//			assertTrue(estado);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("Fallo SQL");
//		}
//	}
//
	@Test
	void testRemove() {
		String[] id = { "11" };
		try {
			estado = dDao.remove(id);

			assertTrue(estado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail("Fallo SQL");
	}
}
