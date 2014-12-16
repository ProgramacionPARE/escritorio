
package vistas;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelos.Main;
import sockets.ClienteMonitor;

/**
 *
 * @author oscar
 */
public class FrmMonitor extends javax.swing.JFrame {

    /**
     * Creates new form FrmMonitor
     */
    public FrmMonitor() {
        super("Monitor");
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    public void autoNoEncontrado(){
        JOptionPane.showMessageDialog(rootPane, "No se encontro auto, con el progresivo dado");
    }
    public void actualizarCentros() {
        Container contentPane = this.getContentPane();
        contentPane.removeAll();
        ArrayList<ClienteMonitor> clienteMonitor = Main.getInstance().getClienteMonitor();
        Iterator<ClienteMonitor> iterator = clienteMonitor.iterator();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        while(iterator.hasNext()){
            final ClienteMonitor next = iterator.next();
            Container container = new Container();
            container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
            JLabel nombre = new JLabel(next.getEstacionamiento().getNombre().toUpperCase()+":   ");
            nombre.setFont(new Font("Serif", Font.PLAIN, 34));
            container.add(Box.createRigidArea(new Dimension(40,100)));
            container.add(nombre);
            JLabel estado = new JLabel();
            estado.setFont(new Font("Serif", Font.PLAIN, 34));
            if(next.getSocket()!= null && next.getSocket().isConnected()){
                estado.setForeground(Color.green);
                estado.setText("Conectado");
            }else{
                estado.setForeground(Color.red);
                estado.setText("Desconectado");
            }
            container.add(estado);
            container.add(Box.createRigidArea(new Dimension(40,100)));
            // JTextField
            final JTextField progresivo = new JTextField(10);
            progresivo.setFont(new Font("Serif", Font.PLAIN, 30));
            progresivo.setMaximumSize(new Dimension(200, 50));
            progresivo.setHorizontalAlignment(JTextField.CENTER);
            progresivo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if( progresivo.getText()!="")
                        next.enviarProgresivo( progresivo.getText());
                    else
                        JOptionPane.showMessageDialog(rootPane, "Introduce un folio");
                }
            });
            
            container.add(progresivo);
            container.add(Box.createRigidArea(new Dimension(40,100)));
            
            
//            ////JButon
//            JButton consulta = new JButton("Consulta");
//            consulta.setBackground(Color.white);
//            consulta.setFont(new Font("Serif", Font.PLAIN, 30));
//            consulta.setOpaque(true);
//            consulta.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    if( progresivo.getText()!="")
//                        next.enviarProgresivo( progresivo.getText());
//                    else
//                        JOptionPane.showMessageDialog(rootPane, "Introduce un folio");
//                }
//            });
//            container.add(consulta);
//            container.add(Box.createRigidArea(new Dimension(40,100)));
            contentPane.add(container);
            
        }
         
        this.pack();
        this.setLocationRelativeTo(null);
     }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Iterator<ClienteMonitor> iterator = Main.getInstance().getClienteMonitor().iterator();
        while(iterator.hasNext()){
            ClienteMonitor next = iterator.next();
            next.apagarHilo();
            try {if(next.getSocket()!=null)
                    next.getSocket().close();
            } catch (IOException ex) {
                Logger.getLogger(FrmMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_formWindowClosing

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

   
}
