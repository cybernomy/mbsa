/*
 * EngineType.java
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
package com.mg.merp.baiengine.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * @author leonova
 * @version $Id: EngineType.java,v 1.1 2006/10/12 11:41:37 safonov Exp $
 */
@DataItemName ("BAi.EngineType")
public enum EngineType {
	/**
	 * Java
	 * 
	 * @since 3.0
	 */
	@EnumConstantText ("resource://com.mg.merp.baiengine.resources.dataitemlabels#EngineType.JavaEngine")
	JAVA_ENGINE,
	
	/**
	 * BeanShell
	 * 
	 * Не поддерживается в текущей реализации
	 */
	@EnumConstantText ("resource://com.mg.merp.baiengine.resources.dataitemlabels#EngineType.BeanShellEngine")
	BEANSHELL_ENDINE,
	
	/**
	 * Pascal
	 * 
	 * @deprecated не поддерживается начиная в версии 4.x
	 */
	@EnumConstantText ("resource://com.mg.merp.baiengine.resources.dataitemlabels#EngineType.PascalEngine")
	@Deprecated
	PASCAL_ENGINE,

}