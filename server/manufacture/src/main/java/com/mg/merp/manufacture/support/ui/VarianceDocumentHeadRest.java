/*
 * VarianceDocumentHeadRest.java
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

import com.mg.merp.manufacture.model.Job;
import com.mg.merp.mfreference.model.WorkCenter;

/**
 * Контроллер формы условий отбора документов о отклонениям
 *
 * @author leonova
 * @version $Id: VarianceDocumentHeadRest.java,v 1.3 2007/07/31 06:31:06 safonov Exp $
 */
public class VarianceDocumentHeadRest extends ManufactureDocumentRest {

  private WorkCenter workCenter = null;
  private Job job = null;

  @Override
  protected void doClearRestrictionItem() {
    super.doClearRestrictionItem();
    this.workCenter = null;
    this.job = null;
  }

  /**
   * @return Returns the job.
   */
  protected Job getJob() {
    return job;
  }

  /**
   * @return Returns the workCenter.
   */
  protected WorkCenter getWorkCenter() {
    return workCenter;
  }

}
