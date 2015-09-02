/*
 * CatalogMt.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.merp.reference.support.ui;

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
import com.mg.merp.reference.CatalogPriceServiceLocal;
import com.mg.merp.reference.PackingServiceLocal;
import com.mg.merp.reference.SetOfGoodServiceLocal;
import com.mg.merp.reference.model.CatalogPrice;
import com.mg.merp.reference.model.Packing;
import com.mg.merp.reference.model.SetOfGood;


/**
 * Форма поддержки каталога
 * 
 * @author Oleg V. Safonov
 * @version $Id: CatalogMt.java,v 1.7 2008/12/29 07:25:38 safonov Exp $
 */
public class CatalogMt extends DefaultMaintenanceForm implements MasterModelListener {
	protected MaintenanceTableController standartCost;
	private CatalogPriceServiceLocal standartCostService;
	protected AttributeMap standartCostProperties = new LocalDataTransferObject();

	private MaintenanceTableController setOfGood;
	private SetOfGoodServiceLocal setOfGoodService;
	protected AttributeMap setOfGoodProperties = new LocalDataTransferObject();

	private MaintenanceTableController packing;
	private PackingServiceLocal packingService;
	protected AttributeMap packingProperties = new LocalDataTransferObject();
	
	public CatalogMt() throws Exception {	
		
		setMasterDetail(true);
		addMasterModelListener(this);
		standartCostService = (CatalogPriceServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/CatalogPrice");
		standartCost = new MaintenanceTableController(standartCostProperties);
		standartCost.initController(standartCostService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from CatalogPrice catprice where catprice.Catalog = :catalog";
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();
			
			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("catalog");
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
				result.add(new TableEJBQLFieldDef(CatalogPrice.class, "Id", "catprice.Id", true));
				result.add(new TableEJBQLFieldDef(CatalogPrice.class, "InAction", "catprice.InAction", false));
				result.add(new TableEJBQLFieldDef(CatalogPrice.class, "Currency", "catprice.Currency.Code", false));
				result.add(new TableEJBQLFieldDef(CatalogPrice.class, "CurrencyRateType", "catprice.CurrencyRateType.Code", false));
				result.add(new TableEJBQLFieldDef(CatalogPrice.class, "CurrencyRateAuthority", "catprice.CurrencyRateAuthority.Code", false));
				result.add(new TableEJBQLFieldDef(CatalogPrice.class, "Price", "catprice.Price", false));
				result.add(new TableEJBQLFieldDef(CatalogPrice.class, "EquivalentPrice", "catprice.EquivalentPrice", false));
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, standartCostService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});
		addMasterModelListener(standartCost);

		setOfGoodService = (SetOfGoodServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/SetOfGood");
		setOfGood = new MaintenanceTableController(setOfGoodProperties);
		setOfGood.initController(setOfGoodService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from SetOfGood sog where sog.CatalogGood = :catalog";
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();
			
			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("catalog");
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
				result.add(new TableEJBQLFieldDef(SetOfGood.class, "Id", "sog.Id", true));
				result.add(new TableEJBQLFieldDef(SetOfGood.class, "CatalogComponent", "sog.CatalogComponent.Code", false));
				result.add(new TableEJBQLFieldDef(SetOfGood.class, "Quantity", "sog.Quantity", false));
				result.add(new TableEJBQLFieldDef(SetOfGood.class, "PriceRelate", "sog.PriceRelate", false));
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, setOfGoodService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}			
			
		});
		addMasterModelListener(setOfGood);

		packingService = (PackingServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Packing");
		packing = new MaintenanceTableController(packingProperties);
		packing.initController(packingService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from Packing pac %s where pac.Catalog = :catalog";
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();
			
			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();				
				paramsValue.clear();	
				paramsName.add("catalog");
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
				result.add(new TableEJBQLFieldDef(Packing.class, "Id", "pac.Id", true));
				result.add(new TableEJBQLFieldDef(Packing.class, "Measure", "pac.Measure.Code", false));
				result.add(new TableEJBQLFieldDef(Packing.class, "QuantityMeasure1", "pac.QuantityMeasure1", false));
				result.add(new TableEJBQLFieldDef(Packing.class, "QuantityMeasure2", "pac.QuantityMeasure2", false));
				result.add(new TableEJBQLFieldDef(Packing.class, "Weight", "pac.Weight", false));
				result.add(new TableEJBQLFieldDef(Packing.class, "WeightMeasure", "weightmeas.Code", " left join pac.WeightMeasure as weightmeas ", false));
				result.add(new TableEJBQLFieldDef(Packing.class, "Volume", "pac.Volume", false));
				result.add(new TableEJBQLFieldDef(Packing.class, "VolumeMeasure", "volumemeas.Code", " left join pac.VolumeMeasure as volumemeas ",false));
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, setOfGoodService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}				
			
		});

		addMasterModelListener(packing);		
		
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		standartCostProperties.put("Catalog", event.getEntity());
		setOfGoodProperties.put("CatalogGood", event.getEntity());
		packingProperties.put("Catalog", event.getEntity());	
	}	
}
