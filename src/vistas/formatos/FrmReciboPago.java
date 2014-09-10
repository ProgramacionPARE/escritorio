

package vistas.formatos;

import java.awt.Color;
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
import javax.swing.JFrame;
import modelos.Auto;
import modelos.Estacionamiento;
import modelos.Progresivo;
import modelos.Turno;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;



    /**
 *
 * @author Roman
 */
public class FrmReciboPago extends JDialog implements Printable {
    private PrinterJob job;
    private Barcode barcode=null;
    private Estacionamiento estacionamiento;
    /** Creates new form VenBoletoValetParking1 */
    public FrmReciboPago(Dialog parent, boolean modal, PrinterJob job,Turno turno,Auto auto,Estacionamiento estacionamiento) {
        super(parent, modal);
        initComponents();
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(parent);
        this.job = PrinterJob.getPrinterJob();
        //LLeno el recibo
        this.txtFecha.setText(auto.getFechaEntrada());
        this.txtBoleto.setText(auto.getProgresivo());
        this.txtEntrada.setText(auto.getHoraEntrada());
        this.txtSalida.setText(auto.getHoraSalida());
        this.txtEstacionamiento.setText(estacionamiento.getDescripcion());
        this.txtImporte.setText(String.format("%.2f", (float) auto.getMontoTangible()*.85));
        this.txtIVA.setText(String.format("%.2f", (float) auto.getMontoTangible()*.15));
        this.txtTotal.setText(String.format("%.2f", auto.getMontoTangible()));
        this.txtProgresivo.setText("NO. "+Progresivo.getUltimoProgresivo(estacionamiento.getCaseta(), "RECIBO_PAGO"));
        Progresivo.setProgresivoMasUno(estacionamiento.getCaseta(), "RECIBO_PAGO");
        
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
            Logger.getLogger(FrmReciboPago.class.getName()).log(Level.SEVERE, null, ex);
        }
        PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
        MediaSizeName mediaSizeName = MediaSize.findMedia(4,4,MediaPrintableArea.INCH);
        printRequestAttributeSet.add(mediaSizeName);
        printRequestAttributeSet.add(new Copies(1));
        
        setVisible(true);
        imprimir();


    }
   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtEstacionamiento = new javax.swing.JTextField();
        txtProgresivo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtEntrada = new javax.swing.JTextField();
        txtImporte = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtSalida = new javax.swing.JTextField();
        txtIVA = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtBoleto = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N
        setUndecorated(true);
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(FrmReciboPago.class);
        jLabel1.setIcon(resourceMap.getIcon("jLabel1.icon")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Droid Sans", 1, 36)); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(378, 63, -1, -1));

        jLabel3.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel3.setName("jLabel3"); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 131, 200, -1));

        jLabel5.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel5.setName("jLabel5"); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 131, 200, -1));

        jLabel7.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel7.setIcon(resourceMap.getIcon("jLabel7.icon")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 131, 160, 310));

        txtEstacionamiento.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        txtEstacionamiento.setText(resourceMap.getString("txtEstacionamiento.text")); // NOI18N
        txtEstacionamiento.setBorder(null);
        txtEstacionamiento.setMaximumSize(new java.awt.Dimension(100, 100));
        txtEstacionamiento.setName("txtEstacionamiento"); // NOI18N
        getContentPane().add(txtEstacionamiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 154, 200, -1));

        txtProgresivo.setFont(new java.awt.Font("Droid Sans", 1, 18)); // NOI18N
        txtProgresivo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtProgresivo.setText(resourceMap.getString("txtProgresivo.text")); // NOI18N
        txtProgresivo.setBorder(null);
        txtProgresivo.setMaximumSize(new java.awt.Dimension(100, 100));
        txtProgresivo.setName("txtProgresivo"); // NOI18N
        getContentPane().add(txtProgresivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 233, -1));

        jLabel8.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel8.setName("jLabel8"); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 195, 200, -1));

        txtEntrada.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        txtEntrada.setBorder(null);
        txtEntrada.setMaximumSize(new java.awt.Dimension(100, 100));
        txtEntrada.setName("txtEntrada"); // NOI18N
        getContentPane().add(txtEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 279, 200, -1));

        txtImporte.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        txtImporte.setBorder(null);
        txtImporte.setMaximumSize(new java.awt.Dimension(100, 100));
        txtImporte.setName("txtImporte"); // NOI18N
        getContentPane().add(txtImporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 218, 200, -1));

        jLabel9.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel9.setName("jLabel9"); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 195, 200, -1));

        jLabel10.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel10.setName("jLabel10"); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 310, 200, -1));

        txtSalida.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        txtSalida.setBorder(null);
        txtSalida.setMaximumSize(new java.awt.Dimension(100, 100));
        txtSalida.setName("txtSalida"); // NOI18N
        getContentPane().add(txtSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 333, 200, -1));

        txtIVA.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        txtIVA.setBorder(null);
        txtIVA.setMaximumSize(new java.awt.Dimension(100, 100));
        txtIVA.setName("txtIVA"); // NOI18N
        getContentPane().add(txtIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 279, 200, -1));

        txtTotal.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        txtTotal.setBorder(null);
        txtTotal.setMaximumSize(new java.awt.Dimension(100, 100));
        txtTotal.setName("txtTotal"); // NOI18N
        getContentPane().add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 333, 200, -1));

        jLabel13.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel13.setName("jLabel13"); // NOI18N
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 310, 200, -1));

        txtBoleto.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        txtBoleto.setBorder(null);
        txtBoleto.setMaximumSize(new java.awt.Dimension(100, 100));
        txtBoleto.setName("txtBoleto"); // NOI18N
        getContentPane().add(txtBoleto, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 154, 200, -1));

        jLabel12.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel12.setName("jLabel12"); // NOI18N
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 254, 200, -1));

        txtFecha.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        txtFecha.setBorder(null);
        txtFecha.setMaximumSize(new java.awt.Dimension(100, 100));
        txtFecha.setName("txtFecha"); // NOI18N
        getContentPane().add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 218, 200, -1));

        jLabel4.setFont(resourceMap.getFont("jLabel14.font")); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 422, 420, -1));

        jLabel14.setFont(resourceMap.getFont("jLabel14.font")); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 386, 420, -1));

        jLabel15.setFont(resourceMap.getFont("jLabel14.font")); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 404, 420, -1));

        jLabel16.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel16.setName("jLabel16"); // NOI18N
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 249, 200, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        //new ImprimirForma(this,job);
        //this.dispose();
    }//GEN-LAST:event_formComponentShown
    
    public void imprimir(){
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
        g2d.transform(AffineTransform.getRotateInstance( Math.toRadians(-90),(this).getWidth()/1.65,(this).getHeight()/1.65 ));
        g2d.transform(AffineTransform.getTranslateInstance(0,-140));
        
        this.printAll(g2d);
        return PAGE_EXISTS;
    
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtBoleto;
    private javax.swing.JTextField txtEntrada;
    private javax.swing.JTextField txtEstacionamiento;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtIVA;
    private javax.swing.JTextField txtImporte;
    private javax.swing.JTextField txtProgresivo;
    private javax.swing.JTextField txtSalida;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

}
