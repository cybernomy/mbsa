/*
 * DocTypeSearchHelp.java
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
package com.mg.merp.document.support.ui;

import com.mg.merp.document.model.DocumentKind;

/**
 * SearchHelp ��� ���� ����� ���������� �� DocSection ���������
 * 
 * @author leonova
 * @version $Id: DocTypeSearchHelp.java,v 1.4 2006/10/16 06:28:00 leonova Exp $
 */
public class DocTypeSearchHelp extends AbstractDocTypeSearchHelp {

	@Override
	protected boolean isDocument() {
		return false;
	}

	@Override
	protected DocumentKind getDocumentKind() {
		return null;
	}

	@Override
	protected boolean isDocModel() {
		return false;
	}

}
