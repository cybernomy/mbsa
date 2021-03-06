/*
 * FeeSummaryModelServiceLocal.java
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
package com.mg.merp.salary;

import com.mg.merp.document.DocumentPattern;
import com.mg.merp.document.model.DocHeadModel;

/**
 * @author leonova
 * @version $Id: FeeSummaryModelServiceLocal.java,v 1.3 2006/09/20 10:59:59 safonov Exp $
 */
public interface FeeSummaryModelServiceLocal
    extends DocumentPattern<DocHeadModel, Integer> {
  /**
   * тип папки для образцов сводов н/у
   */
  final static short FOLDER_PART = 10502;
}
