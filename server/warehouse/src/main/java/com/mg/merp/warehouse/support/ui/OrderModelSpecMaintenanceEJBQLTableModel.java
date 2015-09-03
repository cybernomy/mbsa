/*
 * OrderModelSpecMaintenanceEJBQLTableModel.java
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
package com.mg.merp.warehouse.support.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.warehouse.model.OrderSpecModel;

/**
 * Вспомогательный класс для отображения формы списка образцов заказов
 * 
 * @author leonova
 * @version $Id: OrderModelSpecMaintenanceEJBQLTableModel.java,v 1.1 2006/09/12 10:54:04 leonova Exp $ 
 */
public class OrderModelSpecMaintenanceEJBQLTableModel extends
		DefaultMaintenanceEJBQLTableModel {

	protected String INIT_QUERY_TEXT = "select %s from OrderSpecModel dsm %s where dsm.DocHeadModel = :docHeadModel";
	protected String fieldsList;
	protected String fromList;
	protected List<String> paramsName = new ArrayList<String>();
	protected List<Object> paramsValue = new ArrayList<Object>();
	protected DataBusinessObjectService<PersistentObject, Serializable> specService;
	protected Set<TableEJBQLFieldDef> result;
	
	protected String createQueryText() {
		Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
		fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
		fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		paramsName.clear();
		paramsValue.clear();	
		paramsName.add("docHeadModel");
							
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
		result = super.getDefaultFieldDefsSet();
		result.add(new TableEJBQLFieldDef(OrderSpecModel.class, "Id", "dsm.Id", true));
		result.add(new TableEJBQLFieldDef(OrderSpecModel.class, "Catalog.Code", "dsm.Catalog.Code", true));
		result.add(new TableEJBQLFieldDef(OrderSpecModel.class, "Catalog.FullName", "dsm.Catalog.FullName", false));
		result.add(new TableEJBQLFieldDef(OrderSpecModel.class, "Catalog.Measure1", "meas1.Code", "left join dsm.Catalog.Measure1 as meas1", false));
		result.add(new TableEJBQLFieldDef(OrderSpecModel.class, "Catalog.Measure2", "meas2.Code", "left join dsm.Catalog.Measure2 as meas2", false));
		result.add(new TableEJBQLFieldDef(OrderSpecModel.class, "Price", "dsm.Price", false));
		result.add(new TableEJBQLFieldDef(OrderSpecModel.class, "Summa", "dsm.Summa", false));
		result.add(new TableEJBQLFieldDef(OrderSpecModel.class, "BestBefore", "dsm.BestBefore", false));
		result.add(new TableEJBQLFieldDef(OrderSpecModel.class, "Discount", "dsm.Discount", false));						
		result.add(new TableEJBQLFieldDef(OrderSpecModel.class, "Weight", "dsm.Weight", false));
		result.add(new TableEJBQLFieldDef(OrderSpecModel.class, "Volume", "dsm.Volume", false));
		result.add(new TableEJBQLFieldDef(OrderSpecModel.class, "Catalog.Articul", "dsm.Catalog.Articul", false));
		result.add(new TableEJBQLFieldDef(OrderSpecModel.class, "Comment", "dsm.Comment", false));
		result.add(new TableEJBQLFieldDef(OrderSpecModel.class, "Contractor", "contr.Code", "left join dsm.Contractor as contr", false));			
		result.add(new TableEJBQLFieldDef(OrderSpecModel.class, "SrcStock", "ss.Code", "left join dsm.SrcStock as ss", false));
		result.add(new TableEJBQLFieldDef(OrderSpecModel.class, "SrcMol", "sm.Code", "left join dsm.SrcMol as sm", false));
		result.add(new TableEJBQLFieldDef(OrderSpecModel.class, "DstStock", "dst.Code", "left join dsm.DstStock as dst", false));
		result.add(new TableEJBQLFieldDef(OrderSpecModel.class, "DstMol", "dm.Code", "left join dsm.DstMol as dm", false));
		result.add(new TableEJBQLFieldDef(OrderSpecModel.class, "TaxGroup", "tg.Code", "left join dsm.TaxGroup as tg", false));
		result.add(new TableEJBQLFieldDef(OrderSpecModel.class, "PriceWithDiscount", "dsm.PriceWithDiscount", false));
		result.add(new TableEJBQLFieldDef(OrderSpecModel.class, "SummaWithDiscount", "dsm.SummaWithDiscount", false));		
		return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, specService);
	}
}
