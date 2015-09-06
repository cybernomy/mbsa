/* Messages.java
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
package com.mg.merp.report.support;

import com.mg.framework.generic.MessageSourceAccessor;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: Messages.java,v 1.4 2007/04/11 06:45:31 poroxnenko Exp $
 */
public class Messages extends MessageSourceAccessor {
  public static final String ENGINE_HOME_ERROR = "Birt.Engine.Home.Error";
  public static final String OUTPUT_FORMAT_ERROR = "Birt.Engine.OutputFormat.Error";
  public static final String REPORT_TEMPLATE_NULL = "Report.Template.Null";
  public static final String REPORT_FILE_ERROR = "Report.File.Error";
  public static final String DATASET_META_ERROR = "Report.Template.Dataset.Meta.Error";
  public static final String ALG_ERROR = "Report.Template.Dataset.Algorithm.Error";
  public static final String BIRT_ERROR = "Birt.Engine.Error";
  public static final String REPORT_FORM_TITLE = "Report.Form.Title";
  public static final String REPORT_CREATE_ERROR = "report.create.error";
  public static final String REPORT_WAS_CHANGED = "report.changed";
  public static final String REPORT_MISSING = "report.missing";
  private static final String BUNDLE_NAME = "com.mg.merp.report.resources.messages"; //$NON-NLS-1$
  private static Messages instance;

  static {
    MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  public static Messages getInstance() {
    return instance;
  }
}
