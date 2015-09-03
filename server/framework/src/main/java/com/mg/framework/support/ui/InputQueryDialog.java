/*
 * InputQueryDialog.java
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
package com.mg.framework.support.ui;

import com.mg.framework.api.metadata.ReflectionMetadata;
import com.mg.framework.api.metadata.ui.FieldMetadata;
import com.mg.framework.api.ui.widget.Label;
import com.mg.framework.generic.ui.DefaultDialog;
import com.mg.framework.service.ApplicationDictionaryLocator;

/**
 * Контроллер формы запроса одиночного параметра, отображает диалог с редактором значения, редактор
 * зависит от типа значения
 * 
 * @author Oleg V. Safonov
 * @version $Id: InputQueryDialog.java,v 1.5 2008/03/13 07:32:05 safonov Exp $
 */
public class InputQueryDialog<T> extends DefaultDialog {
	protected T value;
	protected String prompt;
	
	/**
	 * установить начальное значение
	 * 
	 * @param value	значение, не может быть <code>null</code>
	 */
	public void setValue(T value) {
		if (value == null)
			throw new IllegalArgumentException("value is null");
		
		this.value = value;
	}
	
	/**
	 * получить значений
	 * 
	 * @return	введенное значение
	 */
	public T getValue() {
		return value;
	}

	/**
	 * установить подсказку
	 * 
	 * @param prompt	подсказка
	 */
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doGetFieldMetadata(java.lang.String)
	 */
	@Override
	protected FieldMetadata doGetFieldMetadata(String name) {
		//сгенерируем метаданные по значению, поскольку тип значения до установки неизвестен
		return ApplicationDictionaryLocator.locate().getFieldMetadata(new ReflectionMetadata((String) null, value.getClass()));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		view.pack();
		super.doOnRun();
		((Label) view.getWidget("prompt")).setText(prompt);
	}
}
