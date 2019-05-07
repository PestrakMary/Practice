package lab6;

import javafx.scene.control.TableSelectionModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;

public class MyFrame extends JFrame {
    JPanel mainPanel;
    JLabel sample;
    JPanel game;
    BufferedImage image;
    ImageIcon sampl;
    int PART = 4;
    JButton button;
    JButton butt;
    boolean flag = false;
    JButton[][] parts = new JButton[PART][PART];
    String filePath = "image.jpg";

    MyFrame() {
        super("Puzzle");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1150, 1000);
       // this.setResizable(false);
        this.setLayout(new BorderLayout());
        sampl = new ImageIcon(filePath);
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));
        sample = new JLabel();
        sample.setIcon(sampl);


        game = new JPanel();
        game.setLayout(new GridLayout(PART, PART));
        createGame();
        for (int i = 0; i < PART*PART; i++)
            swapParts((int)(Math.random() *PART),
                    (int)(Math.random() * PART),
                    (int)(Math.random() * PART),
                    (int)(Math.random() * PART));
        mainPanel.add(game);
        mainPanel.add(sample);
        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    void createGame() {
        try {
            image = ImageIO.read(new File(filePath));
            int partWidth = image.getWidth() / PART;
            int partHeight = image.getHeight() / PART;
            for (int i = 0; i < PART; i++) {
                for (int j = 0; j < PART; j++) {
                    parts[i][j] = new JButton();
                    parts[i][j].setIcon(new ImageIcon(image.getSubimage(j * partWidth, i* partHeight, partWidth, partHeight)));
                    parts[i][j].setName(Integer.toString(i * PART + j));
                    parts[i][j].putClientProperty(0, i);
                    parts[i][j].putClientProperty(1, j);

                    parts[i][j].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            butt = (JButton) e.getSource();
                            flag = true;
                        }
                    });
                    parts[i][j].addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                            if(button == null) button = (JButton) e.getSource();
                            if (flag) {
                                button = butt;
                                flag = false;
                            }
                            int x = (int) button.getClientProperty(0);
                            int y = (int) button.getClientProperty(1);
                            int key = e.getKeyCode();
                            switch (key) {
                                case KeyEvent.VK_UP: {
                                    if (x - 1 >= 0) {
                                        swapParts(x, y, x - 1, y);
                                        button = parts[x-1][y];
                                        checkCorrectness();
                                    }
                                    break;
                                }
                                case KeyEvent.VK_DOWN: {
                                    if (x+1 < PART) {
                                        swapParts(x, y, x + 1, y);
                                        button = parts[x+1][y];
                                        checkCorrectness();
                                    }
                                    break;
                                }
                                case KeyEvent.VK_LEFT: {
                                    if (y - 1 >= 0) {
                                        swapParts(x, y, x, y - 1);
                                        button = parts[x][y-1];
                                        checkCorrectness();
                                    }
                                    break;
                                }
                                case KeyEvent.VK_RIGHT: {
                                    if (y + 1 < PART) {
                                        swapParts(x, y, x, y + 1);
                                        button = parts[x][y+1];
                                        checkCorrectness();
                                    }
                                    break;
                                }
                                default: break;
                            }
                        }
                    });

                    game.add(parts[i][j]);
                }
            }
        } catch (IOException ex) {

        }
    }

    void swapParts(int x1, int y1, int x2, int y2) {
        JButton but1 = parts[x1][y1];
        JButton but2 = parts[x2][y2];
        String name = but1.getName();
        but1.setName(but2.getName());
        but2.setName(name);
        Icon t = but1.getIcon();
        but1.setIcon(but2.getIcon());
        but2.setIcon(t);

    }
    void checkCorrectness() {
        for (int i = 0; i < PART; i++)
            for (int j = 0; j < PART; j++)
                if(Integer.parseInt(parts[i][j].getName()) != i*PART+j)
                    return;
        JOptionPane.showMessageDialog(this, "You've solved the puzzle!", "CONGRATULATIONS!", JOptionPane.INFORMATION_MESSAGE );
    }

}