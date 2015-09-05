/*
 * Edge.java
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
 * @version $Id: Edge.java,v 1.1 2006/03/27 10:43:34 safonov Exp $
 */
public class Edge extends GraphElement {
	private String label;
	private Vertex source;
	private Vertex target;

	public Edge(Vertex source, Vertex target) {
		this.source = source;
		this.target = target;
	}
	
	/**
	 * @return Returns the label.
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label The label to set.
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return Returns the source.
	 */
	public Vertex getSource() {
		return source;
	}

	/**
	 * @param source The source to set.
	 */
	public void setSource(Vertex source) {
		this.source = source;
	}

	/**
	 * @return Returns the target.
	 */
	public Vertex getTarget() {
		return target;
	}

	/**
	 * @param target The target to set.
	 */
	public void setTarget(Vertex target) {
		this.target = target;
	}

}
