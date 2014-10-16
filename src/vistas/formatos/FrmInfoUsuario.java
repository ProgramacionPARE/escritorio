

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
import modelos.Empleado;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;



    /**
 *
 * @author Roman
 */
public class FrmInfoUsuario extends JDialog implements Printable {
    private PrinterJob job;
    private Barcode barcode=null;

    /** Creates new form VenBoletoValetParking1 */
    public FrmInfoUsuario(Dialog parent, boolean modal, PrinterJob job,Empleado empleado) {
        super(parent, modal);
        try {
            initComponents();
            barcode = BarcodeFactory.createCode128B(empleado.getClave()+ String.format("%06d",empleado.getId() ) );
            barcode.setBarHeight(70);
            barcode.setBarWidth(2);
            barcode.setDrawingText(false);
            jPanel3.add(barcode);
            this.getContentPane().setBackground(Color.white);
            this.setLocationRelativeTo(parent);
            this.job = PrinterJob.getPrinterJob();
            //LLeno el recibo
            this.txtUsuario.setText(empleado.getUsuario());
            this.txtContra.setText(empleado.getContraseña());
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
                Logger.getLogger(FrmInfoUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            MediaSizeName mediaSizeName = MediaSize.findMedia(4,4,MediaPrintableArea.INCH);
            printRequestAttributeSet.add(mediaSizeName);
            printRequestAttributeSet.add(new Copies(1));
            setVisible(true);
            imprimir();
        } catch (BarcodeException ex) {
            Logger.getLogger(FrmInfoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    
 

/** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtContra = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();

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

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(FrmInfoUsuario.class);
        jLabel1.setIcon(resourceMap.getIcon("jLabel1.icon")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 410, 80));

        jLabel3.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel3.setName("jLabel3"); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 80, -1));

        txtUsuario.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        txtUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUsuario.setText(resourceMap.getString("txtUsuario.text")); // NOI18N
        txtUsuario.setBorder(null);
        txtUsuario.setMaximumSize(new java.awt.Dimension(100, 100));
        txtUsuario.setName("txtUsuario"); // NOI18N
        getContentPane().add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 140, 20));

        jLabel8.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel8.setName("jLabel8"); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 110, -1));

        txtContra.setFont(new java.awt.Font("Droid Sans", 0, 18)); // NOI18N
        txtContra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtContra.setBorder(null);
        txtContra.setMaximumSize(new java.awt.Dimension(100, 100));
        txtContra.setName("txtContra"); // NOI18N
        getContentPane().add(txtContra, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 150, -1));

        jPanel3.setBackground(resourceMap.getColor("jPanel3.background")); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new java.awt.BorderLayout());
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 420, 100));

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
        g2d.transform(AffineTransform.getScaleInstance(.5,.5));
        //g2d.transform(AffineTransform.getRotateInstance( Math.toRadians(-90),(this).getWidth()/1.65,(this).getHeight()/1.65 ));
        //g2d.transform(AffineTransform.getTranslateInstance(0,-140));
        
        this.printAll(g2d);
        return PAGE_EXISTS;
    
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtContra;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

}
