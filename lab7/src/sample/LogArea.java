package sample;

import javafx.scene.control.ListView;

import javax.swing.text.Element;
import java.util.ArrayList;

public class LogArea extends ListView implements Observable {
    ArrayList<ListObserver> list;
    LogArea(){
        super();
        list = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        list.add((ListObserver) o);
    }

    @Override
    public void removeObserver(Observer o) {
        list.remove((ListObserver) o);
    }

    @Override
    public void notifyObservers(String message) {
        for(Observer obj: list){
            obj.update(message);
        }
    }

    class ListObserver extends Observer{
        ListObserver(){

        }
        @Override
        public void update(String key) {
            LogArea.this.getItems().add(key);
        }
    }
}
