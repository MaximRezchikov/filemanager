package com.app.filemanager.core.file;

import com.app.filemanager.core.file.service.LocalFileService;
import com.app.filemanager.core.file.service.LocalFileServiceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class LocalFile {

  private static LocalFileService localFileService;

  public static void getDrives() {

    localFileService = new LocalFileServiceImpl();

    localFileService.getAllDrives();
  }

  public static void findFiles(String fileName, File file) {

    localFileService = new LocalFileServiceImpl();

    localFileService.findFile(fileName, file);
  }

  public static void showFile(String fileName) {

    localFileService = new LocalFileServiceImpl();

    localFileService.getFileContext(fileName);
  }

  public static void changeFile(String fileName, String beginningLine) {

    localFileService = new LocalFileServiceImpl();

    try {

      localFileService.addLineToBeginning(fileName, beginningLine);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void getHelp() {

    try (
        BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(LocalFile.class.getResourceAsStream("/help.txt")));
    ) {

      String line;
      while ((line = bufferedReader.readLine()) != null) {
        System.out.println(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
