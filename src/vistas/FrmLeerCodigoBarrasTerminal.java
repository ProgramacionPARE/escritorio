
package vistas;

import ModelosAux.Seguridad;
import ModelosAux.Tiempo;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelos.Auto;
import modelos.Configuracion;
import modelos.Empleado;
import modelos.Estacionamiento;
import modelos.Main;
import modelos.Mensaje;
import modelos.Progresivo;
import modelos.Rest;
import modelos.Turno;
import net.sourceforge.barbecue.BarcodeException;
import vistas.formatos.FrmP1BoletoCliente;
import vistas.formatos.FrmP2BoletoLlaves;
import vistas.formatos.FrmP3BoletoParabrisas;


public class FrmLeerCodigoBarrasTerminal extends JFrame implements Runnable {
    String id;
    Turno turno;
    String accion;
    Estacionamiento estacionamiento;
    Socket socket;
    ObjectInputStream entrada;
    ObjectOutputStream salida;
    Thread t1;
    /**
     * Creates new form FrmLeerCodigoBarras
     */
    public FrmLeerCodigoBarrasTerminal(String accion) {
        super(accion);
        this.socket = Main.getInstance().getSocket();
        this.entrada = Main.getInstance().getEntrada();
        this.salida = Main.getInstance().getSalida();
        this.turno = Main.getInstance().getTurnoActual();
        this.accion = accion;
        this.estacionamiento =  Main.getInstance().getEstacionamiento();
        initComponents();
        this.getContentPane().setBackground(Color.white);
          pack();
        id ="";
        if(accion.equals("CLIENTE")){
            lblMensaje.setText("Coloque su boleto por favor");
        }else if(accion.equals("EXPEDIDOR")){
            lblMensaje.setText("Coloque tu gafete");
        }
       
        this.lblMensaje.setFont(new Font("Dialog", 1, 50));
        Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize();     
        setBounds(0, 0,  screenSize.width,  screenSize.height); 
        if(!accion.equals(Configuracion.CLIENTE)){
           this.lblBienvenido.setVisible(false); 
        }
        t1 = new Thread(this);
        t1.start();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblMensaje = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblBienvenido = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 20, 0};
        layout.rowHeights = new int[] {0, 20, 0, 20, 0, 20, 0};
        getContentPane().setLayout(layout);

        lblMensaje.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMensaje.setName("lblMensaje"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(lblMensaje, gridBagConstraints);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/resources/codigoBarras.jpg"))); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = -400;
        gridBagConstraints.ipady = -145;
        getContentPane().add(jLabel2, gridBagConstraints);

        lblBienvenido.setFont(new java.awt.Font("Dialog", 1, 50)); // NOI18N
        lblBienvenido.setText("Bienvenido");
        lblBienvenido.setName("lblBienvenido"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        getContentPane().add(lblBienvenido, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

        
    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        Auto auto=null;
        if (evt.getKeyChar() == '\n') { 
            if (id.length() ==12 ){
                if(accion.equals("CLIENTE")){
                    try {
                        salida.writeObject(new Mensaje(Mensaje.CLIENTE,id));
                    } catch (IOException ex) {
                        Logger.getLogger(FrmLeerCodigoBarrasTerminal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(accion.equals("EXPEDIDOR")){
                    Empleado empleado = null;
                    empleado = Empleado.getByIdClave(id);
                    if (empleado != null){
                        try {
                            Auto newAuto = new Auto(Progresivo.getUltimoProgresivo(estacionamiento.getCaseta(),"0"),
                                    "",Tiempo.getFecha(),Tiempo.getHora(),"","","",turno.getId(),"0",
                                    "",Seguridad.getClave(5), estacionamiento.getCaseta().getId());
                            
                            //Aumento en uno los boletos generados
                            turno.getDetallesTurno().get(newAuto.getSerie()).setNoBol(turno.getDetallesTurno().get(newAuto.getSerie()).getNoBol()+1);
                            //Actualizo el folio final en el turno
                            turno.getDetallesTurno().get(newAuto.getSerie()).setFolioFinal (turno.getDetallesTurno().get(newAuto.getSerie()).getFolioFinal()+1);
                            
                            Progresivo.setProgresivoMasUno(estacionamiento.getCaseta(),newAuto.getSerie());
                            //Imprimo boletos
                            PrinterJob job = PrinterJob.getPrinterJob();
                            // Boleto al cliente
                            
                            new FrmP1BoletoCliente(new Dialog(this), false,job,newAuto,empleado);
                            //Boleto llaves
                            new FrmP2BoletoLlaves(new Dialog(this), false,job,newAuto,empleado);
                            //Boleto Parabrisas
                            new FrmP3BoletoParabrisas(new Dialog(this), false ,job,newAuto);
                            turno.actualizar();
                            // Guardo entrada y actualizo progresivo
                            newAuto.guardar();
                            Rest.sendAuto(newAuto,estacionamiento);
                        } catch (PrinterException ex) {
                            JOptionPane.showMessageDialog(this, "Hay un probrema con la impresora, verifica que todo este correctamente conectado e intenta de nuevo.", 
                                    "Error de impresion",JOptionPane.ERROR_MESSAGE);
                            Logger.getLogger(FrmLeerCodigoBarrasTerminal.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (BarcodeException ex) {
                            Logger.getLogger(FrmLeerCodigoBarrasTerminal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                    }
                }
                id = "";
            }else{
                id = "";
            }
        }else
            id+=evt.getKeyChar();  
    }//GEN-LAST:event_formKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if(!Configuracion.getInstancia().getTerminal().equals(Configuracion.CAJA)){
            //parent.setVisible(false);
            //parent.dispose();
            //parent = null;
        }
    }//GEN-LAST:event_formWindowClosing

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
      
    }//GEN-LAST:event_formWindowOpened

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblBienvenido;
    private javax.swing.JLabel lblMensaje;
    // End of variables declaration//GEN-END:variables



    @Override
    public void run() {
        FrmCobroCliente frmCobroCliente=null; 
        while (true){
            Mensaje mensaje = null;
            try {
                mensaje = (Mensaje)entrada.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(FrmLeerCodigoBarrasTerminal.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(mensaje.getTipo().equals("cliente")){
                //Auto auto;
                //auto = Auto.getByProgresivoClave((String)mensaje.getMensaje());
            }else  if(mensaje.getTipo().equals("autoCalculo")){
                if(frmCobroCliente == null){
                    frmCobroCliente = new FrmCobroCliente(this,false,((Auto)mensaje.getMensaje()));
                }else{
                    frmCobroCliente.setAuto(((Auto)mensaje.getMensaje()));
                    frmCobroCliente.calcularImporte();
                }
            }
             
        }
    }
}
