package com.app.filemanager.core.file.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LocalFileServiceImpl implements LocalFileService {

  private static final String NEW_LINE = System.getProperty("line.separator");
  public static final String EXTENSION = "([\\w\\:\\\\\\w /]+\\w+\\.\\w+)";

  @Override
  public void getAllDrives() {

    File[] drives = File.listRoots();

    if (drives != null && drives.length > 0) {
      for (File drive : drives) {
        System.out.println(drive);
      }
    }
  }

  @Override
  public void findFile(String fileName, File file) {

    File[] files = file.listFiles();

    if (fileName.matches(EXTENSION) && files != null) {
      for (File fileToFind : files) {
        if (fileToFind.isDirectory()) {
          findFile(fileName, fileToFind);
        }
        else if (fileName.equalsIgnoreCase(fileToFind.getName())) {
          System.out.println(fileToFind.getAbsoluteFile());
        }
      }
    }
    if (!fileName.matches(EXTENSION) && files != null) {
      for (File fileToFind : files) {
        if (fileToFind.isDirectory()) {
          findFile(fileName, fileToFind);
        }
        else {
          String nameWithoutExtension = getNameWithoutExtension(fileToFind.getName());
          if (nameWithoutExtension.contains(fileName)) {
            System.out.println(fileToFind.getAbsoluteFile());
          }
        }
      }
    }
  }

  @Override
  public void getFileContext(String fileName) {

    try (
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
    ) {

      String line;
      while ((line = bufferedReader.readLine()) != null) {
        System.out.println(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void addLineToBeginning(String fileName, String beginningLine) throws IOException {

    BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

    File tempFile = new File("temp.txt");
    if (!tempFile.exists()) {
      tempFile.createNewFile();
    }
    BufferedWriter tempWriter = new BufferedWriter(new FileWriter(tempFile.getAbsoluteFile()));

    String lines;
    while ((lines = bufferedReader.readLine()) != null) {
      tempWriter.write(lines + NEW_LINE);
    }

    tempWriter.flush();
    tempWriter.close();

    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
    bufferedWriter.write(beginningLine + NEW_LINE);

    BufferedReader tempReader = new BufferedReader(new FileReader("temp.txt"));
    String newLines;
    while ((newLines = tempReader.readLine()) != null) {
      bufferedWriter.write(newLines + NEW_LINE);
    }

    tempReader.close();
    bufferedWriter.flush();
    bufferedWriter.flush();
    bufferedReader.close();
    bufferedWriter.close();

    if (tempFile.delete()) {
      System.out.println("Temp file was deleted");
    }
    else {
      System.out.println("Something went wrong");
    }
  }

  private String getNameWithoutExtension(String name) {

    String fileName = new File(name).getName();
    int periodIndex = fileName.lastIndexOf('.');

    if (periodIndex == -1) {
      return fileName;
    }
    else {
      return fileName.substring(0, periodIndex);
    }
  }
}
