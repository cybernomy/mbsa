/*
 * BPMManager.java
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
package com.mg.merp.bpm;

import org.jbpm.JbpmContext;

/**
 * @author Oleg V. Safonov
 * @version $Id: BPMManager.java,v 1.1 2007/05/28 13:05:48 safonov Exp $
 */
public interface BPMManager {

	JbpmContext getCurrentBpmContext();

}
