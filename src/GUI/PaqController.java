/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BL.Paquete;

import DAL.conexion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

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
import javafx.event.ActionEvent;
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
public class PaqController implements Initializable {
 conexion objConexion = new conexion();
 Paquete p = new Paquete();
 ResultSet data;
    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXComboBox<String> cbo_Ing1;

    @FXML
    private JFXComboBox<String> cbo_Dip;

    @FXML
    private JFXComboBox<String> cbo_Ing2;

    @FXML
    private JFXButton btn_M;

    @FXML
    private JFXTextField txt_Nombre;

    @FXML
    private JFXButton btn_A;

    @FXML
    private JFXButton btn_E;

    @FXML
    private JFXButton btn_C;

    @FXML
    private JFXComboBox<String> cbo_Beb;

    @FXML
    private JFXComboBox<String> cbo_Ing3;
        @FXML
    private Label LblID;
    @FXML
    private JFXTreeTableView<Paqu> tabla;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     try {
          btn_A.setDisable(true);
        btn_M.setDisable(false);
        btn_E.setDisable(false);
        btn_C.setDisable(false);
        CargarInformacion();
         Load();
     } catch (SQLException ex) {
         Logger.getLogger(PaqController.class.getName()).log(Level.SEVERE, null, ex);
     }
         tabla.setOnMouseClicked((event) -> {
             
        btn_A.setDisable(true);
        btn_M.setDisable(false);
        btn_E.setDisable(false);
        btn_C.setDisable(false);
        
        txt_Nombre.setText(tabla.getSelectionModel().getSelectedItem().getValue().pizza_paq.getValueSafe());
        cbo_Ing1.getSelectionModel().select(tabla.getSelectionModel().getSelectedItem().getValue().ingrediente1.getValueSafe());
        cbo_Ing2.getSelectionModel().select(tabla.getSelectionModel().getSelectedItem().getValue().ingrediente2.getValueSafe());
        cbo_Ing3.getSelectionModel().select(tabla.getSelectionModel().getSelectedItem().getValue().ingrediente3.getValueSafe());
       cbo_Beb.getSelectionModel().select(tabla.getSelectionModel().getSelectedItem().getValue().bebida.getValueSafe());
        cbo_Dip.getSelectionModel().select(tabla.getSelectionModel().getSelectedItem().getValue().Dip.getValueSafe());
       // LblID.setText(tabla.getSelectionModel().getSelectedItem().getValue().ID.getValueSafe());
        
       }); 
    }    
     @FXML
   public void Agr_1(ActionEvent event) throws SQLException {
        Agregar(RecolectarDatos());
            Limpiar();
            Load();
    }

    @FXML
      void Mod_1(ActionEvent event) throws SQLException {
          Modificar(RecolectarDatos());
        Limpiar();
        Load();
    }

    @FXML
    void Eli_1(ActionEvent event) throws SQLException {
        Eliminar(RecolectarDatos());
        Limpiar();
        Load();
    }
    @FXML
   public void Can_1(ActionEvent event) {
       btn_A.setDisable(false);
        btn_M.setDisable(true);
        btn_E.setDisable(true);
        btn_C.setDisable(true);
        Limpiar();
    }
  
