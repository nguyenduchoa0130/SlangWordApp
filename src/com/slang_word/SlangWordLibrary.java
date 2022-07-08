

package com.slang_word;

import com.constant.ErrorMessage;
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
    String upperCaseKeyWord = keyWord.toUpperCase();
    if (database.get(keyWord) != null) {
      meaning = database.get(keyWord);
    } else {
      if (database.get(upperCaseKeyWord) != null) {
        meaning = database.get(upperCaseKeyWord);
        keyWord = upperCaseKeyWord;
      }
    }

    return meaning != null ? new SlangWord(keyWord, meaning) : new SlangWord(keyWord, ErrorMessage.ERROR_NOT_FIND_SLANG_WORD);
  }

  public List<SlangWord> searchByDefinition(String keyword) {
    List<SlangWord> result = new ArrayList<SlangWord>();
    for (HashMap.Entry<String, String> entry : database.entrySet()) {
      boolean match = Pattern.matches(".*" + keyword + ".*", entry.getValue());
      if (match) {
        result.add(new SlangWord(entry.getKey(), entry.getValue()));
      }
    }
    return result;
  }
}
