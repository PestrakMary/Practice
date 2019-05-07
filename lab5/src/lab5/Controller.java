package lab5;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;

public class Controller {
    Model tableModel;
    View view;

    Controller() {
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.tableModel = new Model();
        controller.view = new View(controller.tableModel);
        //controller.tableModel.addTableModelListener(e->pane.updateUI());
    }

}
