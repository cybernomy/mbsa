/*
 * PartnerMt.java
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
package com.mg.merp.reference.support.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.reference.BankAccountServiceLocal;
import com.mg.merp.reference.ContractorElectronicAddressServiceLocal;
import com.mg.merp.reference.ContractorPhoneServiceLocal;
import com.mg.merp.reference.PartnEmplLinkServiceLocal;
import com.mg.merp.reference.PartnerEmplServiceLocal;
import com.mg.merp.reference.PartnerServiceLocal;
import com.mg.merp.reference.model.BankAccount;
import com.mg.merp.reference.model.ContractorElectronicAddress;
import com.mg.merp.reference.model.ContractorPhone;
import com.mg.merp.reference.model.PartnEmplLink;
import com.mg.merp.reference.model.Partner;
import com.mg.merp.reference.model.PartnerEmpl;
import com.mg.merp.reference.support.Messages;


/**
 * Контроллер формы поддержки партнеров
 * 
 * @author leonova
 * @version $Id: PartnerMt.java,v 1.12 2008/05/21 10:55:56 alikaev Exp $
 */
public class PartnerMt extends DefaultMaintenanceForm implements MasterModelListener {
	private MaintenanceTableController bankFields;
	private BankAccountServiceLocal bankFieldsService;
	protected AttributeMap bankFieldsProperties = new LocalDataTransferObject();

	private MaintenanceTableController phone;
	private ContractorPhoneServiceLocal phoneService;
	protected AttributeMap phoneProperties = new LocalDataTransferObject();

	private MaintenanceTableController email;
	private ContractorElectronicAddressServiceLocal emailService;
	protected AttributeMap emailProperties = new LocalDataTransferObject();

	private MaintenanceTableController partnEmpl;
	private PartnerEmplServiceLocal partnEmplService;
	protected AttributeMap partnEmplProperties = new LocalDataTransferObject();

	private MaintenanceTableController partnEmplLink;
	private PartnEmplLinkServiceLocal partnEmplLinkService;
	protected AttributeMap partnEmplLinkProperties = new LocalDataTransferObject();

