
package nhom19;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class TheLoaiTable extends AbstractTableModel{
    // khai bao tieu de cua mot cot
    private String name[] = {"Mã thể loại", "Tên thể loại"};
    // khai bao lop chua kieu du lieu cua tung cot
    private Class classes[] = {String.class, String.class};
    
    ArrayList<TheLoai> dsTl = new ArrayList<TheLoai>();
    
    public TheLoaiTable(ArrayList<TheLoai> dsTl){
        this.dsTl = dsTl;
    }
    
    @Override
    public int getRowCount() {
        return dsTl.size();
    }

    @Override
    public int getColumnCount() {
        return name.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0){
            return dsTl.get(rowIndex).getMaTl();
        }
        if(columnIndex == 1){
            return dsTl.get(rowIndex).getTenTl();
        }
        return null;
    }
    @Override
    public Class getColumnClass(int columnIndex) {
        return classes[columnIndex]; 
    }

    @Override
    public String getColumnName(int column) {
        return name[column];
    }
}
