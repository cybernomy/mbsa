/*
 * ConfigId.java
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
package com.mg.merp.exchange.model;



/**
 * @author hbm2java
 * @version $Id: ConfigId.java,v 1.1 2005/06/10 06:53:17 safonov Exp $
 */
public class ConfigId extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private com.mg.merp.core.model.SysClient SysClient;
 private java.lang.String SiteCode;


    // Constructors

    /** default constructor */
    public ConfigId() {
    }
    
   
    
    

    // Property accessors
    /**
    
    */
    
    public com.mg.merp.core.model.SysClient getSysClient () {
        return this.SysClient;
    }
    
   public void setSysClient (com.mg.merp.core.model.SysClient SysClient) {
        this.SysClient = SysClient;
    }
    /**
    
    */
    
    public java.lang.String getSiteCode () {
        return this.SiteCode;
    }
    
   public void setSiteCode (java.lang.String SiteCode) {
        this.SiteCode = SiteCode;
    }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ConfigId) ) return false;
		 ConfigId castOther = ( ConfigId ) other; 
         
		 return (this.getSysClient()==castOther.getSysClient()) || (this.getSysClient()==null ? false : (castOther.getSysClient()==null ? false : this.getSysClient().equals(castOther.getSysClient())))
 && (this.getSiteCode()==castOther.getSiteCode()) || (this.getSiteCode()==null ? false : (castOther.getSiteCode()==null ? false : this.getSiteCode().equals(castOther.getSiteCode())));
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getSysClient().hashCode();
         result = 37 * result + this.getSiteCode().hashCode();
         return result;
   }   



}