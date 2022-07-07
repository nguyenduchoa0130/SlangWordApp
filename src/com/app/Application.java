package com.app;

import com.slang_word.SlangWordLibrary;

import java.util.Scanner;

public class Application {
  public static void printMenu() {
    System.out.println("\n Menu:");
    System.out.println("1. Look up a slang word.");
    System.out.println("2. Print all slang words");
    System.out.println("0. Exit application");
  }
  public static void run() {
    SlangWordLibrary slangWordLibrary = new SlangWordLibrary();
    int option;
    System.out.println("====|| WELCOME TO SLANG WORD APPLICATION ||====");
    while (true) {
      printMenu();
    }
  }
}
