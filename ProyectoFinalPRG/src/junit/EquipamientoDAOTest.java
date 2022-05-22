package junit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import modelo.clases.Equipamiento;
import modelo.dao.EquipDAO;

class EquipamientoDAOTest {

	static Equipamiento equipa = null;
	boolean estado;
	EquipDAO eqpDao = new EquipDAO();

	@Before
	static void setUpBeforeClass() throws Exception {
		equipa = new Equipamiento();
		equipa.setIdEquip(21);
		equipa.setNombre("sony");
		equipa.setTipo("Microfono");
		List<String> Caracteristicas = new ArrayList<>();
		Caracteristicas.add("24374");
		equipa.setCaracteristicas(Caracteristicas);

	}
//
//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//	}
//
//	@Test
//	void testCreate() {
//		try {
//			estado = eqpDao.create(equipa);
//
//			assertTrue(estado);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("Fallo SQL");
//		}
//	}

	@Test
	void testSearch() {
		String[] id = { "15" };
		try {
			Equipamiento aux = eqpDao.search(id);
			System.out.println(aux.getCaracteristicas());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fail("Not yet implemented");
	}

//	@Test
//	void testReadAll() {
//		try {
//			Equipamiento aux;
//		
//			Map<Integer, Equipamiento> map = eqpDao.readAll();
//			Iterator<Equipamiento> iter = map.values().iterator();
//			while (iter.hasNext()) {
//				aux = iter.next();
//				System.out.println(((Equipamiento) aux).toString());
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
			estado = eqpDao.update(equipa);

			assertTrue(estado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo SQL");
		}
	}

	@Test
	void testRemove() {
		String[] id = { "21" };
		try {
			estado = eqpDao.remove(id);

			assertTrue(estado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo SQL");
		}

	}
}
