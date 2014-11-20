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
import javax.swing.JDialog;
import modelos.Auto;
import modelos.Estacionamiento;
import modelos.Turno;

/**
 *
 * @author sistema
 */
public class FrmMensajeCliente extends JDialog implements Runnable {
    public static final int COBRADO= 0;
    public static final int CANCELADO= 1;
    public static final int REVIRADO= 2;
    
    private Frame parent;
    private Turno turno;
    private Estacionamiento estacionamiento;
    
    public FrmMensajeCliente(Frame parent , boolean modal, int mensaje) {
        super(parent,"Mensaje",modal);
        initComponents();
        this.parent = parent;
        this.getContentPane().setBackground(Color.white);
        pack();
        setLocationRelativeTo(parent);
        
        Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize(); 
        setBounds(0, 0,  screenSize.width,  screenSize.height); 

        if(mensaje == COBRADO){
                
        }else if(mensaje==CANCELADO){
            lbl1.setForeground(new java.awt.Color(255, 0, 0));
            lbl1.setText("No se registro su pago");
            lbl2.setText("No entregue su boleto, coloque de nuevo en el sensor");
            
        }else if(mensaje == REVIRADO){
            lbl1.setForeground(new java.awt.Color(255, 0, 0));
            
            lbl1.setText("Este boleto ya fue cobrado");
            lbl2.setText("Tome una foto y obtenga una cortesia.");
        }
        new Thread(this).start();
        setVisible(true);
    }
    
        @Override
    public void run() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException ex) {
            Logger.getLogger(FrmMensajeCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lbl1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 15, 0};
        layout.rowHeights = new int[] {0, 50, 0, 50, 0, 50, 0, 50, 0};
        getContentPane().setLayout(layout);

        lbl1.setFont(new java.awt.Font("Dialog", 1, 50)); // NOI18N
        lbl1.setText("Gracias por su visita");
        lbl1.setName("lbl1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        getContentPane().add(lbl1, gridBagConstraints);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectopare/pantallas/resources/pare valet parking 1.jpg"))); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        getContentPane().add(jLabel2, gridBagConstraints);

        lbl2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lbl2.setText("Puede entregar su boleto ahora");
        lbl2.setName("lbl2"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        getContentPane().add(lbl2, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    // End of variables declaration//GEN-END:variables


}
