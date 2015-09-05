/*
 * RemnAcc.java
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
package com.mg.merp.account.model;

import com.mg.framework.api.annotations.DataItemName;



/**
 * @author hbm2java
 * @version $Id: RemnAcc.java,v 1.5 2006/06/22 11:12:19 leonova Exp $
 */
public class RemnAcc extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

    // Fields    

 private java.lang.Integer Id;
 private com.mg.merp.account.model.AccPlan AccPlan;
 private com.mg.merp.core.model.SysClient SysClient;
 private com.mg.merp.account.model.Period Period;
 private java.math.BigDecimal RemnBeginNatDb;
 private java.math.BigDecimal RemnBeginNatKt;
 private java.math.BigDecimal RemnBeginCurDb;
 private java.math.BigDecimal RemnBeginCurKt;
 private java.math.BigDecimal remnEndNatDb;
 private java.math.BigDecimal remnEndNatKt;
 private java.math.BigDecimal remnEndCurKt;
 private java.math.BigDecimal remnEndCurDb;
 private java.math.BigDecimal turnNatKt;
 private java.math.BigDecimal turnNatDb;
 private java.math.BigDecimal turnCurDb;
 private java.math.BigDecimal turnCurKt;


    // Constructors

    /** default constructor */
    public RemnAcc() {
    }
    
    /** constructor with id */
    public RemnAcc(java.lang.Integer Id) {
        this.Id = Id;
    }
   
    
    

    // Property accessors
    /**
    
    */
    @DataItemName("ID")
    public java.lang.Integer getId () {
        return this.Id;
    }
    
   public void setId (java.lang.Integer Id) {
        this.Id = Id;
    }
    /**
    
    */
    
    public com.mg.merp.account.model.AccPlan getAccPlan () {
        return this.AccPlan;
    }
    
   public void setAccPlan (com.mg.merp.account.model.AccPlan Accplan) {
        this.AccPlan = Accplan;
    }
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
    
    public com.mg.merp.account.model.Period getPeriod () {
        return this.Period;
    }
    
   public void setPeriod (com.mg.merp.account.model.Period Period) {
        this.Period = Period;
    }
    /**
    
    */
    @DataItemName("Account.RemnAcc.RemnBeginNatDb")
    public java.math.BigDecimal getRemnBeginNatDb () {
        return this.RemnBeginNatDb;
    }
    
   public void setRemnBeginNatDb (java.math.BigDecimal Remnbeginnatdb) {
        this.RemnBeginNatDb = Remnbeginnatdb;
    }
    /**
    
    */
   @DataItemName("Account.RemnAcc.RemnBeginNatKt")
    public java.math.BigDecimal getRemnBeginNatKt () {
        return this.RemnBeginNatKt;
    }
    
   public void setRemnBeginNatKt (java.math.BigDecimal Remnbeginnatkt) {
        this.RemnBeginNatKt = Remnbeginnatkt;
    }
    /**
    
    */
   @DataItemName("Account.RemnAcc.RemnBeginCurDb")
    public java.math.BigDecimal getRemnBeginCurDb () {
        return this.RemnBeginCurDb;
    }
    
   public void setRemnBeginCurDb (java.math.BigDecimal Remnbegincurdb) {
        this.RemnBeginCurDb = Remnbegincurdb;
    }
    /**
    
    */
   @DataItemName("Account.RemnAcc.RemnBeginCurKt")
    public java.math.BigDecimal getRemnBeginCurKt () {
        return this.RemnBeginCurKt;
    }
    
   public void setRemnBeginCurKt (java.math.BigDecimal Remnbegincurkt) {
        this.RemnBeginCurKt = Remnbegincurkt;
    }

   @DataItemName("Account.RemnAcc.RemnEndCurDb")
	public java.math.BigDecimal getRemnEndCurDb() {
		return remnEndCurDb;
	}
	
	public void setRemnEndCurDb(java.math.BigDecimal remnEndCurDb) {
		this.remnEndCurDb = remnEndCurDb;
	}
	
	@DataItemName("Account.RemnAcc.RemnEndCurKt")
	public java.math.BigDecimal getRemnEndCurKt() {
		return remnEndCurKt;
	}
	
	public void setRemnEndCurKt(java.math.BigDecimal remnEndCurKt) {
		this.remnEndCurKt = remnEndCurKt;
	}
	
	@DataItemName("Account.RemnAcc.RemnEndNatDb")
	public java.math.BigDecimal getRemnEndNatDb() {
		return remnEndNatDb;
	}
	
	public void setRemnEndNatDb(java.math.BigDecimal remnEndNatDb) {
		this.remnEndNatDb = remnEndNatDb;
	}
	
	@DataItemName("Account.RemnAcc.RemnEndNatKt")
	public java.math.BigDecimal getRemnEndNatKt() {
		return remnEndNatKt;
	}
	
	public void setRemnEndNatKt(java.math.BigDecimal remnEndNatKt) {
		this.remnEndNatKt = remnEndNatKt;
	}
	
	@DataItemName("Account.RemnAcc.TurnCurDb")
	public java.math.BigDecimal getTurnCurDb() {
		return turnCurDb;
	}
	
	public void setTurnCurDb(java.math.BigDecimal turnCurDb) {
		this.turnCurDb = turnCurDb;
	}
	
	@DataItemName("Account.RemnAcc.TurnCurKt")
	public java.math.BigDecimal getTurnCurKt() {
		return turnCurKt;
	}
	
	public void setTurnCurKt(java.math.BigDecimal turnCurKt) {
		this.turnCurKt = turnCurKt;
	}
	
	@DataItemName("Account.RemnAcc.TurnNatDb")
	public java.math.BigDecimal getTurnNatDb() {
		return turnNatDb;
	}
	
	public void setTurnNatDb(java.math.BigDecimal turnNatDb) {
		this.turnNatDb = turnNatDb;
	}
	
	@DataItemName("Account.RemnAcc.TurnNatKt")
	public java.math.BigDecimal getTurnNatKt() {
		return turnNatKt;
	}
	
	public void setTurnNatKt(java.math.BigDecimal turnNatKt) {
		this.turnNatKt = turnNatKt;
	}
}