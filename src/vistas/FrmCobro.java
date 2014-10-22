
package vistas;

import ModelosAux.Sistema;
import ModelosAux.Tiempo;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ItemEvent;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import modelos.Auto;
import modelos.Caja;
import modelos.Estacionamiento;
import modelos.Tarifa;
import modelos.Turno;
import org.jdesktop.application.Action;
import vistas.formatos.FrmReciboPago;

/**
 *
 * @author Asistente Proyectos2
 */
public class FrmCobro extends javax.swing.JDialog implements Runnable{
    Estacionamiento estacionamiento;
    Turno turno;
    Auto auto;
    Frame parent;
    /**
     * Creates new form FrmCobro
     */
    public FrmCobro(Frame parent, boolean modal,Turno turno,Auto auto,Estacionamiento estacionamiento) {
        super(parent,"Cobro de boleto", modal);
        initComponents();
        this.auto = auto;
        this.turno = turno;
        this.estacionamiento = estacionamiento;
        this.parent = parent;
        this.getContentPane().setBackground(Color.white);
        pack();
        setLocationRelativeTo(parent);
        this.auto.setHoraSalida(Tiempo.getHora());
        this.auto.setFechaSalida(Tiempo.getFecha());
        
        cargarTarifas();
        calcularImporte();
        txtDineroPagado.grabFocus();
        txtCambio.setBackground(Color.red);
        
        btnCobrar.setEnabled(false);
        setVisible(true);
    }
    
    private void cargarTarifas(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbxTarifas.getModel();
        ArrayList<Tarifa> tarifas = estacionamiento.getCaseta().getTarifas();
        Iterator<Tarifa> iterator = tarifas.iterator();
        while(iterator.hasNext()){
            Tarifa next = iterator.next();
           model.addElement(next.getDescripcion()+"  $"+next.getPrecioHora() );
        }
    }
    
