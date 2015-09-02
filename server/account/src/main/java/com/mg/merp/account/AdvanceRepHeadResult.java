/*
 * AdvanceRepHeadServiceBean.java
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

package com.mg.merp.account;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Модель результата при расчете суммы предыдущего аванса
 * 
 * @author pashistova
 * @author Konstantin S. Alikaev
 * @version $Id: AdvanceRepHeadResult.java,v 1.2 2008/03/12 14:35:37 alikaev Exp $
 */
public class AdvanceRepHeadResult implements Serializable {
	
	public BigDecimal value;
	public Date receiveDate;
			
	public AdvanceRepHeadResult(BigDecimal value, Date receiveDate) {
		this.value = value;
		this.receiveDate = receiveDate;
	}
	
}
