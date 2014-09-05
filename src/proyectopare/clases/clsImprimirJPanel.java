/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectopare.clases;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import javax.swing.Icon;
import javax.swing.JPanel;

/**
 *
 * @author Roman
 */
public class clsImprimirJPanel extends JPanel implements Printable {
    private Icon icon;
    private Image image;

    public clsImprimirJPanel(String txt1,String txt2) {
        super(new java.awt.BorderLayout());
        this.setSize(300, 400);
        et1 = new javax.swing.JLabel();
        et1.setText("primer texto");
        et1.setOpaque(false);
        et1.setBackground(java.awt.Color.RED);

        et2 = new javax.swing.JLabel();
        et2.setText("segundo texto");
        et2.setOpaque(false);
        et2.setBackground(java.awt.Color.BLUE);
        et2.setFont(new Font("Tahoma",Font.BOLD,14));
        this.add(et1, java.awt.BorderLayout.NORTH);
        this.add(et2, java.awt.BorderLayout.SOUTH);
    }

    public int print (Graphics g, PageFormat f, int pageIndex) {
        if (pageIndex == 0) {
            
            
            Graphics2D g2d = (Graphics2D)g;
            g2d.translate(f.getImageableX(),f.getImageableY());

            this.setOpaque(false);
            this.setBackground(java.awt.Color.GREEN);

            this.paintComponent(g);
            return PAGE_EXISTS;
        }
        else
            return NO_SUCH_PAGE;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private javax.swing.JLabel et1;
    private javax.swing.JLabel et2;
}

