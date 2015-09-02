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
 * ������ ������-���������� "����� ��������"
 * 
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: CatalogFolderServiceLocal.java,v 1.3 2008/05/16 05:52:31 alikaev Exp $
 */
public interface CatalogFolderServiceLocal extends com.mg.framework.api.DataBusinessObjectService<CatalogFolder, Integer> {
	
	/**
	 * ������� ������ ��������� ����� �������� ��� �������� ����� ��������
	 * @param catalogFolder - ����� ��������
	 * @param isRecurseSearch - ������� "����������� ����� ��������� ����� ��������"
	 * @param isIncludeRootFolder - ������� "�������� ��������� ����� �������� � ������"
	 * @return ������ ��������� ����� �������� ��� �������� ����� ��������
	 */
	List<CatalogFolder> getNestedCatalogFolders(CatalogFolder catalogFolder, boolean isRecurseSearch, boolean isIncludeRootFolder);
	
}
