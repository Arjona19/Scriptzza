/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.ingredientes;
import BL.proveedores;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import BL.ingredientes;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Santiago
 */
public class IngredientesController implements Initializable {
conexion objConexion = new conexion();
ingredientes ing = new ingredientes();

      @FXML
    private JFXButton btnEliminar;

    @FXML
    private JFXTextField txt_NombreIngrediente;

    @FXML
    private JFXTextField txt_ID;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXTreeTableView<Ingredientes> tabla;

    @FXML
    private JFXButton btnAgregar;

    @FXML
    private JFXButton btnModificar;

ResultSet data;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
        btnAgregar.setDisable(false);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        btnCancelar.setDisable(true);
        Load();
        tabla.setOnMouseClicked((event) -> {
             
        btnAgregar.setDisable(true);
        btnModificar.setDisable(false);
        btnEliminar.setDisable(false);
        btnCancelar.setDisable(false);
        txt_NombreIngrediente.setText(tabla.getSelectionModel().getSelectedItem().getValue().nom_ingrediente.getValueSafe());
        
        txt_ID.setText(tabla.getSelectionModel().getSelectedItem().getValue().ID.getValueSafe());
        
       });
    } catch (SQLException ex) {
        Logger.getLogger(IngredientesController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }    
     
    
    //String cadena = "select * from tblEmpleados";
    
    @FXML
    public int Agregar(ingredientes ingredientes){
        int ComandoEjecutado=objConexion.EjecutarComandoSQL("INSERT INTO ingredientes (nom_ingrediente)" +
                "VALUES('"+ingredientes.getNom_ingrediente()+"');");
        objConexion.Desconectar();
        return ComandoEjecutado;
    }
    
    @FXML
    public int Eliminar(ingredientes ingredientes){
        int ComandoEjecutado=objConexion.EjecutarComandoSQL("DELETE FROM ingredientes WHERE id_ingrediente='"+ingredientes.getId_ingrediente()+"'");
        //
        objConexion.Desconectar();
        
        return ComandoEjecutado;
        
    }
    
    @FXML
    public int Modificar(ingredientes ingredientes){
        int ComandoEjecutado=objConexion.EjecutarComandoSQL("UPDATE ingredientes SET nom_ingrediente='"+ingredientes.getNom_ingrediente()+
                "' WHERE id_ingrediente='"+ingredientes.getId_ingrediente()+"'");
       //
        objConexion.Desconectar();
        return ComandoEjecutado;
    }
    //-------------------------------------------------------
    @FXML
    void Agregar(ActionEvent event) throws SQLException {
            Agregar(RecolectarDato());
           limpiarFormulario();
           Load();
    }

    @FXML
    void Modificar(ActionEvent event) throws SQLException {
        Modificar(RecolectarDato2());
       Load();
        limpiarFormulario();
        
       
    }

    @FXML
    void Eliminar(ActionEvent event) throws SQLException {
        Eliminar(RecolectarDato2());
       Load();
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
        
       txt_NombreIngrediente.setText(null);
      
       txt_ID.setText(null);
       
    }
     
    
    public ingredientes RecolectarDato() {
        
       //proveedor.setId_proveedor(Integer.valueOf(txt_ID.getText()));
       ing.setNom_ingrediente(txt_NombreIngrediente.getText());
       
       return ing;
    }
    
    public ingredientes RecolectarDato2() {
 
      ing.setId_ingrediente(Integer.valueOf(txt_ID.getText()));
       ing.setNom_ingrediente(txt_NombreIngrediente.getText());
       
       return ing;
    }
     
    
      @FXML
   public void Load() throws SQLException {
        data = objConexion.EjecutarSentenciaSQL("select * from ingredientes");
        JFXTreeTableColumn<Ingredientes, String> colum1 = new JFXTreeTableColumn<>("ID");
        colum1.setPrefWidth(150);
        colum1.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Ingredientes, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Ingredientes, String> param) {
                return param.getValue().valueProperty().get().ID;
            }
        });
        JFXTreeTableColumn<Ingredientes, String> colum2 = new JFXTreeTableColumn<>("Ingrediente");
        colum2.setPrefWidth(150);
        colum2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Ingredientes, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Ingredientes, String> param) {
                return param.getValue().valueProperty().get().nom_ingrediente;
            }
        });
        
       
        
         ObservableList<Ingredientes> datos = FXCollections.observableArrayList();
        
        while (data.next()) {
            datos.add(new Ingredientes(data.getObject("id_ingrediente").toString(),data.getObject("nom_ingrediente").toString()));  
        }
        final TreeItem<Ingredientes> root = new RecursiveTreeItem<Ingredientes>(datos, RecursiveTreeObject::getChildren);
        tabla.getColumns().setAll(colum1,colum2);
        tabla.setRoot(root);
        tabla.setShowRoot(false);
    }
    
    
    
}
class Ingredientes extends RecursiveTreeObject<Ingredientes> {

        StringProperty ID;
        StringProperty nom_ingrediente;
        
        
        

        public Ingredientes(  String id, String nom_ingrediente) {
            this.ID = new SimpleStringProperty(id);
            this.nom_ingrediente = new SimpleStringProperty(nom_ingrediente);
            
           
            
        }

    }