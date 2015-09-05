/*
 * BusinessMethodPermission.java
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

package com.mg.framework.api.security;

import java.security.BasicPermission;
import java.security.Permission;
import java.util.List;

import com.mg.framework.utils.StringUtils;

/**
 * Разрешение на выполнение методов бизнес-компонента
 * 
 * @author Oleg V. Safonov
 * @version $Id$
 */
public class BusinessMethodPermission extends BasicPermission {
	
	/**
	 * наименование метода для проверки прав на получиения списка сущностей бизнес-компонента
	 */
	public static final String BROWSE_METHOD = "browse";
	/**
	 * наименование метода для проверки прав на загрузку сущности бизнес-компонента
	 */
	public static final String LOAD_METHOD = "load";
	/**
	 * наименование метода для проверки прав на создание сущности бизнес-компонента
	 */
	public static final String CREATE_METHOD = "create";
	/**
	 * наименование метода для проверки прав на удаление сущности бизнес-компонента
	 */
	public static final String ERASE_METHOD = "erase";
	/**
	 * наименование метода для проверки прав на изменение сущности бизнес-компонента
	 */
	public static final String STORE_METHOD = "store";
	/**
	 * наименование метода для проверки прав на перемещение сущности бизнес-компонента
	 */
	public static final String MOVE_METHOD = "move";
	/**
	 * наименование метода для проверки прав на изменение иерархии бизнес-компонента
	 */
	public static final String CHANGE_HIERARCHY_METHOD = "changeHierarchy";
	
	private String methodName = null;

	/**
	 * конструктор
	 * 
	 * @param name имя бизнес-компонента
	 */
	public BusinessMethodPermission(String name) {
		super(name.replaceAll("/", ".").toUpperCase()); //BasicPermission naming convention
	}

	/**
	 * конструктор
	 * 
	 * @param name		имя бизнес-компонента
	 * @param actions	имя метода
	 */
	public BusinessMethodPermission(String name, String actions) {
		super(name.replaceAll("/", ".").toUpperCase(), actions); //BasicPermission naming convention
		methodName = actions;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
		    return true;

		if (! (obj instanceof BusinessMethodPermission))
		    return false;

		BusinessMethodPermission that = (BusinessMethodPermission) obj;

		return (this.methodName != null && this.methodName.equals(that.methodName) || this.methodName == null && that.methodName == null) &&
		    (this.getName().equals(that.getName()));
	}

	@Override
	public boolean implies(Permission p) {
		if (!(p instanceof BusinessMethodPermission))
		    return false;

		BusinessMethodPermission that = (BusinessMethodPermission) p;
		
		boolean result = true;
		if (methodName != null) {
			//result = false;
			List<String> methods = StringUtils.split(methodName, ",");
			for (String thatName : StringUtils.split(that.methodName, ","))
				if (!methods.contains(thatName)) {
					result = false;
					break;
				}
		}
		
		return result && super.implies(p);
	}

	@Override
	public String getActions() {
		return methodName != null ? methodName : "";
	}
}
