package lab7;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.awt.*;
import java.util.ArrayList;

public class PressedButton extends Label implements Observer{
    PressedButton(){
        super();
        this.setFont(new Font(70));
        this.setTextAlignment(TextAlignment.CENTER);
    }

    @Override
    public void update(String key) {
       this.setText(key);
    }
}