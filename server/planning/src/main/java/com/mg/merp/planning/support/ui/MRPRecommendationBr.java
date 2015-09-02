/*
 * MRPRecommendationBr.java
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
package com.mg.merp.planning.support.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.planning.MRPRecommendationServiceLocal;
import com.mg.merp.planning.model.MrpRecommendation;
import com.mg.merp.planning.model.MrpVersionControl;
import com.mg.merp.planning.support.Messages;

/**
 * Контроллер формы списка рекомендаций ППМ
 * 
 * @author Oleg V. Safonov
 * @author leonova
 * @version $Id: MRPRecommendationBr.java,v 1.2 2007/07/30 10:36:31 safonov Exp $ 
 */
public class MRPRecommendationBr extends DefaultPlainBrowseForm {
	private final String INIT_QUERY_TEXT = "select %s from MrpRecommendation mr %s where mr.MrpVersionControl.Id = :mrpId and mr.MrpRescheduleFlag = com.mg.merp.planning.model.RescheduleFlag.SUGGESTED order by mr.MrpVersionControl.MrpVersion, mr.Warehouse.Code, mr.Catalog.Code, mr.RequiredDate";
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();
	private Integer mrpId;

	@Override
	protected String createQueryText() {
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);			
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		paramsName.clear();
		paramsValue.clear();
		paramsName.add("mrpId");
		paramsValue.add(mrpId);
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList);				

	}
	
	@Override
	protected MaintenanceTableModel createModel() {
		return new DefaultMaintenanceEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(MrpRecommendation.class, "Id", "mr.Id", true));
				result.add(new TableEJBQLFieldDef(MrpRecommendation.class, "Vendor", "v.Code", "left join mr.Vendor as v", false));
				result.add(new TableEJBQLFieldDef(MrpRecommendation.class, "Catalog", "mr.Catalog.Code", false));
				result.add(new TableEJBQLFieldDef(MrpRecommendation.class, "Measure", "mr.Measure.Code", false));
				result.add(new TableEJBQLFieldDef(MrpRecommendation.class, "Warehouse", "mr.Warehouse.Code", false));
				result.add(new TableEJBQLFieldDef(MrpRecommendation.class, "SourceWarehouse", "sw.Code", "left join mr.SourceWarehouse as sw", false));
				result.add(new TableEJBQLFieldDef(MrpRecommendation.class, "RequiredDate", "mr.RequiredDate", false));
				result.add(new TableEJBQLFieldDef(MrpRecommendation.class, "OrderQty", "mr.OrderQty", false));
				result.add(new TableEJBQLFieldDef(MrpRecommendation.class, "MrpQuantity", "mr.MrpQuantity", false));
				result.add(new TableEJBQLFieldDef(MrpRecommendation.class, "PurchaseLeadTime", "mr.PurchaseLeadTime", false));
				result.add(new TableEJBQLFieldDef(MrpRecommendation.class, "FirmPlanSuggestedOrder", "mr.FirmPlanSuggestedOrder", false));				
				result.add(new TableEJBQLFieldDef(MrpRecommendation.class, "OrderDate", "mr.OrderDate", false));
				result.add(new TableEJBQLFieldDef(MrpRecommendation.class, "MrpArrearsFlag", "mr.MrpArrearsFlag", false));
				result.add(new TableEJBQLFieldDef(MrpRecommendation.class, "MrpOrdered", "mr.MrpOrdered", false));
				result.add(new TableEJBQLFieldDef(MrpRecommendation.class, "PurchaseOrTransfer", "mr.PurchaseOrTransfer", false));
				result.add(new TableEJBQLFieldDef(MrpRecommendation.class, "PpReference", "mr.PpReference", false));		
				result.add(new TableEJBQLFieldDef(MrpRecommendation.class, "MrpRescheduleFlag", "mr.MrpRescheduleFlag", false));
				result.add(new TableEJBQLFieldDef(MrpRecommendation.class, "MrpSource", "mr.MrpSource", false));
				result.add(new TableEJBQLFieldDef(MrpRecommendation.class, "OriginalDate", "mr.OriginalDate", false));				
				result.add(new TableEJBQLFieldDef(MrpRecommendation.class, "BatchDate", "mr.BatchDate", false));
				result.add(new TableEJBQLFieldDef(MrpRecommendation.class, "OriginalQuantity", "mr.OriginalQuantity", false));
				
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);

			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
			 */
			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}
			
		};

	}

	/**
	 * Показать форму списка рекомендаций ППМ
	 * 
	 * @param mrpId	версия ППМ
	 */
	public void execute(Serializable mrpId) {
		this.mrpId = (Integer) mrpId;
		
		MrpVersionControl mrpVersion = ServerUtils.getPersistentManager().find(MrpVersionControl.class, mrpId);
		setTitle(Messages.getInstance().getMessage(Messages.MRP_RECOMMENDATION_TITLE, new Object[] {mrpVersion.getCode().trim()}));

		run();		
	}
	
	/**
	 * обработчик подтверждения заказов
	 * 
	 * @param event
	 */
	public void onActionFirm(WidgetEvent event) {
		((MRPRecommendationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(MRPRecommendationServiceLocal.SERVICE_NAME))
				.firm(((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys());
		table.refresh();
	}
	
	/**
	 * обработчик создания заказов
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void onActionCreateOrder(WidgetEvent event) throws Exception {
		((MRPRecommendationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(MRPRecommendationServiceLocal.SERVICE_NAME))
				.createOrder(mrpId);
		table.refresh();
	}

}
