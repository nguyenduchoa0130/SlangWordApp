package com.slang_word;

import com.constant.ErrorMessage;
import com.file_util.FileUtil;

import java.util.HashMap;

public class SlangWordLibrary {
  private HashMap<String, String> database;
  private FileUtil fileUtil;

  public SlangWordLibrary() {
    this.fileUtil = new FileUtil();
    this.database = fileUtil.readSlangWordsFromFile();
  }

  public SlangWord findBySlangWord(String keyWord) {
    String meaning = null;
    String upperCaseKeyWord = keyWord.toUpperCase();
    String lowerCaseKeyWord = keyWord.toLowerCase();
    if (database.get(keyWord) != null) {
      meaning = database.get(keyWord);
    } else {
      if (database.get(upperCaseKeyWord) != null) {
        meaning = database.get(upperCaseKeyWord);
      } else {
        if (database.get(lowerCaseKeyWord) != null) {
          meaning = database.get(lowerCaseKeyWord);
        }
      }
    }

    return meaning != null ? new SlangWord(keyWord, meaning) : new SlangWord(keyWord, ErrorMessage.ERROR_NOT_FIND_SLANG_WORD);
  }
}

