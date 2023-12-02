package Implementation_File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Classes.Client;
import DAO.ClientDao;

public class ClientFile implements ClientDao , Serializable {
    private static long lastClientId = 0;
    @Override
    public List<Client> getAllClients() {
        List<Client> CLients = new ArrayList<>();
        try (FileInputStream file = new FileInputStream("clients.ser")) {

                try(ObjectInputStream Objin= new ObjectInputStream(file)){
                    CLients = (List<Client>) Objin.readObject();
                }catch(Exception e){
                e.getMessage();
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return CLients;
    }

    @Override
    public void addClient(Client client) {
        List<Client> clients = getAllClients();
        lastClientId++;
        client.setId(lastClientId);
        clients.add(client);
        saveClients(clients);
    }

    private void saveClients(List<Client> clients) {
          try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("clients.ser"))) {
                 objectOut.writeObject(clients);
            } catch (IOException e) {
                 e.printStackTrace();
                 }
        }

    @Override
    public void updateClient(Client UPC) {
        List<Client> clients = getAllClients();
        for (int i = 0; i < clients.size(); i++) {
        if (clients.get(i).getId() == UPC.getId()) {
        clients.set(i, UPC);
        break;
      }
    }
    saveClients(clients);
    }

    public void removeClient(long clientId) {
        List<Client> clients = getAllClients();
        clients.removeIf(client -> client.getId() == clientId);
        saveClients(clients);
      }
    
    @Override
    public Client getClient(long clientId) {
        List<Client> clients = getAllClients();
        for (Client client : clients) {
          if (client.getId() == clientId) {
            return client;
          }
        }
        return null;
      }
}
