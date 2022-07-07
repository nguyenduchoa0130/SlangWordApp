package com.file_util;

import com.slang_word.SlangWord;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class FileUtil {
  private final String DATASOURCE_FILE_PATH = "files/slang.txt";
  private final String HISTORIES_FILE_PATH = "files/histories.txt";
  private final String BACKUP_DATASOURCE_FILE_PATH = "files/slang_backup.txt";
  private File slangWordsDataFile;
  private File historiesFile;
  private File backUpFile;

  public FileUtil() {
    this.slangWordsDataFile = new File(this.DATASOURCE_FILE_PATH);
    this.historiesFile = new File(this.HISTORIES_FILE_PATH);
    this.backUpFile = new File(this.BACKUP_DATASOURCE_FILE_PATH);
  }

  public HashMap<String, String> readSlangWordsFromFile() {
    HashMap<String, String> slangWordsHashMap = new HashMap<>();
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
