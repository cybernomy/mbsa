/*
 * StringUtils.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.framework.utils;

import com.mg.framework.api.InternalException;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Misc String Utility Functions
 *
 * @author Oleg V. Safonov
 * @author Valentin A. Poroxnenko
 * @version $Id: StringUtils.java,v 1.7 2008/08/12 09:21:39 safonov Exp $
 */
public class StringUtils {

  /**
   * текстовая константа <пробел>
   */
  public static final String BLANK_STRING = " ";
  /**
   * пустая строка
   */
  public static String EMPTY_STRING = "";
  private static char[] hexChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

  /**
   * Replaces all occurances of oldString in mainString with newString
   *
   * @param mainString The original string
   * @param oldString  The string to replace
   * @param newString  The string to insert in place of the old
   * @return mainString with all occurances of oldString replaced by newString
   */
  public static String replaceString(String mainString, String oldString, String newString) {
    if (mainString == null) {
      return null;
    }
    if (oldString == null || oldString.length() == 0) {
      return mainString;
    }
    if (newString == null) {
      newString = "";
    }

    int i = mainString.lastIndexOf(oldString);

    if (i < 0) return mainString;

    StringBuffer mainSb = new StringBuffer(mainString);

    while (i >= 0) {
      mainSb.replace(i, i + oldString.length(), newString);
      i = mainString.lastIndexOf(oldString, i - 1);
    }
    return mainSb.toString();
  }

  /**
   * Creates a single string from a List of strings seperated by a delimiter.
   *
   * @param list  a list of strings to join
   * @param delim the delimiter character(s) to use. (null value will join with no delimiter)
   * @return a String of all values in the list seperated by the delimiter
   */
  public static String join(List<String> list, String delim) {
    if (list == null || list.size() < 1)
      return null;
    StringBuilder buf = new StringBuilder();
    Iterator<String> i = list.iterator();

    while (i.hasNext()) {
      buf.append(i.next());
      if (i.hasNext())
        buf.append(delim);
    }
    return buf.toString();
  }

  /**
   * Splits a String on a delimiter into a List of Strings.
   *
   * @param str   the String to split
   * @param delim the delimiter character(s) to join on (null will split on whitespace)
   * @return a list of Strings
   */
  public static List<String> split(String str, String delim) {
    List<String> splitList = null;
    StringTokenizer st = null;

    if (str == null)
      return splitList;

    if (delim != null)
      st = new StringTokenizer(str, delim);
    else
      st = new StringTokenizer(str);

    if (st != null && st.hasMoreTokens()) {
      splitList = new ArrayList<String>();

      while (st.hasMoreTokens())
        splitList.add(st.nextToken());
    }
    return splitList;
  }

  /**
   * Encloses each of a List of Strings in quotes.
   *
   * @param list List of String(s) to quote.
   */
  public static List<String> quoteStrList(List<String> list) {
    List<String> tmpList = list;

    list = new ArrayList<String>();
    Iterator<String> i = tmpList.iterator();

    while (i.hasNext()) {
      String str = (String) i.next();

      str = "'" + str + "''";
      list.add(str);
    }
    return list;
  }

  /**
   * Creates a Map from an encoded name/value pair string
   *
   * @param str  The string to decode and format
   * @param trim Trim whitespace off fields
   * @return a Map of name/value pairs
   */
  public static Map<String, String> strToMap(String str, boolean trim) {
    if (str == null) return null;
    Map<String, String> decodedMap = new HashMap<String, String>();
    List<String> elements = split(str, "|");
    Iterator<String> i = elements.iterator();

    while (i.hasNext()) {
      String s = (String) i.next();
      List<String> e = split(s, "=");

      if (e.size() != 2) {
        continue;
      }
      String name = (String) e.get(0);
      String value = (String) e.get(1);
      if (trim) {
        if (name != null) {
          name = name.trim();
        }
        if (value != null) {
          value = value.trim();
        }
      }

      try {
        decodedMap.put(URLDecoder.decode(name, "UTF-8"), URLDecoder.decode(value, "UTF-8"));
      } catch (UnsupportedEncodingException e1) {
        //Debug.logError(e1, module);
      }
    }
    return decodedMap;
  }

  /**
   * Creates a Map from an encoded name/value pair string
   *
   * @param str The string to decode and format
   * @return a Map of name/value pairs
   */
  public static Map<String, String> strToMap(String str) {
    return strToMap(str, false);
  }

