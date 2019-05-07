package lab2;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.StringTokenizer;

public class TreeRenderer extends DefaultTreeCellRenderer {
    ImageIcon student = new ImageIcon("student.png");
    ImageIcon root = new ImageIcon("hat.png");
    ImageIcon group = new ImageIcon("course.png");
    ImageIcon course = new ImageIcon("book.png");
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        JLabel component = (JLabel)super.getTreeCellRendererComponent( tree, value, selected, expanded, leaf, row, hasFocus);
        if(leaf) {
            component.setIcon(student);
        } else {
            if(row==0)
                component.setIcon(root);
           else {
                      StringTokenizer tokenizer = new StringTokenizer( value.toString());
                      String token = tokenizer.nextToken();
                      token = tokenizer.nextToken();
                      if(token.equals("course")){
                          component.setIcon(course);
                      } else{
                          component.setIcon(group);
                      }

              }
            }
            return component;
    }
}
