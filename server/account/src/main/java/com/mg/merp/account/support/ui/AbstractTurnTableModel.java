/**
 * AbstractTurnTableModel.java
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.metadata.BuiltInType;
import com.mg.framework.api.metadata.ui.FieldMetadata;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.account.model.EconomicOper;
import com.mg.merp.account.support.Messages;

/**
 * Базовый класс модели таблицы для отображения спецификаций документа 
 * в дополнительном браузере расположенном в основном браузере документов
 * 
 * @author Artem V. Sharapov
 * @version $Id: AbstractTurnTableModel.java,v 1.1 2009/02/26 08:14:32 sharapov Exp $
 */
public abstract class AbstractTurnTableModel extends DefaultMaintenanceEJBQLTableModel {
	
	private final String INIT_QUERY_TEXT = "select %s from EconomicOper eo left join eo.EconomicSpecs es %s %s group by eo.KeepDate, eo.Id, eo.Comment, " +
			"sm.Code, bdt.Code, eo.BaseDocDate, eo.BaseDocNumber, ct.Code, eo.ContractNumber, eo.ContractDate, cdt.Code, eo.ConfirmDocNumber, eo.ConfirmDocDate, eo.Summa, f.Code, t.Code";

	private List<String> paramNames = new ArrayList<String>();
	private List<Object> paramValues = new ArrayList<Object>();
	private Messages msg;
	
	public AbstractTurnTableModel() {
		msg = Messages.getInstance();
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
	 */
	@Override
	protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
		Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
		result.add(new TableEJBQLFieldDef(EconomicOper.class, "Id", "eo.Id", true));
		result.add(new TableEJBQLFieldDef(EconomicOper.class, "KeepDate", "eo.KeepDate", false));
		result.add(new TableEJBQLFieldDef(EconomicOper.class, "Comment", "eo.Comment", false));
		result.add(new TableEJBQLFieldDef(EconomicOper.class, "SpecMark", "sm.Code", "left join eo.SpecMark as sm", true));
		result.add(new TableEJBQLFieldDef(EconomicOper.class, "BaseDocType", "bdt.Code", "left join eo.BaseDocType as bdt", true));
		result.add(new TableEJBQLFieldDef(EconomicOper.class, "BaseDocDate", "eo.BaseDocDate", false));
		result.add(new TableEJBQLFieldDef(EconomicOper.class, "BaseDocNumber", "eo.BaseDocNumber", false));
		result.add(new TableEJBQLFieldDef(EconomicOper.class, "ContractType", "ct.Code", "left join eo.ContractType as ct", true));
		result.add(new TableEJBQLFieldDef(EconomicOper.class, "ContractNumber", "eo.ContractNumber", false));
		result.add(new TableEJBQLFieldDef(EconomicOper.class, "ContractDate", "eo.ContractDate", false));
		result.add(new TableEJBQLFieldDef(EconomicOper.class, "ConfirmDocType", "cdt.Code", "left join eo.ConfirmDocType as cdt", true));
		result.add(new TableEJBQLFieldDef(EconomicOper.class, "ConfirmDocNumber", "eo.ConfirmDocNumber", false));
		result.add(new TableEJBQLFieldDef(EconomicOper.class, "ConfirmDocDate", "eo.ConfirmDocDate", false));
		result.add(new TableEJBQLFieldDef(EconomicOper.class, "Summa", "eo.Summa", false));
		result.add(new TableEJBQLFieldDef(EconomicOper.class, "From", "f.Code", "left join eo.From as f", true));
		result.add(new TableEJBQLFieldDef(EconomicOper.class, "To", "t.Code", "left join eo.To as t", true));
		
		FieldMetadata lineSumFieldMetadata = new FieldMetadata("sum(es.SummaNat)", BuiltInType.MONETARY_AMOUNT, BigDecimal.class, 0, null);
		lineSumFieldMetadata.setHeader(msg.getMessage(Messages.LINE_SUM));
		result.add(new TableEJBQLFieldDef(lineSumFieldMetadata));
		
		FieldMetadata lineQuanFieldMetadata = new FieldMetadata("sum(es.Quantity)", BuiltInType.QUANTITY, BigDecimal.class, 0, null);
		lineQuanFieldMetadata.setHeader(msg.getMessage(Messages.LINE_QUAN));
		result.add(new TableEJBQLFieldDef(lineQuanFieldMetadata));
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
	 */
	@Override
	protected void doLoad() {
		if (getMasterKey() != null)
			setQuery(createQueryText(), paramNames.toArray(new String[paramNames.size()]), paramValues.toArray(new Object[paramValues.size()]));
		else {
			this.rowList.clear();
			fireModelChange();
		}
	}

	/**
	 * Формирование запроса для отображение в таблице спецификаций
	 */
	private String createQueryText() {
		Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		clearParamNameAndValues();
		String whereText = doGetWhereText(paramNames);
		for (int i = 0; i < paramNames.size(); i++)
			paramValues.add(getMasterKey());
		
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);
	}
	
	private void clearParamNameAndValues() {
		paramNames.clear();
		paramValues.clear();
	}
	
	/**
	 * Получить where-часть текста запроса
	 * @param paramsName - список имен параметров
	 * @return where-часть текста запроса
	 */
	protected abstract String doGetWhereText(List<String> paramsName);

}