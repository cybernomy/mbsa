/*
 * GroupItem.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.merp.document;

import java.io.Serializable;

/**
 * @author Oleg V. Safonov
 *
 */
public class GroupItem implements Serializable {
	public int key;
	public String name;

	public GroupItem() {
	}

	public GroupItem(int key, String name) {
		this.key = key;
		this.name = name;
	}
}
