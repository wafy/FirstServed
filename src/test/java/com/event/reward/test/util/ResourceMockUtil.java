package com.event.reward.test.util;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ResourceMockUtil {

  public static String getString(String fileName) {
    try (FileInputStream fileInputStream = new FileInputStream(getFile(fileName))) {
      StringBuilder sb = new StringBuilder();
      byte[] buffer = new byte[100];
      int readSize;
      while ((readSize = fileInputStream.read(buffer)) != -1) {
        byte[] copied = new byte[readSize];
        System.arraycopy(buffer,0,copied,0, readSize);
        sb.append(new String(copied));
      }
      fileInputStream.close();
      return sb.toString();
    } catch (IOException e) {
      throw new AssertionError(e.getMessage());
    }
  }

  public static File getFile(String fileName) {
    try {
      return ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "mock/" + fileName);
    } catch (FileNotFoundException e) {
      throw new AssertionError(e.getMessage());
    }
  }

  public static FileInputStream getFileInputStream(String fileName) {
    try {
      return new FileInputStream(
          ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "mock/" + fileName));
    } catch (FileNotFoundException e) {
      throw new AssertionError(e.getMessage());
    }
  }
}
