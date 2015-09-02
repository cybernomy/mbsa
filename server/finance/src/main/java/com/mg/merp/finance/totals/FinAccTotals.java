/*
 * FinAccTotals.java
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
package com.mg.merp.finance.totals;

import static com.mg.merp.finance.totals.FinanceTotals.FinanceTotalsKind.*;
import java.util.*;
import com.mg.framework.api.jdbc.DataAccessException;
import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.merp.finance.totals.helperclasses.jdbctemplates.PeriodItem;
import com.mg.merp.finance.totals.helperclasses.jdbctemplates.PeriodItemRM;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: FinAccTotals.java,v 1.6 2006/07/05 09:01:51 poroxnenko Exp $
 */
public class FinAccTotals extends FinanceTotals
{
  static final String JOIN_ACC_PLAN = " left join finaccount a on a.id=r.acc_id";
	
  protected String[] createSQLParts(String fields, String from,
		                        String where, String order)
  {
    boolean  isAccplanJoined = false;
    fields = fields + ", r.ACC_ID";
    if(this.fields.contains("PNAME"))
      fields = fields + ", p.PNAME";
    if(this.fields.contains("DATETO"))
        fields = fields + ", p.DATETO";
    if(this.fields.contains("ACCFOLDER_ID"))
    {
        fields = fields + ", a.FOLDER_ID ACCFOLDER_ID";
        from = from + JOIN_ACC_PLAN;
        isAccplanJoined = true;
    }
    if(this.fields.contains("ACC_CODE"))
    {
        fields = fields + ", a.CODE ACC_CODE";
        if (!isAccplanJoined)
        {
          from = from + JOIN_ACC_PLAN;
          isAccplanJoined = true;
        }
    }
    
    if(this.fields.contains("CURCODE"))
      fields = fields + ", r.curcode";
    for (byte i = 1; i <= MAX_FINANLLEVEL; i++)
    {
    	if(this.fields.contains("ANL"+i+"_ID"))
    		fields = fields + ", r.ANL"+i+"_ID";
    }
    if(this.fields.contains("REMNBEGNAT"))
        fields = fields + ", r.REMNBEGNAT";
    if(this.fields.contains("REMNBEGCUR"))
        fields = fields + ", r.REMNBEGCUR";
    
    if(this.fields.contains("REMNBEGNATPLAN"))
        fields = fields + ", r.REMNBEGNATPLAN";
    if(this.fields.contains("REMNBEGCURPLAN"))
        fields = fields + ", r.REMNBEGCURPLAN";

    int sz;
    if (accountIds != null)
      sz = accountIds.size();
    else 
      sz = 0;
    String s = "";
    if (sz > 0)
    {
      Integer[] ids = accountIds.toArray(new Integer[accountIds.size()]);
      if (sz == 1)
        where = where + " and (r.acc_id = "+ids[0]+")";
      else
      {
        for (int i = 0; i < sz - 1; i++)
          s = s + ids[i] + ",";
        s = s + ids[sz - 1];
        where = where + " and (r.acc_id in ("+s+"))";
      }
    }
   
    if (accountFolderIds != null)
      sz = accountFolderIds.size();
    else 
      sz = 0;
    s = "";
    if (sz > 0)
    {
      if (!isAccplanJoined)
      {
        from = from + JOIN_ACC_PLAN;
        isAccplanJoined = true;
      }	
      if (sz == 1)
        where = where + " and (a.folder_id = "+accountFolderIds.get(0)+")";
      else
      {
        for (int i = 0; i < sz - 1; i++)
          s = s + accountFolderIds.get(i) + ",";
        s = s + accountFolderIds.get(sz - 1);
        where = where + " and (a.folder_id ("+s+"))";
      }
    }

    if (!anlIdList.isEmpty())
    {
      for(Integer key = 1; key <= MAX_FINANLLEVEL; key++)
        if (anlIdList.containsKey(key))
        {
    	  Set<Integer> anlIds = anlIdList.get(key);
    	  if (anlIds != null)
    	    sz = anlIds.size();
    	  else 
    	    sz = 0;
    	  if (sz > 0)
    	  {
    		s = "";
    		for (Iterator it = anlIds.iterator(); it.hasNext();)
    		{
    		  Integer id = (Integer)it.next();
    		  s = s + id;
    		  if (it.hasNext())
    		    s = s + ",";	
    		 }
    		 if (sz == 1)
    		   where = where + "and (r.anl"+key+"_id="+s+")";
    		 else
    		 {
    		   where = where+" and (r.anl"+key+"_id in ("+s+"))";
    		 }
    	  }
        }
    }
    if (currencyCode.length() > 0)
      where = where + " and (r.curcode = '"+currencyCode+"')";
  
    String[] ret = {fields, from, where, order};
    return ret;
  }
  
  @Override
  protected Date getPeriodBeginDate(int periodId)
  {
	  String strQuery = "select p.datefrom from finperiod p where p.id = "+periodId;
	  List<PeriodItem> prdlst = null;
	  Date dt = null;
	  try
	  {
		prdlst  = JdbcTemplate.getInstance().query(strQuery, null, new PeriodItemRM());
	  } catch (DataAccessException e){e.printStackTrace();}
		if (prdlst != null && !prdlst.isEmpty())
		  dt = ((PeriodItem)prdlst.get(0)).getBeginDate();
	return dt;	
		
  }
	
  public FinAccTotals() 
  {
 	super();
  }

  public void init()
  {
    ftKind = ftkAcc;
    table = "finturnacc";
  }

}
