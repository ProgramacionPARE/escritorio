/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.awt.Color;
import java.awt.event.ItemEvent;
import javax.swing.JOptionPane;
import modelos.Auto;
import modelos.BoletoManual;
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
    Auto auto;
    public FrmCobroManual(FrmCobro parent, boolean modal, Auto auto) {
        super(parent,"Cobro manual", modal);
        initComponents();
        this.frmCobro = parent;
        this.auto = auto;
        this.txtFechaEntradaOriginal.setText(auto.getFechaEntrada());
        this.txtFechaSalidaOriginal.setText(auto.getFechaSalida());
        
        this.txtHoraEntradaOriginal.setText(auto.getHoraEntrada().substring(0,5));
        this.txtHoraSalidaOriginal.setText(auto.getHoraSalida().substring(0,5));
                
        this.txtFechaEntradaModificada.setText(auto.getFechaEntrada());
        this.txtFechaSalidaModificada.setText(auto.getFechaSalida());
        
        this.txtHoraEntradaModificada.setText(auto.getHoraEntrada().substring(0,5));
        this.txtHoraSalidaModificada.setText(auto.getHoraSalida().substring(0,5));
        this.getContentPane().setBackground(Color.white);
        
        this.lblFolio.setVisible(false);
        this.txtFolioManual.setVisible(false);
        this.txtHoraEntradaModificada.setEnabled(false);                
        this.txtFechaEntradaModificada.setEnabled(false);
            
        pack();
        setLocationRelativeTo(parent);
        this.setVisible(true);
                
    }
    
    
         private boolean validaCamposEntrada() {

        if (txtFechaEntradaModificada.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Introduce la nueva fecha de entrada.",
            "Formato erroneo",JOptionPane.WARNING_MESSAGE);
            txtFechaEntradaModificada.grabFocus();
            return false;
        }
        if (!this.txtFechaEntradaModificada.getText().matches("[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]")){
            JOptionPane.showMessageDialog(this,"La fecha de entrada debe de terner formato 0000-00-00",
            "Formato erroneo",JOptionPane.WARNING_MESSAGE);
            txtFechaEntradaModificada.grabFocus();
            return false;
        }
        
        if (txtFechaSalidaModificada.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Introduce la nueva fecha de entrada.",
            "Formato erroneo",JOptionPane.WARNING_MESSAGE);
            txtFechaSalidaModificada.grabFocus();
            return false;
        }
        if (!this.txtFechaSalidaModificada.getText().matches("[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]")){
            JOptionPane.showMessageDialog(this,"La fecha de salida debe de terner formato 0000-00-00 ",
            "Formato erroneo",JOptionPane.WARNING_MESSAGE);
            txtFechaSalidaModificada.grabFocus();
            return false;
        }

        
        if (txtHoraEntradaModificada.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Introduce la nueva hora de entrada.",
            "Formato erroneo",JOptionPane.WARNING_MESSAGE);
            txtHoraEntradaModificada.grabFocus();
            return false;
        }
        if (!this.txtHoraEntradaModificada.getText().matches("[0-9][0-9]:[0-9][0-9]")){
            JOptionPane.showMessageDialog(this,"La hora de entrada debe de tener formato 00:00 ",
            "Formato erroneo",JOptionPane.WARNING_MESSAGE);
            txtHoraEntradaModificada.grabFocus();
            return false;
        }
       
         
        if (txtHoraSalidaModificada.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Introduce la nueva fecha de salida.",
            "Formato erroneo",JOptionPane.WARNING_MESSAGE);
            txtHoraSalidaModificada.grabFocus();
            return false;
        }
        if (!this.txtHoraSalidaModificada.getText().matches("[0-9][0-9]:[0-9][0-9]")){
            JOptionPane.showMessageDialog(this,"La hora de salida debe de terner formato 00:00 ",
            "Formato erroneo",JOptionPane.WARNING_MESSAGE);
            txtHoraSalidaModificada.grabFocus();
            return false;
        }
        
        txtHoraEntradaModificada.setText(txtHoraEntradaModificada.getText()+":00");
        txtHoraSalidaModificada.setText(txtHoraSalidaModificada.getText()+":00");
        return true;
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
        btnAceptar = new javax.swing.JButton();
        cbxRazon = new javax.swing.JComboBox();
        txtFolioManual = new javax.swing.JTextField();
        lblFolio = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

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
        txtFechaEntradaOriginal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaEntradaOriginal.setName("txtFechaEntradaOriginal"); // NOI18N

        txtHoraEntradaOriginal.setEditable(false);
        txtHoraEntradaOriginal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHoraEntradaOriginal.setName("txtHoraEntradaOriginal"); // NOI18N

        txtFechaSalidaOriginal.setEditable(false);
        txtFechaSalidaOriginal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaSalidaOriginal.setName("txtFechaSalidaOriginal"); // NOI18N

        txtHoraSalidaOriginal.setEditable(false);
        txtHoraSalidaOriginal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHoraSalidaOriginal.setName("txtHoraSalidaOriginal"); // NOI18N

        txtHoraEntradaModificada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHoraEntradaModificada.setName("txtHoraEntradaModificada"); // NOI18N

        txtFechaSalidaModificada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaSalidaModificada.setName("txtFechaSalidaModificada"); // NOI18N

        jLabel5.setText("Salida");
        jLabel5.setName("jLabel5"); // NOI18N

        txtFechaEntradaModificada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaEntradaModificada.setName("txtFechaEntradaModificada"); // NOI18N

        jLabel6.setText("Entrada");
        jLabel6.setName("jLabel6"); // NOI18N

        txtHoraSalidaModificada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHoraSalidaModificada.setName("txtHoraSalidaModificada"); // NOI18N

        jLabel7.setText("Razon del boleto manual");
        jLabel7.setName("jLabel7"); // NOI18N

        btnAceptar.setBackground(new java.awt.Color(255, 255, 255));
        btnAceptar.setText("Continuar con el cobro");
        btnAceptar.setName("btnAceptar"); // NOI18N
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        cbxRazon.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Salida manual, falla del sistema.", "Salida manual, falla de energia.", "Entrada manual, no se expidio el boleto." }));
        cbxRazon.setName("cbxRazon"); // NOI18N
        cbxRazon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxRazonItemStateChanged(evt);
            }
        });

        txtFolioManual.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFolioManual.setName("txtFolioManual"); // NOI18N

        lblFolio.setText("Folio del boleto manual");
        lblFolio.setName("lblFolio"); // NOI18N

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Cancelar");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(txtFechaEntradaOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txtFechaSalidaOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(txtHoraEntradaOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txtHoraSalidaOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(cbxRazon, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtFechaEntradaModificada, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(txtFechaSalidaModificada, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtHoraEntradaModificada, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(txtHoraSalidaModificada, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblFolio)
                        .addGap(12, 12, 12)
                        .addComponent(txtFolioManual, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButton1)
                        .addGap(6, 6, 6)
                        .addComponent(btnAceptar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFechaEntradaOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaSalidaOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHoraEntradaOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoraSalidaOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addGap(6, 6, 6)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxRazon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFechaEntradaModificada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaSalidaModificada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHoraEntradaModificada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoraSalidaModificada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblFolio))
                    .addComponent(txtFolioManual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        if(this.validaCamposEntrada()){
            BoletoManual boletoManual = new BoletoManual(0,auto.getId(),this.txtFechaEntradaModificada.getText(),
                    this.txtFechaSalidaModificada.getText(),this.txtHoraEntradaModificada.getText(),
                    this.txtHoraSalidaModificada.getText(),(String)cbxRazon.getSelectedItem());
            boletoManual.guardar();
            frmCobro.auto.setIsBoletoManual(true);
            frmCobro.auto.setBoletoManual(boletoManual);
        //frmCobro.auto.setDescuento();
       // frmCobro.auto.actualizar();
        frmCobro.calcularImporte();
        this.dispose();
        }
        
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbxRazonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxRazonItemStateChanged
        if(ItemEvent.SELECTED == evt.getStateChange() ){
            if(cbxRazon.getSelectedIndex()==2){
                lblFolio.setVisible(true);
                this.txtFolioManual.setVisible(true);
                this.txtHoraEntradaModificada.setEnabled(true);                
                this.txtFechaEntradaModificada.setEnabled(true);
            }else{
                
                this.txtHoraEntradaModificada.setEnabled(false);                
                this.txtFechaEntradaModificada.setEnabled(false);
                
                lblFolio.setVisible(false);
                this.txtFolioManual.setVisible(false);
            }
        }
    }//GEN-LAST:event_cbxRazonItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JComboBox cbxRazon;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblFolio;
    private javax.swing.JTextField txtFechaEntradaModificada;
    private javax.swing.JTextField txtFechaEntradaOriginal;
    private javax.swing.JTextField txtFechaSalidaModificada;
    private javax.swing.JTextField txtFechaSalidaOriginal;
    private javax.swing.JTextField txtFolioManual;
    private javax.swing.JTextField txtHoraEntradaModificada;
    private javax.swing.JTextField txtHoraEntradaOriginal;
    private javax.swing.JTextField txtHoraSalidaModificada;
    private javax.swing.JTextField txtHoraSalidaOriginal;
    // End of variables declaration//GEN-END:variables
}
