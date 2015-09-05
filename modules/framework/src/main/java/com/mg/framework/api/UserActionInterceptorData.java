/*
 * UserActionInterceptorData.java
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

import com.mg.framework.api.orm.PersistentObject;

/**
 * @author Oleg V. Safonov
 * @version $Id: UserActionInterceptorData.java,v 1.1 2005/04/28 15:33:24 safonov Exp $
 */
public class UserActionInterceptorData {
    public BusinessObjectService service;
    public PersistentObject entity;
    public AttributeMap oldEntity;
    public AttributeMap featureValues;
    public AttributeMap oldFeatureValues;
    
    public UserActionInterceptorData(BusinessObjectService service, PersistentObject entity,
            AttributeMap oldEntity, AttributeMap featureValues, AttributeMap oldFeatureValues) {
        this.service = service;
        this.entity = entity;
        this.oldEntity = oldEntity;
        this.featureValues = featureValues;
        this.oldFeatureValues = oldFeatureValues;
    }
}
