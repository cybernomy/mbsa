/*
 * PersonalAccountRest.java
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
package com.mg.merp.salary.support.ui;

import java.util.Date;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.merp.personnelref.model.CostsAnl;
import com.mg.merp.personnelref.model.DeductionKind;
import com.mg.merp.personnelref.model.InsuredClass;
import com.mg.merp.personnelref.model.PositionFillKind;
import com.mg.merp.personnelref.model.PrefPosition;
import com.mg.merp.personnelref.model.ServiceKind;
import com.mg.merp.personnelref.model.StaffCategory;
import com.mg.merp.personnelref.model.StaffList;
import com.mg.merp.personnelref.model.TariffingCategory;
import com.mg.merp.personnelref.model.TaxCalcKind;
import com.mg.merp.personnelref.model.WorkCondition;
import com.mg.merp.personnelref.model.WorkSchedule;
import com.mg.merp.personnelref.support.ui.ItemWithDateIntervalRest;
import com.mg.merp.reference.model.FamilyRelation;
import com.mg.merp.reference.model.FamilyStatusKind;
import com.mg.merp.reference.model.IdentDocKind;
import com.mg.merp.salary.model.FeeRef;

/**
 * Контроллер формы условий отбора штатного расписания
 * 
 * @author leonova
 * @version $Id: PersonalAccountRest.java,v 1.3 2006/10/09 04:44:23 leonova Exp $ 
 */
public class PersonalAccountRest extends ItemWithDateIntervalRest {

	@DataItemName("Reference.NaturalPerson.Name")
	private String name = "";
	@DataItemName("Reference.NaturalPerson.Surname")	
	private String surname = "";
	@DataItemName("Reference.NaturalPerson.Patronymic")	
	private String patronymic = "";
	@DataItemName("Reference.Person.INN")
	private String inn= null;
	@DataItemName("Salary.Cond.PersonalAccount.AccountFrom")
	private String aNumberFrom = null;
	@DataItemName("Salary.Cond.PersonalAccount.AccountTill")
	private String aNumberTill = null;
	private IdentDocKind identDocKind = null;
	private FamilyStatusKind familyStatusKind = null;
	private FamilyRelation familyRelation = null;	
	private InsuredClass insuredClass = null;
	private ServiceKind serviceKind = null;
	private DeductionKind deductionKind = null;
	private PrefPosition position = null;
	private PositionFillKind positionFillKind = null;
	private int isBasic = 0;
	@DataItemName("Salary.Cond.PersonalAccount.BeginDateP")
	private Date beginDateP = null;
	@DataItemName("Salary.Cond.PersonalAccount.EndDateP")
	private Date endDateP = null;
	@DataItemName("Salary.Cond.PersonalAccount.BeginDateTC")
	private Date beginDateTC = null;
	@DataItemName("Salary.Cond.PersonalAccount.EndDateTC")
	private Date endDateTC = null;
	@DataItemName("Salary.Cond.PersonalAccount.BeginDateCLF")
	private Date beginDateCLF = null;
	@DataItemName("Salary.Cond.PersonalAccount.EndDateCLF")
	private Date endDateCLF = null;
	private StaffCategory staffCategoryCode = null;
	private WorkCondition workConditionCode = null;
	private WorkSchedule workScheduleCode = null;
	private TariffingCategory tariffingCategoryCode = null;
	private TaxCalcKind taxCalcKindCode = null;	
	@DataItemName("Salary.FeeRef.CostsAnl")
	private CostsAnl anlCode1 = null;
	@DataItemName("Salary.FeeRef.CostsAnl")
	private CostsAnl anlCode2 = null;
	@DataItemName("Salary.FeeRef.CostsAnl")
	private CostsAnl anlCode3 = null;
	@DataItemName("Salary.FeeRef.CostsAnl")
	private CostsAnl anlCode4 = null;
	@DataItemName("Salary.FeeRef.CostsAnl")
	private CostsAnl anlCode5 = null;
	@DataItemName("Salary.FeeRef.CostsAnl")
	private CostsAnl anlCode1CLF = null;
	@DataItemName("Salary.FeeRef.CostsAnl")
	private CostsAnl anlCode2CLF = null;
	@DataItemName("Salary.FeeRef.CostsAnl")
	private CostsAnl anlCode3CLF = null;
	@DataItemName("Salary.FeeRef.CostsAnl")
	private CostsAnl anlCode4CLF = null;
	@DataItemName("Salary.FeeRef.CostsAnl")
	private CostsAnl anlCode5CLF = null;	
	private FeeRef feeModel = null;
	private StaffList staffList = null;
	@DataItemName("Salary.Cond.PersonalAccount.ShowOnlyNotAttached")
	private boolean showOnlyNotAttached = false;
	


	@Override
	protected void doClearRestrictionItem() {
		this.surname = "";
		this.name = "";
		this.patronymic = "";
		this.inn = null;	
		this.aNumberFrom = null;
		this.aNumberTill = null;
		this.familyRelation = null;
		this.familyStatusKind = null;
		this.identDocKind = null;
		this.insuredClass = null;
		this.deductionKind = null;
		this.serviceKind = null;
		this.isBasic = 0;
		this.position = null;
		this.positionFillKind = null;
		this.beginDateCLF = null;
		this.beginDateP = null;
		this.beginDateTC = null;
		this.endDateCLF = null;
		this.endDateP = null;
		this.endDateTC = null;
		this.staffCategoryCode = null;
		this.workConditionCode = null;
		this.workScheduleCode = null;
		this.tariffingCategoryCode = null;
		this.taxCalcKindCode = null;	
		this.anlCode1 = null;	
		this.anlCode2 = null;	
		this.anlCode3 = null;	
		this.anlCode4 = null;	
		this.anlCode5 = null;	
		this.anlCode1CLF = null;	
		this.anlCode2CLF = null;	
		this.anlCode3CLF = null;	
		this.anlCode4CLF = null;	
		this.anlCode5CLF = null;	
		this.feeModel = null;
		this.staffList = null;
		this.showOnlyNotAttached = false;
	}



