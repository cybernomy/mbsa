/*
 * PlanningLevelMt.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.mfreference.support.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.mfreference.PlanningLevelBucketServiceLocal;
import com.mg.merp.mfreference.PlanningLevelServiceLocal;
import com.mg.merp.mfreference.model.PlanningLevel;
import com.mg.merp.mfreference.model.PlanningLevelBucket;

/**
 * Контроллер формы поддержки уровней планирования
 * 
 * @author Julia 'Jetta' Konyashkina
 * @author Artem V. Sharapov
 * @version $Id: PlanningLevelMt.java,v 1.5 2007/02/19 12:55:14 sharapov Exp $
 */
public class PlanningLevelMt extends DefaultMaintenanceForm implements MasterModelListener {
	private MaintenanceTableController planningLevelBucket;
	private PlanningLevelBucketServiceLocal planningLevelBucketService;
	protected AttributeMap planningLevelBucketProperties = new LocalDataTransferObject();
	private PlanningLevelServiceLocal planningLevelService;
	private PlanningLevelBucketGenerationDlg dialog;

	public PlanningLevelMt() throws Exception {
		setMasterDetail(true);
		planningLevelBucketService = (PlanningLevelBucketServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/mfreference/PlanningLevelBucket"); //$NON-NLS-1$
		planningLevelBucket = new MaintenanceTableController(planningLevelBucketProperties);
		planningLevelBucket.initController(planningLevelBucketService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from PlanningLevelBucket plb where plb.PlanningLevel = :planninglevel"; //$NON-NLS-1$
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("planninglevel"); //$NON-NLS-1$
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
				result.add(new TableEJBQLFieldDef(PlanningLevelBucket.class, "Id", "plb.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PlanningLevelBucket.class, "StartDate", "plb.StartDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PlanningLevelBucket.class, "EndDate", "plb.EndDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PlanningLevelBucket.class, "BucketOffset", "plb.BucketOffset", false));				 //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, planningLevelBucketService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});

		addMasterModelListener(planningLevelBucket);

		addMasterModelListener(this);
	}
	public void masterChange(ModelChangeEvent event) {
		planningLevelBucketProperties.put("PlanningLevel", event.getEntity()); //$NON-NLS-1$
	}

	/**
	 * Обработка события выбора пункта контекстного меню "Генерация периодов"
	 * @param event - событие
	 * @throws Exception
	 */
	public void onActionGeneratePlaningLevelBuckets(WidgetEvent event) throws Exception {
		if(getEntity().getAttribute("Id") != null) { //$NON-NLS-1$
			planningLevelService = (PlanningLevelServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/mfreference/PlanningLevel"); //$NON-NLS-1$
			dialog = (PlanningLevelBucketGenerationDlg) UIProducer.produceForm("com/mg/merp/mfreference/resources/PlanningLevelBucketGenerationDlg.mfd.xml"); //$NON-NLS-1$
			dialog.addOkActionListener(new FormActionListener() {
				public void actionPerformed(FormEvent event) {
					planningLevelService.generateBuckets((PlanningLevel) getEntity(), dialog.getBucketLength(), dialog.getBucketNumber());
					planningLevelBucket.refresh();
				}
			});	
			dialog.execute();
		}
	}
}