/*
 * DocActionHistoryController.java
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
package com.mg.merp.docflow.support.ui;

import com.mg.framework.api.Logger;
import com.mg.framework.generic.ui.AbstractTableModel;
import com.mg.framework.support.ui.widget.TableColumnModel;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.docflow.DocFlowManager;
import com.mg.merp.docflow.DocFlowPlugin;
import com.mg.merp.docflow.DocFlowPluginFactory;
import com.mg.merp.docflow.PluginNotImplementedException;
import com.mg.merp.docflow.support.DocFlowPluginFactoryManagerServiceLocator;
import com.mg.merp.docprocess.model.DocHeadState;

import java.text.DateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Модель для отображения истории выполнения этапа ДО
 *
 * @author Oleg V. Safonov
 * @version $Id: DocActionHistoryController.java,v 1.4 2008/04/18 08:41:52 safonov Exp $
 */
public class DocActionHistoryController extends AbstractTableModel {
  protected List<DocHeadState> statesModel;
  private Logger logger = ServerUtils.getLogger(this.getClass());
  private String[] columnsName;
  private Map<Integer, DocFlowPlugin> pluginMap = new HashMap<Integer, DocFlowPlugin>();

  public DocFlowPlugin getDocFlowPlugin(DocHeadState docHeadState) {
    //если Stage == null то это не этап ДО
    if (docHeadState.getDocAction().getStage() == null)
      return null;

    DocFlowPlugin result = pluginMap.get(docHeadState.getId());
    if (result == null) {
      try {
        int pluginId = docHeadState.getDocAction().getStage().getStage().getId();
        if (pluginId != DocFlowManager.DOC_FLOW_CREATE_DOC && pluginId != DocFlowManager.DOC_FLOW_BASED_ON) {
          DocFlowPluginFactory factory = DocFlowPluginFactoryManagerServiceLocator.locate().findPluginFactory(pluginId);
          result = factory.createPlugin();
          pluginMap.put(docHeadState.getId(), result);
        }
      } catch (PluginNotImplementedException e) {
        logger.debug("Plug-in implementation not found", e);
        //если нет реализации то ничего не далаем
      }
    }
    return result;
  }

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
   * @see com.mg.framework.support.ui.widget.TableModel#getColumnCount()
   */
  public int getColumnCount() {
    return columnsName.length;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.support.ui.widget.TableModel#getColumnName(int)
   */
  public String getColumnName(int column) {
    return columnsName[column];
  }

  /* (non-Javadoc)
   * @see com.mg.framework.support.ui.widget.TableModel#getRowCount()
   */
  public int getRowCount() {
    return statesModel != null ? statesModel.size() : 0;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.support.ui.widget.TableModel#getValueAt(int, int)
   */
  public Object getValueAt(int row, int column) {
    DocHeadState item = statesModel.get(row);
    switch (column) {
      case 0:
        return item.getReadySum();
      case 1:
        return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, ServerUtils.getUserLocale()).format(item.getDateTime());
      case 2:
        return item.getUser().getName();
      case 3:
        DocFlowPlugin plugin = getDocFlowPlugin(item);
        if (plugin != null)
          return plugin.getDocActionResultTextRepresentation(item);
        else
          return StringUtils.BLANK_STRING;
      default:
        return StringUtils.BLANK_STRING;
    }
  }

  public void setStatesModel(List<DocHeadState> statesModel) {
    this.statesModel = statesModel;
  }

}
