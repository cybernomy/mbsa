/*
 * PmcConfigId.java
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
package com.mg.merp.paymentcontrol.model;

/**
 * @author hbm2java
 * @version $Id: PmcConfigId.java,v 1.2 2005/06/23 05:12:27 pashistova Exp $
 */
public class PmcConfigId extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields
    private com.mg.merp.core.model.SysClient SysClient;

    private com.mg.merp.reference.model.Currency Currency;

    private com.mg.merp.reference.model.CurrencyRateType CurRateType;

    private com.mg.merp.reference.model.CurrencyRateAuthority CurRateAuthority;

    private java.lang.Short CurrencyPrec;

    /**
   * default constructor
   */
    public PmcConfigId() {
    }

    // Property accessors
    /**

   */
    public com.mg.merp.core.model.SysClient getSysClient() {
        return this.SysClient;
    }

    public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
        this.SysClient = SysClient;
    }

    /**

   */
    public com.mg.merp.reference.model.Currency getCurrency() {
        return this.Currency;
    }

    public void setCurrency(com.mg.merp.reference.model.Currency Currency) {
        this.Currency = Currency;
    }

    /**

   */
    public com.mg.merp.reference.model.CurrencyRateType getCurRateType() {
        return this.CurRateType;
    }

    public void setCurRateType(com.mg.merp.reference.model.CurrencyRateType RefCurrencyRateType) {
        this.CurRateType = RefCurrencyRateType;
    }

    /**

   */
    public com.mg.merp.reference.model.CurrencyRateAuthority getCurRateAuthority() {
        return this.CurRateAuthority;
    }

    public void setCurRateAuthority(com.mg.merp.reference.model.CurrencyRateAuthority RefCurrencyRateAuthority) {
        this.CurRateAuthority = RefCurrencyRateAuthority;
    }

    /**

   */
    public java.lang.Short getCurrencyPrec() {
        return this.CurrencyPrec;
    }

    public void setCurrencyPrec(java.lang.Short CurrencyPrec) {
        this.CurrencyPrec = CurrencyPrec;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof PmcConfigId))
            return false;
        PmcConfigId castOther = (PmcConfigId) other;
        return (this.getSysClient() == castOther.getSysClient()) || (this.getSysClient() == null ? false : (castOther.getSysClient() == null ? false : this.getSysClient().equals(castOther.getSysClient()))) && (this.getCurrency() == castOther.getCurrency()) || (this.getCurrency() == null ? false : (castOther.getCurrency() == null ? false : this.getCurrency().equals(castOther.getCurrency()))) && (this.getCurRateType() == castOther.getCurRateType()) || (this.getCurRateType() == null ? false : (castOther.getCurRateType() == null ? false : this.getCurRateType().equals(castOther.getCurRateType()))) && (this.getCurRateAuthority() == castOther.getCurRateAuthority()) || (this.getCurRateAuthority() == null ? false : (castOther.getCurRateAuthority() == null ? false : this.getCurRateAuthority().equals(castOther.getCurRateAuthority()))) && (this.getCurrencyPrec() == castOther.getCurrencyPrec()) || (this.getCurrencyPrec() == null ? false : (castOther.getCurrencyPrec() == null ? false : this.getCurrencyPrec().equals(castOther.getCurrencyPrec())));
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + this.getSysClient().hashCode();
        result = 37 * result + this.getCurrency().hashCode();
        result = 37 * result + this.getCurRateType().hashCode();
        result = 37 * result + this.getCurRateAuthority().hashCode();
        result = 37 * result + this.getCurrencyPrec().hashCode();
        return result;
    }
}

