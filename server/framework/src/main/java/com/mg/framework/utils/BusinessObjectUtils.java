/*
 * BusinessObjectUtils.java
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

package com.mg.framework.utils;

import java.io.Serializable;

import javax.annotation.security.PermitAll;
import javax.ejb.SessionContext;
import javax.interceptor.InvocationContext;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.Logger;
import com.mg.framework.api.metadata.BusinessServiceMetadata;
import com.mg.framework.api.orm.DetachedCriteria;
import com.mg.framework.api.orm.FlushMode;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.orm.Subqueries;
import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.support.Messages;
import com.mg.framework.support.metadata.BusinessServiceMetadataImpl;

/**
 * ������� ��� ������-�����������
 * 
 * @author Oleg V. Safonov
 * @version $Id$
 */
public class BusinessObjectUtils {

	/**
	 * ����������� ��� ��������� ���� �� ���������� ������� ������-�����������
	 * 
	 * @param ctx			�������� ������
	 * @param logger		������
	 * @param metadata		���������� ������-����������
	 * @return				��������� ���������� ������
	 * 
	 * @throws com.mg.framework.api.SecurityException � ������ ��������� ������������
	 * @throws Exception �� ��������������� � ���������� ������
	 */
	public static Object securityCheckInterceptor(InvocationContext ctx, Logger logger, BusinessServiceMetadata metadata) throws Exception {
		if (metadata == null) {
			if (logger.isDebugEnabled())
				logger.debug(String.format("proceed non security bean: %s", ctx.getTarget().toString()));
			return ctx.proceed();
		}
		if (ctx.getTarget().getClass().getAnnotation(PermitAll.class) != null) {
			if (logger.isDebugEnabled())
				logger.debug(String.format("proceed PermitAll target: %s", metadata.getName()));
			return ctx.proceed();
		}
		if (ctx.getMethod().getAnnotation(PermitAll.class) != null) {
			if (logger.isDebugEnabled())
				logger.debug(String.format("proceed PermitAll method: %s, bean: %s", ctx.getMethod().getName(), metadata.getName()));
			return ctx.proceed();
		}
		if (logger.isDebugEnabled())
			logger.debug(String.format("Check method permission. Bean: %s, method: %s", metadata.getName(), ctx.getMethod().getName()));
		try {
			SecurityUtils.checkPermission(new BusinessMethodPermission(metadata.getName(), ctx.getMethod().getName()));
		} catch (SecurityException e) {
			//������� ����� �� �������� ����� ���������� � ����������
			throw new com.mg.framework.api.SecurityException(Messages.getInstance().getMessage(Messages.NO_PERMISSION), e);
		}
		return ctx.proceed();
	}
	
	/**
	 * �������� ���������� ������-����������
	 * 
	 * @param context	�������� ������-����������
	 * @return			���������� ��� <code>null</code> ���� ���������� �� �������
	 */
	public static BusinessServiceMetadata loadBusinessServiceMetadata(SessionContext context) {
		DetachedCriteria dc = DetachedCriteria.forEntityName("com.mg.merp.core.model.SysClassImplementation", "sciLayer")
				.setProjection(Projections.max("sciLayer.ApplicationLayer"))
				.add(Restrictions.eqProperty("sciLayer.Name", "sci.Name"));
		PersistentObject sysClass = OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria("com.mg.merp.core.model.SysClassImplementation", "sci")
				.setProjection(Projections.property("sci.SysClass"))
				.add(Restrictions.eq("sci.Name", ServerUtils.getJNDIName(context)))
				.add(Subqueries.propertyEq("sci.ApplicationLayer", dc))
				.setCacheable(true)
				.setCacheRegion("com/mg/jet/sysclassimplementations/")
				.setFlushMode(FlushMode.MANUAL));
		return new BusinessServiceMetadataImpl((Integer) sysClass.getAttribute("Id"), (String) sysClass.getAttribute("BeanName"));
	}
	
	/**
	 * �������� ����� ������� �������� ��� ������-����������
	 * 
	 * @param <T>			��� ��������
	 * @param <ID>			��� ���������� �����
	 * @param serviceClass	������-����������
	 * @return				����� ������� �������� ��� <code>null</code> ���� �� ������
	 */
	public static <T extends PersistentObject, ID extends Serializable> Class<T> getBusinessServiceEntityClass(DataBusinessObjectService<T, ID> serviceClass) {
		try {
			return ReflectionUtils.getGenericClass(serviceClass.getClass(), 0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
}
