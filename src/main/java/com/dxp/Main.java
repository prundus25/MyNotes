package com.dxp;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    static ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    public static ListManager manager2 = new ListManager();
    final Color customGrayBlue = Color.rgb(47, 49, 54);
    static boolean dataImp = false;

    public static void main(String[] args) {
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root, customGrayBlue);
        Image icon = new Image(new FileInputStream("src/main/java/com/dxp/LIcon.png"));
    
        stage.getIcons().add(icon);
        stage.setTitle("MyNotes");

        stage.setScene(scene);
        stage.show();
        importData();
        System.out.println(Main.manager2.lists);
<<<<<<< HEAD
        stage.setOnHiding( event -> saveData() );
=======
        stage.setOnHiding( event -> {saveData();} );
>>>>>>> 40abac77130277d10ac9d62dfd5cac2453862830
    }

    public void importData(){
        try {
            manager2.lists = mapper.readValue(new File("target/generated-sources/dataGUI.json"), mapper.getTypeFactory().constructCollectionType(ArrayList.class, MyList.class));
            System.out.println("Data loaded successfully.");
            dataImp = true;
        } catch (Exception e) {
            System.err.println("Error. No data could be loaded: ");
            e.printStackTrace();
            dataImp = false;
        }
    }

    public void saveData(){
        try {
            mapper.writeValue(new File("target/generated-sources/dataGUI.json"), manager2.lists);
            System.out.println("Data saved successfully.");
        } catch (Exception e) {
            System.err.println("Couldn't save data.");
            e.printStackTrace();
        }
    }
}