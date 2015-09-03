/*
 * AccountMt.java
 *
 * Copyright (c) 1998 - 2009 BusinessTechnology, Ltd.
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

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.generic.ui.DefaultCompoundMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableModel;
import com.mg.merp.account.AnlPlanServiceLocal;
import com.mg.merp.account.model.AccPlan;

/**
 * Контроллер формы поддержки плана счетов
 * 
 * @author leonova
 * @version $Id: AccountMt.java,v 1.7 2009/02/25 15:09:59 safonov Exp $
 */
public class AccountMt extends DefaultCompoundMaintenanceForm implements MasterModelListener {
	private MaintenanceTableController analitFirst;
	private AnlPlanServiceLocal analitService;	
	protected AttributeMap analitFirstProperties = new LocalDataTransferObject();

	private MaintenanceTableController analitSecond;	
	protected AttributeMap analitSecondProperties = new LocalDataTransferObject();

	private MaintenanceTableController analitThird;	
	protected AttributeMap analitThirdProperties = new LocalDataTransferObject();

	private MaintenanceTableController analitFourth;
	protected AttributeMap analitFourthProperties = new LocalDataTransferObject();

	private MaintenanceTableController analitFifth;	
	protected AttributeMap analitFifthProperties = new LocalDataTransferObject();

	public AccountMt() throws Exception {
		addMasterModelListener(this);
		analitService = (AnlPlanServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/AnlPlan");
		
		analitFirstProperties.put("AnlLevel", 1);		
		analitFirst = new MaintenanceTableController(analitFirstProperties);
		analitFirst.initController(analitService, new AnlPlanMaintenanceEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.merp.account.support.ui.AnlPlanMaintenanceEJBQLTableModel#getAnalitikaLevel()
			 */
			@Override
			protected short getAnalitikaLevel() {
				return 1;
			}

		});
		addMasterModelListener(analitFirst);
			
		analitSecondProperties.put("AnlLevel", 2);
		analitSecond = new MaintenanceTableController(analitSecondProperties);
		analitSecond.initController(analitService, new AnlPlanMaintenanceEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.merp.account.support.ui.AnlPlanMaintenanceEJBQLTableModel#getAnalitikaLevel()
			 */
			@Override
			protected short getAnalitikaLevel() {
				return 2;
			}

		});

		addMasterModelListener(analitSecond);

		analitThirdProperties.put("AnlLevel", 3);	
		analitThird = new MaintenanceTableController(analitThirdProperties);
		analitThird.initController(analitService, new AnlPlanMaintenanceEJBQLTableModel() {
			
			/* (non-Javadoc)
			 * @see com.mg.merp.account.support.ui.AnlPlanMaintenanceEJBQLTableModel#getAnalitikaLevel()
			 */
			@Override
			protected short getAnalitikaLevel() {
				return 3;
			}

		});
		addMasterModelListener(analitThird);
		
		analitFourthProperties.put("AnlLevel", 4);
		analitFourth = new MaintenanceTableController(analitFourthProperties);
		analitFourth.initController(analitService, new AnlPlanMaintenanceEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.merp.account.support.ui.AnlPlanMaintenanceEJBQLTableModel#getAnalitikaLevel()
			 */
			@Override
			protected short getAnalitikaLevel() {
				return 4;
			}

		});
		addMasterModelListener(analitFourth);
		
		analitFifthProperties.put("AnlLevel", 5);	
		analitFifth = new MaintenanceTableController(analitFifthProperties);
		analitFifth.initController(analitService, new AnlPlanMaintenanceEJBQLTableModel() {
			
			/* (non-Javadoc)
			 * @see com.mg.merp.account.support.ui.AnlPlanMaintenanceEJBQLTableModel#getAnalitikaLevel()
			 */
			@Override
			protected short getAnalitikaLevel() {
				return 5;
			}

		});
		addMasterModelListener(analitFifth);
	}

	private void initMaster(PersistentObject master, AttributeMap analitikaProp, TableModel tableModel) {
		analitikaProp.put("AccPlan", master);
		((AnlPlanMaintenanceEJBQLTableModel) tableModel).setAccPlan((AccPlan) master);
	}

	public void masterChange(ModelChangeEvent event) {
		PersistentObject master = event.getEntity();
		initMaster(master, analitFirstProperties, analitFirst.getModel());
		initMaster(master, analitSecondProperties, analitSecond.getModel());
		initMaster(master, analitThirdProperties, analitThird.getModel());
		initMaster(master, analitFourthProperties, analitFourth.getModel());
		initMaster(master, analitFifthProperties, analitFifth.getModel());
	}

}
