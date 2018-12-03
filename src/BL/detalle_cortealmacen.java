/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

/**
 *
 * @author david
 */
public class detalle_cortealmacen {
    public int id_detallecorte;
    public int id_ingrediente;
    public int cantidad_inicial;
    public int cantidad_sobrante;
    public int total_dia;
    public String fecha_corte;

    public int getId_detallecorte() {
        return id_detallecorte;
    }

    public void setId_detallecorte(int id_detallecorte) {
        this.id_detallecorte = id_detallecorte;
    }

    public int getId_ingrediente() {
        return id_ingrediente;
    }

    public void setId_ingrediente(int id_ingrediente) {
        this.id_ingrediente = id_ingrediente;
    }

    public int getCantidad_inicial() {
        return cantidad_inicial;
    }

    public void setCantidad_inicial(int cantidad_inicial) {
        this.cantidad_inicial = cantidad_inicial;
    }

    public int getCantidad_sobrante() {
        return cantidad_sobrante;
    }

    public void setCantidad_sobrante(int cantidad_sobrante) {
        this.cantidad_sobrante = cantidad_sobrante;
    }

    public int getTotal_dia() {
        return total_dia;
    }

    public void setTotal_dia(int total_dia) {
        this.total_dia = total_dia;
    }

    public String getFecha_corte() {
        return fecha_corte;
    }

    public void setFecha_corte(String fecha_corte) {
        this.fecha_corte = fecha_corte;
    }
    
    
}
