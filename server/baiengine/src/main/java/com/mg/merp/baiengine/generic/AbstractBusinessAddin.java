/*
 * AbstractBusinessAddin.java
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
package com.mg.merp.baiengine.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.Logger;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.baiengine.BusinessAddin;
import com.mg.merp.baiengine.BusinessAddinEvent;
import com.mg.merp.baiengine.BusinessAddinListener;

/**
 * ������� ����� ���� BAi (Business Add-in) ������ ���������� �������
 * 
 * @author Oleg V. Safonov
 * @version $Id: AbstractBusinessAddin.java,v 1.3 2008/04/24 15:10:07 safonov Exp $
 */
public abstract class AbstractBusinessAddin<T> implements BusinessAddin<T> {
	private List<BusinessAddinListener<T>> listeners = new ArrayList<BusinessAddinListener<T>>();
    private Logger logger = ServerUtils.getLogger(getClass().getName());

    protected Logger getLogger() {
        return logger;
    }

    /**
     * ����� ������������ ��� ���������� ���������� ����������, ��� ������� ����������������
     * � ������� ������� ����������� ���������� ����� ������, �������� � ������� ������
     * ������������� �������� ������������
     * 
     * @param params
     */
    protected abstract void extractParams(Map<String, ? extends Object> params);
    
    /**
     * ����������� ����� ������� ���������� �������������� � �������� ����������
     * 
     * @throws Exception
     */
    protected abstract void doPerform() throws Exception;
    
	private void firePerformCompleted(BusinessAddinEvent<T> event) {
		for (BusinessAddinListener<T> listener : listeners)
			listener.completed(event);
	}

	private void firePerformAborted(BusinessAddinEvent<T> event) {
		for (BusinessAddinListener<T> listener : listeners)
			listener.aborted(event);
	}

	/**
	 * ��������� �������� ���������� ���������, �� ���� ������������������ ����������
	 * ����� ������ ����� {@link BusinessAddinListener#completed(BusinessAddinEvent)}
	 * 
	 * @param result	��������� ����������, ��� <code>null</code> ���� ��������� �� ���������
	 */
	public void complete(T result) {
		firePerformCompleted(new BusinessAddinEvent<T>(this, result));
	}
	
	/**
	 * ��������� ���������� ���������, �� ���� ������������������ ����������
	 * ����� ������ ����� {@link BusinessAddinListener#aborted(BusinessAddinEvent)}
	 */
	public void abort() {
		firePerformAborted(new BusinessAddinEvent<T>(this, null));
	}
	
	/* (non-Javadoc)
	 * @see com.mg.merp.algorithmengine.BusinessAddin#registerListener(com.mg.merp.algorithmengine.BusinessAddinListener)
	 */
	public final void registerListener(BusinessAddinListener<T> listener) {
		if (listener != null)
			listeners.add(listener);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.algorithmengine.BusinessAddin#perform(java.util.Map)
	 */
	public final void perform(Map<String, ? extends Object> params) throws Exception, BusinessException {
		extractParams(params);
		doPerform();
	}

}
