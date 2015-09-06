/*
 * BankDocumentOutServiceBean.java
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
import com.mg.merp.account.BankDocumentModelOutServiceLocal;
import com.mg.merp.account.BankDocumentOutServiceLocal;
import com.mg.merp.account.model.BankDocument;
import com.mg.merp.document.Configuration;
import com.mg.merp.document.generic.DocumentServiceBean;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Исходящие банковские документы"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: BankDocumentOutServiceBean.java,v 1.7 2007/11/08 12:18:48 sharapov Exp $
 */
@Stateless(name = "merp/account/BankDocumentOutService") //$NON-NLS-1$
public class BankDocumentOutServiceBean extends DocumentServiceBean<BankDocument, Integer, BankDocumentModelOutServiceLocal> implements BankDocumentOutServiceLocal {

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
    return BankDocumentOutServiceLocal.DOCSECTION;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.BankDocumentOutServiceLocal#calculateDocSum(com.mg.merp.account.model.BankDocument)
   */
  @PermitAll
  public void calculateDocSum(BankDocument entity) {
    doCalculateDocSum(entity);
  }

  protected void doCalculateDocSum(BankDocument entity) {
    DefaultBankDocumentPropertiesCalculationStrategy strategy = new DefaultBankDocumentPropertiesCalculationStrategy(entity, getConfiguration().getCurrencyScale());
    strategy.calculateDocSum();
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.BankDocumentOutServiceLocal#calculateNdsSum(com.mg.merp.account.model.BankDocument)
   */
  @PermitAll
  public void calculateNdsSum(BankDocument entity) {
    doCalculateNdsSum(entity);
  }

  protected void doCalculateNdsSum(BankDocument entity) {
    DefaultBankDocumentPropertiesCalculationStrategy strategy = new DefaultBankDocumentPropertiesCalculationStrategy(entity, getConfiguration().getCurrencyScale());
    strategy.calculateNdsSum();
  }

}
