package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoginController implements Initializable {

	@FXML
    private Button exit;

    @FXML
    private PasswordField password;

    @FXML
    private Button connexion;

    @FXML
    private TextField username;

	
	@FXML
	public void loginAction(ActionEvent e) {
		Window owner = connexion.getScene().getWindow();
		try {
			
			if(username.getText().trim().matches("[aA]dmin")&& password.getText().equals("password")) {
				
				Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("FXML/gestionBank.fxml"));
				Scene scene = new Scene(root);
				Stage stage =  (Stage) ((Node)e.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.setTitle("Gestion de banque");
				
				//pour que gestion de banque etrer aux milieu 
				
				Rectangle2D rectangle2d = Screen.getPrimary().getVisualBounds();
				stage.setX((rectangle2d.getWidth()- stage.getWidth())/2);
				stage.setX((rectangle2d.getHeight()- stage.getHeight())/2);
				
				
			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Form Error!");
				alert.setHeaderText(null);
				alert.setContentText("Please enter your name");
				alert.initOwner(owner);
				alert.show();
			}
			
			
		} catch (Exception ex) {
			
		}
		
	}
	public void exit() {
		Platform.exit();
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
			
		
	}
	
	

}
