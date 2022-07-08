package com.app;

import com.app.valid.ValidInput;
import com.constant.ErrorMessage;
import com.constant.OptionSlang;
import com.constant.Behavior;
import com.constant.UserOption;
import com.slang_word.SlangWord;
import com.slang_word.SlangWordLibrary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Application {
  private static HashMap<String, String> hashMapHistory = new HashMap<>();

  public static void printMenu() {
    System.out.println("\n||================ MENU ================||");
    System.out.println("1. Find meaning by slang word");
    System.out.println("2. Find slang word based on definition");
    System.out.println("3. Show slang word list in history");
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
        System.out.println(findSlangByWord(slangWordLibrary, false));
        break;
      case OptionSlang.OPT_FIND_SLANG_WORD_BASED_ON_MEANING:
        findSlangByDefinition(slangWordLibrary);
        break;
      case OptionSlang.OPT_EXIT_APP:
        System.out.println(Behavior.THANKS_MESSAGE);
        System.exit(0);
      case OptionSlang.OPT_SHOW_LIST_HISTORY_SLANG_WORD:
        showHistoryListSlangWord();
        break;
      default: {
        System.out.println(ErrorMessage.ERROR_NOT_FIND_OPTION);
        chooseFuntion();
        break;
      }
    }
  }

  private static void showHistoryListSlangWord() {
    if (!hashMapHistory.isEmpty() && hashMapHistory.size() > 0) {
      System.out.println(Behavior.SHOW_HISTORY_LIST_SLANG_WORD);
      for (HashMap.Entry<String, String> entry : hashMapHistory.entrySet()) {
        System.out.println("[" + entry.getKey() + "] : " + entry.getValue());
      }
    } else {
      System.out.println(ErrorMessage.ERROR_NOT_FIND_LIST_HISTORY_SLANG_WORD);
    }
  }

  private static void findSlangByDefinition(SlangWordLibrary slangWordLibrary) {
    System.out.printf(Behavior.INPUT_DEFINITION);
    Scanner sc = new Scanner(System.in);
    String langWord = sc.nextLine();
    List<SlangWord> listSlangWords = slangWordLibrary.searchByDefinition(langWord);
    System.out.println("List of Slang word: ");
    if (!listSlangWords.isEmpty() && listSlangWords.size() > 0) {
      listSlangWords.stream().forEach(System.out::println);
    } else {
      System.out.println(ErrorMessage.ERROR_NOT_FIND_SLANG_WORD_IN_LIST);
    }

  }

  private static SlangWord findSlangByWord(SlangWordLibrary slangWordLibrary, Boolean isEditLibrary) {
    System.out.printf(Behavior.INPUT_SLANG_WORD);
    Scanner sc = new Scanner(System.in);
    String langWord = sc.nextLine();

    if (!isEditLibrary) {
      String pattern = "yyyy-MM-dd HH:mm:ss";
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
      String date = simpleDateFormat.format(new Date());
      hashMapHistory.put(date, langWord);

    }

    return slangWordLibrary.findBySlangWord(langWord);
  }


}

