/*
 * GoodsSelectionForm.java
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
package com.mg.merp.document.support.ui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractForm;
import com.mg.framework.generic.ui.AbstractTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableControllerAdapter;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.docflow.support.DocumentUtils;
import com.mg.merp.document.CreateSpecificationInfo;
import com.mg.merp.document.GoodsSelectionEvent;
import com.mg.merp.document.GoodsSelectionListener;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.support.Messages;
import com.mg.merp.reference.CurrentStockSituationLocator;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.MeasureControl;
import com.mg.merp.reference.model.PriceListHead;
import com.mg.merp.reference.model.PriceType;

/**
 *  онтроллер формы подбора номенклатуры при создании спецификаций документов
 * 
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: GoodsSelectionForm.java,v 1.9 2008/09/02 06:40:29 sharapov Exp $
 */
public class GoodsSelectionForm extends AbstractForm implements com.mg.merp.document.GoodsSelectionForm {
	private final static int MEASURE1_COLUMN = 2;
	private final static int MEASURE2_COLUMN = 3;
	private final static int PRICE_COLUMN = 4;
	protected TableControllerAdapter goodsTable;
	private List<GoodsListItem> goodsList = new ArrayList<GoodsListItem>();
	private String[] columnsName;	
	private GoodsSelectionListener selectionListener;
	private GoodsListItem selectedItem;
	
	private PriceListHead priceList;
	private PriceType priceType;
	private Date actDate;
	
