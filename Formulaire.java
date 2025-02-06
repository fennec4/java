

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class Formulaire {
    public static void main(String[] args) {
        // Créer une nouvelle fenêtre pour le formulaire
        JFrame frame = new JFrame("Formulaire");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(8, 2, 10, 10));

        // Ajouter les champs du formulaire
        JLabel nomLabel = new JLabel("Nom:");
        JTextField nomField = new JTextField();

        JLabel prenomLabel = new JLabel("Prénom:");
        JTextField prenomField = new JTextField();

        JLabel emailLabel = new JLabel("Adresse Email:");
        JTextField emailField = new JTextField();

        JLabel phoneLabel = new JLabel("Numéro de Téléphone:");
        JTextField phoneField = new JTextField();

        JLabel addressLabel = new JLabel("Adresse d'Habitat:");
        JTextField addressField = new JTextField();

        JLabel passwordLabel = new JLabel("Mot de Passe:");
        JPasswordField passwordField = new JPasswordField();

        // Ajouter les boutons
        JButton confirmButton = new JButton("Confirmer");
        JButton cancelButton = new JButton("Annuler");

        // Ajouter les actions des boutons
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer les valeurs des champs
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String address = addressField.getText();
                String password = new String(passwordField.getPassword());

                // Validation des champs
                if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Tous les champs doivent être remplis !");
                    return;
                }

                // Validation du numéro de téléphone (5 chiffres)
                if (!phone.matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(frame, "Le numéro de téléphone doit comporter 5 chiffres.");
                    return;
                }

                // Validation de l'email (doit se terminer par @gmail.com)
                if (!email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
                    JOptionPane.showMessageDialog(frame, "L'email doit être un @gmail.com");
                    return;
                }

                // Validation du mot de passe (au moins 6 caractères)
                if (password.length() < 6) {
                    JOptionPane.showMessageDialog(frame, "Le mot de passe doit comporter au moins 6 caractères.");
                    return;
                }

                // Si tout est valide, afficher un message de confirmation et ouvrir la fenêtre de connexion
                JOptionPane.showMessageDialog(frame, "Données Confirmées:\nNom: " + nom + " Prénom: " + prenom );
                new FenetreConnexion(nom, prenom).setVisible(true);
                frame.dispose();  // Fermer la fenêtre de formulaire après confirmation
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Réinitialiser tous les champs
                nomField.setText("");
                prenomField.setText("");
                emailField.setText("");
                phoneField.setText("");
                addressField.setText("");
                passwordField.setText("");
            }
        });

        // Ajouter les composants à la fenêtre
        frame.add(nomLabel);
        frame.add(nomField);
        frame.add(prenomLabel);
        frame.add(prenomField);
        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(phoneLabel);
        frame.add(phoneField);
        frame.add(addressLabel);
        frame.add(addressField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(confirmButton);
        frame.add(cancelButton);

        // Rendre la fenêtre visible
        frame.setVisible(true);
    }
}

class FenetreConnexion extends JFrame {
    private int tentatives = 3;  // Nombre de tentatives autorisées
    private String nom, prenom;

    public FenetreConnexion(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;

        setTitle("Fenêtre de Connexion");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        // Afficher un message de bienvenue avec le nom et le prénom
        panel.add(new JLabel("Bienvenue " + nom + " " + prenom));
        panel.add(new JLabel("Entrez votre mot de passe :"));

        // Champ pour saisir le mot de passe
        JTextField motDePasseField = new JTextField();
        panel.add(motDePasseField);

        // Boutons OK et Annuler
        JButton btnOK = new JButton("OK");
        JButton btnCancel = new JButton("Annuler");

        panel.add(btnOK);
        panel.add(btnCancel);

        add(panel);

        // Action du bouton OK
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String motDePasseSaisi = motDePasseField.getText().trim();
                String motDePasseCorrect = "password";  // Exemple de mot de passe correct

                // Vérification du mot de passe
                if (motDePasseSaisi.equals(motDePasseCorrect)) {
                    JOptionPane.showMessageDialog(FenetreConnexion.this, "Mot de passe correct !");
                    new FenetreGestionNotes().setVisible(true);  // Ouvrir la fenêtre de gestion des notes
                    dispose();  // Fermer la fenêtre de connexion
                } else {
                    tentatives--;
                    if (tentatives > 0) {
                        JOptionPane.showMessageDialog(FenetreConnexion.this, "Mot de passe incorrect. Tentatives restantes : " + tentatives);
                    } else {
                        JOptionPane.showMessageDialog(FenetreConnexion.this, "Vous avez épuisé vos tentatives.");
                        System.exit(0);  // Fermer l'application après épuisement des tentatives
                    }
                }
            }
        });

        // Action du bouton Annuler
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Fermer la fenêtre de connexion
            }
        });
    }
}

