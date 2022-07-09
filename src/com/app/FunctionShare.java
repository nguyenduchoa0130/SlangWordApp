package com.app;

import com.app.valid.ValidInput;
import com.constant.Behavior;
import com.constant.ErrorMessage;
import com.constant.UserOption;
import com.slang_word.GameAnswer;
import com.slang_word.SlangWord;
import com.slang_word.SlangWordLibrary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class FunctionShare {
  private final static HashMap<String, String> hashMapHistory = new HashMap<>();
  private static String createSlangWord;

  static void findSlangByDefinition(SlangWordLibrary slangWordLibrary) {
    System.out.printf(Behavior.INPUT_DEFINITION);
    Scanner sc = new Scanner(System.in);
    String langWord = sc.nextLine();
    List<SlangWord> listSlangWords = slangWordLibrary.searchByDefinition(langWord);
    System.out.println("List of Slang word: ");
    if (!listSlangWords.isEmpty()) {
      listSlangWords.forEach(System.out::println);
    } else {
      System.out.println(ErrorMessage.ERROR_NOT_FIND_SLANG_WORD_IN_LIST);
    }
  }

  static void showHistoryListSlangWord() {
    if (!hashMapHistory.isEmpty()) {
      System.out.println(Behavior.SHOW_HISTORY_LIST_SLANG_WORD);
      for (HashMap.Entry<String, String> entry : hashMapHistory.entrySet()) {
        System.out.println("[" + entry.getKey() + "] : " + entry.getValue());
      }
    } else {
      System.out.println(ErrorMessage.ERROR_NOT_FIND_LIST_HISTORY_SLANG_WORD);
    }
  }

  static SlangWord findSlangByWord(SlangWordLibrary slangWordLibrary, Boolean isEditLibrary) {
    System.out.printf(Behavior.INPUT_SLANG_WORD);
    Scanner sc = new Scanner(System.in);
    String slangWord = sc.nextLine();
    createSlangWord = slangWord;
    if (!isEditLibrary) {
      String pattern = "yyyy-MM-dd HH:mm:ss";
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
      String date = simpleDateFormat.format(new Date());
      hashMapHistory.put(date, slangWord);
    }
    return slangWordLibrary.findBySlangWord(slangWord);
  }

  static void createSlangWord(SlangWordLibrary slangWordLibrary) {

    SlangWord slangWord = findSlangByWord(slangWordLibrary, true);
    if (slangWord == null) {
      slangWord = new SlangWord();
      slangWord.setSlangWord(createSlangWord);
      System.out.printf(Behavior.CREATE_DEFINITION);
      Scanner sc2 = new Scanner(System.in);
      String definition = sc2.nextLine();
      slangWord.setMeaning(definition);
      slangWordLibrary.creatSlangWord(slangWord);
      System.out.println(Behavior.CREATE_SLANG_WORD_SUCCESS);
    } else {
      System.out.println(ErrorMessage.ERROR_SLANG_EXIST);
    }
  }

  static void editSlangWord(SlangWordLibrary slangWordLibrary) {
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

  static void deleteSlangWord(SlangWordLibrary slangWordLibrary) {
    SlangWord slangWord = FunctionShare.findSlangByWord(slangWordLibrary, true);
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

  static void randomSlangWord(SlangWordLibrary slangWordLibrary) {
    SlangWord slangWord = slangWordLibrary.randomSlangWord();
    System.out.println(slangWord);
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
    if (!keyWord) {
      System.out.println("Question: " + gameAnswer.getSlangWord() + " ?");

    } else {
      System.out.println("Question: " + gameAnswer.getMeaning() + " ?");

    }
    System.out.println(" ");
    System.out.printf(Behavior.INPUT_ANSWER);
    int optionNumber = ValidInput.validationInput(Behavior.ANSWER_OPTION);
    boolean result = switch (optionNumber) {
      case UserOption.ANSWER_A -> ValidInput.compareAnswer(gameAnswer.getAnswerA(), gameAnswer, keyWord);
      case UserOption.ANSWER_B -> ValidInput.compareAnswer(gameAnswer.getAnswerB(), gameAnswer, keyWord);
      case UserOption.ANSWER_C -> ValidInput.compareAnswer(gameAnswer.getAnswerC(), gameAnswer, keyWord);
      case UserOption.ANSWER_D -> ValidInput.compareAnswer(gameAnswer.getAnswerD(), gameAnswer, keyWord);
      default -> false;
    };
    if (result) {
      System.out.println(Behavior.ANSWER_VALID);
    } else {
      if (!keyWord) {
        System.out.println(Behavior.ANSWER_INVALID + " and valid answer is: [" + gameAnswer.getMeaning() + "]");

      } else {
        System.out.println(Behavior.ANSWER_INVALID + " and valid answer is: [" + gameAnswer.getSlangWord() + "]");

      }
    }
  }

  static void gameRandomSlangWord(SlangWordLibrary slangWordLibrary, boolean keyWord) {
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
}
