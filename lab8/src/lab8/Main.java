package lab8;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javazoom.jl.player.Player;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static File music = new File("music1.wav");
    public  static AudioInputStream ais;

    public static void main(String[] args) {
        try {
            MyFrame frame =  new MyFrame();
            new Thread() {
                @Override
                public void run() {
                    try {
                        ais = AudioSystem.getAudioInputStream(music);
                        Clip clip = AudioSystem.getClip();
                        clip.open(ais);
                        clip.setFramePosition(0); //устанавливаем указатель на старт
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                        clip.start();

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Can't find resources!");
                    }
                }
            }.start();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Can't find resources!");
        }

    }
}