    public void calcularImporte() {
        //Completo la informacion de la salida del auto
        txtTiempoEstadia.setText("");

        if(auto.isBoletoManual()){
            auto.setHorasTangibles(Tiempo.getDirenciaHoras(auto.getBoletoManual().getFechaEntradaM(),auto.getBoletoManual().getHoraEntradaM(),auto.getBoletoManual().getFechaSalidaM(),auto.getBoletoManual().getHoraSalidaM()));
            auto.setMinutosTangibles(Tiempo.getDirenciaMinutos(auto.getBoletoManual().getFechaEntradaM(),auto.getBoletoManual().getHoraEntradaM(),auto.getBoletoManual().getFechaSalidaM(),auto.getBoletoManual().getHoraSalidaM()));
            txtFechaEntrada.setText(auto.getBoletoManual().getFechaEntradaM());
            txtFechaSalida.setText(auto.getBoletoManual().getFechaSalidaM());
            txtHoraEntrada.setText(auto.getBoletoManual().getHoraEntradaM());
            txtHoraSalida.setText(auto.getBoletoManual().getHoraSalidaM());
        }else{
            auto.setHorasTangibles(Tiempo.getDirenciaHoras(auto.getFechaEntrada(),auto.getHoraEntrada(),auto.getFechaSalida(),auto.getHoraSalida()));
            auto.setMinutosTangibles(Tiempo.getDirenciaMinutos(auto.getFechaEntrada(),auto.getHoraEntrada(),auto.getFechaSalida(),auto.getHoraSalida()));
            txtFechaEntrada.setText(auto.getFechaEntrada());
            txtFechaSalida.setText(auto.getFechaSalida());
            txtHoraEntrada.setText(auto.getHoraEntrada());
            txtHoraSalida.setText(auto.getHoraSalida());
        }
        auto.setTurnoSalida(turno);
        auto.setMontoTangible(Tarifa.getImporteEstadia(auto));
        if(auto.getDescuento()>0){
            lblDescuento.setText("Descuento: $ "+auto.getDescuento());
        }
        // Completo campos del formulario
        
        if(auto.getHorasTangibles()==1)
             txtTiempoEstadia.setText(auto.getHorasTangibles()+ " hora " );
        else if(auto.getHorasTangibles()>1)
             txtTiempoEstadia.setText(auto.getHorasTangibles()+ " horas " );
        
        txtTiempoEstadia.setText(txtTiempoEstadia.getText()+ auto.getMinutosTangibles()+" minutos");
        txtImporteTotal.setText(String.valueOf(auto.getMontoTangible()));
        txtImporteBoletoPerdido.setText(String.valueOf(auto.getTarifa().getPrecioBoletoPerdido()));
        if(auto.getBoletoPerdido()==null){
            jLabel5.setVisible(false);
            txtImporteBoletoPerdido.setVisible(false);
        }
        
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtFechaEntrada = new javax.swing.JTextField();
        txtHoraEntrada = new javax.swing.JTextField();
        txtHoraSalida = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtFechaSalida = new javax.swing.JTextField();
        txtTiempoEstadia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDineroPagado = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtImporteBoletoPerdido = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtImporteTotal = new javax.swing.JTextField();
        btnCobrar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtCambio = new javax.swing.JTextField();
        cbxTarifas = new javax.swing.JComboBox();
        btnDescuento = new javax.swing.JButton();
        lblDescuento = new javax.swing.JLabel();
        btnCobroManual = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Entrada");
        jLabel1.setName("jLabel1"); // NOI18N

        txtFechaEntrada.setEditable(false);
        txtFechaEntrada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaEntrada.setName("txtFechaEntrada"); // NOI18N

        txtHoraEntrada.setEditable(false);
        txtHoraEntrada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHoraEntrada.setName("txtHoraEntrada"); // NOI18N

        txtHoraSalida.setEditable(false);
        txtHoraSalida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHoraSalida.setName("txtHoraSalida"); // NOI18N

        jLabel2.setText("Salida");
        jLabel2.setName("jLabel2"); // NOI18N

        txtFechaSalida.setEditable(false);
        txtFechaSalida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaSalida.setName("txtFechaSalida"); // NOI18N

        txtTiempoEstadia.setEditable(false);
        txtTiempoEstadia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTiempoEstadia.setName("txtTiempoEstadia"); // NOI18N

        jLabel3.setText("Tiempo de estancia");
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText("Tarifa:");
        jLabel4.setName("jLabel4"); // NOI18N

        txtDineroPagado.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        txtDineroPagado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDineroPagado.setName("txtDineroPagado"); // NOI18N
        txtDineroPagado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDineroPagadoActionPerformed(evt);
            }
        });
        txtDineroPagado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDineroPagadoKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDineroPagadoKeyReleased(evt);
            }
        });

        jLabel5.setText("Boleto perdido");
        jLabel5.setName("jLabel5"); // NOI18N

        txtImporteBoletoPerdido.setEditable(false);
        txtImporteBoletoPerdido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImporteBoletoPerdido.setName("txtImporteBoletoPerdido"); // NOI18N

        jLabel6.setText("Importe total");
        jLabel6.setName("jLabel6"); // NOI18N

        txtImporteTotal.setEditable(false);
        txtImporteTotal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtImporteTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImporteTotal.setName("txtImporteTotal"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(FrmCobro.class, this);
        btnCobrar.setAction(actionMap.get("onCobrar")); // NOI18N
        btnCobrar.setBackground(new java.awt.Color(255, 255, 255));
        btnCobrar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCobrar.setText("Cobrar");
        btnCobrar.setName("btnCobrar"); // NOI18N

        jLabel7.setText("Cambio");
        jLabel7.setName("jLabel7"); // NOI18N

        txtCambio.setEditable(false);
        txtCambio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCambio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCambio.setName("txtCambio"); // NOI18N

        cbxTarifas.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cbxTarifas.setName("cbxTarifas"); // NOI18N
        cbxTarifas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cbxTarifasMouseReleased(evt);
            }
        });
        cbxTarifas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTarifasItemStateChanged(evt);
            }
        });

        btnDescuento.setBackground(new java.awt.Color(255, 255, 255));
        btnDescuento.setText("Aplicar descuento");
        btnDescuento.setName("btnDescuento"); // NOI18N
        btnDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescuentoActionPerformed(evt);
            }
        });

        lblDescuento.setName("lblDescuento"); // NOI18N

        btnCobroManual.setBackground(new java.awt.Color(255, 255, 255));
        btnCobroManual.setText("Cobro manual");
        btnCobroManual.setName("btnCobroManual"); // NOI18N
        btnCobroManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobroManualActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(69, 69, 69))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnCobroManual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, 0)
                                        .addComponent(lblDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtImporteBoletoPerdido)
                                    .addComponent(txtImporteTotal)
                                    .addComponent(btnDescuento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(197, 197, 197))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(158, 158, 158)
                                .addComponent(txtTiempoEstadia))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFechaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtHoraSalida)
                                    .addComponent(txtHoraEntrada)))
                            .addComponent(cbxTarifas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDineroPagado)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(txtCambio)
                    .addComponent(btnCobrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel1)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtFechaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtHoraEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDineroPagado, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbxTarifas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTiempoEstadia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtImporteBoletoPerdido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtImporteTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                            .addComponent(btnDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCobroManual, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnCobrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        txtDineroPagado.setNextFocusableComponent(this.cbxTarifas);
        cbxTarifas.setNextFocusableComponent(this.btnCobrar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDineroPagadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDineroPagadoKeyTyped
      int k = (int) evt.getKeyChar();;
      if (k >= 97 && k <= 122 || k >= 65 && k <= 90) 
        evt.consume();      
    }//GEN-LAST:event_txtDineroPagadoKeyTyped

    
    private void txtDineroPagadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDineroPagadoKeyReleased
      if(!txtDineroPagado.getText().equals("")){
        if(Float.valueOf(txtDineroPagado.getText())>=Float.valueOf(txtImporteTotal.getText())){
            btnCobrar.setEnabled(true);
            txtCambio.setText(String.valueOf(Float.valueOf(txtDineroPagado.getText())-Float.valueOf(txtImporteTotal.getText())));
            txtCambio.setBackground(Color.green);
        }
        else{
            txtDineroPagado.grabFocus();
            txtCambio.setText("");
            txtCambio.setBackground(Color.red);
            btnCobrar.setEnabled(false);
        }
     }
    }//GEN-LAST:event_txtDineroPagadoKeyReleased

    private void cbxTarifasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTarifasItemStateChanged
        if(ItemEvent.SELECTED == evt.getStateChange() ){
            auto.setTarifa(estacionamiento.getCaseta().getTarifas().get(cbxTarifas.getSelectedIndex()));
            calcularImporte();
        }
    }//GEN-LAST:event_cbxTarifasItemStateChanged

    private void txtDineroPagadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDineroPagadoActionPerformed
        if(btnCobrar.isEnabled())
            onCobrar();
    }//GEN-LAST:event_txtDineroPagadoActionPerformed

    private void btnDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescuentoActionPerformed
        new FrmLeerCodigoBarrasDescuento(parent,true,turno,estacionamiento,this);        // TODO add your handling code here:
    }//GEN-LAST:event_btnDescuentoActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        String ObjButtons[] = {"Si","No"};
        int PromptResult = JOptionPane.showOptionDialog(null,"Estas seguro de no cobrar este boleto, permanecera abierto.","NO cobrar boleto",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
        if(PromptResult==JOptionPane.YES_OPTION){
            auto.getBoletoManual().eliminar();
            this.dispose();
        }
    }//GEN-LAST:event_formWindowClosing

    private void btnCobroManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobroManualActionPerformed
        new FrmCobroManual(this,true,turno,auto);
    }//GEN-LAST:event_btnCobroManualActionPerformed

    private void cbxTarifasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxTarifasMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTarifasMouseReleased

    @Action
    public void onCobrar() {
        new Thread(this).start();
         int showConfirmDialog ;
        //Pregunto si imprimo recibo de pago
        if(auto.getBoletoCancelado()!=null || auto.getBoletoPerdido()!=null)
            showConfirmDialog = JOptionPane.YES_OPTION;
        else
            showConfirmDialog = JOptionPane.showConfirmDialog(this, "Quieres imprimir recibo de pago", "Recibo de pago",JOptionPane.YES_NO_OPTION);
        if(showConfirmDialog == JOptionPane.YES_OPTION){
            auto.setReciboImpreso(true);
            auto.actualizar();
            auto.setMontoReciboPago(Float.valueOf(txtDineroPagado.getText()));
            new FrmReciboPago(this,false,PrinterJob.getPrinterJob(),turno,auto,estacionamiento);   
        }
        this.dispose();
    }
    
    @Override
    public void run() {
        auto.setDentro(false);
        //Actualizo monto en caja
        Caja caja= Caja.getByCaseta(estacionamiento.getCaseta().getId());
        caja.setMonto(caja.getMonto()+auto.getMontoTangible());
        caja.actualizar();
        //Reviso si el auto fue boleto perdido, cancelado o cobrado normalmente 
        if(auto.getBoletoCancelado()!= null)
            turno.setNoBolCancelados(turno.getNoBolCancelados()+1);
        else if(auto.getBoletoPerdido()!= null)
            turno.setNoBolPerdidos(turno.getNoBolPerdidos()+1);
        else
            turno.setNoBolCobrados(turno.getNoBolCobrados()+1);
       
        turno.setTotal(auto.getMontoTangible());
        auto.actualizar();
        
        //turno.setTotal(DetallesMovimiento.calcularTotal(DetallesMovimiento.generarDetalles(Auto.getAutosCobradosTurnoActual(turno),
                //Auto.getAutosBoletoPerdidoTurnoActual(turno),Auto.getAutosBoletoCanceladoTurnoActual(turno),turno) ));
        turno.actualizar();
        //Reviso si activo la alarma
        ((FrmPrincipal)parent).setCajaAlarma(
                Sistema.requiereRetitroParcial(caja) );
    }    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCobrar;
    private javax.swing.JButton btnCobroManual;
    private javax.swing.JButton btnDescuento;
    private javax.swing.JComboBox cbxTarifas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblDescuento;
    private javax.swing.JTextField txtCambio;
    private javax.swing.JTextField txtDineroPagado;
    private javax.swing.JTextField txtFechaEntrada;
    private javax.swing.JTextField txtFechaSalida;
    private javax.swing.JTextField txtHoraEntrada;
    private javax.swing.JTextField txtHoraSalida;
    private javax.swing.JTextField txtImporteBoletoPerdido;
    private javax.swing.JTextField txtImporteTotal;
    private javax.swing.JTextField txtTiempoEstadia;
    // End of variables declaration//GEN-END:variables

}
