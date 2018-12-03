/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;
import BL.Comprador;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Katia Salazar
 */
public class UsuarioControler {
    conexion objConexion = new conexion();
    
    public int Agregar(Comprador Usuarios){
       int ComandoEjecutado=objConexion.EjecutarComandoSQL("INSERT INTO comprador(nombre,celular,fecha_nacimiento,estado,ciudad,sexo,direccion)\n"+
                "VALUES('"+Usuarios.getNombre()+"','"+Usuarios.getCelular()+"','"+Usuarios.getFecha_nacimiento()+"','"+Usuarios.getEstado()+"','"+Usuarios.getCiudad()+"','"+Usuarios.getSexo()+"','"+Usuarios.getDireccion()+"');");
        objConexion.Desconectar();
        return ComandoEjecutado;
    }

    public int Eliminar(Comprador Usuarios){
        int ComandoEjecutado=objConexion.EjecutarComandoSQL("DELETE FROM comprador WHERE id_usuario='"+Usuarios.getId_usuario()+"'");
        objConexion.Desconectar();
        return ComandoEjecutado;
    }
    
    public int Modificar(Comprador Usuarios){
        int ComandoEjecutado=objConexion.EjecutarComandoSQL("UPDATE comprador SET nombre='"+Usuarios.getNombre()+"', celular='"+Usuarios.getCelular()+"'"+ ",fecha_nacimiento='"+Usuarios.getFecha_nacimiento()+"'"+ ",estado='"+Usuarios.getEstado()+"'"+ ",ciudad='"+Usuarios.getCiudad()+"'"+ ",sexo='"+Usuarios.getSexo()+"'"+ ",direccion='"+Usuarios.getDireccion()+"'  WHERE id_usuario="+Usuarios.getId_usuario());
        objConexion.Desconectar();
        return ComandoEjecutado;
    }
    
    public DefaultTableModel Mostrar(){
        DefaultTableModel dtm =new DefaultTableModel( 
            new Object [][] {}, 
            new String [] {"ID_Usuario","Nombre","Contrasenia","Celular","Fecha Nacimiento","Estado","Ciudad","Sexo","direccion","id_tipousuario"})
    
        { @Override
        public boolean isCellEditable(int row, int column) {
        // Para no editar en el jTable
        return false;
        }};

        try{
            conexion objConexion= new conexion();
            ResultSet Resultado=objConexion.EjecutarSentenciaSQL("SELECT * FROM comprador");
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
                Resultado.getString(8),
                Resultado.getString(9),
                Resultado.getInt(10)
            };
            // Agregar Datos al JTable
            dtm.addRow(Fila);
            }
            return dtm;
        } catch(SQLException e){
            return null;
        }
    }
}
