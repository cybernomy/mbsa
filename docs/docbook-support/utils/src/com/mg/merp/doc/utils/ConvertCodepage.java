package com.mg.merp.doc.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ConvertCodepage {

  /**
   * @param args
   */
  public static void main(String[] args) {
    if (args.length < 2) {
      System.out.println("Usage: java com.mg.merp.doc.utils.ConvertCodepage <src_UTF8_file> <dst_WIN1251_file>");
    }
    try {
      FileInputStream in = new FileInputStream(args[0]);
      byte[] inBytes = new byte[in.available()];
      in.read(inBytes);
      in.close();

      String s = new String(inBytes, "UTF-8");
      byte[] outBytes = s.getBytes("windows-1251");

      FileOutputStream out = new FileOutputStream(args[1]);
      out.write(outBytes);
      out.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
