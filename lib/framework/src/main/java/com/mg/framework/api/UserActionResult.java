/*
 * BeginActionResult.java
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
package com.mg.framework.api;

import java.io.Serializable;

/**
 * @author Oleg V. Safonov
 * @version $Id: UserActionResult.java,v 1.1 2005/04/28 15:33:24 safonov Exp $
 */
public class UserActionResult implements Serializable {
    public String data;
    public Object featureLinks;
    
    public UserActionResult(String data, Object featureLinks) {
        this.data = data;
        this.featureLinks = featureLinks;
    }
}
