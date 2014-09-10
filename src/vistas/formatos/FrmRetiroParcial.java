/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vistas.formatos;

import ModelosAux.Sistema;
import ModelosAux.Tiempo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.swing.JOptionPane;
import modelos.Caja;
import modelos.Estacionamiento;
import modelos.Progresivo;
import modelos.RetiroParcial;
import modelos.Turno;
import proyectopare.ProyectoPareApp;
import proyectopare.clases.PARAMETROS;

/**
 *
 * @author sistema
 */
public class FrmRetiroParcial extends javax.swing.JDialog implements Printable{
    private Estacionamiento estacionamiento;
    private PrinterJob job;
    private Turno turno;

    
    public FrmRetiroParcial(java.awt.Frame parent, boolean modal,Turno turno,Estacionamiento estacionamiento) {
        super(parent, modal);
        initComponents();
        this.turno = turno;
        this.estacionamiento = estacionamiento;
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(parent);
        this.job = PrinterJob.getPrinterJob();
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        int selectedService = 0;
        for(int i = 0; i < services.length;i++){
            if(services[i].getName().toUpperCase().contains("STAR TSP100 CUTTER")){
                selectedService = i;
                }
            }
        try {
            job.setPrintService(services[selectedService]);
        } catch (PrinterException ex) {
            Logger.getLogger(FrmP1BoletoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
        MediaSizeName mediaSizeName = MediaSize.findMedia(4,4,MediaPrintableArea.INCH);
        printRequestAttributeSet.add(mediaSizeName);
        printRequestAttributeSet.add(new Copies(1));
        
        this.txtCajero.setText(turno.getEmpleado().getNombre());
        this.txtCaseta.setText(estacionamiento.getCaseta().getDescripcion());
        this.txtCentroOperativo.setText(estacionamiento.getDescripcion());
        this.txtFecha.setText(Tiempo.getFecha());
        this.txtHora.setText(Tiempo.getHora());
        this.txtTipoTurno.setText(turno.getTipoTurno());
        this.txtProgresivo.setText("NO. "+ Progresivo.getUltimoProgresivo(estacionamiento.getCaseta(),"RETIRO_PARCIAL"));
         lblCopia.setVisible(false);
        setVisible(true);

    }
    
     private boolean validaCamposEntrada() {
        if (txtMonto.getText().equals("")){
            JOptionPane.showMessageDialog(this,"El monto a retirar es obligatorio.",
            "Campo faltante",JOptionPane.WARNING_MESSAGE);
            txtMonto.grabFocus();
            return false;
        }
        return true;

    }
      
    public void imprimir(boolean print){
        PageFormat pf  = new PageFormat();
        Paper paper = pf.getPaper();
        double width = 9d * 72d;
        double height = 16d * 72d;
        double margin = .1d * 72d;
        paper.setSize(width, height);
        paper.setImageableArea(margin,0,
                width - (margin * 2),height);
        pf.setPaper(paper);
        job.setPrintable(this,pf );
        try {
            job.print();
        } catch (PrinterException ex) {
            ex.printStackTrace();
        }finally{
            if(print){
                this.setVisible(false);
                this.dispose();
            }
        }
    }

    @Override
    public int print(Graphics g, PageFormat pageFormat, int page) throws PrinterException {
        if (page > 0) { 
            return NO_SUCH_PAGE;
        }
        
        Graphics2D g2d = (Graphics2D)g;
        g2d.transform(AffineTransform.getScaleInstance(.45,.45));
        g2d.transform(AffineTransform.getRotateInstance( Math.toRadians(-90),(this).getWidth()/1.7,(this).getHeight()/1.7 ));
        g2d.transform(AffineTransform.getTranslateInstance(0,-140));
        
        this.printAll(g2d);
        return PAGE_EXISTS;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblNoSerie = new javax.swing.JLabel();
        lblNoSerie1 = new javax.swing.JLabel();
        lblNoSerie2 = new javax.swing.JLabel();
        txtCentroOperativo = new javax.swing.JTextField();
        lblNoSerie3 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        lblNoSerie4 = new javax.swing.JLabel();
        txtHora = new javax.swing.JTextField();
        lblNoSerie5 = new javax.swing.JLabel();
        txtCajero = new javax.swing.JTextField();
        lblNoSerie6 = new javax.swing.JLabel();
        txtSupervisor = new javax.swing.JTextField();
        lblNoSerie7 = new javax.swing.JLabel();
        txtCaseta = new javax.swing.JTextField();
        lblNoSerie9 = new javax.swing.JLabel();
        txtTipoTurno = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblNoSerie11 = new javax.swing.JLabel();
        lblNoSerie8 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        btnRetiro = new javax.swing.JButton();
        txtProgresivo = new javax.swing.JTextField();
        lblCopia = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectopare/pantallas/resources/pare valet parking 1.jpg"))); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        lblNoSerie.setFont(new java.awt.Font("Tahoma 18", 1, 24)); // NOI18N
        lblNoSerie.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoSerie.setText("Retiro parcial");
        lblNoSerie.setName("lblNoSerie"); // NOI18N

        lblNoSerie1.setFont(new java.awt.Font("Tahoma 18", 1, 20)); // NOI18N
        lblNoSerie1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNoSerie1.setName("lblNoSerie1"); // NOI18N

        lblNoSerie2.setFont(new java.awt.Font("Tahoma 18", 1, 20)); // NOI18N
        lblNoSerie2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoSerie2.setText("Estacionamiento");
        lblNoSerie2.setName("lblNoSerie2"); // NOI18N

        txtCentroOperativo.setEditable(false);
        txtCentroOperativo.setBackground(new java.awt.Color(255, 255, 255));
        txtCentroOperativo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtCentroOperativo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCentroOperativo.setName("txtCentroOperativo"); // NOI18N

        lblNoSerie3.setFont(new java.awt.Font("Tahoma 18", 1, 14)); // NOI18N
        lblNoSerie3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoSerie3.setText("Fecha");
        lblNoSerie3.setName("lblNoSerie3"); // NOI18N

        txtFecha.setEditable(false);
        txtFecha.setBackground(new java.awt.Color(255, 255, 255));
        txtFecha.setFont(new java.awt.Font("Tahoma 18", 1, 14)); // NOI18N
        txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFecha.setName("txtFecha"); // NOI18N

        lblNoSerie4.setFont(new java.awt.Font("Tahoma 18", 1, 14)); // NOI18N
        lblNoSerie4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoSerie4.setText("Hora");
        lblNoSerie4.setName("lblNoSerie4"); // NOI18N

        txtHora.setEditable(false);
        txtHora.setBackground(new java.awt.Color(255, 255, 255));
        txtHora.setFont(new java.awt.Font("Tahoma 18", 1, 14)); // NOI18N
        txtHora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHora.setName("txtHora"); // NOI18N

        lblNoSerie5.setFont(new java.awt.Font("Tahoma 18", 1, 14)); // NOI18N
        lblNoSerie5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoSerie5.setText("Cajero");
        lblNoSerie5.setName("lblNoSerie5"); // NOI18N

        txtCajero.setEditable(false);
        txtCajero.setBackground(new java.awt.Color(255, 255, 255));
        txtCajero.setFont(new java.awt.Font("Tahoma 18", 1, 14)); // NOI18N
        txtCajero.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCajero.setName("txtCajero"); // NOI18N

        lblNoSerie6.setFont(new java.awt.Font("Tahoma 18", 1, 14)); // NOI18N
        lblNoSerie6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoSerie6.setText("Supervisor");
        lblNoSerie6.setName("lblNoSerie6"); // NOI18N

        txtSupervisor.setEditable(false);
        txtSupervisor.setBackground(new java.awt.Color(255, 255, 255));
        txtSupervisor.setFont(new java.awt.Font("Tahoma 18", 1, 14)); // NOI18N
        txtSupervisor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSupervisor.setName("txtSupervisor"); // NOI18N

        lblNoSerie7.setFont(new java.awt.Font("Tahoma 18", 1, 14)); // NOI18N
        lblNoSerie7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoSerie7.setName("lblNoSerie7"); // NOI18N

        txtCaseta.setEditable(false);
        txtCaseta.setBackground(new java.awt.Color(255, 255, 255));
        txtCaseta.setFont(new java.awt.Font("Tahoma 18", 1, 14)); // NOI18N
        txtCaseta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCaseta.setName("txtCaseta"); // NOI18N

        lblNoSerie9.setFont(new java.awt.Font("Tahoma 18", 1, 14)); // NOI18N
        lblNoSerie9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoSerie9.setText("Turno");
        lblNoSerie9.setName("lblNoSerie9"); // NOI18N

        txtTipoTurno.setEditable(false);
        txtTipoTurno.setBackground(new java.awt.Color(255, 255, 255));
        txtTipoTurno.setFont(new java.awt.Font("Tahoma 18", 1, 14)); // NOI18N
        txtTipoTurno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTipoTurno.setName("txtTipoTurno"); // NOI18N

        jLabel16.setText("_________________________________");
        jLabel16.setName("jLabel16"); // NOI18N

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel21.setText("Cajero");
        jLabel21.setName("jLabel21"); // NOI18N

        jLabel20.setText("___________________________________");
        jLabel20.setName("jLabel20"); // NOI18N

        jLabel22.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel22.setText("Supervisor");
        jLabel22.setName("jLabel22"); // NOI18N

        lblNoSerie11.setFont(new java.awt.Font("Tahoma 18", 1, 14)); // NOI18N
        lblNoSerie11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoSerie11.setText("Caseta");
        lblNoSerie11.setName("lblNoSerie11"); // NOI18N

        lblNoSerie8.setFont(new java.awt.Font("Tahoma 18", 1, 20)); // NOI18N
        lblNoSerie8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoSerie8.setText("Monto retirado");
        lblNoSerie8.setName("lblNoSerie8"); // NOI18N

        txtMonto.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        txtMonto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMonto.setName("txtMonto"); // NOI18N

        btnRetiro.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnRetiro.setText("Realizar retiro");
        btnRetiro.setName("btnRetiro"); // NOI18N
        btnRetiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetiroActionPerformed(evt);
            }
        });

