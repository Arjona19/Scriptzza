/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Comprador;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import BL.CompradorController;
import BL.Venta;
import BL.VentaController;
import BL.pizza;
import GUI.VentanaVenta_1;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.util.converter.LocalDateStringConverter;
/**
 * FXML Controller class
 *
 * @author Santiago
 */

public class MiVentaController implements Initializable {
    Comprador usuario = new Comprador();
    CompradorController usuarioc = new CompradorController();
    Venta venta = new Venta();
    VentaController ventac = new VentaController();
    pizza piz= new pizza();
    /**
     * Initializes the controller class.
     */
      @FXML
    private JFXTextField txtDireccion;

    @FXML
    private JFXTextField txtCelular;

    @FXML
    private JFXButton btnLimpComp;

    @FXML
    private DatePicker FechaComprador;

    @FXML
    private JFXButton btnLimpiarDatosVenta;

    @FXML
    private JFXComboBox<String> cbo_sexo;

    @FXML
    private Label lblconcepto;

    @FXML
    private JFXButton btnAgregarCarrito;

    @FXML
    private JFXButton btnLimpiarTodo;

    @FXML
    private JFXButton btnHacerVenta;

    @FXML
    private Label lblTotal;
    
    @FXML
    private JFXComboBox<String> cbo_pizza;

    @FXML
    private JFXTextField txtNombre;

    @FXML
    private Label lblCantidad;
    @FXML
    private JFXDatePicker dateSelector;
    

    @FXML
    private JFXListView<String> idListViewIngredientes;
    
    @FXML
    private JFXListView<String> idListeViewCarrito;

    @FXML
    private JFXTextField txtEstado;
    @FXML
    private JFXComboBox<String> cbo_ingrediente;
    @FXML
    private JFXTextField txtCiudad;
     @FXML
    private JFXRadioButton rbtn_Paquete;
      @FXML
    private JFXRadioButton rbt_Armar;
      
    @FXML
    private ToggleGroup grupoRadio;
        @FXML
    private JFXButton btnAddIng;
        int cantidad=0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObtenerPizzas();
        ObtenerIngredientes();
        cbo_sexo.getItems().add("Hombre");
        cbo_sexo.getItems().add("Mujer");
        
         cbo_ingrediente.setDisable(true);
            cbo_pizza.setDisable(true);
            btnAddIng.setDisable(true);
       
       for(int i = 1 ; i < 5 ; i++) idListeViewCarrito.getItems().add("Item " + i);
        
    cbo_sexo.setPromptText("Seleccionar sexo");
    }
    
    public Comprador RecolectarDatoUsuario() {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

        String date = "44/56/2018";
       String fecha = String.valueOf(date);
        usuario.setNombre(txtNombre.getText());
        usuario.setCelular(txtCelular.getText());
        usuario.setFecha_nacimiento(fecha);
        usuario.setEstado(txtEstado.getText());
        usuario.setCiudad(txtCiudad.getText());
        usuario.setSexo(cbo_sexo.getSelectionModel().getSelectedItem());
        usuario.setDireccion(txtDireccion.getText());
        return usuario;
    }
    
     public void ObtenerPizzas() {
        String consulta = "select nom_pizza from pizza";
        ResultSet pizzas = ventac.ObtenerInformacion(consulta);
        
        try {
       
            while(pizzas.next()){
            cbo_pizza.getItems().add(pizzas.getObject("nom_pizza").toString());
                
              
        }
         
        }
        catch(SQLException  e) {
            System.out.println(e);
        }
        
        
    }
     @FXML
    void changeArmar(ActionEvent event) {
            cbo_ingrediente.setDisable(false);
            cbo_pizza.setDisable(true);
            btnAddIng.setDisable(false);
    }

    @FXML
    void changePaquete(ActionEvent event) {
        cbo_ingrediente.setDisable(true);
            cbo_pizza.setDisable(false);
            btnAddIng.setDisable(true);
    }
     public void ObtenerIngredientes() {
        String consulta = "select nom_ingrediente from ingredientes";
        ResultSet pizzas = ventac.ObtenerInformacion(consulta);
        
        try {
       
            while(pizzas.next()){
            cbo_ingrediente.getItems().add(pizzas.getObject("nom_ingrediente").toString());
                
              
        }
         
        }
        catch(SQLException  e) {
            System.out.println(e);
        }
    }
        int cont=0;
    @FXML
    void AgregarIngrediente(ActionEvent event) {
        cont++;
       if(cont<=3){
        idListViewIngredientes.getItems().add(cbo_ingrediente.getSelectionModel().getSelectedItem());
        }
        
       
    }
    public Venta RecolectarDatoVenta() {
            LocalDate dat= LocalDate.now();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        String date = sdf.format(FechaComprador.getValue().toString());      
        String fecha = String.valueOf(dat);
        venta.setFecha_venta(dat.toString());
        venta.setNom_comprador(txtNombre.getText());        
        venta.setNom_producto(cbo_pizza.getSelectionModel().getSelectedItem());
       // venta.setCantidad();
        venta.setTotal(cantidad);
        
        return venta;
    }
    
    public void LimpiarFormularioUsuario() {
         txtNombre.setText(null);
         txtCelular.setText(null);
         
         txtEstado.setText(null);
         txtCiudad.setText(null);
 
         txtDireccion.setText(null);
     }
     
     public void LimpiarFormularioVenta() {
         //txt_fechaventa.setDate(null);
         //txtCantidad.setText(null);
         
     }
     //----------------
     public void Agregar(){
         usuarioc.Agregar(RecolectarDatoUsuario());
        ventac.Agregar(RecolectarDatoVenta());
        LimpiarFormularioUsuario();
        LimpiarFormularioVenta();
     }
     //----------------
     //-----Metodos Design
    @FXML
    void HacerVenta(ActionEvent event) {
           GUI.VentanaVenta_1 ventan = new VentanaVenta_1();
           ventan.setVisible(true);
    }

    @FXML
    void LimpiarDatosComprador(ActionEvent event) {
        LimpiarFormularioUsuario();
    }

    @FXML
    void LimpiarTddo(ActionEvent event) {
           
    }
     @FXML
    void AgregarCarrito(ActionEvent event) {
            cantidad++;
    }

    @FXML
    void LimpiarDatosVenta(ActionEvent event) {
        LimpiarFormularioVenta();
    }
}
