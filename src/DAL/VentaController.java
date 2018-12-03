/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Venta;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Santiago
 */
public class VentaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    conexion objConexion = new conexion();
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
}
