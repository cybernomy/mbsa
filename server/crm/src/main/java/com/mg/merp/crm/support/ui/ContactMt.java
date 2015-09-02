/*
 * ContactMt.java
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
package com.mg.merp.crm.support.ui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.BusinessException;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultCompoundMaintenanceForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.crm.LinkedDocumentServiceLocal;
import com.mg.merp.crm.OperationServiceLocal;
import com.mg.merp.crm.model.Contact;
import com.mg.merp.crm.model.CrmAddressSource;
import com.mg.merp.crm.model.Operation;
import com.mg.merp.crm.support.Messages;
import com.mg.merp.reference.PersonAddressServiceLocal;
import com.mg.merp.reference.PersonElectronicAddressServiceLocal;
import com.mg.merp.reference.PersonPhoneServiceLocal;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.NaturalPerson;
import com.mg.merp.reference.model.Partner;
import com.mg.merp.reference.model.PersonAddress;
import com.mg.merp.reference.model.PersonElectronicAddress;
import com.mg.merp.reference.model.PersonPhone;

/**
 * Контроллер формы поддержки "Контактное лицо"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ContactMt.java,v 1.9 2008/03/12 12:53:47 alikaev Exp $
 */
public class ContactMt extends DefaultCompoundMaintenanceForm implements MasterModelListener {

	private MaintenanceTableController original;
	private LinkedDocumentServiceLocal originalService;
	protected AttributeMap originalProperties = new LocalDataTransferObject();

	private MaintenanceTableController operation;
	private OperationServiceLocal operationService;
	protected AttributeMap operationProperties = new LocalDataTransferObject();

	private MaintenanceTableController phone;
	private PersonPhoneServiceLocal phoneService;
	protected AttributeMap phoneProperties = new LocalDataTransferObject();

	private MaintenanceTableController email;
	private PersonElectronicAddressServiceLocal emailService;
	protected AttributeMap emailProperties = new LocalDataTransferObject();

	private PersonAddressServiceLocal personAddressService = (PersonAddressServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PersonAddressServiceLocal.LOCAL_SERVICE_NAME);
	
	private String index;
	private String country;
	private String region;
	private String district;
	private String place;

	private String street;
	private String house;
	private String building;
	private String room;
	private String fullAdress;

