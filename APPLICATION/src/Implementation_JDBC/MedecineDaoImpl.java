package Implementation_JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Classes.Medecine;
import Connection.DBConnection;
import DAO.MedecineDao;

public class MedecineDaoImpl implements MedecineDao{
    List<Medecine> medecins = new ArrayList<Medecine>();
	private static DBConnection connect;
    private static Connection con;
    static {
    connect = DBConnection.getCon();
    con = connect.getConnection();
    }
	private PreparedStatement stm;
	private ResultSet rs;
    @Override
    public List<Medecine> getAllMedecins() {
        String req = "SELECT * FROM Medecines";
		try {
			stm = con.prepareStatement(req);
			rs = stm.executeQuery();
			while(rs.next()) {
				Medecine c = new Medecine(rs.getLong(1),rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5));
				medecins.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return medecins;
    }

    @Override
    public void addMedecin(Medecine M) {
        String req = "INSERT INTO Medecines(version,titre,nom,prenom) VALUES (?,?,?,?)";
		try {
			stm = con.prepareStatement(req);
			stm.setInt(1, M.getVersion());
			stm.setString(2, M.getTitre());
			stm.setString(3, M.getNom());
			stm.setString(4, M.getPrenom());
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @Override
   
    public void updateMedecin(Medecine M) {
        String req = "UPDATE Medecines SET version=?,titre=?,nom=?,prenom=? WHERE id=?";
		try {
			stm = con.prepareStatement(req);
			stm.setInt(1, M.getVersion());
			stm.setString(2, M.getTitre());
			stm.setString(3, M.getNom());
			stm.setString(4, M.getPrenom());
			stm.setLong(5, M.getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @Override
    public void removeMedecin(long idm) {
        String req = "DELETE FROM Medecines WHERE id=?";
		try {
			stm = con.prepareStatement(req);
			stm.setLong(1, idm);
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @Override
    public Medecine getMedecin(long idm) {
        Medecine medecin = null;
		// con = Connect.getCon();
		String req = "SELECT * FROM Medecines WHERE id=?";
		try {
			stm = con.prepareStatement(req);
			stm.setLong(1, idm);
			rs = stm.executeQuery();
			while(rs.next()) {
				medecin = new Medecine(rs.getLong(1),rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return medecin;
    }
    
}
