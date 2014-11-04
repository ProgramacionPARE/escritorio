

package vistas.formatos;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
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
import modelos.Auto;
import modelos.Empleado;
import modelos.Turno;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;


public class FrmP2BoletoLlaves extends javax.swing.JDialog implements Printable {
    private PrinterJob job;
    private Barcode barcode=null;
    /** Creates new form VenBoletoValetParking1 */
    public FrmP2BoletoLlaves(java.awt.Dialog parent, boolean modal, PrinterJob job,
           Turno turno, Auto auto,Empleado empleado) throws PrinterException {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(parent);
        this.job = job;
        
        try {
            barcode = BarcodeFactory.createCode128B(auto.getClave().substring(0,4)+"."+auto.getSerie()+auto.getProgresivo());
        } catch (BarcodeException ex) {
            Logger.getLogger(FrmP2BoletoLlaves.class.getName()).log(Level.SEVERE, null, ex);
        }
        barcode.setBarHeight(90);
        barcode.setBarWidth(2);
        barcode.setDrawingText(false);
        jPanel2.add(barcode);

        
        lblModelo.setText(auto.getModelo().toUpperCase());
        lblPlacas.setText(auto.getMatricula().toUpperCase());
        lblEncargado.setText(empleado.getNombre());
        jLabProgresivo.setText("NO. " + auto.getProgresivo());
         this.job = PrinterJob.getPrinterJob();
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        int selectedService = 0;
        for(int i = 0; i < services.length;i++){
            if(services[i].getName().contains("STAR")){
                selectedService = i;
                }
            }
      
            job.setPrintService(services[selectedService]);

        PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
       MediaSizeName mediaSizeName = MediaSize.findMedia(4,4,MediaPrintableArea.INCH);
        printRequestAttributeSet.add(mediaSizeName);
        printRequestAttributeSet.add(new Copies(1));
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

        jPanel1 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabPlacas = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabColor = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabModelo = new javax.swing.JLabel();
        jLabProgresivo = new javax.swing.JLabel();
        lbl12 = new javax.swing.JLabel();
        lblModelo = new javax.swing.JLabel();
        lblPlacas = new javax.swing.JLabel();
        lblEncargado = new javax.swing.JLabel();
        lblColor = new javax.swing.JLabel();
        jLabPlacas1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N
        setUndecorated(true);
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(FrmP2BoletoLlaves.class);
        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel18.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel18.setName("jLabel18"); // NOI18N

        jLabPlacas.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        jLabPlacas.setText(resourceMap.getString("jLabPlacas.text")); // NOI18N
        jLabPlacas.setName("jLabPlacas"); // NOI18N

        jLabel22.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N

        jLabColor.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        jLabColor.setText(resourceMap.getString("jLabColor.text")); // NOI18N
        jLabColor.setName("jLabColor"); // NOI18N

        jLabel9.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel9.setName("jLabel9"); // NOI18N

        jLabModelo.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        jLabModelo.setText(resourceMap.getString("jLabModelo.text")); // NOI18N
        jLabModelo.setName("jLabModelo"); // NOI18N

        jLabProgresivo.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        jLabProgresivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabProgresivo.setText(resourceMap.getString("jLabProgresivo.text")); // NOI18N
        jLabProgresivo.setName("jLabProgresivo"); // NOI18N

        lbl12.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        lbl12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl12.setText(resourceMap.getString("lbl12.text")); // NOI18N
        lbl12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl12.setName("lbl12"); // NOI18N

        lblModelo.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        lblModelo.setText(resourceMap.getString("lblModelo.text")); // NOI18N
        lblModelo.setName("lblModelo"); // NOI18N

        lblPlacas.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        lblPlacas.setText(resourceMap.getString("lblPlacas.text")); // NOI18N
        lblPlacas.setName("lblPlacas"); // NOI18N

        lblEncargado.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        lblEncargado.setText(resourceMap.getString("lblEncargado.text")); // NOI18N
        lblEncargado.setName("lblEncargado"); // NOI18N

        lblColor.setFont(resourceMap.getFont("lblColor.font")); // NOI18N
        lblColor.setText(resourceMap.getString("lblColor.text")); // NOI18N
        lblColor.setName("lblColor"); // NOI18N

        jLabPlacas1.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        jLabPlacas1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabPlacas1.setText(resourceMap.getString("jLabPlacas1.text")); // NOI18N
        jLabPlacas1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabPlacas1.setName("jLabPlacas1"); // NOI18N

        jPanel2.setBackground(resourceMap.getColor("jPanel2.background")); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 84, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblEncargado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabColor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabPlacas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(lblPlacas, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabModelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(lblModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 36, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabProgresivo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabPlacas1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbl12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, 0)
                        .addComponent(lblColor, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lbl12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabPlacas1)
                .addGap(21, 21, 21)
                .addComponent(jLabProgresivo)
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblColor)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabModelo)
                            .addComponent(lblModelo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabPlacas)
                            .addComponent(lblPlacas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabColor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEncargado))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown

    }//GEN-LAST:event_formComponentShown
    private void imprimir() throws PrinterException{
        job = PrinterJob.getPrinterJob();
        PageFormat pf  = new PageFormat();
         Paper paper = pf.getPaper();
        double width = 9d * 72d;
        double height = 16d * 72d;
        double margin = .1d * 72d;
        paper.setSize(width, height);
        paper.setImageableArea(margin,0,
                width - (margin * 2),height);
        pf.setPaper(paper);
       
        job.setPrintable(this,pf);

       
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
        g2d.transform(AffineTransform.getRotateInstance( Math.toRadians(-90),(this).getWidth()/2.1,(this).getHeight()/2.1 ));
        g2d.transform(AffineTransform.getTranslateInstance(30,50));
        
        this.printAll(g2d);
        return PAGE_EXISTS;
    
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabColor;
    private javax.swing.JLabel jLabModelo;
    private javax.swing.JLabel jLabPlacas;
    private javax.swing.JLabel jLabPlacas1;
    private javax.swing.JLabel jLabProgresivo;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl12;
    private javax.swing.JLabel lblColor;
    private javax.swing.JLabel lblEncargado;
    private javax.swing.JLabel lblModelo;
    private javax.swing.JLabel lblPlacas;
    // End of variables declaration//GEN-END:variables


}
