/*
 * BankDocumentModelOtherServiceBean.java
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

import com.mg.merp.account.BankDocumentModelOtherServiceLocal;
import com.mg.merp.account.BankDocumentOtherServiceLocal;
import com.mg.merp.account.model.BankDocumentModel;
import com.mg.merp.document.generic.DocumentModelServiceBean;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Образцы прочих банковских документов"
 *
 * @author leonova
 * @version $Id: BankDocumentModelOtherServiceBean.java,v 1.3 2006/09/12 11:16:43 leonova Exp $
 */
@Stateless(name = "merp/account/BankDocumentModelOtherService")
public class BankDocumentModelOtherServiceBean extends DocumentModelServiceBean<BankDocumentModel, Integer> implements BankDocumentModelOtherServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentModelServiceBean#getDocSectionIdentifier()
   */
  @Override
  protected short getDocSectionIdentifier() {
    return BankDocumentOtherServiceLocal.DOCSECTION;
  }


}
