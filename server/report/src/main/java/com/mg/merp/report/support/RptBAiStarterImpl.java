/* RptBAiStarterImpl.java
*
* Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.report.support;

import com.mg.framework.api.Session;
import com.mg.framework.api.dataset.DataSet;
import com.mg.framework.service.SessionControlImpl;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.baiengine.BusinessAddinEngineLocator;
import com.mg.merp.baiengine.BusinessAddinEvent;
import com.mg.merp.baiengine.BusinessAddinListener;
import com.mg.merp.report.ReportException;
import com.mg.merp.report.RptBAiStarter;
import com.mg.merp.report.generic.ReportBusinessAddin;
import com.mg.merp.wb.report.birt.data.oda.badi.util.Constants;

import java.util.Map;

import javax.transaction.TransactionManager;

/**
 * Реализация интерфейса {@link RptBAiStarter}
 *
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: RptBAiStarterImpl.java,v 1.5 2008/08/12 09:31:23 safonov Exp $
 */
public class RptBAiStarterImpl implements RptBAiStarter {
  private DataSet dataSet;

  /* (non-Javadoc)
   * @see com.mg.merp.report.service.RptBAiStarter#perform()
   */
  public void perform(String code, Map<String, Object> params) {
    TransactionManager tm = ServerUtils.getTransactionManager();
    try {
      //если не установлена текущая сессия, то пытаемся установить сессию из контекста
      boolean isSessionOwner = ServerUtils.getCurrentSession() == null;
      if (isSessionOwner)
        SessionControlImpl.getSingleton().setCurrentSession((Session) params.get(Constants.CURRENT_SESSION_PARAM));
      //стартуем транзакцию если нет текущей
      boolean startTran = tm.getTransaction() == null;
      if (startTran)
        tm.begin();
      try {
        ReportBAiListener rbl = new ReportBAiListener();
        BusinessAddinEngineLocator.locate().perform(code, params, rbl);
      } finally {
        //сбросим сессию если устанавливали ее из контекста
        if (isSessionOwner)
          SessionControlImpl.getSingleton().setCurrentSession(null);
        //завершаем транзакцию если стартовали
        if (startTran)
          tm.commit();
      }
    } catch (RuntimeException e) {
      throw e;
    } catch (Exception e) {
      throw new ReportException(e);
    }
  }

  public DataSet getDataSet() {
    return dataSet;
  }

  /**
   * Класс-слушатель для алгоритмов {@link ReportBusinessAddin}
   */
  class ReportBAiListener implements BusinessAddinListener<DataSet> {

    public void aborted(BusinessAddinEvent<DataSet> event) {
      dataSet = null;

    }

    public void completed(BusinessAddinEvent<DataSet> event) {
      dataSet = event.getResult();
      dataSet.beforeFirstRow();
    }
  }

}
