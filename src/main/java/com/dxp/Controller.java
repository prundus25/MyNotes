package com.dxp;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.animation.Transition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Controller {
    ListManager manager = new ListManager();

    

    @FXML
    private TextField addNoteFld;

    @FXML
    private Label addSuccesful;

    @FXML
    private Label addError;

    @FXML
    private Label labelBg;

    @FXML
    public void addNote(ActionEvent e) {
        String elementName = addNoteFld.getText();
        FadeTransition fadeAdd = new FadeTransition(Duration.seconds(3), addSuccesful);
            fadeAdd.setFromValue(0.0);
            fadeAdd.setToValue(0.0);
            fadeAdd.setCycleCount(1);
            fadeAdd.setAutoReverse(true);
        FadeTransition fadeError = new FadeTransition(Duration.seconds(3), addError);
            fadeError.setFromValue(0.0);
            fadeError.setToValue(0.0);
            fadeError.setCycleCount(1);
            fadeError.setAutoReverse(true);
        FadeTransition addHide = new FadeTransition(Duration.seconds(3), addSuccesful);

        FadeTransition errorHide = new FadeTransition(Duration.seconds(3), addError);

        if(manager.createList(elementName)){
            SequentialTransition seqT = new SequentialTransition();
            seqT.getChildren().addAll(fadeAdd,errorHide);
            seqT.setCycleCount(Timeline.INDEFINITE);
            seqT.setAutoReverse(true);
            seqT.play();
        }else {
            Controller.playTransition(fadeError, fadeAdd);
        }
        addNoteFld.clear();
        System.out.println(manager.lists);
    }

    public static void showTransition(FadeTransition ft){
        ft.setFromValue(0.0);
        ft.setToValue(0.0);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }

    public static void hideTransition(FadeTransition ft){
        ft.setFromValue(0.0);
        ft.setToValue(0.0);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }

    public static void playTransition(FadeTransition ft1, FadeTransition ft2){
        Controller.hideTransition(ft2);
        Controller.showTransition(ft1);
    }
}
