/*
 * CardServiceBean.java
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

package com.mg.merp.discount.support;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.discount.CardHistServiceLocal;
import com.mg.merp.discount.CardServiceLocal;
import com.mg.merp.discount.CardUserServiceLocal;
import com.mg.merp.discount.CoefficientServiceLocal;
import com.mg.merp.discount.ExtraDiscountServiceLocal;
import com.mg.merp.discount.model.Card;
import com.mg.merp.discount.model.CardHist;
import com.mg.merp.discount.model.CardUser;
import com.mg.merp.discount.model.Coefficient;
import com.mg.merp.discount.model.ExtraDiscount;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Дисконтные карты"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: CardServiceBean.java,v 1.7 2007/11/08 06:38:06 sharapov Exp $
 */
@Stateless(name = "merp/discount/CardService") //$NON-NLS-1$
public class CardServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Card, Integer> implements CardServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, Card entity) {
    context.addRule(new MandatoryStringAttribute(entity, "CardNum")); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.merp.discount.CardServiceLocal#getDiscountFromHistory(com.mg.merp.discount.model.Card, java.util.Date)
   */
  @PermitAll
  public BigDecimal getDiscountFromHistory(Card disCard, Date actualDate) {
    return doGetDiscountFromHistory(disCard, actualDate);
  }

  protected BigDecimal doGetDiscountFromHistory(Card disCard, Date actualDate) {
    BigDecimal discount = BigDecimal.ZERO;
    List<BigDecimal> discounts = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(CardHist.class)
        .setProjection(Projections.property("Discount")) //$NON-NLS-1$
        .add(Restrictions.eq("Card", disCard)) //$NON-NLS-1$
        .add(Restrictions.le("TimeStamp", actualDate)) //$NON-NLS-1$
        .addOrder(Order.desc("TimeStamp"))); //$NON-NLS-1$
    if (!discounts.isEmpty())
      discount = discounts.get(0);

    return discount;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#doDeepClone(com.mg.framework.api.orm.PersistentObject, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void doDeepClone(Card entity, Card entityClone) {
    final String CARD_ATTRIBUTE_NAME = "Card"; //$NON-NLS-1$
    CardUserServiceLocal сardUserService = (CardUserServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CardUserServiceLocal.SERVICE_NAME);
    CardHistServiceLocal cardHistoryService = (CardHistServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CardHistServiceLocal.SERVICE_NAME);
    CoefficientServiceLocal coefficientService = (CoefficientServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CoefficientServiceLocal.SERVICE_NAME);
    ExtraDiscountServiceLocal extraDiscountService = (ExtraDiscountServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(ExtraDiscountServiceLocal.SERVICE_NAME);
    AttributeMap initAttributes = new LocalDataTransferObject();
    initAttributes.put(CARD_ATTRIBUTE_NAME, entityClone);
    // копирование пользователей ДК
    for (CardUser cardUser : сardUserService.findByCriteria(Restrictions.eq(CARD_ATTRIBUTE_NAME, entity)))
      сardUserService.clone(cardUser, true, initAttributes);
    // копирование истории ДК
    for (CardHist cardHist : cardHistoryService.findByCriteria(Restrictions.eq(CARD_ATTRIBUTE_NAME, entity)))
      cardHistoryService.clone(cardHist, true, initAttributes);
    // копирование коэфициентов
    for (Coefficient coefficient : coefficientService.findByCriteria(Restrictions.eq(CARD_ATTRIBUTE_NAME, entity)))
      coefficientService.clone(coefficient, true, initAttributes);
    // копирование доп.скидок
    for (ExtraDiscount extraDiscount : extraDiscountService.findByCriteria(Restrictions.eq(CARD_ATTRIBUTE_NAME, entity)))
      extraDiscountService.clone(extraDiscount, true, initAttributes);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.discount.CardServiceLocal#getCardByOwner(int)
   */
  public AttributeMap getCardByOwner(int ownerId) throws ApplicationException {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.discount.CardServiceLocal#getCardsByContractor(int)
   */
  public int[] getCardsByContractor(int contractorId) throws ApplicationException {
    return null;
  }

}
