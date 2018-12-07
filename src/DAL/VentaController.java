/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Venta;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.sql.ResultSet;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Santiago
 */
public class VentaController implements Initializable {
   @FXML
    private JFXButton btnGenerarReporte;

    @FXML
    private DatePicker DataPicker;

    @FXML
    private JFXTreeTableView<Ventas> tabla;

    @FXML
    private JFXButton btnBuscar;
    /**
     * Initializes the controller class.
     */
    conexion objconecion = new conexion();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    

    @FXML
    void Buscar(ActionEvent event) {

    }

    @FXML
    void Reporte(ActionEvent event) {

    }
    @FXML //Pones el id que necesitas
   public void CargarInfo(String dato) throws SQLException {//pon tu consulta
       ResultSet data = objconecion.EjecutarSentenciaSQL("select * from proveedor where id='"+dato+"'");
       //Creas cada columna
        JFXTreeTableColumn<Ventas, String> colum1 = new JFXTreeTableColumn<>("ID");//<--El nombre de la columna
        colum1.setPrefWidth(150);
        colum1.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Ventas, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Ventas, String> param) {
                return param.getValue().valueProperty().get().ID;//<----lo demas asi dejalo solo cambias esto en cada columna
            }
        });
        JFXTreeTableColumn<Ventas, String> colum2 = new JFXTreeTableColumn<>("Nombre del provedor");
        colum2.setPrefWidth(150);
        colum2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Ventas, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Ventas, String> param) {
                return param.getValue().valueProperty().get().nom_provedor;
            }
        });
        
        JFXTreeTableColumn<Ventas, String> colum3 = new JFXTreeTableColumn<>("Productos");
        colum3.setPrefWidth(150);
        colum3.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Ventas, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Ventas, String> param) {
                return param.getValue().valueProperty().get().productos;
            }
        });
        
        
         ObservableList<Ventas> datos = FXCollections.observableArrayList();
        
        while (data.next()) {//pones los datos para recuperar
            datos.add(new Ventas(data.getObject("id_proveedor").toString(),data.getObject("nom_proveedor").toString(), data.getObject("productos").toString()));  
        }
        final TreeItem<Ventas> root = new RecursiveTreeItem<Ventas>(datos, RecursiveTreeObject::getChildren);
        tabla.getColumns().setAll(colum1,colum2,colum3);
        tabla.setRoot(root);
        tabla.setShowRoot(false);
    }
}

     class Ventas extends RecursiveTreeObject<Ventas> {
        //Haz como una clase normal de get y set pero son los StringPropety asi como esta aqui, pero sin los get y sets
        StringProperty ID;
        StringProperty nom_provedor;
        StringProperty productos;
        
        

        public Ventas(  String id,String nom_provedor, String productos) {
            this.ID = new SimpleStringProperty(id);
            this.nom_provedor = new SimpleStringProperty(nom_provedor);
            this.productos = new SimpleStringProperty(productos);
           
            
        }

    }
//    @FXML
//    public int Agregar(Venta ventas){
//       int ComandoEjecutado=objConexion.EjecutarComandoSQL("INSERT INTO ventas(fecha_venta,total_venta,num_pizzas,num_productosextras,num_paquete,id_detalleventa)\n"+
//                "VALUES('"+ventas.getFecha_venta()+"','"+ventas.getTotal_venta()+"','"+ventas.getNum_pizza()+"','"+ventas.getNum_productoextra()+"','"+ventas.getNum_paquete()+"','"+ventas.getId_detalleventa()+"');");
//        objConexion.Desconectar();
//        return ComandoEjecutado;
//    }
//    @FXML
//    public int Eliminar(Venta ventas){
//        int ComandoEjecutado=objConexion.EjecutarComandoSQL("DELETE FROM ventas WHERE id_venta='"+ventas.getId_venta()+"'");
//        objConexion.Desconectar();
//        return ComandoEjecutado;
//    }
//    
//    @FXML
//    public int Modificar(Venta ventas){
//        int ComandoEjecutado=objConexion.EjecutarComandoSQL("UPDATE ventas SET fecha_venta='"+ventas.getFecha_venta()+
//                "',total_venta='"+ventas.getTotal_venta()+
//                "',num_paquete='"+ventas.getNum_paquete()+
//                "',num_pizzas='"+ventas.getNum_pizza()+
//                "',num_productosextras='"+ventas.getNum_productoextra()+
//                "',id_detalleventa='"+ventas.getId_detalleventa()+
//                "' WHERE id_venta="+ventas.getId_venta());
//        objConexion.Desconectar();
//        return ComandoEjecutado;
//    }
//
//    ResultSet ObtenerInformacion(String cadena) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    

