/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectopare.clases;

import javax.swing.DefaultCellEditor;

/**
 *
 * @author Roman
 */
public class MyComboBoxEditor extends DefaultCellEditor {

    public MyComboBoxEditor(String[] items) {
            super(new javax.swing.JComboBox(items));
    }

}
