package com.dxp;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MainList {
    public static void main(String[] args){
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        Scanner input = new Scanner(System.in);
        ListManager manager = new ListManager();

        int option;
        int option2;
        final String SEPARATOR2 = "*******************************";
        final String SPACING = "\n\n\n";
        int listIndex=0;
        
        try {
            manager.lists = objectMapper.readValue(new File("target/generated-sources/data.json"), objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, MyList.class));
            System.out.println("Data loaded successfully.");
        } catch (Exception e) {
            System.err.println("Error. No data could be loaded: ");
            e.printStackTrace();
        }
        
        boolean running = true;
        while (running) {
            System.out.print(MainMenu.mainMessage(manager));
            option = input.nextInt();
            input.nextLine();
            if (option == 0) {
                System.out.println("Saving and exiting...");
                try {
                   objectMapper.writeValue(new File("target/generated-sources/data.json"), manager.lists);
                   System.out.println("Data saved successfully.");
                } catch (Exception e) {
                    System.err.println("Couldn't save data.");
                    e.printStackTrace();
                }
                running = false;
            }
            else if (option == 2){
                System.out.println(SEPARATOR2);
                listIndex = MainMenu.enterIndex(manager);
                if (listIndex < 0) System.out.println("No list found with that number.");
                System.out.println(SEPARATOR2 + SPACING);
                while (true && listIndex >= 0){
                    System.out.print(ListMenu.mainMessage(manager, listIndex));
                    option2 = input.nextInt();
                    input.nextLine();
                    System.out.println(SEPARATOR2);
                    if (option2 == 0){System.out.println("RETURNING TO MAIN MENU..." + SPACING); break;}
                    else {
                        System.out.println(Maps.listMenu(manager, option2, listIndex));
                    }
                    System.out.println(SEPARATOR2 + SPACING);
                }
            }else{
                System.out.println(SEPARATOR2);
                System.out.println(Maps.mainMenu(manager, option));
                System.out.println(SEPARATOR2 + SPACING);
            }
        }
    }
}
