/*
 * ScrapSpecificationServiceLocal.java
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

import com.mg.merp.document.GoodsDocumentSpecification;
import com.mg.merp.manufacture.model.ScrapDocumentHead;
import com.mg.merp.manufacture.model.ScrapDocumentSpec;

/**
 * Базовый интерфейс актов на списание потерь
 *
 * @author Oleg V. Safonov
 * @version $Id: ScrapSpecificationServiceLocal.java,v 1.1 2007/08/06 12:46:24 safonov Exp $
 */
public interface ScrapSpecificationServiceLocal<T extends ScrapDocumentSpec> extends
    GoodsDocumentSpecification<T, Integer> {

  /**
   * создание спецификаций
   *
   * @param docHead документ
   */
  void createSpecifications(ScrapDocumentHead docHead);

}
