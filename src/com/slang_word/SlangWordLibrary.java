package com.slang_word;

import com.file_util.FileUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

  public boolean creatSlangWord(SlangWord slangWord) {
    String tempt = slangWord.getSlangWord().equals(slangWord.getSlangWord().toUpperCase()) ? slangWord.getSlangWord() : slangWord.getSlangWord().toUpperCase();
    if (this.database.containsKey(tempt)) {
      return false;
    } else {
      this.database.put(tempt, slangWord.getMeaning());
      return true;
    }
  }

  public void editSlangWord(SlangWord slangWord) {
    this.database.put(slangWord.getSlangWord(), slangWord.getMeaning());
  }

  public void deleteSlangWord(String slangToDelete) {
    this.database.remove(slangToDelete);
  }
}

