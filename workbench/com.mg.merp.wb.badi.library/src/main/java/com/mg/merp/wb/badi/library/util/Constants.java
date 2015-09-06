/*
 * Constants.java
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
package com.mg.merp.wb.badi.library.util;

/**
 * Общие константы
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: Constants.java,v 1.5 2007/07/11 07:31:40 poroxnenko Exp $
 */
public class Constants {
  public static final String DESCRIPTION = "badi.library.descripton";

  public static final String WIZARD_DESC = "badi.library.wizard.desc";

  public static final String INCLUDED_LIBS_PROPERTY_NAME = "BadiLibraryInclude";

  public static final String MBAI_PROPERTY_FILE_NAME = ".MBAi";

  public static final String MBAI_PROPERTY_FILE_COMMENT = "MBSA library content";

  /**
   * Наименование папки, в которой распологаются архивы с классами
   */
  public static final String FLDR_LIB = "lib";

  /**
   * Название папки, содержащей архивы с исходниками
   */
  public static final String FLDR_SRC = "src";

  /**
   * Название папки, содержащей архивы thirdpart
   */
  public static final String FLDR_THIRD = "thirdpart";

  /**
   * Суффикс названия архива с исходниками
   */
  public static final String SRC_SFX = "-src";

  /**
   * Исходники находятся в корне архива. Иначе {@link #SRC_ARCH_ROOT} установить в значение,
   * соответствующее нахождению пакета исходников в архиве
   */
  public static final String SRC_ARCH_ROOT = "";
}
