package junitPruebas;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modelo.clases.Director;
import modelo.clases.Trabajador;
import modelo.dao.TrabajadorDAO;

class TrabajadorDAOTest {

	static Trabajador tr = null;
	boolean estado;
	TrabajadorDAO tDao = new TrabajadorDAO();

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		tr = new Trabajador();
		tr.setIdTrabajador(10);
		tr.setDni("17090623Y");
		tr.setNombre("manu");
		tr.setApellido("Garcia");
		tr.setNumTel(688612456);
		tr.setNumPremios(6);
		tr.setDireccion("sarriena");
		tr.setTipo("actor");
		tr.setFechaNac(LocalDate.of(1997, 10, 20));
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testCreate() {
	//	try {
	//		estado = tDao.create(tr);

	//		assertTrue(estado);
	//	} catch (SQLException e) {
			// TODO Auto-generated catch block
	//		e.printStackTrace();
	//		fail("Fallo SQL");
	//	}
	}

	@Test
	void testSearch() {
		try {
			Trabajador aux = tDao.search("1");
			System.out.println(aux.getDireccion());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fail("Not yet implemented");
	}

	@Test
	void testReadAll() {

		try {
			Map<String, Trabajador> map = tDao.readAll();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		try {
			estado = tDao.update(tr);

			assertTrue(estado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo SQL");
		}
	}

	@Test
	void testRemove() {
		String[] trabajador = {"4", "sonido"};
	try {
		estado = tDao.remove(trabajador);

		assertTrue(estado);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		fail("Fallo SQL");
	}
}

}
