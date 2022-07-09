package com.slang_word;

public class GameAnswer {
  private String answerA;
  private String answerB;
  private String answerC;
  private String answerD;
  private String validAnswer;

  public GameAnswer(String answerA, String answerB, String answerC, String answerD, String validAnswer) {
    this.answerA = answerA;
    this.answerB = answerB;
    this.answerC = answerC;
    this.answerD = answerD;
    this.validAnswer = validAnswer;
  }

  public GameAnswer() {
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

  public String getValidAnswer() {
    return validAnswer;
  }

  public void setValidAnswer(String validAnswer) {
    this.validAnswer = validAnswer;
  }
}
