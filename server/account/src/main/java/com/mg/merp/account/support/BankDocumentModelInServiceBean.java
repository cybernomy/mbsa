/*
 * BankDocumentModelInServiceBean.java
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

package com.mg.merp.account.support;

import com.mg.merp.account.BankDocumentInServiceLocal;
import com.mg.merp.account.BankDocumentModelInServiceLocal;
import com.mg.merp.account.model.BankDocumentModel;
import com.mg.merp.document.generic.DocumentModelServiceBean;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Образцы входящих банковских документов"
 *
 * @author leonova
 * @version $Id: BankDocumentModelInServiceBean.java,v 1.3 2006/09/12 11:16:43 leonova Exp $
 */
@Stateless(name = "merp/account/BankDocumentModelInService")
public class BankDocumentModelInServiceBean extends DocumentModelServiceBean<BankDocumentModel, Integer> implements BankDocumentModelInServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentModelServiceBean#getDocSectionIdentifier()
   */
  @Override
  protected short getDocSectionIdentifier() {
    return BankDocumentInServiceLocal.DOCSECTION;
  }


}
