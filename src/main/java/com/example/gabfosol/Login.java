package com.example.gabfosol;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Login {
    @FXML
    private TextField logText;
    @FXML
    private TextField logPass;
    public void logSubFun(){
        String handle = logText.getText();
        String pass = logPass.getText();
        System.out.println(handle + ":" + pass);
    }
}
