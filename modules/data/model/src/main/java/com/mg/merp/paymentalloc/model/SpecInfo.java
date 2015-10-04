/*
 * SpecInfo.java
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
package com.mg.merp.paymentalloc.model;

import java.math.BigDecimal;

/**
 * Модель данных для распределения платежа по спецификации документа
 *
 * @author Artem V. Sharapov
 * @version $Id: SpecInfo.java,v 1.1 2007/05/31 14:10:30 sharapov Exp $
 */
public class SpecInfo {

    private com.mg.merp.document.model.DocSpec docSpec;

    private String code;

    private String cName;

    private String mCode;

    private BigDecimal summa;

    private BigDecimal qty;

    private BigDecimal aSumma;

    private BigDecimal aQty;

    private BigDecimal cSumma;

    private BigDecimal cQty;

    public SpecInfo() {
    }

    public BigDecimal getAQty() {
        return this.aQty;
    }

    public void setAQty(BigDecimal qty) {
        this.aQty = qty;
    }

    public BigDecimal getASumma() {
        return this.aSumma;
    }

    public void setASumma(BigDecimal summa) {
        this.aSumma = summa;
    }

    public String getCName() {
        return this.cName;
    }

    public void setCName(String name) {
        this.cName = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getCQty() {
        return this.cQty;
    }

    public void setCQty(BigDecimal qty) {
        this.cQty = qty;
    }

    public BigDecimal getCSumma() {
        return this.cSumma;
    }

    public void setCSumma(BigDecimal summa) {
        this.cSumma = summa;
    }

    public String getMCode() {
        return this.mCode;
    }

    public void setMCode(String code) {
        this.mCode = code;
    }

    public BigDecimal getQty() {
        return this.qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getSumma() {
        return this.summa;
    }

    public void setSumma(BigDecimal summa) {
        this.summa = summa;
    }

    public com.mg.merp.document.model.DocSpec getDocSpec() {
        return this.docSpec;
    }

    public void setDocSpec(com.mg.merp.document.model.DocSpec docSpec) {
        this.docSpec = docSpec;
    }
}

