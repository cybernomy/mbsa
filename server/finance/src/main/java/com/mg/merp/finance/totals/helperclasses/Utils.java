/*
 * Utils.java
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
package com.mg.merp.finance.totals.helperclasses;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.finance.totals.helperclasses.caches.AccountCache;

import javax.naming.NamingException;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: Utils.java,v 1.5 2007/09/17 12:12:01 alikaev Exp $
 */
public class Utils {

  public static String getAnlCode(int accId, int anlId, byte anlLevel) throws ApplicationException, NamingException {
    //TODO: Косяк если имя сервиса содержит и прописные и строчные буквы
    if ((accId > 0) && (anlId > 0)) {
      String beanName = StringUtils.EMPTY_STRING;
      PersistentObject it = AccountCache.loadFinAccountById(accId);
      if (!(Boolean) it.getAttribute("Anl" + anlLevel + "Kind")) //$NON-NLS-1$ //$NON-NLS-2$
        beanName = "merp/finance/Analytics"; //$NON-NLS-1$
      else {
        String tmp = ((String) it.getAttribute("Anl" + anlLevel + "BeanName")).toLowerCase(); //$NON-NLS-1$ //$NON-NLS-2$
        int pos = tmp.indexOf("/"); //$NON-NLS-1$
        String firstLetter = tmp.substring(pos + 1, pos + 2);
        String bigFirstLetter = firstLetter.toUpperCase();
        tmp = tmp.replace("/" + firstLetter, "/" + bigFirstLetter); //$NON-NLS-1$ //$NON-NLS-2$
        beanName = "merp/" + tmp + "Service"; //$NON-NLS-1$ //$NON-NLS-2$
      }
      DataBusinessObjectService service = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService(beanName);
      PersistentObject po = service.initialize(new LocalDataTransferObject());
      po = service.load(anlId);

      String code = getSomeCode(po);
      return code;
    } else
      return StringUtils.EMPTY_STRING;
  }

  private static String getSomeCode(PersistentObject po) {
    if ((String) po.getAttribute("Code") != null) //$NON-NLS-1$
      return (String) po.getAttribute("Code"); //$NON-NLS-1$
    else if ((String) po.getAttribute("UpCode") != null) //$NON-NLS-1$
      return (String) po.getAttribute("UpCode"); //$NON-NLS-1$
    else if ((String) po.getAttribute("AnlName") != null) //$NON-NLS-1$
      return (String) po.getAttribute("AnlName"); //$NON-NLS-1$
      //TODO: если нету ни кода, ни имени
    else return "***"; //$NON-NLS-1$
  }

}
