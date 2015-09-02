/*
 * Catalog.java
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
package com.mg.merp.reference.model;

import java.util.Date;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Каталог"
 * 
 * @author Artem V. Sharapov
 * @sysVersion $Id: Catalog.java,v 1.6 2008/08/27 09:33:32 sharapov Exp $
 */
@DataItemName("Reference.Catalog") //$NON-NLS-1$
public class Catalog extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields

    private int Id;

    private com.mg.merp.reference.model.TaxGroup TaxGroup;

    private com.mg.merp.reference.model.Measure Measure1;

    private com.mg.merp.reference.model.Measure Measure2;

    private com.mg.merp.reference.model.CatalogFolder Folder;

    private com.mg.merp.core.model.SysClient SysClient;

    private com.mg.merp.reference.model.Okp Okp;

    private com.mg.merp.reference.model.Measure WeightMeasure;

    private com.mg.merp.reference.model.Okdp Okdp;

    private com.mg.merp.reference.model.Measure VolumeMeasure;

    private java.lang.String UpCode;

    private java.lang.String Code;

    private java.lang.String FullName;

    private CatalogType GoodType;

    private java.lang.String BarCode;

    private java.lang.String PluCode;

    private boolean IsHasWeight;

    private java.math.BigDecimal Weight;

    private java.math.BigDecimal Volume;

    private java.math.BigDecimal MarketingMargin;

    private java.lang.Short Validity;

    private boolean IsNotInUse;

    private java.math.BigDecimal ShelfLife;

    private TimePeriodKind ShelfLifeMeas;

    private CatalogExpDate ExpDateCalcKind;

    private java.lang.String Articul;

    private MeasureControl MeasureControl;

    private boolean Packaged;

    private boolean UseSerialNum;

