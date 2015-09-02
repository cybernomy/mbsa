/*
 * PriceListHeadMt.java
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MaintenanceAction;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.reference.PricelistPricetypeLinkServiceLocal;
import com.mg.merp.reference.model.PriceListFolder;
import com.mg.merp.reference.model.PriceListHead;
import com.mg.merp.reference.model.PriceListPriceTypeLink;

/**
 * @author leonova
 * @version $Id: PriceListHeadMt.java,v 1.5 2007/01/23 07:22:21 sharapov Exp $
 */
public class PriceListHeadMt extends DefaultMaintenanceForm implements MasterModelListener {

	private MaintenanceTableController priceTypeLink;
	private PricelistPricetypeLinkServiceLocal priceTypeLinkService;
	protected AttributeMap priceTypeLinkProperties = new LocalDataTransferObject();
	
	@Override
	protected void doSave(WidgetEvent event) {
		super.doSave(event);		
		if (getAction() == MaintenanceAction.ADD) {
			PriceListFolder result = new PriceListFolder();		
			result.setFName((String)getEntity().getAttribute("PrName"));
			result.setPriceListHead((PriceListHead)getEntity());
			result.setSysClient(new SysClient(1));
			ServerUtils.getPersistentManager().persist(result);		
		}			
	}

	public PriceListHeadMt() throws Exception {
		setMasterDetail(true);
		
		priceTypeLinkService = (PricelistPricetypeLinkServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/PricelistPricetypeLink");
		priceTypeLink  = new MaintenanceTableController(priceTypeLinkProperties);
		priceTypeLink.initController(priceTypeLinkService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from PriceListPriceTypeLink pt %s where pt.PriceListHead = :priceListHead";
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();
			
			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("priceListHead");
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
				result.add(new TableEJBQLFieldDef(PriceListPriceTypeLink.class, "Id", "pt.Id", true));
				result.add(new TableEJBQLFieldDef(PriceListPriceTypeLink.class, "PriceType", "pt.PriceType.Code", false));				
				result.add(new TableEJBQLFieldDef(PriceListPriceTypeLink.class, "Priority", "pt.Priority", false));
				result.add(new TableEJBQLFieldDef(PriceListPriceTypeLink.class, "AlgRepository", "alg.Code", "left join pt.AlgRepository as alg", false));				
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, priceTypeLinkService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});
		addMasterModelListener(priceTypeLink);
		
		addMasterModelListener(this);
	}
	
	public void masterChange(ModelChangeEvent event) {
		priceTypeLinkProperties.put("PriceListHead", event.getEntity());
	}

}