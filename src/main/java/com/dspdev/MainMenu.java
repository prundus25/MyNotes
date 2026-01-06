package com.dspdev;

import java.util.Scanner;
public abstract class MainMenu {
    static Scanner input = new Scanner(System.in);
    static String name = "***// MAIN MENU \\\\***";
    static String elementName;
    static String elementNewName;
    static int index;
    static int destinationIndex;
    static final String ENTERNUMBER = "Enter list number: ";
    static final String INDEXERROR = "No list found with that number.";
    static String options = "Options: 1 Create list | 2 Consult list | 3 Rename list | 4 Delete list | 5 Move list | 0 Exit App\n";
    static final int OFFSET = 1;

    public static String mainMessage(ListManager manager) {
        return name + "\n" + options + "\n" + manager.getLists() + "\nEnter option: ";
    }

    public static String addCommand(ListManager manager){
        System.out.print("Enter list name: ");
        elementName = input.nextLine();
        return manager.createList(elementName);
    }

    public static String renameCommand(ListManager manager){
        index = MainMenu.enterIndex(manager);
        if (index < 0){
            return INDEXERROR;
        }
        System.out.print("Enter the new name for the list: ");
        elementNewName = input.nextLine();
        return manager.renameList(index, elementNewName);
    }

    public static String removeCommand(ListManager manager){
        index = MainMenu.enterIndex(manager);
        if (index < 0){
            return INDEXERROR;
        }
        return manager.removeList(index);
    }
    
    public static String moveCommand(ListManager manager){
        int nrOfLists = manager.lists.size();
        if(nrOfLists < 2){
            return "Not enough lists for move operation";
        }

        index = MainMenu.enterIndex(manager);
        if (index < 0){
            return INDEXERROR;
        }

        System.out.print("Enter the destination position for the list: ");
        destinationIndex = input.nextInt() - OFFSET;
        input.nextLine();

        if(destinationIndex < 0 || destinationIndex >= nrOfLists){
            return "The destination position doesn't exist for the total number of lists.";
        }
        return manager.moveList(index, destinationIndex);
    }

    public static int enterIndex(ListManager manager){
        System.out.print(ENTERNUMBER);
        index = input.nextInt() - OFFSET;
        input.nextLine();
        int nrOfLists = manager.lists.size();
        return (index >= 0 && index < nrOfLists) ? index : -1;
    }
}

