/**
 * SearchHelpName.java
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
package com.mg.framework.api.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Наименование механизма поиска сущности, с помощью данной аннотации возможно связывание
 * атрибута сущности с механизмом поиска отличным от указанного в элементе данных. Как правило
 * используется для уменьшения количества элементов данных если они различаются только механизмом
 * поиска
 * 
 * @author Oleg V. Safonov
 * @version $Id: SearchHelpName.java,v 1.1 2007/12/13 13:31:11 safonov Exp $
 */
@Target({TYPE, METHOD, FIELD})
@Retention(RUNTIME)
@Documented
@Inherited
public @interface SearchHelpName {
	String value() default "";
}
