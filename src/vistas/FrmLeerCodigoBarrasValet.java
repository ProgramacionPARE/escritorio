
package vistas;

import ModelosAux.Seguridad;
import ModelosAux.Tiempo;
import java.awt.Color;
import java.awt.Frame;
import java.awt.print.PrinterJob;
import modelos.Auto;
import modelos.Empleado;
import modelos.Estacionamiento;
import modelos.Progresivo;
import modelos.Rest;
import modelos.Turno;
import vistas.formatos.FrmP1BoletoCliente;
import vistas.formatos.FrmP2BoletoLlaves;
import vistas.formatos.FrmP3BoletoParabrisas;

/**
 *
 * @author Asistente Proyectos2
 */
public class FrmLeerCodigoBarrasValet extends javax.swing.JDialog {
    String id;
    Turno turno;
    Frame parent;
    String accion;
    Estacionamiento estacionamiento;
    /**
     * Creates new form FrmLeerCodigoBarras
     */
    public FrmLeerCodigoBarrasValet(java.awt.Frame parent, boolean modal,Turno turno, Estacionamiento estacionamiento) {
        super(parent,"Codigo de barras", modal);
        this.parent = parent;
        initComponents();
        this.turno = turno;
        this.accion = accion;
        this.estacionamiento = estacionamiento;
        this.getContentPane().setBackground(Color.white);
        pack();
        id ="";
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    /*
     * 
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Coloca el codigo de tu gafete en el sensor");
        jLabel1.setName("jLabel1"); // NOI18N

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
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

        
    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped

        Empleado empleado = null;
        if (evt.getKeyChar() == '\n') { 
            if (id.length() ==12 )
                empleado = Empleado.getByIdClave(id);
                if (empleado != null){
                    Auto auto= new Auto(0,"","0",Progresivo.getUltimoProgresivo(estacionamiento.getCaseta(),"BOLETO"),
                            Seguridad.getClave(5), "",
                    estacionamiento.getCaseta().getTarifas().get(0).getId(),
                   Tiempo.getFecha() ,"",Tiempo.getHora(),"", turno.getId(), null,0,0, 
                    estacionamiento.getCaseta(),0, "","", "", null,null,0);
                    
                    //Aumento en uno los boletos generados
                    turno.setNoBol(turno.getNoBol()+1);
                    //Actualizo el folio final en el turno
                    turno.setFolioFinal (turno.getFolioFinal()+1); 
                   
                    Progresivo.setProgresivoMasUno(estacionamiento.getCaseta(),"BOLETO");
                    //Imprimo boletos
                    PrinterJob job = PrinterJob.getPrinterJob();
                    // Boleto al cliente
                    new FrmP1BoletoCliente(this, false,job,turno,auto,estacionamiento,empleado);
                    //Boleto llaves
                    new FrmP2BoletoLlaves(this, false,job,turno,auto,empleado);
                    //Boleto Parabrisas
                    new FrmP3BoletoParabrisas(this, false ,job,turno,auto);
                     turno.actualizar();
                    // Guardo entrada y actualizo progresivo
                    auto.guardar();
                    Rest.sendAuto(auto,estacionamiento);
                    id = "";   
//                    this.setVisible(false);
//                    this.dispose();
                }else
                    id = "";
        }else
            id+=evt.getKeyChar();  
    }//GEN-LAST:event_formKeyTyped

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