	public PartnerMt() throws Exception {
		setMasterDetail(true);
		addMasterModelListener(this);
		
		bankFieldsService = (BankAccountServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/BankAccount");
		bankFields = new MaintenanceTableController(bankFieldsProperties);
		bankFields.initController(bankFieldsService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from BankAccount ba %s where ba.Contractor = :contractor";
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();
			
			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("contractor");
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
				result.add(new TableEJBQLFieldDef(BankAccount.class, "Id", "ba.Id", true));				
				result.add(new TableEJBQLFieldDef(BankAccount.class, "Bank", "b", "left join ba.Bank as b", true));
				result.add(new TableEJBQLFieldDef(BankAccount.class, "Name", "ba.Name", false));
				result.add(new TableEJBQLFieldDef(BankAccount.class, "BankAccountType", "bat.Code", "left join ba.BankAccountType as bat", false));
				result.add(new TableEJBQLFieldDef(BankAccount.class, "Currency", "cur.Code", "left join ba.Currency as cur", false));
				result.add(new TableEJBQLFieldDef(BankAccount.class, "Account", "ba.Account", false));
				result.add(new TableEJBQLFieldDef(BankAccount.class, "IsDefault", "ba.IsDefault", false));
				result.add(new TableEJBQLFieldDef(BankAccount.class, "Bank.BIK", "b.BIK", false));
				result.add(new TableEJBQLFieldDef(BankAccount.class, "Bank.Branch", "b.Branch", false));
				result.add(new TableEJBQLFieldDef(BankAccount.class, "Bank.CorrAcc", "b.CorrAcc", false));				
				result.add(new TableEJBQLFieldDef(BankAccount.class, "Bank.Currency", "curacc.Code", "left join b.Currency as curacc", false));
				result.add(new TableEJBQLFieldDef(BankAccount.class, "Bank.Swift", "b.Swift", false));
				result.add(new TableEJBQLFieldDef(BankAccount.class, "Bank.Iban", "b.Iban", false));
				result.add(new TableEJBQLFieldDef(BankAccount.class, "Bank.Bsc", "b.Bsc", false));
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, bankFieldsService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});	

		addMasterModelListener(bankFields);

		phoneService = (ContractorPhoneServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/ContractorPhone");
		phone = new MaintenanceTableController(phoneProperties);
		phone.initController(phoneService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from ContractorPhone cp %s where cp.Contractor = :contractor";
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();
			
			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("contractor");
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
				result.add(new TableEJBQLFieldDef(ContractorPhone.class, "Id", "cp.Id", true));
				result.add(new TableEJBQLFieldDef(ContractorPhone.class, "PhoneKind", "pk.KName", "left join cp.PhoneKind pk", false));
				result.add(new TableEJBQLFieldDef(ContractorPhone.class, "AreaCode", "cp.AreaCode", true));
				result.add(new TableEJBQLFieldDef(ContractorPhone.class, "Phone", "cp.Phone", true));
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, phoneService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});			
		addMasterModelListener(phone);

		emailService = (ContractorElectronicAddressServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/ContractorElectronicAddress");
		email = new MaintenanceTableController(emailProperties);
		email.initController(emailService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from ContractorElectronicAddress cae %s where cae.Contractor = :contractor";
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();
			
			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("contractor");
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
				result.add(new TableEJBQLFieldDef(ContractorElectronicAddress.class, "Id", "cae.Id", true));
				result.add(new TableEJBQLFieldDef(ContractorElectronicAddress.class, "EaddressKind", "ek.Name", "left join cae.EaddressKind as ek", false));
				result.add(new TableEJBQLFieldDef(ContractorElectronicAddress.class, "Protocol", "cae.Protocol", false));
				result.add(new TableEJBQLFieldDef(ContractorElectronicAddress.class, "Address", "cae.Address", false));
				result.add(new TableEJBQLFieldDef(ContractorElectronicAddress.class, "IsActive", "cae.IsActive", false));				
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, emailService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});

		addMasterModelListener(email);

		partnEmplService = (PartnerEmplServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/PartnerEmpl");
		partnEmpl = new MaintenanceTableController(partnEmplProperties);
		partnEmpl.initController(partnEmplService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from PartnerEmpl pe %s where pe.Partner = :partner";
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();
			
			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("partner");
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
				result.add(new TableEJBQLFieldDef(PartnerEmpl.class, "Id", "pe.Id", true));
				result.add(new TableEJBQLFieldDef(PartnerEmpl.class, "Code", "pe.Code", false));
				result.add(new TableEJBQLFieldDef(PartnerEmpl.class, "NaturalPerson.Surname", "np.Surname", "left join pe.NaturalPerson as np", true));
				result.add(new TableEJBQLFieldDef(PartnerEmpl.class, "NaturalPerson.Name", "np.Name", false));
				result.add(new TableEJBQLFieldDef(PartnerEmpl.class, "NaturalPerson.Patronymic", "np.Patronymic", false));
				result.add(new TableEJBQLFieldDef(PartnerEmpl.class, "Office", "pe.Office", false));
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, partnEmplService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});

		addMasterModelListener(partnEmpl);

		partnEmplLinkService = (PartnEmplLinkServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/PartnEmplLink");
		partnEmplLink = new MaintenanceTableController(partnEmplLinkProperties);
		partnEmplLink.setMaintenanceForm("maintenanceFromPartner");
		partnEmplLink.initController(partnEmplLinkService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from PartnEmplLink pel %s where pel.Partner = :partner";
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();
			
			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("partner");
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
				result.add(new TableEJBQLFieldDef(PartnEmplLink.class, "Id", "pel.Id", true));
				result.add(new TableEJBQLFieldDef(PartnEmplLink.class, "Employees.NaturalPerson.Code", "e.Code", "left join pel.Employees.NaturalPerson as e", false));
				result.add(new TableEJBQLFieldDef(PartnEmplLink.class, "DateBegin", "pel.DateBegin", false));
				result.add(new TableEJBQLFieldDef(PartnEmplLink.class, "DateEnd", "pel.DateEnd", false));
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, partnEmplLinkService);
			}

			@Override
			public String getColumnName(int column) {
				return column == 1 ? Messages.getInstance().getMessage(Messages.EMPLOYESS_CODE) : super.getColumnName(column);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});
		addMasterModelListener(partnEmplLink);
		
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		bankFieldsProperties.put("Contractor", event.getEntity());
		phoneProperties.put("Contractor", event.getEntity());
		emailProperties.put("Contractor", event.getEntity());		
		partnEmplProperties.put("Partner", event.getEntity());
		partnEmplLinkProperties.put("Partner", event.getEntity());				
	}

	/**
	 * действие на  событие обновить почтовый адрес
	 * @param event
	 */
	public void onActionBuildAddress(WidgetEvent event) {
		PartnerServiceLocal servicePartner = (PartnerServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PartnerServiceLocal.SERVICE_NAME);
		servicePartner.getFullAddress((Partner) getEntity());
	}

	/**
	 * действие на  событие обновить юридический адрес
	 * @param event
	 */
	public void onActionBuildAddressLegal(WidgetEvent event) {
		PartnerServiceLocal servicePartner = (PartnerServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PartnerServiceLocal.SERVICE_NAME);
		servicePartner.getFullAddressLegal((Partner) getEntity());
	}
	
}
