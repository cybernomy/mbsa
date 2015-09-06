/*
 * MRPRecommendationServiceBean.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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

package com.mg.merp.planning.support;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.mfreference.support.ConfigurationHelper;
import com.mg.merp.planning.FirmPlannedOrderServiceLocal;
import com.mg.merp.planning.MRPRecommendationServiceLocal;
import com.mg.merp.planning.model.MrpRecommendation;
import com.mg.merp.planning.model.MrpVersionControl;
import com.mg.merp.planning.model.RecommendType;
import com.mg.merp.planning.model.RescheduleFlag;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.warehouse.OrderHeadSupServiceLocal;
import com.mg.merp.warehouse.OrderSpecSupServiceLocal;
import com.mg.merp.warehouse.model.OrderHead;
import com.mg.merp.warehouse.model.OrderStatus;
import com.mg.merp.warehouse.support.CreateOrderSpecificationInfoImpl;

import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Рекомендации ППМ"
 *
 * @author Oleg V. Safonov
 * @author leonova
 * @version $Id: MRPRecommendationServiceBean.java,v 1.5 2007/07/30 10:36:48 safonov Exp $
 */
@Stateless(name = "merp/planning/MRPRecommendationService")
@PermitAll
public class MRPRecommendationServiceBean extends AbstractPOJODataBusinessObjectServiceBean<MrpRecommendation, Integer> implements MRPRecommendationServiceLocal {

  private void firm(MrpRecommendation recommendation) {
    recommendation.setFirmPlanSuggestedOrder(true);
  }

  /**
   * создание заказа поставщику
   */
  private void createOrder(OrderItem orderItem, List<MrpRecommendation> recommends, OrderHeadSupServiceLocal orderService, OrderSpecSupServiceLocal orderSpecService) {
    if (recommends.isEmpty())
      return;

    OrderHead orderHead = (OrderHead) orderService.createByPattern(ConfigurationHelper.getConfiguration().getPurchaseOrderModelMrpRec(), null);
    orderHead.setDocDate(orderItem.orderDate);
    orderHead.setTo(orderItem.vendor);
    orderHead.setFixedInput(true);
    orderService.create(orderHead);
    getPersistentManager().flush();

    List<CreateOrderSpecificationInfoImpl> goodsInfoList = new ArrayList<CreateOrderSpecificationInfoImpl>();
    for (MrpRecommendation recommend : recommends) {
      goodsInfoList.add(new CreateOrderSpecificationInfoImpl(
          recommend.getCatalog().getId(),
          null,
          null,
          recommend.getOrderQty(),
          null,
          recommend.getRequiredDate(),
          null,
          null,
          recommend.getWarehouse(),
          null,
          null,
          null,
          null,
          null,
          recommend.getOrderQty(),
          false,
          OrderStatus.ORDERED));
    }
    orderSpecService.bulkCreate(orderHead, goodsInfoList.toArray(new CreateOrderSpecificationInfoImpl[goodsInfoList.size()]));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, MrpRecommendation entity) {
    if (entity.getPurchaseOrTransfer() != null)
      switch (entity.getPurchaseOrTransfer()) {
        case PURCHASE:
          context.addRule(new MandatoryAttribute(entity, "Vendor"));
          break;
        case TRANSFER:
          context.addRule(new MandatoryAttribute(entity, "SourceWarehouse"));
          break;
      }
  }

  /* (non-Javadoc)
   * @see com.mg.merp.planning.MRPRecommendationServiceLocal#createOrder(int)
   */
  public void createOrder(int mrpVersionId) {
    List<MrpRecommendation> recommends = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(MrpRecommendation.class)
        .add(Restrictions.eq("MrpVersionControl.Id", mrpVersionId))
        .add(Restrictions.eq("MrpRescheduleFlag", RescheduleFlag.SUGGESTED))
        .add(Restrictions.eq("FirmPlanSuggestedOrder", true))
        .add(Restrictions.eq("PurchaseOrTransfer", RecommendType.PURCHASE))
        .add(Restrictions.eq("MrpOrdered", false)));

    try {
      Map<OrderItem, List<MrpRecommendation>> groupedRecommends = new HashMap<OrderItem, List<MrpRecommendation>>();
      for (MrpRecommendation recommend : recommends) {
        //группируем по "поставщику" и "дате заказа"
        OrderItem orderItem = new OrderItem(recommend.getVendor(), recommend.getOrderDate());
        List<MrpRecommendation> list = groupedRecommends.get(orderItem);
        if (list == null) {
          list = new ArrayList<MrpRecommendation>();
          groupedRecommends.put(orderItem, list);
        }
        list.add(recommend);

        //проставим отметку на рекомендациях MRP
        recommend.setMrpOrdered(true);
      }

      if (groupedRecommends.isEmpty())
        return;

      //заказ поставщику
      OrderHeadSupServiceLocal orderService = (OrderHeadSupServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(OrderHeadSupServiceLocal.SERVICE_NAME);
      OrderSpecSupServiceLocal orderSpecService = (OrderSpecSupServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(OrderSpecSupServiceLocal.SERVICE_NAME);

      for (OrderItem orderItem : groupedRecommends.keySet())
        createOrder(orderItem, groupedRecommends.get(orderItem), orderService, orderSpecService);

      //создаем заказы на перемещение
      ((FirmPlannedOrderServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(FirmPlannedOrderServiceLocal.SERVICE_NAME)).createByMrpRecommendation(mrpVersionId);
    } catch (ApplicationException e) {
      ServerUtils.setTransactionRollbackOnly();
      throw e;
    }
  }

  /* (non-Javadoc)
   * @see com.mg.merp.planning.MRPRecommendationServiceLocal#clear(com.mg.merp.planning.model.MrpVersionControl)
   */
  public void clear(MrpVersionControl mrpVersion) {
    OrmTemplate.getInstance().bulkUpdateByNamedQuery("Planing.MRPRecommendation.clear", "mrpVersionControl", mrpVersion);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.planning.MRPRecommendationServiceLocal#firm(int[])
   */
  public void firm(Serializable[] recommends) {
    for (Serializable id : recommends)
      firm(load((Integer) id));
  }

  class OrderItem {
    private Contractor vendor;
    private Date orderDate;

    private OrderItem(Contractor vendor, Date orderDate) {
      super();
      this.vendor = vendor;
      this.orderDate = orderDate;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
      return HashCodeBuilder.reflectionHashCode(this);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
      OrderItem key = (OrderItem) obj;
      return this.vendor.getId() == key.vendor.getId() && this.orderDate.compareTo(key.orderDate) == 0;
    }

  }

}
