/*
 * RelationMt.java
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projection;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.orm.ResultTransformer;
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
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.MiscUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.crm.ContactServiceLocal;
import com.mg.merp.crm.DocumentServiceLocal;
import com.mg.merp.crm.LinkedDocumentServiceLocal;
import com.mg.merp.crm.OfferServiceLocal;
import com.mg.merp.crm.OperationServiceLocal;
import com.mg.merp.crm.RelationServiceLocal;
import com.mg.merp.crm.model.Contact;
import com.mg.merp.crm.model.Offer;
import com.mg.merp.crm.model.Operation;
import com.mg.merp.crm.model.Relation;
import com.mg.merp.docflow.support.DocFlowHelper;
import com.mg.merp.document.Document;
import com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceForm;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocHeadModel;
import com.mg.merp.document.model.DocSection;
import com.mg.merp.document.support.DocumentUtils;
import com.mg.merp.reference.ContractorElectronicAddressServiceLocal;
import com.mg.merp.reference.ContractorPhoneServiceLocal;
import com.mg.merp.reference.PersonElectronicAddressServiceLocal;
import com.mg.merp.reference.PersonPhoneServiceLocal;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.model.ContractorElectronicAddress;
import com.mg.merp.reference.model.ContractorPhone;
import com.mg.merp.reference.model.ElectronicAddressKind;
import com.mg.merp.reference.model.NaturalPerson;
import com.mg.merp.reference.model.Partner;
import com.mg.merp.reference.model.PersonAddress;
import com.mg.merp.reference.model.PersonAddressKind;
import com.mg.merp.reference.model.PersonElectronicAddress;
import com.mg.merp.reference.model.PersonPhone;
import com.mg.merp.reference.model.PhoneKind;
import com.mg.merp.reference.model.ProtokolKind;
import com.mg.merp.reference.model.ZipCode;
import com.mg.merp.settlement.ContractorCardServiceLocal;
import com.mg.merp.settlement.model.ContractorCard;

/**
 * Контроллер формы поддержки бизнес-компонента "Деловые отношения" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @author Konstantin S. Alikaev
 * @version $Id: RelationMt.java,v 1.13 2008/03/24 15:13:39 alikaev Exp $
 */
public class RelationMt extends DefaultMaintenanceForm implements MasterModelListener {

	private DefaultTableController phoneTable;
	private DefaultTableController eAdressTable;

	private DefaultTableController contractorCardTable;
	private Integer selectedCardId;

	private MaintenanceTableController original;
	private LinkedDocumentServiceLocal originalService;
	protected AttributeMap originalProperties = new LocalDataTransferObject();

	private MaintenanceTableController offer;
	private OfferServiceLocal offerService;
	protected AttributeMap offerProperties = new LocalDataTransferObject();

	private MaintenanceTableController operation;
	private OperationServiceLocal operationService;
	protected AttributeMap operationProperties = new LocalDataTransferObject();

	private DefaultTableController contact;
	private ContactServiceLocal contactService;
	protected AttributeMap contactProperties = new LocalDataTransferObject();
	private List<String> contactParamsName = new ArrayList<String>();
	private List<Object> contactParamsValue = new ArrayList<Object>();
	private Integer selectedContactLinkId;

	private DefaultTableController documents;
	private DocumentServiceLocal documentsService;	
	protected AttributeMap documentsProperties = new LocalDataTransferObject();
	private Integer selectedDocumentId;

	private MaintenanceTableController relationIssue;
	private RelationServiceLocal relationIssuesService;	
	protected AttributeMap relationIssueProperties = new LocalDataTransferObject();

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

