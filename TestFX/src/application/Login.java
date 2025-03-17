package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class Login {
	
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private TextField nomField;
	@FXML
	private PasswordField mpField;
	public void connect(ActionEvent event) throws IOException {
		
	    String nom = nomField.getText();
		String mp = mpField.getText();
		
		if(nom.equals("Admin") && mp.equals("Admin")) {
			
			root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		}
	    
		  System.out.println("nom : "+nom);
		  System.out.println("mot de pass : "+mp);
	}
	public void supprimer() {
		nomField.clear();
		mpField.clear();
		System.out.println("supprime !!");
	}
}
