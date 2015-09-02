/*
 * OperModelFolderDestSearchHelp.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.account.support.ui;

import java.util.List;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.utils.MiscUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.account.model.EconomicOperModel;
import com.mg.merp.core.model.Folder;
import com.mg.merp.reference.support.ui.FolderByTypeSearchHelp;


/**
 * SearchHelp �����-��������� � �������� ����������
 * 
 * @author leonova
 * @version $Id: OperModelFolderDestSearchHelp.java,v 1.2 2006/11/02 15:40:25 safonov Exp $
 */
public class OperModelFolderDestSearchHelp extends FolderByTypeSearchHelp{
		
	@Override
	protected String[] defineImportContext() {
		return new String[] {"entity"};
	}

	@Override
	protected short getFolderType() {		
		EconomicOperModel operModel = ((EconomicOperModel)getImportContextValue("entity"));
		Integer docSectionModel = ServerUtils.getPersistentManager().find(Folder.class, operModel.getFolder().getId()).getFolderType().intValue();
		List<Integer> list = MiscUtils.convertUncheckedList(Integer.class, OrmTemplate.getInstance().findByNamedParam("select ds.FolderType from DocSection ds where ds.ModelFolderType=:docSectionModel order by ds.Id", "docSectionModel", docSectionModel));
		
		try {
			return (short)list.get(0).intValue();
		} catch (IndexOutOfBoundsException e) {
			throw new ApplicationException(e);
		}
		
	}

}
