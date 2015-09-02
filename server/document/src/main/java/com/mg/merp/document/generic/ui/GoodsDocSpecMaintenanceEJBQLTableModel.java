/*
 * GoodsDocSpecMaintenanceEJBQLTableModel.java
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
package com.mg.merp.document.generic.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.document.model.DocSpec;

/**
 * Вспомогательный класс для отображения формы поддерки товарных документов
 * 
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: GoodsDocSpecMaintenanceEJBQLTableModel.java,v 1.5 2008/12/25 10:11:11 safonov Exp $ 
 */
public abstract class GoodsDocSpecMaintenanceEJBQLTableModel extends
		DefaultMaintenanceEJBQLTableModel {
	private final static String QUERY_TEXT = "select %s from %s ds %s where ds.DocHead.Id = :docHeadId";
	protected List<String> paramsName = new ArrayList<String>();
	protected List<Object> paramsValue = new ArrayList<Object>();

	/**
	 * получить имя модели спецификации документа, может быть переопределен в наследниках,
	 * если у спецификации есть собственная модель расширяющая {@link com.mg.merp.document.model.DocSpec}
	 * 
	 * @return	имя модели
	 */
	protected String getDocSpecModelName() {
		return DocSpec.class.getName();
	}

	/**
	 * создание текста EJBQL запроса для получения информации отображаемой в модели таблицы, как правило не требует
	 * переопределения в наследниках
	 * 
	 * @return	текст EJBQL запроса
	 */
	protected String createQueryText() {
		Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		paramsName.clear();
		paramsValue.clear();	
		paramsName.add("docHeadId");
		paramsValue.add(getMasterKey());
							
		return String.format(QUERY_TEXT, fieldsList, getDocSpecModelName(), fromList);		
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
		result.add(new TableEJBQLFieldDef(DocSpec.class, "Id", "ds.Id", true));
		result.add(new TableEJBQLFieldDef(DocSpec.class, "Catalog.Code", "ds.Catalog.Code", true));
		result.add(new TableEJBQLFieldDef(DocSpec.class, "Catalog.FullName", "ds.Catalog.FullName", false));
		result.add(new TableEJBQLFieldDef(DocSpec.class, "Measure1", "meas1.Code", "left join ds.Measure1 as meas1", false));
		result.add(new TableEJBQLFieldDef(DocSpec.class, "Quantity", "ds.Quantity", false));
		result.add(new TableEJBQLFieldDef(DocSpec.class, "Measure2", "meas2.Code", "left join ds.Measure2 as meas2", false));
		result.add(new TableEJBQLFieldDef(DocSpec.class, "Quantity2", "ds.Quantity2", false));				
		result.add(new TableEJBQLFieldDef(DocSpec.class, "Weight", "ds.Weight", false));
		result.add(new TableEJBQLFieldDef(DocSpec.class, "Volume", "ds.Volume", false));
		result.add(new TableEJBQLFieldDef(DocSpec.class, "Catalog.Articul", "ds.Catalog.Articul", false));
		result.add(new TableEJBQLFieldDef(DocSpec.class, "Contractor", "contr.Code", "left join ds.Contractor as contr", false));			
		result.add(new TableEJBQLFieldDef(DocSpec.class, "Comment", "ds.Comment", false));
		result.add(new TableEJBQLFieldDef(DocSpec.class, "TaxGroup", "tg.Code", "left join ds.TaxGroup as tg", false));
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
	 */
	@Override			
	protected void doLoad() {
		setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
	}

}

