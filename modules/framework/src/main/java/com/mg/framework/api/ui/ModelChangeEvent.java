/*
 * ModelChangeEvent.java
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
package com.mg.framework.api.ui;

import java.io.Serializable;

import com.mg.framework.api.orm.PersistentObject;

/**
 * @author Oleg V. Safonov
 * @version $Id: ModelChangeEvent.java,v 1.2 2006/04/24 08:41:33 safonov Exp $
 */
public class ModelChangeEvent extends UIEvent {
	private Serializable modelKey;
	private PersistentObject entity;

	public ModelChangeEvent(Object source, Serializable modelKey) {
		super(source);
		this.modelKey = modelKey;
	}
	
	public ModelChangeEvent(Object source, PersistentObject entity) {
		super(source);
		if (entity == null)
			throw new IllegalArgumentException("Entity cann't be null");
		this.entity = entity;
		this.modelKey = (Serializable) entity.getPrimaryKey();
	}
	
	public Serializable getModelKey() {
		return modelKey;
	}

	public PersistentObject getEntity() {
		if (entity == null)
			throw new IllegalStateException("Entity is null");
		return entity;
	}

}
