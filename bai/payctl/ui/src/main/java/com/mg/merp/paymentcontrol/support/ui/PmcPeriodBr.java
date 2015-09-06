/*
 * PmcPeriodBr.java
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
 */
package com.mg.merp.paymentcontrol.support.ui;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.MaintenanceTree;
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.generic.ui.DefaultTreeBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.merp.paymentcontrol.PeriodServiceLocal;
import com.mg.merp.paymentcontrol.model.PmcPeriod;

import java.util.List;

/**
 * Контроллер браузера "Периоды планирования"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @author Konstantin S. Alikaev
 * @version $Id: PmcPeriodBr.java,v 1.8 2008/02/12 11:26:46 alikaev Exp $
 */
public class PmcPeriodBr extends DefaultTreeBrowseForm {

  private final String TREE_WIDGET = "tree";
  private PmcCreatePeriodsDialog dialog;

  public PmcPeriodBr() throws Exception {
    super();
    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/paymentcontrol/Period"); //$NON-NLS-1$
    tree.setParentPropertyName("Parent.Id"); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyTreeBrowseForm#loadFolders()
   */
  @Override
  protected TreeNode loadFolders() {
    List<PmcPeriod> list = OrmTemplate.getInstance().find(PmcPeriod.class, String.format("from PmcPeriod pp order by pp.Parent.Id, pp.Id")); //$NON-NLS-1$
    return PeriodTreeNode.createTree(list);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultTreeBrowseForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    super.doOnRun();
    PopupMenu popupMenu = view.getWidget(TREE_WIDGET).getPopupMenu();
    popupMenu.getMenuItem(MaintenanceTree.ADD_MENU_ITEM).setVisible(false);
    popupMenu.getMenuItem(MaintenanceTree.PERMISSION_MENU_ITEM).setVisible(false);
  }

  /**
   * Обработчик пункта КМ "Создать периоды"
   *
   * @param event - событие
   */
  public void onActionCreatePmcPeriod(WidgetEvent event) {
    dialog = (PmcCreatePeriodsDialog) UIProducer.produceForm("com/mg/merp/paymentcontrol/resources/PmcCreatePeriodsDialog.mfd.xml"); //$NON-NLS-1$
    dialog.addOkActionListener(new FormActionListener() {

      public void actionPerformed(FormEvent event) {
        PeriodTreeNode periodTreeNode = (PeriodTreeNode) loadFolders();
        PmcPeriod pmcPeriodRoot = (PmcPeriod) periodTreeNode.getEntity();

        PeriodServiceLocal pmcService = (PeriodServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/paymentcontrol/Period"); //$NON-NLS-1$
        pmcService.createPeriods(
            dialog.isPmcYear(),
            dialog.isPmcHalfYear(),
            dialog.isPmcQuarter(),
            dialog.isPmcMonth(),
            dialog.isPmcTenDays(),
            dialog.isPmcWeek(),
            dialog.isPmcDay(),
            dialog.getBeginDate(),
            dialog.getUpLevelQuantity(),
            pmcPeriodRoot);

        tree.refresh();
      }
    });
    dialog.execute();
  }

}
