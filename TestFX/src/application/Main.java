package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	@Override
	public void start(Stage Stage)  {
			 
			try {
				Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
				Scene scene = new Scene(root);
				// link with css
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
				Image icon = new Image("logo.png");
				Stage.setResizable(false);
				Stage.getIcons().add(icon);
				Stage.setTitle("MotorLab");
				Stage.setScene(scene);
				Stage.show(); 
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
