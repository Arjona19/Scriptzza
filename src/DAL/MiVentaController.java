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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.util.converter.LocalDateStringConverter;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
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
    private JFXButton btnReporte;

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
        double Total=0.0,PrecioPaquete=0.0,PrecioPizza=99;
       List<String> Carrito = new ArrayList<String>();
       conexion conn = new conexion();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObtenerPizzas();
        ObtenerIngredientes();cbo_sexo.setPromptText("Seleccionar sexo");
        cbo_sexo.getItems().add("Hombre");
        cbo_sexo.getItems().add("Mujer");
             btnAgregarCarrito.setDisable(true);
            cbo_ingrediente.setDisable(true);
            cbo_pizza.setDisable(true);
            btnAddIng.setDisable(true);
         cbo_pizza.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String p = cbo_pizza.getSelectionModel().getSelectedItem();
                try {
                    ObtenerPaquete(p);
                    btnAgregarCarrito.setDisable(false);
                } catch (SQLException ex) {
                    Logger.getLogger(MiVentaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
     
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
    public void ObtenerPaquete(String p) throws SQLException{
        ResultSet con =  conn.EjecutarSentenciaSQL("select Precio, ingrediente1,ingrediente2,ingrediente3,bebida,dip from Paquete where pizza_paq='"+p+"'");
                   
                idListViewIngredientes.getItems().add(con.getObject("ingrediente1").toString());
                idListViewIngredientes.getItems().add(con.getObject("ingrediente2").toString());
                idListViewIngredientes.getItems().add(con.getObject("ingrediente3").toString());
                idListViewIngredientes.getItems().add(con.getObject("bebida").toString());
                idListViewIngredientes.getItems().add(con.getObject("dip").toString());
                PrecioPaquete = Double.parseDouble(con.getObject("Precio").toString());
                
    }
     public void ObtenerPizzas() {
        String consulta = "select pizza_paq from Paquete";
        ResultSet pizzas = ventac.ObtenerInformacion(consulta);
        
        try {
       
            while(pizzas.next()){
            cbo_pizza.getItems().add(pizzas.getObject("pizza_paq").toString()); 
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
    public void ReporteVentas() 
      {
        try 
        {  
            conexion oCon = new conexion();
            Connection con = oCon.Conectar(); 
            JasperReport reporte = null;
            String path = "src\\Reportes\\ReporteVenta.jasper";

            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jprint = JasperFillManager.fillReport(reporte,null, con);
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
        }
        catch(Exception  e) 
        {
            System.out.println("No se pudo generar el reporte de ventas. " + e);
        }

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
            Carrito.add(cbo_ingrediente.getSelectionModel().getSelectedItem());
            idListViewIngredientes.getItems().add(cbo_ingrediente.getSelectionModel().getSelectedItem());
            if(cont==3){ 
                Alert mensaje = new  Alert(Alert.AlertType.WARNING);
             mensaje.setTitle("Alerta");
             mensaje.setHeaderText("Mas de 3 ingredientes");
             mensaje.setContentText("Apartir de ahora se contaran como ingredientes extra, con un costo de $10.0");
             mensaje.showAndWait();
                btnAgregarCarrito.setDisable(false);}
        }else if(cont>3){
            Total =Total +10;
            Carrito.add(cbo_ingrediente.getSelectionModel().getSelectedItem());
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
    void SacarReporte(ActionEvent event) {
          ReporteVentas();
    }
    @FXML
    void HacerVenta(ActionEvent event) {
//           GUI.VentanaVenta_1 ventan = new VentanaVenta_1();
//           ventan.setVisible(true);
        Total=0.0;
        idListViewIngredientes.getItems().clear();
        idListeViewCarrito.getItems().clear();
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
        cont=0;
        if(rbtn_Paquete.isSelected()){
            Total = Total + PrecioPaquete;
            idListeViewCarrito.getItems().add("Paquete:"+cbo_pizza.getSelectionModel().getSelectedItem());
        }else{
            Total = Total + 99;
            idListeViewCarrito.getItems().add("Pizza - "+"Ingredientes:"+Carrito.get(0)+","+Carrito.get(1)+","+Carrito.get(2)); 
        }
       Carrito.clear();
       idListViewIngredientes.getItems().clear();
       btnAgregarCarrito.setDisable(true);
       cbo_ingrediente.setDisable(true);
       cbo_pizza.setDisable(true);
       btnAddIng.setDisable(true);
       String t = String.valueOf(Total);
       lblTotal.setText(t);
    }

    @FXML
    void LimpiarDatosVenta(ActionEvent event) {
        LimpiarFormularioVenta();
    }
}
