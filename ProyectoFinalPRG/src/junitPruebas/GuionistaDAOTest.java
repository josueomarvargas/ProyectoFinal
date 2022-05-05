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
import modelo.clases.Guionista;
import modelo.dao.DirectorDAO;
import modelo.dao.GuionistaDAO;

class GuionistaDAOTest {

	static Guionista gui = null;
	boolean estado;
	GuionistaDAO gDao = new GuionistaDAO();

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		gui = new Guionista();
		gui.setIdTrabajador(1);
		gui.setTipo("comedia");
		List<String> Guionista = new ArrayList<>();
		Guionista.add("comedia");
		gui.setTipoGuiones(Guionista);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testCreate() {

		//try {
			//estado = gDao.create(gui);

//			assertTrue(estado);
	//	} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
			//fail("Fallo SQL");
//		}
	}

	@Test
	void testSearch() {
		try {
			Guionista aux = gDao.search("1");
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
			Map<String, Guionista> map = gDao.readAll();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		try {
			estado = gDao.update(gui);

			assertTrue(estado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo SQL");
		}
	}

	@Test
	void testRemove() {
		String[] Guionista = {"1", "terror"};
		try {
			estado = gDao.remove(Guionista);

			assertTrue(estado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo SQL");
		}
	}

}
