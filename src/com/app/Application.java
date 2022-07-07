package com.app;

import com.slang_word.SlangWordLibrary;

import java.util.Scanner;

public class Application {
  public static void printMenu() {

    System.out.println("\n||================ MENU ================||");
    System.out.println("1. Find meaning of slang word");
    System.out.println("2. Find slang word based on definition");
    System.out.println("0. Exit application");
    System.out.println("||======================================||");
  }

  public static int selectOption() {
    System.out.printf("");
    Scanner scanner = new Scanner(System.in);
    return scanner.nextInt();
  }

  public static void run() {
    SlangWordLibrary slangWordLibrary = new SlangWordLibrary();
    int option;
    System.out.println("====|| WELCOME TO SLANG WORD APPLICATION ||====");
    while (true) {
      printMenu();
      option = selectOption();
      switch (option){
        case ApplicationOption.OPT_EXIT_APP: {
          System.out.println("See you!!");
          return;
        }
        default: {
          System.out.println("Please enter a existed option!!");
          break;
        }
      }
    }
  }
}
