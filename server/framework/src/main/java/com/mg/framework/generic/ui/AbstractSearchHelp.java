/*
 * AbstractSearchHelp.java
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
package com.mg.framework.generic.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.Logger;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.utils.ServerUtils;

/**
 * Реализация механизма поиска сущностей, базовый абстрактный класс, в наследнике обязательно необходимо
 * переопределить как минимум один из методов {@link #doSearch()} или {@link #doSearch(PersistentObject)}.
 * Если ни один из методов не будет переопределен, то возникнет зацикливание.
 * 
 * @author Oleg V. Safonov
 * @version $Id: AbstractSearchHelp.java,v 1.7 2009/02/09 14:56:03 safonov Exp $
 */
public abstract class AbstractSearchHelp implements SearchHelp, SearchHelpListener {
	private Logger logger = ServerUtils.getLogger(this.getClass());
	private List<SearchHelpListener> listeners = new ArrayList<SearchHelpListener>();
	private Map<String, Object> importContext = new HashMap<String, Object>();
	private Map<String, Object> exportContext = new HashMap<String, Object>();

	/**
	 * выполняет поиск, должен быть переопределен в наследнике если не переопределен метод {@link #doSearch(PersistentObject)}
	 * 
	 * @throws Exception
	 */
	protected void doSearch() throws Exception {
		doSearch(null);
	}
	
	/**
	 * выполняет поиск, должен быть переопределен в наследнике если не переопределен метод {@link #doSearch()}
	 * 
	 * @param entity	сущность
	 * @throws Exception
	 */
	protected void doSearch(PersistentObject entity) throws Exception {
		doSearch();
	}
	
	/**
	 * выполняет интерактивный просмотр, должен быть переопределен в наследнике
	 * если поддерживается просмотр
	 * 
	 * @param entity сущность
	 */
	protected void doView(PersistentObject entity) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * вызывается непосредственно после выполнения поиска, наследник может
	 * реализовать дополнительную функциональность, например установить контекст
	 * экспорта
	 * 
	 * @param event событие поиска
	 */
	protected void doOnSearchPerformed(SearchHelpEvent event) {
		
	}
	
	/**
	 * описывает контекст экспорта
	 * 
	 * @return	контекст
	 */
	protected String[] defineExportContext() {
		return null;
	}
	
	/**
	 * описывает контекст импорта
	 * 
	 * @return	контекст
	 */
	protected String[] defineImportContext() {
		return null;
	}
	
	/**
	 * get logger for this object
	 * 
	 * @return logger
	 */
	protected Logger getLogger() {
		return logger;
	}
	
	/**
	 * получить значение контекста импорта
	 * 
	 * @param name	имя значения
	 * @return		значение
	 */
	protected Object getImportContextValue(String name) {
		return importContext.get(name);
	}
	
	/**
	 * установить значение контекста экспорта
	 * 
	 * @param name	имя значения
	 * @param value	значение
	 */
	protected void setExportContextValue(String name, Object value) {
		exportContext.put(name, value);
	}
	
	/**
	 * отправить событие о выполнении поиска
	 * 
	 * @param event	событие
	 */
	public void fireSearchPerformed(SearchHelpEvent event) {
		for (SearchHelpListener listener : listeners)
			listener.searchPerformed(event);
	}

	/**
	 * отправить событие об отмене поиска
	 * 
	 * @param event	событие
	 */
	public void fireSearchCanceled(SearchHelpEvent event) {
		for (SearchHelpListener listener : listeners)
			listener.searchCanceled(event);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.SearchHelp#addSearchHelpListener(com.mg.framework.api.ui.SearchHelpListener)
	 */
	public void addSearchHelpListener(SearchHelpListener listener) {
		if (!listeners.contains(listener))
			listeners.add(listener);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.SearchHelp#removeSearchHelpListener(com.mg.framework.api.ui.SearchHelpListener)
	 */
	public void removeSearchHelpListener(SearchHelpListener listener) {
		listeners.remove(listener);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.SearchHelp#search()
	 */
	public void search() {
		try {
			doSearch();
		} catch (ApplicationException e) {
			throw e;
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.SearchHelp#search(com.mg.framework.api.orm.PersistentObject)
	 */
	public void search(PersistentObject entity) {
		try {
			doSearch(entity);
		} catch (ApplicationException e) {
			throw e;
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
	}

	/*
	 *  (non-Javadoc)
	 * @see com.mg.framework.api.ui.SearchHelpListener#searchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
	 */
	public void searchPerformed(SearchHelpEvent event) {
		doOnSearchPerformed(event);
		fireSearchPerformed(event);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.SearchHelpListener#searchCanceled(com.mg.framework.api.ui.SearchHelpEvent)
	 */
	public void searchCanceled(SearchHelpEvent event) {
		fireSearchCanceled(event);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.SearchHelp#isSupportView()
	 */
	public boolean isSupportView() {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.SearchHelp#view(com.mg.framework.api.orm.PersistentObject)
	 */
	public void view(PersistentObject entity) {
		doView(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.SearchHelp#getExportParameters()
	 */
	public final String[] getExportContextDefinition() {
		return defineExportContext();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.SearchHelp#getExportParameterValues()
	 */
	public final Map<String, Object> getExportContext() {
		if (exportContext.size() == 0)
			return null;
		else
			return new HashMap<String, Object>(exportContext);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.SearchHelp#getImportParameters()
	 */
	public final String[] getImportContextDefinition() {
		return defineImportContext();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.SearchHelp#setImportParameterValues(java.util.Map)
	 */
	public final void setImportContext(Map<String, Object> context) {
		importContext.clear();
		importContext.putAll(context);
	}
}
