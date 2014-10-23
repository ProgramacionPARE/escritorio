
package vistas;

import java.awt.Color;
import java.awt.Frame;
import java.awt.print.PrinterJob;
import javax.swing.JFrame;
import modelos.Auto;
import modelos.Estacionamiento;
import modelos.Turno;
import vistas.formatos.FrmBoletoCancelado;
import vistas.formatos.FrmBoletoPerdido;
import vistas.formatos.FrmReciboPago;


public class FrmLeerCodigoBarras extends javax.swing.JDialog {
    String id;
    Turno turno;
    Frame parent;
    String accion;
    Estacionamiento estacionamiento;
    /**
     * Creates new form FrmLeerCodigoBarras
     */
    public FrmLeerCodigoBarras(java.awt.Frame parent, boolean modal,Turno turno,String accion, Estacionamiento estacionamiento) {
        super(parent,"Codigo de barras", modal);
        this.parent = parent;
        initComponents();
        this.turno = turno;
        this.accion = accion;
        this.estacionamiento = estacionamiento;
        this.getContentPane().setBackground(Color.white);
        pack();
        id ="";
        if(accion.equals("COBRO")){
            lblMensaje.setText("Coloca el boleto frente al sensor.");
        }else if(accion.equals("CANCELAR")){
                lblMensaje.setText("Coloca el boleto frente al sensor.");
        }else if(accion.equals("RECIBO")){
            lblMensaje.setText("Coloca el boleto frente al sensor.");
        }else if(accion.equals("PERDIDO")){
            lblMensaje.setText("Coloca la contra frente al sensor.");
        }else if(accion.equals("CONTRA")){
            lblMensaje.setText("Coloca la contra frente al sensor.");
        }else if(accion.equals("ENTRADA")){
        }
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMensaje = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        lblMensaje.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMensaje.setText("Coloca el codigo del boleto en el sensor");
        lblMensaje.setName("lblMensaje"); // NOI18N

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/resources/codigoBarras.jpg"))); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMensaje)
                .addGap(28, 28, 28)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

        
    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        Auto auto=null;
        if (evt.getKeyChar() == '\n') { 
            if (id.length() ==12 ){
                if(accion.equals("COBRO")){
                    auto = Auto.getByProgresivoClave(id);
                    if (auto != null){
                        if(auto.isDentro())
                            new FrmCobro(parent, true,turno,auto,estacionamiento);
                        this.dispose();
                    }else{
                        id = "";
                    }
                }else if(accion.equals("CANCELAR")){
                    auto = Auto.getByProgresivoClave(id);
                    if (auto != null){
                        if(auto.getBoletoCancelado()==null)
                            new FrmBoletoCancelado((JFrame) parent, true,turno,auto,estacionamiento);
                        else
                            new FrmCobro(parent,true, turno, auto,estacionamiento);
                        this.dispose();
                    }else{
                        id = "";
                    }   
                }else if(accion.equals("RECIBO")){
                    auto = Auto.getByProgresivoClave(id);
                    if (auto != null){
                        if(!auto.isReciboImpreso()){
                            auto.setReciboImpreso(true);
                            auto.actualizar();
                            new FrmReciboPago(this,false,PrinterJob.getPrinterJob(),turno,auto,estacionamiento);
                        }
                        this.dispose();
                    }else{
                        id = "";
                    }
                }else if(accion.equals("PERDIDO")){
                    auto = Auto.getByProgresivoClaveContra(id);
                    if (auto != null){
                        new FrmBoletoPerdido(parent,true,turno,auto,estacionamiento);
                        this.dispose();
                    }else{
                        id = "";
                    }
                }else if(accion.equals("CONTRA")){
                    auto = Auto.getByProgresivoClaveContra(id);
                    if (auto != null){
                        auto.setIsBoletoContra(true);
                        new FrmCobro(parent,true,turno,auto,estacionamiento);
                        this.dispose();
                    }else{
                        id = "";
                    }
                }else if(accion.equals("ENTRADA")){
                
                }
               
            }else{
                id = "";
            }
        }else
            id+=evt.getKeyChar();  
    }//GEN-LAST:event_formKeyTyped

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblMensaje;
    // End of variables declaration//GEN-END:variables
}
