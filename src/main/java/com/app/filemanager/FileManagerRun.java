package com.app.filemanager;

import com.app.filemanager.core.file.LocalFile;

import java.io.File;
import java.util.Scanner;

public class FileManagerRun {

  public static void main(String[] args) {

    mark:
    while (true) {
      try {

        System.out.println("Enter help to get the explanations");
        Scanner scanner = new Scanner(System.in);

        switch (scanner.next()) {
          case "help":
            LocalFile.getHelp();
            System.out.println();
            break;
          case "getDrives":
            LocalFile.getDrives();
            System.out.println();
            break;
          case "findFiles":

            System.out.println("Enter the directory where to search ");
            String directory = scanner.next();

            System.out.println("Enter the file name with extension or without extension to be searched");
            String fileName = scanner.next();

            System.out.println("Searching, please wait...");

            LocalFile.findFiles(fileName, new File(directory));
            System.out.println();
            break;
          case "showFile":

            System.out.println("Enter the directory and file to show ");
            String fileNameToShow = scanner.next();
            LocalFile.showFile(fileNameToShow);
            System.out.println();
            System.out.println();
            break;
          case "changeFile":

            System.out.println("Enter file name you want to change");
            String fileToChange = scanner.next();
            scanner.nextLine();

            System.out.println("Enter any line");
            String beginningLine = scanner.nextLine();

            LocalFile.changeFile(fileToChange, beginningLine);
            System.out.println();
            break;

          case "exit":
            break mark;
        }

      } catch (Exception e) {
        System.out.println("Please try again");
        continue mark;
      }

//
//
//
//      System.out.println("Enter the directory where to search ");
//      String directory = scanner.next();
//
//      System.out.println("Enter the file to be searched.. ");
//      String fileName = scanner.next();
//
//      System.out.println("Searching, please wait...");
//      fileService.findFile(fileName, new File(directory));
//
//      System.out.println("Enter file name you want to change");
//      String fileToWatch = scanner.next();
//      fileService.getFileContext(fileToWatch);
//
//      System.out.println("Enter file name you want to change");
//      System.out.println("Enter any line");
//      String fileToChange = scanner.next();
//      scanner.nextLine();
//      String beginningLine = scanner.nextLine();
//      try {
//        fileService.addLineToBeginning(fileToChange, beginningLine);
//      } catch (IOException e) {
//        e.printStackTrace();
//      }

    }
  }
}
