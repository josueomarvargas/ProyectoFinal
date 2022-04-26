package modelo.dao;

import java.sql.SQLException;
import java.util.Map;

import controlador.interfaz.BDgeneric;
import modelo.clases.TrabajadorUsuario;

public class TrabajadorUserDAO implements BDgeneric<TrabajadorUsuario> {

	@Override
	public boolean create(TrabajadorUsuario clase) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TrabajadorUsuario search(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, TrabajadorUsuario> readAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(TrabajadorUsuario clase) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(String id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
