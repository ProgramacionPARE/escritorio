

package vistas;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import modelos.Estacionamiento;


public class FrmConfiguracion extends javax.swing.JDialog {
    Estacionamiento estacionamiento;
    
    public FrmConfiguracion(java.awt.Frame parent, boolean modal,Estacionamiento estacionamiento) {
        super(parent,"Configuracion", modal);
        initComponents();
        ButtonGroup group = new ButtonGroup();
        group.add(this.rbtAutoservicio);
        group.add(this.rbtValet);
        this.estacionamiento  = estacionamiento;
        txtEstacionamiento.setText(estacionamiento.getDescripcion());
        txtDireccion.setText(estacionamiento.getDireccion());
        txtCentroCostos.setText(String.valueOf(estacionamiento.getCentroCostos()));
        txtNumeroCaseta.setText(String.valueOf(estacionamiento.getNumeroCaseta()));
        if(estacionamiento.getTipo().equals("Valet")){
            this.rbtValet.setSelected(true);
            this.rbtAutoservicio.setSelected(false);
        }else if(estacionamiento.getTipo().equals("Autoservicio")){
            this.rbtValet.setSelected(false);
            this.rbtAutoservicio.setSelected(true);
        }
        setLocationRelativeTo(parent);
        setVisible(true);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtEstacionamiento = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCentroCostos = new javax.swing.JTextField();
        txtNumeroCaseta = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        rbtValet = new javax.swing.JRadioButton();
        rbtAutoservicio = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Estacionamiento");
        jLabel1.setName("jLabel1"); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 308, -1));

        txtEstacionamiento.setEditable(false);
        txtEstacionamiento.setName("txtEstacionamiento"); // NOI18N
        getContentPane().add(txtEstacionamiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 33, 308, -1));

        jLabel2.setText("Direccion");
        jLabel2.setName("jLabel2"); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 70, 308, -1));

        txtDireccion.setEditable(false);
        txtDireccion.setName("txtDireccion"); // NOI18N
        getContentPane().add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 91, 308, -1));

        jLabel3.setText("Centro de costos");
        jLabel3.setName("jLabel3"); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 128, 139, -1));

        jLabel4.setText("Numero caseta");
        jLabel4.setName("jLabel4"); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 128, 151, -1));

        txtCentroCostos.setEditable(false);
        txtCentroCostos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCentroCostos.setName("txtCentroCostos"); // NOI18N
        getContentPane().add(txtCentroCostos, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 149, 139, -1));

        txtNumeroCaseta.setEditable(false);
        txtNumeroCaseta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNumeroCaseta.setName("txtNumeroCaseta"); // NOI18N
        getContentPane().add(txtNumeroCaseta, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 149, 151, -1));

        btnModificar.setText("Modificar");
        btnModificar.setName("btnModificar"); // NOI18N
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 308, 39));

        btnGuardar.setText("Guardar");
        btnGuardar.setName("btnGuardar"); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 308, 39));

        jLabel5.setName("jLabel5"); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 60, 10));

        rbtValet.setText("ValetParking");
        rbtValet.setEnabled(false);
        rbtValet.setName("rbtValet"); // NOI18N
        getContentPane().add(rbtValet, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        rbtAutoservicio.setText("Autoservicio");
        rbtAutoservicio.setEnabled(false);
        rbtAutoservicio.setName("rbtAutoservicio"); // NOI18N
        getContentPane().add(rbtAutoservicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, -1, -1));

        jLabel6.setText("Tipo de estacionamiento");
        jLabel6.setName("jLabel6"); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (validaCamposEntrada()){ 
            estacionamiento.setDescripcion(txtEstacionamiento.getText());
            estacionamiento.setCentroCostos(Integer.valueOf(txtCentroCostos.getText()));
            estacionamiento.setDireccion(txtDireccion.getText());
            estacionamiento.setNumeroCaseta(Integer.valueOf(txtNumeroCaseta.getText()));
            if(rbtValet.isSelected())
                estacionamiento.setTipo("Valet");
            if(rbtAutoservicio.isSelected())
                estacionamiento.setTipo("Autoservicio");
            
            estacionamiento.actualizar();
            txtCentroCostos.setEditable(false);
            txtDireccion.setEditable(false);
            txtEstacionamiento.setEditable(false);
            txtNumeroCaseta.setEditable(false);
            btnModificar.setVisible(true);
            btnGuardar.setVisible(false);
            rbtAutoservicio.setEnabled(false);
            rbtValet.setEnabled(false);
        
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        txtCentroCostos.setEditable(true);
        txtDireccion.setEditable(true);
        txtEstacionamiento.setEditable(true);
        txtNumeroCaseta.setEditable(true);
        btnModificar.setVisible(false);
        btnGuardar.setVisible(true);
        rbtAutoservicio.setEnabled(true);
        rbtValet.setEnabled(true);
        
    }//GEN-LAST:event_btnModificarActionPerformed

  private boolean validaCamposEntrada() {
        if (txtEstacionamiento.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Introduce el nombre del estacionamiento.",
            "Campo faltante",JOptionPane.WARNING_MESSAGE);
            txtEstacionamiento.grabFocus();
            return false;
        }
        if (txtDireccion.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Introduce la direccion",
            "Campo faltante",JOptionPane.WARNING_MESSAGE);
            txtDireccion.grabFocus();
            return false;
        }
        if (txtCentroCostos.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Introduce el centro de costos",
            "Campo faltante",JOptionPane.WARNING_MESSAGE);
            txtCentroCostos.grabFocus();
            return false;
        }
        if (txtNumeroCaseta.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Introduce el centro de costos",
            "Campo faltante",JOptionPane.WARNING_MESSAGE);
            txtNumeroCaseta.grabFocus();
            return false;
        }
        
        return true;
  }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JRadioButton rbtAutoservicio;
    private javax.swing.JRadioButton rbtValet;
    private javax.swing.JTextField txtCentroCostos;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEstacionamiento;
    private javax.swing.JTextField txtNumeroCaseta;
    // End of variables declaration//GEN-END:variables
}
