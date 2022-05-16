package junitPruebas;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modelo.clases.Director;
import modelo.clases.Trabajador;
import modelo.clases.Usuario;
import modelo.dao.UserDAO;

class UserDAOTest {

	static Usuario usr = null;
	boolean estado;
	UserDAO uDao = new UserDAO();

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
			usr = new Usuario();
			usr.setIdUsuario("ManuLopez");
			usr.setPasswd("abcd*1234");
			usr.setIdTrabajador(5);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testCreate() {
		try {
			estado = uDao.create(usr);

		assertTrue(estado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo SQL");
		}
		
	}

//	@Test
//	void testSearch() {
//		String [] id = {"administrador"};
//		try {
//			Usuario aux = uDao.search(id);
//			System.out.println(aux.getPasswd());
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
//			Map<String, Usuario> map = uDao.readAll();
//			
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		fail("Not yet implemented");
//	}
//	@Test
//	void testUpdate() {
//		try {
//			estado = uDao.update(usr);
//			assertTrue(estado);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("Fallo SQL");
//		}
//	
//	}
//
//	@Test
//	void testRemove() {
//		String[] user = {"josue", "5"};
//		try {
//			estado = uDao.remove(user);
//
//			assertTrue(estado);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("Fallo SQL");
//		}
//	}

}
