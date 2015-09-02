/*
 * AbstractDataDomainImpl.java
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
package com.mg.framework.generic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.BrowseCond;
import com.mg.framework.api.DataDomain;
import com.mg.framework.api.jdbc.RowMapper;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.utils.ServerUtils;

/**
 * @author Oleg V. Safonov
 * @version $Id: AbstractDataDomainImpl.java,v 1.9 2006/09/28 12:24:12 safonov Exp $
 *
 * @deprecated
 */
@Deprecated
public abstract class AbstractDataDomainImpl extends AbstractDomainImpl	implements DataDomain {

//    private BeanFeature[] getFeatures(int classId) throws ApplicationException {
//        List<BeanFeature> list = getJdbcTemplate().query("select f.id, f.name, f.datatype from featurelink fl, feature f " +
//                "where (fl.class_id = ?) and (fl.rec_id is null) and (f.id = fl.feature_id)", new Object[] {classId}, 
//                new RowMapper<BeanFeature>(){
//                    public BeanFeature mapRow(ResultSet rs, int rowNum) throws SQLException {
//                        BeanFeature result = new BeanFeature(rs.getInt("id"), rs.getString("name"), "", rs.getShort("datatype"));
//                        if (result.data_type == 5) //entity
//                            result.val_field_name = "val_text";
//                        else
//                            result.val_field_name = "val";
//                        return result;
//                    }
//                }
//        );
//        BeanFeature[] result = new BeanFeature[0]; 
//        return list.toArray(result);
//    }

    private boolean isHasFeature(int classId) throws ApplicationException {
        List<Boolean> list = getJdbcTemplate().query("select (select count(*) from systemconfig where exists(select * from featurelink fl where fl.class_id = c.id)) has_feature " +
                "from sys_class c where c.id = ?", new Object[] {classId}, 
                new RowMapper<Boolean>(){
                    public Boolean mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Boolean(rs.getInt("has_feature") > 0);
                    }
                }
        );
        return list.get(0).booleanValue();
    }
    
//    private AttributeMetadata[] getAttributeMetadata() throws ApplicationException {
//        List<EntityAttribute> attrList = ServerUtils.getRepository().getEntity("").getAllAttributes();
//        AttributeMetadata[] result = new AttributeMetadata[attrList.size()];
//        int i = 0;
//        for (EntityAttribute attr : attrList) {
//            result[i].attr_type = AttributeType.from_int(0);
//            result[i].browse_caption = attr.getDataItem().getHeader();
//            result[i].caption = attr.getDataItem().getLongLabel();
//            result[i].caption_control = "";
//            result[i].caption_control_property = "";
//            result[i].value_control = "";
//            result[i].value_control_property = "";
//            result[i].required = attr.getDataItem().getDomain().isNotNull();
//            result[i].string_length = attr.getDataItem().getDomain().getLength();
//            result[i].visible = true;
//            result[i].read_only = false;
//            result[i].read_only_ui = false;
//            result[i].enum_string_values = new String[0];
//            result[i].is_text = false;
//            result[i].is_parent_key = false;
//            i++;
//        }
//        return result;
//    }
    
	protected abstract String getPersistentClassName();
	
	protected abstract String createBrowseSQL();
	
//    protected BeanMetadata doLoadMetadata() throws ApplicationException {
//        BeanMetadata result = super.doLoadMetadata();
//        result.feature_list = getFeatures(result.class_id);
//        result.have_feature = isHasFeature(result.class_id);
////        result.attr_metadata_list = getAttributeMetadata();
//        return result;
//    }
    
	protected Class getPersistentClass() throws ClassNotFoundException {
		return ServerUtils.loadClass(getPersistentClassName());
	}
	
	public PersistentManager getPersistentManager() {
		return ServerUtils.getPersistentManager();
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataDomain#make(com.mg.framework.api.AttributeMap)
	 */
	public PersistentObject make(AttributeMap attributes)
			throws ApplicationException {
		return getPersistentManager().make(getPersistentClassName(), attributes);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataDomain#initialize(com.mg.framework.api.AttributeMap)
	 */
	public PersistentObject initialize(AttributeMap attributes)
			throws ApplicationException {
		return getPersistentManager().make(getPersistentClassName(), attributes);
	}

    public Object create(AttributeMap attributes) throws ApplicationException {
        return this.create(make(attributes));
    }
    
	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataDomain#create(com.mg.framework.api.orm.PersistentObject)
	 */
	public Object create(PersistentObject entity) throws ApplicationException {
		return getPersistentManager().create(entity).getPrimaryKey();
	}

    public void store(AttributeMap attributes) throws ApplicationException {
        store(make(attributes));
    }
    
	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataDomain#store(com.mg.framework.api.orm.PersistentObject)
	 */
	public void store(PersistentObject entity) throws ApplicationException {
		getPersistentManager().store(entity);
	}

	public void remove(Object key) throws ApplicationException {
		getPersistentManager().remove(getPersistentClassName(), key);
	}

	public PersistentObject load(Object key) throws ApplicationException {
		return getPersistentManager().find(getPersistentClassName(), key);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataDomain#loadBrowse(com.mg.framework.api.BrowseCond)
	 */
	public Object loadBrowse(BrowseCond cond) throws ApplicationException {
		try {
			//DataSetWraper ds = new DataSetWraper();
			//String data = ds.getStringData(createBrowseSQL());
			//ByteArrayInputStream bis ;
			//bis.
			// TODO Auto-generated method stub
			//return data.getBytes("UTF-8");
			return null;
		}
		catch (Exception e) {
			throw new ApplicationException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataDomain#loadView(java.lang.Object, java.util.Collection, java.lang.String)
	 */
	public AttributeMap loadView(Object primaryKey, Collection keyOfAttributes,
			String viewName) throws ApplicationException {
        PersistentObject pObj = load(primaryKey);
        AttributeMap result = new com.mg.framework.support.LocalDataTransferObject();
//        List<FormField> fieldList = ServerUtils.getRepository().getForm("").getFields();
//        for (FormField field : fieldList) {
//            String name = field.getAttributeName();
//            result.put(name, pObj.getAttribute(name));
//        }
        return result;
		//return load(primaryKey).getAllAttributes();
	}

	public Object clone(Object key, boolean cloneDetail) throws ApplicationException {
		//TODO
		return null;
	}

	public void changeParent(Object key, Object newParent) throws ApplicationException {
		//TODO
	}

}
