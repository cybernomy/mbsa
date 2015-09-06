/*
 * AccountCache.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 *
 */
package com.mg.merp.finance.totals.helperclasses.caches;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.DataAccessException;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.finance.totals.helperclasses.jdbctemplates.AccountItem;
import com.mg.merp.finance.totals.helperclasses.jdbctemplates.AccountItemRM;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.naming.NamingException;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: AccountCache.java,v 1.4 2007/09/17 12:10:06 alikaev Exp $
 */
public class AccountCache {

  private static AccountCache instance = null;

  AccountCache() {
  }

  public synchronized static AccountCache getInstance() {
    return instance == null ? new AccountCache() : instance;
  }

  public static PersistentObject loadFinAccountById(int id) throws ApplicationException, NamingException {
    PersistentObject po = loadFromCash(id);
    return po == null ? loadBean(id) : po;
  }

  public static List<AccountItem> getFinAccountsByCode(Set codes) throws DataAccessException {
    List<AccountItem> lst = getFromCash(codes);
    return (lst == null || lst.isEmpty()) ? getFromDB(codes) : lst;
  }

  private static List<AccountItem> getFromCash(Set codes) {
    //TODO: Поискать для начала в кеше
    return null;
  }

  private static PersistentObject loadFromCash(int id) {
    //TODO: Поискать для начала в кеше
    return null;
  }

  private static PersistentObject loadBean(int id) throws ApplicationException, NamingException {
    DataBusinessObjectService service = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/finance/Account");
    PersistentObject po = service.initialize(new LocalDataTransferObject());
    po = service.load(id);
    return po;
  }

  private static List<AccountItem> getFromDB(Set codes) throws DataAccessException {
    String tmpStr = StringUtils.EMPTY_STRING;
    for (Iterator itc = codes.iterator(); itc.hasNext(); ) {
      String s = (String) itc.next();
      if (s.indexOf("*") > -1) {
        s = ((StringUtils.replaceString(s, "*", "%")).toUpperCase()).trim();
        tmpStr = tmpStr + " a.upcode like '" + s + "' or ";
      } else {
        tmpStr = tmpStr + " a.upcode = '" + s + "' or ";
      }
    }
    Integer len = tmpStr.length();
    if (len > 0)
      tmpStr = tmpStr.substring(1, len - 4);

    String strQuery = "select a.id, a.upcode from finaccount a where " + tmpStr + " ;";

    List<AccountItem> list = JdbcTemplate.getInstance().query(strQuery, null, new AccountItemRM());
    return list;
  }

}
