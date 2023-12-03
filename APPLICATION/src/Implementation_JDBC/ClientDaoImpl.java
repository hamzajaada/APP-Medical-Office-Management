package Implementation_JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Classes.Client;
import Connection.DBConnection;
import DAO.ClientDao;

public class ClientDaoImpl implements ClientDao {
        private static DBConnection connect;
        private static Connection con;
        private PreparedStatement stm;
        private ResultSet rs;
        static {
            connect = DBConnection.getCon();
            con = connect.getConnection();
        }
    @Override
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<Client>();
		String req = "SELECT * FROM clients";
            try {
            stm = con.prepareStatement(req);
                    rs = stm.executeQuery();
                    while(rs.next()) {
                        Client c = new Client(rs.getLong(1),rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5));
                        clients.add(c);
                    }
            } catch (Exception e) {
            e.printStackTrace();
            }
        return clients;
        }
    @Override
    public void addClient(Client c) {
        String req = "INSERT INTO clients(version, titre, nom, prenom) VALUES (?, ?, ?, ?)";
      try {
          stm = con.prepareStatement(req);
          stm.setInt(1, c.getVersion());
          stm.setString(2, c.getTitre());
          stm.setString(3, c.getNom());
          stm.setString(4, c.getPrenom());
          stm.executeUpdate();
      } catch (SQLException e) {
          e.printStackTrace();
      }
    }

    @Override
    public void updateClient(Client C) {
        String req = "UPDATE clients SET version=?,titre=?,nom=?,prenom=? WHERE id=?";
        try {
			stm = con.prepareStatement(req);
			stm.setInt(1, C.getVersion());
			stm.setString(2, C.getTitre());
			stm.setString(3, C.getNom());
			stm.setString(4, C.getPrenom());
            stm.setLong(5, C.getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @Override
    public void removeClient(long id_client) {
        String req = "DELETE FROM clients WHERE id = ?";
        try {
          stm = con.prepareStatement(req);
          stm.setLong(1, id_client);
          stm.executeUpdate();
        } catch (Exception e) {
          e.printStackTrace();   }
      }
    

    @Override
    public Client getClient(long id_client) {
        Client cl = null;
        String req = "SELECT * FROM clients WHERE id=?";
        try {
          stm = con.prepareStatement(req);
          stm.setLong(1,id_client);
          rs = stm.executeQuery();
          while (rs.next()) {
            cl =  new Client(rs.getLong(1),rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5));
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
        return cl;
    }
    
}
