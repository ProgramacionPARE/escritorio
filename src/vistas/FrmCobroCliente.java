
package vistas;

import ModelosAux.Sistema;
import ModelosAux.Tiempo;
import java.awt.Color;
import java.awt.Dialog;
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
public class FrmCobroCliente extends javax.swing.JDialog implements Runnable{
    Estacionamiento estacionamiento;
    Turno turno;
    Auto auto;
    Frame parent;
    /**
     * Creates new form FrmCobro
     */
    public FrmCobroCliente(Frame parent, boolean modal,Turno turno,Auto auto,Estacionamiento estacionamiento) {
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
//        txtDineroPagado.grabFocus();
//        txtCambio.setBackground(Color.red);
//        
//        btnCobrar.setEnabled(false);
        setVisible(true);
    }
    
    private void cargarTarifas(){
//        DefaultComboBoxModel model = (DefaultComboBoxModel) cbxTarifas.getModel();
//        ArrayList<Tarifa> tarifas = estacionamiento.getCaseta().getTarifas();
//        Iterator<Tarifa> iterator = tarifas.iterator();
//        while(iterator.hasNext()){
//            Tarifa next = iterator.next();
//           model.addElement(next.getDescripcion()+"  $"+next.getPrecioHora() );
//        }
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
//        if(auto.getDescuento()>0){
//            lblDescuento.setText("Descuento: $ "+auto.getDescuento());
//        }
        // Completo campos del formulario
        
        if(auto.getHorasTangibles()==1)
             txtTiempoEstadia.setText(auto.getHorasTangibles()+ " hora " );
        else if(auto.getHorasTangibles()>1)
             txtTiempoEstadia.setText(auto.getHorasTangibles()+ " horas " );
        
        txtTiempoEstadia.setText(txtTiempoEstadia.getText()+ auto.getMinutosTangibles()+" minutos");
        txtImporteTotal.setText(String.valueOf(auto.getMontoTangible()));
//        txtImporteBoletoPerdido.setText(String.valueOf(auto.getTarifa().getPrecioBoletoPerdido()));
//        if(auto.isBoletoPerdido()){
//            jLabel5.setVisible(false);
//            txtImporteBoletoPerdido.setVisible(false);
//        }
        
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        txtFechaEntrada = new javax.swing.JTextField();
        txtHoraEntrada = new javax.swing.JTextField();
        txtHoraSalida = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtFechaSalida = new javax.swing.JTextField();
        txtTiempoEstadia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtImporteTotal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0};
        getContentPane().setLayout(layout);

        jLabel1.setFont(new java.awt.Font("Aegean", 0, 48)); // NOI18N
        jLabel1.setText("Entrada");
        jLabel1.setName("jLabel1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jLabel1, gridBagConstraints);

        txtFechaEntrada.setEditable(false);
        txtFechaEntrada.setFont(new java.awt.Font("Aegean", 0, 48)); // NOI18N
        txtFechaEntrada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaEntrada.setName("txtFechaEntrada"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(txtFechaEntrada, gridBagConstraints);

        txtHoraEntrada.setEditable(false);
        txtHoraEntrada.setFont(new java.awt.Font("Aegean", 0, 48)); // NOI18N
        txtHoraEntrada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHoraEntrada.setName("txtHoraEntrada"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(txtHoraEntrada, gridBagConstraints);

        txtHoraSalida.setEditable(false);
        txtHoraSalida.setFont(new java.awt.Font("Aegean", 0, 48)); // NOI18N
        txtHoraSalida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHoraSalida.setName("txtHoraSalida"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(txtHoraSalida, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Aegean", 0, 48)); // NOI18N
        jLabel2.setText("Salida");
        jLabel2.setName("jLabel2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jLabel2, gridBagConstraints);

        txtFechaSalida.setEditable(false);
        txtFechaSalida.setFont(new java.awt.Font("Aegean", 0, 48)); // NOI18N
        txtFechaSalida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaSalida.setName("txtFechaSalida"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(txtFechaSalida, gridBagConstraints);

        txtTiempoEstadia.setEditable(false);
        txtTiempoEstadia.setFont(new java.awt.Font("Aegean", 0, 48)); // NOI18N
        txtTiempoEstadia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTiempoEstadia.setName("txtTiempoEstadia"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(txtTiempoEstadia, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Aegean", 0, 48)); // NOI18N
        jLabel3.setText("Tiempo de estancia");
        jLabel3.setName("jLabel3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jLabel3, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Aegean", 0, 48)); // NOI18N
        jLabel6.setText("Importe total");
        jLabel6.setName("jLabel6"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jLabel6, gridBagConstraints);

        txtImporteTotal.setEditable(false);
        txtImporteTotal.setFont(new java.awt.Font("Aegean", 0, 48)); // NOI18N
        txtImporteTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImporteTotal.setName("txtImporteTotal"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(txtImporteTotal, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        String ObjButtons[] = {"Si","No"};
        int PromptResult = JOptionPane.showOptionDialog(null,"Estas seguro de no cobrar este boleto, permanecera abierto.","NO cobrar boleto",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
        if(PromptResult==JOptionPane.YES_OPTION){
            if(auto.isBoletoManual())
                auto.getBoletoManual().eliminar();
            this.dispose();
        }
    }//GEN-LAST:event_formWindowClosing

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
//            auto.setMontoReciboPago(Float.valueOf(txtDineroPagado.getText()));
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
            turno.getDetallesTurno().get(auto.getSerie()).setNoBolCancelados(turno.getDetallesTurno().get(auto.getSerie()).getNoBolCancelados()+1);
        else if(auto.getBoletoPerdido()!= null)
            turno.getDetallesTurno().get(auto.getSerie()).setNoBolPerdidos(turno.getDetallesTurno().get(auto.getSerie()).getNoBolPerdidos()+1);
        else
            turno.getDetallesTurno().get(auto.getSerie()).setNoBolCobrados(turno.getDetallesTurno().get(auto.getSerie()).getNoBolCobrados()+1);
       
        turno.getDetallesTurno().get(auto.getSerie()).setTotal(turno.getDetallesTurno().get(auto.getSerie()).getTotal()+auto.getMontoTangible());
        auto.actualizar();
        
        //turno.setTotal(DetallesMovimiento.calcularTotal(DetallesMovimiento.generarDetalles(Auto.getAutosCobradosTurnoActual(turno),
                //Auto.getAutosBoletoPerdidoTurnoActual(turno),Auto.getAutosBoletoCanceladoTurnoActual(turno),turno) ));
        turno.actualizar();
        //Reviso si activo la alarma
        ((FrmPrincipal)parent).setCajaAlarma(
                Sistema.requiereRetitroParcial(caja) );
    }    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtFechaEntrada;
    private javax.swing.JTextField txtFechaSalida;
    private javax.swing.JTextField txtHoraEntrada;
    private javax.swing.JTextField txtHoraSalida;
    private javax.swing.JTextField txtImporteTotal;
    private javax.swing.JTextField txtTiempoEstadia;
    // End of variables declaration//GEN-END:variables

}
