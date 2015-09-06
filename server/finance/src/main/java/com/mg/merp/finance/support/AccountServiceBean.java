/*
 * AccountServiceBean.java
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

package com.mg.merp.finance.support;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.DataUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.finance.AccountServiceLocal;
import com.mg.merp.finance.AnalyticsServiceLocal;
import com.mg.merp.finance.model.Account;
import com.mg.merp.finance.model.Analytics;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Финансовые счета"
 *
 * @author leonova
 * @version $Id: AccountServiceBean.java,v 1.5 2007/11/08 06:42:17 sharapov Exp $
 */
@Stateless(name = "merp/finance/AccountService") //$NON-NLS-1$
public class AccountServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Account, Integer> implements AccountServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(T)
   */
  @Override
  protected void onInitialize(Account entity) {
    entity.setKind((short) 0);
  }

  private void adjustAccount(Account entity) {
    entity.setUpCode(StringUtils.toUpperCase(entity.getCode()));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(T)
   */
  @Override
  protected void onCreate(Account entity) {
    adjustAccount(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(T)
   */
  @Override
  protected void onStore(Account entity) {
    adjustAccount(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, Account entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Code")); //$NON-NLS-1$
    context.addRule(new MandatoryStringAttribute(entity, "AccName"));         //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onClone(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onClone(Account entity) {
    entity.setCode(DataUtils.generateUniqueString(20));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#doDeepClone(com.mg.framework.api.orm.PersistentObject, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void doDeepClone(Account entity, Account entityClone) {
    final String FIN_ACCOUNT_ATTRIBUTE_NAME = "FinAcc"; //$NON-NLS-1$
    AnalyticsServiceLocal analyticsService = (AnalyticsServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/finance/Analytics"); //$NON-NLS-1$
    AttributeMap initAttributes = new LocalDataTransferObject();
    initAttributes.put(FIN_ACCOUNT_ATTRIBUTE_NAME, entityClone);
    for (Analytics analytica : analyticsService.findByCriteria(Restrictions.eq(FIN_ACCOUNT_ATTRIBUTE_NAME, entity)))
      analyticsService.clone(analytica, true, initAttributes);
  }

  /**
   * @ejb.interface-method view-type = "local"
   */
  public AttributeMap getFullRecord(String code) throws ApplicationException {
    return null;//((AccountDomainImpl) getDomain()).getFullRecord(code);
  }

  /**
   * @ejb.interface-method view-type = "local"
   */
  public int canChangeAnalytics(int accId) throws ApplicationException {
    return 0;//((AccountDomainImpl) getDomain()).canChangeAnalytics(accId);
  }
}
