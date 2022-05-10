package junitPruebas;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controlador.utils.dao.DAOFactory;
import modelo.clases.Actor;
import modelo.clases.Director;
import modelo.clases.Trabajador;
import modelo.dao.TrabajadorDAO;

class DirectorDAOTest {

	static Trabajador dir = null;
	boolean estado;
	TrabajadorDAO dDao = (TrabajadorDAO) DAOFactory.TRABAJADOR.getInstance();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		dir = new Director();
		dir.setIdTrabajador(19);
		dir.setNombre("anne");
		dir.setApellido("perez");
		dir.setNumTel(688612456);
		dir.setNumPremios(6);
		dir.setDireccion("Bergara");
		dir.setTipo("director");
		dir.setFechaNac(LocalDate.of(1997, 10, 20));
		
		((Director) dir).setCategoria("comedia");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

//	@Test
//	void testCreate() {
//		try {
//		estado = dDao.create(dir);
//
//			assertTrue(estado);
//		} catch (Exception e) {
//		// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("Fallo SQL");
//		}
//	}

//	@Test
//	void testSearch() {
//		String[] id = {"14"};
//		try {
//			Director aux = (Director) dDao.search(id);
//			System.out.println(aux.toString());
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
//			Trabajador aux;
//			Map<Integer, Trabajador> map = dDao.readAll();
//			System.out.println(map.get(1).toString());
//			Iterator<Trabajador> iter = map.values().iterator();
//			while (iter.hasNext()) {
//				aux = iter.next();
//				if(aux instanceof Director) {
//					System.out.println(((Director) aux).toString());
//					
//				}
//				
//		dir.toString();		
//						
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		fail("Not yet implemented");
//	}

//	@Test
//	void testUpdate() {
//		try {
//			estado = dDao.update(dir);
//
//			assertTrue(estado);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("Fallo SQL");
//		}
//	}
//
	@Test
	void testRemove() {
		String [] id = {"14"};
		 try {
		 estado = dDao.remove(id);

			assertTrue(estado);
		 } catch (Exception e) {
		// TODO Auto-generated catch block
		 e.printStackTrace();
		 fail("Fallo SQL");
		 }
	}
}
