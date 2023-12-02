package DAO;

import java.util.List;
import Classes.Client;

public interface ClientDao {
  List<Client> getAllClients();
	void addClient(Client C);
	void updateClient(Client C);
	void removeClient(long id_client);
	Client getClient(long id_client);
}