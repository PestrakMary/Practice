package lab8;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GamePlay {
    Shark sharkPlayer;
    Canvas canvas;
    int level = 1;
    int[] score;
    Controller controller;
    int numbOfTypes = 5;
    int maxInLevel = 1;
    int COUNT_OF_LEVELS = 3;
    Image[] icons;
    GamePlay(){
        score = new int[numbOfTypes];
        controller = new Controller(score, maxInLevel);
        icons = new BufferedImage[numbOfTypes];
        createObjects(level);
        this.canvas = new Canvas();
        canvas.paintLevel(level);
        canvas.paintPlayer(sharkPlayer);
        javax.swing.Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               canvas.bufferRepaint(sharkPlayer, controller.objests);
               controller.notifyObservers(sharkPlayer.x, sharkPlayer.y);
            }
        });
       timer.start();
    }


    void createObjects(int level){
        sharkPlayer = new Shark("shark1.png", "shark2.png");
        for(int i = 1; i<=numbOfTypes; i++){
            boolean side;
            if(i != 1 && i!=5)
                side = false;
            else side = true;
            controller.registerObserver(new Fish("fish"+level+i+".png", side, i-1));
            controller.registerObserver(new Fish("fish"+level+i+".png", side, i-1));
            controller.registerObserver(new Fish("fish"+level+i+".png", side, i-1));
            controller.registerObserver(new Fish("fish"+level+i+".png", side, i-1));
            try {
                icons[i-1] = ImageIO.read(new File("fish" + level + i + ".png"));
            } catch (IOException ex){
                JOptionPane.showMessageDialog(null, "Can't find resources!");
            }
        }
    }
    boolean checkWin(){
        for(int i = 0; i<numbOfTypes; i++){
            if(score[i]<maxInLevel) return false;
        }
        return true;
    }

    void deleteObjects(){
        for(Observer o: controller.objests){
            controller.removeObserver(o);
        }
    }


    void updateLevel() throws GameOverException{
        if(level<COUNT_OF_LEVELS) {
            level++;
            maxInLevel += 1;
            score = new int[numbOfTypes];
            controller = new Controller(score, maxInLevel);
            createObjects(level);
            canvas.paintLevel(level);
            canvas.paintPlayer(sharkPlayer);
            canvas.bufferRepaint(sharkPlayer, controller.objests);
        } else throw new GameOverException();
    }

}
