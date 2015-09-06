/*
 * PlanningLevelBucketGenerationDlg.java
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
package com.mg.merp.mfreference.support.ui;

import com.mg.framework.generic.ui.DefaultDialog;

/**
 * Контроллер диалога генерации периодов уровня планирования
 *
 * @author Artem V. Sharapov
 * @version $Id: PlanningLevelBucketGenerationDlg.java,v 1.1 2007/02/19 12:55:14 sharapov Exp $
 */
public class PlanningLevelBucketGenerationDlg extends DefaultDialog {

  // Fields

  private java.lang.Integer bucketLength; // длина периода в днях
  private java.lang.Integer bucketNumber; // количество периодов

  // Property accessors

  /**
   * @return the bucketLength
   */
  public java.lang.Integer getBucketLength() {
    return bucketLength;
  }

  /**
   * @param bucketLength the bucketLength to set
   */
  public void setBucketLength(java.lang.Integer bucketLength) {
    this.bucketLength = bucketLength;
  }

  /**
   * @return the bucketNumber
   */
  public java.lang.Integer getBucketNumber() {
    return bucketNumber;
  }

  /**
   * @param bucketNumber the bucketNumber to set
   */
  public void setBucketNumber(java.lang.Integer bucketNumber) {
    this.bucketNumber = bucketNumber;
  }

}
