/*
 * Messages.java
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
package com.mg.merp.bpm.support;

import com.mg.framework.generic.MessageSourceAccessor;

/**
 * @author Oleg V. Safonov
 * @version $Id: Messages.java,v 1.2 2007/09/10 15:09:53 alikaev Exp $
 */
public class Messages extends MessageSourceAccessor {
  //message keys
  public static final String PROCESS_IMAGE_TITLE = "ProcessImageTitle"; //$NON-NLS-1$
  public static final String PROCESS_INSTANCES_TITLE = "ProcessInstancesTitle"; //$NON-NLS-1$
  public static final String PROCESS_LOGS_TITLE = "ProcessLogsTitle"; //$NON-NLS-1$
  public static final String PROCESS_VERSIONS_TITLE = "ProcessVersionsTitle"; //$NON-NLS-1$
  public static final String PROCESS_NOT_FOUND = "ProcessNotFound"; //$NON-NLS-1$
  private static final String BUNDLE_NAME = "com.mg.merp.bpm.resources.messages"; //$NON-NLS-1$
  private static Messages instance;

  static {
    MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  public static Messages getInstance() {
    return instance;
  }


}
