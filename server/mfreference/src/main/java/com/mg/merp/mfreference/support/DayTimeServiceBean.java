/*
 * DaytimeServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.mfreference.support;

import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.MiscUtils;
import com.mg.merp.mfreference.DayCalendarNotFoundException;
import com.mg.merp.mfreference.DayTimeNotFoundException;
import com.mg.merp.mfreference.DayTimeServiceLocal;
import com.mg.merp.mfreference.DayTimes;
import com.mg.merp.mfreference.TimeRange;
import com.mg.merp.mfreference.model.DayCalendar;
import com.mg.merp.mfreference.model.DayTime;
import com.mg.merp.mfreference.model.ScheduleDirection;
import com.mg.merp.mfreference.model.WeekCalendar;
import com.mg.merp.reference.HolidaysServiceLocal;

import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Сменный календарь"
 *
 * @author leonova
 * @version $Id: DayTimeServiceBean.java,v 1.4 2007/07/30 10:24:41 safonov Exp $
 */
@Stateless(name = "merp/mfreference/DayTimeService")
public class DayTimeServiceBean extends AbstractPOJODataBusinessObjectServiceBean<DayTime, Integer> implements DayTimeServiceLocal {

  private DayTimes internalDayTimes(WeekCalendar weekCal, Date searchDate, ScheduleDirection schedDirection) {
    DayCalendar dayCal = MfUtils.getDayCalendar(weekCal.getId(), searchDate);
    if (dayCal == null)
      throw new DayCalendarNotFoundException(weekCal, searchDate);
    List<DayTime> list = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(DayTime.class)
        .add(Restrictions.eq("DayCal", dayCal))
        .addOrder(Order.asc("StartTick")));
    if (list.isEmpty())
      throw new DayTimeNotFoundException(dayCal);

