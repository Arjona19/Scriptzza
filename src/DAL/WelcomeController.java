/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;


import BL.Login;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import scriptzza.Scriptzza;

/**
 * FXML Controller class
 *
 * @author Santiago
 */

public class WelcomeController implements Initializable {
private String name;
    /**
     * @param name the name to set
     */
LoginController controller;

    public void setName(String name) {
        this.name = name;
        Lbl_user.setText(name);
    }
Login objLogin = new Login();
    @FXML
    private Label Lbl_user;
     @FXML
    private AnchorPane root;
    /**
     * Initializes the controller class.
     */
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), root);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), root);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();
            
            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });

            fadeOut.setOnFinished((e) -> {
                try {
                     // get a handle to the stage
                        Stage stages = (Stage) root.getScene().getWindow();
                        // do what you have to do
                        stages.close();
                    FXMLLoader fxml = new FXMLLoader();
                    fxml.setLocation(getClass().getResource("/GUI/Home.fxml"));
                    Parent roote = fxml.load();
                    Scene scene = new Scene(roote);
                    Stage stage = new Stage();
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();
                  
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
    }    
     
   
}
