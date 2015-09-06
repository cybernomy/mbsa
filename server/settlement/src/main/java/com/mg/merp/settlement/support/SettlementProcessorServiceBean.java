/*
 * SettlementProcessorServiceBean.java
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

package com.mg.merp.settlement.support;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.math.RoundContext;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.generic.AbstractPOJOBusinessObjectStatefulServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.account.CashDocumentInServiceLocal;
import com.mg.merp.account.CashDocumentOutServiceLocal;
import com.mg.merp.account.model.CashDocument;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.reference.CurrencyRateNotFoundException;
import com.mg.merp.reference.CurrencyRateServiceLocal;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.OrgUnit;
import com.mg.merp.settlement.ContractorCardServiceLocal;
import com.mg.merp.settlement.SettlementProcessorServiceLocal;
import com.mg.merp.settlement.model.ContractorCard;
import com.mg.merp.settlement.model.ContractorCardHist;
import com.mg.merp.settlement.model.ContractorCardPlan;
import com.mg.merp.settlement.model.StlConfig;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 * Реализация процессора модуля "Расчеты с партнерами"
 *
 * @author Artem V. Sharapov
 * @version $Id: SettlementProcessorServiceBean.java,v 1.6 2007/07/12 13:15:56 safonov Exp $
 */
@Stateful(name = "merp/settlement/SettlementProcessorService") //$NON-NLS-1$
public class SettlementProcessorServiceBean extends AbstractPOJOBusinessObjectStatefulServiceBean implements SettlementProcessorServiceLocal {

  private final short EXPENSE_KIND = 1;
  private final short INCOME_KIND = 0;
  private DocFlowPluginInvokeParams docFlowParams;
  private List<DocumentSpecItem> docSpecs;
  private StlConfig config;
  private BigDecimal sumCur;
  private BigDecimal sumNat;
  private DocHead docHead;
  private Contractor contractorFrom;
  private Contractor contractorTo;
  private Integer expenseHistId;
  private Integer incomeHistId;

  /*
   * (non-Javadoc)
   * @see com.mg.merp.settlement.SettlementProcessorServiceLocal#setToPlanContractorCard(com.mg.merp.docflow.DocFlowPluginInvokeParams)
   */
  @PermitAll
  @Remove
  public void setToPlanContractorCard(DocFlowPluginInvokeParams docFlowParams) {
    prepareValues(docFlowParams);
    internalUpdateContractorCard(true, true, docHead, contractorFrom, contractorTo, docFlowParams.getProcessDate(), sumCur, sumNat);
    updateDocFlowParams();
  }

  private void prepareValues(DocFlowPluginInvokeParams params) {
    initValues(params);
    BigDecimal curRate = BigDecimal.ONE;
    boolean isCurrencyDirect;
    int docSectionId = docHead.getDocSection().getId();

    if (docSectionId == CashDocumentInServiceLocal.DOCSECTION || docSectionId == CashDocumentOutServiceLocal.DOCSECTION) {
      if (docSectionId == CashDocumentInServiceLocal.DOCSECTION)
        contractorTo = ((CashDocument) docHead).getCompany();
      else
        contractorFrom = ((CashDocument) docHead).getCompany();
    }

    try {
      curRate = getCurrencyConverter().getCurrencyRate(
          config.getBaseCurrency(),
          docHead.getCurrency(),
          config.getCurrencyRateAuthority(),
          config.getCurrencyRateType(),
          docFlowParams.getProcessDate());
      isCurrencyDirect = true;
    } catch (CurrencyRateNotFoundException e) {

      curRate = getCurrencyConverter().getIndirectCurrencyRate(
          config.getBaseCurrency(),
          docHead.getCurrency(),
          config.getCurrencyRateAuthority(),
          config.getCurrencyRateType(),
          docFlowParams.getProcessDate());
      isCurrencyDirect = false;
    }

    if (docHead.getDocSection().isWithSpec() && docSpecs != null) {
      for (DocumentSpecItem docSpecItem : docSpecs)
        sumCur = MathUtils.add(sumCur, docSpecItem.getPerformedSum(), new RoundContext(config.getCurrencyPrec()));

      if (isCurrencyDirect)
        sumNat = MathUtils.multiply(sumCur, curRate, new RoundContext(config.getCurrencyPrec()));
      else
        sumNat = MathUtils.divide(sumCur, curRate, new RoundContext(config.getCurrencyPrec()));
    } else {
      sumCur = MathUtils.round(docFlowParams.getPerformedSum(), new RoundContext(config.getCurrencyPrec()));

      if (isCurrencyDirect)
        sumNat = MathUtils.multiply(sumCur, curRate, new RoundContext(config.getCurrencyPrec()));
      else
        sumNat = MathUtils.divide(sumCur, curRate, new RoundContext(config.getCurrencyPrec()));
    }
  }

