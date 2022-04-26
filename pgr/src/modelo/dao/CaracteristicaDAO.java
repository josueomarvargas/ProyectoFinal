package modelo.dao;

import java.sql.SQLException;
import java.util.Map;

import controlador.interfaz.BDgeneric;
import modelo.clases.Equipamiento;

public class CaracteristicaDAO implements BDgeneric<Equipamiento>{

	@Override
	public boolean create(Equipamiento clase) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Equipamiento search(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Equipamiento> readAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Equipamiento clase) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(String id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
