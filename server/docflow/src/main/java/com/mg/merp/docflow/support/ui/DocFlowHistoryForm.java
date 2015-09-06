/*
 * DocFlowHistoryForm.java
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
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractForm;
import com.mg.framework.generic.ui.AbstractTableModel;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableColumnModel;
import com.mg.framework.utils.MiscUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.docflow.DocFlowPlugin;
import com.mg.merp.docflow.support.DocumentUtils;
import com.mg.merp.docflow.support.Messages;
import com.mg.merp.docprocess.model.DocHeadState;
import com.mg.merp.document.model.DocHead;

import java.io.Serializable;
import java.util.List;

/**
 * Форма истории выполнения ДО
 *
 * @author Oleg V. Safonov
 * @version $Id: DocFlowHistoryForm.java,v 1.11 2006/12/12 14:54:37 safonov Exp $
 */
public class DocFlowHistoryForm extends AbstractForm {
  private final static String LOAD_DOCUMENT_HISTORY_EJBQL = "select new com.mg.merp.docflow.support.ui.DocHistory(da.Id, da.ActionType, stdSt.Name, st.Name, da.StageState, (select sum(dhs.ReadySum) from da.DocHeadStates dhs)) from DocAction da left join da.Stage st left join st.Stage stdSt where da.DocHead.Id = :docId order by da";
  private final static String LOAD_DOCHEAD_STATE_BROWSE_EJBQL = "from DocHeadState dhs where dhs.DocAction.Id = :docActionId order by dhs.Id";
  protected DefaultTableController docHistory;
  protected DefaultTableController docActionHistory;
  private DocHeadState currentDocHeadState;

  public DocFlowHistoryForm() {
    this.docHistory = new DefaultTableController(new DocHistoryController());
    this.docActionHistory = new DefaultTableController(new DocHeadStateController());
  }

  private List<DocHeadState> loadDocHeadStateBrowse(Integer actionId) {
    List<DocHeadState> result = MiscUtils.convertUncheckedList(DocHeadState.class, OrmTemplate.getInstance().findByNamedParam(LOAD_DOCHEAD_STATE_BROWSE_EJBQL, "docActionId", actionId));
    //stupid approach for resolve lazy initialize exception
    for (DocHeadState docHeadState : result) {
      docHeadState.getUser().getName();
      if (docHeadState.getDocAction().getStage() != null)
        docHeadState.getDocAction().getStage().getStage().getId();
    }
    return result;
  }

  /**
   * Обработчик события "Закрыть форму"
   */
  public void onActionOk(WidgetEvent event) {
    close();
  }

  /**
   * обработчик события "Показать результат выполнения ДО"
   */
  public void onActionShowDocActionResult(WidgetEvent event) {
    DocFlowPlugin plugin = ((DocHeadStateController) docActionHistory.getModel()).getDocFlowPlugin(currentDocHeadState);
    if (plugin != null)
      plugin.showDocActionResult(currentDocHeadState);
  }

  /**
   * показать форму
   */
  public void execute(Serializable documentId) {
    DocHead docHead = ServerUtils.getPersistentManager().find(DocHead.class, documentId);
    setTitle(String.format(ServerUtils.getUserLocale(), Messages.getInstance().getMessage(Messages.DOCFLOW_HISTORY_TITLE), DocumentUtils.generateDocumentTitle(docHead)));

    ((DocHistoryController) docHistory.getModel()).docHistoryModel = MiscUtils.convertUncheckedList(DocHistory.class, OrmTemplate.getInstance().findByNamedParam(LOAD_DOCUMENT_HISTORY_EJBQL, "docId", documentId));

    run(UIUtils.isModalMode());
  }

  private class DocHistoryController extends AbstractTableModel {
    private List<DocHistory> docHistoryModel;
    private String[] columnsName;

    /* (non-Javadoc)
     * @see com.mg.framework.generic.ui.AbstractTableModel#doSetColumns(com.mg.framework.support.ui.widget.TableColumnModel[])
     */
    @Override
    protected void doSetColumns(TableColumnModel[] columns) {
      this.columnsName = new String[columns.length];
      for (int i = 0; i < columns.length; i++)
        this.columnsName[i] = columns[i].getTitle();
    }

    /* (non-Javadoc)
     * @see com.mg.framework.support.ui.widget.TableControllerAdapter#getColumnName(int)
     */
    public String getColumnName(int column) {
      return columnsName[column];
    }

    /* (non-Javadoc)
     * @see com.mg.framework.support.ui.widget.TableControllerAdapter#getValueAt(int, int)
     */
    public Object getValueAt(int row, int column) {
      DocHistory history = docHistoryModel.get(row);
      switch (column) {
        case 0:
          return MiscUtils.getEnumTextRepresentation(history.getActionType());
        case 1:
          return history.getName();
        case 2:
          return MiscUtils.getEnumTextRepresentation(history.getStageState());
        case 3:
          return history.getReadyAmount();
        default:
          return "";
      }
    }

    /* (non-Javadoc)
     * @see com.mg.framework.support.ui.widget.TableControllerAdapter#getColumnCount()
     */
    public int getColumnCount() {
      return columnsName.length;
    }

    /* (non-Javadoc)
     * @see com.mg.framework.support.ui.widget.TableControllerAdapter#getRowCount()
     */
    public int getRowCount() {
      return docHistoryModel.size();
    }

    /* (non-Javadoc)
     * @see com.mg.framework.support.ui.widget.TableControllerAdapter#setCurrentRow(int)
     */
    public void setSelectedRows(int[] rows) {
      if (rows.length != 0) {
        DocHeadStateController history = (DocHeadStateController) docActionHistory.getModel();
        history.setStatesModel(loadDocHeadStateBrowse(docHistoryModel.get(rows[0]).getId()));
        history.fireModelChange();
      }
    }

  }

  private class DocHeadStateController extends DocActionHistoryController {

    /* (non-Javadoc)
     * @see com.mg.framework.generic.ui.AbstractTableModel#setSelectedRows(int[])
     */
    @Override
    public void setSelectedRows(int[] rows) {
      if (rows.length != 0) {
        currentDocHeadState = statesModel.get(rows[0]);
      }
    }

  }
}
