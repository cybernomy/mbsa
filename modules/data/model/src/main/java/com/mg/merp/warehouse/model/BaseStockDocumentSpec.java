/**
 * BaseStockDocumentSpec.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.warehouse.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import com.mg.merp.document.model.DocSpec;

/**
 * Базовый класс спецификаций складских документов
 *
 * @author Oleg V. Safonov
 * @version $Id: BaseStockDocumentSpec.java,v 1.1 2007/09/27 15:38:46 safonov Exp $
 */
public class BaseStockDocumentSpec extends DocSpec {

    // Fields
    private BigDecimal Cost;

    private BigDecimal Discount;

    private BigDecimal PriceWithDiscount;

    private BigDecimal SummaWithDiscount;

    private BigDecimal DocDiscount;

    @Transient
    private BigDecimal externalDiscountValue;

    /**
   * default constructor
   */
    public BaseStockDocumentSpec() {
    }

    @Column(name = "COST", columnDefinition = "NUMERIC", precision = 15, scale = 4)
    @DataItemName("Warehouse.WareDocSpec.Cost")
    public BigDecimal getCost() {
        return this.Cost;
    }

    public void setCost(BigDecimal Cost) {
        this.Cost = Cost;
    }

    @Column(name = "DISCOUNT", columnDefinition = "NUMERIC", precision = 18, scale = 6)
    @DataItemName("Warehouse.WareDocSpec.Discount")
    public BigDecimal getDiscount() {
        return this.Discount;
    }

    public void setDiscount(BigDecimal Discount) {
        this.Discount = Discount;
    }

    @Column(name = "PRICE_WITH_DISCOUNT", columnDefinition = "NUMERIC", precision = 15, scale = 4)
    @DataItemName("Warehouse.WareDocSpec.PriceWithDiscount")
    public BigDecimal getPriceWithDiscount() {
        return this.PriceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal PriceWithDiscount) {
        this.PriceWithDiscount = PriceWithDiscount;
    }

    @Column(name = "SUMMA_WITH_DISCOUNT", columnDefinition = "NUMERIC", precision = 15, scale = 4)
    @DataItemName("Warehouse.WareDocSpec.SummaWithDiscount")
    public BigDecimal getSummaWithDiscount() {
        return this.SummaWithDiscount;
    }

    public void setSummaWithDiscount(BigDecimal SummaWithDiscount) {
        this.SummaWithDiscount = SummaWithDiscount;
    }

    @Column(name = "DOC_DISCOUNT", columnDefinition = "NUMERIC", precision = 18, scale = 6)
    @DataItemName("Warehouse.WareDocSpec.DocDiscount")
    public BigDecimal getDocDiscount() {
        return this.DocDiscount;
    }

    public void setDocDiscount(BigDecimal DocDiscount) {
        this.DocDiscount = DocDiscount;
    }

    public BigDecimal getExternalDiscountValue() {
        return externalDiscountValue;
    }

    public void setExternalDiscountValue(BigDecimal discountValue) {
        this.externalDiscountValue = discountValue;
    }
}

