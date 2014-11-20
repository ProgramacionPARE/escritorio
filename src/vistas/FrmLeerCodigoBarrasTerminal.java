
package vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JFrame;
import modelos.Auto;
import modelos.Configuracion;
import modelos.Estacionamiento;
import modelos.Main;
import modelos.Turno;


public class FrmLeerCodigoBarrasTerminal extends JFrame  {
    private String id;
    private Turno turno;
    private String accion;
    private Estacionamiento estacionamiento;
    private Socket socket;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private Thread t1;
    private boolean cerrar;
    private Frame parent;
    private boolean cierre;

    
    public FrmLeerCodigoBarrasTerminal(String accion) {
        super("Cobro");
        
        this.turno = Main.getInstance().getTurnoActual();
        this.accion = accion;
        this.estacionamiento =  Main.getInstance().getEstacionamiento();
        initComponents();
        this.getContentPane().setBackground(Color.white);
          pack();
        id ="";
        if(accion.equals(Configuracion.CAJA)){
            lblMensaje.setText("Coloque su boleto por favor");
        }else if(accion.equals("EXPEDIDOR")){
            lblMensaje.setText("Coloque tu gafete");
        }
       
        this.lblMensaje.setFont(new Font("Dialog", 1, 50));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        if(!accion.equals(Configuracion.CLIENTE)){
           this.lblBienvenido.setVisible(false); 
        }
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMensaje = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblBienvenido = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        lblMensaje.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMensaje.setName("lblMensaje"); // NOI18N
        getContentPane().add(lblMensaje, new java.awt.GridBagConstraints());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/resources/codigoBarras.jpg"))); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        getContentPane().add(jLabel2, new java.awt.GridBagConstraints());

        lblBienvenido.setFont(new java.awt.Font("Dialog", 1, 50)); // NOI18N
        lblBienvenido.setText("Bienvenido");
        lblBienvenido.setName("lblBienvenido"); // NOI18N
        getContentPane().add(lblBienvenido, new java.awt.GridBagConstraints());

        pack();
    }// </editor-fold>//GEN-END:initComponents

        
    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        Auto auto=null;
        if (evt.getKeyChar() == '\n') { 
            if (id.length() ==12 ){
                if(accion.equals("CLIENTE")){
                    Main.getInstance().getClientePantalla().enviarCodigo(id);
                }
                id = "";
            }else{
                id = "";
            }
        }else
            id+=evt.getKeyChar();  
    }//GEN-LAST:event_formKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblBienvenido;
    private javax.swing.JLabel lblMensaje;
    // End of variables declaration//GEN-END:variables

}