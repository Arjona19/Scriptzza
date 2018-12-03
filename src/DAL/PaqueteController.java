/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Paquete;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Santiago
 */
public class PaqueteController implements Initializable {
 conexion objConexion = new conexion();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    public int Agregar(Paquete paquetes){
       int ComandoEjecutado=objConexion.EjecutarComandoSQL("INSERT INTO Paquetes(nom_paquete,id_pizza,id_productoextra,precio_paquete)\n"+
                "VALUES('"+paquetes.getNom_paquete()+"','"+paquetes.getId_pizza()+"','"+paquetes.getId_productoextra()+"','"+paquetes.getPrecio_paquete()+"');");
        objConexion.Desconectar();
        return ComandoEjecutado;
    }
 @FXML
    public int Eliminar(Paquete paquetes){
        int ComandoEjecutado=objConexion.EjecutarComandoSQL("DELETE FROM Paquetes WHERE id_paquete='"+paquetes.getId_paquete()+"'");
        objConexion.Desconectar();
        return ComandoEjecutado;
    }
     @FXML
    public int Modificar(Paquete paquetes){
        int ComandoEjecutado=objConexion.EjecutarComandoSQL("UPDATE Paquetes SET nom_paquete='"+paquetes.getNom_paquete()+"',id_pizza='"+paquetes.getId_pizza()+"',id_productoextra='"+paquetes.getId_productoextra()+
        "',precio_paquete='"+paquetes.getPrecio_paquete()+
                "' WHERE id_paquete="+paquetes.getId_paquete());
        objConexion.Desconectar();
        return ComandoEjecutado;
    }
}
