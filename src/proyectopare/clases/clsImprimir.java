/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectopare.clases;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Roman
 */
public class clsImprimir extends JPanel implements Printable {
    private Icon icon;
    private Image image;

    public clsImprimir() {
    }

    public int print (Graphics g, PageFormat f, int pageIndex) {
//        new javax.swing.ImageIcon(getClass().getResource
//        this.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectopare/clases/reportes/archivosjasper/codigoBarras.gif")));
        if (pageIndex == 0) {
            this.setSize(250, 250);
            this.paintComponent(g);
            return PAGE_EXISTS;
        }
        else
            return NO_SUCH_PAGE;
    }

    @Override
    protected void paintComponent(Graphics g) {
        ImageIcon fondo = new ImageIcon("codigoBarras.jpg");

        g.drawImage(fondo.getImage(), 0, 0, 250, 150, null);
        setOpaque(false);
        super.paintComponent(g);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon){
        this.icon=icon;
        setImage(((ImageIcon)icon).getImage());
    }
}

