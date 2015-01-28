package vistas.formatos;

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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelos.Auto;
import modelos.BoletoCancelado;
import modelos.Empleado;
import modelos.Estacionamiento;
import modelos.Main;
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

    public FrmBoletoCancelado(javax.swing.JFrame padre, boolean b, Auto auto) {
        super(padre,b);
        this.getContentPane().setBackground(Color.white);
        this.estacionamiento =  Main.getInstance().getEstacionamiento();
        this.turno = Main.getInstance().getTurnoActual();
        this.auto = auto;
        this.parent = padre;
        initComponents();
        this.setLocationRelativeTo(padre);
        iniciarOtrosComponentes();
        setVisible(true);
    }

   
    private void iniciarOtrosComponentes() {
        txtCajero.setText(turno.getEmpleadoEntrada().getNombre());
      
        txtFecha.setText(Tiempo.getFecha()+" "+Tiempo.getHora());
        
        txtProgresivo.setText(String.valueOf(auto.getProgresivo()));
        txtTurno.setText(turno.getTipoTurno());
    }
    
      private boolean validaCamposEntrada() {
       
      
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
        g2d.transform(AffineTransform.getScaleInstance(.47,.47));
        g2d.transform(AffineTransform.getRotateInstance( Math.toRadians(-90),(this).getWidth()/2,(this).getHeight()/2 ));
        g2d.transform(AffineTransform.getTranslateInstance(100,60));
        
        this.printAll(g2d);
        return PAGE_EXISTS;
    
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        txtProgresivo = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        lblProgresivoPerdido = new javax.swing.JLabel();
        txtTurno = new javax.swing.JTextField();
        txtCajero = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        cbxRazon = new javax.swing.JComboBox();

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
        jPanel1.setBorder(null);
        jPanel1.setForeground(resourceMap.getColor("jLabel10.foreground")); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel8.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        jLabel8.setForeground(resourceMap.getColor("jLabel10.foreground")); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel9.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        jLabel9.setForeground(resourceMap.getColor("jLabel10.foreground")); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel10.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        jLabel10.setForeground(resourceMap.getColor("jLabel10.foreground")); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel10.setName("jLabel10"); // NOI18N

        txtFecha.setEditable(false);
        txtFecha.setBackground(resourceMap.getColor("txtFecha.background")); // NOI18N
        txtFecha.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        txtFecha.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtFecha.setBorder(null);
        txtFecha.setName("txtFecha"); // NOI18N

        txtProgresivo.setEditable(false);
        txtProgresivo.setBackground(resourceMap.getColor("txtProgresivo.background")); // NOI18N
        txtProgresivo.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        txtProgresivo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtProgresivo.setBorder(null);
        txtProgresivo.setName("txtProgresivo"); // NOI18N
        txtProgresivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProgresivoActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        lblProgresivoPerdido.setFont(resourceMap.getFont("lblProgresivoPerdido.font")); // NOI18N
        lblProgresivoPerdido.setText(resourceMap.getString("lblProgresivoPerdido.text")); // NOI18N
        lblProgresivoPerdido.setName("lblProgresivoPerdido"); // NOI18N

        txtTurno.setEditable(false);
        txtTurno.setBackground(resourceMap.getColor("txtTurno.background")); // NOI18N
        txtTurno.setFont(resourceMap.getFont("txtTurno.font")); // NOI18N
        txtTurno.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtTurno.setBorder(null);
        txtTurno.setName("txtTurno"); // NOI18N

        txtCajero.setEditable(false);
        txtCajero.setBackground(resourceMap.getColor("txtCajero.background")); // NOI18N
        txtCajero.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        txtCajero.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCajero.setBorder(null);
        txtCajero.setName("txtCajero"); // NOI18N

        jLabel19.setFont(new java.awt.Font("Droid Sans", 1, 24)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N

        btnGuardar.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        btnGuardar.setMnemonic('g');
        btnGuardar.setText(resourceMap.getString("btnGuardar.text")); // NOI18N
        btnGuardar.setName("btnGuardar"); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel20.setFont(resourceMap.getFont("jLabel20.font")); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N

        jLabel22.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        jLabel22.setForeground(resourceMap.getColor("jLabel10.foreground")); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N

        jLabel23.setIcon(resourceMap.getIcon("jLabel23.icon")); // NOI18N
        jLabel23.setText(resourceMap.getString("jLabel23.text")); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N

        jLabel25.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        jLabel25.setForeground(resourceMap.getColor("jLabel10.foreground")); // NOI18N
        jLabel25.setText(resourceMap.getString("jLabel25.text")); // NOI18N
        jLabel25.setName("jLabel25"); // NOI18N

        cbxRazon.setBackground(resourceMap.getColor("cbxRazon.background")); // NOI18N
        cbxRazon.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        cbxRazon.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "El cliente no se quedo", "Boleto  incompleto", "Prueba de sistema" }));
        cbxRazon.setName("cbxRazon"); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(jLabel19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 296, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, lblProgresivoPerdido, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 296, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(0, 0, Short.MAX_VALUE)))
                .add(12, 12, 12))
            .add(jPanel1Layout.createSequentialGroup()
                .add(33, 33, 33)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(jPanel1Layout.createSequentialGroup()
                            .add(jLabel15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 93, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(txtProgresivo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 149, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(jPanel1Layout.createSequentialGroup()
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                .add(jLabel10, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, txtFecha, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 149, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, txtTurno, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 149, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, txtCajero, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 149, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel25, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                            .add(89, 89, 89)
                            .add(btnGuardar)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 82, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .add(0, 0, Short.MAX_VALUE))
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(cbxRazon, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel20, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel22, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(54, 54, 54)
                        .add(lblProgresivoPerdido, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(18, 18, 18)
                .add(jLabel19)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(txtProgresivo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel15))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(txtFecha, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel8))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel10)
                    .add(txtCajero, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(txtTurno, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel9))
                .add(8, 8, 8)
                .add(jLabel25)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cbxRazon, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(41, 41, 41)
                .add(jLabel20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel22)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(btnGuardar)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 319, Short.MAX_VALUE)
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
            if(new Operacion(this.parent).requierePermisos(turno.getEmpleadoEntrada(), "Supervisor",true)){
                btnGuardar.setVisible(false);
                boletoCancelado = new BoletoCancelado(auto.getId(), turno.getId(),(String)cbxRazon.getSelectedItem());
                boletoCancelado.guardar();
                auto.setBoletoCancelado(boletoCancelado);
                auto.setIsBoletoCancelado(true);
                auto.actualizar();
                new FrmCobro(parent,true, auto,true);
                imprimir();
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtProgresivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProgresivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProgresivoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox cbxRazon;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblProgresivoPerdido;
    private javax.swing.JTextField txtCajero;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtProgresivo;
    private javax.swing.JTextField txtTurno;
    // End of variables declaration//GEN-END:variables

  
}
