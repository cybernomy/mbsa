/*
 * ColumnDef.java
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

import com.mg.framework.support.dataset.sohlman.ColumnInfo;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: ColumnDef.java,v 1.2 2006/07/05 09:10:08 poroxnenko Exp $
 */
public class ColumnDef 
{
  private ColumnInfo colInf;
  
  public ColumnDef(String fieldName, Class fieldType)
  {
	  colInf = new ColumnInfo(fieldName, fieldType);
  }
  
  public ColumnDef(String fieldName, String fieldType) throws ClassNotFoundException
  {
	colInf = new ColumnInfo(fieldName, Class.forName(fieldType));
  }
  
  public String getFieldName()
  {
	  return colInf.getName();
  }
 
  public Class getFieldType()
  {
	  return colInf.getColumnClass();
  }
  
  public ColumnInfo getColumnInfo()
  {
	  return colInf;
  }
}
