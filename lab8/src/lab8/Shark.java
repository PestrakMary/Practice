package lab8;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Shark extends GameObject {
    Image imageLeft;
    Image imageRight;
    int x;
    int y;
    int speed = 25;
    boolean side = false;
    Shark(String pathLeft, String pathRight){
        super();
        try {
            imageLeft = ImageIO.read(new File(pathLeft));
            imageRight = ImageIO.read(new File(pathRight));
        } catch (IOException ex){
            JOptionPane.showMessageDialog(null, "Can't find resources!");
        }
        x = (int)Math.random()*MyFrame.Width;
        y = (int)Math.random()*MyFrame.Height;
    }
    void moveRight(){
        x+=speed;
        side = true;
    }
    void moveLeft(){
        x-=speed;
        side = false;
    }
    void moveUp(){
        y-=speed;
    }
    void moveDown(){
        y+=speed;
    }
    @Override
    void paintObject (Graphics g){
        if(side == true)
        g.drawImage(imageRight, x, y, null);
        else g.drawImage(imageLeft, x, y, null);
    }

}
