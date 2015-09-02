/* AbstractConverter.java
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
package com.mg.framework.generic;

import net.sf.dozer.util.mapping.converters.CustomConverter;

/**
 * �����-������ ��� �������-������������� ���������������� ����� �������� � ������
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: AbstractConverter.java,v 1.3 2007/09/24 15:49:52 safonov Exp $ 
 */
public abstract class AbstractConverter implements CustomConverter {

	/**
	 * ���������� ����������� ��������, ������ ���� ���������� �������� 
	 * ��������������� ������������� (destination --> source � source --> destination)
	 * 
	 * @param destination	������ ��������
	 * @param source		������ ��������
	 * @param destClass		����� ������� ���������
	 * @param sourceClass	����� ������� ���������
	 * @return	������ ���������� � ���� �����������
	 */
	abstract protected Object doConvert(Object destination, Object source, Class<?> destClass, Class<?> sourceClass);
	
	/**
	 * ����������� ������ ������� � ������ � �������
	 * 
	 * @param destination
	 * 			������ �	
	 * @param source
	 * 			������ �
	 * @param destClass
	 * 			����� ������� �
	 * @param sourceClass
	 * 			����� ������� �
	 * 
	 * @return	������������������ ������
	 */
	@SuppressWarnings("unchecked")
	public final Object convert(Object destination, Object source, Class destClass, Class sourceClass) {
		return doConvert(destination, source, destClass, sourceClass);
	}

}
