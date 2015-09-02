/**
 * Language.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.framework.api.ui;

import java.io.Serializable;
import java.util.Locale;

/**
 * Описатель языка
 * 
 * @author Oleg V. Safonov
 * @version $Id: Language.java,v 1.1 2008/04/09 14:32:34 safonov Exp $
 */
public class Language implements Serializable {
	private String name;
	private Locale locale;

	/**
	 * создание описателя
	 * 
	 * @param name		имя
	 * @param language	язык
	 * @param country	страна
	 * @param variant	вариант языка
	 */
	public Language(String name, String language, String country, String variant) {
		super();
		this.name = name;
		this.locale = new Locale(language, country, variant == null ? "" : variant);
	}

	/**
	 * получить имя
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * получить локаль
	 * 
	 * @return the locale
	 */
	public Locale getLocale() {
		return locale;
	}

}
