/*
 * InputDocumentSpec.java
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
package com.mg.merp.manufacture.model;




/**
 * @author hbm2java
 * @version $Id: InputDocumentSpec.java,v 1.3 2005/07/13 13:53:13 safonov Exp $
 */
public class InputDocumentSpec extends com.mg.merp.document.model.DocSpec implements java.io.Serializable {

    // Fields    

 private com.mg.merp.manufacture.model.JobRouteResource JobRouteResource;
 private com.mg.merp.mfreference.model.CostCategories CostCategory;


    // Constructors

    /** default constructor */
    public InputDocumentSpec() {
    }
    
 
    // Property accessors
    /**
    
    */
  
    
    public com.mg.merp.manufacture.model.JobRouteResource getJobRouteResource () {
        return this.JobRouteResource;
    }
    
   public void setJobRouteResource (com.mg.merp.manufacture.model.JobRouteResource MfJobRouteResource) {
        this.JobRouteResource = MfJobRouteResource;
    }
    
    /**
    
    */
    
    public com.mg.merp.mfreference.model.CostCategories getCostCategory () {
        return this.CostCategory;
    }
    
   public void setCostCategory (com.mg.merp.mfreference.model.CostCategories MfCostCategories) {
        this.CostCategory = MfCostCategories;
    }




}