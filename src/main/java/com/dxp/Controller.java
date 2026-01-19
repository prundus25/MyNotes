package com.dxp;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller {
    static final double BASE_DURATION = 0.15;
    @FXML
    private TextField addNoteFld;

    @FXML
    private Label addMessage;

    @FXML
    private Button exitBtn;
    public void exitApp(){
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Label dataMessage;

    @FXML
    private Pane notifPane;
     
    public void displayDataMsg(){
        if (Main.dataImp){
            dataMessage.setText("Data loaded successfully!");
            notifPane.setStyle("-fx-background-color: #416B47; -fx-background-radius: 15");
        } 
        else {
            dataMessage.setText("Error. No data could be loaded.");
            notifPane.setStyle("-fx-background-color: #8A4242; -fx-background-radius: 15");
        }
        FadeTransition fadeDataMsg = new FadeTransition(Duration.seconds(BASE_DURATION), dataMessage);
            fadeDataMsg.setInterpolator( Interpolator.EASE_BOTH);
            fadeDataMsg.setFromValue(0.0);
            fadeDataMsg.setToValue(1.0);
            fadeDataMsg.setCycleCount(1);
            fadeDataMsg.setDelay(Duration.seconds(BASE_DURATION*3.4));
            fadeDataMsg.setOnFinished(e -> hideDataMsg());
            fadeDataMsg.play();
    }

    public void hideDataMsg(){
        FadeTransition fadeDataMsg = new FadeTransition(Duration.seconds(BASE_DURATION), dataMessage);
            fadeDataMsg.setInterpolator( Interpolator.EASE_BOTH);
            fadeDataMsg.setFromValue(1.0);
            fadeDataMsg.setToValue(0.0);
            fadeDataMsg.setCycleCount(1);
            fadeDataMsg.setDelay(Duration.seconds(2));
            fadeDataMsg.play();
    }         

    public void displayNotif(){
        if (Main.dataImp) dataMessage.setText("Data loaded successfully!");
        else dataMessage.setText("Error. No data could be loaded.");


        KeyValue paneW = new KeyValue(notifPane.prefWidthProperty(), 267);
        KeyFrame paneWF = new KeyFrame(Duration.seconds(BASE_DURATION), paneW);

        KeyValue paneX = new KeyValue(notifPane.translateXProperty(), -267);
        KeyFrame paneXF = new KeyFrame(Duration.seconds(BASE_DURATION), paneX);

        Timeline showNotif = new Timeline(paneWF, paneXF);
        showNotif.setDelay(Duration.seconds(BASE_DURATION*2));
        showNotif.setOnFinished(e -> {
            notifPane.setPrefWidth(0);
            notifPane.setTranslateX(0);
            hideNotif();
        });
        showNotif.play();
    }

    public void hideNotif(){
        KeyValue paneW = new KeyValue(notifPane.prefWidthProperty(), 267, Interpolator.EASE_BOTH);
        KeyFrame paneWF = new KeyFrame(Duration.seconds(BASE_DURATION), paneW);

        KeyValue paneX = new KeyValue(notifPane.translateXProperty(), -267, Interpolator.EASE_BOTH);
        KeyFrame paneXF = new KeyFrame(Duration.seconds(BASE_DURATION), paneX);

        Timeline showNotif = new Timeline(paneWF, paneXF);
        showNotif.setRate(-1);
        showNotif.setDelay(Duration.seconds(2.4));
        showNotif.play();
    }

    @FXML
    public void addNote(ActionEvent e) {
        String elementName = addNoteFld.getText();
        FadeTransition fadeAdd = new FadeTransition(Duration.seconds(3), addMessage);
            fadeAdd.setFromValue(1.0);
            fadeAdd.setToValue(0.0);
            fadeAdd.setCycleCount(1);
            fadeAdd.setAutoReverse(true);

        if(Main.manager2.createList(elementName)){
            addMessage.setText("Note succesfully created!");
            fadeAdd.play();
        }else {
            addMessage.setText("Note already exists!");
            fadeAdd.play();
        }
        addNoteFld.clear();
        System.out.println(Main.manager2.lists);
    }
}
