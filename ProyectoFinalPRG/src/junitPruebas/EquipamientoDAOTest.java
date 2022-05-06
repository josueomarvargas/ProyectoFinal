package junitPruebas;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modelo.clases.Actor;
import modelo.clases.Director;
import modelo.clases.Equipamiento;
import modelo.clases.Trabajador;
import modelo.dao.CaracteristicaDAO;
import modelo.dao.EquipDAO;


class EquipamientoDAOTest {

	static Equipamiento equipa = null;
	boolean estado;
	EquipDAO eqpDao = new EquipDAO();

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		equipa = new Equipamiento();
		equipa.setIdEquip(19);
		List<String> Caracteristicas = new ArrayList<>();
		Caracteristicas.add("8K");
		equipa.setCaracteristicas(Caracteristicas);

	}
//
//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//	}

//	@Test
//	void testCreate() {
//		try {
//			estado = eqpDao.create(equipa);
//
//			assertTrue(estado);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("Fallo SQL");
//		}
//	}

//	@Test
//	void testSearch() {
//		try {
//		Equipamiento aux = eqpDao.search("14");
//			System.out.println(aux.getCaracteristicas());
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
//			Equipamiento aux;
//		
//			Map<Integer, Equipamiento> map = eqpDao.readAll();
//			Iterator<Equipamiento> iter = map.values().iterator();
//			while (iter.hasNext()) {
//				aux = iter.next();
//				System.out.println(((Equipamiento) aux).toString());
//			}
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		fail("Not yet implemented");
//	}
	@Test
	void testUpdate() {
		try {
			estado = eqpDao.update(equipa);

			assertTrue(estado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo SQL");
		}
	}

@Test
	void testRemove() {
		
		try {
			estado = eqpDao.remove("21");

			assertTrue(estado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo SQL");
		}
	
}
}


