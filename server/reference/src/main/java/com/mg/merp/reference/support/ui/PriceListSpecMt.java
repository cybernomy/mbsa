/*
 * PriceListSpecMt.java
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
package com.mg.merp.reference.support.ui;

import java.math.BigDecimal;
import java.util.List;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultEntityListTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.merp.reference.model.PriceListSpec;
import com.mg.merp.reference.model.PriceListSpecPrice;

/**
 * Контроллер формы поддержки "Спецификации прайс-листов"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PriceListSpecMt.java,v 1.7 2008/06/07 12:22:48 sharapov Exp $
 */
public class PriceListSpecMt extends DefaultMaintenanceForm implements MasterModelListener {

	protected DefaultTableController priceSpecTypeLink;
	protected AttributeMap priceSpecTypeLinkProperties = new LocalDataTransferObject();

	private List<PriceListSpecPrice> specPrices;
	private final String PRICE_TYPE_CODE_COLUMN = "Id.PriceType.Code"; //$NON-NLS-1$
	private final String PRICE_COLUMN = "Price"; //$NON-NLS-1$

	public PriceListSpecMt() throws Exception {		
		setMasterDetail(true);
		addMasterModelListener(this);

		priceSpecTypeLink = new DefaultTableController(new DefaultEntityListTableModel<PriceListSpecPrice>() {

			/*
			 * (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected void doLoad() {
				setEntityList(specPrices, new String[] {PRICE_TYPE_CODE_COLUMN, PRICE_COLUMN});
				fireModelChange();
			}

			/*
			 * (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#isCellEditable(int, int)
			 */
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column == 1)
					return true;
				else
					return false;
			}

			/*
			 * (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#setValueAt(java.lang.Object, int, int)
			 */
			@Override
			public void setValueAt(Object value, int row, int column) {
				PriceListSpecPrice specPrice = entityList.get(row);
				switch (column) {
					case 1: 
						specPrice.setPrice((BigDecimal) value);
						break;
				}
			}

			/*
			 * (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEntityListTableModel#getRowCount()
			 */
			@Override
			public int getRowCount() {
				if(entityList != null)
					return entityList.size();
				else
					return 0;
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#getColumnClass(int)
			 */
			@Override
			public Class<?> getColumnClass(int column) {
				switch(column) {
				case 1:
					return BigDecimal.class;
				default:
					return super.getColumnClass(column); 
				}
			}
		});
		priceSpecTypeLink.getModel().load();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		refreshSpecPriceTable();
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doSave(com.mg.framework.api.ui.WidgetEvent)
	 */
	@Override
	protected void doSave(WidgetEvent event) {
		super.doSave(event);
		if(specPrices == null) {
			refreshModel();
			refreshSpecPriceTable();
		}
	}

	private void refreshSpecPriceTable() {
		specPrices = ((PriceListSpec) getEntity()).getPriceListSpecPrice();
		priceSpecTypeLink.getModel().load();
	}

}
