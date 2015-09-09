/*
 * DataTypes.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 *
 */
package com.mg.merp.wb.report.birt.data.oda.badi.util;

import org.eclipse.datatools.connectivity.oda.OdaException;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * Типы данных
 *
 * @author Oleg V. Safonov
 * @author Valentin A. Poroxnenko
 * @version $Id: DataTypes.java,v 1.8 2007/08/30 14:48:51 safonov Exp $
 */
public final class DataTypes {
  /**
   * целый
   */
  public static final int INT = Types.INTEGER;
  /**
   * с двойной точностью
   */
  public static final int DOUBLE = Types.DOUBLE;
  /**
   * строка
   */
  public static final int STRING = Types.VARCHAR;
  /**
   * дата
   */
  public static final int DATE = Types.DATE;
  /**
   * время
   */
  public static final int TIME = Types.TIME;
  /**
   * временная метка
   */
  public static final int TIMESTAMP = Types.TIMESTAMP;
  /**
   * двоичный объект
   */
  public static final int BLOB = Types.BLOB;
  /**
   * с плавающей точкой
   */
  public static final int BIGDECIMAL = Types.NUMERIC;
  /**
   * логический
   */
  public static final int BOOLEAN = Types.BOOLEAN;

  //
  private static Map<String, Integer> typeStringIntPair = new HashMap<String, Integer>();

  private static Map<Integer, String> typeIntStringPair = new HashMap<Integer, String>();

  static {
    typeStringIntPair.put("java.lang.Integer", new Integer(INT)); //$NON-NLS-1$
    typeStringIntPair.put("java.lang.Double", new Integer(DOUBLE)); //$NON-NLS-1$
    typeStringIntPair.put("java.lang.String", new Integer(STRING)); //$NON-NLS-1$
    typeStringIntPair.put("java.util.Date", new Integer(DATE)); //$NON-NLS-1$
    typeStringIntPair.put("java.sql.Time", new Integer(TIME)); //$NON-NLS-1$
    typeStringIntPair.put("java.sql.Timestamp", new Integer(TIMESTAMP)); //$NON-NLS-1$
    typeStringIntPair.put("java.math.BigDecimal", new Integer(BIGDECIMAL)); //$NON-NLS-1$
    typeStringIntPair.put("java.lang.Boolean", BOOLEAN); //$NON-NLS-1$

    typeIntStringPair.put(new Integer(INT), "java.lang.Integer"); //$NON-NLS-1$
    typeIntStringPair.put(new Integer(DOUBLE), "java.lang.Double"); //$NON-NLS-1$
    typeIntStringPair.put(new Integer(STRING), "java.lang.String"); //$NON-NLS-1$
    typeIntStringPair.put(new Integer(DATE), "java.util.Date"); //$NON-NLS-1$
    typeIntStringPair.put(new Integer(TIME), "java.sql.Time"); //$NON-NLS-1$
    typeIntStringPair.put(new Integer(TIMESTAMP), "java.sql.Timestamp"); //$NON-NLS-1$
    typeIntStringPair.put(new Integer(BIGDECIMAL), "java.math.BigDecimal"); //$NON-NLS-1$
    typeIntStringPair.put(BOOLEAN, "java.lang.Boolean"); //$NON-NLS-1$
  }

  /**
   * Возвращает целое, соответствующее входному аргументу
   *
   * @param typeName имя типа
   * @return код типа
   */
  public static int getType(String typeName) throws OdaException {
    String preparedTypeName = typeName.trim();
    if (typeStringIntPair.containsKey(preparedTypeName))
      return typeStringIntPair.get(preparedTypeName);
    throw new OdaException(); //$NON-NLS-1$
  }

  /**
   * Возвращает строку, соответствующую входному аргументу
   *
   * @param type код типа
   * @return имя типа
   */
  public static String getTypeString(int type) throws OdaException {
    if (typeIntStringPair.containsKey(type))
      return typeIntStringPair.get(type).toString();
    throw new OdaException(); //$NON-NLS-1$
  }

  /**
   * Проверка валидности имени типа
   *
   * @param typeName имя типа
   */
  public static boolean isValidType(String typeName) {
    return typeStringIntPair.containsKey(typeName.trim());
  }

}