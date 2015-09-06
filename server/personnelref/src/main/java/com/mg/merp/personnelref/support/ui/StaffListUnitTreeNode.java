/*
 * StaffListUnitTreeNode.java
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
package com.mg.merp.personnelref.support.ui;

import com.mg.framework.support.ui.widget.tree.EntityTreeNode;
import com.mg.merp.personnelref.model.StaffListUnit;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Oleg V. Safonov
 * @version $Id: StaffListUnitTreeNode.java,v 1.2 2008/01/28 11:40:50 safonov Exp $
 */
public class StaffListUnitTreeNode extends EntityTreeNode {

  public StaffListUnitTreeNode(StaffListUnitTreeNode parent, StaffListUnit entity) {
    super(parent);
    this.entity = entity;
  }

  public static StaffListUnitTreeNode createTree(List<StaffListUnit> folders) {
    StaffListUnitTreeNode root = null;
    Map<Integer, StaffListUnitTreeNode> nodeMap = new HashMap<Integer, StaffListUnitTreeNode>();
    for (StaffListUnit folder : folders) {
      if (folder.getParent() == null) {
        root = new StaffListUnitTreeNode(null, folder);
        nodeMap.put(folder.getId(), root);
      } else {
        StaffListUnitTreeNode parentNode, node;
        parentNode = nodeMap.get(folder.getParent().getId());
        if (parentNode != null) {
          node = new StaffListUnitTreeNode(parentNode, folder);
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
    return ((StaffListUnit) entity).getUName();
  }

  public void addChild(StaffListUnitTreeNode child) {
    children.add(child);
  }
}
