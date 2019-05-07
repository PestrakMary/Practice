package lab8;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class Controller implements Observable {
    ArrayList<Observer> objests;
    int[] score;
    int maxResult;

    Controller(int[] score, int maxResult){
        objests = new ArrayList<>();
        this.score = score;
        this.maxResult = maxResult;
    }

    @Override
    public void registerObserver(Observer o) {
        objests.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        objests.remove(o);
    }

    @Override
    public void notifyObservers(int x, int y) {
        for(Observer obj: objests){
            obj.update(x, y);
            if(((Fish)obj).eaten){
                    ((Fish) obj).setStartCoord();
                if(score[((Fish) obj).type]<maxResult)
                score[((Fish) obj).type]++;
            }
        }
    }
}
