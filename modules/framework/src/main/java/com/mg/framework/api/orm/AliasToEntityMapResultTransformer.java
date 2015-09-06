/*
 * AliasToEntityMapResultTransformer.java
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
package com.mg.framework.api.orm;

import java.util.HashMap;
import java.util.Map;

/**
 * Реализация стандартного трансформера, трансформирует кортеж в карту <имя поля, значение>
 *
 * @author Oleg V. Safonov
 * @version $Id: AliasToEntityMapResultTransformer.java,v 1.1 2007/07/27 09:32:16 safonov Exp $
 */
public class AliasToEntityMapResultTransformer implements ResultTransformer<Map<String, Object>> {

  /* (non-Javadoc)
   * @see com.mg.framework.api.orm.ResultTransformer#transformTuple(java.lang.Object[], java.lang.String[])
   */
  public Map<String, Object> transformTuple(Object[] tuple, String[] aliases) {
    Map<String, Object> result = new HashMap<String, Object>(tuple.length);
    for (int i = 0; i < tuple.length; i++) {
      String alias = aliases[i];
      if (alias != null) {
        result.put(alias, tuple[i]);
      }
    }
    return result;
  }

}
