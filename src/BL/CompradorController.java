/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import DAL.conexion;

/**
 *
 * @author Santiago
// */
public class CompradorController {
    conexion objConexion = new conexion();
    
    public int Agregar(Comprador Usuarios){
       int ComandoEjecutado=objConexion.EjecutarComandoSQL("INSERT INTO Comprador(nombre,celular,fecha_nacimiento,estado,ciudad,sexo,direccion)\n"+
                "VALUES('"+Usuarios.getNombre()+"','"+Usuarios.getCelular()+"','"+Usuarios.getFecha_nacimiento()+"','"+Usuarios.getEstado()+"','"+Usuarios.getCiudad()+"','"+Usuarios.getSexo()+"','"+Usuarios.getDireccion()+"');");
        objConexion.Desconectar();
        return ComandoEjecutado;
    }

    public int Eliminar(Comprador Usuarios){
        int ComandoEjecutado=objConexion.EjecutarComandoSQL("DELETE FROM Comprador WHERE id_usuario='"+Usuarios.getId_usuario()+"'");
        objConexion.Desconectar();
        return ComandoEjecutado;
    }
    
    public int Modificar(Comprador Usuarios){
        int ComandoEjecutado=objConexion.EjecutarComandoSQL("UPDATE Comprador SET nombre='"+Usuarios.getNombre()+"', celular='"+Usuarios.getCelular()+"'"+ ",fecha_nacimiento='"+Usuarios.getFecha_nacimiento()+"'"+ ",estado='"+Usuarios.getEstado()+"'"+ ",ciudad='"+Usuarios.getCiudad()+"'"+ ",sexo='"+Usuarios.getSexo()+"'"+ ",direccion='"+Usuarios.getDireccion()+"'  WHERE id_usuario="+Usuarios.getId_usuario());
        objConexion.Desconectar();
        return ComandoEjecutado;
    }
    
   
}

