/*
 * GraphElement.java
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
package com.mg.framework.support.ui.widget.graph;

/**
 * @author Oleg V. Safonov
 * @version $Id: GraphElement.java,v 1.1 2006/03/27 10:43:34 safonov Exp $
 */
public class GraphElement {
	private Object port;

	/**
	 * @return Returns the port.
	 */
	public Object getPort() {
		return port;
	}

	/**
	 * @param port The port to set.
	 */
	public void setPort(Object port) {
		this.port = port;
	}

}
