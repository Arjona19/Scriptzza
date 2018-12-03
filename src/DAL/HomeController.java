/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import com.jfoenix.controls.JFXDrawer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import DAL.Drawer.DrawerBoxController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import DAL.Drawer.ViewChangeCallback;
/**
 * FXML Controller class
 *
 * @author Santiago
 */
public class HomeController implements Initializable,ViewChangeCallback {

    /**
     * Initializes the controller class.
     */
     @FXML
    private JFXButton btn_action;
 @FXML
    private AnchorPane rootContainer;
//    @FXML
//    private JFXTreeTableView<User> tabla;
    ResultSet data;
    conexion con = new conexion();
    @FXML
    private JFXDrawer drawer;
    DrawerBoxController d= new DrawerBoxController();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Drawer/DrawerBox.fxml"));
            VBox box = loader.load();
            DrawerBoxController controller = loader.getController();
            controller.setCallback(this);
            drawer.setSidePane(box);
            drawer.open();
            AnchorPane parentContent = FXMLLoader.load(getClass().getResource(("/GUI/Provedores.fxml")));
                    rootContainer.getChildren().setAll(parentContent);
            //Load();
        } catch (Exception e) {
        }
    }
    
    @FXML
   public void Load() throws SQLException {
        data = con.EjecutarSentenciaSQL("select * from Test");
        JFXTreeTableColumn<User, String> columUsuario = new JFXTreeTableColumn<>("Usuario");
        columUsuario.setPrefWidth(150);
        columUsuario.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().valueProperty().get().Usuario;
            }
        });
        
        JFXTreeTableColumn<User, String> columContra = new JFXTreeTableColumn<>("Contraseña");
         columContra.setPrefWidth(150);
         columContra.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().Contraseña;
            }
        });
         ObservableList<User> users = FXCollections.observableArrayList();
        
        while (data.next()) {
            users.add(new User(data.getObject("Usuario").toString(), data.getObject("Contraseña").toString()));  
        }
        final TreeItem<User> root = new RecursiveTreeItem<User>(users, RecursiveTreeObject::getChildren);
//        tabla.getColumns().setAll(columUsuario,columContra);
//        tabla.setRoot(root);
//        tabla.setShowRoot(false);
    }

    @Override
    public void updateView(JFXButton Button) {
        try {
            switch (Button.getText()) {
            case "Control de Usuarios":
                
                break;
            case "Ingredientes":
                AnchorPane ingrediente = FXMLLoader.load(getClass().getResource(("/GUI/ingredientes.fxml")));
                    rootContainer.getChildren().setAll(ingrediente);
                break;
            case "Provedores":
                    AnchorPane provedores = FXMLLoader.load(getClass().getResource(("/GUI/Provedores.fxml")));
                    rootContainer.getChildren().setAll(provedores);
                break;
            case "Paquetes":
                AnchorPane paquetes = FXMLLoader.load(getClass().getResource(("/GUI/Paquete.fxml")));
                    rootContainer.getChildren().setAll(paquetes);
                break;
             case "Ventas":
               AnchorPane ventas = FXMLLoader.load(getClass().getResource(("/GUI/Venta.fxml")));
                    rootContainer.getChildren().setAll(ventas);
                break;
             case "Miventa":
                 AnchorPane MiVenta = FXMLLoader.load(getClass().getResource(("/GUI/MiVenta.fxml")));
                    rootContainer.getChildren().setAll(MiVenta);
                break;
        }
        } catch (Exception e) {
        }
       
    }
    
    class User extends RecursiveTreeObject<User> {

        StringProperty Usuario;
        StringProperty Contraseña;
        
        public User( String Usuario, String Contraseña) {
            this.Usuario = new SimpleStringProperty(Usuario);
            this.Contraseña = new SimpleStringProperty(Contraseña);
            
        }

    }
}
