/*
 * PriceListSpecRest.java
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
package com.mg.merp.reference.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm;
import com.mg.merp.reference.model.CatalogType;
import com.mg.merp.reference.model.PriceType;

/**
 * ���������� ����� ������� ������ ������������ �����-������ 
 * 
 * @author Artem V. Sharapov
 * @author Konstantin S. Alikaev
 * @version $Id: PriceListSpecRest.java,v 1.5 2008/10/13 05:50:58 sharapov Exp $
 */
public class PriceListSpecRest extends DefaultHierarhyRestrictionForm {

	@DataItemName("Reference.Cond.PriceListSpec.SpecName") //$NON-NLS-1$
	private String specName = null;

	@DataItemName("Reference.Cond.PriceListSpec.SpecFullName") //$NON-NLS-1$
	private String specFullName = null;

	@DataItemName("Reference.Cond.PriceListSpec.Code") //$NON-NLS-1$
	private String code = null;

	/*
	 * ��� ������� 
	 */
	private CatalogType goodType = null;

	/*
	 * ������������� �� 
	 */
	@DataItemName("Reference.Cond.PriceListSpec.DateTill") //$NON-NLS-1$
	private java.util.Date dateTill= null;

	/*
	 * ������� ����������
	 */
	@DataItemName("Reference.Cond.PriceListSpec.InternalCode") //$NON-NLS-1$
	private String internalCode = null;

	/*
	 * �������
	 */
	@DataItemName("Reference.Cond.PriceListSpec.Articul") //$NON-NLS-1$
	private String articul = null;

	@DataItemName("Reference.Cond.PriceListSpec.NotInUse")	 //$NON-NLS-1$
	private boolean notInUse = false;

	@DataItemName("Reference.Cond.PriceListSpec.ShowInUse")	 //$NON-NLS-1$
	private boolean showInUse = false;

	/**
	 * ������������ ������� "��� ����"
	 */
	@DataItemName("Reference.Cond.PriceListSpec.NotInUsePriceType")	 //$NON-NLS-1$
	private boolean notInUsePriceType = false;

	/**
	 * ��� ����
	 */
	@DataItemName("Reference.PriceType")	 //$NON-NLS-1$
	private PriceType priceType = null;
	
	private Integer priceListHeadId;
	
	@Override
	protected void doClearRestrictionItem() {
		super.doClearRestrictionItem();
		this.specName = null;
		this.specFullName = null;
		this.code = null;
		this.internalCode = null;
		this.articul = null;
		this.goodType = null;
		this.dateTill= null;
		this.notInUse = false;
		this.showInUse = false;
		this.notInUsePriceType = false;
		this.priceType = null;
	}

	/**
	 * @return ������������ ������������(�������) �����-�����
	 */
	public String getSpecName() {
		return specName;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return ������� ����������
	 */
	public String getInternalCode() {
		return internalCode;
	}

	/**
	 * @return ������� 
	 */
	public String getArticul() {
		return articul;
	}

	/**
	 * @return ������������ � �����-�����
	 */
	public String getSpecFullName() {
		return specFullName;
	}

	/**
	 * @return ������������� �� 
	 */
	public java.util.Date getDateTill() {
		return dateTill;
	}

	/**
	 * @return ��� �������
	 */
	public CatalogType getGoodType() {
		return goodType;
	}

	/**
	 * @return the notInUse
	 */
	public boolean isNotInUse() {
		return notInUse;
	}

	/**
	 * @return the showInUse
	 */
	public boolean isShowInUse() {
		return showInUse;
	}

	public PriceType getPriceType() {
		return priceType;
	}

	public boolean isNotInUsePriceType() {
		return notInUsePriceType;
	}

	/**
	 * @return the priceListHeadId
	 */
	public Integer getPriceListHeadId() {
		return this.priceListHeadId;
	}

	/**
	 * ���������� ������������� �����-�����
	 * @param priceListHeadId - ������������� �����-�����
	 */
	public void setPriceListHeadId(Integer priceListHeadId) {
		this.priceListHeadId = priceListHeadId;
	}

}
