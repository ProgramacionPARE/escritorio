

package vistas;

import ModelosAux.Tiempo;
import modelos.Turno;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import modelos.Auto;
import modelos.Estacionamiento;
import org.jdesktop.application.Action;
import vistas.formatos.FrmBoletoPerdido;


public class FrmMenuParking extends javax.swing.JDialog implements Runnable {
    Frame parent;
    Turno turno;
    Estacionamiento  estacionamiento;
    FrmMenuParking frmMenuParking;
    
    public FrmMenuParking(final java.awt.Frame parent, boolean modal,final Turno turno,   final Estacionamiento  estacionamiento) {
        super(parent,"Estacionamiento", modal);
        this.parent = parent;
        this.turno = turno;
        this.frmMenuParking = this;
        this.estacionamiento = estacionamiento;
        initComponents();
        this.getContentPane().setBackground(Color.white);
        pack();
        
        ///////////////// Atajos de teclado
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "exit");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "entrada");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), "perdido");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), "cancelado");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), "recibo");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), "malEstado");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0), "salida");
        getRootPane().getActionMap().put("exit", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSalir();
            }
        });
        getRootPane().getActionMap().put("entrada", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onEntrada();
            }
        });
        getRootPane().getActionMap().put("perdido", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onBoletoPerdido();
            }
        });
        getRootPane().getActionMap().put("cancelado", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onBoletoCancelado();
            }
        });
        getRootPane().getActionMap().put("recibo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onReciboPago();
            }
        });
        getRootPane().getActionMap().put("malEstado", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onBoletoIlegible();
            }
        });
        getRootPane().getActionMap().put("salida", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSalida();
            }
        });
        
        if(estacionamiento.getTipo().equals("Autoservicio")){
            btnBoletoCancelado.setVisible(false);
            btnBoletoMaltratado.setVisible(false);
            btnReciboPago.setVisible(false);
            btnSalida.setVisible(false);
        }
        pack();
        
        FrmPrincipal.nuevaVentana(this);
        /////////////////////////////////
        setLocationRelativeTo(parent);
        setVisible(true);
        new Thread(this).start();
    }
    
    @Action
    public void onEntrada() {
        if(estacionamiento.getTipo().equals("Valet"))
            new FrmEntradaValet(parent,true,turno,estacionamiento);
        else if(estacionamiento.getTipo().equals("Autoservicio"))
            new FrmEntradaAutoservicio(parent,true,turno,estacionamiento);
        else if(estacionamiento.getTipo().equals("ValetMasivo"))
            //new FrmLeerCodigoBarrasValet(parent,true,turno,estacionamiento);
            new FrmLeerCodigoBarras(parent,true,turno,"ENTRADA",estacionamiento);
    }
    
    @Action
    public void onBoletoPerdido() {
        if(!estacionamiento.getTipo().equals("Autoservicio")){
            new FrmLeerCodigoBarras(parent,true,turno,"PERDIDO",estacionamiento);
        }else{
            Auto auto = new Auto();
            auto.setHoraEntrada(Tiempo.getHora());
            auto.setFechaEntrada(Tiempo.getFecha());
            new FrmBoletoPerdido(parent, true, turno,auto,estacionamiento);
        }
    }

    @Action
    public void onBoletoCancelado() {
         new FrmLeerCodigoBarras(parent, true,turno,"CANCELAR",estacionamiento);
    }
    @Action
    public void onReciboPago() {
        new FrmLeerCodigoBarras(parent, true,turno,"RECIBO",estacionamiento);
    }
    
    @Action
    public void onBoletoIlegible() {
        new FrmLeerCodigoBarras(parent, true,turno,"CONTRA",estacionamiento);
    }
    
    @Action
    public void onSalida() {
        new FrmLeerCodigoBarras(parent, true,turno,"COBRO",estacionamiento);
    }
    
    
    @Action
    public void onSalir() {
        this.dispose();
    }
   
    
    @Override
    public void run() {
        while(true){
            try {
                lblNoAutos.setText(String.valueOf(Auto.getAutosPendientes().size()));
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(FrmMenuParking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEntrada = new javax.swing.JButton();
        btnBoletoPerdido = new javax.swing.JButton();
        btnBoletoCancelado = new javax.swing.JButton();
        btnSalida = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblNoAutos = new javax.swing.JLabel();
        btnReciboPago = new javax.swing.JButton();
        btnBoletoMaltratado = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(FrmMenuParking.class, this);
        btnEntrada.setAction(actionMap.get("onEntrada")); // NOI18N
        btnEntrada.setBackground(new java.awt.Color(255, 255, 255));
        btnEntrada.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnEntrada.setText("<html><b>Entrada</b><br><center>(F2)</center></html>");
        btnEntrada.setName("btnEntrada"); // NOI18N
        getContentPane().add(btnEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 70, 200, 110));

        btnBoletoPerdido.setAction(actionMap.get("onBoletoPerdido")); // NOI18N
        btnBoletoPerdido.setBackground(new java.awt.Color(255, 255, 255));
        btnBoletoPerdido.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnBoletoPerdido.setText("<html><b>Boleto perdido</b><br><center>(F3)</center></html>");
        btnBoletoPerdido.setName("btnBoletoPerdido"); // NOI18N
        getContentPane().add(btnBoletoPerdido, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 70, 200, 50));

        btnBoletoCancelado.setAction(actionMap.get("onBoletoCancelado")); // NOI18N
        btnBoletoCancelado.setBackground(new java.awt.Color(255, 255, 255));
        btnBoletoCancelado.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnBoletoCancelado.setText("<html><b>Boleto cancelado</b><br><center>(F4)</center></html>");
        btnBoletoCancelado.setName("btnBoletoCancelado"); // NOI18N
        getContentPane().add(btnBoletoCancelado, new org.netbeans.lib.awtextra.AbsoluteConstraints(454, 70, 200, 50));

        btnSalida.setAction(actionMap.get("onSalida")); // NOI18N
        btnSalida.setBackground(new java.awt.Color(255, 255, 255));
        btnSalida.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnSalida.setText("<html><b>Salida</b><br><center>(F7)</center></html>");
        btnSalida.setName("btnSalida"); // NOI18N
        getContentPane().add(btnSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(672, 70, 200, 110));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Autos en estacionamiento:");
        jLabel1.setName("jLabel1"); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 20, 253, 29));

        lblNoAutos.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblNoAutos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoAutos.setText("0");
        lblNoAutos.setName("lblNoAutos"); // NOI18N
        getContentPane().add(lblNoAutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(277, 17, 65, -1));

        btnReciboPago.setAction(actionMap.get("onReciboPago")); // NOI18N
        btnReciboPago.setBackground(new java.awt.Color(255, 255, 255));
        btnReciboPago.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnReciboPago.setText("<html><center><b>Reimpimir recibo</b><br>(F5)</center></html>");
        btnReciboPago.setName("btnReciboPago"); // NOI18N
        getContentPane().add(btnReciboPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 130, 200, -1));

        btnBoletoMaltratado.setBackground(new java.awt.Color(255, 255, 255));
        btnBoletoMaltratado.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnBoletoMaltratado.setText("<html><b>Codigo ilegiblle</b><br><center>(F6)</center></html>");
        btnBoletoMaltratado.setIconTextGap(1);
        btnBoletoMaltratado.setName("btnBoletoMaltratado"); // NOI18N
        getContentPane().add(btnBoletoMaltratado, new org.netbeans.lib.awtextra.AbsoluteConstraints(454, 129, 200, 50));

        jLabel2.setText(" ");
        jLabel2.setName("jLabel2"); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 180, 50, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        
       
    }//GEN-LAST:event_formWindowGainedFocus

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
 
    }//GEN-LAST:event_formWindowStateChanged

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        FrmPrincipal.restaurarUltimaVentana();
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBoletoCancelado;
    private javax.swing.JButton btnBoletoMaltratado;
    private javax.swing.JButton btnBoletoPerdido;
    private javax.swing.JButton btnEntrada;
    private javax.swing.JButton btnReciboPago;
    private javax.swing.JButton btnSalida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblNoAutos;
    // End of variables declaration//GEN-END:variables


}
