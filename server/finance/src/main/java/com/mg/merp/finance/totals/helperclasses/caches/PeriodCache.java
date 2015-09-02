/*
 * PeriodCache.java
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
package com.mg.merp.finance.totals.helperclasses.caches;

import java.util.*;
import java.sql.Timestamp;

import com.mg.framework.api.jdbc.DataAccessException;
import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.merp.finance.totals.helperclasses.*;
import com.mg.merp.finance.totals.helperclasses.jdbctemplates.PeriodItem;
import com.mg.merp.finance.totals.helperclasses.jdbctemplates.PeriodItemRM;
import static com.mg.merp.finance.totals.FinanceTotals.DatePositionInPeriod.*;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: PeriodCache.java,v 1.5 2006/12/15 11:54:40 leonova Exp $
 */
public class PeriodCache 
{
  private static PeriodCache instance = null;
	
  PeriodCache(){}
  
  public synchronized static PeriodCache getInstance()
  {
	  if (instance == null)
		instance = new PeriodCache();
	  return instance;
  }
  
  private static PeriodItem getFromDB(Date date) throws DataAccessException
  {
  
	  String strQuery = "select id, datefrom, dateto from finperiod where ? between datefrom and dateto ;";
      List<PeriodItem> prdlst  = JdbcTemplate.getInstance().query(strQuery, new Object[]{new Timestamp(date.getTime())}, new PeriodItemRM());
      PeriodItem prd = null;
      if (prdlst != null && !prdlst.isEmpty())
        prd = (PeriodItem)prdlst.get(0);
	  return prd;	  
  }
  
  private static PeriodItem getFromDB(int id) throws DataAccessException
  {
  
	  String strQuery = "select id, datefrom, dateto from finperiod where id = " + id;
      List<PeriodItem> prdlst  = JdbcTemplate.getInstance().query(strQuery, null, new PeriodItemRM());
      PeriodItem prd = null;
      if (prdlst != null && !prdlst.isEmpty())
        prd = (PeriodItem)prdlst.get(0);
	  return prd;	  
  }  
  
  private static PeriodItem getFromCash(Date date)
  {
	  //TODO: Поискать для начала в кеше
	  return null;
  }
  
  private static PeriodItem getFromCash(int id)
  {
	  //TODO: Поискать для начала в кеше
	  return null;
  }  
  
  public static PeriodItem findById(Integer id) throws DataAccessException
  {
	  PeriodItem prd = getFromCash(id); 
	  if ( prd == null )
	    return getFromDB(id);
	  else
		return prd; 
  }
  
  public static PeriodItem findByDate(Date date)  throws DataAccessException
  {
	  PeriodItem prd = getFromCash(date); 
	  if ( prd == null )
	    return getFromDB(date);
	  else
		return prd; 
  }
  
  public static boolean isBeginOfPeriod(Integer periodId, Date date) throws DataAccessException
  {
	return findById(periodId).getBeginDate().equals(date);  
  }
  
  public static boolean isEndOfPeriod(Integer periodId, Date date) throws DataAccessException
  {
	return findById(periodId).getEndDate().equals(date);
  }
  
  public static Period getPeriod(Date date) throws DataAccessException
  {
	  PeriodItem item = findByDate(date);
	  Period period = new Period(); 
  	  if (date.equals(item.getBeginDate()))
        period.setDatePosition(dppBegin);
      else if (date.equals(item.getEndDate()))
    	period.setDatePosition(dppEnd);
      else if (date.after(item.getBeginDate()) && date.before(item.getEndDate()))
    	period.setDatePosition(dppInside);
      else
    	period.setDatePosition(dppOutside);
      
  	  period.setId(item.getId());
	  return period;
  }
  
  
  public static List<PeriodItem> getPeriodsBetween(Date date1, Date date2) throws DataAccessException
  {
	  List<PeriodItem> prdlst = getFromCash(date1, date2); 
	  if ( prdlst == null )
	    return getFromDB(date1, date2);
	  else
		return prdlst; 
  }
  
  private static List<PeriodItem> getFromCash(Date date1, Date date2)
  {
	  //TODO: Поискать для начала в кеше
	  return null;
  }  
  
  public static List<PeriodItem> getFromDB(Date date1, Date date2) throws DataAccessException
  {
	  String strQuery = "select id, datefrom, dateto from finperiod where (datefrom > ?)  and (dateto < ?)";
      List<PeriodItem> prdlst  = JdbcTemplate.getInstance().query(strQuery, new Object[]{new Timestamp(date1.getTime()), new Timestamp(date2.getTime())}, new PeriodItemRM());
	  return prdlst;
  }
  
  
}
