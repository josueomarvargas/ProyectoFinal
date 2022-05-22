package junit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import controlador.utils.dao.FactoryDAO;
import modelo.clases.Serie;
import modelo.dao.ObraDAO;

class SerieDAOTest {

	static Serie ser = null;
	boolean estado;
	ObraDAO sDao = (ObraDAO) FactoryDAO.getObra();

	@Before
	static void setUpBeforeClass() throws Exception {
		ser = new Serie();
		ser.setIdObra(10);
		ser.setTipo("serie");
		ser.setFechaEstreno(LocalDate.of(1997, 10, 20));
		List<List<String>> aux = ((Serie) ser).getNombreCap();
		aux.get(0).add("jon");
		aux.add(new ArrayList<>());
		String nomCap = "iker";

	}
//
//	@Test
//	void testCreate() {
//
//		try {
//			estado = ((BDgeneric<ObraAudiovisual>) sDao).create(ser);
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
//		String [] id = {"11"};
//		try {
//			ObraAudiovisual aux = sDao.search(id);
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
//			Map<Integer, ObraAudiovisual> map = sDao.readAll();
//			Iterator<ObraAudiovisual> iter = map.values().iterator();
//			while (iter.hasNext()) {
//				aux = iter.next();
//				if(aux instanceof Serie) {
//					System.out.println(((Serie) aux).toString());
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

	@Test
	void testUpdate() {
		try {
			estado = sDao.update(ser);

			assertTrue(estado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo SQL");
		}
	}

//	@Test
//	void testRemove() {
//		String[] idObra = { "10" };
//		try {
//			estado = sDao.remove(idObra);
//
//			assertTrue(estado);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("Fallo SQL");
//		}
//	}

}
