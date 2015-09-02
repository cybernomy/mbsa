/*
 * RemnAccBr.java
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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.jdbc.RowMapper;
import com.mg.framework.api.ui.HierarchyRestrictionSupport;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.account.AccountServiceLocal;
import com.mg.merp.account.RemnAccServiceLocal;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.Period;
import com.mg.merp.account.model.RemnAcc;
import com.mg.merp.core.support.CoreUtils;
import com.mg.merp.reference.model.Currency;

/**
 * Контроллер браузера бизнес-компонента "Остатки и обороты по счетам"
 * 
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @author Konstantin S. Alikaev
 * @version $Id: RemnAccBr.java,v 1.13 2009/03/18 10:44:39 sharapov Exp $
 */
public class RemnAccBr extends AbstractHierarchyBrowseFormWithTurn {
	
	private final String INIT_QUERY_TEXT_FOR_INTERBASE = "select %s from acc_remnacc_select(%s, %s, %s, %s) ra where %s order by ra.datefrom"; //$NON-NLS-1$
	private final String INIT_QUERY_TEXT_FOR_ORACLE = "select %s from table(acc_remnacc_select(%s, %s, %s, %s)) ra where %s order by ra.datefrom"; //$NON-NLS-1$
	private List<Object> paramsValue = new ArrayList<Object>();
	private RemnAccServiceLocal serviceRemnAcc = null;
	
	
	@SuppressWarnings("unchecked")
	public RemnAccBr() throws Exception {
		super();
		folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder"); //$NON-NLS-1$
		treeUIProperties.put("FolderType", AccountServiceLocal.FOLDER_PART); //$NON-NLS-1$
		restrictionFormName = "com/mg/merp/account/resources/RemnAccRest.mfd.xml"; //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultLegacyHierarchyBrowseForm#loadFolders()
	 */
	@Override
	protected TreeNode loadFolders() throws ApplicationException {
		return CoreUtils.loadFolderHierarchy(AccountServiceLocal.FOLDER_PART);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
	 */
	@Override
	protected String createQueryText() {
		paramsValue.clear();

		String fields = " ra.ID, ra.PNAME, ra.DATEFROM, ra.DATETO, ra.ACC, ra.CURCODE, " +  //$NON-NLS-1$
		"ra.REMNBEGINNATDB, ra.REMNBEGINNATKT, ra.REMNENDNATDB," + //$NON-NLS-1$
		" ra.REMNENDNATKT, ra.TURNNATDB, ra.TURNNATKT, ra.REMNBEGINCURDB, ra.REMNBEGINCURKT, " + //$NON-NLS-1$
		"ra.REMNENDCURDB, ra.REMNENDCURKT, ra.TURNCURDB, ra.TURNCURKT ";  //$NON-NLS-1$
		RemnAccRest restForm = (RemnAccRest) getRestrictionForm();
		boolean restIsHierarchy = ((HierarchyRestrictionSupport) restForm).isUseHierarchy();
		AccPlan accPlan = restForm.getAccCode();
		Integer periodId1 = restForm.getPeriodFrom() != null ? restForm.getPeriodFrom().getId() : null;
		Integer periodId2 = restForm.getPeriodTo() != null ? restForm.getPeriodTo().getId() : null;
		Integer accountId = accPlan != null ? accPlan.getId() : null;
		StringBuilder whereText = new StringBuilder("(0=0)"); //$NON-NLS-1$
		Currency currency =  restForm.getCurrencyCode();
		Integer folderId = null;
		if (restIsHierarchy) {
			folderId = (Integer) folderEntity.getPrimaryKey();
		}
		if (currency != null) {
			whereText.append("and (ra.CURCODE = ?)"); //$NON-NLS-1$
			paramsValue.add(currency.getCode());
		}
		String queryText = StringUtils.format(INIT_QUERY_TEXT_FOR_INTERBASE, fields, periodId1, periodId2, accountId, folderId, whereText.toString());
		switch (DatabaseUtils.getDBMSType()) {
		case INTERBASE:	return queryText;	
		case FIREBIRD:	return queryText;	
		case ORACLE: return StringUtils.format(INIT_QUERY_TEXT_FOR_ORACLE, fields, periodId1, periodId2, accountId, folderId, whereText.toString());
		default: throw new UnsupportedOperationException();
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
	 */
	@Override
	protected MaintenanceTableModel createModel() {
		return new DefaultMaintenanceEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
			 */
			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(RemnAcc.class, "Id", "ra.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(RemnAcc.class, "Period", "ra.Period.Name", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Period.class, "DateFrom", "p.DateFrom", "left join ra.Period as p", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Period.class, "DateTo", "p1.DateTo", "left join ra.Period as p1", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(RemnAcc.class, "AccPlan", "ra.AccPlan.Acc", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(AccPlan.class, "Currency", "acp.Currency.Code", "left join ra.AccPlan as acp", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(RemnAcc.class, "RemnBeginNatDb", "ra.RemnBeginNatDb", true));	 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(RemnAcc.class, "RemnBeginNatKt", "ra.RemnBeginNatKt", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(RemnAcc.class, "RemnEndNatDb", "ra.RemnEndNatDb", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(RemnAcc.class, "RemnEndNatKt", "ra.RemnEndNatKt", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(RemnAcc.class, "TurnNatDb", "ra.TurnNatDb", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(RemnAcc.class, "TurnNatKt", "ra.TurnNatKt", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(RemnAcc.class, "RemnBeginCurDb", "ra.RemnBeginCurDb", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(RemnAcc.class, "RemnBeginCurKt", "ra.RemnBeginCurKt", true));	//$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(RemnAcc.class, "RemnEndCurKt", "ra.RemnEndCurKt", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(RemnAcc.class, "RemnEndCurDb", "ra.RemnEndCurDb", true));	 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(RemnAcc.class, "TurnCurDb", "ra.TurnCurDb", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(RemnAcc.class, "TurnCurKt", "ra.TurnCurKt", true)); //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
			}			

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected void doLoad() {
				List<Object[]> listRow = JdbcTemplate.getInstance().query(createQueryText(), paramsValue.toArray(new Object[paramsValue.size()]), new RowMapper<Object[]>() {

					public Object[] mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new Object[] {rs.getInt(1), rs.getString(2),rs.getDate(3), rs.getDate(4), rs.getString(5), rs.getString(6), 
								rs.getBigDecimal(7), rs.getBigDecimal(8), rs.getBigDecimal(9), 
								rs.getBigDecimal(10), rs.getBigDecimal(11), rs.getBigDecimal(12),
								rs.getBigDecimal(13), rs.getBigDecimal(14), rs.getBigDecimal(15), 
								rs.getBigDecimal(16), rs.getBigDecimal(17), rs.getBigDecimal(18)};
					}
				});
				setRowList(listRow);
			}
		};
	}
	
	/**
	 * Вызов сервиса бизнес-компонента "Остатки и обороты по счетам бух. учета"
	 * @return
	 */
	protected RemnAccServiceLocal getRemnAccService() {
		if (serviceRemnAcc == null)
			return (RemnAccServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/RemnAcc"); //$NON-NLS-1$
		return serviceRemnAcc;
	}

	/**
	 * Обработчик кнопки контекстного меню "Удалить пустые строки"
	 * 
	 * @param event
	 * @throws ApplicationException
	 */
	public void onActionDeleteEmptyStrings(WidgetEvent event) throws ApplicationException {
		RemnAccRest remnRest = (RemnAccRest) getRestrictionForm();
		getRemnAccService().removeEmptyRecords(remnRest.getPeriodFrom(), remnRest.getPeriodTo());
		table.refresh();
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.account.support.ui.AbstractHierarchyBrowseFormWithTurn#doGetTurnDbWhereText(java.util.List)
	 */
	@Override
	protected String doGetTurnDbWhereText(List<String> paramsName) {
		paramsName.add("remnId");
		return " where es.RemnAccDb.Id = :remnId";
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.account.support.ui.AbstractHierarchyBrowseFormWithTurn#doGetTurnKtWhereText(java.util.List)
	 */
	@Override
	protected String doGetTurnKtWhereText(List<String> paramsName) {
		paramsName.add("remnId");
		return " where es.RemnAccKt.Id = :remnId";
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.account.support.ui.AbstractHierarchyBrowseFormWithTurn#doGetTurnWhereText(java.util.List)
	 */
	@Override
	protected String doGetTurnWhereText(List<String> paramsName) {
		paramsName.add("remnId");
		return " where es.RemnAccKt.Id = :remnId or es.RemnAccDb.Id = :remnId";
	}
	
}
