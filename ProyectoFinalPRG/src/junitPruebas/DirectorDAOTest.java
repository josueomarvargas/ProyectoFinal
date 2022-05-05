package junitPruebas;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modelo.clases.Actor;
import modelo.clases.Director;
import modelo.clases.Trabajador;
import modelo.dao.DirectorDAO;

class DirectorDAOTest {

	static Director dir = null;
	boolean estado;
	DirectorDAO dDao = new DirectorDAO();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		dir = new Director();
		dir.setIdTrabajador(1);
		dir.setCategoria("comedia");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testCreate() {
//		try {
//			estado = dDao.create(dir);
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
			Director aux = dDao.search("7");
			System.out.println(aux.getCategoria());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fail("Not yet implemented");
	}

	@Test
	void testReadAll() {
		
		try {
			Map<String, Director> map = dDao.readAll();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
//		try {
//			estado = dDao.update(dir);
//
//			assertTrue(estado);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("Fallo SQL");
//		}
	}

	@Test
	void testRemove() {
		// try {
		// estado = dDao.remove("3");

//			assertTrue(estado);
		// } catch (SQLException e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		// fail("Fallo SQL");
		// }
	}
}
