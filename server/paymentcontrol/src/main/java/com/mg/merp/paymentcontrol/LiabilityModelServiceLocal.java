/*
 * LiabilityModelServiceLocal.java
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
package com.mg.merp.paymentcontrol;

import com.mg.merp.paymentcontrol.model.Liability;

/**
 * Сервис бизнес-компонента "Образцы реестра обязательств"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: LiabilityModelServiceLocal.java,v 1.3 2007/05/14 04:59:59 sharapov Exp $
 */
public interface LiabilityModelServiceLocal extends com.mg.framework.api.DataBusinessObjectService<Liability, Integer> {
	
	/**
	 * тип папки для образцов реестра обязательств
	 */
	final static short FOLDER_PART = 13402;
}
