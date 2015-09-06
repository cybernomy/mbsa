/*
 * OperationServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */

package com.mg.merp.crm.support;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.crm.OperationServiceLocal;
import com.mg.merp.crm.model.Operation;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Действия"
 *
 * @author leonova
 * @version $Id: OperationServiceBean.java,v 1.4 2006/09/06 05:24:25 leonova Exp $
 */
@Stateless(name = "merp/crm/OperationService")
public class OperationServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Operation, Integer> implements OperationServiceLocal {


  /**
   * @ejb.interface-method view-type = "local"
   */
  public String sendEmail(int[] keys) throws ApplicationException {
    return "";//((OperationDomainImpl) getDomain()).sendEmail(keys);
  }

  /**
   * @ejb.interface-method view-type = "local"
   */
  public String[] getTaskTemplate() throws ApplicationException {
    return null;//((OperationDomainImpl) getDomain()).getTaskTemplate();
  }

}
