/*
 * EconomicSpecServiceBean.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.validator.Rule;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.account.EconomicSpecServiceLocal;
import com.mg.merp.account.PeriodServiceLocal;
import com.mg.merp.account.PermissibleAccountsServiceLocal;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.EconomicOper;
import com.mg.merp.account.model.EconomicSpec;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Спецификация хозяйственных операций"
 *
 * @author leonova
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: EconomicSpecServiceBean.java,v 1.8 2009/03/04 12:38:14 sharapov Exp $
 */
@Stateless(name = "merp/account/EconomicSpecService")
public class EconomicSpecServiceBean extends AbstractPOJODataBusinessObjectServiceBean<EconomicSpec, Integer> implements EconomicSpecServiceLocal {

  /**
   * проверка периода на возможность изменения
   *
   * @param operDate дата ХО
   */
  private void checkPeriod(EconomicSpec spec) {
    ((PeriodServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/Period")).checkPeriod(getPersistentManager().find(EconomicOper.class, spec.getEconomicOper().getId()).getKeepDate());
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onCreate(EconomicSpec entity) {
    checkPeriod(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onErase(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onErase(EconomicSpec entity) {
    checkPeriod(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onStore(EconomicSpec entity) {
    checkPeriod(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, final EconomicSpec entity) {
    PersistentManager pm = getPersistentManager();
    final Messages msg = Messages.getInstance();
    EconomicOper economicOper = null;

    AccPlan accPlanKt = entity.getAccKt() == null ? null : pm.find(AccPlan.class, entity.getAccKt().getId());
    AccPlan accPlanDb = entity.getAccDb() == null ? null : pm.find(AccPlan.class, entity.getAccDb().getId());

    boolean isValidateMaterialKt = accPlanKt == null ? false : accPlanKt.isMaterialAcc();
    boolean isValidateMaterialDb = accPlanDb == null ? false : accPlanDb.isMaterialAcc();
    if (isValidateMaterialKt || isValidateMaterialDb) {
      context.addRule(new MandatoryAttribute(entity, "Catalog"));
      economicOper = pm.find(EconomicOper.class, entity.getEconomicOper().getId());
    }
    if (isValidateMaterialKt) {
      context.addRule(new MandatoryAttribute(economicOper, "To") {

        /* (non-Javadoc)
         * @see com.mg.framework.generic.validator.EntityBeanRule#getMessage()
         */
        @Override
        public String getMessage() {
          return msg.getMessage(Messages.IN_OPERATION_MSG_PATTERN, new Object[]{super.getMessage()});
        }
      });
    }
    if (isValidateMaterialDb) {
      context.addRule(new MandatoryAttribute(economicOper, "From") {

        /* (non-Javadoc)
         * @see com.mg.framework.generic.validator.EntityBeanRule#getMessage()
         */
        @Override
        public String getMessage() {
          return msg.getMessage(Messages.IN_OPERATION_MSG_PATTERN, new Object[]{super.getMessage()});
        }
      });
    }

    boolean isValidateBalancedDb = accPlanKt == null ? true : accPlanKt.isBal();
    boolean isValidateBalancedKt = accPlanDb == null ? true : accPlanDb.isBal();
    if (isValidateBalancedKt)
      context.addRule(new MandatoryAttribute(entity, "AccKt"));
    if (isValidateBalancedDb)
      context.addRule(new MandatoryAttribute(entity, "AccDb"));

    if (isValidateBalancedDb || isValidateBalancedKt) {
      context.addRule(new Rule() {

        private PermissibleAccountsServiceLocal permissibleAccountsService = (PermissibleAccountsServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/PermissibleAccounts");

        /* (non-Javadoc)
         * @see com.mg.framework.api.validator.Rule#getMessage()
         */
        public String getMessage() {
          return null;
        }

        /* (non-Javadoc)
         * @see com.mg.framework.api.validator.Rule#validate(com.mg.framework.api.validator.ValidationContext)
         */
        public void validate(ValidationContext context) {
          try {
            permissibleAccountsService.ckeckPermissibleCorrespondance(entity);
          } catch (BusinessException be) {
            context.getStatus().error(this, be.getMessage());
          }
        }
      });
    }
  }

}
