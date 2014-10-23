package vistas.formatos;

import ModelosAux.Tiempo;
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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelos.Auto;
import modelos.BoletoCancelado;
import modelos.Empleado;
import modelos.Estacionamiento;
import modelos.Operacion;
import modelos.Turno;
import vistas.FrmCobro;


public class FrmBoletoCancelado extends JDialog implements Printable {
    private Estacionamiento estacionamiento;
    private Turno turno;
    private Auto auto;
    private BoletoCancelado boletoCancelado;
    private PrinterJob job;
    private Empleado empleado;
    private JFrame parent;

    public FrmBoletoCancelado(javax.swing.JFrame padre, boolean b, Turno turno,Auto auto,Estacionamiento estacionamiento) {
        super(padre,b);
        this.turno = turno;
        this.auto = auto;
        this.parent = padre;
        this.estacionamiento = estacionamiento;
        initComponents();
        this.setLocationRelativeTo(padre);
        iniciarOtrosComponentes();
        setVisible(true);
    }

   
    private void iniciarOtrosComponentes() {
        txtCajero.setText(turno.getEmpleado().getNombre());
        txtCaseta.setText(estacionamiento.getCaseta().getDescripcion());
        txtCentroOperativo.setText(estacionamiento.getDescripcion());
        txtFecha.setText(Tiempo.getHora()+"  "+Tiempo.getFecha());
        txtPlacas.setText(auto.getMatricula());
        txtProgresivo.setText(String.valueOf(auto.getProgresivo()));
        txtTurno.setText(turno.getTipoTurno());
    }
    
      private boolean validaCamposEntrada() {
       
        if (txtRazon.getText().equals("")){
            JOptionPane.showMessageDialog(this,"La razon de la cancelacion de boleto es obligatoria.",
            "Campo faltante",JOptionPane.WARNING_MESSAGE);
            txtRazon.grabFocus();
            return false;
        }
        return true;

    }
    private void imprimir(){
        job = PrinterJob.getPrinterJob();
        PageFormat pf  = new PageFormat();
        Paper paper = pf.getPaper();
        double width = 20d * 72d;
        double height = 20d * 72d;
        double margin = .1d * 72d;
        paper.setSize(width, height);
        paper.setImageableArea(margin,0,
                width - (margin * 2),height);
        pf.setPaper(paper);
       
        job.setPrintable(this,pf);

        try {
            job.print();
        } catch (PrinterException ex) {
            ex.printStackTrace();
        }  
        this.setVisible(false);
        this.dispose();
    }
    
    @Override
    public int print(Graphics g, PageFormat pageFormat, int page) throws PrinterException {
        if (page > 0) { 
            return NO_SUCH_PAGE;
        }
        
        Graphics2D g2d = (Graphics2D)g;
        g2d.transform(AffineTransform.getScaleInstance(.48,.48));
        g2d.transform(AffineTransform.getRotateInstance( Math.toRadians(-90),(this).getWidth()/1.5,(this).getHeight()/1.5 ));
        g2d.transform(AffineTransform.getTranslateInstance(0,-220));
        
        this.printAll(g2d);
        return PAGE_EXISTS;
    
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        txtPlacas = new javax.swing.JTextField();
        txtProgresivo = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtCentroOperativo = new javax.swing.JTextField();
        lblProgresivoPerdido = new javax.swing.JLabel();
        txtCaseta = new javax.swing.JTextField();
        txtTurno = new javax.swing.JTextField();
        txtCajero = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtRazon = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(FrmBoletoCancelado.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel1.setForeground(resourceMap.getColor("jLabel10.foreground")); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("btnGuardar.font")); // NOI18N
        jLabel1.setForeground(resourceMap.getColor("jLabel10.foreground")); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("btnGuardar.font")); // NOI18N
        jLabel2.setForeground(resourceMap.getColor("jLabel10.foreground")); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel7.setFont(resourceMap.getFont("btnGuardar.font")); // NOI18N
        jLabel7.setForeground(resourceMap.getColor("jLabel10.foreground")); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel8.setFont(resourceMap.getFont("btnGuardar.font")); // NOI18N
        jLabel8.setForeground(resourceMap.getColor("jLabel10.foreground")); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel9.setFont(resourceMap.getFont("btnGuardar.font")); // NOI18N
        jLabel9.setForeground(resourceMap.getColor("jLabel10.foreground")); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel10.setFont(resourceMap.getFont("btnGuardar.font")); // NOI18N
        jLabel10.setForeground(resourceMap.getColor("jLabel10.foreground")); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel10.setName("jLabel10"); // NOI18N

        txtFecha.setEditable(false);
        txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFecha.setName("txtFecha"); // NOI18N

        txtPlacas.setEditable(false);
        txtPlacas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlacas.setName("txtPlacas"); // NOI18N

