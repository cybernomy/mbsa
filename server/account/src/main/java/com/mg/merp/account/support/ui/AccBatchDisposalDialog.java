/*
 * AccBatchDisposalDialog.java
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

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.FlushMode;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projection;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.orm.ResultTransformer;
import com.mg.framework.generic.ui.AbstractTableModel;
import com.mg.framework.generic.ui.DefaultWizardDialog;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.account.model.AccBatch;
import com.mg.merp.account.model.EconomicOper;
import com.mg.merp.account.model.EconomicSpec;
import com.mg.merp.account.support.Messages;
import com.mg.merp.reference.model.Catalog;

/**
 * Контроллер формы диалога списания с партии (вызывается при отработке ЭДО "Создать ХО")
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: AccBatchDisposalDialog.java,v 1.3 2008/03/25 14:54:12 alikaev Exp $
 */
public class AccBatchDisposalDialog extends DefaultWizardDialog {
	
	private DefaultTableController accBatchHistoryList;
	
	private Catalog catalogDocSpec;
	
    @DataItemName("Reference.Code")
	private String catalogCode;
	
    @DataItemName("Reference.Name")
	private String catalogName;
	
	@DataItemName("Document.DocSpec.Quantity1")
	private BigDecimal quntitySpec;
	
	public AccBatchDisposalDialog() {
		this.accBatchHistoryList = new DefaultTableController(new AccBatchHistoryList());
	}
	
	private class AccBatchHistoryList extends AbstractTableModel {
		Messages msg = Messages.getInstance();
		private String[] columnsName = new String[] {
				msg.getMessage(Messages.ACC_BATCH_ID), msg.getMessage(Messages.ACC_BATCH_INCOME_DATE), 
				msg.getMessage(Messages.ACC_BATCH_DOCTYPE), msg.getMessage(Messages.ACC_BATCH_DOCNUMBER), 
				msg.getMessage(Messages.ACC_BATCH_DOCDATE), msg.getMessage(Messages.ACC_BATCH_DEBIT_QUAN), 
				msg.getMessage(Messages.ACC_BATCH_CREDIT_QUAN), msg.getMessage(Messages.ACC_BATCH_DIFF_QUAN), 
				msg.getMessage(Messages.ACC_BATCH_PROCESS_QUAN), msg.getMessage(Messages.ACC_BATCH_PRICECUR), 
				msg.getMessage(Messages.ACC_BATCH_PRICENAT) 
		};
		private List<AccBatchHistoryItem> specListModel;

