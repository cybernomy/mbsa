/*
 * DefaultRestrictionForm.java
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

import com.mg.framework.api.ui.AddinFieldsRestriction;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.RestrictionForm;
import com.mg.framework.api.ui.UIProfile;
import com.mg.framework.api.ui.View;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.utils.DataUtils;

/**
 * Базовый класс формы ограничений (условий отбора). Классы наследники расширяют свойства ограничений
 * путем добавления специфичных атрибутов, как правило рекомендуется зарегистрировать атрибуты
 * с помощью метода {@link #registerRestrictionItem()}, после регистрации состояние атрибута и
 * сохранение/восстановление в профиле будет производиться автоматически. Регистрацию необходимо
 * проводить в переопределенном конструкторе контроллера.
 * 
 * @author Oleg V. Safonov
 * @version $Id: DefaultRestrictionForm.java,v 1.9 2008/10/09 06:51:06 safonov Exp $
 */
public abstract class DefaultRestrictionForm extends DefaultDialog implements RestrictionForm {
	private static final String SHOW_ATTR_NAME = "isShowOnEnter"; //$NON-NLS-1$
	private boolean isShowOnEnter = true;
	private Map<String, RestrictionItem> itemsList = new HashMap<String, RestrictionItem>();

	private class RestrictionItem implements Serializable {
		private Object initValue;
		private boolean storeInProfile;
		private Class<?> clazz;

		private RestrictionItem(String name, Object initValue, boolean storeInProfile) {
			super();
			this.initValue = initValue;
			this.storeInProfile = storeInProfile;
			this.clazz = getFieldMetadata(name).getJavaType();
		}

	}
	
	public DefaultRestrictionForm() {
		super();
		addCancelActionListener(new FormActionListener() {

			public void actionPerformed(FormEvent event) {
				undoAll();
			}
			
		});
		registerRestrictionItem(SHOW_ATTR_NAME, true, true);
	}

	//protected 
	
	/**
	 * регистрация элемента ограничения, при очистке формы атрибуты будут сброшены в начальные значения,
	 * если установлен признак сохранения в профиле, то значение атрибута будет сохраняться и восстанавливаться
	 * при создании формы
	 * 
	 * @param name	имя элемента
	 * @param initValue	начальное значение
	 * @param storeInProfile	признак сохранения в профиле пользователя
	 */
	protected void registerRestrictionItem(String name, Object initValue, boolean storeInProfile) {
		itemsList.put(name, new RestrictionItem(name, initValue, storeInProfile));
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		resetUndo();
		view.pack();
		super.doOnRun();
	}

	/**
	 * реализация очистки ограничений, в наследниках возможна специфическая установка
	 * начальных значений
	 *
	 */
	protected void doClearRestrictionItem() {
		for (String name : itemsList.keySet()) {
			RestrictionItem item = itemsList.get(name);
			try {
				setFieldValue(name, item.initValue);
			} catch (Exception e) {
			}
		}
	}
	
	/**
	 * восстановление ограничений, в наследниках возможно восстановление специфических значений
	 * 
	 * @param profile профиль UI
	 */
	protected void doRestoreLayout(UIProfile profile) {
		for (String name : itemsList.keySet()) {
			RestrictionItem item = itemsList.get(name);
			if (item.storeInProfile)
				try {
					String str = profile.getProperty(name);
					doSetFieldValue(name, DataUtils.stringToValue(str, item.clazz));
				} catch (Exception e) {
					if (logger.isDebugEnabled())
						logger.debug("Couldn't restore property: " + name, e);
				}
		}
	}
	
	/**
	 * сохранения ограничений, в наследниках возможно сохранения специфических значений
	 * 
	 * @param profile профиль UI
	 */
	protected void doSaveLayout(UIProfile profile) {
		for (String name : itemsList.keySet()) {
			if (itemsList.get(name).storeInProfile)
				try {
					Object value = doGetFieldValue(name);
					profile.setProperty(name, DataUtils.valueToString(value));
				} catch (Exception e) {
					if (logger.isDebugEnabled())
						logger.debug("Couldn't save property: " + name, e);
				}
		}
	}
	
	/**
	 * приведение ограничений к начальным установкам
	 *
	 */
	public void clearRestrictionItem() {
		doClearRestrictionItem();
	}
	
	public void onActionClear(WidgetEvent event) {
		clearRestrictionItem();
	}
	
	public AddinFieldsRestriction getAddinFieldsRestriction() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doOnClose()
	 */
	@Override
	protected void doOnClose() {
		doSaveLayout(view.getUIProfile());
		super.doOnClose();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#setView(com.mg.framework.api.ui.View)
	 */
	@Override
	public void setView(View view) {
		super.setView(view);
		doRestoreLayout(view.getUIProfile());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.RestrictionForm#isShow()
	 */
	public boolean isShowOnEnter() {
		return isShowOnEnter;
	}

}
