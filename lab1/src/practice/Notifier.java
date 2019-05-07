package practice;

import practice.Notifiable;

import java.util.Set;

public class Notifier {
    Set<Notifiable> notifiables;
    public Notifier(Set<? extends Notifiable> notifiables){
        super();
        this.notifiables = (Set<Notifiable>) notifiables;
    }

    public void doNotifyAll(String message){
        for(Notifiable obj: notifiables){
            obj.notify(message);
        }
    }


}
