/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Paquete;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Santiago
 */
public class PaqueteController implements Initializable {
 conexion objConexion = new conexion();
 Paquete p = new Paquete();
 ResultSet data;
    /**
     * Initializes the controller class.
     */
     @FXML
    private JFXComboBox<String> cbo_Dip;

    @FXML
    private JFXTextField txt_NombrePa;

    @FXML
    private JFXComboBox<String> cbo_In1;

    @FXML
    private JFXComboBox<String> cbo_In2;

    @FXML
    private JFXButton btnEliminar;

    @FXML
    private JFXButton btnModficar;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXTreeTableView<Paquetess> tabla;

    @FXML
    private JFXButton btnAgregar;

    @FXML
    private JFXComboBox<String> cbo_Ing3;

    @FXML
    private JFXComboBox<String> cbo_Bebida;
    @FXML
    private Label lblID;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     try {
          btnAgregar.setDisable(true);
        btnModficar.setDisable(false);
        btnEliminar.setDisable(false);
        btnCancelar.setDisable(false);
        CargarInformacion();
         Load();
     } catch (SQLException ex) {
         Logger.getLogger(PaqueteController.class.getName()).log(Level.SEVERE, null, ex);
     }
         tabla.setOnMouseClicked((event) -> {
             
        btnAgregar.setDisable(true);
        btnModficar.setDisable(false);
        btnEliminar.setDisable(false);
        btnCancelar.setDisable(false);
        
        txt_NombrePa.setText(tabla.getSelectionModel().getSelectedItem().getValue().pizza_paq.getValueSafe());
        cbo_In1.getSelectionModel().select(tabla.getSelectionModel().getSelectedItem().getValue().ingrediente1.getValueSafe());
        cbo_In2.getSelectionModel().select(tabla.getSelectionModel().getSelectedItem().getValue().ingrediente2.getValueSafe());
        cbo_Ing3.getSelectionModel().select(tabla.getSelectionModel().getSelectedItem().getValue().ingrediente3.getValueSafe());
       cbo_Bebida.getSelectionModel().select(tabla.getSelectionModel().getSelectedItem().getValue().bebida.getValueSafe());
        cbo_Dip.getSelectionModel().select(tabla.getSelectionModel().getSelectedItem().getValue().Dip.getValueSafe());
        lblID.setText(tabla.getSelectionModel().getSelectedItem().getValue().ID.getValueSafe());
        
       }); 
    }    
    
  
