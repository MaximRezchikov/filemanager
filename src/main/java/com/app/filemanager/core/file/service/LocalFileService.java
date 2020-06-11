package com.app.filemanager.core.file.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public interface LocalFileService {

  void getAllDrives();

  void findFile(String fileName, File file);

  void getFileContext(String fileName);

  void addLineToBeginning(String fileName, String beginningLine) throws IOException;
}
