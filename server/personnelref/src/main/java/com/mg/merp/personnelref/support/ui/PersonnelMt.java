/*
 * PersonnelMt.java
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
package com.mg.merp.personnelref.support.ui;

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
import com.mg.merp.personnelref.AddressServiceLocal;
import com.mg.merp.personnelref.FamilyDeductionsServiceLocal;
import com.mg.merp.personnelref.PersonnelAttestationServiceLocal;
import com.mg.merp.personnelref.PersonnelEducationServiceLocal;
import com.mg.merp.personnelref.PersonnelLabourContractServiceLocal;
import com.mg.merp.personnelref.PersonnelLanguageServiceLocal;
import com.mg.merp.personnelref.PersonnelLeaveServiceLocal;
import com.mg.merp.personnelref.PersonnelProfessionServiceLocal;
import com.mg.merp.personnelref.PersonnelRecordServiceLocal;
import com.mg.merp.personnelref.PersonnelRewardServiceLocal;
import com.mg.merp.personnelref.PersonnelServiceServiceLocal;
import com.mg.merp.personnelref.PersonnelSkillRaisingServiceLocal;
import com.mg.merp.personnelref.PersonnelSocialBenefitServiceLocal;
import com.mg.merp.personnelref.PersonnelTransferServiceLocal;
import com.mg.merp.personnelref.PersonnelVocationalTrainingServiceLocal;
import com.mg.merp.personnelref.model.Address;
import com.mg.merp.personnelref.model.FamilyDeductions;
import com.mg.merp.personnelref.model.PersonnelAttestation;
import com.mg.merp.personnelref.model.PersonnelEducation;
import com.mg.merp.personnelref.model.PersonnelLabourContract;
import com.mg.merp.personnelref.model.PersonnelLanguage;
import com.mg.merp.personnelref.model.PersonnelLeave;
import com.mg.merp.personnelref.model.PersonnelProfession;
import com.mg.merp.personnelref.model.PersonnelRecord;
import com.mg.merp.personnelref.model.PersonnelReward;
import com.mg.merp.personnelref.model.PersonnelService;
import com.mg.merp.personnelref.model.PersonnelSkillRaising;
import com.mg.merp.personnelref.model.PersonnelSocialBenefit;
import com.mg.merp.personnelref.model.PersonnelTransfer;
import com.mg.merp.personnelref.model.PersonnelVocationalTraining;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поддержки бизнес-компонента "Основные сведения о сотрудниках"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PersonnelMt.java,v 1.11 2007/07/10 07:34:05 sharapov Exp $
 */
public class PersonnelMt extends DefaultMaintenanceForm implements MasterModelListener {

  protected AttributeMap personnelServiceProperties = new LocalDataTransferObject();
  protected AttributeMap personnelAddressProperties = new LocalDataTransferObject();
  protected AttributeMap personnelLanguageProperties = new LocalDataTransferObject();
  protected AttributeMap personnelEducationProperties = new LocalDataTransferObject();
  protected AttributeMap personnelProfessionProperties = new LocalDataTransferObject();
  protected AttributeMap personnelRecordProperties = new LocalDataTransferObject();
  protected AttributeMap personnelTransferProperties = new LocalDataTransferObject();
  protected AttributeMap personnelLeaveProperties = new LocalDataTransferObject();
  protected AttributeMap personnelAttestationProperties = new LocalDataTransferObject();
  protected AttributeMap personnelSkillRaisingProperties = new LocalDataTransferObject();
  protected AttributeMap persSocialBenefitProperties = new LocalDataTransferObject();
  protected AttributeMap familyDeductionsProperties = new LocalDataTransferObject();
  protected AttributeMap persLabourContractProperties = new LocalDataTransferObject();
  protected AttributeMap personnelRewardProperties = new LocalDataTransferObject();
  protected AttributeMap personnelVocationalProperties = new LocalDataTransferObject();
  private MaintenanceTableController personnelService;
  private PersonnelServiceServiceLocal personnelServiceService;
  private MaintenanceTableController personnelAddress;
  private AddressServiceLocal personnelAddressService;
  private MaintenanceTableController personnelLanguage;
  private PersonnelLanguageServiceLocal personnelLanguageService;
  private MaintenanceTableController personnelEducation;
  private PersonnelEducationServiceLocal personnelEducationService;
  private MaintenanceTableController personnelProfession;
  private PersonnelProfessionServiceLocal personnelProfessionService;
  private MaintenanceTableController personnelRecord;
  private PersonnelRecordServiceLocal personnelRecordService;
  private MaintenanceTableController personnelTransfer;
  private PersonnelTransferServiceLocal personnelTransferService;
  private MaintenanceTableController personnelLeave;
  private PersonnelLeaveServiceLocal personnelLeaveService;
  private MaintenanceTableController personnelAttestation;
  private PersonnelAttestationServiceLocal personnelAttestationService;
  private MaintenanceTableController personnelSkillRaising;
  private PersonnelSkillRaisingServiceLocal personnelSkillRaisingService;
  private MaintenanceTableController persSocialBenefit;
  private PersonnelSocialBenefitServiceLocal persSocialBenefitService;
  private MaintenanceTableController familyDeductions;
  private FamilyDeductionsServiceLocal familyDeductionsService;
  private MaintenanceTableController persLabourContract;
  private PersonnelLabourContractServiceLocal persLabourContractService;
  private MaintenanceTableController personnelReward;
  private PersonnelRewardServiceLocal personnelRewardService;
  private MaintenanceTableController personnelVocational;
  private PersonnelVocationalTrainingServiceLocal personnelVocationalService;

