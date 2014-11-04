/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Auto;
import modelos.Estacionamiento;
import modelos.Turno;

/**
 *
 * @author sistema
 */
public class FrmMensajeCliente extends javax.swing.JDialog implements Runnable {
    private Frame parent;
    private Turno turno;
    private Estacionamiento estacionamiento;
    
    public FrmMensajeCliente(java.awt.Frame parent, boolean modal,String mensaje,Turno turno,Estacionamiento estacionameinto) {
        super(parent, modal);
        initComponents();
        this.parent = parent;
        this.getContentPane().setBackground(Color.white);
        pack();
        setLocationRelativeTo(parent);
        
        Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize(); 
        setBounds(0, 0,  screenSize.width,  screenSize.height); 

        if(mensaje.equals("COBRO")){
            
        }

        new Thread(this).start();
        setVisible(true);
    }
    public void run() {
           while(true){
               try {
                    Auto auto = Auto.getCambioEstadoServidor();
                    if(auto != null){
                        if(auto.getEstadoServidor()== 7){ 
                            auto.setEstadoServidor(0);
                            auto.actualizarEstadoServidor();
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 15, 0};
        layout.rowHeights = new int[] {0, 50, 0};
        getContentPane().setLayout(layout);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 50)); // NOI18N
        jLabel1.setText("Gracias por su visita");
        jLabel1.setName("jLabel1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        getContentPane().add(jLabel1, gridBagConstraints);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectopare/pantallas/resources/pare valet parking 1.jpg"))); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        getContentPane().add(jLabel2, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
