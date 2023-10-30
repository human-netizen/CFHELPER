package com.example.gabfosol;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class UnsolvedList implements Initializable {
    @FXML
    private ListView<String>myList;
    @FXML
    private String myField;

    String[] hehe = {"amar" , "naam" , "boka" , "dhali" , "dhali" , "is" , "boka" , "so" , "boka"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myList.getItems().addAll(hehe);
    }
}
