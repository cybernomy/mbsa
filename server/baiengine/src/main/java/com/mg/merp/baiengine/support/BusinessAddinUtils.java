/*
 * BusinessAddinUtils.java
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
package com.mg.merp.baiengine.support;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import com.mg.framework.api.Logger;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.baiengine.model.ConstantDataType;

/**
 * ������� ������ BusinessAddin
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: BusinessAddinUtils.java,v 1.2 2009/02/06 07:56:24 safonov Exp $
 */
public class BusinessAddinUtils {
	private static Logger logger = ServerUtils.getLogger(BusinessAddinUtils.class);

	/**
	 * ������������ ������ � ������ ���� constantDataType 
	 * 
	 * @param	value ������
	 * @param	constantDataType ��� ���������
	 * @return	������ ���� constantDataType ��� <code>null</code> ���� <code>value == null</code> ��� �������� �������������� �������� ��� �����������
	 */
	public static Object convertConstantValue(String value, ConstantDataType constantDataType) {
		if (value == null) 
			return null;
		switch (constantDataType) {
		case INTEGER: 
			try {
				return new Integer(value);
			} catch (NumberFormatException e) {
				logger.error("Error during constant value convertation", e);
				return null;
			}
		case DATE:	
			try {

				return DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US).parse(value);
			} catch (ParseException e) {
				try {
					logger.debug("Try parse date value in ru_RU locale");
					return DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("ru", "RU")).parse(value); //$NON-NLS-1$ //$NON-NLS-2$
				} catch (ParseException e1) {
					logger.error("Error during constant value convertation", e1);
				}					
				logger.error("Error during constant value convertation", e);
				return null;
			}
		case FLOAT:			
			try {
				return NumberFormat.getNumberInstance(Locale.US).parse(value).doubleValue();
			} catch (ParseException e) {
				try {
					logger.debug("Try parse float value in ru_RU locale");
					return NumberFormat.getNumberInstance(new Locale("ru", "RU")).parse(value); //$NON-NLS-1$ //$NON-NLS-2$
				} catch (ParseException e1) {
					logger.error("Error during constant value convertation", e1);
				}
				logger.error("Error during constant value convertation", e);
				return null;				
			}
		case STRING:
			return value;
		default:
			logger.debug("Unsupported constant type");
			return null;				
		}
	}
	
	/**
	 * ������������ ������ ���� constantDataType � ������
	 * 
	 * @param value				������ ���� constantDataType
	 * @param constantDataType	��� ���������
	 * @return ������ ��� <code>null</code> ���� <code>value == null</code>
	 */
	public static String convertConstantValue(Object value, ConstantDataType constantDataType) {
		if (value == null) 
			return null;
		switch (constantDataType) {
		case INTEGER: 
			return ((Integer) value).toString();
		case DATE:
			return DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US).format((Date) value);
		case FLOAT:
			try {
				NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
				numberFormat.setMaximumFractionDigits(6);
				return numberFormat.format(value);
			} catch (IllegalArgumentException e) {
				logger.error("Error during constant value convertation", e);
				return value.toString();
			}
		case STRING:
			return (String) value;
		default: 
			return null;
		}	
	}
	
}
