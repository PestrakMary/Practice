package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(getScene());
        primaryStage.show();
    }

    Scene getScene(){
        LogArea log = new LogArea();
        log.registerObserver(new Observer());
        PressedButton pressedButton = new PressedButton();
        pressedButton.registerObserver(new Observer());
        pressedButton.setFont(new Font(50));
        pressedButton.setAlignment(Pos.CENTER);
        log.setEditable(false);
        HBox hBox = new HBox();
        hBox.getChildren().addAll(log, pressedButton);
        Scene scene = new Scene(hBox, 650, 560);
        scene.setOnKeyPressed((event -> {
            String message;
            if(event.getText().isEmpty()) message = event.getCode().getName();
            else message= event.getText();
            pressedButton.notifyObservers(message);
            log.notifyObservers(message);
        }));
        return scene;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
