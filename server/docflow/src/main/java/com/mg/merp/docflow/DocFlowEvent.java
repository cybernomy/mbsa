/*
 * DocFlowEvent.java
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
package com.mg.merp.docflow;

import java.util.EventObject;

/**
 * @author Oleg V. Safonov
 * @version $Id: DocFlowEvent.java,v 1.2 2006/04/24 07:38:07 safonov Exp $
 */
public class DocFlowEvent extends EventObject {

	public DocFlowEvent(DocFlowManager source) {
		super(source);
	}
	
	public DocFlowManager getDocFlowManager() {
		return (DocFlowManager) getSource();
	}
}
