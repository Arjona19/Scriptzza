/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;
import BL.Venta;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Katia Salazar
 */
public class VentaControler {
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
    
    public DefaultTableModel Mostrar(){
        DefaultTableModel dtm =new DefaultTableModel( 
            new Object [][] {}, 
            new String [] {"ID","Fecha Venta","Total Venta Venta","Numero paquete","Numero de Pizzas","Numero de productos extra","iddetalleventa"})
    
        { @Override
        public boolean isCellEditable(int row, int column) {
        // Para no editar en el jTable
        return false;
        }};

        try{
            conexion objConexion= new conexion();
            ResultSet Resultado=objConexion.EjecutarSentenciaSQL("SELECT * FROM ventas");
            while(Resultado.next()){
            // Recuperar Datos de la BD
            Object[] Fila={
                Resultado.getString(1),
                Resultado.getString(2),
                Resultado.getString(3),
                Resultado.getString(4),
                Resultado.getString(5),
                Resultado.getString(6),
                Resultado.getString(7),
                //Resultado.getString(3)
            };
            // Agregar Datos al JTable
            dtm.addRow(Fila);
            }
            return dtm;
        } catch(SQLException e){
            return null;
        }
    }
    
    public ResultSet ObtenerInformacion(String consulta) {
   
            ResultSet Resultado=objConexion.EjecutarSentenciaSQL(consulta);
            objConexion.Desconectar();
            return Resultado;
    }
}
