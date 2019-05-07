package lab2;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table,
                value, isSelected, hasFocus, row, column);

        if (column == 3 && row == table.getRowCount() - 1) return null;
        else return label;
        //  } else {
        //   label.setLabelFor(new Checkbox());
        //}
        //return label;
    }
}
