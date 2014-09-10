

package vistas;

import vistas.formatos.FrmBoletoPerdido;
import modelos.Auto;
import modelos.Turno;
import java.awt.Color;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelos.Estacionamiento;
import org.jdesktop.application.Action;


public class FrmBoletoPerdidoPlaca extends javax.swing.JDialog {
    Estacionamiento estacionamiento;
    Turno turno;
    Frame parent;
    public FrmBoletoPerdidoPlaca(java.awt.Frame parent, boolean modal,Turno turno,Estacionamiento estacionamiento) {
        super(parent,"Boleto perdido" ,modal);
        initComponents();
        this.parent = parent;
        this.turno = turno;
        this.estacionamiento =  estacionamiento;
        this.getContentPane().setBackground(Color.white);
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtPlaca = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Ingresa la placa de auto con boleto perdido.");
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(FrmBoletoPerdidoPlaca.class, this);
        jButton1.setAction(actionMap.get("onBuscarPlaca")); // NOI18N
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton1.setText("Buscar");
        jButton1.setName("jButton1"); // NOI18N

        txtPlaca.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        txtPlaca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlaca.setName("txtPlaca"); // NOI18N
        txtPlaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPlacaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPlaca)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPlacaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlacaKeyReleased
           txtPlaca.setText(txtPlaca.getText().toUpperCase());
    }//GEN-LAST:event_txtPlacaKeyReleased



    @Action
    public void onBuscarPlaca() {
        Auto auto = Auto.getAutoByMatricula(txtPlaca.getText());
        if(auto!= null){
            if(auto.isDentro()){
                if(auto.getBoletoPerdido()==null)
                     new FrmBoletoPerdido((JFrame) parent,true, turno, auto,estacionamiento);
                else
                    new FrmCobro(parent,true, turno, auto,estacionamiento);
                this.dispose();
                }
            else
                JOptionPane.showMessageDialog(this,"No se encontro un coche con la matricula indicada","Error", JOptionPane.WARNING_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this,"No se encontro un coche con la matricula indicada","Error", JOptionPane.WARNING_MESSAGE);
                    
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtPlaca;
    // End of variables declaration//GEN-END:variables
}
