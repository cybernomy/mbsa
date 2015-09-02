/*
 * UserProfileImpl.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.framework.service;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.mg.framework.api.UserProfile;
import com.mg.framework.utils.StringUtils;

/**
 * @author Oleg V. Safonov
 * @version $Id: UserProfileImpl.java,v 1.9 2007/04/13 14:10:16 safonov Exp $
 *
 */
public final class UserProfileImpl implements UserProfile, Serializable {
	private int[] groups = null;
	private int identificator = 0;
	private String userName;
	private String groupsCommaText;
	private Locale locale = null;
	private Map<String, Map<String, Boolean>> methodsPermission = null;//new HashMap();
	private String[] modules = null;

	public UserProfileImpl(String name, int id, int[] roles, String[] subsystems) {
		super();
		this.userName = name;
		this.identificator = id;
		this.groups = roles;
		this.modules = subsystems;
		List<String> strGroups = new ArrayList<String>();
		for (int group : groups)
			strGroups.add(Integer.toString(group));
		groupsCommaText = StringUtils.join(strGroups, ",");
	}

	// UserProfile

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Locale getLocale() {
		return locale;
	}

	public Map<String, Map<String, Boolean>> getMethodsPermission() {
		return methodsPermission;
	}
	
	public void setMethodsPermission(Map<String, Map<String, Boolean>> methodsPermission) {
		this.methodsPermission = methodsPermission;
	}
	
	public int getIdentificator() {
		return identificator;
	}

	public String getUserName() {
		return userName;
	}

	public int[] getGroups() {
		int[] result = new int[groups.length];
		System.arraycopy(groups, 0, result, 0, groups.length);
		return result;
	}

	public String getGroupsCommaText() {
		return groupsCommaText;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.UserProfile#getModuleAccess()
	 */
	public String[] getPermittableSubsystems() {
		String[] result = new String[modules.length];
		System.arraycopy(modules, 0, result, 0, modules.length);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.UserProfile#getGlobalMenu()
	 */
	public URL getGlobalMenu() {
		//TODO реализовать загрузку названия ресурса меню из записи пользователя или из SystemClient
		return null;
	}

}
