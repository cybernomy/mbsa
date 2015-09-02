/*
 * GenericProjectionImpl.java
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
package com.mg.framework.support.orm;

import org.hibernate.criterion.Projections;

import com.mg.framework.api.orm.Projection;

/**
 * @author Oleg V. Safonov
 * @version $Id: GenericProjectionImpl.java,v 1.2 2006/12/12 13:30:30 safonov Exp $
 */
public class GenericProjectionImpl implements Projection {
	private org.hibernate.criterion.Projection hibernateProjection;

	public GenericProjectionImpl(org.hibernate.criterion.Projection hibernateProjection) {
		this.hibernateProjection = hibernateProjection;
	}

	public org.hibernate.criterion.Projection getHibernateProjection() {
		return hibernateProjection;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.Projection#as(java.lang.String)
	 */
	public Projection as(String alias) {
		return new GenericProjectionImpl(Projections.alias(this.hibernateProjection, alias));
	}

}
