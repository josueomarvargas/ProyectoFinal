package modelo.dao;

import java.sql.SQLException;
import java.util.Map;

import controlador.interfaz.BDgeneric;
import modelo.clases.Patrocinador;
	
public class PatrocinadorDAO implements BDgeneric<Patrocinador>{

	@Override
	public boolean create(Patrocinador clase) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Patrocinador search(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Patrocinador> readAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Patrocinador clase) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(String id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
