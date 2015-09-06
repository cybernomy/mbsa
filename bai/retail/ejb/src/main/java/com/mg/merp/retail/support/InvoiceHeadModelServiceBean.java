/*
 * InvoiceHeadModelServiceBean.java
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

package com.mg.merp.retail.support;

import com.mg.merp.document.generic.DocumentModelServiceBean;
import com.mg.merp.retail.InvoiceHeadModelServiceLocal;
import com.mg.merp.retail.InvoiceHeadServiceLocal;
import com.mg.merp.retail.model.RtlInvoiceHeadModel;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Образцы документов на отпуск"
 *
 * @author leonova
 * @version $Id: InvoiceHeadModelServiceBean.java,v 1.3 2006/09/12 11:05:41 leonova Exp $
 */
@Stateless(name = "merp/retail/InvoiceHeadModelService")
public class InvoiceHeadModelServiceBean extends DocumentModelServiceBean<RtlInvoiceHeadModel, Integer> implements InvoiceHeadModelServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentModelServiceBean#getDocSectionIdentifier()
   */
  @Override
  protected short getDocSectionIdentifier() {
    return InvoiceHeadServiceLocal.DOCSECTION;
  }


}
