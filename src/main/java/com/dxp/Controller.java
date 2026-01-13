package com.dxp;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private TextField addNoteFld;

    @FXML
    private Label addMessage;

    @FXML
    private Label dataMessage;

    @FXML
    private Button exitBtn;
    public void exitApp(){
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
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