        txtProgresivo.setEditable(false);
        txtProgresivo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProgresivo.setName("txtProgresivo"); // NOI18N
        txtProgresivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProgresivoActionPerformed(evt);
            }
        });

        jLabel15.setFont(resourceMap.getFont("btnGuardar.font")); // NOI18N
        jLabel15.setForeground(resourceMap.getColor("jLabel10.foreground")); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        txtCentroOperativo.setEditable(false);
        txtCentroOperativo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCentroOperativo.setName("txtCentroOperativo"); // NOI18N

        lblProgresivoPerdido.setFont(resourceMap.getFont("lblProgresivoPerdido.font")); // NOI18N
        lblProgresivoPerdido.setText(resourceMap.getString("lblProgresivoPerdido.text")); // NOI18N
        lblProgresivoPerdido.setName("lblProgresivoPerdido"); // NOI18N

        txtCaseta.setEditable(false);
        txtCaseta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCaseta.setName("txtCaseta"); // NOI18N

        txtTurno.setEditable(false);
        txtTurno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTurno.setName("txtTurno"); // NOI18N

        txtCajero.setEditable(false);
        txtCajero.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCajero.setName("txtCajero"); // NOI18N

        jLabel19.setFont(resourceMap.getFont("jLabel19.font")); // NOI18N
        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N

        btnGuardar.setFont(resourceMap.getFont("btnGuardar.font")); // NOI18N
        btnGuardar.setMnemonic('g');
        btnGuardar.setText(resourceMap.getString("btnGuardar.text")); // NOI18N
        btnGuardar.setName("btnGuardar"); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N

        jLabel22.setFont(resourceMap.getFont("btnGuardar.font")); // NOI18N
        jLabel22.setForeground(resourceMap.getColor("jLabel10.foreground")); // NOI18N
        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N

        jLabel23.setIcon(resourceMap.getIcon("jLabel23.icon")); // NOI18N
        jLabel23.setText(resourceMap.getString("jLabel23.text")); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N

        jLabel25.setFont(resourceMap.getFont("btnGuardar.font")); // NOI18N
        jLabel25.setForeground(resourceMap.getColor("jLabel10.foreground")); // NOI18N
        jLabel25.setText(resourceMap.getString("jLabel25.text")); // NOI18N
        jLabel25.setName("jLabel25"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        txtRazon.setColumns(20);
        txtRazon.setRows(5);
        txtRazon.setName("txtRazon"); // NOI18N
        jScrollPane1.setViewportView(txtRazon);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jLabel20)
                        .add(239, 239, 239))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 154, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jScrollPane1))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(jLabel15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 145, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 154, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 154, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                            .add(txtCentroOperativo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                                            .add(txtProgresivo))
                                        .add(18, 18, 18)
                                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(jPanel1Layout.createSequentialGroup()
                                                .add(jLabel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 59, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(txtFecha))
                                            .add(lblProgresivoPerdido, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(txtCaseta)
                                            .add(txtCajero))
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(jPanel1Layout.createSequentialGroup()
                                                .add(jLabel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 58, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .add(264, 264, 264))
                                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .add(jLabel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 58, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                                .add(txtPlacas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 252, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(0, 0, Short.MAX_VALUE)
                                        .add(txtTurno, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 252, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(244, 244, 244)
                        .add(jLabel22)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(btnGuardar)
                        .add(47, 47, 47)))
                .addContainerGap())
            .add(jPanel1Layout.createSequentialGroup()
                .add(jLabel23, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(45, 45, 45)
                .add(jLabel19)
                .add(58, 58, 58))
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel25)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(19, 19, 19)
                        .add(jLabel19)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(lblProgresivoPerdido, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(txtProgresivo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel15))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(txtFecha, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel8)
                    .add(txtCentroOperativo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(txtTurno, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel2)
                        .add(txtCaseta, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel9)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(txtPlacas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel10)
                        .add(txtCajero, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel7)))
                .add(18, 18, 18)
                .add(jLabel25)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 122, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jLabel20, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel22)
                    .add(btnGuardar))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        this.dispose();
    }//GEN-LAST:event_formWindowClosed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        if (validaCamposEntrada()){
            if(new Operacion(this.parent).requierePermisos(turno.getEmpleado(), "Supervisor",true)){
                btnGuardar.setVisible(false);
                boletoCancelado = new BoletoCancelado(0, txtRazon.getText(), turno);
                boletoCancelado.setAuto(auto);
                boletoCancelado.guardar();
                auto.setBoletoCancelado(boletoCancelado);
                auto.setIsBoletoCancelado(true);
                auto.actualizar();
                new FrmCobro(parent,true, turno, auto,estacionamiento);
                imprimir();
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtProgresivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProgresivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProgresivoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblProgresivoPerdido;
    private javax.swing.JTextField txtCajero;
    private javax.swing.JTextField txtCaseta;
    private javax.swing.JTextField txtCentroOperativo;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtPlacas;
    private javax.swing.JTextField txtProgresivo;
    private javax.swing.JTextArea txtRazon;
    private javax.swing.JTextField txtTurno;
    // End of variables declaration//GEN-END:variables

  
}
