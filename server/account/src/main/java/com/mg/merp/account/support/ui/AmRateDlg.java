/*
 * AmRateDlg.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.account.support.ui;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultWizardDialog;
import com.mg.framework.utils.DataUtils;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.merp.account.model.Period;
import com.mg.merp.account.support.Messages;
import com.mg.merp.reference.model.MonthOfYear;

import java.util.Date;
import java.util.List;

/**
 * Контроллер диалога "Ведите дату начисления амортизации"
 *
 * @author Konstantin S. Alikaev
 * @version $Id: AmRateDlg.java,v 1.2 2008/05/20 11:49:20 alikaev Exp $
 */
public class AmRateDlg extends DefaultWizardDialog {

  private MonthOfYear month;

  @DataItemName("Account.InvHead.QYear")
  private Integer year = 0;

  public AmRateDlg() {
    Date nowDate = DateTimeUtils.nowDate();
    this.year = DateTimeUtils.getYear(nowDate);
    this.month = getMonth(DateTimeUtils.getMonth(nowDate));
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultDialog#onActionOk(com.mg.framework.api.ui.WidgetEvent)
   */
  @Override
  public void onActionOk(WidgetEvent event) {
    Integer numberMonth = getNumberMonth();
    Date keepDate = DateTimeUtils.toDate(numberMonth, DaysPerMonth(numberMonth, year), year, 0, 0, 0);
    List<Period> periods = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(Period.class)
        .add(Restrictions.le("DateFrom", keepDate))
        .add(Restrictions.ge("DateTo", keepDate)));
    if (periods.isEmpty())
      throw new BusinessException(Messages.getInstance().getMessage(Messages.NOT_FOUND_PERIOD_BY_DATE));
    else
      super.onActionOk(event);
  }

  /**
   * По месяцу и году возвращет количество дней в месяце
   *
   * @param month - номер месяца
   * @param year  - год
   */
  private int DaysPerMonth(int month, int year) {
    int[] daysIsMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    return (month < 1 || month > 12) ? 0 : (month == 2 && year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) ? daysIsMonth[month - 1] + 1 : daysIsMonth[month - 1];
  }

  /**
   * Номер месяца
   *
   * @return month
   */
  public MonthOfYear getMonth() {
    return month;
  }

  /**
   * Год
   *
   * @return year
   */
  public Integer getYear() {
    return year;
  }

  /**
   * Возвращает номер месяца
   */
  public int getNumberMonth() {
    return DataUtils.convertEnumToOrdinal(month) + 1;
  }

  /**
   * Номеру месяца возвращает месяц
   *
   * @param number - номер месяца
   */
  public MonthOfYear getMonth(int number) {
    switch (number) {
      case 1:
        return MonthOfYear.JANUARY;
      case 2:
        return MonthOfYear.FEBRUARY;
      case 3:
        return MonthOfYear.MARCH;
      case 4:
        return MonthOfYear.APRIL;
      case 5:
        return MonthOfYear.MAY;
      case 6:
        return MonthOfYear.JUNE;
      case 7:
        return MonthOfYear.JULY;
      case 8:
        return MonthOfYear.AUGUST;
      case 9:
        return MonthOfYear.SEPTEMBER;
      case 10:
        return MonthOfYear.OCTORER;
      case 11:
        return MonthOfYear.NOVEMBER;
      case 12:
        return MonthOfYear.DECEMBER;
      default:
        return MonthOfYear.JANUARY;
    }
  }

}
