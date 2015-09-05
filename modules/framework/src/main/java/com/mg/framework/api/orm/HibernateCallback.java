/*
 * HibernateCallback.java
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
package com.mg.framework.api.orm;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * Callback interface for Hibernate code. To be used with HibernateTemplate's execute
 * method, assumably often as anonymous classes within a method implementation.
 * The typical implementation will call Session.load/find/save/update to perform
 * some operations on persistent objects. It can also perform direct JDBC operations
 * via Hibernate's Session.connection() method, returning the active JDBC connection.
 *
 * <p>Note that Hibernate works on unmodified plain Java objects, performing dirty
 * detection via copies made at load time. Returned objects can thus be used outside
 * of an active Hibernate Session without any hassle, e.g. for display in a web GUI.
 * Reassociating such instances with a new Session, e.g. for updates when coming
 * back from the GUI, is straightforward, as the instance has kept its identity.
 * You should care to reassociate them as early as possible though, to avoid having
 * already loaded a version from the database in the same Session.
 *
 * @author Juergen Hoeller
 * @author Oleg V. Safonov
 * @version $Id: HibernateCallback.java,v 1.2 2006/12/12 13:54:52 safonov Exp $
 * 
 */
public interface HibernateCallback<T> {

    /**
     * Gets called by <code>HibernateTemplate.execute</code> with an active
     * Hibernate Session. Does not need to care about activating or closing
     * the Session, or handling transactions.
     *
     * <p>If called without a thread-bound Hibernate transaction (initiated
     * by HibernateTransactionManager), the code will simply get executed on the
     * underlying JDBC connection with its transactional semantics. If Hibernate
     * is configured to use a JTA-aware DataSource, the JDBC connection and thus
     * the callback code will be transactional if a JTA transaction is active.
     *
     * <p>Allows for returning a result object created within the callback, i.e.
     * a domain object or a collection of domain objects. Note that there's
     * special support for single step actions: see HibernateTemplate.find etc.
     * A thrown RuntimeException is treated as application exception, it gets
     * propagated to the caller of the template.
     *
     * @param session active Hibernate session
     * @return a result object, or null if none
     * @throws HibernateException in case of Hibernate errors
     * @throws SQLException in case of errors on direct JDBC access
     * @see HibernateTemplate#execute
     * @see HibernateTransactionManager
     */
    T doInHibernate(Session session) throws HibernateException, SQLException;

}
