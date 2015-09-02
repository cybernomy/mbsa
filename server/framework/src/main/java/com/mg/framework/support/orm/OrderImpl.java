/*
 * OrderImpl.java
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

/**
 * @author Oleg V. Safonov
 * @version $Id: OrderImpl.java,v 1.1 2006/01/24 14:14:25 safonov Exp $
 */
public class OrderImpl {
    private org.hibernate.criterion.Order hibernateOrder;

    public OrderImpl(org.hibernate.criterion.Order hibernateOrder) {
        this.hibernateOrder = hibernateOrder;
    }
    
    public org.hibernate.criterion.Order getHibernateOrder() {
        return hibernateOrder;
    }
}
