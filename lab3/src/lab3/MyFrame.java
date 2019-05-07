package lab3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.TimerTask;

public class MyFrame extends JFrame {
    MyFrame(){
        super();
        this.setSize(1000, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Canvas canvas = new Canvas();

        this.setLayout(new BorderLayout());
        this.add(canvas, BorderLayout.CENTER);
        this.setVisible(true);
    }

}

class Canvas extends JPanel{
    int xCenter = 250;
    int yCenter = 250;
    int radius = 200;
    double corner = -90;
    int xCurrent;
    int yCurrent;
    BufferedImage buffer;
    private final Color BACK_COLOR = new Color(255, 255, 255);
    Canvas() {
        super();
        this.setSize(1000, 1000);
        this.setBackground(BACK_COLOR);
        buffer = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                xCurrent = (int) (xCenter + radius * Math.cos((corner*Math.PI)/180));
                yCurrent = (int) (yCenter + radius * Math.sin((corner*Math.PI)/180));
                corner += 6;
                Graphics graphics = buffer.getGraphics();
                graphics.setColor(Color.PINK);
                graphics.fillOval(xCenter, yCenter, 2 * radius, 2 * radius);
                graphics.setColor(Color.MAGENTA);
                graphics.drawLine(xCenter+radius, yCenter+radius, xCurrent+radius, yCurrent+radius);
                repaint();
            }
        });
        timer.start();
        }

    @Override
    public void paintComponent(Graphics graph){
        super.paintComponent(graph);
        graph.drawImage(buffer,0, 0, null);
    }
}
