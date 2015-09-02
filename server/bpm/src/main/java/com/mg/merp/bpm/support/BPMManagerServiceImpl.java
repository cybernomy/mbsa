/*
 * BPMManagerServiceImpl.java
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

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;

import com.mg.merp.bpm.BPMManager;

/**
 * @author Oleg V. Safonov
 * @version $Id: BPMManagerServiceImpl.java,v 1.2 2007/10/03 11:08:17 safonov Exp $
 */
public class BPMManagerServiceImpl implements BPMManager {
	private JbpmConfiguration jbpmConfiguration;

	public void startService() throws Exception {
		jbpmConfiguration = JbpmConfiguration.parseResource("META-INF/jbpm.cfg.xml");
	}

	public void stopService() throws Exception {
		jbpmConfiguration = null;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.bpm.BPMManager#getCurrentBpmContext()
	 */
	public JbpmContext getCurrentBpmContext() {
		return jbpmConfiguration.createJbpmContext("merp.jbpm-context");
	}

}
