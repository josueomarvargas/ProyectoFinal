package junit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modelo.clases.Participa;
import modelo.dao.ParticipaDAO;

class ParticipaDAOTest {

	static Participa par = null;
	boolean estado;
	ParticipaDAO parDao = new ParticipaDAO();

	@Before
	static void setUpBeforeClass() throws Exception {
		par = new Participa();
		List<Integer> trabajador = new ArrayList<>();
		trabajador.add(12);
		par.setIdTrabajador(trabajador);
		List<Integer> obra = new ArrayList<>();
		obra.add(14);
		par.setIdObra(obra);
	}

	@After
	static void tearDownAfterClass() throws Exception {
	}

//	@Test
//	void testCreate() {
//		try {
//			estado = parDao.create(par);
//
//			assertTrue(estado);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("Fallo SQL");
//		}
//
//	}

	@Test
	void testSearch() {
		String[] id = { "12", "" };
		try {
			Participa aux = parDao.search(id);

			System.out.println(aux.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fail("Not yet implemented");
	}

//	@Test
//	void testReadAll() {
//		try {
//			Participa aux;
//		
//			Map<Integer, Participa> map = parDao.readAll();
//			Iterator<Participa> iter = map.values().iterator();
//			while (iter.hasNext()) {
//				aux = iter.next();
//				System.out.println(((Participa) aux).toString());
//			}
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		fail("Not yet implemented");
//	}

	@Test
	void testUpdate() {
		try {
			estado = parDao.update(par);

			assertTrue(estado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo SQL");
		}
	}

	@Test
	void testRemove() {
		String[] id = { "12", "14" };
		try {
			estado = parDao.remove(id);

			assertTrue(estado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo SQL");
		}

	}

}
