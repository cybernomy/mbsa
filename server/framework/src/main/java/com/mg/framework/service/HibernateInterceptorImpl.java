/*
 * HibernateInterceptorImpl.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 *
 */
package com.mg.framework.service;

import java.io.Serializable;

import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

/**
 * Перехватчик событий системы персистенции Hibernate
 * @see org.hibernate.Interceptor
 * 
 * @author Oleg V. Safonov
 * @version $Id: HibernateInterceptorImpl.java,v 1.2 2006/10/17 14:27:25 safonov Exp $
 */
public class HibernateInterceptorImpl extends EmptyInterceptor {

    public boolean onFlushDirty(Object entity, Serializable id,
            Object[] currentState, Object[] previousState,
            String[] propertyNames, Type[] types) throws CallbackException {
//        if (!(entity instanceof PersistentObject))
//            return false;
//        
//        AttributeMap currentValue = new LocalDataTransferObject();
//        AttributeMap prevValue = new LocalDataTransferObject();
//        for (int i = 0; i < currentState.length; i++)
//            currentValue.put(propertyNames[i], currentState[i]);
//        //sometimes maybe null
//        if (previousState != null)
//        	for (int i = 0; i < previousState.length; i++)
//        		prevValue.put(propertyNames[i], previousState[i]);
//        
//        try {
//            return EntityInterceptorManagerLocator.locate().invokeOnModifyInterceptor((PersistentObject) entity, id, currentValue, prevValue);
//        } catch (Exception e) {
//            throw new CallbackException(e);
//        }
    	return false;
    }

    public boolean onSave(Object entity, Serializable id, Object[] state,
            String[] propertyNames, Type[] types) throws CallbackException {
//        if (!(entity instanceof PersistentObject))
//            return false;
//        
//        boolean result = false;
//        AttributeMap value = new LocalDataTransferObject();
//        for (int i = 0; i < state.length; i++) {
//        	if (propertyNames[i].equals("SysClient") && state[i] == null) {
//        		//TODO заполняем поле sys_client 1, необходимо заполнять текущим мандантом в который вошел пользователь
//        		state[i] = ServerUtils.getPersistentManager().find("com.mg.merp.core.model.SysClient", 1);
//        		result = true;
//        	}
//            value.put(propertyNames[i], state[i]);
//        	
//        }
//
//        try {
//            return EntityInterceptorManagerLocator.locate().invokeOnCreateInterceptor((PersistentObject) entity, id, value) || result;
//        } catch (Exception e) {
//            throw new CallbackException(e);
//        }
    	return false;
    }

    public void onDelete(Object entity, Serializable id, Object[] state,
            String[] propertyNames, Type[] types) throws CallbackException {
//        if (!(entity instanceof PersistentObject))
//            return;
//        
//        AttributeMap value = new LocalDataTransferObject();
//        for (int i = 0; i < state.length; i++)
//            value.put(propertyNames[i], state[i]);
//
//        try {
//            EntityInterceptorManagerLocator.locate().invokeOnRemoveInterceptor((PersistentObject) entity, id, value);
//        } catch (Exception e) {
//            throw new CallbackException(e);
//        }
    }

}
