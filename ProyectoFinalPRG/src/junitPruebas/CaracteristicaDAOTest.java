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
import modelo.clases.Equipamiento;
import modelo.dao.CaracteristicaDAO;
import modelo.dao.DirectorDAO;

class CaracteristicaDAOTest {

	static Equipamiento equipa = null;
	boolean estado;
	CaracteristicaDAO cDao = new CaracteristicaDAO();

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		equipa = new Equipamiento();
		equipa.setIdEquip(1);
		List<String> Caracteristicas = new ArrayList<>();
		Caracteristicas.add("4K");
		equipa.setCaracteristicas(Caracteristicas);

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testCreate() {
		try {
			estado = cDao.create(equipa);

			assertTrue(estado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo SQL");
		}
	}

	@Test
	void testSearch() {
		try {
		Equipamiento aux = cDao.search("1");
			System.out.println(aux.getCaracteristicas());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fail("Not yet implemented");
	}


	@Test
	void testReadAll() {
		try {
			Map<String, Equipamiento> map = cDao.readAll();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail("Not yet implemented");
	}
	@Test
	void testUpdate() {
		try {
			estado = cDao.update(equipa);

			assertTrue(estado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo SQL");
		}
	}

	@Test
	void testRemove() {
		//String[] Caracteristicas = {"2", "terror"};
		//try {
			//estado = cDao.remove(Caracteristicas);

//			assertTrue(estado);
	//	} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//	fail("Fallo SQL");
		}
	
	}