	public ContactMt()throws Exception{
		super();
		addMasterModelListener(this);
		
		originalService = (LinkedDocumentServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/crm/LinkedDocument"); //$NON-NLS-1$
		original = new MaintenanceTableController(originalProperties);
		original.initController(originalService, new LinkedDocumentMaintenanceEJBQLTableModel() {
			protected final String INIT_QUERY_TEXT = "select %s from LinkedDocument ld %s where ld.Contact = :contact"; //$NON-NLS-1$
			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();
				paramsName.add("contact"); //$NON-NLS-1$
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
			 * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		
		});
		addMasterModelListener(original);

		operationService = (OperationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/crm/Operation"); //$NON-NLS-1$
		operation = new MaintenanceTableController(operationProperties);
		operation.initController(operationService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from Operation op %s where op.Contact = :contact"; //$NON-NLS-1$
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("contact"); //$NON-NLS-1$
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
				result.add(new TableEJBQLFieldDef(Operation.class, "Id", "op.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Operation.class, "Code", "op.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Operation.class, "Relation", "rel.Code", "left join op.Relation as rel", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Operation.class, "Contact", "pcont.Surname", "left join op.Contact as cont left join cont.Person as pcont", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Operation.class, "Kind", "k.Code", "left join op.Kind as k", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Operation.class, "Priority", "pr.Code", "left join op.Priority as pr", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Operation.class, "Purpose", "pur.Code", "left join op.Purpose as pur", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Operation.class, "IsPlan", "op.IsPlan", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Operation.class, "CreateDate", "op.CreateDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Operation.class, "PlanDateFrom", "op.PlanDateFrom", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Operation.class, "PlanDateTill", "op.PlanDateTill", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Operation.class, "FactDateFrom", "op.FactDateFrom", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Operation.class, "FactDateTill", "op.FactDateTill", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Operation.class, "Status", "op.Status", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Operation.class, "State", "op.State", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Operation.class, "Curator", "pcur.Surname", "left join op.Curator as cur left join cur.Person as pcur", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Operation.class, "Responsible", "presp.Surname", "left join op.Responsible as resp left join resp.Person as presp", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, operationService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});
		addMasterModelListener(operation);

		phoneService = (PersonPhoneServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/PersonPhone"); //$NON-NLS-1$
		phone = new MaintenanceTableController(phoneProperties) {

			@Override
			protected void doAdd() {
				NaturalPerson naturalPerson = ((Contact) getEntity()).getPerson();
				if (naturalPerson == null)
					throw new BusinessException(Messages.getInstance().getMessage(Messages.NATURAL_PERSON_NOT_CHOOSE));
				else 
					phoneProperties.put("NaturalPerson", naturalPerson); //$NON-NLS-1$
				super.doAdd();
			}
			
		};
		phone.initController(phoneService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from PersonPhone pp %s where pp.NaturalPerson = :person"; //$NON-NLS-1$
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();
			
			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("person"); //$NON-NLS-1$
				paramsValue.add(((Contact) getEntity()).getPerson());					
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
				result.add(new TableEJBQLFieldDef(PersonPhone.class, "Id", "pp.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PersonPhone.class, "AreaCode", "pp.AreaCode", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PersonPhone.class, "Phone", "pp.Phone", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PersonPhone.class, "PhoneKind", "pk.KName", "left join pp.PhoneKind as pk", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

		emailService = (PersonElectronicAddressServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/PersonElectronicAddress"); //$NON-NLS-1$
		email = new MaintenanceTableController(emailProperties) {

			@Override
			protected void doAdd() {
				NaturalPerson naturalPerson = ((Contact) getEntity()).getPerson();
				if (naturalPerson == null)
					throw new BusinessException(Messages.getInstance().getMessage(Messages.NATURAL_PERSON_NOT_CHOOSE));
				else 
					emailProperties.put("NaturalPerson", naturalPerson); //$NON-NLS-1$
				super.doAdd();
			}
			
		};
		email.initController(emailService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from PersonElectronicAddress pea %s where pea.NaturalPerson = :person"; //$NON-NLS-1$
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("person"); //$NON-NLS-1$
				paramsValue.add(((Contact) getEntity()).getPerson());					
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
				result.add(new TableEJBQLFieldDef(PersonElectronicAddress.class, "Id", "pea.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PersonElectronicAddress.class, "EaddressKind", "ek.Name", "left join pea.EaddressKind as ek", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(PersonElectronicAddress.class, "Protocol", "pea.Protocol", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PersonElectronicAddress.class, "Address", "pea.Address", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PersonElectronicAddress.class, "IsActive", "pea.IsActive", false)); //$NON-NLS-1$ //$NON-NLS-2$
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
		addMasterModelListener(this);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		operationProperties.put("Contact", event.getEntity()); //$NON-NLS-1$
		originalProperties.put("Contact", event.getEntity()); //$NON-NLS-1$
		phoneProperties.put("NaturalPerson", ((Contact) event.getEntity()).getPerson()); //$NON-NLS-1$
		emailProperties.put("NaturalPerson", ((Contact) event.getEntity()).getPerson()); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		refreshAdressInfo();
		super.doOnRun();
	}

	/**
	 * Обработчик кнопки "Обновить адрес"  
	 * @param event - событие
	 */
	public void onActionRefreshAdressInfo(WidgetEvent event) {
		refreshAdressInfo();
	}
	
	private void refreshAdressInfo() {
		CrmAddressSource addressSource = (CrmAddressSource) getEntity().getAttribute("AddressSource"); //$NON-NLS-1$
		if(addressSource != null) {
			resetAdressInfo();
			if(CrmAddressSource.COMPANY.compareTo(addressSource) == 0)
				fillAdressInfoByCompany();
			else
				fillAdressInfoByPerson();
		}
	}

	private void fillAdressInfoByCompany() {
		Contractor contractor = (Contractor) getEntity().getAttribute("Contractor"); //$NON-NLS-1$
		if(contractor == null)
			return;
		
		Partner partner = ServerUtils.getPersistentManager().find(Partner.class, contractor.getId());
		if(partner != null) {
			fillAdressInfo(
					partner.getZip(),
					partner.getCountry() == null ? null : partner.getCountry().getCName(),
					partner.getRegion() == null ? null : partner.getRegion().getName(),
					partner.getDistrict() == null ? null : partner.getDistrict().getName(),
					partner.getPlace() == null ? null : partner.getPlace().getName(),
					partner.getStreet(),
					partner.getHouse(),
					partner.getBuilding(),
					partner.getRoom(),
					partner.getAddress());
		}
	}

	private void fillAdressInfoByPerson() {
		NaturalPerson person = (NaturalPerson) getEntity().getAttribute("Person"); //$NON-NLS-1$
		if(person != null) {
			PersonAddress personAddress = personAddressService.getActualPersonAdress(person, Calendar.getInstance().getTime());
			if(personAddress != null) {
				fillAdressInfo(
						personAddress.getPostIndex(),
						personAddress.getCountry() == null ? null : personAddress.getCountry().getCName(),
						personAddress.getRegionName(),
						personAddress.getDistrict() == null ? null : personAddress.getDistrict().getName(),
						personAddress.getPlace() == null ? null : personAddress.getPlace().getName(),
						personAddress.getStreet(),
						personAddress.getHouse(),
						personAddress.getBuilding(),
						personAddress.getRoom(),
						personAddress.getFullAddress());
			}
		}
	}

	private void fillAdressInfo(String index, String country, String region, String district, String place, String street, String house, String building, String room, String fullAdress) {
		this.index = index; 
		this.country = country; 
		this.region = region;
		this.district = district;
		this.place = place; 
		this.street = street;
		this.house = house;
		this.building = building;
		this.room = room;
		this.fullAdress = fullAdress;
	}
	
	private void resetAdressInfo() {
		this.index = null; 
		this.country = null; 
		this.region = null;
		this.district = null;
		this.place = null; 
		this.street = null;
		this.house = null;
		this.building = null;
		this.room = null;
		this.fullAdress = null;
	}

	public String getIndex() {
		return this.index;
	}

	public String getCountry() {
		return this.country;
	}

	public String getRegion() {
		return this.region;
	}

	public String getDistrict() {
		return this.district;
	}

	public String getPlace() {
		return this.place;
	}

	public String getStreet() {
		return this.street;
	}

	public String getHouse() {
		return this.house;
	}

	public String getBuilding() {
		return this.building;
	}

	public String getRoom() {
		return this.room;
	}

	public String getFullAdress() {
		return this.fullAdress;
	}

}
