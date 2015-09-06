/*
 * FeeSummaryHeadServiceLocal.java
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
package com.mg.merp.salary;

import com.mg.merp.salary.model.FeeSummaryHead;

/**
 * Сервис бизнес-компонента "Своды н/у по аналитике"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: FeeSummaryHeadServiceLocal.java,v 1.4 2007/08/27 06:16:11 sharapov Exp $
 */
public interface FeeSummaryHeadServiceLocal extends com.mg.merp.document.GoodsDocument<FeeSummaryHead, Integer, FeeSummaryModelServiceLocal, FeeSummarySpecServiceLocal> {

  /**
   * Имя сервиса
   */
  final static String SERVICE_NAME = "merp/salary/FeeSummaryHead"; //$NON-NLS-1$

  /**
   * тип папки для сводов по н/у
   */
  final static short FOLDER_PART = 10501;

  /**
   * docsection для сводов по н/у
   */
  final static short DOCSECTION = 10501;

}
