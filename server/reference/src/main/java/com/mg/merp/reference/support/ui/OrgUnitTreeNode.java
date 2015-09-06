/*
 * OrgUnitTreeNode.java
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
package com.mg.merp.reference.support.ui;

import com.mg.framework.support.ui.widget.tree.EntityTreeNode;
import com.mg.merp.reference.model.OrgUnit;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Oleg V. Safonov
 * @version $Id: OrgUnitTreeNode.java,v 1.1 2006/08/16 04:42:51 leonova Exp $
 */
public class OrgUnitTreeNode extends EntityTreeNode {

  public OrgUnitTreeNode(OrgUnitTreeNode parent, OrgUnit entity) {
    super(parent);
    this.entity = entity;
  }

  public static OrgUnitTreeNode createTree(List<OrgUnit> folders) {
    OrgUnitTreeNode root = null;
    Map<Integer, OrgUnitTreeNode> nodeMap = new HashMap<Integer, OrgUnitTreeNode>();
    for (OrgUnit folder : folders) {
      if (folder.getFolderId() == null || folder.getFolderId().intValue() == 0) {
        root = new OrgUnitTreeNode(null, folder);
        nodeMap.put(folder.getId(), root);
      } else {
        OrgUnitTreeNode parentNode, node;
        parentNode = nodeMap.get(folder.getFolderId());
        if (parentNode != null) {
          node = new OrgUnitTreeNode(parentNode, folder);
          nodeMap.put(folder.getId(), node);
          parentNode.addChild(node);
        }
      }
    }
    return root;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.support.ui.widget.tree.EntityTreeNode#doGetPrimaryKey()
   */
  @Override
  protected Serializable doGetPrimaryKey() {
    return (Serializable) entity.getPrimaryKey();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.support.ui.widget.tree.TreeNode#getText()
   */
  @Override
  public String getText() {
    return ((OrgUnit) entity).getFullName();
  }

  public void addChild(OrgUnitTreeNode child) {
    children.add(child);
  }
}
