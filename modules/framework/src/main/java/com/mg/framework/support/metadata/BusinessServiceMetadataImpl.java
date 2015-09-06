/*
 * BusinessServiceMetadataImpl.java
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
package com.mg.framework.support.metadata;

import com.mg.framework.api.metadata.BusinessServiceMetadata;

import java.io.Serializable;

/**
 * Реализация метаданных бизнес компонента
 *
 * @author Oleg V. Safonov
 * @version $Id: BusinessServiceMetadataImpl.java,v 1.2 2006/07/27 15:40:14 safonov Exp $
 */
public class BusinessServiceMetadataImpl implements BusinessServiceMetadata, Serializable {
  private int identificator;
  private String name;

  public BusinessServiceMetadataImpl(int identificator) {
    this.identificator = identificator;
  }

  public BusinessServiceMetadataImpl(int identificator, String name) {
    this.identificator = identificator;
    this.name = name;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.BusinessServiceMetadata#getIdentificator()
   */
  public int getIdentificator() {
    return identificator;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.BusinessServiceMetadata#getName()
   */
  public String getName() {
    return name;
  }

}
