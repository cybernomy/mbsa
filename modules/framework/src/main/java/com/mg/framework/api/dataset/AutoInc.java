/*
 * AutoInc.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.framework.api.dataset;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: AutoInc.java,v 1.2 2006/07/05 09:10:08 poroxnenko Exp $
 */
public class AutoInc 
{
  private Integer curValue;	
  public AutoInc()
  {
	  this.curValue = 1;
  }
  public Integer inc()
  {
	  return curValue++;
  }
  public Integer getValue()
  {
	  return this.curValue;
  }
}
