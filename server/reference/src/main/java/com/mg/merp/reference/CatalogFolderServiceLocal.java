/*
 * CatalogFolderServiceLocal.java
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
package com.mg.merp.reference;

import java.util.List;

import com.mg.merp.reference.model.CatalogFolder;

/**
 * Сервис бизнес-компонента "Папки каталога"
 * 
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: CatalogFolderServiceLocal.java,v 1.3 2008/05/16 05:52:31 alikaev Exp $
 */
public interface CatalogFolderServiceLocal extends com.mg.framework.api.DataBusinessObjectService<CatalogFolder, Integer> {
	
	/**
	 * Получть список вложенных папок каталога для заданной папки каталога
	 * @param catalogFolder - папка каталога
	 * @param isRecurseSearch - признак "рекурсивный поиск вложенных папок каталога"
	 * @param isIncludeRootFolder - признак "включать указанную папку каталога в список"
	 * @return список вложенных папок каталога для заданной папки каталога
	 */
	List<CatalogFolder> getNestedCatalogFolders(CatalogFolder catalogFolder, boolean isRecurseSearch, boolean isIncludeRootFolder);
	
}
