package com.example.gabfosol;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.skin.TextFieldSkin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Signup implements Initializable {
    @FXML
    private Button submitBut;
    @FXML
    private ProgressBar myBar;
    @FXML
    private PasswordField signPass;
    private Timeline timeline = new Timeline();
    private boolean eyePressed = false;
    @FXML
    private Label passLabel;
    @FXML
    private Button passSet;
    private JsonParse jp = new JsonParse();
    @FXML
    private TextField signText;
    @FXML
    private Label notVerified;




    public void eye(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Signup.fxml"));

        } catch (IOException e) {
            System.out.println(e.toString());
        }
        System.out.println("HERE");
        TextFieldSkin skin = new TextFieldSkin(signPass) {
            // Override the maskText method to show or hide the text
            @Override
            protected String maskText(String txt) {
                // If the eye icon is pressed, return the original text
                if (eyePressed) {
                    return txt;
                }
                // Otherwise, return the masked text
                return super.maskText(txt);
            }
        };

        // Set the custom skin to the password field
        signPass.setSkin(skin);

        // Create an image for the eye icon
        Image image = new Image("file:icons/showPass.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(32);
        imageView.setFitWidth(32);

        StackPane root = new StackPane(signPass, imageView);

        // Adjust the alignment and margin of the image view
        StackPane.setAlignment(imageView, Pos.CENTER_RIGHT);
        StackPane.setMargin(imageView, new Insets(0, 10, 0, 0));



        // Add a mouse pressed handler to the image view
        imageView.setOnMousePressed(e -> {
            // Set the flag to true
            eyePressed = true;
            // Reset the text of the password field to trigger the maskText method
            signPass.setText(signPass.getText());
            // Move the caret to the end
            signPass.end();
        });

        // Add a mouse released handler to the image view
        imageView.setOnMouseReleased(e -> {
            // Set the flag to false
            eyePressed = false;
            // Reset the text of the password field to trigger the maskText method
            signPass.setText(signPass.getText());
            // Move the caret to the end
            signPass.end();
        });
        root.getChildren().add(new StackPane(signPass, imageView));

// Adjust the alignment and margin of the image view
        StackPane.setAlignment(imageView, Pos.CENTER_RIGHT);
        StackPane.setMargin(imageView, new Insets(0, 10, 0, 0));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        signPass.setVisible(false);
        passSet.setVisible(false);
        passLabel.setVisible(false);

    }
    public void signSubFun(){ //Signup submit
        if (timeline.getStatus() == Animation.Status.RUNNING) {
            timeline.stop();
        }
        // Reset the progress bar to zero
        myBar.setProgress(0);
        System.out.println("pressed");

        // Create a key frame that sets the progress to 1 in 90 seconds
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(10), event -> {
            String handle = signText.getText();
            JSONArray ja = jp.res("https://codeforces.com/api/user.status?handle="+handle+"&from=1&count=1");
            if(ja.length() == 0)System.out.println("lol");
            else{
                String verdict = null;
                try {
                    verdict = ja.getJSONObject(0).getString("verdict");
                } catch (JSONException e) {
                    System.out.println("No");
                }
                JSONObject problem = null;
                try {
                    problem = ja.getJSONObject(0).getJSONObject("problem");
                } catch (JSONException e) {
                    System.out.println();
                }
                String problemLink = null;
                try {
                    problemLink = problem.getString("contestId") + problem.getString("index");
                } catch (JSONException e) {
                    System.out.println();
                }
                String toMatch = "4ACOMPILATION_ERROR";
                System.out.println(problemLink + verdict);
                if((problemLink + verdict).equals(toMatch)){
                    notVerified.setText("Verified");
                    passLabel.setVisible(true);
                    passSet.setVisible(true);
                    signPass.setVisible(true);
                }
            }




        }, new KeyValue(myBar.progressProperty(), 1));
        // Add the key frame to the timeline
        timeline.getKeyFrames().add(keyFrame);

        // Start the animation
        timeline.play();
    }
    public void passSetFun(){ //Password submit
        String handle = signText.getText();
        String pass = signPass.getText();
        System.out.println(handle + ":" + pass);
    }
}
