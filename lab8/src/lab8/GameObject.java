package lab8;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameObject {
    Image image;
    int x;
    int y;
    boolean side;
    GameObject(){}
    GameObject(String path, boolean side){
        this.side = side;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException ex){
            JOptionPane.showMessageDialog(null, "Can't find resources!");
        }
    }
    void paintObject(Graphics g){
        g.drawImage(image, x, y, null);
    }
}
