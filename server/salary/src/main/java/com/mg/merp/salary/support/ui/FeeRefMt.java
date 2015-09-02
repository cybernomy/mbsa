/*
 * FeeRefMt.java
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
package com.mg.merp.salary.support.ui;

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
import com.mg.merp.salary.FeeRefParamServiceLocal;
import com.mg.merp.salary.IncludedFeeServiceLocal;
import com.mg.merp.salary.ReplacedFeeServiceLocal;
import com.mg.merp.salary.TariffingInFeeServiceLocal;
import com.mg.merp.salary.model.FeeRefParam;
import com.mg.merp.salary.model.IncludedFee;
import com.mg.merp.salary.model.ReplacedFee;
import com.mg.merp.salary.model.TariffingInFee;

/**
 * Контроллер формы поддержки "Начисления/удержания" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: FeeRefMt.java,v 1.7 2007/07/18 08:33:10 sharapov Exp $
 */
public class FeeRefMt extends DefaultMaintenanceForm implements MasterModelListener {

	private MaintenanceTableController included;
	private IncludedFeeServiceLocal includedService;
	protected AttributeMap includedProperties = new LocalDataTransferObject();

	private MaintenanceTableController replaced;
	private ReplacedFeeServiceLocal replacedService;
	protected AttributeMap replacedProperties = new LocalDataTransferObject();

	private MaintenanceTableController tariffingInFee;
	private TariffingInFeeServiceLocal tariffingInFeeService;
	protected AttributeMap tariffingInFeeProperties = new LocalDataTransferObject();

	private MaintenanceTableController param;
	private FeeRefParamServiceLocal paramService;
	protected AttributeMap paramProperties = new LocalDataTransferObject();


	public FeeRefMt() throws Exception {
		setMasterDetail(true);
		addMasterModelListener(this);

		includedService = (IncludedFeeServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/salary/IncludedFee"); //$NON-NLS-1$
		included = new MaintenanceTableController(includedProperties);
		included.initController(includedService, new DefaultMaintenanceEJBQLTableModel() {

			private final String INIT_QUERY_TEXT = "select %s from IncludedFee if where if.FeeRef = :feeref"; //$NON-NLS-1$
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("feeref"); //$NON-NLS-1$
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
				result.add(new TableEJBQLFieldDef(IncludedFee.class, "Id", "if.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(IncludedFee.class, "IncludedFee.FCode", "if.IncludedFee.FCode", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(IncludedFee.class, "IncludedFee.Priority", "if.IncludedFee.Priority", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(IncludedFee.class, "SumSign", "if.SumSign", false)); //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, includedService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});
		addMasterModelListener(included);

		replacedService = (ReplacedFeeServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/salary/ReplacedFee"); //$NON-NLS-1$
		replaced = new MaintenanceTableController(replacedProperties);
		replaced.initController(replacedService, new DefaultMaintenanceEJBQLTableModel() {

			private final String INIT_QUERY_TEXT = "select %s from ReplacedFee rf where rf.FeeRef = :feeref"; //$NON-NLS-1$
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("feeref"); //$NON-NLS-1$
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
				result.add(new TableEJBQLFieldDef(ReplacedFee.class, "Id", "rf.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(ReplacedFee.class, "ReplacedFee.FCode", "rf.ReplacedFee.FCode", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(ReplacedFee.class, "ReplacedFee.Priority", "rf.ReplacedFee.Priority", false)); //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, replacedService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});
		addMasterModelListener(replaced);

		tariffingInFeeService = (TariffingInFeeServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/salary/TariffingInFee"); //$NON-NLS-1$
		tariffingInFee = new MaintenanceTableController(tariffingInFeeProperties);
		tariffingInFee.initController(tariffingInFeeService, new DefaultMaintenanceEJBQLTableModel() {

			private final String INIT_QUERY_TEXT = "select %s from TariffingInFee tif where tif.FeeRef = :feeref"; //$NON-NLS-1$
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);				
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("feeref"); //$NON-NLS-1$
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
				result.add(new TableEJBQLFieldDef(TariffingInFee.class, "Id", "tif.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TariffingInFee.class, "TariffingCategory.CCode", "tif.TariffingCategory.CCode", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TariffingInFee.class, "TariffingCategory.Priority", "tif.TariffingCategory.Priority", false)); //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, tariffingInFeeService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});
		addMasterModelListener(tariffingInFee);

		paramService = (FeeRefParamServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/salary/FeeRefParam"); //$NON-NLS-1$
		param = new MaintenanceTableController(paramProperties);
		param.initController(paramService, new DefaultMaintenanceEJBQLTableModel() {

			private final String INIT_QUERY_TEXT = "select %s from FeeRefParam frp %s where frp.FeeRef = :feeref"; //$NON-NLS-1$
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("feeref"); //$NON-NLS-1$
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
				result.add(new TableEJBQLFieldDef(FeeRefParam.class, "Id", "frp.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(FeeRefParam.class, "PCode", "frp.PCode", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(FeeRefParam.class, "PName", "frp.PName", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(FeeRefParam.class, "CalcAlg", "alg.Code", "left join frp.CalcAlg as alg", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(FeeRefParam.class, "Priority", "frp.Priority", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(FeeRefParam.class, "ParamType", "frp.ParamType", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(FeeRefParam.class, "CalcOnce", "frp.CalcOnce", false)); //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, paramService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});
		addMasterModelListener(param);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		includedProperties.put("FeeRef", event.getEntity()); //$NON-NLS-1$
		replacedProperties.put("FeeRef", event.getEntity()); //$NON-NLS-1$
		tariffingInFeeProperties.put("FeeRef", event.getEntity()); //$NON-NLS-1$
		paramProperties.put("FeeRef", event.getEntity()); //$NON-NLS-1$
	}

}