//---------------------------------------
    public Paquete RecolectarDatos(){
        p.setId_paquete(LblID.getText());
        p.setNom_pizza(txt_Nombre.getText());
        p.setIngrediente1(cbo_Ing1.getSelectionModel().getSelectedItem());
        p.setIngrediente2(cbo_Ing2.getSelectionModel().getSelectedItem());
        p.setIngrediente3(cbo_Ing3.getSelectionModel().getSelectedItem());
        p.setBebida(cbo_Beb.getSelectionModel().getSelectedItem());
        p.setDip(cbo_Dip.getSelectionModel().getSelectedItem());
        return p;
    }
    public void Limpiar(){
        txt_Nombre.setText(null);
        cbo_Ing1.getSelectionModel().select("");
        cbo_Ing2.getSelectionModel().select("");
        cbo_Ing3.getSelectionModel().select("");
        cbo_Beb.getSelectionModel().select("");
        cbo_Dip.getSelectionModel().select("");
    }
    @FXML
   public void Load() throws SQLException {
        data = objConexion.EjecutarSentenciaSQL("select * from Paquete");
        JFXTreeTableColumn<Paqu, String> colum1 = new JFXTreeTableColumn<>("ID");
        colum1.setPrefWidth(150);
        colum1.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Paqu, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Paqu, String> param) {
                return param.getValue().valueProperty().get().ID;
            }
        });
        JFXTreeTableColumn<Paqu, String> colum2 = new JFXTreeTableColumn<>("Nombre del paquete");
        colum2.setPrefWidth(150);
        colum2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Paqu, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Paqu, String> param) {
                return param.getValue().valueProperty().get().pizza_paq;
            }
        });
        
        JFXTreeTableColumn<Paqu, String> colum3 = new JFXTreeTableColumn<>("Ingrediente 1");
        colum2.setPrefWidth(150);
        colum2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Paqu, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Paqu, String> param) {
                return param.getValue().valueProperty().get().ingrediente1;
            }
        });
        
        JFXTreeTableColumn<Paqu, String> colum4 = new JFXTreeTableColumn<>("Ingrediente 2");
        colum2.setPrefWidth(150);
        colum2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Paqu, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Paqu, String> param) {
                return param.getValue().valueProperty().get().ingrediente2;
            }
        });
        JFXTreeTableColumn<Paqu, String> colum5 = new JFXTreeTableColumn<>("Ingrediente 3");
        colum2.setPrefWidth(150);
        colum2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Paqu, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Paqu, String> param) {
                return param.getValue().valueProperty().get().ingrediente3;
            }
        });
        JFXTreeTableColumn<Paqu, String> colum6 = new JFXTreeTableColumn<>("Bebida");
        colum2.setPrefWidth(150);
        colum2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Paqu, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Paqu, String> param) {
                return param.getValue().valueProperty().get().bebida;
            }
        });
        JFXTreeTableColumn<Paqu, String> colum7 = new JFXTreeTableColumn<>("Dip");
        colum2.setPrefWidth(150);
        colum2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Paqu, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Paqu, String> param) {
                return param.getValue().valueProperty().get().Dip;
            }
        });
         ObservableList<Paqu> datos = FXCollections.observableArrayList();
        
        while (data.next()) {
            datos.add(new Paqu(data.getObject("ID_p").toString(),data.getObject("pizza_paq").toString(), data.getObject("ingrediente1").toString(), data.getObject("ingrediente2").toString(), data.getObject("ingrediente3").toString(), data.getObject("bebida").toString(), data.getObject("dip").toString()));  
        }
        final TreeItem<Paqu> root = new RecursiveTreeItem<Paqu>(datos, RecursiveTreeObject::getChildren);
        tabla.getColumns().setAll(colum1,colum2,colum3);
        tabla.setRoot(root);
        tabla.setShowRoot(false);
    }
   
     
  //--------------------------------------  
    public void CargarInformacion() throws SQLException{
        ResultSet Ingredientes = objConexion.EjecutarSentenciaSQL("select * from ingredientes");
        ResultSet Bebidas = objConexion.EjecutarSentenciaSQL("select * from Bebidas");
        ResultSet Dips = objConexion.EjecutarSentenciaSQL("select * from Dips");
        while(Ingredientes.next()){
            cbo_Ing1.getItems().add(Ingredientes.getObject("nom_ingrediente").toString());
            cbo_Ing2.getItems().add(Ingredientes.getObject("nom_ingrediente").toString());
            cbo_Ing3.getItems().add(Ingredientes.getObject("nom_ingrediente").toString());
            
        }
        
        while(Bebidas.next()){
            cbo_Beb.getItems().add(Bebidas.getObject("NombreBebida").toString());
           
        }
        while(Dips.next()){
         cbo_Dip.getItems().add(Dips.getObject("NombreDip").toString());
        }
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

class Paqu extends RecursiveTreeObject<Paqu> {

        StringProperty ID;
        StringProperty pizza_paq;
        StringProperty ingrediente1;
        StringProperty ingrediente2;
        StringProperty ingrediente3;
        StringProperty bebida;
        StringProperty Dip;
        
        

        public Paqu(  String id,String pizza_paq, String ingrediente1,String ingrediente2,String ingrediente3,String bebida, String Dip) {
            this.ID = new SimpleStringProperty(id);
            this.pizza_paq = new SimpleStringProperty(pizza_paq);
            this.ingrediente1 = new SimpleStringProperty(ingrediente1);
            this.ingrediente2 = new SimpleStringProperty(ingrediente2);
            this.ingrediente3 = new SimpleStringProperty(ingrediente3);
            this.bebida = new SimpleStringProperty(bebida);
            this.Dip = new SimpleStringProperty(Dip);
           
            
        }

    }