  /**
   * Creates an encoded String from a Map of name/value pairs (MUST BE STRINGS!)
   *
   * @param map The Map of name/value pairs
   * @return String The encoded String
   */
  public static String mapToStr(Map<String, String> map) {
    if (map == null) return null;
    StringBuffer buf = new StringBuffer();
    Set<String> keySet = map.keySet();
    Iterator<String> i = keySet.iterator();
    boolean first = true;

    while (i.hasNext()) {
      Object key = i.next();
      Object value = map.get(key);

      if (!(key instanceof String) || !(value instanceof String))
        continue;
      String encodedName = null;
      try {
        encodedName = URLEncoder.encode((String) key, "UTF-8");
      } catch (UnsupportedEncodingException e) {
        //Debug.logError(e, module);
      }
      String encodedValue = null;
      try {
        encodedValue = URLEncoder.encode((String) value, "UTF-8");
      } catch (UnsupportedEncodingException e) {
        //Debug.logError(e, module);
      }

      if (first)
        first = false;
      else
        buf.append("|");

      buf.append(encodedName);
      buf.append("=");
      buf.append(encodedValue);
    }
    return buf.toString();
  }

  /**
   * Create a Map from a List of keys and a List of values
   *
   * @param keys   List of keys
   * @param values List of values
   * @return Map of combined lists
   * @throws IllegalArgumentException When either List is null or the sizes do not equal
   */
  public static Map<Object, Object> createMap(List<Object> keys, List<Object> values) {
    if (keys == null || values == null || keys.size() != values.size()) {
      throw new IllegalArgumentException("Keys and Values cannot be null and must be the same size");
    }
    Map<Object, Object> newMap = new HashMap<Object, Object>();
    for (int i = 0; i < keys.size(); i++) {
      newMap.put(keys.get(i), values.get(i));
    }
    return newMap;
  }

  /**
   * Make sure the string starts with a forward slash but does not end with one; converts
   * back-slashes to forward-slashes; if in String is null or empty, returns zero length string.
   */
  public static String cleanUpPathPrefix(String prefix) {
    if (prefix == null || prefix.length() == 0) return "";

    StringBuffer cppBuff = new StringBuffer(prefix.replace('\\', '/'));

    if (cppBuff.charAt(0) != '/') {
      cppBuff.insert(0, '/');
    }
    if (cppBuff.charAt(cppBuff.length() - 1) == '/') {
      cppBuff.deleteCharAt(cppBuff.length() - 1);
    }
    return cppBuff.toString();
  }

