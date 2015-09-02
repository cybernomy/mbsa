/*
 * Image.java
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
package com.mg.framework.api.ui.widget;

import java.net.URL;

import com.mg.framework.api.ui.Widget;

/**
 * Элемент "Изображение", предназначен для отображения форматов PNG, JPG
 * 
 * @author Oleg V. Safonov
 * @version $Id: Image.java,v 1.1 2007/05/28 13:40:21 safonov Exp $
 */
public interface Image extends Widget {

	/**
	 * установить изображение
	 * 
	 * @param data	изображение
	 */
	void setData(byte[] data);
	
	/**
	 * показать изображение из указанного расположения
	 * 
	 * @param location	расположение изображения
	 */
	void setLocation(URL location);
	
}
