/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import DAL.conexion;
import java.sql.ResultSet;

/**
 *
 * @author Santiago
 */
public class VentaController {
     conexion objConexion = new conexion();
    
    public int Agregar(Venta ventas){
       int ComandoEjecutado=objConexion.EjecutarComandoSQL("INSERT INTO ventas(fecha_venta, nom_comprador, num_producto, cantidad ,total)"+
                "VALUES('"+ventas.getFecha_venta()+"','"+ventas.getNom_comprador()+"','"+ventas.getNom_producto()+"','"+ventas.getCantidad()+"','"+ventas.getTotal()+"')");
        objConexion.Desconectar();
        return ComandoEjecutado;
    }

    public int Eliminar(Venta ventas){
        int ComandoEjecutado=objConexion.EjecutarComandoSQL("DELETE FROM ventas WHERE id_venta='"+ventas.getId_venta()+"'");
        objConexion.Desconectar();
        return ComandoEjecutado;
    }
    
    public int Modificar(Venta ventas){
        int ComandoEjecutado=objConexion.EjecutarComandoSQL("UPDATE ventas SET fecha_venta='"+ventas.getFecha_venta()+
                "',nom_comprador='"+ventas.getNom_comprador()+
                "',nom_producto='"+ventas.getNom_producto()+
                "',cantidad='"+ventas.getCantidad()+
                "',total='"+ventas.getTotal()+"'  WHERE id_venta="+ventas.getId_venta());
        objConexion.Desconectar();
        return ComandoEjecutado;
    }
    
  
    public ResultSet ObtenerInformacion(String consulta) {
   
            ResultSet Resultado=objConexion.EjecutarSentenciaSQL(consulta);
            objConexion.Desconectar();
        return Resultado;
    }
}
