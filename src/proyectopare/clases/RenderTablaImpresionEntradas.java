/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectopare.clases;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Roman
 */
public class RenderTablaImpresionEntradas extends JTextField implements TableCellRenderer {

    public RenderTablaImpresionEntradas() {
        super();
        this.setBorder(null);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
        boolean hasFocus, int row, int column) {
        if(column == 0 && row == 0) {
            javax.swing.JLabel et = new javax.swing.JLabel("");
            javax.swing.ImageIcon img = new javax.swing.ImageIcon(
                    getClass().getResource("/proyectopare/pantallas/resources/PARE_estacionamiento_impresiones.gif"));
            table.setRowHeight(row, 26);
            et.setIcon(img);
            et.setOpaque(false);
            et.setBorder(null);
            return et;
        }
        switch(row) {
            case 0:
                this.setHorizontalAlignment(RenderTablaImpresionEntradas.RIGHT);
                this.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 13));
                break;
            case 12:
                this.setHorizontalAlignment(RenderTablaImpresionEntradas.CENTER);
                this.setFont(new java.awt.Font("3 of 9 Barcode", java.awt.Font.PLAIN, 35));
                table.setRowHeight(row, 44);

//                try {
//                    this.setFont(java.awt.Font.createFont(java.awt.Font.PLAIN, getClass().getResourceAsStream("/proyectopare/fuente3of9/3of9.ttf")));
//                } catch (FontFormatException ex) {
//                    ex.printStackTrace();
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
                break;
            default:
//                if(column ==1) {
                this.setHorizontalAlignment(RenderTablaImpresionEntradas.LEADING);
                this.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11));
//                table.setRowHeight(row, 10);
//                }else {
//
//                }
                break;
        }
        this.setText(String.valueOf(value));
        return this;
    }
}
