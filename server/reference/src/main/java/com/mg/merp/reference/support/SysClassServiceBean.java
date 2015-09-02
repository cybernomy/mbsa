/*
 * SysClassServiceBean.java
 *
 * Copyright (C) 1998 - 2004 Millennium Group. All rights reserved.
 *
 * Millennium ERP system.
 *
 */

package com.mg.merp.reference.support;

import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.core.model.SysClass;
import com.mg.merp.reference.SysClassServiceLocal;

/**
 * Бизнес-компонент "Бизнес-компоненты" 
 * 
 * @author leonova
 * @version $Id: SysClassServiceBean.java,v 1.3 2006/09/13 07:00:35 leonova Exp $
 */
@Stateless(name="merp/reference/SysClassService")
public class SysClassServiceBean extends AbstractPOJODataBusinessObjectServiceBean<SysClass, Integer> implements SysClassServiceLocal {



}
