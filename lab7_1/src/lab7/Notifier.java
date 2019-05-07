package lab7;

import java.util.ArrayList;

public class Notifier implements Observable {
    ArrayList<Observer> observers;
    Notifier(){
        observers = new ArrayList<>();
    }
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(String message) {
        for(Observer observer:observers){
            observer.update(message);
        }

    }
}
