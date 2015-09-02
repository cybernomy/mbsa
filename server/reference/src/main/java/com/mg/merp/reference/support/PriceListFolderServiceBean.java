/*
 * PriceListFolderServiceBean.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.reference.support;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.UserProfile;
import com.mg.framework.api.orm.FlushMode;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.security.SecuritySystem;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.FolderRights;
import com.mg.merp.reference.CatalogFolderServiceLocal;
import com.mg.merp.reference.PriceListFolderServiceLocal;
import com.mg.merp.reference.PriceListSpecServiceLocal;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.CatalogFolder;
import com.mg.merp.reference.model.PriceListFolder;
import com.mg.merp.reference.model.PriceListHead;
import com.mg.merp.reference.model.PriceListSpec;
import com.mg.merp.security.model.Groups;

/**
 * ������-��������� "����� �����-������"
 * 
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: PriceListFolderServiceBean.java,v 1.7 2008/05/16 05:54:25 alikaev Exp $
 */
@Stateless(name="merp/reference/PriceListFolderService")
@PermitAll
public class PriceListFolderServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PriceListFolder, Integer> implements PriceListFolderServiceLocal {

	private PriceListSpecServiceLocal priceListSpecService = null;

	/* (non-Javadoc)
	 * @see com.mg.merp.reference.PriceListFolderServiceLocal#addFromCatalog(int, int, boolean)
	 */
	public void addFromCatalog(int catalogFolderId, int priceListFolderId, boolean createSpec) {
		doAddFromCatalog(catalogFolderId, priceListFolderId, createSpec);
	}

