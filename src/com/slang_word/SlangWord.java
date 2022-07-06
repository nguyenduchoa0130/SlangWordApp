package com.slang_word;

public class SlangWord {
  private String slangWord = null;
  private String meaning = null;

  public SlangWord(String rawString) {
    String[] extractSlangWord = rawString.split("`");
    if(extractSlangWord.length != 2) {
      System.out.println("The slang word is invalid with pattern: \\slang word\\`\\meaning\\");
    }else {
      this.slangWord = extractSlangWord[0];
      this.meaning = extractSlangWord[1];
    }
  }

  public SlangWord(String slangWord, String meaning) {
    if(Boolean.parseBoolean(slangWord) && Boolean.parseBoolean(meaning)) {
      this.slangWord = slangWord;
      this.meaning = meaning;
    }
  }

  public String getSlangWord() {
    return slangWord;
  }

  public void setSlangWord(String slangWord) {
    this.slangWord = slangWord;
  }

  public String getMeaning() {
    return meaning;
  }

  public void setMeaning(String meaning) {
    this.meaning = meaning;
  }

  @Override
  public String toString() {
    return this.slangWord + ": " + this.meaning;
  }
}
