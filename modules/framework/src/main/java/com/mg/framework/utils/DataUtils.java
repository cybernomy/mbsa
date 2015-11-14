/**
 * DataUtils.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.framework.utils;

import com.ibm.icu.math.BigDecimal;
import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.support.LocalDataTransferObject;

import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.UUID;


/**
 * Утилиты работы с данными
 *
 * @author Oleg V. Safonov
 * @version $Id: DataUtils.java,v 1.13 2008/10/09 06:44:03 safonov Exp $
 */
public class DataUtils {
  /**
   * максимальная длина имени оригинала в байтах
   */
  public static final int ORIGINAL_NAME_LENGTH = 0xff;

  /**
   * конвертирует перечислимы тип в порядковое значение
   *
   * @param value значение перечислимого типа
   * @return порядковое значение или -1 если <code>value == null</code>
   */
  public static Integer convertEnumToOrdinal(Enum<?> value) {
    if (value == null)
      return -1;
    else
      return value.ordinal();
  }

  /**
   * конвертирует порядковое значение перечислимого типа в перечислимый тип
   *
   * @param <T>   перечислимый тип
   * @param clazz класс перечислимого типа
   * @param value порядковое значение
   * @return значение перечислимого типа или <code>null</code> если <code>value == -1</code>
   */
  public static <T extends Enum<?>> T convertOrdinalToEnum(Class<T> clazz, Integer value) {
    if (value == -1)
      return null;
    else
      return clazz.getEnumConstants()[value];
  }

  /**
   * извлечь тело содержимого оригинала
   *
   * @param original оригинал
   * @return тило оригинала
   */
  public static byte[] extractOriginalBody(byte[] original) {
    if (original == null)
      return null;

    byte[] result = new byte[original.length - ORIGINAL_NAME_LENGTH];
    System.arraycopy(original, ORIGINAL_NAME_LENGTH, result, 0, result.length);
    return result;
  }

  /**
   * извлечь наименование оригинала
   *
   * @param original оригинал
   * @return имя оригинала
   */
  public static String extractOriginalName(byte[] original) {
    if (original == null)
      return null;

    int lastIdx = 0;
    try {
      for (lastIdx = 0; lastIdx < ORIGINAL_NAME_LENGTH; lastIdx++)
        if (original[lastIdx] == 0)
          break;
      return new String(original, 0, lastIdx, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      //если не прочитали в UTF-8, то пытаемся прочитать в стандартной, это только для совместимости с предыдущими версиями
      return new String(original, 0, lastIdx);
    }
  }

  /**
   * создать оригинал по телу и имени
   *
   * @param body тело оригинала
   * @param name имя
   * @return оригинал
   */
  public static byte[] originalToBinary(byte[] body, String name) {
    byte[] result = new byte[body.length + ORIGINAL_NAME_LENGTH];
    Arrays.fill(result, (byte) 0);
    byte[] nameArray;
    try {
      nameArray = name.getBytes("UTF-8");
    } catch (UnsupportedEncodingException e) {
      nameArray = name.getBytes();
    }
    System.arraycopy(nameArray, 0, result, 0, nameArray.length);
    System.arraycopy(body, 0, result, ORIGINAL_NAME_LENGTH, body.length);
    return result;
  }

  /**
   * генерация уникальной строки
   *
   * @param maxLength максимальная длина строки
   */
  public static String generateUniqueString(int maxLength) {
    String str = String.valueOf(Math.abs(new Random().nextLong()));
    if (maxLength < str.length())
      return str.substring(0, maxLength);
    else
      return str;
  }

  /**
   * создание списка атрибутов
   *
   * @param name  имя
   * @param value значение
   * @return список атрибутов
   */
  public static AttributeMap toAttributeMap(String name, Object value) {
    AttributeMap result = new LocalDataTransferObject();
    result.put(name, value);
    return result;
  }

  /**
   * генерация UUID, результат будет приведен к верхнему регистру и будут удалены символы
   * <code>-</code>
   *
   * @return UUID
   */
  public static String generateUUID() {
    return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
  }

  /**
   * преобразовать значение свойства в строку не используя локализацию
   *
   * @param value значение
   * @return преобразованное в строку значение или <code>null</code> если <code>value == null</code>
   */
  public static String valueToString(Object value) {
    if (value == null)
      return null;
    if (value instanceof PersistentObject) {
      PersistentObject po = (PersistentObject) value;
      return new StringBuilder("__entity:").append(ReflectionUtils.getEntityClass(po).getName()).append("#").append(po.getPrimaryKey()).toString();
    } else if (value instanceof Time)
      return value.toString();
    else if (value instanceof Timestamp)
      return value.toString();
    else if (value instanceof Date)
      return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format((Date) value);
    else
      return value.toString();
  }

  /**
   * преобразование строкового представления значения свойства в значение не используя локализацию
   *
   * @param str   строкового представление
   * @param clazz тип результата
   * @return значение
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static Object stringToValue(String str, Class<?> clazz) {
    if (str == null || clazz == null || String.class.isAssignableFrom(clazz))
      return str;
    if (PersistentObject.class.isAssignableFrom(clazz)) {
      if (str.startsWith("__entity:")) {
        //удалим префикс, 1я часть это имя сущности, 2я часть первичный ключ
        String[] entityStr = str.substring(9).split("#");
        //TODO обрабатываются только сущности с первичным ключом Integer
        return ServerUtils.getPersistentManager().find(entityStr[0], Integer.valueOf(entityStr[1]));
      } else
        return str;
    } else if (Integer.class.isAssignableFrom(clazz) || Integer.TYPE.equals(clazz))
      return Integer.parseInt(str);
    else if (Long.class.isAssignableFrom(clazz) || Long.TYPE.equals(clazz))
      return Long.parseLong(str);
    else if (Short.class.isAssignableFrom(clazz) || Short.TYPE.equals(clazz))
      return Short.parseShort(str);
    else if (java.math.BigDecimal.class.isAssignableFrom(clazz))
      return new BigDecimal(str);
    else if (Float.class.isAssignableFrom(clazz) || Float.TYPE.equals(clazz))
      return Float.parseFloat(str);
    else if (Double.class.isAssignableFrom(clazz) || Double.TYPE.equals(clazz))
      return Double.parseDouble(str);
    else if (Boolean.class.isAssignableFrom(clazz) || Boolean.TYPE.equals(clazz))
      return Boolean.parseBoolean(str);
    else if (Time.class.isAssignableFrom(clazz))
      return Time.valueOf(str);
    else if (Timestamp.class.isAssignableFrom(clazz))
      return Timestamp.valueOf(str);
    else if (Date.class.isAssignableFrom(clazz))
      try {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(str);
      } catch (ParseException e) {
        return str;
      }
    else if (Enum.class.isAssignableFrom(clazz))
      return Enum.valueOf((Class<Enum>) clazz, str);
    else if (Byte.class.isAssignableFrom(clazz) || Byte.TYPE.equals(clazz))
      return Byte.valueOf(str);
    else
      return str;
  }

}
