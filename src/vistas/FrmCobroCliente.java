
package vistas;

import ModelosAux.Sistema;
import ModelosAux.Tiempo;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import modelos.Auto;
import modelos.Caja;
import modelos.Configuracion;
import modelos.Estacionamiento;
import modelos.Tarifa;
import modelos.Turno;
import org.jdesktop.application.Action;
import vistas.formatos.FrmReciboPago;

/**
 *
 * @author Asistente Proyectos2
 */
public class FrmCobroCliente extends javax.swing.JDialog implements Runnable {
    Estacionamiento estacionamiento;
    Turno turno;
    Auto auto;
    Frame parent;
    Thread t1;
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
        calcularImporte();
        Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize(); 
        setBounds(0, 0,  screenSize.width,  screenSize.height); 
        t1 = new Thread(this);
        t1.start();
        setVisible(true);
    }
    
    @Override
    public void run() {
        while(true){
            try {
                Auto auto = Auto.getCambioEstadoServidor();
                if(auto != null){
                    if(auto.getEstadoServidor()== 3){
                        auto.setEstadoServidor(2);
                        auto.actualizarEstadoServidor();
                        this.auto.setTarifa(auto.getTarifa());
                        this.calcularImporte();
                    }else if(auto.getEstadoServidor()==4){ 
                        auto.setEstadoServidor(6);
                        auto.actualizarEstadoServidor();
                        new FrmMensajeCliente(parent,true,"COBRO",turno,estacionamiento);
                        break;
        
                    }
                }
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.dispose();
    }
    
    public void calcularImporte() {
        //Completo la informacion de la salida del auto
        txtTiempoEstadia.setText("");
        txtTarifa.setText(auto.getTarifa().getDescripcion());
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
        jLabel4 = new javax.swing.JLabel();
        txtTarifa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0};
        layout.rowHeights = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        getContentPane().setLayout(layout);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jLabel1.setText("Entrada");
        jLabel1.setName("jLabel1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jLabel1, gridBagConstraints);

        txtFechaEntrada.setEditable(false);
        txtFechaEntrada.setBackground(new java.awt.Color(255, 255, 255));
        txtFechaEntrada.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        txtFechaEntrada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaEntrada.setName("txtFechaEntrada"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        getContentPane().add(txtFechaEntrada, gridBagConstraints);

        txtHoraEntrada.setEditable(false);
        txtHoraEntrada.setBackground(new java.awt.Color(255, 255, 255));
        txtHoraEntrada.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        txtHoraEntrada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHoraEntrada.setName("txtHoraEntrada"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        getContentPane().add(txtHoraEntrada, gridBagConstraints);

        txtHoraSalida.setEditable(false);
        txtHoraSalida.setBackground(new java.awt.Color(255, 255, 255));
        txtHoraSalida.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        txtHoraSalida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHoraSalida.setName("txtHoraSalida"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        getContentPane().add(txtHoraSalida, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jLabel2.setText("Salida");
        jLabel2.setName("jLabel2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jLabel2, gridBagConstraints);

        txtFechaSalida.setEditable(false);
        txtFechaSalida.setBackground(new java.awt.Color(255, 255, 255));
        txtFechaSalida.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        txtFechaSalida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaSalida.setName("txtFechaSalida"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        getContentPane().add(txtFechaSalida, gridBagConstraints);

        txtTiempoEstadia.setEditable(false);
        txtTiempoEstadia.setBackground(new java.awt.Color(255, 255, 255));
        txtTiempoEstadia.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        txtTiempoEstadia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTiempoEstadia.setName("txtTiempoEstadia"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        getContentPane().add(txtTiempoEstadia, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jLabel3.setText("Tiempo");
        jLabel3.setName("jLabel3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jLabel3, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 70)); // NOI18N
        jLabel6.setText("Importe");
        jLabel6.setName("jLabel6"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jLabel6, gridBagConstraints);

        txtImporteTotal.setEditable(false);
        txtImporteTotal.setBackground(new java.awt.Color(255, 255, 255));
        txtImporteTotal.setFont(new java.awt.Font("Dialog", 0, 70)); // NOI18N
        txtImporteTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImporteTotal.setName("txtImporteTotal"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        getContentPane().add(txtImporteTotal, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jLabel4.setText("Tarifa");
        jLabel4.setName("jLabel4"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jLabel4, gridBagConstraints);

        txtTarifa.setEditable(false);
        txtTarifa.setBackground(new java.awt.Color(255, 255, 255));
        txtTarifa.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        txtTarifa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTarifa.setName("txtTarifa"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 2.0;
        getContentPane().add(txtTarifa, gridBagConstraints);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectopare/pantallas/resources/pare valet parking 1.jpg"))); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        getContentPane().add(jLabel5, gridBagConstraints);

        jPanel1.setName("jPanel1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtFechaEntrada;
    private javax.swing.JTextField txtFechaSalida;
    private javax.swing.JTextField txtHoraEntrada;
    private javax.swing.JTextField txtHoraSalida;
    private javax.swing.JTextField txtImporteTotal;
    private javax.swing.JTextField txtTarifa;
    private javax.swing.JTextField txtTiempoEstadia;
    // End of variables declaration//GEN-END:variables

    

}
