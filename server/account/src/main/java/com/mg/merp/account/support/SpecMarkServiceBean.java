/*
 * SpecMarkServiceBean.java
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

package com.mg.merp.account.support;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.account.SpecMarkServiceLocal;
import com.mg.merp.account.model.SpecMark;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Особые отметки"
 *
 * @author leonova
 * @version $Id: SpecMarkServiceBean.java,v 1.4 2007/01/16 11:42:46 sharapov Exp $
 */
@Stateless(name = "merp/account/SpecMarkService") //$NON-NLS-1$
public class SpecMarkServiceBean extends AbstractPOJODataBusinessObjectServiceBean<SpecMark, String> implements SpecMarkServiceLocal {

  private void adjustSpecMark(SpecMark entity) {
    entity.setUpCode(StringUtils.toUpperCase(entity.getCode()));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(T)
   */
  @Override
  protected void onCreate(SpecMark entity) {
    adjustSpecMark(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(T)
   */
  @Override
  protected void onStore(SpecMark entity) {
    adjustSpecMark(entity);
  }

}
