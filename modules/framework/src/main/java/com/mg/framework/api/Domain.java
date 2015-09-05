/*
 * Domain.java
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
package com.mg.framework.api;

import java.io.Serializable;

/**
 * @author Oleg V. Safonov
 * @version $Id: Domain.java,v 1.5 2006/09/28 12:24:12 safonov Exp $
 *
 */
@Deprecated
public interface Domain extends Serializable {
    public String getDeploymentDescriptorName() throws ApplicationException;
	//public BeanMetadata loadMetadata() throws ApplicationException;
	public String translateDataAccessException(ApplicationException e) throws ApplicationException;
	public void reloadMetadata() throws ApplicationException;
	public void destroy() throws Throwable;
}
