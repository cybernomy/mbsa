/*
 * PmcHelper.java
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
package com.mg.merp.paymentcontrol.support.ui;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.ui.Alert;
import com.mg.framework.api.ui.AlertListener;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.Folder;
import com.mg.merp.paymentcontrol.ExecutionServiceLocal;
import com.mg.merp.paymentcontrol.LiabilityServiceLocal;
import com.mg.merp.paymentcontrol.PmcHelperListener;
import com.mg.merp.paymentcontrol.ResourceServiceLocal;
import com.mg.merp.paymentcontrol.model.Execution;
import com.mg.merp.paymentcontrol.model.ExecutionParams;
import com.mg.merp.paymentcontrol.model.Liability;
import com.mg.merp.paymentcontrol.model.PlanPaymentItem;
import com.mg.merp.paymentcontrol.model.PmcResource;
import com.mg.merp.paymentcontrol.model.TransferParams;
import com.mg.merp.paymentcontrol.support.Messages;
import com.mg.merp.reference.model.Currency;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Класс помощник для работы с обязательствами
 *
 * @author Artem V. Sharapov
 * @version $Id: PmcHelper.java,v 1.1 2007/05/14 05:23:52 sharapov Exp $
 */
public class PmcHelper {

  /**
   * Исполнить обязательство
   *
   * @param resourceId        - идентификатор средства платежа
   * @param liabilityId       - идентификатор обязательства
   * @param actDate           - дата операции
   * @param pmcHelperListener - слушатель завершения операции
   */
  public static void executeLiabilityByPmcManagment(Integer resourceId, Integer liabilityId, Date actDate, final PmcHelperListener pmcHelperListener) {
    if (resourceId == null || liabilityId == null || actDate == null)
      return;

    PmcResource resource = ServerUtils.getPersistentManager().find(PmcResource.class, resourceId);
    final Liability liability = ServerUtils.getPersistentManager().find(Liability.class, liabilityId);
    ExecutionParams executionParams = prepareExecutionParams(resource, liability, actDate);
    if (liability.getPrefOnly() && liability.getPrefResource() != null)
      resource = liability.getPrefResource();
    final PmcResource resourceToExecute = resource;

    final ExecutionDlg executionDialog = createExecutionDialog(resource.getName(), executionParams.getSumToExecute(), resource.getCurCode(), executionParams.getActDate(), executionParams.getDateToExecute());
    executionDialog.addOkActionListener(new FormActionListener() {

      /* (non-Javadoc)
       * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
       */
      public void actionPerformed(FormEvent event) {
        getExecutionService().executeLiability(
            liability,
            resourceToExecute,
            executionDialog.getExecutionDate(),
            executionDialog.getSumToExecute());

        pmcHelperListener.complete();
      }
    });
    executionDialog.execute();
  }

  /**
   * Исполнить обязательство
   *
   * @param liabilityId       - идентификатор обязательства
   * @param planPaymentItem   - строка оборотки плана
   * @param versionId         - идентификатор версии планирования
   * @param pmcHelperListener - слушатель завершения операции
   */
  public static void executeLiabilityByPmcPlaning(Integer liabilityId, final PlanPaymentItem planPaymentItem, final Integer versionId, final PmcHelperListener pmcHelperListener) {
    final Liability liability = ServerUtils.getPersistentManager().find(Liability.class, liabilityId);
    ExecutionParams executionParams = prepareExecutionParams(liabilityId, planPaymentItem, versionId);
    final ExecutionDlg executionDialog = createExecutionDialog(executionParams.getResourceName(), executionParams.getSumToExecute(), liability.getCurCode(), executionParams.getActDate(), executionParams.getDateToExecute());

    executionDialog.addOkActionListener(new FormActionListener() {

      /* (non-Javadoc)
       * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
       */
      public void actionPerformed(FormEvent event) {
        getExecutionService().executeLiability(
            planPaymentItem.getResourceId(),
            planPaymentItem.getResourceFolderId(),
            liability,
            versionId,
            executionDialog.getExecutionDate(),
            executionDialog.getSumToExecute());

        pmcHelperListener.complete();
      }
    });
    executionDialog.execute();
  }

