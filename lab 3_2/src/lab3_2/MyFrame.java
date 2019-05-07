package lab3_2;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class MyFrame extends JFrame {
    BufferedImage image;
    int a = 650;
    int speed = 200;
    boolean direction = true;
    MyFrame(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(a, a);
        this.setLayout(new BorderLayout());
        try {
            image = ImageIO.read(new File("camomile.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.add(new Canvas(), BorderLayout.CENTER);
        JPanel menuPanel = new JPanel(new GridLayout(2, 2));
        menuPanel.add(new JLabel("Speed"));
        menuPanel.add(new JLabel("Direction"));
       JSlider slider = new JSlider(0, 500, speed);
        slider.setPaintLabels(true);
        slider.setLabelTable(slider.createStandardLabels(50));
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                speed = slider.getValue();
            }
        });
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setSelectedItem("clockwise");
        comboBox.addItem("clockwise");
        comboBox.addItem("anticlockwise");
        comboBox.setEditable(false);
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getItem().toString().equals("clockwise")) direction = true;
                if(e.getItem().toString().equals("anticlockwise")) direction = false;
            }
        });

        menuPanel.add(slider);
        menuPanel.add(comboBox);
        this.add(menuPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    class Canvas extends JPanel{
        int xCenter;
        int yCenter;
        double radius;
        double xCurrent;
        double yCurrent;
        double corner = -90;
        Canvas(){
            super();
            this.setSize(MyFrame.this.getHeight()-120, MyFrame.this.getWidth()-50);
            radius = Math.min(this.getHeight()/2, this.getWidth()/2);
            xCenter = 0;
            yCenter = 0;
            this.setBackground(Color.white);
            Timer timer = new Timer(200, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(direction)
                    corner+=(double)speed/(radius);
                    else corner-=(double)speed/(radius);
                    radius = Math.min(Canvas.this.getBounds().height/2, Canvas.this.getBounds().width/2);
                    xCurrent =  (xCenter + radius * Math.cos((corner*Math.PI)/180));
                    yCurrent =  (yCenter + radius * Math.sin((corner*Math.PI)/180));
                    repaint();
                }
            });
            timer.start();
        }
        @Override
        public void paintComponent(Graphics graphics){
            super.paintComponent(graphics);
            graphics.setColor(Color.orange);
            graphics.drawOval(xCenter, yCenter, (int)(2*radius), (int)(2*radius));
            graphics.drawImage(image,(int)(radius + xCurrent), (int)(radius+yCurrent), null);

        }

    }


}

