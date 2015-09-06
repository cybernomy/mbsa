/*
 * ScheduleRest.java
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
package com.mg.merp.lbschedule.support.ui;


import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm;
import com.mg.merp.document.model.DocType;
import com.mg.merp.lbschedule.model.ItemKind;
import com.mg.merp.lbschedule.model.ScheduleStatus;
import com.mg.merp.paymentcontrol.model.PmcResource;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.Currency;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Контроллер формы условий отбора "Графиков исполнения обязательств"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ScheduleRest.java,v 1.3 2007/04/21 11:49:33 sharapov Exp $
 */
public class ScheduleRest extends DefaultHierarhyRestrictionForm {

  @DataItemName("Document.DocNumber") //$NON-NLS-1$
  private String docNumber = ""; //$NON-NLS-1$
  @DataItemName("Document.DocDate") //$NON-NLS-1$
  private Date docDate = null;
  private DocType docType = null;
  private ScheduleStatus headStatus = null;
  @DataItemName("LbSchedule.Schedule.Comment") //$NON-NLS-1$
  private String comments = ""; //$NON-NLS-1$
  @DataItemName("LbSchedule.Cond.Item.Status") //$NON-NLS-1$
  private ScheduleStatus itemStatus = null;
  private ItemKind itemKindCode = null;
  @DataItemName("LbSchedule.Cond.Item.Currency") //$NON-NLS-1$
  private Currency itemCurrency = null;
  @DataItemName("LbSchedule.Cond.Item.Comments") //$NON-NLS-1$
  private String itemComments = null;
  @DataItemName("LbSchedule.Cond.Item.FactSumFrom") //$NON-NLS-1$
  private BigDecimal factSumFrom = null;
  @DataItemName("LbSchedule.Cond.Item.FactSumTill") //$NON-NLS-1$
  private BigDecimal factSumTill = null;
  @DataItemName("LbSchedule.Cond.Item.SumFrom") //$NON-NLS-1$
  private BigDecimal sumFrom = null;
  @DataItemName("LbSchedule.Cond.Item.SumTill")     //$NON-NLS-1$
  private BigDecimal sumTill = null;
  @DataItemName("LbSchedule.Cond.Item.ItemDateFrom") //$NON-NLS-1$
  private Date itemDateFrom = null;
  @DataItemName("LbSchedule.Cond.Item.ItemDateTill") //$NON-NLS-1$
  private Date itemDateTill = null;
  private Catalog catalogCode = null;
  @DataItemName("LbSchedule.Item.From") //$NON-NLS-1$
  private Contractor fromCode = null;
  @DataItemName("LbSchedule.Item.To") //$NON-NLS-1$
  private Contractor toCode = null;
  @DataItemName("LbSchedule.Item.ResourceTo") //$NON-NLS-1$
  private PmcResource resourceToCode = null;
  @DataItemName("LbSchedule.Item.ResourceFrom") //$NON-NLS-1$
  private PmcResource resourceFromCode = null;

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm#doClearRestrictionItem()
   */
  @Override
  protected void doClearRestrictionItem() {
    super.doClearRestrictionItem();
    this.docNumber = ""; //$NON-NLS-1$
    this.docDate = null;
    this.docType = null;
    this.headStatus = null;
    this.itemComments = ""; //$NON-NLS-1$
    this.comments = null;
    this.itemCurrency = null;
    this.itemKindCode = null;
    this.itemStatus = null;
    this.itemDateFrom = null;
    this.itemDateTill = null;
    this.sumFrom = null;
    this.sumTill = null;
    this.factSumFrom = null;
    this.factSumTill = null;
    this.catalogCode = null;
    this.toCode = null;
    this.fromCode = null;
    this.resourceFromCode = null;
    this.resourceToCode = null;
  }

  /**
   * @return Returns the catalogCode.
   */
  protected Catalog getCatalogCode() {
    return catalogCode;
  }

  /**
   * @return Returns the comments.
   */
  protected String getComments() {
    return comments;
  }

  /**
   * @return Returns the docDate.
   */
  protected Date getDocDate() {
    return docDate;
  }

  /**
   * @return Returns the docNumber.
   */
  protected String getDocNumber() {
    return docNumber;
  }

  /**
   * @return Returns the factSumFrom.
   */
  protected BigDecimal getFactSumFrom() {
    return factSumFrom;
  }

  /**
   * @return Returns the factSumTill.
   */
  protected BigDecimal getFactSumTill() {
    return factSumTill;
  }

  /**
   * @return Returns the fromCode.
   */
  protected Contractor getFromCode() {
    return fromCode;
  }

  /**
   * @return Returns the headStatus.
   */
  protected ScheduleStatus getHeadStatus() {
    return headStatus;
  }

  /**
   * @return Returns the itemComments.
   */
  protected String getItemComments() {
    return itemComments;
  }

  /**
   * @return Returns the itemCurrency.
   */
  protected Currency getItemCurrency() {
    return itemCurrency;
  }

  /**
   * @return Returns the itemDateFrom.
   */
  protected Date getItemDateFrom() {
    return itemDateFrom;
  }

  /**
   * @return Returns the itemDateTill.
   */
  protected Date getItemDateTill() {
    return itemDateTill;
  }

  /**
   * @return Returns the itemKindCode.
   */
  protected ItemKind getItemKindCode() {
    return itemKindCode;
  }

  /**
   * @return Returns the itemStatus.
   */
  protected ScheduleStatus getItemStatus() {
    return itemStatus;
  }

  /**
   * @return Returns the resourceFromCode.
   */
  protected PmcResource getResourceFromCode() {
    return resourceFromCode;
  }

  /**
   * @return Returns the resourceToCode.
   */
  protected PmcResource getResourceToCode() {
    return resourceToCode;
  }

  /**
   * @return Returns the sumFrom.
   */
  protected BigDecimal getSumFrom() {
    return sumFrom;
  }

  /**
   * @return Returns the sumTill.
   */
  protected BigDecimal getSumTill() {
    return sumTill;
  }

  /**
   * @return Returns the toCode.
   */
  protected Contractor getToCode() {
    return toCode;
  }

  /**
   * @return the docType
   */
  public DocType getDocType() {
    return docType;
  }

  /**
   * @param docType the docType to set
   */
  public void setDocType(DocType docType) {
    this.docType = docType;
  }

}
