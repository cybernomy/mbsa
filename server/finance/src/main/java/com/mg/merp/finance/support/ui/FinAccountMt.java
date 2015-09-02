/*
 * FinAccountMt.java
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
package com.mg.merp.finance.support.ui;

import java.util.Set;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.finance.AnalyticsServiceLocal;

/**
 * @author leonova
 * @version $Id: FinAccountMt.java,v 1.5 2008/02/19 05:33:20 alikaev Exp $
 */
public class FinAccountMt extends DefaultMaintenanceForm implements MasterModelListener {
	private MaintenanceTableController analitFirst;
	private AnalyticsServiceLocal analitService;	
	protected AttributeMap analitFirstProperties = new LocalDataTransferObject();

	private MaintenanceTableController analitSecond;	
	protected AttributeMap analitSecondProperties = new LocalDataTransferObject();

	private MaintenanceTableController analitThird;	
	protected AttributeMap analitThirdProperties = new LocalDataTransferObject();

	private MaintenanceTableController analitFourth;	
	protected AttributeMap analitFourthProperties = new LocalDataTransferObject();

	private MaintenanceTableController analitFifth;	
	protected AttributeMap analitFifthProperties = new LocalDataTransferObject();
	
	public FinAccountMt() throws Exception {	
		setMasterDetail(true);
		analitService = (AnalyticsServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/finance/Analytics");
		
		analitFirstProperties.put("AnlLevel", 1);
		analitFirst = new MaintenanceTableController(analitFirstProperties);
		analitFirst.initController(analitService, new FinAnlMaintenanceEJBQLTableModel() {
			
			protected String createQueryText() {
				super.createQueryText();
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);				
				fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("finacc");
				paramsValue.add(getEntity());	
				paramsName.add("anlLevel");
				paramsValue.add((short)1);					
				return String.format(INIT_QUERY_TEXT, fieldsList, fromList);		
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
			 */
			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}
		});
		addMasterModelListener(analitFirst);

		analitSecondProperties.put("AnlLevel", 2);
		analitSecond = new MaintenanceTableController(analitSecondProperties);
		analitSecond.initController(analitService, new FinAnlMaintenanceEJBQLTableModel() {
			
			protected String createQueryText() {
				super.createQueryText();
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);				
				fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("finacc");
				paramsValue.add(getEntity());	
				paramsName.add("anlLevel");
				paramsValue.add((short)2);					
				return String.format(INIT_QUERY_TEXT, fieldsList, fromList);		
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
			 */
			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}
		});

		addMasterModelListener(analitSecond);

		analitThirdProperties.put("AnlLevel", 3);		
		analitThird = new MaintenanceTableController(analitThirdProperties);
		analitThird.initController(analitService, new FinAnlMaintenanceEJBQLTableModel() {
			
			protected String createQueryText() {
				super.createQueryText();
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);				
				fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("finacc");
				paramsValue.add(getEntity());	
				paramsName.add("anlLevel");
				paramsValue.add((short)3);					
				return String.format(INIT_QUERY_TEXT, fieldsList, fromList);		
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
			 */
			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}
		});

		addMasterModelListener(analitThird);
		
		analitFourthProperties.put("AnlLevel", 4);	
		analitFourth = new MaintenanceTableController(analitFourthProperties);
		analitFourth.initController(analitService, new FinAnlMaintenanceEJBQLTableModel() {
			
			protected String createQueryText() {
				super.createQueryText();
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);				
				fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("finacc");
				paramsValue.add(getEntity());
				paramsName.add("anlLevel");
				paramsValue.add((short)4);	
				return String.format(INIT_QUERY_TEXT, fieldsList, fromList);		
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
			 */
			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}
		});

		addMasterModelListener(analitFourth);
		
		analitFifthProperties.put("AnlLevel", 5);		
		analitFifth = new MaintenanceTableController(analitFifthProperties);
		analitFifth.initController(analitService, new FinAnlMaintenanceEJBQLTableModel() {
			
			protected String createQueryText() {
				super.createQueryText();
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);				
				fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("finacc");
				paramsValue.add(getEntity());	
				paramsName.add("anlLevel");
				paramsValue.add((short)5);					
				return String.format(INIT_QUERY_TEXT, fieldsList, fromList);		
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
			 */
			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}
		});

		addMasterModelListener(analitFifth);
		
		addMasterModelListener(this);
	}
	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		analitFirstProperties.put("FinAcc", event.getEntity());
		analitSecondProperties.put("FinAcc", event.getEntity());
		analitThirdProperties.put("FinAcc", event.getEntity());
		analitFourthProperties.put("FinAcc", event.getEntity());
		analitFifthProperties.put("FinAcc", event.getEntity());		
	}

}
