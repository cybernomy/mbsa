/*
 * TaxGroupMt.java
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
package com.mg.merp.reference.support.ui;

import java.util.Set;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpForm;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.generic.ui.DefaultCompoundMaintenanceForm;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.Dialogs;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.reference.TaxGroupServiceLocal;
import com.mg.merp.reference.TaxServiceLocal;
import com.mg.merp.reference.model.Tax;
import com.mg.merp.reference.model.TaxGroup;
import com.mg.merp.reference.model.TaxLink;
import com.mg.merp.reference.support.Messages;

/**
 * Стандартная форма поддержки бизнес-компонента "Группы налогов"
 * 
 * @author Oleg V. Safonov
 * @author leonova
 * @version $Id: TaxGroupMt.java,v 1.9 2008/02/12 09:10:12 safonov Exp $
 */
public class TaxGroupMt extends DefaultCompoundMaintenanceForm implements MasterModelListener {
	private final static String LOAD_TAXLINK_EJBQL = "select tl.Id, tl.Tax.Code, tl.Tax.TName, tl.FeeOrder from TaxLink tl where tl.TaxGroup = :taxGroup";
	protected DefaultTableController taxesTable;
	private Integer taxLink;
	private Short feeOrder;
	
	public TaxGroupMt() throws Exception {
		//taxesModel = new TaxesModel();
		taxesTable = new DefaultTableController(new DefaultEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(TaxLink.class, "Id", "tl.Id", true));
				result.add(new TableEJBQLFieldDef(TaxLink.class, "Tax.Code", "tl.Tax.Code", true));
				result.add(new TableEJBQLFieldDef(TaxLink.class, "Tax.TName", "tl.Tax.TName", true));
				result.add(new TableEJBQLFieldDef(TaxLink.class, "FeeOrder", "tl.FeeOrder", true));
				return result;
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
			 */
			@Override
			public void setSelectedRows(int[] rows) {
				if (rows.length == 0)
					taxLink = null;
				else {
					Object[] row = getRowList().get(rows[0]);
					taxLink = (Integer) row[0];
					feeOrder = (Short) row[3];
				}
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected void doLoad() {
				setQuery(LOAD_TAXLINK_EJBQL, new String[] {"taxGroup"}, new Object[] {getEntity()});
			}
			
		});
		
		addMasterModelListener(this);
	}

	private TaxGroupServiceLocal getTaxGroupService() {
		return (TaxGroupServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/TaxGroup");
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doSetDependentReadOnly(boolean)
	 */
	@Override
	protected void doSetDependentReadOnly(boolean readOnly) {
		super.doSetDependentReadOnly(readOnly);
		PopupMenu menu = view.getWidget("taxesTable").getPopupMenu();
		menu.getMenuItem("includeTaxItem").setEnabled(!readOnly);
		menu.getMenuItem("excludeTaxItem").setEnabled(!readOnly);
		menu.getMenuItem("changeTaxItem").setEnabled(!readOnly);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		taxesTable.getModel().load();
	}

	public void onActionIncludeTax(WidgetEvent event) throws ApplicationException {
		final TaxServiceLocal service = (TaxServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Tax");
		SearchHelpForm form = (SearchHelpForm) ApplicationDictionaryLocator.locate().getBrowseForm(service, null);
		form.addSearchHelpListener(new SearchHelpListener() {

			public void searchPerformed(SearchHelpEvent event) {
				final Tax tax = ((Tax) event.getItems()[0]);
				Dialogs.inputQuery(Messages.getInstance().getMessage(Messages.TAXLINK_INPUT_ORDER), Messages.getInstance().getMessage(Messages.TAXLINK_ORDER), new Short((short) 1), new Dialogs.InputQueryDialogListener<Short>() {

					public void inputPerformed(Short value) {
						getTaxGroupService().includeTax((TaxGroup) getEntity(), tax, value);
						taxesTable.getModel().load();
						//TaxGroupServiceLocal groupService = (TaxGroupServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/TaxGroup");
						//taxesModel.insertLink(groupService.includeTax(group, tax, value));
					}

					public void inputCanceled() {
					}
					
				});
			}

			public void searchCanceled(SearchHelpEvent event) {
				//do nothing
			}

		});
		form.run();
	}
	
	public void onActionExcludeTax(WidgetEvent event) throws ApplicationException {
		if (taxLink == null)
			return;
		getTaxGroupService().excludeTax(ServerUtils.getPersistentManager().find(TaxLink.class, taxLink));
		taxesTable.getModel().load();
	}
	
	public void onActionChangeFeeOrder(WidgetEvent event) throws ApplicationException {
		if (taxLink == null)
			return;
		Dialogs.inputQuery(Messages.getInstance().getMessage(Messages.TAXLINK_INPUT_ORDER), Messages.getInstance().getMessage(Messages.TAXLINK_ORDER), feeOrder, new Dialogs.InputQueryDialogListener<Short>() {

			public void inputPerformed(Short value) {
				TaxGroupServiceLocal group = (TaxGroupServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/TaxGroup");
				group.editTax(ServerUtils.getPersistentManager().find(TaxLink.class, taxLink), value);
				taxesTable.getModel().load();
			}

			public void inputCanceled() {
			}
			
		});
	}
}
