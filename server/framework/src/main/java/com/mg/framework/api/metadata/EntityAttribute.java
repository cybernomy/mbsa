/*
 * EntityAttribute.java
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
package com.mg.framework.api.metadata;

import com.mg.framework.api.ApplicationException;

/**
 * @author Oleg V. Safonov
 * @version $Id: EntityAttribute.java,v 1.1 2006/01/24 13:45:28 safonov Exp $
 */
public interface EntityAttribute {
    public String getName() throws ApplicationException;
    public String getDescription() throws ApplicationException;
    public DataItem getDataItem() throws ApplicationException;
}
