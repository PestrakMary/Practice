package sample;

import javafx.scene.control.Label;

import java.awt.*;
import java.util.ArrayList;

public class PressedButton extends Label implements Observable {
    ArrayList<ButtonObserver> list;
    PressedButton(){
        super();
        list = new ArrayList<>();
    }
    @Override
    public void registerObserver(Observer o) { list.add((ButtonObserver) o); }

    @Override
    public void removeObserver(Observer o) {
        list.remove((ButtonObserver) o);
    }

    @Override
    public void notifyObservers(String message) {
        for(Observer obj: list){
            obj.update(message);
        }
    }
    class ButtonObserver extends Observer{
        ButtonObserver(){

        }
        @Override
        public void update(String message) {
            PressedButton.this.setText(message);
        }
    }

}

