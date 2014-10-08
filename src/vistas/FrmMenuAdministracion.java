

package vistas;

import java.awt.Color;
import java.awt.Frame;
import modelos.Estacionamiento;
import modelos.Turno;


public class FrmMenuAdministracion extends javax.swing.JDialog {
   Estacionamiento  estacionamiento;
    Turno turno;
    Frame parent;

    public FrmMenuAdministracion(java.awt.Frame parent, boolean modal,Turno turno,Estacionamiento  estacionamiento) {
        super(parent,"Administracion", modal);
        initComponents();
        this.getContentPane().setBackground(Color.white);
        this.turno = turno;
        this.parent = parent;
        this.estacionamiento =  estacionamiento;
         FrmPrincipal.nuevaVentana(this);
        setLocationRelativeTo(parent);
        setVisible(true);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnEstacionamiento = new javax.swing.JButton();
        btnDescuentos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Folios");
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Tarifas");
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnEstacionamiento.setBackground(new java.awt.Color(255, 255, 255));
        btnEstacionamiento.setText("Estacionamiento");
        btnEstacionamiento.setName("btnEstacionamiento"); // NOI18N
        btnEstacionamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstacionamientoActionPerformed(evt);
            }
        });

        btnDescuentos.setBackground(new java.awt.Color(255, 255, 255));
        btnDescuentos.setText("Descuentos");
        btnDescuentos.setName("btnDescuentos"); // NOI18N
        btnDescuentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescuentosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDescuentos, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEstacionamiento)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEstacionamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDescuentos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new FrmTarifas(this.parent,true,estacionamiento);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new FrmFolios(this.parent,true,turno,estacionamiento);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnEstacionamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstacionamientoActionPerformed
       new FrmConfiguracion(this.parent,true,estacionamiento);
    }//GEN-LAST:event_btnEstacionamientoActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
         FrmPrincipal.restaurarUltimaVentana();        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void btnDescuentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescuentosActionPerformed
        new FrmDescuentos(parent,true);
    }//GEN-LAST:event_btnDescuentosActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDescuentos;
    private javax.swing.JButton btnEstacionamiento;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    // End of variables declaration//GEN-END:variables
}
