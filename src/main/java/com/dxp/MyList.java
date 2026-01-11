package com.dxp;

import java.util.ArrayList;

public class MyList {
    private String name;
    ArrayList<String> items = new ArrayList<>();

    // Apparently? No-arg constructor needed for JSON deserialization ?
    public MyList(){
        // Jackson requires a public no-arg constructor
    }

    public MyList(String name) {
        this.name=name;
    }

    public void renameList(String newName){
        this.name = newName;
    }
  
    @Override
    public String toString(){
        return this.name;
    }
    
    public String addItem(String itemName){
        this.items.add(itemName);
        return "Added item \"" + itemName + "\".";
    }

    public void addItem(String itemName, int index){
        this.items.add(index, itemName);
    }

    public String renameItem(int index, String newName){
        String oldName = this.items.get(index);
        this.items.remove(index);
        this.items.add(index, newName);
        return "Renamed item \"" + oldName + "\" to \"" + newName + "\".";
    }

    public String moveItem(int index , int destinationIndex){
        String itemToMove = this.items.get(index);
        int OFFSET = 1;

        if(destinationIndex > index){
            this.items.add(destinationIndex + OFFSET, itemToMove);
            this.items.remove(index);
        }else {
            this.items.add(destinationIndex, itemToMove);
            this.items.remove(index + OFFSET);
        }
        return "Moved item \"" + itemToMove + "\" to position "+ (destinationIndex+OFFSET) + ".";
    }

    public String removeItem(int index){
        String itemToRemove = this.items.get(index);
        this.items.remove(index);
        return "Removed item \"" + itemToRemove + "\".";
    }

    public String itemString(){
        int n = 1;
        String stringOfItems = "";
        for(String item : items){
            stringOfItems+= (n++) + ". " + item + "\n";
        }
        return stringOfItems;
    }
}
