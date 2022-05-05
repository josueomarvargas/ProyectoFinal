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
			usr.setIdUsuario("mikinadal");
			usr.setPasswd("abcd*1234");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testCreate() {
		//try {
			//estado = uDao.create(usr);
//
	//		assertTrue(estado);
		//} catch (SQLException e) {
			//// TODO Auto-generated catch block
			//e.printStackTrace();
			//fail("Fallo SQL");
		//}
		
	}

	@Test
	void testSearch() {
		try {
			Usuario aux = uDao.search("lander.guarrotxena");
			System.out.println(aux.getPasswd());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fail("Not yet implemented");
	}

	@Test
	void testReadAll() {
		try {
			Map<String, Usuario> map = uDao.readAll();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail("Not yet implemented");
	}
	@Test
	void testUpdate() {
		try {
			estado = uDao.update(usr);
			assertTrue(estado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo SQL");
		}
	
	}

	@Test
	void testRemove() {
		String[] user = {"ikerperez ", "2"};
		try {
			estado = uDao.remove(user);

			assertTrue(estado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo SQL");
		}
	}

}
