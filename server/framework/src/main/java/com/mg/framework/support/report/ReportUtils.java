/**
 * ReportUtils.java
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
package com.mg.framework.support.report;

import java.io.Serializable;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

import com.mg.framework.api.Logger;
import com.mg.framework.api.report.RptProperties;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;

/**
 * ������� ���������� �������. ����������� � ����, �.�. ��������� � � ������ ���������� � �
 * ������� ����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: ReportUtils.java,v 1.3 2008/08/04 13:35:47 safonov Exp $
 */
public class ReportUtils {
	private static Logger logger = ServerUtils.getLogger(ReportUtils.class);

	private static Serializable convertStringToID(String idDesc) {
		String[] idInfo = idDesc.split("#");
		if (idInfo.length == 2) {
			String typeName = idInfo[0];
			if (Integer.class.getName().equalsIgnoreCase(typeName))
				return Integer.parseInt(idInfo[1]);
			else if (Long.class.getName().equalsIgnoreCase(typeName))
				return Long.parseLong(idInfo[1]);
			else
				return idInfo[1];
		} else
			return null;
	}
	
	/**
	 * �������������� ���������� BIRT � ���������� ���� BAi, �.�. � BIRT ���� ���������� ������, �� ���
	 * �������� ����������� ����� ���������� ��������� ��� � �������� ������ ��� ����������,
	 * ������ ����� ���������� ������ � ����������� � �������� � custom ����� �������.
	 * �� ������� ������ ������������� ��� Entity. ����� ������������� ��������� � ������������������
	 * ������� {@link com.mg.framework.api.report.RptProperties#ENTITY_IDS_DATASET_PARAMETER} �
	 * {@link com.mg.framework.api.report.RptProperties#BUSINESS_SERVICE_DATASET_PARAMETER}
	 * 
	 * @param name	��� ���������
	 * @param value	�������� ��������� ���������� �������
	 * @param entityManager	�������� ��������� ���������
	 * @return	��������������� �������� ���������
	 */
	public static Object convertParamValue(String name, Object value, Object entityManager) {
		 if (value instanceof String) {
			String strValue = (String) value;
			if (RptProperties.ENTITY_IDS_DATASET_PARAMETER.equalsIgnoreCase(name)) {
				//{<java type>#<identificator value>}[,{<java type>#<identificator value>}]
				if (strValue.startsWith("{") && strValue.endsWith("}")) {
					String[] strIds = StringUtils.split(strValue.substring(1, strValue.length() - 1), "},{").toArray(new String[0]);
					int length = strIds.length;
					if (length == 0)
						return value;
					//������ ������ ���� ������ �������� > 1, ����� ������� ������� ������
					//������ ��������� ������������� ��� ������������� � ����������� � QL ��������
					if (length > 1) {
						Serializable[] ids = new Serializable[strIds.length];
						for (int i = 0; i < strIds.length; i++)
							ids[i] = convertStringToID(strIds[i]);
						return ids;
					} else
						return convertStringToID(strIds[0]);
				} else
					return value;
			} else if (RptProperties.BUSINESS_SERVICE_DATASET_PARAMETER.equalsIgnoreCase(name)) {
				//��� ������� ������-����������
				return ApplicationDictionaryLocator.locate().getBusinessService(strValue);
			} else if (strValue.startsWith("${entity:") && strValue.endsWith("}")) {
				//���� ������ ������������ ��������, �� �������� ��������
				//${entity:<entity name>#<identificator value>[;searchHelp:<search help name>]}
				if (logger != null)
					logger.debug("convert entity parameter: " + strValue);
				strValue = strValue.substring(9, strValue.length() - 1);
				//������ ���������� � searchHelp
				int idx = strValue.indexOf(";searchHelp:");
				if (idx != -1)
					strValue = strValue.substring(0, idx);
				String[] entityInfo = strValue.split("#");
				//���������� �� ������ ���������� � ��������
				if (entityInfo.length != 2) {
					if (logger != null)
						logger.error("invalid entity description: " + strValue);
					return null;
				}
				Session entityManagerImpl = (Session) entityManager;
				ClassMetadata classMetadata = entityManagerImpl.getSessionFactory().getClassMetadata(entityInfo[0]);
				if (classMetadata == null) {
					logger.error("metadata of entity not found: " + entityInfo[0]);
					return null;
				}
				Type type = classMetadata.getIdentifierType();
				Serializable entityID = entityInfo[1];
				if (type instanceof IntegerType)
					entityID = Integer.parseInt((String) entityID);
				if (type instanceof LongType)
					entityID = Long.parseLong((String) entityID);
				return entityManagerImpl.get(entityInfo[0], entityID);
			} else
				return value;
		} else
			return value;
	}

	/**
	 * ������� ��������� ������������� ��������� � ����� ��������
	 * 
	 * @param entity	��������
	 * @return	��������� �������������
	 */
	public static String createEntityParam(Object entity) {
		if (entity == null)
			return null;
		//����������� �������� � ������, ����� ����� ���� �������� �������� � �������
		//${entity:<entity name>#<identificator value>}
		Session session = (Session) ServerUtils.getPersistentManager().getDelegate();
		session.lock(entity, LockMode.NONE);//reassociate a transient instance with a session
		return createEntityParam(session.getEntityName(entity), session.getIdentifier(entity));
	}
	
	/**
	 * ������� ��������� ������������� ��������� � ����� ��������
	 * 
	 * @param entityName	��� ��������
	 * @param entityId	�������������
	 * @return	��������� �������������
	 */
	public static String createEntityParam(String entityName, Serializable entityId) {
		StringBuilder sb = new StringBuilder("${entity:")
				.append(entityName)
				.append("#")
				.append(entityId.toString())
				.append("}");
		return sb.toString();
	}
	
	/**
	 * ������� ��������� ������������� ��������� � ����� �������������� ���������
	 * 
	 * @param ids	�������������� ���������
	 * @return	��������� �������������
	 */
	public static String createEntityIdsParam(Serializable[] ids) {
		if (ids == null || ids.length == 0)
			return null;
		StringBuilder sb = new StringBuilder();
		//{<java type>#<identificator value>}[,{<java type>#<identificator value>}]
		for (Serializable id : ids) {
			if (sb.length() != 0)
				sb.append(",");
			sb.append("{")
					.append(id.getClass().getName())
					.append("#")
					.append(id)
					.append("}");
		}
		return sb.toString();
	}
	
}
