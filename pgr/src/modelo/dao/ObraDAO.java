package modelo.dao;

import java.sql.SQLException;
import java.util.Map;

import controlador.interfaz.BDgeneric;
import modelo.clases.ObraAudiovisual;

public class ObraDAO implements BDgeneric<ObraAudiovisual>{

	@Override
	public boolean create(ObraAudiovisual clase) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ObraAudiovisual search(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, ObraAudiovisual> readAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(ObraAudiovisual clase) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(String id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
