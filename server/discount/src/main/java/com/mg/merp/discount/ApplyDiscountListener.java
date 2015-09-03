/*
 * ApplyDiscountListener.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.discount;

import java.io.Serializable;

/**
 * Слушатель применения скидки/наценки
 * 
 * @author Artem V. Sharapov
 * @version $Id: ApplyDiscountListener.java,v 1.1 2009/01/22 06:47:35 sharapov Exp $
 */
public interface ApplyDiscountListener extends Serializable {

	/**
	 * Применение скидки/наценки завершено успешно
	 */
	void completed();
	
	/**
	 * Применение скидки/наценки отменено
	 */
	void aborted();
	
}
