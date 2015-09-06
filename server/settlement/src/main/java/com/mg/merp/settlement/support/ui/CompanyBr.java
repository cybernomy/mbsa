/*
 * CompanyBr.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.settlement.support.ui;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.reference.model.OrgUnit;
import com.mg.merp.reference.support.ui.OrgUnitBr;
import com.mg.merp.reference.support.ui.OrgUnitTreeNode;
import com.mg.merp.settlement.ContractorCardServiceLocal;
import com.mg.merp.settlement.support.Messages;

import java.util.List;

/**
 * Контроллер браузера бизнес-компонента "Центры учета"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @author Konstantin S. Alikaev
 * @version $Id: CompanyBr.java,v 1.8 2008/04/29 07:57:03 alikaev Exp $
 */
public class CompanyBr extends OrgUnitBr {

  private final String TREE_WIDGET_NAME = "tree";  //$NON-NLS-1$
  private final String INIT_QUERY_TEXT = "select org from OrgUnit org where %s and (org.OrgUnitKind = 0 or org.OrgUnitKind = 1) order by org.FolderId, org.FullName"; //$NON-NLS-1$
  private final String FOLDER_FIELD_NAME = "org.Id"; //$NON-NLS-1$
  private final int FOLDER_PART = 4;

  public CompanyBr() throws Exception {
    super();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultTreeBrowseForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    super.doOnRun();
    view.getWidget(TREE_WIDGET_NAME).setReadOnly(true);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacyTreeBrowseForm#loadFolders()
   */
  @Override
  protected TreeNode loadFolders() {
    List<OrgUnit> list = OrmTemplate.getInstance().find(OrgUnit.class, String.format(INIT_QUERY_TEXT, DatabaseUtils.generateFlatBrowseWhereEJBQL(FOLDER_FIELD_NAME, FOLDER_PART)));
    OrgUnitTreeNode root = OrgUnitTreeNode.createTree(list);
    if (root == null)
      throw new BusinessException(Messages.getInstance().getMessage(Messages.CREATE_TREE_FAIL));
    return root;
  }

  /**
   * Обработка события выбора пункта К.М. "Карточки расчетов с партнерами"
   *
   * @param event - событие
   */
  public void onActionShowContractorCard(WidgetEvent event) throws Exception {
    if (currentNode != null) {
      final ContractorCardServiceLocal service = (ContractorCardServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/settlement/ContractorCard"); //$NON-NLS-1$
      ContractorCardBr form = (ContractorCardBr) ApplicationDictionaryLocator.locate().getBrowseForm(service, null);
      Integer orgUnitId = (Integer) currentNode.getPrimaryKey();
      OrgUnit orgUnit = ServerUtils.getPersistentManager().find(OrgUnit.class, orgUnitId);
      form.setCompanyId(orgUnitId);
      form.setTitle(StringUtils.format(Messages.getInstance().getMessage(Messages.CONTRACTOR_CARD_FORMBR_TITLE), orgUnit.getCode().trim()));
      form.run();
    }
  }

}
