/*
 * PaymentallocProcessorServiceBean.java
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
package com.mg.merp.paymentalloc.support;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.math.RoundContext;
import com.mg.framework.api.orm.DetachedCriteria;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.orm.Projection;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.orm.Subqueries;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.MaintenanceFormActionListener;
import com.mg.framework.api.ui.MaintenanceFormEvent;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.generic.AbstractPOJOBusinessObjectStatefulServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.service.CustomFieldsManagerLocator;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.Folder;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.document.model.DocType;
import com.mg.merp.document.model.DocumentSpecTax;
import com.mg.merp.document.support.DocumentUtils;
import com.mg.merp.paymentalloc.PaymentServiceLocal;
import com.mg.merp.paymentalloc.PaymentallocProcessorListener;
import com.mg.merp.paymentalloc.PaymentallocProcessorServiceLocal;
import com.mg.merp.paymentalloc.TransactHeadServiceLocal;
import com.mg.merp.paymentalloc.TransactSpecServiceLocal;
import com.mg.merp.paymentalloc.TransactTaxServiceLocal;
import com.mg.merp.paymentalloc.model.DocGroup;
import com.mg.merp.paymentalloc.model.DocGroupLink;
import com.mg.merp.paymentalloc.model.Payment;
import com.mg.merp.paymentalloc.model.SpecInfo;
import com.mg.merp.paymentalloc.model.TransactHead;
import com.mg.merp.paymentalloc.model.TransactSpec;
import com.mg.merp.paymentalloc.model.TransactTax;
import com.mg.merp.paymentalloc.support.ui.InteractiveAllocationDlg;
import com.mg.merp.reference.CurrencyConversionResult;
import com.mg.merp.reference.CurrencyServiceLocal;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.Currency;
import com.mg.merp.reference.model.OrgUnit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 * Реализация процессора модуля "Журнал платежей"
 *
 * @author Artem V. Sharapov
 * @version $Id: PaymentallocProcessorServiceBean.java,v 1.7 2007/10/01 13:01:50 sharapov Exp $
 */
