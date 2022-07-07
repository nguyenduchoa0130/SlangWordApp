package com.slang_word;

import com.file_util.FileUtil;

import java.util.HashMap;

public class SlangWordLibrary {
  private HashMap<String, String> database;
  private FileUtil fileUtil;

  public SlangWordLibrary() {
    this.fileUtil = new FileUtil();
    this.database = fileUtil.readSlangWordsFromFile();
  }
}
