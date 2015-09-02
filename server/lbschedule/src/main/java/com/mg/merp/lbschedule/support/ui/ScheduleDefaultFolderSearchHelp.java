/*
 * ScheduleDefaultFolderSearchHelp.java
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
package com.mg.merp.lbschedule.support.ui;

import com.mg.merp.lbschedule.ScheduleServiceLocal;
import com.mg.merp.reference.support.ui.FolderByTypeSearchHelp;

/**
 * Поисковик "Образца папки по-умолчанию для создания графика исполнения обязательств"
 * 
 * @author Artem V. Sharapov
 * @version $Id: ScheduleDefaultFolderSearchHelp.java,v 1.1 2007/01/13 13:19:33 sharapov Exp $
 */
public class ScheduleDefaultFolderSearchHelp extends FolderByTypeSearchHelp {

	/* (non-Javadoc)
	 * @see com.mg.merp.reference.support.ui.FolderByTypeSearchHelp#getFolderType()
	 */
	@Override
	protected short getFolderType() {
		return ScheduleServiceLocal.FOLDER_PART;
	}

}
