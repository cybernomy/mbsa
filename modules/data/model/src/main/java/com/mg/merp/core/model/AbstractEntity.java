/**
 *
 */
package com.mg.merp.core.model;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import com.mg.framework.service.PersistentObjectHibernate;

/**
 * @author Valentin A. Poroxnenko
 *
 * Sep 20, 2015
 */
@MappedSuperclass
@FilterDef(name="__mg_tenantFilter", parameters= { @ParamDef( name="sysClientId", type="long") }, defaultCondition="CLIENT_ID = :sysClientId")
@Filter(name="__mg_tenantFilter")
public class AbstractEntity extends PersistentObjectHibernate {

}
