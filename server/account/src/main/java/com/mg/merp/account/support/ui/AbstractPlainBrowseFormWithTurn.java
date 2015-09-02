/*
 * AbstractPlainBrowseFormWithTurn.java
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
package com.mg.merp.account.support.ui;

import java.io.Serializable;
import java.util.List;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.Widget;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.CheckBoxMenuItem;
import com.mg.framework.api.ui.widget.MaintenanceTable;
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.api.ui.widget.SplitPane;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.merp.account.OperationServiceLocal;

/**
 * ���������� �������� �������� � ������������ ��������
 * 
 * @author Artem V. Sharapov
 * @version $Id: AbstractPlainBrowseFormWithTurn.java,v 1.2 2009/03/17 08:43:43 sharapov Exp $
 */
public abstract class AbstractPlainBrowseFormWithTurn extends DefaultPlainBrowseForm {

	protected OperationServiceLocal operationService;
	protected boolean isShowTurn = false;

	protected AttributeMap turnDbProperties = new LocalDataTransferObject();
	protected MaintenanceTableController turnDb;

	protected AttributeMap turnKtProperties = new LocalDataTransferObject();
	protected MaintenanceTableController turnKt;

	private final String IS_SHOW_TURN_TABLES = "isShowTurnTables"; 
	private final String SPLIT_CONTAINER_NAME = "turnSplit";
	private final String VIEW_TURNS_MENUITEM = "viewTurns";
	private final String SPEC_TABLE_WIDGET = "turnFields";

	public AbstractPlainBrowseFormWithTurn() {
		super();
		operationService = (OperationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/Operation");

		turnDb = new MaintenanceTableController(turnDbProperties);
		turnKt = new MaintenanceTableController(turnKtProperties);

		table.addMasterModelListener(new MasterModelListener() {

			/* (non-Javadoc)
			 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
			 */
			public void masterChange(ModelChangeEvent event) {
				if (isShowTurn) {
					turnDb.fireMasterChange(event);
					turnKt.fireMasterChange(event);
				}
			}
		});
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultHierarchyBrowseForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		isShowTurn = view.getUIProfile().getProperty(IS_SHOW_TURN_TABLES, false);

		turnDb.initController(operationService, new AbstractTurnTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.merp.account.support.ui.AbstractTurnTableModel#doGetWhereText(java.util.List)
			 */
			@Override
			protected String doGetWhereText(List<String> paramsName) {
				return doGetTurnDbWhereText(paramsName);
			}
		});
		turnKt.initController(operationService, new AbstractTurnTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.merp.account.support.ui.AbstractTurnTableModel#doGetWhereText(java.util.List)
			 */
			@Override
			protected String doGetWhereText(List<String> paramsName) {
				return doGetTurnKtWhereText(paramsName);
			}
		});
		super.doOnRun();
		((CheckBoxMenuItem) view.getWidget(TABLE_WIDGET).getPopupMenu().getMenuItem(VIEW_TURNS_MENUITEM)).setSelected(isShowTurn);
		setVisibleSpec(isShowTurn);
		adjustPopupMenuOfTurnTables();
	}

	/**
	 * �������� where-����� ������ ������� ��� ������� �������� �� ������
	 * @param paramsName - ������ ���� ����������
	 * @return where-����� ������ ������� ��� ������� �������� �� ������
	 */
	protected abstract String doGetTurnDbWhereText(List<String> paramsName);
	
	/**
	 * �������� where-����� ������ ������� ��� ������� �������� �� �������
	 * @param paramsName - ������ ���� ����������
	 * @return where-����� ������ ������� ��� ������� �������� �� �������
	 */
	protected abstract String doGetTurnKtWhereText(List<String> paramsName);

	/**
	 * ��������� �� ������� ��������
	 */
	private void adjustPopupMenuOfTurnTables() {
		doAdjustTurnDbTablePopupMenu(view.getWidget("turnDb").getPopupMenu());
		doAdjustTurnDbTablePopupMenu(view.getWidget("turnKt").getPopupMenu());
	}

	/**
	 * ��������� �� ������ �������� �� ������
	 * @param popupMenu - �� ������ �������� �� ������
	 */
	protected void doAdjustTurnDbTablePopupMenu(PopupMenu popupMenu) {
		UIUtils.setVisibleEnabledProperty(popupMenu.getMenuItem(MaintenanceTable.ADD_MENU_ITEM), false);
	}

	/**
	 * ��������� �� ������ �������� �� �������
	 * @param popupMenu - �� ������ �������� �� �������
	 */
	protected void doAdjustTurnKtTablePopupMenu(PopupMenu popupMenu) {
		UIUtils.setVisibleEnabledProperty(popupMenu.getMenuItem(MaintenanceTable.ADD_MENU_ITEM), false);
	}

	/**
	 * ��������� �������� ��������� �������
	 * @param isVisible	- <code>true</code> - �����, ����� �� �����
	 */
	private void setVisibleSpec(boolean isVisible) {
		Widget widget = view.getWidget(SPEC_TABLE_WIDGET);
		if (widget != null)
			widget.setVisible(isVisible);		
		this.isShowTurn = isVisible;
		view.getUIProfile().setProperty(IS_SHOW_TURN_TABLES, isVisible);
		((SplitPane) view.getWidget(SPLIT_CONTAINER_NAME)).setDividerLocation(isVisible ? 62 : 100);
	}

	/**
	 * ���������� ������ �� "�������� �������"
	 * @param event - �������
	 */
	protected void onActionViewTurns(WidgetEvent event) {
		boolean selected = ((CheckBoxMenuItem) event.getWidget()).isSelected();
		if (isShowTurn != selected) {
			if (selected) {
				Serializable[] remnIds = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
				if (remnIds.length == 1) {
					Serializable remnId = remnIds[0];
					((MaintenanceTableModel) turnDb.getModel()).setCurrentMaster(remnId);
					turnDb.refresh();

					((MaintenanceTableModel) turnKt.getModel()).setCurrentMaster(remnId);
					turnKt.refresh();
				}
			}
			isShowTurn = selected;
			setVisibleSpec(isShowTurn);
		} 
	}

}