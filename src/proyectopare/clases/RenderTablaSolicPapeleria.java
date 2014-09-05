/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectopare.clases;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Roman
 */
public class RenderTablaSolicPapeleria extends JComboBox implements TableCellRenderer {

    public RenderTablaSolicPapeleria(String[] items) {
        super(items);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
        boolean hasFocus, int row, int column) {
//        if (isSelected) {
//                setForeground(table.getSelectionForeground());
//                super.setBackground(table.getSelectionBackground());
//            } else {
//                setForeground(table.getForeground());
//                setBackground(table.getBackground());
//            }
//        if(value instanceof String) {
//            this.setFont(new java.awt.Font("tahoma", java.awt.Font.PLAIN, 11));
//            setSelectedItem(value);
//        }
       
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            super.setBackground(table.getSelectionBackground());
        } else {
            setForeground(Color.BLACK);
            setBackground(Color.WHITE);
        }
        // Select the current value
        this.setSelectedItem(value);
        return this;
    }
}

//public class MyComboBoxEditor extends DefaultCellEditor {
//    public MyComboBoxEditor(String[] items) {
//        super(new JComboBox(items));
//    }
//}


