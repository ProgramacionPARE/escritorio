

package vistas;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import modelos.Estacionamiento;
import modelos.Main;


public class FrmConfiguracion extends javax.swing.JDialog {
    Estacionamiento estacionamiento;
    
    public FrmConfiguracion(java.awt.Frame parent, boolean modal) {
        super(parent,"Configuracion", modal);
        initComponents();
        ButtonGroup group = new ButtonGroup();
        group.add(this.rbtAutoservicio);
        group.add(this.rbtValet);
        group.add(this.rbtValetMasivo);
        this.estacionamiento =  Main.getInstance().getEstacionamiento();
        txtEstacionamiento.setText(estacionamiento.getDescripcion());
      
        if(estacionamiento.getTipo().equals("Valet")){
            this.rbtValet.setSelected(true);
            this.rbtAutoservicio.setSelected(false);
            this.rbtValetMasivo.setSelected(false);
        }else if(estacionamiento.getTipo().equals("Autoservicio")){
            this.rbtValet.setSelected(false);
            this.rbtAutoservicio.setSelected(true);
            this.rbtValetMasivo.setSelected(false);
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
        btnModificar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        rbtValet = new javax.swing.JRadioButton();
        rbtAutoservicio = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        rbtValetMasivo = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Estacionamiento");
        jLabel1.setName("jLabel1"); // NOI18N

        txtEstacionamiento.setEditable(false);
        txtEstacionamiento.setName("txtEstacionamiento"); // NOI18N

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(txtEstacionamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(rbtValet)
                        .addGap(34, 34, 34)
                        .addComponent(rbtAutoservicio))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(rbtValetMasivo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addComponent(txtEstacionamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtValet)
                    .addComponent(rbtAutoservicio))
                .addGap(7, 7, 7)
                .addComponent(rbtValetMasivo)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (validaCamposEntrada()){ 
            estacionamiento.setDescripcion(txtEstacionamiento.getText());
            if(rbtValet.isSelected())
                estacionamiento.setTipo("Valet");
            if(rbtAutoservicio.isSelected())
                estacionamiento.setTipo("Autoservicio");
            if(rbtValetMasivo.isSelected())
                estacionamiento.setTipo("ValetMasivo");
            
            estacionamiento.actualizar();
           
            txtEstacionamiento.setEditable(false);
          
            btnModificar.setVisible(true);
            btnGuardar.setVisible(false);
            rbtAutoservicio.setEnabled(false);
            rbtValet.setEnabled(false);
            rbtValetMasivo.setEnabled(false);
        
            
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        txtEstacionamiento.setEditable(true);
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
       
        
        
        return true;
  }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JRadioButton rbtAutoservicio;
    private javax.swing.JRadioButton rbtValet;
    private javax.swing.JRadioButton rbtValetMasivo;
    private javax.swing.JTextField txtEstacionamiento;
    // End of variables declaration//GEN-END:variables
}
