package junit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modelo.clases.Usuario;
import modelo.dao.UserDAO;

class UserDAOTest {

	static Usuario usr = null;
	boolean estado;
	UserDAO uDao = new UserDAO();

	@Before
	static void setUpBeforeClass() throws Exception {
		usr = new Usuario();
		usr.setIdUsuario("ManuLopez");
		usr.setPasswd("abcd*1234");
		usr.setIdTrabajador(3);
	}

	@After
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
