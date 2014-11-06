

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
        group.add(this.rbtValetMasivo);
        
        this.estacionamiento  = estacionamiento;
        txtEstacionamiento.setText(estacionamiento.getDescripcion());
        txtDireccion.setText(estacionamiento.getDireccion());
        txtCentroCostos.setText(String.valueOf(estacionamiento.getCentroCostos()));
        txtNumeroCaseta.setText(String.valueOf(estacionamiento.getNumeroCaseta()));
        if(estacionamiento.getTipo().equals("Valet")){
            this.rbtValet.setSelected(true);
            this.rbtAutoservicio.setSelected(false);
            this.rbtValetMasivo.setSelected(false);
        }else if(estacionamiento.getTipo().equals("Autoservicio")){
            this.rbtValet.setSelected(false);
            this.rbtAutoservicio.setSelected(false);
            this.rbtValetMasivo.setSelected(true);
        }else if(estacionamiento.getTipo().equals("ValetMasivo")){
            this.rbtValet.setSelected(false);
            this.rbtAutoservicio.setSelected(false);
            this.rbtValetMasivo.setSelected(true);
            
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
        rbtValet = new javax.swing.JRadioButton();
        rbtAutoservicio = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        rbtValetMasivo = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        txtDireccionIP = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Estacionamiento");
        jLabel1.setName("jLabel1"); // NOI18N

        txtEstacionamiento.setEditable(false);
        txtEstacionamiento.setName("txtEstacionamiento"); // NOI18N

        jLabel2.setText("Direccion");
        jLabel2.setName("jLabel2"); // NOI18N

        txtDireccion.setEditable(false);
        txtDireccion.setName("txtDireccion"); // NOI18N

        jLabel3.setText("Centro de costos");
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText("Numero caseta");
        jLabel4.setName("jLabel4"); // NOI18N

        txtCentroCostos.setEditable(false);
        txtCentroCostos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCentroCostos.setName("txtCentroCostos"); // NOI18N

        txtNumeroCaseta.setEditable(false);
        txtNumeroCaseta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNumeroCaseta.setName("txtNumeroCaseta"); // NOI18N

        btnModificar.setText("Modificar");
        btnModificar.setName("btnModificar"); // NOI18N
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.setName("btnGuardar"); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        rbtValet.setText("ValetParking");
        rbtValet.setEnabled(false);
        rbtValet.setName("rbtValet"); // NOI18N

        rbtAutoservicio.setText("Autoservicio");
        rbtAutoservicio.setEnabled(false);
        rbtAutoservicio.setName("rbtAutoservicio"); // NOI18N

        jLabel6.setText("Tipo de estacionamiento");
        jLabel6.setName("jLabel6"); // NOI18N

        rbtValetMasivo.setText("ValetMasivo");
        rbtValetMasivo.setEnabled(false);
        rbtValetMasivo.setName("rbtValetMasivo"); // NOI18N

        jLabel7.setText("Direccion IP");
        jLabel7.setName("jLabel7"); // NOI18N

        txtDireccionIP.setEditable(false);
        txtDireccionIP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDireccionIP.setName("txtDireccionIP"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(txtEstacionamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(txtCentroCostos, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(txtNumeroCaseta, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(txtDireccionIP, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel6))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(rbtValet)
                .addGap(34, 34, 34)
                .addComponent(rbtAutoservicio))
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(rbtValetMasivo))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addComponent(txtEstacionamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(6, 6, 6)
                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCentroCostos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumeroCaseta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jLabel7)
                .addGap(5, 5, 5)
                .addComponent(txtDireccionIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel6)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtValet)
                    .addComponent(rbtAutoservicio))
                .addGap(7, 7, 7)
                .addComponent(rbtValetMasivo)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

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
            if(rbtValetMasivo.isSelected())
                estacionamiento.setTipo("ValetMasivo");
            
            estacionamiento.actualizar();
            txtCentroCostos.setEditable(false);
            txtDireccion.setEditable(false);
            txtEstacionamiento.setEditable(false);
            txtNumeroCaseta.setEditable(false);
            btnModificar.setVisible(true);
            btnGuardar.setVisible(false);
            rbtAutoservicio.setEnabled(false);
            rbtValet.setEnabled(false);
            rbtValetMasivo.setEnabled(false);
        
            
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
        rbtValetMasivo.setEnabled(true);
        
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JRadioButton rbtAutoservicio;
    private javax.swing.JRadioButton rbtValet;
    private javax.swing.JRadioButton rbtValetMasivo;
    private javax.swing.JTextField txtCentroCostos;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDireccionIP;
    private javax.swing.JTextField txtEstacionamiento;
    private javax.swing.JTextField txtNumeroCaseta;
    // End of variables declaration//GEN-END:variables
}
