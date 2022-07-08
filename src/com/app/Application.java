package com.app;

import com.constant.OptionSlang;
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
    System.out.printf("Enter your option: ");
    Scanner scanner = new Scanner(System.in);
    return scanner.nextInt();
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

      System.out.printf("Please enter your option: ");
      Scanner scanner = new Scanner(System.in);
      int optionNumber = scanner.nextInt();
      switch (optionNumber) {
        case UserOption.OPTION_CONTINUE:
          chooseFuntion();
          break;
        case UserOption.OPTION_EXIT:
          System.out.println("============THANK YOU && SEE YOU AGAIN============");
          return ;
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
        SlangWord result = findSlangByWord(slangWordLibrary);
        if(result != null) {
          System.out.println(result);
        }else {
          System.out.println("No slang word found!");
        }
        break;
      case OptionSlang.OPT_EXIT_APP: {
        System.out.println("See you!!");
        return;
      }
      default: {
        System.out.println("Please enter a existed option!!");
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

