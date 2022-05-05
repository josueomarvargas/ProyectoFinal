package junitPruebas;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modelo.clases.Actor;
import modelo.clases.Director;
import modelo.clases.ObraAudiovisual;
import modelo.clases.Trabajador;
import modelo.dao.ActorDAO;
import modelo.dao.ObraDAO;

class ObraDAOTest {
	

	static ObraAudiovisual obr = null;
	boolean estado;
	ObraDAO oDao = new ObraDAO();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		obr = new ObraAudiovisual();
		obr.setIdObra(30);
		obr.setDuracion(170);
		obr.setNombre("Señor de los anillos");
		obr.setFechaEstreno(LocalDate.of(2001, 10, 20));
		obr.setTipo("Pelicula");
		obr.setPresupuesto(600000000);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testCreate() {
		//try {
			//estado = oDao.create(obr);

//			assertTrue(estado);
	//	} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
			//fail("Fallo SQL");
	//	}
}

	@Test
	void testSearch() {
		try {
			ObraAudiovisual aux = oDao.search("6");
			System.out.println(aux.getDuracion());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fail("Not yet implemented");
	}

	@Test
	void testReadAll() {
		try {
			Map<String, ObraAudiovisual> map = oDao.readAll();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
			try {
				estado = oDao.update(obr);

				assertTrue(estado);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail("Fallo SQL");
			}
	}

	@Test
	void testRemove() {
		String[] ObraAudiovisual = {"30", "Señor de los anillos"};
		try {
			estado = oDao.remove(ObraAudiovisual);

			assertTrue(estado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo SQL");
		}
	}
}
