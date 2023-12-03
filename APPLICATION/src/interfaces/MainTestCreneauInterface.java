package interfaces;
import Classes.*;
import Implementation_JDBC.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainTestCreneauInterface extends JFrame {

    private CreneauDaoImpl creneauDao;

    private DefaultTableModel tableModel;
    private JTable table;

    public MainTestCreneauInterface() {
        creneauDao = new CreneauDaoImpl();

        setTitle("Gestion de Creneau");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Ajouter un titre en couleur verte
        JLabel titleLabel = new JLabel("Gestion de Creneau", SwingConstants.CENTER);
        titleLabel.setForeground(Color.GREEN);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Créer un tableau pour afficher les créneaux
        tableModel = new DefaultTableModel(new Object[]{"ID", "Version", "HDebut", "MDebut", "HFin", "MFin", "ID Medecin"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        add(scrollPane, BorderLayout.CENTER);

        // Ajouter un bouton "Ajouter Creneau"
        JButton addButton = new JButton("Ajouter Creneau");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddCreneauForm();
            }
        });
        add(addButton, BorderLayout.SOUTH);

        // Ajouter un bouton "Modifier Creneau"
        JButton updateButton = new JButton("Modifier Creneau");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() != -1) {
                    int selectedRow = table.getSelectedRow();
                    Creneau selectedCreneau = getCreneauFromTableRow(selectedRow);
                    showUpdateCreneauForm(selectedCreneau);
                } else {
                    JOptionPane.showMessageDialog(MainTestCreneauInterface.this, "Veuillez sélectionner un créneau à modifier.",
                            "Avertissement", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        add(updateButton, BorderLayout.SOUTH);

        // Charger les créneaux existants dans le tableau
        refreshTable();
    }

    private void showAddCreneauForm() {
        JFrame addCreneauFrame = new JFrame("Ajouter un Creneau");
        addCreneauFrame.setSize(400, 300);
        addCreneauFrame.setLocationRelativeTo(this);

        JPanel formPanel = new JPanel(new GridLayout(7, 2));

        JTextField txtVersion = new JTextField();
        JTextField txtHDebut = new JTextField();
        JTextField txtMDebut = new JTextField();
        JTextField txtHFin = new JTextField();
        JTextField txtMFin = new JTextField();
        JTextField txtIdMedecin = new JTextField();

        formPanel.add(new JLabel("Version:"));
        formPanel.add(txtVersion);
        formPanel.add(new JLabel("HDebut:"));
        formPanel.add(txtHDebut);
        formPanel.add(new JLabel("MDebut:"));
        formPanel.add(txtMDebut);
        formPanel.add(new JLabel("HFin:"));
        formPanel.add(txtHFin);
        formPanel.add(new JLabel("MFin:"));
        formPanel.add(txtMFin);
        formPanel.add(new JLabel("ID Medecin:"));
        formPanel.add(txtIdMedecin);

        JButton btnSave = new JButton("Enregistrer");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int version = Integer.parseInt(txtVersion.getText());
                    int hDebut = Integer.parseInt(txtHDebut.getText());
                    int mDebut = Integer.parseInt(txtMDebut.getText());
                    int hFin = Integer.parseInt(txtHFin.getText());
                    int mFin = Integer.parseInt(txtMFin.getText());
                    long idMedecin = Long.parseLong(txtIdMedecin.getText());

                    Creneau newCreneau = new Creneau(0, version, hDebut, mDebut, hFin, mFin, idMedecin);
                    creneauDao.addCreneau(newCreneau);
                    refreshTable();
                    addCreneauFrame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainTestCreneauInterface.this, "Veuillez entrer des valeurs valides.",
                            "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        formPanel.add(btnSave);

        addCreneauFrame.add(formPanel);
        addCreneauFrame.setVisible(true);
    }

    private void showUpdateCreneauForm(Creneau creneau) {
        JFrame updateCreneauFrame = new JFrame("Modifier un Creneau");
        updateCreneauFrame.setSize(400, 300);
        updateCreneauFrame.setLocationRelativeTo(this);

        JPanel formPanel = new JPanel(new GridLayout(7, 2));

        JTextField txtVersion = new JTextField(String.valueOf(creneau.getVersion()));
        JTextField txtHDebut = new JTextField(String.valueOf(creneau.getHdebut()));
        JTextField txtMDebut = new JTextField(String.valueOf(creneau.getMdebut()));
        JTextField txtHFin = new JTextField(String.valueOf(creneau.getHfin()));
        JTextField txtMFin = new JTextField(String.valueOf(creneau.getMfin()));
        JTextField txtIdMedecin = new JTextField(String.valueOf(creneau.getId_medecin()));

        formPanel.add(new JLabel("Version:"));
        formPanel.add(txtVersion);
        formPanel.add(new JLabel("HDebut:"));
        formPanel.add(txtHDebut);
        formPanel.add(new JLabel("MDebut:"));
        formPanel.add(txtMDebut);
        formPanel.add(new JLabel("HFin:"));
        formPanel.add(txtHFin);
        formPanel.add(new JLabel("MFin:"));
        formPanel.add(txtMFin);
        formPanel.add(new JLabel("ID Medecin:"));
        formPanel.add(txtIdMedecin);

        JButton btnUpdate = new JButton("Mettre à jour");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int version = Integer.parseInt(txtVersion.getText());
                    int hDebut = Integer.parseInt(txtHDebut.getText());
                    int mDebut = Integer.parseInt(txtMDebut.getText());
                    int hFin = Integer.parseInt(txtHFin.getText());
                    int mFin = Integer.parseInt(txtMFin.getText());
                    long idMedecin = Long.parseLong(txtIdMedecin.getText());

                    creneau.setVersion(version);
                    creneau.setHdebut(hDebut);
                    creneau.setMdebut(mDebut);
                    creneau.setHfin(hFin);
                    creneau.setMfin(mFin);
                    creneau.setId_medecin(idMedecin);

                    creneauDao.updateCreneau(creneau);
                    refreshTable();
                    updateCreneauFrame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainTestCreneauInterface.this, "Veuillez entrer des valeurs valides.",
                            "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        formPanel.add(btnUpdate);

        updateCreneauFrame.add(formPanel);
        updateCreneauFrame.setVisible(true);
    }

    private void refreshTable() {
        tableModel.setRowCount(0);

        List<Creneau> allCreneaux = creneauDao.getAllCreneaus();
        for (Creneau creneau : allCreneaux) {
            tableModel.addRow(new Object[]{creneau.getId(), creneau.getVersion(),
                    creneau.getHdebut(), creneau.getMdebut(), creneau.getHfin(), creneau.getMfin(),
                    creneau.getId_medecin()});
        }
    }

    private Creneau getCreneauFromTableRow(int row) {
        long id = (long) tableModel.getValueAt(row, 0);
        int version = (int) tableModel.getValueAt(row, 1);
        int hDebut = (int) tableModel.getValueAt(row, 2);
        int mDebut = (int) tableModel.getValueAt(row, 3);
        int hFin = (int) tableModel.getValueAt(row, 4);
        int mFin = (int) tableModel.getValueAt(row, 5);
        long idMedecin = (long) tableModel.getValueAt(row, 6);

        return new Creneau(id, version, hDebut, mDebut, hFin, mFin, idMedecin);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    MainTestCreneauInterface window = new MainTestCreneauInterface();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
