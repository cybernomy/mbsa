/*
 * ContractFactItemSpecData.java
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
package com.mg.merp.contract.model;

import com.mg.merp.reference.model.Catalog;
import java.math.BigDecimal;

/**
 * Структура данных для создания спецификации фактического пункта контракта
 *
 * @author Artem V. Sharapov
 * @version $Id: ContractFactItemSpecData.java,v 1.1 2007/03/07 12:31:28 sharapov Exp $
 */
public class ContractFactItemSpecData {

    // Fields
    private com.mg.merp.reference.model.Catalog catalog;

    private java.math.BigDecimal quantity;

    private java.math.BigDecimal price;

    private java.math.BigDecimal summa;

    /* Default constractor */
    public ContractFactItemSpecData() {
    }

    public ContractFactItemSpecData(Catalog catalog, BigDecimal quantity, BigDecimal price, BigDecimal summa) {
        this.catalog = catalog;
        this.quantity = quantity;
        this.price = price;
        this.summa = summa;
    }

    // Property accessors
    /**
   * @return the catalog
   */
    public com.mg.merp.reference.model.Catalog getCatalog() {
        return catalog;
    }

    /**
   * @param catalog the catalog to set
   */
    public void setCatalog(com.mg.merp.reference.model.Catalog catalog) {
        this.catalog = catalog;
    }

    /**
   * @return the price
   */
    public java.math.BigDecimal getPrice() {
        return price;
    }

    /**
   * @param price the price to set
   */
    public void setPrice(java.math.BigDecimal price) {
        this.price = price;
    }

    /**
   * @return the quantity
   */
    public java.math.BigDecimal getQuantity() {
        return quantity;
    }

    /**
   * @param quantity the quantity to set
   */
    public void setQuantity(java.math.BigDecimal quantity) {
        this.quantity = quantity;
    }

    /**
   * @return the summa
   */
    public java.math.BigDecimal getSumma() {
        return summa;
    }

    /**
   * @param summa the summa to set
   */
    public void setSumma(java.math.BigDecimal summa) {
        this.summa = summa;
    }
}

