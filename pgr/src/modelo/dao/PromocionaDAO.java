package modelo.dao;

import java.sql.SQLException;
import java.util.Map;

import controlador.interfaz.BDgeneric;
import modelo.clases.Promociona;

public class PromocionaDAO implements BDgeneric<Promociona> {

	@Override
	public boolean create(Promociona clase) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Promociona search(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Promociona> readAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Promociona clase) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(String id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
