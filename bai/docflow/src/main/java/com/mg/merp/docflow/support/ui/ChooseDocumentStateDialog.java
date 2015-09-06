/*
 * ChooseDocumentStateDialog.java
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

import com.mg.framework.generic.ui.DefaultWizardDialog;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.merp.docprocess.model.DocHeadState;

import java.util.List;

/**
 * TODO i18n
 *
 * @author Oleg V. Safonov
 * @version $Id: ChooseDocumentStateDialog.java,v 1.5 2006/10/21 12:30:44 safonov Exp $
 */
public class ChooseDocumentStateDialog extends DefaultWizardDialog {
  private DefaultTableController statesList;
  private int stateId;

  public ChooseDocumentStateDialog() {
    statesList = new DefaultTableController(new StateListModel());
  }

  /**
   * @param statesList The statesList to set.
   */
  public void setStatesList(List<DocHeadState> statesList) {
    ((StateListModel) this.statesList.getModel()).setStatesModel(statesList);
  }

  /**
   * @return Returns the stateId.
   */
  public int getStateId() {
    return stateId;
  }

  private class StateListModel extends DocActionHistoryController {

    /* (non-Javadoc)
     * @see com.mg.framework.support.ui.widget.TableControllerAdapter#setCurrentRow(int)
     */
    public void setSelectedRows(int[] rows) {
      if (rows.length != 0)
        stateId = statesModel.get(rows[0]).getId();
      else
        stateId = 0;
    }

  }
}
