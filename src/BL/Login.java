/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import DAL.LoginController;
import DAL.WelcomeController;
import DAL.conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author Santiago
 */

public class Login {
     FXMLLoader fxml = new FXMLLoader();
      LoginController controller =fxml.getController();
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    
    conexion con = new conexion();
    private String nombre;
    ResultSet data= null;
    Boolean status=false;
    public boolean CheckVerifyAutentic(String usuario, String Contraseña){
        data = con.EjecutarSentenciaSQL("select Nombre from usuarios WHERE Usuario ='"+usuario+"' and Contraseña='"+Contraseña+"'");
        try {
            if(!data.isBeforeFirst()){
                status= false;
            }else{
            String Nombre=data.getObject("Nombre").toString();
                nombre = Nombre;
                return status=true;
            
            }
            
            //System.out.println(data.ne);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
       return status;
    }
    
    
    
}
