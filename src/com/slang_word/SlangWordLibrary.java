package com.slang_word;

import com.app.valid.ValidInput;
import com.file_util.FileUtil;

import java.util.*;
import java.util.regex.Pattern;

public class SlangWordLibrary {
  private HashMap<String, String> database;
  private FileUtil fileUtil;

  public SlangWordLibrary() {
    this.fileUtil = new FileUtil();
    this.database = fileUtil.readSlangWordsFromFile();
  }

  public SlangWord findBySlangWord(String keyWord) {
    String meaning = null;
    String tempt = keyWord.equals(keyWord.toUpperCase()) ? keyWord : keyWord.toUpperCase();
    if (database.get(tempt) != null) {
      meaning = database.get(tempt);
    }
    return meaning != null ? new SlangWord(tempt, meaning) : null;
  }

  public List<SlangWord> searchByDefinition(String keyword) {
    List<SlangWord> result = new ArrayList<>();
    for (HashMap.Entry<String, String> entry : database.entrySet()) {
      boolean match = Pattern.matches(".*" + keyword + ".*", entry.getValue());
      if (match) {
        result.add(new SlangWord(entry.getKey(), entry.getValue()));
      }
    }
    return result;
  }

  public void creatSlangWord(SlangWord slangWord) {
    String tempt = slangWord.getSlangWord().equals(slangWord.getSlangWord().toUpperCase()) ? slangWord.getSlangWord() : slangWord.getSlangWord().toUpperCase();
    this.database.put(tempt, slangWord.getMeaning());
  }

  public void editSlangWord(SlangWord slangWord) {
    this.database.put(slangWord.getSlangWord(), slangWord.getMeaning());
  }

  public void deleteSlangWord(String slangToDelete) {
    this.database.remove(slangToDelete);
  }

  public SlangWord randomSlangWord() {
    Random generator = new Random();
    Object[] keys = this.database.keySet().toArray();
    String slangWord = (String) keys[generator.nextInt(keys.length)];
    return new SlangWord(slangWord, database.get(slangWord));
  }

  public String getAnswer() {
    boolean flag = false;
    String keyWord;
    do {
      keyWord = this.randomSlangWord().getSlangWord();
      flag = ValidInput.checkExistOfItemInList(keyWord);
    } while (!flag);
    return keyWord;
  }

  public GameAnswer gameRandom(boolean keyWord) {
    GameAnswer gameAswer = new GameAnswer();
    String a = getAnswer();
    String b = getAnswer();
    String c = getAnswer();
    String d = getAnswer();
    List<String> listAnswers = Arrays.asList(a, b, c, d);
    Random generator = new Random();
    String resultRandom = listAnswers.get(generator.nextInt(listAnswers.size()));
    gameAswer.setSlangWord(resultRandom);
    gameAswer.setMeaning(database.get(resultRandom));
    if (!keyWord) {
      gameAswer.setAnswerA(database.get(a));
      gameAswer.setAnswerB(database.get(b));
      gameAswer.setAnswerC(database.get(c));
      gameAswer.setAnswerD(database.get(d));
    } else {
      gameAswer.setAnswerA(a);
      gameAswer.setAnswerB(b);
      gameAswer.setAnswerC(c);
      gameAswer.setAnswerD(d);
    }
    return gameAswer;
  }
}