    DayTime firstDay = list.get(0);
    DayTime endDay = list.get(list.size() - 1);
    switch (schedDirection) {
      case BACKWARD:
        return new DayTimes(firstDay.getStartTick(), endDay.getStartTick() + endDay.getTicks()
            , endDay.getStartTick(), endDay.getStartTick() + endDay.getTicks());
      case FORWARD:
        return new DayTimes(firstDay.getStartTick(), endDay.getStartTick() + endDay.getTicks()
            , firstDay.getStartTick(), firstDay.getStartTick() + firstDay.getTicks());
      default:
        throw new IllegalArgumentException("Invalid direction");
    }
  }

  private TimeRange internalGetTimes(WeekCalendar weekCal, long baseDateTime, long runTime, ScheduleDirection schedDirection) {
    long finishDayTime, startDayTime, finishTime, startTime, lenLastBlock, remainingTime, timeOver, lastProcessedInterval;
    finishDayTime = baseDateTime;
    startDayTime = baseDateTime;
    switch (schedDirection) {
      case BACKWARD:
        Date curDate = DateTimeUtils.getDayStart(MfUtils.tickToDate(baseDateTime));
        finishTime = baseDateTime - MfUtils.dateToTick(curDate); //фактически это кол-во тиков от начала дня, в который попадает baseDateTime

        //Ищем точку финиша
        for (; ; ) {
          DayTimes dayTimes = internalDayTimes(weekCal, curDate, schedDirection);
          if (isDayHoliday(curDate)) {
            DateTimeUtils.incDay(curDate, -1); //отступаем на 1 день назад
            finishTime = MfUtils.TICKS_PER_DAY;
            finishDayTime -= MfUtils.TICKS_PER_DAY;
          } else {
            //день НЕ является праздничным
            if (finishTime < dayTimes.getEndTicks()) {
              if (finishTime < dayTimes.getStartDayTicks()) {
                finishDayTime -= finishTime;
                DateTimeUtils.incDay(curDate, -1); //отступаем на 1 день назад
                finishTime = MfUtils.TICKS_PER_DAY;
              } else
                break;
            } else {
              finishDayTime = finishDayTime - finishTime + dayTimes.getEndTicks();
              if (dayTimes.isDayEmpty()) {
                //это означает, что день пустой (нерабочий)
                DateTimeUtils.incDay(curDate, -1); //отступаем на 1 день назад
                finishTime = MfUtils.TICKS_PER_DAY;
              } else {
                finishTime = dayTimes.getEndTicks();
                break;
              }
            }
          }
        }

        //Ищем временной интервал (внутри дня финиша), которому принадлежит FinishTime
        OrmTemplate ormTemplate = OrmTemplate.getInstance();
        DayCalendar dayCal = MfUtils.getDayCalendar(weekCal.getId(), curDate);
        List<DayTime> list = MiscUtils.convertUncheckedList(DayTime.class, ormTemplate.findByNamedQueryAndNamedParam("Manufacture.DayTime.loadRange", new String[]{"dayCal", "finishTime"}, new Object[]{dayCal, finishTime}));
        if (list.isEmpty()) {
          list = MiscUtils.convertUncheckedList(DayTime.class, ormTemplate.findByNamedQueryAndNamedParam("Manufacture.DayTime.loadNext", new String[]{"dayCal", "finishTime"}, new Object[]{dayCal, finishTime}));
          if (list.isEmpty())
            throw new DayTimeNotFoundException(dayCal);
          //Взять ПОСЛЕДНЮЮ запись, то есть с датой окончания, ближайшей к FinishTime (слева от нее);
          DayTime dayTime = list.get(list.size() - 1);
          finishDayTime = finishDayTime - finishTime + dayTime.getStartTick() + dayTime.getTicks();
          startTime = dayTime.getStartTick();
          lastProcessedInterval = startTime;
          lenLastBlock = dayTime.getTicks();
        } else {
          DayTime dayTime = list.get(0);
          startTime = dayTime.getStartTick();
          lastProcessedInterval = startTime;
          lenLastBlock = dayTime.getTicks() - ((dayTime.getStartTick() + dayTime.getTicks()) - finishTime);
        }

        //Вычисляем точку старта, путем отступления назад от точки FinishDayTime на величину runTime
        remainingTime = runTime;
        timeOver = lenLastBlock - remainingTime;
        if (timeOver < 0) {
          for (; ; ) {
            list = MiscUtils.convertUncheckedList(DayTime.class, ormTemplate.findByNamedQueryAndNamedParam("Manufacture.DayTime.loadBegin", new String[]{"dayCal", "finishTime"}, new Object[]{dayCal, lastProcessedInterval}));
            if (!list.isEmpty()) {
              for (int i = list.size() - 1; i >= 0; i--) {
                DayTime dayTime = list.get(i);
                startTime = dayTime.getStartTick();
                timeOver = dayTime.getTicks() - remainingTime;
                lastProcessedInterval = startTime;
                if (timeOver >= 0)
                  break;
                else
                  remainingTime = timeOver * -1;
              }
            }
            if (timeOver >= 0)
              break;
            else {
              DateTimeUtils.incDay(curDate, -1); //отступаем на 1 день назад
              dayCal = MfUtils.getDayCalendar(weekCal.getId(), curDate);
              lastProcessedInterval = MfUtils.TICKS_PER_DAY + 1; //чтобы загрузились все интервалы, + 1 для надежности:)
            }
          }
        }

        startDayTime = MfUtils.dateToTick(curDate) + startTime + timeOver;
        break;
      case FORWARD:
        //TODO все абсолютно аналогично, только двигаться надо вперед во времени
        throw new IllegalArgumentException("Not implemented");
    }
    return new TimeRange(startDayTime, finishDayTime);
  }

  private boolean isDayHoliday(Date date) {
    return ((HolidaysServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(HolidaysServiceLocal.SERVICE_NAME)).isDayHoliday(date);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.mfreference.DayTimeServiceLocal#getTimes(int, long, long, com.mg.merp.mfreference.model.ScheduleDirection)
   */
  @PermitAll
  public TimeRange getTimes(int weekCalId, long baseDateTime, long runTime, ScheduleDirection schedDirection) {
    return internalGetTimes(getPersistentManager().find(WeekCalendar.class, weekCalId), baseDateTime, runTime, schedDirection);
  }

}
