/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.skins.JFXSpinnerSkin;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import scriptzza.Scriptzza;
import BL.Login;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 * @author Santiago
 */
public class LoginController implements Initializable {
public static Boolean Login = false;
Login objLogin = new Login();
LoginController controllerlogin;
    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane root;
    @FXML
    private JFXPasswordField txt_pass;

    @FXML
    private JFXSpinner spiner;

    @FXML
    private JFXButton btn_sesion;
    @FXML
    StackPane stackPane;
    @FXML
    private JFXTextField txt_usuario;
    @FXML
    private MaterialIconView checkDone;
    Scriptzza splash = new Scriptzza();
    Stage stage = new Stage();
    String nombre;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controllerlogin= this;
        if (!Scriptzza.isSplashLoaded) {
            loadSplashScreen();
        }
        checkDone.setVisible(false);
        spiner.setVisible(false);
           
             
       
        
    }    
     private void loadSplashScreen() {
        try {
            scriptzza.Scriptzza.isSplashLoaded = true;
            
            AnchorPane pane = FXMLLoader.load(getClass().getResource(("/GUI/splash.fxml")));
            root.getChildren().setAll(pane);
        
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
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
                    fxml.setLocation(getClass().getResource("/GUI/Login.fxml"));
                    Parent root = fxml.load();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();
                    
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     @FXML
     public void click() throws IOException{
         String usuario = txt_usuario.getText();
         String contraseña = txt_pass.getText();
         txt_pass.setDisable(true);
         txt_usuario.setDisable(true);
         btn_sesion.setDisable(true);
         spiner.setVisible(true);
         if(objLogin.CheckVerifyAutentic(usuario, contraseña)){  
             try {
             FXMLLoader fxml = new FXMLLoader();
            fxml.setLocation(getClass().getResource("/GUI/Welcome.fxml")); 
            Parent roote = fxml.load();
            WelcomeController wel = fxml.<WelcomeController>getController();
            String n = objLogin.getNombre();
            wel.setName(n);
             System.out.println(n);
            spiner.setVisible(false);
            checkDone.setVisible(true);
            Scene scene = new Scene(roote);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
             } catch (Exception e) {
             }     
            Stage stages = (Stage) root.getScene().getWindow();
            stages.close();
         }else{
            
         
             spiner.setVisible(false);
             JFXDialogLayout content = new JFXDialogLayout();
             content.setHeading(new Text("Ups"));
             content.setBackground(Background.EMPTY);
             content.setBody(new Text("Su usuario y/o contraseña son incorrectos, verifique sus datos y vuelva a intentarlo"));
             JFXDialog dialog = new JFXDialog(stackPane,content,JFXDialog.DialogTransition.CENTER);
             JFXButton button= new JFXButton("OK");
             button.setOnAction(new EventHandler<ActionEvent>() {
                 @Override
                 public void handle(ActionEvent event) {
                      txt_pass.setDisable(false);
                      txt_usuario.setDisable(false);
                       btn_sesion.setDisable(false);
                     dialog.close();
                }
             });
             content.setActions(button);
             dialog.show();
             System.out.println("Algo fallo");
         }
         
     }
     
    
    
}
