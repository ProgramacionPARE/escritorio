/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vistas;

import java.util.ArrayList;
import javax.swing.JTextField;
import modelos.Tarifa;


public class FrmTarifas extends javax.swing.JDialog {
    private Tarifa tarifa;
    private ArrayList<JTextField>  txtTarifas = new ArrayList();

    
    public FrmTarifas(java.awt.Frame parent, boolean modal) {
        super(parent,"Tarifas", modal);
        initComponents();
        txtTarifas.add(txtFraccion1);
        txtTarifas.add(txtFraccion2);
        txtTarifas.add(txtFraccion3);
        txtTarifas.add(txtFraccion4);
        btnGuardar.setVisible(false);
        
        this.setLocationRelativeTo(parent);
        ArrayList<Tarifa> all = Tarifa.getAll();
       
        if (all.size()>0){ 
            tarifa = all.get(0);
            cargarDatos(tarifa);}
        else{
            tarifa = new Tarifa();
            tarifa.guardar();
            cargarDatos(tarifa);
        }
            
        setVisible(true);
    }
    
   private void cargarDatos(Tarifa tarifa){
        this.txtPrecioHora.setText(String.valueOf(tarifa.getPrecioHora()));
        this.txtTarifaMaxima.setText(String.valueOf(tarifa.getTarifaMaxima()));
        this.txtHorasCompletas.setText(String.valueOf(tarifa.getHorasCompletas()));
        this.txtBoletoPerdido.setText(String.valueOf(tarifa.getPrecioBoletoPerdido()));
        this.txtDescripcion.setText(String.valueOf(tarifa.getDescripcion()));
        int i=0;
        for(Float n:tarifa.getCostos()){
            txtTarifas.get(i).setText(String.valueOf(n));
            i++;
        }
   }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel19 = new javax.swing.JLabel();
        txtPrecioHora = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtTarifaMaxima = new javax.swing.JTextField();
        txtBoletoPerdido = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtHorasCompletas = new javax.swing.JTextField();
        lblFraccion9 = new javax.swing.JLabel();
        txtFraccion1 = new javax.swing.JTextField();
        lblFraccion10 = new javax.swing.JLabel();
        txtFraccion2 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtFraccion3 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtFraccion4 = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Precio hora:");
        jLabel19.setName("jLabel1"); // NOI18N
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 154, -1));

        txtPrecioHora.setEditable(false);
        txtPrecioHora.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        txtPrecioHora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioHora.setName("txtPrecioHora"); // NOI18N
        getContentPane().add(txtPrecioHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 154, -1));

        jLabel20.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Tarifa maxima:");
        jLabel20.setName("jLabel2"); // NOI18N
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 154, -1));

        txtTarifaMaxima.setEditable(false);
        txtTarifaMaxima.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        txtTarifaMaxima.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTarifaMaxima.setName("txtTarifaMaxima"); // NOI18N
        getContentPane().add(txtTarifaMaxima, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 154, -1));

        txtBoletoPerdido.setEditable(false);
        txtBoletoPerdido.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        txtBoletoPerdido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBoletoPerdido.setName("txtBoletoPerdido"); // NOI18N
        getContentPane().add(txtBoletoPerdido, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 154, -1));

        jLabel21.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Boleto perdido:");
        jLabel21.setName("jLabel3"); // NOI18N
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 154, -1));

        jLabel22.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Horas Completas:");
        jLabel22.setName("jLabel4"); // NOI18N
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 154, -1));

        txtHorasCompletas.setEditable(false);
        txtHorasCompletas.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        txtHorasCompletas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHorasCompletas.setName("txtHorasCompletas"); // NOI18N
        getContentPane().add(txtHorasCompletas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 154, -1));

        lblFraccion9.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        lblFraccion9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFraccion9.setText("Fraccion 1");
        lblFraccion9.setName("lblFraccion1"); // NOI18N
        getContentPane().add(lblFraccion9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 162, -1));

        txtFraccion1.setEditable(false);
        txtFraccion1.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        txtFraccion1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFraccion1.setName("txtFraccion1"); // NOI18N
        getContentPane().add(txtFraccion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 160, -1));

        lblFraccion10.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        lblFraccion10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFraccion10.setText("Fraccion 2");
        lblFraccion10.setName("lblFraccion2"); // NOI18N
        getContentPane().add(lblFraccion10, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 162, -1));

        txtFraccion2.setEditable(false);
        txtFraccion2.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        txtFraccion2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFraccion2.setName("txtFraccion2"); // NOI18N
        getContentPane().add(txtFraccion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 160, -1));

        jLabel24.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Fraccion 3");
        jLabel24.setName("jLabel8"); // NOI18N
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 162, -1));

        txtFraccion3.setEditable(false);
        txtFraccion3.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        txtFraccion3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFraccion3.setName("txtFraccion3"); // NOI18N
        getContentPane().add(txtFraccion3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 160, -1));

        jLabel25.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Fraccion 4");
        jLabel25.setName("jLabel9"); // NOI18N
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 162, -1));

        txtFraccion4.setEditable(false);
        txtFraccion4.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        txtFraccion4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFraccion4.setName("txtFraccion4"); // NOI18N
        getContentPane().add(txtFraccion4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 160, -1));

        btnModificar.setBackground(new java.awt.Color(255, 255, 255));
        btnModificar.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setName("jButton2"); // NOI18N
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 330, 41));

        btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardar.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setName("jButton3"); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 330, 41));

        jLabel23.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Descripción");
        jLabel23.setName("jLabel23"); // NOI18N
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 334, -1));

        txtDescripcion.setEditable(false);
        txtDescripcion.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        txtDescripcion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDescripcion.setName("txtDescripcion"); // NOI18N
        getContentPane().add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 330, 20));

        jLabel1.setText(" ");
        jLabel1.setName("jLabel1"); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 390, 50, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        btnModificar.setVisible(false);
        btnGuardar.setVisible(true);
        txtBoletoPerdido.setEditable(true);
        txtDescripcion.setEditable(true);
        txtFraccion1.setEditable(true);
        txtFraccion2.setEditable(true);
        txtFraccion3.setEditable(true);
        txtFraccion4.setEditable(true);
        txtHorasCompletas.setEditable(true);
        txtPrecioHora.setEditable(true);
        txtTarifaMaxima.setEditable(true);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        tarifa.setHorasCompletas(Integer.valueOf( txtHorasCompletas.getText()));
        tarifa.setPrecioBoletoPerdido(Float.valueOf( txtBoletoPerdido.getText()));
        tarifa.setPrecioHora(Float.valueOf( txtPrecioHora.getText()));
        tarifa.setDescripcion(txtDescripcion.getText());
        tarifa.setTarifaMaxima(Float.valueOf( txtTarifaMaxima.getText()));
        tarifa.setCostos(new float[]{Float.valueOf( txtFraccion1.getText()),
            Float.valueOf( txtFraccion2.getText()),Float.valueOf( txtFraccion3.getText()),
            Float.valueOf( txtFraccion4.getText())});
        tarifa.actualizar();
        
        btnModificar.setVisible(true);
        btnGuardar.setVisible(false);
        txtBoletoPerdido.setEditable(false);
        txtDescripcion.setEditable(false);
        txtFraccion1.setEditable(false);
        txtFraccion2.setEditable(false);
        txtFraccion3.setEditable(false);
        txtFraccion4.setEditable(false);
        txtHorasCompletas.setEditable(false);
        txtPrecioHora.setEditable(false);
        txtTarifaMaxima.setEditable(false);
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel lblFraccion10;
    private javax.swing.JLabel lblFraccion9;
    private javax.swing.JTextField txtBoletoPerdido;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtFraccion1;
    private javax.swing.JTextField txtFraccion2;
    private javax.swing.JTextField txtFraccion3;
    private javax.swing.JTextField txtFraccion4;
    private javax.swing.JTextField txtHorasCompletas;
    private javax.swing.JTextField txtPrecioHora;
    private javax.swing.JTextField txtTarifaMaxima;
    // End of variables declaration//GEN-END:variables
}
