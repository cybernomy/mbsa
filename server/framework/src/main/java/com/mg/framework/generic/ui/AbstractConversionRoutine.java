/*
 * AbstractConversionRoutine.java
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
package com.mg.framework.generic.ui;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.mg.framework.api.metadata.ConversionRoutine;

/**
 * ������� ���������� ����������� ��������� ��� ����������������� ����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: AbstractConversionRoutine.java,v 1.2 2006/08/31 08:56:24 safonov Exp $
 */
public abstract class AbstractConversionRoutine<S, U> implements ConversionRoutine<S, U>, Serializable {
	private Map<String, Object> importContext = new HashMap<String, Object>();

	/**
	 * ��������� �������� �������
	 * 
	 * @return	��������
	 */
	protected String[] defineImportContext() {
		return null;
	}

	/**
	 * �������� �������� ��������� �������
	 * 
	 * @param name	��� ��������
	 * @return		��������
	 */
	protected Object getImportContextValue(String name) {
		return importContext.get(name);
	}

	/**
     * ���������� ����������� �� �������� ����������������� ���������� � ��������
     * �������������� ��������, ������ ���� ������������� � ������
     * ����������� �����������
	 * 
	 * @param value	�������� ������������ � UI
	 * @return		�������� ��� �������
	 */
	protected abstract S doInputConverse(U value);
	
	/**
     * ���������� ����������� �� �������� ��������������� �������� � ��������
     * ��� ����������������� ����������, ������ ���� ������������� � ������
     * ����������� �����������
	 * 
	 * @param value	�������� �� �������
	 * @return		�������� ��� ����������� � UI
	 */
	protected abstract U doOutputConverse(S value);
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.ConversionRoutine#inputConverse(U)
	 */
	public S inputConverse(U value) {
		return doInputConverse(value);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.ConversionRoutine#outputConverse(S)
	 */
	public U outputConverse(S value) {
		return doOutputConverse(value);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.ConversionRoutine#getImportContextDefinition()
	 */
	public String[] getImportContextDefinition() {
		return defineImportContext();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.ConversionRoutine#setImportContext(java.util.Map)
	 */
	public void setImportContext(Map<String, Object> context) {
		importContext.clear();
		importContext.putAll(context);
	}

}
