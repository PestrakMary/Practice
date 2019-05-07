package lab5;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableSelectionModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;

public class View extends JFrame {
    JScrollPane pane;
    JTable table;
    View(Model tableModel){
        super("Excel");
        this.setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setBackground(new Color(176,196,222));
        table = new JTable();
        table.setBackground(new Color(245,255,255));
        table.setRowHeight(20);
        table.getTableHeader().setBackground(new Color(176,196,222));
        table.setModel(tableModel);
        table.setCellSelectionEnabled(true);
        table.setColumnSelectionAllowed(false);
        table.setEnabled(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setDefaultRenderer(String.class, new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    if (column == 0)
                        this.setText(new Integer(row + 1).toString());
                    else
                            this.setText((value == null) ? "" : tableModel.calculateCell((String) value, row, column, new HashSet<Integer>()));
                    return this;
                }
            });
        tableModel.addTableModelListener(e->pane.updateUI());
        pane = new JScrollPane(table);
        this.add(pane, BorderLayout.CENTER);
        this.setSize(1000, 500);
        this.setVisible(true);

    }
}