		/* (non-Javadoc)
		 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#getColumnName(int)
		 */
		public String getColumnName(int column) {
			return columnsName[column];
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#getValueAt(int, int)
		 */
		public Object getValueAt(int row, int column) {
			AccBatchHistoryItem item = specListModel.get(row);
			switch (column) {
			case 0: return item.id;
			case 1: return item.incomeDate;
			case 2: return item.DocType;
			case 3: return item.DocNumber;
			case 4: return item.DocDate;
			case 5: return item.debitQuntity;
			case 6: return item.creditQuntity;
			case 7: return item.diffQuntity;
			case 8: return item.processQuntity;
			case 9: return item.priceNat;
			case 10: return item.priceCur;
			default: return StringUtils.EMPTY_STRING;
			}
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#getColumnCount()
		 */
		public int getColumnCount() {
			return columnsName.length;
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#getRowCount()
		 */
		public int getRowCount() {
			return specListModel.size();
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#isCellEditable(int, int)
		 */
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return columnIndex == 8;
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#setValueAt(java.lang.Object, int, int)
		 */
		public void setValueAt(Object value, int rowIndex, int columnIndex) {
			AccBatchHistoryItem item = specListModel.get(rowIndex);
			switch (columnIndex) {
			case 8: //processQuntity
				//проверка если количество введенное пользователем больше чем остаток в партии, то устанавливаем 
				//значение равное остатку в партии
				BigDecimal processQuntity = (BigDecimal) value;
				if (processQuntity.compareTo(item.diffQuntity) > 0)
					processQuntity = item.diffQuntity;
				item.processQuntity = processQuntity;
				//fireModelChange();
				fireTableRowsUpdated(rowIndex, rowIndex);
				break;
			}
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.generic.ui.AbstractTableModel#getColumnClass(int)
		 */
		@Override
		public Class<?> getColumnClass(int column) {
			if (column == 8)
				return BigDecimal.class;
			else
				return super.getColumnClass(column);
		}

	}
	
	/**
	 * Запуск диалогового окна
	 * 
	 * @param economicSpec
	 * 					- спецификация хоз. операции
	 */
	public void execute(EconomicSpec economicSpec) {
		this.catalogDocSpec = economicSpec.getCatalog();
		this.quntitySpec = economicSpec.getQuantity();
		this.catalogCode = economicSpec.getCatalog().getCode();
		this.catalogName = economicSpec.getCatalog().getFullName();
		((AccBatchHistoryList) this.accBatchHistoryList.getModel()).specListModel = loadModel(economicSpec);
		super.execute();
	}
	
	/**
	 * Загрузка данных в таблицу
	 * @param economicSpec
	 * 					- спецификация хоз. операции
	 * @return
	 */
	private List<AccBatchHistoryItem> loadModel(EconomicSpec economicSpec) {
		Projection projection = Projections.projectionList(Projections.groupProperty("Id"), Projections.groupProperty("InComeDate"), 
				Projections.groupProperty("DocType"), Projections.groupProperty("DocNumber"), Projections.groupProperty("DocDate"),
				Projections.groupProperty("BeginQuan"), Projections.groupProperty("EndQuan"),
				Projections.groupProperty("InComeCostNat"), Projections.groupProperty("InComeCostCur"));
		Criteria criteria = OrmTemplate.createCriteria(AccBatch.class)
				.add(Restrictions.eq("AccPlan", economicSpec.getAccKt()))
				.add(Restrictions.ne("EndQuan", BigDecimal.ZERO))
				.setProjection(projection)
				.setFlushMode(FlushMode.MANUAL)
				.setResultTransformer(new ResultTransformer<AccBatchHistoryItem>() {

			public AccBatchHistoryItem transformTuple(Object[] tuple, String[] aliases) {
				BigDecimal beginQuan = (BigDecimal) tuple[5];				
				BigDecimal endQuan = (BigDecimal) tuple[6];
				if (endQuan == null)
					endQuan = BigDecimal.ZERO;
				return new AccBatchHistoryItem((Integer)tuple[0], (Date) tuple[1], tuple[2].toString(), tuple[3].toString(), tuple[4].toString(), beginQuan, beginQuan.subtract(endQuan), (BigDecimal) tuple[7], (BigDecimal) tuple[8]);
			}
		});
		EconomicOper economicOper = economicSpec.getEconomicOper();
		if (economicOper.getKeepDate() != null)
			criteria.add(Restrictions.le("InComeDate", economicOper.getKeepDate()));			
		if (economicOper.getFrom() != null)
			criteria.add(Restrictions.eq("Contractor", economicOper.getFrom()));			
		if (economicSpec.getCatalog() != null)
			criteria.add(Restrictions.eq("CatalogId", economicSpec.getCatalog().getId()));
		if (economicSpec.getAnlKt1() != null)
			criteria.add(Restrictions.eq("AnlPlan1Id", economicSpec.getAnlKt1().getId()));
		if (economicSpec.getAnlKt2() != null)
			criteria.add(Restrictions.eq("AnlPlan2Id", economicSpec.getAnlKt2().getId()));
		if (economicSpec.getAnlKt3() != null)
			criteria.add(Restrictions.eq("AnlPlan3Id", economicSpec.getAnlKt3().getId()));
		if (economicSpec.getAnlKt4() != null)
			criteria.add(Restrictions.eq("AnlPlan4Id", economicSpec.getAnlKt4().getId()));
		if (economicSpec.getAnlKt5() != null)
			criteria.add(Restrictions.eq("AnlPlan5Id", economicSpec.getAnlKt5().getId()));
		
		return OrmTemplate.getInstance().findByCriteria(criteria);
	}
	
	public Catalog getCatalogDocSpec() {
		return catalogDocSpec;
	}

	public void setCatalogDocSpec(Catalog catalogDocSpec) {
		this.catalogDocSpec = catalogDocSpec;
	}

	public BigDecimal getQuntitySpec() {
		return quntitySpec;
	}

	public void setQuntitySpec(BigDecimal quntitySpec) {
		this.quntitySpec = quntitySpec;
	}

	public List<AccBatchHistoryItem> getRowList() {
		return ((AccBatchHistoryList) this.accBatchHistoryList.getModel()).specListModel;
	}

	public String getCatalogCode() {
		return catalogCode;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public class AccBatchHistoryItem {
		
		private Integer id;
		
		private Date incomeDate;
		
		private String DocType;

		private String DocNumber;
		
		private String DocDate;
		
		private BigDecimal debitQuntity;
		
		private BigDecimal creditQuntity;
		
		private BigDecimal diffQuntity;
		
		private BigDecimal processQuntity;
		
		private BigDecimal priceNat;
		
		private BigDecimal priceCur;

		public BigDecimal getProcessQuntity() {
			return processQuntity;
		}

		public Integer getId() {
			return id;
		}

		public Date getIncomeDate() {
			return incomeDate;
		}

		public String getDocType() {
			return DocType;
		}

		public String getDocNumber() {
			return DocNumber;
		}

		public String getDocDate() {
			return DocDate;
		}

		public BigDecimal getDebitQuntity() {
			return debitQuntity;
		}

		public BigDecimal getCreditQuntity() {
			return creditQuntity;
		}

		public BigDecimal getDiffQuntity() {
			return diffQuntity;
		}

		public BigDecimal getPriceNat() {
			return priceNat;
		}

		public BigDecimal getPriceCur() {
			return priceCur;
		}

		public AccBatchHistoryItem(Integer id, Date incomeDate, String docType, String docNumber, String docDate, BigDecimal debitQuntity,
				BigDecimal creditQuntity, BigDecimal priceNat, BigDecimal priceCur) {
			this.id = id;
			this.incomeDate = incomeDate;
			this.DocType = docType;
			this.DocNumber = docNumber;
			this.DocDate = docDate;
			this.debitQuntity = debitQuntity;
			this.creditQuntity = creditQuntity;
			if (debitQuntity != null)
				this.diffQuntity = creditQuntity != null ? debitQuntity.subtract(creditQuntity) : debitQuntity;
			else
				this.diffQuntity = creditQuntity;
			this.priceNat = priceNat;
			this.priceCur = priceCur;
		}
	
	}

}
