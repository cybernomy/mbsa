/*
 * InvoiceSpecOutServiceBean.java
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

package com.mg.merp.warehouse.support;

import com.mg.merp.warehouse.InvoiceHeadOutServiceLocal;
import com.mg.merp.warehouse.InvoiceSpecOutServiceLocal;
import com.mg.merp.warehouse.generic.AbstractWarehouseDocumentSpec;
import com.mg.merp.warehouse.model.InvoiceSpec;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Спецификация исходящих накладных"
 *
 * @author leonova
 * @version $Id: InvoiceSpecOutServiceBean.java,v 1.6 2007/09/07 12:27:13 safonov Exp $
 */
@Stateless(name = "merp/warehouse/InvoiceSpecOutService")
public class InvoiceSpecOutServiceBean extends AbstractWarehouseDocumentSpec<InvoiceSpec> implements InvoiceSpecOutServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#getDocSectionIdentifier()
   */
  @Override
  protected int getDocSectionIdentifier() {
    return InvoiceHeadOutServiceLocal.DOCSECTION;
  }

}
