package com.dspdev;

public abstract class Maps {

  public static String mainMenu(ListManager manager, int option){
    return switch (option) {
      case 1 -> MainMenu.addCommand(manager);
      case 3 -> MainMenu.renameCommand(manager);
      case 4 -> MainMenu.removeCommand(manager);
      case 5 -> MainMenu.moveCommand(manager);
      default -> "Invalid option, try again.";
    };
  }

  public static String listMenu(ListManager manager, int option, int listIndex){
    return switch (option) {
      case 1 -> ListMenu.addCommand(manager, listIndex);
      case 2 -> ListMenu.renameCommand(manager, listIndex);
      case 3 -> ListMenu.removeCommand(manager, listIndex);
      case 4 -> ListMenu.moveCommand(manager, listIndex);
      default -> "Invalid option, try again.";
    };
  }
}
