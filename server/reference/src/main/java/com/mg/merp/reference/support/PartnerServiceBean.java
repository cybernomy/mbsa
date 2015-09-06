/*
 * PartnerServiceBean.java
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

package com.mg.merp.reference.support;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.validator.EntityBeanRule;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.Messages;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.reference.CountryServiceLocal;
import com.mg.merp.reference.PartnerServiceLocal;
import com.mg.merp.reference.generic.ContractorServiceBean;
import com.mg.merp.reference.model.BankAccount;
import com.mg.merp.reference.model.Partner;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Партнеры"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PartnerServiceBean.java,v 1.10 2009/01/12 14:39:21 safonov Exp $
 */
@Stateless(name = "merp/reference/PartnerService") //$NON-NLS-1$
public class PartnerServiceBean extends ContractorServiceBean<Partner> implements PartnerServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.reference.generic.ContractorServiceBean#adjustOrgUnit(T)
   */
  protected void adjustPartner(Partner entity) {
    entity.setUpCode(StringUtils.toUpperCase(entity.getCode()));
  }

  /* (non-Javadoc)
   * @see com.mg.merp.reference.generic.ContractorServiceBean#onCreate(T)
   */
  @Override
  protected void onCreate(Partner entity) {
    adjustPartner(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.reference.generic.ContractorServiceBean#onStore(T)
   */
  @Override
  protected void onStore(Partner entity) {
    adjustPartner(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, final Partner entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Code")); //$NON-NLS-1$
    context.addRule(new MandatoryStringAttribute(entity, "FullName")); //$NON-NLS-1$
    context.addRule(new EntityBeanRule(Messages.getInstance().getMessage(Messages.UNIQUE_VALIDATOR), entity, "Code") { //$NON-NLS-1$
      @Override
      protected void doValidate(ValidationContext context) {
        if (OrmTemplate.getInstance().findUniqueByCriteria(Partner.class, Restrictions.eq("UpCode", StringUtils.toUpperCase(entity.getCode())), Restrictions.ne("Id", entity.getId())) != null) //$NON-NLS-1$ //$NON-NLS-2$
          context.getStatus().error(this);
      }
    });

    Boolean uniqINN = entity.getINNUnique();
    if (uniqINN != null && uniqINN.booleanValue() && !StringUtils.stringNullOrEmpty(entity.getINN())) {
      context.addRule(new EntityBeanRule(Messages.getInstance().getMessage(Messages.UNIQUE_VALIDATOR), entity, "INN") {  //$NON-NLS-1$
        @Override
        protected void doValidate(ValidationContext context) {
          if (OrmTemplate.getInstance().findUniqueByCriteria(Partner.class, Restrictions.eq("INN", entity.getINN()), Restrictions.ne("Id", entity.getId())) != null)  //$NON-NLS-1$ //$NON-NLS-2$
            context.getStatus().error(this);

        }
      });

    }
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(T)
   */
  @Override
  protected void onInitialize(Partner entity) {
    entity.setINN(""); //$NON-NLS-1$
    entity.setOKONH(""); //$NON-NLS-1$
    entity.setOKPO(""); //$NON-NLS-1$
    entity.setCity(""); //$NON-NLS-1$
    entity.setArea(""); //$NON-NLS-1$
    entity.setAddress(""); //$NON-NLS-1$
    entity.setAddressLegal(""); //$NON-NLS-1$
    entity.setCityLegal(""); //$NON-NLS-1$
    entity.setAreaLegal(""); //$NON-NLS-1$
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.reference.PartnerServiceLocal#getFullAddress(com.mg.merp.reference.model.Partner)
   */
  @PermitAll
  public void getFullAddress(Partner partner) {
    doGetFullAddress(partner);
  }

  /**
   * формирует и записывает полный почтовый адрес
   *
   * @param personAddress адрес проживания физического лица
   */
  private void doGetFullAddress(Partner partner) {
    if (partner != null) {
      CountryServiceLocal serviceCountry = (CountryServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CountryServiceLocal.SERVICE_NAME);
      String address = serviceCountry.getAddressText(partner.getCountry(), partner.getRegion()
          , partner.getDistrict(), partner.getPlace(), partner.getStreet(), partner.getHouse()
          , partner.getBuilding(), partner.getRoom(), partner.getZipCode());

      partner.setAddress(address);
      getPersistentManager().merge(partner);
    }
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.reference.PartnerServiceLocal#getFullAddressLegal(com.mg.merp.reference.model.Partner)
   */
  @PermitAll
  public void getFullAddressLegal(Partner partner) {
    doGetFullAddressLegal(partner);
  }

  /**
   * формирует и записывает полный юредический адрес
   *
   * @param personAddress адрес проживания физического лица
   */
  private void doGetFullAddressLegal(Partner partner) {
    if (partner != null) {
      CountryServiceLocal serviceCountry = (CountryServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CountryServiceLocal.SERVICE_NAME);
      String addresslegal = serviceCountry.getAddressText(partner.getCountry1(), partner.getRegion1()
          , partner.getDistrict1(), partner.getPlace1(), partner.getStreet1(), partner.getHouse1()
          , partner.getBuilding1(), partner.getRoom1(), partner.getZipCode1());

      partner.setAddressLegal(addresslegal);
      getPersistentManager().merge(partner);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.merp.reference.PartnerServiceLocal#getDefaultBankAccount(com.mg.merp.reference.model.Partner)
   */
  @PermitAll
  public BankAccount getDefaultBankAccount(Partner partner) {
    return doGetDefaultBankAccount(partner);
  }

  /**
   * Получить банковский счет по умолчанию
   *
   * @param partner - партнер
   * @return банковский счет по умолчанию, или <code>null</code> если не найден
   */
  protected BankAccount doGetDefaultBankAccount(Partner partner) {
    List<BankAccount> bankAccounts = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(BankAccount.class)
        .add(Restrictions.eq("Contractor", partner)) //$NON-NLS-1$
        .add(Restrictions.eq("IsDefault", true))); //$NON-NLS-1$
    if (!bankAccounts.isEmpty())
      return bankAccounts.get(0);
    else
      return null;
  }

}
