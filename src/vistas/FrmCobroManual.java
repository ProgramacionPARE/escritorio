/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.awt.Color;
import modelos.Auto;
import modelos.Estacionamiento;
import modelos.Turno;

/**
 *
 * @author sistema
 */
public class FrmCobroManual extends javax.swing.JDialog {

    /**
     * Creates new form FrmCobroManual1
     */
    FrmCobro frmCobro;
    
    public FrmCobroManual(FrmCobro parent, boolean modal, Turno turno, Auto auto) {
        super(parent,"Cobro manual", modal);
        initComponents();
        this.frmCobro = parent;
        this.txtFechaEntradaOriginal.setText(auto.getFechaEntrada());
        this.txtFechaSalidaOriginal.setText(auto.getFechaSalida());
        
        this.txtHoraEntradaOriginal.setText(auto.getHoraEntrada());
        this.txtHoraSalidaOriginal.setText(auto.getHoraSalida());
                
        this.txtFechaEntradaModificada.setText(auto.getFechaEntrada());
        this.txtFechaSalidaModificada.setText(auto.getFechaSalida());
            
        this.txtHoraEntradaModificada.setText(auto.getHoraEntrada());
        this.txtHoraSalidaModificada.setText(auto.getHoraSalida());
        this.getContentPane().setBackground(Color.white);
        pack();
        setLocationRelativeTo(parent);
        this.setVisible(true);
                
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtFechaEntradaOriginal = new javax.swing.JTextField();
        txtHoraEntradaOriginal = new javax.swing.JTextField();
        txtFechaSalidaOriginal = new javax.swing.JTextField();
        txtHoraSalidaOriginal = new javax.swing.JTextField();
        txtHoraEntradaModificada = new javax.swing.JTextField();
        txtFechaSalidaModificada = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtFechaEntradaModificada = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtHoraSalidaModificada = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtRazon = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Fecha original del sistema");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Fecha manual");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText("Entrada");
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText("Salida");
        jLabel4.setName("jLabel4"); // NOI18N

        txtFechaEntradaOriginal.setEditable(false);
        txtFechaEntradaOriginal.setName("txtFechaEntradaOriginal"); // NOI18N

        txtHoraEntradaOriginal.setEditable(false);
        txtHoraEntradaOriginal.setName("txtHoraEntradaOriginal"); // NOI18N

        txtFechaSalidaOriginal.setEditable(false);
        txtFechaSalidaOriginal.setName("txtFechaSalidaOriginal"); // NOI18N

        txtHoraSalidaOriginal.setEditable(false);
        txtHoraSalidaOriginal.setName("txtHoraSalidaOriginal"); // NOI18N

        txtHoraEntradaModificada.setName("txtHoraEntradaModificada"); // NOI18N

        txtFechaSalidaModificada.setName("txtFechaSalidaModificada"); // NOI18N

        jLabel5.setText("Salida");
        jLabel5.setName("jLabel5"); // NOI18N

        txtFechaEntradaModificada.setName("txtFechaEntradaModificada"); // NOI18N

        jLabel6.setText("Entrada");
        jLabel6.setName("jLabel6"); // NOI18N

        txtHoraSalidaModificada.setName("txtHoraSalidaModificada"); // NOI18N

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("Razon del boleto manual");
        jLabel7.setName("jLabel7"); // NOI18N

        txtRazon.setName("txtRazon"); // NOI18N

        btnAceptar.setBackground(new java.awt.Color(255, 255, 255));
        btnAceptar.setText("Continuar con el cobro");
        btnAceptar.setName("btnAceptar"); // NOI18N
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6)
                            .addComponent(txtFechaEntradaModificada)
                            .addComponent(txtHoraEntradaModificada, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFechaSalidaModificada)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtHoraSalidaModificada)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(txtFechaEntradaOriginal)
                            .addComponent(txtHoraEntradaOriginal, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHoraSalidaOriginal)
                            .addComponent(txtFechaSalidaOriginal)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtRazon)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFechaEntradaOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaSalidaOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHoraEntradaOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoraSalidaOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFechaEntradaModificada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaSalidaModificada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHoraEntradaModificada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoraSalidaModificada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRazon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
//        frmCobro.auto.setDescuento();
       // frmCobro.auto.actualizar();
        frmCobro.calcularImporte();
        
    }//GEN-LAST:event_btnAceptarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtFechaEntradaModificada;
    private javax.swing.JTextField txtFechaEntradaOriginal;
    private javax.swing.JTextField txtFechaSalidaModificada;
    private javax.swing.JTextField txtFechaSalidaOriginal;
    private javax.swing.JTextField txtHoraEntradaModificada;
    private javax.swing.JTextField txtHoraEntradaOriginal;
    private javax.swing.JTextField txtHoraSalidaModificada;
    private javax.swing.JTextField txtHoraSalidaOriginal;
    private javax.swing.JTextField txtRazon;
    // End of variables declaration//GEN-END:variables
}
