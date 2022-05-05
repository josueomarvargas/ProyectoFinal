package junitPruebas;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modelo.clases.Director;
import modelo.clases.EquipObra;
import modelo.clases.Trabajador;
import modelo.dao.EquipObraDAO;
import modelo.dao.TrabajadorDAO;

class EquipObraDAOTest {
	
	static EquipObra eqo = null;
	boolean estado;
	EquipObraDAO eqoDao = new EquipObraDAO();


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		eqo = new EquipObra();
		eqo.setIdObra(10);
		List<Integer> Equipamiento = new ArrayList<>();
		Equipamiento.add(14);
		eqo.setIdEquip(Equipamiento);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testCreate() {
//		try {
//			estado = eqoDao.create(eqo);
//
//			assertTrue(estado);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("Fallo SQL");
//		}

	}

	@Test
	void testSearch() {
		try {
			EquipObra aux = eqoDao.search("1");
			System.out.println(aux.getIdEquip());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fail("Not yet implemented");
	}
	@Test
	void testReadAll() {
		try {
			Map<String, EquipObra> map = eqoDao.readAll();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail("Not yet implemented");
	}


	@Test
	void testUpdate() {
		try {
			estado = eqoDao.update(eqo);

			assertTrue(estado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo SQL");
		}
	}

	@Test
	void testRemove() {
		String[] EquipObra = {"10", "14"};
		try {
			estado = eqoDao.remove(EquipObra);

			assertTrue(estado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo SQL");
		}
	}
}
