/*
 * LiabilityMt.java
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
package com.mg.merp.paymentcontrol.support.ui;

import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.support.DocumentUtils;
import com.mg.merp.paymentcontrol.model.Liability;

/**
 * Контроллер формы поддержки бизнес-компонента
 *
 * @author Konstantin S. Alikaev
 * @version $Id: LiabilityMt.java,v 1.1 2008/04/01 05:24:40 alikaev Exp $
 */
public class LiabilityMt extends DefaultMaintenanceForm {

  /**
   * Обработчик просмотра документа-основания
   *
   * @param event - событие
   */
  public void onActionViewBaseDocument(WidgetEvent event) {
    DocumentUtils.viewDocument(((Liability) getEntity()).getBaseDoc());
  }

  /**
   * Обработчик просмотра документа
   *
   * @param event - событие
   */
  public void onActionViewConfirmDocument(WidgetEvent event) {
    DocumentUtils.viewDocument(((Liability) getEntity()).getDocHead());
  }

  /**
   * Обработчик просмотра/выбора контракта
   *
   * @param event - событие
   */
  public void onActionViewOrChooseContract(WidgetEvent event) {
    final Liability liability = (Liability) getEntity();
    if (liability.getContract() != null) {
      DocumentUtils.viewDocument(liability.getContract());
    } else
      DocumentUtils.chooseContract(new SearchHelpListener() {

        /* (non-Javadoc)
         * @see com.mg.framework.api.ui.SearchHelpListener#searchCanceled(com.mg.framework.api.ui.SearchHelpEvent)
         */
        public void searchCanceled(SearchHelpEvent event) {
          // do nothing
        }

        /* (non-Javadoc)
         * @see com.mg.framework.api.ui.SearchHelpListener#searchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
         */
        public void searchPerformed(SearchHelpEvent event) {
          DocHead contract = (DocHead) event.getItems()[0];
          liability.setContractType(contract.getDocType());
          liability.setContractNumber(contract.getDocNumber().trim());
          liability.setContractDate(contract.getDocDate());
          view.flushModel();
        }
      });
  }

}
