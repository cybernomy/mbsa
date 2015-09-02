/*
 * EntityPropertyText.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.framework.api.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * �������� ���������� �� ��������� � ������� ��� ������ � ���������������� ����������
 * ������ �� ������
 * 
 * @author Oleg V. Safonov
 * @version $Id: EntityPropertyText.java,v 1.1 2006/01/24 13:27:54 safonov Exp $
 */
@Target({TYPE})
@Retention(RUNTIME)
@Documented
@Inherited
public @interface EntityPropertyText {
	
	/**
	 * ������ ������������ ���������
	 * 
	 * @return
	 */
	String[] value() default {};
	
	/**
	 * ������ ��� ������
	 * 
	 * @see  java.util.Formatter
	 * @return
	 */
	String format() default "";
}
