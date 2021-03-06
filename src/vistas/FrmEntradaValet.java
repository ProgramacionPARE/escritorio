package vistas;

import ModelosAux.Seguridad;
import ModelosAux.Tiempo;
import java.awt.Color;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelos.Auto;
import modelos.Estacionamiento;
import modelos.Main;
import modelos.Progresivo;
import modelos.Rest;
import modelos.Turno;
import net.sourceforge.barbecue.BarcodeException;
import org.jdesktop.application.Action;
import vistas.formatos.FrmP1BoletoCliente;
import vistas.formatos.FrmP2BoletoLlaves;
import vistas.formatos.FrmP3BoletoParabrisas;

/**
 *
 * @author Asistente Proyectos2
 */
public class FrmEntradaValet extends javax.swing.JDialog {

    Turno turno;
    Estacionamiento estacionamiento;

    /**
     * Creates new form FrmParkingEntrada
     */
    public FrmEntradaValet(java.awt.Frame parent, boolean modal) {
        super(parent, "Entrada nueva", modal);
        initComponents();
        this.estacionamiento =  Main.getInstance().getEstacionamiento();
        this.turno = Main.getInstance().getTurnoActual();
        this.getContentPane().setBackground(Color.white);
        txtFechaEntrada.setText(Tiempo.getFecha());
        txtHoraEntrada.setText(Tiempo.getHora());
        txtCajero.setText(turno.getEmpleadoEntrada().getNombre());
        txtProgresivo.setText(Progresivo.getUltimoProgresivo(estacionamiento.getCaseta(), "0"));
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private boolean validaCamposEntrada() {
        if (txtMatricula.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Introduce las placas del auto",
                    "Campo faltante", JOptionPane.WARNING_MESSAGE);
            txtMatricula.grabFocus();
            return false;
        }
        if (txtMatricula.getText().length() < 3) {
            JOptionPane.showMessageDialog(this, "La placa tiene que tener al menos 3 letras",
                    "Formato erroneo", JOptionPane.WARNING_MESSAGE);
            txtMatricula.grabFocus();
            return false;
        }

        return true;
    }

    @Action
    public void onGuardarAuto() {
        //Valido campos
        if (!validaCamposEntrada()) {
            return;
        }
        //Confirmo accion
        int confirmDialog = JOptionPane.showConfirmDialog(this, "¿Se imprimira el boleto estas seguro?",
                "Imprimir boleto", JOptionPane.YES_NO_CANCEL_OPTION);
        if (confirmDialog == JOptionPane.YES_OPTION) {

            try {
                Auto auto = new Auto(txtProgresivo.getText(), txtMatricula.getText(),
                        txtFechaEntrada.getText(), txtHoraEntrada.getText(), "", txtModelo.getText(), "", turno.getId(), "0",
                        txtNotas.getText(), Seguridad.getClave(5),
                        estacionamiento.getCaseta().getId());
                auto.setDentro(true);
                //Aumento en uno los boletos generados
                turno.getDetallesTurno().get(auto.getSerie()).setNoBol(turno.getDetallesTurno().get(auto.getSerie()).getNoBol() + 1);
                //Actualizo el folio final en el turno
                turno.getDetallesTurno().get(auto.getSerie()).setFolioFinal(turno.getDetallesTurno().get(auto.getSerie()).getFolioFinal() + 1);
               
                // Guardo entrada y actualizo progresivo
                
                Progresivo.setProgresivoMasUno(estacionamiento.getCaseta(), auto.getSerie());
                //Imprimo boletos
                PrinterJob job = PrinterJob.getPrinterJob();
                // Boleto al cliente
                new FrmP1BoletoCliente(this, false, job, auto, turno.getEmpleadoEntrada());
                //Boleto llaves
                new FrmP2BoletoLlaves(this, false,  job, auto, turno.getEmpleadoEntrada());
                //Boleto Parabrisas
                new FrmP3BoletoParabrisas(this, false, job, auto);
               
                turno.actualizar();
                Rest.sendTurnoDetalle(  turno.getDetallesTurno().get(auto.getSerie()), Main.getInstance().getEstacionamiento());
                auto.guardar();
                Rest.sendAuto(auto, estacionamiento);
                this.setVisible(false);
                this.dispose();
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(this, "Hay un probrema con la impresora, verifica que todo este correctamente conectado e intenta de nuevo.",
                        "Error de impresion", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(FrmEntradaValet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BarcodeException ex) {
                Logger.getLogger(FrmEntradaValet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMatricula = new javax.swing.JTextField();
        txtModelo = new javax.swing.JTextField();
        txtNotas = new javax.swing.JTextField();
        btnEntrada = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Entrada:");
        jLabel1.setName("jLabel1"); // NOI18N

        txtHoraEntrada.setEditable(false);
        txtHoraEntrada.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtHoraEntrada.setName("txtHoraEntrada"); // NOI18N

        txtFechaEntrada.setEditable(false);
        txtFechaEntrada.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtFechaEntrada.setName("txtFechaEntrada"); // NOI18N

        jLabel2.setText("Cajero:");
        jLabel2.setName("jLabel2"); // NOI18N

        txtCajero.setEditable(false);
        txtCajero.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCajero.setName("txtCajero"); // NOI18N

        jLabel3.setText("Progresivo:");
        jLabel3.setName("jLabel3"); // NOI18N

        txtProgresivo.setEditable(false);
        txtProgresivo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtProgresivo.setName("txtProgresivo"); // NOI18N

        jLabel4.setText("Matricula");
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText("Modelo");
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel7.setText("Notas:");
        jLabel7.setName("jLabel7"); // NOI18N

        txtMatricula.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        txtMatricula.setName("txtMatricula"); // NOI18N
        txtMatricula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMatriculaKeyReleased(evt);
            }
        });

        txtModelo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtModelo.setName("txtModelo"); // NOI18N

        txtNotas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNotas.setName("txtNotas"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(FrmEntradaValet.class, this);
        btnEntrada.setAction(actionMap.get("onGuardarAuto")); // NOI18N
        btnEntrada.setBackground(new java.awt.Color(255, 255, 255));
        btnEntrada.setText("Entrada");
        btnEntrada.setName("btnEntrada"); // NOI18N

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
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(txtHoraEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtFechaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtCajero)
                                .addComponent(txtMatricula))
                            .addComponent(txtNotas)
                            .addComponent(txtModelo)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProgresivo, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtProgresivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMatriculaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatriculaKeyReleased
        txtMatricula.setText(txtMatricula.getText().toUpperCase());
    }//GEN-LAST:event_txtMatriculaKeyReleased

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrada;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtCajero;
    private javax.swing.JTextField txtFechaEntrada;
    private javax.swing.JTextField txtHoraEntrada;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtNotas;
    private javax.swing.JTextField txtProgresivo;
    // End of variables declaration//GEN-END:variables
}
