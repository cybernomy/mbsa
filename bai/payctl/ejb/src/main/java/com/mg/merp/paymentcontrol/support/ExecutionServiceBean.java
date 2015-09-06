/*
 * ExecutionServiceBean.java
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

package com.mg.merp.paymentcontrol.support;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.math.RoundContext;
import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.service.CustomFieldsManagerLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.account.model.BankDocument;
import com.mg.merp.account.model.BankDocumentModel;
import com.mg.merp.core.model.Folder;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.document.Configuration;
import com.mg.merp.document.Document;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocHeadModel;
import com.mg.merp.document.support.DocumentUtils;
import com.mg.merp.paymentcontrol.ExecutionServiceLocal;
import com.mg.merp.paymentcontrol.LiabilityServiceLocal;
import com.mg.merp.paymentcontrol.model.Execution;
import com.mg.merp.paymentcontrol.model.Liability;
import com.mg.merp.paymentcontrol.model.PmcConfig;
import com.mg.merp.paymentcontrol.model.PmcResource;
import com.mg.merp.paymentcontrol.model.TransferParams;
import com.mg.merp.paymentcontrol.model.Version;
import com.mg.merp.paymentcontrol.model.VersionStatus;
import com.mg.merp.paymentcontrol.model.VersionStatusKind;
import com.mg.merp.reference.CurrencyConversionResult;
import com.mg.merp.reference.CurrencyServiceLocal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Исполнение обязательств"
 *
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: ExecutionServiceBean.java,v 1.11 2007/08/21 06:36:52 sharapov Exp $
 */
