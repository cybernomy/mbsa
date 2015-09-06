/*
 * FinTurnAccItemRM.java
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
package com.mg.merp.finance.totals.helperclasses.jdbctemplates;

import com.mg.framework.api.jdbc.RowMapper;
import com.mg.merp.finance.totals.FinanceTotals;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: FinTurnAccItemRM.java,v 1.3 2006/07/05 09:06:30 poroxnenko Exp $
 */
public class FinTurnAccItemRM implements RowMapper<FinTurnAccItem> {

  public FinTurnAccItem mapRow(ResultSet rs, int rowNum) throws SQLException {
    return tryGetFinTurnAcc(rs);
  }

  private FinTurnAccItem tryGetFinTurnAcc(ResultSet rs) {
    FinTurnAccItem ftacc = new FinTurnAccItem();
    //common
    try {
      ftacc.ID = rs.getInt("ID");
    } catch (SQLException e) {
    }
    try {
      ftacc.PERIOD_ID = rs.getInt("PERIOD_ID");
    } catch (SQLException e) {
    }
    try {
      ftacc.PNAME = rs.getString("PNAME").trim();
    } catch (SQLException e) {
    }
    try {
      ftacc.CURCODE = rs.getString("CURCODE").trim();
    } catch (SQLException e) {
    }
    try {
      ftacc.DATEFROM = rs.getDate("DATEFROM");
    } catch (SQLException e) {
    }
    try {
      ftacc.DATETO = rs.getDate("DATETO");
    } catch (SQLException e) {
    }
    //accounts
    try {
      ftacc.ACC_ID = rs.getInt("ACC_ID");
    } catch (SQLException e) {
    }
    try {
      ftacc.ACCFOLDER_ID = rs.getInt("ACCFOLDER_ID");
    } catch (SQLException e) {
    }
    try {
      ftacc.ACC_CODE = rs.getString("ACC_CODE").trim();
    } catch (SQLException e) {
    }
    try {
      ftacc.ANL = rs.getString("ANL").trim();
    } catch (SQLException e) {
    }
    for (byte i = 0; i < FinanceTotals.MAX_FINANLLEVEL; i++) {
      try {
        ftacc.anlId[i] = rs.getInt("ANL" + (i + 1) + "_ID");
      } catch (SQLException e) {
      }
      try {
        ftacc.anlCode[i] = rs.getString("ANL" + (i + 1) + "_CODE").trim();
      } catch (SQLException e) {
      }
      try {
        ftacc.fAnlId[i] = rs.getInt("FANL" + (i + 1) + "_ID");
      } catch (SQLException e) {
      }
      try {
        ftacc.fAnlCode[i] = rs.getString("FANL" + (i + 1) + "_CODE").trim();
      } catch (SQLException e) {
      }

    }
    //features
    try {
      ftacc.FEAT_ID = rs.getInt("FEAT_ID");
    } catch (SQLException e) {
    }
    try {
      ftacc.FEATFOLDER_ID = rs.getInt("FEATFOLDER_ID");
    } catch (SQLException e) {
    }
    try {
      ftacc.FEAT_CODE = rs.getString("FEAT_CODE").trim();
    } catch (SQLException e) {
    }
    try {
      ftacc.FANL = rs.getString("FANL").trim();
    } catch (SQLException e) {
    }

    //total
    try {
      ftacc.REMNBEGNAT = rs.getBigDecimal("REMNBEGNAT");
    } catch (SQLException e) {
    }
    try {
      ftacc.REMNBEGCUR = rs.getBigDecimal("REMNBEGCUR");
    } catch (SQLException e) {
    }
    try {
      ftacc.INCOMENAT = rs.getBigDecimal("INCOMENAT");
    } catch (SQLException e) {
    }
    try {
      ftacc.INCOMECUR = rs.getBigDecimal("INCOMECUR");
    } catch (SQLException e) {
    }
    try {
      ftacc.OUTCOMENAT = rs.getBigDecimal("OUTCOMENAT");
    } catch (SQLException e) {
    }
    try {
      ftacc.OUTCOMECUR = rs.getBigDecimal("OUTCOMECUR");
    } catch (SQLException e) {
    }
    try {
      ftacc.REMNENDNAT = rs.getBigDecimal("REMNENDNAT");
    } catch (SQLException e) {
    }
    try {
      ftacc.REMNENDCUR = rs.getBigDecimal("REMNENDCUR");
    } catch (SQLException e) {
    }
    try {
      ftacc.REMNBEGNATPLAN = rs.getBigDecimal("REMNBEGNATPLAN");
    } catch (SQLException e) {
    }
    try {
      ftacc.REMNBEGCURPLAN = rs.getBigDecimal("REMNBEGCURPLAN");
    } catch (SQLException e) {
    }
    try {
      ftacc.INCOMENATPLAN = rs.getBigDecimal("INCOMENATPLAN");
    } catch (SQLException e) {
    }
    try {
      ftacc.INCOMECURPLAN = rs.getBigDecimal("INCOMECURPLAN");
    } catch (SQLException e) {
    }
    try {
      ftacc.OUTCOMENATPLAN = rs.getBigDecimal("OUTCOMENATPLAN");
    } catch (SQLException e) {
    }
    try {
      ftacc.OUTCOMECURPLAN = rs.getBigDecimal("OUTCOMECURPLAN");
    } catch (SQLException e) {
    }
    try {
      ftacc.REMNENDNATPLAN = rs.getBigDecimal("REMNENDNATPLAN");
    } catch (SQLException e) {
    }
    try {
      ftacc.REMNENDCURPLAN = rs.getBigDecimal("REMNENDCURPLAN");
    } catch (SQLException e) {
    }
    return ftacc;
  }

}
