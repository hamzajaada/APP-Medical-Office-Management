package interfaces;

import Classes.Medecine;
import Implementation_JDBC.MedecineDaoImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainTestMedecineInterface extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private MedecineDaoImpl medecineDao;

    public MainTestMedecineInterface() {
        medecineDao = new MedecineDaoImpl();
        initialize();
        refreshTable();
    }

    private void initialize() {
        setTitle("Gestion de cabinet médical | Gestion des Medecins");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Ajouter un titre en couleur verte
        JLabel titleLabel = new JLabel("Gestion de cabinet médical | Gestion des Medecins", SwingConstants.CENTER);
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

        JButton btnBack = new JButton("Retour à la gestion des clients");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainTestClientInterface clientInterface = new MainTestClientInterface();
                clientInterface.setVisible(true);
                dispose();  // Ferme la fenêtre actuelle (MainTestMedecineInterface)
            }
        });
        buttonPanel.add(btnBack);

        JButton btnAdd = new JButton("Ajouter");
        btnAdd.setBackground(Color.GREEN);  // Couleur verte pour Ajouter
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddMedecinForm();
            }
        });
        buttonPanel.add(btnAdd);

        JButton btnUpdate = new JButton("Modifier");
        btnUpdate.setBackground(Color.BLUE);  // Couleur bleue pour Modifier
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() != -1) {
                    int selectedRow = table.getSelectedRow();
                    Medecine selectedMedecine = getMedecineFromTableRow(selectedRow);
                    showUpdateMedecineForm(selectedMedecine);
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
                    Medecine selectedMedecine = getMedecineFromTableRow(selectedRow);
                    medecineDao.removeMedecin(selectedMedecine.getId());
                    refreshTable();
                }
            }
        });
        buttonPanel.add(btnRemove);
    }

    private void refreshTable() {
        tableModel.setRowCount(0);

        List<Medecine> allMedecins = medecineDao.getAllMedecins();
        for (Medecine medecine : allMedecins) {
            tableModel.addRow(new Object[]{medecine.getId(), medecine.getVersion(), medecine.getTitre(),
                    medecine.getNom(), medecine.getPrenom()});
        }
    }

    private Medecine getMedecineFromTableRow(int row) {
        long id = (long) tableModel.getValueAt(row, 0);
        int version = (int) tableModel.getValueAt(row, 1);
        String titre = (String) tableModel.getValueAt(row, 2);
        String nom = (String) tableModel.getValueAt(row, 3);
        String prenom = (String) tableModel.getValueAt(row, 4);

        return new Medecine(id, version, titre, nom, prenom);
    }

    private void showAddMedecinForm() {
        JFrame addMedecinFrame = new JFrame("Ajouter un médecin");
        addMedecinFrame.setSize(400, 300);
        addMedecinFrame.setLocationRelativeTo(this);

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

                Medecine newMedecine = new Medecine(0, version, titre, nom, prenom);
                medecineDao.addMedecin(newMedecine);
                refreshTable();
                addMedecinFrame.dispose();
            }
        });

        formPanel.add(btnSave);

        addMedecinFrame.add(formPanel);
        addMedecinFrame.setVisible(true);
    }

    private void showUpdateMedecineForm(Medecine medecine) {
        JFrame updateMedecineFrame = new JFrame("Modifier un médecin");
        updateMedecineFrame.setSize(400, 300);
        updateMedecineFrame.setLocationRelativeTo(this);

        JPanel formPanel = new JPanel(new GridLayout(5, 2));

        JTextField txtVersion = new JTextField(String.valueOf(medecine.getVersion()));
        JTextField txtTitre = new JTextField(medecine.getTitre());
        JTextField txtNom = new JTextField(medecine.getNom());
        JTextField txtPrenom = new JTextField(medecine.getPrenom());

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

                // Mettre à jour le médecin existant
                medecine.setVersion(version);
                medecine.setTitre(titre);
                medecine.setNom(nom);
                medecine.setPrenom(prenom);

                medecineDao.updateMedecin(medecine);
                refreshTable();
                updateMedecineFrame.dispose();
        }
    });

    formPanel.add(btnUpdate);

    updateMedecineFrame.add(formPanel);
    updateMedecineFrame.setVisible(true);
}

    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    MainTestMedecineInterface window = new MainTestMedecineInterface();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
