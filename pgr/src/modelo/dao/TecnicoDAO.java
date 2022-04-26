package modelo.dao;

import java.sql.SQLException;
import java.util.Map;

import controlador.interfaz.BDgeneric;
import modelo.clases.TecnicoAudiovisual;

public class TecnicoDAO implements BDgeneric<TecnicoAudiovisual> {

	@Override
	public boolean create(TecnicoAudiovisual clase) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TecnicoAudiovisual search(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, TecnicoAudiovisual> readAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(TecnicoAudiovisual clase) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(String id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