//---------------------------------------
    public Paquete RecolectarDatos(){
        p.setId_paquete(lblID.getText());
        p.setNom_pizza(txt_NombrePa.getText());
        p.setIngrediente1(cbo_In1.getSelectionModel().getSelectedItem());
        p.setIngrediente2(cbo_In2.getSelectionModel().getSelectedItem());
        p.setIngrediente3(cbo_Ing3.getSelectionModel().getSelectedItem());
        p.setBebida(cbo_Bebida.getSelectionModel().getSelectedItem());
        p.setDip(cbo_Dip.getSelectionModel().getSelectedItem());
        return p;
    }
    public void Limpiar(){
        txt_NombrePa.setText(null);
        cbo_In1.getSelectionModel().select("");
        cbo_In2.getSelectionModel().select("");
        cbo_Ing3.getSelectionModel().select("");
        cbo_Bebida.getSelectionModel().select("");
        cbo_Dip.getSelectionModel().select("");
    }
    @FXML
   public void Load() throws SQLException {
        data = objConexion.EjecutarSentenciaSQL("select * from Paquete");
        JFXTreeTableColumn<Paquetess, String> colum1 = new JFXTreeTableColumn<>("ID");
        colum1.setPrefWidth(150);
        colum1.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Paquetess, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Paquetess, String> param) {
                return param.getValue().valueProperty().get().ID;
            }
        });
        JFXTreeTableColumn<Paquetess, String> colum2 = new JFXTreeTableColumn<>("Nombre del paquete");
        colum2.setPrefWidth(150);
        colum2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Paquetess, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Paquetess, String> param) {
                return param.getValue().valueProperty().get().pizza_paq;
            }
        });
        
        JFXTreeTableColumn<Paquetess, String> colum3 = new JFXTreeTableColumn<>("Ingrediente 1");
        colum2.setPrefWidth(150);
        colum2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Paquetess, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Paquetess, String> param) {
                return param.getValue().valueProperty().get().ingrediente1;
            }
        });
        
        JFXTreeTableColumn<Paquetess, String> colum4 = new JFXTreeTableColumn<>("Ingrediente 2");
        colum2.setPrefWidth(150);
        colum2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Paquetess, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Paquetess, String> param) {
                return param.getValue().valueProperty().get().ingrediente2;
            }
        });
        JFXTreeTableColumn<Paquetess, String> colum5 = new JFXTreeTableColumn<>("Ingrediente 3");
        colum2.setPrefWidth(150);
        colum2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Paquetess, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Paquetess, String> param) {
                return param.getValue().valueProperty().get().ingrediente3;
            }
        });
        JFXTreeTableColumn<Paquetess, String> colum6 = new JFXTreeTableColumn<>("Bebida");
        colum2.setPrefWidth(150);
        colum2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Paquetess, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Paquetess, String> param) {
                return param.getValue().valueProperty().get().bebida;
            }
        });
        JFXTreeTableColumn<Paquetess, String> colum7 = new JFXTreeTableColumn<>("Dip");
        colum2.setPrefWidth(150);
        colum2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Paquetess, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Paquetess, String> param) {
                return param.getValue().valueProperty().get().Dip;
            }
        });
         ObservableList<Paquetess> datos = FXCollections.observableArrayList();
        
        while (data.next()) {
            datos.add(new Paquetess(data.getObject("ID_p").toString(),data.getObject("pizza_paq").toString(), data.getObject("ingrediente1").toString(), data.getObject("ingrediente2").toString(), data.getObject("ingrediente3").toString(), data.getObject("bebida").toString(), data.getObject("dip").toString()));  
        }
        final TreeItem<Paquetess> root = new RecursiveTreeItem<Paquetess>(datos, RecursiveTreeObject::getChildren);
        tabla.getColumns().setAll(colum1,colum2,colum3);
        tabla.setRoot(root);
        tabla.setShowRoot(false);
    }
   
     
  //--------------------------------------  
    public void CargarInformacion() throws SQLException{
        ResultSet Ingredientes = objConexion.EjecutarSentenciaSQL("select * from ingredientes");
        ResultSet Bebidas = objConexion.EjecutarSentenciaSQL("select * from Bebidas");
        ResultSet Dips = objConexion.EjecutarSentenciaSQL("select * from Dips");
        while(Ingredientes.next() || Bebidas.next()||Dips.next()){
            cbo_In1.getItems().add(Ingredientes.getObject("nom_ingrediente").toString());
            cbo_In2.getItems().add(Ingredientes.getObject("nom_ingrediente").toString());
            cbo_Ing3.getItems().add(Ingredientes.getObject("nom_ingrediente").toString());
            cbo_Bebida.getItems().add(Ingredientes.getObject("NombreBebida").toString());
            cbo_Dip.getItems().add(Ingredientes.getObject("NombreDip").toString());
        }
    
    }
   @FXML
   public void Agregar1(ActionEvent event) throws SQLException {
            Agregar(RecolectarDatos());
            Limpiar();
            Load();
    }

   @FXML
   public void Modificar1(ActionEvent event) throws SQLException {
        Modificar(RecolectarDatos());
        Limpiar();
        Load();
    }

    @FXML
   public void Eliminar1(ActionEvent event) throws SQLException {
        Eliminar(RecolectarDatos());
        Limpiar();
        Load();
    }

    @FXML
   public void Cancelar1(ActionEvent event) {
        btnAgregar.setDisable(false);
        btnModficar.setDisable(true);
        btnEliminar.setDisable(true);
        btnCancelar.setDisable(true);
        Limpiar();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public int Agregar(Paquete paquetes){
       int ComandoEjecutado=objConexion.EjecutarComandoSQL("INSERT INTO Paquete(pizza_paq,ingrediente1,ingrediente2,ingrediente3,bebida,dip)\n"+
                "VALUES('"+paquetes.getNom_pizza()+"','"+paquetes.getIngrediente1()+"','"+paquetes.getIngrediente2()+"','"+paquetes.getIngrediente3()+"','"+paquetes.getBebida()+"','"+paquetes.getDip()+"');");
        objConexion.Desconectar();
        return ComandoEjecutado;
    }

    public int Eliminar(Paquete paquetes){
        int ComandoEjecutado=objConexion.EjecutarComandoSQL("DELETE FROM Paquete WHERE ID_p='"+paquetes.getId_paquete()+"'");
        objConexion.Desconectar();
        return ComandoEjecutado;
    }
    
    public int Modificar(Paquete paquetes){
        int ComandoEjecutado=objConexion.EjecutarComandoSQL("UPDATE Paquete SET pizza_paq='"+paquetes.getNom_pizza()+"',ingrediente1='"+paquetes.getIngrediente1()+"',ingrediente2='"+paquetes.getIngrediente2()+
        "',ingrediente3='"+paquetes.getIngrediente3()+"',bebida='"+paquetes.getBebida()+"',dip='"+paquetes.getDip()+
                "' WHERE id_paquete="+paquetes.getId_paquete());
        objConexion.Desconectar();
        return ComandoEjecutado;
    }
}

class Paquetess extends RecursiveTreeObject<Paquetess> {

        StringProperty ID;
        StringProperty pizza_paq;
        StringProperty ingrediente1;
        StringProperty ingrediente2;
        StringProperty ingrediente3;
        StringProperty bebida;
        StringProperty Dip;
        
        

        public Paquetess(  String id,String pizza_paq, String ingrediente1,String ingrediente2,String ingrediente3,String bebida, String Dip) {
            this.ID = new SimpleStringProperty(id);
            this.pizza_paq = new SimpleStringProperty(pizza_paq);
            this.ingrediente1 = new SimpleStringProperty(ingrediente1);
            this.ingrediente2 = new SimpleStringProperty(ingrediente2);
            this.ingrediente3 = new SimpleStringProperty(ingrediente3);
            this.bebida = new SimpleStringProperty(bebida);
            this.Dip = new SimpleStringProperty(Dip);
           
            
        }

    }