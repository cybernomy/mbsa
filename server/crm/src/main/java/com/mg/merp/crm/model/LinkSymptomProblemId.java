/*
 * LinkSymptomProblemId.java
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
package com.mg.merp.crm.model;



/**
 * @author hbm2java
 * @version $Id: LinkSymptomProblemId.java,v 1.1 2005/06/10 06:52:18 safonov Exp $
 */
public class LinkSymptomProblemId extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private com.mg.merp.crm.model.Symptom CrmSymptom;
 private com.mg.merp.crm.model.Problem CrmProblem;
 private com.mg.merp.core.model.SysClient SysClient;


    // Constructors

    /** default constructor */
    public LinkSymptomProblemId() {
    }
    
   
    
    

    // Property accessors
    /**
    
    */
    
    public com.mg.merp.crm.model.Symptom getCrmSymptom () {
        return this.CrmSymptom;
    }
    
   public void setCrmSymptom (com.mg.merp.crm.model.Symptom CrmSymptom) {
        this.CrmSymptom = CrmSymptom;
    }
    /**
    
    */
    
    public com.mg.merp.crm.model.Problem getCrmProblem () {
        return this.CrmProblem;
    }
    
   public void setCrmProblem (com.mg.merp.crm.model.Problem CrmProblem) {
        this.CrmProblem = CrmProblem;
    }
    /**
    
    */
    
    public com.mg.merp.core.model.SysClient getSysClient () {
        return this.SysClient;
    }
    
   public void setSysClient (com.mg.merp.core.model.SysClient SysClient) {
        this.SysClient = SysClient;
    }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof LinkSymptomProblemId) ) return false;
		 LinkSymptomProblemId castOther = ( LinkSymptomProblemId ) other; 
         
		 return (this.getCrmSymptom()==castOther.getCrmSymptom()) || (this.getCrmSymptom()==null ? false : (castOther.getCrmSymptom()==null ? false : this.getCrmSymptom().equals(castOther.getCrmSymptom())))
 && (this.getCrmProblem()==castOther.getCrmProblem()) || (this.getCrmProblem()==null ? false : (castOther.getCrmProblem()==null ? false : this.getCrmProblem().equals(castOther.getCrmProblem())))
 && (this.getSysClient()==castOther.getSysClient()) || (this.getSysClient()==null ? false : (castOther.getSysClient()==null ? false : this.getSysClient().equals(castOther.getSysClient())));
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getCrmSymptom().hashCode();
         result = 37 * result + this.getCrmProblem().hashCode();
         result = 37 * result + this.getSysClient().hashCode();
         return result;
   }   



}