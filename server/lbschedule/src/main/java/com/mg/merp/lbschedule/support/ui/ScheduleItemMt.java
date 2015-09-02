/*
 * ScheduleItemBr.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.ui.Alert;
import com.mg.framework.api.ui.AlertListener;
import com.mg.framework.api.ui.MaintenanceFormActionListener;
import com.mg.framework.api.ui.MaintenanceFormEvent;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.CheckBox;
import com.mg.framework.api.ui.widget.EntityField;
import com.mg.framework.api.ui.widget.EnumComboBoxField;
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.Messages;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.document.CreateSpecificationInfo;
import com.mg.merp.document.GoodsSelectionEvent;
import com.mg.merp.document.GoodsSelectionListener;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.lbschedule.ItemServiceLocal;
import com.mg.merp.lbschedule.ItemSpecServiceLocal;
import com.mg.merp.lbschedule.model.DateOffSetKind;
import com.mg.merp.lbschedule.model.Item;
import com.mg.merp.lbschedule.model.ItemContractorSource;
import com.mg.merp.lbschedule.model.ItemSpec;
import com.mg.merp.lbschedule.model.ItemSpecCreateData;
import com.mg.merp.lbschedule.model.Schedule;
import com.mg.merp.lbschedule.model.ScheduleDocHeadLink;
import com.mg.merp.lbschedule.model.ScheduleStatus;
import com.mg.merp.lbschedule.model.SpecSource;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.PriceListHead;
import com.mg.merp.reference.model.PriceType;

/**
 * Контроллер формы поддержки бизнес-компонента "Пункты графика исполнения обязательств"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ScheduleItemMt.java,v 1.6 2007/04/24 15:42:23 sharapov Exp $
 */
public class ScheduleItemMt  extends DefaultMaintenanceForm implements MasterModelListener {

	private DefaultTableController specItem;
	protected AttributeMap specItemProperties = new LocalDataTransferObject();

	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();

	private DocHead docHead;

	private final String HAS_SPEC_WIDGET = "HasSpec"; //$NON-NLS-1$
	private final String CUR_CODE_WIDGET = "CurCode"; //$NON-NLS-1$
	private final String RATE_TYPE_WIDGET = "CurRateType"; //$NON-NLS-1$
	private final String RATE_AUTHRITY_WIDGET = "CurRateAuthority"; //$NON-NLS-1$
	private final String SPEC_SOURCE_WIDGET = "SpecSource"; //$NON-NLS-1$
	private final String PRICE_LIST_WIDGET = "PriceListHead"; //$NON-NLS-1$
	private final String PRICE_TYPE_WIDGET = "PriceType"; //$NON-NLS-1$
	private final String FROM_SOURCE_WIDGET = "FromSource"; //$NON-NLS-1$
	private final String TO_SOURCE_WIDGET = "ToSource"; //$NON-NLS-1$
	private final String FROM_WIDGET = "From"; //$NON-NLS-1$
	private final String TO_WIDGET = "To"; //$NON-NLS-1$
	private final String PARAM_NAME = "Item";  //$NON-NLS-1$

