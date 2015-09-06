/*
 * StaffListPositionRest.java
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
package com.mg.merp.personnelref.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm;
import com.mg.merp.personnelref.model.CostsAnl;
import com.mg.merp.personnelref.model.PrefPosition;
import com.mg.merp.personnelref.model.StaffCategory;
import com.mg.merp.personnelref.model.TariffingCategory;
import com.mg.merp.personnelref.model.TaxCalcKind;
import com.mg.merp.personnelref.model.WorkCondition;
import com.mg.merp.personnelref.model.WorkSchedule;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author leonova
 * @version $Id: StaffListPositionRest.java,v 1.4 2006/10/23 08:14:29 leonova Exp $
 */
public class StaffListPositionRest extends DefaultHierarhyRestrictionForm {

  @DataItemName("PersonnelRef.Cond.BeginDate")
  private Date beginDate = null;
  @DataItemName("PersonnelRef.Cond.EndDate")
  private Date endDate = null;
  private PrefPosition positionName = null;
  private StaffCategory staffCategoryCode = null;
  private WorkCondition workConditionCode = null;
  private WorkSchedule workScheduleCode = null;
  private TariffingCategory tariffingCategoryCode = null;
  private TaxCalcKind taxCalcKindCode = null;
  @DataItemName("Salary.FeeRef.CostsAnl")
  private CostsAnl anlCode1 = null;
  @DataItemName("Salary.FeeRef.CostsAnl")
  private CostsAnl anlCode2 = null;
  @DataItemName("Salary.FeeRef.CostsAnl")
  private CostsAnl anlCode3 = null;
  @DataItemName("Salary.FeeRef.CostsAnl")
  private CostsAnl anlCode4 = null;
  @DataItemName("Salary.FeeRef.CostsAnl")
  private CostsAnl anlCode5 = null;
  @DataItemName("Salary.Cond.Staff.BeginDateTC")
  private Date beginDateTC = null;
  @DataItemName("Salary.Cond.Staff.EndDateTC")
  private Date endDateTC = null;
  @DataItemName("Salary.Cond.Staff.MinSalaryFrom")
  private BigDecimal minSalaryFrom = null;
  @DataItemName("Salary.Cond.Staff.MinSalaryTill")
  private BigDecimal minSalaryTill = null;
  @DataItemName("Salary.Cond.Staff.FldName")
  private String fldName = "";
  @DataItemName("Salary.Cond.Staff.UCode")
  private String uCode = "";
  @DataItemName("Salary.Cond.Staff.WorkScheduleP")
  private WorkSchedule workScheduleCodeP = null;
  @DataItemName("Salary.Cond.Staff.TaxCalcKindP")
  private TaxCalcKind taxCalcKindCodeP = null;
  @DataItemName("Salary.FeeRef.CostsAnl")
  private CostsAnl anlCode1P = null;
  @DataItemName("Salary.FeeRef.CostsAnl")
  private CostsAnl anlCode2P = null;
  @DataItemName("Salary.FeeRef.CostsAnl")
  private CostsAnl anlCode3P = null;
  @DataItemName("Salary.FeeRef.CostsAnl")
  private CostsAnl anlCode4P = null;
  @DataItemName("Salary.FeeRef.CostsAnl")
  private CostsAnl anlCode5P = null;


  @Override
  protected void doClearRestrictionItem() {
    super.doClearRestrictionItem();
    this.beginDate = null;
    this.endDate = null;
    this.positionName = null;
    this.staffCategoryCode = null;
    this.workConditionCode = null;
    this.workScheduleCode = null;
    this.tariffingCategoryCode = null;
    this.taxCalcKindCode = null;
    this.anlCode1 = null;
    this.anlCode2 = null;
    this.anlCode3 = null;
    this.anlCode4 = null;
    this.anlCode5 = null;
    this.beginDateTC = null;
    this.endDateTC = null;
    this.minSalaryFrom = null;
    this.minSalaryTill = null;
    this.fldName = "";
    this.uCode = "";
    this.workScheduleCodeP = null;
    this.taxCalcKindCodeP = null;
    this.anlCode1P = null;
    this.anlCode2P = null;
    this.anlCode3P = null;
    this.anlCode4P = null;
    this.anlCode5P = null;
  }


