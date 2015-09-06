/*
 * PriceListFolderServiceLocal.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.reference;

import com.mg.merp.reference.model.PriceListFolder;

/**
 * Бизнес-компонент "Папки прайс-листов"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @author Konstantin S. Alikaev
 * @version $Id: PriceListFolderServiceLocal.java,v 1.4 2008/05/16 05:52:31 alikaev Exp $
 */
public interface PriceListFolderServiceLocal extends com.mg.framework.api.DataBusinessObjectService<PriceListFolder, Integer> {

  /**
   * тип раздела папок "Прайс-листов"
   */
  final static short FOLDER_PART = 2;

  /**
   * Имя сервиса
   */
  final static String SERVICE_NAME = "merp/reference/PriceListFolder"; //$NON-NLS-1$

  /**
   * Cоздать спецификации прайс-листа на основе каталога
   *
   * @param catalogFolderId   - папка каталога
   * @param priceListFolderId - папка прайс-листа
   * @param createSpec        - признак создания спецификаций прайс-листа
   */
  void addFromCatalog(int catalogFolderId, int priceListFolderId, boolean createSpec);

}
