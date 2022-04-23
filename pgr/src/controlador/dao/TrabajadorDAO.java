package controlador.dao;

import java.sql.SQLException;
import java.util.Map;

import controlador.BDgeneric;
import modelo.Trabajador;

public class TrabajadorDAO implements BDgeneric<Trabajador>{

	@Override
	public boolean create(Trabajador clase) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Trabajador search(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Trabajador> readAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Trabajador clase) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(String id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
