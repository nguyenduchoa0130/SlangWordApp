package com.file_util;

import com.slang_word.SlangWord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileUtil {
  private final String DATASOURCE_FILE_PATH = "files/slang.txt";
  private final String HISTORIES_FILE_PATH = "files/histories.txt";
  private File slangWordsDataFile;
  private File historiesFile;

  public FileUtil() {
    this.slangWordsDataFile = new File(this.DATASOURCE_FILE_PATH);
    this.historiesFile = new File(this.HISTORIES_FILE_PATH);
  }

  public Map<String, String> readSlangWordsFromFile() {
    Map<String, String> slangWordsHashMap = new HashMap<>();
    try {
      Scanner scanner = new Scanner(slangWordsDataFile);
      while (scanner.hasNextLine()) {
        String rawString = scanner.nextLine();
        SlangWord slangWord = new SlangWord(rawString);
        slangWordsHashMap.put(slangWord.getSlangWord(), slangWord.getMeaning());
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return slangWordsHashMap;
  }
}
