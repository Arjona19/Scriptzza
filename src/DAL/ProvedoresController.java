/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.proveedores;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javax.swing.JTable;

/**
 * FXML Controller class
 *
 * @author Santiago
 */
public class ProvedoresController implements Initializable {
   @FXML
    private JFXButton btnEliminar;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXButton btnAgregar;

    @FXML
    private JFXButton btnModificar;

    @FXML
    private DatePicker date;

    @FXML
    private JFXTextField txt_ID;
    @FXML
    private JFXTextField txt_nombreprovedor;

    @FXML
    private JFXTextField txt_Productos;
    /**
     * Initializes the controller class.
     */
     @FXML
    private JFXTreeTableView<Prov> tabla;
ResultSet data;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
           btnAgregar.setDisable(true);
        btnModificar.setDisable(false);
        btnEliminar.setDisable(false);
        btnCancelar.setDisable(false);
           Load();
       } catch (SQLException ex) {
           Logger.getLogger(ProvedoresController.class.getName()).log(Level.SEVERE, null, ex);
       }
       tabla.setOnMouseClicked((event) -> {
             
        btnAgregar.setDisable(true);
        btnModificar.setDisable(false);
        btnEliminar.setDisable(false);
        btnCancelar.setDisable(false);
        txt_nombreprovedor.setText(tabla.getSelectionModel().getSelectedItem().getValue().nom_provedor.getValueSafe());
        txt_Productos.setText(tabla.getSelectionModel().getSelectedItem().getValue().productos.getValueSafe());
        txt_ID.setText(tabla.getSelectionModel().getSelectedItem().getValue().ID.getValueSafe());
        
       });
    }    
    
    conexion objConexion = new conexion();
    proveedores proveedor = new proveedores();
    //String cadena = "select * from tblEmpleados";
    @FXML
    public int Agregar(proveedores proveedores){
        int ComandoEjecutado=objConexion.EjecutarComandoSQL("INSERT INTO proveedor(nom_proveedor,productos)\n"+
                "VALUES('"+proveedores.getNom_proveedor()+"','"+proveedores.getProductos()+"');");
        objConexion.Desconectar();
        return ComandoEjecutado;
    }
    @FXML
    public int Eliminar(proveedores proveedores) throws SQLException{
        int ComandoEjecutado=objConexion.EjecutarComandoSQL("DELETE FROM proveedor WHERE id_proveedor='"+proveedores.getId_proveedor()+"'");
        //"+proveedores.getId_proveedor()+"
        objConexion.Desconectar();
        Load();
        return ComandoEjecutado;
        
    }
    
    @FXML
    public int Modificar(proveedores proveedores) throws SQLException{
        int ComandoEjecutado=objConexion.EjecutarComandoSQL("UPDATE proveedor SET nom_proveedor='"+proveedores.getNom_proveedor()+
                "',productos='"+proveedores.getProductos()+"' WHERE id_proveedor='"+proveedores.getId_proveedor()+"'");
        //
        objConexion.Desconectar();
        Load();
        return ComandoEjecutado;
    }
    //----------------------------------------------------------
    @FXML
    void Agregar(ActionEvent event) throws SQLException {
            Agregar(RecolectarDato());
           limpiarFormulario();
           Load();
    }

    @FXML
    void Modificar(ActionEvent event) throws SQLException {
        Modificar(RecolectarDato2());
       
        limpiarFormulario();
        
       
    }

    @FXML
    void Eliminar(ActionEvent event) throws SQLException {
        Eliminar(RecolectarDato2());
       
        limpiarFormulario();
        btnAgregar.setDisable(false);
        btnModificar.setDisable(false);
        btnEliminar.setDisable(false);
        btnCancelar.setDisable(false);
    }

    @FXML
    void Cancelar(ActionEvent event) {
        btnAgregar.setDisable(false);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        btnCancelar.setDisable(true);
        limpiarFormulario();
    }
    public void limpiarFormulario() {
        
       txt_nombreprovedor.setText(null);
       txt_Productos.setText(null);
       txt_ID.setText(null);
       
    }
     
    
    public proveedores RecolectarDato() {
        
       //proveedor.setId_proveedor(Integer.valueOf(txt_ID.getText()));
       proveedor.setNom_proveedor(txt_nombreprovedor.getText());
       proveedor.setProductos(txt_Productos.getText());
       return proveedor;
    }
    
    public proveedores RecolectarDato2() {
//       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//       String date = sdf.format("");
//       
//       String fecha = String.valueOf(date);
 
      proveedor.setId_proveedor(Integer.valueOf(txt_ID.getText()));
       proveedor.setNom_proveedor(txt_nombreprovedor.getText());
       proveedor.setProductos(txt_Productos.getText());
//       proveedor.setFecha_pago(fecha);
       return proveedor;
    }
     @FXML
    void tbl_proveedoresMouseClicked(ActionEvent event) {
           
        
    }
    
      @FXML
   public void Load() throws SQLException {
        data = objConexion.EjecutarSentenciaSQL("select * from proveedor");
        JFXTreeTableColumn<Prov, String> colum1 = new JFXTreeTableColumn<>("ID");
        colum1.setPrefWidth(150);
        colum1.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Prov, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Prov, String> param) {
                return param.getValue().valueProperty().get().ID;
            }
        });
        JFXTreeTableColumn<Prov, String> colum2 = new JFXTreeTableColumn<>("Nombre del provedor");
        colum2.setPrefWidth(150);
        colum2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Prov, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Prov, String> param) {
                return param.getValue().valueProperty().get().nom_provedor;
            }
        });
        
        JFXTreeTableColumn<Prov, String> colum3 = new JFXTreeTableColumn<>("Productos");
        colum3.setPrefWidth(150);
        colum3.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Prov, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Prov, String> param) {
                return param.getValue().valueProperty().get().productos;
            }
        });
        
        
         ObservableList<Prov> datos = FXCollections.observableArrayList();
        
        while (data.next()) {
            datos.add(new Prov(data.getObject("id_proveedor").toString(),data.getObject("nom_proveedor").toString(), data.getObject("productos").toString()));  
        }
        final TreeItem<Prov> root = new RecursiveTreeItem<Prov>(datos, RecursiveTreeObject::getChildren);
        tabla.getColumns().setAll(colum1,colum2,colum3);
        tabla.setRoot(root);
        tabla.setShowRoot(false);
    }
}

 class Prov extends RecursiveTreeObject<Prov> {

        StringProperty ID;
        StringProperty nom_provedor;
        StringProperty productos;
        
        

        public Prov(  String id,String nom_provedor, String productos) {
            this.ID = new SimpleStringProperty(id);
            this.nom_provedor = new SimpleStringProperty(nom_provedor);
            this.productos = new SimpleStringProperty(productos);
           
            
        }

    }