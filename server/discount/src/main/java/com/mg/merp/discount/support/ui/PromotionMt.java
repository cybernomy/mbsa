/*
 * PromotionMt.java
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
package com.mg.merp.discount.support.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.discount.PromotionLineServiceLocal;
import com.mg.merp.discount.model.PromotionLine;

/**
 * Контроллер формы поддержки "Рекламное мероприятие"
 * 
 * @author Artem V. Sharapov
 * @version $Id: PromotionMt.java,v 1.1 2007/10/30 14:15:02 sharapov Exp $
 */
public class PromotionMt extends DefaultMaintenanceForm implements MasterModelListener {

	private MaintenanceTableController promotionLineTable;
	private PromotionLineServiceLocal promotionLineService;
	protected AttributeMap promotionLineProperties = new LocalDataTransferObject();
	
	protected final String PROMOTION = "Promotion"; //$NON-NLS-1$


	public PromotionMt() throws Exception {
		setMasterDetail(true);
		addMasterModelListener(this);

		promotionLineService = (PromotionLineServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PromotionLineServiceLocal.SERVICE_NAME);
		promotionLineTable = new MaintenanceTableController(promotionLineProperties);
		promotionLineTable.initController(promotionLineService, new DefaultMaintenanceEJBQLTableModel() {

			private final String INIT_QUERY_TEXT = "select %s from PromotionLine pl %s where pl.Promotion = :Promotion"; //$NON-NLS-1$
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add(PROMOTION);
				paramsValue.add(getEntity());					
				return String.format(INIT_QUERY_TEXT, fieldsList, fromList);		
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
			 */
			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(PromotionLine.class, "Id", "pl.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PromotionLine.class, "CatalogFolder", "catFolder.FName", "left join pl.CatalogFolder catFolder", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(PromotionLine.class, "Catalog", "cat.Code", "left join pl.Catalog cat", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(PromotionLine.class, "Discount", "pl.Discount", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PromotionLine.class, "Price", "pl.Price", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PromotionLine.class, "IsActive", "pl.IsActive", false)); //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, promotionLineService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});
		addMasterModelListener(promotionLineTable);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		promotionLineProperties.put(PROMOTION, event.getEntity());
	}

}
