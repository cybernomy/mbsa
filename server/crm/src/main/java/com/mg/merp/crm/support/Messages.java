/*
 * Messages.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.merp.crm.support;

import com.mg.framework.generic.MessageSourceAccessor;

/**
 * @author Oleg V. Safonov
 * @author Konstantin S. Alikaev
 * @version $Id: Messages.java,v 1.3 2008/03/04 08:53:00 alikaev Exp $
 */
public class Messages extends MessageSourceAccessor {
  //message keys
  public static final String CHECK_WEIGHT = "CheckWeight"; //$NON-NLS-1$
  public static final String INVALID_SYMPTOM = "InvalidSymptom"; //$NON-NLS-1$
  public static final String ID = "Id"; //$NON-NLS-1$
  public static final String ELECTRONIC_ADRESS_KIND = "ElectronicAdressKind"; //$NON-NLS-1$
  public static final String ELECTRONIC_ADRESS_PROTOCOL = "ElectronicAdressProtocol"; //$NON-NLS-1$
  public static final String ELECTRONIC_ADRESS_NAME = "ElectronicAdressName"; //$NON-NLS-1$
  public static final String ELECTRONIC_ADRESS_IS_ACTIVE = "ElectronicAdressIsActive"; //$NON-NLS-1$
  public static final String PHONE_AREA_CODE = "PhoneAreaCode"; //$NON-NLS-1$
  public static final String PHONE_NAME = "PhoneName"; //$NON-NLS-1$
  public static final String PHONE_KIND = "PhoneKind"; //$NON-NLS-1$
  public static final String NATURAL_PERSON_NOT_CHOOSE = "NaturalPersonNotChoose"; //$NON-NLS-1$
  private static final String BUNDLE_NAME = "com.mg.merp.crm.resources.messages"; //$NON-NLS-1$
  private static Messages instance;

  static {
    MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  public static Messages getInstance() {
    return instance;
  }
}