//    private com.mg.merp.planning.model.GenericItem GenericItem;

    private Date sysVersion;
    
    private CustomsDeclaration customsDeclaration;
    
    private Country countryOfOrigin;
    
    private java.util.Set SetOfCatalogPrice;

    private java.util.Set SetOfSetOfGood;

    private java.util.Set SetOfPacking;

    // Constructors

    /** default constructor */
    public Catalog() {
    }

    /** constructor with id */
    public Catalog(int Id) {
        this.Id = Id;
    }

    // Property accessors
    /**
     * 
     */
    @DataItemName("ID") //$NON-NLS-1$
    public int getId() {
        return this.Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * 
     */
    @DataItemName ("Reference.Catalog.TaxGroup") //$NON-NLS-1$
    public com.mg.merp.reference.model.TaxGroup getTaxGroup() {
        return this.TaxGroup;
    }

    public void setTaxGroup(com.mg.merp.reference.model.TaxGroup TaxGroup) {
        this.TaxGroup = TaxGroup;
    }

    /**
     * 
     */
    @DataItemName("Reference.Catalog.Measure1") //$NON-NLS-1$
    public com.mg.merp.reference.model.Measure getMeasure1() {
        return this.Measure1;
    }

    public void setMeasure1(com.mg.merp.reference.model.Measure Measure1) {
        this.Measure1 = Measure1;
    }

    /**
     * 
     */
    @DataItemName("Reference.Catalog.Measure2") //$NON-NLS-1$
    public com.mg.merp.reference.model.Measure getMeasure2() {
        return this.Measure2;
    }

    public void setMeasure2(com.mg.merp.reference.model.Measure Measure2) {
        this.Measure2 = Measure2;
    }

    /**
     * 
     */
    public com.mg.merp.reference.model.CatalogFolder getFolder() {
        return this.Folder;
    }

    public void setFolder(com.mg.merp.reference.model.CatalogFolder Folder) {
        this.Folder = Folder;
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
    public com.mg.merp.reference.model.Okp getOkp() {
        return this.Okp;
    }

    public void setOkp(com.mg.merp.reference.model.Okp Okp) {
        this.Okp = Okp;
    }

    /**
     * 
     */
    @DataItemName ("Reference.Catalog.WeightMeasure") //$NON-NLS-1$
    public com.mg.merp.reference.model.Measure getWeightMeasure() {
        return this.WeightMeasure;
    }

    public void setWeightMeasure(com.mg.merp.reference.model.Measure WeightMeasure) {
        this.WeightMeasure = WeightMeasure;
    }

    /**
     * 
     */ 
    public com.mg.merp.reference.model.Okdp getOkdp() {
        return this.Okdp;
    }

    public void setOkdp(com.mg.merp.reference.model.Okdp Okdp) {
        this.Okdp = Okdp;
    }

    /**
     * 
     */
    @DataItemName ("Reference.Catalog.VolumeMeasure") //$NON-NLS-1$
    public com.mg.merp.reference.model.Measure getVolumeMeasure() {
        return this.VolumeMeasure;
    }

    public void setVolumeMeasure(com.mg.merp.reference.model.Measure VolumeMeasure) {
        this.VolumeMeasure = VolumeMeasure;
    }

    /**
     * 
     */
    public java.lang.String getUpCode() {
        return this.UpCode;
    }

    public void setUpCode(java.lang.String UpCode) {
        this.UpCode = UpCode;
    }

    /**
     * 
     */
    @DataItemName("Reference.Code") //$NON-NLS-1$
    public java.lang.String getCode() {
        return this.Code;
    }

    public void setCode(java.lang.String Code) {
        this.Code = Code;
    }

    /**
     * 
     */
    @DataItemName("Reference.Name") //$NON-NLS-1$
    public java.lang.String getFullName() {
        return this.FullName;
    }

    public void setFullName(java.lang.String FullName) {
        this.FullName = FullName;
    }

    /**
     * 
     */
    public CatalogType getGoodType() {
        return this.GoodType;
    }

    public void setGoodType(CatalogType GoodType) {
        this.GoodType = GoodType;
    }

    /**
     * 
     */
    @DataItemName("Reference.Catalog.BarCode") //$NON-NLS-1$
    public java.lang.String getBarCode() {
        return this.BarCode;
    }

    public void setBarCode(java.lang.String BarCode) {
        this.BarCode = BarCode;
    }

    /**
     * 
     */
    @DataItemName ("Reference.Catalog.PluCode") //$NON-NLS-1$
    public java.lang.String getPluCode() {
        return this.PluCode;
    }

    public void setPluCode(java.lang.String PluCode) {
        this.PluCode = PluCode;
    }

    /**
     * 
     */
    @DataItemName ("Reference.Catalog.IsHasWeight") //$NON-NLS-1$
    public boolean getIsHasWeight() {
        return this.IsHasWeight;
    }

    public void setIsHasWeight(boolean IsHasWeight) {
        this.IsHasWeight = IsHasWeight;
    }

    /**
     * 
     */
    @DataItemName ("Reference.Catalog.Weight") //$NON-NLS-1$
    public java.math.BigDecimal getWeight() {
        return this.Weight;
    }

    public void setWeight(java.math.BigDecimal Weight) {
        this.Weight = Weight;
    }

    /**
     * 
     */
    @DataItemName ("Reference.Catalog.Volume") //$NON-NLS-1$
    public java.math.BigDecimal getVolume() {
        return this.Volume;
    }

    public void setVolume(java.math.BigDecimal Volume) {
        this.Volume = Volume;
    }

    /**
     * 
     */
    @DataItemName ("Reference.Catalog.MarketMargin") //$NON-NLS-1$
    public java.math.BigDecimal getMarketingMargin() {
        return this.MarketingMargin;
    }

    public void setMarketingMargin(java.math.BigDecimal MarketingMargin) {
        this.MarketingMargin = MarketingMargin;
    }

    /**
     * 
     */
    public java.lang.Short getValidity() {
        return this.Validity;
    }

    public void setValidity(java.lang.Short Validity) {
        this.Validity = Validity;
    }

    /**
     * 
     */
    @DataItemName ("Reference.Catalog.IsNotInUse") //$NON-NLS-1$
    public boolean getIsNotInUse() {
        return this.IsNotInUse;
    }

    public void setIsNotInUse(boolean IsNotInUse) {
        this.IsNotInUse = IsNotInUse;
    }

    /**
     * 
     */
    @DataItemName ("Reference.Catalog.ShelfLife") //$NON-NLS-1$
    public java.math.BigDecimal getShelfLife() {
        return this.ShelfLife;
    }

    public void setShelfLife(java.math.BigDecimal ShelfLife) {
        this.ShelfLife = ShelfLife;
    }

    /**
     * 
     */
    public TimePeriodKind getShelfLifeMeas() {
        return this.ShelfLifeMeas;
    }

    public void setShelfLifeMeas(TimePeriodKind ShelfLifeMeas) {
        this.ShelfLifeMeas = ShelfLifeMeas;
    }

    /**
     * 
     */
    public CatalogExpDate getExpDateCalcKind() {
        return this.ExpDateCalcKind;
    }

    public void setExpDateCalcKind(CatalogExpDate ExpDateCalcKind) {
        this.ExpDateCalcKind = ExpDateCalcKind;
    }

    /**
     * 
     */
    @DataItemName ("Reference.Catalog.Articul") //$NON-NLS-1$
    public java.lang.String getArticul() {
        return this.Articul;
    }

    public void setArticul(java.lang.String Articul) {
        this.Articul = Articul;
    }

    /**
     * 
     */
    public MeasureControl getMeasureControl() {
        return this.MeasureControl;
    }

    public void setMeasureControl(MeasureControl MeasureControl) {
        this.MeasureControl = MeasureControl;
    }

    /**
     * 
     */
    @DataItemName ("Reference.Catalog.Packaged") //$NON-NLS-1$
    public boolean getPackaged() {
        return this.Packaged;
    }

    public void setPackaged(boolean Packaged) {
        this.Packaged = Packaged;
    }

    /**
     * 
     */
    @DataItemName ("Reference.Catalog.UseSerialNum") //$NON-NLS-1$
    public boolean getUseSerialNum() {
        return this.UseSerialNum;
    }

    public void setUseSerialNum(boolean UseSerialNum) {
        this.UseSerialNum = UseSerialNum;
    }

    /**
     * 
     */
//    @DataItemName ("Reference.Catalog.GenericItem")
//    public com.mg.merp.planning.model.GenericItem getGenericItem() {
//        return this.GenericItem;
//    }
//
//    public void setGenericItem(
//            com.mg.merp.planning.model.GenericItem GenericItem) {
//        this.GenericItem = GenericItem;
//    }

    /**
     * 
     */
    public java.util.Set getSetOfCatalogPrice() {
        return this.SetOfCatalogPrice;
    }

    public void setSetOfCatalogPrice(java.util.Set SetOfCatalogprice) {
        this.SetOfCatalogPrice = SetOfCatalogprice;
    }

    /**
     * 
     */
    public java.util.Set getSetOfSetOfGood() {
        return this.SetOfSetOfGood;
    }

    public void setSetOfSetOfGood(java.util.Set SetOfSetofgood) {
        this.SetOfSetOfGood = SetOfSetofgood;
    }

    /**
     * 
     */
    public java.util.Set getSetOfPacking() {
        return this.SetOfPacking;
    }

    public void setSetOfPacking(java.util.Set SetOfPacking) {
        this.SetOfPacking = SetOfPacking;
    }

	/**
	 * @return Returns the sysVersion.
	 */
	public Date getSysVersion() {
		return sysVersion;
	}

	/**
	 * @param sysVersion The sysVersion to set.
	 */
	public void setSysVersion(Date version) {
		this.sysVersion = version;
	}

	/**
	 * @return the customsDeclaration
	 */
	@DataItemName("Reference.CD") //$NON-NLS-1$
	public CustomsDeclaration getCustomsDeclaration() {
		return this.customsDeclaration;
	}

	/**
	 * @param customsDeclaration the customsDeclaration to set
	 */
	public void setCustomsDeclaration(CustomsDeclaration customsDeclaration) {
		this.customsDeclaration = customsDeclaration;
	}

	/**
	 * @return the countryOfOrigin
	 */
	@DataItemName("Reference.CountryOfOrigin") //$NON-NLS-1$
	public Country getCountryOfOrigin() {
		return this.countryOfOrigin;
	}

	/**
	 * @param countryOfOrigin the countryOfOrigin to set
	 */
	public void setCountryOfOrigin(Country countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}
	
}