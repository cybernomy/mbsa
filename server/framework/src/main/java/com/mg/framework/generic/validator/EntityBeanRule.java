/*
 * EntityBeanRule.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.framework.generic.validator;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.MessageSourceAccessor;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.ReflectionUtils;

/**
 * ����������� ������� �������� ������ � ��������-���������
 * 
 * @author Oleg V. Safonov
 * @version $Id: EntityBeanRule.java,v 1.2 2006/08/15 15:45:35 safonov Exp $
 */
public abstract class EntityBeanRule extends AbstractRule {
	private PersistentObject entity;
	private String propertyName;

	/**
	 * ������� �������
	 * 
	 * @param message		���������
	 * @param entity		������-�������� ��������
	 * @param propertyName	������������ �������� ��������
	 */
	public EntityBeanRule(String message, PersistentObject entity, String propertyName) {
		super(message, entity.getAttribute(propertyName));
		this.entity = entity;
		this.propertyName = propertyName;
	}

	/**
	 * ������� �������
	 * 
	 * @param messageSource	�������� ���������
	 * @param code			��� ���������
	 * @param entity		������-�������� ��������
	 * @param propertyName	������������ �������� ��������
	 */
	public EntityBeanRule(MessageSourceAccessor messageSource, String code, PersistentObject entity, String propertyName) {
		super(messageSource, code, entity.getAttribute(propertyName));
		this.entity = entity;
		this.propertyName = propertyName;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.validator.Rule#getMessage()
	 */
	@Override
	public String getMessage() {
		String msg = super.getMessage();
		msg = String.format("%s '%s'", msg, ApplicationDictionaryLocator.locate().getFieldMetadata(ReflectionUtils.getPropertyReflectionMetadata(ReflectionUtils.getEntityClass(entity), propertyName)).getShortLabel());
		return msg;
	}

}
