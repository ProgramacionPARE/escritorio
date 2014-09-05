/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectopare.clases;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JDialog;
import vistas.formatos.FrmP2BoletoLlaves;

public class ImprimirForma implements Printable {
    JDialog pdia;

//    public ImprimirForma(JDialog f) {
//        pdia = f;
//        System.out.println("Imprimiendo forma...");
//        PrinterJob job = PrinterJob.getPrinterJob();
//         job.setPrintable(this);
//         boolean ok = job.printDialog();
//         if (ok) {
//             try {
//                  job.print();
//             } catch (PrinterException ex) {
//                 ex.printStackTrace();
//             }
//         }
//    } }
 

    public ImprimirForma(JDialog f, PrinterJob job) {
        pdia = f;
        System.out.println("Imprimiendo forma...");
        job.setPrintable(this);
        try {
            job.print();
        } catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }

    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        if (page > 0) { /* We have only one page, and 'page' is zero-based */
//            javax.swing.JOptionPane.showMessageDialog(null,
//                    "No se pueden imprimir más de una página.", "Error",
//                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D)g;
//        g2d.translate(pf.getImageableX(), pf.getImageableY());
        /* Now print the window and its visible contents */

//       AffineTransform at = new AffineTransform();
////
////        g2d.setTransform(AffineTransform.getRotateInstance( 1.5707963, pdia.getWidth()/2, pdia.getHeight()/2));
        g2d.setTransform(AffineTransform.getRotateInstance( Math.toRadians(-90), pdia.getWidth()/2, pdia.getHeight()/2 ));
//
//        g2d.setTransform(at);

//        g2d.translate(0,0);

        pdia.printAll(g2d);
        return PAGE_EXISTS;
    }

}