  /**
   * Подготовить параметры отображения для диалога "Параметры исполнения обязательства"
   *
   * @param liabilityId     - идентификатор обязательства
   * @param planPaymentItem - строка оборотки плана
   * @param versionId       - идентификатор версии планирования
   * @return параметры для диалога
   */
  private static ExecutionParams prepareExecutionParams(Integer liabilityId, PlanPaymentItem planPaymentItem, Integer versionId) {
    Liability liability = ServerUtils.getPersistentManager().find(Liability.class, liabilityId);

    if (liability.getCurCode().getCode().compareTo(planPaymentItem.getCurrencyCode()) != 0 && planPaymentItem.getResourceId() != null)
      throw new BusinessException(Messages.getInstance().getMessage(Messages.IS_NOT_SAME_CURRENCY));

    BigDecimal remnSum = getLiabilityService().getRemnSum(liabilityId, versionId);

    if (!isCanBeExecuted(remnSum))
      throw new BusinessException(Messages.getInstance().getMessage(Messages.CAN_NOT_BE_EXECUTED));

    BigDecimal sumToExecute = BigDecimal.ZERO;
    if (liability.getReceivable())
      sumToExecute = remnSum;
    else
      sumToExecute = remnSum.min(planPaymentItem.getEndSaldo());

    String resourceName;
    if (liability.getPrefOnly() && liability.getPrefResource() != null)
      resourceName = liability.getPrefResource().getName();
    else
      resourceName = planPaymentItem.getResource();

    Date dateToExecute = liability.getDateToExecute();
    Date actDate;
    if (liability.getDateToExecute().compareTo(planPaymentItem.getDateFrom()) >= 0 && liability.getDateToExecute().compareTo(planPaymentItem.getDateTill()) <= 0)
      actDate = liability.getDateToExecute();
    else
      actDate = planPaymentItem.getDateFrom();

    ExecutionParams executionParams = new ExecutionParams();
    executionParams.setSumToExecute(sumToExecute);
    executionParams.setResourceName(resourceName);
    executionParams.setDateToExecute(dateToExecute);
    executionParams.setActDate(actDate);

    return executionParams;
  }

  /**
   * Подготовить параметры отображения для диалога "Параметры исполнения обязательства"
   *
   * @param resource  - средство платежа
   * @param liability - обязательство
   * @param actDate   - дата операции
   * @return параметры для диалога
   */
  private static ExecutionParams prepareExecutionParams(PmcResource resource, Liability liability, Date actDate) {
    if (!isSameCurrency(resource.getCurCode(), liability.getCurCode()))
      throw new BusinessException(Messages.getInstance().getMessage(Messages.IS_NOT_SAME_CURRENCY));

    BigDecimal remnSum = getLiabilityService().getRemnSum(liability.getId(), null);

    if (!isCanBeExecuted(remnSum))
      throw new BusinessException(Messages.getInstance().getMessage(Messages.CAN_NOT_BE_EXECUTED));

    BigDecimal sumToExecute = BigDecimal.ZERO;
    if (liability.getReceivable())
      sumToExecute = remnSum;
    else {
      BigDecimal balance = getResourceService().getBalance((Integer) resource.getId(), actDate);
      sumToExecute = remnSum.min(balance);
    }

    if (liability.getPrefOnly() && liability.getPrefResource() != null) {
      resource = liability.getPrefResource();
      //TODO: нужна ли повторная проверка валюты если в качестве средства платежа используется "предпочтительное средство платежа" обязательства?
      //TODO: по какому средству платежа расчитывать Баланс?
    }

    ExecutionParams executionParams = new ExecutionParams();
    executionParams.setSumToExecute(sumToExecute);
    executionParams.setResourceName(resource.getName());
    executionParams.setDateToExecute(liability.getDateToExecute());
    executionParams.setActDate(liability.getDateToExecute());

    return executionParams;
  }

  /**
   * Создать диалог "Параметры исполнения обязательства" с параметрами
   *
   * @param resourceName  - наименование средства платежа
   * @param sumToExecute  - сумма исполнения
   * @param executionCur  - валюта исполнения
   * @param actDate       - дата исполнения
   * @param dateToExecute - исполнить
   * @return диалог
   */
  private static ExecutionDlg createExecutionDialog(String resourceName, BigDecimal sumToExecute, Currency executionCur, Date actDate, Date dateToExecute) {
    ExecutionDlg executionDlg = (ExecutionDlg) UIProducer.produceForm("com/mg/merp/paymentcontrol/resources/ExecutionDlg.mfd.xml"); //$NON-NLS-1$
    executionDlg.setResourceName(resourceName);
    executionDlg.setSumToExecute(sumToExecute);
    executionDlg.setExecutionCur(executionCur);
    executionDlg.setExecutionDate(actDate);
    executionDlg.setDateToExecute(dateToExecute);
    return executionDlg;
  }

  /**
   * Проверка возможности исполнения обязательства
   *
   * @param remnSum - неисполненный остаток обязательства
   * @return true - да возможно; false - обязательство уже исполнено
   */
  private static boolean isCanBeExecuted(BigDecimal remnSum) {
    if (MathUtils.compareToZero(remnSum) <= 0)
      return false;
    else
      return true;
  }

