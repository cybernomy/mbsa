/*
 * InvoiceHeadModelOutServiceBean.java
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

package com.mg.merp.warehouse.support;

import com.mg.merp.document.generic.DocumentModelServiceBean;
import com.mg.merp.warehouse.InvoiceHeadModelOutServiceLocal;
import com.mg.merp.warehouse.InvoiceHeadOutServiceLocal;
import com.mg.merp.warehouse.model.InvoiceHeadModel;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Образцы исходящих накладных"
 *
 * @author leonova
 * @version $Id: InvoiceHeadModelOutServiceBean.java,v 1.3 2006/09/12 10:48:36 leonova Exp $
 */
@Stateless(name = "merp/warehouse/InvoiceHeadModelOutService")
public class InvoiceHeadModelOutServiceBean extends DocumentModelServiceBean<InvoiceHeadModel, Integer> implements InvoiceHeadModelOutServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentModelServiceBean#getDocSectionIdentifier()
   */
  @Override
  protected short getDocSectionIdentifier() {
    return InvoiceHeadOutServiceLocal.DOCSECTION;
  }


}
