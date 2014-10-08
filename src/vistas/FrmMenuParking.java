

package vistas;

import ModelosAux.Tiempo;
import modelos.Turno;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
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
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), "entrada");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), "perdido");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0), "prenda");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), "cancelado");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), "recibo");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), "malEstado");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), "Salida");
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
        getRootPane().getActionMap().put("prenda", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //onSalir();
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
                //onSalir();
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
            btnObjetoPrenda.setVisible(false);
            btnReciboPago.setVisible(false);
            btnSalida.setVisible(false);
        }
        pack();
        
        FrmPrincipal.nuevaVentana(this);
        /////////////////////////////////
        setLocationRelativeTo(parent);
        setVisible(true);
    }
    
    @Action
    public void onEntrada() {
        if(estacionamiento.getTipo().equals("Valet"))
            new FrmEntradaValet(parent,true,turno,estacionamiento);
        else if(estacionamiento.getTipo().equals("Autoservicio"))
            new FrmEntradaAutoservicio(parent,true,turno,estacionamiento);
        else if(estacionamiento.getTipo().equals("ValetMasivo"))
            new FrmLeerCodigoBarrasValet(parent,true,turno,estacionamiento);
        
    }
    
    @Action
    public void onSalida() {
        new FrmLeerCodigoBarras(parent, true,turno,"COBRO",estacionamiento);
    }
    
    @Action
    public void onBoletoPerdido() {
        if(estacionamiento.getTipo().equals("Valet"))
            new FrmBoletoPerdidoPlaca(parent, true, turno,estacionamiento);
        else if(estacionamiento.getTipo().equals("Autoservicio")){
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
    public void onSalir() {
        this.dispose();
    }
   
    
    @Override
    public void run() {
       lblNoAutos.setText(String.valueOf(Auto.getAutosPendientes(turno).size()));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEntrada = new javax.swing.JButton();
        btnBoletoPerdido = new javax.swing.JButton();
        btnObjetoPrenda = new javax.swing.JButton();
        btnBoletoCancelado = new javax.swing.JButton();
        btnSalida = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblNoAutos = new javax.swing.JLabel();
        btnReciboPago = new javax.swing.JButton();
        btnBoletoMaltratado = new javax.swing.JButton();

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

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(FrmMenuParking.class, this);
        btnEntrada.setAction(actionMap.get("onEntrada")); // NOI18N
        btnEntrada.setBackground(new java.awt.Color(255, 255, 255));
        btnEntrada.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnEntrada.setText("<html><b>Entrada</b><br><center>(F5)</center></html>");
        btnEntrada.setName("btnEntrada"); // NOI18N

        btnBoletoPerdido.setAction(actionMap.get("onBoletoPerdido")); // NOI18N
        btnBoletoPerdido.setBackground(new java.awt.Color(255, 255, 255));
        btnBoletoPerdido.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnBoletoPerdido.setText("<html><b>Boleto perdido</b><br><center>(F6)</center></html>");
        btnBoletoPerdido.setName("btnBoletoPerdido"); // NOI18N

        btnObjetoPrenda.setBackground(new java.awt.Color(255, 255, 255));
        btnObjetoPrenda.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnObjetoPrenda.setText("<html><b>Objeto  prenda</b><br><center>(F7)</center></html>");
        btnObjetoPrenda.setName("btnObjetoPrenda"); // NOI18N

        btnBoletoCancelado.setAction(actionMap.get("onBoletoCancelado")); // NOI18N
        btnBoletoCancelado.setBackground(new java.awt.Color(255, 255, 255));
        btnBoletoCancelado.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnBoletoCancelado.setText("<html><b>Boleto cancelado</b><br><center>(F8)</center></html>");
        btnBoletoCancelado.setName("btnBoletoCancelado"); // NOI18N

        btnSalida.setAction(actionMap.get("onSalida")); // NOI18N
        btnSalida.setBackground(new java.awt.Color(255, 255, 255));
        btnSalida.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnSalida.setText("<html><b>Salida</b><br><center>(F12)</center></html>");
        btnSalida.setName("btnSalida"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Autos en estacionamiento:");
        jLabel1.setName("jLabel1"); // NOI18N

        lblNoAutos.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblNoAutos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoAutos.setText("0");
        lblNoAutos.setName("lblNoAutos"); // NOI18N

        btnReciboPago.setAction(actionMap.get("onReciboPago")); // NOI18N
        btnReciboPago.setBackground(new java.awt.Color(255, 255, 255));
        btnReciboPago.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnReciboPago.setText("<html><b>Recibo de pago</b><br><center>(F9)</center></html>");
        btnReciboPago.setName("btnReciboPago"); // NOI18N

        btnBoletoMaltratado.setBackground(new java.awt.Color(255, 255, 255));
        btnBoletoMaltratado.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnBoletoMaltratado.setText("<html><b>Boleto maltratado</b><br><center>(F10)</center></html>");
        btnBoletoMaltratado.setIconTextGap(1);
        btnBoletoMaltratado.setName("btnBoletoMaltratado"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBoletoPerdido, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReciboPago, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(btnObjetoPrenda, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBoletoCancelado, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBoletoMaltratado, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(btnSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNoAutos, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNoAutos))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBoletoPerdido, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnReciboPago, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnObjetoPrenda, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBoletoCancelado, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnBoletoMaltratado, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        new Thread(this).start();
       
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
    private javax.swing.JButton btnObjetoPrenda;
    private javax.swing.JButton btnReciboPago;
    private javax.swing.JButton btnSalida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblNoAutos;
    // End of variables declaration//GEN-END:variables


}
