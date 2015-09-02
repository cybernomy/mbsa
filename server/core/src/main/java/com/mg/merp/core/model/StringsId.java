/*
 * StringsId.java
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
package com.mg.merp.core.model;


/**
 * @author hbm2java
 * @version $Id: StringsId.java,v 1.1 2005/06/10 06:52:29 safonov Exp $
 */
public class StringsId extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private java.lang.Integer Section;
 private java.lang.Integer Val;


    // Constructors

    /** default constructor */
    public StringsId() {
    }
    
   
    
    

    // Property accessors
    /**
    
    */
    
    public java.lang.Integer getSection () {
        return this.Section;
    }
    
   public void setSection (java.lang.Integer Section) {
        this.Section = Section;
    }
    /**
    
    */
    
    public java.lang.Integer getVal () {
        return this.Val;
    }
    
   public void setVal (java.lang.Integer Val) {
        this.Val = Val;
    }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof StringsId) ) return false;
		 StringsId castOther = ( StringsId ) other; 
         
		 return (this.getSection()==castOther.getSection()) || (this.getSection()==null ? false : (castOther.getSection()==null ? false : this.getSection().equals(castOther.getSection())))
 && (this.getVal()==castOther.getVal()) || (this.getVal()==null ? false : (castOther.getVal()==null ? false : this.getVal().equals(castOther.getVal())));
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getSection().hashCode();
         result = 37 * result + this.getVal().hashCode();
         return result;
   }   



}