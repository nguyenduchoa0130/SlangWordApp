package com.app;

import com.app.valid.ValidInput;
import com.constant.ErrorMessage;
import com.constant.OptionSlang;
import com.constant.Behavior;
import com.constant.UserOption;
import com.slang_word.SlangWord;
import com.slang_word.SlangWordLibrary;

import java.util.Scanner;

public class Application {

  public static void printMenu() {
    System.out.println("\n||================ MENU ================||");
    System.out.println("1. Find meaning by slang word");
    System.out.println("2. Find slang word based on definition");
    System.out.println("0. Exit application");
    System.out.println("||======================================||");
  }

  private static int selectOption() {
    System.out.printf(Behavior.INPUT_MESSAGE);
    return ValidInput.validationInput(Behavior.TYPE_OPTION);
  }

  public static void run() {
    chooseFuntion();
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
          chooseFuntion();
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

  private static void chooseFuntion() {

    SlangWordLibrary slangWordLibrary = new SlangWordLibrary();
    int option;
    System.out.println("====|| WELCOME TO SLANG WORD APPLICATION ||====");
    printMenu();
    option = selectOption();
    switch (option) {
      case OptionSlang.OPT_FIND_MEANING_BY_SLANG_WORD:
        System.out.println(findSlangByWord(slangWordLibrary));
        break;
      case OptionSlang.OPT_EXIT_APP: {
        System.out.println(Behavior.THANKS_MESSAGE);
        System.exit(0);
      }
      default: {
        System.out.println(ErrorMessage.ERROR_NOT_FIND_OPTION);
        chooseFuntion();
        break;
      }
    }
  }

  private static SlangWord findSlangByWord(SlangWordLibrary slangWordLibrary) {
    System.out.printf("Please enter slang word: ");
    Scanner sc = new Scanner(System.in);
    String langWord = sc.nextLine();
    return slangWordLibrary.findBySlangWord(langWord);
  }


}


