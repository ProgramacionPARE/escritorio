
package vistas;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Descuento;
import modelos.Estacionamiento;
import modelos.Progresivo;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;


public class FrmDescuentos extends javax.swing.JDialog {
    private Barcode barcode=null;
    private Estacionamiento estacionamiento;
    
    public FrmDescuentos(java.awt.Frame parent, boolean modal,Estacionamiento estacionamiento) {
        super(parent,"Descuentos", modal);
        initComponents();
        this.estacionamiento = estacionamiento;
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGenerarDescuento = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtNoCortesias = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbxTipoCortesia = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnGenerarDescuento.setText("<html><center>Generar <br>descuento</center></html>");
        btnGenerarDescuento.setName("btnGenerarDescuento"); // NOI18N
        btnGenerarDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarDescuentoActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Tipo de cortesia");
        jLabel3.setName("jLabel3"); // NOI18N

        txtNoCortesias.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNoCortesias.setName("txtNoCortesias"); // NOI18N

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Numero de cortesias");
        jLabel4.setName("jLabel4"); // NOI18N

        cbxTipoCortesia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cortesia total", "Cortesia por tiempo" }));
        cbxTipoCortesia.setName("cbxTipoCortesia"); // NOI18N
        cbxTipoCortesia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTipoCortesiaActionPerformed(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Descuento $");
        jLabel5.setName("jLabel5"); // NOI18N

        txtDescuento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDescuento.setName("txtDescuento"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGenerarDescuento)
                    .addComponent(cbxTipoCortesia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNoCortesias, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                            .addComponent(txtDescuento, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxTipoCortesia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNoCortesias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGenerarDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarDescuentoActionPerformed
        float montoDescuento = Float.valueOf(txtNoCortesias.getText());
        int noCortesias = Integer.valueOf(txtNoCortesias.getText());
        Descuento desc;
        for(int i= 0 ; i < noCortesias;i++){
            try {
                desc = new Descuento(Long.valueOf(Progresivo.getUltimoProgresivo(estacionamiento.getCaseta(),"cortesia")),montoDescuento,true);
                desc.guardar();
                barcode = BarcodeFactory.createCode128B(desc.getClave()+ String.format("%06d", desc.getFolio()));
                barcode.setBarHeight(40);
                barcode.setBarWidth(1);
                barcode.setDrawingText(false);
                File f = new File("descuentos/"+String.format("%06d", desc.getFolio())+".png");
                BarcodeImageHandler.savePNG(barcode, f);
            } catch (BarcodeException ex) {
                Logger.getLogger(FrmDescuentos.class.getName()).log(Level.SEVERE, null, ex);
            }catch (OutputException ex) {
                    Logger.getLogger(FrmDescuentos.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }//GEN-LAST:event_btnGenerarDescuentoActionPerformed

    private void cbxTipoCortesiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoCortesiaActionPerformed
        if(cbxTipoCortesia.getSelectedIndex() == 0){
            jLabel5.setVisible(false);
            txtDescuento.setVisible(false);
        }else{
            jLabel5.setVisible(true);
            txtDescuento.setVisible(true);
        }
    }//GEN-LAST:event_cbxTipoCortesiaActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarDescuento;
    private javax.swing.JComboBox cbxTipoCortesia;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtNoCortesias;
    // End of variables declaration//GEN-END:variables
}
