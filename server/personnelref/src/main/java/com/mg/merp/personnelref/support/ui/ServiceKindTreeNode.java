/*
 * ServiceKindTreeNode.java
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
import com.mg.merp.personnelref.model.ServiceKind;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author leonova
 * @version $Id: ServiceKindTreeNode.java,v 1.2 2006/09/06 12:48:35 leonova Exp $
 */
public class ServiceKindTreeNode extends EntityTreeNode {

  public ServiceKindTreeNode(ServiceKindTreeNode parent, ServiceKind entity) {
    super(parent);
    this.entity = entity;
  }

  public static ServiceKindTreeNode createTree(List<ServiceKind> folders) {
    ServiceKindTreeNode root = null;
    Map<Integer, ServiceKindTreeNode> nodeMap = new HashMap<Integer, ServiceKindTreeNode>();
    for (ServiceKind folder : folders) {
      if (folder.getParentId() == null || folder.getParentId().intValue() == 0) {
        root = new ServiceKindTreeNode(null, folder);
        nodeMap.put(folder.getId(), root);
      } else {
        ServiceKindTreeNode parentNode, node;
        parentNode = nodeMap.get(folder.getParentId());
        if (parentNode != null) {
          node = new ServiceKindTreeNode(parentNode, folder);
          nodeMap.put(folder.getId(), node);
          parentNode.addChild(node);
        }
      }
    }
    return root;


  }

  public void addChild(ServiceKindTreeNode child) {
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
    return ((ServiceKind) entity).getKName();
  }
}
