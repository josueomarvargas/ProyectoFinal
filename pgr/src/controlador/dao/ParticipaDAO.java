package controlador.dao;

import java.sql.SQLException;
import java.util.Map;

import controlador.BDgeneric;
import modelo.Participa;

public class ParticipaDAO implements BDgeneric<Participa>{

	@Override
	public boolean create(Participa clase) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Participa search(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Participa> readAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Participa clase) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(String id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
