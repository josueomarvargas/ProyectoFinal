package junit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modelo.clases.Promociona;
import modelo.dao.PromocionaDAO;

class PromocionaDAOTest {
	static Promociona promo = null;
	boolean estado;
	PromocionaDAO proDao = new PromocionaDAO();

	@Before
	static void setUpBeforeClass() throws Exception {
		promo = new Promociona();
		List<Integer> IdObra = new ArrayList<>();
		IdObra.add(14);
		promo.setIdObra(IdObra);
		List<Integer> IdPatrocinador = new ArrayList<>();
		IdPatrocinador.add(7);
		promo.setIdPatro(IdPatrocinador);

	}

	@After
	static void tearDownAfterClass() throws Exception {
	}

//	@Test
//	void testCreate() {
//		try {
//			estado = proDao.create(promo);
//
//			assertTrue(estado);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("Fallo SQL");
//		}
//	}
//
//	@Test
//	void testSearch() {
//		String[] id = { "14", "" };
//		try {
//			Promociona aux = proDao.search(id);
//
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
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testUpdate() {
//		try {
//			estado = proDao.update(promo);
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
	void testRemove() {
		String[] id = { "14", "7" };
		try {
			estado = proDao.remove(id);

			assertTrue(estado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo SQL");
		}

	}

}
