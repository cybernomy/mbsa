/*
 * Entity.java
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

import java.util.List;

import com.mg.framework.api.ApplicationException;

/**
 * @author Oleg V. Safonov
 * @version $Id: Entity.java,v 1.1 2006/01/24 13:45:28 safonov Exp $
 */
public interface Entity {
    public String getName() throws ApplicationException;
    public String getDescription() throws ApplicationException;
    public String getPersistentName() throws ApplicationException;
    public Entity getSuperEntity() throws ApplicationException;
    public EntityAttribute getAttribute(String name) throws ApplicationException;
    public List<EntityAttribute> getAttributes(List<String> names) throws ApplicationException;
    public List<EntityAttribute> getAllAttributes() throws ApplicationException;
}
