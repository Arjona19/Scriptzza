/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Santiago
 */
public class TiketController implements Initializable {
    MiVentaController controller;
    /**
     * @return the Total
     */
    public String getTotal() {
        return Total;
    }

    /**
     * @param Total the Total to set
     */
    public void setTotal(String Total) {
        this.Total = Total;
       lblTotal.setText(Total);
    }

    /**
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre=Nombre;
       lblNombre.setText(Nombre);
    }

    /**
     * @return the Celular
     */
    public String getCelular() {
        return Celular;
    }

    /**
     * @param Celular the Celular to set
     */
    public void setCelular(String Celular) {
        this.Celular = Celular;
      LblCelular.setText(Celular);
    }

    /**
     * Initializes the controller class.
     */
        @FXML
    private Label lblNombre;
   @FXML
    private Label lblTotal;
    @FXML
    private JFXListView<String> ListProductos;

    @FXML
    private Label LblCelular;
    private String Nombre;
    private String Celular;
    private String Total;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
       
        
        
    }    
    
    public void Agregar(ListView items){
        ListProductos.getItems().setAll(items.getItems());
    }
}
