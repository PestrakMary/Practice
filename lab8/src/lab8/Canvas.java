package lab8;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Canvas extends JPanel {
    BufferedImage background;
    BufferedImage buffer;
    String backgroundPath = "background";
    Canvas(){
        super();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(buffer, 0, 0, null);
    }

    public void bufferRepaint(GameObject sharkPlayer, ArrayList<Observer> observers){
        buffer.getGraphics().clearRect(0, 0, buffer.getWidth(), buffer.getHeight());
        buffer.getGraphics().drawImage(background, 0, 0, null);
            sharkPlayer.paintObject(buffer.getGraphics());
        for(Observer obj: observers) {
            if (((Fish)obj).isVisible()) {
                ((Fish)obj).move();
           } else{
                ((Fish) obj).setStartCoord();
            }
            ((Fish) obj).paintObject(buffer.getGraphics());
            repaint();
        }
    }

    public void paintLevel(int level){
        try {
            background = ImageIO.read(new File(backgroundPath+level+".jpg"));
        } catch (IOException ex){
            JOptionPane.showMessageDialog(null, "Can't find resources!");
        }
        buffer = new BufferedImage(background.getWidth(), background.getHeight(), BufferedImage.TYPE_INT_ARGB);
        buffer.getGraphics().drawImage(background, 0, 0, null);
        this.repaint();
    }

    public void paintPlayer(GameObject sharkPlayer){
        sharkPlayer.paintObject(buffer.getGraphics());
    }
}
