/*
 * PayRollRest.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.salary.support.ui;


import com.mg.framework.api.annotations.DataItemName;
import com.mg.merp.document.model.DocType;
import com.mg.merp.personnelref.support.ui.ItemWithDateIntervalRest;
import com.mg.merp.salary.model.PayRoll;
import com.mg.merp.salary.model.RollKind;

import java.util.Date;

/**
 * Контроллер формы условий отбора платежных ведомостей
 *
 * @author leonova
 * @version $Id: PaySheetRest.java,v 1.4 2006/08/28 12:44:17 leonova Exp $
 */
public class PaySheetRest extends ItemWithDateIntervalRest {

  @DataItemName("Salary.PaySheet.PayRoll")
  private PayRoll payRollNumber = null;
  private RollKind rollKindName = null;
  @DataItemName("Salary.PaySheet.SNumber")
  private String sNumber = "";
  @DataItemName("Salary.Cond.PaySheet.DocType")
  private DocType docType = null;
  @DataItemName("Salary.Cond.PaySheet.DocNumber")
  private String docNumber = "";
  @DataItemName("Salary.Cond.PaySheet.DocDateFrom")
  private Date docDateFrom = null;
  @DataItemName("Salary.Cond.PaySheet.DocDateTill")
  private Date docDateTill = null;


  @Override
  protected void doClearRestrictionItem() {
    super.doClearRestrictionItem();
    this.payRollNumber = null;
    this.rollKindName = null;
    this.sNumber = "";
    this.docType = null;
    this.docNumber = "";
    this.docDateFrom = null;
    this.docDateTill = null;
  }


  /**
   * @return Returns the docDateFrom.
   */
  protected Date getDocDateFrom() {
    return docDateFrom;
  }


  /**
   * @return Returns the docDateTill.
   */
  protected Date getDocDateTill() {
    return docDateTill;
  }


  /**
   * @return Returns the docNumber.
   */
  protected String getDocNumber() {
    return docNumber;
  }


  /**
   * @return Returns the docType.
   */
  protected DocType getDocType() {
    return docType;
  }


  /**
   * @return Returns the payRollNumber.
   */
  protected PayRoll getPayRollNumber() {
    return payRollNumber;
  }


  /**
   * @return Returns the rollKindName.
   */
  protected RollKind getRollKindName() {
    return rollKindName;
  }


  /**
   * @return Returns the sNumber.
   */
  protected String getSNumber() {
    return sNumber;
  }


}