	/**
	 * @return Returns the anlCode1.
	 */
	protected CostsAnl getAnlCode1() {
		return anlCode1;
	}



	/**
	 * @return Returns the anlCode1CLF.
	 */
	protected CostsAnl getAnlCode1CLF() {
		return anlCode1CLF;
	}



	/**
	 * @return Returns the anlCode2.
	 */
	protected CostsAnl getAnlCode2() {
		return anlCode2;
	}



	/**
	 * @return Returns the anlCode2CLF.
	 */
	protected CostsAnl getAnlCode2CLF() {
		return anlCode2CLF;
	}



	/**
	 * @return Returns the anlCode3.
	 */
	protected CostsAnl getAnlCode3() {
		return anlCode3;
	}



	/**
	 * @return Returns the anlCode3CLF.
	 */
	protected CostsAnl getAnlCode3CLF() {
		return anlCode3CLF;
	}



	/**
	 * @return Returns the anlCode4.
	 */
	protected CostsAnl getAnlCode4() {
		return anlCode4;
	}



	/**
	 * @return Returns the anlCode4CLF.
	 */
	protected CostsAnl getAnlCode4CLF() {
		return anlCode4CLF;
	}



	/**
	 * @return Returns the anlCode5.
	 */
	protected CostsAnl getAnlCode5() {
		return anlCode5;
	}



	/**
	 * @return Returns the anlCode5CLF.
	 */
	protected CostsAnl getAnlCode5CLF() {
		return anlCode5CLF;
	}



	/**
	 * @return Returns the aNumberFrom.
	 */
	protected String getANumberFrom() {
		return aNumberFrom;
	}



	/**
	 * @return Returns the aNumberTill.
	 */
	protected String getANumberTill() {
		return aNumberTill;
	}



	/**
	 * @return Returns the beginDateCLF.
	 */
	protected Date getBeginDateCLF() {
		return beginDateCLF;
	}



	/**
	 * @return Returns the beginDateP.
	 */
	protected Date getBeginDateP() {
		return beginDateP;
	}



	/**
	 * @return Returns the beginDateTC.
	 */
	protected Date getBeginDateTC() {
		return beginDateTC;
	}



	/**
	 * @return Returns the deductionKind.
	 */
	protected DeductionKind getDeductionKind() {
		return deductionKind;
	}



	/**
	 * @return Returns the endDateCLF.
	 */
	protected Date getEndDateCLF() {
		return endDateCLF;
	}



	/**
	 * @return Returns the endDateP.
	 */
	protected Date getEndDateP() {
		return endDateP;
	}



	/**
	 * @return Returns the endDateTC.
	 */
	protected Date getEndDateTC() {
		return endDateTC;
	}



	/**
	 * @return Returns the familyRelation.
	 */
	protected FamilyRelation getFamilyRelation() {
		return familyRelation;
	}



	/**
	 * @return Returns the familyStatusKind.
	 */
	protected FamilyStatusKind getFamilyStatusKind() {
		return familyStatusKind;
	}



	/**
	 * @return Returns the feeModel.
	 */
	protected FeeRef getFeeModel() {
		return feeModel;
	}



	/**
	 * @return Returns the identDocKind.
	 */
	protected IdentDocKind getIdentDocKind() {
		return identDocKind;
	}



	/**
	 * @return Returns the inn.
	 */
	protected String getInn() {
		return inn;
	}



	/**
	 * @return Returns the insuredClass.
	 */
	protected InsuredClass getInsuredClass() {
		return insuredClass;
	}



	/**
	 * @return Returns the isBasic.
	 */
	protected int getIsBasic() {
		return isBasic;
	}



	/**
	 * @return Returns the name.
	 */
	protected String getName() {
		return name;
	}



	/**
	 * @return Returns the patronymic.
	 */
	protected String getPatronymic() {
		return patronymic;
	}



	/**
	 * @return Returns the position.
	 */
	protected PrefPosition getPosition() {
		return position;
	}



	/**
	 * @return Returns the positionFillKind.
	 */
	protected PositionFillKind getPositionFillKind() {
		return positionFillKind;
	}



	/**
	 * @return Returns the serviceKind.
	 */
	protected ServiceKind getServiceKind() {
		return serviceKind;
	}



	/**
	 * @return Returns the showOnlyNotAttached.
	 */
	protected boolean isShowOnlyNotAttached() {
		return showOnlyNotAttached;
	}



	/**
	 * @return Returns the staffCategoryCode.
	 */
	protected StaffCategory getStaffCategoryCode() {
		return staffCategoryCode;
	}



	/**
	 * @return Returns the staffList.
	 */
	protected StaffList getStaffList() {
		return staffList;
	}



	/**
	 * @return Returns the surname.
	 */
	protected String getSurname() {
		return surname;
	}



	/**
	 * @return Returns the tariffingCategoryCode.
	 */
	protected TariffingCategory getTariffingCategoryCode() {
		return tariffingCategoryCode;
	}



	/**
	 * @return Returns the taxCalcKindCode.
	 */
	protected TaxCalcKind getTaxCalcKindCode() {
		return taxCalcKindCode;
	}



	/**
	 * @return Returns the workConditionCode.
	 */
	protected WorkCondition getWorkConditionCode() {
		return workConditionCode;
	}



	/**
	 * @return Returns the workScheduleCode.
	 */
	protected WorkSchedule getWorkScheduleCode() {
		return workScheduleCode;
	}


}
