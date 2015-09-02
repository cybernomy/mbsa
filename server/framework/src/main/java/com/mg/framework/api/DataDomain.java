/*
 * DataDomain.java
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

import java.util.Collection;

import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.PersistentObject;


/**
 * @author Oleg V. Safonov
 * @version $Id: DataDomain.java,v 1.6 2008/07/21 15:27:35 safonov Exp $
 * 
 */
@Deprecated
public interface DataDomain extends Domain {
    // persistence methods
    public PersistentObject initialize() throws ApplicationException;
    public Object create(PersistentObject entity) throws ApplicationException;
    public void store(PersistentObject entity) throws ApplicationException;
    public void remove(Object key) throws ApplicationException;
    public PersistentObject load(Object key) throws ApplicationException;
    
    // helper methods for interaction with UI
    public PersistentObject make(AttributeMap attributes) throws ApplicationException;
    //public AttributeMap initialize(AttributeMap attributes) throws ApplicationException;
    public PersistentObject initialize(AttributeMap attributes) throws ApplicationException;
    public Object create(AttributeMap attributes) throws ApplicationException;
    public void store(AttributeMap attributes) throws ApplicationException;
    public Object loadBrowse(BrowseCond cond) throws ApplicationException;
    public AttributeMap loadView(Object primaryKey, Collection<?> keyOfAttributes, String viewName) throws ApplicationException;
    public Object clone(Object key, boolean cloneDetail) throws ApplicationException;
    public void changeParent(Object key, Object newParent) throws ApplicationException;
    
    //
    public PersistentManager getPersistentManager();
}
