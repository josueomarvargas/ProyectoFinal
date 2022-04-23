package controlador.dao;

import java.sql.SQLException;
import java.util.Map;

import controlador.BDgeneric;
import modelo.Pelicula;

public class PeliDAO implements BDgeneric<Pelicula> {

	@Override
	public boolean create(Pelicula clase) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pelicula search(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Pelicula> readAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Pelicula clase) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(String id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
