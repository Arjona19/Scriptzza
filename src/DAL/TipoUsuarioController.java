/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BL.Tipo_Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Santiago
 */
public class TipoUsuarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    conexion objConexion = new conexion();
    @FXML
    public int Agregar(Tipo_Usuario tipos){
       int ComandoEjecutado=objConexion.EjecutarComandoSQL("INSERT INTO tipo_usuario(nom_tipo)\n"+
                "VALUES('"+tipos.getNom_tipo()+"');");
        objConexion.Desconectar();
        return ComandoEjecutado;
    }
    @FXML
    public int Eliminar(Tipo_Usuario tipos){
        int ComandoEjecutado=objConexion.EjecutarComandoSQL("DELETE FROM tipo_usuario WHERE id_tipousuario='"+tipos.getId_tipousuario()+"'");
        objConexion.Desconectar();
        return ComandoEjecutado;
    }
    @FXML
    public int Modificar(Tipo_Usuario tipos){
        int ComandoEjecutado=objConexion.EjecutarComandoSQL("UPDATE tipo_usuario SET nom_tipo='"+tipos.getNom_tipo()+
                "' WHERE id_tipousuario="+tipos.getId_tipousuario());
        objConexion.Desconectar();
        return ComandoEjecutado;
    }
}
