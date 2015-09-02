/*
 * AbstractBusinessObjectStatefulServiceBean.java
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
package com.mg.framework.generic;

import javax.annotation.security.PermitAll;
import javax.ejb.EJBException;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.naming.NamingException;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.BusinessObjectService;
import com.mg.framework.api.Domain;
import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.metadata.BusinessServiceMetadata;
import com.mg.framework.utils.BusinessObjectUtils;
import com.mg.framework.utils.ServerUtils;

/**
 * @author Oleg V. Safonov
 * @version $Id: AbstractBusinessObjectStatefulServiceBean.java,v 1.10 2006/09/28 12:24:12 safonov Exp $
 * 
 */
@Deprecated
public abstract class AbstractBusinessObjectStatefulServiceBean 
	extends AbstractStatefulSessionBean implements BusinessObjectService {

	private static final String DOMAIN_CLASS_NAME="java:/comp/env/DomainClassName"; //$NON-NLS-1$
	private static final String PERSISTENT_NAME="java:/comp/env/PersistentName"; //$NON-NLS-1$
	private String persistentName = null; 
	protected Domain domain = null;

	protected String getPersistentName() {
		try {
			if (persistentName == null)
				persistentName = (String) getInitialContext().lookup(PERSISTENT_NAME);
			return persistentName;
		}
		catch (Exception e) {
			throw new EJBException(e);
		}
	}

	private String getDomainName() throws NamingException {
		try {
			return (String) getInitialContext().lookup(DOMAIN_CLASS_NAME);
		}
		catch (NamingException ne) {
			//FIXME JBoss PFD specific
			return (String) getInitialContext().lookup("java:/comp.ejb3/env/DomainClassName");
		}
	}

	private void destroyDomain() throws EJBException {
		if (domain != null)
			try {
				domain.destroy();
				domain = null;
			}
			catch (Exception e) {
				throw new EJBException(e);
			}
			catch (Throwable th) {
				throw new EJBException(th.getLocalizedMessage());
			}
	}

	protected Domain createDomain() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NamingException  {
		return (Domain) ServerUtils.loadClass(getDomainName()).newInstance();
	}

	protected Domain getDomain() {
		try {
			if (domain == null)
				domain = createDomain();
			return domain;
		}
		catch (Exception e) {
			throw new EJBException(e);
		}
	}
	
	protected JdbcTemplate getJdbcTemplate() {
	    return JdbcTemplate.getInstance();
	}
	
	@PermitAll
    public String getDeploymentDescriptorName() throws ApplicationException {
        return getDomain().getDeploymentDescriptorName();
    }
    
//	@PermitAll
//	public BeanMetadata loadMetadata() throws ApplicationException {
//		return getDomain().loadMetadata();
//	}

	public String translateDataAccessException(ApplicationException e) throws ApplicationException {
		return getDomain().translateDataAccessException(e);
	}

	protected void doPassivate() throws EJBException {
		super.doPassivate();
		destroyDomain();
	}

	protected void doDestroy() {
		super.doDestroy();
		destroyDomain();
	}

	@AroundInvoke
	public Object securityCheckIntercept(InvocationContext ctx) throws Exception {
		return BusinessObjectUtils.securityCheckInterceptor(ctx, getLogger(), getBusinessServiceMetadata());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.BusinessObjectService#loadBusinessServiceMetadata()
	 */
	@PermitAll
	public BusinessServiceMetadata getBusinessServiceMetadata() {
		return BusinessObjectUtils.loadBusinessServiceMetadata(getSessionContext());
	}

}
