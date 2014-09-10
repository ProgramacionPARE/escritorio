
package vistas;

import ModelosAux.Sistema;
import ModelosAux.Tiempo;
import java.awt.Color;
import java.awt.print.PrinterJob;
import javax.swing.JOptionPane;
import modelos.Auto;
import modelos.Caja;
import modelos.DetallesMovimiento;
import modelos.Estacionamiento;
import modelos.Tarifa;
import modelos.Turno;
import org.jdesktop.application.Action;
import proyectopare.ProyectoPareApp;
import vistas.formatos.FrmReciboPago;

/**
 *
 * @author Asistente Proyectos2
 */
public class FrmCobro extends javax.swing.JDialog implements Runnable{
    Estacionamiento estacionamiento;
    Turno turno;
    Auto auto;
    
    /**
     * Creates new form FrmCobro
     */
    public FrmCobro(java.awt.Frame parent, boolean modal,Turno turno,Auto auto,Estacionamiento estacionamiento) {
        super(parent,"Cobro de boleto", modal);
        initComponents();
        this.auto = auto;
        this.turno = turno;
        this.estacionamiento = estacionamiento;
        this.getContentPane().setBackground(Color.white);
        pack();
        setLocationRelativeTo(parent);
        calcularImporte();
        txtDineroPagado.grabFocus();
        txtCambio.setBackground(Color.red);
        btnCobrar.setEnabled(false);
        setVisible(true);
    }
    
    private void calcularImporte() {
        //Completo la informacion de la salida del auto
        auto.setHoraSalida(Tiempo.getHora());
        auto.setFechaSalida(Tiempo.getFecha());
        
        auto.setHorasTangibles(Tiempo.getDirenciaHoras(auto.getFechaEntrada(),auto.getHoraEntrada(),auto.getFechaSalida(),auto.getHoraSalida()));
        auto.setMinutosTangibles(Tiempo.getDirenciaMinutos(auto.getFechaEntrada(),auto.getHoraEntrada(),auto.getFechaSalida(),auto.getHoraSalida()));
        auto.setTurnoSalida(turno);
        auto.setMontoTangible(Tarifa.getImporteEstadia(auto));
        // Completo campos del formulario
        txtFechaEntrada.setText(auto.getFechaEntrada());
        txtFechaSalida.setText(auto.getFechaSalida());
        txtHoraEntrada.setText(auto.getHoraEntrada());
        txtHoraSalida.setText(auto.getHoraSalida());
        if(auto.getHorasTangibles()==1)
             txtTiempoEstadia.setText(auto.getHorasTangibles()+ " hora " );
        else if(auto.getHorasTangibles()>1)
             txtTiempoEstadia.setText(auto.getHorasTangibles()+ " horas " );
        
        txtTiempoEstadia.setText(txtTiempoEstadia.getText()+ auto.getMinutosTangibles()+" minutos");
        txtImporteHora.setText(String.valueOf(auto.getTarifa().getPrecioHora()));
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
        txtImporteHora = new javax.swing.JTextField();
        txtDineroPagado = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtImporteBoletoPerdido = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtImporteTotal = new javax.swing.JTextField();
        btnCobrar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtCambio = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        jLabel4.setText("Precio por hora:");
        jLabel4.setName("jLabel4"); // NOI18N

        txtImporteHora.setEditable(false);
        txtImporteHora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImporteHora.setName("txtImporteHora"); // NOI18N

        txtDineroPagado.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        txtDineroPagado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDineroPagado.setName("txtDineroPagado"); // NOI18N
        txtDineroPagado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDineroPagadoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDineroPagadoKeyTyped(evt);
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
        btnCobrar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCobrar.setText("Cobrar");
        btnCobrar.setName("btnCobrar"); // NOI18N

        jLabel7.setText("Cambio");
        jLabel7.setName("jLabel7"); // NOI18N

        txtCambio.setEditable(false);
        txtCambio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCambio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCambio.setName("txtCambio"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtFechaEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                    .addComponent(txtFechaSalida))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtHoraSalida)
                                    .addComponent(txtHoraEntrada)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTiempoEstadia, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDineroPagado)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtImporteTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .addComponent(txtImporteBoletoPerdido)
                            .addComponent(txtImporteHora))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCambio))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtFechaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHoraEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addComponent(txtDineroPagado, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtTiempoEstadia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtImporteHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtImporteBoletoPerdido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtImporteTotal)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtCambio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

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

    @Action
    public void onCobrar() {
        new Thread(this).start();
        //Pregunto si imprimo recibo de pago
        int showConfirmDialog = JOptionPane.showConfirmDialog(this, "Quieres imprimir recibo de pago", "Recibo de pago",JOptionPane.YES_NO_OPTION);
        if(showConfirmDialog == JOptionPane.YES_OPTION){
            auto.setReciboImpreso(true);
            auto.actualizar();
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
       
        auto.actualizar();
        
        turno.setTotal(DetallesMovimiento.calcularTotal(DetallesMovimiento.generarDetalles(Auto.getAutosCobradosTurnoActual(turno),
                Auto.getAutosBoletoPerdidoTurnoActual(turno),Auto.getAutosBoletoCanceladoTurnoActual(turno))));
        turno.actualizar();
        //Reviso si activo la alarma
        ProyectoPareApp.getApplication().getView().setCajaAlarma(
                Sistema.requiereRetitroParcial(caja) );
    }    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCobrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtCambio;
    private javax.swing.JTextField txtDineroPagado;
    private javax.swing.JTextField txtFechaEntrada;
    private javax.swing.JTextField txtFechaSalida;
    private javax.swing.JTextField txtHoraEntrada;
    private javax.swing.JTextField txtHoraSalida;
    private javax.swing.JTextField txtImporteBoletoPerdido;
    private javax.swing.JTextField txtImporteHora;
    private javax.swing.JTextField txtImporteTotal;
    private javax.swing.JTextField txtTiempoEstadia;
    // End of variables declaration//GEN-END:variables

}
