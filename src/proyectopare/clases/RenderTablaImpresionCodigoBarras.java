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
public class RenderTablaImpresionCodigoBarras extends JTextField implements TableCellRenderer {

    public RenderTablaImpresionCodigoBarras() {
        super();
        this.setBorder(null);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
        boolean hasFocus, int row, int column) {
        
        switch(row) {
            case 2:
                this.setHorizontalAlignment(RenderTablaImpresionCodigoBarras.CENTER);
                this.setFont(new java.awt.Font("3 of 9 Barcode", java.awt.Font.PLAIN, 28));
                table.setRowHeight(row, 42);
                break;
            case 3:
                table.setRowHeight(row, 1);
                break;
            default:
                this.setHorizontalAlignment(RenderTablaImpresionEntradas.LEADING);
                this.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11));
                break;
        }
        this.setText(String.valueOf(value));
        return this;
    }
}
