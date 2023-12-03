package interfaces;

import Classes.Client;
import Implementation_JDBC.ClientDaoImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainTestClientInterface extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private ClientDaoImpl clientDao;

    public MainTestClientInterface() {
        clientDao = new ClientDaoImpl();
        initialize();
        refreshTable();
    }

    private void initialize() {
        setTitle("Gestion de cabinet médical | Gestion des Clients");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Ajouter un titre en couleur verte
        JLabel titleLabel = new JLabel("Gestion de cabinet médical | Gestion des Clients", SwingConstants.CENTER);
        titleLabel.setForeground(Color.GREEN);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Version", "Titre", "Nom", "Prenom"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        JButton btnBack = new JButton("Retour à la gestion des Medecines");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainTestMedecineInterface clientInterface = new MainTestMedecineInterface();
                clientInterface.setVisible(true);
                dispose();  // Ferme la fenêtre actuelle (MainTestMedecineInterface)
            }
        });
        buttonPanel.add(btnBack);

        JButton btnAdd = new JButton("Ajouter");
        btnAdd.setBackground(Color.GREEN);  // Couleur verte pour Ajouter
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddClientForm();
            }
        });
        buttonPanel.add(btnAdd);

        JButton btnUpdate = new JButton("Modifier");
        btnUpdate.setBackground(Color.BLUE);  // Couleur bleue pour Modifier
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() != -1) {
                    int selectedRow = table.getSelectedRow();
                    Client selectedClient = getClientFromTableRow(selectedRow);
                    showUpdateClientForm(selectedClient);
                }
            }
        });
        buttonPanel.add(btnUpdate);

        JButton btnRemove = new JButton("Supprimer");
        btnRemove.setBackground(Color.RED);  // Couleur rouge pour Supprimer
        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() != -1) {
                    int selectedRow = table.getSelectedRow();
                    Client selectedClient = getClientFromTableRow(selectedRow);
                    clientDao.removeClient(selectedClient.getId());
                    refreshTable();
                }
            }
        });
        buttonPanel.add(btnRemove);
    }

    private void refreshTable() {
        tableModel.setRowCount(0);

        List<Client> allClients = clientDao.getAllClients();
        for (Client client : allClients) {
            tableModel.addRow(new Object[]{client.getId(), client.getVersion(), client.getTitre(),
                    client.getNom(), client.getPrenom()});
        }
    }

    private Client getClientFromTableRow(int row) {
        long id = (long) tableModel.getValueAt(row, 0);
        int version = (int) tableModel.getValueAt(row, 1);
        String titre = (String) tableModel.getValueAt(row, 2);
        String nom = (String) tableModel.getValueAt(row, 3);
        String prenom = (String) tableModel.getValueAt(row, 4);

        return new Client(id, version, titre, nom, prenom);
    }

    private void showAddClientForm() {
        JFrame addClientFrame = new JFrame("Ajouter un client");
        addClientFrame.setSize(400, 300);
        addClientFrame.setLocationRelativeTo(this);

        JPanel formPanel = new JPanel(new GridLayout(5, 2));

        JTextField txtVersion = new JTextField();
        JTextField txtTitre = new JTextField();
        JTextField txtNom = new JTextField();
        JTextField txtPrenom = new JTextField();

        formPanel.add(new JLabel("Version:"));
        formPanel.add(txtVersion);
        formPanel.add(new JLabel("Titre:"));
        formPanel.add(txtTitre);
        formPanel.add(new JLabel("Nom:"));
        formPanel.add(txtNom);
        formPanel.add(new JLabel("Prenom:"));
        formPanel.add(txtPrenom);

        JButton btnSave = new JButton("Enregistrer");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int version = Integer.parseInt(txtVersion.getText());
                String titre = txtTitre.getText();
                String nom = txtNom.getText();
                String prenom = txtPrenom.getText();

                Client newClient = new Client(0, version, titre, nom, prenom);
                clientDao.addClient(newClient);
                refreshTable();
                addClientFrame.dispose();
            }
        });

        formPanel.add(btnSave);

        addClientFrame.add(formPanel);
        addClientFrame.setVisible(true);
    }

    private void showUpdateClientForm(Client client) {
        JFrame updateClientFrame = new JFrame("Modifier un client");
        updateClientFrame.setSize(400, 300);
        updateClientFrame.setLocationRelativeTo(this);

        JPanel formPanel = new JPanel(new GridLayout(5, 2));

        JTextField txtVersion = new JTextField(String.valueOf(client.getVersion()));
        JTextField txtTitre = new JTextField(client.getTitre());
        JTextField txtNom = new JTextField(client.getNom());
        JTextField txtPrenom = new JTextField(client.getPrenom());

        formPanel.add(new JLabel("Version:"));
        formPanel.add(txtVersion);
        formPanel.add(new JLabel("Titre:"));
        formPanel.add(txtTitre);
        formPanel.add(new JLabel("Nom:"));
        formPanel.add(txtNom);
        formPanel.add(new JLabel("Prenom:"));
        formPanel.add(txtPrenom);

        JButton btnUpdate = new JButton("Mettre à jour");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int version = Integer.parseInt(txtVersion.getText());
                String titre = txtTitre.getText();
                String nom = txtNom.getText();
                String prenom = txtPrenom.getText();

                client.setVersion(version);
                client.setTitre(titre);
                client.setNom(nom);
                client.setPrenom(prenom);

                clientDao.updateClient(client);
                refreshTable();
                updateClientFrame.dispose();
            }
        });

        formPanel.add(btnUpdate);

        updateClientFrame.add(formPanel);
        updateClientFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    MainTestClientInterface window = new MainTestClientInterface();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
