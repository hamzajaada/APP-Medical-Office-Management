package interfaces;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRendezVous {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Ajouter Rendez-vous");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400); // Taille augmentée
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(240, 240, 240));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2, 15, 15)); // Ajustement des espacements
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // Marge intérieure accrue

        Font labelFont = new Font("Arial", Font.PLAIN, 16); // Changement de la police des labels

        JLabel clientLabel = new JLabel("Client:");
        clientLabel.setFont(labelFont); // Application de la nouvelle police
        JTextField clientTextField = new JTextField();

        JLabel medecinLabel = new JLabel("Médecin:");
        medecinLabel.setFont(labelFont); // Application de la nouvelle police
        JTextField medecinTextField = new JTextField();

        JLabel creneauLabel = new JLabel("Créneau:");
        creneauLabel.setFont(labelFont); // Application de la nouvelle police
        JTextField creneauTextField = new JTextField();

        JButton ajouterButton = new JButton("Ajouter Rendez-vous");
        ajouterButton.setBackground(new Color(50, 205, 50));
        ajouterButton.setForeground(Color.WHITE);

        mainPanel.add(clientLabel);
        mainPanel.add(clientTextField);
        mainPanel.add(medecinLabel);
        mainPanel.add(medecinTextField);
        mainPanel.add(creneauLabel);
        mainPanel.add(creneauTextField);
        mainPanel.add(new JLabel());
        mainPanel.add(ajouterButton);

        frame.add(mainPanel, BorderLayout.CENTER);

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ajouter la logique pour traiter l'ajout du rendez-vous ici
                String client = clientTextField.getText();
                String medecin = medecinTextField.getText();
                String creneau = creneauTextField.getText();

                // Ajouter la logique pour traiter les données (par exemple, appeler une méthode du modèle)
                System.out.println("Ajout de rendez-vous pour le client: " + client +
                        ", Médecin: " + medecin +
                        ", Créneau: " + creneau);
            }
        });

        frame.setVisible(true);
    }
}
