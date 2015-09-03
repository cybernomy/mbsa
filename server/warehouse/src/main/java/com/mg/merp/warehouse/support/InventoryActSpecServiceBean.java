/*
 * InventoryActSpecServiceBean.java
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

package com.mg.merp.warehouse.support;

import javax.ejb.Stateless;

import com.mg.framework.utils.MathUtils;
import com.mg.merp.document.CreateSpecificationInfo;
import com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.warehouse.InventoryActHeadServiceLocal;
import com.mg.merp.warehouse.InventoryActSpecServiceLocal;
import com.mg.merp.warehouse.model.InventoryActSpec;

/**
 * Бизнес-компонент "Спецификация актов инвентаризации" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: InventoryActSpecServiceBean.java,v 1.7 2008/08/20 11:53:08 sharapov Exp $
 */
@Stateless(name="merp/warehouse/InventoryActSpecService") //$NON-NLS-1$
public class InventoryActSpecServiceBean extends GoodsDocumentSpecificationServiceBean<InventoryActSpec, Integer> implements InventoryActSpecServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected int getDocSectionIdentifier() {
		return InventoryActHeadServiceLocal.DOCSECTION;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#initializeForBulkCreate(com.mg.merp.document.model.DocHead, com.mg.merp.document.CreateSpecificationInfo)
	 */
	@Override
	protected InventoryActSpec initializeForBulkCreate(DocHead docHead, CreateSpecificationInfo goodsInfo) {
		InventoryActSpec specEntity = super.initializeForBulkCreate(docHead, goodsInfo);
		// если осуществляется операция "инвентаризация склада", то сумму и количество в доп. ЕИ берем из
		// информации о номеклатуре для создания спецификации акта инвентаризации
		// (если инвентаризация будет проводиться по правилу: одна строка ТМЦ по всем ценам прихода,
		// что-бы сумма одинаковых позиций каталога не вычислялась путем умножения кол-ва на 'усредненную цену', а была математической суммой)
		if(goodsInfo instanceof CreateInventoryActSpecInfo) {
			specEntity.setPrice(((CreateInventoryActSpecInfo) goodsInfo).getPrice());
			specEntity.setSumma(((CreateInventoryActSpecInfo) goodsInfo).getSumma());
			specEntity.setSumma1(((CreateInventoryActSpecInfo) goodsInfo).getSumma1());
			specEntity.setQuantity2(((CreateInventoryActSpecInfo) goodsInfo).getQuantity2());
			specEntity.setAdjusted(true);
		} else { // добавление позиции из окна подбора номенклатуры (defect #4817)
			specEntity.setPrice(goodsInfo.getPrice());
			specEntity.setQuantity(null);
			specEntity.setRealQuantity(goodsInfo.getQuantity1());
			specEntity.setQuantity2(null);
			specEntity.setRealQuantity2(goodsInfo.getQuantity2());
			specEntity.setRealSumma(specEntity.getSumma1());
			specEntity.setSumma(null);
			specEntity.setSumma1(null);
			specEntity.setAdjusted(true);
		}
		return specEntity;
	}
	
	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#doAdjust(com.mg.merp.document.model.DocSpec)
	 */
	@Override
	protected void doAdjust(InventoryActSpec entity) {
		//super.doAdjust(entity);
		entity.setRealSumma(MathUtils.multiply(entity.getPrice1(), entity.getRealQuantity(), getRoundContext()));
	}

}
