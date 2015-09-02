/*
 * AbstractSearchForm.java
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
package com.mg.framework.generic.ui;

import java.util.ArrayList;
import java.util.List;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpForm;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.Widget;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.Button;

/**
 * ����������� ����� ����� ������ ���������, ��� ������� ������������ � ���� � {@link com.mg.framework.api.ui.SearchHelp SearchHelp}.
 * �������� ������� ���������������� ��� ������ � {@link com.mg.framework.api.ui.SearchHelp SearchHelp}.
 * 
 * @author Oleg V. Safonov
 * @version $Id: AbstractSearchForm.java,v 1.5 2009/02/09 12:58:36 safonov Exp $
 */
public abstract class AbstractSearchForm extends AbstractForm implements SearchHelpForm {
	private List<SearchHelpListener> searchHelpListener = new ArrayList<SearchHelpListener>();
	private boolean searchPerformed = false;
	private PersistentObject targetEntity = null;
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		searchPerformed = false;
		super.doOnRun();
		
		//��������� SearchHelp, ���� ������������ � ������ ��������, �� ������� ������ chooseButton
		if (!searchHelpListener.isEmpty()) {
			Widget chooseButton = view.getWidget(STANDART_CHOOSE_BUTTON);
			if (chooseButton != null) {
				chooseButton.setVisible(true);
				view.getWindow().setDefaultButton((Button) chooseButton);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doOnClose()
	 */
	@Override
	protected void doOnClose() {
		if (!searchPerformed)
			fireSearchCanceled(new SearchHelpEvent(this, null));
		super.doOnClose();
	}

	/**
	 * �������� ������ ��������� ��������-���������, ������ ���� ������������� � �����������
	 * 
	 * @return
	 */
	protected abstract PersistentObject[] getSearchedEntities();
	
	/**
	 * ������� ������� � ���������� ������
	 * 
	 * @param event
	 */
	protected void doOnActionChoose(WidgetEvent event) {
		PersistentObject[] entities = getSearchedEntities();
		if (entities.length != 0) {
			fireSearchPerformed(new SearchHelpEvent(this, entities));
			searchPerformed = true; //https://issues.m-g.ru/bugzilla/show_bug.cgi?id=4986
			close();
		}
	}

	/**
	 * �������� �������� ��� ����������������
	 * 
	 * @return	��������, <code>null</code> ���� �� �����������
	 */
	protected PersistentObject getTargetEntity() {
		return targetEntity;
	}

	/**
	 * �������� ������� � ���������� ��������� SearchHelp
	 * 
	 * @param event	�������
	 */
	public void fireSearchPerformed(SearchHelpEvent event) {
		for (SearchHelpListener listener : searchHelpListener)
			listener.searchPerformed(event);
	}

	/**
	 * �������� ������� �� ������ ��������� SearchHelp
	 * 
	 * @param event	�������
	 */
	public void fireSearchCanceled(SearchHelpEvent event) {
		for (SearchHelpListener listener : searchHelpListener)
			listener.searchCanceled(event);
	}

	/**
	 * ���������� ������� ������
	 * 
	 * @param event	�������
	 */
	public final void onActionChoose(WidgetEvent event) {
		//searchPerformed = true; https://issues.m-g.ru/bugzilla/show_bug.cgi?id=4986
		doOnActionChoose(event);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.SearchHelpForm#addSearchHelpListener(com.mg.framework.api.ui.SearchHelpListener)
	 */
	public void addSearchHelpListener(SearchHelpListener listener) {
		if (listener != null)
			searchHelpListener.add(listener);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.SearchHelpForm#removeSearchHelpListener(com.mg.framework.api.ui.SearchHelpListener)
	 */
	public void removeSearchHelpListener(SearchHelpListener listener) {
		if (listener != null)
			searchHelpListener.remove(listener);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.SearchHelpForm#getSearchHelpListeners()
	 */
	public SearchHelpListener[] getSearchHelpListeners() {
		return searchHelpListener.toArray(new SearchHelpListener[searchHelpListener.size()]);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.SearchHelpForm#setEntity(com.mg.framework.api.orm.PersistentObject)
	 */
	public void setTargetEntity(PersistentObject entity) {
		this.targetEntity = entity;
	}

}
