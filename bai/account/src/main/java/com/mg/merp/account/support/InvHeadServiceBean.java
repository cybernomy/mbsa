/*
 * InvHeadServiceBean.java
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
import com.mg.framework.api.orm.FlushMode;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.account.AccInventoryMoveParams;
import com.mg.merp.account.AccInventoryRetireParams;
import com.mg.merp.account.AccRevaluateParams;
import com.mg.merp.account.InvHeadServiceLocal;
import com.mg.merp.account.InventoryServiceLocal;
import com.mg.merp.account.model.AccKind;
import com.mg.merp.account.model.InvActionKind;
import com.mg.merp.account.model.InvHead;
import com.mg.merp.account.model.InvHistory;
import com.mg.merp.account.model.InvLocation;
import com.mg.merp.account.model.Inventory;
import com.mg.merp.account.model.Okof;
import com.mg.merp.account.model.Period;
import com.mg.merp.core.model.Folder;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Инвентарная картотека"
 *
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: InvHeadServiceBean.java,v 1.5 2008/04/29 05:01:04 alikaev Exp $
 */
@Stateless(name = "merp/account/InvHeadService")
public class InvHeadServiceBean extends AbstractPOJODataBusinessObjectServiceBean<InvHead, Integer> implements InvHeadServiceLocal {

