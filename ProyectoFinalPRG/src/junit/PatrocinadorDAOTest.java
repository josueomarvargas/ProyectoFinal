package junit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modelo.clases.Patrocinador;
import modelo.dao.PatrocinadorDAO;

class PatrocinadorDAOTest {

	static Patrocinador patro = null;
	boolean estado;
	PatrocinadorDAO patDao = new PatrocinadorDAO();

	@Before
	static void setUpBeforeClass() throws Exception {
		patro = new Patrocinador();
		patro.setIdPatro(7);
		patro.setNombre("kas");
		patro.setCondicion("tres anuncios");
		patro.setCantDinero(3000000);

	}

	@After
	static void tearDownAfterClass() throws Exception {
	}
//
//	@Test
//	void testCreate() {
//		try {
//			estado = patDao.create(patro);
//			assertTrue(estado);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("Fallo SQL");
//		}
//	}

//	@Test
//	void testSearch() {
//		String [] id = {"7"};
//		try {
//		Patrocinador aux = patDao.search(id);
//			System.out.println(aux.getCantDinero());
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
//			Patrocinador aux;
//		
//			Map<Integer, Patrocinador> map = patDao.readAll();
//			Iterator<Patrocinador> iter = map.values().iterator();
//			while (iter.hasNext()) {
//				aux = iter.next();
//				System.out.println(((Patrocinador) aux).toString());
//			}
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		fail("Not yet implemented");
//	}

//	@Test
//	void testUpdate() {
//		try {
//			estado = patDao.update(patro);
//
//			assertTrue(estado);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("Fallo SQL");
//		}
//	}

	@Test
	void testRemove() {
		String[] id = { "7" };
		try {
			estado = patDao.remove(id);

			assertTrue(estado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo SQL");
		}

	}

}
