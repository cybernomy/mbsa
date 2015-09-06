/*
 * PaySheetServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.salary.support;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.salary.PaySheetServiceLocal;
import com.mg.merp.salary.model.PaySheet;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Платежные ведомости"
 *
 * @author leonova
 * @version $Id: PaySheetServiceBean.java,v 1.4 2006/08/28 12:44:53 leonova Exp $
 */
@Stateless(name = "merp/salary/PaySheetService")
public class PaySheetServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PaySheet, Integer> implements PaySheetServiceLocal {


  /**
   * @ejb.interface-method view-type = "local"
   */
  public int createCashDocIn(int paySheetId, int docModelId) throws ApplicationException {
    return 0;//((PaySheetDomainImpl) getDomain()).createCashDocIn(paySheetId, docModelId);
  }

  /**
   * @ejb.interface-method view-type = "local"
   */
  public int createCashDocOut(int paySheetId, int docModelId) throws ApplicationException {
    return 0;//((PaySheetDomainImpl) getDomain()).createCashDocOut(paySheetId, docModelId);
  }
}
