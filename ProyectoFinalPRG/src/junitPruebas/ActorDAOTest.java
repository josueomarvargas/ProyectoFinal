package junitPruebas;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modelo.clases.Actor;
import modelo.clases.Director;
import modelo.dao.ActorDAO;

class ActorDAOTest {

	static Actor act = null;
	boolean estado;
	ActorDAO aDao = new ActorDAO();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		act = new Actor();
		act.setIdTrabajador(1);
		List<String> especialidades = new ArrayList<>();
		especialidades.add("comedia");
		especialidades.add("doblaje");
		especialidades.add("terror");
		act.setEspecialidades(especialidades);
	}

	@Test
	void testCreate() {

		try {
			estado = aDao.create(act);

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
			Actor aux = aDao.search("1");
			System.out.println(aux.getEspecialidades());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fail("Not yet implemented");
	}

	@Test
	void testReadAll() {
		try {
			Map<String, Actor> map = aDao.readAll();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail("Not yet implemented");
	}


	@Test
	void testUpdate() {
		try {
			estado = aDao.update(act);

			assertTrue(estado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo SQL");
		}
	}

	@Test
	void testRemove() {
		String[] actor = {"1", "terror"};
		try {
			estado = aDao.remove(actor);

			assertTrue(estado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo SQL");
		}
	}

}
