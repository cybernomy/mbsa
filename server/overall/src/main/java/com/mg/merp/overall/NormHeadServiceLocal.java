/*
 * NormHeadServiceLocal.java
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
package com.mg.merp.overall;

import com.mg.merp.overall.model.NormHead;

/**
 * Бизнес - компонент "Нормы выдачи спецодежды"
 * 
 * @author leonova
 * @version $Id: NormHeadServiceLocal.java,v 1.1 2008/06/30 04:13:38 alikaev Exp $
 */
public interface NormHeadServiceLocal extends com.mg.framework.api.DataBusinessObjectService<NormHead, Integer> {
	
	/**
	 * тип папки для норм выдачи одежды
	 */
	final static short FOLDER_PART = 55;
	
}
