package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import controlador.interfaz.BDview;
import controlador.utils.dao.SQLCon;
import modelo.clases.ViewPeli;

public class ViewPeliDAO implements BDview<ViewPeli> {

	private final String viewPeli = "SELECT * FROM viewpelis";

	@Override
	public Map<Integer, ViewPeli> callView() {

		// Recoger la conexión
		Connection con = SQLCon.openConnection();
		ResultSet rs;
		Map<Integer, ViewPeli> pelis = new HashMap<>();
		ViewPeli vp = null;

		// Prepare Statement - ViewPelis
		try (PreparedStatement stat = con.prepareStatement(viewPeli);) {

			// Recuperar los datos con el RS
			rs = stat.executeQuery();

			// Guardar los datos en el objecto
			while (rs.next()) {
				vp = new ViewPeli();
				vp.setId(rs.getInt(1));
				vp.setNombre(rs.getString(2));
				vp.setDirector(rs.getString(3));
				vp.setGuionista(rs.getString(4));
				vp.setNumTrabajadores(rs.getInt(5));
				vp.setPresupuesto(rs.getInt(6));
				vp.setFechaEstreno(rs.getDate(7) != null ? rs.getDate(7).toLocalDate() : null);
				vp.setEsTaquillero(rs.getString(8));

				// Guardar las pelis en el map
				pelis.put(vp.getId(), vp);
			}

		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			if (con != null) {
				try {
					con.close();
					SQLCon.closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return pelis;

	}

}
