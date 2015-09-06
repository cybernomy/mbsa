/*
 * FinanceTurnoverUpdater.java
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
package com.mg.merp.finance.support;

import com.mg.framework.api.jdbc.CallableStatementCreator;
import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.jdbc.RowMapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс обертка изменения финансовых оборотных ведомостей
 *
 * @author Oleg V. Safonov
 * @version $Id: FinanceTurnoverUpdater.java,v 1.1 2007/01/16 14:43:43 safonov Exp $
 */
public class FinanceTurnoverUpdater {

  private static OperInfo loadOperInfo(int operId) {
    OperInfo result = new OperInfo();
    JdbcTemplate templ = JdbcTemplate.getInstance();
    List<Integer> period = templ.query("select p.id from finperiod p, finoper o where (o.id = ?) and (o.keepdate between p.datefrom and p.dateto)", new Object[]{operId}, new RowMapper<Integer>() {
      public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getInt(1);
      }
    });
    if (period.isEmpty())
      throw new IllegalArgumentException();
    List<String> currency = templ.query("select curcode from finoper where id = ?", new Object[]{operId}, new RowMapper<String>() {
      public String mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getString(1);
      }
    });
    if (currency.isEmpty())
      throw new IllegalArgumentException();
    result.periodId = period.get(0);
    result.currencyCode = currency.get(0);
    return result;
  }

  /**
   * изменить оборотную ведомость
   *
   * @param operId идентификатор финансовой операции
   */
  public static void execute(final int operId) {
    final OperInfo oper = loadOperInfo(operId);
    JdbcTemplate.getInstance().call(new CallableStatementCreator() {
      public CallableStatement createCallableStatement(Connection con) throws SQLException {
        CallableStatement result = con.prepareCall("{call FIN_UPDATE_TURN(?, ?, ?, ?)}");
        result.setInt(1, operId);
        result.setInt(2, oper.periodId);
        result.setObject(3, null);
        result.setString(4, oper.currencyCode);
        return result;
      }
    }
        , new ArrayList<Object>());
  }

  private static class OperInfo {
    Integer periodId;
    String currencyCode;
  }

}
