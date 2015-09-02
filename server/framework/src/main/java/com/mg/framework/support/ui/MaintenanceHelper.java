/*
 * MaintenanceHelper.java
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
package com.mg.framework.support.ui;

import java.io.Serializable;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.MaintenanceAction;
import com.mg.framework.api.ui.MaintenanceForm;
import com.mg.framework.api.ui.MaintenanceFormActionListener;
import com.mg.framework.service.ApplicationDictionaryLocator;

/**
 * ����� �������� ��� ������������� �������� ��� ������-�����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: MaintenanceHelper.java,v 1.9 2007/08/10 13:11:27 safonov Exp $
 */
public class MaintenanceHelper implements Serializable {

	/**
	 * ������������� ���������� ���������� ������-����������
	 * 
	 * @param <T>		��� ������-����������
	 * @param <ID>		��� ��������������
	 * @param service	������-���������
	 * @param entity	������ ��������
	 * @param formName	��� ����� ���������, ����� ���� <code>null</code>, � ���� ������ ������� ����� �� ���������
	 * @param listener	��������� ������� ����� ���������, ����� ���� <code>null</code>
	 */
	public static void add(final DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> service,
			PersistentObject entity, final String formName, final MaintenanceFormActionListener listener) {
		if (service == null)
			throw new IllegalArgumentException("Service can't be null");
		internalShowMaintenanceForm(service, entity,
				MaintenanceAction.ADD, formName, listener);
	}

	/**
	 * ������������� ���������� ���������� ������-����������
	 * 
	 * @param <T>		��� ������-����������
	 * @param <ID>		��� ��������������
	 * @param service		������-���������
	 * @param uiProperties	���������������� ��������, ����� ���� <code>null</code>
	 * @param formName		��� ����� ���������, ����� ���� <code>null</code>, � ���� ������ ������� ����� �� ���������
	 * @param listener		��������� ������� ����� ���������, ����� ���� <code>null</code>
	 * 
	 * @throws IllegalArgumentException ���� ������ <code>null</code>
	 */
	public static void add(final DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> service,
			final AttributeMap uiProperties, final String formName, final MaintenanceFormActionListener listener) {
		add(service, service.initialize(uiProperties), formName, listener);
	}
	
	/**
	 * ������������� ��������� ���������� ������-����������
	 * 
	 * @param <T>		��� ������-����������
	 * @param <ID>		��� ��������������
	 * @param service		������-���������
	 * @param primaryKey	��������� ���� ���������� ������-����������
	 * @param formName		��� ����� ���������, ����� ���� <code>null</code>, � ���� ������ ������� ����� �� ���������
	 * @param listener		��������� ������� ����� ���������, ����� ���� <code>null</code>
	 * 
	 * @throws IllegalArgumentException ���� ������ <code>null</code>
	 */
	@SuppressWarnings("unchecked")
	public static void edit(final DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> service,
			final Serializable primaryKey, final String formName, final MaintenanceFormActionListener listener) {
		if (service == null)
			throw new IllegalArgumentException("Service can't be null");
		DataBusinessObjectService s = service;//prevent compiler error
		internalShowMaintenanceForm(service, s.load(primaryKey),
				MaintenanceAction.EDIT, formName, listener);
	}

	/**
	 * ������������� ��������� ���������� ������-����������
	 * 
	 * @param service	������-���������
	 * @param entity	��������� ���� ���������� ������-����������
	 * @param formName	��� ����� ���������, ����� ���� <code>null</code>, � ���� ������ ������� ����� �� ���������
	 * @param listener	��������� ������� ����� ���������, ����� ���� <code>null</code>
	 */
	public static void edit(final DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> service,
			PersistentObject entity, final String formName, final MaintenanceFormActionListener listener) {
		if (service == null)
			throw new IllegalArgumentException("Service can't be null");
		internalShowMaintenanceForm(service, entity,
				MaintenanceAction.EDIT, formName, listener);
	}

	/**
	 * ������������� ����������� ���������� ������-����������
	 * 
	 * @param service		������-���������
	 * @param primaryKey	��������� ���� ���������� ������-����������
	 * @param deepClone		������� ����������� � ��������� ��������
	 * @param formName		��� ����� ���������, ����� ���� <code>null</code>, � ���� ������ ������� ����� �� ���������
	 * @param listener		��������� ������� ����� ���������, ����� ���� <code>null</code>
	 */
	@SuppressWarnings("unchecked")
	public static void clone(final DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> service,
			final Serializable primaryKey, boolean deepClone, final String formName, final MaintenanceFormActionListener listener) {
		if (service == null)
			throw new IllegalArgumentException("Service can't be null");
		DataBusinessObjectService s = service;//prevent compiler error
		internalShowMaintenanceForm(service, s.clone(s.load(primaryKey), deepClone, null),
				MaintenanceAction.CLONE, formName, listener);
	}

	/**
	 * ������������� �������� ���������� ������-����������
	 * 
	 * @param <T>		��� ������-����������
	 * @param <ID>		��� ��������������
	 * @param service		������-���������
	 * @param primaryKey	��������� ���� ���������� ������-����������
	 * @param formName		��� ����� ���������, ����� ���� <code>null</code>, � ���� ������ ������� ����� �� ���������
	 * @param listener		��������� ������� ����� ���������, ����� ���� <code>null</code>
	 * 
	 * @throws IllegalArgumentException ���� ������ <code>null</code>
	 */
	@SuppressWarnings("unchecked")
	public static void view(final DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> service, 
			final Serializable primaryKey, final String formName, final MaintenanceFormActionListener listener) {
		if (service == null)
			throw new IllegalArgumentException("Service can't be null");
		DataBusinessObjectService s = service;//prevent compiler error
		internalShowMaintenanceForm(service, s.load(primaryKey),
				MaintenanceAction.VIEW, formName, listener);
	}
	
	/**
	 * ���������� ������� ����� ���������
	 * 
	 * @param service
	 * @param entity
	 * @param action
	 * @param formName
	 * @param listener
	 */
	@SuppressWarnings("unchecked") //$NON-NLS-1$
    private static void internalShowMaintenanceForm(final DataBusinessObjectService<? extends PersistentObject, ? extends Serializable> service,
    		final PersistentObject entity, final MaintenanceAction action, final String formName,
    		final MaintenanceFormActionListener listener) {
    	//load form
    	MaintenanceForm form = (MaintenanceForm) ApplicationDictionaryLocator.locate().getMaintenaceForm(service, formName);
    	//set listener on action
    	form.addMaintenanceFormActionListener(listener);
    	//execute form
    	form.execute((DataBusinessObjectService<PersistentObject, Serializable>) service, entity, action);
    }
    
}
