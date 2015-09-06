/*
 * LibInfo.java
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

import java.util.Set;

/**
 * Структура для хранения информации об архиве, подлежащим помещению в библиотеку
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: LibInfo.java,v 1.2 2007/07/11 07:31:40 poroxnenko Exp $
 */
public class LibInfo {
  public String jarPath = null;
  public String srcPath = null;
  public String libName = null;
  public String libVersion;
  public String libTitle = null;
  public String libDesc = null;
  public String libVendor = null;
  public Set<String> libDepends = null;

  public LibInfo() {
  }
}
