package com.example.gabfosol;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class StopwatchController implements Initializable {

    @FXML
    private Label timeLabel;

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    private Button resumeButton;

    @FXML
    private Button resetButton;

    private Timeline timeline;
    private int hours = 0;
    private int minutes = 0;
    private int seconds = 0;
    private boolean running = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        timeline = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeTime();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeLabel.setText("00:00:00");
    }

    @FXML
    private void start(ActionEvent event) {
        if (!running) {
            timeline.play();
            running = true;
        }
    }

    @FXML
    private void stop(ActionEvent event) {
        if (running) {
            timeline.pause();
            running = false;
        }
    }

    @FXML
    private void resume(ActionEvent event) {
        start(event);
    }

    @FXML
    private void reset(ActionEvent event) {
        timeline.stop();
        running = false;
        hours = 0;
        minutes = 0;
        seconds = 0;
        timeLabel.setText("00:00:00");
    }

    private void changeTime() {
        if (running) {
            seconds++;
            if (seconds == 60) {
                seconds = 0;
                minutes++;
            }
            if (minutes == 60) {
                minutes = 0;
                hours++;
            }
            timeLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        }
    }
    public void display(){
        System.out.println(hours);
        System.out.println(minutes);
        System.out.println(seconds);
    }
    public String getHours(){
        return String.valueOf(hours);
    }
    public String getMinutes(){
        return String.valueOf(minutes);
    }
    public String getSeconds(){
        return String.valueOf(seconds);
    }

}
