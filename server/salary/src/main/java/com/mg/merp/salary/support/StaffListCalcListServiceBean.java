/*
 * StaffListCalcListServiceBean.java
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
package com.mg.merp.salary.support;

import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.salary.StaffListCalcListServiceLocal;
import com.mg.merp.salary.model.CalcList;

/**
 * Бизнес-компонент "Лицевые счета сотрудников" 
 * 
 * @author leonova
 * @version $Id: StaffListCalcListServiceBean.java,v 1.4 2006/09/08 07:10:10 leonova Exp $
 */
@Stateless(name="merp/salary/StaffListCalcListService")
public class StaffListCalcListServiceBean extends
        AbstractPOJODataBusinessObjectServiceBean<CalcList, Integer> implements StaffListCalcListServiceLocal {
    


}
