package com.example.gabfosol;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.application.Application;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.Desktop;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class UnsolvedList extends Application implements Initializable{
    @FXML
    private ListView<String>myList;
    @FXML
    private TextField ratingField;

    String[] links = new String[100];
    String[] names = new String[100];


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //myList.getItems().addAll(hehe);


    }
    public void addProblem(){
        ObservableList<String> items = FXCollections.observableArrayList();
        myList.setItems(items);
        myList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            // If the item is null or empty, set the graphic to null
                            setGraphic(null);
                        } else {
                            // Otherwise, create an HBox with a hyperlink, two buttons, and a text field
                            Hyperlink hyperlink = new Hyperlink();
                            Button button1 = new Button("Click me");
                            Button button2 = new Button("Another Button");
                            TextField textField = new TextField();
                            textField.setPrefHeight(80);

                            // Set the text and link of the hyperlink from the arrays
                            int index = getIndex();
                            if (index >= 0 && index < 100) {
                                hyperlink.setText(names[index]);
                                hyperlink.setOnAction(e -> getHostServices().showDocument(links[index]));
                            }

                            //Region spacer = new Region();

                            // Create a HBox and add the hyperlink, the spacer, and the other components as its children
                            GridPane gridPane = new GridPane();
                            gridPane.add(hyperlink, 0, 0);
                            gridPane.add(button1, 1, 0);
                            gridPane.add(button2, 3, 0);
                            gridPane.add(textField, 2, 0);

                            // Set some padding and spacing for the GridPane
                            gridPane.setPadding(new Insets(10));
                            gridPane.setHgap(10);

                            // Set the constraints for each node in the GridPane
                            // The hyperlink will span three columns and grow horizontally to fill the available space
                            //GridPane.setColumnSpan(hyperlink, 3);
                            //GridPane.setHgrow(hyperlink, Priority.ALWAYS);
                            GridPane.setHalignment(hyperlink , HPos.LEFT);
                            hyperlink.setPrefWidth(400);
                            // The first button will have a fixed width of 80 pixels and be aligned to the right edge of the GridPane
                            button1.setPrefWidth(80);
                            GridPane.setHalignment(button1, HPos.RIGHT);

                            // The second button will have a fixed width of 80 pixels and be aligned to the right edge of the GridPane


                            // The text field will have a fixed width of 120 pixels and be aligned to the right edge of the GridPane
                            textField.setPrefWidth(400);
                            GridPane.setHalignment(textField, HPos.RIGHT);
                            button2.setPrefWidth(80);
                            GridPane.setHalignment(button2, HPos.RIGHT);

                            // All the nodes will be aligned to the center vertically within the row
                            GridPane.setValignment(hyperlink, VPos.CENTER);
                            GridPane.setValignment(button1, VPos.CENTER);
                            GridPane.setValignment(button2, VPos.CENTER);
                            GridPane.setValignment(textField, VPos.CENTER);
                            setGraphic(gridPane);
                        }
                    }
                };
            }
        });

        JsonParse jp = new JsonParse();


        JSONArray ja = null;
        try {
            ja = jp.resObject("https://codeforces.com/api/problemset.problems").getJSONArray("problems");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        int idx = 0;
        for(int i = 0 ; i < ja.length() ; i++){
            JSONObject problem = null;
            try {
                problem = ja.getJSONObject(i);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            if(problem.has("rating")){
                try {

                    if(problem.getString("rating").equals(ratingField.getText())){

                        String base = "https://codeforces.com/problemset/problem/";
                        String link = base + problem.getString("contestId") + "/" + problem.getString("index");
                        links[idx] = link;
                        names[idx] = problem.getString("name");
                        System.out.println(link);
                        idx++;
                        if(idx >= 100)break;
                    }

                }
                catch (Exception e){
                    System.out.println("not found");
                }

            }
        }



        // Add some sample items to the observable list
        items.addAll(links);
    }



    @Override
    public void start(Stage stage) throws Exception {

    }
}
