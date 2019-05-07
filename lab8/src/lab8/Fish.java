package lab8;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Fish extends GameObject implements Observer{
    int radius;
    boolean eaten;
    int type;
    Fish(String path, boolean side, int type){
        super(path, side);
        eaten = false;
        this.type = type;
       setStartCoord();
    }

    void move(){
        if (this.side) {
            this.x += radius*Math.random();
                this.y +=radius*Math.random();
        } else {
            this.x -= radius*Math.random();
                this.y -=  radius*Math.random();
        }
    }

    boolean isVisible(){
        return !(x<-50 || x>MyFrame.Width || y<-50 || y>MyFrame.Height);
    }


    void setStartCoord(){
        if(side){
            x = 0;
        } else{
            x = MyFrame.Width;
        }
        y = (int)(Math.random()*MyFrame.Height +(MyFrame.Height *Math.random())/9);
        radius = (int)((Math.random()+1)*17+Math.random()*7+Math.random()*56)/3;
        eaten = false;
    }

    @Override
    public void update(int x, int y) {
        if((this.x < x+50 && this.x>x-50) && (this.y < y+50 && this.y>y-50)) eaten = true;
    }
}
