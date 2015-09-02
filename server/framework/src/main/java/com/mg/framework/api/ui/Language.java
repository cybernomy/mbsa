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
 * ��������� �����
 * 
 * @author Oleg V. Safonov
 * @version $Id: Language.java,v 1.1 2008/04/09 14:32:34 safonov Exp $
 */
public class Language implements Serializable {
	private String name;
	private Locale locale;

	/**
	 * �������� ���������
	 * 
	 * @param name		���
	 * @param language	����
	 * @param country	������
	 * @param variant	������� �����
	 */
	public Language(String name, String language, String country, String variant) {
		super();
		this.name = name;
		this.locale = new Locale(language, country, variant == null ? "" : variant);
	}

	/**
	 * �������� ���
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * �������� ������
	 * 
	 * @return the locale
	 */
	public Locale getLocale() {
		return locale;
	}

}
