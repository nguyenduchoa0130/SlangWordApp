package com.slang_word;

public class GameAnswer extends SlangWord {
  private String answerA;
  private String answerB;
  private String answerC;
  private String answerD;
  private String keyWord;

  public GameAnswer() {
  }

  public GameAnswer(String slangWord, String meaning, String answerA, String answerB, String answerC, String answerD, String keyWord) {
    super(slangWord, meaning);
    this.answerA = answerA;
    this.answerB = answerB;
    this.answerC = answerC;
    this.answerD = answerD;
    this.keyWord = keyWord;
  }

  public String getAnswerA() {
    return answerA;
  }

  public void setAnswerA(String answerA) {
    this.answerA = answerA;
  }

  public String getAnswerB() {
    return answerB;
  }

  public void setAnswerB(String answerB) {
    this.answerB = answerB;
  }


  public String getAnswerC() {
    return answerC;
  }

  public void setAnswerC(String answerC) {
    this.answerC = answerC;
  }

  public String getAnswerD() {
    return answerD;
  }

  public void setAnswerD(String answerD) {
    this.answerD = answerD;
  }

  public String getKeyWord() {
    return keyWord;
  }

  public void setKeyWord(String keyWord) {
    this.keyWord = keyWord;
  }
}
