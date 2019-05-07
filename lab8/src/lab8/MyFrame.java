package lab8;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyFrame extends JFrame {
    static int Width = 2000;
    static int Height = 1800;
    JLabel[] score;
    JPanel results;
    MyFrame() {
        super("HUNGRY SHARK");
        try {
            this.setIconImage(ImageIO.read(new File("shark1.png")));
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Can't find resources!");
        }
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        GamePlay gamePlay = new GamePlay();
        score = new JLabel[gamePlay.numbOfTypes];
        results = new JPanel(new GridLayout(1,gamePlay.numbOfTypes));
        for(int i =0; i<score.length; i++){
            score[i] = new JLabel();
            score[i].setIcon(new ImageIcon(gamePlay.icons[i]));
            score[i].setText(gamePlay.score[i]+" / "+gamePlay.maxInLevel);
            results.add(score[i]);
        }
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_UP:{
                        try {
                            gamePlay.sharkPlayer.moveUp();
                            gamePlay.controller.notifyObservers(gamePlay.sharkPlayer.x, gamePlay.sharkPlayer.y);
                            if (gamePlay.checkWin()) {
                                gamePlay.updateLevel();
                            } else
                                gamePlay.canvas.bufferRepaint(gamePlay.sharkPlayer, gamePlay.controller.objests);
                            updateResult(gamePlay);
                        } catch (GameOverException ex){
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                        break;
                    }
                    case KeyEvent.VK_DOWN:{
                        try {
                        gamePlay.sharkPlayer.moveDown();
                        gamePlay.controller.notifyObservers(gamePlay.sharkPlayer.x, gamePlay.sharkPlayer.y);
                        if(gamePlay.checkWin()){
                            gamePlay.updateLevel();
                        } else
                        gamePlay.canvas.bufferRepaint(gamePlay.sharkPlayer, gamePlay.controller.objests);
                        updateResult(gamePlay);
                        } catch (GameOverException ex){
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                        break;
                    }
                    case KeyEvent.VK_RIGHT:{
                        try{
                        gamePlay.sharkPlayer.moveRight();
                        gamePlay.controller.notifyObservers(gamePlay.sharkPlayer.x, gamePlay.sharkPlayer.y);
                        if(gamePlay.checkWin()){
                            gamePlay.updateLevel();
                        } else
                        gamePlay.canvas.bufferRepaint(gamePlay.sharkPlayer, gamePlay.controller.objests);
                        updateResult(gamePlay);
                    } catch (GameOverException ex){
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                        break;
                    }
                    case KeyEvent.VK_LEFT:{
                        try{
                        gamePlay.sharkPlayer.moveLeft();
                        gamePlay.controller.notifyObservers(gamePlay.sharkPlayer.x, gamePlay.sharkPlayer.y);
                        if(gamePlay.checkWin()){
                            gamePlay.updateLevel();
                        } else
                        gamePlay.canvas.bufferRepaint(gamePlay.sharkPlayer, gamePlay.controller.objests);
                        updateResult(gamePlay);
                        } catch (GameOverException ex){
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                        break;
                    }
                }
            }
        });
        this.add(gamePlay.canvas, BorderLayout.CENTER);
        this.add(results, BorderLayout.NORTH);
        setExtendedState(MAXIMIZED_BOTH);
    }

    void updateResult(GamePlay gamePlay){
        for(int i =0; i<score.length; i++){
            score[i].setIcon(new ImageIcon(gamePlay.icons[i]));
            score[i].setText(gamePlay.score[i]+" / "+gamePlay.maxInLevel);
        }
    }
}
