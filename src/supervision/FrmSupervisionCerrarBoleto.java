
package supervision;

import ModelosAux.Tiempo;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import modelos.Auto;
import modelos.Estacionamiento;
import modelos.Rest;
import modelos.Tarifa;
import modelos.TurnoDetalles;


public class FrmSupervisionCerrarBoleto extends javax.swing.JDialog {

 
    private Auto auto;
    private TurnoDetalles detalle;
    private ArrayList<Tarifa> tarifas;
    private Estacionamiento estacionamiento;
    
    
    public FrmSupervisionCerrarBoleto(java.awt.Frame parent, boolean modal, Auto auto,Estacionamiento estacionamiento,TurnoDetalles detalle) {
        super(parent,"Cerrar boleto", modal);
        initComponents();
        this.auto = auto;
        this.estacionamiento = estacionamiento;
        this.detalle = detalle;
        tarifas = estacionamiento.getCaseta().getTarifas();
        lblProgresivo.setText(lblProgresivo.getText()+"  "+auto.getProgresivo());
        Iterator<Tarifa> iterator = tarifas.iterator();
        while(iterator.hasNext()){
            Tarifa next = iterator.next();
            cbxTarifa.addItem(next.getDescripcion());
        }
        
        txtTurnoSalida.setText(String.valueOf(auto.getIdTurnoEntrada()));
        txtFechaSalida.setText(auto.getFechaEntrada());
        txtHoraSalida.setText(auto.getHoraEntrada());
        

        this.setBackground(Color.white);
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        rbtNormal = new javax.swing.JRadioButton();
        rbtCancelar = new javax.swing.JRadioButton();
        rbtPerdido = new javax.swing.JRadioButton();
        txtTurnoSalida = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtHoraSalida = new javax.swing.JTextField();
        txtFechaSalida = new javax.swing.JTextField();
        cbxTarifa = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtImporte = new javax.swing.JTextField();
        btnCalcular = new javax.swing.JButton();
        btnCobrar = new javax.swing.JButton();
        lblProgresivo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        buttonGroup1.add(rbtNormal);
        rbtNormal.setSelected(true);
        rbtNormal.setText("Normal");
        rbtNormal.setName("rbtNormal"); // NOI18N
        rbtNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtNormalActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtCancelar);
        rbtCancelar.setText("Cancelar");
        rbtCancelar.setName("rbtCancelar"); // NOI18N
        rbtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtCancelarActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtPerdido);
        rbtPerdido.setText("Perdido");
        rbtPerdido.setName("rbtPerdido"); // NOI18N
        rbtPerdido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtPerdidoActionPerformed(evt);
            }
        });

        txtTurnoSalida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTurnoSalida.setName("txtTurnoSalida"); // NOI18N

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Hora salida");
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Turno salida");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Fecha salida");
        jLabel2.setName("jLabel2"); // NOI18N

        txtHoraSalida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHoraSalida.setName("txtHoraSalida"); // NOI18N

        txtFechaSalida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaSalida.setName("txtFechaSalida"); // NOI18N

        cbxTarifa.setName("cbxTarifa"); // NOI18N

        jLabel4.setText("Tarifa");
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText("Importe");
        jLabel5.setName("jLabel5"); // NOI18N

        txtImporte.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtImporte.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImporte.setName("txtImporte"); // NOI18N

        btnCalcular.setText("Calcular");
        btnCalcular.setName("btnCalcular"); // NOI18N
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });

        btnCobrar.setText("Cobrar");
        btnCobrar.setName("btnCobrar"); // NOI18N
        btnCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobrarActionPerformed(evt);
            }
        });

        lblProgresivo.setText("Progresivo:");
        lblProgresivo.setName("lblProgresivo"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtImporte)
                            .addComponent(cbxTarifa, 0, 180, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCalcular, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                            .addComponent(btnCobrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 6, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFechaSalida, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTurnoSalida, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(rbtCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                                    .addComponent(rbtPerdido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rbtNormal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblProgresivo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblProgresivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtTurnoSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rbtNormal)
                        .addGap(18, 18, 18)
                        .addComponent(rbtCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(rbtPerdido)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCalcular))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCobrar)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
        auto.setIdTurnoSalida(Integer.valueOf(txtTurnoSalida.getText()));
        auto.setFechaSalida(txtFechaSalida.getText());
        auto.setHoraSalida(txtHoraSalida.getText());
        auto.setHorasTangibles(Tiempo.getDirenciaHoras(auto.getFechaEntrada(),auto.getHoraEntrada(),auto.getFechaSalida(),auto.getHoraSalida()));
        auto.setMinutosTangibles(Tiempo.getDirenciaMinutos(auto.getFechaEntrada(),auto.getHoraEntrada(),auto.getFechaSalida(),auto.getHoraSalida()));
            
        auto.setTarifa(tarifas.get(cbxTarifa.getSelectedIndex()));
        
        float importeEstadia = Tarifa.getImporteEstadia(auto);
        txtImporte.setText(String.valueOf(importeEstadia));
        auto.setMontoTangible(importeEstadia);
        
    }//GEN-LAST:event_btnCalcularActionPerformed

    private void btnCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrarActionPerformed
        auto.setIsBoletoOficina(true);
        auto.setDentro(false);
        if(auto.isBoletoCancelado())
            detalle.setNoBolCancelados(detalle.getNoBolCancelados()+1);
        else if(auto.isBoletoPerdido())
            detalle.setNoBolPerdidos(detalle.getNoBolPerdidos()+1);
        else
            detalle.setNoBolCobrados(detalle.getNoBolCobrados()+1);
       
        detalle.setTotal(detalle.getTotal()+auto.getMontoTangible());
        auto.actualizar();
        detalle.actualizar(); 
        Rest.sendTurnoDetalle( detalle, estacionamiento);
        Rest.sendAuto(auto, estacionamiento);
         JOptionPane.showMessageDialog(null,"El boleto se cerro correctamente", "Ok",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnCobrarActionPerformed

    private void rbtNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtNormalActionPerformed
        if(rbtNormal.isSelected()){
            auto.setIsBoletoPerdido(false);
            auto.setIsBoletoCancelado(false);
        }else if(rbtCancelar.isSelected()){
            auto.setIsBoletoPerdido(false);
            auto.setIsBoletoCancelado(true);
        }else if(rbtPerdido.isSelected()){
            auto.setIsBoletoPerdido(true);
            auto.setIsBoletoCancelado(false);
        }
    }//GEN-LAST:event_rbtNormalActionPerformed

    private void rbtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtCancelarActionPerformed
        if(rbtNormal.isSelected()){
            auto.setIsBoletoPerdido(false);
            auto.setIsBoletoCancelado(false);
        }else if(rbtCancelar.isSelected()){
            auto.setIsBoletoPerdido(false);
            auto.setIsBoletoCancelado(true);
        }else if(rbtPerdido.isSelected()){
            auto.setIsBoletoPerdido(true);
            auto.setIsBoletoCancelado(false);
        }
    }//GEN-LAST:event_rbtCancelarActionPerformed

    private void rbtPerdidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtPerdidoActionPerformed
       if(rbtNormal.isSelected()){
            auto.setIsBoletoPerdido(false);
            auto.setIsBoletoCancelado(false);
        }else if(rbtCancelar.isSelected()){
            auto.setIsBoletoPerdido(false);
            auto.setIsBoletoCancelado(true);
        }else if(rbtPerdido.isSelected()){
            auto.setIsBoletoPerdido(true);
            auto.setIsBoletoCancelado(false);
        }
    }//GEN-LAST:event_rbtPerdidoActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalcular;
    private javax.swing.JButton btnCobrar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbxTarifa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblProgresivo;
    private javax.swing.JRadioButton rbtCancelar;
    private javax.swing.JRadioButton rbtNormal;
    private javax.swing.JRadioButton rbtPerdido;
    private javax.swing.JTextField txtFechaSalida;
    private javax.swing.JTextField txtHoraSalida;
    private javax.swing.JTextField txtImporte;
    private javax.swing.JTextField txtTurnoSalida;
    // End of variables declaration//GEN-END:variables
}
