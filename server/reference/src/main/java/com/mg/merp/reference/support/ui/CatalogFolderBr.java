/*
 * CatalogFolderBr.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.merp.reference.support.ui;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.generic.ui.DefaultTreeBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.reference.model.CatalogFolder;

import java.util.List;

/**
 * Контроллер папок каталога
 *
 * @author leonova
 * @version $Id: CatalogFolderBr.java,v 1.5 2007/11/08 16:32:39 safonov Exp $
 */
public class CatalogFolderBr extends DefaultTreeBrowseForm {

  public CatalogFolderBr() throws Exception {
    super();
    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/CatalogFolder");
    tree.setParentPropertyName("CatalogFolder");
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#loadFolders()
   */
  @Override
  protected TreeNode loadFolders() {
    List<CatalogFolder> list = OrmTemplate.getInstance().find(CatalogFolder.class, String.format("from CatalogFolder cf where %s order by cf.CatalogFolder.Id, cf.FName", DatabaseUtils.generateFlatBrowseWhereEJBQL("cf.Id", 1)));
    return CatalogFolderTreeNode.createTree(list);
  }

}