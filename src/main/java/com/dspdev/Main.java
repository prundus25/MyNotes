package com.dspdev;

import java.io.FileInputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.image.*;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        final Color customGrayBlue = Color.rgb(47, 49, 54);
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root, customGrayBlue);
        Image icon = new Image(new FileInputStream("src/main/java/com/dspdev/LIcon.png"));
    
        stage.getIcons().add(icon);
        stage.setTitle("List Manager (Alpha)");

        stage.setScene(scene);
        stage.show();
    }
}