  public PersonnelMt() throws Exception {
    setMasterDetail(true);
    addMasterModelListener(this);

    personnelServiceService = (PersonnelServiceServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/PersonnelService"); //$NON-NLS-1$
    personnelService = new MaintenanceTableController(personnelServiceProperties);
    personnelService.initController(personnelServiceService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from PersonnelService ps %s where ps.Personnel = :personnel"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("personnel"); //$NON-NLS-1$
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
        result.add(new TableEJBQLFieldDef(PersonnelService.class, "Id", "ps.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelService.class, "ServiceKind", "sk.KName", "left join ps.ServiceKind as sk", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PersonnelService.class, "BeginDate", "ps.BeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelService.class, "EndDate", "ps.EndDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelService.class, "LengthService", "ps.LengthService", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelService.class, "Ratio", "ps.Ratio", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelService.class, "IsGoingOn", "ps.IsGoingOn", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, personnelServiceService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    addMasterModelListener(personnelService);

    personnelAddressService = (AddressServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/Address"); //$NON-NLS-1$
    personnelAddress = new MaintenanceTableController(personnelAddressProperties);
    personnelAddress.initController(personnelAddressService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from Address ad %s where ad.Personnel = :personnel"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("personnel"); //$NON-NLS-1$
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
        result.add(new TableEJBQLFieldDef(Address.class, "Id", "ad.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Address.class, "AddressKind", "ad.AddressKind", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Address.class, "BeginDate", "ad.BeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Address.class, "FullAddress", "ad.FullAddress", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Address.class, "House", "ad.House", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Address.class, "Block", "ad.Block", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Address.class, "Flat", "ad.Flat", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Address.class, "KladrCode", "ad.KladrCode", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, personnelAddressService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    addMasterModelListener(personnelAddress);

    personnelLanguageService = (PersonnelLanguageServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/PersonnelLanguage"); //$NON-NLS-1$
    personnelLanguage = new MaintenanceTableController(personnelLanguageProperties);
    personnelLanguage.initController(personnelLanguageService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from PersonnelLanguage plang %s where plang.Personnel = :personnel"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("personnel"); //$NON-NLS-1$
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
        result.add(new TableEJBQLFieldDef(PersonnelLanguage.class, "Id", "plang.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelLanguage.class, "ForeignLanguage", "fl.Name", "left join plang.ForeignLanguage as fl", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PersonnelLanguage.class, "LanguageKnowledge", "lk.Name", "left join plang.LanguageKnowledge as lk", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, personnelLanguageService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    addMasterModelListener(personnelLanguage);

    personnelEducationService = (PersonnelEducationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/PersonnelEducation"); //$NON-NLS-1$
    personnelEducation = new MaintenanceTableController(personnelEducationProperties);
    personnelEducation.initController(personnelEducationService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from PersonnelEducation pe %s where pe.Personnel = :personnel"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("personnel"); //$NON-NLS-1$
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
        result.add(new TableEJBQLFieldDef(PersonnelEducation.class, "Id", "pe.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelEducation.class, "InstitutionName", "pe.InstitutionName", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelEducation.class, "DiplomaQualification", "pe.DiplomaQualification", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelEducation.class, "DiplomaNumber", "pe.DiplomaNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelEducation.class, "DiplomaDate", "pe.DiplomaDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelEducation.class, "GraduationYear", "pe.GraduationYear", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelEducation.class, "Speciality", "s.Name", "left join pe.Speciality as s", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PersonnelEducation.class, "IsAdditional", "pe.IsAdditional", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelEducation.class, "AdditionalEducationKind", "aek.Code", "left join pe.AdditionalEducationKind as aek", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, personnelEducationService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    addMasterModelListener(personnelEducation);

    personnelProfessionService = (PersonnelProfessionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/PersonnelProfession"); //$NON-NLS-1$
    personnelProfession = new MaintenanceTableController(personnelProfessionProperties);
    personnelProfession.initController(personnelProfessionService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from PersonnelProfession pp %s where pp.Personnel = :personnel"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("personnel"); //$NON-NLS-1$
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
        result.add(new TableEJBQLFieldDef(PersonnelProfession.class, "Id", "pp.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelProfession.class, "Job", "job.Name", "left join pp.Job as job", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PersonnelProfession.class, "IsBasic", "pp.IsBasic", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, personnelProfessionService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    addMasterModelListener(personnelProfession);

    personnelRecordService = (PersonnelRecordServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/PersonnelRecord"); //$NON-NLS-1$
    personnelRecord = new MaintenanceTableController(personnelRecordProperties);
    personnelRecord.initController(personnelRecordService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from PersonnelRecord pr %s where pr.Personnel = :personnel"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("personnel"); //$NON-NLS-1$
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
        result.add(new TableEJBQLFieldDef(PersonnelRecord.class, "Id", "pr.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelRecord.class, "OriginalDocument.DocName", "od.DocName", "left join pr.OriginalDocument as od", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PersonnelRecord.class, "OriginalDocument.DocDate", "od.DocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelRecord.class, "OriginalDocument.DocNumber", "od.DocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelRecord.class, "Name", "pr.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelRecord.class, "Description", "pr.Description", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, personnelRecordService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    addMasterModelListener(personnelRecord);

    personnelTransferService = (PersonnelTransferServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/PersonnelTransfer"); //$NON-NLS-1$
    personnelTransfer = new MaintenanceTableController(personnelTransferProperties);
    personnelTransfer.initController(personnelTransferService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from PersonnelTransfer pt %s where pt.Personnel = :personnel"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("personnel"); //$NON-NLS-1$
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
        result.add(new TableEJBQLFieldDef(PersonnelTransfer.class, "Id", "pt.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelTransfer.class, "TransferDate", "pt.TransferDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelTransfer.class, "StaffListUnit", "slu.UName", "left join pt.StaffListUnit as slu", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PersonnelTransfer.class, "StaffListPosition", "slp.Position.Name", "left join pt.StaffListPosition as slp", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PersonnelTransfer.class, "Salary", "pt.Salary", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelTransfer.class, "SalaryRaise", "pt.SalaryRaise", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, personnelTransferService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    addMasterModelListener(personnelTransfer);

    personnelLeaveService = (PersonnelLeaveServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/PersonnelLeave"); //$NON-NLS-1$
    personnelLeave = new MaintenanceTableController(personnelLeaveProperties);
    personnelLeave.initController(personnelLeaveService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from PersonnelLeave pl %s where pl.Personnel = :personnel"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("personnel"); //$NON-NLS-1$
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
        result.add(new TableEJBQLFieldDef(PersonnelLeave.class, "Id", "pl.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelLeave.class, "LeaveKind", "lk.Code", "left join pl.LeaveKind as lk", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PersonnelLeave.class, "WorkBeginDate", "pl.WorkBeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelLeave.class, "WorkEndDate", "pl.WorkEndDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelLeave.class, "HolidayNumber", "pl.HolidayNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelLeave.class, "BeginDate", "pl.BeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelLeave.class, "EndDate", "pl.EndDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, personnelLeaveService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });
    addMasterModelListener(personnelLeave);

    personnelAttestationService = (PersonnelAttestationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/PersonnelAttestation"); //$NON-NLS-1$
    personnelAttestation = new MaintenanceTableController(personnelAttestationProperties);
    personnelAttestation.initController(personnelAttestationService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from PersonnelAttestation pa %s where pa.Personnel = :personnel"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("personnel"); //$NON-NLS-1$
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
        result.add(new TableEJBQLFieldDef(PersonnelAttestation.class, "Id", "pa.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelAttestation.class, "AttestationDate", "pa.AttestationDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelAttestation.class, "Resolution", "pa.Resolution", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelAttestation.class, "ResolutionDocument.DocName", "rd.DocName", "left join pa.ResolutionDocument as rd", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PersonnelAttestation.class, "ResolutionDocument.DocDate", "rd.DocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelAttestation.class, "ResolutionDocument.DocNumber", "rd.DocNumber", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, personnelAttestationService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    addMasterModelListener(personnelAttestation);

    personnelSkillRaisingService = (PersonnelSkillRaisingServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/PersonnelSkillRaising"); //$NON-NLS-1$
    personnelSkillRaising = new MaintenanceTableController(personnelSkillRaisingProperties);
    personnelSkillRaising.initController(personnelSkillRaisingService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from PersonnelSkillRaising psr %s where psr.Personnel = :personnel"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("personnel"); //$NON-NLS-1$
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
        result.add(new TableEJBQLFieldDef(PersonnelSkillRaising.class, "Id", "psr.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelSkillRaising.class, "StudyBeginDate", "psr.StudyBeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelSkillRaising.class, "StudyEndDate", "psr.StudyEndDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelSkillRaising.class, "SkillRaisingKind", "srk.Code", "left join psr.SkillRaisingKind as srk", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PersonnelSkillRaising.class, "InstitutionName", "psr.InstitutionName", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelSkillRaising.class, "InstitutionAddress", "psr.InstitutionAddress", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelSkillRaising.class, "CertificateDocument.DocName", "rd.DocName", "left join psr.CertificateDocument as rd", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PersonnelSkillRaising.class, "CertificateDocument.DocDate", "rd.DocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelSkillRaising.class, "CertificateDocument.DocNumber", "rd.DocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, personnelSkillRaisingService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });
    addMasterModelListener(personnelSkillRaising);

    persSocialBenefitService = (PersonnelSocialBenefitServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/PersonnelSocialBenefit"); //$NON-NLS-1$
    persSocialBenefit = new MaintenanceTableController(persSocialBenefitProperties);
    persSocialBenefit.initController(persSocialBenefitService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from PersonnelSocialBenefit psb %s where psb.Personnel = :personnel"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("personnel"); //$NON-NLS-1$
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
        result.add(new TableEJBQLFieldDef(PersonnelSocialBenefit.class, "Id", "psb.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelSocialBenefit.class, "BenefitName", "psb.BenefitName", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelSocialBenefit.class, "BenefitReason", "psb.BenefitReason", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelSocialBenefit.class, "OriginalDocument.DocName", "rd.DocName", "left join psb.OriginalDocument as rd", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PersonnelSocialBenefit.class, "OriginalDocument.DocDate", "rd.DocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelSocialBenefit.class, "OriginalDocument.DocNumber", "rd.DocNumber", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, persSocialBenefitService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });
    addMasterModelListener(persSocialBenefit);

    familyDeductionsService = (FamilyDeductionsServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/FamilyDeductions"); //$NON-NLS-1$

    familyDeductions = new MaintenanceTableController(familyDeductionsProperties);
    familyDeductions.initController(familyDeductionsService, new DefaultMaintenanceEJBQLTableModel() {
      //private final String INIT_QUERY_TEXT = "select %s from FamilyMember fm, FamilyDeductions fd, Personnel p %s where fm.NaturalPerson = p.Person and p = :personnel and fd.FamilyMember = fm.Id"; //$NON-NLS-1$
      private final String INIT_QUERY_TEXT = "select %s from FamilyMember fm, FamilyDeductions fd, Personnel p %s where fm.NaturalPerson = p.Person and p = :personnel and fd.FamilyMember = fm.Id"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("personnel"); //$NON-NLS-1$
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
        result.add(new TableEJBQLFieldDef(FamilyDeductions.class, "Id", "fd.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        //result.add(new TableEJBQLFieldDef(FamilyDeductions.class, "FamilyMember", "(np.Surname||' '||np.Name||' '||np.Patronymic) as name", "left join p.Person as np", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(FamilyDeductions.class, "FamilyMember", "(fm.Surname||' '||fm.FName||' '||fm.Patronymic) as name", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(FamilyDeductions.class, "DeductionKind", "dk.DCode", "left join fd.DeductionKind as dk", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(FamilyDeductions.class, "BeginDate", "fd.BeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(FamilyDeductions.class, "EndDate", "fd.EndDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(FamilyDeductions.class, "Ratio", "fd.Ratio", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, familyDeductionsService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });
    addMasterModelListener(familyDeductions);

    persLabourContractService = (PersonnelLabourContractServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/PersonnelLabourContract"); //$NON-NLS-1$
    persLabourContract = new MaintenanceTableController(persLabourContractProperties);
    persLabourContract.initController(persLabourContractService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from PersonnelLabourContract plc %s where plc.Personnel = :personnel"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("personnel"); //$NON-NLS-1$
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
        result.add(new TableEJBQLFieldDef(PersonnelLabourContract.class, "Id", "plc.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelLabourContract.class, "OriginalDocument.DocName", "rd.DocName", "left join plc.OriginalDocument as rd", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PersonnelLabourContract.class, "OriginalDocument.DocDate", "rd.DocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelLabourContract.class, "OriginalDocument.DocNumber", "rd.DocNumber", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelLabourContract.class, "Name", "plc.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$

        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, persLabourContractService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });
    addMasterModelListener(persLabourContract);

    personnelRewardService = (PersonnelRewardServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/PersonnelReward"); //$NON-NLS-1$
    personnelReward = new MaintenanceTableController(personnelRewardProperties);
    personnelReward.initController(personnelRewardService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from PersonnelReward pr %s where pr.Personnel = :personnel"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("personnel"); //$NON-NLS-1$
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
        result.add(new TableEJBQLFieldDef(PersonnelReward.class, "Id", "pr.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelReward.class, "OriginalDocument.DocName", "rd.DocName", "left join pr.OriginalDocument as rd", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PersonnelReward.class, "OriginalDocument.DocDate", "rd.DocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelReward.class, "OriginalDocument.DocNumber", "rd.DocNumber", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelReward.class, "RewardName", "pr.RewardName", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, personnelRewardService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });
    addMasterModelListener(personnelReward);

    personnelVocationalService = (PersonnelVocationalTrainingServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/PersonnelVocationalTraining"); //$NON-NLS-1$
    personnelVocational = new MaintenanceTableController(personnelVocationalProperties);
    personnelVocational.initController(personnelVocationalService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from PersonnelVocationalTraining pvt %s where pvt.Personnel = :personnel"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("personnel"); //$NON-NLS-1$
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
        result.add(new TableEJBQLFieldDef(PersonnelVocationalTraining.class, "Id", "pvt.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelVocationalTraining.class, "TrainingBeginDate", "pvt.TrainingBeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelVocationalTraining.class, "TrainingEndDate", "pvt.TrainingEndDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelVocationalTraining.class, "Speciality", "s.Name", "left join pvt.Speciality as s", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PersonnelVocationalTraining.class, "CertificateDocument.DocName", "rd.DocName", "left join pvt.CertificateDocument as rd", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PersonnelVocationalTraining.class, "CertificateDocument.DocDate", "rd.DocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonnelVocationalTraining.class, "CertificateDocument.DocNumber", "rd.DocNumber", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, personnelVocationalService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });
    addMasterModelListener(personnelVocational);


  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    personnelServiceProperties.put("Personnel", event.getEntity());  //$NON-NLS-1$
    personnelAddressProperties.put("Personnel", event.getEntity());         //$NON-NLS-1$
    personnelLanguageProperties.put("Personnel", event.getEntity());  //$NON-NLS-1$
    personnelEducationProperties.put("Personnel", event.getEntity()); //$NON-NLS-1$
    personnelProfessionProperties.put("Personnel", event.getEntity()); //$NON-NLS-1$
    personnelRecordProperties.put("Personnel", event.getEntity());         //$NON-NLS-1$
    personnelTransferProperties.put("Personnel", event.getEntity()); //$NON-NLS-1$
    personnelLeaveProperties.put("Personnel", event.getEntity()); //$NON-NLS-1$
    personnelAttestationProperties.put("Personnel", event.getEntity()); //$NON-NLS-1$
    personnelSkillRaisingProperties.put("Personnel", event.getEntity());     //$NON-NLS-1$
    persSocialBenefitProperties.put("Personnel", event.getEntity()); //$NON-NLS-1$
    familyDeductionsProperties.put("FamilyMember.NaturalPerson", event.getEntity().getAttribute("Person")); //$NON-NLS-1$ //$NON-NLS-2$
    familyDeductionsProperties.put("Person", event.getEntity().getAttribute("Person")); //$NON-NLS-1$ //$NON-NLS-2$
    persLabourContractProperties.put("Personnel", event.getEntity()); //$NON-NLS-1$
    personnelRewardProperties.put("Personnel", event.getEntity()); //$NON-NLS-1$
    personnelVocationalProperties.put("Personnel", event.getEntity()); //$NON-NLS-1$
  }
}
