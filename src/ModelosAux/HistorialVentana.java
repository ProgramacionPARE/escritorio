/*
 *     Clase auxiliar donde se guardan temporalmente las ventanas, al abrir una nueva,
       despues de que la nueva ventana se cierra, la ultima que se guardo regresa a su
       estado normal.
 */
package ModelosAux;

import java.awt.Rectangle;
import javax.swing.JDialog;

/**
 *
 * @author sistema
 */
public class HistorialVentana {
    JDialog ventana;
    Rectangle bordes;

    public HistorialVentana(JDialog ventana, Rectangle bordes) {
        this.ventana = ventana;
        this.bordes = bordes;
    }

    public JDialog getVentana() {
        return ventana;
    }

    public void setVentana(JDialog ventana) {
        this.ventana = ventana;
    }

    public Rectangle getBordes() {
        return bordes;
    }

    public void setBordes(Rectangle bordes) {
        this.bordes = bordes;
    }
    
    
}

