/* EntityTransformatorServiceMBean.java
*
* Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.framework.service.jboss;

import com.mg.framework.api.EntityTransformer;

import org.jboss.system.ServiceMBean;

/**
 * Сервис преобразования сущностей для контейнера JBoss
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: EntityTransformerServiceMBean.java,v 1.2 2007/09/21 09:52:05 safonov Exp $
 */
public interface EntityTransformerServiceMBean extends ServiceMBean, EntityTransformer {

}
