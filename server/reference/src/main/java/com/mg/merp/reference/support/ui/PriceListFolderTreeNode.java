/*
 * CatalogFolderTreeNode.java
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
import com.mg.merp.reference.model.PriceListFolder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Oleg V. Safonov
 * @version $Id: PriceListFolderTreeNode.java,v 1.2 2008/01/28 10:35:46 safonov Exp $
 */
public class PriceListFolderTreeNode extends EntityTreeNode {

  public PriceListFolderTreeNode(PriceListFolderTreeNode parent, PriceListFolder entity) {
    super(parent);
    this.entity = entity;
  }

  public static PriceListFolderTreeNode createTree(List<PriceListFolder> folders) {
    PriceListFolderTreeNode root = null;
    Map<Integer, PriceListFolderTreeNode> nodeMap = new HashMap<Integer, PriceListFolderTreeNode>();
    for (PriceListFolder folder : folders) {
      if (folder.getParent() == null) {
        root = new PriceListFolderTreeNode(null, folder);
        nodeMap.put(folder.getId(), root);
      } else {
        PriceListFolderTreeNode parentNode, node;
        parentNode = nodeMap.get(folder.getParent().getId());
        if (parentNode != null) {
          node = new PriceListFolderTreeNode(parentNode, folder);
          nodeMap.put(folder.getId(), node);
          parentNode.addChild(node);
        }
      }
    }
    return root;
  }

  public void addChild(PriceListFolderTreeNode child) {
    children.add(child);
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
    return ((PriceListFolder) entity).getFName();
  }
}
