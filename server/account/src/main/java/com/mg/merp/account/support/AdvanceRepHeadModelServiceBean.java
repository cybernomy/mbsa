/*
 * AdvanceRepHeadModelServiceBean.java
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

import com.mg.merp.account.AdvanceRepHeadModelServiceLocal;
import com.mg.merp.account.AdvanceRepHeadServiceLocal;
import com.mg.merp.account.model.AdvanceRepHeadModel;
import com.mg.merp.document.generic.DocumentModelServiceBean;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Образцы авансовых отчетов"
 *
 * @author leonova
 * @version $Id: AdvanceRepHeadModelServiceBean.java,v 1.3 2006/09/12 11:16:43 leonova Exp $
 */
@Stateless(name = "merp/account/AdvanceRepHeadModelService")
public class AdvanceRepHeadModelServiceBean extends DocumentModelServiceBean<AdvanceRepHeadModel, Integer> implements AdvanceRepHeadModelServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentModelServiceBean#getDocSectionIdentifier()
   */
  @Override
  protected short getDocSectionIdentifier() {
    return AdvanceRepHeadServiceLocal.DOCSECTION;
  }


}
