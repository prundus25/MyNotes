package com.dxp;

import java.util.Scanner;
public abstract class ListMenu {
    static Scanner input = new Scanner(System.in);
    static String name = "***// LIST MENU \\\\***";
    static String elementName;
    static String elementNewName;
    static int itemIndex;
    static final String ENTERNUMBER = "Enter item number: ";
    static final String INDEXERROR = "No item found with that number.";
    static String options = "Options: 1 Add item | 2 Rename item | 3 Delete item | 4 Move item | 0 Exit to Main Menu\n";
    static final int OFFSET = 1;

    private ListMenu(){
        //private constructor to hide the implicit public one
    }
    
    public static String mainMessage(ListManager manager, int listIndex) {
        return name + "\n" + options + "\n" + manager.getListItems(listIndex) + "\nEnter option: ";
    }

    public static String addCommand(ListManager manager, int listIndex){
        System.out.print("Enter item name: ");
        elementName = input.nextLine();
        return manager.lists.get(listIndex).addItem(elementName);
    }

    public static String renameCommand(ListManager manager, int listIndex){
        itemIndex = ListMenu.enterIndex(manager, listIndex);
        if (itemIndex < 0){
            return INDEXERROR;
        }
        System.out.print("Enter a new name: ");
        elementNewName = input.nextLine();
        return manager.lists.get(listIndex).renameItem(itemIndex, elementNewName);
    }

    public static String removeCommand(ListManager manager, int listIndex){
        itemIndex = ListMenu.enterIndex(manager, listIndex);
        if (itemIndex < 0){
            return INDEXERROR;
        }
        return manager.lists.get(listIndex).removeItem(itemIndex);
    }
    
    public static String moveCommand(ListManager manager, int listIndex){
        int listSize = manager.lists.get(listIndex).items.size();
        if(listSize < 2){
            return "Not enough items for move operation";
        }

        itemIndex = ListMenu.enterIndex(manager, listIndex);
        if (itemIndex < 0){
            return INDEXERROR;
        }
        
        System.out.print("Enter the destination position for the item: ");
        int destinationIndex = input.nextInt() - OFFSET;
        input.nextLine();
        if(destinationIndex < 0 || destinationIndex >= listSize){
            return "The destination position doesn't exist for the total number of items in the list.";
        }
        return manager.lists.get(listIndex).moveItem(itemIndex, destinationIndex);
    }

    private static int enterIndex(ListManager manager, int listIndex){
        System.out.print(ENTERNUMBER);
        itemIndex = input.nextInt() - OFFSET;
        input.nextLine();
        int listSize = manager.lists.get(listIndex).items.size();
        return (itemIndex >= 0 && itemIndex < listSize) ? itemIndex : -1;
    }
}
