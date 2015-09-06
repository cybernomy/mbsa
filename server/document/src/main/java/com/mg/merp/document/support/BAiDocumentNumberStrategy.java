/*
 * BAiDocumentNumberStrategy.java
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
package com.mg.merp.document.support;

import com.mg.framework.utils.MiscUtils;
import com.mg.merp.baiengine.BusinessAddinEngineLocator;
import com.mg.merp.baiengine.BusinessAddinEvent;
import com.mg.merp.baiengine.BusinessAddinListener;
import com.mg.merp.baiengine.model.Repository;
import com.mg.merp.document.generic.AbstractDocumentNumberStrategy;
import com.mg.merp.document.model.DocHead;

/**
 * Стратегия формирования номера через BAi
 *
 * @author Oleg V. Safonov
 * @version $Id: BAiDocumentNumberStrategy.java,v 1.2 2007/03/23 14:33:23 safonov Exp $
 */
public class BAiDocumentNumberStrategy extends AbstractDocumentNumberStrategy {
  private String docNumber = null;
  private Repository repository;

  public BAiDocumentNumberStrategy(Repository repository) {
    this.repository = repository;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.AbstractDocumentNumberStrategy#doGenerateNumber(com.mg.merp.document.model.DocHead)
   */
  @Override
  protected String doGenerateNumber(DocHead docHead) {
    BusinessAddinEngineLocator.locate().perform(repository, MiscUtils.toMap(DocumentNumberBusinessAddin.DOCHEAD_PARAM, docHead), new BusinessAddinListener<String>() {

      public void aborted(BusinessAddinEvent<String> event) {
      }

      public void completed(BusinessAddinEvent<String> event) {
        docNumber = event.getResult();
      }

    });
    return docNumber;
  }

}
