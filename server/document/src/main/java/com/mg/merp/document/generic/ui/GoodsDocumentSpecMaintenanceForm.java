/*
 * GoodsDocumentSpecMaintenanceForm.java
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

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.Widget;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.document.DocumentSpecPackageServiceLocal;
import com.mg.merp.document.DocumentSpecSerialNumServiceLocal;
import com.mg.merp.document.GoodsDocumentSpecification;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.document.model.DocumentSpecPackage;
import com.mg.merp.document.model.DocumentSpecSerialNum;
import com.mg.merp.document.model.DocumentSpecTax;

/**
 * Контроллер формы поддержки спецификаций
 * 
 * @author leonova
 * @version $Id: GoodsDocumentSpecMaintenanceForm.java,v 1.7 2008/06/06 06:36:11 sharapov Exp $
 */
public class GoodsDocumentSpecMaintenanceForm extends DefaultMaintenanceForm implements MasterModelListener{
	private MaintenanceTableController serialNum;
	private DocumentSpecSerialNumServiceLocal serialNumService;
	protected AttributeMap serialNumProperties = new LocalDataTransferObject();

	private MaintenanceTableController packing;
	private DocumentSpecPackageServiceLocal packingService;
	protected AttributeMap packingProperties = new LocalDataTransferObject();

	private DefaultTableController documentSpecTaxes;

	public GoodsDocumentSpecMaintenanceForm() throws Exception{
		super();
		addMasterModelListener(this);
		setMasterDetail(true);

		serialNumService = (DocumentSpecSerialNumServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/document/DocumentSpecSerialNum");
		serialNum = new MaintenanceTableController(serialNumProperties);
		serialNum.initController(serialNumService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from DocumentSpecSerialNum dssn where dssn.DocSpec = :docSpec";
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("docSpec");
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
				result.add(new TableEJBQLFieldDef(DocumentSpecSerialNum.class, "Id", "dssn.Id", true));
				result.add(new TableEJBQLFieldDef(DocumentSpecSerialNum.class, "SerialNum", "dssn.SerialNum", false));
				result.add(new TableEJBQLFieldDef(DocumentSpecSerialNum.class, "Comment", "dssn.Comment", false));
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, serialNumService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});

		addMasterModelListener(serialNum);		

		packingService = (DocumentSpecPackageServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/document/DocumentSpecPackage");
		packing = new MaintenanceTableController(packingProperties);
		packing.initController(packingService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from DocumentSpecPackage dsp %s where dsp.DocSpec = :docSpec";
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("docSpec");
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
				result.add(new TableEJBQLFieldDef(DocumentSpecPackage.class, "Id", "dsp.Id", true));
				result.add(new TableEJBQLFieldDef(DocumentSpecPackage.class, "Packing.Measure", "p.Measure.Code", "left join dsp.Packing as p", false));
				result.add(new TableEJBQLFieldDef(DocumentSpecPackage.class, "Quantity", "dsp.Quantity", false));
				result.add(new TableEJBQLFieldDef(DocumentSpecPackage.class, "Weight", "dsp.Weight", false));
				result.add(new TableEJBQLFieldDef(DocumentSpecPackage.class, "Volume", "dsp.Volume", false));
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, packingService);
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

		documentSpecTaxes = new DefaultTableController(new DefaultEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(DocumentSpecTax.class, "SumElement", "dst.SumElement", false));
				result.add(new TableEJBQLFieldDef(DocumentSpecTax.class, "PriceElement", "dst.PriceElement", false));
				result.add(new TableEJBQLFieldDef(DocumentSpecTax.class, "Tax.Code", "dst.Tax.Code", false));
				result.add(new TableEJBQLFieldDef(DocumentSpecTax.class, "Tax.TaxForm", "dst.Tax.TaxForm", false));
				result.add(new TableEJBQLFieldDef(DocumentSpecTax.class, "Tax.TaxType", "dst.Tax.TaxType", false));
				result.add(new TableEJBQLFieldDef(DocumentSpecTax.class, "Tax.DirectRate", "dst.Tax.DirectRate", false));
				result.add(new TableEJBQLFieldDef(DocumentSpecTax.class, "Tax.InverseRate", "dst.Tax.InverseRate", false));
				//result.add(new TableEJBQLFieldDef(CalcTaxesLink.class, "Included", "ctl.Included", false));
				//result.add(new TableEJBQLFieldDef(CalcTaxesLink.class, "Subject", "ctl.Subject", false));
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, packingService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected void doLoad() {
				List<Object[]> list = new ArrayList<Object[]>();
				Set<DocumentSpecTax> taxes = ((DocSpec) getEntity()).getTaxes();
				if (taxes != null)
					for (DocumentSpecTax specTax : taxes)
						list.add(new Object[] {specTax.getSumElement(), specTax.getPriceElement(), specTax.getTax().getCode(), specTax.getTax().getTaxForm(), specTax.getTax().getTaxType(), specTax.getTax().getDirectRate(), specTax.getTax().getInverseRate()});
				setRowList(list);
			}
		});

		addMasterModelListener(documentSpecTaxes);
	}

	private void setCatalogFieldReadOnly() {
		Widget catalogWidget = view.getWidget("Catalog");
		if (catalogWidget != null)
			catalogWidget.setReadOnly(true);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnClone()
	 */
	@Override
	protected void doOnClone() {
		super.doOnClone();
		setCatalogFieldReadOnly();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnEdit()
	 */
	@Override
	protected void doOnEdit() {
		super.doOnEdit();
		setCatalogFieldReadOnly();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		serialNumProperties.put("DocSpec", event.getEntity());
		packingProperties.put("DocSpec", event.getEntity());
	}

	/**
	 * обработчик кнопки "Adjust"
	 * 
	 * @param event
	 */
	@SuppressWarnings("unchecked")
	public void onActionAdjust(WidgetEvent event) {
		((GoodsDocumentSpecification) getService()).adjust((DocSpec) getEntity());
		//обновим спецификацию
		view.flushModel();
		//обновим список налогов
		documentSpecTaxes.getModel().load();
	}

}
