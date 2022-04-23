package controlador.dao;

import java.sql.SQLException;
import java.util.Map;

import controlador.BDgeneric;
import modelo.Guionista;

public class GuionistaDAO implements BDgeneric<Guionista>{

	@Override
	public boolean create(Guionista clase) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Guionista search(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Guionista> readAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Guionista clase) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(String id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
