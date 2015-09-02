/* MapToDocumentCustomConverter.java
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
package com.mg.merp.document.support;

import java.util.Map;

import net.sf.dozer.util.mapping.MappingException;

import com.mg.framework.generic.AbstractConverter;
import com.mg.framework.utils.BeanUtils;
import com.mg.merp.document.model.DocHead;

/**
 * �����-��������������� ���������� � Map � �������. ��������� ��� ������
 * ��������
 * http://sourceforge.net/tracker/index.php?func=detail&aid=1709116&group_id=133517&atid=727368
 * 
 * @author Oleg V. Safonov
 * @author Valentin A. Poroxnenko
 * @version $Id: MapToDocumentCustomConverter.java,v 1.6 2007/10/25 09:27:23 safonov Exp $
 */
public class MapToDocumentCustomConverter extends AbstractConverter {

	@SuppressWarnings("unchecked")
	private Map<String, Object> convertToMap(Object object) {
		return (Map<String, Object>) object;
	}
	
	/**
	 * �������� ��������� ��������� �� ��������������� ��� �������� � ������ ��������
	 * 
	 * @param docAttr	������ ���������
	 */
	protected void removeUnusedAttributes(Map<String, Object> docAttr) {
		docAttr.remove("Id"); //�� �������� ��������� ����
		docAttr.remove("UNID"); //�� �������� UID
		docAttr.remove("Requester"); //������ ��������������� �������
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractConverter#doConvert(java.lang.Object, java.lang.Object, java.lang.Class, java.lang.Class)
	 */
	protected Object doConvert(Object destination, Object source, Class<?> destClass, Class<?> sourceClass) {
		if (source instanceof Map) {
			Map<String, Object> srcMap = convertToMap(source);
			Object dest = destination;
			if (dest == null) {
				try {
					dest = destClass.newInstance();
				} catch (InstantiationException e) {
					throw new MappingException(e);
				} catch (IllegalAccessException e) {
					throw new MappingException(e);
				}
			}

			removeUnusedAttributes(srcMap);

			DocHead result = (DocHead) dest; //������ ���� ����������
			for (String key : srcMap.keySet()) {
				Object value = srcMap.get(key);
				if (result.hasAttribute(key)) //http://issues.m-g.ru/bugzilla/show_bug.cgi?id=4407
					BeanUtils.setProperty(result, key, value);
			}

			return result;
		} else if (source instanceof DocHead) {
			if (destination == null)
				return ((DocHead) source).getAllAttributes();
			else {
				Map<String, Object> dest = convertToMap(destination); //������ ���� map
				dest.putAll(((DocHead) source).getAllAttributes());
				return dest;
			}
		} else {
			throw new IllegalArgumentException("support Map or DocHead only");
		}
	}

}
