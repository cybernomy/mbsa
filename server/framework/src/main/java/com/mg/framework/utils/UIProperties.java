/**
 * UIProperties.java
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
package com.mg.framework.utils;

import java.io.FileInputStream;
import java.util.Properties;

import com.mg.framework.api.Logger;
import com.mg.framework.api.ui.HorizontalAlignment;

/**
 * �������� ����������������� ����������. ��� ��������� ����� �������� ��������� ������������
 * ������� ���������� ��� ���������� � ���� ���������.
 * 
 * @author Oleg V. Safonov
 * @version $Id: UIProperties.java,v 1.1 2008/07/28 14:20:16 safonov Exp $
 */
public class UIProperties {
	/**
	 * ��� ����� ������� ��������� ��������� �� ���������
	 */
	private static final String UI_PROPERTIES_FILE_PATH = "/conf/ui.properties";
	private static Logger logger = ServerUtils.getLogger(UIProperties.class);
	private static Properties uiProperties;
	
	static {
		uiProperties = new Properties();
		try {
			uiProperties.load(new FileInputStream(ServerUtils.MBSA_CUSTOM_LOCATION.concat(UI_PROPERTIES_FILE_PATH)));
		} catch (Exception e) {
			logger.warn("UI properties loading failed, use defaults", e);
		}
	}

	/**
	 * �������� ������ ����������� �������� ������� � ������� �������
	 * 
	 * @return	������
	 */
	public static String getMonetaryAmountTableFormat() {
		return uiProperties.getProperty("table.monetaryamountcell.format", ",##0.00");
	}
	
	/**
	 * �������� ������ ����������� �������� ������� � ����������
	 * 
	 * @return	������
	 */
	public static String getMonetaryAmountEditorFormat() {
		return uiProperties.getProperty("editor.monetaryamount.format", ",##0.00");
	}

	/**
	 * �������� ������ ����������� �������������� ������� � ������� �������
	 * 
	 * @return	������
	 */
	public static String getQuantityTableFormat() {
		return uiProperties.getProperty("table.quantitycell.format", ",##0.000");
	}
	
	/**
	 * �������� ������ ����������� �������������� ������� � ����������
	 * 
	 * @return	������
	 */
	public static String getQuantityEditorFormat() {
		return uiProperties.getProperty("editor.quantity.format", ",##0.000");
	}

	/**
	 * �������� �������� ��������������� ������������
	 * 
	 * @param name	��� ��������
	 * @param defaultValue	�������� �� ���������
	 * @return	�������� �������� ��� {@link com.mg.framework.api.ui.HorizontalAlignment#LEFT} ���� �� �������
	 */
	public static HorizontalAlignment getHorizontalAlignmentProperty(String name, HorizontalAlignment defaultValue) {
		try {
			String value = uiProperties.getProperty(name);
			if (value == null)
				return defaultValue;
			else
				return HorizontalAlignment.valueOf(value.toUpperCase());
		} catch (Exception e) {
			logger.debug("No horizontal alignment", e);
			return HorizontalAlignment.LEFT;
		}
	}

	/**
	 * �������� �������� ��������������� ������������ �������� ������� � ������� �������
	 * 
	 * @return	�������� �������� ��� {@link com.mg.framework.api.ui.HorizontalAlignment#RIGHT} ���� �� �������
	 */
	public static HorizontalAlignment getNumberTableCellHorizontalAlignment() {
		return getHorizontalAlignmentProperty("table.numbercell.horizontalalignment", HorizontalAlignment.RIGHT);
	}

	/**
	 * �������� �������� ��������������� ������������ �������� ������� � ������� �������
	 * 
	 * @return	�������� �������� ��� {@link com.mg.framework.api.ui.HorizontalAlignment#RIGHT} ���� �� �������
	 */
	public static HorizontalAlignment getMonetaryAmountTableCellHorizontalAlignment() {
		return getHorizontalAlignmentProperty("table.monetaryamountcell.horizontalalignment", HorizontalAlignment.RIGHT);
	}

	/**
	 * �������� �������� ��������������� ������������ �������������� ������� � ������� �������
	 * 
	 * @return	�������� �������� ��� {@link com.mg.framework.api.ui.HorizontalAlignment#RIGHT} ���� �� �������
	 */
	public static HorizontalAlignment getQuantityTableCellHorizontalAlignment() {
		return getHorizontalAlignmentProperty("table.quantitycell.horizontalalignment", HorizontalAlignment.RIGHT);
	}

	/**
	 * �������� �������� ��������������� ������������ �������� ������� � ����������
	 * 
	 * @return	�������� �������� ��� {@link com.mg.framework.api.ui.HorizontalAlignment#RIGHT} ���� �� �������
	 */
	public static HorizontalAlignment getNumberEditorHorizontalAlignment() {
		return getHorizontalAlignmentProperty("editor.number.horizontalalignment", HorizontalAlignment.RIGHT);
	}

	/**
	 * �������� �������� ��������������� ������������ �������� ������� � ����������
	 * 
	 * @return	�������� �������� ��� {@link com.mg.framework.api.ui.HorizontalAlignment#RIGHT} ���� �� �������
	 */
	public static HorizontalAlignment getMonetaryAmountEditorHorizontalAlignment() {
		return getHorizontalAlignmentProperty("editor.monetaryamount.horizontalalignment", HorizontalAlignment.RIGHT);
	}

	/**
	 * �������� �������� ��������������� ������������ �������������� ������� � ����������
	 * 
	 * @return	�������� �������� ��� {@link com.mg.framework.api.ui.HorizontalAlignment#RIGHT} ���� �� �������
	 */
	public static HorizontalAlignment getQuantityEditorHorizontalAlignment() {
		return getHorizontalAlignmentProperty("editor.quantity.horizontalalignment", HorizontalAlignment.RIGHT);
	}

}
