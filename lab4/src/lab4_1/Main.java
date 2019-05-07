package lab4_1;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import javafx.scene.control.Button;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends Application {
    TreeMap<String, String> typeAndPattern;
    Image imageTrue;
    Image imageFalse;
    ImageView imageTrue1;
    ImageView imageFalse1;

    public static void main(String[] args) {
	Application.launch(args);
    }
    @Override
    public void init() throws Exception{
        super.init();
        imageTrue = new Image(this.getClass().getResourceAsStream("true.png"));
        imageFalse = new Image(getClass().getResourceAsStream("false.png"));
        imageTrue1 = new ImageView(imageTrue);
        imageFalse1 = new ImageView(imageFalse);
        typeAndPattern = new TreeMap<>();
        typeAndPattern.put("Natural number", "[1-9][0-9]*");
        typeAndPattern.put("Integer", "[+-]?[1-9][0-9]*|[+-]?0");
        typeAndPattern.put("Float", "([+-]?[0-9]+\\.[0-9]*)|([+-]?[0-9]*\\.[0-9]+)|([+-]?[1-9][0-9]*|[+-]?0)");
        typeAndPattern.put("Exponential", "([+-]?[0-9]*.[0-9]+)e[+-]?[1-9][0-9]*|([+-]?0)|([+-]?[0-9]+.[0-9]*)e[+-]?[1-9][0-9]");
        typeAndPattern.put("Date", "\\s(((0[1-9]|1[0-9]|2[0-9])/02)|(0[1-9]|[1-2][0-9]|30)/(04|06|09|11)|([0][1-9]|[1-2][0-9]|30|31)/(01|03|05|07|10|12))/(\\d{4})");
        typeAndPattern.put("Time", "([0]?[0-9]|1[1-9]|2[0-3])(:([0]?[0-9]|[1-5][0-9])){1,2}");
        typeAndPattern.put("Email", "[A-Za-z0-9+]+(.[A-Za-z0-9]+)*@[A-Za-z0-9]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})");
    }

    @Override
    public void start(Stage stage) throws Exception {
        ComboBox<String> regulList = new ComboBox<>();
        regulList.getItems().addAll(typeAndPattern.keySet());
        TextField textField = new TextField();
        Label result = new Label();
        Button button = new Button("Check!");
        Group group = new Group();
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               check(textField.getText(), regulList.getSelectionModel().getSelectedItem(), result);
            }
        });
        regulList.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                check(textField.getText(), regulList.getSelectionModel().getSelectedItem(), result);
            }
        });

        textField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                check(textField.getText(), regulList.getSelectionModel().getSelectedItem(), result);
            }
        });

        group.getChildren().add(textField);
        BorderPane panel = new BorderPane(group);
        panel.setLeft(regulList);
        panel.setRight(result);
        panel.setBottom(button);
        panel.setTop(new Label("Check whether the entered expression responds to the specified type."));
        panel.setAlignment(regulList, Pos.CENTER);
        BorderPane.setAlignment(result, Pos.CENTER );
        panel.setAlignment(button, Pos.BOTTOM_CENTER);
        Scene scene = new Scene(panel);

        stage.setScene(scene);
        stage.setTitle("First Application");
        stage.setWidth(650);
        stage.setHeight(300);
        stage.show();
    }
    public void check(String value, String type, Label result){
        String matcher = typeAndPattern.get(type);
        Pattern p = Pattern.compile(matcher);
        Matcher matcher1 = p.matcher(value);
        if(matcher1.matches() == true)
            result.setGraphic(imageTrue1);
        else result.setGraphic(imageFalse1);
    }
}
