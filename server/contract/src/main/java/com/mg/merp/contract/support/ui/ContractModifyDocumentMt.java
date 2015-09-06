/*
 * ContractModifyDocumentMt.java
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
package com.mg.merp.contract.support.ui;

import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.merp.document.support.DocumentUtils;

/**
 * Контроллер формы поддержки "Изменение контракта"
 *
 * @author Artem V. Sharapov
 * @version $Id: ContractModifyDocumentMt.java,v 1.1 2007/11/22 15:59:26 sharapov Exp $
 */
public class ContractModifyDocumentMt extends DefaultMaintenanceForm {

  private final static String DOCUMENT_ATTRIBUTE_NAME = "Document"; //$NON-NLS-1$

  public ContractModifyDocumentMt() {
  }

  /**
   * Обработчик просмотра документа
   *
   * @param event - событие
   */
  public void onActionViewDocument(WidgetEvent event) {
    DocumentUtils.viewDocument(getEntity(), DOCUMENT_ATTRIBUTE_NAME);
  }

}
