/*
 * ChooseDocFlowStageDialog.java
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
package com.mg.merp.docflow.support.ui;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.generic.ui.DefaultEntityListTableModel;
import com.mg.framework.generic.ui.DefaultWizardDialog;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.MiscUtils;
import com.mg.merp.docprocess.model.StageAction;

/**
 * @author Oleg V. Safonov
 * @version $Id: ChooseDocFlowStageDialog.java,v 1.2 2006/09/11 09:29:40 safonov Exp $
 */
public class ChooseDocFlowStageDialog extends DefaultWizardDialog {
  private final static String LOAD_STAGES_EJBQL = "from StageAction"; //$NON-NLS-1$
  private final static String[] fieldList = new String[]{"Id", "Name"}; //$NON-NLS-1$ $NON-NLS-2$
  private DefaultTableController stagesList;

  public ChooseDocFlowStageDialog() {
    stagesList = new DefaultTableController(new DefaultEntityListTableModel<StageAction>() {
      @Override
      protected void doLoad() {
        setEntityList(MiscUtils.convertUncheckedList(StageAction.class, OrmTemplate.getInstance().find(LOAD_STAGES_EJBQL)), fieldList);
      }
    });
    stagesList.getModel().load();
  }

  public StageAction getStageAction() {
    return ((DefaultEntityListTableModel<StageAction>) stagesList.getModel()).getSelectedEntities()[0];
  }
}
