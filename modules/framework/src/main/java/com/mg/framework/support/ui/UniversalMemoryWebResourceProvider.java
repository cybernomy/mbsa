/*
 * UniversalMemoryWebResourceProvider.java
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
package com.mg.framework.support.ui;

import com.mg.framework.generic.ui.MemoryWebResourceProvider;

import java.io.IOException;

/**
 * Универсальный web ресурс в памяти
 *
 * @author Oleg V. Safonov
 * @version $Id: UniversalMemoryWebResourceProvider.java,v 1.1 2007/03/19 12:07:30 safonov Exp $
 */
public class UniversalMemoryWebResourceProvider extends
    MemoryWebResourceProvider {

  /**
   * создание ресурса по расширению файла и содержимому
   *
   * @param fileNameSuffix расширение файла
   * @param bytes          содержимое
   */
  public UniversalMemoryWebResourceProvider(String fileNameSuffix, byte[] bytes) {
    super(fileNameSuffix);
    fBytes = bytes;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.MemoryWebResourceProvider#createBytes()
   */
  @Override
  protected byte[] createBytes() throws IOException {
    return fBytes;
  }

}
