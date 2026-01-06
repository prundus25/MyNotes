//create list object, rename, delete
//import java.util.List;
package com.dspdev;

import java.util.ArrayList;

public class ListManager {
   ArrayList<MyList> lists = new ArrayList<>();

   public ListManager() {
   }

   public String createList(String name){
      if(isNameValid(name)){lists.add(new MyList(name)); return "List \"" + name + "\" created.";}
      else return "List already exists";
   }

   public String createList(String name, int index){
      if(isNameValid(name)){lists.add(index, new MyList(name)); return "List \"" + name + "\" created.";}
      else return "List already exists";
   }

   public String moveList(int index , int destinationIndex){
      String listName = lists.get(index).toString();
      MyList listToMove = lists.get(index);
      int OFFSET = 1;

      if(destinationIndex > index){
         lists.add(destinationIndex + OFFSET, listToMove);
         lists.remove(index);
      }else {
         lists.add(destinationIndex, listToMove);
         lists.remove(index + OFFSET);
      }
      return "Moved list \"" + listName + "\" to position "+ (destinationIndex+OFFSET) + ".";
   }

   public String getLists(){
      int n = 1;
      String stringOfLists;
      if(lists.isEmpty()){
         stringOfLists="You currently have no lists.";
      }else{
         stringOfLists="Your lists are:\n***************\n";
         for(MyList list : lists){
            stringOfLists += (n++) + ". " + list + "\n";
         }
      }
      return stringOfLists;
   }

   public String getListItems(int index){
      String listName = lists.get(index).toString();
      return (lists.get(index).items.isEmpty()) ? "The list \"" + listName + "\" is empty." : "Items from list \"" + listName + "\":\n" + lists.get(index).itemString();
   }

   public boolean isNameValid(String name){
      return !lists.toString().contains(name);
   }

   public String renameList(int index, String newName){
      String listName = lists.get(index).toString();
      lists.get(index).renameList(newName);
      return "Renamed list \"" + listName + "\" to \"" + newName + "\".";
   }

   public String removeList(int index){
      String listName = lists.get(index).toString();
      lists.remove(index);
      return "Removed list \"" + listName + "\".";
   }
}
