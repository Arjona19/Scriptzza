/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL.Drawer;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import DAL.Drawer.ViewChangeCallback;

/**
 * FXML Controller class
 *
 * @author Santiago
 */
public class DrawerBoxController implements Initializable {

    @FXML
    private JFXListView<Label> Listview;
     @FXML
    private JFXButton btnDrawerMiVenta;

    @FXML
    private JFXButton btnDrawerVentas;

    @FXML
    private JFXButton btnDrawerProvedores;

    @FXML
    private JFXButton btnDrawerPaquetes;

    @FXML
    private JFXButton btnDrawerControlUsuarios;

    @FXML
    private JFXButton btnDrawerIngredientes;
    ViewChangeCallback callback;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    public void setCallback(ViewChangeCallback callback) {
        this.callback = callback;
    }
    @FXML
    private void changeView(ActionEvent event) {
        JFXButton btn = (JFXButton) event.getSource();
        switch (btn.getText()) {
            case "Control de Usuarios":
                callback.updateView(btnDrawerControlUsuarios);
                break;
            case "Ingredientes":
                callback.updateView(btnDrawerIngredientes);
                break;
            case "Provedores":
                callback.updateView(btnDrawerProvedores);
                break;
            case "Paquetes":
                callback.updateView(btnDrawerPaquetes);
                break;
             case "Ventas":
                callback.updateView(btnDrawerVentas);
                break;
             case "Miventa":
                callback.updateView(btnDrawerMiVenta);
                break;
        }
    }
}
