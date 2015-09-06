/*
 * BankDocumentInServiceBean.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.account.BankDocumentInServiceLocal;
import com.mg.merp.account.BankDocumentModelInServiceLocal;
import com.mg.merp.account.model.BankDocument;
import com.mg.merp.document.Configuration;
import com.mg.merp.document.generic.DocumentServiceBean;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Входящие банковские документы"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: BankDocumentInServiceBean.java,v 1.7 2007/11/08 12:18:48 sharapov Exp $
 */
@Stateless(name = "merp/account/BankDocumentInService") //$NON-NLS-1$
public class BankDocumentInServiceBean extends DocumentServiceBean<BankDocument, Integer, BankDocumentModelInServiceLocal> implements BankDocumentInServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentServiceBean#doGetConfiguration()
   */
  @Override
  protected Configuration doGetConfiguration() {
    return ConfigurationHelper.getDocumentConfiguration();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, BankDocument entity) {
    super.onValidate(context, entity);
    context.addRule(new MandatoryAttribute(entity, "To")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "From")); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.DocumentServiceBean#getDocSectionIdentifier()
   */
  @Override
  protected int getDocSectionIdentifier() {
    return BankDocumentInServiceLocal.DOCSECTION;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.BankDocumentInServiceLocal#calculateDocSum(com.mg.merp.account.model.BankDocument)
   */
  @PermitAll
  public void calculateDocSum(BankDocument bankDocument) {
    doCalculateDocSum(bankDocument);
  }

  protected void doCalculateDocSum(BankDocument bankDocument) {
    DefaultBankDocumentPropertiesCalculationStrategy strategy = new DefaultBankDocumentPropertiesCalculationStrategy(bankDocument, getConfiguration().getCurrencyScale());
    strategy.calculateDocSum();
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.BankDocumentInServiceLocal#calculateNdsSum(com.mg.merp.account.model.BankDocument)
   */
  @PermitAll
  public void calculateNdsSum(BankDocument bankDocument) {
    doCalculateNdsSum(bankDocument);
  }

  protected void doCalculateNdsSum(BankDocument bankDocument) {
    DefaultBankDocumentPropertiesCalculationStrategy strategy = new DefaultBankDocumentPropertiesCalculationStrategy(bankDocument, getConfiguration().getCurrencyScale());
    strategy.calculateNdsSum();
  }

}
