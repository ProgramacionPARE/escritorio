

package vistas.formatos;

import vistas.formatos.FrmP1BoletoCliente;
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
import modelos.Auto;
import modelos.Turno;
import proyectopare.clases.ImprimirForma;

/**
 *
 * @author Roman
 */
public class FrmP3BoletoParabrisas extends javax.swing.JDialog implements Printable,Runnable{
    private PrinterJob job;

    /** Creates new form VenBoletoValetParking1 */
    public FrmP3BoletoParabrisas(java.awt.Dialog parent, boolean modal, PrinterJob job, Turno turno, Auto auto) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(parent);
        this.job = job;
        jLabProgresivo.setText("NO. " + auto.getProgresivo());
        new Thread(this).start();
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabPlacas1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabProgresivo = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N
        setUndecorated(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(FrmP3BoletoParabrisas.class);
        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel9.setFont(resourceMap.getFont("jLabPlacas1.font")); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel19.setFont(resourceMap.getFont("jLabPlacas1.font")); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N

        jLabel18.setFont(resourceMap.getFont("jLabPlacas1.font")); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N

        jLabPlacas1.setFont(resourceMap.getFont("jLabPlacas1.font")); // NOI18N
        jLabPlacas1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabPlacas1.setText(resourceMap.getString("jLabPlacas1.text")); // NOI18N
        jLabPlacas1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabPlacas1.setName("jLabPlacas1"); // NOI18N

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectopare/pantallas/resources/valetparking 3.jpg"))); // NOI18N
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel8.setIconTextGap(0);
        jLabel8.setName("jLabel8"); // NOI18N

        jLabProgresivo.setFont(resourceMap.getFont("jLabPlacas1.font")); // NOI18N
        jLabProgresivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabProgresivo.setText(resourceMap.getString("jLabProgresivo.text")); // NOI18N
        jLabProgresivo.setName("jLabProgresivo"); // NOI18N

        jLabel20.setFont(resourceMap.getFont("jLabPlacas1.font")); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabPlacas1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabProgresivo, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabProgresivo)
                .addGap(41, 41, 41)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3)
                .addComponent(jLabPlacas1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addGap(53, 53, 53))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
     
    }//GEN-LAST:event_formComponentShown
 private void imprimir(){
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

        try {
            job.print();
        } catch (PrinterException ex) {
            ex.printStackTrace();
        }  finally{
           this.setVisible(false);
           this.dispose();
        }
    }
    
    @Override
    public int print(Graphics g, PageFormat pageFormat, int page) throws PrinterException {
        if (page > 0) { 
            return NO_SUCH_PAGE;
        }
        
       Graphics2D g2d = (Graphics2D)g;
        g2d.transform(AffineTransform.getScaleInstance(.45,.45));
        g2d.transform(AffineTransform.getRotateInstance( Math.toRadians(-90),(this).getWidth()/2.6,(this).getHeight()/2.6 ));
        g2d.transform(AffineTransform.getTranslateInstance(0,80));
        
        this.printAll(g2d);
        return PAGE_EXISTS;
    
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabPlacas1;
    private javax.swing.JLabel jLabProgresivo;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        this.job = PrinterJob.getPrinterJob();
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        int selectedService = 0;
        for(int i = 0; i < services.length;i++){
            if(services[i].getName().contains("STAR")){
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
        setVisible(true);
        imprimir(); }

}
