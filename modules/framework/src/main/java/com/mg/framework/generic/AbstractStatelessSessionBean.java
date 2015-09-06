/*
 * AbstractStatelessSessionBean.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.framework.generic;


/**
 * @author Oleg V. Safonov
 * @version $Id: AbstractStatelessSessionBean.java,v 1.4 2006/03/01 15:26:36 safonov Exp $
 */
public abstract class AbstractStatelessSessionBean extends AbstractSessionBean {

  /**
   * This is declared abstract to ensure that subclasses implement this.
   * Otherwise it isn't required by the compiler, but will
   * fail on deployment.
   * <br/>The BeanFactory is available at this point
   */
    /*public void ejbCreate() throws CreateException {

	}*/

	/* (non-Javadoc)
	 * @see javax.ejb.SessionBean#ejbActivate()
	 */
	/*public void ejbActivate() throws EJBException, RemoteException {
		throw new IllegalStateException("ejbActivate must not be invoked on a stateless session bean"); //$NON-NLS-1$
	}*/

	/* (non-Javadoc)
	 * @see javax.ejb.SessionBean#ejbPassivate()
	 */
	/*public void ejbPassivate() throws EJBException, RemoteException {
		throw new IllegalStateException("ejbPassivate must not be invoked on a stateless session bean"); //$NON-NLS-1$
	}*/

}
