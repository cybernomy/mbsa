/*
 * ScrapProductHeadServiceLocal.java
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
package com.mg.merp.manufacture;

import com.mg.merp.manufacture.model.ScrapDocumentHead;

/**
 * Бизнес-компонент "Акт на списание потерь с операции"
 *
 * @author Oleg V. Safonov
 * @version $Id: ScrapProductHeadServiceLocal.java,v 1.5 2007/08/06 12:46:24 safonov Exp $
 */
public interface ScrapProductHeadServiceLocal
    extends com.mg.merp.document.GoodsDocument<ScrapDocumentHead, Integer, ScrapProductModelServiceLocal, ScrapProductSpecServiceLocal> {
  /**
   * имя сервиса
   */
  static final String SERVICE_NAME = "merp/manufacture/ScrapProductHead";

  /**
   * тип папки для актов на списание потерь с операции
   */
  final static short FOLDER_PART = 12506;

  /**
   * docsection для актов на списание потерь с операции
   */
  final static short DOCSECTION = 12005;
}
