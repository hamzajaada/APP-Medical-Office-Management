package Implementation_JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Classes.RendezVous;
import Connection.DBConnection;
import DAO.RendezVousDao;

public class RendezVousDaoImpl implements RendezVousDao {
 List<RendezVous> RVs = new ArrayList<RendezVous>();
	private static DBConnection connect;
  private static Connection con;

  static {
    connect = DBConnection.getCon();
    con = connect.getConnection();
  }
	private PreparedStatement stm;
	private ResultSet rs;

    @Override
    public List<RendezVous> getAllRVs() {
        String req = "SELECT * FROM RendezVous";
		try {
			stm = con.prepareStatement(req);
			rs = stm.executeQuery();
			while(rs.next()) {
				RendezVous R = new RendezVous(rs.getLong(1),String.valueOf(rs.getDate(2)), rs.getLong(3), rs.getLong(4));
				RVs.add(R);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return RVs;
    }

    @Override
    public void addRV(RendezVous rv) {
        String req = "INSERT INTO RendezVous (jour,id_client,id_creneau) VALUES (?,?,?)";
		try {
			stm = con.prepareStatement(req);
			stm.setString(1, rv.getJour());
			stm.setLong(2, rv.getId_client());
			stm.setLong(3, rv.getId_creneau());
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @Override
    public void updateRV(RendezVous rv) {
        String req = "UPDATE RendezVous SET jour=?,id_client=?,id_creneau=? WHERE id=?";
		try {
			stm = con.prepareStatement(req);
			stm.setString(1, rv.getJour());
			stm.setLong(2, rv.getId_client());
			stm.setLong(3, rv.getId_creneau());
			stm.setLong(4, rv.getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @Override
    public void removeRV(long idrv) {
        String req = "DELETE FROM RendezVous WHERE id=?";
		try {
			stm = con.prepareStatement(req);
			stm.setLong(1, idrv);
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @Override
    public RendezVous getRV(long idrv) {
        RendezVous R=null;
		String req = "SELECT * FROM RendezVous WHERE id=?";
		try {
			stm = con.prepareStatement(req);
			stm.setLong(1, idrv);
			rs = stm.executeQuery();
			while(rs.next()) {
				R = new RendezVous(rs.getLong(1),String.valueOf(rs.getDate(2)), rs.getLong(3), rs.getLong(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return R;
    }
    
}
