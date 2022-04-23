package controlador.dao;

import java.sql.SQLException;
import java.util.Map;

import controlador.BDgeneric;
import modelo.EquipObra;

public class EquipObraDAO implements BDgeneric<EquipObra> {

	@Override
	public boolean create(EquipObra clase) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EquipObra search(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, EquipObra> readAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(EquipObra clase) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(String id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
