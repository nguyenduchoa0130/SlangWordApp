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
  private static HashMap<String, String> hashMapHistory = new HashMap<>();
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
        SlangWord slangWord = findSlangByWord(slangWordLibrary, false);
        if (slangWord != null) {
          System.out.println(slangWord);
        } else {
          System.out.println(ErrorMessage.ERROR_NOT_FIND_SLANG_WORD);
        }
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
      case OptionSlang.OPT_CREATE_NEW_SLANG_WORD:
        createSlangWord(slangWordLibrary);
        break;
      case OptionSlang.OPT_EDIT_SLANG_WORD:
        editSlangWord(slangWordLibrary);
        break;
      case OptionSlang.OPT_DELETE_SLANG_WORD:
        deleteSlangWord(slangWordLibrary);
        break;
      case OptionSlang.OPT_RESET_SLANG_WORD:
        resetListSlangWords();
        break;
      case OptionSlang.OPT_RANDOM_SLANG_WORD:
        randomSlangWord();
        break;
      case OptionSlang.OPT_GAME_RANDOM_SLANG_WORD:
        gameRandomSlangWord(slangWordLibrary, true);
        break;
      case OptionSlang.OPT_GAME_RANDOM_DEFINITION:
        gameRandomSlangWord(slangWordLibrary, false);
        break;
      default: {
        System.out.println(ErrorMessage.ERROR_NOT_FIND_OPTION);
        chooseFunction();
        break;
      }
    }
  }

  private static void startGame(SlangWordLibrary slangWordLibrary, Boolean keyWord) {

    GameAnswer gameAnswer = slangWordLibrary.gameRandom(keyWord);
    System.out.println(" ");
    System.out.println("===============================================");
    System.out.println("======================|| WELCOME TO GAME ||======================");
    System.out.println("1. " + gameAnswer.getAnswerA());
    System.out.println("2. " + gameAnswer.getAnswerB());
    System.out.println("3. " + gameAnswer.getAnswerC());
    System.out.println("4. " + gameAnswer.getAnswerD());
    System.out.println("===============================================");

    System.out.printf(Behavior.INPUT_ANSWER);
    int optionNumber = ValidInput.validationInput(Behavior.ANSWER_OPTION);
    boolean result = false;
    switch (optionNumber) {
      case UserOption.ANSWER_A:
        result = ValidInput.compareAnswer(gameAnswer.getAnswerA(), gameAnswer);
        break;
      case UserOption.ANSWER_B:
        result = ValidInput.compareAnswer(gameAnswer.getAnswerB(), gameAnswer);
        break;
      case UserOption.ANSWER_C:
        result = ValidInput.compareAnswer(gameAnswer.getAnswerC(), gameAnswer);
        break;
      case UserOption.ANSWER_D:
        result = ValidInput.compareAnswer(gameAnswer.getAnswerD(), gameAnswer);
        break;
    }

    if (result) {
      System.out.println(Behavior.ANSWER_VALID);
    } else {
      System.out.println(Behavior.ANSWER_INVALID + " and valid answer is: [" + gameAnswer.getValidAnswer() + "]");
    }
  }

  private static void gameRandomSlangWord(SlangWordLibrary slangWordLibrary, boolean keyWord) {
    startGame(slangWordLibrary, keyWord);
    do {
      System.out.println(" ");
      System.out.println("||==================================||");
      System.out.println("Do you wanna continue game?");
      System.out.println("Enter 1 to continue");
      System.out.println("Enter 0 to exit !!!");
      System.out.println("||==================================||");
      System.out.printf(Behavior.INPUT_MESSAGE);
      int optionNumber = ValidInput.validationInput(Behavior.USER_OPTION);
      switch (optionNumber) {
        case UserOption.OPTION_CONTINUE:
          startGame(slangWordLibrary, keyWord);
          break;
        case UserOption.OPTION_EXIT:
          return;
        default:
          System.out.println(ErrorMessage.ERROR_NOT_FIND_OPTION);
          break;
      }
    } while (true);
  }

  private static void randomSlangWord() {
    SlangWord slangWord = slangWordLibrary.randomSlangWord();
    System.out.println(slangWord);
  }

  private static void resetListSlangWords() {
    slangWordLibrary = new SlangWordLibrary();
  }

  private static void deleteSlangWord(SlangWordLibrary slangWordLibrary) {
    SlangWord slangWord = findSlangByWord(slangWordLibrary, true);
    if (slangWord != null) {
      System.out.println("====||  Are you sure you want delete slang word " + slangWord.getSlangWord() + " ||====");
      System.out.println("Enter 1 to continue");
      System.out.println("Enter 0 to exit !!!");
      System.out.println("==============================================");
      int optionNumber = ValidInput.validationInput(Behavior.USER_OPTION);

      switch (optionNumber) {
        case UserOption.OPTION_CONTINUE:
          slangWordLibrary.deleteSlangWord(slangWord.getSlangWord());
          System.out.println(Behavior.DELETE_SLANG_WORD_SUCCESS);
          break;
        case UserOption.OPTION_EXIT:
          break;
      }


    } else {
      System.out.println(ErrorMessage.ERROR_NOT_FIND_SLANG_WORD);
    }
  }

  private static void editSlangWord(SlangWordLibrary slangWordLibrary) {
    SlangWord slangWord = findSlangByWord(slangWordLibrary, true);
    if (slangWord != null) {
      System.out.printf(Behavior.CREATE_DEFINITION);
      Scanner sc2 = new Scanner(System.in);
      String definition = sc2.nextLine();
      slangWord.setMeaning(definition);
      slangWordLibrary.editSlangWord(slangWord);
      System.out.println(Behavior.EDIT_SLANG_WORD_SUCCESS);
    } else {
      System.out.println(ErrorMessage.ERROR_NOT_FIND_SLANG_WORD);
    }
  }

  private static void createSlangWord(SlangWordLibrary slangWordLibrary) {
    System.out.printf(Behavior.CREATE_SLANG_WORD);
    Scanner sc = new Scanner(System.in);
    String keyWord = sc.nextLine();
    System.out.printf(Behavior.CREATE_DEFINITION);
    Scanner sc2 = new Scanner(System.in);
    String defintion = sc2.nextLine();
    SlangWord slangWord = new SlangWord();
    slangWord.setSlangWord(keyWord);
    slangWord.setMeaning(defintion);
    boolean resultCreate = slangWordLibrary.creatSlangWord(slangWord);
    if (resultCreate) {
      System.out.println(Behavior.CREATE_SLANG_WORD_SUCCESS);
    } else {
      System.out.println(ErrorMessage.ERROR_SLANG_EXIST);
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
