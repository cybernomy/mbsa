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
 * Базовый класс всех BAi (Business Add-in) бизнес расширений системы
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
     * Метод предназначен для извлечения переданных параметров, как правило переопределяется
     * в базовых классах дополнениях конкретных точек вызова, например в базовом классе
     * настраиваемых действий пользователя
     * 
     * @param params
     */
    protected abstract void extractParams(Map<String, ? extends Object> params);
    
    /**
     * Абстрактный метод который необходимо переопределить в реальных алгоритмах
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
	 * Завершить успешное выполнение алгоритма, на всех зарегистрированных слушателях
	 * будет вызван метод {@link BusinessAddinListener#completed(BusinessAddinEvent)}
	 * 
	 * @param result	результат выполнения, или <code>null</code> если результат не требуется
	 */
	public void complete(T result) {
		firePerformCompleted(new BusinessAddinEvent<T>(this, result));
	}
	
	/**
	 * Прерывает выполнение алгоритма, на всех зарегистрированных слушателях
	 * будет вызван метод {@link BusinessAddinListener#aborted(BusinessAddinEvent)}
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
