/*
 * BankDocumentOutMt.java
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
package com.mg.merp.account.support.ui;

import com.mg.framework.api.ui.WidgetEvent;
import com.mg.merp.account.BankDocumentOutServiceLocal;
import com.mg.merp.account.model.BankDocument;
import com.mg.merp.document.generic.ui.DocumentMaintenanceForm;
import com.mg.merp.reference.support.ui.ContractorSearchForm;

/**
 * Контроллер формы поддержки "Исходящих банковских документов"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: BankDocumentOutMt.java,v 1.3 2007/11/08 12:18:48 sharapov Exp $
 */
public class BankDocumentOutMt extends DocumentMaintenanceForm {

  public BankDocumentOutMt() {
    contractorFromKinds = new String[]{ContractorSearchForm.CONTRACTOR_PARTNER};
    contractorToKinds = new String[]{ContractorSearchForm.CONTRACTOR_PARTNER};
  }

  /**
   * Обработчик кнопки "Рассчитать суммы НДС"
   *
   * @param event - событие
   */
  public void onActionCalculateNdsSum(WidgetEvent event) {
    ((BankDocumentOutServiceLocal) this.getService()).calculateNdsSum((BankDocument) getEntity());
  }

  /**
   * Обработчик кнопки "Рассчитать сумму документа"
   *
   * @param event - событие
   */
  public void onActionCalculateDocSum(WidgetEvent event) {
    ((BankDocumentOutServiceLocal) this.getService()).calculateDocSum((BankDocument) getEntity());
  }

}
