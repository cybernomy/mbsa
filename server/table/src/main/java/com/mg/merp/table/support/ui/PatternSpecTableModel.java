/*
 * PatternSpecTableModel.java
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
package com.mg.merp.table.support.ui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.ui.Color;
import com.mg.framework.api.ui.widget.TableCellRenderParameters;
import com.mg.framework.generic.ui.AbstractTableModel;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.table.model.PatternHead;
import com.mg.merp.table.model.PatternSpec;
import com.mg.merp.table.model.TimeKind;
import com.mg.merp.table.support.TimeBoardHelper;

/**
 * ћодель таблицы "—пецификаци€ шаблона"
 * 
 * @author Artem V. Sharapov
 * @version $Id: PatternSpecTableModel.java,v 1.1 2008/08/12 14:38:08 sharapov Exp $
 */
public class PatternSpecTableModel extends AbstractTableModel {

	private String[] columnNames = null;
	private Integer columnCount = 0;
	private int timeKindsSize = 0;
	private int[] selectedRows = null;
	private int[] columnRenders = null;
	private List<PatternSpec[]> tableModelItemList = new ArrayList<PatternSpec[]>();
	private PersistentManager persistentManager = ServerUtils.getPersistentManager();

	public PatternSpecTableModel() {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return columnCount;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#getColumnName(int)
	 */
	public String getColumnName(int column) {
		return columnNames[column];
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return tableModelItemList.size();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractTableModel#setSelectedRows(int[])
	 */
	@Override
	public void setSelectedRows(int[] rows) {
		selectedRows = rows;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int row, int column) {
		PatternSpec specItem = getCurrentModelItem(row, column);
		switch (column) {
		case 0: 
			return specItem.getTimeKind().getCode().trim();
		default: 
			if (specItem.getHoursQuantity() != null) {
				if(MathUtils.compareToZero(specItem.getHoursQuantity()) != 0)
					return specItem.getHoursQuantity();
				else
					return StringUtils.EMPTY_STRING;
			} else
				return specItem.getTimeKind().getCode().trim();
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractTableModel#getCellRenderParameters(com.mg.framework.api.ui.widget.TableCellRenderParameters, int, int, boolean, boolean)
	 */
	@Override
	public boolean getCellRenderParameters(TableCellRenderParameters params, int column, int row, boolean isSelected, boolean hasFocus) {
		if(column == 0) {
			params.setBackground(TimeBoardHelper.FIXED_COLUMN_BACKGROUND_COLOR);
			return true;
		} else {
			PatternSpec modelItem = getCurrentModelItem(row, column);
			if(row == 0) {
				params.setBackground(new Color(modelItem.getTimeKind().getBackGroundColor()));
				params.setForeground(new Color(modelItem.getTimeKind().getFontColor()));
				return true;
			} else if(MathUtils.compareToZeroOrNull(modelItem.getHoursQuantity()) != 0) {
				params.setBackground(new Color(modelItem.getTimeKind().getBackGroundColor()));
				params.setForeground(new Color(modelItem.getTimeKind().getFontColor()));
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractTableModel#getCustomRenderColumns()
	 */
	@Override
	public int[] getCustomRenderColumns() {
		return columnRenders;
	}

	/**
	 * ”становить тип времени
	 * @param timeKind - тип времени
	 * @param columns - список колонок дл€ которых нужно установить
	 */
	public void setTimeKind(TimeKind timeKind, int[] columns) {
		if(columns == null || columns.length == 0 || selectedRows == null || selectedRows.length == 0)
			return;
		else {
			int row = 0;
			int col = columns[0];
			PatternSpec specItem = tableModelItemList.get(row)[col];
			specItem.setTimeKind(timeKind);
			specItem.setHoursQuantity(null);
			fireTableCellUpdated(row, col);
		}
	}

	/**
	 * ”становить количество часов
	 * @param hours - кол-во часов
	 * @param columns - список колонок дл€ которых нужно установить
	 */
	public void setHours(BigDecimal hours, int[] columns) {
		int row = selectedRows[0];
		int col = columns[0];
		PatternSpec modelItem = getCurrentModelItem(row, col);
		modelItem.setHoursQuantity(hours == null ? BigDecimal.ZERO : hours);
		modelItem.setTimeKind(tableModelItemList.get(row)[0].getTimeKind());
		fireTableCellUpdated(row, col);
	}

	/**
	 * ѕолучить количество часов
	 * @param columns - список колонок дл€ которых нужно получить
	 * @return количество часов
	 */
	public BigDecimal getHours(int[] columns) {
		int row = selectedRows[0];
		int col = columns[0];
		BigDecimal hours = getCurrentModelItem(row, col).getHoursQuantity();
		return hours == null ? BigDecimal.ZERO : hours;
	}

	/**
	 * «аполнить грид спецификации
	 */
	public void fillGrid(PatternHead patternHead, List<PatternSpec> patternSpecs, List<TimeKind> timeKinds) {
		if(patternHead.getId() == null)
			return;

		prepareGrid(patternHead.getDuration(), timeKinds);
		for(int i = 0; i < timeKindsSize; i++) {
			TimeKind timeKind = timeKinds.get(i);
			PatternSpec[] specInfos = new PatternSpec[columnCount];
			for(int j = 0; j < columnCount; j++) {
				PatternSpec patternSpec = findPatternSpec(timeKind, j, patternSpecs);
				if(i == 0 && patternSpec == null)
					patternSpec = findPatternSpecDaily(j, patternSpecs);

				if(patternSpec != null) {
					patternSpec.setTimeKind(getTimeKind(patternSpec));
					specInfos[j] = patternSpec;
				} else {
					patternSpec = new PatternSpec(); 
					patternSpec.setTimeKind(timeKind);
					patternSpec.setDayNumber(j);
					patternSpec.setHoursQuantity(BigDecimal.ZERO);
					patternSpec.setPatternHead(patternHead);
					specInfos[j] = patternSpec; 
				}
			}
			tableModelItemList.add(specInfos);
		}
		fireTableStructureChanged();
	}

	private void prepareGrid(Integer duration, List<TimeKind> timeKinds) {
		tableModelItemList.clear();
		columnCount = duration + 1;
		timeKindsSize = timeKinds.size();
		initColumnsMetadata();
	}

	private void initColumnsMetadata() {
		columnNames = new String[columnCount];
		columnRenders = new int[columnCount];
		for(Integer i = 0; i < columnCount; i++) {
			if(i > 0)
				columnNames[i] = i.toString();
			columnRenders[i] = i;
		}
	}

	private PatternSpec findPatternSpec(TimeKind timeKind, Integer dayNumber, List<PatternSpec> patternSpecs) {
		PatternSpec patternSpec = null;
		Iterator<PatternSpec> iterator = patternSpecs.iterator();
		while(iterator.hasNext()) {
			PatternSpec spec = iterator.next();
			if(timeKind.getId().equals(spec.getTimeKind().getId()) && dayNumber.equals(spec.getDayNumber())) {
				patternSpec = spec;
				iterator.remove();
				break;
			}
		}
		return patternSpec;
	}

	private PatternSpec findPatternSpecDaily(Integer dayNumber, List<PatternSpec> patternSpecs) {
		PatternSpec patternSpec = null;
		Iterator<PatternSpec> iterator = patternSpecs.iterator();
		while(iterator.hasNext()) {
			PatternSpec spec = iterator.next();
			if(spec.getHoursQuantity() == null && dayNumber.equals(spec.getDayNumber())) {
				patternSpec = spec;
				iterator.remove();
				break;
			}
		}
		return patternSpec; 
	}

	protected PatternSpec getCurrentModelItem(int row, int column) {
		return tableModelItemList.get(row)[column];
	}

	private TimeKind getTimeKind(PatternSpec patternSpec) {
		return patternSpec == null ? null : persistentManager.find(TimeKind.class, patternSpec.getTimeKind().getId());
	}

	/**
	 * ѕолучить список позиций спецификации шаблона
	 * @return список позиций спецификации шаблона
	 */
	public List<PatternSpec[]> getTableModelItemList() {
		return this.tableModelItemList;
	}

}