@Stateless(name = "merp/paymentcontrol/ExecutionService") //$NON-NLS-1$
public class ExecutionServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<Execution, Integer> implements ExecutionServiceLocal {

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onErase(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onErase(Execution entity) {
    checkForOperationPossibility(entity);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.paymentcontrol.ExecutionServiceLocal#checkForOperationPosibility(com.mg.merp.paymentcontrol.model.Execution)
   */
  @PermitAll
  public void checkForOperationPossibility(Execution entity) {
    doCheckForOperationPossibility(entity);
  }

  private void doCheckForOperationPossibility(Execution entity) {
    if (entity.getDocCreated())
      throw new BusinessException(Messages.getInstance().getMessage(Messages.CANT_BE_CHANGED_OR_DELETED));
  }

  /* (non-Javadoc)
   * @see com.mg.merp.paymentcontrol.ExecutionServiceLocal#executeLiability(com.mg.merp.paymentcontrol.model.Liability, com.mg.merp.paymentcontrol.model.PmcResource, java.util.Date, java.math.BigDecimal)
   */
  public void executeLiability(Liability liability, PmcResource pmcResource, Date planDate, BigDecimal sumCur) {
    doExecuteLiability(liability, pmcResource, planDate, sumCur);
  }

  private void doExecuteLiability(Liability liability, PmcResource pmcResource, Date planDate, BigDecimal sumCur) {
    short transferKind = 0; // НЕ внутреннее перемещение средств
    Execution execution = createExecution(liability, pmcResource, planDate, sumCur, transferKind, null);
    ServerUtils.getPersistentManager().persist(execution);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.paymentcontrol.ExecutionServiceLocal#executeLiability(java.lang.Integer, java.lang.Integer, com.mg.merp.paymentcontrol.model.Liability, java.lang.Integer, java.util.Date, java.math.BigDecimal)
   */
  public void executeLiability(Integer resourceId, Integer resourceFolderId, Liability liability, Integer versionId, Date execDate, BigDecimal sumCur) {
    checkVersionStatus(versionId, execDate);

    short transferKind = 0; // НЕ внутреннее перемещение средств
    Version version = ServerUtils.getPersistentManager().find(Version.class, versionId);
    Execution execution = createExecution(liability, null, execDate, sumCur, transferKind, version);

    if (liability.getPrefOnly() && liability.getPrefResource() != null)
      execution.setResource(liability.getPrefResource());
    else {
      if (resourceId != null) {
        PmcResource resource = ServerUtils.getPersistentManager().find(PmcResource.class, resourceId);
        execution.setResource(resource);
      } else {
        Folder resourceFolder = ServerUtils.getPersistentManager().find(Folder.class, resourceFolderId);
        execution.setResourceFolder(resourceFolder);
      }
    }

    ServerUtils.getPersistentManager().persist(execution);
  }

  private Execution createExecution(Liability liability, PmcResource pmcResource, Date planDate, BigDecimal sumCur, short transferKind, Version version) {
    if (MathUtils.compareToZero(sumCur) <= 0)
      throw new BusinessException(Messages.getInstance().getMessage(Messages.EXECUTION_SUM_INVALID));

    PmcConfig config = getModuleConfiguration();

    Execution execution = initialize();
    execution.setLiability(liability);
    execution.setResource(pmcResource);
    execution.setVersion(version);
    execution.setPlanDate(planDate);
    execution.setSumCur(sumCur);
    execution.setCurCode(liability.getCurCode());
    execution.setCurRateAuthority(liability.getCurRateAuthority());
    execution.setCurRateType(liability.getCurRateType());
    execution.setReceivable(liability.getReceivable());
    execution.setTransferKind(transferKind);

    BigDecimal converdedToModuleCurSum = getCurencyConverter().conversion(
        config.getCurrency(),
        execution.getCurCode(),
        config.getCurRateAuthority(),
        config.getCurRateType(),
        execution.getPlanDate(),
        MathUtils.round(execution.getSumCur(), new RoundContext(config.getCurrencyPrec())));
    execution.setSumNat(converdedToModuleCurSum);

    return execution;
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.paymentcontrol.ExecutionServiceLocal#transferResourses(com.mg.merp.paymentcontrol.model.TransferParams, java.lang.Integer)
   */
  public void transferResourses(TransferParams transferParams, Integer versionId) {
    internalTransferResourses(transferParams, versionId);
  }

  private void internalTransferResourses(TransferParams transferParams, Integer versionId) {
    short transferKind = 1; // внутреннее перемещение средств
    Version version = null;

    if (versionId != null)
      version = ServerUtils.getPersistentManager().find(Version.class, versionId);

    // расход (expense)
    checkVersionStatus(versionId, transferParams.getDateExpense());
    // обязательство по расходу (expense)
    Liability liabilityModelExpense = ServerUtils.getPersistentManager().find(Liability.class, transferParams.getLiabilityModelExpense().getId()); // to avoid lazy initilize exception
    Liability liabilityExpense = createLiabilityWithModel(transferParams.getResourceExpense(), transferParams.getDateExpense(), transferParams.getSumExpense(), liabilityModelExpense, transferKind, version);

    // исполнение обязательства по расходу (expense)
    Execution executionExpense = createExecution(liabilityExpense, transferParams.getResourceExpense(), transferParams.getDateExpense(), transferParams.getSumExpense(), transferKind, version);
    if (transferParams.getResourceExpense() == null)
      executionExpense.setResourceFolder(transferParams.getResourceFolderExpense());

    ServerUtils.getPersistentManager().persist(liabilityExpense);
    ServerUtils.getPersistentManager().persist(executionExpense);

    // приход (income)
    checkVersionStatus(versionId, transferParams.getDateIncome());
    // обязательство по приходу (income)
    Liability liabilityModelIncome = ServerUtils.getPersistentManager().find(Liability.class, transferParams.getLiabilityModelIncome().getId()); // to avoid lazy initilize exception
    Liability liabilityIncome = createLiabilityWithModel(transferParams.getResourceIncome(), transferParams.getDateIncome(), transferParams.getSumIncome(), liabilityModelIncome, transferKind, version);

    // исполнение обязательства по приходу (income)
    Execution executionIncome = createExecution(liabilityIncome, transferParams.getResourceIncome(), transferParams.getDateIncome(), transferParams.getSumIncome(), transferKind, version);
    if (transferParams.getResourceIncome() == null)
      executionIncome.setResourceFolder(transferParams.getResourceFolderIncome());

    ServerUtils.getPersistentManager().persist(liabilityIncome);
    ServerUtils.getPersistentManager().persist(executionIncome);
  }

  /**
   * Создать обязательство с использованием образца
   *
   * @param resource       - средство платежа
   * @param dateToExecute  - дата иполнения
   * @param sumCur         - сумма
   * @param liabilityModel - образец обязательства
   * @param transferKind   - признак перемещения ресурсов
   * @param version        - версия планирования
   * @return обязательство
   */
  private Liability createLiabilityWithModel(PmcResource resource, Date dateToExecute, BigDecimal sumCur, Liability liabilityModel, short transferKind, Version version) {
    PmcConfig config = getModuleConfiguration();

    Liability liability = getLiabilityService().createByPattern(liabilityModel, liabilityModel.getDestFolder());

    liability.setRegDate(Calendar.getInstance().getTime());
    liability.setDateToExecute(dateToExecute);
    liability.setSumCur(sumCur);

    if (resource != null) {
      liability.setCurCode(resource.getCurCode());
      liability.setCurRateAuthority(resource.getCurRateAuthority());
      liability.setCurRateType(resource.getCurRateType());
    } else {
      liability.setCurCode(config.getCurrency());
      liability.setCurRateAuthority(config.getCurRateAuthority());
      liability.setCurRateType(config.getCurRateType());
    }
    liability.setTransferKind(transferKind);

    if (version != null) {
      liability.setVersion(version);
      liability.setIsShared(false);
    } else {
      liability.setVersion(null);
      liability.setIsShared(true);
    }
    liability.setExecutions(null);

    return liability;
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.paymentcontrol.ExecutionServiceLocal#createDocuments(java.io.Serializable[], java.util.Date)
   */
  public String createDocuments(Serializable[] executionIds, Date date) {
    return doCreateDocuments(executionIds, date);
  }

  @SuppressWarnings("unchecked") //$NON-NLS-1$
  private String doCreateDocuments(Serializable[] executionIds, Date date) {
    StringBuilder detailReportText = new StringBuilder();
    Integer totalCount = 0;
    Integer successCount = 0;
    Integer failCount = 0;
    if (executionIds != null)
      totalCount = executionIds.length;

    for (Serializable executionId : executionIds) {
      Execution execution = ServerUtils.getPersistentManager().find(Execution.class, executionId);
      if (execution.getVersion() == null && execution.getDocHead() == null && execution.getApproved()) {
        if (execution.getPlanDate().compareTo(date) == 0) {
          DocHead document = createDocumentByExecution(execution, detailReportText);
          if (document != null) {
            successCount++;
            DocumentUtils.getDocumentService(document.getDocSection()).create(document);
            execution.setDocHead(document);
            execution.setDocCreated(true);
            ServerUtils.getPersistentManager().merge(execution);
          } else
            failCount++;
        }
      }
    }
    StringBuilder headerReportText = new StringBuilder(String.format(Messages.getInstance().getMessage(Messages.REPORT_HEADER), totalCount, successCount, failCount));
    return headerReportText.append(detailReportText).toString();
  }

  private DocHead createDocumentByExecution(Execution execution, StringBuilder detailReportText) {
    Messages msg = Messages.getInstance();
    if (execution.getResource() == null) {
      detailReportText.append(String.format(msg.getMessage(Messages.REPORT_RESOURCE_INVALID), execution.getLiability().getNum(), execution.getSumCur().toString()));
      return null;
    }

    DocHeadModel docHeadModel = null;
    PmcResource resource = execution.getResource();
    Liability liability = execution.getLiability();

    if (execution.getReceivable() && execution.getTransferKind() == 0)
      docHeadModel = resource.getInDocModel1();
    if (execution.getReceivable() && execution.getTransferKind() == 1)
      docHeadModel = resource.getInDocModel2();
    if (!execution.getReceivable() && execution.getTransferKind() == 0)
      docHeadModel = resource.getOutDocModel1();
    if (!execution.getReceivable() && execution.getTransferKind() == 1)
      docHeadModel = resource.getOutDocModel2();

    if (docHeadModel == null) {
      detailReportText.append(String.format(msg.getMessage(Messages.REPORT_PATTERN_INVALID), execution.getLiability().getNum(), execution.getSumCur().toString(), execution.getResource().getName()));
      return null;
    }

    docHeadModel = ServerUtils.getPersistentManager().find(BankDocumentModel.class, docHeadModel.getId());

    Document documentService = DocumentUtils.getDocumentService(docHeadModel.getDocSection());
    DocHead document = (DocHead) documentService.createByPattern(docHeadModel, docHeadModel.getModelDestFolder());

    if (!(document instanceof BankDocument))
      throw new BusinessException(Messages.getInstance().getMessage(Messages.NOT_BANK_DOCUMENT_CREATION_ERROR));


    document.setDocSection(docHeadModel.getDocSection());
    document.setFolder(docHeadModel.getModelDestFolder());

    if (document.getDocDate() == null)
      document.setDocDate(execution.getPlanDate());

    if (document.getFrom() == null && liability.getFrom() != null)
      document.setFrom(liability.getFrom());

    if (((BankDocument) document).getPayerBankReq() == null && liability.getFromBankAcc() != null)
      ((BankDocument) document).setPayerBankReq(liability.getFromBankAcc());

    if (document.getTo() == null && liability.getTo() != null)
      document.setTo(liability.getTo());

    if (((BankDocument) document).getRecipientBankReq() == null && liability.getToBankAcc() != null)
      ((BankDocument) document).setRecipientBankReq(liability.getToBankAcc());

    if (liability.getDocHead() != null)
      document.setBaseDocument(liability.getDocHead());
    document.setBaseDocType(liability.getBaseDocType());
    document.setBaseDocNumber(liability.getBaseDocNumber());
    document.setBaseDocDate(liability.getBaseDocDate());

    if (liability.getContract() != null)
      document.setContract(liability.getContract());
    document.setContractType(liability.getContractType());
    document.setContractNumber(liability.getContractNumber());
    document.setContractDate(liability.getContractDate());

    document.setCurrency(execution.getCurCode());
    document.setCurrencyRateType(execution.getCurRateType());
    document.setCurrencyRateAuthority(execution.getCurRateAuthority());

    document.setSumCur(execution.getSumCur());

    Configuration docConfig = documentService.getConfiguration();
    CurrencyConversionResult conversionResult = getCurencyConverter().conversionEx(
        docConfig.getLocalCurrency(),
        document.getCurrency(),
        document.getCurrencyRateAuthority(),
        document.getCurrencyRateType(),
        execution.getPlanDate(),
        document.getSumCur());

    document.setSumNat(conversionResult.getAmount());

    if (conversionResult.isDirect())
      document.setCurCource(conversionResult.getRate());
    else
      document.setCurCource(BigDecimal.ONE.divide(conversionResult.getRate()));

    if (MathUtils.compareToZero(((BankDocumentModel) docHeadModel).getNds2Rate()) == 1) {
      ((BankDocument) document).setSummaWithNds2(conversionResult.getAmount());

      BigDecimal step1 = MathUtils.add(((BankDocumentModel) docHeadModel).getNds2Rate(), new BigDecimal(100), new RoundContext(4));
      BigDecimal step2 = MathUtils.divide(new BigDecimal(100), step1, new RoundContext(4));
      BigDecimal step3 = MathUtils.subtract(BigDecimal.ONE, step2, new RoundContext(4));
      BigDecimal step4 = MathUtils.multiply(((BankDocument) document).getSummaWithNds2(), step3, new RoundContext(4));
      ((BankDocument) document).setNds2Summa(step4);

      ((BankDocument) document).setClearSumma(((BankDocument) document).getSummaWithNds2().subtract(((BankDocument) document).getNds2Summa()));
    } else if (MathUtils.compareToZero(((BankDocumentModel) docHeadModel).getNds1Rate()) == 1) {
      ((BankDocument) document).setSummaWithNds1(conversionResult.getAmount());

      BigDecimal step1 = MathUtils.add(((BankDocumentModel) docHeadModel).getNds1Rate(), new BigDecimal(100), new RoundContext(4));
      BigDecimal step2 = MathUtils.divide(new BigDecimal(100), step1, new RoundContext(4));
      BigDecimal step3 = MathUtils.subtract(BigDecimal.ONE, step2, new RoundContext(4));
      BigDecimal step4 = MathUtils.multiply(((BankDocument) document).getSummaWithNds1(), step3, new RoundContext(4));
      ((BankDocument) document).setNds1Summa(step4);

      ((BankDocument) document).setClearSumma(((BankDocument) document).getSummaWithNds1().subtract(((BankDocument) document).getNds1Summa()));
    } else
      ((BankDocument) document).setClearSumma(conversionResult.getAmount());

    //clone custom fields
    CustomFieldsManagerLocator.locate().cloneValues(this, execution, documentService, document);

    if (document != null)
      detailReportText.append(String.format(msg.getMessage(Messages.REPORT_DOCUMENT_CREATED), execution.getLiability().getNum(), execution.getSumCur().toString(), document.getDocType().getCode(), document.getDocNumber(), document.getDocDate()));
    return document;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.paymentcontrol.ExecutionServiceLocal#setApproved(java.io.Serializable[], boolean)
   */
  public void setApproved(Serializable[] executionIds, boolean isApproved) {
    doSetApproved(executionIds, isApproved);
  }

  private void doSetApproved(Serializable[] executionIds, boolean isApproved) {
    for (Serializable executionId : executionIds) {
      Execution execution = ServerUtils.getPersistentManager().find(Execution.class, executionId);
      execution.setApproved(isApproved);
      ServerUtils.getPersistentManager().merge(execution);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.merp.paymentcontrol.ExecutionServiceLocal#showCreatedDocument(java.lang.Integer)
   */
  @PermitAll
  public void showCreatedDocument(Integer executionId) {
    doShowCreatedDocument(executionId);
  }

  @SuppressWarnings("unchecked") //$NON-NLS-1$
  private void doShowCreatedDocument(Integer executionId) {
    if (executionId == null)
      return;

    Execution execution = ServerUtils.getPersistentManager().find(Execution.class, executionId);
    if (execution == null)
      return;

    DocHead docHead = execution.getDocHead();

    if (docHead != null) {
      Document documentService = DocumentUtils.getDocumentService(docHead.getDocSection());
      MaintenanceHelper.view(documentService, docHead.getId(), null, null);
    }
  }

  /* (non-Javadoc)
   * @see com.mg.merp.paymentcontrol.ExecutionServiceLocal#deleteCreatedDocument(java.io.Serializable[])
   */
  public void deleteCreatedDocument(Serializable[] executionIds) {
    doDeleteCreatedDocument(executionIds);
  }

  private void doDeleteCreatedDocument(Serializable[] executionIds) {
    for (Serializable executionId : executionIds) {
      Execution execution = ServerUtils.getPersistentManager().find(Execution.class, executionId);
      if (execution.getVersion() == null && execution.getDocHead() != null && execution.getDocProcessed() == false) {
        ServerUtils.getPersistentManager().remove(execution.getDocHead());
        execution.setDocHead(null);
        execution.setDocCreated(false);
        ServerUtils.getPersistentManager().merge(execution);
      }
    }
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.paymentcontrol.ExecutionServiceLocal#checkVersionStatus(java.lang.Integer, java.util.Date)
   */
  @PermitAll
  public void checkVersionStatus(Integer versionId, Date execDate) {
    doCheckVersionStatus(versionId, execDate);
  }

  private void doCheckVersionStatus(Integer versionId, Date execDate) {
    if (versionId == null)
      return;

    Criteria criteria = OrmTemplate.createCriteria(VersionStatus.class)
        .setProjection(Projections.property("Kind")) //$NON-NLS-1$
        .add(Restrictions.eq("Version.Id", versionId)) //$NON-NLS-1$
        .add(Restrictions.and(Restrictions.le("DateFrom", execDate), Restrictions.ge("DateTill", execDate))); //$NON-NLS-1$ //$NON-NLS-2$

    List<Object> result = OrmTemplate.getInstance().findByCriteria(criteria);

    if (!result.contains(VersionStatusKind.IN_WORK))
      throw new BusinessException(Messages.getInstance().getMessage(Messages.VERSION_STAUS_IN_WORK));
    if (result.contains(VersionStatusKind.READY))
      throw new BusinessException(Messages.getInstance().getMessage(Messages.VERSION_STAUS_READY));
    if (result.contains(VersionStatusKind.EXECUTE))
      throw new BusinessException(Messages.getInstance().getMessage(Messages.VERSION_STAUS_EXECUTE));
  }

  private LiabilityServiceLocal getLiabilityService() {
    return (LiabilityServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(LiabilityServiceLocal.LOCAL_SERVICE_NAME);
  }

  private PmcConfig getModuleConfiguration() {
    SysClient sysClient = (SysClient) ServerUtils.getCurrentSession().getSystemTenant();
    return ServerUtils.getPersistentManager().find(PmcConfig.class, sysClient.getId());
  }

  private CurrencyServiceLocal getCurencyConverter() {
    return (CurrencyServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CurrencyServiceLocal.LOCAL_SERVICE_NAME);
  }

}