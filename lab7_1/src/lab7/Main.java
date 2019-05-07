package lab7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Label;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("KeyLogger");
        primaryStage.setScene(getScene());
        primaryStage.show();
    }

    Scene getScene(){
        Notifier notifier = new Notifier();
        LogArea log = new LogArea();
        Label label1 = new Label("Pressed Key: ");
        Label label2 = new Label("Log Text box: ");
        PressedButton pressedButton = new PressedButton();
        notifier.registerObserver(log);
        notifier.registerObserver(pressedButton);
        GridPane panel = new GridPane();
        panel.getColumnConstraints().add(new ColumnConstraints(250));
        panel.getColumnConstraints().add(new ColumnConstraints(250));
        panel.addRow(0,label1,label2);
        panel.addRow(1, pressedButton, log);
        Scene scene = new Scene(panel, 500, 500);
        scene.setOnKeyPressed((event) -> {
            String message;
            if(event.getText().isEmpty()) message = event.getCode().getName();
            else message = event.getText();
            notifier.notifyObservers(message);
        });
        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
