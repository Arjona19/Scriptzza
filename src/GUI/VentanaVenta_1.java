/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import  BL.Comprador;
import BL.Venta;
import DAL.VentaControler;
import DAL.UsuarioControler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.*;

/**
 *
 * @author david
 */
public class VentanaVenta_1 extends javax.swing.JFrame {
    
    Comprador usuario = new Comprador();
    UsuarioControler usuarioc = new UsuarioControler();
   
    Venta venta = new Venta();
    VentaControler ventac = new VentaControler();
    public double TOTAL = 0;
    /*Metodo para recolectar todos los datos*/
    public Comprador RecolectarDatoUsuario() {
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       String date = sdf.format(txt_fechanacimiento.getDate());      
       String fecha = String.valueOf(date);
        
        usuario.setNombre(txt_nombre.getText());
        usuario.setCelular(txt_celular.getText());
        usuario.setFecha_nacimiento(fecha);
        usuario.setEstado(txt_estado.getText());
        usuario.setCiudad(txt_ciudad.getText());
        usuario.setSexo(cmb_sexo.getSelectedItem().toString());
        usuario.setDireccion(txt_direccion.getText());
        
        return usuario;
    }
    
    public void RecolectarDatoVenta() {
        double sumador = 0;
        
            int k = 1;
            
        String[] arreglo_pizzas =arreglo_pizzasstring.split("-");
       
        int contador_primario = 0;
        do {
            
              Date fecha = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date = sdf.format(fecha);      
            venta.setFecha_venta(date);        
            venta.setNom_comprador(txt_nombre.getText());
            venta.setNom_producto(arreglo_pizzas[k]);
            String cadena = "select precio from pizza where nom_pizza = '"+arreglo_pizzas[k]+"'";
            
             ResultSet precio = ventac.ObtenerInformacion(cadena);
             venta.setCantidad(1);
             double  totalf = 0;
                try {
                     while(precio.next()){

                        
                         sumador =  sumador +  Double.parseDouble(precio.getString ("precio"));
                         totalf =   Double.parseDouble(precio.getString("precio"));
                        

                    }


                }
                catch(Exception e) {
                    System.out.println("Error" + e);
                }
                 venta.setTotal(totalf);
                 
                k++;
                contador_primario++;
                ventac.Agregar(venta);
                
                 this.TOTAL = sumador;
        }
        while(contador_primario <  this.contador_pizzas);
      
        
    }
    /*Metodo para recolectar todos los datos*/
    public double ObtenerSuma() {
        double  totalf2 = 0;
        int k2 =1;
        String[] arreglo_pizzas =arreglo_pizzasstring.split("-");
        for (int i = 0; i < contador_pizzas; i++) {
            String cadena = "select precio from pizza where nom_pizza = '"+arreglo_pizzas[k2++]+"'";
            
             ResultSet precio = ventac.ObtenerInformacion(cadena);
             
             
                try {
                     while(precio.next()){

                        
                         totalf2 = totalf2 +precio.getDouble("precio");
                         
                        

                    }


                }
                catch(Exception e) {
                    System.out.println("Error" + e);
                }
            
        }
        
        return this.TOTAL =totalf2;
    }
    
    public void ObtenerPizzas() {
        String consulta = "select nom_pizza from pizza";
        ResultSet pizzas = ventac.ObtenerInformacion(consulta);
        
        try {
            while(pizzas.next()){
            
                cmb_pizza.addItem(pizzas.getString(1));
              
        }
        
        }
        catch(SQLException  e) {
            System.out.println(e);
        }
        
        
    }
    