	public RelationMt() throws Exception {
		super();
		setMasterDetail(true);
		phoneTable = new DefaultTableController(new PhoneListModel());
		eAdressTable = new DefaultTableController(new ElectronicAdressTableModel());

		contractorCardTable = new DefaultTableController(new DefaultEJBQLTableModel() { 
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

			protected String createQueryText() {
				String INIT_QUERY_TEXT = "select %s from ContractorCard cc %s where cc.Contractor = :contractor"; //$NON-NLS-1$
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();
				paramsName.add("contractor"); //$NON-NLS-1$
				if(getIsLeagalPerson())
					paramsValue.add(getLegalPerson());
				else
					paramsValue.add(null);
				return String.format(INIT_QUERY_TEXT, fieldsList, fromList);		
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
			 */
			@Override
			public void setSelectedRows(int[] rows) {
				if (rows.length == 0)
					selectedCardId = null;
				else {
					Object[] row = getRowList().get(rows[0]);
					selectedCardId = (Integer) row[0];
				}
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(ContractorCard.class, "Id", "cc.Id", true));  //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(ContractorCard.class, "OrgUnit", "ou.FullName", "left join cc.OrgUnit as ou", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(ContractorCard.class, "Contractor", "c.FullName", "left join cc.Contractor as c", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(ContractorCard.class, "TotalIncome", "cc.TotalIncome", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(ContractorCard.class, "TotalExpenses", "cc.TotalExpenses", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(ContractorCard.class, "DebitorInDebLimit", "cc.DebitorInDebLimit", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(ContractorCard.class, "CreditorInDebLimit", "cc.CreditorInDebLimit", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(ContractorCard.class, "PlanIncome", "cc.PlanIncome", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(ContractorCard.class, "PlanExpenses", "cc.PlanExpenses", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(ContractorCard.class, "DebitorInDebSum", "cc.DebitorInDebSum", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(ContractorCard.class, "CreditorInDebSum", "cc.CreditorInDebSum", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(ContractorCard.class, "PlanDebitorInDebSum", "cc.PlanDebitorInDebSum", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(ContractorCard.class, "PlanCreditorInDebSum", "cc.PlanCreditorInDebSum", false)); //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, getContractorCardService());
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});
		addMasterModelListener(contractorCardTable);

		originalService = (LinkedDocumentServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/crm/LinkedDocument"); //$NON-NLS-1$
		original = new MaintenanceTableController(originalProperties);
		original.initController(originalService, new LinkedDocumentMaintenanceEJBQLTableModel() {
			protected final String INIT_QUERY_TEXT = "select %s from LinkedDocument ld %s where ld.Relation = :relation"; //$NON-NLS-1$
			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();
				paramsName.add("relation"); //$NON-NLS-1$
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
		});
		addMasterModelListener(original);

		offerService = (OfferServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/crm/Offer"); //$NON-NLS-1$
		offer = new MaintenanceTableController(offerProperties);
		offer.initController(offerService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from Offer o %s where o.Relation = :relation"; //$NON-NLS-1$
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("relation"); //$NON-NLS-1$
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
				result.add(new TableEJBQLFieldDef(Offer.class, "Id", "o.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Offer.class, "Code", "o.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Offer.class, "Name", "o.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Offer.class, "OfferDate", "o.OfferDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Offer.class, "ValidUntil", "o.ValidUntil", false));				 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Offer.class, "Status", "st.Code", "left join o.Status as st", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Offer.class, "SuccessReason", "sr.Code", "left join o.SuccessReason as sr", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Offer.class, "FailReason", "fr.Code", "left join o.FailReason as fr", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Offer.class, "Kind", "k.Code", "left join o.Kind as k", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Offer.class, "Forecast", "f.Code", "left join o.Forecast as f", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Offer.class, "Responsible", "pres.Surname", "left join o.Responsible as res left join res.Person as pres", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, offerService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});
		addMasterModelListener(offer);

		operationService = (OperationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/crm/Operation"); //$NON-NLS-1$
		operation = new MaintenanceTableController(operationProperties);
		operation.initController(operationService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from Operation op %s where op.Relation = :relation"; //$NON-NLS-1$
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("relation"); //$NON-NLS-1$
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
				result.add(new TableEJBQLFieldDef(Operation.class, "State", "op.State", false));				 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Operation.class, "Curator", "pcur.Surname", "left join op.Curator as cur left join cur.Person as pcur", false));	 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Operation.class, "Responsible", "presp.Surname", "left join op.Responsible as resp left join resp.Person as presp", false));			 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

		contact = new DefaultTableController(new DefaultEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from Contact c left join c.ContactLinks cl %s where cl.Id.CrmRelation.Id = :relation"; //$NON-NLS-1$

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				contactParamsName.clear();
				contactParamsValue.clear();	
				contactParamsName.add("relation"); //$NON-NLS-1$
				if(getEntity() != null)
					contactParamsValue.add(getEntity().getAttribute("Id")); //$NON-NLS-1$
				else
					contactParamsValue.add(null);

				return String.format(INIT_QUERY_TEXT, fieldsList, fromList);		
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
			 */
			@Override
			public void setSelectedRows(int[] rows) {
				if (rows.length == 0)
					selectedContactLinkId = null;
				else {
					Object[] row = getRowList().get(rows[0]);
					selectedContactLinkId = (Integer) row[0];
				}
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(Contact.class, "Id", "c.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Contact.class, "Person", "pcont.Surname", "left join c.Person as pcont", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Contact.class, "Contractor", "contr.Code", "left join c.Contractor as contr", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Contact.class, "Curator", "pcur.Surname", "left join c.Curator as cur left join cur.Person as pcur", false));	 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Contact.class, "Responsible", "presp.Surname", "left join c.Responsible as resp left join resp.Person as presp", false));			 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Contact.class, "Priority", "c.Priority", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Contact.class, "IsDefault", "c.IsDefault", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Contact.class, "ThePosition", "pos.Name", "left join c.ThePosition as pos", false));				 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Contact.class, "IsRetired", "c.IsRetired", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Contact.class, "NickName", "c.NickName", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Contact.class, "PersonTitle", "pt.Code", "left join c.PersonTitle as pt", false));				 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Contact.class, "AddressSource", "c.AddressSource", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Contact.class, "Comments", "c.Comments", false));				 //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, contactService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), contactParamsName.toArray(new String[contactParamsName.size()]), contactParamsValue.toArray(new Object[contactParamsValue.size()]));
			}
		});
		addMasterModelListener(contact);


		//documentsProperties.put("restrictionForm", "com/mg/merp/crm/resources/ContactRest.mfd.xml"); //$NON-NLS-1$ //$NON-NLS-2$
		documents = new DefaultTableController(new DefaultEJBQLTableModel() {
			private String queryText = "select distinct %s from DocHead d, %s %s"; //$NON-NLS-1$
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("contr"); //$NON-NLS-1$
				StringBuilder whereText = new StringBuilder(" where "); //$NON-NLS-1$
				if ((Boolean) getEntity().getAttribute("IsLegalPerson")) { //$NON-NLS-1$
					fromList = " Partner p " + fromList;
					whereText.append("d.To = :contr or d.From = :contr"); //$NON-NLS-1$
					paramsValue.add(getEntity().getAttribute("LegalPerson"));	 //$NON-NLS-1$
				} 
				else {
					fromList = " Employees e " + fromList;
					whereText.append("(d.To = e or d.From = e) and e.NaturalPerson = :contr"); //$NON-NLS-1$
					paramsValue.add(getEntity().getAttribute("NaturalPerson")); //$NON-NLS-1$
				}	
				return String.format(queryText, fieldsList, fromList, whereText);		
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(DocHead.class, "Id", "d.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(DocHead.class, "DocSection", "d.DocSection.DSName", false));				 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(DocHead.class, "DocType", "d.DocType.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(DocHead.class, "DocNumber", "d.DocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(DocHead.class, "DocDate", "d.DocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(DocHead.class, "Currency", "d.Currency.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(DocHead.class, "CurCource", "d.CurCource", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(DocHead.class, "SumNat", "d.SumNat", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(DocHead.class, "SumCur", "d.SumCur", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(DocHead.class, "To", "t.Code", "left join d.To as t", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(DocHead.class, "From", "f.Code", "left join d.From as f", false));				 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, documentsService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
			
			@Override
			public void setSelectedRows(int[] rows) {
				if (rows.length == 0)
					selectedDocumentId = null;
				else {
					Object[] row = getRowList().get(rows[0]);
					selectedDocumentId = (Integer) row[0];
				}
			}
			
		});
		addMasterModelListener(documents);

		relationIssue = new MaintenanceTableController(relationIssueProperties);
		relationIssuesService = (RelationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/crm/Relation"); //$NON-NLS-1$
		relationIssue.initController(relationIssuesService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from Relation rel %s where rel.Parent = :relation"; //$NON-NLS-1$
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("relation"); //$NON-NLS-1$
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
				result.add(new TableEJBQLFieldDef(Relation.class, "Id", "rel.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Relation.class, "Code", "rel.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Relation.class, "Name", "rel.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, relationIssuesService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});		
		addMasterModelListener(relationIssue);
		addMasterModelListener(this);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		refreshPhoneTable();
		refreshEAdressTable();
		offerProperties.put("Relation", event.getEntity());	 //$NON-NLS-1$
		operationProperties.put("Relation", event.getEntity()); //$NON-NLS-1$
		contactParamsName.add("relation"); //$NON-NLS-1$
		contactParamsValue.add(getEntity().getAttribute("Id")); //$NON-NLS-1$
		originalProperties.put("Relation", event.getEntity()); //$NON-NLS-1$
		relationIssueProperties.put("Parent", event.getEntity()); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnView()
	 */
	@Override
	protected void doOnView() {
		super.doOnView();
		setEnableDocumentPopupMenu(false);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnAdd()
	 */
	@Override
	protected void doOnAdd() {
		super.doOnAdd();
		setEnableDocumentPopupMenu(false);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doSetDependentReadOnly(boolean)
	 */
	@Override
	protected void doSetDependentReadOnly(boolean readOnly) {
		super.doSetDependentReadOnly(readOnly);
		setEnableDocumentPopupMenu(!readOnly);
	}

	private void setEnableDocumentPopupMenu(boolean isEnable) {
		PopupMenu popupMenu = view.getWidget("documents").getPopupMenu(); //$NON-NLS-1$
		popupMenu.getMenuItem("addDocument").setEnabled(isEnable); //$NON-NLS-1$
		popupMenu.getMenuItem("editDocument").setEnabled(isEnable); //$NON-NLS-1$
		popupMenu.getMenuItem("deleteDocument").setEnabled(isEnable); //$NON-NLS-1$
		popupMenu.getMenuItem("editSpecification").setEnabled(isEnable); //$NON-NLS-1$
		popupMenu.getMenuItem("executeDocFlow").setEnabled(isEnable); //$NON-NLS-1$
		popupMenu.getMenuItem("rollBackDocFlow").setEnabled(isEnable); //$NON-NLS-1$
	}

	/**
	 * Обновить таблицу телефонов
	 */
	private void refreshPhoneTable() {
		List<PhoneItem> phoneItems = new ArrayList<PhoneItem>();
		Projection projection = Projections.projectionList(Projections.property("Id"), Projections.property("AreaCode"), Projections.property("Phone"), Projections.property("PhoneKind")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		if(!getIsLeagalPerson()) {
			phoneItems = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(PersonPhone.class)
					.setProjection(projection)
					.add(Restrictions.eq("NaturalPerson", getNaturalPerson())) //$NON-NLS-1$
					.setResultTransformer(new ResultTransformer<PhoneItem>() {

						public PhoneItem transformTuple(Object[] tuple, String[] aliases) {
							String phoneKindStr = ((PhoneKind) tuple[3]) == null ? "" : ((PhoneKind) tuple[3]).getKName();  //$NON-NLS-1$
							return new PhoneItem((Integer) tuple[0], (String) tuple[1], (String) tuple[2], phoneKindStr);
						}
					}));
		}
		else {
			phoneItems = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(ContractorPhone.class)
					.setProjection(projection)
					.add(Restrictions.eq("Contractor", getLegalPerson())) //$NON-NLS-1$
					.setResultTransformer(new ResultTransformer<PhoneItem>() {

						public PhoneItem transformTuple(Object[] tuple, String[] aliases) {
							PhoneKind phoneKind = (PhoneKind) tuple[3];
							String phoneKindStr = "";  //$NON-NLS-1$
							if(phoneKind != null)
								phoneKindStr = phoneKind.getKName();  
							return new PhoneItem((Integer) tuple[0], (String) tuple[1], (String) tuple[2], phoneKindStr);
						}
					}));
		}
		((PhoneListModel) phoneTable.getModel()).setPhoneListModel(phoneItems);
		((PhoneListModel) phoneTable.getModel()).fireModelChange();
	}

	/**
	 * Обновить таблицу электронных адресов
	 */
	private void refreshEAdressTable() {
		List<ElectronicAdressItem> eAdressItems = new ArrayList<ElectronicAdressItem>();
		Projection projection = Projections.projectionList(Projections.property("Id"), Projections.property("EaddressKind"), Projections.property("Protocol"), Projections.property("Address"), Projections.property("IsActive")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		if(!getIsLeagalPerson()) {
			eAdressItems = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(PersonElectronicAddress.class)
					.setProjection(projection)
					.add(Restrictions.eq("NaturalPerson", getNaturalPerson())) //$NON-NLS-1$
					.setResultTransformer(new ResultTransformer<ElectronicAdressItem>() {

						public ElectronicAdressItem transformTuple(Object[] tuple, String[] aliases) {
							String adressKindStr = (ElectronicAddressKind) tuple[1] == null ? "" : ((ElectronicAddressKind) tuple[1]).getName();  //$NON-NLS-1$
							String protocolStr = (ProtokolKind) tuple[2] == null ? "" : MiscUtils.getEnumTextRepresentation((ProtokolKind) tuple[2]); //$NON-NLS-1$
							return new ElectronicAdressItem((Integer) tuple[0], adressKindStr, protocolStr, (String) tuple[3], (Boolean) tuple[4]);
						}
					}));

		}
		else {
			eAdressItems = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(ContractorElectronicAddress.class)
					.setProjection(projection)
					.add(Restrictions.eq("Contractor", getLegalPerson())) //$NON-NLS-1$
					.setResultTransformer(new ResultTransformer<ElectronicAdressItem>() {

						public ElectronicAdressItem transformTuple(Object[] tuple, String[] aliases) {
							String adressKindStr = (ElectronicAddressKind) tuple[1] == null ? "" : ((ElectronicAddressKind) tuple[1]).getName();  //$NON-NLS-1$
							String protocolStr = (ProtokolKind) tuple[2] == null ? "" : MiscUtils.getEnumTextRepresentation((ProtokolKind) tuple[2]); //$NON-NLS-1$
							return new ElectronicAdressItem((Integer) tuple[0], adressKindStr, protocolStr, (String) tuple[3], (Boolean) tuple[4]);
						}
					}));

		}
		((ElectronicAdressTableModel) eAdressTable.getModel()).setElectronicAdressModel(eAdressItems);
		((ElectronicAdressTableModel) eAdressTable.getModel()).fireModelChange();
	}

	/**
	 * Обновить таблицу карточек расчетов 
	 */
	private void refreshContractorCardTable() {
		contractorCardTable.getModel().load();
	}

	/**
	 * Обновить таблицу контакных лиц 
	 */
	private void refreshContactPersonTable() {
		contact.getModel().load();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doBeforeOutput()
	 */
	@Override
	protected void doBeforeOutput() {
		getAdressInformation();
	}

	/**
	 * Получить адрес (юр.лица или физ.лица)  
	 */
	private void getAdressInformation() {
		resetAdressInformation();
		if (!getIsLeagalPerson()) { 
			if(getNaturalPerson() == null)
				return;
			List<PersonAddress> naturalPersonAdreses = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(PersonAddress.class)
					.add(Restrictions.eq("NaturalPerson", getNaturalPerson()))); //$NON-NLS-1$ //$NON-NLS-2$

			if(!naturalPersonAdreses.isEmpty()) {
				PersonAddress naturalPersonAdress = null;
				if(naturalPersonAdreses.size() > 1)
					for (PersonAddress address : naturalPersonAdreses) {
						if(address.getAddressKind() == PersonAddressKind.REGISTER)
							naturalPersonAdress = address; 
					}
				else
					naturalPersonAdress = naturalPersonAdreses.get(0); 
				ZipCode zipCode = naturalPersonAdress.getZipCode();
				if(zipCode != null) {
					index = naturalPersonAdress.getZipCode().getCode();
					if(zipCode.getCountry() != null)
						country = naturalPersonAdress.getZipCode().getCountry().getCName();
					if(zipCode.getRegion() != null)
						region = naturalPersonAdress.getZipCode().getRegion().getName();
					if(zipCode.getDistrict() != null)
						district = naturalPersonAdress.getZipCode().getDistrict().getName();
					if(zipCode.getPlace() != null)
						place = naturalPersonAdress.getZipCode().getPlace().getName();
				}
				street = naturalPersonAdress.getStreet();
				house = naturalPersonAdress.getHouse();
				building = naturalPersonAdress.getBuilding();
				room = naturalPersonAdress.getRoom();
				fullAdress = naturalPersonAdress.getFullAddress();
			}
		}
		else {
			if(getLegalPerson() == null)
				return;
			Partner partner = (Partner) ServerUtils.getPersistentManager().find(Partner.class, ((Contractor) getLegalPerson()).getId());
			if(partner != null) {
				ZipCode zipCode = partner.getZipCode();
				if(zipCode != null) {
					index = partner.getZipCode().getCode();
					if(zipCode.getCountry() != null)
						country = partner.getZipCode().getCountry().getCName();
					if(zipCode.getRegion() != null)
						region = partner.getZipCode().getRegion().getName();
					if(zipCode.getDistrict() != null)
						district = partner.getZipCode().getDistrict().getName();
					if(zipCode.getPlace() != null)
						place = partner.getZipCode().getPlace().getName();
				}
				street = partner.getStreet();
				house = partner.getHouse();
				building = partner.getBuilding();
				room = partner.getRoom();
				fullAdress = partner.getAddress();
			}
		}
	}

	/**
	 * Сбросить значения полей адреса в NULL
	 */
	private void resetAdressInformation() {
		index = null;
		country = null;
		region = null;
		district = null;
		place = null;
		street = null;
		house = null;
		building = null;
		room = null;
		fullAdress = null;
	}

	/**
	 * Обработка события изменения типа лица (юридичекое/физическое)
	 * @param event - событие
	 */
	public void onActionPersonTypeChanged(WidgetEvent event) {
		view.flushForm();
		getAdressInformation();
		refreshPhoneTable();
		refreshEAdressTable();
		refreshContractorCardTable();
	}

	/**
	 * Обработка события добавления контактного лица
	 * @param event - событие
	 * @throws Exception
	 */
	public void onActionAddContactPerson(WidgetEvent event) throws Exception {
		if(getEntity().getAttribute("Id") == null) //$NON-NLS-1$
			return;
		SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.crm.support.ui.ContactSearchHelp"); //$NON-NLS-1$
		searchHelp.addSearchHelpListener(new SearchHelpListener() {
			public void searchPerformed(SearchHelpEvent event) {
				getContactService().linkRelation((Contact) event.getItems()[0], (Relation) getEntity());
				refreshContactPersonTable();
			}

			public void searchCanceled(SearchHelpEvent event) {
				//do nothing
			}
		});
		searchHelp.search();
	}

	/**
	 * Обработка события удаления контактного лица
	 * @param event - событие
	 * @throws Exception
	 */
	public void onActionRemoveContactPerson(WidgetEvent event) throws Exception {
		if(selectedContactLinkId != null && (Relation)getEntity() != null) {
			Contact contact = (Contact) ServerUtils.getPersistentManager().find(Contact.class, selectedContactLinkId);
			getContactService().unLinkRelation(contact, (Relation) getEntity());
			refreshContactPersonTable();
		}
	}

	/**
	 * Обработка события просмотра контактного лица
	 * @param event - событие
	 * @throws Exception
	 */
	public void onActionShowContactPerson(WidgetEvent event) throws Exception {
		if(selectedContactLinkId != null)
			MaintenanceHelper.view(getContactService(), selectedContactLinkId, null, null);
	}

	/**
	 * Обработка события изменения контактного лица
	 * @param event - событие
	 * @throws Exception
	 */
	public void onActionEditContactPerson(WidgetEvent event) throws Exception {
		if(selectedContactLinkId != null)
			MaintenanceHelper.edit(getContactService(), selectedContactLinkId, null, new MaintenanceFormActionListener() {

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
					refreshContactPersonTable();
				}
			});
	}

	/**
	 * Обработка события обновления контактной информации (телефоны/электронные адреса)
	 * @param event - событие
	 * @throws ApplicationException
	 */
	public void onActionRefreshContact(WidgetEvent event) throws ApplicationException {
		refreshPhoneTable();
		refreshEAdressTable();
	}

	/**
	 * Обработка события добавления телефона
	 * @param event - событие
	 * @throws ApplicationException
	 */
	public void onActionAddPhone(WidgetEvent event) throws ApplicationException {
		if(getIsLeagalPerson()) 
			AddPhoneToLegalPerson(getLegalPerson());
		else
			AddPhoneToNaturalPerson(getNaturalPerson());
	}

	/**
	 * Добавить телефон физ.лицу
	 */	
	private void AddPhoneToNaturalPerson(NaturalPerson naturalPerson) {
		if(naturalPerson == null)
			return;
		PersonPhone personPhone = getNaturalPersonPhoneService().initialize();
		personPhone.setNaturalPerson(naturalPerson);
		MaintenanceHelper.add(getNaturalPersonPhoneService(), personPhone, null, new MaintenanceFormActionListener() {

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
				refreshPhoneTable();
			}
		});
	}

	/**
	 * Добавить телефон юр.лицу
	 */
	private void AddPhoneToLegalPerson(Contractor legalPerson) {
		if(legalPerson == null)
			return;
		ContractorPhone legalPersonPhone = getLegalPersonPhoneService().initialize();
		legalPersonPhone.setContractor(legalPerson);
		MaintenanceHelper.add(getLegalPersonPhoneService(), legalPersonPhone, null, new MaintenanceFormActionListener() {

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
				refreshPhoneTable();
			}
		});
	}

	/**
	 * Обработка события удаления телефона
	 * @param event - событие
	 * @throws ApplicationException
	 */
	public void onActionRemovePhone(WidgetEvent event) throws ApplicationException {
		Integer phoneId = ((PhoneListModel) this.phoneTable.getModel()).getSelectedPhoneId();
		if(getIsLeagalPerson()) 
			RemovePhoneFromLegalPerson(phoneId);
		else
			RemovePhoneFromNaturalPerson(phoneId);
		refreshPhoneTable();
	}

	/**
	 * Удалить телефон юр.лица
	 * @param phoneId - идентификатор телефона
	 */
	private void RemovePhoneFromLegalPerson(Integer phoneId) {
		if(phoneId != null)
			getLegalPersonPhoneService().erase(phoneId);
	}

	/**
	 * Удалить телефон физ.лица
	 * @param phoneId - идентификатор телефона
	 */
	private void RemovePhoneFromNaturalPerson(Integer phoneId) {
		if(phoneId != null)
			getNaturalPersonPhoneService().erase(phoneId);
	}

	/**
	 * Обработка события просмотра телефона
	 * @param event - событие
	 * @throws ApplicationException
	 */
	public void onActionShowPhone(WidgetEvent event) throws ApplicationException {
		Integer phoneId = ((PhoneListModel) this.phoneTable.getModel()).getSelectedPhoneId();
		if(getIsLeagalPerson()) 
			ShowPhoneOfLegalPerson(phoneId);
		else
			ShowPhoneOfNaturalPerson(phoneId);
	}

	/**
	 * Просмотр телефона юр.лица
	 * @param phoneId - идентификатор телефона
	 */
	private void ShowPhoneOfLegalPerson(Integer phoneId) {
		if(phoneId != null)
			MaintenanceHelper.view(getLegalPersonPhoneService(), phoneId, null, null);
	}

	/**
	 * Просмотр телефона физ.лица
	 * @param phoneId - идентификатор телефона
	 */
	private void ShowPhoneOfNaturalPerson(Integer phoneId) {
		if(phoneId != null)
			MaintenanceHelper.view(getNaturalPersonPhoneService(), phoneId, null, null); 
	}

	/**
	 * Обработка события изменения телефона
	 * @param event - событие
	 * @throws ApplicationException
	 */
	public void onActionEditPhone(WidgetEvent event) throws ApplicationException {
		Integer phoneId = ((PhoneListModel) this.phoneTable.getModel()).getSelectedPhoneId();
		if(getIsLeagalPerson()) 
			EditPhoneOfLegalPerson(phoneId);
		else
			EditPhoneOfNaturalPerson(phoneId);
	}

	/**
	 * Изменение телефона юр.лица
	 * @param phoneId - идентификатор телефона
	 */
	private void EditPhoneOfLegalPerson(Integer phoneId) {
		if(phoneId == null)
			return;
		MaintenanceHelper.edit(getLegalPersonPhoneService(), phoneId, null, new MaintenanceFormActionListener() {

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
				refreshPhoneTable();
			}
		});
	}

	/**
	 * Изменение телефона физ.лица
	 * @param phoneId - идентификатор телефона
	 */
	private void EditPhoneOfNaturalPerson(Integer phoneId) {
		if(phoneId == null)
			return;
		MaintenanceHelper.edit(getNaturalPersonPhoneService(), phoneId, null, new MaintenanceFormActionListener() {

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
				refreshPhoneTable();
			}
		});
	}

	/**
	 * Обработка события добавления электронного адреса
	 * @param event - событие
	 * @throws ApplicationException
	 */
	public void onActionAddEAdress(WidgetEvent event) throws ApplicationException {
		if(getIsLeagalPerson()) 
			AddEAdressToLegalPerson(getLegalPerson());
		else
			AddEAdressToNaturalPerson(getNaturalPerson());
	}

	/**
	 * Добавить электронный адрес юр.лицу
	 * @param legalPerson - юр.лицо
	 */
	private void AddEAdressToLegalPerson(Contractor legalPerson) {
		if(legalPerson == null)
			return;
		ContractorElectronicAddress legalPersonElectronicAddress = getLegalPersonElectronicAddressService().initialize();
		legalPersonElectronicAddress.setContractor(legalPerson);
		MaintenanceHelper.add(getLegalPersonElectronicAddressService(), legalPersonElectronicAddress, null, new MaintenanceFormActionListener() {

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
				refreshEAdressTable();
			}
		});
	}

	/**
	 * Добавить электронный адрес физ.лицу
	 * @param naturalPerson - физ.лицо
	 */
	private void AddEAdressToNaturalPerson(NaturalPerson naturalPerson) {
		if(naturalPerson == null)
			return;
		PersonElectronicAddress personElectronicAddress = getNaturalPersonElectronicAddressService().initialize();
		personElectronicAddress.setNaturalPerson(naturalPerson);
		MaintenanceHelper.add(getNaturalPersonElectronicAddressService(), personElectronicAddress, null, new MaintenanceFormActionListener() {

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
				refreshEAdressTable();
			}
		});
	}

	/**
	 * Обработка события удаления электронного адреса
	 * @param event - событие
	 * @throws ApplicationException
	 */
	public void onActionRemoveEAdress(WidgetEvent event) throws ApplicationException {
		Integer eAdressId = ((ElectronicAdressTableModel) this.eAdressTable.getModel()).getSelectedElectronicAdressId();
		if(getIsLeagalPerson()) 
			removeEAdressFromLegalPerson(eAdressId);
		else
			removeEAdressFromNaturalPerson(eAdressId);
		refreshEAdressTable();
	}

	/**
	 * Удалить электронный адрес у юр.лица
	 * @param eAdressId - идентификатор электронного адреса
	 */
	private void removeEAdressFromLegalPerson(Integer eAdressId) {
		if(eAdressId != null)
			getLegalPersonElectronicAddressService().erase(eAdressId);
	}

	/**
	 * Удалить электронный адрес у физ.лица
	 * @param eAdressId - идентификатор электронного адреса
	 */
	private void removeEAdressFromNaturalPerson(Integer eAdressId) {
		if(eAdressId != null)
			getNaturalPersonElectronicAddressService().erase(eAdressId);
	}

	/**
	 * Обработка события просмотра электронного адреса
	 * @param event - событие
	 * @throws ApplicationException
	 */
	public void onActionShowEAdress(WidgetEvent event) throws ApplicationException {
		Integer eAdressId = ((ElectronicAdressTableModel) this.eAdressTable.getModel()).getSelectedElectronicAdressId();
		if(getIsLeagalPerson()) 
			showEAdressOfLegalPerson(eAdressId);
		else
			showEAdressOfNaturalPerson(eAdressId);
	}

	/**
	 * Просмотреть электронный адрес у юр.лица
	 * @param eAdressId - идентификатор электронного адреса
	 */
	private void showEAdressOfLegalPerson(Integer eAdressId) {
		if(eAdressId != null)
			MaintenanceHelper.view(getLegalPersonElectronicAddressService(), eAdressId, null, null);
	}

	/**
	 * Просмотреть электронный адрес у физ.лица
	 * @param eAdressId - идентификатор электронного адреса
	 */
	private void showEAdressOfNaturalPerson(Integer eAdressId) {
		if(eAdressId != null)
			MaintenanceHelper.view(getNaturalPersonElectronicAddressService(), eAdressId, null, null);
	}

	/**
	 * Обработка события изменения электронного адреса
	 * @param event - событие
	 */
	public void onActionEditEAdress(WidgetEvent event) {
		Integer eAdressId = ((ElectronicAdressTableModel) eAdressTable.getModel()).getSelectedElectronicAdressId();
		if(getIsLeagalPerson()) 
			editEAdressOfLegalPerson(eAdressId);
		else
			editEAdressOfNaturalPerson(eAdressId);
	}

	/**
	 * Изменить электронный адрес у юр.лица
	 * @param eAdressId - идентификатор электронного адреса
	 */
	private void editEAdressOfLegalPerson(Integer eAdressId) {
		if(eAdressId == null)
			return;
		MaintenanceHelper.edit(getLegalPersonElectronicAddressService(), eAdressId, null, new MaintenanceFormActionListener() {

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
				refreshEAdressTable();
			}
		});
	}

