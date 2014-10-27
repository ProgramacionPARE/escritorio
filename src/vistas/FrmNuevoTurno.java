

package vistas;

import modelos.Caja;
import modelos.Estacionamiento;
import modelos.Turno;

/**
 *
 * @author sistema
 */
public class FrmNuevoTurno extends javax.swing.JDialog {
    Caja caja;
    Turno turno;
    Estacionamiento estacionamiento;
    /**
     * Creates new form FrmNuevoTurno
     */
    public FrmNuevoTurno(java.awt.Frame parent, boolean modal,Turno turno, Estacionamiento estacionamiento) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(parent);
        this.turno = turno;
        caja = Caja.getByCaseta(estacionamiento.getCaseta().getId());
        this.txtFondoInicial.setText(String.valueOf(caja.getFondo()));
        this.setVisible(true);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtFondoInicial = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnEntrar = new javax.swing.JButton();
        cmbTurnos = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Abriendo turno");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Fondo inicial:");
        jLabel2.setName("jLabel2"); // NOI18N

        txtFondoInicial.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txtFondoInicial.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtFondoInicial.setName("txtFondoInicial"); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Turno:");
        jLabel3.setName("jLabel3"); // NOI18N

        btnEntrar.setBackground(new java.awt.Color(255, 255, 255));
        btnEntrar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnEntrar.setText("Entrar");
        btnEntrar.setName("btnEntrar"); // NOI18N
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        cmbTurnos.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cmbTurnos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Primer turno", "Segundo turno", "Tercer turno" }));
        cmbTurnos.setName("cmbTurnos"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnEntrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbTurnos, 0, 249, Short.MAX_VALUE)
                            .addComponent(txtFondoInicial))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtFondoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbTurnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnEntrar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        caja.setFondo(Float.valueOf(txtFondoInicial.getText()));
        turno.setTipoTurno((String)this.cmbTurnos.getSelectedItem());
        turno.actualizar();
        caja.actualizar();
        this.dispose();
    }//GEN-LAST:event_btnEntrarActionPerformed

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JComboBox cmbTurnos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtFondoInicial;
    // End of variables declaration//GEN-END:variables
}
