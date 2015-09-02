/*
 * PersistentManagerEJBImpl.java
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
package com.mg.framework.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJBLocalHome;
import javax.ejb.EJBLocalObject;
import javax.ejb.RemoveException;
import javax.naming.Context;
import javax.naming.NamingException;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.utils.ServerUtils;

/**
 * @author Oleg V. Safonov
 * @version $Id: PersistentManagerEJBImpl.java,v 1.7 2007/11/07 12:59:30 safonov Exp $
 *
 */
@SuppressWarnings("unchecked")
public class PersistentManagerEJBImpl implements PersistentManager {
	//private static Map homeMap = Collections.synchronizedMap(new HashMap());
	private static Map<String, BeanInfoValue> beanInfoMap = Collections.synchronizedMap(new HashMap<String, BeanInfoValue>());

	private class BeanInfoValue {
		EJBLocalHome home;
	}

	private BeanInfoValue createBeanInfo(String beanName) throws NamingException {
		BeanInfoValue result = new BeanInfoValue();
		Context ctx = ServerUtils.getContext();
		try {
			result.home = (EJBLocalHome) ctx.lookup(beanName);
		} finally {
			ctx.close();
		}
		return result;
	}

	private EJBLocalHome getHome(String beanName) throws NamingException {
		BeanInfoValue bi = (BeanInfoValue) beanInfoMap.get(beanName);
		if (bi == null) {
			//create bean info
			bi = createBeanInfo(beanName);
			beanInfoMap.put(beanName, bi);
		}
		EJBLocalHome result = bi.home;
		return result; 
	}

	private PersistentObject findByPrimaryKey(EJBLocalHome home, Object primaryKey) throws com.mg.framework.api.orm.FinderException {
		Method findMethod;
		try {
			findMethod = home.getClass().getMethod("findByPrimaryKey", new Class[] {Object.class});
			return (PersistentObject) findMethod.invoke(home, new Object[] {primaryKey});
		}
		catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
		catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			throw new com.mg.framework.api.orm.FinderException();
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.api.PersistentManager#load(java.lang.String)
	 */
	public PersistentObject find(String name, Object primaryKey) throws com.mg.framework.api.orm.FinderException {
		try {
			return findByPrimaryKey(getHome(name), primaryKey);
		}
		catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}

	public PersistentObject make(String name, AttributeMap attributes) {
		//AttributeMap result = new AttributeMap();
		return null;
	}

	public PersistentObject load(String name, Object primaryKey, Collection keyOfAttributes) throws com.mg.framework.api.orm.FinderException {
		PersistentObject obj = find(name, primaryKey);
		return obj;
	}

	public PersistentObject store(PersistentObject object) throws com.mg.framework.api.orm.FinderException {
		if (object instanceof EJBLocalObject)
			return object;
		//PersistentObject obj = find(name, attributes.getPrimaryKey());
		//obj.setAttributes(attributes);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.api.PersistentManager#create(java.lang.String, com.mg.merp.api.AttributeMap)
	 */
	public PersistentObject create(String name, AttributeMap values) throws com.mg.framework.api.orm.CreateException {
		// TODO Auto-generated method stub
		return null;
	}

	public PersistentObject create(PersistentObject object) throws com.mg.framework.api.orm.CreateException {
		return null;
	}

	public void remove(String name, Object primaryKey) {
		try {
			getHome(name).remove(primaryKey);
		}
		catch (NamingException e) {
			throw new RuntimeException(e);
		}
		catch (RemoveException e) {
			throw new com.mg.framework.api.orm.RemoveException();
		}
	}

	public void flush() {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.PersistentManager#remove(com.mg.framework.api.orm.PersistentObject)
	 */
	public void remove(PersistentObject entity) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.PersistentManager#clear()
	 */
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.PersistentManager#contains(java.lang.Object)
	 */
	public boolean contains(Object entity) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.PersistentManager#find(java.lang.Class, java.lang.Object)
	 */
	public <T> T find(Class<T> entityClass, Object primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.PersistentManager#merge(java.lang.Object)
	 */
	public <T> T merge(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.PersistentManager#persist(com.mg.framework.api.orm.PersistentObject)
	 */
	public void persist(PersistentObject entity) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.PersistentManager#refresh(com.mg.framework.api.orm.PersistentObject)
	 */
	public void refresh(PersistentObject entity) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.PersistentManager#getReference(java.lang.Class, java.lang.Object)
	 */
	public <T> T getReference(Class<T> entityClass, Object primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.PersistentManager#createCriteria(java.lang.String)
	 */
	public Criteria createCriteria(String entityName) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.PersistentManager#getDelegate()
	 */
	public Object getDelegate() {
		// TODO Auto-generated method stub
		return null;
	}

}
