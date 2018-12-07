/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 public class conexion {
    String CadenaConexion="C:\\Users\\Santiago\\Documents\\Administracion del tiempo\\Proyecto_Pizeria.db";
    Connection conn=null;
 public conexion(){ }
 public Connection Conectar(){
        try {Class.forName("org.sqlite.JDBC");
        
        this.conn= DriverManager.getConnection("jdbc:sqlite:"+CadenaConexion);
         System.out.println("Conectado a la BD");
        } catch (Exception e) {
        System.out.println("Problemas al conectarse"+e);
    }
    return this.conn;
 }
public void Desconectar(){
    this.conn=null;
}
public int EjecutarComandoSQL(String Sentencia){
        try {
            PreparedStatement pstm=Conectar().prepareStatement(Sentencia);
            pstm.execute();
            return 1;
        }catch (SQLException e) {
            System.out.println(e);
            return 0;
        }
}
public ResultSet EjecutarSentenciaSQL(String Sentencia){
    try {
       
        PreparedStatement pstm=Conectar().prepareStatement(Sentencia);
        pstm.execute();
        ResultSet Resultado=pstm.executeQuery();
        return Resultado;
    }catch (SQLException e) {
        System.out.println(e);
        return null;
    }
   }
}

