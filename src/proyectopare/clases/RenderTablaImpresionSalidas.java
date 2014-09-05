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
public class RenderTablaImpresionSalidas extends JTextField implements TableCellRenderer {

    public RenderTablaImpresionSalidas() {
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
                this.setOpaque(true);
                this.setHorizontalAlignment(RenderTablaImpresionSalidas.RIGHT);
                this.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 13));
                break;
            case 14:
                this.setHorizontalAlignment(RenderTablaImpresionSalidas.CENTER);
                this.setFont(new java.awt.Font("3 of 9 Barcode", java.awt.Font.PLAIN, 35));
                table.setRowHeight(row, 44);
                break;
            default:
//                if(column ==1) {
                this.setHorizontalAlignment(RenderTablaImpresionSalidas.LEADING);
                this.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11));
//                table.setRowHeight(row, 28);
//                }else {
//
//                }
                break;
        }
        this.setText(String.valueOf(value));
        return this;
    }
}

