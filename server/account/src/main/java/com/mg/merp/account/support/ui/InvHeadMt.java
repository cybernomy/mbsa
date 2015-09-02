/*
 * InvHeadMt.java
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
import com.mg.merp.account.InvMetalServiceLocal;
import com.mg.merp.account.InvProductionServiceLocal;
import com.mg.merp.account.InventoryServiceLocal;
import com.mg.merp.account.model.InvMetal;
import com.mg.merp.account.model.InvProduction;
import com.mg.merp.account.model.Inventory;

/**
 * Контроллер формы формы поддержки инвентарной картотеки 
 * 
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: InvHeadMt.java,v 1.8 2008/03/31 12:52:22 alikaev Exp $
 */
public class InvHeadMt extends DefaultMaintenanceForm implements MasterModelListener {
	private MaintenanceTableController production;
	private InvProductionServiceLocal productionService;
	protected AttributeMap productionProperties = new LocalDataTransferObject();

	private MaintenanceTableController metal;
	private InvMetalServiceLocal metalService;
	protected AttributeMap metalProperties = new LocalDataTransferObject();

	private MaintenanceTableController inventory;
	private InventoryServiceLocal inventoryService;
	protected AttributeMap inventoryProperties = new LocalDataTransferObject();

	public InvHeadMt() throws Exception {
		setMasterDetail(true);

		productionService = (InvProductionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/InvProduction");
		production = new MaintenanceTableController(productionProperties);
		production.initController(productionService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from InvProduction ip where ip.AccInvHead = :invhead";
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();
			
			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("invhead");
				paramsValue.add(getEntity());					
				return String.format(INIT_QUERY_TEXT, fieldsList);		
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
				result.add(new TableEJBQLFieldDef(InvProduction.class, "Id", "ip.Id", true));				
				result.add(new TableEJBQLFieldDef(InvProduction.class, "QYear", "(ip.AMonth / 12) as qyear", false));
				result.add(new TableEJBQLFieldDef(InvProduction.class, "QMonth", "ip.QMonth", false));				
				result.add(new TableEJBQLFieldDef(InvProduction.class, "Production", "ip.Production", false));
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, productionService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				//throw new ApplicationException(createQueryText());
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});
		addMasterModelListener(production);
		
		metalService = (InvMetalServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/InvMetal");
		metal = new MaintenanceTableController(metalProperties);
		metal.initController(metalService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from InvMetal im %s where im.InvHead = :invhead";
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();
			
			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("invhead");
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
				result.add(new TableEJBQLFieldDef(InvMetal.class, "Id", "im.Id", true));
				result.add(new TableEJBQLFieldDef(InvMetal.class, "MetalCode", "im.MetalCode.Code", false));
				result.add(new TableEJBQLFieldDef(InvMetal.class, "Mass", "im.Mass", false));
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, metalService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});

		addMasterModelListener(metal);
		
		inventoryService = (InventoryServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/Inventory");
		inventory = new MaintenanceTableController(inventoryProperties);
		inventory.initController(inventoryService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from Inventory i %s where i.InvHead = :invhead";
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();
			
			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("invhead");
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
				result.add(new TableEJBQLFieldDef(Inventory.class, "Id", "i.Id", true));
				result.add(new TableEJBQLFieldDef(Inventory.class, "AccKind", "i.AccKind.Code", false));
				result.add(new TableEJBQLFieldDef(Inventory.class, "AccGroup", "ag.GCode", "left join i.AccGroup as ag", false));
				result.add(new TableEJBQLFieldDef(Inventory.class, "BalanceCost", "i.BalanceCost", false));
				result.add(new TableEJBQLFieldDef(Inventory.class, "BeginCost", "i.BeginCost", false));
				result.add(new TableEJBQLFieldDef(Inventory.class, "Initialloss", "i.Initialloss", false));		
				result.add(new TableEJBQLFieldDef(Inventory.class, "BeginLoss", "i.BeginLoss", false));
				result.add(new TableEJBQLFieldDef(Inventory.class, "BeginLossDate", "i.BeginLossDate", false));
				result.add(new TableEJBQLFieldDef(Inventory.class, "Amort", "i.Amort", false));
				result.add(new TableEJBQLFieldDef(Inventory.class, "EndCost", "i.EndCost", false));				
				result.add(new TableEJBQLFieldDef(Inventory.class, "AmortDate", "i.AmortDate", false));	
				result.add(new TableEJBQLFieldDef(Inventory.class, "AmCode", "am.Code", "left join i.AmCode as am", false));	
				result.add(new TableEJBQLFieldDef(Inventory.class, "Factor", "i.Factor", false));
				result.add(new TableEJBQLFieldDef(Inventory.class, "Algorithm", "i.Algorithm", false));	
				result.add(new TableEJBQLFieldDef(Inventory.class, "YearAmortRate", "i.YearAmortRate", false));
				result.add(new TableEJBQLFieldDef(Inventory.class, "ExplPeriodY", "i.ExplPeriodY", false));
				result.add(new TableEJBQLFieldDef(Inventory.class, "ExplPeriodM", "i.ExplPeriodM", false));
				result.add(new TableEJBQLFieldDef(Inventory.class, "AccDb", "db.Acc", "left join i.AccDb as db", false));
//				result.add(new TableEJBQLFieldDef(Inventory.class, "AnlDb1", "(db1.Code||','||db2.Code||','||db3.Code||','||db4.Code||','||db5.Code) as AnaliticDb", "left join i.AnlDb1 as db1 left join i.AnlDb2 as db2 left join i.AnlDb3 as db3 left join i.AnlDb4 as db4 left join i.AnlDb5 as db5", false));
				result.add(new TableEJBQLFieldDef(Inventory.class, "AccKt", "kt.Acc", "left join i.AccKt as kt", false));
//				result.add(new TableEJBQLFieldDef(Inventory.class, "AnlKt1", "(kt1.Code||','||kt2.Code||','||kt3.Code||','||kt4.Code||','||kt5.Code) as AnaliticKt", "left join i.AnlKt1 as kt1 left join i.AnlKt2 as kt2 left join i.AnlKt3 as kt3 left join i.AnlKt4 as kt4 left join i.AnlKt5 as kt5", false));
				result.add(new TableEJBQLFieldDef(Inventory.class, "AccPlan", "acc.Acc", "left join i.AccPlan as acc", false));
//				result.add(new TableEJBQLFieldDef(Inventory.class, "Anl1", "(anl1.Code||','||anl2.Code||','||anl3.Code||','||anl4.Code||','||anl5.Code) as Analitic", "left join i.Anl1 as anl1 left join i.Anl2 as anl2 left join i.Anl3 as anl3 left join i.Anl4 as anl4 left join i.Anl5 as anl5", false));				
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, inventoryService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});

		addMasterModelListener(inventory);	
		addMasterModelListener(this);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		productionProperties.put("AccInvHead", event.getEntity());
		metalProperties.put("InvHead", event.getEntity());		
		inventoryProperties.put("InvHead", event.getEntity());				
	}
	
}