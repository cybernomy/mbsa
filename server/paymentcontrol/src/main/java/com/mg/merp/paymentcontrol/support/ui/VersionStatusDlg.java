/*
 * VersionStatusDlg.java
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

import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultDialog;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.paymentcontrol.model.PmcPeriod;
import com.mg.merp.paymentcontrol.model.VersionStatusKind;

import java.util.Calendar;

/**
 * Контроллер диалога "добавления статуса версии"
 *
 * @author Artem V. Sharapov
 * @version $Id: VersionStatusDlg.java,v 1.1 2007/05/14 05:23:52 sharapov Exp $
 */
public class VersionStatusDlg extends DefaultDialog {

  private VersionStatusKind kind;
  private String pmcPeriodName;
  private java.util.Date dateFrom;
  private java.util.Date dateTill;
  private java.util.Date createDate;
  private String creator;


  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    createDate = Calendar.getInstance().getTime();
    creator = ServerUtils.getUserProfile().getUserName();
    super.doOnRun();
  }

  /**
   * Обработчик кнопки "Выбрать период планирования"
   *
   * @param event - событие
   * @throws Exception - ИС
   */
  public void onActionChoosePmcPeriod(WidgetEvent event) throws Exception {
    SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.paymentcontrol.support.ui.PmcPeriodSearchHelp"); //$NON-NLS-1$
    searchHelp.addSearchHelpListener(new SearchHelpListener() {

      public void searchPerformed(SearchHelpEvent event) {
        PmcPeriod pmcPeriod = (PmcPeriod) event.getItems()[0];
        pmcPeriodName = pmcPeriod.getName();
        dateFrom = pmcPeriod.getDateFrom();
        dateTill = pmcPeriod.getDateTill();
        view.flushModel();
      }

      public void searchCanceled(SearchHelpEvent event) {
        // do nothing
      }
    });
    searchHelp.search();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultDialog#onActionOk(com.mg.framework.api.ui.WidgetEvent)
   */
  @Override
  public void onActionOk(WidgetEvent event) {
    if (kind != null && dateFrom != null && dateTill != null)
      if (kind != VersionStatusKind.NONE)
        super.onActionOk(event);
  }

  /**
   * @return the createDate
   */
  public java.util.Date getCreateDate() {
    return createDate;
  }

  /**
   * @param createDate the createDate to set
   */
  public void setCreateDate(java.util.Date createDate) {
    this.createDate = createDate;
  }

  /**
   * @return the creator
   */
  public String getCreator() {
    return creator;
  }

  /**
   * @param creator the creator to set
   */
  public void setCreator(String creator) {
    this.creator = creator;
  }

  /**
   * @return the dateFrom
   */
  public java.util.Date getDateFrom() {
    return dateFrom;
  }

  /**
   * @param dateFrom the dateFrom to set
   */
  public void setDateFrom(java.util.Date dateFrom) {
    this.dateFrom = dateFrom;
  }

  /**
   * @return the dateTill
   */
  public java.util.Date getDateTill() {
    return dateTill;
  }

  /**
   * @param dateTill the dateTill to set
   */
  public void setDateTill(java.util.Date dateTill) {
    this.dateTill = dateTill;
  }

  /**
   * @return the kind
   */
  public VersionStatusKind getKind() {
    return kind;
  }

  /**
   * @param kind the kind to set
   */
  public void setKind(VersionStatusKind kind) {
    this.kind = kind;
  }

  /**
   * @return the pmcPeriodName
   */
  public String getPmcPeriodName() {
    return pmcPeriodName;
  }

  /**
   * @param pmcPeriodName the pmcPeriodName to set
   */
  public void setPmcPeriodName(String pmcPeriodName) {
    this.pmcPeriodName = pmcPeriodName;
  }

}
