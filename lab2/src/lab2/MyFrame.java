package lab2;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;
import java.util.function.BiConsumer;

public class MyFrame extends JFrame {
    boolean editing = false;
    float total = 0;
    MyFrame() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(650, 560);
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        JTabbedPane jTabbedPane = new JTabbedPane(1);
        jTabbedPane.addTab("List", getPanel1());
        jTabbedPane.addTab("Table", getPanel2());
        jTabbedPane.addTab("Tree", getPanel3());
        this.add(jTabbedPane);
        this.setVisible(true);
    }

    JPanel getPanel1() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JList<String> list = new JList<>();
        JScrollPane pane = new JScrollPane(list);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        Map<String, String> mapCapitals = new HashMap<>();
        Map<String, ImageIcon> mapPicters = new HashMap<>();

        readFile(mapCapitals, mapPicters);
        Object[] model = mapCapitals.keySet().toArray();
        String[] listModel = new String[model.length];
        for (int i = 0; i < model.length; i++) {
            listModel[i] = (String) model[i];
        }
        list.setListData(listModel);
        ListRenderer renderer = new ListRenderer(mapPicters, mapCapitals);
        list.setCellRenderer(renderer);
        panel.add(pane, BorderLayout.CENTER);
        return panel;
    }

    JPanel getPanel2() {
        JPanel panel = new JPanel();
        Object[] columnNames = {"Flag", "Information", "Price, $", "Choose"};
        Object[] summary = {null, "Сумма заказа составит: ", total, null};
        int cost = 2;
        int check = 3;
        Object[][] data = {
                {new ImageIcon("flag_belarus.png"), "Замкі Беларусі", new Float(60), false},
                {new ImageIcon("flag_russia.png"), "Золотое кольцо России", new Float(160), false},
                {new ImageIcon("flag_usa.png"), "Большое яблоко. Желтые такси", new Float(500), false},
                {new ImageIcon("flag_great_britain.png"), "London is the capital of Great Britain", new Float(230), false},
                {new ImageIcon("flag_ukraine.png"), "Припять - город-призрак.", new Float(35), false},
                {new ImageIcon("flag_poland.png"), "Из истории Речи Посполитой", new Float(100), false},
                {new ImageIcon("flag_germany.png"), "Выходные в Баварии", new Float(180), false},
                {new ImageIcon("flag_turkey.png"), "Чарующий Восток. По местам жизни Великой Хюррем.", new Float(250), false},
                {new ImageIcon("flag_france.png"), "Париж - город любви.", new Float(230), false},
                summary

        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable jTable = new JTable(model){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex){
                    case 0:
                        return ImageIcon.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Float.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                switch (e.getType()) {
                    case TableModelEvent.UPDATE: {
                        DefaultTableModel tableModel = (DefaultTableModel) e.getSource();
                        if(!editing){
                        float total = 0;
                        if(e.getColumn() == cost || e.getColumn()==check) {
                            for (int i = 0; i < tableModel.getRowCount() - 1; i++) {
                                if ((Boolean) tableModel.getValueAt(i, check)) {
                                    total += (Float) tableModel.getValueAt(i, cost);

                                }
                            }
                            editing = true;
                            tableModel.setValueAt(total, tableModel.getRowCount() - 1, cost);
                        }
                        editing = false;
                        }
                        break;
                    }
                    case TableModelEvent.INSERT: {

                        break;
                    }
                    case TableModelEvent.DELETE: {
                        break;
                    }
                }
            }
        });


        JButton addRow = new JButton("Добавить тур");
        addRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] objects = new Object[4];
                objects[0] = new ImageIcon("earth.png");
                model.insertRow(model.getRowCount() - 1, objects);
            }
        });

        JScrollPane scrollPane = new JScrollPane(jTable);

        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(addRow, BorderLayout.SOUTH);
        return panel;
    }

    JPanel getPanel3() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        Object[] hierarhy = {"Faculty",
                new Object[]{"1 course",
                        new Object[]{"1 group", new Object[]{"Mary"}, new Object[]{"Steve"}, new Object[]{"Sergey"}, new Object[]{"Klara"}},
                        new Object[]{"2 group", new Object[]{"Lally"}, new Object[]{"Pavel"}, new Object[]{"Mark"}},
                        new Object[]{"3 group", new Object[]{"Sam"}, new Object[]{"Mary"}}},
                new Object[]{"2 course",
                        new Object[]{"1 group", new Object[]{"Mary"}, new Object[]{"Sam"}, new Object[]{"Sergey"}, new Object[]{"Petr"}},
                        new Object[]{"2 group", new Object[]{"Danila"}, new Object[]{"Mark"}, new Object[]{"Olga"}},
                        new Object[]{"3 group", new Object[]{"Sasha"}},
                        new Object[]{"4 group", new Object[]{"Sam"}, new Object[]{"Kate"}}},
                new Object[]{"3 course",
                        new Object[]{"1 group", new Object[]{"Marina"}, new Object[]{"Sanny"}, new Object[]{"Pam"}},
                        new Object[]{"2 group", new Object[]{"Dan"}, new Object[]{"Marta"}, new Object[]{"Olga"}}},
                new Object[]{"4 course",
                        new Object[]{"1 group", new Object[]{"Kate"}, new Object[]{"Lera"}, new Object[]{"Lanna"}, new Object[]{"Mark"}, new Object[]{"Roman"}}}
        };
        JTree tree = new JTree(processHierarchy(hierarhy));
        tree.setCellRenderer(new TreeRenderer());
        tree.setEditable(true);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
        final DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

        JPanel inputPanel = new JPanel(new GridLayout(3, 3));
        inputPanel.add(new JLabel("Course: "));
        JTextArea jTextCourse = new JTextArea("2 course");
        inputPanel.add(jTextCourse);
        inputPanel.add(new JLabel("Group: "));
        JTextArea jTextGroup = new JTextArea("1 group");
        inputPanel.add(jTextGroup);
        inputPanel.add(new JLabel("Name: "));
        JTextArea jTextName = new JTextArea("Mary");
        inputPanel.add(jTextName);
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String course = jTextCourse.getText();
                String group = jTextGroup.getText();
                String name = jTextName.getText();
                addNewNode(course, group, name, tree);
                tree.updateUI();
            }
        });
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

                TreePath[] paths = tree.getSelectionPaths();
                if (paths != null) {
                    for (TreePath path : paths) {
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                                path.getLastPathComponent();
                        if (node.getParent() != null) {
                            try {
                                model.removeNodeFromParent(node);
                                tree.updateUI();
                            } catch(NullPointerException ex){
                            }
                        }
                    }
                }
            }
        });
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editing = true;
                String name = jTextName.getText();
                String group = jTextGroup.getText();
                String course = jTextCourse.getText();
                TreePath[] paths = tree.getSelectionPaths();
                if (paths != null)
                    if (paths.length == 1) {
                        TreePath treePath = tree.getSelectionPath();
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath.getLastPathComponent();
                        Object obj = node.getUserObject();
                        if (obj instanceof String) {
                            if (obj.equals(name)) {
                                model.removeNodeFromParent(node);
                                addNewNode(course, group, name, tree);
                                editing = false;
                                tree.updateUI();
                            }
                        }
                    }
            }
        });

        tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                if (!editing) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
                    if (node.isLeaf()) {
                        jTextName.setText((String) node.getUserObject());
                        node = (DefaultMutableTreeNode) node.getParent();
                        jTextGroup.setText((String) node.getUserObject());
                        node = (DefaultMutableTreeNode) node.getParent();
                        jTextCourse.setText((String) node.getUserObject());
                    }
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);
        JScrollPane pane = new JScrollPane(tree);

        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(pane, BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.SOUTH);
        return panel;
    }


    DefaultMutableTreeNode processHierarchy(
            Object[] hierarchy) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(hierarchy[0]);
        DefaultMutableTreeNode child;
        for (int i = 1; i < hierarchy.length; i++) {
            Object nodeSpecifier = hierarchy[i];
            if (nodeSpecifier instanceof Object[]) { //Node with children
                child = processHierarchy((Object[]) nodeSpecifier);
            } else {
                child = new DefaultMutableTreeNode(nodeSpecifier); //Leaf
            }
            node.add(child);
        }
        return (node);
    }


    void readFile(Map<String, String> map, Map<String, ImageIcon> mapIcons) {
        try {
            Scanner scan = new Scanner(new File("Countries.txt"));
            while (scan.hasNext()) {
                String country = scan.next();
                String capital = scan.next();
                String path = scan.next();
                map.put(country, capital);
                mapIcons.put(country, new ImageIcon(path));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void addNewNode(String c, String gr, String n, JTree tree) {
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) (tree.getModel().getRoot());
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) (tree.getModel().getRoot());
        Enumeration<DefaultMutableTreeNode> children = node.children();
        while (children.hasMoreElements()) {
            node = children.nextElement();
            if (node.getUserObject().equals(c)) {
                children = node.children();
                while (children.hasMoreElements()) {
                    node = children.nextElement();
                    if (node.getUserObject().equals(gr)) {
                        children = node.children();
                        while (children.hasMoreElements()) {
                            if (children.nextElement().getUserObject().equals(n)) ;
                            else {
                                if (!children.hasMoreElements())
                                    node.add(new DefaultMutableTreeNode(n));

                            }
                        }
                    } else {
                        if (!children.hasMoreElements()) {
                            DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(gr);
                            treeNode.add(new DefaultMutableTreeNode(n));
                            node = (DefaultMutableTreeNode) node.getParent();
                            node.add(treeNode);
                        }
                    }
                }
            } else {
                if (!children.hasMoreElements()) {
                    DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(c);
                    treeNode.add(new DefaultMutableTreeNode(gr));
                    treeNode = treeNode.getFirstLeaf();
                    treeNode.add(new DefaultMutableTreeNode(n));
                    node = (DefaultMutableTreeNode) node.getParent();
                    node.add((DefaultMutableTreeNode) treeNode.getParent());

                }
            }
        }
    }
}





