/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import DAL.conexion;
/**
 * FXML Controller class
 *
 * @author Santiago
 */
public class SplashController implements Initializable {

    /**
     * Initializes the controller class.
     */
    conexion con = new conexion();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con.Conectar();
    }    
    
}
