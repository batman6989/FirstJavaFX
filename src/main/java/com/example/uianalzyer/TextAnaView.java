package com.example.uianalzyer;
import javafx.application.Application;
import javafx.geometry.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class TextAnaView extends Application {
    public static int range;
    public static String resultEntry;
    public static String htmlFile = "TheRaven.html";
    @Override
    public void start(Stage stage) {
        try {
            File poem = new File(htmlFile);
            Button button1 = new Button("Search This Range");
            FileInputStream ravenstream = new FileInputStream("C:\\Users\\Johnn\\IdeaProjects\\UIAnalzyer\\src\\main\\java\\com\\example\\uianalzyer\\poe.jpeg");
            Image raven = new Image(ravenstream);
            ImageView imageView = new ImageView(raven);
            imageView.setX(610);
            imageView.setY(205);
            imageView.setFitHeight(455);
            imageView.setFitWidth(500);
            imageView.setPreserveRatio(true);
        try {
                Fileconverter.textT(poem, range);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }




            Group root = new Group(imageView); //create group for line scene
            Scene scene = new Scene(root, 1080, 768);
            stage.setTitle("UI Analyzer");
            Pane p = new Pane();
            Label label1 = new Label();
            label1.setLayoutX(440);
            label1.setLayoutY(20);
            label1.setText("Poem Analyzer");



            TextField resultsField = new TextField();    //textfield 1
            resultsField.setText(resultEntry);
            resultsField.setMaxWidth(40);

            scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent eventType) {
                    System.out.println("Key Press is: " + resultsField.getText());
                    range = Integer.parseInt(resultsField.getText());
                    System.out.println("range integer is: " + range);

                    File poem = new File(htmlFile);
                    try {
                        Fileconverter.textT(poem, range);
                    } catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
            });

            TextArea output = new TextArea();    //textfield 1
            output.setPrefSize(500, 400);

        button1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    output.clear();
                    for (int i = 0; i < Fileconverter.array.size(); i++) {
                        output.appendText(Fileconverter.array.get(i) + "\n");
                    }
                }
            });

            //textfield grid settings 1
    GridPane grid = new GridPane();
    grid.setLayoutY(100);
    grid.setLayoutX(50);
    grid.setVgap(4);
    grid.setHgap(10);
    grid.setPadding(new Insets(5, 5, 5, 5));
    grid.add(new Label("Enter Range of Results: "), 0, 0);
    grid.add(resultsField, 1, 0);
    grid.add(button1, 0, 3);
    grid.setGridLinesVisible(true);


            //textfield grid settings 2

            GridPane grid2 = new GridPane();

            grid2.setLayoutY(200);
            grid2.setLayoutX(50);


            grid2.setVgap(4);
            grid2.setHgap(10);
            grid2.setPadding(new Insets(5, 5, 5, 5));
            grid2.add(new Label("Poem Output: "), 0, 0);
            grid2.add(output, 0, 15);


            Group label = (Group) scene.getRoot();
            root.getChildren().add(button1);
            root.getChildren().add(label1);  //creates a label
            label.getChildren().add(grid);  //group for text label grid
            label.getChildren().add(grid2);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main (String[]args){
        launch(args);
    }
}