class FenetreGestionNotes extends JFrame {
    private JComboBox<String> comboModules;
    private JTextField txtNote;
    private JButton btnAjouter, btnModifier, btnSupprimer;
    private JTable tableNotes;
    private DefaultTableModel tableModel;

    public FenetreGestionNotes() {
        setTitle("Gestion des Notes");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panneau principal
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // ComboBox pour sélectionner un module
        String[] modules = { "IHM", "Economie Numérique", "Base de Données", "Algorithmes", "Structures de Données" };
        comboModules = new JComboBox<>(modules);

        // Champ de saisie pour la note
        txtNote = new JTextField(10);

        // Bouton Ajouter pour ajouter une note
        btnAjouter = new JButton("Ajouter");

        // Créer le modèle de tableau pour afficher les notes
        tableModel = new DefaultTableModel(new Object[]{"Module", "Note"}, 0);
        tableNotes = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(tableNotes);

        // Ajouter des boutons de modification et suppression
        btnModifier = new JButton("Modifier");
        btnSupprimer = new JButton("Supprimer");

        panel.add(new JLabel("Sélectionner un module :"));
        panel.add(comboModules);
        panel.add(new JLabel("Entrez la note :"));
        panel.add(txtNote);
        panel.add(btnAjouter);
        panel.add(btnModifier);
        panel.add(btnSupprimer);
        panel.add(scrollPane);  // Ajouter la table à la fenêtre

        add(panel);

        // Action du bouton Ajouter
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String module = (String) comboModules.getSelectedItem();  // Récupère le module sélectionné
                String noteText = txtNote.getText().trim();  // Récupère la note saisie et la nettoie

                // Vérifie si la note n'est pas vide
                if (!noteText.isEmpty()) {
                    try {
                        // Essaye de convertir la note en un double
                        double note = Double.parseDouble(noteText);

                        // Vérifie si la note est comprise entre 0 et 20
                        if (note >= 0 && note <= 20) {
                            // Ajoute une ligne à la table
                            tableModel.addRow(new Object[]{module, note});
                        } else {
                            JOptionPane.showMessageDialog(FenetreGestionNotes.this, "La note doit être comprise entre 0 et 20.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(FenetreGestionNotes.this, "Veuillez entrer une note valide (un nombre).", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(FenetreGestionNotes.this, "Veuillez entrer une note.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action du bouton Modifier
        btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableNotes.getSelectedRow();  // Obtenez la ligne sélectionnée
                if (selectedRow != -1) {
                    // Récupère la note actuelle et l'éditeur
                    String newNoteText = JOptionPane.showInputDialog("Entrez la nouvelle note :");
                    try {
                        double newNote = Double.parseDouble(newNoteText);
                        if (newNote >= 0 && newNote <= 20) {
                            tableModel.setValueAt(newNote, selectedRow, 1);  // Remplace la note de la ligne sélectionnée
                        } else {
                            JOptionPane.showMessageDialog(FenetreGestionNotes.this, "La note doit être comprise entre 0 et 20.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(FenetreGestionNotes.this, "Veuillez entrer une note valide (un nombre).", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(FenetreGestionNotes.this, "Veuillez sélectionner une ligne à modifier.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action du bouton Supprimer
        btnSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableNotes.getSelectedRow();  // Obtenez la ligne sélectionnée
                if (selectedRow != -1) {
                    tableModel.removeRow(selectedRow);  // Supprime la ligne sélectionnée
                } else {
                    JOptionPane.showMessageDialog(FenetreGestionNotes.this, "Veuillez sélectionner une ligne à supprimer.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
