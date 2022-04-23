package controlador.dao;

import java.sql.SQLException;
import java.util.Map;

import controlador.BDgeneric;
import modelo.Actor;

public class ActorDAO implements BDgeneric<Actor> {

	@Override
	public boolean create(Actor clase) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Actor search(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Actor> readAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Actor clase) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(String id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