        txtProgresivo.setEditable(false);
        txtProgresivo.setBackground(new java.awt.Color(255, 255, 255));
        txtProgresivo.setFont(new java.awt.Font("Tahoma 18", 1, 14)); // NOI18N
        txtProgresivo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProgresivo.setName("txtProgresivo"); // NOI18N

        lblCopia.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        lblCopia.setText("Copia");
        lblCopia.setName("lblCopia"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(lblNoSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(lblNoSerie1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 10, Short.MAX_VALUE))
                                    .addComponent(txtProgresivo)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNoSerie7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNoSerie3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNoSerie4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHora))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblNoSerie5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCajero, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNoSerie6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSupervisor))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNoSerie11, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCaseta, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNoSerie9, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTipoTurno))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addComponent(lblNoSerie8, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMonto))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNoSerie2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCentroOperativo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jLabel21)
                                .addGap(137, 137, 137)
                                .addComponent(lblCopia))
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(66, 66, 66))
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRetiro, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(txtProgresivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNoSerie1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblNoSerie)))
                .addGap(28, 28, 28)
                .addComponent(lblNoSerie7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoSerie2)
                    .addComponent(txtCentroOperativo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoSerie3)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNoSerie4)
                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNoSerie5)
                    .addComponent(txtCajero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNoSerie6)
                    .addComponent(txtSupervisor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCaseta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNoSerie9)
                    .addComponent(txtTipoTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNoSerie11))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMonto)
                    .addComponent(lblNoSerie8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel20))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22))
                        .addGap(18, 18, 18)
                        .addComponent(btnRetiro))
                    .addComponent(lblCopia))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRetiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetiroActionPerformed
       if(validaCamposEntrada()){
            Caja caja = Caja.getByCaseta(estacionamiento.getCaseta().getId());
            RetiroParcial retiro = new RetiroParcial(Long.valueOf( Progresivo.getUltimoProgresivo(
               estacionamiento.getCaseta(),"RETIRO_PARCIAL")),
                this.txtFecha.getText(),this.txtHora.getText(),Float.valueOf(this.txtMonto.getText()),
                caja.getMonto(),turno.getId(),estacionamiento.getCaseta().getId());
            btnRetiro.setVisible(false);
            
            Progresivo.setProgresivoMasUno(estacionamiento.getCaseta(),"RETIRO_PARCIAL");
            retiro.guardar();
            turno.getRetirosParciales().add(retiro);
            turno.actualizar();
            //LLevo el monto de la caja a 0
           
            caja.setMonto(caja.getMonto()-Float.valueOf(txtMonto.getText()));
            caja.actualizar();
            //Reviso si activo la alarma
            ProyectoPareApp.getApplication().getView().setCajaAlarma(
                Sistema.requiereRetitroParcial(caja) );
            imprimir(false);
            lblCopia.setVisible(true);
            imprimir(true);
       }
    }//GEN-LAST:event_btnRetiroActionPerformed

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRetiro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel lblCopia;
    private javax.swing.JLabel lblNoSerie;
    private javax.swing.JLabel lblNoSerie1;
    private javax.swing.JLabel lblNoSerie11;
    private javax.swing.JLabel lblNoSerie2;
    private javax.swing.JLabel lblNoSerie3;
    private javax.swing.JLabel lblNoSerie4;
    private javax.swing.JLabel lblNoSerie5;
    private javax.swing.JLabel lblNoSerie6;
    private javax.swing.JLabel lblNoSerie7;
    private javax.swing.JLabel lblNoSerie8;
    private javax.swing.JLabel lblNoSerie9;
    private javax.swing.JTextField txtCajero;
    private javax.swing.JTextField txtCaseta;
    private javax.swing.JTextField txtCentroOperativo;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtProgresivo;
    private javax.swing.JTextField txtSupervisor;
    private javax.swing.JTextField txtTipoTurno;
    // End of variables declaration//GEN-END:variables
}
