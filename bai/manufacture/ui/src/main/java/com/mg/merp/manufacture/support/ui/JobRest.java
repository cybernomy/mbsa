/*
 * JobRest.java
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
package com.mg.merp.manufacture.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm;
import com.mg.merp.manufacture.model.JobStatus;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;

import java.util.Date;

/**
 * Контроллер формы условий отбора ЗНП
 *
 * @author leonova
 * @version $Id: JobRest.java,v 1.3 2006/10/18 12:59:01 leonova Exp $
 */
public class JobRest extends DefaultHierarhyRestrictionForm {

  @DataItemName("Reference.Cond.DateFrom")
  private Date jobDateFrom = null;
  @DataItemName("Reference.Cond.DateTill")
  private Date jobDateTill = null;
  private JobStatus jobStatus = null;
  @DataItemName("Manufacture.Job.JobNumber")
  private String jobNumber = "";
  private Catalog catalogCode = null;
  @DataItemName("Manufacture.Job.DefDstStock")
  private Contractor dstStockCode = null;
  @DataItemName("Manufacture.Job.DefSrcStock")
  private Contractor srcStockCode = null;
  @DataItemName("Manufacture.Job.DefDstMol")
  private Contractor dstMolCode = null;
  @DataItemName("Manufacture.Job.DefSrcMol")
  private Contractor srcMolCode = null;
  @DataItemName("Manufacture.Cond.Job.StartDateFrom")
  private Date startDateFrom = null;
  @DataItemName("Manufacture.Cond.Job.StartDateTill")
  private Date startDateTill = null;
  @DataItemName("Manufacture.Cond.Job.EndDateFrom")
  private Date endDateFrom = null;
  @DataItemName("Manufacture.Cond.Job.EndDateTill")
  private Date endDateTill = null;


  @Override
  protected void doClearRestrictionItem() {
    super.doClearRestrictionItem();
    this.jobDateFrom = null;
    this.jobDateTill = null;
    this.jobStatus = null;
    this.jobNumber = "";
    this.catalogCode = null;
    this.srcMolCode = null;
    this.srcStockCode = null;
    this.dstMolCode = null;
    this.dstStockCode = null;
    this.startDateFrom = null;
    this.startDateTill = null;
    this.endDateFrom = null;
    this.endDateTill = null;
  }


  /**
   * @return Returns the catalogCode.
   */
  protected Catalog getCatalogCode() {
    return catalogCode;
  }


  /**
   * @return Returns the dstMolCode.
   */
  protected Contractor getDstMolCode() {
    return dstMolCode;
  }


  /**
   * @return Returns the dstStockCode.
   */
  protected Contractor getDstStockCode() {
    return dstStockCode;
  }


  /**
   * @return Returns the endDateFrom.
   */
  protected Date getEndDateFrom() {
    return endDateFrom;
  }


  /**
   * @return Returns the endDateTill.
   */
  protected Date getEndDateTill() {
    return endDateTill;
  }


  /**
   * @return Returns the jobDateFrom.
   */
  protected Date getJobDateFrom() {
    return jobDateFrom;
  }


  /**
   * @return Returns the jobDateTill.
   */
  protected Date getJobDateTill() {
    return jobDateTill;
  }


  /**
   * @return Returns the jobNumber.
   */
  protected String getJobNumber() {
    return jobNumber;
  }


  /**
   * @return Returns the jobStatus.
   */
  protected JobStatus getJobStatus() {
    return jobStatus;
  }


  /**
   * @return Returns the srcMolCode.
   */
  protected Contractor getSrcMolCode() {
    return srcMolCode;
  }


  /**
   * @return Returns the srcStockCode.
   */
  protected Contractor getSrcStockCode() {
    return srcStockCode;
  }


  /**
   * @return Returns the startDateFrom.
   */
  protected Date getStartDateFrom() {
    return startDateFrom;
  }


  /**
   * @return Returns the startDateTill.
   */
  protected Date getStartDateTill() {
    return startDateTill;
  }


}
