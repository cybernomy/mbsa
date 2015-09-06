/*
 * BomServiceBean.java
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

package com.mg.merp.mfreference.support;

import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.utils.MathUtils;
import com.mg.merp.mfreference.BOMServiceLocal;
import com.mg.merp.mfreference.model.Bom;
import com.mg.merp.mfreference.model.BomLabor;
import com.mg.merp.mfreference.model.BomMachine;
import com.mg.merp.mfreference.model.BomRoute;
import com.mg.merp.mfreference.model.BomRouteResource;
import com.mg.merp.mfreference.model.BomType;
import com.mg.merp.mfreference.model.LaborClass;
import com.mg.merp.mfreference.model.TimeRateFlag;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Состав изделий"
 *
 * @author leonova
 * @version $Id: BOMServiceBean.java,v 1.8 2009/03/05 12:26:46 safonov Exp $
 */
@Stateless(name = "merp/mfreference/BOMService")
public class BOMServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Bom, Integer> implements BOMServiceLocal {


  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(T)
   */
  @Override
  protected void onInitialize(Bom entity) {
    entity.setStandartCostDetail(MfUtils.createCostDetail());
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, Bom entity) {
    context.addRule(new MandatoryAttribute(entity, "Catalog"));
    context.addRule(new MandatoryAttribute(entity, "DefSrcMol"));
    context.addRule(new MandatoryAttribute(entity, "DefDstMol"));
    context.addRule(new MandatoryAttribute(entity, "DefDstStock"));
    context.addRule(new MandatoryAttribute(entity, "DefSrcStock"));
    context.addRule(new MandatoryAttribute(entity, "PlanningLotQty"));
    context.addRule(new MandatoryAttribute(entity, "SetupTimeUM"));
    context.addRule(new MandatoryAttribute(entity, "RunTimeUM"));
  }

  private void internalCalculateOperLeadTimes(Date actualityDate, Bom bom) {
    if (bom == null)
      return;

    OrmTemplate ormTemplate = OrmTemplate.getInstance();
    List<BomRoute> routes = ormTemplate.findByCriteria(OrmTemplate.createCriteria(BomRoute.class)
        .add(Restrictions.eq("Bom", bom)));
    for (BomRoute route : routes) {
      long timeSeq = 0, fixedTicks = 0, fixedTicksInSeq = 0, varTicks = 0, varTicksInSeq = 0;

      List<BomRouteResource> resources = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(BomRouteResource.class, "brr")
          .add(Restrictions.eq("brr.BomRoute", route))
          .add(Restrictions.le("brr.EffOnDate", actualityDate))
          .add(Restrictions.ge("brr.EffOffDate", actualityDate))
          .addOrder(Order.asc("brr.TimeSequence")));
      for (BomRouteResource resource : resources) {
        if (timeSeq != resource.getTimeSequence()) {
          timeSeq = resource.getTimeSequence();
          fixedTicks += fixedTicksInSeq; //Накапливаем максимумы
          varTicks += varTicksInSeq; //Накапливаем максимумы
          fixedTicksInSeq = 0;
          varTicksInSeq = 0;
        }
        if (resource instanceof BomLabor) {
          BomLabor bomLabor = (BomLabor) resource;
          LaborClass laborClass = bomLabor.getLaborClass();
          BigDecimal lbrNumber = bomLabor.getLbrNumber();
          if (lbrNumber == null || MathUtils.compareToZero(lbrNumber) == 0)
            lbrNumber = BigDecimal.ONE;
          if (laborClass.getTimeRateFlag() == TimeRateFlag.FIXED) {
            if (bomLabor.getRunTicksLbr() / lbrNumber.longValue() > fixedTicksInSeq)
              fixedTicksInSeq = bomLabor.getRunTicksLbr() / lbrNumber.longValue(); //то есть ищем максимум
          } else {
            //Время на единицу готовой продукции
            if (bomLabor.getRunTicksLbr() / lbrNumber.longValue() > varTicksInSeq)
              varTicksInSeq = bomLabor.getRunTicksLbr() / lbrNumber.longValue(); //то есть ищем максимум
          }
        } else if (resource instanceof BomMachine) {
          BomMachine bomMachine = (BomMachine) resource;
          BigDecimal mchNumber = bomMachine.getMchNumber();
          if (mchNumber == null || MathUtils.compareToZero(mchNumber) == 0)
            mchNumber = BigDecimal.ONE;
          if (bomMachine.getTimeRateFlag() == TimeRateFlag.FIXED) {
            if (bomMachine.getRunTicksMch() / mchNumber.longValue() > fixedTicksInSeq)
              fixedTicksInSeq = bomMachine.getRunTicksMch() / mchNumber.longValue(); //то есть ищем максимум
          } else {
            //Время на единицу готовой продукции
            if (bomMachine.getRunTicksMch() / mchNumber.longValue() > varTicksInSeq)
              varTicksInSeq = bomMachine.getRunTicksMch() / mchNumber.longValue(); //то есть ищем максимум
          }
        }
      }
      fixedTicks = Math.max(fixedTicks, fixedTicksInSeq); //Добавляем последний максимум
      varTicks = Math.max(varTicks, varTicksInSeq); //Добавляем последний максимум

      route.setSetupTicks(fixedTicks);
      route.setRunTicks(varTicks);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.merp.mfreference.BOMServiceLocal#calculateOperLeadTimes(java.util.Date, int[])
   */
  public void calculateOperLeadTimes(Date actualityDate, int[] catalogList) {
    for (int catalogId : catalogList)
      internalCalculateOperLeadTimes(actualityDate, findCurrentBOM(catalogId));
  }

  /* (non-Javadoc)
   * @see com.mg.merp.mfreference.BOMServiceLocal#findCurrentBOM(int)
   */
  @PermitAll
  public Bom findCurrentBOM(int catalogId) {
    return OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(Bom.class)
        .add(Restrictions.eq("Catalog.Id", catalogId))
        .add(Restrictions.eq("BomType", BomType.CURRENT)));
  }

  /* (non-Javadoc)
   * @see com.mg.merp.mfreference.BOMServiceLocal#findStandartBOM(int)
   */
  @PermitAll
  public Bom findStandartBOM(int catalogId) {
    return OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(Bom.class)
        .add(Restrictions.eq("Catalog.Id", catalogId))
        .add(Restrictions.eq("BomType", BomType.STANDART)));
  }

  /* (non-Javadoc)
   * @see com.mg.merp.mfreference.BOMServiceLocal#updateRollupDateTime(com.mg.merp.mfreference.model.Bom, java.util.Date)
   */
  @PermitAll
  public void updateRollupDateTime(Bom bom, Date date) {
    bom = load(bom.getId());
    bom.setRollUpDateTime(date);
    store(bom);
  }

}
