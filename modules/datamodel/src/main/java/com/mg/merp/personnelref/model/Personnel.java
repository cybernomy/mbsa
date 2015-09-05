/*
 * Personnel.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.merp.personnelref.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: Personnel.java,v 1.6 2006/10/02 10:59:26 leonova Exp $
 */
public class Personnel extends
		com.mg.framework.service.PersistentObjectHibernate implements
		java.io.Serializable {

	// Fields

	private int Id;

	private com.mg.merp.humanresources.model.Order RetireOrder;

	private com.mg.merp.personnelref.model.PersonnelGroup Group;

	private com.mg.merp.reference.model.NaturalPerson Person;

	private com.mg.merp.personnelref.model.InsuredClass InsuredClass;

	private com.mg.merp.personnelref.model.EducationDegree EducationDegree;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.personnelref.model.ReserveCategory MilReserveCategory;

	private com.mg.merp.personnelref.model.MilitaryRankKind MilRankKind;

	private com.mg.merp.personnelref.model.MilitaryRank MilRank;

	private com.mg.merp.personnelref.model.MilitaryValidity MilValidity;

	private java.util.Date ActDate;

	private java.lang.String TableNumber;

	private java.lang.String Name1;

	private java.lang.String Name2;

	private java.lang.String Name3;

	private java.util.Date BornDate;

	private java.lang.Integer Sex;

	private java.math.BigDecimal Stature;

	private com.mg.merp.reference.model.Measure MeasureUpCode;

	private java.io.Serializable Photo;

	private java.lang.String PensionNumber;

	private java.lang.String Inn;

	private java.lang.String LabourContractNumber;

	private java.util.Date LabourContractDate;

	private java.lang.String AdditionalInfo;

	private java.lang.String RetireReason;

	private java.util.Date RetireDate;

	private java.lang.String MilSpeciality;

	private java.lang.String MilCommissariat;

	private java.lang.String MilRegPartyNumber;

	private java.lang.String MilRegSpecial;

	private MilitaryRegTakenOff MilIsTakenOff;

	private java.util.Set SetOfPrefPersonnelProfession;

	private java.util.Set SetOfPrefPersonnelReward;

	private java.util.Set SetOfPrefPersonnelAttestation;

	private java.util.Set SetOfPrefFamilyMembers;

	private java.util.Set SetOfPrefPersonnelLanguage;

	private java.util.Set SetOfPrefPersonnelRecord;

	private java.util.Set SetOfPrefPersonnelSocialBenefit;

	private java.util.Set SetOfPrefPersonnelSkillRaising;

	private java.util.Set SetOfPrefPersonnelLabourContract;

	private java.util.Set SetOfPrefPersVocationalTraining;

	private java.util.Set SetOfPrefPersonnelTransfer;

	private java.util.Set SetOfPrefPersonnelLeave;

	private java.util.Set SetOfPrefPersonnelService;

	private java.util.Set SetOfPrefIdentDoc;

	private java.util.Set SetOfPrefAddress;

	// private java.util.Set SetOfOvrCard;
	private java.util.Set SetOfPrefPersonnelPhone;

	private java.util.Set SetOfPrefPersonnelEducation;

	// Constructors

	/** default constructor */
	public Personnel() {
	}

	/** constructor with id */
	public Personnel(int Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID")
	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */

	public com.mg.merp.humanresources.model.Order getRetireOrder() {
		return this.RetireOrder;
	}

	public void setRetireOrder(com.mg.merp.humanresources.model.Order HrOrder) {
		this.RetireOrder = HrOrder;
	}

	/**
	 * 
	 */

	public com.mg.merp.personnelref.model.PersonnelGroup getGroup() {
		return this.Group;
	}

	public void setGroup(
			com.mg.merp.personnelref.model.PersonnelGroup PrefPersonnelGroup) {
		this.Group = PrefPersonnelGroup;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.Person")
	public com.mg.merp.reference.model.NaturalPerson getPerson() {
		return this.Person;
	}

	public void setPerson(
			com.mg.merp.reference.model.NaturalPerson RefNaturalPerson) {
		this.Person = RefNaturalPerson;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.Personnel.InsuredClass")
	public com.mg.merp.personnelref.model.InsuredClass getInsuredClass() {
		return this.InsuredClass;
	}

	public void setInsuredClass(
			com.mg.merp.personnelref.model.InsuredClass PrefInsuredclass) {
		this.InsuredClass = PrefInsuredclass;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.Personnel.EducationDegree")
	public com.mg.merp.personnelref.model.EducationDegree getEducationDegree() {
		return this.EducationDegree;
	}

	public void setEducationDegree(
			com.mg.merp.personnelref.model.EducationDegree PrefEducationDegree) {
		this.EducationDegree = PrefEducationDegree;
	}

	/**
	 * 
	 */

	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**
	 * 
	 */
	public com.mg.merp.personnelref.model.ReserveCategory getMilReserveCategory() {
		return this.MilReserveCategory;
	}

	public void setMilReserveCategory(
			com.mg.merp.personnelref.model.ReserveCategory PrefReserveCategory) {
		this.MilReserveCategory = PrefReserveCategory;
	}

	/**
	 * 
	 */
	public com.mg.merp.personnelref.model.MilitaryRankKind getMilRankKind() {
		return this.MilRankKind;
	}

	public void setMilRankKind(
			com.mg.merp.personnelref.model.MilitaryRankKind PrefMilitaryRankKind) {
		this.MilRankKind = PrefMilitaryRankKind;
	}

	/**
	 * 
	 */
	public com.mg.merp.personnelref.model.MilitaryRank getMilRank() {
		return this.MilRank;
	}

	public void setMilRank(
			com.mg.merp.personnelref.model.MilitaryRank PrefMilitaryRank) {
		this.MilRank = PrefMilitaryRank;
	}

	/**
	 * 
	 */
	public com.mg.merp.personnelref.model.MilitaryValidity getMilValidity() {
		return this.MilValidity;
	}

	public void setMilValidity(
			com.mg.merp.personnelref.model.MilitaryValidity PrefMilitaryValidity) {
		this.MilValidity = PrefMilitaryValidity;
	}

	/**
	 * 
	 */

	public java.util.Date getActDate() {
		return this.ActDate;
	}

	public void setActDate(java.util.Date ActDate) {
		this.ActDate = ActDate;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.Personnel.TableNumber")
	public java.lang.String getTableNumber() {
		return this.TableNumber;
	}

	public void setTableNumber(java.lang.String TableNumber) {
		this.TableNumber = TableNumber;
	}

	/**
	 * 
	 */

	public java.lang.String getName1() {
		return this.Name1;
	}

	public void setName1(java.lang.String Name1) {
		this.Name1 = Name1;
	}

	/**
	 * 
	 */

	public java.lang.String getName2() {
		return this.Name2;
	}

	public void setName2(java.lang.String Name2) {
		this.Name2 = Name2;
	}

	/**
	 * 
	 */

	public java.lang.String getName3() {
		return this.Name3;
	}

	public void setName3(java.lang.String Name3) {
		this.Name3 = Name3;
	}

	/**
	 * 
	 */

	public java.util.Date getBornDate() {
		return this.BornDate;
	}

	public void setBornDate(java.util.Date Borndate) {
		this.BornDate = Borndate;
	}

	/**
	 * 
	 */

	public java.lang.Integer getSex() {
		return this.Sex;
	}

	public void setSex(java.lang.Integer Sex) {
		this.Sex = Sex;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Personnel.Stature")
	public java.math.BigDecimal getStature() {
		return this.Stature;
	}

	public void setStature(java.math.BigDecimal Stature) {
		this.Stature = Stature;
	}

	/**
	 * 
	 */
	@DataItemName("PersonnelRef.Personnel.Measure")
	public com.mg.merp.reference.model.Measure getMeasureUpCode() {
		return this.MeasureUpCode;
	}

	public void setMeasureUpCode(com.mg.merp.reference.model.Measure MeasureUpcode) {
		this.MeasureUpCode = MeasureUpcode;
	}

	/**
	 * 
	 */

	public java.io.Serializable getPhoto() {
		return this.Photo;
	}

	public void setPhoto(java.io.Serializable Photo) {
		this.Photo = Photo;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.Personnel.PensionNumber")
	public java.lang.String getPensionNumber() {
		return this.PensionNumber;
	}

	public void setPensionNumber(java.lang.String PensionNumber) {
		this.PensionNumber = PensionNumber;
	}

	/**
	 * 
	 */

	public java.lang.String getInn() {
		return this.Inn;
	}

	public void setInn(java.lang.String Inn) {
		this.Inn = Inn;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.Personnel.LabourContrNum")
	public java.lang.String getLabourContractNumber() {
		return this.LabourContractNumber;
	}

	public void setLabourContractNumber(java.lang.String LabourContractNumber) {
		this.LabourContractNumber = LabourContractNumber;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.Personnel.LabourContrDate")
	public java.util.Date getLabourContractDate() {
		return this.LabourContractDate;
	}

	public void setLabourContractDate(java.util.Date LabourContractDate) {
		this.LabourContractDate = LabourContractDate;
	}

	/**
	 * 
	 */

	public java.lang.String getAdditionalInfo() {
		return this.AdditionalInfo;
	}

	public void setAdditionalInfo(java.lang.String AdditionalInfo) {
		this.AdditionalInfo = AdditionalInfo;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.Personnel.RetireReason")
	public java.lang.String getRetireReason() {
		return this.RetireReason;
	}

	public void setRetireReason(java.lang.String RetireReason) {
		this.RetireReason = RetireReason;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.Personnel.RetireDate")
	public java.util.Date getRetireDate() {
		return this.RetireDate;
	}

	public void setRetireDate(java.util.Date RetireDate) {
		this.RetireDate = RetireDate;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.Personnel.MilSpeciality")
	public java.lang.String getMilSpeciality() {
		return this.MilSpeciality;
	}

	public void setMilSpeciality(java.lang.String MilSpeciality) {
		this.MilSpeciality = MilSpeciality;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.Personnel.MilCommissariat")
	public java.lang.String getMilCommissariat() {
		return this.MilCommissariat;
	}

	public void setMilCommissariat(java.lang.String MilCommissariat) {
		this.MilCommissariat = MilCommissariat;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.Personnel.MilRegPartyNumber")
	public java.lang.String getMilRegPartyNumber() {
		return this.MilRegPartyNumber;
	}

	public void setMilRegPartyNumber(java.lang.String MilRegPartyNumber) {
		this.MilRegPartyNumber = MilRegPartyNumber;
	}

	/**
	 * 
	 */

	@DataItemName("PersonnelRef.Personnel.MilRegSpecial")
	public java.lang.String getMilRegSpecial() {
		return this.MilRegSpecial;
	}

	public void setMilRegSpecial(java.lang.String MilRegSpecial) {
		this.MilRegSpecial = MilRegSpecial;
	}

	/**
	 * 
	 */

	public MilitaryRegTakenOff getMilIsTakenOff() {
		return this.MilIsTakenOff;
	}

	public void setMilIsTakenOff(MilitaryRegTakenOff MilIsTakenOff) {
		this.MilIsTakenOff = MilIsTakenOff;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfPrefPersonnelProfession() {
		return this.SetOfPrefPersonnelProfession;
	}

	public void setSetOfPrefPersonnelProfession(
			java.util.Set SetOfPrefPersonnelProfession) {
		this.SetOfPrefPersonnelProfession = SetOfPrefPersonnelProfession;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfPrefPersonnelReward() {
		return this.SetOfPrefPersonnelReward;
	}

	public void setSetOfPrefPersonnelReward(
			java.util.Set SetOfPrefPersonnelReward) {
		this.SetOfPrefPersonnelReward = SetOfPrefPersonnelReward;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfPrefPersonnelAttestation() {
		return this.SetOfPrefPersonnelAttestation;
	}

	public void setSetOfPrefPersonnelAttestation(
			java.util.Set SetOfPrefPersonnelAttestation) {
		this.SetOfPrefPersonnelAttestation = SetOfPrefPersonnelAttestation;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfPrefFamilyMembers() {
		return this.SetOfPrefFamilyMembers;
	}

	public void setSetOfPrefFamilyMembers(java.util.Set SetOfPrefFamilyMembers) {
		this.SetOfPrefFamilyMembers = SetOfPrefFamilyMembers;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfPrefPersonnelLanguage() {
		return this.SetOfPrefPersonnelLanguage;
	}

	public void setSetOfPrefPersonnelLanguage(
			java.util.Set SetOfPrefPersonnelLanguage) {
		this.SetOfPrefPersonnelLanguage = SetOfPrefPersonnelLanguage;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfPrefPersonnelRecord() {
		return this.SetOfPrefPersonnelRecord;
	}

	public void setSetOfPrefPersonnelRecord(
			java.util.Set SetOfPrefPersonnelRecord) {
		this.SetOfPrefPersonnelRecord = SetOfPrefPersonnelRecord;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfPrefPersonnelSocialBenefit() {
		return this.SetOfPrefPersonnelSocialBenefit;
	}

	public void setSetOfPrefPersonnelSocialBenefit(
			java.util.Set SetOfPrefPersonnelSocialBenefit) {
		this.SetOfPrefPersonnelSocialBenefit = SetOfPrefPersonnelSocialBenefit;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfPrefPersonnelSkillRaising() {
		return this.SetOfPrefPersonnelSkillRaising;
	}

	public void setSetOfPrefPersonnelSkillRaising(
			java.util.Set SetOfPrefPersonnelSkillRaising) {
		this.SetOfPrefPersonnelSkillRaising = SetOfPrefPersonnelSkillRaising;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfPrefPersonnelLabourContract() {
		return this.SetOfPrefPersonnelLabourContract;
	}

	public void setSetOfPrefPersonnelLabourContract(
			java.util.Set SetOfPrefPersonnelLabourContract) {
		this.SetOfPrefPersonnelLabourContract = SetOfPrefPersonnelLabourContract;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfPrefPersVocationalTraining() {
		return this.SetOfPrefPersVocationalTraining;
	}

	public void setSetOfPrefPersVocationalTraining(
			java.util.Set SetOfPrefPersVocationalTraining) {
		this.SetOfPrefPersVocationalTraining = SetOfPrefPersVocationalTraining;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfPrefPersonnelTransfer() {
		return this.SetOfPrefPersonnelTransfer;
	}

	public void setSetOfPrefPersonnelTransfer(
			java.util.Set SetOfPrefPersonnelTransfer) {
		this.SetOfPrefPersonnelTransfer = SetOfPrefPersonnelTransfer;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfPrefPersonnelLeave() {
		return this.SetOfPrefPersonnelLeave;
	}

	public void setSetOfPrefPersonnelLeave(java.util.Set SetOfPrefPersonnelLeave) {
		this.SetOfPrefPersonnelLeave = SetOfPrefPersonnelLeave;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfPrefPersonnelService() {
		return this.SetOfPrefPersonnelService;
	}

	public void setSetOfPrefPersonnelService(
			java.util.Set SetOfPrefPersonnelService) {
		this.SetOfPrefPersonnelService = SetOfPrefPersonnelService;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfPrefIdentDoc() {
		return this.SetOfPrefIdentDoc;
	}

	public void setSetOfPrefIdentDoc(java.util.Set SetOfPrefIdentDoc) {
		this.SetOfPrefIdentDoc = SetOfPrefIdentDoc;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfPrefAddress() {
		return this.SetOfPrefAddress;
	}

	public void setSetOfPrefAddress(java.util.Set SetOfPrefAddress) {
		this.SetOfPrefAddress = SetOfPrefAddress;
	}

	/**
	 * 
	 */
	public java.util.Set getSetOfPrefPersonnelPhone() {
		return this.SetOfPrefPersonnelPhone;
	}

	public void setSetOfPrefPersonnelPhone(java.util.Set SetOfPrefPersonnelPhone) {
		this.SetOfPrefPersonnelPhone = SetOfPrefPersonnelPhone;
	}

	/**
	 * 
	 */

	public java.util.Set getSetOfPrefPersonnelEducation() {
		return this.SetOfPrefPersonnelEducation;
	}

	public void setSetOfPrefPersonnelEducation(
			java.util.Set SetOfPrefPersonnelEducation) {
		this.SetOfPrefPersonnelEducation = SetOfPrefPersonnelEducation;
	}

}