  private void initValues(DocFlowPluginInvokeParams params) {
    config = getModuleConfiguration();
    docFlowParams = params;
    docHead = docFlowParams.getDocument();
    contractorFrom = docHead.getFrom();
    contractorTo = docHead.getTo();
    sumCur = new BigDecimal(0);
    sumNat = new BigDecimal(0);
    expenseHistId = 0;
    incomeHistId = 0;
    try {
      docSpecs = docFlowParams.getSpecList();
    } catch (IllegalStateException e) {
      docSpecs = null;
    }
  }

  /**
   * Обновить карточку расчетов с партнером
   *
   * @param isSet       - признак "поставить в план" / "снять с плана"
   * @param docHead     - документ
   * @param from        - от кого
   * @param to          - кому
   * @param processDate - дата обработки документа
   * @param sumCur      - сумма в валюте
   * @param sumNat      - сумма в нац. валюте
   * @return результат завершения операции
   */
  private boolean internalUpdateContractorCard(boolean isPlan, boolean isSet, DocHead docHead, Contractor from, Contractor to, Date processDate, BigDecimal sumCur, BigDecimal sumNat) {
    boolean result = false;
    Contractor company = null;
    // expense (расход)
    company = getCompany(from);
    if (company != null) {
      ContractorCard card = getCard(company, to);
      if (isPlan)
        expenseHistId = createPlanHistory(card, docHead, processDate, sumCur, sumNat, EXPENSE_KIND, isSet);
      else
        expenseHistId = createHistory(card, docHead, processDate, sumCur, sumNat, EXPENSE_KIND);
      result = true;
    }
    // income (приход)
    company = getCompany(to);
    if (company != null) {
      ContractorCard card = getCard(company, from);
      if (isPlan)
        incomeHistId = createPlanHistory(card, docHead, processDate, sumCur, sumNat, INCOME_KIND, isSet);
      else
        incomeHistId = createHistory(card, docHead, processDate, sumCur, sumNat, INCOME_KIND);
      result = true;
    }
    return result;
  }

  private Contractor getCompany(Contractor partner) {
    List<Contractor> companies = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(OrgUnit.class)
        .add(Restrictions.eq("Partner", partner))); //$NON-NLS-1$

