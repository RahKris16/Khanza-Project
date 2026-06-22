 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fungsi;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

//public class TextAreaRenderer extends JTextArea implements TableCellRenderer {
//    public TextAreaRenderer() {
//        setLineWrap(true);
//        setWrapStyleWord(true);
//        setOpaque(true);
//    }
//
//    @Override
//    public Component getTableCellRendererComponent(JTable table, Object value,
//            boolean isSelected, boolean hasFocus, int row, int column) {
//        setText(value == null ? "" : value.toString());
//        setSize(table.getColumnModel().getColumn(column).getWidth(), Short.MAX_VALUE);
//        if (isSelected) {
//            setBackground(table.getSelectionBackground());
//            setForeground(table.getSelectionForeground());
//        } else {
//            setBackground(table.getBackground());
//            setForeground(table.getForeground());
//        }
//        return this;
//    }
//}

public class TextAreaRenderer extends JTextArea implements TableCellRenderer {
    public TextAreaRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value == null ? "" : value.toString());
        setFont(table.getFont());
        setSize(table.getColumnModel().getColumn(column).getWidth(), Short.MAX_VALUE);
        if (isSelected) {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        } else {
            setBackground(table.getBackground());
            setForeground(table.getForeground());
        }
        return this;
    }
}
