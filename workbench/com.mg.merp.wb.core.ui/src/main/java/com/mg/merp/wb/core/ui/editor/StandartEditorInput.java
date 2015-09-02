/* StandartEditorInput.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.wb.core.ui.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

/**
 * Класс, реализующий {@link IEditorInput} для формы редактирования
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: StandartEditorInput.java,v 1.5 2008/08/15 10:49:59 safonov Exp $
 */
public abstract class StandartEditorInput<T> implements IEditorInput {

	/**
	 * Данные
	 */
	protected T object;

	/**
	 * Признак создания нового объекта
	 */
	private boolean createNew;

	public StandartEditorInput(T object, boolean createNew) {
		this.object = object;
		this.createNew = createNew;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IEditorInput#exists()
	 */
	public boolean exists() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IEditorInput#getImageDescriptor()
	 */
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IEditorInput#getName()
	 */
	public String getName() {
		return getEditorName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IEditorInput#getPersistable()
	 */
	public IPersistableElement getPersistable() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IEditorInput#getToolTipText()
	 */
	public String getToolTipText() {
		return getToolTip();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public Object getAdapter(Class adapter) {
		if (adapter == IEditorInput.class) {
			return this;
		}
		return null;
	}

	public T getData() {
		return this.object;
	}

	/**
	 * @return
	 * 			истина если создаётся новый бизнес компонент,
	 * 			ложь-если редактируется существующий
	 */
	public boolean isCreateNew() {
		return createNew;
	}

	public void setCreateNew(boolean createNew) {
		this.createNew = createNew;
	}

	public abstract String getToolTip();

	public abstract String getEditorName();
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj){
		//сравниваем именна классов для определения того ли типа объект, иначе на
		//следующем приведении возможна ClassCastException
		if (obj != null && this.getClass().equals(obj.getClass())) {
			return isMatch(((StandartEditorInput<T>) obj).object);
		}
		else
			return false;
	}
	
	/**
	 * Сравнение двух БК на идентичность
	 * 
	 * @param obj
	 * 
	 * @return
	 */
	public abstract boolean isMatch(T obj);
}
