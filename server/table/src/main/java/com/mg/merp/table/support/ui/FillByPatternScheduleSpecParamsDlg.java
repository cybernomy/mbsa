/*
 * FillByPatternScheduleSpecParamsDlg.java
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
package com.mg.merp.table.support.ui;

import java.util.Date;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultDialog;
import com.mg.merp.table.model.PatternHead;

/**
 * Контроллер диалога "Параметры заполнения спецификации графика в табельном учете"
 * 
 * @author Artem V. Sharapov
 * @version $Id: FillByPatternScheduleSpecParamsDlg.java,v 1.1 2008/08/12 14:38:08 sharapov Exp $
 */
public class FillByPatternScheduleSpecParamsDlg extends DefaultDialog {
	
	public final static String WINDOW_NAME = "com.mg.merp.table.FillByPatternScheduleSpecParamsDlg"; //$NON-NLS-1$
	
	@DataItemName("Table.Schedule.DefaultPatternHead") //$NON-NLS-1$
	private PatternHead pattern;
	private Integer offset;
	private Date dateFrom;
	private Date dateTill;
	
	public FillByPatternScheduleSpecParamsDlg() {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultDialog#onActionOk(com.mg.framework.api.ui.WidgetEvent)
	 */
	@Override
	public void onActionOk(WidgetEvent event) {
		if(isParamsValid())
			super.onActionOk(event);
	}
	
	protected boolean isParamsValid() {
		return !(pattern == null || offset == null || dateFrom == null || dateTill == null);
	}

	/**
	 * @return the pattern
	 */
	public PatternHead getPattern() {
		return this.pattern;
	}

	/**
	 * @param pattern the pattern to set
	 */
	public void setPattern(PatternHead pattern) {
		this.pattern = pattern;
	}

	/**
	 * @return the offset
	 */
	public Integer getOffset() {
		return this.offset;
	}

	/**
	 * @param offset the offset to set
	 */
	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	/**
	 * @return the dateFrom
	 */
	public Date getDateFrom() {
		return this.dateFrom;
	}

	/**
	 * @param dateFrom the dateFrom to set
	 */
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	/**
	 * @return the dateTill
	 */
	public Date getDateTill() {
		return this.dateTill;
	}

	/**
	 * @param dateTill the dateTill to set
	 */
	public void setDateTill(Date dateTill) {
		this.dateTill = dateTill;
	}

}
