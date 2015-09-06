/*
 * OutputProductHeadRest.java
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
import com.mg.merp.manufacture.model.Job;
import com.mg.merp.manufacture.model.JobRoute;
import com.mg.merp.mfreference.model.Crew;
import com.mg.merp.mfreference.model.WorkCenter;
import com.mg.merp.reference.model.Contractor;

/**
 * Контроллер формы условий отбора акта выпуска готовой продукции
 *
 * @author leonova
 * @version $Id: OutputProductHeadRest.java,v 1.3 2007/07/31 06:31:06 safonov Exp $
 */
public class OutputProductHeadRest extends ManufactureDocumentRest {
  protected Crew crew = null;
  @DataItemName("Manufacture.OutputProductHead.Oper")
  protected JobRoute oper = null;
  protected WorkCenter workCenter = null;
  protected Job Job = null; //названо по JavaBean NC, чтобы работал SearchHelp
  @DataItemName("Manufacture.OutputProductHead.Contractor")
  protected Contractor contractor = null;
  @DataItemName("Manufacture.OutputProductHead.Employee")
  protected Contractor employee = null;

  @Override
  protected void doClearRestrictionItem() {
    super.doClearRestrictionItem();
    this.crew = null;
    this.oper = null;
    this.workCenter = null;
    this.Job = null;
    this.contractor = null;
    this.employee = null;
  }

  /**
   * @return Returns the contractor.
   */
  public Contractor getContractor() {
    return contractor;
  }

  /**
   * @return Returns the crew.
   */
  public Crew getCrew() {
    return crew;
  }

  /**
   * @return Returns the employee.
   */
  public Contractor getEmployee() {
    return employee;
  }

  /**
   * @return Returns the job.
   */
  public Job getJob() {
    return Job;
  }

  /**
   * @return Returns the oper.
   */
  public JobRoute getOper() {
    return oper;
  }

  /**
   * @return Returns the workCenter.
   */
  public WorkCenter getWorkCenter() {
    return workCenter;
  }


}
