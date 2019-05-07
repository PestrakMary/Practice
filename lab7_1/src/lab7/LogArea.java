package lab7;

import javafx.scene.control.ListView;

import javax.swing.text.Element;
import java.util.ArrayList;

public class LogArea extends ListView implements Observer {
    LogArea(){
        super();
        this.setEditable(false);
    }

    @Override
    public void update(String key) {
        this.getItems().add(key);
    }

}