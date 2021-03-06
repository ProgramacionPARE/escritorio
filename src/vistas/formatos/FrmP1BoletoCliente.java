

package vistas.formatos;

import java.awt.Dialog;
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
import javax.swing.JDialog;
import modelos.Auto;
import modelos.Empleado;
import modelos.Estacionamiento;
import modelos.Main;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;



    /**
 *
 * @author Roman
 */
public class FrmP1BoletoCliente extends JDialog implements Printable  {
    private Estacionamiento estacionamiento;
    private PrinterJob job;
    private Barcode barcode=null;
    /** Creates new form VenBoletoValetParking1 */
    public FrmP1BoletoCliente(Dialog parent, boolean modal, PrinterJob job,Auto auto,Empleado empleado) throws PrinterException, BarcodeException {
        super(parent, modal);
        initComponents();
        //barcode = BarcodeFactory.createUPCA(progresivo);
        barcode = BarcodeFactory.createCode128B(auto.getClave()+auto.getSerie()+auto.getProgresivo());
        barcode.setBarHeight(90);
        barcode.setBarWidth(3);
        barcode.setDrawingText(false);
        jPanel3.add(barcode);
        this.estacionamiento = Main.getInstance().getEstacionamiento();
        this.setLocationRelativeTo(parent);
        
        this.job = PrinterJob.getPrinterJob();
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        int selectedService = 0;
        for(int i = 0; i < services.length;i++){
            if(services[i].getName().toUpperCase().contains("STAR")){
                selectedService = i;
                }
            }

        job.setPrintService(services[selectedService]);

        PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
        MediaSizeName mediaSizeName = MediaSize.findMedia(4,4,MediaPrintableArea.INCH);
        printRequestAttributeSet.add(mediaSizeName);
        printRequestAttributeSet.add(new Copies(1));
        
        lblLugar.setText(estacionamiento.getDescripcion());
       
        lblPlacas.setText(auto.getMatricula());
        lblProgresivo.setText("No. " + auto.getProgresivo() );
        lblFecha.setText(auto.getFechaEntrada()+" "+auto.getHoraEntrada());
        lblAcomodador.setText(empleado.getNombre().toUpperCase());
        lblModelo.setText(auto.getModelo().toUpperCase());
        lblNotas.setText(auto.getNota());
        setVisible(true);
        imprimir();
    }
 
    
     public FrmP1BoletoCliente(Dialog parent, boolean modal, PrinterJob job,Auto auto,Empleado empleado,String nombreEstacionemiento) throws PrinterException, BarcodeException {
        super(parent, modal);
        initComponents();
        //barcode = BarcodeFactory.createUPCA(progresivo);
        barcode = BarcodeFactory.createCode128B(auto.getClave()+auto.getSerie()+auto.getProgresivo());
        barcode.setBarHeight(90);
        barcode.setBarWidth(3);
        barcode.setDrawingText(false);
        jPanel3.add(barcode);
        this.setLocationRelativeTo(parent);
        
        this.job = PrinterJob.getPrinterJob();
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        int selectedService = 0;
        for(int i = 0; i < services.length;i++){
            if(services[i].getName().toUpperCase().contains("STAR")){
                selectedService = i;
                }
            }

        job.setPrintService(services[selectedService]);

        PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
        MediaSizeName mediaSizeName = MediaSize.findMedia(4,4,MediaPrintableArea.INCH);
        printRequestAttributeSet.add(mediaSizeName);
        printRequestAttributeSet.add(new Copies(1));
        
        lblLugar.setText(nombreEstacionemiento);
       
        lblPlacas.setText(auto.getMatricula());
        lblProgresivo.setText("No. " + auto.getProgresivo() );
        lblFecha.setText(auto.getFechaEntrada()+" "+auto.getHoraEntrada());
        lblAcomodador.setText(empleado.getNombre().toUpperCase());
        lblModelo.setText(auto.getModelo().toUpperCase());
        lblNotas.setText(auto.getNota());
        setVisible(true);
        imprimir();
    }

/** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabPlacas = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblAcomodador = new javax.swing.JLabel();
        lblLugar = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabPlacas1 = new javax.swing.JLabel();
        lblPlacas = new javax.swing.JLabel();
        jLabPlacas4 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblProgresivo = new javax.swing.JLabel();
        jLabPlacas2 = new javax.swing.JLabel();
        lblModelo = new javax.swing.JLabel();
        lblNotas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N
        setUndecorated(true);
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(FrmP1BoletoCliente.class);
        jPanel2.setBackground(resourceMap.getColor("jPanel2.background")); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel1.setIcon(resourceMap.getIcon("jLabel1.icon")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel23.setText(resourceMap.getString("jLabel23.text")); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N

        jLabel18.setFont(resourceMap.getFont("jLabel18.font")); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N

        jLabPlacas.setFont(resourceMap.getFont("jLabPlacas2.font")); // NOI18N
        jLabPlacas.setText(resourceMap.getString("jLabPlacas.text")); // NOI18N
        jLabPlacas.setName("jLabPlacas"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        lblAcomodador.setFont(resourceMap.getFont("jLabPlacas2.font")); // NOI18N
        lblAcomodador.setText(resourceMap.getString("lblAcomodador.text")); // NOI18N
        lblAcomodador.setName("lblAcomodador"); // NOI18N

        lblLugar.setFont(resourceMap.getFont("lblLugar.font")); // NOI18N
        lblLugar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLugar.setText(resourceMap.getString("lblLugar.text")); // NOI18N
        lblLugar.setName("lblLugar"); // NOI18N

        jPanel3.setBackground(resourceMap.getColor("jPanel3.background")); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/resources/auto4vistas.jpg"))); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel8.setIconTextGap(0);
        jLabel8.setName("jLabel8"); // NOI18N

        jLabPlacas1.setFont(resourceMap.getFont("jLabPlacas2.font")); // NOI18N
        jLabPlacas1.setText(resourceMap.getString("jLabPlacas1.text")); // NOI18N
        jLabPlacas1.setName("jLabPlacas1"); // NOI18N

        lblPlacas.setFont(resourceMap.getFont("jLabPlacas2.font")); // NOI18N
        lblPlacas.setText(resourceMap.getString("lblPlacas.text")); // NOI18N
        lblPlacas.setName("lblPlacas"); // NOI18N

        jLabPlacas4.setFont(resourceMap.getFont("jLabPlacas2.font")); // NOI18N
        jLabPlacas4.setText(resourceMap.getString("jLabPlacas4.text")); // NOI18N
        jLabPlacas4.setName("jLabPlacas4"); // NOI18N

        lblFecha.setFont(resourceMap.getFont("jLabPlacas2.font")); // NOI18N
        lblFecha.setText(resourceMap.getString("lblFecha.text")); // NOI18N
        lblFecha.setName("lblFecha"); // NOI18N

        lblProgresivo.setFont(new java.awt.Font("Droid Sans", 0, 28)); // NOI18N
        lblProgresivo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblProgresivo.setText(resourceMap.getString("lblProgresivo.text")); // NOI18N
        lblProgresivo.setName("lblProgresivo"); // NOI18N

        jLabPlacas2.setFont(resourceMap.getFont("jLabPlacas2.font")); // NOI18N
        jLabPlacas2.setText(resourceMap.getString("jLabPlacas2.text")); // NOI18N
        jLabPlacas2.setName("jLabPlacas2"); // NOI18N

        lblModelo.setFont(resourceMap.getFont("jLabPlacas2.font")); // NOI18N
        lblModelo.setText(resourceMap.getString("lblModelo.text")); // NOI18N
        lblModelo.setName("lblModelo"); // NOI18N

        lblNotas.setText(resourceMap.getString("lblNotas.text")); // NOI18N
        lblNotas.setName("lblNotas"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblLugar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProgresivo, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabPlacas4, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(jLabPlacas1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabPlacas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabPlacas2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(lblPlacas, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblNotas, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(lblModelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(97, 97, 97))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(lblAcomodador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(42, 42, 42)))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLugar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblProgresivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabPlacas4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabPlacas)
                    .addComponent(lblPlacas, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNotas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabPlacas2)
                    .addComponent(lblModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabPlacas1)
                    .addComponent(lblAcomodador, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        //new ImprimirForma(this,job);
        //this.dispose();
    }//GEN-LAST:event_formComponentShown
    
    public void imprimir() throws PrinterException{
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
        job.print();
        this.setVisible(false);
        this.dispose();

    }

    @Override
    public int print(Graphics g, PageFormat pageFormat, int page) throws PrinterException {
        if (page > 0) { 
            return NO_SUCH_PAGE;
        }
        
        Graphics2D g2d = (Graphics2D)g;
        g2d.transform(AffineTransform.getScaleInstance(.45,.45));
        g2d.transform(AffineTransform.getRotateInstance( Math.toRadians(-90),(this).getWidth()/1.7,(this).getHeight()/1.7 ));
        g2d.transform(AffineTransform.getTranslateInstance(0,-180));
        
        this.printAll(g2d);
        return PAGE_EXISTS;
    
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabPlacas;
    private javax.swing.JLabel jLabPlacas1;
    private javax.swing.JLabel jLabPlacas2;
    private javax.swing.JLabel jLabPlacas4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblAcomodador;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblLugar;
    private javax.swing.JLabel lblModelo;
    private javax.swing.JLabel lblNotas;
    private javax.swing.JLabel lblPlacas;
    private javax.swing.JLabel lblProgresivo;
    // End of variables declaration//GEN-END:variables

    
}
