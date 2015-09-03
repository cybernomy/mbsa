/**
 * DocumentAggregatePropertiesStrategy.java
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
package com.mg.merp.document;

import java.io.Serializable;

/**
 * Стратегия изменения агрегирующих свойств документа
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocumentAggregatePropertiesStrategy.java,v 1.1 2007/09/26 09:37:42 safonov Exp $
 */
public interface DocumentAggregatePropertiesStrategy extends Serializable {

	/**
	 * вычислить и установить агрегирующие свойства
	 */
	void calculate();

}
