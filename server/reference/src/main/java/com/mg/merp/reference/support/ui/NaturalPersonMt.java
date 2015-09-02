/*
 * NaturalPersonMt.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
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
import com.mg.framework.utils.StringUtils;
import com.mg.merp.reference.FamilyMemberServiceLocal;
import com.mg.merp.reference.FamilyStatusServiceLocal;
import com.mg.merp.reference.IdentDocServiceLocal;
import com.mg.merp.reference.NaturalPersonHistServiceLocal;
import com.mg.merp.reference.PersonAddressServiceLocal;
import com.mg.merp.reference.PersonElectronicAddressServiceLocal;
import com.mg.merp.reference.PersonPhoneServiceLocal;
import com.mg.merp.reference.model.FamilyMember;
import com.mg.merp.reference.model.FamilyStatus;
import com.mg.merp.reference.model.IdentDoc;
import com.mg.merp.reference.model.NaturalPerson;
import com.mg.merp.reference.model.NaturalPersonHist;
import com.mg.merp.reference.model.PersonAddress;
import com.mg.merp.reference.model.PersonElectronicAddress;
import com.mg.merp.reference.model.PersonPhone;


/**
 * @author leonova
 * @version $Id: NaturalPersonMt.java,v 1.8 2007/09/07 10:27:25 alikaev Exp $
 */
public class NaturalPersonMt extends DefaultMaintenanceForm implements MasterModelListener {
	private MaintenanceTableController familyStatus;
	private FamilyStatusServiceLocal familyStatusService;
	protected AttributeMap familyStatusProperties = new LocalDataTransferObject();

	private MaintenanceTableController familyMembers;
	private FamilyMemberServiceLocal familyMembersService;
	protected AttributeMap familyMembersProperties = new LocalDataTransferObject();

	private MaintenanceTableController addressPerson;
	private PersonAddressServiceLocal addressPersonService;
	protected AttributeMap addressPersonProperties = new LocalDataTransferObject();

	private MaintenanceTableController identDoc;
	private IdentDocServiceLocal identDocService;
	protected AttributeMap identDocProperties = new LocalDataTransferObject();

	private MaintenanceTableController personHist;
	private NaturalPersonHistServiceLocal personHistService;
	protected AttributeMap personHistProperties = new LocalDataTransferObject();

	private MaintenanceTableController phone;
	private PersonPhoneServiceLocal phoneService;
	protected AttributeMap phoneProperties = new LocalDataTransferObject();

	private MaintenanceTableController email;
	private PersonElectronicAddressServiceLocal emailService;
	protected AttributeMap emailProperties = new LocalDataTransferObject();