  /**
   * @return Returns the anlCode1.
   */
  protected CostsAnl getAnlCode1() {
    return anlCode1;
  }


  /**
   * @return Returns the anlCode1P.
   */
  protected CostsAnl getAnlCode1P() {
    return anlCode1P;
  }


  /**
   * @return Returns the anlCode2.
   */
  protected CostsAnl getAnlCode2() {
    return anlCode2;
  }


  /**
   * @return Returns the anlCode2P.
   */
  protected CostsAnl getAnlCode2P() {
    return anlCode2P;
  }


  /**
   * @return Returns the anlCode3.
   */
  protected CostsAnl getAnlCode3() {
    return anlCode3;
  }


  /**
   * @return Returns the anlCode3P.
   */
  protected CostsAnl getAnlCode3P() {
    return anlCode3P;
  }


  /**
   * @return Returns the anlCode4.
   */
  protected CostsAnl getAnlCode4() {
    return anlCode4;
  }


  /**
   * @return Returns the anlCode4P.
   */
  protected CostsAnl getAnlCode4P() {
    return anlCode4P;
  }


  /**
   * @return Returns the anlCode5.
   */
  protected CostsAnl getAnlCode5() {
    return anlCode5;
  }


  /**
   * @return Returns the anlCode5P.
   */
  protected CostsAnl getAnlCode5P() {
    return anlCode5P;
  }


  /**
   * @return Returns the beginDate.
   */
  protected Date getBeginDate() {
    return beginDate;
  }


  /**
   * @return Returns the beginDateTC.
   */
  protected Date getBeginDateTC() {
    return beginDateTC;
  }


  /**
   * @return Returns the endDate.
   */
  protected Date getEndDate() {
    return endDate;
  }


  /**
   * @return Returns the endDateTC.
   */
  protected Date getEndDateTC() {
    return endDateTC;
  }


  /**
   * @return Returns the fldName.
   */
  protected String getFldName() {
    return fldName;
  }


  /**
   * @return Returns the minSalaryFrom.
   */
  protected BigDecimal getMinSalaryFrom() {
    return minSalaryFrom;
  }


  /**
   * @return Returns the minSalaryTill.
   */
  protected BigDecimal getMinSalaryTill() {
    return minSalaryTill;
  }


  /**
   * @return Returns the positionName.
   */
  protected PrefPosition getPositionName() {
    return positionName;
  }


  /**
   * @return Returns the staffCategoryCode.
   */
  protected StaffCategory getStaffCategoryCode() {
    return staffCategoryCode;
  }


  /**
   * @return Returns the tariffingCategoryCode.
   */
  protected TariffingCategory getTariffingCategoryCode() {
    return tariffingCategoryCode;
  }


  /**
   * @return Returns the taxCalcKindCode.
   */
  protected TaxCalcKind getTaxCalcKindCode() {
    return taxCalcKindCode;
  }


  /**
   * @return Returns the taxCalcKindCodeP.
   */
  protected TaxCalcKind getTaxCalcKindCodeP() {
    return taxCalcKindCodeP;
  }


  /**
   * @return Returns the uCode.
   */
  protected String getUCode() {
    return uCode;
  }


  /**
   * @return Returns the workConditionCode.
   */
  protected WorkCondition getWorkConditionCode() {
    return workConditionCode;
  }


  /**
   * @return Returns the workScheduleCode.
   */
  protected WorkSchedule getWorkScheduleCode() {
    return workScheduleCode;
  }


  /**
   * @return Returns the workScheduleCodeP.
   */
  protected WorkSchedule getWorkScheduleCodeP() {
    return workScheduleCodeP;
  }


}