	/**
	 * C������ ������������ �����-����� �� ������ ��������
	 * 
	 * @param catalogFolderId	
	 * 					- ����� ��������
	 * @param priceListFolderId	
	 * 					- ����� �����-�����
	 * @param createSpec		
	 * 					- ������� �������� ������������ �����-�����
	 */
	protected void doAddFromCatalog(int catalogFolderId, int priceListFolderId, boolean createSpec) {
		CatalogFolderServiceLocal catalogFolderService = (CatalogFolderServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/CatalogFolder");
		PersistentManager pm = ServerUtils.getPersistentManager();

		CatalogFolder catFolder = pm.find(CatalogFolder.class, catalogFolderId);
		PriceListFolder priceListFolder = pm.find(PriceListFolder.class, priceListFolderId);
		PriceListHead plh = priceListFolder.getPriceListHead();

		for (CatalogFolder catalogFolder : catalogFolderService.getNestedCatalogFolders(pm.find(CatalogFolder.class, catalogFolderId), true, true)) {
			PriceListFolder priceListFolderParent = null;
			CatalogFolder catalogFolderParent = catalogFolder.getCatalogFolder();
			//����� �����-�������� �� ����� � ������
			if (catalogFolderParent == null || catalogFolder.getId() == catFolder.getId())
				priceListFolderParent = priceListFolder;
			else 
				priceListFolderParent = OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(PriceListFolder.class)
						.add(Restrictions.eq("PriceListHead", plh))
						.add(Restrictions.eq("FName", catalogFolderParent.getFName()))
						.setFlushMode(FlushMode.MANUAL));
			//���������� �����
			PriceListFolder plFolder = createPriceListFolderAndAddedRigths(plh, priceListFolderParent, catalogFolder);
			//�������� ������������ �����-�����
			if (createSpec) {
				List<Catalog> catalogs = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(Catalog.class)
						.add(Restrictions.eq("Folder", catalogFolder))
						.setFlushMode(FlushMode.MANUAL));
				for (Catalog catalog : catalogs) 
					createPriceListSpec(plFolder, catalog, BigDecimal.ZERO, BigDecimal.ZERO, catalog.getFullName(), false, plh.getId(), plh.getCreateDate());
			}
		}
	}

	/**
	 * �������� ������������ ����-�����
	 * 
	 * @param folder	- �����
	 * @param catalog	- �����
	 * @param price		- ������� ����
	 * @param lastCost	- ���� ���������� �������
	 * @param sName		- ������������ ������� �����-�����
	 * @param canceled	- <code>true</code> - ������� ������������, ����� �� ������������
	 * @param priceListHeadId	- ������������� ��������� �����-�����
	 * @param actDate	- ���� ������ �������� �������
	 */
	private void createPriceListSpec(PriceListFolder folder, Catalog catalog, BigDecimal price, BigDecimal lastCost, String sName, boolean canceled, Integer priceListHeadId, Date actDate) {
		PriceListSpec priceListSpec = getPriceListSpecService().initialize();
		priceListSpec.setFolder(folder);
		priceListSpec.setCatalog(catalog);
		priceListSpec.setPrice(price);
		priceListSpec.setLastCost(lastCost);
		priceListSpec.setCanceled(canceled);
		priceListSpec.setSName(sName);
		priceListSpec.setPriceListHeadId(priceListHeadId);
		priceListSpec.setActDate(actDate);
		getPriceListSpecService().create(priceListSpec);		
	}

	/**
	 * ���������� ����� �����-����� � ��������� ���� �� ���
	 * 
	 * @param priceListHead
	 * 				- ��������� �����-�����
	 * @param priceListFolderParent
	 * 				- ����� �������� ��� ����������� �����
	 * @param catalogFolder
	 * 				- ����� ��� �� ������� ��������� ����� �����-����� 
	 * @return
	 * 				- ��������� ����� �����-�����
	 */
	private PriceListFolder createPriceListFolderAndAddedRigths(PriceListHead priceListHead, PriceListFolder priceListFolderParent, CatalogFolder catalogFolder) {
		//���������� ����� ����� -�����
		PriceListFolder plFolder = initialize();
		plFolder.setPriceListHead(priceListHead);
		plFolder.setParent(priceListFolderParent);
		plFolder.setFName(catalogFolder.getFName());
		create(plFolder);
		PersistentManager pm = ServerUtils.getPersistentManager();
		pm.flush();
		UserProfile user = ServerUtils.getUserProfile();
		List<FolderRights>  catFolderRights = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(FolderRights.class)
				.add(Restrictions.eq("FolderId", catalogFolder.getId()))
				.add(Restrictions.eq("FolderPart",(short) 1))
				.setFlushMode(FlushMode.MANUAL));
		for (FolderRights catFolderRight : catFolderRights) {
			Groups group = catFolderRight.getSecGroups();
			if (group.getId() != SecuritySystem.ADMIN_GROUP && !user.getGroupsCommaText().contains(group.getName())) {
				//���������� ���� �� ����� ����� - �����
				FolderRights plFolderRights = new FolderRights();
				plFolderRights.setFolderId(plFolder.getId());
				plFolderRights.setFolderPart((short) 2);
				plFolderRights.setPermission(catFolderRight.isPermission());
				plFolderRights.setSecGroups(catFolderRight.getSecGroups());
				ServerUtils.getPersistentManager().persist(plFolderRights);
			}
		}
		return plFolder;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#doMove(java.util.List, java.lang.Object)
	 */
	@Override
	public boolean doMove(List<Integer> primaryKeys, Object targetEntity) {
		boolean result = false;
		for (Integer key : primaryKeys) {
			PriceListFolder entity = load(key);
			PriceListFolder targetFolder = (PriceListFolder) targetEntity;
			//�� �������� �������� ����� � ����� �� ���� ����, ������������� ������ ���� ������ �������������� ���������, � ���������
			//������ ���������� ��������� ������ ��������
			if (entity.getParent() != null && entity.getId() > targetFolder.getId() && entity.getParent().getId() != targetFolder.getId()) {
				entity.setParent(targetFolder);
				result = true;
			}
		}
		return result;
	}

	/**
	 * ���������� ������-��������� "������������ �����-�����"
	 * 
	 * @return
	 */
	private PriceListSpecServiceLocal getPriceListSpecService() {
		if (priceListSpecService == null)
			priceListSpecService = (PriceListSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/PriceListSpec");
		return priceListSpecService;
	}

}
