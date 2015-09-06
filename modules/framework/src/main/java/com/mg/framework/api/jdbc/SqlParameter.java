/*
 * SqlParameter.java
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
package com.mg.framework.api.jdbc;

import java.util.LinkedList;
import java.util.List;

/**
 * Object to represent a SQL parameter definition. Parameters may be anonymous, in which case name
 * is null. However all parameters must define a SQL type constant from java.sql.Types.
 *
 * @author Oleg V. Safonov
 * @author Rod Johnson
 * @version $Id: SqlParameter.java,v 1.2 2005/06/01 12:43:18 safonov Exp $
 */
public class SqlParameter {

  private String name;

  /**
   * SQL type constant from java.sql.Types
   */
  private int type;

  /**
   * used for types that are user-named like: STRUCT, DISTINCT, JAVA_OBJECT, and named array types.
   */
  private String typeName;


  /**
   * Add a new anonymous parameter
   */
  public SqlParameter(int type) {
    this(null, type, null);
  }

  public SqlParameter(int type, String typeName) {
    this(null, type, typeName);
  }

  public SqlParameter(String name, int type) {
    this(name, type, null);
  }

  public SqlParameter(String name, int type, String typeName) {
    this.name = name;
    this.type = type;
    this.typeName = typeName;
  }

  /**
   * Convert a list of JDBC types, as defined in the java.sql.Types class, to a List of SqlParameter
   * objects as used in this package
   */
  public static List<SqlParameter> sqlTypesToAnonymousParameterList(int[] types) {
    List<SqlParameter> l = new LinkedList<SqlParameter>();
    if (types != null) {
      for (int i = 0; i < types.length; i++) {
        l.add(new SqlParameter(types[i]));
      }
    }
    return l;
  }

  public String getName() {
    return name;
  }

  public int getSqlType() {
    return type;
  }

  public String getTypeName() {
    return typeName;
  }

}
