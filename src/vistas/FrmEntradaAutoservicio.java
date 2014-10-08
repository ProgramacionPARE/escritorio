
package vistas;

import ModelosAux.Seguridad;
import ModelosAux.Tiempo;
import java.awt.Color;
import java.awt.Frame;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import modelos.Auto;
import modelos.Estacionamiento;
import modelos.Rest;
import modelos.Turno;
import org.jdesktop.application.Action;

/**
 *
 * @author Asistente Proyectos2
 */
public class FrmEntradaAutoservicio extends javax.swing.JDialog {
    Turno turno;
    Estacionamiento estacionamiento;
    Frame parent;
    /**
     * Creates new form FrmParkingEntrada
     */
    public FrmEntradaAutoservicio(java.awt.Frame parent, boolean modal,Turno turno, Estacionamiento estacionamiento) {
        super(parent,"Entrada nueva", modal);
        initComponents();
        this.turno = turno;
        this.estacionamiento = estacionamiento;
        this.parent = parent;
        this.getContentPane().setBackground(Color.white);
        txtFechaEntrada.setText(Tiempo.getFecha());
        //txtHoraEntrada.setText(Tiempo.getHora().substring(0, 5));
        txtCajero.setText(turno.getEmpleado().getNombre());
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbxSeries.getModel();
        for (String s: estacionamiento.getCaseta().getSeries() )
            model.addElement(s);
        cbxSeries.setModel(model);
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }
   
     private boolean validaCamposEntrada() {

        if (txtProgresivo.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Introduce el folio del boleto",
            "Formato erroneo",JOptionPane.WARNING_MESSAGE);
            txtProgresivo.grabFocus();
            return false;
        }
        if (!this.txtHoraEntrada.getText().matches("[0-9][0-9]:[0-9][0-9]")){
            JOptionPane.showMessageDialog(this,"La hora de entrada debe de terner formato 00:00 ",
            "Formato erroneo",JOptionPane.WARNING_MESSAGE);
            txtHoraEntrada.grabFocus();
            return false;
        }
        if (!Tiempo.horaVsHoraActual(txtFechaEntrada.getText(),txtHoraEntrada.getText()+":00")){
            JOptionPane.showMessageDialog(this,"La hora de entrada no puede ser mayor a la hora actual ",
            "Hora incorrecta",JOptionPane.WARNING_MESSAGE);
            txtHoraEntrada.grabFocus();
            return false;
        }
        
        
        txtHoraEntrada.setText(txtHoraEntrada.getText()+":00");
        return true;
     }
    
    @Action
    public void onGuardarAuto() {    
        //Valido campos
        if(!validaCamposEntrada())
            return;
        //Confirmo accion
        /*
                //  El siguente codigo se comento para evitar la impresion de boleto 
                //  y pasar directamente al cobro
                
        int confirmDialog = JOptionPane.showConfirmDialog(this,"¿Se imprimira el boleto estas seguro?",
                "Imprimir boleto",JOptionPane.YES_NO_CANCEL_OPTION);
        if(confirmDialog == JOptionPane.YES_OPTION){
            Auto auto= new Auto(0,txtSerie.getText(), String.format("%06d",Integer.valueOf(txtProgresivo.getText())),Seguridad.getClave(5), "",
                    estacionamiento.getCaseta().getTarifas().get(0),
                    txtFechaEntrada.getText() ,"", txtHoraEntrada.getText(),"", turno, null,0,0, 
                    estacionamiento.getCaseta(),0, "", "", "", null,null);
            //Aumento en uno los boletos generados
            turno.setNoBol(turno.getNoBol()+1);
            //Actualizo el folio final en el turno
            turno.setFolioFinal (turno.getFolioFinal()+1); 
            turno.actualizar();
            // Guardo entrada y actualizo progresivo
            auto.guardar();
            Progresivo.setProgresivoMasUno(estacionamiento.getCaseta(),"BOLETO");
            //Imprimo boletos
            PrinterJob job = PrinterJob.getPrinterJob();
            // Boleto al cliente
            new FrmP1BoletoClienteAutoServicio(this, false,job,turno,auto,estacionamiento);
            //Boleto llaves
           
            
            this.setVisible(false);
            this.dispose();

        
        }*/
        Auto auto= new Auto(0,"",(String)cbxSeries.getSelectedItem(), String.format("%06d",Integer.valueOf(txtProgresivo.getText())),Seguridad.getClave(5), "",
                    estacionamiento.getCaseta().getTarifas().get(0),
                    txtFechaEntrada.getText() ,"", txtHoraEntrada.getText(),"", turno, null,0,0, 
                    estacionamiento.getCaseta(),0, "", "", "", null,null,0);
         //Aumento en uno los boletos generados
        turno.setNoBol(turno.getNoBol()+1);
        //Actualizo el folio final en el turno
        turno.setFolioFinal (turno.getFolioFinal()+1); 
        turno.actualizar();
        // Guardo entrada y actualizo progresivo
        auto.guardar();
        Rest.sendAuto(auto,estacionamiento);
        this.setVisible(false);
        new FrmCobro(parent, true,turno,auto,estacionamiento);
        this.dispose();
        
        
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtHoraEntrada = new javax.swing.JTextField();
        txtFechaEntrada = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCajero = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtProgresivo = new javax.swing.JTextField();
        btnEntrada = new javax.swing.JButton();
        cbxSeries = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Entrada:");
        jLabel1.setName("jLabel1"); // NOI18N

        txtHoraEntrada.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtHoraEntrada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHoraEntrada.setName("txtHoraEntrada"); // NOI18N

        txtFechaEntrada.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtFechaEntrada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaEntrada.setName("txtFechaEntrada"); // NOI18N

        jLabel2.setText("Cajero:");
        jLabel2.setName("jLabel2"); // NOI18N

        txtCajero.setEditable(false);
        txtCajero.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCajero.setName("txtCajero"); // NOI18N

        jLabel3.setText("Progresivo:");
        jLabel3.setName("jLabel3"); // NOI18N

        txtProgresivo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtProgresivo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProgresivo.setName("txtProgresivo"); // NOI18N
        txtProgresivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProgresivoActionPerformed(evt);
            }
        });

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(FrmEntradaAutoservicio.class, this);
        btnEntrada.setAction(actionMap.get("onGuardarAuto")); // NOI18N
        btnEntrada.setBackground(new java.awt.Color(255, 255, 255));
        btnEntrada.setText("Entrada");
        btnEntrada.setName("btnEntrada"); // NOI18N

        cbxSeries.setName("cbxSeries"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtHoraEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbxSeries, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtProgresivo))
                            .addComponent(txtCajero))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtHoraEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCajero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtProgresivo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbxSeries, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        txtProgresivo.setNextFocusableComponent(this.btnEntrada);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtProgresivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProgresivoActionPerformed
       onGuardarAuto();
    }//GEN-LAST:event_txtProgresivoActionPerformed

    /**
     * @param args the command line arguments
     */
   


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrada;
    private javax.swing.JComboBox cbxSeries;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtCajero;
    private javax.swing.JTextField txtFechaEntrada;
    private javax.swing.JTextField txtHoraEntrada;
    private javax.swing.JTextField txtProgresivo;
    // End of variables declaration//GEN-END:variables
}
