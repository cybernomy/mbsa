/*
 * InputDocumentSumDialog.java
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
package com.mg.merp.docflow.support.ui;

import java.math.BigDecimal;

import com.mg.framework.generic.ui.DefaultWizardDialog;

/**
 * @author Oleg V. Safonov
 * @version $Id: InputDocumentSumDialog.java,v 1.2 2006/09/11 09:29:40 safonov Exp $
 */
public class InputDocumentSumDialog extends DefaultWizardDialog {
	private BigDecimal docSum;
	
	public BigDecimal getDocSum() {
		return docSum;
	}
	
	public void setDocSum(BigDecimal docSum) {
		this.docSum = docSum;
	}
}
