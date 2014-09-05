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
public class RenderTablaParking implements TableCellRenderer {

    public RenderTablaParking() {
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
            etiqueta.setBackground(GRIS);
            etiqueta.setHorizontalAlignment(JLabel.CENTER);
            etiqueta.setText(value.toString());
            return etiqueta;
        } //FIN Byte
        else if(value instanceof Boolean) {
            boolean v = ((Boolean)value).booleanValue();
            if(table.getValueAt(row, 0).equals('C')) {
                ch.setBackground(Color.LIGHT_GRAY);
            }
            else if(table.getValueAt(row, 0).equals('T')) {
                ch.setBackground (ROJO);
            }
            else {
                ch.setBackground (table.getValueAt(row, 1).equals(true) ? VERDE : Color.YELLOW);
            }
            ch.setSelected(v);
            if (isSelected) {
                ch.setForeground(ROJO);
                ch.setBackground (Color.WHITE);
            }
            return ch;
        } // FIN Boolean
        else {
            if(table.getValueAt(row, 0).equals('C')) {
                etiqueta.setBackground(Color.LIGHT_GRAY);
            }
            else if(table.getValueAt(row, 0).equals('T')) {
                etiqueta.setBackground (ROJO);
            }
            else {
                etiqueta.setBackground (table.getValueAt(row, 1).equals(true) ? VERDE : Color.YELLOW);
            }
            etiqueta.setText(String.valueOf(value));
            if (isSelected) {
                etiqueta.setForeground(ROJO);
                etiqueta.setBackground (Color.WHITE);
            }
        }
        return etiqueta;
    }

    public static final Color VERDE = new Color(102,255,102);
    public static final Color ROJO = new Color(255,34,34);
    public static final Color GRIS = new Color(153,153,153);
}

