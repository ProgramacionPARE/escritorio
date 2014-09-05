/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectopare.clases;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Roman
 */
public class RenderTablaOperadores implements TableCellRenderer {

    public RenderTablaOperadores() {
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
        boolean hasFocus, int row, int column) {
        JLabel etiqueta = new JLabel();
        JCheckBox ch = new JCheckBox();
        ch.setOpaque(true);
        etiqueta.setOpaque(true);
        etiqueta.setFont(new java.awt.Font("tahoma", java.awt.Font.PLAIN, 11));
        ch.setFont(new java.awt.Font("tahoma", java.awt.Font.PLAIN, 11));
        if(value instanceof Character) {
            etiqueta.setBackground(Color.GRAY);
            etiqueta.setHorizontalAlignment(JLabel.CENTER);
            etiqueta.setText(value.toString());
            return etiqueta;
        } //FIN Byte
        else if(value instanceof Boolean) {
            boolean v = ((Boolean)value).booleanValue();
            if(table.getValueAt(row, 0).equals('C')) {
                ch.setBackground(Color.LIGHT_GRAY);
            }
            else if(!v) {
                ch.setBackground (Color.YELLOW);
            }
            else {
                ch.setBackground (Color.GREEN);
            }
            ch.setSelected(v);
            if (isSelected) {
                ch.setForeground(Color.RED);
                ch.setBackground (Color.WHITE);
            }
            return ch;
        } // FIN Boolean
        else {
            if(table.getValueAt(row, 0).equals('C')) {
                etiqueta.setBackground(Color.LIGHT_GRAY);
            }
            else if(Boolean.parseBoolean(String.valueOf(table.getValueAt(row, 1)))) {
                etiqueta.setBackground (Color.GREEN);
            }
            else {
                etiqueta.setBackground (Color.YELLOW);
            }
            etiqueta.setText(String.valueOf(value));
            if (isSelected) {
                etiqueta.setForeground(Color.RED);
                etiqueta.setBackground (Color.WHITE);
            }
        }
        return etiqueta;
    }
}

