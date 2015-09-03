/*
 * GoodsSelectionForm.java
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
package com.mg.merp.lbschedule.support.ui;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.mg.merp.reference.CurrentStockSituation;
import com.mg.merp.reference.CurrentStockSituationLocator;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.MeasureControl;
import com.mg.merp.reference.model.PriceListHead;
import com.mg.merp.reference.model.PriceType;

/**
 * Контроллер формы подбора номенклатуры при создании спецификаций пункта графика 
 * 
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: GoodsSelectionForm.java,v 1.1 2007/04/17 12:51:41 sharapov Exp $
 */
public class GoodsSelectionForm extends AbstractForm {
	protected TableControllerAdapter goodsTable;
	private List<GoodsListItem> goodsList = new ArrayList<GoodsListItem>();
	private String[] columnsName;	
	private GoodsSelectionListener selectionListener;
	private GoodsListItem selectedItem;

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
				return column == 4 || column == 2 || column == 3 && MeasureControl.CATCHWEIGHT.equals(goodsList.get(row).measureControl);
			}

			@Override
			public void setValueAt(Object value, int row, int column) {
				GoodsListItem item = goodsList.get(row);
				switch (column) {
				case 2:
					item.quantity1 = new BigDecimal((String) value);
					break;
				case 3:
					item.quantity2 = new BigDecimal((String) value);
					break;
				case 4:
					item.price = new BigDecimal((String) value);
					break;
				}
			}

			public void setSelectedRows(int[] rows) {
				selectedItem = goodsList.get(rows[0]);
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

	@SuppressWarnings("unchecked") //$NON-NLS-1$
	private void loadGoodsList(PriceListHead priceList, PriceType priceType) {
		List<Object[]> tmpList;
		goodsList.clear();
		if (priceList != null) {
			String whereClause;
			String[] paramsName;
			Object[] paramsValue;
			if (priceType != null) {
				whereClause = " prSpec.Folder.PriceListHead.Id = :priceListHeadId and prices.Id.PriceType = :priceType and";//"prSpec.Folder.PriceListHead.Id = :priceListHeadId and prices.Id.PriceType.Id = :priceTypeId and"; //$NON-NLS-1$
				paramsName = new String[] {"priceListHeadId", "priceType"}; //$NON-NLS-1$ //$NON-NLS-2$
				paramsValue = new Object[] {priceList.getId(), priceType};
				//paramsName = new String[] {"priceListHeadId", "priceTypeId"};
				//paramsValue = new Object[] {priceList.getId(), priceType.getId()};
			}
			else {
				whereClause = "prSpec.Folder.PriceListHead.Id = :priceListHeadId and"; //$NON-NLS-1$
				paramsName = new String[] {"priceListHeadId"}; //$NON-NLS-1$
				paramsValue = new Object[] {priceList.getId()};
			}
			tmpList = OrmTemplate.getInstance().findByNamedParam(new StringBuilder("select distinct prSpec.Catalog.Code, prSpec.SName, prSpec.Catalog.Id, prSpec.Id, prSpec.Catalog.MeasureControl, prices.Price from PriceListSpec prSpec left join prSpec.PriceListSpecPrice prices where ").append(whereClause).append(DatabaseUtils.generateFlatBrowseWhereEJBQL("prSpec.Folder.Id", 2)).append(" order by prSpec.Catalog.UpCode").toString(), paramsName, paramsValue);			 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
			tmpList = OrmTemplate.getInstance().find(new StringBuilder("select goods.Code, goods.FullName, goods.Id, goods.MeasureControl from Catalog goods where").append(DatabaseUtils.generateFlatBrowseWhereEJBQL("goods.Folder.Id", 1)).append(" order by goods.UpCode").toString()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
	 * @param priceList		прайс-лист
	 * @param priceTypeLink	тип цен
	 */
	public void execute(GoodsSelectionListener listener, DocHead docHead) {
		selectionListener = listener;
		loadGoodsList(docHead.getPriceList(), null);
		view.setTitle(Messages.getInstance().getMessage(Messages.GOODS_SELECTION_TITLE, new Object[] {DocumentUtils.generateDocumentTitle(docHead)}));
		run();
	}

	/**
	 * запуск формы
	 * @param listener - слушатель
	 * @param priceList - прайс-лист
	 * @param priceType - тип цены
	 */
	public void execute(PriceListHead priceList, PriceType priceType, GoodsSelectionListener listener) {
		selectionListener = listener;
		loadGoodsList(priceList, priceType);
		view.setTitle(com.mg.merp.lbschedule.support.Messages.getInstance().getMessage(com.mg.merp.lbschedule.support.Messages.PRICE_LIST_SPEC_TITLE));
		run();
	}

	/**
	 * обработчик события выбора номенклатуры
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

	public void onActionShowStockSituation(WidgetEvent event) throws Exception {
		final CurrentStockSituation service = CurrentStockSituationLocator.locate();
		Catalog catalog = ServerUtils.getPersistentManager().find(Catalog.class, selectedItem.catalogId);
		service.showSituationForm(catalog);
	}

}
