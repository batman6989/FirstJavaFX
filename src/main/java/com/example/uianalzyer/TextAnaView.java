package com.example.uianalzyer;
import javafx.application.Application;
import javafx.geometry.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class TextAnaView extends Application {
    public static int range;
    public static String results;
    public static String htmlpoem = "com/example/uianalzyer/TheRaven.html";
    @Override
    public void start(Stage stage) {
        try {
            File poem = new File(htmlpoem);

            FileInputStream ravenstream = new FileInputStream("C:\\Users\\Johnn\\IdeaProjects\\UIAnalzyer\\src\\main\\java\\com\\example\\uianalzyer\\poe.jpeg");
            Image raven = new Image(ravenstream);
            ImageView imageView = new ImageView(raven);
            imageView.setX(610);
            imageView.setY(205);
            imageView.setFitHeight(455);
            imageView.setFitWidth(500);
            imageView.setPreserveRatio(true);
        try {
                Fileconverter.textT(poem,range);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }

            Group root = new Group(imageView); //create group for line scene
            Scene scene = new Scene(root, 1080, 768);
            stage.setTitle("UI Analyzer");

        Button button1 = new Button("Search This Range");
        TextField resultsoutput = new TextField();
        resultsoutput.setMaxWidth(59);
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent eventType) {
                    System.out.println("Range number users presses: " + range);
                    range = Integer.parseInt(resultsoutput.getText());
                    File poem = new File(htmlpoem);
                    try {
                        Fileconverter.textT(poem, range);
                    } catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
        });

        TextArea output = new TextArea();
        output.setText(results);
        output.setPrefSize(500, 400);
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                output.clear();
                for (int i = 0; i < Fileconverter.wordArray.size(); i++) {
                    output.appendText(Fileconverter.wordArray.get(i) + "\n");
                    }
                }
            });



        Label label1 = new Label("Poem Analyzer");
        label1.setLayoutX(440);
        label1.setLayoutY(20);
        GridPane rangeGrid = new GridPane();
        rangeGrid.setLayoutY(100);
        rangeGrid.setLayoutX(50);
        rangeGrid.setVgap(8);
        rangeGrid.setHgap(10);
        rangeGrid.setPadding(new Insets(5, 5, 5, 5));
        rangeGrid.add(new Label("Enter Range of Results: "), 0, 0);
        rangeGrid.add(resultsoutput, 1, 0);
        rangeGrid.setGridLinesVisible(true);



        GridPane outputGride = new GridPane();
        outputGride.setLayoutY(200);
        outputGride.setLayoutX(50);
        outputGride.setVgap(8);
        outputGride.setHgap(10);
        outputGride.setPadding(new Insets(5, 5, 5, 5));
        outputGride.add(new Label("Poem Output: "), 0, 0);
        outputGride.add(output, 0, 15);
        outputGride.add(button1,3,3);



        Group label = (Group) scene.getRoot();
        root.getChildren().add(button1);
        root.getChildren().add(label1);  //creates a label
        label.getChildren().add(rangeGrid);  //group for text label grid
        label.getChildren().add(outputGride);
        stage.setScene(scene);
        stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main (String[]args){
        launch(args);
    }
}