    if (companies != null && !companies.isEmpty())
      return companies.get(0);
    else
      return null;
  }

  /**
   * Получить карточку расчетов с партнером
   *
   * @param company - подразделение
   * @param partner - партнер
   * @return карточка расчетов с партнером
   */
  private ContractorCard getCard(Contractor company, Contractor partner) {
    List<ContractorCard> cards = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(ContractorCard.class)
        .add(Restrictions.eq("OrgUnit", company)) //$NON-NLS-1$
        .add(Restrictions.eq("Contractor", partner))); //$NON-NLS-1$
    if (cards != null && !cards.isEmpty())
      return cards.get(0);
    else
      return createCard(company, partner);
  }

  /**
   * Создать карточку расчетов с партнером
   *
   * @param company - подразделение
   * @param partner - партнер
   * @return карточка расчетов с партнером
   */
  private ContractorCard createCard(Contractor company, Contractor partner) {
    ContractorCardServiceLocal contractorCardService = (ContractorCardServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/settlement/ContractorCard"); //$NON-NLS-1$
    ContractorCard contractorCard = contractorCardService.initialize();
    contractorCard.setContractor(partner);
    contractorCard.setOrgUnit(company);
    contractorCard.setTotalIncome(new BigDecimal(0));
    contractorCard.setTotalExpenses(new BigDecimal(0));
    contractorCard.setDebitorInDebLimit(new BigDecimal(0));
    contractorCard.setCreditorInDebSum(new BigDecimal(0));
    contractorCard.setCreditorInDebLimit(new BigDecimal(0));
    contractorCard.setPlanExpenses(new BigDecimal(0));
    contractorCard.setPlanIncome(new BigDecimal(0));
    ServerUtils.getPersistentManager().persist(contractorCard);
    return contractorCard;
  }

  private Integer createPlanHistory(ContractorCard card, DocHead docHead, Date processDate, BigDecimal sumCur, BigDecimal sumNat, Short kind, boolean isSet) {
    ContractorCardPlan cardPlanHistory = new ContractorCardPlan();
    cardPlanHistory.setContractorCard(card);
    cardPlanHistory.setDocHead(docHead);
    cardPlanHistory.setKind(kind);
    cardPlanHistory.setIsSet(isSet);
    cardPlanHistory.setProcessDate(processDate);
    cardPlanHistory.setSumCur(sumCur);
    cardPlanHistory.setSumNat(sumNat);
    cardPlanHistory.setDateTime(Calendar.getInstance().getTime());
    ServerUtils.getPersistentManager().persist(cardPlanHistory);
    return cardPlanHistory.getId();
  }

  private Integer createHistory(ContractorCard card, DocHead docHead, Date processDate, BigDecimal sumCur, BigDecimal sumNat, Short kind) {
    ContractorCardHist cardHistory = new ContractorCardHist();
    cardHistory.setContractorCard(card);
    cardHistory.setDocHead(docHead);
    cardHistory.setKind(kind);
    cardHistory.setProcessDate(processDate);
    cardHistory.setSumCur(sumCur);
    cardHistory.setSumNat(sumNat);
    cardHistory.setDateTime(Calendar.getInstance().getTime());
    ServerUtils.getPersistentManager().persist(cardHistory);
    return cardHistory.getId();
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.settlement.SettlementProcessorServiceLocal#rollBackSetToPlanContractorCard(com.mg.merp.docflow.DocFlowPluginInvokeParams)
   */
  @PermitAll
  @Remove
  public void rollBackSetToPlanContractorCard(DocFlowPluginInvokeParams docFlowParams) {
    internalRollBackSetToPlanContractorCard(docFlowParams);
  }

  private void internalRollBackSetToPlanContractorCard(DocFlowPluginInvokeParams docFlowParams) {
    if (docFlowParams.getData1() != 0)
      rollBackUpdateContractorCardPlan(docFlowParams.getData1());
    if (docFlowParams.getData2() != 0)
      rollBackUpdateContractorCardPlan(docFlowParams.getData2());
  }

  private void rollBackUpdateContractorCardPlan(Integer id) {
    ContractorCardPlan contractorCardPlan = ServerUtils.getPersistentManager().find(ContractorCardPlan.class, id);
    if (contractorCardPlan != null)
      ServerUtils.getPersistentManager().remove(contractorCardPlan);
    else
      throw new BusinessException(Messages.getInstance().getMessage(Messages.ROLLBACK_FAIL));
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.settlement.SettlementProcessorServiceLocal#processInSettlement(com.mg.merp.docflow.DocFlowPluginInvokeParams)
   */
  @PermitAll
  @Remove
  public void processInSettlement(DocFlowPluginInvokeParams docFlowParams) {
    prepareValues(docFlowParams);
    internalUpdateContractorCard(false, false, docHead, contractorFrom, contractorTo, docFlowParams.getProcessDate(), sumCur, sumNat);
    updateDocFlowParams();
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.settlement.SettlementProcessorServiceLocal#rollBackProcessInSettlement(com.mg.merp.docflow.DocFlowPluginInvokeParams)
   */
  @PermitAll
  @Remove
  public void rollBackProcessInSettlement(DocFlowPluginInvokeParams docFlowParams) {
    internalRollBackProcessInSettlement(docFlowParams);
  }

  private void internalRollBackProcessInSettlement(DocFlowPluginInvokeParams docFlowParams) {
    if (docFlowParams.getData1() != 0)
      rollBackUpdateContractorCardHistory(docFlowParams.getData1());
    if (docFlowParams.getData2() != 0)
      rollBackUpdateContractorCardHistory(docFlowParams.getData2());
  }

  private void rollBackUpdateContractorCardHistory(Integer id) {
    ContractorCardHist contractorCardHist = ServerUtils.getPersistentManager().find(ContractorCardHist.class, id);
    if (contractorCardHist != null)
      ServerUtils.getPersistentManager().remove(contractorCardHist);
    else
      throw new BusinessException(Messages.getInstance().getMessage(Messages.ROLLBACK_FAIL));
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.settlement.SettlementProcessorServiceLocal#unsetFromPlanContractorCard(com.mg.merp.docflow.DocFlowPluginInvokeParams)
   */
  @PermitAll
  @Remove
  public void unsetFromPlanContractorCard(DocFlowPluginInvokeParams docFlowParams) {
    internalUnsetFromPlanContractorCard(docFlowParams);
  }

  private void internalUnsetFromPlanContractorCard(DocFlowPluginInvokeParams docFlowParams) {
    prepareValues(docFlowParams);
    internalUpdateContractorCard(true, false, docHead, contractorFrom, contractorTo, docFlowParams.getProcessDate(), sumCur, sumNat);
    updateDocFlowParams();
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.settlement.SettlementProcessorServiceLocal#rollBackUnsetFromPlanContractorCard(com.mg.merp.docflow.DocFlowPluginInvokeParams)
   */
  @PermitAll
  @Remove
  public void rollBackUnsetFromPlanContractorCard(DocFlowPluginInvokeParams docFlowParams) {
    internalRollBackSetToPlanContractorCard(docFlowParams);
  }

  /**
   * Обновить параметры документооборота
   */
  private void updateDocFlowParams() {
    docFlowParams.setData1(expenseHistId);
    docFlowParams.setData2(incomeHistId);
  }

  private StlConfig getModuleConfiguration() {
    SysClient sysClient = (SysClient) ServerUtils.getCurrentSession().getSystemTenant();
    return ServerUtils.getPersistentManager().find(StlConfig.class, sysClient.getId());
  }

  private CurrencyRateServiceLocal getCurrencyConverter() {
    return (CurrencyRateServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CurrencyRateServiceLocal.SERVICE_NAME);
  }

}
