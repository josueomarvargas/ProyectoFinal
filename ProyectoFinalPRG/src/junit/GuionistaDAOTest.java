package junit;

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

import controlador.utils.dao.FactoryDAO;
import modelo.clases.Actor;
import modelo.clases.Director;
import modelo.clases.Guionista;
import modelo.clases.Trabajador;
import modelo.dao.TrabajadorDAO;

class GuionistaDAOTest {

	static Trabajador gui = null;
	boolean estado;
	static TrabajadorDAO gDao =new TrabajadorDAO();

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		gui = new Guionista();
		gui.setIdTrabajador(66);
		gui.setDni("17090623Y");
		gui.setNombre("Luis");
		gui.setApellido("etxeberria");
		gui.setNumTel(688612456);
		gui.setNumPremios(6);
		gui.setDireccion("Serralta");
		gui.setTipo("Guionista");
		gui.setFechaNac(LocalDate.of(1997, 10, 20));
		List<String> Guionista = new ArrayList<>();
		Guionista.add("comedia");
		((Guionista) gui).setTipoGuiones(Guionista);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	gDao=null;
	}

	@Test
	void testCreate() {

		estado = gDao.create(gui);
			
		assertTrue(estado);
	
	}

	@Test
	void testSearch() {
		String [] id = {"15"};
		
			Guionista aux = (Guionista) gDao.search(id);
assertEquals(aux, gui);	
	}



	@Test
	void testReadAll() {
		try {
			Trabajador aux;
		Map<Integer, Trabajador> map = gDao.readAll();
			Iterator<Trabajador> iter = map.values().iterator();
			while (iter.hasNext()) {
				aux = iter.next();
				if(aux instanceof Guionista) {
					System.out.println(((Guionista) aux).toString());
					
				}
				estado =true;
				gui.toString();	
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	assertTrue(estado);	
	}
	@Test
	void testUpdate() {
		try {
			estado = gDao.update(gui);
			assertTrue(estado);

			assertTrue(estado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo SQL");
		}
	}

	@Test
	void testRemove() {
		String [] id = {"15"};	
		try {
			estado = gDao.remove(id);

			assertTrue(estado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Fallo SQL");
		}
	}
}
//}
