package com.app;

import com.app.valid.ValidInput;
import com.constant.Behavior;
import com.constant.ErrorMessage;
import com.constant.OptionSlang;
import com.constant.UserOption;
import com.slang_word.GameAnswer;
import com.slang_word.SlangWord;
import com.slang_word.SlangWordLibrary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Application {
  private static SlangWordLibrary slangWordLibrary = new SlangWordLibrary();

  public static void printMenu() {
    System.out.println("\n||================ MENU ================||");
    System.out.println("1. Find meaning by slang word");
    System.out.println("2. Find slang word based on definition");
    System.out.println("3. Show slang word list in history");
    System.out.println("4. Create a new slang word");
    System.out.println("5. Edit a slang word");
    System.out.println("6. Delete a slang word");
    System.out.println("7. Reset slang word list");
    System.out.println("8. Random a slang word");
    System.out.println("9. Game random a slang word");
    System.out.println("10. Game Random a definition");
    System.out.println("0. Exit application");
    System.out.println("||======================================||");
  }

  private static int selectOption() {
    System.out.printf(Behavior.INPUT_MESSAGE);
    return ValidInput.validationInput(Behavior.TYPE_OPTION);
  }

  public static void run() {
    chooseFunction();
    do {
      System.out.println(" ");
      System.out.println("||==================================||");
      System.out.println("Do you wanna continue program?");
      System.out.println("Enter 1 to continue");
      System.out.println("Enter 0 to exit !!!");
      System.out.println("||==================================||");
      System.out.printf(Behavior.INPUT_MESSAGE);
      int optionNumber = ValidInput.validationInput(Behavior.USER_OPTION);
      switch (optionNumber) {
        case UserOption.OPTION_CONTINUE:
          chooseFunction();
          break;
        case UserOption.OPTION_EXIT:
          System.out.println(Behavior.THANKS_MESSAGE);
          return;
        default:
          System.out.println(ErrorMessage.ERROR_NOT_FIND_OPTION);
          break;
      }
    } while (true);
  }

  private static void chooseFunction() {
    int option;
    System.out.println("====|| WELCOME TO SLANG WORD APPLICATION ||====");
    printMenu();
    option = selectOption();
    switch (option) {
      case OptionSlang.OPT_FIND_MEANING_BY_SLANG_WORD:
        SlangWord slangWord = FunctionShare.findSlangByWord(slangWordLibrary, false);
        if (slangWord != null) {
          System.out.println(slangWord);
        } else {
          System.out.println(ErrorMessage.ERROR_NOT_FIND_SLANG_WORD);
        }
        break;
      case OptionSlang.OPT_FIND_SLANG_WORD_BASED_ON_MEANING:
        FunctionShare.findSlangByDefinition(slangWordLibrary);
        break;
      case OptionSlang.OPT_EXIT_APP:
        System.out.println(Behavior.THANKS_MESSAGE);
        System.exit(0);
      case OptionSlang.OPT_SHOW_LIST_HISTORY_SLANG_WORD:
        FunctionShare.showHistoryListSlangWord();
        break;
      case OptionSlang.OPT_CREATE_NEW_SLANG_WORD:
        FunctionShare.createSlangWord(slangWordLibrary);
        break;
      case OptionSlang.OPT_EDIT_SLANG_WORD:
        FunctionShare.editSlangWord(slangWordLibrary);
        break;
      case OptionSlang.OPT_DELETE_SLANG_WORD:
        FunctionShare.deleteSlangWord(slangWordLibrary);
        break;
      case OptionSlang.OPT_RESET_SLANG_WORD:
        slangWordLibrary = new SlangWordLibrary();
        System.out.println(Behavior.RESET_SLANG_WORD_LIST);
        break;
      case OptionSlang.OPT_RANDOM_SLANG_WORD:
        FunctionShare.randomSlangWord(slangWordLibrary);
        break;
      case OptionSlang.OPT_GAME_RANDOM_SLANG_WORD:
        FunctionShare.gameRandomSlangWord(slangWordLibrary, true);
        break;
      case OptionSlang.OPT_GAME_RANDOM_DEFINITION:
        FunctionShare.gameRandomSlangWord(slangWordLibrary, false);
        break;
      default: {
        System.out.println(ErrorMessage.ERROR_NOT_FIND_OPTION);
        chooseFunction();
        break;
      }
    }
  }

}
