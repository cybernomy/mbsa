/*
 * TimeBoardSpecTableModel.java
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
import com.mg.merp.table.TimeBoardSpecServiceLocal;
import com.mg.merp.table.model.TimeBoardSpec;
import com.mg.merp.table.model.TimeKind;
import com.mg.merp.table.support.TimeBoardHelper;

/**
 * ������ ������� "������������ ������"
 * 
 * @author Artem V. Sharapov
 * @version $Id: TimeBoardSpecTableModel.java,v 1.3 2008/08/13 12:06:51 sharapov Exp $
 */
public class TimeBoardSpecTableModel extends AbstractTableModel {

	private String[] columnNames;
	private int[] columnRenders = null;
	private Integer columnCount = 0;
	private int[] selectedRows = null;
	private int timeKindsSize = 0;
	private boolean[][] cellModificationMap; 

	private List<TimeBoardSpec[]> tableModelItemList = new ArrayList<TimeBoardSpec[]>();
	private List<TimeBoardSpecItem> timeBoardSpecItems = new ArrayList<TimeBoardSpecItem>();

	private PersistentManager persistentManager = ServerUtils.getPersistentManager();
	protected TimeBoardSpecServiceLocal timeBoardSpecService = (TimeBoardSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(TimeBoardSpecServiceLocal.SERVICE_NAME);


	public TimeBoardSpecTableModel() {
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
		TimeBoardSpec specItem = getTableModelItem(row, column);
		switch (column) {
		case 0:
			if(row % timeKindsSize == 0)
				return timeBoardSpecItems.get(row / timeKindsSize).getCode();
			else
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
		} else if(!isGridCellEditable(row, column)) {
			params.setBackground(Color.GRAY);
			return true;
		} else {
			TimeBoardSpec modelItem = getTableModelItem(row, column);
			if(row % timeKindsSize == 0) {
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
	 * ���������� ��� �������
	 * @param timeKind - ��� �������
	 * @param columns - ������ ������� ��� ������� ����� ����������
	 */
	public void setTimeKind(TimeKind timeKind, int[] columns) {
		if(columns == null || columns.length == 0 || selectedRows == null || selectedRows.length == 0)
			return;
		else {
			int row = selectedRows[0];
			int col = columns[0];
			if(row % timeKindsSize != 0)
				row = row - (row % timeKindsSize);
			TimeBoardSpecItem timeBoardSpecItem = timeBoardSpecItems.get(row / timeKindsSize);
			TimeBoardSpec specItem = tableModelItemList.get(row)[col];
			if(DateTimeUtils.isDateBetweenNulable(specItem.getTimeBoardDate(), timeBoardSpecItem.getPositionBeginDate(), timeBoardSpecItem.getPositionEndDate())) {
				specItem.setTimeKind(timeKind);
				specItem.setHoursQuantity(null);
				updateSpec(specItem);
				fireTableCellUpdated(row, col);
			}
		}
	}

	/**
	 * ���������� ���������� �����
	 * @param hours - ���-�� �����
	 * @param columns - ������ ������� ��� ������� ����� ����������
	 */
	public void setHours(BigDecimal hours, int[] columns) {
		int row = selectedRows[0];
		int col = columns[0];
		TimeBoardSpec specItem = getTableModelItem(row, col);
		specItem.setHoursQuantity(hours == null ? BigDecimal.ZERO : hours);
		specItem.setTimeKind(getTableModelItem(row, 0).getTimeKind());

		if(MathUtils.compareToZeroOrNull(specItem.getHoursQuantity()) != 0)
			updateSpec(specItem);
		else
			eraseSpec(specItem);

		fireTableCellUpdated(row, col);
	}

	/**
	 * �������� ���������� �����
	 * @param columns - ������ ������� ��� ������� ����� ��������
	 * @return ���������� �����
	 */
	public BigDecimal getHours(int[] columns) {
		int row = selectedRows[0];
		int col = columns[0];
		BigDecimal hours = getTableModelItem(row, col).getHoursQuantity();
		return hours == null ? BigDecimal.ZERO : hours;
	}

	/**
	 * ��������� �������� ������������ ��������� ����������� ������
	 * @param column - ����������� ��������
	 * @return <code>true</code> ���� ��������� ����������� ������ ���������; �� ���� ��������� ������� <code>false</code> 
	 */
	public boolean isGridCellEditable(int column) {
		return isGridCellEditable(selectedRows[0], column);
	}

	protected boolean isGridCellEditable(int row, int column) {
		return cellModificationMap[row][column];
	}

	protected TimeBoardSpec getTableModelItem(int row, int column) {
		return tableModelItemList.get(row)[column];
	}

	/**
	 * ��������� ������� ������������
	 * @param specItem - ������� ������������
	 */
	private void updateSpec(TimeBoardSpec specItem) {
		if(specItem.getId() == null)
			timeBoardSpecService.create(specItem);
		else
			timeBoardSpecService.store(specItem);
	}

	/**
	 * ������� ������� ������������
	 * @param specItem - ������� ������������
	 */
	private void eraseSpec(TimeBoardSpec specItem) {
		timeBoardSpecService.erase(specItem);
	}

	/**
	 * ��������� ���� ������������
	 */
	public void fillGrid(Date dateFrom, Date dateTill, List<TimeBoardSpecItem> timeBoardSpecItems, List<TimeKind> timeKinds) {
		prepareGrid(dateFrom, dateTill, timeBoardSpecItems, timeKinds);
		for(int row = 0; row < timeBoardSpecItems.size(); row++) {
			TimeBoardSpecItem timeBoardSpecItem = timeBoardSpecItems.get(row);
			List<TimeBoardSpec> timeBoardSpecs = timeBoardSpecItem.getTimeBoardSpecs();
			for(int k = 0; k < timeKindsSize; k++) {
				TimeKind timeKind = timeKinds.get(k);
				TimeBoardSpec[] rowItems = new TimeBoardSpec[columnCount];
				for(int column = 0; column < columnCount; column++) {
					Date timeBoardDate = DateTimeUtils.incDay(dateFrom, column - 1);
					TimeBoardSpec item = findItem(k == 0, timeKind, timeBoardDate, timeBoardSpecs);
					if(item != null)
						item.setTimeKind(getTimeKind(item));
					else {
						item = new TimeBoardSpec();
						item.setTimeKind(timeKind);
						item.setTimeBoardDate(timeBoardDate);
						item.setHoursQuantity(BigDecimal.ZERO);
						item.setTimeBoardPosition(timeBoardSpecItem.getTimeBoardPosition());
					}
					rowItems[column] = item;
				}
				tableModelItemList.add(rowItems);
			}
		}
		initCellModificationMap();
		fireTableStructureChanged();
	}

	private void initCellModificationMap() {
		cellModificationMap = new boolean[timeBoardSpecItems.size() * timeKindsSize][columnCount];
		for (int row = 0; row < tableModelItemList.size(); row++) {
			TimeBoardSpec[] timeBoardSpecs = tableModelItemList.get(row);
			for (int column = 0; column < timeBoardSpecs.length; column++) {
				TimeBoardSpecItem timeBoardSpecItem = timeBoardSpecItems.get(row / timeKindsSize);
				TimeBoardSpec specItem = tableModelItemList.get(row)[column];
				cellModificationMap[row][column] = column != 0 && DateTimeUtils.isDateBetweenNulable(specItem.getTimeBoardDate(), timeBoardSpecItem.getPositionBeginDate(), timeBoardSpecItem.getPositionEndDate());
			}
		}
	}

	private void prepareGrid(Date dateFrom, Date dateTill, List<TimeBoardSpecItem> timeBoardSpecItems, List<TimeKind> timeKinds) {
		this.timeBoardSpecItems = timeBoardSpecItems;
		if(timeKinds != null)
			timeKindsSize = timeKinds.size();
		tableModelItemList.clear();
		columnCount = ((int) DateTimeUtils.getDaysBetween(dateFrom, dateTill)) + 2;
		initColumnsMetadata(dateFrom);
	}

	private void initColumnsMetadata(Date dateFrom) {
		Date scheduleDate = dateFrom;
		columnNames = new String[columnCount];
		columnRenders = new int[columnCount];
		for(Integer column = 0; column < columnCount; column++) {
			if(column > 0) {
				columnNames[column] = String.valueOf(DateTimeUtils.getDayOfMonth(scheduleDate));
				scheduleDate = DateTimeUtils.incDay(scheduleDate, 1);
			}
			columnRenders[column] = column;
		}
	}
	
	private TimeBoardSpec findItem(boolean isSearchForWorkTime, TimeKind timeKind, Date date, List<TimeBoardSpec> specList) {
		TimeBoardSpec foundItem = null;
		boolean isFound = false;
		Iterator<TimeBoardSpec> iterator = specList.iterator();
		while(iterator.hasNext()) {
			TimeBoardSpec item = iterator.next();
			if(date.equals(item.getTimeBoardDate())) {
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

	private TimeKind getTimeKind(TimeBoardSpec scheduleSpec) {
		return scheduleSpec == null ? null : persistentManager.find(TimeKind.class, scheduleSpec.getTimeKind().getId());
	}

	/**
	 * �������� ������ ������� ������������
	 * @return ������ ������� ������������
	 */
	public List<TimeBoardSpec[]> getTableModelItemList() {
		return this.tableModelItemList;
	}

}