    public void ObtenerIngredientes() {
        String cadena = "select nom_ingrediente from ingredientes";
        ResultSet ingredientes = ventac.ObtenerInformacion(cadena);
        
        try{
            while(ingredientes.next()){
            
                  cmb_ingredientes.addItem(ingredientes.getString(1));
              
        }
          
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }
    

    /**
     * Creates new form VentanaVenta
     */
    public VentanaVenta_1() {
        initComponents();
        ObtenerPizzas();
        ObtenerIngredientes();
        btn_lista.setEnabled(false);
        btn_venta.setEnabled(false);
       
       
    }
   public DefaultListModel modelo = new DefaultListModel();
   public int contador = 1;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jTextField9 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txt_celular = new javax.swing.JTextField();
        txt_direccion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmb_sexo = new javax.swing.JComboBox<>();
        txt_estado = new javax.swing.JTextField();
        txt_fechanacimiento = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_ciudad = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cmb_pizza = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        cmb_ingredientes = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtarea2 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista1 = new javax.swing.JList<>();
        btn_lista = new javax.swing.JButton();
        btn_borrarlista = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btn_limpiarusuario = new javax.swing.JButton();
        btn_venta = new javax.swing.JButton();
        btn_limpiarventa = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jTextField9.setText("jTextField9");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))), "Datos del Comprador", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18))); // NOI18N
        jPanel1.setLayout(null);
        jPanel1.add(txt_celular);
        txt_celular.setBounds(300, 60, 74, 30);
        jPanel1.add(txt_direccion);
        txt_direccion.setBounds(560, 110, 92, 20);

        jLabel4.setText("Fecha de nacimiento");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(490, 40, 150, 14);

        jLabel9.setText("Sexo");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(380, 90, 80, 14);

        cmb_sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hombre", "Mujer" }));
        jPanel1.add(cmb_sexo);
        cmb_sexo.setBounds(380, 110, 137, 30);
        jPanel1.add(txt_estado);
        txt_estado.setBounds(60, 110, 78, 30);
        jPanel1.add(txt_fechanacimiento);
        txt_fechanacimiento.setBounds(490, 60, 99, 30);

        jLabel6.setText("estado");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(60, 90, 90, 14);
        jPanel1.add(txt_nombre);
        txt_nombre.setBounds(130, 60, 101, 20);

        jLabel7.setText("Ciudad");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(220, 90, 80, 14);

        jLabel2.setText("Nombre");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(130, 40, 60, 14);
        jPanel1.add(txt_ciudad);
        txt_ciudad.setBounds(210, 110, 78, 30);

        jLabel3.setText("Celular");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(300, 40, 50, 14);

        jLabel8.setText("Direccion");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(560, 90, 80, 14);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos de la Compra", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 18))); // NOI18N
        jPanel2.setLayout(null);

        jLabel10.setText("Pizzas");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(20, 40, 100, 14);

        jPanel2.add(cmb_pizza);
        cmb_pizza.setBounds(20, 60, 120, 20);

        jLabel12.setText("Ingredientes");
        jPanel2.add(jLabel12);
        jLabel12.setBounds(20, 80, 80, 20);

        jPanel2.add(cmb_ingredientes);
        cmb_ingredientes.setBounds(20, 100, 150, 20);

        txtarea2.setColumns(20);
        txtarea2.setRows(5);
        jScrollPane3.setViewportView(txtarea2);

        jPanel2.add(jScrollPane3);
        jScrollPane3.setBounds(20, 130, 250, 50);

        jButton1.setText("Agregar Ingrediente");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(20, 190, 170, 23);

        jScrollPane1.setViewportView(lista1);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(310, 50, 350, 130);

        btn_lista.setText("Agregar a la lista");
        btn_lista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_listaActionPerformed(evt);
            }
        });
        jPanel2.add(btn_lista);
        btn_lista.setBounds(500, 190, 160, 23);

        btn_borrarlista.setText("Borrar toda la lista");
        btn_borrarlista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_borrarlistaActionPerformed(evt);
            }
        });
        jPanel2.add(btn_borrarlista);
        btn_borrarlista.setBounds(310, 190, 140, 23);

        jLabel1.setText("Lista de Pizzas");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(310, 30, 120, 14);

        btn_limpiarusuario.setText("Limpiar datos del comprado");
        btn_limpiarusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiarusuarioActionPerformed(evt);
            }
        });

        btn_venta.setText("Hacer Venta");
        btn_venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ventaActionPerformed(evt);
            }
        });

        btn_limpiarventa.setText("Limpiar datos de la venta");
        btn_limpiarventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiarventaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_limpiarusuario)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_venta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_limpiarventa)))))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(btn_limpiarusuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_venta)
                    .addComponent(btn_limpiarventa))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public String arreglo_pizzasstring = "";
   public int contador_pizzas = 0;
    
    
    private void btn_ventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ventaActionPerformed
        
        Ticket ventanaticket = new Ticket();
        ventanaticket.lista_comprador.setModel(modelo);
        ventanaticket.lbl_nombrecomprador.setText(txt_nombre.getText());
        ventanaticket.lbl_numerocomprador.setText(txt_celular.getText());
        ventanaticket.lbl_total_comprador.setText(String.valueOf(ObtenerSuma()));
         
        ventanaticket.setVisible(true);
       
       usuarioc.Agregar(RecolectarDatoUsuario());
       RecolectarDatoVenta();
        LimpiarFormularioUsuario();
        LimpiarFormularioVenta();
          
        
        
        
       
        
        
        
    }//GEN-LAST:event_btn_ventaActionPerformed

    private void btn_limpiarusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarusuarioActionPerformed
        LimpiarFormularioUsuario();
    }//GEN-LAST:event_btn_limpiarusuarioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        txtarea2.setText(txtarea2.getText()+""+ cmb_ingredientes.getSelectedItem().toString()+",");
        if (contador ==3) {
            jButton1.setEnabled(false);
            btn_lista.setEnabled(true);
           
            
        }
        else {
            contador++;
           
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_listaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_listaActionPerformed
      String[] validar_arreglo_ingredientes = new String[3];
      
      String cadena = txtarea2.getText();
      String[]  arreglo_ingredientes = cadena.split(",");
      
      validar_arreglo_ingredientes[0] = arreglo_ingredientes[0];
      validar_arreglo_ingredientes[1] = arreglo_ingredientes[1];
      validar_arreglo_ingredientes[2] = arreglo_ingredientes[2];
      
        if (validar_arreglo_ingredientes[0] .equals("")) {
            JOptionPane.showMessageDialog(null, "Se necesitan 3 ingredientes");
        }
        
         if (validar_arreglo_ingredientes[1] .equals("")) {
            JOptionPane.showMessageDialog(null, "Se necesitan 3 ingredientes");
        }
         
          if (validar_arreglo_ingredientes[2] .equals("")) {
            JOptionPane.showMessageDialog(null, "Se necesitan 3 ingredientes");
        }
      
        
       
          
          arreglo_pizzasstring = arreglo_pizzasstring+ "-" + cmb_pizza.getSelectedItem().toString(); 
        //  String[] prueba = arreglo_pizzasstring.split("-");
          contador_pizzas++;
          if (contador_pizzas == 1) {
            btn_venta.setEnabled(true);
        }
          
         
          //JOptionPane.showMessageDialog(null, contador_pizzas + prueba[]);
           modelo.addElement(cmb_pizza.getSelectedItem().toString() + "- Ingredientes-"+ validar_arreglo_ingredientes[0] + ", " + validar_arreglo_ingredientes[1] + ", "+ validar_arreglo_ingredientes[2] );
           lista1.setModel(modelo);
           txtarea2.setText(null);
           
           contador = 1;
           jButton1.setEnabled(true);
           btn_lista.setEnabled(false);
           
          
    }//GEN-LAST:event_btn_listaActionPerformed

    private void btn_borrarlistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_borrarlistaActionPerformed
      modelo.clear();
      txtarea2.setText(null);
      contador = 1;
      jButton1.setEnabled(true);
      btn_lista.setEnabled(false);
     arreglo_pizzasstring = "";
     contador_pizzas = 0;
       btn_venta.setEnabled(false);
     
     
     
      
      
      
      

    }//GEN-LAST:event_btn_borrarlistaActionPerformed

    private void btn_limpiarventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarventaActionPerformed
       modelo.clear();
      txtarea2.setText(null);
      contador = 1;
      jButton1.setEnabled(true);
      btn_lista.setEnabled(false);
     arreglo_pizzasstring = "";
     contador_pizzas = 0;
       btn_venta.setEnabled(false);
    }//GEN-LAST:event_btn_limpiarventaActionPerformed

    
     public void LimpiarFormularioUsuario() {
         txt_nombre.setText(null);
         txt_celular.setText(null);
         txt_fechanacimiento.setDate(null);
         txt_estado.setText(null);
         txt_ciudad.setText(null);
 
         txt_direccion.setText(null);
     }
     
     public void LimpiarFormularioVenta() {
        
        
      txtarea2.setText(null);
      contador = 1;
      jButton1.setEnabled(true);
      btn_lista.setEnabled(false);
     arreglo_pizzasstring = "";
     contador_pizzas = 0;
       btn_venta.setEnabled(false);
         
     }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaVenta_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaVenta_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaVenta_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaVenta_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaVenta_1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_borrarlista;
    private javax.swing.JButton btn_limpiarusuario;
    private javax.swing.JButton btn_limpiarventa;
    private javax.swing.JButton btn_lista;
    private javax.swing.JButton btn_venta;
    private javax.swing.JComboBox<String> cmb_ingredientes;
    private javax.swing.JComboBox<String> cmb_pizza;
    private javax.swing.JComboBox<String> cmb_sexo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField9;
    public javax.swing.JList<String> lista1;
    public javax.swing.JTextField txt_celular;
    private javax.swing.JTextField txt_ciudad;
    private javax.swing.JTextField txt_direccion;
    private javax.swing.JTextField txt_estado;
    private com.toedter.calendar.JDateChooser txt_fechanacimiento;
    public javax.swing.JTextField txt_nombre;
    private javax.swing.JTextArea txtarea2;
    // End of variables declaration//GEN-END:variables
}
