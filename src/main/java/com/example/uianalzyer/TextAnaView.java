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

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Scanner;

public class TextAnaView extends Application {
    public static int range;
    public static String resultEntry;
    public static String results;
    public static String htmlpoem = "C:\\Users\\Johnn\\IdeaProjects\\UIAnalzyer\\src\\main\\java\\com\\example\\uianalzyer\\TheRaven.html";
    public static String databaseW = "";
    @Override
    public void start(Stage primaryStage) {
        try {
            Line line = new Line();

            // line settings

            line.setStartX(55.0);
            line.setStartY(80.0);
            line.setEndX(1040.0);
            line.setEndY(65.0);

            Group root = new Group(line);


            Scene scene = new Scene(root,1080,768);


            primaryStage.setTitle("Frequency Word App");




            Label label1 = new Label();     //label1

            label1.setLayoutX(440);
            label1.setLayoutY(20);
            label1.setText("UI Analyzer");
            label1.setFont(Font.font("verdana", FontPosture.REGULAR, 20)); //setting static font




            TextField resultsField = new TextField();
            resultsField.setMaxWidth(40);


            TextField schema = new TextField();
            schema.setMaxWidth(120);

            TextField queryField = new TextField();
            queryField.setPrefWidth(300);
            queryField.setPromptText("SELECT Words FROM word ORDER BY Words*1;");




            resultsField.setOnKeyReleased(new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent eventType) {
                    // TODO Auto-generated method stub

                    System.out.println("Key Press is: " + resultsField.getText());

                    range = Integer.parseInt(resultsField.getText());

                    System.out.println("range integer is: " + range);


                    File poem = new File(htmlpoem);

                    try {
                        Fileconverter.textT(poem, range);
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }



                }


            });



            queryField.setOnKeyReleased(new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent eventType) {
                    // TODO Auto-generated method stub

                    SqlConnection.query = queryField.getText();
                    System.out.println("Query is: " + queryField.getText());
                    System.out.println("Query Field = " + SqlConnection.query);


                }


            });


            schema.setOnKeyReleased(new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent eventType) {
                    // TODO Auto-generated method stub

                    SqlConnection.schemaName = schema.getText();
                    System.out.println("Schema is: " + schema.getText());
                    System.out.println("Schema is set to = " + SqlConnection.schemaName);


                }


            });




            TextArea output = new TextArea();    //textfield 1
            output.setPrefSize(450, 400);


            TextArea outputDB = new TextArea();
            outputDB.setPrefSize(450, 400);

            Label lab = new Label();
            lab.setText("SQL Database Information");

            Label intruc1 = new Label();
            intruc1.setText("                                                     SQL Query Field                                                 Schema ");



            Button button1 = new Button();  //button1
            button1.setText("Execute Search");


            Button dbButton = new Button();  //button1
            dbButton.setText("Query Database");



            dbButton.setOnAction(new EventHandler<ActionEvent>() {


                @Override public void handle(ActionEvent e) {


                    try {
                        SqlConnection.print();
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    outputDB.clear();


                    try {
                        for (int i = 0; i < SqlConnection.arrayMaster.size(); i++) {


                            outputDB.appendText(SqlConnection.arrayMaster.get(i) + "\n");

                        }
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block


                        System.out.println("Contents failed to load in Text Area");


                    }


                }
            });


            button1.setOnAction(new EventHandler<ActionEvent>() {


                @Override public void handle(ActionEvent e) {



                    output.clear();


                    for (int i = 0; i < Fileconverter.wordArray.size(); i++) {


                        output.appendText(Fileconverter.wordArray.get(i) + "\n");




                    }


                }
            });


            Button insertDB = new Button();
            insertDB.setText("Insert Data to SQL Table");

            insertDB.setOnAction(new EventHandler<ActionEvent>() {


                @Override public void handle(ActionEvent e) {



                    for (int i = 0; i < Fileconverter.wordArray.size(); i ++) {

                        try {
                            SqlConnection.insert(Fileconverter.wordArray.get(i));
                        } catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }


                    }


                }
            });

            GridPane grid = new GridPane();

            grid.setLayoutY(100);
            grid.setLayoutX(50);

            grid.setVgap(4);
            grid.setHgap(10);
            grid.setPadding(new Insets(5, 5, 5, 5));
            grid.add(new Label("Enter Range of Results: "), 0, 0);
            grid.add(resultsField, 1, 0);
            grid.add(button1, 3, 0);
            grid.add(insertDB, 5, 0);


            grid.add(queryField, 6, 28);
            grid.add(schema, 7, 28);
            grid.add(dbButton, 7, 15);


            GridPane grid2 = new GridPane();

            grid2.setLayoutY(200);
            grid2.setLayoutX(50);


            grid2.setVgap(4);
            grid2.setHgap(10);
            grid2.setPadding(new Insets(5, 5, 5, 5));
            grid2.add(new Label("Top Words found in HTML Poem "), 0, 0);
            grid2.add(output, 0, 15);
            grid2.add(outputDB, 1, 15);
            grid2.add(lab, 1, 1);
            grid2.add(intruc1, 1, 2);




            Group label = (Group) scene.getRoot();


            root.getChildren().add(label1);  //creates a label


            label.getChildren().add(grid2);  //group for text label grid
            label.getChildren().add(grid);








            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {



        launch(args);



    }
}