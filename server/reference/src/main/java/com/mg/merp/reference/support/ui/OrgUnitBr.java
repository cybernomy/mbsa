/*
 * OrgUnitBr.java
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
package com.mg.merp.reference.support.ui;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.generic.ui.DefaultTreeBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.reference.model.OrgUnit;

import java.util.List;

/**
 * Контроллер браузера подразделений
 *
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: OrgUnitBr.java,v 1.5 2007/11/08 16:32:39 safonov Exp $
 */
public class OrgUnitBr extends DefaultTreeBrowseForm {

  public OrgUnitBr() throws Exception {
    super();
    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/OrgUnit"); //$NON-NLS-1$
    tree.setParentPropertyName("FolderId"); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyTreeBrowseForm#loadFolders()
   */
  @Override
  protected TreeNode loadFolders() {
    List<OrgUnit> list = OrmTemplate.getInstance().find(OrgUnit.class, String.format("from OrgUnit org where %s order by org.FolderId, org.FullName", DatabaseUtils.generateFlatBrowseWhereEJBQL("org.Id", 4))); //$NON-NLS-1$ //$NON-NLS-2$
    return OrgUnitTreeNode.createTree(list);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultTreeBrowseForm#setupFolderPermissions()
   */
  @Override
  protected void setupFolderPermissions() {
    if (currentNode != null)
      ServerUtils.getSecuritySystem().setupTreePermission((Integer) currentNode.getPrimaryKey(), 4, "com.mg.merp.reference.model.OrgUnit", "FolderId"); //$NON-NLS-1$ //$NON-NLS-2$
  }

}
