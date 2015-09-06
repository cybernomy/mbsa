/*
 * SolutionSearchSolutionTableModel.java
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
 *
 */
package com.mg.merp.crm.support.ui;

import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.crm.model.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Модель таблицы "Решения" Специализирована для диалога "Поиск решения"
 *
 * @author Artem V. Sharapov
 * @version $Id: SolutionSearchSolutionTableModel.java,v 1.1 2007/05/16 06:21:43 sharapov Exp $
 */
public class SolutionSearchSolutionTableModel extends DefaultEJBQLTableModel {

  private final String INIT_QUERY_TEXT = "select %s from Solution s join s.LinkProblemSolutions ls %s where ls.Id.CrmProblem = :problem"; //$NON-NLS-1$
  private List<String> solutionParamsName = new ArrayList<String>();
  private List<Object> solutionParamsValue = new ArrayList<Object>();


  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
   */
  @Override
  public void setSelectedRows(int[] rows) {
  }

  protected String createQueryText() {
    Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    return String.format(INIT_QUERY_TEXT, fieldsList, fromList);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
   */
  @Override
  protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
    Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
    result.add(new TableEJBQLFieldDef(Solution.class, "Id", "s.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(Solution.class, "Creator", "p.Surname", "left join s.Creator.Person as p", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    result.add(new TableEJBQLFieldDef(Solution.class, "Name", "s.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(Solution.class, "Info", "s.Info", false)); //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(Solution.class, "ValidFrom", "s.ValidFrom", false)); //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(Solution.class, "ValidTo", "s.ValidTo", false)); //$NON-NLS-1$ //$NON-NLS-2$
    return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, null);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
   */
  @Override
  protected void doLoad() {
    setQuery(createQueryText(), solutionParamsName.toArray(new String[solutionParamsName.size()]), solutionParamsValue.toArray(new Object[solutionParamsValue.size()]));
  }

  /**
   * Установить параметры модели таблицы
   *
   * @param solutionParamsName  - список имен параметров
   * @param solutionParamsValue - список значений параметров
   */
  public void setTableModelParams(List<String> solutionParamsName, List<Object> solutionParamsValue) {
    this.solutionParamsName = solutionParamsName;
    this.solutionParamsValue = solutionParamsValue;
  }

  /**
   * Очистить содержимое таблицы
   */
  public void resetTable() {
    rowList.clear();
    this.fireModelChange();
  }
}
