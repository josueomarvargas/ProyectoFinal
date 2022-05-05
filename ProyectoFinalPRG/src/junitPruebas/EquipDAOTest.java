package junitPruebas;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modelo.clases.Director;
import modelo.clases.Equipamiento;
import modelo.dao.EquipDAO;

class EquipDAOTest {
	
	static Equipamiento equi = null;
	boolean estado;
	EquipDAO eqDao = new EquipDAO();

	@BeforeAll
		static void setUpBeforeClass() throws Exception {
			equi = new Equipamiento();
			equi.setIdEquip(8);
			equi.setTipo("Sony");
			equi.setNombre("Microfono");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testCreate() {
	//try {
		//		estado = eqDao.create(equi);

		//		assertTrue(estado);
		//	} catch (SQLException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			//	fail("Fallo SQL");
	//		}
	}

	@Test
	void testSearch() {
		try {
			Equipamiento aux = eqDao.search("1");
			System.out.println(aux.getTipo());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fail("Not yet implemented");
	}
	@Test
	void testReadAll() {
		try {
			Map<String, Equipamiento> map = eqDao.readAll();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		try {
			estado = eqDao.update(equi);

			assertTrue(estado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo SQL");
		}
	}

	@Test
	void testRemove() {
		String[] Equipamiento = {"10", "Microfono"};
		try {
			estado = eqDao.remove(Equipamiento);

			assertTrue(estado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo SQL");
		}
	}

}