	public NaturalPersonMt() throws Exception {
		addMasterModelListener(this);
		setMasterDetail(true);

		familyStatusService = (FamilyStatusServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/FamilyStatus"); //$NON-NLS-1$
		familyStatus = new MaintenanceTableController(familyStatusProperties);
		familyStatus.initController(familyStatusService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from FamilyStatus fs %s where fs.NaturalPerson = :person"; //$NON-NLS-1$
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("person"); //$NON-NLS-1$
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
				result.add(new TableEJBQLFieldDef(FamilyStatus.class, "Id", "fs.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(FamilyStatus.class, "FamilyStatusKind", "fsk.KCode", "left join fs.FamilyStatusKind as fsk", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(FamilyStatus.class, "BeginDate", "fs.BeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, familyStatusService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});

		addMasterModelListener(familyStatus);

		familyMembersService = (FamilyMemberServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/FamilyMember"); //$NON-NLS-1$
		familyMembers = new MaintenanceTableController(familyMembersProperties);
		familyMembers.initController(familyMembersService, new DefaultMaintenanceEJBQLTableModel() {		
			private final String INIT_QUERY_TEXT = "select %s from FamilyMember fm %s where fm.NaturalPerson = :person"; //$NON-NLS-1$
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("person"); //$NON-NLS-1$
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
				result.add(new TableEJBQLFieldDef(FamilyMember.class, "Id", "fm.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(FamilyMember.class, "FamilyRelation", "fr.RCode", "left join fm.FamilyRelation as fr", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(FamilyMember.class, "Surname", "fm.Surname", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(FamilyMember.class, "FName", "fm.FName", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(FamilyMember.class, "Patronymic", "fm.Patronymic", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(FamilyMember.class, "Birthdate", "fm.Birthdate", false));				 //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, familyMembersService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});
		addMasterModelListener(familyMembers);

		addressPersonService = (PersonAddressServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/PersonAddress"); //$NON-NLS-1$
		addressPerson = new MaintenanceTableController(addressPersonProperties);
		addressPerson.initController(addressPersonService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from PersonAddress pa %s where pa.NaturalPerson = :person"; //$NON-NLS-1$
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("person"); //$NON-NLS-1$
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
				result.add(new TableEJBQLFieldDef(PersonAddress.class, "Id", "pa.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PersonAddress.class, "AddressKind", "pa.AddressKind", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PersonAddress.class, "BeginDate", "pa.BeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PersonAddress.class, "ZipCode", "zc.Code", "left join pa.ZipCode as zc", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(PersonAddress.class, "Country", "c.CCode", "left join pa.Country as c", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(PersonAddress.class, "Region", "r.Name", "left join pa.Region as r", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(PersonAddress.class, "District", "d.Name", "left join pa.District as d", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(PersonAddress.class, "Place", "pl.Name", "left join pa.Place as pl", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(PersonAddress.class, "Street", "pa.Street", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PersonAddress.class, "House", "pa.House", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PersonAddress.class, "Building", "pa.Building", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PersonAddress.class, "Room", "pa.Room", false)); //$NON-NLS-1$ //$NON-NLS-2$

				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, addressPersonService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});
		addMasterModelListener(addressPerson);	

		identDocService = (IdentDocServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/IdentDoc"); //$NON-NLS-1$
		identDoc = new MaintenanceTableController(identDocProperties);
		identDoc.initController(identDocService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from IdentDoc idoc %s where idoc.NaturalPerson = :person"; //$NON-NLS-1$
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("person"); //$NON-NLS-1$
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
				result.add(new TableEJBQLFieldDef(IdentDoc.class, "Id", "idoc.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(IdentDoc.class, "IsBasic", "idoc.IsBasic", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(IdentDoc.class, "SeriesLeft", "idoc.SeriesLeft", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(IdentDoc.class, "SeriesDivider", "idoc.SeriesDivider", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(IdentDoc.class, "SeriesRight", "idoc.SeriesRight", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(IdentDoc.class, "DocNumber", "idoc.DocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(IdentDoc.class, "WhoIssued", "idoc.WhoIssued", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(IdentDoc.class, "WhenIssued", "idoc.WhenIssued", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(IdentDoc.class, "ActiveTill", "idoc.ActiveTill", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(IdentDoc.class, "CitizenShip", "idoc.CitizenShip", false));				 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(IdentDoc.class, "Birthdate", "idoc.Birthdate", false));	 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(IdentDoc.class, "BirthPlaceCountry", "idoc.BirthPlaceCountry", false));	 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(IdentDoc.class, "BirthPlaceRegion", "idoc.BirthPlaceRegion", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(IdentDoc.class, "BirthPlaceDistrict", "idoc.BirthPlaceDistrict", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(IdentDoc.class, "BirthPlaceCity", "idoc.BirthPlaceCity", false));					 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(IdentDoc.class, "BirthPlaceOkato", "idoc.BirthPlaceOkato", false));	 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(IdentDoc.class, "IdentDocKind", "idk.KCode", "left join idoc.IdentDocKind as idk", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(IdentDoc.class, "Nationality", "nat.NName", "left join idoc.Nationality as nat", false));				 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, identDocService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});

		addMasterModelListener(identDoc);	

		personHistService = (NaturalPersonHistServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/NaturalPersonHist"); //$NON-NLS-1$
		personHist = new MaintenanceTableController(personHistProperties) {

			@Override
			protected void doRefresh() {
					refreshModel();
					super.doRefresh();
			}

		};
		personHist.initController(personHistService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from NaturalPersonHist nph where nph.NaturalPerson = :person order by nph.ActDate desc"; //$NON-NLS-1$
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsValue.add(getEntity());					
				paramsName.add("person"); //$NON-NLS-1$
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
				result.add(new TableEJBQLFieldDef(NaturalPersonHist.class, "Id", "nph.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(NaturalPersonHist.class, "ActDate", "nph.ActDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(NaturalPersonHist.class, "Surname", "nph.Surname", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(NaturalPersonHist.class, "Name", "nph.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(NaturalPersonHist.class, "Patronymic", "nph.Patronymic", false));				 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(NaturalPersonHist.class, "Inn", "nph.Inn", false)); //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, personHistService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});

		addMasterModelListener(personHist);

		phoneService = (PersonPhoneServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/PersonPhone"); //$NON-NLS-1$
		phone = new MaintenanceTableController(phoneProperties);
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
				result.add(new TableEJBQLFieldDef(PersonPhone.class, "Id", "pp.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PersonPhone.class, "AreaCode", "pp.AreaCode", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PersonPhone.class, "Phone", "pp.Phone", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PersonPhone.class, "PhoneKind", "pk.KName", "left join pp.PhoneKind as pk", false));				 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
		email = new MaintenanceTableController(emailProperties);
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
				result.add(new TableEJBQLFieldDef(PersonElectronicAddress.class, "Id", "pea.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PersonElectronicAddress.class, "EaddressKind", "ek.Name", "left join pea.EaddressKind as ek", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(PersonElectronicAddress.class, "Protocol", "pea.Protocol", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PersonElectronicAddress.class, "Address", "pea.Address", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(PersonElectronicAddress.class, "IsActive", "pea.IsActive", false));				 //$NON-NLS-1$ //$NON-NLS-2$
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

	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnSave()
	 */
	@Override
	protected void doOnSave() {
		//При сохранении проверяем заполнены ли обязательные поля физического лица
		//Если да  то закрываем для редактирования поля физического лица
		if (((NaturalPerson) getEntity()).getActDate() != null && ((NaturalPerson) getEntity()).getSurname() != null 
				&& StringUtils.EMPTY_STRING.compareTo(((NaturalPerson) getEntity()).getSurname()) != 0)
			setReadOnlyFieldNaturalPerson(true);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnAdd()
	 */
	@Override
	protected void doOnAdd() {
		super.doOnAdd();
		setReadOnlyFieldNaturalPerson(false);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnClone()
	 */
	@Override
	protected void doOnClone() {
		setReadOnlyFieldNaturalPerson(true);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnEdit()
	 */
	@Override
	protected void doOnEdit() {
		setReadOnlyFieldNaturalPerson(true);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnView()
	 */
	@Override
	protected void doOnView() {
		setReadOnlyFieldNaturalPerson(true);
		super.doOnView();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		familyStatusProperties.put("NaturalPerson", event.getEntity()); //$NON-NLS-1$
		familyMembersProperties.put("NaturalPerson", event.getEntity()); //$NON-NLS-1$
		addressPersonProperties.put("NaturalPerson", event.getEntity()); //$NON-NLS-1$
		identDocProperties.put("NaturalPerson", event.getEntity()); //$NON-NLS-1$
		personHistProperties.put("NaturalPerson", event.getEntity()); //$NON-NLS-1$
		phoneProperties.put("NaturalPerson", event.getEntity());	 //$NON-NLS-1$
		emailProperties.put("NaturalPerson", event.getEntity());	 //$NON-NLS-1$
	}

	/**
	 * закрываем/открываем для редактирования поля физического лица
	 *  
	 * @param isReadOnly признак 
	 */
	private void setReadOnlyFieldNaturalPerson(boolean isReadOnly){
		view.getWidget("Surname").setReadOnly(isReadOnly); //$NON-NLS-1$
		view.getWidget("Name").setReadOnly(isReadOnly); //$NON-NLS-1$
		view.getWidget("Patronymic").setReadOnly(isReadOnly); //$NON-NLS-1$
		view.getWidget("Inn").setReadOnly(isReadOnly); //$NON-NLS-1$
		view.getWidget("ActDate").setReadOnly(isReadOnly); //$NON-NLS-1$
	}

}