

package vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Configuracion;
import modelos.Main;
import modelos.Mensaje;
import modelos.Principal;


public class FrmErrorCarga extends javax.swing.JFrame implements Runnable {
    private Socket socket;
    private Thread t1;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private boolean cerrar;
    public FrmErrorCarga(java.awt.Frame parent, boolean modal,Socket socket) {
        super("Error");
        this.cerrar = true;
        initComponents();
        this.getContentPane().setBackground(Color.white);
        pack();
        setLocationRelativeTo(parent);
        Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize(); 
        setBounds(0, 0,  screenSize.width,  screenSize.height);
        this.socket = socket;
        this.t1 = new Thread(this);
        t1.start();
        this.setVisible(true);
    }
    
    @Override
    public void run() {
        while(cerrar){
            try {
                if (socket == null){
                    socket = new Socket(Configuracion.getInstancia().getIp(),8123);
                    Main.getInstance().setSocket(socket);
                    break;
                }
                else
                    break;
            }catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    Thread.sleep(5000);
                }catch (InterruptedException ex1) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }        
        } 
        if(socket!=null){
            try {
                entrada = new ObjectInputStream( socket.getInputStream());
                salida = new ObjectOutputStream(socket.getOutputStream());
                salida.flush();
                Main.getInstance().setEntrada(entrada);
                Main.getInstance().setSalida(salida);
            } catch (IOException ex) {
                Logger.getLogger(FrmLeerCodigoBarrasTerminal.class.getName()).log(Level.SEVERE, null, ex);
            }
            jLabel1.setText("Error turno cerrado");
            jLabel2.setText("Revisa que exista turno abierto en caja.");

            while(cerrar){
                try {
                    salida.writeObject(new Mensaje("turnoAbierto",""));
                    Mensaje mensaje = (Mensaje)entrada.readObject();
                    if(mensaje.getTipo().equals("turnoAbierto")){
                        if((boolean)mensaje.getMensaje())
                            break;
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(FrmErrorCarga.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.dispose();
            if( Configuracion.getInstancia().getTerminal().equals(Configuracion.CLIENTE)){
                new FrmLeerCodigoBarrasTerminal("CLIENTE");
            }
        }
        if(cerrar)
            this.dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0};
        layout.rowHeights = new int[] {0, 20, 0};
        getContentPane().setLayout(layout);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setText("Error de conexion");
        jLabel1.setName("jLabel1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        getContentPane().add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Verifica que el sistema en caja este funcionando.");
        jLabel2.setName("jLabel2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        getContentPane().add(jLabel2, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            cerrar=false;
            if(socket!=null){
                entrada.close();
                salida.close();
                socket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(FrmErrorCarga.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

}