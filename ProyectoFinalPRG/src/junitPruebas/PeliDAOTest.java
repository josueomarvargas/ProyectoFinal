package junitPruebas;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controlador.interfaz.BDgeneric;
import controlador.utils.dao.DAOFactory;
import modelo.clases.Actor;
import modelo.clases.ObraAudiovisual;
import modelo.clases.Pelicula;
import modelo.clases.Trabajador;
import modelo.dao.ObraDAO;
import modelo.dao.TrabajadorDAO;
import modelo.dao.ViewPeliDAO;

class PeliDAOTest {

	static Pelicula pel = null;
	boolean estado;
	ObraDAO pDao = (ObraDAO) DAOFactory.OBRA.getInstance();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		pel = new Pelicula();
		pel.setIdObra(1);
		pel.setDuracion(120);
		pel.setEsTaquillera(false);
		pel.setFechaEstreno(LocalDate.of(1997, 10, 20));
		pel.setNombre("Iker");
		pel.setPresupuesto(70000);
		pel.setTipo("Pelicula");
	}

//	@Test
//	void testCreate() {
//
//		try {
//			estado = ((BDgeneric<ObraAudiovisual>) pDao).create(pel);
//
//			assertTrue(estado);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("Fallo SQL");
//		}
//
//	}

//	@Test
//	void testSearch() {
//		String [] id = {"6"};
//		try {
//			ObraAudiovisual aux = pDao.search(id);
//			
//			System.out.println(aux.toString());
//			
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
//			ObraAudiovisual aux;
//			Map<Integer, ObraAudiovisual> map = pDao.readAll();
//			Iterator<ObraAudiovisual> iter = map.values().iterator();
//			while (iter.hasNext()) {
//				aux = iter.next();
//				if(aux instanceof Pelicula) {
//					System.out.println(((Pelicula) aux).toString());
//					
//				}
//				
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
//			estado = pDao.update(pel);
//
//			assertTrue(estado);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("Fallo SQL");
//		}
//	}

	@Test
	void testRemove() {
		String[] idObra = { "6" };
		try {
			estado = pDao.remove(idObra);

			assertTrue(estado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo SQL");
		}
	}

}
