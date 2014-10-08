/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

