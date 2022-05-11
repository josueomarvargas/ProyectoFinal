package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import controlador.interfaz.BDview;
import controlador.utils.dao.SQLCon;
import modelo.clases.ViewSerie;

public class ViewSerieDAO implements BDview<ViewSerie> {

	private final String viewPeli = "SELECT * FROM viewseries";

	@Override
	public Map<Integer, ViewSerie> callView() {
		// Recoger la conexión
		Connection con = SQLCon.openConnection();
		ResultSet rs;
		Map<Integer, ViewSerie> series = new HashMap<>();
		ViewSerie vs = null;

		// Prepare Statement - ViewPelis
		try (PreparedStatement stat = con.prepareStatement(viewPeli)) {

			// Recuperar los datos con el RS
			rs = stat.executeQuery();

			// Guardar los datos en el objecto
			while (rs.next()) {
				vs = new ViewSerie();
				vs.setId(rs.getInt(1));
				vs.setNombre(rs.getString(2));
				vs.setDirector(rs.getString(3));
				vs.setGuionista(rs.getString(4));
				vs.setNumTrabajadores(rs.getInt(5));
				vs.setPresupuesto(rs.getInt(6));
				vs.setFechaEstreno(rs.getDate(7).toLocalDate());
				vs.setTemporadas(rs.getInt(8));
				vs.setCapitulos(rs.getInt(9));

				// Guardar las pelis en el map
				series.put(vs.getId(), vs);

			}

		} catch (SQLException e) {
			e.printStackTrace();

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

		return series;
	}

}
