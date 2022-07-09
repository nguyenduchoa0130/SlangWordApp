package com.app.valid;

import com.constant.Behavior;
import com.constant.ErrorMessage;
import com.constant.OptionSlang;
import com.constant.UserOption;
import com.slang_word.GameAnswer;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ValidInput {
  private static Set<String> items = new HashSet<>();
  private static boolean checkNumber(String strNum) {
    if (strNum == null) {
      return false;
    }
    try {
      double d = Double.parseDouble(strNum);
    } catch (NumberFormatException nfe) {
      return false;
    }
    return true;
  }
  private static int checkInputNumber() {
    Scanner scanner = new Scanner(System.in);
    String inputString = scanner.nextLine();

    while (!checkNumber(inputString)) {
      System.out.println(ErrorMessage.ERROR_INPUT_NUMBER);
      System.out.print(Behavior.INPUT_AGAIN);
      inputString = scanner.nextLine();
    }

    return Integer.parseInt(inputString);
  }

  public static int validationInput(int typeCheck) {
    int result = checkInputNumber();
    switch (typeCheck) {
      case Behavior.TYPE_OPTION:
        while (!OptionSlang.LIST_OPTION.contains(result)) {
          System.out.println(ErrorMessage.ERROR_NOT_FIND_OPTION);
          System.out.print(Behavior.INPUT_AGAIN);
          int inputAgain = checkInputNumber();
          result = inputAgain;
        }
        break;
      case Behavior.USER_OPTION:
        while (!UserOption.LIST_SELECTIONS_OF_USER.contains(result)) {
          System.out.println(ErrorMessage.ERROR_NOT_FIND_OPTION);
          System.out.print(Behavior.INPUT_AGAIN);
          int inputAgain = checkInputNumber();
          result = inputAgain;
        }
        break;
      case Behavior.ANSWER_OPTION:
        while (!UserOption.LIST_ANSERS.contains(result)) {
          System.out.println(ErrorMessage.ERROR_NOT_FIND_OPTION);
          System.out.print(Behavior.INPUT_AGAIN);
          int inputAgain = checkInputNumber();
          result = inputAgain;
        }
        break;
    }

    return result;
  }

  public static boolean checkExistOfItemInList(String item){
    if(!items.contains(item)){
      items.add(item);
      return false;
    }
    return true;
  }

  public static boolean compareAnswer(String answer, GameAnswer gameAnser) {
    if (answer.equalsIgnoreCase(gameAnser.getValidAnswer())) {
      return true;
    }
    return false;
  }


}

