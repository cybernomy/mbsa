/**
 * FileChooseHandler.java
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
package com.mg.framework.api.ui;

import java.io.Serializable;

/**
 * Обработчик выбора файлов пользователем
 * 
 * @author Oleg V. Safonov
 * @version $Id: FileChooseHandler.java,v 1.1 2008/05/29 13:41:01 safonov Exp $
 */
public interface FileChooseHandler extends Serializable {
	/**
	 * Indicates that the user cancelled the action.
	 */
	 final  static int CANCELLED = 1;

	/**
	 * Indicates that the action failed.
	 */
	 final  static int FAILED = 2;

	/**
	 * Invoked when the choose file action was successful.
	 *
	 * @param filePaths 		 the paths of the chosen files
	 * @param fileNames 		 the names of the chosen files
	 */
	 void onSuccess(String[] filePaths, String[] fileNames);

	/**
	 * Invoked when the choose file action failed.
	 *
	 * @param reason 		 the reason of the failure, one of the following values:
<ul>
<li>CANCELLED: the user cancelled the action
<li>FAILED: the action failed
</ul>
	 * @param description 		 the description of the failure
	 */
	 void onFailure(int reason, String description);

}
