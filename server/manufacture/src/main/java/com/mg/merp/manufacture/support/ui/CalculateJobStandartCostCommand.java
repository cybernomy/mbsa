/*
 * CalculateJobStandartCostCommand.java
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
package com.mg.merp.manufacture.support.ui;

import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.api.ui.Alert;
import com.mg.framework.api.ui.MenuCommand;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.Dialogs;
import com.mg.framework.support.ui.Dialogs.InputQueryDialogListener;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.SecurityUtils;
import com.mg.merp.manufacture.CostProcessorServiceLocal;
import com.mg.merp.manufacture.support.Messages;

import java.util.Date;
import java.util.Map;

/**
 * @author Oleg V. Safonov
 * @version $Id: CalculateJobStandartCostCommand.java,v 1.2 2008/08/15 14:40:50 sharapov Exp $
 */
public class CalculateJobStandartCostCommand implements MenuCommand {

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MenuCommand#execute()
   */
  public void execute() throws Exception {
    Dialogs.inputQuery(Messages.getInstance().getMessage(Messages.INPUT_DATE), Messages.getInstance().getMessage(Messages.ACTUALITY_DATE), DateTimeUtils.nowDate(), new InputQueryDialogListener<Date>() {

      public void inputCanceled() {
      }

      public void inputPerformed(Date value) {
        ((CostProcessorServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CostProcessorServiceLocal.SERVICE_NAME))
            .calculateJobStandartCost(value);

        UIUtils.showAlert(Alert.MessageType.INFORMATION_MESSAGE, null, Messages.getInstance().getMessage(Messages.CALC_STD_COST_SUCCESSFULLY_COMPLETE),
            com.mg.framework.support.Messages.getInstance().getMessage(com.mg.framework.support.Messages.OK_BUTTON_TEXT));
      }

    });
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MenuCommand#init(java.util.Map)
   */
  public void init(Map<String, String> params) {
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MenuCommand#isPermitted()
   */
  public boolean isPermitted() {
    return SecurityUtils.tryCheckPermission(new BusinessMethodPermission(CostProcessorServiceLocal.SERVICE_NAME, "calculateJobStandartCost")); //$NON-NLS-1$
  }

}
