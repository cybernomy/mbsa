/*
 * MetadataCacheService.java
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
package com.mg.framework.service.jboss;

import org.jboss.system.ServiceMBeanSupport;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.metadata.MetadataCache;
import com.mg.framework.service.MetadataCacheImpl;

/**
 * @author Oleg V. Safonov
 *
 */
public class MetadataCacheService
	extends ServiceMBeanSupport
	implements MetadataCacheServiceMBean {
    
    private MetadataCache delegate;
    
    public MetadataCacheService() {
        delegate = new MetadataCacheImpl();
    }
    
	protected void createService() throws Exception {
	    ((MetadataCacheImpl) delegate).create();
	    ((MetadataCacheImpl) delegate).start();
	}

	protected void destroyService() throws Exception {
	    ((MetadataCacheImpl) delegate).stop();
	    ((MetadataCacheImpl) delegate).destroy();
	}

    public void invalidate(int classId) throws ApplicationException {
        delegate.invalidate(classId);
    }
    
    public boolean isInvalidated(int classId, long timeStamp) throws ApplicationException {
        return delegate.isInvalidated(classId, timeStamp);
    }

}