	public ScheduleItemMt() throws Exception {
		setMasterDetail(true);

		specItem = new DefaultTableController(new DefaultMaintenanceEJBQLTableModel() {

			private String createQueryText() {
				String INIT_QUERY_TEXT = "select %s from ItemSpec ispec %s where ispec.Item = :Item"; //$NON-NLS-1$
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				return String.format(INIT_QUERY_TEXT, fieldsList, fromList);		
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected void doLoad() {
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add(PARAM_NAME);
				paramsValue.add(getEntity());
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(ItemSpec.class, "Id", "ispec.Id", true));  //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(ItemSpec.class, "Catalog.Code", "cat.Code", "left join ispec.Catalog cat", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(ItemSpec.class, "Catalog.FullName", "cat.FullName", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(ItemSpec.class, "Price", "ispec.Price", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(ItemSpec.class, "ClearPrice", "ispec.ClearPrice", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(ItemSpec.class, "Summa", "ispec.Summa", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(ItemSpec.class, "ClearSumma", "ispec.ClearSumma", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(ItemSpec.class, "Measure1", "m1.Code", "left join ispec.Measure1 m1", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(ItemSpec.class, "Qty1", "ispec.Qty1", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(ItemSpec.class, "Measure2", "m2.Code", "left join ispec.Measure2 m2", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(ItemSpec.class, "Qty2", "ispec.Qty2", false)); //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, null);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
			 */
			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}
		});
		addMasterModelListener(specItem);
		addMasterModelListener(this);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		specItemProperties.put(PARAM_NAME, event.getEntity());
		getDocHead();
		if(getCheckBoxValue(HAS_SPEC_WIDGET))
			view.getWidget(HAS_SPEC_WIDGET).setEnabled(false);
		adjustView();
	}


	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnView()
	 */
	@Override
	protected void doOnView() {
		super.doOnView();
		PopupMenu menu = view.getWidget("specItem").getPopupMenu(); //$NON-NLS-1$
		menu.getMenuItem("addSpec").setEnabled(false); //$NON-NLS-1$
		menu.getMenuItem("editSpec").setEnabled(false); //$NON-NLS-1$
		menu.getMenuItem("removeSpec").setEnabled(false); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnAdd()
	 */
	@Override
	protected void doOnAdd() {
		super.doOnAdd();
		getDocHead();
		initFieldValues();
		adjustView();
	}

	private void adjustView() {
		adjustViewByHasSpec();
		adjustViewBySpecSource();
	}

	private void initFieldValues() {
		getEntity().setAttribute("DateOffSetKind", DateOffSetKind.WORKDAY); //$NON-NLS-1$
		getEntity().setAttribute("DateOffSet", 0); //$NON-NLS-1$
		getEntity().setAttribute("Perc", 0); //$NON-NLS-1$
		getEntity().setAttribute("Status", ScheduleStatus.PLANNED); //$NON-NLS-1$

		getEntity().setAttribute(CUR_CODE_WIDGET, docHead.getCurrency());
		getEntity().setAttribute(RATE_TYPE_WIDGET, docHead.getCurrencyRateType());
		getEntity().setAttribute(RATE_AUTHRITY_WIDGET, docHead.getCurrencyRateAuthority());

		view.flushModel();
	}

	/**
	 * Обработчик события "Расчет даты"
	 * @param event - событие
	 */
	public void onActionCalculateDates(WidgetEvent event) {
		((ItemServiceLocal) getService()).computeResultDate((Item) getEntity());
	}

	/**
	 * Обработчик события "Расчет сумм"
	 * @param event - событие
	 */
	public void onActionCalculateSums(WidgetEvent event) {
		((ItemServiceLocal) getService()).computeResultSum((Item) getEntity());
	}

	/**
	 * Обработчик события КМ "Добавить спецификацию" 
	 * @param event - событие
	 */
	public void onActionAddSpec(WidgetEvent event) throws Exception {
		if(getCheckBoxValue(HAS_SPEC_WIDGET)) {
			SpecSource specSource = (SpecSource) getEnumBoxValue(SPEC_SOURCE_WIDGET);
			if(specSource == SpecSource.DOCUMENT)
				addSpecByDoc();
			if(specSource == SpecSource.CATALOG)
				addSpecByCatalog();
			if(specSource == SpecSource.PRICELIST)
				addSpecByPriceList();
		}
	}

	/**
	 * Добавление позиции спецификации из спецификации документа
	 * @throws Exception - ИС
	 */
	private void addSpecByDoc() throws Exception {
		SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.lbschedule.support.ui.DocSpecSearchHelp"); //$NON-NLS-1$
		searchHelp.addSearchHelpListener(new SearchHelpListener() {
			public void searchPerformed(SearchHelpEvent event) {
				PersistentObject[] entitys = event.getItems();
				ItemSpecCreateData[] itemSpecCreateData = new ItemSpecCreateData[entitys.length];
				int i = 0;
				for (PersistentObject object : entitys) {
					itemSpecCreateData[i++] = new ItemSpecCreateData(
							((DocSpec) object).getCatalog(), 
							(DocSpec) object, 
							((DocSpec) object).getMeasure1(),
							((DocSpec) object).getQuantity(),
							((DocSpec) object).getMeasure2(),
							((DocSpec) object).getQuantity2(),
							((DocSpec) object).getPrice()
					);
				}
				getItemSpecService().addSpec((Item) getEntity(), itemSpecCreateData);
				refreshSpecItemTable();
			}

			public void searchCanceled(SearchHelpEvent event) {
				//do nothing
			}
		});
		((DocSpecSearchHelp) searchHelp).setSearchParams(docHead);
		searchHelp.search();
	}

	/**
	 * Добавление позиции спецификации из спецификации каталога
	 * @throws Exception - ИС
	 */
	private void addSpecByCatalog() throws Exception {
		SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.reference.support.ui.CatalogSearchHelp"); //$NON-NLS-1$
		searchHelp.addSearchHelpListener(new SearchHelpListener() {
			public void searchPerformed(SearchHelpEvent event) {
				PersistentObject[] entitys = event.getItems();
				ItemSpecCreateData[] itemSpecCreateData = new ItemSpecCreateData[entitys.length];
				int i = 0;
				for (PersistentObject object : entitys) {
					itemSpecCreateData[i++] = new ItemSpecCreateData(
							((Catalog) object), 
							null, 
							((Catalog) object).getMeasure1(),
							BigDecimal.ZERO,
							((Catalog) object).getMeasure2(),
							BigDecimal.ZERO,
							BigDecimal.ZERO
					);
				}
				getItemSpecService().addSpec((Item) getEntity(), itemSpecCreateData);
				refreshSpecItemTable();
			}

			public void searchCanceled(SearchHelpEvent event) {
				//do nothing
			}
		});
		searchHelp.search();
	}

	/**
	 * Добавление позиции спецификации из спецификации прайс-листа
	 */
	private void addSpecByPriceList() {
		GoodsSelectionForm goodsSelectionForm = (GoodsSelectionForm) UIProducer.produceForm("com/mg/merp/lbschedule/resources/GoodsSelectionForm.mfd.xml"); //$NON-NLS-1$
		goodsSelectionForm.execute(getPriceList(), getPriceType(), new GoodsSelectionListener() {

			/* (non-Javadoc)
			 * @see com.mg.merp.document.GoodsSelectionListener#doSelect(com.mg.merp.document.GoodsSelectionEvent)
			 */
			public void doSelect(GoodsSelectionEvent event) {
				CreateSpecificationInfo[] entitys = event.getSpecInfo();
				ItemSpecCreateData[] itemSpecCreateData = new ItemSpecCreateData[entitys.length];
				int i = 0;
				for (CreateSpecificationInfo object : entitys) {
					itemSpecCreateData[i] = new ItemSpecCreateData(
							((Catalog) ServerUtils.getPersistentManager().find(Catalog.class, object.getCatalogId())), 
							null, 
							null,
							object.getQuantity1(),
							null,
							object.getQuantity2(),
							object.getPrice()
					);
					itemSpecCreateData[i].setPriceList(getPriceList());
					i++;
				}
				getItemSpecService().addSpec((Item) getEntity(), itemSpecCreateData);
				refreshSpecItemTable();
			}
		});
	}

	/**
	 * Обработчик события КМ "Изменить позицию спецификации" 
	 * @param event - событие
	 */
	public void onActionEditSpec(WidgetEvent event) {
		Serializable[] selectedIDs = getSpecItemSelectedPrimaryKeys();
		if(selectedIDs != null && selectedIDs.length > 0)
			MaintenanceHelper.edit(getItemSpecService(), selectedIDs[0], null, new MaintenanceFormActionListener() {

				/* (non-Javadoc)
				 * @see com.mg.framework.api.ui.MaintenanceFormActionListener#canceled(com.mg.framework.api.ui.MaintenanceFormEvent)
				 */
				public void canceled(MaintenanceFormEvent event) {
					// do nothing
				}

				/* (non-Javadoc)
				 * @see com.mg.framework.api.ui.MaintenanceFormActionListener#performed(com.mg.framework.api.ui.MaintenanceFormEvent)
				 */
				public void performed(MaintenanceFormEvent event) {
					refreshSpecItemTable();
				}
			});
	}

	/**
	 * Обработчик события КМ "Удалить позицию спецификации" 
	 * @param event - событие
	 */
	public void onActionRemoveSpec(WidgetEvent event) {
		final Serializable[] selectedIDs = getSpecItemSelectedPrimaryKeys();
		if(selectedIDs != null && selectedIDs.length > 0) {
			Messages msg = Messages.getInstance();
			final String yesButton = msg.getMessage(Messages.YES_BUTTON_TEXT);
			UIUtils.showAlert(Alert.MessageType.QUESTION_MESSAGE, msg.getMessage(Messages.ERASE_ALERT_TITLE),msg.getMessage(Messages.ERASE_ALERT_QUESTION), yesButton, msg.getMessage(Messages.NO_BUTTON_TEXT), new AlertListener() {
				public void alertClosing(String value) {
					if(value.equals(yesButton)) {
						getItemSpecService().removeSpec(selectedIDs);
						refreshSpecItemTable();
					}
				}
			});
		}
	}

	/**
	 * Обработчик события КМ "Просмотр позиции спецификации" 
	 * @param event - событие
	 */
	public void onActionViewSpec(WidgetEvent event) {
		Serializable[] selectedIDs = getSpecItemSelectedPrimaryKeys();
		if(selectedIDs != null && selectedIDs.length > 0)
			MaintenanceHelper.view(getItemSpecService(), selectedIDs[0], null, null);
	}

	/**
	 * Обработчик события КМ "Обновить таблицу спецификаций" 
	 * @param event - событие
	 */
	public void onActionRefreshSpec(WidgetEvent event) {
		refreshSpecItemTable();
	}

	private void refreshSpecItemTable() {
		if(getEntity().getAttribute("Id") != null) //$NON-NLS-1$
			specItem.getModel().load();
	}

	public void onActionFromSourceChanged(WidgetEvent event) throws Exception {
		ItemContractorSource fromSource = (ItemContractorSource) getEnumBoxValue(FROM_SOURCE_WIDGET);
		getEntity().setAttribute(FROM_WIDGET,getContractor(fromSource));
		view.flushModel();
	}

	public void onActionToSourceChanged(WidgetEvent event) throws Exception {
		ItemContractorSource toSource = (ItemContractorSource) getEnumBoxValue(TO_SOURCE_WIDGET);
		getEntity().setAttribute(TO_WIDGET, getContractor(toSource));
		view.flushModel();
	}

	public void onActionSpecSourceChanged(WidgetEvent event) throws Exception {
		adjustViewBySpecSource();
	}

	private void adjustViewBySpecSource() {
		SpecSource specSource = (SpecSource) getEnumBoxValue(SPEC_SOURCE_WIDGET);
		if(specSource == SpecSource.PRICELIST) {
			view.getWidget(PRICE_LIST_WIDGET).setReadOnly(false);
			view.getWidget(PRICE_TYPE_WIDGET).setReadOnly(false);
		}
		else {
			getEntity().setAttribute(PRICE_LIST_WIDGET, null);
			getEntity().setAttribute(PRICE_TYPE_WIDGET, null);
			view.getWidget(PRICE_LIST_WIDGET).setReadOnly(true);
			view.getWidget(PRICE_TYPE_WIDGET).setReadOnly(true);
		}
	}

	public void onActionHasSpecChanged(WidgetEvent event) throws Exception {
		adjustViewByHasSpec();
	}

	private void adjustViewByHasSpec() {
		if(!getCheckBoxValue(HAS_SPEC_WIDGET)) {
			getEntity().setAttribute(SPEC_SOURCE_WIDGET, null);
			getEntity().setAttribute(PRICE_LIST_WIDGET, null);
			getEntity().setAttribute(PRICE_TYPE_WIDGET, null);
			view.getWidget(SPEC_SOURCE_WIDGET).setReadOnly(true);
			view.getWidget(PRICE_LIST_WIDGET).setReadOnly(true);
			view.getWidget(PRICE_TYPE_WIDGET).setReadOnly(true);
		}
		else 
			view.getWidget(SPEC_SOURCE_WIDGET).setReadOnly(false);
	}

	private PriceListHead getPriceList() {
		return (PriceListHead) ((EntityField) view.getWidget(PRICE_LIST_WIDGET)).getEditorValue();
	}

	private PriceType getPriceType() {
		return (PriceType) ((EntityField) view.getWidget(PRICE_TYPE_WIDGET)).getEditorValue();
	}

	private void getDocHead() {
		Schedule schedule = (Schedule) getEntity().getAttribute("Schedule"); //$NON-NLS-1$
		//docHead = ServerUtils.getPersistentManager().find(DocHead.class, schedule.getDocId());
		ScheduleDocHeadLink scheduleDocHeadLink = OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(ScheduleDocHeadLink.class)
				.add(Restrictions.eq("Schedule", schedule))); //$NON-NLS-1$

		docHead = scheduleDocHeadLink.getDocHead();
	}

