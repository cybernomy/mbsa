/*
 * ApplicationLayer.java
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
package com.mg.framework.api.metadata;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Уровни приложения
 * 
 * @author Oleg V. Safonov
 * @version $Id: ApplicationLayer.java,v 1.2 2008/03/03 13:11:02 safonov Exp $
 */
@DataItemName ("Core.ApplicationLayer")
public enum ApplicationLayer {
	
	/**
	 * System самый низний из иерархии слоев, обслуживается исключительно
	 * разработчиками компании производителя Millennium Business Suite Anywhere
	 * 
	 */
	SYSTEM,
	
	/**
	 * Global solutions предоставляется сторонним разработчикам для разработки
	 * глобальных решений
	 * 
	 */
	GLOBAL,
	
	/**
	 * Distributor добавляется локальная функциональность страны представительства,
	 * изменяющая стандартную логику системы в соответствии с требованиями
	 * законодательства и спецификой ведения учета этой страны
	 * 
	 */
	DISTRIBUTOR,
	
	/**
	 * Local solutions предоставляется сторонним разработчикам для разработки
	 * локальных решений
	 * 
	 */
	LOCAL,
	
	/**
	 * Business предназначен для разработки "горизонтальных" (индустриальных, или отраслевых) и
	 * "вертикальных" (то есть не связанных со спецификой отраслей) приложений 
	 * 
	 */
	BUSINESS,
	
	/**
	 * Value added resellers используется для доработки функциональности в
	 * соответствии с требованиями конкретного клиента, смотри {@link #BUS}
	 * 
	 */
	VAR,
	
	/**
	 * Customer может использоваться клиентом для хранения собственных опробированных
	 * решений, в данном слое например может храниться решение характерное для
	 * всей организации
	 * 
	 */
	CUSTOMER,
	
	/**
	 * User модификации которые не прошли окончательного тестирования, находятся в
	 * разработке или просто являются временными решениями, в данном слое могут
	 * храниться решения специфичные для некоторых филиалов организации
	 * 
	 */
	USER
}
