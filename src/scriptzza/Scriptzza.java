/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scriptzza;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Santiago
 */
public class Scriptzza extends Application {
public static Boolean isSplashLoaded = false;
    
    Stage app= null;
    @Override
    public void start(Stage stage) throws Exception {
        this.app= stage;
        FXMLLoader fxml = new FXMLLoader();
        fxml.setLocation(getClass().getResource("/GUI/Login.fxml"));
        Parent root = fxml.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.setResizable(false);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
