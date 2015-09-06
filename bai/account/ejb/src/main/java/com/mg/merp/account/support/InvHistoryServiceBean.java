/*
 * InvHistoryServiceBean.java
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
import com.mg.framework.api.math.RoundContext;
import com.mg.framework.api.orm.FlushMode;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.account.AmortizationServiceLocal;
import com.mg.merp.account.InvHistoryServiceLocal;
import com.mg.merp.account.OperationServiceLocal;
import com.mg.merp.account.model.EconomicOper;
import com.mg.merp.account.model.EconomicSpec;
import com.mg.merp.account.model.InvHead;
import com.mg.merp.account.model.InvHistory;
import com.mg.merp.account.model.InvLocation;
import com.mg.merp.account.model.Inventory;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Бизнес-компонент "История инвентарной карточки"
 *
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: InvHistoryServiceBean.java,v 1.5 2008/04/29 10:30:43 alikaev Exp $
 */
@Stateless(name = "merp/account/InvHistoryService")
public class InvHistoryServiceBean extends AbstractPOJODataBusinessObjectServiceBean<InvHistory, Integer> implements InvHistoryServiceLocal {

  private OperationServiceLocal operationService = null;

  private AmortizationServiceLocal amortizationService = null;

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onErase(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onErase(InvHistory entity) {
    super.onErase(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.account.InvHistoryServiceLocal#rollbackInvHistory(com.mg.merp.account.model.InvHistory)
   */
  @PermitAll
  public void rollbackInvHistory(InvHistory invHistory) {
    doRollbackInvHistory(invHistory);
  }

  /**
   * Откат истории инв.объекта
   *
   * @param invHistory - история инвентарной карточки
   */
  protected void doRollbackInvHistory(InvHistory invHistory) {
    RoundContext roundContext = new RoundContext(ConfigurationHelper.getConfiguration().getCurrencyPrec());
    if (!isLastRecord(invHistory))
      throw new BusinessException(Messages.getInstance().getMessage(Messages.INVALID_ROLLBACK_INVHISTORY));
    Inventory inventory = invHistory.getInventory();
    BigDecimal derpVal = invHistory.getDeltaDeprVal();
    BigDecimal balCost = invHistory.getDeltaBalCost();
    BigDecimal initialLoss = invHistory.getDeltaInitialLoss();
    BigDecimal beginLoss = invHistory.getDeltaBeginLoss();
    erase(invHistory);
    InvHead invHead = inventory.getInvHead();
    EconomicOper eo = invHistory.getEconomicOper();
    switch (invHistory.getKind()) {
      case AMORT:
        getOperationService().erase(eo);
        AccountTurnoverUpdater.execute(eo, true);
        getAmortizationService().erase(invHistory.getAccAmortization());
        InvHistory prevInvHistory = getPrevInvHistory(invHistory);
        inventory.setAmort(MathUtils.addNullable(inventory.getAmort(), derpVal, roundContext));
        inventory.setEndCost(MathUtils.subtractNullable(inventory.getEndCost(), derpVal, roundContext));
        inventory.setAmortDate(prevInvHistory != null ? prevInvHistory.getActDate() : null);
        break;
      case OVERESTIMATION:
        getOperationService().erase(eo);
        AccountTurnoverUpdater.execute(eo, true);
        inventory.setEndCost(MathUtils.subtractNullable(inventory.getEndCost(), balCost, roundContext));
        inventory.setBalanceCost(MathUtils.subtractNullable(inventory.getBalanceCost(), balCost, roundContext));
        break;
      case REVAL:
        getOperationService().erase(eo);
        AccountTurnoverUpdater.execute(eo, true);
        //DBalCost - dbeginloss - dinitialloss - ddeprval
        BigDecimal dendCost = MathUtils.subtractNullable(balCost, MathUtils.addNullable(derpVal, MathUtils.addNullable(beginLoss, initialLoss, roundContext), roundContext), roundContext);
        inventory.setBalanceCost(MathUtils.subtractNullable(inventory.getBalanceCost(), balCost, roundContext));
        inventory.setBeginLoss(MathUtils.subtractNullable(inventory.getBeginLoss(), beginLoss, roundContext));
        inventory.setInitialloss(MathUtils.subtractNullable(inventory.getInitialloss(), initialLoss, roundContext));
        inventory.setAmort(MathUtils.subtractNullable(inventory.getAmort(), derpVal, roundContext));
        inventory.setEndCost(MathUtils.subtractNullable(inventory.getEndCost(), dendCost, roundContext));
        break;
      case FREEZ:
        getAmortizationService().erase(invHistory.getAccAmortization());
        break;
      case MOVE:
        EconomicSpec es = invHistory.getEconomicSpec();
        inventory.setAccPlan(es.getAccKt());
        inventory.setAnl1(es.getAnlKt1());
        inventory.setAnl2(es.getAnlKt2());
        inventory.setAnl3(es.getAnlKt3());
        inventory.setAnl4(es.getAnlKt4());
        inventory.setAnl5(es.getAnlKt5());
        invHead.setContractor(invHistory.getEconomicOper().getFrom());
        if (invHistory.getOldInvLocationId() != null)
          invHead.setInvLocation(ServerUtils.getPersistentManager().find(InvLocation.class, invHistory.getOldInvLocationId()));
        if (!StringUtils.stringNullOrEmpty(invHistory.getOldInOperDocNum())) {
          invHead.setInOperDocNum(invHistory.getOldInOperDocNum());
          invHead.setInOperDate(invHistory.getOldInOperDate());
        }
        getOperationService().erase(eo);
        AccountTurnoverUpdater.execute(eo, true);
        break;
      case RETIRE:
        invHead.setOutOperDocNum(null);
        invHead.setOutOperDate(null);
        getOperationService().erase(eo);
        AccountTurnoverUpdater.execute(eo, true);
        break;
    }
  }

  /**
   * Проверка, является ли история инвентарной карточки последней
   *
   * @param invHistory - инвентарная карточка
   * @return - <code>true</code> - последняя, иначе <code>false</code>
   */
  private boolean isLastRecord(InvHistory invHistory) {
    return findByCriteria(Restrictions.eq("Inventory", invHistory.getInventory()), Restrictions.gt("Id", invHistory.getId())).isEmpty() ? true : false;
  }

  /**
   * Возвращает предыдущую историю инвентарной карточки
   *
   * @param invHistory - история инвентарной карточки
   */
  private InvHistory getPrevInvHistory(InvHistory invHistory) {
    List<InvHistory> invHistories = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(InvHistory.class)
        .add(Restrictions.eq("Inventory", invHistory.getInventory()))
        .add(Restrictions.lt("Id", invHistory.getId()))
        .addOrder(Order.desc("Id"))
        .setFlushMode(FlushMode.MANUAL)
        .setMaxResults(1));
    return invHistories.isEmpty() ? null : invHistories.get(0);
  }

  /**
   * Возвращет бизнес-компонент "Хоз. операция"
   */
  private OperationServiceLocal getOperationService() {
    if (operationService == null)
      operationService = (OperationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/Operation"); //$NON-NLS-1$
    return operationService;
  }

  /**
   * Возвращает бизнес-компонент "Ведомость начисления амортизации"
   */
  private AmortizationServiceLocal getAmortizationService() {
    if (amortizationService == null)
      amortizationService = (AmortizationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/Amortization"); //$NON-NLS-1$
    return amortizationService;
  }

}