  private InventoryServiceLocal inventoryService = null;

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, InvHead entity) {
    context.addRule(new MandatoryStringAttribute(entity, "GroupNum"));
    context.addRule(new MandatoryStringAttribute(entity, "CardNum"));
    context.addRule(new MandatoryAttribute(entity, "Catalog"));
    context.addRule(new MandatoryAttribute(entity, "Contractor"));
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onStore(InvHead entity) {
    List<Inventory> inventories = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(Inventory.class)
        .add(Restrictions.eq("InvHead", entity)));
    Folder folder = entity.getFolder();
    String groupNum = entity.getGroupNum();
    String cardNum = entity.getCardNum();
    String objNum = entity.getObjNum();
    Okof okof = entity.getOkof();
    InvLocation invLocation = entity.getInvLocation();
    String manufacturer = entity.getManufacturer();
    String model = entity.getModel();
    String serialNum = entity.getSerialNum();
    String passpNum = entity.getPasspNum();
    String inOperDocNum = entity.getInOperDocNum();
    Date inOperDate = entity.getInOperDate();
    Date outOperDate = entity.getOutOperDate();
    String outOperDocNum = entity.getOutOperDocNum();
    boolean complex = entity.getIsComplex();
    boolean common = entity.getIsCommon();
    String comment = entity.getComment();
    Catalog catalog = entity.getCatalog();
    Contractor contractor = entity.getContractor();
    Date incomeDate = entity.getIncomeDate();
    String incomeDocNum = entity.getIncomeDocNum();
    String invName = entity.getInvName();
    for (Inventory inventory : inventories) {
      inventory.setFolder(folder);
      inventory.setGroupNum(groupNum);
      inventory.setCardNum(cardNum);
      inventory.setObjNum(objNum);
      inventory.setOkof(okof);
      inventory.setInvLocation(invLocation);
      inventory.setManufacturer(manufacturer);
      inventory.setModel(model);
      inventory.setSerialNum(serialNum);
      inventory.setPasspNum(passpNum);
      inventory.setInOperDocNum(inOperDocNum);
      inventory.setInOperDate(inOperDate);
      inventory.setOutOperDate(outOperDate);
      inventory.setOutOperDocNum(outOperDocNum);
      inventory.setIsComplex(complex);
      inventory.setIsCommon(common);
      inventory.setComment(comment);
      inventory.setCatalog(catalog);
      inventory.setContractor(contractor);
      inventory.setInvName(invName);
      inventory.setIncomeDate(incomeDate);
      inventory.setIncomeDocNum(incomeDocNum);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.InvHeadServiceLocal#calcAmortization(java.lang.Integer, com.mg.merp.account.model.InvHead, java.util.List)
   */
  public Integer calcAmortization(Short month, List<InvHead> invHeads, AccKind accKind) {
    return doCalcAmortization(month, invHeads, accKind);
  }


  /**
   * Начисление амортизации
   *
   * @param month   - месяц начисления амортизации в абсолютном исчислении (год*12 + месяц)
   * @param invHead - инвентарные карточки
   * @param accKind - вид учета основных средств и нематериальных активов
   */
  protected Integer doCalcAmortization(Short month, List<InvHead> invHeads, AccKind accKind) {
    return getInventoryService().calcAmortization(getInventoryList(invHeads, accKind), month, 0);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.InvHeadServiceLocal#revaluate(java.util.List, com.mg.merp.account.model.AccKind, com.mg.merp.account.AccRevaluateParams)
   */
  public void revaluate(List<InvHead> invHeads, AccKind accKind, AccRevaluateParams accRevaluateParams) {
    doRevaluate(invHeads, accKind, accRevaluateParams);
  }

  /**
   * Переоценка/дооценка
   *
   * @param invHeads           - список инвентарных картотек
   * @param accKind            - вид учета
   * @param accRevaluateParams - параметры для проведения переоценки
   */
  protected void doRevaluate(List<InvHead> invHeads, AccKind accKind, AccRevaluateParams accRevaluateParams) {
    getInventoryService().revaluate(getInventoryList(invHeads, accKind), accRevaluateParams);
  }


  /* (non-Javadoc)
   * @see com.mg.merp.account.InvHeadServiceLocal#rollback(java.util.List, com.mg.merp.account.model.AccKind)
   */
  public void rollback(List<InvHead> invHeads, List<AccKind> accKinds) {
    doRollback(invHeads, accKinds);
  }

  /**
   * Отменить последнее действие
   *
   * @param invHeads - инвентарные картотеки
   * @param accKind  - вид учета
   */
  protected void doRollback(List<InvHead> invHeads, List<AccKind> accKinds) {
    checkRollback(invHeads, accKinds);
    getInventoryService().rollback(getInventoryList(invHeads, accKinds));
  }

  /**
   * Проверка возможности отката
   *
   * @param invHeads - список инвентарных картотек
   * @param accKinds - список видов учета
   */
  private void checkRollback(List<InvHead> invHeads, List<AccKind> accKinds) {
    OrmTemplate ormTemplate = OrmTemplate.getInstance();
    for (InvHead invHead : invHeads) {
      boolean isCommonOperPresent = false;
      boolean isIndividualOperPresent = false;
      boolean isAllAccKinds = true;
      boolean mustRollbackCommonOper = false;
      List<Inventory> inventories = ormTemplate.findByCriteria(OrmTemplate.createCriteria(Inventory.class)
          .add(Restrictions.eq("InvHead", invHead))
          .setFlushMode(FlushMode.MANUAL));
      for (Inventory inventory : inventories) {
        isAllAccKinds = isContainsAccKindList(accKinds, inventory.getAccKind());
        List<InvHistory> invHistories = ormTemplate.findByCriteria(OrmTemplate.createCriteria(InvHistory.class)
            .addOrder(Order.desc("Id"))
            .add(Restrictions.eq("Inventory", inventory))
            .setFlushMode(FlushMode.MANUAL)
            .setMaxResults(1));
        if (!invHistories.isEmpty()) {
          InvHistory invHistory = invHistories.get(0);
          if (invHistory.getKind() == InvActionKind.MOVE || invHistory.getKind() == InvActionKind.RETIRE) {
            isCommonOperPresent = true;
            if (isAllAccKinds)
              mustRollbackCommonOper = true;
          } else
            isIndividualOperPresent = true;
          if (isCommonOperPresent && mustRollbackCommonOper) {
            if (isIndividualOperPresent)
              throw new BusinessException(Messages.getInstance().getMessage(Messages.COMMON_OPER_MUST_BE_LAST));
            if (!isAllAccKinds)
              throw new BusinessException(Messages.getInstance().getMessage(Messages.ALL_ACCKINDS_REQUIRED));
          }
        }
      }
    }
  }

  /**
   * Проверяет принадлежность вида учета к списку видов учета
   *
   * @param accKinds - список видов учета
   * @param accKind  - вид учета
   */
  private boolean isContainsAccKindList(List<AccKind> accKinds, AccKind accKind) {
    for (AccKind accKind2 : accKinds)
      if (accKind.getId() == accKind2.getId())
        return true;
    return false;
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.account.InvHeadServiceLocal#moveInventory(java.util.List, com.mg.merp.account.model.AccKind, com.mg.merp.account.AccInventoryMoveParams)
   */
  public void moveInventory(List<InvHead> invHeads, AccKind accKind, AccInventoryMoveParams params) {
    doMoveInventory(invHeads, accKind, params);
  }

  /**
   * Перемещение
   *
   * @param invHeads - список инвентарных картотек
   * @param accKind  - вид учета
   * @param params   - параметры для проведения операции перемещения инвентарных карточек
   */
  protected void doMoveInventory(List<InvHead> invHeads, AccKind accKind, AccInventoryMoveParams params) {
    getInventoryService().moveInventory(getInventoryList(invHeads, accKind), params);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.account.InvHeadServiceLocal#retire(java.util.List, com.mg.merp.account.model.AccKind, com.mg.merp.account.AccInventoryRetireParams)
   */
  public void retire(List<InvHead> invHeads, AccKind accKind, AccInventoryRetireParams params) {
    doRetire(invHeads, accKind, params);
  }

  /**
   * Списание
   *
   * @param invHeads - список инвентарных картотек
   * @param accKind  - вид учета
   * @param params   - параметры для проведения операции списания инвентарных карточек
   */
  protected void doRetire(List<InvHead> invHeads, AccKind accKind, AccInventoryRetireParams params) {
    getInventoryService().retire(getInventoryList(invHeads, accKind), params);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.account.InvHeadServiceLocal#freeze(java.util.List, com.mg.merp.account.model.AccKind, java.util.Date)
   */
  public void freeze(List<InvHead> invHeads, AccKind accKind, Date freezeDate) {
    doFreeze(invHeads, accKind, freezeDate);
  }

  /**
   * Консервация
   *
   * @param invHeads   - список инвентарных картотек
   * @param accKind    - вид учета
   * @param freezeDate - дата по которую консервируем инвентарную карточку
   */
  protected void doFreeze(List<InvHead> invHeads, AccKind accKind, Date freezeDate) {
    getInventoryService().freeze(getInventoryList(invHeads, accKind), freezeDate);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.account.InvHeadServiceLocal#makeRemains(java.util.List, com.mg.merp.account.model.AccKind, com.mg.merp.account.model.Period)
   */
  public void makeRemains(List<InvHead> invHeads, AccKind accKind, Period period) {
    doMakeRemains(invHeads, accKind, period);
  }

  /**
   * Формирование остатков
   *
   * @param invHeads - список инвентарных картотек
   * @param accKind  - вид учета
   * @param period   - учетный период
   */
  protected void doMakeRemains(List<InvHead> invHeads, AccKind accKind, Period period) {
    getInventoryService().makeRemains(getInventoryList(invHeads, accKind), period);
  }

  /**
   * Возвращает список данных по видам учета
   *
   * @param invHead - инвентарные карточки
   * @param accKind - вид учета основных средств и нематериальных активов
   */
  private List<Inventory> getInventoryList(List<InvHead> invHeads, AccKind accKind) {
    List<Inventory> inventoryList = new ArrayList<Inventory>();
    OrmTemplate ormTemplate = OrmTemplate.getInstance();
    for (InvHead invHead : invHeads) {
      List<Inventory> inventories = ormTemplate.findByCriteria(OrmTemplate.createCriteria(Inventory.class)
          .add(Restrictions.eq("InvHead", invHead))
          .add(Restrictions.eq("AccKind", accKind)));
      inventoryList.addAll(inventories);
    }
    return inventoryList;
  }

  /**
   * Возвращает список данных по видам учета
   *
   * @param invHead - инвентарные карточки
   * @param accKind - вид учета основных средств и нематериальных активов
   */
  private List<Inventory> getInventoryList(List<InvHead> invHeads, List<AccKind> accKinds) {
    List<Inventory> inventoryList = new ArrayList<Inventory>();
    for (AccKind accKind : accKinds)
      inventoryList.addAll(getInventoryList(invHeads, accKind));
    return inventoryList;
  }

  /**
   * Возвращает бизнес-компонент "Данные по видам учета"
   */
  private InventoryServiceLocal getInventoryService() {
    if (inventoryService == null)
      inventoryService = (InventoryServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/Inventory");
    return inventoryService;
  }

}
