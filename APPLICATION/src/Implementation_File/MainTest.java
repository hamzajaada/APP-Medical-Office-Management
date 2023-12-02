package Implementation_File;

import Classes.Client;
import Implementation_File.ClientFile;

import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        // Create an instance of ClientFile
        ClientFile clientFile = new ClientFile();

        // Add clients
        Client client1 = new Client(0, 1, "Mr.", "Doe", "John");
        Client client2 = new Client(0, 1, "Ms.", "Smith", "Alice");

        clientFile.addClient(client1);
        clientFile.addClient(client2);

        // Display all clients
        List<Client> allClients = clientFile.getAllClients();
        System.out.println("All Clients:");
        for (Client client : allClients) {
            System.out.println(client.getId() + ": " + client.getTitre() + " " + client.getNom() + " " + client.getPrenom());
        }

        // Update a client
        Client updatedClient = new Client(1, 2, "Dr.", "Brown", "Bob");
        clientFile.updateClient(updatedClient);

        // Display all clients after update
        allClients = clientFile.getAllClients();
        System.out.println("\nAll Clients after Update:");
        for (Client client : allClients) {
            System.out.println(client.getId() + ": " + client.getTitre() + " " + client.getNom() + " " + client.getPrenom());
        }

        // Remove a client
        long clientIdToRemove = 2;
        clientFile.removeClient(clientIdToRemove);

        // Display all clients after removal
        allClients = clientFile.getAllClients();
        System.out.println("\nAll Clients after Removal:");
        for (Client client : allClients) {
            System.out.println(client.getId() + ": " + client.getTitre() + " " + client.getNom() + " " + client.getPrenom());
        }

        // Get a specific client
        long clientIdToGet = 1;
        Client retrievedClient = clientFile.getClient(clientIdToGet);
        if (retrievedClient != null) {
            System.out.println("\nClient with ID " + clientIdToGet + " found: " +
                    retrievedClient.getTitre() + " " + retrievedClient.getNom() + " " + retrievedClient.getPrenom());
        } else {
            System.out.println("\nClient with ID " + clientIdToGet + " not found.");
        }
    }
}