@Stateful(name = "merp/paymentalloc/PaymentallocProcessorService") //$NON-NLS-1$
public class PaymentallocProcessorServiceBean extends AbstractPOJOBusinessObjectStatefulServiceBean
    implements PaymentallocProcessorServiceLocal {

  private final int BANK_DOCUMENT_IN_DOCSECTION = 2;
  private final int BANK_DOCUMENT_OUT_DOCSECTION = 3;
  private final int BANK_DOCUMENT_OTHER_DOCSECTION = 4;
  private final int CASH_DOCUMENT_IN_DOCSECTION = 5;
  private final int CASH_DOCUMENT_OUT_DOCSECTION = 6;
  private DocFlowPluginInvokeParams docFlowParams;
  private PaymentallocProcessorListener processorListener;
  private List<SpecInfo> specList;


  /* (non-Javadoc)
   * @see com.mg.merp.paymentalloc.PaymentallocProcessorServiceLocal#createPayment(com.mg.merp.docflow.DocFlowPluginInvokeParams, com.mg.merp.paymentalloc.PaymentallocProcessorListener)
   */
  @PermitAll
  @Remove
  public void createPayment(DocFlowPluginInvokeParams docFlowParams, PaymentallocProcessorListener processorListener) throws Exception {
    prepareProcessor(docFlowParams, processorListener);
    doCreatePayment();
  }

  /* (non-Javadoc)
   * @see com.mg.merp.paymentalloc.PaymentallocProcessorServiceLocal#rollBackCreatePayment(com.mg.merp.docflow.DocFlowPluginInvokeParams)
   */
  @PermitAll
  @Remove
  public void rollBackCreatePayment(DocFlowPluginInvokeParams docFlowParams) {
    doRollBackCreatePayment(docFlowParams);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.paymentalloc.PaymentallocProcessorServiceLocal#allocatePayment(com.mg.merp.docflow.DocFlowPluginInvokeParams, com.mg.merp.paymentalloc.PaymentallocProcessorListener)
   */
  @PermitAll
  @Remove
  public void allocatePayment(DocFlowPluginInvokeParams docFlowParams, PaymentallocProcessorListener processorListener) throws Exception {
    prepareProcessor(docFlowParams, processorListener);
    doAllocatePayment(false);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.paymentalloc.PaymentallocProcessorServiceLocal#rollBackAllocatePayment(com.mg.merp.docflow.DocFlowPluginInvokeParams)
   */
  @PermitAll
  @Remove
  public void rollBackAllocatePayment(DocFlowPluginInvokeParams docFlowParams) {
    doRollBackAllocatePayment(docFlowParams);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.paymentalloc.PaymentallocProcessorServiceLocal#allocatePaymentIteractive(com.mg.merp.docflow.DocFlowPluginInvokeParams, com.mg.merp.paymentalloc.PaymentallocProcessorListener)
   */
  @PermitAll
  @Remove
  public void allocatePaymentIteractive(DocFlowPluginInvokeParams docFlowParams, PaymentallocProcessorListener processorListener) throws Exception {
    prepareProcessor(docFlowParams, processorListener);
    doAllocatePayment(true);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.paymentalloc.PaymentallocProcessorServiceLocal#rollBackAllocatePaymentIteractive(com.mg.merp.docflow.DocFlowPluginInvokeParams)
   */
  @PermitAll
  @Remove
  public void rollBackAllocatePaymentIteractive(DocFlowPluginInvokeParams docFlowParams) {
    doRollBackAllocatePayment(docFlowParams);
  }

  protected void doRollBackAllocatePayment(DocFlowPluginInvokeParams docFlowParams) {
    if (docFlowParams.getData2() != null) {
      TransactHead transactHead = ServerUtils.getPersistentManager().find(TransactHead.class, docFlowParams.getData2());
      if (transactHead != null)
        getTransactHeadService().erase(transactHead);
      else
        getLogger().warn("attempt to delete non-existent entity"); //$NON-NLS-1$
    }
  }

  protected void doAllocatePayment(final boolean isInteractive) throws Exception {
    final List<DocGroup> docGroups = getDocGroups(docFlowParams.getDocument().getDocType());
    if (docGroups == null || docGroups.size() == 0)
      throw new BusinessException(Messages.getInstance().getMessage(Messages.PAYMENT_CANT_BE_ALLOCATED));

    SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.paymentalloc.support.ui.PaymentSearchHelp"); //$NON-NLS-1$
    searchHelp.addSearchHelpListener(new SearchHelpListener() {

      public void searchPerformed(SearchHelpEvent event) {
        internalAllocatePayment((Payment) event.getItems()[0], isInteractive, docGroups);
      }

      public void searchCanceled(SearchHelpEvent event) {
        processorListener.canceled();
      }
    });
    searchHelp.search();
  }

  protected void internalAllocatePayment(Payment payment, boolean isInteractive, List<DocGroup> docGroups) {
    PersistentObject pmaConfig = getModuleConfiguration();
    RoundContext currencyPrecRoundContext = new RoundContext((Integer) pmaConfig.getAttribute("CurrencyPrec")); //$NON-NLS-1$
    CurrencyConversionResult conversionResult = null;
    BigDecimal docSumCur = BigDecimal.ZERO;
    BigDecimal docCurRate = BigDecimal.ONE;
    boolean isDocCurRateDirect = true;

    BigDecimal remnAllocatedSum = MathUtils.subtract(payment.getSumCur(), getAllocatedSum(payment, docGroups, docFlowParams.getDocument()), currencyPrecRoundContext);

    if (MathUtils.compareToZero(remnAllocatedSum) == 0)
      throw new BusinessException(Messages.getInstance().getMessage(Messages.PAYMENT_FULL_ALLOCATED));

    TransactHead transactHead = initializeTransactHead(payment, docFlowParams.getDocument(), docFlowParams.getProcessDate());

    if (payment.getCurCode() != docFlowParams.getDocument().getCurrency()) {
      conversionResult = getCurrencyConverter().conversionEx(
          payment.getCurCode(),
          docFlowParams.getDocument().getCurrency(),
          payment.getCurRateAuthority(),
          payment.getCurRateType(),
          payment.getPDate(),
          docFlowParams.getDocument().getSumCur());
      docSumCur = MathUtils.round(conversionResult.getAmount(), currencyPrecRoundContext);
      docCurRate = conversionResult.getRate();
      isDocCurRateDirect = conversionResult.isDirect();
    } else {
      docSumCur = docFlowParams.getDocument().getSumCur();
      docCurRate = BigDecimal.ONE;
    }

    if (docFlowParams.getDocument().getDocSection().isWithSpec()) {
      specList = initializeSpecList(docFlowParams.getSpecList(), docCurRate, isDocCurRateDirect);

      allocateAutomaticly(specList, remnAllocatedSum);
      if (isInteractive)
        allocateManualy(specList, remnAllocatedSum, docSumCur, transactHead, payment, docCurRate, isDocCurRateDirect);
      else
        processAllocation(docSumCur, transactHead, payment, docCurRate, isDocCurRateDirect);
    } else
      processAllocation(docSumCur, transactHead, payment, docCurRate, isDocCurRateDirect);
  }

  /**
   * Инициализировать заголовок связанного документа
   *
   * @param payment - запись журнала платежей
   * @param docHead - документ
   * @param date    - дата
   * @return заголовок связанного документа
   */
  protected TransactHead initializeTransactHead(Payment payment, DocHead docHead, Date date) {
    TransactHead transactHead = getTransactHeadService().initialize();
    transactHead.setPayment(payment);
    transactHead.setDocHead(docHead);
    transactHead.setPDate(date);
    transactHead.setSysClient((SysClient) ServerUtils.getCurrentSession().getSystemTenant());
    return transactHead;
  }

  /**
   * Обработать распределение
   *
   * @param docSumCur       - распределяемая сумма документа
   * @param transactHead    - заголовок связанного документа
   * @param payment         - запись журнала платежей
   * @param docCurRate      - курс
   * @param isCurRateDirect - признак направления курса
   */
  protected void processAllocation(BigDecimal docSumCur, TransactHead transactHead, Payment payment, BigDecimal docCurRate, boolean isCurRateDirect) {
    BigDecimal sumCur = BigDecimal.ZERO;
    if (docFlowParams.getDocument().getDocSection().isWithSpec())
      sumCur = getItemsAllocatedSum();
    else
      sumCur = docSumCur.min(docFlowParams.getPerformedSum());

    getTransactHeadService().create(transactHead);

    if (specList != null)
      processTransactSpecs(transactHead, docCurRate, isCurRateDirect);

    processTransactHead(transactHead, payment, sumCur, docSumCur);

    updateDocFlowSpecList(docFlowParams.getSpecList(), docCurRate, isCurRateDirect);
    updateDocFlowParamsByAllocatePayment(payment, transactHead, getDocAllocatedSum(sumCur, docCurRate, isCurRateDirect));
    processorListener.completed();
  }

  /**
   * Обработать позиции спецификации связанного документа
   *
   * @param transactHead       - заголовок связанного документа
   * @param docCurRate         - курс
   * @param isDocCurRateDirect - признак направления курса
   */
  protected void processTransactSpecs(TransactHead transactHead, BigDecimal docCurRate, boolean isDocCurRateDirect) {
    for (SpecInfo specInfo : specList) {
      if (specInfo.getAQty().compareTo(BigDecimal.ZERO) != 0 || specInfo.getASumma().compareTo(BigDecimal.ZERO) != 0) {
        TransactSpec transactSpec = getTransactSpecService().initialize();
        transactSpec.setTrHead(transactHead);
        transactSpec.setDocSpec(specInfo.getDocSpec());
        transactSpec.setTotalQty(specInfo.getQty());
        transactSpec.setTotalSum(specInfo.getSumma());
        transactSpec.setAllocQty(specInfo.getAQty());
        transactSpec.setAllocSum(specInfo.getASumma());

        getTransactSpecService().create(transactSpec);

        if (docFlowParams.getDocument().getDocSection().isWithTaxes())
          processTaxes(specInfo, transactSpec, docCurRate, isDocCurRateDirect);
      }
    }
  }

  /**
   * Обновить список позиций спецификации соответсвии с распределением
   *
   * @param docSpecs           - список позиций спецификации документа
   * @param docCurRate         - курс
   * @param isDocCurRateDirect - признак направления курса
   */
  private void updateDocFlowSpecList(List<DocumentSpecItem> docSpecs, BigDecimal docCurRate, boolean isDocCurRateDirect) {
    for (int i = 0; i < docSpecs.size(); i++) {
      DocumentSpecItem docSpec = docSpecs.get(i);
      docSpec.setPerformedQuantity1(specList.get(i).getAQty());
      if (BigDecimal.ONE.compareTo(docCurRate) != 0) {
        if (isDocCurRateDirect)
          docSpec.setPerformedSum(specList.get(i).getASumma().divide(docCurRate));
        else
          docSpec.setPerformedSum(specList.get(i).getASumma().multiply(docCurRate));
      } else
        docSpec.setPerformedSum(specList.get(i).getASumma());
    }
  }

  /**
   * Обработать заголовок связанного документа
   *
   * @param transactHead - заголовок связанного документа
   * @param payment      - запись журнала платежей
   * @param sumCur       - распределённая сумма документа
   * @param docSumCur    - сумма документа
   */
  protected void processTransactHead(TransactHead transactHead, Payment payment, BigDecimal sumCur, BigDecimal docSumCur) {
    PersistentObject pmaConfig = getModuleConfiguration();
    Integer currencyPrec = (Integer) pmaConfig.getAttribute("CurrencyPrec"); //$NON-NLS-1$

    transactHead.setAllocSumCur(sumCur);
    transactHead.setTotalSumCur(docSumCur);

    if (payment.getCurCode() != (Currency) pmaConfig.getAttribute("NatCurrency")) { //$NON-NLS-1$
      CurrencyConversionResult conversionResult = getCurrencyConverter().conversionEx(
          (Currency) pmaConfig.getAttribute("NatCurrency"), //$NON-NLS-1$
          payment.getCurCode(),
          payment.getCurRateAuthority(),
          payment.getCurRateType(),
          payment.getPDate(),
          sumCur);
      transactHead.setAllocSumNat(MathUtils.round(conversionResult.getAmount(), new RoundContext(currencyPrec)));

      if (conversionResult.isDirect())
        transactHead.setTotalSumNat(MathUtils.multiply(docSumCur, conversionResult.getRate(), new RoundContext(currencyPrec)));
      else
        transactHead.setTotalSumNat(MathUtils.divide(docSumCur, conversionResult.getRate(), new RoundContext(currencyPrec)));
    } else {
      transactHead.setAllocSumNat(sumCur);
      transactHead.setTotalSumNat(docSumCur);
    }
    getTransactHeadService().store(transactHead);
  }

  /**
   * Получить распределённую сумму документа с учетом курса валюты
   *
   * @param sumCur          - распределённая сумма документа
   * @param docCurRate      - курс валюты
   * @param isCurRateDirect - признак направления курса
   * @return распределённая сумма документа с учетом курса валюты
   */
  private BigDecimal getDocAllocatedSum(BigDecimal sumCur, BigDecimal docCurRate, boolean isCurRateDirect) {
    BigDecimal allocatedSum = BigDecimal.ZERO;
    if (BigDecimal.ONE.compareTo(docCurRate) != 0) {
      if (isCurRateDirect)
        allocatedSum = sumCur.divide(docCurRate);
      else
        allocatedSum = sumCur.multiply(docCurRate);
    } else
      allocatedSum = sumCur;
    return allocatedSum;
  }

  private void updateDocFlowParamsByAllocatePayment(Payment payment, TransactHead transactHead, BigDecimal allocatedSum) {
    docFlowParams.setData1(payment.getId());
    docFlowParams.setData2(transactHead.getId());
    docFlowParams.setPerformedSum(allocatedSum);
  }

  /**
   * Инициализация списка распределяемых позиций спецификации
   *
   * @param docSpecs           - позиций спецификации документа
   * @param docCurRate         - курс
   * @param isDocCurRateDirect - признак направления курса
   * @return список распределяемых позиций спецификации
   */
  protected List<SpecInfo> initializeSpecList(List<DocumentSpecItem> docSpecs, BigDecimal docCurRate, boolean isDocCurRateDirect) {
    List<SpecInfo> specList = new ArrayList<SpecInfo>();
    for (int i = 0; i < docSpecs.size(); i++) {
      DocumentSpecItem docSpecItem = docSpecs.get(i);
      SpecInfo specInfo = initializeByDocSpecItem(docSpecItem, docCurRate, isDocCurRateDirect);
      specInfo.setAQty(docSpecItem.getDocSpec().getQuantity());
      specInfo.setCQty(docSpecItem.getDocSpec().getQuantity());

      if (BigDecimal.ONE.compareTo(docCurRate) != 0) {
        if (isDocCurRateDirect)
          specInfo.setASumma(docSpecItem.getDocSpec().getSumma().multiply(docCurRate));
        else
          specInfo.setASumma(docSpecItem.getDocSpec().getSumma().divide(docCurRate));
      } else
        specInfo.setASumma(docSpecItem.getDocSpec().getSumma());

      specInfo.setCSumma(specInfo.getASumma());

      specList.add(specInfo);
    }
    return specList;
  }

  /**
   * Автоматическое распределение
   *
   * @param specList      - список позиций спецификации
   * @param sumToAllocate - сумма для рапределения
   */
  private void allocateAutomaticly(List<SpecInfo> specList, BigDecimal sumToAllocate) {
    RoundContext currencyPrecRoundContext = new RoundContext((Integer) getModuleConfiguration().getAttribute("CurrencyPrec")); //$NON-NLS-1$
    BigDecimal oSum = sumToAllocate;
    boolean endOfPayment = false;
    for (SpecInfo specInfo : specList) {
      if (endOfPayment) {
        specInfo.setAQty(BigDecimal.ZERO);
        specInfo.setASumma(BigDecimal.ZERO);
      } else {
        BigDecimal sQty = specInfo.getAQty();
        BigDecimal sSum = specInfo.getASumma();
        specInfo.setASumma(oSum.min(sSum));
        if (sSum.compareTo(BigDecimal.ZERO) != 0)
          specInfo.setAQty(MathUtils.multiply(sQty, MathUtils.divide(specInfo.getASumma(), sSum, currencyPrecRoundContext), currencyPrecRoundContext));
        else
          specInfo.setAQty(BigDecimal.ZERO);

        oSum = oSum.subtract(specInfo.getASumma());

        if (oSum.compareTo(BigDecimal.ZERO) <= 0)
          endOfPayment = true;
      }
    }
  }

  /**
   * Интерактивное распределение
   *
   * @param allocationList  - список позиций спецификации
   * @param sumToAllocate   - сумма для рапределения
   * @param docSumCur       - сумма документа
   * @param transactHead    - связанный документ
   * @param payment         - запись журнала платежей
   * @param docCurRate      - курс валюты
   * @param isCurRateDirect - признак направления курса
   */
  private void allocateManualy(List<SpecInfo> allocationList, BigDecimal sumToAllocate, final BigDecimal docSumCur, final TransactHead transactHead, final Payment payment, final BigDecimal docCurRate, final boolean isCurRateDirect) {
    final InteractiveAllocationDlg dialog = (InteractiveAllocationDlg) UIProducer.produceForm("com/mg/merp/paymentalloc/resources/InteractiveAllocationDlg.mfd.xml"); //$NON-NLS-1$;
    dialog.addOkActionListener(new FormActionListener() {

      public void actionPerformed(FormEvent event) {
        specList = dialog.getAllocationList();
        processAllocation(docSumCur, transactHead, payment, docCurRate, isCurRateDirect);

      }
    });
    dialog.addCancelActionListener(new FormActionListener() {

      public void actionPerformed(FormEvent event) {
        processorListener.canceled();
      }
    });
    dialog.setParams(allocationList, sumToAllocate);
    dialog.execute();
  }

  /**
   * Получить распределенную сумму позиций спецификации
   *
   * @return распределенная сумма позиций спецификации
   */
  protected BigDecimal getItemsAllocatedSum() {
    BigDecimal allocatedSum = BigDecimal.ZERO;
    for (SpecInfo specInfo : specList) {
      if (specInfo.getAQty().compareTo(BigDecimal.ZERO) != 0 || specInfo.getASumma().compareTo(BigDecimal.ZERO) != 0)
        allocatedSum = allocatedSum.add(specInfo.getASumma());
    }
    return allocatedSum;
  }

  /**
   * Инициализировать распределяемую позицию параметрами позиции спецификации документа
   *
   * @param docSpecItem   - позиция спецификации документа
   * @param docCurRate    - курс валюты
   * @param curRateDirect - признак направления курса
   * @return распределяемая позиция
   */
  protected SpecInfo initializeByDocSpecItem(DocumentSpecItem docSpecItem, BigDecimal docCurRate, boolean curRateDirect) {
    SpecInfo specInfo = new SpecInfo();
    DocSpec docSpec = docSpecItem.getDocSpec();
    specInfo.setDocSpec(docSpec);

    if (docSpec.getCatalog() != null) {
      specInfo.setCode(docSpec.getCatalog().getCode());
      specInfo.setCName(docSpec.getCatalog().getFullName());
    }
    if (docSpec.getMeasure1() != null)
      specInfo.setMCode(docSpec.getMeasure1().getCode());

    specInfo.setQty(docSpecItem.getDocSpec().getQuantity());

    if (BigDecimal.ONE.compareTo(docCurRate) != 0) {
      if (curRateDirect)
        specInfo.setSumma(docSpecItem.getDocSpec().getSumma().multiply(docCurRate));
      else
        specInfo.setSumma(docSpecItem.getDocSpec().getSumma().divide(docCurRate));
    } else
      specInfo.setSumma(docSpecItem.getDocSpec().getSumma());

    return specInfo;
  }

  /**
   * Обработка налогов распределяемой позиции спецификации
   *
   * @param specInfo      - распределённая позиция спецификации
   * @param transactSpec  - позиция спецификации, связанного документа
   * @param docCurRate    - курс валюты
   * @param curRateDirect - признак направления курса
   */
  protected void processTaxes(SpecInfo specInfo, TransactSpec transactSpec, BigDecimal docCurRate, boolean curRateDirect) {
    RoundContext currencyPrecRoundContext = new RoundContext((Integer) getModuleConfiguration().getAttribute("CurrencyPrec")); //$NON-NLS-1$

    Projection projection = Projections.projectionList(Projections.sum("SumElement"), Projections.groupProperty("Id")); //$NON-NLS-1$ //$NON-NLS-2$
    BigDecimal taxSum = BigDecimal.ZERO;
    List<Object[]> documentSpecTaxes = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(DocumentSpecTax.class)
        .setProjection(projection)
        .add(Restrictions.eq("DocSpec", specInfo.getDocSpec()))); //$NON-NLS-1$

    for (Object[] documentSpecTax : documentSpecTaxes) {
      TransactTax transactTax = getTransactTaxService().initialize();
      transactTax.setTrSpec(transactSpec);
      DocumentSpecTax docSpecTax = ServerUtils.getPersistentManager().find(DocumentSpecTax.class, documentSpecTax[1]);
      transactTax.setTaxSumm(docSpecTax);
      transactTax.setTax(docSpecTax.getTax());

      taxSum = (BigDecimal) documentSpecTax[0];
      if (BigDecimal.ONE.compareTo(docCurRate) != 0) {
        if (curRateDirect)
          taxSum = taxSum.multiply(docCurRate);
        else
          taxSum = taxSum.divide(docCurRate);
      }
      transactTax.setTotalSum(taxSum);
      transactTax.setAllocSum(MathUtils.multiply(taxSum, MathUtils.divide(specInfo.getASumma(), specInfo.getSumma(), currencyPrecRoundContext), currencyPrecRoundContext));

      getTransactTaxService().create(transactTax);
    }
  }

  /**
   * Получить распределённую сумму платежа для документа
   *
   * @param payment   - платеж
   * @param docGroups - группы документов, к которым принадлежит документ
   * @param docHead   - документ
   * @return распределённая сумм платежа для документа
   */
  protected BigDecimal getAllocatedSum(Payment payment, List<DocGroup> docGroups, DocHead docHead) {
    DetachedCriteria dc = DetachedCriteria.forClass(DocGroupLink.class)
        .setProjection(Projections.property("Id")) //$NON-NLS-1$
        .add(Restrictions.in("DocGroup", docGroups)) //$NON-NLS-1$
        .add(Restrictions.eq("DocType", docHead.getDocType())); //$NON-NLS-1$

    Projection projection = Projections.sum("AllocSumCur"); //$NON-NLS-1$

    Object result = OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(TransactHead.class)
        .setProjection(projection)
        .add(Restrictions.eq("Payment", payment)) //$NON-NLS-1$
        .add(Restrictions.eq("DocHead", docHead)) //$NON-NLS-1$
        .add(Subqueries.exists(dc)));

    if (result != null)
      return (BigDecimal) result;
    else
      return BigDecimal.ZERO;
  }

  /**
   * Получить список групп документов к которым принадлежит тип документа
   *
   * @param docType - тип документа
   * @return список групп документов
   */
  private List<DocGroup> getDocGroups(DocType docType) {
    Projection projection = Projections.projectionList(Projections.property("DocGroup")); //$NON-NLS-1$

    return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(DocGroupLink.class)
        .setProjection(projection)
        .add(Restrictions.eq("DocType", docType))); //$NON-NLS-1$
  }

  private void prepareProcessor(DocFlowPluginInvokeParams docFlowParams, PaymentallocProcessorListener processorListener) {
    this.docFlowParams = docFlowParams;
    this.processorListener = processorListener;
  }

  protected void doCreatePayment() throws Exception {
    Integer patternId = docFlowParams.getPerformedStage().getLinkDocModel();

    if (patternId == null) {
      SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.paymentalloc.support.ui.PaymentModelSearchHelp"); //$NON-NLS-1$
      searchHelp.addSearchHelpListener(new SearchHelpListener() {

        public void searchPerformed(SearchHelpEvent event) {
          internalCreatePayment((Payment) event.getItems()[0]);
        }

        public void searchCanceled(SearchHelpEvent event) {
          processorListener.canceled();
        }
      });
      searchHelp.search();
    } else
      internalCreatePayment(loadPaymentPattern(patternId));
  }

  protected void internalCreatePayment(Payment pattern) {
    Payment payment = initializePaymentByDoc(createPaymentByPattern(pattern, docFlowParams.getPerformedStage().getLinkDocDestFolder()), docFlowParams.getDocument());
    if (payment.getPDate() == null)
      payment.setPDate(docFlowParams.getProcessDate());

    computeSumValues(payment);
    setAccordingOrgUnit(payment);
    storePayment(payment);

    if (docFlowParams.getPerformedStage().isShowNewDocument())
      showPayment(payment);
    else {
      updateDocFlowParams(payment);
      processorListener.completed();
    }
  }

  private void showPayment(final Payment payment) {
    if (payment.getId() != null)
      MaintenanceHelper.edit(getPaymentService(), payment.getId(), null, new MaintenanceFormActionListener() {

        public void canceled(MaintenanceFormEvent event) {
          processorListener.canceled();
        }

        public void performed(MaintenanceFormEvent event) {
          updateDocFlowParams(payment);
          processorListener.completed();
        }
      });
  }

  private void updateDocFlowParams(Payment payment) {
    this.docFlowParams.setData1(payment.getId());
  }

  /**
   * Расчитать денежные величины записи журнала платежей
   *
   * @param payment - запись журнала платежей
   */
  protected void computeSumValues(Payment payment) {
    PersistentObject pmaConfig = getModuleConfiguration();
    CurrencyServiceLocal currencyConverter = getCurrencyConverter();

    payment.setSumCur(MathUtils.round(docFlowParams.getPerformedSum(), new RoundContext((Integer) pmaConfig.getAttribute("CurrencyPrec")))); //$NON-NLS-1$

    // берем валюту из документа, если она не проставлена в образце
    if (payment.getCurCode() == null) {
      payment.setCurCode(docFlowParams.getDocument().getCurrency());
      payment.setCurRateAuthority(docFlowParams.getDocument().getCurrencyRateAuthority());
      payment.setCurRateType(docFlowParams.getDocument().getCurrencyRateType());
    } else if (payment.getCurCode() != docFlowParams.getDocument().getCurrency()) {
      BigDecimal convertedSum = currencyConverter.conversion(
          payment.getCurCode(),
          docFlowParams.getDocument().getCurrency(),
          payment.getCurRateAuthority(),
          payment.getCurRateType(),
          payment.getPDate(),
          docFlowParams.getPerformedSum());

      payment.setSumCur(MathUtils.round(convertedSum, new RoundContext((Integer) pmaConfig.getAttribute("CurrencyPrec")))); //$NON-NLS-1$
    }
    CurrencyConversionResult conversionResult = currencyConverter.conversionEx(
        (Currency) pmaConfig.getAttribute("NatCurrency"), //$NON-NLS-1$
        payment.getCurCode(),
        payment.getCurRateAuthority(),
        payment.getCurRateType(),
        payment.getPDate(),
        BigDecimal.ONE);
    payment.setCurRate(conversionResult.getRate());

    if (((Currency) pmaConfig.getAttribute("NatCurrency")) == docFlowParams.getDocument().getCurrency()) //$NON-NLS-1$
      payment.setSumNat(docFlowParams.getPerformedSum());
    else
      payment.setSumNat(MathUtils.multiply(payment.getSumCur(), payment.getCurRate(), new RoundContext((Integer) pmaConfig.getAttribute("CurrencyPrec")))); //$NON-NLS-1$
  }

  /**
   * Установить подразделения компании для записи журнала платежей
   *
   * @param payment - запись журнала платежей
   */
  protected void setAccordingOrgUnit(Payment payment) {
    payment.setFromUnit(getOrgUnit(payment.getContractorFrom()));
    payment.setToUnit(getOrgUnit(payment.getContractorTo()));

    if (payment.getFromUnit() == null && payment.getToUnit() == null)
      throw new BusinessException(Messages.getInstance().getMessage(Messages.ORG_UNIT_NOT_FOUND));
  }

  /**
   * Получить подразделение компании, связанное с контрагентом
   *
   * @param partner - контрагент
   * @return подразделение компании, связанное с контрагентом
   */
  protected Contractor getOrgUnit(Contractor partner) {
    if (partner != null)
      return OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(OrgUnit.class)
          .add(Restrictions.eq("Partner", partner))); //$NON-NLS-1$
    else
      return null;
  }

  /**
   * Инициализировать запись журнала данными из документа
   *
   * @param payment  - запись журнала
   * @param document - документ
   * @return запись журнала платежей
   */
  protected Payment initializePaymentByDoc(Payment payment, DocHead document) {
    if (payment != null) {
      payment.setDocHead(document);
      payment.setDocType(document.getDocType());
      payment.setDocNumber(document.getDocNumber());
      payment.setDocDate(document.getDocDate());

      payment.setBaseDoc(document.getBaseDocument());
      payment.setBaseDocType(document.getBaseDocType());
      payment.setBaseDocNumber(document.getBaseDocNumber());
      payment.setBaseDocDate(document.getBaseDocDate());

      payment.setContract(document.getContract());
      payment.setContractType(document.getContractType());
      payment.setContractNumber(document.getContractNumber());
      payment.setContractDate(document.getContractDate());

      payment.setContractorFrom(document.getFrom());
      payment.setContractorTo(document.getTo());

      // назначение платежа
      int docSection = document.getDocSection().getId();
      if (docSection == BANK_DOCUMENT_IN_DOCSECTION || docSection == BANK_DOCUMENT_OUT_DOCSECTION || docSection == BANK_DOCUMENT_OTHER_DOCSECTION)
        payment.setAttribute("Description", document.getAttribute("Comment")); //$NON-NLS-1$ //$NON-NLS-2$
      else if (docSection == CASH_DOCUMENT_IN_DOCSECTION || docSection == CASH_DOCUMENT_OUT_DOCSECTION)
        payment.setAttribute("Description", document.getAttribute("Base")); //$NON-NLS-1$ //$NON-NLS-2$

      // clone custom fields
      CustomFieldsManagerLocator.locate().cloneValues(DocumentUtils.getDocumentService(document.getDocSection()), document,
          (PaymentServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PaymentServiceLocal.LOCAL_SERVICE_NAME), payment);
    }
    return payment;
  }

  /**
   * Создать запись журнала по образцу
   *
   * @param pattern           - образец записи журнала
   * @param destinationFolder - папка-приемник записи
   * @return запись журнала платежей
   */
  protected Payment createPaymentByPattern(Payment pattern, Folder destinationFolder) {
    Folder dstFolder = null;
    // если папка-приемник не установлена в образце записи журнала, то берем папку-приемник из параметров ДО
    if (pattern.getDestFolder() != null)
      dstFolder = pattern.getDestFolder();
    else
      dstFolder = destinationFolder;

    if (dstFolder == null)
      throw new BusinessException(Messages.getInstance().getMessage(Messages.PAYMENT_DST_FOLDER_INVALID));

    Payment payment = getPaymentService().createByPattern(pattern, dstFolder);
    payment.setTransactHeads(null);
    return payment;
  }

  protected void doRollBackCreatePayment(DocFlowPluginInvokeParams docFlowParams) {
    if (docFlowParams.getData1() != null) {
      Payment payment = ServerUtils.getPersistentManager().find(Payment.class, docFlowParams.getData1());
      if (payment != null)
        getPaymentService().erase(payment);
      else
        getLogger().warn("attempt to delete non-existent entity"); //$NON-NLS-1$
    }
  }

  private TransactTaxServiceLocal getTransactTaxService() {
    return (TransactTaxServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(TransactTaxServiceLocal.LOCAL_SERVICE_NAME);
  }

  private TransactSpecServiceLocal getTransactSpecService() {
    return (TransactSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(TransactSpecServiceLocal.LOCAL_SERVICE_NAME);
  }

  private PaymentServiceLocal getPaymentService() {
    return (PaymentServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PaymentServiceLocal.LOCAL_SERVICE_NAME);
  }

  private TransactHeadServiceLocal getTransactHeadService() {
    return (TransactHeadServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(TransactHeadServiceLocal.LOCAL_SERVICE_NAME);
  }

  private Payment loadPaymentPattern(Integer patternId) {
    return ServerUtils.getPersistentManager().find(Payment.class, patternId);
  }

  private PersistentObject getModuleConfiguration() {
    SysClient sysClient = (SysClient) ServerUtils.getCurrentSession().getSystemTenant();
    return ServerUtils.getPersistentManager().find("com.mg.merp.account.model.AccConfig", sysClient.getId()); //$NON-NLS-1$
  }

  private CurrencyServiceLocal getCurrencyConverter() {
    return (CurrencyServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Currency"); //$NON-NLS-1$
  }

  private void storePayment(Payment payment) {
    getPaymentService().create(payment);
  }

}