  /**
   * Переместить средства
   *
   * @param resourceId       - идентификатор средства платежа
   * @param resourceFolderId - идентификатор папки средства платежа
   * @param versionId        - идентификатор версии планирования
   * @param actDate          - дата операции
   */
  public static void transferResourses(Integer resourceId, Integer resourceFolderId, final Integer versionId, Date actDate, final PmcHelperListener pmcHelperListener) {
    final TransferDlg transferDlg = (TransferDlg) UIProducer.produceForm("com/mg/merp/paymentcontrol/resources/TransferDlg.mfd.xml"); //$NON-NLS-1$
    PmcResource resourceExpense = null;
    Folder resourceFolderExpense = null;

    if (resourceId != null)
      resourceExpense = ServerUtils.getPersistentManager().find(PmcResource.class, resourceId);
    if (resourceFolderId != null)
      resourceFolderExpense = ServerUtils.getPersistentManager().find(Folder.class, resourceFolderId);

    if (resourceExpense != null)
      transferDlg.setResourceExpense(resourceExpense);
    else
      transferDlg.setResourceFolderExpense(resourceFolderExpense);
    transferDlg.setDateExpense(actDate);

    transferDlg.addOkActionListener(new FormActionListener() {

      /* (non-Javadoc)
       * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
       */
      public void actionPerformed(FormEvent event) {
        TransferParams transferParams = new TransferParams(
            transferDlg.getResourceFolderExpense(),
            transferDlg.getResourceExpense(),
            transferDlg.getDateExpense(),
            transferDlg.getSumExpense(),
            transferDlg.getLiabilityModelExpense(),
            transferDlg.getResourceFolderIncome(),
            transferDlg.getResourceIncome(),
            transferDlg.getDateIncome(),
            transferDlg.getSumIncome(),
            transferDlg.getLiabilityModelIncome());

        getExecutionService().transferResourses(transferParams, versionId);

        pmcHelperListener.complete();
      }
    });
    transferDlg.execute();
  }

  /**
   * Сформировать документы
   *
   * @param executionIds - список идентификаторов исполнений
   * @param actDate      - дата формирования
   */
  public static void createDocuments(Serializable[] executionIds, Date actDate, final PmcHelperListener pmcHelperListener) {
    String report = getExecutionService().createDocuments(executionIds, actDate);
    pmcHelperListener.complete();
    throw new BusinessException(report);
  }

  /**
   * Удалить созданный документ
   *
   * @param executionIds - список идентификаторов исполнений
   */
  public static void deleteCreatedDocument(final Serializable[] executionIds, final PmcHelperListener pmcHelperListener) {
    if (executionIds != null && executionIds.length > 0) {
      Messages pmcMsg = Messages.getInstance();
      com.mg.framework.support.Messages msg = com.mg.framework.support.Messages.getInstance();
      final String yesButton = msg.getMessage(com.mg.framework.support.Messages.YES_BUTTON_TEXT);
      UIUtils.showAlert(Alert.MessageType.QUESTION_MESSAGE, msg.getMessage(com.mg.framework.support.Messages.ERASE_ALERT_TITLE), pmcMsg.getMessage(Messages.ERASE_DOC_QUESTION), yesButton, msg.getMessage(com.mg.framework.support.Messages.NO_BUTTON_TEXT), new AlertListener() {
        public void alertClosing(String value) {
          if (value.equals(yesButton)) {
            getExecutionService().deleteCreatedDocument(executionIds);
            pmcHelperListener.complete();
          }
        }
      });
    }
  }

  /**
   * Удалить исполнение
   *
   * @param executionId - идентификатор исполнения
   */
  public static void removeExecution(final Serializable executionId, final Integer versionId, final PmcHelperListener pmcHelperListener) {
    if (executionId != null) {
      com.mg.framework.support.Messages msg = com.mg.framework.support.Messages.getInstance();
      final String yesButton = msg.getMessage(com.mg.framework.support.Messages.YES_BUTTON_TEXT);
      UIUtils.showAlert(Alert.MessageType.QUESTION_MESSAGE, msg.getMessage(com.mg.framework.support.Messages.ERASE_ALERT_TITLE), msg.getMessage(com.mg.framework.support.Messages.ERASE_ALERT_QUESTION), yesButton, msg.getMessage(com.mg.framework.support.Messages.NO_BUTTON_TEXT), new AlertListener() {
        public void alertClosing(String value) {
          if (value.equals(yesButton)) {
            Execution execution = ServerUtils.getPersistentManager().find(Execution.class, executionId);
            getExecutionService().checkVersionStatus(versionId, execution.getPlanDate());
            getExecutionService().erase((Integer) executionId);

            pmcHelperListener.complete();
          }
        }
      });
    }
  }

  private static boolean isSameCurrency(Currency currency1, Currency currency2) {
    if (currency1.getId().equals(currency2.getId()))
      return true;
    else
      return false;
  }

  private static ExecutionServiceLocal getExecutionService() {
    return (ExecutionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(ExecutionServiceLocal.LOCAL_SERVICE_NAME);
  }

  private static ResourceServiceLocal getResourceService() {
    return (ResourceServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(ResourceServiceLocal.LOCAL_SERVICE_NAME);
  }

  private static LiabilityServiceLocal getLiabilityService() {
    return (LiabilityServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(LiabilityServiceLocal.LOCAL_SERVICE_NAME);
  }

}