	public GoodsSelectionForm() {
		Messages msg = Messages.getInstance();
		columnsName = new String[] {msg.getMessage(Messages.GOODS_CODE), msg.getMessage(Messages.GOODS_NAME), msg.getMessage(Messages.GOODS_QUANTITY1), msg.getMessage(Messages.GOODS_QUANTITY2), msg.getMessage(Messages.GOODS_PRICE)};
		goodsTable = new DefaultTableController(new AbstractTableModel() {

			public int getColumnCount() {
				return columnsName.length;
			}

			public String getColumnName(int column) {
				return columnsName[column];
			}

			public int getRowCount() {
				return goodsList.size();
			}

			public Object getValueAt(int row, int column) {
				GoodsListItem item = goodsList.get(row);
				switch (column) {
				case 0: return item.code;
				case 1: return item.FullName;
				case 2: return item.quantity1;
				case 3: return item.quantity2;
				case 4: return item.price;
				}
				return null;
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == PRICE_COLUMN || column == MEASURE1_COLUMN || column == MEASURE2_COLUMN && MeasureControl.CATCHWEIGHT.equals(goodsList.get(row).measureControl);
			}

			@Override
			public void setValueAt(Object value, int row, int column) {
				GoodsListItem item = goodsList.get(row);
				BigDecimal decimalValue = (BigDecimal) value;
				switch (column) {
				case MEASURE1_COLUMN:
					item.quantity1 = decimalValue;
					break;
				case MEASURE2_COLUMN:
					item.quantity2 = decimalValue;
					break;
				case PRICE_COLUMN:
					item.price = decimalValue;
					break;
				}
			}
			
			public void setSelectedRows(int[] rows) {
				if (rows.length == 0)
					selectedItem = null;
				else
					selectedItem = goodsList.get(rows[0]);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#getColumnClass(int)
			 */
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case MEASURE1_COLUMN:
				case MEASURE2_COLUMN:
				case PRICE_COLUMN:
					return BigDecimal.class;
				default:
					return super.getColumnClass(column);
				}
			}

		});
	}

	class GoodsListItem {
		String code;
		String FullName;
		Integer catalogId;
		Integer priceListSpecId;
		MeasureControl measureControl;
		BigDecimal quantity1;
		BigDecimal quantity2;
		BigDecimal price;
	}
	
	class CreateSpecificationInfoImpl implements CreateSpecificationInfo {
		Integer catalogId;
		Integer pricelistId;
		BigDecimal price;
		BigDecimal quantity1;
		BigDecimal quantity2;

		public CreateSpecificationInfoImpl(Integer catalogId, Integer pricelistId, BigDecimal price, BigDecimal quantity1, BigDecimal quantity2) {
			super();
			this.catalogId = catalogId;
			this.pricelistId = pricelistId;
			this.price = price;
			this.quantity1 = quantity1;
			this.quantity2 = quantity2;
		}

		public Integer getCatalogId() {
			return catalogId;
		}

		public BigDecimal getPrice() {
			return price;
		}

		public Integer getPricelistId() {
			return pricelistId;
		}

		public BigDecimal getQuantity1() {
			return quantity1;
		}

		public BigDecimal getQuantity2() {
			return quantity2;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void loadGoodsList(PriceListHead priceList, PriceType priceType, Date actDate) {
		List<Object[]> tmpList;
		goodsList.clear();
		if (priceList != null) {
			StringBuilder queryText = new StringBuilder("select distinct prSpec.Catalog.Code, prSpec.SName, prSpec.Catalog.Id, prSpec.Id, prSpec.Catalog.MeasureControl, prices.Price from PriceListSpec prSpec join prSpec.PriceListSpecPrice prices where ");
			List<String> paramsName = new ArrayList<String>();
			List<Object> paramsValue = new ArrayList<Object>();
			if (priceType != null) {
				queryText.append("prSpec.Folder.PriceListHead.Id = :priceListHeadId and prices.Id.PriceType = :priceType and");
				paramsName.add("priceListHeadId");
				paramsName.add("priceType");
				paramsValue.add(priceList.getId());
				paramsValue.add(priceType);
			}
			else {
				queryText.append("prSpec.Folder.PriceListHead.Id = :priceListHeadId and");
				paramsName.add("priceListHeadId");
				paramsValue.add(priceList.getId());
			}
			if (actDate != null) {
				queryText.append(" :actDate between prSpec.ActDate and prSpec.ActDateTill and");
				paramsName.add("actDate");
				paramsValue.add(actDate);
			}
			tmpList = OrmTemplate.getInstance().findByNamedParam(queryText.append(DatabaseUtils.generateFlatBrowseWhereEJBQL("prSpec.Folder.Id", 2))
					.append(" order by prSpec.Catalog.UpCode").toString(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));			
			for (Object[] item : tmpList) {
				GoodsListItem goods = new GoodsListItem();
				goods.code = (String) item[0];
				goods.FullName = (String) item[1];
				goods.catalogId = (Integer) item[2];
				goods.priceListSpecId = (Integer) item[3];
				goods.measureControl = (MeasureControl) item[4];
				goods.price = (BigDecimal) item[5];
				goodsList.add(goods);
			}
		}
		else {
			tmpList = OrmTemplate.getInstance().find(new StringBuilder("select goods.Code, goods.FullName, goods.Id, goods.MeasureControl from Catalog goods where")
					.append(DatabaseUtils.generateFlatBrowseWhereEJBQL("goods.Folder.Id", 1))
					.append(" order by goods.UpCode").toString());
			for (Object[] item : tmpList) {
				GoodsListItem goods = new GoodsListItem();
				goods.code = (String) item[0];
				goods.FullName = (String) item[1];
				goods.catalogId = (Integer) item[2];
				goods.measureControl = (MeasureControl) item[3];
				goodsList.add(goods);
			}
		}
	}

	/**
	 * запуск формы подбора номенклатуры
	 * 
	 * @param listener		слушатель
	 * @param docHead		документ
	 */
	public void execute(GoodsSelectionListener listener, DocHead docHead) {
		execute(listener, docHead.getPriceList(), docHead.getPriceType(), docHead.getDocDate(), Messages.getInstance().getMessage(Messages.GOODS_SELECTION_TITLE, new Object[] {DocumentUtils.generateDocumentTitle(docHead)}));
	}

	/**
	 * запуск формы подбора номенклатуры
	 * 
	 * @param listener		слушатель
	 * @param priceList		прайс-лист
	 * @param priceType		тип цены
	 * @param title			заголовок формы
	 */
	public void execute(GoodsSelectionListener listener, PriceListHead priceList, PriceType priceType, Date actDate, String title) {
		initialize(priceList, priceType, actDate, listener);
		loadGoodsTable();
		view.setTitle(title);
		run();
	}
		
	/**
	 * »нициализировать параметры запуска формы подбора номенклатуры
	 * @param priceList - прайс-лист
	 * @param priceType - тип цены
	 * @param actDate - на дату
	 * @param listener - слушатель
	 */
	private void initialize(PriceListHead priceList, PriceType priceType, Date actDate, GoodsSelectionListener listener) {
		this.selectionListener = listener;
		this.priceList = priceList;
		this.priceType = priceType;
		this.actDate = actDate;
	}
	
	/**
	 * «аполнить таблицу данными
	 */
	private void loadGoodsTable() {
		loadGoodsList(priceList, priceType, actDate);
	}

	/**
	 * обработчик событи€ выбора номенклатуры
	 * 
	 * @param event
	 */
	public void onActionChoose(WidgetEvent event) {
		if (selectionListener != null) {
			List<CreateSpecificationInfo> specInfoList = new ArrayList<CreateSpecificationInfo>();
			for (int i = 0; i < goodsList.size(); i++) {
				GoodsListItem item = goodsList.get(i);
				if (item.quantity1 != null || item.quantity2 != null) {
					CreateSpecificationInfo specInfo = new CreateSpecificationInfoImpl(item.catalogId, item.priceListSpecId, item.price, item.quantity1, item.quantity2);
					specInfoList.add(specInfo);
					item.quantity1 = null;
					item.quantity2 = null;
					((AbstractTableModel) goodsTable.getModel()).fireTableRowsUpdated(i, i);
				}
			}
			
			if (specInfoList.size() != 0)
				selectionListener.doSelect(new GoodsSelectionEvent(this, specInfoList.toArray(new CreateSpecificationInfo[specInfoList.size()])));				
		}
	}
	
	/**
	 * ќбработчик событи€ обновлени€ номенклатуры
	 * @param event - событие
	 */
	public void onActionRefresh(WidgetEvent event) {
		loadGoodsTable();
		((AbstractTableModel) goodsTable.getModel()).fireModelChange();
	}
	
	/**
	 * обработчик событи€ получени€ состо€ни€ складов
	 * 
	 * @param event
	 */
	public void onActionShowStockSituation(WidgetEvent event) {
		if (selectedItem != null)
			CurrentStockSituationLocator.locate().showSituationForm(
					ServerUtils.getPersistentManager().find(Catalog.class, selectedItem.catalogId));
	}

}
