/* WorkbenchImpl.java
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
package com.mg.merp.workbench.support;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.utils.MiscUtils;
import com.mg.merp.security.model.Groups;
import com.mg.merp.workbench.service.Workbench;

import java.util.HashMap;
import java.util.List;

import javax.naming.InitialContext;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.transaction.UserTransaction;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: WorkbenchImpl.java,v 1.4 2007/04/11 06:51:41 poroxnenko Exp $
 */
public class WorkbenchImpl implements Workbench {

  /* (non-Javadoc)
   * @see com.mg.merp.workbench.service.Workbench#testConnectionOK()
   */
  public void testConnectionOK() {
    return;
  }

  public TreeModel getSysClasses() throws Exception {
    OrmTemplate tmpl = OrmTemplate.getInstance();
    UserTransaction utx = (UserTransaction) new InitialContext().lookup("UserTransaction");
    utx.begin();
    try {
      List<PersistentObject> scl = MiscUtils.convertUncheckedList(PersistentObject.class, tmpl.find("select sc from SysClass sc order by sc.SysModule.Name, sc.BeanName"));
      DefaultMutableTreeNode root = new DefaultMutableTreeNode();
      TreeModel result = new DefaultTreeModel(root);

      HashMap<String, Integer> alias = new HashMap<String, Integer>();
      for (PersistentObject sc : scl) {
        String scDesc = (String) sc.getAttribute("Description");
        if (scDesc == null)
          scDesc = "";
        String scBeanName = (String) sc.getAttribute("BeanName");

        PersistentObject sm = (PersistentObject) sc.getAttribute("SysModule");
        String smName = (String) sm.getAttribute("Name");
        String smDesc = (String) sm.getAttribute("Description");
        if (smDesc == null)
          smDesc = "";

        StringBuffer sb = new StringBuffer();
        String key = new String(sb.append(smName).append("(").append(smDesc).append(")"));
        int num;
        if (!alias.containsKey(key)) {
          num = root.getChildCount();
          alias.put(key, num);
          root.add(new DefaultMutableTreeNode(key));
        } else
          num = alias.get(key);

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) root.getChildAt(num);
        StringBuffer sb2 = new StringBuffer();
        DefaultMutableTreeNode leaf = new DefaultMutableTreeNode(new String(sb2.append(scBeanName).append("(").append(scDesc).append(")")));
        leaf.setAllowsChildren(false);
        node.add(leaf);
      }

      utx.commit();
      return result;
    } catch (Exception ex) {
      utx.rollback();
      throw new ApplicationException(Messages.getInstance().getMessage(Messages.GET_SYSCLASSES_ERROR), ex.getCause());
    }
  }

  public List<Groups> getSecGroups() throws Exception {
    OrmTemplate tmpl = OrmTemplate.getInstance();
    UserTransaction utx = (UserTransaction) new InitialContext().lookup("UserTransaction");
    utx.begin();
    try {
      List<Groups> sgrl = MiscUtils.convertUncheckedList(Groups.class, tmpl.find("select sg from Groups sg"));
      utx.commit();
      return sgrl;
    } catch (Exception ex) {
      utx.rollback();
      throw new ApplicationException(Messages.getInstance().getMessage(Messages.GET_SECGROUPS_ERROR), ex.getCause());
    }
  }

}
