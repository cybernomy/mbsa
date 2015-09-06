/*
 * CurrentStockSituationImpl.java
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
package com.mg.merp.warehouse.support;

import com.mg.framework.api.Logger;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.MiscUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.reference.CurrentStockSituation;
import com.mg.merp.reference.StockSituationValues;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.OrgUnit;
import com.mg.merp.warehouse.WareCardServiceLocal;
import com.mg.merp.warehouse.WarehouseServiceLocal;
import com.mg.merp.warehouse.model.StockCard;
import com.mg.merp.warehouse.model.Warehouse;
import com.mg.merp.warehouse.support.ui.CurrentStockSituationForm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Реализация сервиса рассчёта количества на складах
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: CurrentStockSituationImpl.java,v 1.8 2008/09/09 12:27:55 sharapov Exp $
 */
public class CurrentStockSituationImpl implements CurrentStockSituation {

  private Logger logger = ServerUtils.getLogger(getClass());

  /**
   * реализация получения текущего состояния склада
   */
  private StockSituationValues internalGetSituation(OrgUnit warehouse, Contractor mol, Catalog catalog, boolean onlyAvailable, boolean byContractor) {
    WareCardServiceLocal whbServ = (WareCardServiceLocal) ApplicationDictionaryLocator.locate()
        .getBusinessService(WareCardServiceLocal.LOCAL_SERVICE_NAME);
    StockCard stockCard = null;
    //учитываем МОЛ
    if (byContractor)
      stockCard = whbServ.findStockCard(warehouse, mol, catalog, onlyAvailable);
    else
      stockCard = whbServ.findStockCard(warehouse, catalog, onlyAvailable);

    if (stockCard != null)
      return calculateAvailable(stockCard);
    else
      return null;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.reference.CurrentStockSituation#getSituation(com.mg.merp.reference.model.Catalog)
   */
  public List<StockSituationValues> getSituation(Catalog catalog) {
    if (catalog == null)
      throw new IllegalArgumentException("Catalog entity can't be null");
    else
      return internalGetSituation(catalog.getId());
  }

  /**
   * Рассчёт количества на всех складах
   *
   * @return количество на складах (может быть <code>null</code>)
   */
  private List<StockSituationValues> internalGetSituation(Integer catalogId) {
    //поиск всех КСУ, доступных пользователю, для данной позиции каталога
    StringBuilder query = new StringBuilder()
        .append("select sc.Stock.Id, sum(sc.Quantity), sum(sc.Quantity2),")
        .append(" sum(sc.PlanIn), sum(sc.PlanIn2),")
        .append(" sum(sc.PlanOut), sum(sc.PlanOut2),")
        .append(" sum(sc.Reserve), sum(sc.Reserve2)")
        .append(" from StockCard sc where sc.Catalog.Id = :catalogId and ")
        .append(DatabaseUtils.generateFlatBrowseWhereEJBQL("sc.Stock", 4))
        .append(" group by (sc.Stock.Id)");

    List<Object[]> rsltL = MiscUtils.convertUncheckedList(Object[].class, OrmTemplate.getInstance().findByNamedParam(new String(query), "catalogId", catalogId));

    if (!rsltL.isEmpty()) {
      List<StockSituationValues> result = new ArrayList<StockSituationValues>();

      WarehouseServiceLocal whServ = (WarehouseServiceLocal) ApplicationDictionaryLocator
          .locate().getBusinessService(WarehouseServiceLocal.LOCAL_SERVICE_NAME);
      for (Object[] oa : rsltL) {
        StockCard tmpStockCard = new StockCard();

        tmpStockCard.setStock(whServ.load((Integer) oa[0]));
        tmpStockCard.setQuantity((BigDecimal) oa[1]);
        tmpStockCard.setQuantity2((BigDecimal) oa[2]);

        tmpStockCard.setPlanIn((BigDecimal) oa[3]);
        tmpStockCard.setPlanIn2((BigDecimal) oa[4]);

        tmpStockCard.setPlanOut((BigDecimal) oa[5]);
        tmpStockCard.setPlanOut2((BigDecimal) oa[6]);

        tmpStockCard.setReserve((BigDecimal) oa[7]);
        tmpStockCard.setReserve2((BigDecimal) oa[8]);

        result.add(calculateAvailable(tmpStockCard));
      }
      return result;
    } else
      return null;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.reference.CurrentStockSituation#getAgregateSituation(com.mg.merp.reference.model.Catalog)
   */
  public StockSituationValues getAgregateSituation(Catalog catalog) {
    if (catalog == null)
      throw new IllegalArgumentException("Catalog entity can't be null");
    else
      return getAgregateSituation(catalog.getId());
  }

  /* (non-Javadoc)
   * @see com.mg.merp.reference.CurrentStockSituation#getAgregateSituation(java.lang.Integer)
   */
  public StockSituationValues getAgregateSituation(Integer catalogId) {
    BigDecimal available1 = BigDecimal.ZERO;
    BigDecimal available2 = BigDecimal.ZERO;
    BigDecimal located1 = BigDecimal.ZERO;
    BigDecimal located2 = BigDecimal.ZERO;
    BigDecimal planningReceipt1 = BigDecimal.ZERO;
    BigDecimal planningReceipt2 = BigDecimal.ZERO;
    BigDecimal planningIssue1 = BigDecimal.ZERO;
    BigDecimal planningIssue2 = BigDecimal.ZERO;
    BigDecimal reserved1 = BigDecimal.ZERO;
    BigDecimal reserved2 = BigDecimal.ZERO;
    List<StockSituationValues> stockSituationValues = internalGetSituation(catalogId);
    if (stockSituationValues != null) {
      for (StockSituationValues situationValues : stockSituationValues) {
        if (situationValues.getAvailable1() != null)
          available1 = available1.add(situationValues.getAvailable1());
        if (situationValues.getAvailable2() != null)
          available2 = available2.add(situationValues.getAvailable2());

        if (situationValues.getLocated1() != null)
          located1 = located1.add(situationValues.getLocated1());
        if (situationValues.getLocated2() != null)
          located2 = located2.add(situationValues.getLocated2());

        if (situationValues.getPlanningReceipt1() != null)
          planningReceipt1 = planningReceipt1.add(situationValues.getPlanningReceipt1());
        if (situationValues.getPlanningReceipt2() != null)
          planningReceipt2 = planningReceipt2.add(situationValues.getPlanningReceipt2());

        if (situationValues.getPlanningIssue1() != null)
          planningIssue1 = planningIssue1.add(situationValues.getPlanningIssue1());
        if (situationValues.getPlanningIssue2() != null)
          planningIssue2 = planningIssue2.add(situationValues.getPlanningIssue2());

        if (situationValues.getReserved1() != null)
          reserved1 = reserved1.add(situationValues.getReserved1());
        if (situationValues.getReserved2() != null)
          reserved2 = reserved2.add(situationValues.getReserved2());
      }
    }
    return new StockSituationValuesImpl(null, available1, available2, located1, located2, planningReceipt1, planningReceipt2, planningIssue1, planningIssue2, reserved1, reserved2);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.reference.CurrentStockSituation#getSituation(com.mg.merp.reference.model.OrgUnit, com.mg.merp.reference.model.Contractor, com.mg.merp.reference.model.Catalog)
   */
  public StockSituationValues getSituation(OrgUnit warehouse, Contractor mol, Catalog catalog) {
        /*WareCardServiceLocal whbServ = (WareCardServiceLocal) ApplicationDictionaryLocator.locate()
        .getBusinessService(WareCardServiceLocal.LOCAL_SERVICE_NAME);
		StockCard stockCard = whbServ.findStockCard(warehouse, mol, catalog, true);

		if (stockCard != null) 
			return calculateAvailable(stockCard);
		else
			return null;*/
    return internalGetSituation(warehouse, mol, catalog, true, true);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.reference.CurrentStockSituation#getSituation(com.mg.merp.reference.model.OrgUnit, com.mg.merp.reference.model.Catalog, boolean)
   */
  public StockSituationValues getSituation(OrgUnit warehouse, Catalog catalog, boolean onlyAvailable) {
    return internalGetSituation(warehouse, null, catalog, onlyAvailable, false);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.reference.CurrentStockSituation#getSituation(com.mg.merp.reference.model.OrgUnit, com.mg.merp.reference.model.Catalog)
   */
  public StockSituationValues getSituation(OrgUnit warehouse, Catalog catalog) {
    return internalGetSituation(warehouse, null, catalog, true, false);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.reference.CurrentStockSituation#getSituation(com.mg.merp.reference.model.OrgUnit, com.mg.merp.reference.model.Contractor, com.mg.merp.reference.model.Catalog, boolean)
   */
  public StockSituationValues getSituation(OrgUnit warehouse, Contractor mol, Catalog catalog, boolean onlyAvailable) {
    return internalGetSituation(warehouse, null, catalog, onlyAvailable, true);
  }

  /**
   * расчет текущего состояния по позиции склада
   */
  private StockSituationValues calculateAvailable(StockCard stockCard) {
    Warehouse warehouse = (Warehouse) stockCard.getStock();
    StockSituationValuesImpl quan = new StockSituationValuesImpl();
    quan.setWarehouse(warehouse);
    BigDecimal available = BigDecimal.ZERO;
    BigDecimal available2 = BigDecimal.ZERO;

    //фактическое количество
    BigDecimal fact = stockCard.getQuantity();
    BigDecimal fact2 = stockCard.getQuantity2();
    quan.setLocated1(fact);
    quan.setLocated2(fact2);
    //учитываем ли фактическое количество
    if (warehouse.getCalcFact()) {
      //с каким знаком учитываем
      if (warehouse.getCalcFactSign()) {
        available = fact != null ? fact.negate() : available;
        available2 = fact2 != null ? fact2.negate() : available2;
      } else {
        available = fact != null ? fact : available;
        available2 = fact2 != null ? fact2 : available2;
      }
    }

    //плановый приход
    BigDecimal planArrival = stockCard.getPlanIn();
    BigDecimal planArrival2 = stockCard.getPlanIn2();
    quan.setPlanningReceipt1(planArrival);
    quan.setPlanningReceipt2(planArrival2);
    //учитываем ли плановый приход
    if (warehouse.getCalcPlanIn()) {
      //с каким знаком учитываем
      if (warehouse.getCalcPlanInSign()) {
        planArrival = planArrival != null ? planArrival.negate() : null;
        planArrival2 = planArrival2 != null ? planArrival2.negate() : null;
      }
      if (planArrival != null)
        available = available.add(planArrival);
      if (planArrival2 != null)
        available2 = available2.add(planArrival2);
    }

    //плановый расход
    BigDecimal planDisposal = stockCard.getPlanOut();
    BigDecimal planDisposal2 = stockCard.getPlanOut2();
    quan.setPlanningIssue1(planDisposal);
    quan.setPlanningIssue2(planDisposal2);
    //учитываем ли плановый расход
    if (warehouse.getCalcPlanOut()) {
      //с каким знаком учитываем
      if (warehouse.getCalcPlanOutSign()) {
        planDisposal = planDisposal != null ? planDisposal.negate() : null;
        planDisposal2 = planDisposal2 != null ? planDisposal2.negate() : null;
      }
      if (planDisposal != null)
        available = available.add(planDisposal);
      if (planDisposal2 != null)
        available2 = available2.add(planDisposal2);
    }

    //зарезервировано
    BigDecimal reserve = stockCard.getReserve();
    BigDecimal reserve2 = stockCard.getReserve2();
    quan.setReserved1(reserve);
    quan.setReserved2(reserve2);
    //учитываем ли зарезервировано
    if (warehouse.getCalcReserve()) {
      //с каким знаком учитываем
      if (warehouse.getCalcReserveSign()) {
        reserve = reserve != null ? reserve.negate() : null;
        reserve2 = reserve2 != null ? reserve2.negate() : null;
      }
      if (reserve != null)
        available = available.add(reserve);
      if (reserve2 != null)
        available2 = available2.add(reserve2);
    }

    quan.setAvailable1(available);
    quan.setAvailable2(available2);

    return quan;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.reference.CurrentStockSituation#showSituationForm(com.mg.merp.reference.model.Catalog)
   */
  public void showSituationForm(Catalog catalog) {
    if (catalog == null)
      return;

    CurrentStockSituationForm dialog = (CurrentStockSituationForm) UIProducer.produceForm(CurrentStockSituationForm.FORM_PATH);
    dialog.execute(catalog);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.reference.CurrentStockSituation#showSituationForm(java.io.Serializable[])
   */
  public void showSituationForm(Serializable[] catalogIds) {
    if (catalogIds == null || catalogIds.length == 0)
      return;

    for (Serializable id : catalogIds)
      showSituationForm(ServerUtils.getPersistentManager().find(Catalog.class, id));
  }

  /**
   * @return the logger
   */
  public Logger getLogger() {
    return this.logger;
  }

}