	/**
	 * Изменить электронный адрес у физ.лица
	 * @param eAdressId - идентификатор электронного адреса
	 */
	private void editEAdressOfNaturalPerson(Integer eAdressId) {
		if(eAdressId == null)
			return;
		MaintenanceHelper.edit(getNaturalPersonElectronicAddressService(), eAdressId, null, new MaintenanceFormActionListener() {

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
				refreshEAdressTable();
			}
		});
	}

	/**
	 * Обработка события просмотра карточки расчетов
	 * @param event - событие
	 * @throws ApplicationException
	 */
	public void onActionShowContractorCard(WidgetEvent event) throws ApplicationException {
		if(selectedCardId != null)
			MaintenanceHelper.view(getContractorCardService(), selectedCardId, null, null);

	}

	/**
	 * Обработка события изменения карточки расчетов
	 * @param event - событие
	 * @throws ApplicationException
	 */
	public void onActionEditContractorCard(WidgetEvent event) throws ApplicationException {
		if(selectedCardId == null)
			return;
		MaintenanceHelper.edit(getContractorCardService(), selectedCardId, null, new MaintenanceFormActionListener() {

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
				refreshContractorCardTable();
			}
		});
	}

	/**
	 * Обработка события обновления таблицы карточек расчетов
	 * @param event - событие
	 * @throws ApplicationException
	 */
	public void onActionRefreshContractorCard(WidgetEvent event) throws ApplicationException {
		refreshContractorCardTable();
	}

	/**
	 * Обработчик пункта КМ "Добавить документ"  
	 * @param event - событие
	 */
	public void onActionAddDocument(WidgetEvent event) throws Exception {
		SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.document.support.ui.DocSectionSearchHelp"); //$NON-NLS-1$
		searchHelp.addSearchHelpListener(new SearchHelpListener() {
			public void searchPerformed(SearchHelpEvent event) {
				Document documentService = DocumentUtils.getDocumentService((DocSection) event.getItems()[0]);
				try {
					showPatternDialog((DocSection) event.getItems()[0], documentService);
				}
				catch(Exception e) {
				}
			}

			public void searchCanceled(SearchHelpEvent event) {
				// do nothing
			}
		});
		searchHelp.search();
	}

	/**
	 * Показать форму поиска образца документа
	 * @param docSection - раздел документа
	 * @param documentService - сервис документа
	 * @throws Exception - ИС
	 */
	private void showPatternDialog(DocSection docSection, final Document documentService) throws Exception {
		SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.document.support.ui.UniversalDocModelSearchHelp"); //$NON-NLS-1$
		searchHelp.addSearchHelpListener(new SearchHelpListener() {
			public void searchPerformed(SearchHelpEvent event) {
				insertDocumentByPattern((DocHeadModel) event.getItems()[0], documentService);
			}

			public void searchCanceled(SearchHelpEvent event) {
				//do nothing
			}
		});
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("DocSection", documentService.getDocSection()); //$NON-NLS-1$
		searchHelp.setImportContext(context);
		searchHelp.search();
	}

	/**
	 * Создать документ по образцу и отобразить
	 * @param docHeadModel - образец документа
	 * @param documentService - сервис документа
	 */
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	private void insertDocumentByPattern(DocHeadModel docHeadModel, Document documentService) {
		DocHead docHead = ((Document) documentService).createByPattern(docHeadModel, null);

		MaintenanceHelper.add(DocumentUtils.getDocumentService(docHead.getDocSection()), docHead, null, new MaintenanceFormActionListener() {

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
				documents.getModel().load();
			}
		});
	}

	/**
	 * Обработчик пункта КМ "Изменить документ"  
	 * @param event - событие
	 */
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	public void onActionEditDocument(WidgetEvent event) {
		DocHead docHead = getDocHead();

		if(docHead != null) {
			Document documentService = DocumentUtils.getDocumentService(docHead.getDocSection());
			MaintenanceHelper.edit(documentService, docHead.getId(), null, new MaintenanceFormActionListener() {

				public void canceled(MaintenanceFormEvent event) {
					// do nothing
				}

				public void performed(MaintenanceFormEvent event) {
					documents.getModel().load();
				}
			});
		}
	}

	/**
	 * Обработчик пункта КМ "Удалить документ"  
	 * @param event - событие
	 */
	public void onActionDeleteDocument(WidgetEvent event) {
		final DocHead docHead = getDocHead();
		if(docHead != null) {
			com.mg.framework.support.Messages msg = com.mg.framework.support.Messages.getInstance();
			final String yesButton = msg.getMessage(com.mg.framework.support.Messages.YES_BUTTON_TEXT);
			UIUtils.showAlert(Alert.MessageType.QUESTION_MESSAGE, msg.getMessage(com.mg.framework.support.Messages.ERASE_ALERT_TITLE), msg.getMessage(com.mg.framework.support.Messages.ERASE_ALERT_QUESTION), yesButton, msg.getMessage(com.mg.framework.support.Messages.NO_BUTTON_TEXT), new AlertListener() {
				public void alertClosing(String value) {
					if(value.equals(yesButton)) {
						ServerUtils.getPersistentManager().remove(docHead);
						documents.getModel().load();
					}
				}
			});
		}
	}

	/**
	 * Обработчик пункта КМ "Просмотреть документ"  
	 * @param event - событие
	 */
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	public void onActionViewDocument(WidgetEvent event) {
		DocHead docHead = getDocHead();
		if(docHead != null) {
			Document documentService = DocumentUtils.getDocumentService(docHead.getDocSection());
			MaintenanceHelper.view(documentService, docHead.getId(), null, null);
		}
	}

	/**
	 * Обработчик пункта КМ "Обновить таблицу документов"  
	 * @param event - событие
	 */
	public void onActionRefreshDocument(WidgetEvent event) {
		documents.getModel().load();
	}

	/**
	 * Обработчик пункта КМ "Условия отбора документов"  
	 * @param event - событие
	 */
	public void onActionRestDocument(WidgetEvent event) {

	}

	/**
	 * Обработчик пункта КМ "Изменение спецификаций"  
	 * @param event - событие
	 */
	public void onActionEditSpecification(WidgetEvent event) {
		Integer selectedKey = getDocumentSelectedPrimaryKey();
		if(selectedKey != null) {
			DocHead docHead = getDocHead();
			if(docHead != null) {
				DocSection docSection = docHead.getDocSection();
				if(docSection.isWithSpec()) {
					Document documentService = DocumentUtils.getDocumentService(docSection);
					editDocumetSpecification(documentService, selectedKey);
				}
			}
		}
	}

	/**
	 * Показать документ в режиме изменения спецификаций
	 * @param documentService - cервис документа
	 * @param docId - идентификатор документа
	 */
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	private void editDocumetSpecification(Document documentService, Serializable docId) {
		GoodsDocumentMaintenanceForm form = (GoodsDocumentMaintenanceForm) ApplicationDictionaryLocator.locate().getMaintenaceForm(documentService, null);

		form.addMaintenanceFormActionListener(new MaintenanceFormActionListener() {

			public void canceled(MaintenanceFormEvent event) {
				// do nothing
			}

			public void performed(MaintenanceFormEvent event) {
				documents.getModel().load();
			}
		});
		form.executeEditSpecifications(documentService, (DocHead) documentService.load(docId));
	}

	/**
	 * Обработчик пункта КМ "Отработка документа"  
	 * @param event - событие
	 */
	public void onActionExecuteDocFlow(WidgetEvent event) {
		Integer selectedKey = getDocumentSelectedPrimaryKey();
		if(selectedKey != null)
			DocFlowHelper.execute(selectedKey);
	}

	/**
	 * Обработчик пункта КМ "Откат ДО"  
	 * @param event - событие
	 */
	public void onActionRollBackDocFlow(WidgetEvent event) {
		Integer selectedKey = getDocumentSelectedPrimaryKey();
		if(selectedKey != null)
			DocFlowHelper.rollback(selectedKey);
	}

	/**
	 * Обработчик пункта КМ "История ДО"  
	 * @param event - событие
	 */
	public void onActionDocFlowHistory(WidgetEvent event) {
		Integer selectedKey = getDocumentSelectedPrimaryKey();
		if(selectedKey != null)
			DocFlowHelper.showDocumentHistory(selectedKey);
	}

	private DocHead getDocHead() {
		Integer selectedKey = getDocumentSelectedPrimaryKey();
		if(selectedKey != null)
			return ServerUtils.getPersistentManager().find(DocHead.class, selectedKey);
		else
			return null;
	}

	private Integer getDocumentSelectedPrimaryKey() {
		return selectedDocumentId;
	}

	/**
	 * Получить физ.лицо
	 * @return физ.лицо
	 */
	private NaturalPerson getNaturalPerson() {
		NaturalPerson naturalPerson = (NaturalPerson) getEntity().getAttribute("NaturalPerson"); //$NON-NLS-1$
		if(naturalPerson != null)
			ServerUtils.getPersistentManager().refresh(naturalPerson);
		return naturalPerson;
	}

	/**
	 * Получить юр.лицо
	 * @return юр.лицо
	 */
	private Contractor getLegalPerson() {
		Contractor legalPerson = (Contractor) getEntity().getAttribute("LegalPerson"); //$NON-NLS-1$
		if(legalPerson != null)
			ServerUtils.getPersistentManager().refresh(legalPerson);
		return legalPerson;
	}

	/**
	 * Получить текущий тип лица
	 * @return true - юр.лицо; false - физ.лицо
	 */
	private boolean getIsLeagalPerson() {
		return (Boolean)getEntity().getAttribute("IsLegalPerson"); //$NON-NLS-1$
	}

	private PersonPhoneServiceLocal getNaturalPersonPhoneService() {
		return (PersonPhoneServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/PersonPhone"); //$NON-NLS-1$
	}

	private ContractorPhoneServiceLocal getLegalPersonPhoneService() {
		return (ContractorPhoneServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/ContractorPhone"); //$NON-NLS-1$
	}

	private PersonElectronicAddressServiceLocal getNaturalPersonElectronicAddressService() {
		return (PersonElectronicAddressServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/PersonElectronicAddress"); //$NON-NLS-1$
	}

	private ContractorElectronicAddressServiceLocal getLegalPersonElectronicAddressService() {
		return (ContractorElectronicAddressServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/ContractorElectronicAddress"); //$NON-NLS-1$
	}

	private ContractorCardServiceLocal getContractorCardService() {
		return (ContractorCardServiceLocal)  ApplicationDictionaryLocator.locate().getBusinessService("merp/settlement/ContractorCard"); //$NON-NLS-1$
	}

	private ContactServiceLocal getContactService() {
		return (ContactServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/crm/Contact"); //$NON-NLS-1$
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return the index
	 */
	public String getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(String index) {
		this.index = index;
	}

	/**
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * @param place the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return the building
	 */
	public String getBuilding() {
		return building;
	}

	/**
	 * @param building the building to set
	 */
	public void setBuilding(String building) {
		this.building = building;
	}

	/**
	 * @return the fullAdress
	 */
	public String getFullAdress() {
		return fullAdress;
	}

	/**
	 * @param fullAdress the fullAdress to set
	 */
	public void setFullAdress(String fullAdress) {
		this.fullAdress = fullAdress;
	}

	/**
	 * @return the house
	 */
	public String getHouse() {
		return house;
	}

	/**
	 * @param house the house to set
	 */
	public void setHouse(String house) {
		this.house = house;
	}

	/**
	 * @return the room
	 */
	public String getRoom() {
		return room;
	}

	/**
	 * @param room the room to set
	 */
	public void setRoom(String room) {
		this.room = room;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

}