  /**
   * Removes all spaces from a string
   */
  public static String removeSpaces(String str) {
    StringBuffer newString = new StringBuffer();
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) != ' ')
        newString.append(str.charAt(i));
    }
    return newString.toString();
  }

  public static String toHexString(byte[] bytes) {
    StringBuffer buf = new StringBuffer(bytes.length * 2);
    for (int i = 0; i < bytes.length; i++) {
      buf.append(hexChar[(bytes[i] & 0xf0) >>> 4]);
      buf.append(hexChar[bytes[i] & 0x0f]);
    }
    return buf.toString();

  }

  public static String cleanHexString(String str) {
    StringBuffer buf = new StringBuffer();
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) != (int) 32 && str.charAt(i) != ':') {
        buf.append(str.charAt(i));
      }
    }
    return buf.toString();
  }

  public static byte[] fromHexString(String str) {
    str = cleanHexString(str);
    int stringLength = str.length();
    if ((stringLength & 0x1) != 0) {
      throw new IllegalArgumentException("fromHexStringКrequiresКanКevenКnumberКofКhexКcharacters");
    }
    byte[] b = new byte[stringLength / 2];

    for (int i = 0, j = 0; i < stringLength; i += 2, j++) {
      int high = convertChar(str.charAt(i));
      int low = convertChar(str.charAt(i + 1));
      b[j] = (byte) ((high << 4) | low);
    }
    return b;
  }

  /**
   * преобразует первый символ строки в верхний регистр, используется текущая локаль, данный вызов
   * эквивалентен <code>firstCharToUpper(str, ServerUtils.getUserLocale)</code>.
   *
   * @param str строка для преобразования
   * @return преобразованная строка
   */
  public static String firstCharToUpper(String str) {
    return firstCharToUpper(str, ServerUtils.getUserLocale());
  }

  /**
   * преобразует первый символ строки в верхний регистр
   *
   * @param str    строка для преобразования
   * @param locale локаль используемая при преобразовании
   * @return преобразованная строка
   */
  public static String firstCharToUpper(String str, Locale locale) {
    String first, rest;

    if (stringNullOrEmpty(str))
      return str;

    first = str.substring(0, 1).toUpperCase(locale);
    rest = str.substring(1);
    return first + rest;
  }

  /**
   * преобразует первый символ строки в нижний регистр, используется текущая локаль, данный вызов
   * эквивалентен <code>firstCharToLower(str, ServerUtils.getUserLocale)</code>.
   *
   * @param str строка для преобразования
   * @return преобразованная строка
   */
  public static String firstCharToLower(String str) {
    return firstCharToLower(str, ServerUtils.getUserLocale());
  }

  /**
   * преобразует первый символ строки в нижний регистр
   *
   * @param str    строка для преобразования
   * @param locale локаль используемая при преобразовании
   * @return преобразованная строка
   */
  public static String firstCharToLower(String str, Locale locale) {
    String first, rest;

    if (stringNullOrEmpty(str))
      return str;

    first = str.substring(0, 1).toLowerCase(locale);
    rest = str.substring(1);

    return first + rest;
  }

  /**
   * проверка строки на <code>null</code> или пустую строку
   *
   * @param str проверяемая строка
   * @return <code>true</code> если строка <code>null</code> или пустая
   */
  public static boolean stringNullOrEmpty(String str) {
    return (str == null) || (str.length() == 0);
  }

  /**
   * Returns a formatted string using the locale of current user, format string, and arguments.
   *
   * @param format A <a href="../util/Formatter.html#syntax">format string</a>
   * @param args   Arguments referenced by the format specifiers in the format string
   * @return A formatted string
   * @see java.lang.String#format(java.util.Locale, java.lang.String, java.lang.Object[])
   */
  public static String format(String format, Object... args) {
    return String.format(ServerUtils.getUserLocale(), format, args);
  }

  /**
   * Converts all of the characters in this <code>String</code> to upper case using the rules of the
   * current <code>Locale</code>.
   *
   * @param string string
   * @return upper case string or <code>null</code> if string is null
   * @see java.lang.String#toLowerCase(java.util.Locale)
   */
  public static String toUpperCase(String string) {
    if (string == null)
      return null;
    return string.toUpperCase(ServerUtils.getUserLocale());
  }

  /**
   * Converts all of the characters in this <code>String</code> to lower case using the rules of the
   * current <code>Locale</code>.
   *
   * @param string string
   * @return lower case string or <code>null</code> if string is null
   * @see java.lang.String#toLowerCase(java.util.Locale)
   */
  public static String toLowerCase(String string) {
    if (string == null)
      return null;
    return string.toLowerCase(ServerUtils.getUserLocale());
  }

  /**
   * дополняет строку символами слева.
   *
   * @param string  исходная строка
   * @param length  длина новой строки
   * @param padChar символ для добавления
   * @param cut     используется если длина исходной строки больше <code>length</code>, если
   *                <code>true</code> то строка будет урезана до новой длины, иначе вернет исходную
   *                строку
   * @return сформированная строка
   */
  public static String padLeft(String string, int length, char padChar, boolean cut) {
    if (string == null)
      return null;
    if (string.length() > length)
      return cut ? string.substring(0, length) : string;
    else {
      char[] chars = new char[length - string.length()];
      Arrays.fill(chars, padChar);
      return new String(chars).concat(string);
    }
  }

  /**
   * дополняет строку символами справа до длины
   *
   * @param string  исходная строка
   * @param length  длина новой строки
   * @param padChar символ для добавления
   * @param cut     используется если длина исходной строки больше <code>length</code>, если
   *                <code>true</code> то строка будет урезана до новой длины, иначе вернет исходную
   *                строку
   * @return сформированная строка
   */
  public static String padRight(String string, int length, char padChar, boolean cut) {
    if (string == null)
      return null;
    if (string.length() > length)
      return cut ? string.substring(0, length) : string;
    else {
      char[] chars = new char[length - string.length()];
      Arrays.fill(chars, padChar);
      return string.concat(new String(chars));
    }
  }

  /**
   * Encode a string in base 64.
   *
   * @param str string
   * @return string encoded in base 64
   */
  public static String encodeBase64(String str) {
    if (str != null) {
      try {
        byte[] decodedBytes = str.getBytes("UTF-8");
        byte[] encodedBytes = Base64.encodeBase64(decodedBytes);
        return new String(encodedBytes, "UTF-8");
      } catch (UnsupportedEncodingException e) {
        return str;
      }
    } else {
      return null;
    }
  }

  /**
   * Decodes a base64 string.
   *
   * @param string base64 string
   * @return string
   */
  public static String decodeBase64(String string) {
    try {
      byte[] encodedBytes = string.getBytes("UTF-8");
      byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
      return new String(decodedBytes, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      return string;
    }

  }

  public static String iso2utf8(String msg) {
    try {
      return new String(msg.getBytes("ISO-8859-1"), "UTF-8");
    } catch (UnsupportedEncodingException e) {
      //actually it's impossible
      throw new InternalException(e);
    }
  }

  private static int convertChar(char c) {
    if ('0' <= c && c <= '9') {
      return c - '0';
    } else if ('a' <= c && c <= 'f') {
      return c - 'a' + 0xa;
    } else if ('A' <= c && c <= 'F') {
      return c - 'A' + 0xa;
    } else {
      throw new IllegalArgumentException("Invalid hex character: " + c);
    }
  }

}
