
package vistas;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Descuento;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;


public class FrmDescuentos extends javax.swing.JDialog {
    private Barcode barcode=null;

    public FrmDescuentos(java.awt.Frame parent, boolean modal) {
        super(parent,"Descuentos", modal);
        initComponents();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGenerarDescuento = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtFolioInicial = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtFolioFinal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnGenerarDescuento.setText("<html><center>Generar <br>descuento</center></html>");
        btnGenerarDescuento.setName("btnGenerarDescuento"); // NOI18N
        btnGenerarDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarDescuentoActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Folio Inicial");
        jLabel1.setName("jLabel1"); // NOI18N

        txtFolioInicial.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFolioInicial.setName("txtFolioInicial"); // NOI18N

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Folio final");
        jLabel2.setName("jLabel2"); // NOI18N

        txtFolioFinal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFolioFinal.setName("txtFolioFinal"); // NOI18N

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Descuento $");
        jLabel3.setName("jLabel3"); // NOI18N

        txtDescuento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDescuento.setName("txtDescuento"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFolioInicial)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFolioFinal, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(btnGenerarDescuento))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFolioInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFolioFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGenerarDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarDescuentoActionPerformed
        int ini = Integer.valueOf(txtFolioInicial.getText());
        int fin = Integer.valueOf(txtFolioFinal.getText());
        float montoDescuento = Float.valueOf(txtDescuento.getText());
        Descuento desc;
        for(int i= ini ; i <= fin;i++){
            try {
                desc = new Descuento(0,i,montoDescuento,true);
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



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarDescuento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtFolioFinal;
    private javax.swing.JTextField txtFolioInicial;
    // End of variables declaration//GEN-END:variables
}