	private Contractor getContractor(ItemContractorSource contractorSource) {
		ServerUtils.getPersistentManager().refresh(docHead);
		if(contractorSource == ItemContractorSource.FROM)
			return docHead.getFrom();
		if(contractorSource == ItemContractorSource.TO)
			return docHead.getTo();
		if(contractorSource == ItemContractorSource.THROUGH)
			return docHead.getThrough();
		if(contractorSource == ItemContractorSource.STOCKFROM)
			return docHead.getSrcStock();
		if(contractorSource == ItemContractorSource.STOCKTO)
			return docHead.getDstStock();
		if(contractorSource == ItemContractorSource.MOLFROM)
			return docHead.getSrcMol();
		if(contractorSource == ItemContractorSource.MOLTO)
			return docHead.getDstMol();
		return null;
	}

	private Serializable[] getSpecItemSelectedPrimaryKeys() {
		return ((DefaultMaintenanceEJBQLTableModel) specItem.getModel()).getSelectedPrimaryKeys();
	}

	private ItemSpecServiceLocal getItemSpecService() {
		return (ItemSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(ItemSpecServiceLocal.LOCAL_SERVICE_NAME);
	}

	private boolean getCheckBoxValue(String widgetName) {
		return (Boolean) ((CheckBox) view.getWidget(widgetName)).getEditorValue();
	}

	private Object getEnumBoxValue(String widgetName) {
		return ((EnumComboBoxField) view.getWidget(widgetName)).getEditorValue();
	}

}
