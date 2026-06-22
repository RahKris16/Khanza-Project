/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fungsi;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Owner
 */
public class WarnaTableKasirRalan extends DefaultTableCellRenderer {
    //Modifikasi untuk pendaftaran dan kasir
//    private String safeGet(JTable table, int row, int col) {
//        if (col >= table.getColumnCount()) return "";
//        Object val = table.getValueAt(row, col);
//        return val != null ? val.toString() : "";
//    }
    
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (row % 2 == 1){
            component.setBackground(new Color(255,246,244));
        }else{
            component.setBackground(new Color(255,255,255));
        } 
        if(table.getValueAt(row,10).toString().equals("Batal")){
            component.setBackground(new Color(255,243,109));
        }else if(table.getValueAt(row,10).toString().equals("Dirujuk")||table.getValueAt(row,10).toString().equals("Meninggal")||table.getValueAt(row,10).toString().equals("Pulang Paksa")){
            component.setBackground(new Color(152,152,156));
        }else if(table.getValueAt(row,10).toString().equals("Dirawat")){
            component.setBackground(new Color(119,221,119));
        }
        
//        Default
//        if(table.getValueAt(row,15).toString().equals("Sudah Bayar")){
//            component.setBackground(new Color(168,255,168));
//        }

//        Modifikasi JKN
        if(table.getValueAt(row,10).toString().equals("Sudah")){
            if(table.getValueAt(row,15).toString().equals("Sudah Bayar")){
                component.setBackground(new Color(168,255,168));
            }else{
                component.setBackground(new Color(154, 243, 245));
            }
        }
        return component;
    }

}
