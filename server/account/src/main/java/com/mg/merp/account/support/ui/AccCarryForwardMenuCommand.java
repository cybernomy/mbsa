/*
 * AccCarryForwardMenuCommand.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.ShowComponentBrowse;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.utils.SecurityUtils;
import com.mg.merp.account.RemnAccServiceLocal;
import com.mg.merp.account.RemnAnlServiceLocal;
import com.mg.merp.account.RemnDbKtServiceLocal;
import com.mg.merp.account.RemnValServiceLocal;
import com.mg.merp.account.support.Messages;

import java.util.Map;

/**
 * Контроллер комманды меню "Перенос остатков" бух.учета
 *
 * @author Artem V. Sharapov
 * @version $Id: AccCarryForwardMenuCommand.java,v 1.2 2008/08/15 13:43:36 sharapov Exp $
 */
public class AccCarryForwardMenuCommand extends ShowComponentBrowse {

  public static final String CARRY_FORWARD_METHOD_NAME = "carryForward"; //$NON-NLS-1$
  private AccCarryForwardDialog dialog;

  /* (non-Javadoc)
   * @see com.mg.framework.support.ui.ShowComponentBrowse#execute()
   */
  @Override
  public void execute() throws Exception {
    dialog = (AccCarryForwardDialog) UIProducer.produceForm("com/mg/merp/account/resources/AccCarryForwardDialog.mfd.xml"); //$NON-NLS-1$
    dialog.addOkActionListener(new FormActionListener() {
      public void actionPerformed(FormEvent event) {
        Messages errorMsg = Messages.getInstance();

        if (dialog.getAccPeriodFrom() == null || dialog.getAccPeriodTill() == null)
          throw new BusinessException(errorMsg.getMessage(Messages.ACC_INVALID_PERIOD_RANGE));

        if ((dialog.getAccounts() == null && !dialog.isAllAcc()) &&
            (dialog.getAnlAccounts() == null && !dialog.isAllAnlAcc()) &&
            (dialog.getValAccounts() == null && !dialog.isAllValAcc()) &&
            (dialog.getDbKtAccounts() == null && !dialog.isAllDbKtAcc()))
          throw new BusinessException(errorMsg.getMessage(Messages.ACC_INVALID_ACCOUNTS_LIST));


        if (dialog.getAccounts() != null || dialog.isAllAcc()) {
          RemnAccServiceLocal remnAccService = (RemnAccServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/RemnAcc"); //$NON-NLS-1$
          remnAccService.carryForward(dialog.getAccPeriodFrom(), dialog.getAccPeriodTill(), dialog.isAllAcc(), dialog.getAccounts());
        }

        if (dialog.getAnlAccounts() != null || dialog.isAllAnlAcc()) {
          RemnAnlServiceLocal remnAnlService = (RemnAnlServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/RemnAnl"); //$NON-NLS-1$
          remnAnlService.carryForward(dialog.getAccPeriodFrom(), dialog.getAccPeriodTill(), dialog.isAllAnlAcc(), dialog.getAnlAccounts());
        }

        if (dialog.getValAccounts() != null || dialog.isAllValAcc()) {
          RemnValServiceLocal remnValService = (RemnValServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/RemnVal"); //$NON-NLS-1$
          remnValService.carryForward(dialog.getAccPeriodFrom(), dialog.getAccPeriodTill(), dialog.isAllValAcc(), dialog.getValAccounts());
        }

        if (dialog.getDbKtAccounts() != null || dialog.isAllDbKtAcc()) {
          RemnDbKtServiceLocal remnDbKtService = (RemnDbKtServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/RemnDbKt"); //$NON-NLS-1$
          remnDbKtService.carryForward(dialog.getAccPeriodFrom(), dialog.getAccPeriodTill(), dialog.isAllDbKtAcc(), dialog.getDbKtAccounts());
        }
      }
    });
    dialog.execute();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.support.ui.ShowComponentBrowse#init(java.util.Map)
   */
  @Override
  public void init(Map<String, String> params) {

  }

  /* (non-Javadoc)
   * @see com.mg.framework.support.ui.ShowComponentBrowse#isPermitted()
   */
  @Override
  public boolean isPermitted() {
    return SecurityUtils.tryCheckPermission(new BusinessMethodPermission("merp/account/RemnAcc", CARRY_FORWARD_METHOD_NAME)) //$NON-NLS-1$
        || SecurityUtils.tryCheckPermission(new BusinessMethodPermission("merp/account/RemnAnl", CARRY_FORWARD_METHOD_NAME)) //$NON-NLS-1$
        || SecurityUtils.tryCheckPermission(new BusinessMethodPermission("merp/account/RemnVal", CARRY_FORWARD_METHOD_NAME)) //$NON-NLS-1$
        || SecurityUtils.tryCheckPermission(new BusinessMethodPermission("merp/account/RemnDbKt", CARRY_FORWARD_METHOD_NAME)); //$NON-NLS-1$
  }

}