/*
 * InputHeadRest.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.manufacture.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.merp.document.model.DocType;
import com.mg.merp.manufacture.model.Job;
import com.mg.merp.manufacture.model.JobRoute;
import com.mg.merp.mfreference.model.Crew;
import com.mg.merp.mfreference.model.WorkCenter;
import com.mg.merp.reference.model.Contractor;

import java.util.Date;

/**
 * Контроллер формы условий отбора актов выпуска готовой продукции
 *
 * @author leonova
 * @version $Id: InputHeadRest.java,v 1.3 2007/07/31 06:31:06 safonov Exp $
 */
public class InputHeadRest extends ManufactureDocumentRest {
  protected Crew crew = null;
  @DataItemName("Manufacture.InputDocHead.Oper")
  protected JobRoute oper = null;
  protected WorkCenter workCenter = null;
  protected Job Job = null; //названо по JavaBean NC, чтобы работал SearchHelp
  @DataItemName("Manufacture.InputDocHead.Contractor")
  protected Contractor contractor = null;
  @DataItemName("Manufacture.InputDocHead.Employee")
  protected Contractor employee = null;
  @DataItemName("Manufacture.Cond.OutputDocNumber")
  private String outputDocNumber = "";
  @DataItemName("Manufacture.Cond.OutputDocType")
  private DocType outputDocType = null;
  @DataItemName("Manufacture.Cond.OutputDocDateFrom")
  private Date outputDocDateFrom = null;
  @DataItemName("Manufacture.Cond.OutputDocDateTill")
  private Date outputDocDateTill = null;

  @Override
  protected void doClearRestrictionItem() {
    super.doClearRestrictionItem();
    this.crew = null;
    this.oper = null;
    this.workCenter = null;
    this.Job = null;
    this.contractor = null;
    this.employee = null;
    this.outputDocDateFrom = null;
    this.outputDocDateTill = null;
    this.outputDocNumber = "";
    this.outputDocType = null;
  }

  /**
   * @return Returns the outputDocDateFrom.
   */
  protected Date getOutputDocDateFrom() {
    return outputDocDateFrom;
  }

  /**
   * @return Returns the outputDocDateTill.
   */
  protected Date getOutputDocDateTill() {
    return outputDocDateTill;
  }

  /**
   * @return Returns the outputDocNumber.
   */
  protected String getOutputDocNumber() {
    return outputDocNumber;
  }

  /**
   * @return Returns the outputDocType.
   */
  protected DocType getOutputDocType() {
    return outputDocType;
  }

  /**
   * @return the contractor
   */
  public Contractor getContractor() {
    return contractor;
  }

  /**
   * @return the crew
   */
  public Crew getCrew() {
    return crew;
  }

  /**
   * @return the employee
   */
  public Contractor getEmployee() {
    return employee;
  }

  /**
   * @return the job
   */
  public Job getJob() {
    return Job;
  }

  /**
   * @return the oper
   */
  public JobRoute getOper() {
    return oper;
  }

  /**
   * @return the workCenter
   */
  public WorkCenter getWorkCenter() {
    return workCenter;
  }

}