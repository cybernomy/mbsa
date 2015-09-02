/*
 * ScheduleSpecTableModel.java
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
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.ui.Color;
import com.mg.framework.api.ui.widget.TableCellRenderParameters;
import com.mg.framework.generic.ui.AbstractTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.table.ScheduleSpecServiceLocal;
import com.mg.merp.table.model.ScheduleHead;
import com.mg.merp.table.model.ScheduleSpec;
import com.mg.merp.table.model.TimeKind;
import com.mg.merp.table.support.TimeBoardHelper;

/**
 * Модель таблицы "Спецификация графика работ в табельном учете"
 * 
 * @author Artem V. Sharapov
 * @version $Id: ScheduleSpecTableModel.java,v 1.3 2008/08/13 12:06:51 sharapov Exp $
 */
public class ScheduleSpecTableModel extends AbstractTableModel {

	private String[] columnNames;
	private List<ScheduleSpec[]> tableModelItemList = new ArrayList<ScheduleSpec[]>();
	private Integer columnCount = 0;
	private int[] selectedRows = null;
	private int[] columnRenders = null;
	private PersistentManager persistentManager = ServerUtils.getPersistentManager();
	protected ScheduleSpecServiceLocal scheduleSpecService = (ScheduleSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(ScheduleSpecServiceLocal.SERVICE_NAME);

	public ScheduleSpecTableModel() {
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
		TimeKind workTimeKind = getTableModelItem(row, 0).getTimeKind();
		ScheduleSpec modelItem = getTableModelItem(row, column);
		switch (column) {
		case 0: 
			return modelItem.getTimeKind().getCode().trim();
		default:
			if (modelItem.getTimeKind().getId().compareTo(workTimeKind.getId()) != 0)
				return modelItem.getTimeKind().getCode().trim();
			else if(MathUtils.compareToZeroOrNull(modelItem.getHoursQuantity()) != 0)
				return modelItem.getHoursQuantity();
			else
				return StringUtils.EMPTY_STRING;
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractTableModel#getCustomRenderColumns()
	 */
	@Override
	public int[] getCustomRenderColumns() {
		return columnRenders;
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
			ScheduleSpec modelItem = getTableModelItem(row, column);
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

	/**
	 * Установить тип времени
	 * @param timeKind - тип времени
	 * @param columns - список колонок для которых нужно установить
	 */
	public void setTimeKind(TimeKind timeKind, int[] columns) {
		if(columns == null || columns.length == 0 || selectedRows == null || selectedRows.length == 0)
			return;
		else {
			int row = selectedRows[0];
			int col = columns[0];
			if(row == 0) {
				ScheduleSpec specItem = tableModelItemList.get(row)[col];
				specItem.setTimeKind(timeKind);
				specItem.setHoursQuantity(null);
				updateSpec(specItem);
				fireTableCellUpdated(row, col);
			}
		}
	}

	/**
	 * Установить количество часов
	 * @param hours - кол-во часов
	 * @param columns - список колонок для которых нужно установить
	 */
	public void setHours(BigDecimal hours, int[] columns) {
		int row = selectedRows[0];
		int col = columns[0];
		ScheduleSpec modelItem = getTableModelItem(row, col);
		modelItem.setHoursQuantity(hours);
		modelItem.setTimeKind(tableModelItemList.get(row)[0].getTimeKind());

		if(MathUtils.compareToZeroOrNull(modelItem.getHoursQuantity()) != 0)
			updateSpec(modelItem);
		else
			eraseSpec(modelItem);

		fireTableCellUpdated(row, col);
	}

	/**
	 * Получить количество часов
	 * @param columns - список колонок для которых нужно получить
	 * @return количество часов
	 */
	public BigDecimal getHours(int[] columns) {
		int row = selectedRows[0];
		int col = columns[0];
		BigDecimal hours = getTableModelItem(row, col).getHoursQuantity();
		return hours == null ? BigDecimal.ZERO : hours;
	}

	protected ScheduleSpec getTableModelItem(int row, int column) {
		return tableModelItemList.get(row)[column];
	}

	/**
	 * Сохранить позицию спецификации
	 * @param specItem - позиция спецификации
	 */
	private void updateSpec(ScheduleSpec specItem) {
		if(specItem.getId() == null)
			scheduleSpecService.create(specItem);
		else
			scheduleSpecService.store(specItem);
	}

	/**
	 * Удалить позицию спецификации
	 * @param specItem - позиция спецификации
	 */
	private void eraseSpec(ScheduleSpec specItem) {
		scheduleSpecService.erase(specItem);
	}

	/**
	 * Заполнить грид спецификации
	 */
	public void fillGrid(ScheduleHead scheduleHead, Date dateFrom, Date dateTill, List<ScheduleSpec> scheduleSpecs, List<TimeKind> timeKinds) {
		if(scheduleHead.getId() == null)
			return;

		prepareGrid(dateFrom, dateTill);
		for(int row = 0; row < timeKinds.size(); row++) {
			TimeKind timeKind = timeKinds.get(row);
			ScheduleSpec[] rowItems = new ScheduleSpec[columnCount];
			for(int column = 0; column < columnCount; column++) {
				Date scheduleDate = DateTimeUtils.incDay(dateFrom, column - 1);
				ScheduleSpec item = findItem(row == 0, timeKind, scheduleDate, scheduleSpecs);
				if(item != null)
					item.setTimeKind(getTimeKind(item));
				else {
					item = new ScheduleSpec(); 
					item.setTimeKind(timeKind);
					item.setScheduleDate(scheduleDate);
					item.setHoursQuantity(BigDecimal.ZERO);
					item.setScheduleHead(scheduleHead);
				}
				rowItems[column] = item;
			}
			tableModelItemList.add(rowItems);
		}
		fireTableStructureChanged();
	}

	private void prepareGrid(Date dateFrom, Date dateTill) {
		tableModelItemList.clear();
		columnCount = ((int) DateTimeUtils.getDaysBetween(dateFrom, dateTill)) + 2;
		initColumnsMetadata(dateFrom);
	}

	private void initColumnsMetadata(Date dateFrom) {
		Date scheduleDate = dateFrom;
		columnNames = new String[columnCount];
		columnRenders = new int[columnCount];
		for(int column = 0; column < columnCount; column++) {
			if(column > 0) {
				columnNames[column] = String.valueOf(DateTimeUtils.getDayOfMonth(scheduleDate));
				scheduleDate = DateTimeUtils.incDay(scheduleDate, 1);
			}
			columnRenders[column] = column;
		}
	}

	private ScheduleSpec findItem(boolean isSearchForWorkTime, TimeKind timeKind, Date date, List<ScheduleSpec> specList) {
		ScheduleSpec foundItem = null;
		boolean isFound = false;
		Iterator<ScheduleSpec> iterator = specList.iterator();
		while(iterator.hasNext()) {
			ScheduleSpec item = iterator.next();
			if(date.equals(item.getScheduleDate())) {
				if(isSearchForWorkTime) {
					boolean isHoursZeroOrNull = MathUtils.compareToZeroOrNull(item.getHoursQuantity()) == 0;
					if((!isHoursZeroOrNull && timeKind.getId().equals(item.getTimeKind().getId())) || (isHoursZeroOrNull && !timeKind.getId().equals(item.getTimeKind().getId())))
						isFound = true;
				} else if(timeKind.getId().equals(item.getTimeKind().getId()))
					isFound = true;
			}
			if(isFound) {
				foundItem = item;
				iterator.remove();
				break;
			}
		}
		return foundItem;
	}

	private TimeKind getTimeKind(ScheduleSpec scheduleSpec) {
		return scheduleSpec == null ? null : persistentManager.find(TimeKind.class, scheduleSpec.getTimeKind().getId());
	}

	/**
	 * Получить список позиций спецификации шаблона
	 * @return список позиций спецификации шаблона
	 */
	public List<ScheduleSpec[]> getTableModelItemList() {
		return this.tableModelItemList;
	}

}
