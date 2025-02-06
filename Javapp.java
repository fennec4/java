package app;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Javapp {
	
	
	// declaration des champs de saisit :
     public static JTextField nom = new JTextField();
	 public	static JTextField prenom = new JTextField();
	 public	static JTextField adresse = new JTextField();
	 public	static JTextField telephone = new JTextField();
	 public	static JTextField email = new JTextField();
	 public	static JPasswordField password = new JPasswordField();
	// declaration des buttons :
	 public static JButton confirmer = new JButton("confirmer") ;
	 public static JButton annuler = new JButton("annuler");
	// declaration du tableau :
	 public static JTable table ;
	 public static DefaultTableModel model;
	
	//formulaire de sesie :
	public static void Formulaire(){
		
	//creation du frame :
		JFrame frame = new JFrame() ;	
		frame.setSize(500,400);
	    frame.setResizable(false);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setTitle("Formulaire de validation");
		frame.setLayout(new GridLayout(7,2,10,10));
	//modification des bouttons
	    confirmer.setFocusable(false);
		annuler.setFocusable(false);
		
	// ajout des composant a la fenetre :
		frame.add(new JLabel("Nom :"));
		frame.add(nom);
		frame.add(new JLabel("Prénom :"));
		frame.add(prenom);
		frame.add(new JLabel("Adresse :"));
		frame.add(adresse);
		frame.add(new JLabel("Téléphone :"));
		frame.add(telephone);
		frame.add(new JLabel("Email :"));
		frame.add(email);
		frame.add(new JLabel("Mot de passe :"));
		frame.add(password);
		
	// ajout des bouttons a la fenetre :
		frame.add(confirmer);
		frame.add(annuler);
	
	// ajouter des action au bouttons :
		confirmer.addActionListener(e ->{ 
			// verification de validation des champs :
			if(valide()) {
				//ouvrire la fenetre de connexion
				login();
			}
			});
		annuler.addActionListener(e ->{
			// réinitialisation de tout les champs : 
			reset();
			
		});
		
		frame.setVisible(true);
		
	}
	
	//methode pour valider la cesie des champs :
	public static boolean valide() {
		//la cesie des informations :
		String nom =Javapp.nom.getText();
		String prenom =Javapp.prenom.getText();
		String adresse =Javapp.adresse.getText();
		String telephone =Javapp.telephone.getText();
		String email =Javapp.email.getText();
		String password =new String(Javapp.password.getPassword()); //type casting from char to String
		//verification que tout les champs sont valides :
		if(nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty() || telephone.isEmpty() || email.isEmpty() || password.isEmpty()) {
			JOptionPane.showMessageDialog( confirmer, "tout les champs doives ètre remplis !");
			return false;
		}
		//validatiion du telephone :
		if(!telephone.matches("\\d{10}")) {//la condition indique \\d => num (0-9) {10} => 10 chiffres
			JOptionPane.showMessageDialog( confirmer, "le numero de téléphone dois comporter 10 chifres ");
			return false;
		}
		//validation de l'email :
		String emailvalid = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		if(!email.matches(emailvalid)) {
			JOptionPane.showMessageDialog( confirmer, "saisissez un e-mail correcte " );
			return false;
		}
		//validation du mot de passe :
		if(password.length()<6) {
			JOptionPane.showMessageDialog( confirmer, "saisissez un mot de passe avec 6 characters" );
			return false;
		}
		//tout est validé :
		return true;
		
	}

	//methode pour réinitialiser les champs :
	public static  void reset() {	
	   Javapp.nom.setText(null);
	   Javapp.prenom.setText(null);
	   Javapp.adresse.setText(null);
	   Javapp.telephone.setText(null);
	   Javapp.email.setText(null);
	   Javapp.password.setText(null);
		
	}

	//methode pour login page
	public static void login() {
		JFrame login = new JFrame();
		JButton cnx = new JButton("Connexion");
		login.setTitle("Connexion");
		login.setSize(350,200);
		login.setResizable(false);
		login.setDefaultCloseOperation(login.EXIT_ON_CLOSE);
		login.add(new JLabel("Bienvenue, "+Javapp.nom.getText()+" "+Javapp.prenom.getText()));
		login.add(cnx);
		login.setLayout(new FlowLayout(FlowLayout.CENTER,10,50)); // pour mettre le boutton a cote du label
		cnx.setFocusable(false);
		cnx.addActionListener(e->{
			//input window :
			int tentative = 3;
			
			while(tentative >0) {
				String input =JOptionPane.showInputDialog(login, "Entrez votre mot de passe: ");
				if(input.equals(new String(Javapp.password.getPassword()))) {
					JOptionPane.showMessageDialog(login, "Mot de passe correcte !");
					login.dispose();
					notewindow();
					break;
				}
				else {
					tentative --;
					if(tentative == 0) {
						JOptionPane.showMessageDialog(login, "vous avez epuisez vos tentatives!");
					}
					else {
						JOptionPane.showMessageDialog(login, "Mot de passe incorrect, tentatives :"+tentative);
						login.dispose();
					}
				}
			}
		});
		login.setVisible(true);
	}
	
	//methode pour fenetre gestion des notes
	public static void notewindow() {
		JFrame window = new JFrame("Gestion des notes");
		window.setSize(600,350);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new BorderLayout());
		
		
		// creer la table pour afficher les notes :
		model = new DefaultTableModel(new String[] {"module","note"},0);
		table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);
		window.add(table,BorderLayout.CENTER);
		
		// creer la liste des modules :
		JComboBox<String> modules = new JComboBox<>(new String[] {"IHM","Economie Numérique","Base de donnée","Algorithmique","Structure de donnée"});
		JPanel panel = new JPanel();
		panel.add(new JLabel("Sélectionez le module :"));
		panel.add(modules);
		
		//creer un champ pour entrer les notes :
		JTextField notes = new JTextField(5);
		panel.add(new JLabel("Entrez la note entre 0-20 :"));
		panel.add(notes);
		
		//bouton Ajouter :
		JButton ajt = new JButton("Ajouter");
		ajt.addActionListener(e->{
			String module = (String) modules.getSelectedItem();
			String note = notes.getText();
			if(note.isEmpty()||Integer.parseInt(note)<0 || Integer.parseInt(note)>20) {
				JOptionPane.showMessageDialog(window, "La note doit etre entre 0 et 20");
			}
			else {
				model.addRow(new Object[] {module,note});
			}
		});
		panel.add(ajt);
		
		//modifier et supprimer les lignes :
		JPanel action = new JPanel();
		JButton modifier = new JButton("modifier");
		JButton supprimer = new JButton("supprimer");
		action.add(modifier);
		action.add(supprimer);
		
		//modifier une ligne :
		modifier.addActionListener(e->{
			int select = table.getSelectedRow();
			if(select != -1) {
				String newnote = JOptionPane.showInputDialog(window,"Entrez la note :");
				model.setValueAt(newnote, select, 1);
			}
			else {
				JOptionPane.showMessageDialog(window, "Selectionnez une ligne a modifier");
			}
		});
		
		//supprimer une ligne :
		supprimer.addActionListener(e->{
			int select = table.getSelectedRow();
			if(select != -1) {
				model.removeRow(select);
			}
			else {
				JOptionPane.showMessageDialog(window, "Selectionnez une ligne a supprimer");
			}
		});
		
		
		
		
		//tout ajouter a la fenetre :
		window.add(panel,BorderLayout.NORTH);
		window.add(action,BorderLayout.SOUTH);
		window.setVisible(true);
	}
	
	public static void main(String[] args) {
		Formulaire();
		
	}
}
